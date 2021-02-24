package pack.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OldSearchImpl implements OldSearchInter{
	
	
	private DataSource ds;
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	
	@Override
	public ArrayList<OldBookDto> getDataAll(String type, String search) {
		ArrayList<OldBookDto> list = new ArrayList<OldBookDto>();
		String sql = "select * from oldbook";
		try {
			conn=ds.getConnection();
			if(search == null) {
				sql += " order by ob_no desc";
				pstmt = conn.prepareStatement(sql);
			}else {
				sql += " where " + type + " like ?";
				sql += " order by ob_no desc";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + search + "%");
			}
			rs = pstmt.executeQuery();
			
			//책, 제목, 가격, 년도, 출판사
			while(rs.next()) {
				OldBookDto dto = new OldBookDto();
				
				dto.setOb_no(rs.getInt("ob_no"));
				dto.setOb_name(rs.getString("ob_name"));
				dto.setOb_price(rs.getInt("ob_price"));
				dto.setOb_bdate(rs.getString("ob_bdate"));
				dto.setOb_comp(rs.getString("ob_comp"));
				dto.setOb_image(rs.getString("ob_image"));
				list.add(dto);
			}
		
		} catch (Exception e) {
			System.out.println("getDataAll err : " + e);
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return list;
	}
	
}
