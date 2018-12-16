package com.example.oalam.firephoneauth;

public class datapH {
    String phid;
    String phValue;
    String token;

    public datapH(String phid, String phValue, String token) {
        this.phid = phid;
        this.phValue = phValue;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public String getPhid() {
        return phid;
    }

    public String getPhValue() {
        return phValue;
    }
}
