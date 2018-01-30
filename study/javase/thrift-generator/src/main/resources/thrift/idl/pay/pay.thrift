namespace java com.xman.rainbow.finance.thrift.pay
include 'common.thrift'

typedef i32 int
typedef i64 long

/**
* 订单交易类型
**/
enum OrderTradeType
{
    PURCHASE_PRE_PAY    =0,//采购计划预支付货款（冻结）
    PURCHASE_UNFREEZE   =1,//采购计划剩余金额解冻
    PAY_ON_PURCHASE     =2,//采购平台支付
    REFUND_ON_PURCHASE  =3,//采购平台退款
    SETTLE_ON_PURCHASE  =4,//采购平台结算
    PAY_ON_SUPPLY       =5,//供应商平台支付
    REFUND_ON_SUPPLY    =6,//供应商平台退款
    SETTLE_ON_SUPPLY    =7,//供应商平台结算
}
/**
 * 资金流转步骤
 */
enum PayStep {
    PURCHASE_PRE_PAY            = 01,//采购计划预支付货款（冻结）
    PURCHASE_UNFREEZE           = 11,//采购计划剩余金额解冻
    BORDER_INCOME_TRANSFER      = 21,//边民货款转账
    TAX_SETTLEMENT              = 22,//代收代缴税费
    BORDER_SERVICE_CHARGE       = 23,//平台收取边民收益
    REFUND_ON_PURCHASE          = 31,
    PURCHASE_SERVICE_CHARGE     = 41,//平台收取供应商收益结算
    WITHHOLD_CONSUMPTION        = 51,//边民支付
    RECHARGE                    = 52,//通联充值
    CONSUMPTION                 = 53,//消费
    REFUND_LZCCB                = 63,//柳行退款
    SUPPLYER_SETTLEMENT         = 71,//供应商结算结汇
    SUPPLYER_PLATFORM_SETTLEMENT= 72,//供应平台收益实时结算
}
/**
* 支付渠道
**/
enum PayChannel{
    ONLINE =0,  //在线支付
    POS = 1,    //pos机线下支付
    OFFLINE=2,   //线下转账
    ACCOUNTPAY = 3,//账户余额支付
}
/**
* 支付方式
**/
enum PayType{
    ALL     =1, //全款
    DEPOSIT =2, //定金
    BALANCE =3, //余款
}
/**
* 银行代码
**/
enum BankCode{
    LZCCB = 1, //柳州银行
    ALLIN = 2, //通联
    OUTER = 3, //境外
    OTHER = 4, //其他
}


/**
* 交易方向
**/
enum TradeDirection{
    USER_MERCHANT = 1,  //用户向商户付款
    MERCHANT_USER = 2,  //商户向用户付款
}
/**
* 交易类型（柳行转账申请/账户流水）
**/
enum TradeType{
    WITHHOLD    = 1,     //收入
    PAY         = 2,     //支出
    FREEZE      = 3,     //冻结
    UNFREEZE    = 4,     //解冻
}

/**
 * 交易状态（柳行转账申请/结果返回）
 **/
 enum TradeState{
     SUCCESS         =  0//表示成功,
     FAIL            =  1 //交易失败,
     WAIT_PAY        =  2//未支付,
     PROCESSING      =  3 //处理中,
 }

 /**
 * 平台交易状态
 **/
 enum TradeStatus{
     PROCESSING      =  0//处理中,
     SUCCESS         = 1 //交易成功,
     FAIL            = 2//交易失败,
     ERROR           = 3//异常
 }

const int STATUS_OK = 0
const string MESSAGE_OK = "OK"

struct OrderPayInfo{
    1:required string orderCode,          //订单ID
    2:required i64 userId,                //边民ID
    3:i64  accountId,                     //账户ID
    4:i64  bankCardId,                    //银行卡ID
    5:i64 merchantId,                     //商户ID
    6:i64 merchantAccountId,              //商户账户ID
    7:required PayChannel payChannel,     //支付渠道
    8:required PayType payType,           //支付方式
    9:required i64 tradeAmount,             //支付金额
    10:optional string transactionId,      //银行交易流水号，线下转账或POS刷卡需提供
}

struct OrderTradeVo{
    1:required string orderCode,         //订单ID
    2:PayType orderPayType,             //订单支付类型
    3:PayChannel tradeChannel,          //支付渠道
    4:required i64 tradeAmount,         //交易金额
    5:i64 serviceCharge,                //服务费
    6:i64 taxAmount,                    //税费
    7:i64 accountId,
    8:required i64 userId,              //用户ID
    9:required i64 merchantId,          //商户ID
    10:i64 merchantAccountId,
    11:string tradeAbstract,            //交易摘要
    12:required i64 operatorId,         //操作人ID
    13:required string appId,
}
/**
 * 边民买入付款结果（供应商平台）
 */
struct PayReturnMsg {
  1:required   i32                  errorCode=STATUS_OK,
  2:optional   string               errorMsg=MESSAGE_OK,
  3:optional   PayResult  payResult,//支付结果信息
  4:required   string  logId
}

/**
* 支付结果信息
**/
struct PayResult {
    1:required string           orderCode,      //订单号
    2:required PayStep          payStep,        //支付步骤
    3:required TradeStatus      tradeStatus,    //支付状态
    4:required long tradeCode,                         //平台交易流水号
    5:optional string transactionId                    //银行交易流水号
}

/**
* 供应商平台结算信息
*
**/
struct SettleOnSupplyInfo{
    1:required string orderCode,          //订单ID
    2:i64 userId,                         //买家ID
    3:i32  accountId,                     //账户ID
    4:i64 merchantId,                      //商户ID
    5:i64 merchantAccountId,               //商户账户ID
    6:required i64 orderAmount,           //订单金额
    7:required i64 payAmount,             //实付金额
    8:required i64 serviceCharge,        //服务费
}

/**
* 采购商平台结算信息
*
**/
struct SettleOnPurchaseInfo{
    1:required string orderCode,
    2:required long billId,             //账单号
    3:required i64 userId,                //边民ID
    4:i64  accountId,                     //边民账户ID
    5:i64  bankCardId,                    //边民收益银行卡ID
    6:required i64 merchantId,            //商户ID
    7:i64 merchantAccountId,              //商户账户ID
    8:required i64 tradeAmount,           //商品价格=边民货款价格+税费金额+边民服务费
    9:required i64 borderIncomeAmount,    //边民货款价格
    10:required i64 taxAmount,            //税费金额
    11:i64 borderServiceCharge,           //边民服务费
    12:required i64 purchaseServiceCharge,//采购商服务费
}

/**
* 结算结果信息
**/
struct SettleResult {
    1:required string           orderCode,      //订单号
    2:required long             billId,         //账单号
    3:PayStep                   payStep,        //结算步骤
    4:required TradeStatus      tradeStatus,   //结算状态
}

/**
 * 采购商平台结算结果
 */
struct SettleReturnMsg {
  1:required   i32                  errorCode=STATUS_OK,
  2:optional   string               errorMsg=MESSAGE_OK,
  3:optional   list<SettleResult>  settleResultList,//结算结果信息
  5:required   string  logId
}
/**
* 流水查询列表带分页 返回参数
**/
struct AccountJournalListPageReturnMsg {
   1:required   i32     errorCode
   2:optional   string  errorMsg
   3:optional   AccountJournalListPage accountJournalListPage
   4:required string logId
}

/**
* 流水列表分页信息
**/
struct AccountJournalListPage {
    1:required i32 pageNo
    2:required i32 pageSize
    3:required i32 totalPage
    4:required i32 totalCount
    5:optional list<AccountJournalInfo> accountJournalInfoList
}
/**
*流水信息
**/
struct AccountJournalInfo {
    1:optional i64 id
    2:optional i64 accountId                //账户ID
    3:optional i64 userId                   //用户ID/商户ID
    4:optional string orderCode             //订单编号
    5:optional i64  tradeCode               //交易流水号
    6:optional string transactionId         //银行交易流水号
    7:optional TradeType  tradeType         //'收支类型1收入2支出
    8:optional i64  tradeAmount            // 交易金额 单位分
    9:optional string  tradeAbstract        //摘要
    10:optional i64  createTime           //交易时间
}
/**
* 流水查询条件
**/
struct AccountJournalQueryTerms{
    1:optional i64 accountId
    2:optional i64 userId
    3:i64 createTimeStart,                           //交易时间,开始
    4:i64 createTimeEnd,                             //交易时间,结束
}



/**
* 账户类型
**/
enum AccountType{
    ENTERPRISE  =1 //企业账户
    PERSONAL    =2 //个人账户
}
/**
* 账户角色
**/
enum AccountRole{
PURCHASER           = 1,//采购商
PURCHASE_PLATFORM   = 2,// 采购商平台
BORDER              = 3,// 边民
SUPPLY_PLATFORM     = 4,// 供应商平台
SUPPLIER            = 5,// 供应商
}
/**
* 账户状态1正常2失效
**/
enum AccountStatus{
    NORMAL      =1//正常
    INVALID     =2//失效
}
/**
* 账户查询条件
**/
struct AccountQueryTerms{
    1:optional i64 accountId
    2:optional i64 userId
    3:string  userName
    4:AccountRole accountRole
}
/**
* 账户查询列表带分页 返回参数
**/
struct AccountListPageReturnMsg {
   1:required   i32     errorCode
   2:optional   string  errorMsg
   3:optional   AccountListPage accountListPage
   4:required string logId
}

/**
* 账户列表分页信息
**/
struct AccountListPage {
    1:required i32 pageNo
    2:required i32 pageSize
    3:required i32 totalPage
    4:required i32 totalCount
    5:optional list<AccountInfo> accountInfoList
}
struct AccountInfo {
    1:i64 accountId,                        //账户ID
    2:required i64 userId,                           //用户ID/商户ID
    3:required AccountType accountType,     //账户类型
    4:required AccountRole accountRole,     //账户角色
    5:required AccountStatus accountStatus, //账户状态
    6:i64 availableBalance,                 //可用余额
    7:i64 balance,                          //余额
    8:i64 profit,                           //累计收益
    9:i64 lastProfit,                        //昨日收益
    10:string userName                        //用户名称
}

struct BankCardInfo{
    1:i64 accountId,         //账户ID
    2:i64 userId,            //用户ID
    3:i32 defaultStatus      //默认支付银行卡1默认2非默认
    4:i32 bankCardFlag       //
    5:required BankCode bankCode,     //银行代码 1柳行2通联3境外
    6:required string bankCardNo,        //卡号
    7:string currency,       //币种CNY人民币
    8:string certNo,         //身份证号
    9:required string name,           //户名
    10:required string phoneNum,      //手机号
    11:string bankName,      //开户银行名称
    12:string bankAddr,      //开户银行地址
    13:string addr,          //开户人地址
    14:string country,       //所在国家或地区
    15:string swiftCode,     //银行国际代码
}

//支付渠道分配的商户号信息
struct BankMerInfo{
    1:required BankCode bankCode,    //银行代码
    2:required string merId,            //商户号
}

//边民注册信息
struct AccountRegisterForB{
    1:required i64 userId,                   //用户ID
    2:required string userName                        //用户名称
    3:required BankCardInfo bankCardInfo,    //柳行银行卡信息
}

//供应商注册信息
struct AccountRegisterForS{
    1:required i64 userId,                   //用户ID
    2:required string userName               //用户名称
    3:required BankCardInfo bankCardInfo,    //境外银行卡信息
    4:required BankMerInfo bankMerInfo,          //通联分配供应商商户信息
}

//供应商注册信息
struct AccountRegisterForP{
    1:required i64 userId,                   //用户ID
    2:required string userName               //用户名称
    3:required BankCardInfo bankCardInfo,    //银行卡信息
}

//账户状态信息
struct AccountStatusInfo {
    1:i64 accountId,                        //账户ID
    2:required i64 userId,                  //用户ID/商户ID
    3:required AccountRole accountRole,     //账户角色
    4:required AccountStatus accountStatus, //账户状态
}

/**
 * 账户信息
 */
struct AccountReturnMsg {
    1:required i32     errorCode=STATUS_OK,
    2:optional string  errorMsg=MESSAGE_OK,
	3:optional AccountInfo accountInfo, //账户信息
	4:optional BankCardInfo bankCardInfo,//银行卡信息
    5:required string logId
}

/**
* 交易记录询列表带分页 返回参数
**/
struct TradeRecordListPageReturnMsg {
   1:required   i32     errorCode
   2:optional   string  errorMsg
   3:optional   TradeRecordListPage tradeRecordListPage
   4:required   string logId
   5:optional list<TradeRecordInfo> tradeRecordInfoList
}

/**
* 交易记录列表分页信息
**/
struct TradeRecordListPage {
    1:required i32 pageNo
    2:required i32 pageSize
    3:required i32 totalPage
    4:required i32 totalCount
    5:optional list<TradeRecordInfo> tradeRecordInfoList
}
/**
*交易记录信息
**/
struct TradeRecordInfo {
  1:i64 tradeCode , //交易流水号,
  2:i64 orderTradeCode , //订单交易流水号,
  3:string transactionId  , //银行交易流水号,
  4:string orderCode  , //订单编号,
  5:TradeStatus tradeStatus , //交易状态0等待付款1付款完成2付款失败,
  6:PayStep tradeStep , //支付步骤,
  7:PayType orderPayType , //订单支付方式：1全款2定金3尾款,
  8:PayChannel  tradeChannel , //交易渠道0 ：在线支付 1：POS刷卡2：线下转账3：账户余额,
  9:i64 tradeAmount , //交易金额，单位分,
  10:i32 bankCode , //借记方银行编码,
  11:string bankNname ,//借记方银行名称,
  12:i64 bankCardNo , //借记方银行卡号,
  13:i64 accountId  , //借记方账户,
  14:i64 userId  , //借记方用户ID/商户ID,
  15:string name  ,//借记方持有人姓名,
  16:TradeDirection tradeDirection , //交易类型，支付方向：1.用户to商户 2.商户to用户,
  17:i64 merchantId  , //供应商/采购商ID,
  18:i64 merchantAccountId , //商户/平台账户编码,
  19:string merchantNum , //COMMENT 商户号,
  20:string tradeAbstract ,// 交易摘要,
  21:i64 createTime , // 交易时间,
  22:i64 updateTime , //更新时间,
}
/**
* 交易记录查询条件
**/
struct TradeRecordQueryTerms{
    1:optional string orderCode,
    2:string billId,
    3:optional i64 userId,
    4:i64 createTimeStart,                           //交易时间,开始
    5:i64 createTimeEnd,                             //交易时间,结束
}




/**
 * //采购商账户充值、提现对象信息
 */
struct AccountBalanceDealReturnMsg {
    1:required i32     errorCode=STATUS_OK,
    2:optional string  errorMsg=MESSAGE_OK,
	3:optional AccountBalanceDealInfo accountBalanceDealInfo, //账户信息
    4:required string logId
}

/**
* //采购商账户充值、提现对象信息记录询列表带分页 返回参数
**/
struct AccountBalanceDealListPageReturnMsg {
   1:required   i32     errorCode
   2:optional   string  errorMsg
   3:optional   AccountBalanceDealListPage accountBalanceDealListPage
   4:required   string logId
}
/**
* 交易记录查询条件
**/
struct AccountBalanceDealQueryTerms{
    1:optional i64 applyUserId// 申请用户ID/商户ID',
    2:optional AccountBalanceDealType dealType//处理类型 1充值2提现',
    3:optional AccountBalanceDealStatus dealStatus//状态1申请 2成功 3失败',
    4:i64 createTimeStart,                           //交易时间,开始
    5:i64 createTimeEnd,                             //交易时间,结束
    6:string applyUserName,                             //名称
}
/**
* //采购商账户充值、提现对象信息记录列表分页信息
**/
struct AccountBalanceDealListPage {
    1:required i32 pageNo
    2:required i32 pageSize
    3:required i32 totalPage
    4:required i32 totalCount
    5:optional list<AccountBalanceDealInfo> accountBalanceDealInfoList
}
//采购商账户充值、提现对象信息
struct AccountBalanceDealInfo {
   1:optional i64 abdId // 余额处理申请ID',
   2:optional i64 accountId// '申请账户ID',
   3:optional i64 applyUserId// 申请用户ID/商户ID',
   4:optional i64 dealUserId// 处理用户ID/商户ID',
   5:optional AccountBalanceDealType dealType//处理类型 1充值2提现',
   6:optional string bankName// '银行名称',
   7:optional string bankCard// '银行卡',
   8:optional string transactionId// '银行流水号',
   9:optional i64 money// '金额单位分',
   10:optional AccountBalanceDealStatus dealStatus//状态1申请 2成功 3失败',
   12:optional string applyDesc//'申请说明',
   13:optional string dealDesc//处理摘要',
   14:optional i64 createTime// '创建时间',
   15:optional i64 dealTime// '处理时间',
   16:optional string  applyUserName// '申请用户名称',
}

//采购商账户充值申请
struct AccountRechargeApplyInfo {
   1:optional i64 accountId// '申请账户ID',
   2:optional i64 applyMerchantId// 申请商户ID',
   3:optional i64 applyUserId// 申请用户ID/商户ID',
   4:optional string bankName// '银行名称',
   5:optional string bankCard// '银行卡',
   6:optional string transactionId// '银行流水号',
   7:optional i64 money// '金额单位分',
   8:optional string applyDesc//'申请说明',
}
//采购商账户充值确认
struct AccountRechargeDealnfo {
    1:optional i64 abdId // 余额处理申请ID',  ,
    2:optional i64 dealUserId// 处理用户ID/商户ID',
    3:optional AccountBalanceDealStatus dealStatus//状态1申请 2成功 3失败',
    4:optional string dealDesc//处理摘要',
}
//采购商账户提现申请
struct AccountWithdrawApplyInfo {
        1:optional i64 accountId// '申请账户ID',
        2:optional i64 applyMerchantId// 申请商户ID',
        3:optional i64 applyUserId// 申请用户ID',
        4:optional string bankName// '银行名称',
        5:optional string bankCard// '银行卡',
        6:optional i64 money// '金额单位分',
        7:optional string applyDesc//'申请说明',
}
//采购商账户提现确认
 struct AccountWithdrawDealnfo {
       1:optional i64 abdId // 余额处理申请ID',
       2:optional i64 dealUserId// 处理用户ID/商户ID',
       3:optional string bankName// '银行名称',
       4:optional string bankCard// '银行卡',
       5:optional string transactionId// '银行流水号',
       6:optional AccountBalanceDealStatus dealStatus//状态1申请 2成功 3失败',
       7:optional string dealDesc//处理摘要',
 }
 /**
  * 账户余额处理状态
  **/
  enum AccountBalanceDealStatus{
      APPLY      =1//申请
      SUCCESS     =2//成功
      FAIL      =3//失败
  }
   /**
    * 账户余额处理状态
    **/
    enum AccountBalanceDealType{
        RECHARGE      =1//申请
        WITHDRAW     =2//成功
    }