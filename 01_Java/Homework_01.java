package JavaHomeWork;

public class Homework_01 {

	public static void main(String[] args) {
		
		/*第一題*/
		System.out.println("第一題:");
		int a =12;
		int b =6;
		int sum = a+b;
		int product = a*b;
		System.out.println(a+"與"+b+"之和等於"+sum);
		System.out.println(a+"與"+b+"之積"+product);
		/*第二題*/
		System.out.println("第二題:");	
		int egg=200;
		int dozen=12;
		int quotient,remainder;
		quotient= egg/dozen;
		remainder= egg%dozen;
		System.out.println(egg+"顆蛋共是"+quotient+"打"+remainder+"顆");	
		/*第三題*/
		System.out.println("第三題:");		
		int total_seconds=256559;
		int seconds=total_seconds%60;
		int total_mins=total_seconds/60;
		int mins=total_mins%60;
		int total_hours=total_mins/60;
		int hours=total_hours%24;
		int total_days=total_hours/24;
		int days=total_days%24;
	
		System.out.println("256559秒等於"
		+days+"天"+hours+"小時"+mins+"分"+seconds+"秒");
		/*第三題驗算*/	
//		int min=60;
//		int hour=min*60;
//		int day=hour*24;
//		int check=days*day+hours*hour+mins*min+seconds;
//		System.out.println("(驗算："
//		+days+"天"+hours+"小時"+mins+"分"+seconds+"秒"
//		+"等於"+check+"秒)");
		/*第四題*/
		System.out.println("第四題:");
		final float PI=3.1415f;
		int r=5;
		float circulararea=PI*r*r;
		float circumference=2*PI*r;
		System.out.println("半徑為"+r);
		System.out.println("圓面積等於"+circulararea);
		System.out.println("圓周長等於"+circumference);
		/*第五題*/
		System.out.println("第五題:");
	
		int presentvalue=1_500_000;//150萬
		double amount=1.0;
		double principal=1;    
        double rate=0.02;       //年利率2%          
        for(int year=1;year<=10;year++){        	
            amount=principal*Math.pow(principal+rate,year);
    
        }
        double finalvalue=presentvalue*amount;
        System.out.println(finalvalue);

		/*第六題*/
		System.out.println("第六題:");
		System.out.println(5+5);	//運算數值5+5=10
		System.out.println(5+'5');	//'5'的ASCII內碼為53,5+53=58
		System.out.println(5+"5");	//將字串"5"加到5後面並排顯示55
	}
	

}
