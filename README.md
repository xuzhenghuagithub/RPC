# rpc
RPC框架 
oderservice   
  order-api（rpc公共接口） 
  order-provider  服务端，发布服务，使用socket接受客户端调用信息，利用反射实现接口调用
  order-provider-anno 自定义注解，利用spring容器自动加载服务接口实例
userservice   调用远程接口，使用动态代理形式封装远程调用方法，利用socket发送调用信息，
              优化后，自定义注解利用spring容器自动加载动态代理的远程接口对象
