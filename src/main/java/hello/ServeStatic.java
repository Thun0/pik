package hello;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//ConfigureToServeStatic
@Configuration
@EnableWebMvc
public class ServeStatic extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/app/**").addResourceLocations("/");  
    }
    
    @Override
    public void configureViewResolvers(final ViewResolverRegistry registry) {
        registry.jsp("/", ".html");
    } 
}