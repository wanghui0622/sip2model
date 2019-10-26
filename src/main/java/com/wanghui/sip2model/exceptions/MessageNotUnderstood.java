package com.wanghui.sip2model.exceptions;


/**
 * @auther wanghui
 * @create 2018-11-13 0:36
 * @Description  非法消息异常
 */
public class MessageNotUnderstood extends Exception {

    private static final long serialVersionUID = 1857825095575274480L;

    public MessageNotUnderstood() {
      super();
    }

    public MessageNotUnderstood(Throwable ex) {
      super(ex);
    }
}
