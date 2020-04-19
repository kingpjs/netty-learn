package stickpackage;

import lombok.Data;

/**
 * @author honghao.zhang
 * Created on 2020-04-14 14:33
 */
@Data
public class CustomMsg {

    /**
     * 类型 系统编号 0XAB 表示A系统， 0XBC 表示B系统
     */
    private byte type;

    /**
     * 信息标志 0XAB 表示心跳包 0XBC 表示超时包 0XCD 业务信息包
     */
    private byte flag;

    /**
     * 主题信息的长度
     */
    private int length;

    /**
     * 主题信息
     */
    private String body;

    public  CustomMsg() {

    }


}
