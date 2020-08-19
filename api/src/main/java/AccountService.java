public interface AccountService {

    /**
     * debit balance of user's account
     */
    void debit(long order_no,String userId, int money);
}