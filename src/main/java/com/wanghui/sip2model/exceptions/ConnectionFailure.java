package com.wanghui.sip2model.exceptions;

/**
 * @auther wanghui
 * @create 2018-11-13 0:36
 * @Description  连接失败
 */
public class ConnectionFailure extends Exception {
    private static final long serialVersionUID = 5184375852496129646L;

    public ConnectionFailure() {
    }

    public ConnectionFailure(Throwable cause) {
        super(cause);
    }
}
