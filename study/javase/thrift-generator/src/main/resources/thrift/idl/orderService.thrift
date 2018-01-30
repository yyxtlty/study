namespace java com.xman.rainbow.order.thrift
include 'xman.thrift'
include "order/order.thrift"
include 'common.thrift'

typedef common.ReturnMsg    ReturnMsg
typedef common.LongReturnMsg    LongReturnMsg
typedef common.IdListReturnMsg    IdListReturnMsg
typedef common.BooleanReturnMsg BooleanReturnMsg
typedef common.DoubleReturnMsg DoubleReturnMsg
typedef common.BooleanResultsReturnMsg BooleanResultsReturnMsg
typedef common.IntReturnMsg IntReturnMsg
typedef common.ListMapReturnMsg ListMapReturnMsg
typedef i32 int
typedef i64 long
typedef order.OrderStatus OrderStatus
typedef order.OrderInfo   OrderInfo
typedef order.DeclareInfo   DeclareInfo
typedef order.PayInfo PayInfo
typedef order.OrderReturnMsg OrderReturnMsg
typedef order.OrderListReturnMsg OrderListReturnMsg
typedef order.OrderListPageReturnMsg OrderListPageReturnMsg
typedef order.OrderQueryTerms OrderQueryTerms
typedef order.BatchOrderQueryTerms BatchOrderQueryTerms

/**
 * 订单服务
 *
 */
service OrderService  extends xman.BaseService{

    /**
    * 下订单
    * @param orderInfo 订单信息
    * @return OrderReturnMsg
    */
   ReturnMsg createOrder(1:string appId, 2:string token, 3:string logId,4:OrderInfo orderInfo);

    /**
    * 下订单(边民一体机)
    * @param orderInfo 订单信息
    * @return OrderReturnMsg
    */
   ReturnMsg createOrderByBorder(1:string appId, 2:string token, 3:string logId,4:OrderInfo orderInfo);

  /**
   * 确认下单(运营或供应商确认)
   * @param orderCode 订单id
   * @param userId 运营人员ID
   * @return ReturnMsg
   */
   ReturnMsg confirmedCreate(1:string appId, 2:string token, 3:string logId,4:i64 userId,5:string orderCode);
  /**
  * 支付,运营发起或用户发起
  * @param payInfo 支付信息
  * @return ReturnMsg
  */
  ReturnMsg orderPay(1:string appId, 2:string token, 3:string logId,4:i64 userId,5:PayInfo payInfo);
   /**
   * 确认支付,支付成功回调
   * @param orderCode 订单id
   * @return ReturnMsg
   */
   ReturnMsg confirmedPay(1:string appId, 2:string token, 3:string logId,4:string orderCode);

   /**
   * 入园确认(订单)
   **/
   ReturnMsg confirmedPark(1:string appId, 2:string token, 3:string logId,4:i64 userId,5:string orderCode);


   /**
   * 报关 边民app调用,调用完成后,发送订单给履约服务进行报关,同时状态变更成待发货
   **/
   ReturnMsg declareCustoms(1:string appId, 2:string token, 3:string logId,4:DeclareInfo declareInfo);
   /**
   * 提交发货 履约服务调用
   **/
   ReturnMsg deliver(1:string appId, 2:string token, 3:string logId,4:string orderCode,6:string remarks);

   /**
   * 完成订单 履约服务调用
   **/
   ReturnMsg completionOrder(1:string appId, 2:string token, 3:string logId,4:string orderCode);
   /**
   * 申请取消订单
   * @param orderCode 订单id
   * @param userId 操作人ID
   * @return OrderReturnMsg
   */
   ReturnMsg  cancelOrder(1:string appId, 2:string token, 3:string logId,4:string orderCode,6:string reason);

    /**
    * 确认取消订单
    * @param orderCode 订单id
    * @param userId 操作人ID
    * @return OrderReturnMsg
    */
    ReturnMsg  confirmedCancel(1:string appId, 2:string token, 3:string logId,4:string orderCode,5:i64 userId,6:string reason);

    /**
    * 查询订单
    **/
    OrderListPageReturnMsg queryOrder(1:string appId, 2:string token, 3:string logId,5:OrderQueryTerms terms,6:i32 pageNo,7:i32 pageSize);

    /**
    * 查询订单(未分页)
    **/
    OrderListReturnMsg queryOrderList(1:string appId, 2:string token, 3:string logId,5:OrderQueryTerms terms);

    /**
    * 根据订单号查询订单
    **/
    OrderReturnMsg queryOrderByOrCode(1:string appId, 2:string token, 3:string logId,4:i64 userId,5:string orderCode);

    /**
    * 根据订单号查询订单
    **/
    ListMapReturnMsg queryOrderStatusHistory(1:string appId, 2:string token, 3:string logId,4:i64 userId,5:string orderCode);

    /**
    *   按日期统计订单数据
    **/
    ListMapReturnMsg appDataQueryOrderCount(1:string appId, 2:string token, 3:string logId,4:i32 groupType,5:string orderTime);

    /**
    * 查询订单(未分页)
    **/
    ReturnMsg everyDayOneOrder(1:string appId, 2:string token, 3:string logId,5:string borderId);


}


