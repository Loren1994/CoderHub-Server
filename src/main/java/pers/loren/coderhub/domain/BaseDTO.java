package pers.loren.coderhub.domain;

import java.io.Serializable;

public class BaseDTO implements Serializable {
    private Integer userId;
    private String ip;
    private Integer deviceType;
    private Integer operatorType;
    private String operateDetailContent;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public Integer getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(Integer operatorType) {
        this.operatorType = operatorType;
    }

    public String getOperateDetailContent() {
        return operateDetailContent;
    }

    public void setOperateDetailContent(String operateDetailContent) {
        this.operateDetailContent = operateDetailContent;
    }
}
