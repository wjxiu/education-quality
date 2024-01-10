package com.github.wjxiu.DTO;

import lombok.Data;

import java.util.List;

/**
 * @author xiu
 * @create 2024-01-09 19:37
 */
@Data
public class TableDataInfo {
    private static final long serialVersionUID = 153L;

    /** 总记录数 */
    private long total;

    /** 列表数据 */
    private List<?> rows;

    /** 消息状态码 */
    private int code;

    /** 消息内容 */
    private String msg;
}
