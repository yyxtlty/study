namespace java com.xman.rainbow.finance.thrift.settlement
include 'common.thrift'
include 'order/order.thrift'
typedef i32 int
typedef i64 long
typedef order.OrderItemInfo OrderItemInfo;

/**
 * 结算状态
 * 1-待支付，2-已取消
 */
enum Status {
    PAYMENT          =9,//已支付
    PENDING          =1,//待结算
    PRE_SETTLED      =2,//预结算
    SETTLED          =3,//已结算
    DOUBTFUL         =4,//存疑
    CLOSED           =5,//关闭,已处理
}

/**
 * 结算信息
 */
struct SettlementInfo {
    1:optional i64 id,
    2:optional i64 shopId,
    3:optional string shopName,
    4:optional i32 orderCount,
    5:optional i64 amountTotal,
    6:optional i64 payAmountTotal,
    7:optional i64 customsTotal,
    8:optional i64 balanceTotal,
    10:optional i64 profitTotal,
    13:optional i64 taxationTotal,
    14:optional i32 status,
    15:optional i64 userId,
    16:optional i64 balanceTime,
    17:optional i64 billDate,
    18:optional i64 createTime,
}

/**
* 结算查询条件
**/
struct SettlementQueryTerms{
    2:optional i64 billDateStart     //账单日期
    3:optional i64 billDateEnd       //账单日期
    5:optional i64 shopId       //结算商户
    6:optional i64 shopName       //结算商户
}

/**
* 结算账单明细
**/
struct BillInfo{
    1:optional i64 id,
    2:optional i64 seId,
    3:optional string orderCode,
    4:optional i64 userId,
    5:optional i64 shopId,
    6:optional string shopName,
    7:optional i64 amount,
    8:optional i64 payAmount,
    9:optional i64 customsAmount,
    10:optional i64 profitTotal,
    11:optional i32 status,
    12:optional i64 billDate,
    13:optional i64 orderTime,
    14:optional i64 balanceTime,
    15:optional i64 createTime,
    16:optional list<OrderItemInfo> itemInfos,
    17:optional string remarks,
}

/**
* 账单结算统计
**/
struct BillSettlementInfo{
  1:optional i64 billDate,            //账单日期
  2:optional i64 shopId,              //账单店铺
  3:optional string shopName,         //店铺名称
  4:optional i32 orderCount,          //账单总数
  5:optional i32 settledCount,        //已结算账单数
  6:optional i32 pendingCount,        //待结算账单数
  7:optional i32 doubtfulCount,       //存疑账单数
  8:optional i32 closedCount,         //已解决账单数
  9:optional i32 settledBalance,      //已结算账单金额(结算费)
  10:optional i32 pendingBalance,      //待结算账单金额(结算费)
  11:optional i32 doubtfulBalance,    //存疑账单金额 (结算费)
  12:optional i64 closedBalance,      //已处理账单金额(结算费)
  13:optional i64 amountTotal,        //账单总货款
  14:optional i64 payAmountTotal,     //账单总支付金额
  15:optional i64 customsTotal,       //账单总报关金额
  16:optional i64 balanceTotal,       //账单总结算金额
  17:optional i64 profitTotal,        //账单总服务费
  18:optional i64 taxationTotal,      //账单总税费
  19:optional i32 preCount,        //待结算账单数
  20:optional i64 preBalance,        //待结算账单数
}

/**
* 账单查询条件
**/
struct BillQueryTerms{
    1:i64 seId,                              //订单编号
    2:string orderCode,                              //订单编号
    3:optional Status status         //结算状态
    4:optional i64 billDateStart     //账单日期
    5:optional i64 billDateEnd       //账单日期
    6:optional i64 balanceTimeStart     //结算时间
    7:optional i64 balanceTimeEnd       //结算时间
    8:optional i64 orderTimeStart     //订单时间
    9:optional i64 orderTimeEnd       //订单时间
    10:optional i64 shopId       //结算商户
    11:optional i64 userId       //结算用户ID
}

const int STATUS_OK = 0
const string MESSAGE_OK = "OK"

/**
 * 结算信息响应,针对单个订单
 */
struct SettlementReturnMsg {
    1:required i32     errorCode=STATUS_OK,
    2:optional string  errorMsg=MESSAGE_OK,
	3:optional SettlementInfo SettlementInfo, //结算信息
	4:required string  logId
}

/**
* 结算信息列表
**/
struct SettlementListReturnMsg {
    1:required i32     errorCode=STATUS_OK,
    2:optional string  errorMsg=MESSAGE_OK,
    3:optional list<SettlementInfo> SettlementInfos
    4:required string  logId
}

/**
* 账单结算统计查询列表带分页 返回参数
**/
struct BillSettlementPageReturnMsg {
   1:required   i32     errorCode
   2:optional   string  errorMsg
   3:optional   BillSettlementListPage billSettlementPage
   4:required  string  logId
}

/**
* 账单结算统计列表分页信息
**/
struct BillSettlementListPage {
    1:required i32 pageNo
    2:required i32 pageSize
    3:required i32 totalPage
    4:required i32 totalCount
    5:optional list<BillSettlementInfo> settlementInfoList
}


/**
 * 账单信息响应,针对单个订单
 */
struct BillReturnMsg {
    1:required i32     errorCode=STATUS_OK,
    2:optional string  errorMsg=MESSAGE_OK,
	3:optional BillInfo billInfo, //结算信息
	4:required string  logId
}

/**
* 账单查询列表带分页 返回参数
**/
struct BillListPageReturnMsg {
   1:required   i32     errorCode
   2:optional   string  errorMsg
   3:optional   BillListPage billListPage
   4:required  string  logId
}
/**
* 账单列表分页信息
**/
struct BillListPage {
    1:required i32 pageNo
    2:required i32 pageSize
    3:required i32 totalPage
    4:required i32 totalCount
    5:optional list<BillInfo> billInfoList
}


/**
* 账单列表信息
**/
struct BillListReturnMsg {
    1:required i32     errorCode=STATUS_OK,
    2:optional string  errorMsg=MESSAGE_OK,
    3:optional list<BillInfo> billInfoList
    4:required  string  logId
}
