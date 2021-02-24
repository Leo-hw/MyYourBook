package pack.admin.model;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pack.controller.AdminBean;
import pack.controller.ChartPrintBean;
import pack.controller.FaqBoardBean;
import pack.controller.InqueryBean;
import pack.controller.NewBookBean;
import pack.controller.OldBookBean;
import pack.controller.OrderInfoBean;
import pack.controller.UserBean;
import pack.model.AdminDto;
import pack.model.FaqBoardDto;
import pack.model.InqueryDto;
import pack.model.NewBookDto;
import pack.model.OldBookDto;
import pack.model.OrderInfoDto;
import pack.model.RentInfoDto;
import pack.model.ReviewDto;
import pack.model.UserDto;

@Repository
public class AdminImpl extends SqlSessionDaoSupport implements AdminInter {
	
	@Autowired
	public AdminImpl(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}

	@Override
	public AdminDto getAdminLoginInfo(String admin_id) {
		return getSqlSession().selectOne("selectAdminData", admin_id);
	}

	@Override
	public boolean insertBookData(NewBookBean bean) {
		try {
			getSqlSession().insert("insertBookData", bean);
			return true;
		} catch (Exception e) {
			System.out.println("insertBookData error : " + e); 		//개발자를 위한 내용
			return false;
		}
	}
	
	@Override
	public List<NewBookDto> getNewBook() {
		return getSqlSession().selectList("selectBookDataAll");
	}
	
	@Override
	public List<UserDto> getUser() {
		return getSqlSession().selectList("selectUserAll");
	}
	
	@Override
	public List<OldBookDto> getSellOb() {
		return getSqlSession().selectList("selectSellObAll");
	}
	
	@Override
	public List<OldBookDto> getStandby() {
		return getSqlSession().selectList("selectStandbyAll");
	}
	
	@Override
	public boolean updateState(OldBookBean bean) {
		try {
			getSqlSession().update("upobstate", bean);
			return true;
		} catch (Exception e) {
			System.out.println("updateState error : " + e); 		//개발자를 위한 내용
			return false;
		}
	}
	
	@Override
	public List<OldBookDto> getRBook() {
		return getSqlSession().selectList("selectRentBookAll");
	}
	
	@Override
	public List<OldBookDto> getReuse() {
		return getSqlSession().selectList("selectReuseAll");
	}
	
	@Override
	public List<OrderInfoDto> getnbOrderData() {
		return getSqlSession().selectList("selectnbOrderAll");
	}
	
	@Override
	public List<OrderInfoDto> getobOrderData() {
		return getSqlSession().selectList("selectobOrderAll");
	}
	
	@Override
	public List<RentInfoDto> getRentList() {
		return getSqlSession().selectList("selectRentAll");
	}
	
	@Override
	public List<UserDto> getUserPoint() {
		return getSqlSession().selectList("selectUserPointAll");
	}
	
	@Override
	public List<OrderInfoDto> getNobank() {
		return getSqlSession().selectList("selectNobankAll");
	}
	
	@Override
	public boolean updateOrderState(OrderInfoBean bean) {
		try {
			getSqlSession().update("uporderstate", bean);
			return true;
		} catch (Exception e) {
			System.out.println("updateOrderState error : " + e); 		//개발자를 위한 내용
			return false;
		}
	}
	
	@Override
	public List<OrderInfoDto> getOrderData() {
		return getSqlSession().selectList("selectorderAll");
	}
	
	@Override
	public boolean updateThrow(int ob_no) {
		try {
			getSqlSession().update("obthrow", ob_no);
			return true;
		} catch (Exception e) {
			System.out.println("updateThrow error : " + e); 		//개발자를 위한 내용
			return false;
		}
	}
	
	@Override
	public List<RentInfoDto> getDelayData() {
		return getSqlSession().selectList("selectdelayAll");
	}
	
	@Override
	public boolean updateUser(UserBean bean) {
		try {
			getSqlSession().update("upuser", bean);
			return true;
		} catch (Exception e) {
			System.out.println("updateUser error : " + e); 		//포인트 차감, 상태 업데이트
			return false;
		}
	}
	
	@Override
	public boolean removeOb(int ob_no) {
		try {
			getSqlSession().delete("rmoldbook", ob_no);
			getSqlSession().delete("rmrentinfo");
			return true;
		} catch (Exception e) {
			System.out.println("removeOb error : " + e); 		//장기연체 도서 삭제
			return false;
		}
	}
	
	@Override
	public List<String> getDelayId() {
		return getSqlSession().selectList("selectdelayid");
	}
	
	@Override
	public boolean updateDcount(String user_id) {
		try {
			getSqlSession().update("updcount", user_id);
			return true;
		} catch (Exception e) {
			System.out.println("updateDcount error : " + e); 		//연체 횟수
			return false;
		}
	}
	
	@Override
	public List<UserDto> getDelay() {
		return getSqlSession().selectList("selectdelay");
	}
	
	@Override
	public boolean updatePenalty(UserBean bean) {
		try {
			getSqlSession().update("uppenalty", bean);
			return true;
		} catch (Exception e) {
			System.out.println("updatePenalty error : " + e); 		//패널티 업데이트
			return false;
		}
	}
	
	@Override
	public List<UserDto> getRefuse() {
		return getSqlSession().selectList("selectrefusecount");
	}
	
	@Override
	public List<UserDto> getUserdel() {
		return getSqlSession().selectList("selectdeluser");
	}
	
	@Override
	public List<UserDto> getUsercheck() {
		return getSqlSession().selectList("selectuserpcheck");
	}
	
	@Override
	public boolean updateDelUser(String user_id) {
		try {
			getSqlSession().update("updeluser", user_id);
			return true;
		} catch (Exception e) {
			System.out.println("updateDelUser error : " + e); 		//연체 횟수
			return false;
		}
	}
	
	@Override
	public List<OrderInfoDto> getDelayDeposit() {
		return getSqlSession().selectList("selectdelaydeposit");
	}
	
	@Override
	public boolean delOrder(String orderlist_no) {
		try {
			getSqlSession().delete("rmorder", orderlist_no);
			return true;
		} catch (Exception e) {
			System.out.println("delOrder error : " + e); 		//미납 주문 삭제
			return false;
		}
	}
	
	@Override
	public boolean insertFaqData(FaqBoardBean bean) {
		try {
			getSqlSession().insert("insertFAQData", bean);
			return true;
		} catch (Exception e) {
			System.out.println("insertFaqData error : " + e); 		//개발자를 위한 내용
			return false;
		}
	}
	
	@Override
	public List<FaqBoardDto> getFaqlist() {
		return getSqlSession().selectList("selectfaqAll");
	}
	
	@Override
	public List<InqueryDto> getinqlist() {
		return getSqlSession().selectList("selectinqAll");
	}
	
	@Override
	public List<ReviewDto> getreviewAll() {
		return getSqlSession().selectList("selectreviewAll");
	}
	
	@Override
	public List<OrderInfoDto> getObProfit() {
		return getSqlSession().selectList("obprofit");
	}
	@Override
	public List<OrderInfoDto> getNbProfit() {
		return getSqlSession().selectList("nbprofit");
	}
	
	@Override
	public List<OrderInfoDto> getProfit() {
		return getSqlSession().selectList("profit");
	}
	
	@Override
	public OrderInfoDto getObProfitmonth() {
		return getSqlSession().selectOne("obprofitmonth");
	}
	@Override
	public OrderInfoDto getNbProfitmonth() {
		return getSqlSession().selectOne("nbprofitmonth");
	}
	
	@Override
	public OrderInfoDto getProfitmonth() {
		return getSqlSession().selectOne("profitmonth");
	}
	
	@Override
	public List<AdminDto> getAdminyet() {
		return getSqlSession().selectList("adminyetAll");
	}
	
	@Override
	public boolean delAdmin(String admin_id) {
		try {
			getSqlSession().insert("deladmin", admin_id);
			return true;
		} catch (Exception e) {
			System.out.println("delAdmin error : " + e); 		//개발자를 위한 내용
			return false;
		}
	}
	
	@Override
	public boolean upAdmin(AdminBean bean) {
		try {
			getSqlSession().insert("upadmin", bean);
			return true;
		} catch (Exception e) {
			System.out.println("upAdmin error : " + e); 		//개발자를 위한 내용
			return false;
		}
	}
	
	@Override
	public List<AdminDto> getAdminInfo() {
		return getSqlSession().selectList("getadminAll");
	}
	
	@Override
	public boolean upAdminJik(AdminBean bean) {
		try {
			getSqlSession().insert("upadminjik", bean);
			return true;
		} catch (Exception e) {
			System.out.println("upAdminJik error : " + e); 		//개발자를 위한 내용
			return false;
		}
	}
	
	@Override
	public List<ReviewDto> getBestReview() {
		return getSqlSession().selectList("mbrmonth");
	}
	
	@Override
	public List<ReviewDto> getBestReviewmonth(String sql) {
		return getSqlSession().selectList("mbestreview",sql);
	}
	
	@Override
	public List<ReviewDto> getRmonth() {
		return getSqlSession().selectList("mbreviewmonth");
	}
	
	@Override
	public boolean upUserPoint(UserBean bean) {
		try {
			getSqlSession().insert("upuserpoint", bean);
			return true;
		} catch (Exception e) {
			System.out.println("upUserPoint error : " + e); 		//개발자를 위한 내용
			return false;
		}
	}
	
	@Override
	public List<NewBookDto> getOmonth() {
		return getSqlSession().selectList("mbsellermonth");
	}
	
	@Override
	public List<NewBookDto> getOcmonth() {
		return getSqlSession().selectList("mbsellercmonth");
	}
	
	@Override
	public List<NewBookDto> getBestSellermonth(String sql) {
		return getSqlSession().selectList("mbestseller",sql);
	}
	
	@Override
	public boolean upNbStock(NewBookBean bean) {
		try {
			getSqlSession().insert("upnbstock", bean);
			return true;
		} catch (Exception e) {
			System.out.println("upNbStock error : " + e); 		//개발자를 위한 내용
			return false;
		}
	}
	
	@Override
	public List<RentInfoDto> getRentmonth() {
		return getSqlSession().selectList("mbrentmonth");
	}
	
	@Override
	public List<RentInfoDto> getRentcmonth() {
		return getSqlSession().selectList("mbrentcmonth");
	}
	
	@Override
	public List<RentInfoDto> getBestRentmonth(String sql) {
		return getSqlSession().selectList("mbestrent",sql);
	}
	
	@Override
	public List<RentInfoDto> getRentKing() {
		return getSqlSession().selectList("rentking");
	}
	
	@Override
	public String getMonth() {
		return getSqlSession().selectOne("currentmonth");
	}
	
	@Override
	public List<OrderInfoDto> getBuyKing() {
		return getSqlSession().selectList("buyking");
	}
	
	@Override
	public InqueryDto getMaxNum() {
		return getSqlSession().selectOne("getMaxNum");
	}
	
	@Override
	public InqueryDto getInqData(int inq_no) {
		return getSqlSession().selectOne("selectInqPart", inq_no);
	}
	
	@Override
	public boolean upOnum(InqueryBean bean) {
		try {
			getSqlSession().update("updateOnum", bean);
			return true;
		} catch (Exception e) {
			System.out.println("upOnum error : " + e); 		//개발자를 위한 내용
			return false;
		}
	}
	
	@Override
	public boolean insInqReply(InqueryBean bean) {
		try {
			getSqlSession().insert("insertinqReply", bean);
			return true;
		} catch (Exception e) {
			System.out.println("insInqReply error : " + e); 		//개발자를 위한 내용
			return false;
		}
	}
	
	@Override
	public boolean RollbackStock(NewBookBean bean) {
		try {
			getSqlSession().insert("rollbackstock", bean);
			return true;
		} catch (Exception e) {
			System.out.println("RollbackStock error : " + e); 		//개발자를 위한 내용
			return false;
		}
	}
	
	@Override
	public List<NewBookDto> theMostSellBook() {
		return getSqlSession().selectList("getmostsellbook");
	}
	
	@Override
	public List<OldBookDto> theMostRentBook() {
		return getSqlSession().selectList("getmostrentbook");
	}
	
	@Override
	public String IdCheck(String admin_id) {
		return getSqlSession().selectOne("adminidcheck");
	}
	
	@Override
	public boolean insertAdmin(AdminBean bean) {
		try {
			getSqlSession().insert("admininsert", bean);
			return true;
		} catch (Exception e) {
			System.out.println("insertAdmin error : " + e); 		//개발자를 위한 내용
			return false;
		}
	}
	
	@Override
	public boolean insChartData(ChartPrintBean bean) {
		try {
			getSqlSession().insert("inschartdata", bean);
			return true;
		} catch (Exception e) {
			System.out.println("insChartData error : " + e); 		//개발자를 위한 내용
			return false;
		}
	}
	
	@Override
	public String getChartmonth(String cmonth) {
		return getSqlSession().selectOne("getchartmonth", cmonth);
	}
	
	@Override
	public boolean upChartData(ChartPrintBean bean) {
		try {
			getSqlSession().insert("upchartdata", bean);
			return true;
		} catch (Exception e) {
			System.out.println("upChartData error : " + e); 		//개발자를 위한 내용
			return false;
		}
	}
	
	@Override
	public boolean updateAdmin(AdminBean bean) {
		try {
			getSqlSession().update("updateadmininfo", bean);
			return true;
		} catch (Exception e) {
			System.out.println("updateAdmin error : " + e); 		//개발자를 위한 내용
			return false;
		}
	}
}
