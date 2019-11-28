package testdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Airplane {
	public void Addair() {	// 7. 항공권 등록
		String name;
		String airfrom;		// 출발지
		String airto;		// 도착지
		int airprice = 0;	// 가격
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("등록할 항공사의 이름 :");
		name = sc.nextLine();
		System.out.println("등록할 항공권의 출발지 :");
		airfrom = sc.nextLine();
		System.out.println("등록할 항공권의 도착지 :");
		airto = sc.nextLine();
		System.out.println("등록할 항공권의 가격 :");
		
		int flag = 1;
		
		try {
			airprice = sc.nextInt();
			sc.nextLine();
		}
		catch(InputMismatchException e)	{
			flag = 0;
			sc.nextLine();
			System.out.println("항공권 등록 실패!");
		}
		
		if(flag == 0)
			return;
		
		//airprice = sc.nextInt();
		//sc.nextLine();
		
		String query = "insert into Airplane(airplanename, airfrom, airto, airprice) values(?, ?, ?, ?);";
		PreparedStatement pstmt = null;
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(
					"jdbc:mysql://192.168.56.101:4567/hotel","hhs","a123456789");
			//PreparedStatement pstmt=con.prepareStatement("INSERT INTO Customer(custname, birth, phone, address) VALUES(?, ?, ?, ?)");
			//pstmt = setString(1, name);
			System.out.println("OK");
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, airfrom);
			pstmt.setString(3, airto);
			pstmt.setInt(4, airprice);
			
			int r = pstmt.executeUpdate();
			
			if(r > 0)
				System.out.println("항공권 등록 완료!");
			else
				System.out.println("항공권 등록 실패!");
			
			con.close();
		}catch(Exception e){ System.out.println(e);}
	}
	
	public void Delair() {	// 8. 항공권 삭제
		int airid;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("삭제할 항공권 상품번호 :");
		airid = sc.nextInt();
		sc.nextLine();
		
		String query = "delete from Airplane where airplaneid = ?";
		PreparedStatement pstmt = null;
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(
					"jdbc:mysql://192.168.56.101:4567/hotel","hhs","a123456789");
			//PreparedStatement pstmt=con.prepareStatement("INSERT INTO Customer(custname, birth, phone, address) VALUES(?, ?, ?, ?)");
			//pstmt = setString(1, name);
			System.out.println("OK");
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, airid);
			
			int r = pstmt.executeUpdate();
			
			if(r > 0)
				System.out.println("항공권 삭제 완료!");
			else
				System.out.println("일치하는 항공권 번호가 없습니다!");
			
			con.close();
		}catch(Exception e){ System.out.println(e);}
	}
	
	public void printair() {	// 10. 항공권 전체 목록 보기
		try{			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(
					"jdbc:mysql://192.168.56.101:4567/hotel","hhs","a123456789");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT * FROM Airplane");
			
			System.out.println("========================================================");
			System.out.println("항공권 상품 번호          항공사 이름           출발지           도착지          가격");
			System.out.println("========================================================");
			while(rs.next())
				System.out.println("    "+rs.getInt(1)+"         "+rs.getString(2)+"                    "+rs.getString(3)+"            "+rs.getString(4)+"         "+rs.getString(5));
			System.out.println("========================================================");
			
			con.close();
		}catch(Exception e){ System.out.println(e);}
	}
	
	public void searchair_airname() {	// 12. 항공사로 항공권 검색
		String airname;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("검색할 항공사 이름을 입력 :  ");
		airname = sc.nextLine();
		
		String query = "SELECT * FROM Airplane WHERE airplanename = ?";
		PreparedStatement pstmt = null;
		
		try{			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(
					"jdbc:mysql://192.168.56.101:4567/hotel","hhs","a123456789");
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, airname);
			ResultSet rs=pstmt.executeQuery();
			
			System.out.println("========================================================");
			System.out.println("항공권 상품 번호          항공사 이름           출발지           도착지          가격");
			System.out.println("========================================================");
			while(rs.next())
				System.out.println("    "+rs.getInt(1)+"         "+rs.getString(2)+"                    "+rs.getString(3)+"            "+rs.getString(4)+"         "+rs.getString(5));
			System.out.println("========================================================");
			
			con.close();
		}catch(Exception e){ System.out.println(e);}
	}
	
	public void searchair_price() {	// 13. 항공권 가격으로 항공권상품 검색
		int price;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("최대 가격으로 설정할 금액 입력 :  ");
		price = sc.nextInt();
		sc.nextLine();
		
		String query = "SELECT * FROM Airplane WHERE airprice <= ?";
		PreparedStatement pstmt = null;
		
		try{			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(
					"jdbc:mysql://192.168.56.101:4567/hotel","hhs","a123456789");
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, price);
			ResultSet rs=pstmt.executeQuery();
			
			System.out.println("========================================================");
			System.out.println("항공권 상품 번호          항공사 이름           출발지           도착지          가격");
			System.out.println("========================================================");
			while(rs.next())
				System.out.println("    "+rs.getInt(1)+"         "+rs.getString(2)+"                    "+rs.getString(3)+"            "+rs.getString(4)+"         "+rs.getString(5));
			System.out.println("========================================================");
			
			con.close();
		}catch(Exception e){ System.out.println(e);}
	}
}
