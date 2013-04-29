package logic;

import java.util.Arrays;
import java.util.Comparator;

public class StatPolicy {
	
	private static final int[] RANK_THRESHOLDS = {-500, -250, 0, 250, 500, 750, 1000, 1250, 1500, 1750};
	private static final double[] TIMING_MULTIPLIERS = {1.0, 0.9, 0.8, 0.7, 0.6, 0.5, 0.4, 0.3, 0.2, 0.1}; 
	private static final int TIMING_BASE = 100;
	
	private static final double[] TIME_BONUS_THRESHOLD = {0.4, 0.45, 0.5, 0.55, 0.6, 0.65, 0.7, 0.75, 0.8, 0.85};
	private static final int TIME_BONUS = 50;
	private static final int[] RANK_DIFFERENCE_BONUS = {0, 25, 50, 75, 100, 125, 150, 175, 200, 300};
	
	public StatPolicy() {
		//no-op
	}
	
	public int[] calculateScores(Player[] gamePlayers) {
		Player[] players = new Player[4];
		players[0] = gamePlayers[0];
		players[1] = gamePlayers[1];
		players[2] = gamePlayers[2];
		players[3] = gamePlayers[3];
		int[] scores = {0, 0, 0, 0};
		//sort players in order of score
		Arrays.sort(players, new Comparator<Player>(){
	        @Override
	        public int compare(Player x, Player y) {
	            return x.getScore() - y.getScore();
	        }
	    });
		
		//add victory/defeat bonuses
		int rankDif;
		for(int i = 0; i < players.length; i++) {
			//won against these players
			for(int j = 0; j < i; j++) {
				rankDif = players[j].getRank() - players[i].getRank();
				//if winning players rank is smaller, apply bonus
				if(rankDif > 0) {
					scores[i] = scores[i] + RANK_DIFFERENCE_BONUS[rankDif];
				}
			}
			//lost to these players
			for(int j = i+1; i < players.length; i++) {
				rankDif = players[j].getRank() - players[i].getRank();
				//if winning players rank is smaller, apply penalty
				if(rankDif < 0) {
					scores[i] = scores[i] - RANK_DIFFERENCE_BONUS[-1*rankDif];
				}
			}
		}
		
		//add timing bonus
		for(int i = 0; i < 4; i++) {
			double timePerTurn = players[i].getTimeInControlSec()/players[i].getNumTurns();
			int rank = players[i].getRank();
			if(timePerTurn < (TIMING_BASE * TIMING_MULTIPLIERS[rank-1])*TIME_BONUS_THRESHOLD[rank-1])
				scores[i] = scores[i] + TIME_BONUS;
		}
		
		//add score
		int maxScore = 89 + 15;
		for(int i = 0; i < 4; i++) {
			int gameScore = players[i].getScore();
			if(gameScore < 0)
				gameScore = 89 + gameScore;
			else if(gameScore < 15)
				gameScore = maxScore - gameScore;
			else
				gameScore = maxScore;
			scores[i] = scores[i] + gameScore;
		}
		
		return scores;
	}
	
	public int getRank(int score) {
		for(int i = 0; i < RANK_THRESHOLDS.length; i++) {
			if(score < RANK_THRESHOLDS[i])
				return i+1;
		}
		return 10;
	}
	
	public int getTimeout(int rank) {
		return (int)(TIMING_BASE * TIMING_MULTIPLIERS[rank - 1]);
	}
}
