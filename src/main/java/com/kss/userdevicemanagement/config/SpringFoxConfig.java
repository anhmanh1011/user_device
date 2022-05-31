package com.kss.userdevicemanagement.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    @Bean
    public Docket api() {
//        List<Response> responseMessages = Collections.singletonList(new Response(String.valueOf(EnumCodeResponse.INVALID_PARAM.getCode()), EnumCodeResponse.INVALID_PARAM.getMessage(), true, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList()));

//        HttpAuthenticationScheme authenticationScheme = HttpAuthenticationScheme
//                .JWT_BEARER_BUILDER
//                .name("jwtTOKEN")
//                .build();

        Docket docket = new Docket(DocumentationType.OAS_30).forCodeGeneration(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kss.userdevicemanagement.controller"))
                .paths(PathSelectors.any())
                .build().
                securitySchemes(Collections.emptyList())
//                .securityContexts(Collections.singletonList(securityContext()))
                .apiInfo(apiInfo());
//        docket.globalResponses(HttpMethod.POST, responseMessages)
//                .globalResponses(HttpMethod.PUT, responseMessages)
//                .globalResponses(HttpMethod.GET, responseMessages)
//                .globalResponses(HttpMethod.DELETE, responseMessages);
        return docket;
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }

//    private ApiKey apiKey() {
//        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
//    }
//
//    private SecurityContext securityContext() {
//        return SecurityContext.builder()
//                .securityReferences(defaultAuth())
//                .build();
//    }

//    private SecurityContext securityContext() {
//        return SecurityContext.builder()
//                .securityReferences(defaultAuth())
//                .forPaths(PathSelectors.ant("/e/**"))
//                .build();
//    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return List.of(new SecurityReference("jwtTOKEN", authorizationScopes));
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "User device management",
                "provider any api for device",
                "1.0.0",
                "https://git.kss.com.vn/projects/ACC/repos/spring-boot-keycloak/browse",
                new Contact("DAO DUC MANH", "https://git.kss.com.vn/projects/ACC/repos/spring-boot-keycloak/browse", "manh.dd@kss.com.vn"),
                "License of API", "https://git.kss.com.vn/projects/ACC/repos/spring-boot-keycloak/browse", Collections.emptyList());
    }

    @Bean
    public static BeanPostProcessor springfoxHandlerProviderBeanPostProcessor() {
        return new BeanPostProcessor() {

            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (bean instanceof WebMvcRequestHandlerProvider) {
                    customizeSpringfoxHandlerMappings(getHandlerMappings(bean));
                }
                return bean;
            }

            private <T extends RequestMappingInfoHandlerMapping> void customizeSpringfoxHandlerMappings(List<T> mappings) {
                List<T> copy = mappings.stream()
                        .filter(mapping -> mapping.getPatternParser() == null)
                        .collect(Collectors.toList());
                mappings.clear();
                mappings.addAll(copy);
            }

            @SuppressWarnings("unchecked")
            private List<RequestMappingInfoHandlerMapping> getHandlerMappings(Object bean) {
                try {
                    Field field = ReflectionUtils.findField(bean.getClass(), "handlerMappings");
                    field.setAccessible(true);
                    return (List<RequestMappingInfoHandlerMapping>) field.get(bean);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    throw new IllegalStateException(e);
                }
            }
        };
    }

}
