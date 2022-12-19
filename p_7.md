package src3;

public class p_5 {
	static long startTime = 0;
	
	public static void main(String[] args) {
		p_5 th1 = new p_5();
		th1.start();
		startTime = System.currentTimeMillis();
		
		for(int i=0; i <300; i++)
			System.out.printf("%s", new String("-"));
		
		System.out.println("소요시간1:" +(System.currentTimeMillis()- startTime));
	}
}
class p_51 extends Thread {
		public void run() {
		for(int i=0; i < 300; i++)
			System.out.printf("%s", new String("|"));
		
		System.out.print("소요시간2:"+(System.currentTimeMillis()- startTime));
	}

}
