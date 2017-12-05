Zuul的主要功能是路由转发和过滤器。
默认和Ribbon结合实现了负载均衡的功能。

####测试
启动eureka-service,eureka-client,service-ribbon,service-feign,service-zuul
访问http://localhost:8769/api-a/hi?name=api-a
访问http://localhost:8769/api-b/hi?name=api-b

