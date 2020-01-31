package java0131;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.mindrot.jbcrypt.BCrypt;

public class OracleMain {

	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream("./db.txt")))){
			String driverClass = br.readLine();
			//System.out.println(driverClass);
			String url = br.readLine();
			String id = br.readLine();
			String pw = br.readLine();
			
			//드라이버 클래스 로드
			Class.forName(driverClass);
			//데이터베이스 연결 객체 생성
			Connection con = DriverManager.getConnection(url, id, pw);
			//System.out.println(con);
			
			//삽입하는 구문
			/*
			PreparedStatement pstmt = 
				con.prepareStatement(
					"insert into transactions(num, itemcode, itemname, price, cnt, transdate, userid) "
					+ "values(?,?,?,?,?,?,?)");
			
			pstmt.setInt(1, 2);
			pstmt.setString(2, "t1000");
			pstmt.setString(3, "터미네이터");
			pstmt.setInt(4, 200000);
			pstmt.setInt(5, 1);
			
			Calendar cal = new GregorianCalendar(1984, 2, 27, 00, 00, 00);
			//java.sql.Date
			Date transdate = new Date(cal.getTimeInMillis());
			pstmt.setDate(6, transdate);
			
			pstmt.setString(7, "아놀드 슈왈츠제네거");
			
			int result = pstmt.executeUpdate();
			if(result > 0) {
				System.out.println("삽입 성공");
			}
			pstmt.close();
			*/
			
			//수정하는 구문
			
			/*
			PreparedStatement pstmt = 
				con.prepareStatement(
					"update transactions set itemcode=?, itemname=?, price=?,"
					+ " cnt=?, transdate=?, userid=?"
					+ " where num = ?");
			
			//값 검증 작업을 해야 합니다.
			
			pstmt.setString(1, "t1000");
			pstmt.setString(2, "터미네이터");
			pstmt.setInt(3, 2000);
			pstmt.setInt(4, 2);
			
			Calendar cal = new GregorianCalendar(1984, 2, 27, 00, 00, 00);
			//java.sql.Date
			Date transdate = new Date(cal.getTimeInMillis());
			pstmt.setDate(5, transdate);
			
			pstmt.setString(6, "아놀드 슈왈츠제네거");
			pstmt.setInt(7, 3);
			
			int result = pstmt.executeUpdate();
			if(result > 0) {
				System.out.println("수정 성공");
			}else if(result == 0) {
				System.out.println("조건에 맞는 데이터가 없습니다.");
			}
			pstmt.close();
			*/
			
			//데이터 삭제 - 기본키를 가지고 데이터를 삭제하는 것이 일반적
			/*
			PreparedStatement pstmt = 
					con.prepareStatement(
						"delete from transactions where num = ?");
			pstmt.setInt(1, 2);
			
			int result = pstmt.executeUpdate();
			if(result > 0) {
				System.out.println("삭제 성공");
			}else if(result == 0) {
				System.out.println("조건에 맞는 데이터가 없습니다.");
			}
			pstmt.close();
			*/
			
			//암호화된 문장 비교
			/*
			System.out.println(BCrypt.checkpw("터미네이터", BCrypt.hashpw("터미네이터", BCrypt.gensalt())));
			System.out.println(BCrypt.checkpw("터미네이터1", BCrypt.hashpw("터미네이터", BCrypt.gensalt())));

			System.out.println(BCrypt.hashpw("터미네이터", BCrypt.gensalt()));
			System.out.println(BCrypt.hashpw("터미네이터", BCrypt.gensalt()));

			System.out.println(BCrypt.checkpw("터미네이터", 
				"$2a$10$r2UsLMUAdCYeXs.ocfAJRORhuUFlHg21iAzy5ze9U21I3AqyNx.HS"));
			System.out.println(BCrypt.checkpw("터미네이터", 
				"$2a$10$RrxpxIep/n6PfyRvUL5miOmOGMIolWJK1grmINOt1lpfHwleS2x06"));
			*/
			
			
			//데이터베이스에 데이터를 저장하거나 수정할 때 사용할 수 없는 단어를 확인해서 저장하거나 수정하는 것이 좋습니다.
			//특히 SQL 예약어는 확인해서 데이터로 사용하지 못하도록 하는 것이 좋습니다.
			/*
			String [] stop_words = {"or", "and"};
			
			PreparedStatement pstmt = 
					con.prepareStatement(
						"insert into transactions(num, itemcode, itemname, price, cnt, transdate, userid) "
						+ "values(?,?,?,?,?,?,?)");
				
				pstmt.setInt(1, 5);
				String str = "dkdskfasadsfand";
				//stop_words의 모든 데이터를 순회
				for(String temp : stop_words) {
					//indexOf는 temp가 몇번째 있는지 검색해주는 메소드
					//찾으면 찾은 위치를 리턴하고 못찾으면 -1을 리턴
					if(str.indexOf(temp) >= 0) {
						System.out.println("사용할 수 없는 단어가 포함되어 있습니다.");
						//return 하면 작업을 수행하지 않음
						//return;
						
						//찾으면 ""으로 치환 - 제거
						str = str.replace(temp, "");
					}
				}
				//id 나 검색어 등은 모두 대문자 또는 모두 소문자로 변경해서 저장하는 것이 일반적
				pstmt.setString(2, str.toUpperCase());
				pstmt.setString(3, BCrypt.hashpw("터미네이터", BCrypt.gensalt()));
				pstmt.setInt(4, 200000);
				pstmt.setInt(5, 1);
				
				Calendar cal = new GregorianCalendar(1984, 2, 27, 00, 00, 00);
				//java.sql.Date
				Date transdate = new Date(cal.getTimeInMillis());
				pstmt.setDate(6, transdate);
				
				pstmt.setString(7, "아놀드 슈왈츠제네거");
				
				int result = pstmt.executeUpdate();
				if(result > 0) {
					System.out.println("삽입 성공");
				}
				pstmt.close();
				*/
			
			//commit()을 만나거나 DDL, DCL을 성공할 때 까지 데이터베이스에 반영이 안됩니다.
			
			con.setAutoCommit(false);
			PreparedStatement pstmt = 
					con.prepareStatement(
						"insert into transactions(num, itemcode) "
						+ "values(?,?)");
				
				pstmt.setInt(1, 7);
				//id 나 검색어 등은 모두 대문자 또는 모두 소문자로 변경해서 저장하는 것이 일반적
				pstmt.setString(2, "태권브이");
					
				int result = pstmt.executeUpdate();
				if(result > 0) {
					System.out.println("삽입 성공");
				}
				pstmt.close();
				
				pstmt = 
						con.prepareStatement(
							"insert into transactions(num, itemcode) "
							+ "values(?,?)");
					
				pstmt.setInt(1, 7);
				//id 나 검색어 등은 모두 대문자 또는 모두 소문자로 변경해서 저장하는 것이 일반적
				pstmt.setString(2, "태권브이");
						
				result = pstmt.executeUpdate();
				if(result > 0) {
					System.out.println("삽입 성공");
				}
				pstmt.close();
				
				con.commit();
			con.close();
			
		}catch(Exception e) {
			System.out.println("데이터베이스 예외:" + e.getMessage());
			e.printStackTrace();
		}

	}

}








