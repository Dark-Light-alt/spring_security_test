package com;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ApplicationStart {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationStart.class);
    }

    @Bean
    public WebMvcConfigurer crossConfig() {
        return new WebMvcConfigurer() {
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedHeaders("*")
                        .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTINOS")
                        .allowCredentials(true)
                        .maxAge(3600);
            }
        };
    }

    @Bean
    public HttpMessageConverters fastJsonConfig() {
        // 创建 converter 转换消息对象
        FastJsonHttpMessageConverter fastJsonConverter = new FastJsonHttpMessageConverter();

        // 创建 fastjson 配置对象
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteMapNullValue, SerializerFeature.QuoteFieldNames, SerializerFeature.WriteDateUseDateFormat);

        // 将配置信息放入 converter 对象
        fastJsonConverter.setFastJsonConfig(fastJsonConfig);

        HttpMessageConverter<?> httpMessageConverter = fastJsonConverter;

        return new HttpMessageConverters(httpMessageConverter);
    }
}
