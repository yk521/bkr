package bkr;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

import bkr.api.session.filter.JwtFilter;

/**
 * SpringBoot启动类
 * 
 * @author chengd
 */
@SpringBootApplication
public class BkrApplication extends WebMvcConfigurerAdapter {

    /**
     * 启动Springboot
     * 
     * @param args
     *            启动参数
     */
    public static void main(String[] args) {
        SpringApplication.run(BkrApplication.class, args);
    }

    /**
     * 配置fastjson
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);

        /**
         * 1.需要定义一个convert转换消息的对象 <br>
         * 2.创建配置信息，加入配置信息：比如是否需要格式化返回的json <br>
         * 3.converter中添加配置信息 <br>
         * 4.convert添加到converters当中 <br>
         */
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastJsonConfig
                .setSerializerFeatures(SerializerFeature.WriteMapNullValue);
        fastJsonConfig
                .setSerializerFeatures(SerializerFeature.WriteNullStringAsEmpty);
        fastJsonConfig
                .setSerializerFeatures(SerializerFeature.WriteNullListAsEmpty);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(fastJsonHttpMessageConverter);
    }
    
    @Bean  
    public FilterRegistrationBean  basicFilterRegistrationBean() {  
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();  
        JwtFilter jwtFilter = new JwtFilter();  
        registrationBean.setFilter(jwtFilter);  
        List<String> urlPatterns = new ArrayList<String>();  
        urlPatterns.add("/api/audience/login");  
        registrationBean.setUrlPatterns(urlPatterns);  
        return registrationBean;  
    }  
      
    @Bean  
    public FilterRegistrationBean jwtFilterRegistrationBean(){  
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();  
        JwtFilter jwtFilter = new JwtFilter();  
        registrationBean.setFilter(jwtFilter);  
        List<String> urlPatterns = new ArrayList<String>();  
        urlPatterns.add("/api/audience/login");  
        registrationBean.setUrlPatterns(urlPatterns);  
        return registrationBean;  
    }
}
