import java.util.Random;

public class Lottery<E> {
	
	private E[] array;
	int currentIndex;
	
	public Lottery(E[] array) {
		this.array = array;
		shuffle();
		currentIndex = 0;
	}
	
	public void shuffle() {
		Random generator = new Random();
		for(int i = 0; i < array.length; i++) {
			int swapWith = generator.nextInt(array.length);
			swapIndecies(i, swapWith);
		}
	}
	
	private void swapIndecies(int i1, int i2) {
		E temp;
		temp = array[i1];
		array[i1] = array[i2];
		array[i2] = temp;
	}
	
	public E getNext() {
		if(currentIndex >= array.length)
			return null;
		else {
			return array[currentIndex++];
		}
	}
}
