package br.com.bjbraz.poll;

import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableSwagger2
@PropertySources(value={
        @PropertySource(value = "classpath:swagger-config.properties", ignoreResourceNotFound = true)
})
public class SwaggerConfig {

    @Value("${swagger.info.title}")
    private String infoTitle;
    @Value("${swagger.info.description}")
    private String infoDescription;
    @Value("${swagger.info.version}")
    private String infoVersion;
    @Value("${swagger.info.terms-of-service-url}")
    private String infoTermsOfService;

    @Value("${swagger.info.contact.name}")
    private String infoContactName;
    @Value("${swagger.info.contact.url}")
    private String infoContactUrl;
    @Value("${swagger.info.contact.email}")
    private String infoContactEmail;

    @Value("${swagger.info.license}")
    private String infoLicense;
    @Value("${swagger.info.license-url}")
    private String infoLicenseUrl;
    @Value("${swagger.host}")
    private String host;
    @Value("${swagger.tags.example}")
    private String exampleTag;
    @Value("${swagger.tags.example.desc}")
    private String exampleTagDescription;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("(/actuator*|/autoconfig*|/env*|/heapdump*|/metrics*|/health*)")))
                .build()
                //.tags(new Tag(exampleTag, exampleTagDescription))
                .host(host)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(infoTitle, infoDescription, infoVersion, infoTermsOfService,
                new Contact(infoContactName, infoContactUrl, infoContactEmail), infoLicense, infoLicenseUrl,
                Collections.emptyList());
    }

}
