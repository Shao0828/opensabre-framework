package io.github.opensabre.boot.config;

import io.github.opensabre.boot.event.OpensabreSensitiveDesensitizerProcessor;
import io.github.opensabre.boot.sensitive.log.LogBackCoreConverter;
import io.github.opensabre.boot.sensitive.log.desensitizer.LogBackDesensitizer;
import io.github.opensabre.boot.sensitive.log.desensitizer.RegxLogBackDesensitizer;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 初使化脱敏配置
 */
@AutoConfiguration
public class OpensabreSensitiveConfig {

    @Bean
    public BeanFactoryPostProcessor beanFactoryPostProcessor() {
        return configurableListableBeanFactory -> {
            LogBackCoreConverter logBackCoreConverter = LogBackCoreConverter.getInstance();
            configurableListableBeanFactory.registerSingleton("logBackCoreConverter", logBackCoreConverter);
            configurableListableBeanFactory.registerSingleton("defaultDesensitizerStrategy", logBackCoreConverter.getDesensitizerStrategy());
        };
    }

    @Bean
    public LogBackDesensitizer regxLogBackDesensitizer() {
        return new RegxLogBackDesensitizer();
    }

    @Bean
    public BeanPostProcessor opensabreSensitiveDesensitizerProcessor() {
        return new OpensabreSensitiveDesensitizerProcessor();
    }
}