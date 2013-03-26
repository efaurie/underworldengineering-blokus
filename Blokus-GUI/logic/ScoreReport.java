package logic;

public class ScoreReport {
	
	String[] names;
	String[] colors;
	int[] scores;
	String[] times;
	int[] cornersBlocked;
	
	public ScoreReport() {
		init();
	}
	
	private void init() {
		names = new String[4];
		scores = new int[4];
		times = new String[4];
		colors = new String[4];
		cornersBlocked = new int[4];
	}
	
	public void setData(int id, String name, String color, int score, String time, int corners) {
		names[id] = name;
		scores[id] = score;
		times[id] = time;
		colors[id] = color;
		cornersBlocked[id] = corners;
	}
	
	public String[] getNames() {
		return names;
	}
	
	public String[] getColors() {
		return colors;
	}
	
	public int[] getScores() {
		return scores;
	}
	
	public String[] getTimes() {
		return times;
	}
	
	public int[] getCornersBlocked() {
		return cornersBlocked;
	}
}
