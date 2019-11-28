package testdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Airplanereservation {
	public void printreair() {			// 예약된 항공권 보기
		try{			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(
					"jdbc:mysql://192.168.56.101:4567/hotel","hhs","a123456789");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT Airplanereservation.Airplanereservationid, Customer.custname, Airplane.airplanename, Airplane.airfrom, Airplane.airto, Airplanereservation.departuredate, Airplanereservation.departure, Airplanereservation.arrivaledate, Airplanereservation.arrial, Airplane.airprice FROM Customer, Airplane, Airplanereservation WHERE Customer.custid = Airplanereservation.custid AND Airplane.Airplaneid = Airplanereservation.Airplaneid");
			
			System.out.println("=================================================================================================");
			System.out.println("항공권 예약 번호     고객이름         항공사 이름         출발지         도착지         촐발날짜           출발시간            도착날짜           도착시간             가격");
			System.out.println("=================================================================================================");
			while(rs.next())
				System.out.println("    "+rs.getInt(1)+"        "+rs.getString(2)+"             "+rs.getString(3)+"            "+rs.getString(4)+"            "+rs.getString(5)+"           "+rs.getString(6)+"     "+rs.getString(7)+"     "+rs.getString(8)+"     "+rs.getString(9)+"      "+rs.getString(10));
			System.out.println("=================================================================================================");
			
			con.close();
		}catch(Exception e){ System.out.println(e);}
	}
	
	public void Airplanereservation() {    // 항공권 에약
		int custid = 0;
		int airplaneid = 0;
		String departure;	// 출발시각
		String arrial;		// 도착시각
		String departuredate;	// 출발날짜
		String arrialdate;		// 도착날짜
		int num=0;
		
		Scanner sc = new Scanner(System.in);
		ResultSet rs=null;
		
		String query = "insert into Airplanereservation(custid, airplaneid, departuredate, arrivaledate, departure, arrial) values(?, ?, ?, ?, ?, ?);";
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
				System.out.println("항공권 예약 실패!");
			}
			
			System.out.println("예약할 항공권의 상품번호(airplaneid) :");
			
			int flag2 = 1;
			
			try {
				airplaneid = sc.nextInt();
				sc.nextLine();
				rs=stmt.executeQuery("SELECT count(*) FROM Airplane where airplaneid ="+ airplaneid);
				
				while(rs.next()) {	
					num = rs.getInt(1);
				}

				if(num == 0) {
					System.out.println("존재하지 않는 항공권 번호입니다.");
					return;
				}
			}
			catch(InputMismatchException e)	{
				flag2 = 0;
				sc.nextLine();
				System.out.println("항공권 예약 실패!");
			}
			
			System.out.println("등록할 항공권의 출발날짜 :");
			departuredate = sc.nextLine();
			System.out.println("등록할 항공권의 도착날짜 :");
			arrialdate = sc.nextLine();
			System.out.println("등록할 항공권의 출발시각 :");
			departure = sc.nextLine();
			System.out.println("등록할 항공권의 도착시각 :");
			arrial = sc.nextLine();
			
			pstmt = con.prepareStatement(query);
			
			
			pstmt.setInt(1, custid);
			pstmt.setInt(2, airplaneid);
			pstmt.setString(3, departuredate);
			pstmt.setString(4, arrialdate);
			pstmt.setString(5, departure);
			pstmt.setString(6, arrial);
			
			int r = pstmt.executeUpdate();
			
			if(r > 0)
				System.out.println("항공권 예약 완료!");
			else
				System.out.println("항공권 예약 실패!");
		
			con.close();
		}catch(Exception e){ System.out.println(e);}
	}
	
	public void Delreair() {	//  항공권 예약 취소
		int airreservationid;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("예약 취소할 항공권 예약번호 :");
		airreservationid = sc.nextInt();
		sc.nextLine();
		
		String query = "delete from Airplanereservation where Airplanereservationid = ?";
		PreparedStatement pstmt = null;
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(
					"jdbc:mysql://192.168.56.101:4567/hotel","hhs","a123456789");
			System.out.println("OK");
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, airreservationid);
			
			int r = pstmt.executeUpdate();
			
			if(r > 0)
				System.out.println("항공권 에약 취소 완료!");
			else
				System.out.println("일치하는 항공권 예약번호가 없습니다!");
			
			con.close();
		}catch(Exception e){ System.out.println(e);}
	}
}
