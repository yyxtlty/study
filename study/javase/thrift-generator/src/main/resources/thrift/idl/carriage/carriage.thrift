namespace java com.xman.rainbow.carriage.thrift.carriage
include 'common.thrift'
typedef i32 int
typedef i64 long


/**
*履约状态 轮询其他系统接口获取订单履约状态，暂无用
*/
enum CarriageStatus{
    BAOGUAN = 10,   //报关
    DAIPAICHE = 20,    //待派车
    YIPAICHE = 30, //已派车
    YITIHUO = 40 ;//已提货
    YIQINGGUAN = 50;//已清关
    FAIL = 99;//履约失败
}
/**
*履约阶段性反馈状态 用于轮询反馈失败的状态到order 平台
*/
enum CarriageBackStatus{
     ERROR = 0,  //反馈失败
     SUCCESS = 1,    //反馈成功
}

enum PayType{
    ALL     =0,//全款
    DEPOSIT =1,  //定金
    BALANCE =2, //余款
}


/**
 * 履约信息
 */
struct CarriageInfo {
    1:optional string   orderCode,                 //订单编号
 	2:optional i32 carriageStatus,              //约履状态
 	3:optional bool backStatus,                   //回调状态
 	4:optional string carriageInfo,                 //履约信息
 	5:optional i64 createTime,                   // 创建时间
 	6:optional string  carriageStatusDesc,    //约履状态描述
}
/**
 * 查询履约信息
 */
struct CarriageQueryInfo {
	1:optional string   orderCode,                 //订单编号
	2:optional string carriageStatus,              //约履状态
	3:optional string backStatus,                  //回调状态
	4:optional string carriageInfo,                //履约信息
	5:optional string createTime,                  // 创建时间
}
const int STATUS_OK = 0
const string MESSAGE_OK = "OK"
/**
 * 订单历史记录响应
 */
struct CarriageListReturnMsg {
	1:required   i32     errorCode=STATUS_OK,
    2:optional   string  errorMsg=MESSAGE_OK,
	3:list<CarriageInfo> carriages,
	4:required   string  logId
}
/**
* 履约查询条件
**/
struct CarriageQueryTerms{
    1:string orderCode,                              //订单编号
    2:optional CarriageStatus carriageStatus,              //订单状态
    3:optional string backStatus,                   //回调状态
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


/**
 * 履约信息响应,针对单个订单
 */
struct CarriageReturnMsg {
    1:required i32     errorCode=STATUS_OK,
    2:optional string  errorMsg=MESSAGE_OK,
	3:optional CarriageQueryInfo carriageQueryInfo, //订单信息
	4:required string  logId
}




/**
* 履约列表分页信息
**/
struct CarriageListPage {
    1:required i32 pageNo
    2:required i32 pageSize
    3:required i32 totalPage
    4:required i32 totalCount
    5:optional list<CarriageInfo> carriageInfoList
}



