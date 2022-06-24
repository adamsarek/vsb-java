package pl1.lab10;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class SortedByLength {

	// example of nested static class
	public static class NestedComparator implements Comparator<String> {
		@Override
		public int compare(String o1, String o2) {
			if(o1.length() < o2.length()) {
				return -1;
			}
			if(o1.length() > o2.length()) {
				return 1;
			}
			return 0;
		}
	}

	public static void main(String[] args) {

		Book bk = new Book();
		List<String> words = new LinkedList<>(bk.getWords());
		words.sort(new StringLengthComparator());

		// Nested static class
		//words.sort(new Book.NestedComparator());
		//words.sort(new NestedComparator());

		// Nested inner class
		// mame pristup k instancnym premennym
		//Book.NestedInnerComparator innerComparator = bk.new NestedInnerComparator();
		//words.sort(innerComparator);

		// example of local inner class
		// nemame pristup k instacnym premennym iba k statickym final do verzie JDK 1.7
		// od JDK 1.8 uz to je mozne
		class LocalComparator implements Comparator<String> {
			@Override
			public int compare(String o1, String o2) {
				if(o1.length() < o2.length()) {
					return -1;
				}
				if(o1.length() > o2.length()) {
					return 1;
				}
				return 0;
			}
		}
		//words.sort(new LocalComparator());

		// Anonymous class
		// pouziva sa v specifickych pripadoch kedy je pouzitie nutne vzhladom k moznej izolacii triedy
		// alebo napriklad v pripade, ze potrebujeme pracovat s lokalnymi premennymi
		// nemate chut prechadzat kopec kodu alebo nemozete nic menit a pridavat...atd...
		Comparator<String> anonymComparator = new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if(o1.length() < o2.length()) {
					return -1;
				}
				if(o1.length() > o2.length()) {
					return 1;
				}
				return 0;
			}
		};
		//words.sort(anonymComparator);

		// lambda comparator definition
		//Comparator<String> lambdaComparator = Comparator.comparing(s -> s.length());
		//words.sort(lambdaComparator);

		// method Reference comparator definition
		//Comparator<String> mrComparator = Comparator.comparing(String::length);
		//words.sort(mrComparator);

		for(String word: words) {
			System.out.println(word);
		}
	}
}
