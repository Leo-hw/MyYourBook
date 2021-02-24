package pack.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

@Repository
public class SearchImpl implements SearchInter{

   private Connection conn;
   private PreparedStatement pstmt;
   private ResultSet rs;
   

   @Override
   public ArrayList<NewBookDto> getDataAll(String type, String search) {
      ArrayList<NewBookDto> list = new ArrayList<NewBookDto>();
      String sql = "select * from newbook";
      try {
         String url = "jdbc:mysql://localhost:3306/test";
         String id = "root";
         String pw = "123";
         Class.forName("org.mariadb.jdbc.Driver");
         conn = DriverManager.getConnection(url, id, pw);
         if(search == null) {
            sql += " order by nb_no desc";
            pstmt = conn.prepareStatement(sql);
         }else {
            sql += " where " + type + " like ?";
            sql += " order by nb_no desc";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + search + "%");
         }
         rs = pstmt.executeQuery();
         
         //책, 제목, 가격, 년도, 출판사
         while(rs.next()) {
            NewBookDto dto = new NewBookDto();
            
            dto.setNb_no(rs.getInt("nb_no"));
            dto.setNb_name(rs.getString("nb_name"));
            dto.setNb_price(rs.getInt("nb_price"));
            dto.setNb_bdate(rs.getString("nb_bdate"));
            dto.setNb_comp(rs.getString("nb_comp"));
            dto.setNb_image(rs.getString("nb_image"));
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