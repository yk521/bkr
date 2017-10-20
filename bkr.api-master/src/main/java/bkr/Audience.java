package bkr;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix="audience")
@PropertySource("classpath:jwt.properties")
public class Audience {

	private String clientId;  
    private String base64Secret;  
    private String name;  
    private int expiresSecond;
}