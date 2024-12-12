package com.design.spot_check_voucher.common;

/**
 * @Auth: zhuan
 * @Desc: 返回结果消息提示常量类
 */
public class MessageConstant {
    //---------------------------抽凭信息（Voucheck）操作消息提示信息---------------------------------------
    public static final String VOUCHECK_SEARCH_SUCCESS="查询凭证列表信息成功！";
    public static final String VOUCHECK_ADD_SUCCESS="新增凭证信息成功！";
    public static final String VOUCHECK_ADD_FAIL="新增凭证信息失败！";
    public static final String VOUCHECK_UPDATE_SUCCESS="修改凭证信息成功！";
    public static final String VOUCHECK_DELETE_SUCCESS="删除凭证信息成功！";
    public static final String VOUCHECK_PIC_UPLOAD_SUCCESS="凭证文件上传成功！";
    public static final String VOUCHECK_PIC_DEL_SUCCESS = "凭证文件删除成功！";
    public static final String VOUCHECK_FIND_BY_ID_SUCCESS = "根据主键获取凭证对象成功！";
    public static final String VOUCHECK_ADDCHECK_SUCCESS = "更新抽查列表成功！";
    //---------------------------实习生信息（Intern）操作消息提示信息---------------------------------------
    public static final String INTERN_SEARCH_SUCCESS="查询实习生列表信息成功！";
    public static final String INTERN_ADD_SUCCESS="新增实习生信息成功！";
    public static final String INTERN_ADD_FAIL="新增实习生信息失败！";
    public static final String INTERN_UPDATE_SUCCESS="修改实习生信息成功！";
    public static final String INTERN_DELETE_SUCCESS="删除实习生信息成功！";
    public static final String INTERN_FIND_BY_ID_SUCCESS = "根据主键获取实习生对象成功！";
    public static final String INTERN_UPDATE_STATUS_SUCCESS = "实习生状态信息更新成功！";
    //---------------------------审计师信息（Auditor）操作消息提示信息---------------------------------------
    public static final String AUDITOR_SEARCH_SUCCESS="查询审计师生列表信息成功！";
    public static final String AUDITOR_ADD_SUCCESS="新增审计师信息成功！";
    public static final String AUDITOR_ADD_FAIL="新增审计师信息失败！";
    public static final String AUDITOR_UPDATE_SUCCESS="修改审计师信息成功！";
    public static final String AUDITOR_DELETE_SUCCESS="删除审计师信息成功！";
    public static final String AUDITOR_FIND_BY_ID_SUCCESS = "根据主键获取审计师对象成功！";
    public static final String AUDITOR_UPDATE_STATUS_SUCCESS = "审计师状态信息更新成功！";
    //---------------------------系统提示信息----------------------------------------------------------
    public static final String SYSTEM_BUSY = "系统繁忙，请求稍后重试！";
    //---------------------------登录提示信息-------------------------------------------------------
    public static final String LOGIN_ERR = "账号或密码错误！";
    //---------------------------文件上传提示信息-------------------------------------------------------
    public static final String NO_FILE_SELECTED = "未选择上传的文件,请求选择后上传!";
    public static final String NO_WRITE_PERMISSION = "上传目录没有写权限!";
    public static final String INCORRECT_DIRECTORY_NAME = "目录名不正确!";
    public static final String SIZE_EXCEEDS__LIMIT = "上传文件大小超过限制!";
    public static final String FILE_TYPE_ERROR = "文件类型错误，只允许上传JPG/PNG/JPEG/GIF等图片类型的文件!";
    public static final String FILE_INDENTIFY_SUCCESS="图片识别成功";


}
