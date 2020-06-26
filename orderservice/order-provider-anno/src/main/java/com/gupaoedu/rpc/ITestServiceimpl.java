package com.gupaoedu.rpc;

@GPRemoteService
public class ITestServiceimpl implements ITestService {
    @Override
    public String test() {
        return "ITestServiceimpl 的test方法";
    }
}
