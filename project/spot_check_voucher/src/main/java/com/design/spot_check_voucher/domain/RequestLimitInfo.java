package com.design.spot_check_voucher.domain;

/**
 * Created with IntelliJ IDEA
 *
 * @Author: Yuyao
 * @Date: 2023/04/19/14:33
 * @Description:
 */
public class RequestLimitInfo {
    private int remainingRequests;
    private int maxCount;
    private long resetTime;

    public RequestLimitInfo(int maxCount, long resetTime) {
        this.maxCount = maxCount;
        this.remainingRequests = maxCount;
        this.resetTime = resetTime;
    }

    public int getRemainingRequests() {
        return remainingRequests;
    }

    public void decrement() {
        this.remainingRequests--;
    }

    public long getResetTime() {
        return resetTime;
    }

    public void reset(long currentTime) {
        this.remainingRequests = maxCount;
        this.resetTime = currentTime;
    }
}
