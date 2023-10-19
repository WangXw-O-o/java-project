package com.wxw.code.sync.annotation;

import com.wxw.code.sync.common.InputTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface CreatorFormItem {

    int sort();

    /**
     * 名称
     * @return
     */
    String name();

    /**
     * 字段命名
     * @return
     */
    String key();

    /**
     * 表单项的类型
     * @return
     */
    InputTypeEnum type();

    /**
     * 权限
     * @return
     */
    String hasPermi() default "";

    /**
     * 日期选择器的名称
     * @return
     */
    String dateName() default "";

    /**
     * 日期选择器的类型：1:开始时间，2:结束时间
     * @return
     */
    int dateType() default 0;

    /**
     * 字符串，使用逗号隔开，key1,name1,key2,name2,key3,name3，组数不限制
     * 如：1,类型1,2,类型2,3,类型3
     * @return
     */
    String selectOption() default "";

}
