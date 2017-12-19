package JavaHomeWork;

public class Homework_02 {

	public static void main(String[] args) {
		
		/*第1題*/
		System.out.println("第1題:");
		/*計算2+4+6+8+...+1000*/
		int sum=0,count;
		for(count=1;count<=1000;count++){
			if(count%2==0){
				sum+=count;
			}
		}
		System.out.println(sum);
		/*第2題*/
		System.out.println("第2題:");
		/*用for寫10階乘*/
		int f_factorial=1;
		int f_multiplier=1;
		for(;f_multiplier <=10; f_multiplier++){
			f_factorial *= f_multiplier;
		}
		System.out.println(f_factorial);
		/*第3題*/
		System.out.println("第3題:");
		/*用while寫10階乘*/
		int w_factorial=1;
		int w_multiplier=1;
		while( w_multiplier <=10){
			w_factorial *= w_multiplier;
			w_multiplier++;
		}
		System.out.println(w_factorial);
		/*第4題*/
		System.out.println("第4題:");
		/*show:1 4 9 16 25 36 49 64 81 100*/
			int start=0,increment;
			for(increment=1;increment<=19;increment+=2){
				start+=increment;
				System.out.print(start+" ");
			}
			System.out.println();
		/*進階1*/
		System.out.println("進階1:");
		/*大樂透不要數字4*/
		 int sumlotto = 0, ten = 10; 
		 for (int lotto = 1; lotto <= 49 ; lotto++) {
		 if (lotto % ten == 4) { //去除個位數4
		 }
		 else if (lotto / ten == 4) { 
		 }                     //去除十位數4
		 else {
		 System.out.print(lotto + ",");
		 sumlotto++; //可選總計
		 }
		 }
		 System.out.println();
		 System.out.println("有" + sumlotto + "個號碼可選");
		/*進階2*/
		System.out.println("進階2:");
		/*數字倒三角形*/
			for (int i = 11; i >= 1; i--){
				for (int j = 1; j < i ; j++){
				System.out.print(j + " ");
				}
			System.out.println();
			}
		/*進階3*/
		System.out.println("進階3:");
		/*英文字母三角形*/
		int n1, n2, n3;
		for (n1 = 0; n1 <= 5; n1++) { 
			for (n2 = 0; n2 <= n1; n2++) { 
				n3 = 65 + n1; 
				System.out.print((char)n3);
			}
			System.out.println();
		}
	}

}
