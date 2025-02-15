package com.design.spot_check_voucher.common;

/**
 * @Auth: zhuan
 * @Desc: 标准返回码code
 * 状态码
 */
public class StatusCode {

    public static final int OK=2000;//成功
    public static final int ERROR =2001;//失败
    public static final int LOGINERROR =2002;//用户名或密码错误
    public static final int ACCESSERROR =2003;//权限不足
    public static final int REMOTEERROR =2004;//远程调用失败
    public static final int REPERROR =2005;//重复操作
    public static final int ID1=1001;//auditor
    public static final int ID2=1002;//intern
    public static final int ID3=1003;//manager
    public static final int IDE=1004;//账户密码错误


}
