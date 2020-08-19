public interface StorageService {

    /**
     * deduct storage count
     */
    void deduct(long order_no,String commodityCode, int count);
}