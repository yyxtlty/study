namespace java com.xman.rainbow.finance.thrift
include 'xman.thrift'
include "pay/pay.thrift"
include 'common.thrift'


typedef common.ReturnMsg    ReturnMsg
typedef pay.OrderPayInfo    OrderPayInfo
typedef pay.OrderTradeVo OrderTradeVo
typedef pay.PayReturnMsg  PayReturnMsg
typedef pay.SettleOnSupplyInfo SettleOnSupplyInfo
typedef pay.SettleOnPurchaseInfo SettleOnPurchaseInfo
typedef pay.PayStep PayStep
typedef pay.AccountJournalListPageReturnMsg AccountJournalListPageReturnMsg
typedef pay.AccountJournalQueryTerms AccountJournalQueryTerms
typedef pay.AccountJournalListPage AccountJournalListPage
typedef pay.AccountJournalInfo AccountJournalInfo
typedef pay.SettleReturnMsg SettleReturnMsg
typedef pay.AccountListPageReturnMsg AccountListPageReturnMsg
typedef pay.AccountQueryTerms AccountQueryTerms
typedef pay.AccountListPage AccountListPage
typedef pay.AccountInfo AccountInfo
typedef pay.AccountReturnMsg AccountReturnMsg
typedef pay.AccountRegisterForB AccountRegisterForB
typedef pay.AccountRegisterForS AccountRegisterForS
typedef pay.AccountRegisterForP AccountRegisterForP
typedef pay.AccountStatusInfo AccountStatusInfo
typedef pay.BankCardInfo BankCardInfo

typedef pay.TradeRecordInfo TradeRecordInfo
typedef pay.TradeRecordListPageReturnMsg TradeRecordListPageReturnMsg
typedef pay.TradeRecordQueryTerms TradeRecordQueryTerms
typedef pay.AccountBalanceDealInfo AccountBalanceDealInfo
typedef pay.AccountRechargeApplyInfo AccountRechargeApplyInfo
typedef pay.AccountRechargeDealnfo AccountRechargeDealnfo
typedef pay.AccountWithdrawApplyInfo AccountWithdrawApplyInfo
typedef pay.AccountWithdrawDealnfo AccountWithdrawDealnfo
typedef pay.AccountBalanceDealReturnMsg AccountBalanceDealReturnMsg
typedef pay.AccountBalanceDealListPageReturnMsg AccountBalanceDealListPageReturnMsg
typedef pay.AccountBalanceDealQueryTerms AccountBalanceDealQueryTerms
typedef i32 int
typedef i64 long
/**
 * 支付服务
 *
 */
service PayService{

/**
* 边民买入付款(供应商平台)
**/
    PayReturnMsg payOnSupplier(1:string appId,2:string token, 3:string logId,4:long userId,5:OrderPayInfo orderPayInfo );

/**
* 边民买入退款(供应商平台)
**/
    PayReturnMsg refundOnSupplier(1:string appId,2:string token, 3:string logId,4:long userId,5:OrderTradeVo orderTradeVo );


/**
* 供应商平台结算
**/
    ReturnMsg settlementOnSupply(1:string appId,2:string token, 3:string logId,4:long userId,5:list<SettleOnSupplyInfo> settleOnSupplyList);

/**
* 采购商平台结算
**/
    SettleReturnMsg settlementOnPurchase(1:string appId,2:string token, 3:string logId,4:long userId,5:list<SettleOnPurchaseInfo> settleOnPurchaseList);

/**
* 账户注册--边民
**/
    ReturnMsg registerAccountForB(1:string appId, 2:string token, 3:string logId,4:AccountRegisterForB accountRegisterForB);

/**
* 账户注册--供应商
**/
    ReturnMsg registerAccountForS(1:string appId, 2:string token, 3:string logId,4:AccountRegisterForS accountRegisterForS);

/**
* 账户注册--采购商
**/
    ReturnMsg registerAccountForP(1:string appId, 2:string token, 3:string logId,4:AccountRegisterForP accountRegisterForP);

/**
* 修改账户状态（正常、失效）
**/
    ReturnMsg accountStatus(1:string appId, 2:string token, 3:string logId,4:AccountStatusInfo info);

/**
* 账户充值
**/
    ReturnMsg accountRecharge(1:string appId, 2:string token, 3:string logId,4:long userId,5:long accountId,6:string transactionId,7:long rechargeAmount);
/**
* 账户余额预支付，余额冻结
**/
    ReturnMsg accountFreeze(1:string appId, 2:string token, 3:string logId,4:long userId,5:long accountId,6:string orderCode,7:long amount);
/**
* 账户冻结金额解冻
**/
    ReturnMsg accountUnFreeze(1:string appId, 2:string token, 3:string logId,4:long userId,5:long accountId,6:string orderCode,7:long amount);
/**
* 账户支付
**/
    ReturnMsg accountPay(1:string appId, 2:string token, 3:string logId,4:long userId,5:OrderPayInfo orderPayInfo);
/**
* 账户退款
**/
    ReturnMsg accountRefund(1:string appId, 2:string token, 3:string logId,4:long userId,5:OrderPayInfo orderPayInfo);
/**
* 运营对账户余额提现
**/
    ReturnMsg accountWithdrawByMis(1:string appId, 2:string token, 3:string logId,4:AccountBalanceDealInfo accountBalanceDealInfo);
/**
* 运营对账户充值
**/
    ReturnMsg accountRechargeByMis(1:string appId, 2:string token, 3:string logId,4:AccountBalanceDealInfo accountBalanceDealInfo);
/**
* 账户余额提现
**/
    ReturnMsg accountWithdraw(1:string appId, 2:string token, 3:string logId,4:long userId,5:long accountId,6:long withDrawAmount,7:string transactionId);
/**
* 账户余额提现申请
**/
    ReturnMsg accountWithdrawApply(1:string appId, 2:string token, 3:string logId,4:AccountWithdrawApplyInfo accountWithdrawApplyInfo);
/**
* 账户余额提现确认
**/
    ReturnMsg accountWithdrawDeal(1:string appId, 2:string token, 3:string logId,4:AccountWithdrawDealnfo accountWithdrawDealnfo);

/**
* 账户充值申请
**/
    ReturnMsg accountRechargeApply(1:string appId, 2:string token, 3:string logId,4:AccountRechargeApplyInfo accountRechargeApplyInfo);
/**
* 账户充值申请
**/
    ReturnMsg accountRechargeDeal(1:string appId, 2:string token, 3:string logId,4:AccountRechargeDealnfo accountRechargeDealnfo);
/**
* 账户处理信息查询
**/
    AccountBalanceDealReturnMsg queryAccountBalanceDeal(1:string appId, 2:string token, 3:string logId,4:long userId);
/**
* 账户处理分页查询
**/
    AccountBalanceDealListPageReturnMsg queryAccountBalanceDealList(1:string appId, 2:string token, 3:string logId,5:AccountBalanceDealQueryTerms terms,6:i32 pageNo,7:i32 pageSize);
/**
* 账户信息分页查询
**/
    AccountListPageReturnMsg queryAccountList(1:string appId, 2:string token, 3:string logId,5:AccountQueryTerms terms,6:i32 pageNo,7:i32 pageSize);

/**
* 流水分页查询
**/
    AccountJournalListPageReturnMsg queryAccountJournal(1:string appId, 2:string token, 3:string logId,5:AccountJournalQueryTerms terms,6:i32 pageNo,7:i32 pageSize);
/**
* 账户信息查询
**/
    AccountReturnMsg queryAccount(1:string appId, 2:string token, 3:string logId,4:long userId,5:long accountId);
/**
* 交易流水分页查询
**/
    TradeRecordListPageReturnMsg queryTradeRecordList(1:string appId, 2:string token, 3:string logId,5:TradeRecordQueryTerms terms,6:i32 pageNo,7:i32 pageSize);
/**
* 交易流水查询
**/
    TradeRecordListPageReturnMsg queryTradeRecordListNoPage(1:string appId, 2:string token, 3:string logId,5:TradeRecordQueryTerms terms);
/**
* 根据账户id修改银行卡信息
**/
    ReturnMsg updateBankCardByUserId(1:string logId,2:BankCardInfo backCardInfo);
}