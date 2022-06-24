package pl1.lab05;

public class Task03 {
	public static void main(String[] args) {
		MyDate md = new MyDate(2019, 10, 14);
		MyDate md1 = new MyDate(2019, 10, 14);
		MyDate md2 = new MyDate(2018, 10, 14);
		MyDate md3 = md;
		
		if (md.equals(md1)) {
			System.out.println("md and md1 are same");
		} else {
			System.out.println("md and md1 are different");
		}
		
		if (md.equals(md2)) {
			System.out.println("md and md2 are same");
		} else {
			System.out.println("md and md2 are different");
		}
		if (md.equals(md3)) {
			System.out.println("md and md3 are same");
		} else {
			System.out.println("md and md3 are different");
		}
	}
}
