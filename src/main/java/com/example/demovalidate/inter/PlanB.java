package com.example.demovalidate.inter;

import javax.validation.groups.Default;

/**
 * 继承 Default 使用 @Validated({PlanB.class})时【同时执行】未设置分组的校验注解
 * 不继承时 使用 @Validated({PlanB.class})【不会校验】 未设置分组的校验注解
 */
public interface PlanB extends Default {
}
