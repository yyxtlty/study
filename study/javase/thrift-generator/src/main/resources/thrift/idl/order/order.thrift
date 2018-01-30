namespace java com.xman.rainbow.order.thrift.order
include 'common.thrift'

typedef i32 int
typedef i64 long

/**
 * 订单状态
 */
enum OrderStatus {
    PRE_CREATE          =99,    //创建(批单状态)
    CREATED             = 1,    //下单
    WAIT_PAY            = 2,    //待支付
    WAIT_BALANCE_PAY    = 3,    //待支付尾款
    WAIT_PARK           = 4,    //待入园
    WAIT_APPLY_CUSTOMS  = 5,    //待报关
    WAIT_DELIVER        = 6,    //待发货
	DELIVER             = 7,    //运输中
	COMPLETION          = 8,    //完成
	TIMEOUT             = 9,   //超时
    CANCEL_APPLY        = 10,   //申请取消
    CANCEL              = 11,   //取消成功
    REFUND              = 12,   //退款中
    ERROR           = 13,   //支付异常
}

enum OrderType{
    ORDINARY = 1,   //普通订单
    BATCH = 2,    //批单
}

enum PayChannel{
    ONLINE =0,  //在线支付
    POS = 1,    //pos机线下支付
}

enum PayType{
    ALL     =1, //全款
    DEPOSIT =2, //定金
    BALANCE =3, //余款
}

enum PayStatus{
    CREATE      =0,//创建支付
    SUCCESS     =1,//支付成功
    FAIL        =2,//支付失败
}

struct OrderItemInfo {
    1:i64 itemId,               //商品ID
    2:i64 skuId,
    3:i64 shopId,
    4:i32 quantity,         //数量
    5:optional string itemTitle,  //商品名称
    6:optional string picUrl,   //商品图片
    7:optional i64 categoryId,  //类目ID
    8:optional i64 price,   //单价
    9:optional i64 customsAmount,//报关价
    10:optional i64 unit,//单位
}

/**
 * 订单信息
 */
struct OrderInfo {
    1:optional i64 id,                                //主键id
	2:optional string   orderCode,                    //订单编号
	3:optional string batchCode,                      //批单号
	4:optional OrderType orderType,                  //订单类型
	5:optional i64 userId,                            //买家ID
	6:optional string userName,                       //用户名称
	7:optional i64 shopId,                            //店铺ID
	8:optional string shopName,                       //店铺名称
	9:optional i64 merchantId,                        //商户ID
	10:optional string merchantName,                   //商户名称
	11:optional list<OrderItemInfo> itemInfo,          //商品详细
	12:optional i64 amount,                            //订单总价 单位分
	13:optional string depositRatio,                   //预付比例
	14:optional i64 payAmount,                        //实付总额
	15:optional PayChannel payChannel,                 //支付方式
	16:optional PayType payType,                      //支付类型
	17:optional OrderStatus orderStatus,              //订单状态
	18:optional string remarks,                       //备注
	19:optional i64 createTime,                       //创建时间
	20:optional i64 updateTime,                       //修改时间
	21:optional i64 taskId,                           //任务ID
	22:optional i64 customsAmount,                    //报关价格
}

/**
 * 查询订单信息
 */
struct OrderQueryInfo {
    1:OrderInfo orderInfo,                          //订单信息
	2:list<map<string,string>> statusHistorys,       //状态变更历史
}

/**
* 订单查询条件
**/
struct OrderQueryTerms{
    1:string orderCode,                              //订单编号
    2:string batchCode,
    3:i64 itemId,               //商品ID
    4:i64 skuId
    5:i64 userId,                                    //买家ID
    6:i64 shopId,                                    //店铺ID
    7:optional OrderStatus orderStatus,              //订单状态
    8:optional PayType payType,                      //支付类型
    9:optional PayChannel payChannel,                //支付渠道
    10:i64 createTimeStart,                           //交易时间,开始
    11:i64 createTimeEnd,                             //交易时间,结束
    12:string itemTitle,                             //商品名称
}

/**
* 批量订单查询条件
**/
struct BatchOrderQueryTerms{
    1:string batchCode,
    2:i64 itemId,               //商品ID
    3:i64 skuId
    4:i64 shopId,                                    //店铺ID
    5:optional OrderStatus orderStatus,              //订单状态
    6:required PayType payType,                      //支付类型
    7:i64 createTimeStart,                           //交易时间,开始
    8:i64 createTimeEnd,                             //交易时间,结束
}
/**
 * 支付信息
 */
struct PayInfo {
    1:required string orderCode,          //订单ID
    2:i32  accountId,                     //账户ID
    3:i64  bankCardId,                    //银行卡ID
    4:required i64 userId,                //边民ID
    5:i64 merchantId,                     //商户ID
    6:required PayChannel payChannel,     //支付渠道
    7:required i64 payAmount,             //支付金额
    8:optional string transactionId,      //银行交易流水号，线下转账或POS刷卡需提供
}


/**
* 报关信息
**/
struct DeclareInfo{
   1:string orderCode,//订单编号
   2:string batchCode,
   3:required i64 userId,                            //买家ID
   4:required i64 shopId,                            //店铺ID
}


const int STATUS_OK = 0
const string MESSAGE_OK = "OK"

/**
 * 订单信息响应,针对单个订单
 */
struct OrderReturnMsg {
    1:required i32     errorCode=STATUS_OK,
    2:optional string  errorMsg=MESSAGE_OK,
	3:optional OrderQueryInfo orderQueryInfo, //订单信息
	4:required string  logId
}


/**
 * 单个用户对应的所有订单状态,不包括交易关闭的
 */
struct OrderStatusReturnMsg {
  1:required   i32     errorCode=STATUS_OK,
  2:optional   string  errorMsg=MESSAGE_OK,
  3:map<long,OrderStatus> orderStatusMap,
  4:required   string  logId
}

/**
 * 订单历史记录响应
 */
struct OrderListReturnMsg {
	1:required   i32     errorCode=STATUS_OK,
    2:optional   string  errorMsg=MESSAGE_OK,
	3:list<OrderInfo> orders,
	4:required   string  logId
}
/**
* 订单查询列表带分页 返回参数
**/
struct OrderListPageReturnMsg {
   1:required   i32     errorCode
   2:optional   string  errorMsg
   3:optional   OrderListPage orderListPage
   4:required  string  logId
}
/**
* 订单列表分页信息
**/
struct OrderListPage {
    1:required i32 pageNo
    2:required i32 pageSize
    3:required i32 totalPage
    4:required i32 totalCount
    5:optional list<OrderInfo> orderInfoList
}



