namespace java com.xman.rainbow.carriage.thrift
include 'xman.thrift'
include "carriage/carriage.thrift"
include "order/order.thrift"
include 'common.thrift'

typedef common.ReturnMsg    ReturnMsg
typedef common.LongReturnMsg    LongReturnMsg
typedef common.IdListReturnMsg    IdListReturnMsg
typedef common.BooleanReturnMsg BooleanReturnMsg
typedef common.DoubleReturnMsg DoubleReturnMsg
typedef common.BooleanResultsReturnMsg BooleanResultsReturnMsg
typedef common.IntReturnMsg IntReturnMsg

typedef i32 int
typedef i64 long
typedef carriage.CarriageStatus CarriageStatus
typedef carriage.CarriageInfo   CarriageInfo
typedef carriage.DeclareInfo   DeclareInfo

typedef carriage.CarriageReturnMsg CarriageReturnMsg
typedef carriage.CarriageListReturnMsg CarriageListReturnMsg
typedef order.OrderInfo OrderInfo
typedef carriage.CarriageQueryTerms CarriageQueryTerms

/**
 * 履约服务
 *
 */
service CarriageService  extends xman.BaseService{




    /**
    * 创建履约
    * 根据状态查询履约 list 约履状态（报关成功【已报关】 --轮询运营服务【已派车】“运输中”--轮询运营服务【已提货】--轮询运营服务【已清关】“已完成”）
    * 轮询履约 调用其他接口
    * 根据轮询回来的履约状态需要回调order服务 order提供接口
    * 根据订单号 查询此订单的全部履约记录 list
    *
    **/
  /**
   * 创建履约
   **/
   ReturnMsg createCarriage(1:string appId, 2:string token, 3:string logId,4:OrderInfo orderInfo);

     /**
    * 根据订单号 查询此订单的全部履约记录 list
    **/
   CarriageListReturnMsg queryCarriageByOrderCode(1:string appId, 2:string token, 3:string logId,4:string orderCode);
  /**
   * 报关结果接口
   **/
   ReturnMsg declarationResult(1:string appId, 2:string token, 3:string logId,4:OrderInfo orderInfo,5:bool status);


}


