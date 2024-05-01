package com.mredust.sqlgenerator.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 词库表
 * @author <a href="https://github.com/Mredust">Mredust</a>
 * @TableName dict
 */
@TableName(value ="dict")
@Data
public class Dict implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 词库名称
     */
    private String name;

    /**
     * 词库内容（json 数组）
     */
    private String content;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
