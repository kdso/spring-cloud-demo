* Spring cloud有两种服务调用方式，一种是ribbon+restTemplate，另一种是feign。
* ribbon是一个负载均衡客户端，可以很好的控制htt和tcp的一些行为。Feign默认集成了ribbon。

###运行
* 一个服务注册中心，eureka-server,端口为8761
* service-hi工程跑了两个client实例，端口分别为8762,8763，分别向服务注册中心注册
* sercvice-ribbon端口为8764,向服务注册中心注册
* 当sercvice-ribbon通过restTemplate调用service-hi的hi接口时，因为用ribbon进行了负载均衡，会轮流的调用service-hi：8762和8763 两个端口的hi接口