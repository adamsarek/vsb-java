package pl1.lab12.producer_consumer;

public class Producer extends Thread {
	private Pool pool;
	private int values;

	public Producer(Pool pool, int values) {
		this.pool = pool;
		this.values = values;
	}

	public void run() {
		for (int i = 0; i < values; i++) {
			pool.put(i); // Wait until the previous value is consumed
			System.out.println("Producer put: " + i);
			try {
				Thread.sleep((int) (Math.random() * 1000));
			} catch (InterruptedException e) {
			}
		}
	}
}