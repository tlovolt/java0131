package java0131;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectMain {

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
			
			//transactions 테이블의 모든 데이터 가져오기
			PreparedStatement pstmt = con.prepareStatement("select * from transactions");
			ResultSet rs = pstmt.executeQuery();
			/*
			//여러 개의 컬럼으로 구성된 여러 개의 데이터를 저장
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			
			//데이터를 순회
			while(rs.next()) {
				//하나의 행을 저장할 Map을 생성
				Map<String, Object> map = new HashMap<String, Object>();
				
				map.put("num", rs.getInt("num"));
				map.put("itemcode", rs.getString("itemcode"));
				//3번째 컬럼의 값을 문자열로 가져오기
				map.put("itemname", rs.getString(3));
				map.put("price", rs.getInt("price"));
				map.put("cnt", rs.getInt("cnt"));
				map.put("transdate", rs.getDate("transdate"));
				map.put("userid", rs.getString("userid"));
				
				//하나의 행을 list에 저장
				list.add(map);
			}
			
			//list의 데이터 출력
			for(Map<String, Object> map : list) {
				//System.out.println(map);
				System.out.println(map.get("num"));
			}
			*/
			
			List<Transaction> list = new ArrayList<Transaction>();
			while(rs.next()) {
				Transaction transaction = new Transaction();
				
				
				transaction.setNum(rs.getInt("num"));
				transaction.setItemcode(rs.getString("itemcode"));
				transaction.setItemname(rs.getString("itemname"));
				transaction.setPrice(rs.getInt("price"));
				transaction.setCnt(rs.getInt("cnt"));
				transaction.setTransdate(rs.getDate("transdate"));
				transaction.setUserid(rs.getString("userid"));
				
				
				list.add(transaction);
			}
			
			for(Transaction transaction : list) {
				//System.out.println(transaction);
				System.out.println(transaction.getNum() + ":" + transaction.getItemname());
			}
			
			rs.close();
			pstmt.close();
			con.close();
		}catch(Exception e) {
			System.out.println("데이터베이스 예외:" + e.getMessage());
			e.printStackTrace();
		}
		

	}

}








