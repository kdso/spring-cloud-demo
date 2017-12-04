####简介
Netflix开源了Hystrix组件，实现了断路器模式，SpringCloud对这一组件进行了整合。 
在微服务架构中，一个请求需要调用多个服务是非常常见的,较底层的服务如果出现故障，会导致连锁故障。
当对特定的服务的调用的不可用达到一个阀值（Hystric 是5秒20次） 断路器将会被打开。
断路打开后，可用避免连锁故障，fallback方法可以直接返回一个固定值。

####流程说明:
1:每次调用创建一个新的HystrixCommand,把依赖调用封装在run()方法中.
2:执行execute()/queue做同步或异步调用.
3:判断熔断器(circuit-breaker)是否打开,如果打开跳到步骤8,进行降级策略,如果关闭进入步骤.
4:判断线程池/队列/信号量是否跑满，如果跑满进入降级步骤8,否则继续后续步骤.
5:调用HystrixCommand的run方法.运行依赖逻辑
5a:依赖逻辑调用超时,进入步骤8.
6:判断逻辑是否调用成功
6a:返回成功调用结果
6b:调用出错，进入步骤8.
7:计算熔断器状态,所有的运行状态(成功, 失败, 拒绝,超时)上报给熔断器，用于统计从而判断熔断器状态.
8:getFallback()降级逻辑.
  以下四种情况将触发getFallback调用：
 (1):run()方法抛出非HystrixBadRequestException异常。
 (2):run()方法调用超时
 (3):熔断器开启拦截调用
 (4):线程池/队列/信号量是否跑满
8a:没有实现getFallback的Command将直接抛出异常
8b:fallback降级逻辑调用成功直接返回
8c:降级逻辑调用失败抛出异常
9:返回执行成功结果

####测试
启动client和service
访问hystrix，调用client的方法，然后关闭client，刷新页面，出现fallback的页面

####注
通过命令模式封装调用，来实现弹性保护，继承HystrixCommand类，配置HystrixCommand
