package com.gupaoedu.rpc;
@GPRemoteService
public class OrderServiceimpl implements IOrderService {
    @Override
    public String queryOrderList() {
        return "EXCUTE QUERYORDERLIST METHOD";
    }

    @Override
    public String queryOrderById() {
        return "EXCUTE QUERYORDERBYID METHOD";
    }
}
