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
			Customer cust = new Customer();
			Hotel ht = new Hotel();
			Airplane air = new Airplane();
			Hotelreservation reht = new Hotelreservation();
			Airplanereservation reair = new Airplanereservation();
			
			int check = 0;		// 메뉴 선택
			     
			System.out.println("-----------------------------------------------------------");
			System.out.println("    1.  고객 등록                                         2.  고객 정보 수정 ");
			System.out.println("    3.  예약된 호텔 보기                               4.  예약된 항공권 보기");
			System.out.println("    5.  호텔 등록                                         6.  호텔 삭제");
			System.out.println("    7.  항공권 등록                                      8.  항공권 삭제");
			System.out.println("    9.  호텔 전체 목록 보기                           10. 호텔명으로 호텔상품 검색    ");
			System.out.println("    11. 최대 인원 수로 호텔상품 검색              12. 호텔 가격으로 호텔상품 검색 ");
			System.out.println("    13. 항공권 전체 목록 보기                        14. 항공사로 항공권 검색");
			System.out.println("    15. 항공권 가격으로 항공권 검색               16. 호텔 에약");
			System.out.println("    17. 호텔 예약 취소                                  18. 항공권 예약");
			System.out.println("    19. 항공권 예약 취소                               20. 고객 명단 보기");
			System.out.println("    21. 고객 삭제                                         99. 프로그램 종료");
			System.out.println("-----------------------------------------------------------");
			System.out.print("메뉴 선택   :  ");
			
			int flag = 1;
			
			try {
				check = sc.nextInt();
				sc.nextLine();
			}
			catch(InputMismatchException e)	{
				flag = 0;
				sc.nextLine();
				System.out.println("메뉴를 다시 선택해주세요! :  ");
			}
			
			if(flag == 0)
				continue;
			
			if(check == 1)				// 1. 고객 등록
				cust.Addcust();
			else if(check == 2)			// 2. 고객 정보 수정
				cust.modifycust();
			else if(check ==3)			// 3. 예약된 호텔 보기
				reht.printrehotel();
			else if(check == 4)			// 4. 예약된 항공권 보기
				reair.printreair();
			else if(check == 5)			// 5. 호텔 등록
				ht.Addhotel();
			else if(check == 6)			// 6. 호텔 삭제
				ht.Delhotel();
			else if(check == 7)			// 7. 항공권 등록
				air.Addair();
			else if(check == 8)			// 8. 항공권 삭제
				air.Delair();
			else if(check == 9)			// 9. 호텔 전체 목록 보기
				ht.printht();
			else if(check == 10)		// 10. 호텔명으로 호텔상품 검색
				ht.searchht_name();
			else if(check == 11)		// 11. 최대 인원 수로 호텔 상품 검색
				ht.searchht_roomsize();
			else if(check == 12)		// 12. 호텔 가격으로 호텔삼품 검색
				ht.searchht_price();
			else if(check == 13)		// 13. 항공권 전체 목록 보기
				air.printair();
			else if(check == 14)		// 14. 항공사로 항공권 검색
				air.searchair_airname();
			else if(check == 15)		// 15. 항공권 가격으로 항공권 검색
				air.searchair_price();
			else if(check == 16)		// 16. 호텔 예약
				reht.Hotelreservation();
			else if(check == 17)		// 17. 호텔 에약 취소
				reht.Delrehotel();
			else if(check == 18)		// 18. 항공권 예약
				reair.Airplanereservation();
			else if(check == 19)		// 19. 항공권 예약 취소
				reair.Delreair();
			else if(check == 20)		// 20. 고객 명단 보기
				cust.printcust();
			else if(check == 21)		// 21. 고객 삭제
				cust.Delcust();		
			else if(check == 99) {		// 99. 프로그램 종료
				System.out.println("종료.");
				break;
			}
			else
				System.out.println("메뉴를 다시 선택해주세요! :  ");
		}
	}
}
