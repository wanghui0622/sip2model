package com.wanghui.sip2model.model;

import com.wanghui.sip2model.annotations.Command;
import com.wanghui.sip2model.annotations.TestCaseDefault;
import com.wanghui.sip2model.annotations.TestCasePopulated;

/**
 * @auther wanghui
 * @create 2018-11-13 0:36
 * @Description
 */
@Command("96")
@TestCaseDefault("96AZFEF6")
@TestCasePopulated("96AZFEF6")
public class SCResend extends Message {
    private static final long serialVersionUID = 7077005327500597112L;

    @Override
    protected String addChecksum(String command, Character sequence) {
        StringBuffer check = new StringBuffer();
        check.append("AZ");
        try {
            check.append(calculateChecksum(command + check.toString()));
            return command + check.toString();
        } catch (Exception e) {
            return command;
        }
    }
}
