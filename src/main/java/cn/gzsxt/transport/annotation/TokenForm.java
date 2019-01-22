package cn.gzsxt.transport.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 声明一个防重提的注解
 * @author ranger
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TokenForm {
	
	/**
	 * 用于标识创建token
	 * @return
	 */
	boolean create() default false;
	/**
	 * 用于标识删除token
	 * @return
	 */
	boolean remove() default false;

}
