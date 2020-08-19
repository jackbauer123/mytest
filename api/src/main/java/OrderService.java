
public interface OrderService {

    /**
     * create order
     */
    Order create(long order_no,String userId, String commodityCode, int orderCount);
}