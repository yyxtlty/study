include 'xman.thrift'

namespace java com.xman.rainbow.order.thrift.common

struct ReturnMsg {
    1:required  i32     errorCode
    2:optional  string  errorMsg
    3:optional  string  data
    4:required  string  logId
}

struct LongReturnMsg {
    1:required  i32     errorCode
    2:optional  string  errorMsg
    3:optional  i64  data
    4:required  string  logId
}

struct IntReturnMsg {
    1:required  i32     errorCode
    2:optional  string  errorMsg
    3:optional  i32  data
    4:required  string  logId
}
struct IdListReturnMsg {
    1:required  i32     errorCode
    2:optional  string  errorMsg
    3:optional  set<i64>  data
    4:required  string  logId
}

struct BooleanResult {
    1:required  bool  valid
    2:optional string reason
}

struct BooleanReturnMsg {
    1:required  i32     errorCode
    2:optional  string  errorMsg
    3:optional  BooleanResult  result
    4:required  string  logId
}

struct BooleanResultsReturnMsg {
    1:required  i32   errorCode
    2:optional  string  errorMsg
    3:optional map<string, BooleanResult> result
    4:required  string  logId
}
struct DoubleReturnMsg {
    1:required  i32     errorCode
    2:optional  string  errorMsg
    3:optional  double  data
    4:required  string  logId
}

struct ListMapReturnMsg {
    1:required  i32     errorCode
    2:optional  string  errorMsg
    3:optional  list<map<string,string>>  data
    4:required  string  logId
}


struct PageResp{
    /** 总页数 **/
    1: optional i32 totalpage;
    /** 每页显示记录数 **/
    2: optional i32 pageSize;
    /** 当前页 **/
    3: optional i32 currentpage;
    /** 总个数 **/
    4: optional i32 totalCount;
}


struct QueryPage{
    /** 每页显示记录数 **/
    1: optional i32 pageSize;
     /** 查询页码 **/
    2: optional i32 pageCode;
}

/**查询区间**/
struct QueryRange{
       1:optional i64 startValue;//区间开始值
       2:optional i64 endValue;//区间结束值
}

enum OrderSection{
     //产品section
     price=101,//价格
     total_amount=102,//库存
     sales=103,//售卖数量
     create_time=104,//创建时间
     //订单section
     item_price=201,//商品单价
     amount=202,//订单总价
     item_count=203,//购买数量
     pay_time=204,//支付时间
     product_name=205//产品名称
}

enum OrderRule{
      DESC=1,//降序
      ASC=2  //升序
}
