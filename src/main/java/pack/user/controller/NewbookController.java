package pack.user.controller;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;

import pack.controller.OrderInfoBean;
import pack.controller.ReviewBean;
import pack.controller.UserBean;
import pack.model.CardInfoDto;
import pack.model.NewBookDto;
import pack.model.OrderInfoDto;
import pack.model.ReviewDto;
import pack.model.UserDto;
import pack.user.model.CardInfoInter;
import pack.user.model.NewBookInter;
import pack.user.model.OrderInfoInter;
import pack.user.model.ReviewInter;
import pack.user.model.UserInter;

@Controller
public class NewbookController {
   @Autowired
   private NewBookInter newbookinter;
   
   @Autowired
   private ReviewInter reviewInter;
   
   @Autowired
   private CardInfoInter cardInter;
   
   @Autowired
   private UserInter userInter;
   
   @Autowired
   private OrderInfoInter orderInter;
   
   @RequestMapping(value = "newbook", method = RequestMethod.GET)
   public ModelAndView main(@RequestParam("book_no") String nb_no) {
      ModelAndView modelAndView = new ModelAndView();
      
      newbookinter.plusReadCnt(Integer.parseInt(nb_no));
      
      // 고른 책의 책 정보
      NewBookDto newbook = newbookinter.selectNewbook(Integer.parseInt(nb_no));
      modelAndView.setViewName("newbook");
      modelAndView.addObject("newbook", newbook);
      
      // 같은 저자의 책 3개 랜덤 뽑기
      List<NewBookDto> authorList = newbookinter.selectAuthorList(newbook.getNb_author());
      modelAndView.setViewName("newbook");
      modelAndView.addObject("authorList", authorList);
      
      // 해당책의 리뷰 보여주기
      List<ReviewDto> reviewList = reviewInter.selectNewbookReviewList(Integer.parseInt(nb_no));
      modelAndView.setViewName("newbook");
      modelAndView.addObject("reviewList", reviewList);
      return modelAndView;
      
      
      
      
   }
   
   // 해당책의 리뷰 쓰기
   @RequestMapping(value = "writeReview", method = RequestMethod.POST)
   public String reviewWrite(@RequestParam("review_id") String review_id,
         @RequestParam("review_bookno") String review_bookno, @RequestParam("review_context") String review_context) {
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String format_time = format.format(System.currentTimeMillis());
      
      ReviewBean bean = new ReviewBean();
      
      bean.setReview_id(review_id);
      bean.setReview_bookno(Integer.parseInt(review_bookno));
      bean.setReview_context(review_context);
      bean.setReview_date(format_time);
      bean.setReview_rate(0);
      bean.setReview_gonggam(0);
      boolean b = reviewInter.insertNewbookReview(bean);
      if(b) {
         return "redirect:/newbook?book_no="+review_bookno;
      }else {
         return "error";
      }
   }
   
   // 해당책의 리뷰 쓰기
   @RequestMapping(value = "plusGonggam", method = RequestMethod.GET)
   public String plusGonggam(@RequestParam("review_no") String review_no) {
      ReviewDto dto = reviewInter.selectNewbookReview(Integer.parseInt(review_no));
      boolean b = reviewInter.plusGonggam(Integer.parseInt(review_no));
      
      if(b) {
         return "redirect:/newbook?book_no="+dto.getReview_bookno();
      }else {
         return "error";
      }
   }
   // 해당책의 리뷰 지우기
   @RequestMapping(value = "deleteReview", method = RequestMethod.GET)
   public String deleteReview(HttpSession session  ,@RequestParam("review_no") String review_no) {
      ReviewDto dto = reviewInter.selectNewbookReview(Integer.parseInt(review_no));
      String id = (String)session.getAttribute("id");
      
      //아이디가 같을 때만 지울 수 있다.
      if(id.equals(dto.getReview_id())) {
         boolean b = reviewInter.deleteReview(Integer.parseInt(review_no));
         
         if(b) {
            return "redirect:/newbook?book_no="+dto.getReview_bookno();
         }else {
            return "error";
         }
      }else {
         return "error";
      }

   }
   
   
   
   //바로구매 페이지로 넘어가기
   @RequestMapping(value = "directbuy", method = RequestMethod.POST)
   public ModelAndView moveDirectBuy(@RequestParam("order_bookno") String order_bookno,
                              @RequestParam("id") String id, @RequestParam("orderscount") String orderscount) {
      
      ModelAndView modelAndView = new ModelAndView();
      NewBookDto orderbook = newbookinter.selectNewbook(Integer.parseInt(order_bookno));
      
      
      //회원이면 할인된 가격
      if(!id.equals("")) {
         // 책 가격 설정
         
         orderbook.setNb_price((int)(orderbook.getNb_price()*0.9));
         
         // 회원이면 등록된 카드 가져오기
         CardInfoDto cardDto = cardInter.selectCard(id);
         modelAndView.setViewName("directbuy");
         modelAndView.addObject("cardDto", cardDto);
         
         // 회원이면 등록된 포인트 가져오기
         UserDto userDto = userInter.selectUser(id);
         modelAndView.setViewName("directbuy");
         modelAndView.addObject("userDto",userDto);
      }
      //주문한 책의 개수 설정
      modelAndView.setViewName("directbuy");
      modelAndView.addObject("orderscount", orderscount);
      
      //주문한 책 금액 합계
      int order_sum = orderbook.getNb_price() * Integer.parseInt(orderscount);
      
      modelAndView.setViewName("directbuy");
      modelAndView.addObject("order_sum", order_sum);
      
      //주문한 책 정보
      modelAndView.setViewName("directbuy");
      modelAndView.addObject("orderbook", orderbook);
      return modelAndView;
   }
   
   
   
   
   
   //바로구매 페이지에서 결제
//      @RequestMapping(value = "directbuy_pay", method = RequestMethod.GET)
//      public String directBuy(@RequestParam("id") String order_id, 
//                           @RequestParam("order_bookno") int order_bookno,
//                           @RequestParam("order_scount") int order_scount,
//                           @RequestParam("order_sum") int order_sum,
//                           @RequestParam("radioPaytype") String radioPaytype,
//                           @RequestParam("orderpwd") String orderpass,
//                           @RequestParam("realpoint") int realpoint,
//                           @RequestParam("address1") String address1,
//                           @RequestParam("address2") String address2
//                           ) {
   @RequestMapping(value = "directbuy_pay", method = RequestMethod.GET)
   public String directBuy(HttpSession session,HttpServletRequest request
                        ) {
         String order_id = request.getParameter("id");
         String order_bookno1 = request.getParameter("order_bookno");
         String order_scount1 = request.getParameter("order_scount");
         String order_sum1 = request.getParameter("order_sum");
         String radioPaytype = request.getParameter("radioPaytype");
         String orderpass1 = request.getParameter("orderpwd");
         String realpoint1 = request.getParameter("realpoint");
         String address1 = request.getParameter("address1");
         String address2 = request.getParameter("address2");
         
//         System.out.println("-------------------------");
//         System.out.println("order_id " +order_id);
//         System.out.println("order_bookno1 " +order_bookno1);
//         System.out.println("order_scount1 " +order_scount1);
//         System.out.println("order_sum1 " +order_sum1);
//         System.out.println("radioPaytype " +radioPaytype);
//         System.out.println("orderpass1 " +orderpass1);
//         System.out.println("realpoint1 " +realpoint1);
//         System.out.println("address1 " +address1);
//         System.out.println("address2 " +address2);
         
      
         
         
         
         
         
         int order_bookno = Integer.parseInt(order_bookno1);
         
         int order_scount = Integer.parseInt(order_scount1);
         
         int order_sum = Integer.parseInt(order_sum1);
         
         int realpoint;
         if(realpoint1 == "") {
            System.out.println("왜 안되냐구");
            realpoint = 0;
         }else {
            realpoint = Integer.parseInt(realpoint1);
         }
         
         System.out.println("realpoint " + realpoint1);
         
         
         
         SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
         String format_time = format.format(System.currentTimeMillis());
         
         //공통부분
         OrderInfoBean orderbean = new OrderInfoBean();
         
         
         
         
         //orderlist_no 부분
         Date now = new Date();
           SimpleDateFormat vans = new SimpleDateFormat("yyyyMMdd");
           String wdate = vans.format(now);
          
           DecimalFormat df = new DecimalFormat("00");
         
           Random random = new Random();
           int count = random.nextInt(99) + 1;
         
         orderbean.setOrderlist_no(wdate+"-"+df.format(count));
         orderbean.setOrder_date(format_time);
         orderbean.setOrder_scount(order_scount);
         orderbean.setOrder_sum(order_sum);
         orderbean.setOrder_bookno(order_bookno);
         orderbean.setOrder_booktype("1");//새책은 1!
         orderbean.setOrder_address(address1+" "+address2);
         
         
         //회원일 경우
         if(!order_id.equals("")) {
            UserDto userDto = userInter.selectUser(order_id);
            
            
            //포인트 쓸경우 user_id랑 user_point만 가져온다.
            if(realpoint != 0) {
               UserBean userBean = new UserBean();
               userBean.setUser_id(order_id);
               userBean.setUser_point(realpoint);
               boolean point_b = userInter.usePoint(userBean);
               
               
               //여기 또 수정 했어요
               UserDto userDto1 = userInter.selectUser(order_id);
               
               session.setAttribute("point", userDto1.getUser_point());
               if(point_b){
                  System.out.println("usePoint Success");
               }else {
                  System.out.println("usePoint Fail,,,");
               }            
            }
            
            
            //카드결제일 경우
            if(radioPaytype.equals("카드결제")) {
               orderbean.setOrder_paytype("1");//1은 카드결제
               orderbean.setOrder_person(userDto.getUser_name());
               orderbean.setOrder_id(userDto.getUser_id());
               orderbean.setOrder_state("1"); // 카드 결제는 주문 상태로 무조건 1로 된다
               
            }
            //무통장입금일 경우
            else {
               orderbean.setOrder_paytype("0");//0은 무통장입금
               orderbean.setOrder_person(userDto.getUser_name());
               orderbean.setOrder_id(userDto.getUser_id());
               orderbean.setOrder_state("0"); // 무통장입금는 주문 상태로 무조건 0로 된다
            }
            
            boolean b = orderInter.buyNewBookUser(orderbean);
            //포인트 값 고치기 위해
            
            
            if(b) {
               return "redirect:/buymain";
            }else {
               return "error";
            }
            
         }
         //비회원일 경우
         else {
            
            orderbean.setOrder_paytype("0");//0은 무통장입금
            orderbean.setOrder_passwd(orderpass1);
            orderbean.setOrder_state("0"); // 무통장입금는 주문 상태로 무조건 0로 된다
            orderbean.setOrder_person("비회원");
            

            boolean b = orderInter.buyNewBookUnuser(orderbean); // 구매 했다
            
            // 비밀번호로 최근 구매내역 불러오기
            OrderInfoDto orderDto = orderInter.getOrderbyPass(orderpass1);
            
            System.out.println();
            if(b) {
               return "redirect:/unmemberorder?order_no="+orderDto.getOrder_no()+"&order_passwd="+orderDto.getOrder_passwd();
            }else {
               return "error";
            }
         }
   }
   
   
   
   // 비회원dl 구매했을 때 주문내역 불러오기
   @RequestMapping(value = "unmemberorder", method = RequestMethod.GET)
   public ModelAndView unmemberOrder(@RequestParam("order_no") int order_no,
                              @RequestParam("order_passwd") String order_passwd) {
      ModelAndView view = new ModelAndView();
      OrderInfoBean orderBean = new OrderInfoBean();
      orderBean.setOrder_no(order_no);
      orderBean.setOrder_passwd(order_passwd);

      OrderInfoDto orderDto = orderInter.unmemberOrder(orderBean);
      
      view.setViewName("unmemberorder");
      view.addObject("orderDto",orderDto);
      NewBookDto newbookDto = newbookinter.selectNewbook(orderDto.getOrder_bookno());
      
      view.setViewName("unmemberorder");
      view.addObject("newbook",newbookDto);
      return view;
   }
   
}