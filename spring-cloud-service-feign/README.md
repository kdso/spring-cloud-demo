*   Feign是一个声明式的伪Http客户端，它使得写Http客户端变得更简单。
    使用Feign，只需要创建一个接口并注解。
    它具有可插拔的注解特性，可使用Feign 注解和JAX-RS注解。
    Feign支持可插拔的编码器和解码器。
    Feign默认集成了Ribbon，并和Eureka结合，默认实现了负载均衡的效果。
    
###运行
* 一个服务注册中心，eureka-server,端口为8761
* service-hi工程跑了两个client实例，端口分别为8762,8763，分别向服务注册中心注册
* sercvice-ribbon端口为8764,向服务注册中心注册
* 当sercvice-ribbon通过restTemplate调用service-hi的hi接口时，因为用ribbon进行了负载均衡，会轮流的调用service-hi：8762和8763 两个端口的hi接口
