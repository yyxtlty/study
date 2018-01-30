namespace java com.xman.rainbow.finance.thrift
include 'xman.thrift'
include "settlement/settlement.thrift"
include 'common.thrift'
include 'order/order.thrift'

typedef common.ReturnMsg    ReturnMsg
typedef common.LongReturnMsg    LongReturnMsg
typedef common.IdListReturnMsg    IdListReturnMsg
typedef common.BooleanReturnMsg BooleanReturnMsg
typedef common.DoubleReturnMsg DoubleReturnMsg
typedef common.BooleanResultsReturnMsg BooleanResultsReturnMsg
typedef common.IntReturnMsg IntReturnMsg
typedef common.ListMapReturnMsg ListMapReturnMsg
typedef settlement.BillListPageReturnMsg BillListPageReturnMsg
typedef settlement.BillReturnMsg BillReturnMsg
typedef settlement.SettlementListReturnMsg SettlementListReturnMsg
typedef settlement.SettlementReturnMsg SettlementReturnMsg
typedef settlement.BillQueryTerms BillQueryTerms
typedef settlement.SettlementQueryTerms SettlementQueryTerms
typedef settlement.BillListReturnMsg BillListReturnMsg
typedef settlement.BillSettlementPageReturnMsg BillSettlementPageReturnMsg
typedef order.OrderInfo OrderInfo

typedef i32 int
typedef i64 long

/**
 * 结算服务
 *
 */
service SettlementService {

    /**
    * 创建账单
    **/
    ReturnMsg createBill(1:string appId, 2:string token, 3:string logId,4:OrderInfo orderInfo);
     /**
    * 账单状态改成待结算
    **/
    ReturnMsg pending(1:string appId, 2:string token, 3:string logId,4:string orderCode);
    /**
    * 查询账单结算统计
    **/
    BillSettlementPageReturnMsg queryBillSettlementPage(1:string appId, 2:string token, 3:string logId,5:SettlementQueryTerms terms,6:i32 pageNo,7:i32 pageSize);
    /**
    * 查询结算列表
    **/
    SettlementListReturnMsg querySettlementByBillDate(1:string appId, 2:string token, 3:string logId,5:i64 billDate,6:i64 shopId);
    /**
    * 根据结算单号查结算信息
    **/
    SettlementReturnMsg querySettlementById(1:string appId, 2:string token, 3:string logId,4:i64 seId);
    /**
    * 查询账单列表
    **/
    BillListPageReturnMsg queryBillPage(1:string appId, 2:string token, 3:string logId,5:BillQueryTerms terms,6:i32 pageNo,7:i32 pageSize);
    /**
    * 查询账单列表 不分页
    **/
    BillListReturnMsg queryBillList(1:string appId, 2:string token, 3:string logId,5:BillQueryTerms terms);

    /**
    * 根据账单ID查账单
    **/
    BillReturnMsg queryBillById(1:string appId, 2:string token, 3:string logId,4:i64 billId);

    /**
    * 预结算账单
    **/
    ReturnMsg preSettlement(1:string appId, 2:string token, 3:string logId,4:i64 userId,5:i64 billDate,6:i64 shopId);
    /**
    * 结算
    **/
    ReturnMsg settlement(1:string appId, 2:string token, 3:string logId,4:i64 userId,5:i64 seId);
    /**
    * 账单单存疑
    **/
    ReturnMsg doubtfulBill(1:string appId, 2:string token, 3:string logId,4:i64 billId,5:string remarks);
    /**
    * 存疑账单解决
    **/
    ReturnMsg solveDoubtfulBill(1:string appId, 2:string token, 3:string logId,4:i64 billId,5:string remarks);
    /**
    * 关闭账单,解决账单
    **/
    ReturnMsg closedBill(1:string appId, 2:string token, 3:string logId,4:i64 billId,5:string remarks);

     /**
     *   按日期统计订单数据
     **/
     ListMapReturnMsg appDataSettlementCount(1:string appId, 2:string token, 3:string logId,4:string billDate);

}


