package com.fourous.nettyProxy.meta;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: wonderzh
 * @Date: 2020/8/10
 * @Version: 2.0
 */
public class HfPayload {
    /**
     * 监控量id
     */
    private String itemId;
    /**
     * 量的值
     */
    private List<Double> value;
    /**
     * 本组数据中最大值
     */
    private Double maximum;
    /**
     * 本组数据中最小值
     */
    private Double minimum;
    /**
     * 数据采集开始时间
     */
    private Date startTime;
    /**
     * 数据采集结束时间
     */
    private Date endTime;
    /**
     * 频率
     */
    private Integer frequency;

    private String collectionId;

    private String collectionName;

    private String deviceId;

    private Map<String,String> extraInfo;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public List<Double> getValue() {
        return value;
    }

    public void setValue(List<Double> value) {
        this.value = value;
    }

    public Double getMaximum() {
        return maximum;
    }

    public void setMaximum(Double maximum) {
        this.maximum = maximum;
    }

    public Double getMinimum() {
        return minimum;
    }

    public void setMinimum(Double minimum) {
        this.minimum = minimum;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Map<String, String> getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(Map<String, String> extraInfo) {
        this.extraInfo = extraInfo;
    }
}
