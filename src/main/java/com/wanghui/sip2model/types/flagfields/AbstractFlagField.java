package com.wanghui.sip2model.types.flagfields;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @auther wanghui
 * @create 2018-11-13 0:36
 * @Description
 */
public abstract class AbstractFlagField implements Serializable {

    private static final long serialVersionUID = -3439876098750195408L;
    private String flags;

    AbstractFlagField(String flags) {
        this.flags = flags;
        this.checkLength();
    }

    abstract protected int getLength();

    abstract protected char[] getValid();

    private boolean isValid(char ch) {
        for (char valid : this.getValid()) {
            if (ch == valid) {
                return true;
            }
        }
        return false;
    }

    private void checkLength() {
        StringBuffer result = new StringBuffer("");
        for (int i = 0; i < this.getLength(); i++) {
            if (i < this.flags.length()) {
                char ch = this.flags.charAt(i);
                if (this.isValid(ch)) {
                    result.append(ch);
                } else {
                    result.append(this.getValid()[0]);
                }
            } else {
                result.append(this.getValid()[0]);
            }
        }
        this.flags = result.toString();
    }

    @Override
    public String toString() {
        this.checkLength();
        return this.flags;
    }

    protected boolean isSet(int field) {
        this.checkLength();
        if ((field < this.flags.length()) && (this.getValid().length > 0)) {
            if (this.flags.charAt(field) == this.getValid()[this.getValid().length - 1]) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    protected void set(int field, boolean flag) {
        if (flag) {
            this.set(field);
        } else {
            this.unset(field);
        }
    }

    private void set(int field) {
        this.checkLength();
        if ((field < this.flags.length()) && (this.getValid().length > 0)) {
            this.flags = this.flags.substring(0, field) + this.getValid()[this.getValid().length - 1] + this.flags.substring(field + 1);
        }
    }

    private void unset(int field) {
        this.checkLength();
        if ((field < this.flags.length()) && (this.getValid().length > 0)) {
            this.flags = this.flags.substring(0, field) + this.getValid()[0] + this.flags.substring(field + 1);
        }
    }

    public void clear() {
        this.flags = "";
        this.checkLength();
    }

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
    }

}
