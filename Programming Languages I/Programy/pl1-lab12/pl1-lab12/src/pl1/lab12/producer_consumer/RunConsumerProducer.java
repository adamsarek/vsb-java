package pl1.lab12.producer_consumer;

public class RunConsumerProducer {
	public static void main(String[] args) {
		Pool pool = new NaivePool();
		Producer p = new Producer(pool, 100);
		Consumer c = new Consumer(pool);
		p.start();
		c.start();
	}
}
