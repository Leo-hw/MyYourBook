package pack.model;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FaqDao extends SqlSessionDaoSupport{

	@Autowired
	public FaqDao(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
		//System.out.println("3");
	}
	
	public List<FaqBoardDto> qnaListAll(){
		List<FaqBoardDto> list = getSqlSession().selectList("selectQnaAll");
		//System.out.println("2");
		return list;
	}
	
	public List<FaqBoardDto> qnaListPart(String qna_class){
		List<FaqBoardDto> list = getSqlSession().selectList("selectQnaClass", qna_class);
		return list;
	}
	
	public List<FaqBoardDto> faqDetail(String faq_no){
		List<FaqBoardDto> list = getSqlSession().selectList("selectFaqDetail", faq_no);
		return list;
	}
	
}
