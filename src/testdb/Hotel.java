package testdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Hotel {
	public void Addhotel() {	// 호텔 등록
		String name;
		int roomsize = 0;
		int hotelprice;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("등록할 호텔의 이름 :");
		name = sc.nextLine();
		System.out.println("등록할 호텔의 인원수 :");
		
		try {
			roomsize = sc.nextInt();
			sc.nextLine();
		}
		catch(InputMismatchException e)	{
			sc.nextLine();
			System.out.println("호텔 등록 실패!");
			return;
		}
		
		System.out.println("등록할 호텔의 가격 :");
		hotelprice = sc.nextInt();
		sc.nextLine();
		
		String query = "insert into Hotel(hotelname, roomsize, hotelprice) values(?, ?, ?);";
		PreparedStatement pstmt = null;
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(
					"jdbc:mysql://192.168.56.101:4567/hotel","hhs","a123456789");
			System.out.println("OK");
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setInt(2, roomsize);
			pstmt.setInt(3, hotelprice);
			
			int r = pstmt.executeUpdate();
			
			if(r > 0)
				System.out.println("호텔 등록 완료!");
			else
				System.out.println("호텔 등록 실패!");
			
			con.close();
		}catch(Exception e){ System.out.println(e);}
	}
	
	public void Delhotel() {	// 호텔 삭제
		int hotelid;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("삭제할 호텔 상품번호 :");
		hotelid = sc.nextInt();
		sc.nextLine();
		
		String query = "delete from Hotel where hotelid = ?";
		PreparedStatement pstmt = null;
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(
					"jdbc:mysql://192.168.56.101:4567/hotel","hhs","a123456789");
			System.out.println("OK");
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, hotelid);
			
			int r = pstmt.executeUpdate();

			if(r > 0)
				System.out.println("호텔 삭제 완료!");
			else
				System.out.println("일치하는 호텔 상품번호가 없습니다!");
			
			con.close();
		}catch(Exception e){ System.out.println(e);}
	}
	
	public void printht() {	// 호텔 전체 목록 보기
		try{			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(
					"jdbc:mysql://192.168.56.101:4567/hotel","hhs","a123456789");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT * FROM Hotel");
			
			System.out.println("========================================================");
			System.out.println("호텔 상품 번호          호텔이름           최대 인원 수          가격");
			System.out.println("========================================================");
			while(rs.next())
				System.out.println("    "+rs.getInt(1)+"         "+rs.getString(2)+"                    "+rs.getString(3)+"         "+rs.getString(4));
			System.out.println("========================================================");
			
			con.close();
		}catch(Exception e){ System.out.println(e);}
	}
	
	public void searchht_name() {	// 호텔명으로 호텔상품 검색
		String hotelname;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("검색할 호텔명을 입력 :  ");
		hotelname = sc.nextLine();
		
		String query = "SELECT * FROM Hotel WHERE hotelname = ?";
		PreparedStatement pstmt = null;
		
		try{			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(
					"jdbc:mysql://192.168.56.101:4567/hotel","hhs","a123456789");
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, hotelname);
			ResultSet rs=pstmt.executeQuery();
			
			System.out.println("========================================================");
			System.out.println("호텔 상품 번호          호텔이름           최대 인원 수          가격");
			System.out.println("========================================================");
			while(rs.next())
				System.out.println("    "+rs.getInt(1)+"         "+rs.getString(2)+"                    "+rs.getString(3)+"         "+rs.getString(4));
			System.out.println("========================================================");
			
			con.close();
		}catch(Exception e){ System.out.println(e);}
	}
	
	public void searchht_roomsize() {	// 최대 인원 수로 호텔상품 검색
		int roomsize = 0;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("검색할 최대 인원 수를 입력 :  ");
		roomsize = sc.nextInt();
		sc.nextLine();
		
		String query = "SELECT * FROM Hotel WHERE roomsize = ?";
		PreparedStatement pstmt = null;
		
		try{			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(
					"jdbc:mysql://192.168.56.101:4567/hotel","hhs","a123456789");
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, roomsize);
			ResultSet rs=pstmt.executeQuery();
			
			System.out.println("========================================================");
			System.out.println("호텔 상품 번호          호텔이름           최대 인원 수          가격");
			System.out.println("========================================================");
			while(rs.next())
				System.out.println("    "+rs.getInt(1)+"         "+rs.getString(2)+"                    "+rs.getString(3)+"         "+rs.getString(4));
			System.out.println("========================================================");
			
			con.close();
		}catch(Exception e){ System.out.println(e);}
	}
	
	public void searchht_price() {	// 호텔 가격으로 호텔상품 검색
		int price;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("최대 가격으로 설정할 금액 입력 :  ");
		price = sc.nextInt();
		sc.nextLine();
		
		String query = "SELECT * FROM Hotel WHERE hotelprice <= ?";
		PreparedStatement pstmt = null;
		
		try{			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(
					"jdbc:mysql://192.168.56.101:4567/hotel","hhs","a123456789");
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, price);
			ResultSet rs=pstmt.executeQuery();
			
			System.out.println("========================================================");
			System.out.println("호텔 상품 번호          호텔이름           최대 인원 수          가격");
			System.out.println("========================================================");
			while(rs.next())
				System.out.println("    "+rs.getInt(1)+"         "+rs.getString(2)+"                    "+rs.getString(3)+"         "+rs.getString(4));
			System.out.println("========================================================");
			
			con.close();
		}catch(Exception e){ System.out.println(e);}
	}
}
