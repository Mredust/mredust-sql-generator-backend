package com.mredust.sqlgenerator.sql;

import com.mredust.sqlgenerator.core.GeneratorFacade;
import com.mredust.sqlgenerator.core.builder.TableSchemaBuilder;
import com.mredust.sqlgenerator.core.model.vo.GenerateVO;
import com.mredust.sqlgenerator.core.schema.TableSchema;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

/**
 * @author <a href="https://github.com/Mredust">Mredust</a>
 */
@SpringBootTest
class SqlTest {
    @Test
    void generateSqlTest() {
        TableSchema tableSchema = new TableSchema();
        tableSchema.setDatabaseName("db");
        tableSchema.setTableName("user");
        tableSchema.setTableComment("用户表");
        tableSchema.setMockNum(0);
        ArrayList<TableSchema.Field> list = new ArrayList<>();
        TableSchema.Field field = new TableSchema.Field();
        field.setFieldName("id");
        field.setFieldType("int");
        field.setNotNull(true);
        field.setComment("主键");
        field.setPrimaryKey(true);
        field.setAutoIncrement(true);
        
        
        TableSchema.Field field1 = new TableSchema.Field();
        field1.setFieldName("username");
        field1.setFieldType("varchar");
        field1.setFieldLength("255");
        field1.setNotNull(false);
        field1.setComment("用户昵称");
        field1.setPrimaryKey(false);
        field1.setAutoIncrement(false);
        
        TableSchema.Field field2 = new TableSchema.Field();
        field2.setFieldName("sex");
        field2.setFieldType("tinyint");
        field2.setFieldLength("1");
        field2.setDefaultValue("2");
        field2.setNotNull(true);
        field2.setComment("性别 0-女 1-男 2-未知");
        field2.setPrimaryKey(false);
        field2.setAutoIncrement(false);
        list.add(field);
        list.add(field1);
        list.add(field2);
        tableSchema.setFieldList(list);
        
        GenerateVO generateVO = GeneratorFacade.generateData(tableSchema);
    }
    
    @Test
    void generateTableSchemaTest() {
        String sql = "-- 用户表\n" +
                " create table if not exists `user` (\n" +
                " `id` int not null auto_increment comment '主键' primary key,\n" +
                "`username` varchar(255) null comment '用户昵称',\n" +
                "`sex` tinyint(1) not null default 2 comment '性别 0-女 1-男 2-未知' \n" +
                ") comment='用户表';";
        TableSchema tableSchema = TableSchemaBuilder.buildTableSchema(sql);
        System.out.println(tableSchema);
    }
}
