package testdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Hotelreservation {
	public void printrehotel() {	// 예약된 호텔 보기
		try{			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(
					"jdbc:mysql://192.168.56.101:4567/hotel","hhs","a123456789");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT Hotelreservation.hotelreservationid, Customer.custname, Hotel.hotelname, Hotel.roomsize, Hotel.hotelprice, Hotelreservation.checkin, Hotelreservation.checkout FROM Customer, Hotel, Hotelreservation WHERE Customer.custid = Hotelreservation.custid AND Hotel.hotelid = Hotelreservation.hotelid");
			
			System.out.println("============================================================================");
			System.out.println("호텔 예약 번호          고객이름          호텔 이름          최대 인원 수          가 격          체크인 날짜          체크아웃 날짜");
			System.out.println("============================================================================");
			while(rs.next())
				System.out.println("    "+rs.getInt(1)+"         "+rs.getString(2)+"              "+rs.getString(3)+"                    "+rs.getString(4)+"        "+rs.getString(5)+"     "+rs.getString(6)+"      "+rs.getString(7));
			System.out.println("============================================================================");
			
			con.close();
		}catch(Exception e){ System.out.println(e);}
	}
	
	public void Hotelreservation() {    // 호텔 에약
		int custid = 0;
		int hotelid = 0;
		String checkin;
		String checkout;
		int num = 0;
		
		Scanner sc = new Scanner(System.in);
		ResultSet rs=null;
		
		String query = "insert into Hotelreservation(custid, hotelid, checkin, checkout) values(?, ?, ?, ?);";
		PreparedStatement pstmt = null;		
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(
					"jdbc:mysql://192.168.56.101:4567/hotel","hhs","a123456789");
			System.out.println("OK"); 
			
			Statement stmt=con.createStatement();

			System.out.println("예약할 고객의 고유 번호(custid) :");
			
			int flag1 = 1;
			
			try {
				custid = sc.nextInt();
				sc.nextLine();
				rs=stmt.executeQuery("SELECT count(*) FROM Customer where custid ="+ custid);
				
				while(rs.next()) {	
					num = rs.getInt(1);
				}

				if(num == 0) {
					System.out.println("존재하지 않는 고객 번호입니다.");
					return;
				}
			}
			catch(InputMismatchException e)	{
				flag1 = 0;
				sc.nextLine();
				System.out.println("호텔 예약 실패!");
			}
			
			System.out.println("예약할 호텔의 상품번호(hotelid) :");
			
			int flag2 = 1;
			
			try {
				hotelid = sc.nextInt();
				sc.nextLine();
				rs=stmt.executeQuery("SELECT count(*) FROM Hotel where Hotelid ="+ hotelid);
				
				while(rs.next()) {	
					num = rs.getInt(1);
				}

				if(num == 0) {
					System.out.println("존재하지 않는 호텔 번호입니다.");
					return;
				}
			}
			catch(InputMismatchException e)	{
				flag2 = 0;
				sc.nextLine();
				System.out.println("호텔 예약 실패!");
			}
			
			System.out.println("등록할 호텔의 체크인 날짜 :");
			checkin = sc.nextLine();
			System.out.println("등록할 호텔의 체크아웃 날짜 :");
			checkout = sc.nextLine();
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, custid);
			pstmt.setInt(2, hotelid);
			pstmt.setString(3, checkin);
			pstmt.setString(4, checkout);
			
			int r = pstmt.executeUpdate();
			
			if(r > 0)
				System.out.println("호텔 예약 완료!");
			else
				System.out.println("호텔 예약 실패!");
		
			con.close();
		}catch(Exception e){ System.out.println(e);}
	}
	
	public void Delrehotel() {	// 호텔 예약 취소
		int hotelreservationid;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("예약 취소할 호텔 예약번호 :");
		hotelreservationid = sc.nextInt();
		sc.nextLine();
		
		String query = "delete from Hotelreservation where hotelreservationid = ?";
		PreparedStatement pstmt = null;
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(
					"jdbc:mysql://192.168.56.101:4567/hotel","hhs","a123456789");
			System.out.println("OK");
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, hotelreservationid);
			
			int r = pstmt.executeUpdate();
			
			if(r > 0)
				System.out.println("호텔 예약 취소 완료!");
			else
				System.out.println("일치하는 호텔 예약번호가 없습니다!");
			
			con.close();
		}catch(Exception e){ System.out.println(e);}
	}
}
