package testdb;

import java.io.*;
import java.sql.*;
import java.util.Scanner;
import java.util.InputMismatchException;

public class menu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			Test ts = new Test();
			int check = 0;     // 메뉴 선택
			
			System.out.println("---------------------------------------------");
			System.out.println("    1.  고객 등록                     2.  고객 정보 수정 ");
			System.out.println("    3.  예약된 호텔 보기           4.  예약된 항공권 보기");
			System.out.println("    5.  호텔 등록                     6.  호텔 삭제");
			System.out.println("    7.  항공권 등록                  8.  항공권 삭제");
			System.out.println("    9.  호텔 검색                     10. 항공권 검색");
			System.out.println("    11. 호텔 에약                     12. 호텔 예약 취소");
			System.out.println("    13. 항공권 예약                  14. 항공권 예약 취소");
			System.out.println("    15. 고객 삭제                     99. 프로그램 종료");
			System.out.println("---------------------------------------------");
			System.out.print("메뉴 선택   :  ");
			
			int flag = 1;
			
			try {
				check = sc.nextInt();
				sc.nextLine();
			}
			catch(InputMismatchException e)	{
				flag = 0;
				sc.nextLine();
				System.out.println("메뉴를 다시 선택해주세요!");
			}
			
			if(flag == 0)
				continue;
			
			//check = sc.nextInt();
			//sc.nextLine();
			
			if(check == 1)
				ts.Addcust();
			else if(check ==3)
				ts.printrehotel();
			else if(check == 5)
				ts.Addhotel();
			else if(check == 6)
				ts.Delhotel();
			else if(check == 7)
				ts.Addair();
			else if(check == 8)
				ts.Delair();
			else if(check == 11)
				ts.Hotelreservation();
			else if(check == 12)
				ts.Delrehotel();
			else if(check == 13)
				ts.Airplanereservation();
			else if(check == 14)
				ts.Delreair();
			else if(check == 15)
				ts.Delcust();
			else if(check == 99) {
				System.out.println("종료.");
				break;
			}
			else
				System.out.println("메뉴를 다시 선택해주세요!");
		}
	}
}
