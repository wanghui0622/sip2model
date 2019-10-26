package com.wanghui.sip2model.model;

import com.wanghui.sip2model.annotations.Command;
import com.wanghui.sip2model.annotations.TestCaseDefault;
import com.wanghui.sip2model.annotations.TestCasePopulated;

/**
 * @auther wanghui
 * @create 2018-11-13 0:36
 * @Description
 */
@Command("97")
@TestCaseDefault("97AZFEF5")
@TestCasePopulated("97AZFEF5")
public class ACSResend extends Message {
    private static final long serialVersionUID = 1455544775405713654L;
    
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
