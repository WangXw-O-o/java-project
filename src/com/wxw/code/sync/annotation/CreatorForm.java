package com.wxw.code.sync.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface CreatorForm {

    /**
     * 搜索按钮权限
     * @return
     */
    String searchPermi() default "";

    /**
     * 导出按钮权限
     * @return
     */
    String exportPermi() default "";

}
