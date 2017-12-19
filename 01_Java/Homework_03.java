package JavaHomeWork;

public class ch5practice {


	public static void main(String[] args) {
		
		/*1. for + while*/
		System.out.println("使用for迴圈+while迴圈輸出九九乘法表:");
		int fw_i, fw_j;
		
		for (fw_i = 1;  fw_i <= 9; fw_i++) {
			fw_j =0;
			while ( fw_j <= 8) {
				fw_j++;
				System.out.print(fw_i +"*"+ fw_j +"="+ fw_i * fw_j +" ");
			}
			System.out.println();
		}	
		/*2. for + do while*/
		System.out.println("使用for迴圈+do while迴圈輸出九九乘法表:");
		int fd_i, fd_j;
		for (fd_i = 1;  fd_i <= 9; fd_i++) {
			fd_j = 0;
			do{
				fd_j++;
				System.out.print(fd_i +"*"+ fd_j +"="+ fd_i * fd_j +" ");
			} while (fd_j <= 8);
			System.out.println();
		}
		/*3. while + do while*/
		System.out.println("使用while迴圈+do while迴圈輸出九九乘法表:");
		int wd_i, wd_j;
		wd_i=0;
		while ( wd_i <= 8) {
			wd_i++;
			wd_j = 0;
			do{
				wd_j++;
				System.out.print(wd_i +"*"+ wd_j +"="+ wd_i * wd_j +" ");
			} while (wd_j <= 8);
			System.out.println();
		}
	}


}
