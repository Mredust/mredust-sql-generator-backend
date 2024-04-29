package com.mredust.sqlgenerator.core.builder.sql;

import com.mredust.sqlgenerator.common.ResponseCode;
import com.mredust.sqlgenerator.exception.BusinessException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SQL 方言工厂
 * 工厂 + 单例模式：降低开销
 *
 * @author <a href="https://github.com/Mredust">Mredust</a>
 */
public class SQLDialectFactory {
    private SQLDialectFactory() {
    }
    
    /**
     * className => 方言实例映射
     */
    private static final Map<String, SQLDialect> DIALECT_POOL = new ConcurrentHashMap<>();
    
    public static SQLDialect getDialect(String className) {
        SQLDialect sqlDialect = DIALECT_POOL.get(className);
        if (sqlDialect == null) {
            synchronized (className.intern()) {
                sqlDialect = DIALECT_POOL.computeIfAbsent(className, key -> {
                    try {
                        return (SQLDialect) Class.forName(className).newInstance();
                    } catch (Exception e) {
                        throw new BusinessException(ResponseCode.SYSTEM_ERROR);
                    }
                });
            }
        }
        return sqlDialect;
    }
}
