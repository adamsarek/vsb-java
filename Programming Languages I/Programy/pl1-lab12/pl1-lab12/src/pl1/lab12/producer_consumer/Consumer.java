package pl1.lab12.producer_consumer;

public class Consumer extends Thread {
	private Pool pool;

	public Consumer(Pool pool) {
		this.pool = pool;
	}

	public void run() {
		int value;
		for (int i = 0; i < 10; i++) {
			value = pool.get(); // Wait until the value is produced
			System.out.println("Consumer got: " + value);
		}
	}
}
