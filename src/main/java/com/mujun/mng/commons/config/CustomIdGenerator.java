package com.mujun.mng.commons.config;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicLong;

/**
 * ID 生成器，根据传入的实体类对象生成主键
 * author chenjun
 */
//@Component
public class CustomIdGenerator implements IdentifierGenerator {
    private static Logger logger = LoggerFactory.getLogger(CustomIdGenerator.class);

    private final AtomicLong al = new AtomicLong(1);
    @Override
    public Long nextId(Object entity) {
        //可以将当前传入的class全类名来作为bizKey,或者提取参数来生成bizKey进行分布式Id调用生成.
        String bizKey = entity.getClass().getName();
        MetaObject metaObject = SystemMetaObject.forObject(entity);
        String name = (String) metaObject.getValue("name");
        final long id = al.getAndAdd(1);
        logger.info("为{}生成主键值->:{}", name, id);
        return id;
    }
}