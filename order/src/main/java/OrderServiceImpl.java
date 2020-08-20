import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

public class OrderServiceImpl  implements OrderService {

    private OrderDAO orderDAO;

    private AccountService accountService;

    private JdbcTemplate jdbcTemplate;




    //@Transactional
    public Order create(long order_no,String userId, String commodityCode, int orderCount) {
        System.out.println("订单开始！！！");
        int orderMoney = calculate(commodityCode, orderCount);
        accountService.debit(order_no,userId, orderMoney);
        orderDAO=new OrderDAO(jdbcTemplate);
        Order order = new Order();
        order.userId = userId;
        order.commodityCode = commodityCode;
        order.count = orderCount;
        order.money = orderMoney;
        order.order_no=order_no;
        // INSERT INTO orders ...
        return orderDAO.insert(order);
    }


    private  int calculate(String commodityCode, int orderCount){
        return 10;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}