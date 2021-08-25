
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

public class OrderServiceImpl  implements OrderService {

    private OrderDAO orderDAO;


    private JdbcTemplate jdbcTemplate;
    
    private CommandHelloWorld commandHelloWorld;




    //@Transactional
    public Order create(long order_no,String userId, String commodityCode, int orderCount) {
        System.out.println("订单开始！！！");
        int orderMoney = calculate(commodityCode, orderCount);
        OrderBean orderBean = new OrderBean();
        orderBean.order_no=order_no;orderBean.orderMoney=orderMoney;orderBean.userId=userId;
        //commandHelloWorld.setOrderBean(orderBean);
        //CatchExceptionInfo execute = commandHelloWorld.execute();
        //if(Objects.nonNull(execute.getException())) throw new RuntimeException("orderException...");
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


    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setCommandHelloWorld(CommandHelloWorld commandHelloWorld) {
        this.commandHelloWorld = commandHelloWorld;
    }
}