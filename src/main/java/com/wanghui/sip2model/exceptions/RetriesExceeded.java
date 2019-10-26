package com.wanghui.sip2model.exceptions;


/**
 * @auther wanghui
 * @create 2018-11-13 0:36
 * @Description  超过重试次数
 */
public class RetriesExceeded extends Exception {

    private static final long serialVersionUID = 1416841113916472161L;

    public RetriesExceeded() {
      super();
    }
    
    public RetriesExceeded(Throwable ex) {
      super(ex);
    }
}
