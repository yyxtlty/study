namespace java com.xman.rainbow.order.thrift.common

struct HelloMsg {
    1:required  i32     errorCode
    2:optional  string  errorMsg
    3:optional  string  data
}

service BaseService {
    HelloMsg stats()
}