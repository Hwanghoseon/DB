package testdb;

import java.util.Scanner;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.InputMismatchException;

public class Test
{
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
	
	public void Addhotel() {	// 5. 호텔 등록
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
			//PreparedStatement pstmt=con.prepareStatement("INSERT INTO Customer(custname, birth, phone, address) VALUES(?, ?, ?, ?)");
			//pstmt = setString(1, name);
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
	
	public void Delhotel() {	// 6. 호텔 삭제
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
			
			int r = pstmt.executeUpdate();

			if(r > 0)
				System.out.println("호텔 삭제 완료!");
			else
				System.out.println("일치하는 호텔 상품번호가 없습니다!");
			
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
	
	public void Hotelreservation() {    // 14. 호텔 에약
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
			//PreparedStatement pstmt=con.prepareStatement("INSERT INTO Customer(custname, birth, phone, address) VALUES(?, ?, ?, ?)");
			//pstmt = setString(1, name);
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
	
	public void Airplanereservation() {    // 16. 항공권 에약
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
	
	public void Delrehotel() {	// 15. 호텔 예약 취소
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
			
			int r = pstmt.executeUpdate();
			
			if(r > 0)
				System.out.println("예약 호텔 삭제 완료!");
			else
				System.out.println("일치하는 호텔 예약번호가 없습니다!");
			
			con.close();
		}catch(Exception e){ System.out.println(e);}
	}
	
	public void Delreair() {	//  17. 항공권 예약 취소
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
			
			int r = pstmt.executeUpdate();
			
			if(r > 0)
				System.out.println("예약 항공권 삭제 완료!");
			else
				System.out.println("일치하는 항공권 예약번호가 없습니다!");
			
			con.close();
		}catch(Exception e){ System.out.println(e);}
	}
	
	public void printrehotel() {	// 3. 예약된 호텔 보기
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
	
	public void printreair() {	// 4. 예약된 항공권 보기
		try{			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(
					"jdbc:mysql://192.168.56.101:4567/hotel","hhs","a123456789");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT Airplanereservation.Airplanereservationid, Customer.custname, Airplane.airplanename, Airplane.airfrom, Airplane.airto, Airplanereservation.departuredate, Airplanereservation.departure, Airplanereservation.arrivaledate, Airplanereservation.arrial, Airplane.airprice FROM Customer, Airplane, Airplanereservation WHERE Customer.custid = Airplanereservation.custid AND Airplane.Airplaneid = Airplanereservation.Airplaneid");
			
			System.out.println("=================================================================================================");
			System.out.println("항공권 예약 번호     고객이름         항공사 이름         출발지         도착지         촐발날짜           출발식간            도착날짜           도착시간             가격");
			System.out.println("=================================================================================================");
			while(rs.next())
				System.out.println("    "+rs.getInt(1)+"        "+rs.getString(2)+"             "+rs.getString(3)+"            "+rs.getString(4)+"            "+rs.getString(5)+"           "+rs.getString(6)+"     "+rs.getString(7)+"     "+rs.getString(8)+"     "+rs.getString(9)+"      "+rs.getString(10));
			System.out.println("=================================================================================================");
			
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
	
	public void printht() {	// 9. 호텔 전체 목록 보기
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
	
	public void searchht_name() {	// 9. 호텔명으로 호텔상품 검색
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
	
	public void searchht_roomsize() {	// 10. 최대 인원 수로 호텔상품 검색
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
	
	
	public void searchht_price() {	// 11. 호텔 가격으로 호텔상품 검색
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