package com.mredust.sqlgenerator.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 表信息表
 *
 * @author <a href="https://github.com/Mredust">Mredust</a>
 * @TableName table_info
 */
@TableName(value = "table_info")
@Data
public class TableInfo implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 表名称
     */
    private String name;
    
    /**
     * 表信息（json）
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
