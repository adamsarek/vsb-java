package pl1.lab12.producer_consumer;

public class NaivePool implements Pool {

	private volatile boolean full = false;
	
	private volatile int value;
	@Override
	public void put(int aValue) {
		while(full) {
			
		}
		value = aValue;
		full = true;
		
	}

	@Override
	public int get() {
		while(!full) {
			
		}
		int result = value;
		full = false;
		return result;
	}

}
