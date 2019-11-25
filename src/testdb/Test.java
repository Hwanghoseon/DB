package testdb;

import java.util.Scanner;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.InputMismatchException;

public class Test
{
	public void Addcust() {    // 고객 등록
		String name;
		String birth;
		String phone;
		String address;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("등록할 고객의 이름 :");
		name = sc.nextLine();
		System.out.println("등록할 고객의 생년월일 :");
		birth = sc.nextLine();
		System.out.println("등록할 고객의 휴대전화번호 :");
		phone = sc.nextLine();
		System.out.println("등록할 고객의 주소 :");
		address = sc.nextLine();
		
		String query = "insert into Customer(custname, birth, phone, address) values(?, ?, ?, ?);";
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
			pstmt.setString(2, birth);
			pstmt.setString(3, phone);
			pstmt.setString(4, address);
			
			int r = pstmt.executeUpdate();
			
			if(r > 0)
				System.out.println("고객 등록 완료!");
			else
				System.out.println("고객 등록 실패!");
			
			con.close();
		}catch(Exception e){ System.out.println(e);}
	}
	
	public void Addhotel() {	// 호텔 등록
		String name;
		String checkin;
		String checkout;
		int roomsize = 0;
		int hotelprice;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("등록할 호텔의 이름 :");
		name = sc.nextLine();
		System.out.println("등록할 호텔의 인원수 :");
		
		int flag = 1;
		
		try {
			roomsize = sc.nextInt();
			sc.nextLine();
		}
		catch(InputMismatchException e)	{
			flag = 0;
			sc.nextLine();
			System.out.println("호텔 등록 실패!");
		}
		
		if(flag == 0)
			return;
		
		System.out.println("등록할 호텔의 체크인 날짜 :");
		checkin = sc.nextLine();
		System.out.println("등록할 호텔의 체크아웃 날짜 :");
		checkout = sc.nextLine();
		System.out.println("등록할 호텔의 가격 :");
		hotelprice = sc.nextInt();
		sc.nextLine();
		
		String query = "insert into Hotel(hotelname, roomsize, checkin, checkout, hotelprice) values(?, ?, ?, ?, ?);";
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
			pstmt.setInt(2, roomsize);
			pstmt.setString(3, checkin);
			pstmt.setString(4, checkout);
			pstmt.setInt(5, hotelprice);
			
			int r = pstmt.executeUpdate();
			
			if(r > 0)
				System.out.println("호텔 등록 완료!");
			else
				System.out.println("호텔 등록 실패!");
			
			con.close();
		}catch(Exception e){ System.out.println(e);}
	}

	public void Addair() {	// 항공권 등록
		String name;
		String departure;   // 출발시각
		String arrial;   // 도착시각
		String airfrom;   // 출발지
		String airto;   // 도착지
		int airprice = 0;   // 가격
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("등록할 항공권의 이름 :");
		name = sc.nextLine();
		System.out.println("등록할 항공권의 출발시각 :");
		departure = sc.nextLine();
		System.out.println("등록할 항공권의 도착시각 :");
		arrial = sc.nextLine();
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
		
		String query = "insert into Airplane(airplanename, departure, arrial, airfrom, airto, airprice) values(?, ?, ?, ?, ?, ?);";
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
			pstmt.setString(2, departure);
			pstmt.setString(3, arrial);
			pstmt.setString(4, airfrom);
			pstmt.setString(5, airto);
			pstmt.setInt(6, airprice);
			
			int r = pstmt.executeUpdate();
			
			if(r > 0)
				System.out.println("항공권 등록 완료!");
			else
				System.out.println("항공권 등록 실패!");
			
			con.close();
		}catch(Exception e){ System.out.println(e);}
	}
	
	public void Delcust() {	// 고객 삭제
		int custid;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("삭제할 고객번호 :");
		custid = sc.nextInt();
		sc.nextLine();
		
		String query = "delete from Customer where custid = ?";
		PreparedStatement pstmt = null;
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(
					"jdbc:mysql://192.168.56.101:4567/hotel","hhs","a123456789");
			//PreparedStatement pstmt=con.prepareStatement("INSERT INTO Customer(custname, birth, phone, address) VALUES(?, ?, ?, ?)");
			//pstmt = setString(1, name);
			System.out.println("OK");
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, custid);
			
			pstmt.executeUpdate();
			
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
			//PreparedStatement pstmt=con.prepareStatement("INSERT INTO Customer(custname, birth, phone, address) VALUES(?, ?, ?, ?)");
			//pstmt = setString(1, name);
			System.out.println("OK");
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, hotelid);
			
			pstmt.executeUpdate();
			
			con.close();
		}catch(Exception e){ System.out.println(e);}
	}
	
	public void Delair() {	// 항공권 삭제
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
			
			pstmt.executeUpdate();
			
			con.close();
		}catch(Exception e){ System.out.println(e);}
	}
	
	public void Hotelreservation() {    // 호텔 에약
		int custid;
		int hotelid = 0;
		String htdate;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("예약할 고객의 고유 번호(custid) :");
		custid = sc.nextInt();
		sc.nextLine();
		System.out.println("예약할 호텔의 상품번호(hotelid) :");
		hotelid = sc.nextInt();
		sc.nextLine();
		System.out.println("호텔을 예약할 날짜 :");
		htdate = sc.nextLine();
		
		String query = "insert into Hotelreservation(custid, hotelid, htdate) values(?, ?, ?);";
		PreparedStatement pstmt = null;
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(
					"jdbc:mysql://192.168.56.101:4567/hotel","hhs","a123456789");
			//PreparedStatement pstmt=con.prepareStatement("INSERT INTO Customer(custname, birth, phone, address) VALUES(?, ?, ?, ?)");
			//pstmt = setString(1, name);
			System.out.println("OK"); 
			
		
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, custid);
			pstmt.setInt(2, hotelid);
			pstmt.setString(3, htdate);
			
			int r = pstmt.executeUpdate();
			
			if(r > 0)
				System.out.println("고객 등록 완료!");
			else
				System.out.println("고객 등록 실패!");
		
			con.close();
		}catch(Exception e){ System.out.println(e);}
	}
	
	public void Airplanereservation() {    // 항공권 에약
		int custid;
		int airplaneid = 0;
		String airdate;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("예약할 고객의 고유 번호(custid) :");
		custid = sc.nextInt();
		sc.nextLine();
		System.out.println("예약할 항공권의 상품번호(airplaneid) :");
		airplaneid = sc.nextInt();
		sc.nextLine();
		System.out.println("항공권을 예약할 날짜 :");
		airdate = sc.nextLine();
		
		String query = "insert into Airplanereservation(custid, airplaneid, airdate) values(?, ?, ?);";
		PreparedStatement pstmt = null;
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(
					"jdbc:mysql://192.168.56.101:4567/hotel","hhs","a123456789");
			System.out.println("OK"); 
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, custid);
			pstmt.setInt(2, airplaneid);
			pstmt.setString(3, airdate);
			
			int r = pstmt.executeUpdate();
			
			if(r > 0)
				System.out.println("항공권 예약 완료!");
			else
				System.out.println("항공권 예약 실패!");
		
			con.close();
		}catch(Exception e){ System.out.println(e);}
	}
	
	public void printrehotel() {	// 예약된 호텔 보기	/////////////////////////////////////////////////////////////////////
		try{			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(
					"jdbc:mysql://192.168.56.101:4567/hotel","hhs","a123456789");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT * FROM Hotelreservation");
			
			while(rs.next())
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
			
			con.close();
		}catch(Exception e){ System.out.println(e);}
	}
	
	public void Delrehotel() {	// 호텔 예약 취소
		int hotelreservationid;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("삭제할 호텔 예약번호 :");
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
			
			pstmt.executeUpdate();
			
			con.close();
		}catch(Exception e){ System.out.println(e);}
	}
	
	public void Delreair() {	//  항공권 예약 취소
		int airreservationid;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("삭제할 항공권 예약번호 :");
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
			
			pstmt.executeUpdate();
			
			con.close();
		}catch(Exception e){ System.out.println(e);}
	}
	
	public void b() {		
		try{			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(
					"jdbc:mysql://192.168.56.101:4567/madang","hhs","a123456789");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT * FROM Book");
			
			while(rs.next())
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
			
			con.close();
		}catch(Exception e){ System.out.println(e);}
	}
}