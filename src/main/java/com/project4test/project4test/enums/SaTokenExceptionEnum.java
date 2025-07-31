package com.project4test.project4test.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * SaToken异常码枚举类
 * 统一管理SaToken相关的异常状态码和描述信息
 */
@Getter
@AllArgsConstructor
public enum SaTokenExceptionEnum {

    // 基础异常
    UNSPECIFIED_EXCEPTION(-1, "代表这个异常在抛出时未指定异常细分状态码"),

    // 上下文相关异常
    CONTEXT_PROCESSOR_INVALID(10001, "未能获取有效的上下文处理器"),
    CONTEXT_INVALID(10002, "未能获取有效的上下文"),
    JSON_CONVERTER_UNIMPLEMENTED(10003, "JSON 转换器未实现"),

    // StpLogic相关异常
    STP_LOGIC_NOT_FOUND(10011, "未能从全局 StpLogic 集合中找到对应 type 的 StpLogic"),

    // 配置相关异常
    CONFIG_FILE_LOAD_FAILED(10021, "指定的配置文件加载失败"),
    CONFIG_PROPERTY_READ_FAILED(10022, "配置文件属性无法正常读取"),

    // 侦听器相关异常
    LISTENER_COLLECTION_EMPTY(10031, "重置的侦听器集合不可以为空"),
    LISTENER_NULL(10032, "注册的侦听器不可以为空"),

    // Token相关异常
    SAME_TOKEN_INVALID(10301, "提供的 Same-Token 是无效的"),

    // 认证相关异常
    HTTP_BASIC_AUTH_FAILED(10311, "表示未能通过 Http Basic 认证校验"),
    HTTP_METHOD_INVALID(10321, "提供的 HttpMethod 是无效的"),

    // 登录与Token相关异常
    TOKEN_NOT_FOUND(11011, "未能读取到有效Token"),
    LOGIN_ID_EMPTY(11002, "登录时的账号id值为空"),
    TOKEN_CHANGE_ID_EMPTY(11003, "更改 Token 指向的 账号Id 时，账号Id值为空"),
    TOKEN_INVALID(11012, "Token无效"),
    TOKEN_EXPIRED(11013, "Token已过期"),
    TOKEN_REPLACED(11014, "Token已被顶下线"),
    TOKEN_KICKED_OUT(11015, "Token已被踢下线"),
    TOKEN_FROZEN(11016, "Token已被冻结"),

    // JWT相关异常
    JWT_PLUGIN_REQUIRED(11031, "在未集成 sa-token-jwt 插件时调用 getExtra() 抛出异常"),

    // 权限相关异常
    ROLE_MISSING(11041, "缺少指定的角色"),
    PERMISSION_MISSING(11051, "缺少指定的权限"),

    // 服务封禁相关异常
    SERVICE_BAN_CHECK_FAILED(11061, "当前账号未通过服务封禁校验"),
    UNBAN_ACCOUNT_INVALID(11062, "提供要解禁的账号无效"),
    UNBAN_SERVICE_INVALID(11063, "提供要解禁的服务无效"),
    UNBAN_LEVEL_INVALID(11064, "提供要解禁的等级无效"),

    // 二级认证相关异常
    TWO_FACTOR_AUTH_FAILED(11071, "二级认证校验未通过"),

    // 参数相关异常
    PARAMETER_MISSING(12001, "请求中缺少指定的参数"),
    COOKIE_NAME_MISSING(12002, "构建 Cookie 时缺少 name 参数"),
    COOKIE_VALUE_MISSING(12003, "构建 Cookie 时缺少 value 参数"),

    // 编码解码相关异常
    BASE64_ENCODE_ERROR(12101, "Base64 编码异常"),
    BASE64_DECODE_ERROR(12102, "Base64 解码异常"),
    URL_ENCODE_ERROR(12103, "URL 编码异常"),
    URL_DECODE_ERROR(12104, "URL 解码异常"),

    // 加密相关异常
    MD5_ENCRYPT_ERROR(12111, "md5 加密异常"),
    SHA1_ENCRYPT_ERROR(12112, "sha1 加密异常"),
    SHA256_ENCRYPT_ERROR(12113, "sha256 加密异常"),
    AES_ENCRYPT_ERROR(12114, "AES 加密异常"),
    AES_DECRYPT_ERROR(12115, "AES 解密异常"),
    RSA_PUBLIC_ENCRYPT_ERROR(12116, "RSA 公钥加密异常"),
    RSA_PRIVATE_ENCRYPT_ERROR(12117, "RSA 私钥加密异常"),
    RSA_PUBLIC_DECRYPT_ERROR(12118, "RSA 公钥解密异常"),
    RSA_PRIVATE_DECRYPT_ERROR(12119, "RSA 私钥解密异常"),

    // 签名相关异常
    SIGNATURE_KEY_EMPTY(12201, "参与参数签名的秘钥不可为空"),
    SIGNATURE_INVALID(12202, "给定的签名无效"),
    TIMESTAMP_OUT_OF_RANGE(12203, "timestamp 超出允许的范围");

    /**
     * 异常状态码
     */
    private final int code;

    /**
     * 异常描述信息
     */
    private final String msg;


}
