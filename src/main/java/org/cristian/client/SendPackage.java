package org.cristian.client;

import java.io.Serializable;

public class SendPackage implements Serializable {

    private String nick;
    private String ip;
    private String message;

    public SendPackage(){}

    public SendPackage(String nick, String ip, String message) {
        this.nick = nick;
        this.ip = ip;
        this.message = message;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}