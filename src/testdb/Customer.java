package testdb;

import java.util.Scanner;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.InputMismatchException;

public class Customer {
	public void Addcust() {    // 1. 고객 등록
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
	
	public void modifycust() {    // 2. 고객 정보 수정
		int custid;
		String name;
		String birth;
		String phone;
		String address;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("수정할 고객의 고객번호 :  ");
		
		try {
			custid = sc.nextInt();
			sc.nextLine();
		}
		catch(InputMismatchException e)	{
			sc.nextLine();
			System.out.println("일치하는 고객번호를 가진 고객이 없습니다!");
			return;
		}
		
		System.out.println(custid + "번 고객의 수정할 이름 :");
		name = sc.nextLine();
		System.out.println(custid + "번 등록할 고객의 생년월일 :");
		birth = sc.nextLine();
		System.out.println(custid + "번 등록할 고객의 휴대전화번호 :");
		phone = sc.nextLine();
		System.out.println(custid + "번 등록할 고객의 주소 :");
		address = sc.nextLine();
		
		String query = "update Customer set custname=?, birth=?, phone=?, address=? WHERE custid=?;";
		PreparedStatement pstmt = null;
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(
					"jdbc:mysql://192.168.56.101:4567/hotel","hhs","a123456789");			
			System.out.println("OK");
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, birth);
			pstmt.setString(3, phone);
			pstmt.setString(4, address);
			pstmt.setInt(5, custid);
			
			int r = pstmt.executeUpdate();
			
			if(r > 0)
				System.out.println("고객 정보 수정 완료!");
			else
				System.out.println("고객 정보 수정 실패!");
			
			con.close();
		}catch(Exception e){ System.out.println(e);}
	}
	
	public void printcust() {	// 18. 고객 명단 보기
		try{			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(
					"jdbc:mysql://192.168.56.101:4567/hotel","hhs","a123456789");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT * FROM Customer");
			
			System.out.println("===========================================================================");
			System.out.println("고객번호                         고객 이름                         생년월일                        전화번호                          주소");
			System.out.println("===========================================================================");
			while(rs.next())
				System.out.println("    "+rs.getInt(1)+"            "+rs.getString(2)+"                           "+rs.getString(3)+"        "+rs.getString(4)+"      "+rs.getString(5));
			System.out.println("===========================================================================");
			
			con.close();
		}catch(Exception e){ System.out.println(e);}
	}
	
	public void Delcust() {	// 19. 고객 삭제
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
			
			int r = pstmt.executeUpdate();
			
			if(r > 0)
				System.out.println("고객 삭제 완료!");
			else
				System.out.println("일치하는 고객 번호가 없습니다!");
			
			con.close();
		}catch(Exception e){ System.out.println(e);}
	}
}
