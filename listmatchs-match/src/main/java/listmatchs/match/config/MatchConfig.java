package listmatchs.match.config;

import listmatchs.security.config.SecurityConfig;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration
@Import({ SecurityConfig.class })
public class MatchConfig {
	  
}
