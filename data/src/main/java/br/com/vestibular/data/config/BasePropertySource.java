package br.com.vestibular.data.config;

import org.springframework.context.annotation.PropertySource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@PropertySource("classpath:application.properties")
@PropertySource("classpath:data.properties")
@PropertySource("classpath:data-${spring.profiles.active}.properties")
public @interface BasePropertySource {
}
