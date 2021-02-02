package com.ambevtech.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ambevtech.core"))
                .paths(regex("/.*"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {

        ApiInfo apiInfo = new ApiInfo(
                "Previsão do Tempo API REST - AmbevTech - Case",
                "API REST de previsão do tempo por cidades.",
                "1.0.0",
                "Terms of Service",
                new Contact("Thiago Belini", "",
                        "thibelini@gmail.com"), "","", new ArrayList<VendorExtension>()
        );

        return apiInfo;
    }

}

//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
//
//    @Bean
//    public Docket api() {
//        final List<ResponseMessage> globalResponses = Arrays.asList(
//                new ResponseMessageBuilder().code(200).message("OK").build(),
//                new ResponseMessageBuilder().code(201).message("Created").build(),
//                new ResponseMessageBuilder().code(202).message("Accepted").build(),
//                new ResponseMessageBuilder().code(204).message("No Content").build(),
//                new ResponseMessageBuilder().code(400).message("Bad Request").build(),
//                new ResponseMessageBuilder().code(401).message("Unauthorized").build(),
//                new ResponseMessageBuilder().code(403).message("Forbidden").build(),
//                new ResponseMessageBuilder().code(404).message("Not Found").build(),
//                new ResponseMessageBuilder().code(500).message("Internal Error").build());
//
//
//        ParameterBuilder aParameterBuilder = new ParameterBuilder();
//        aParameterBuilder.name("Content-Type")
//                .modelRef(new ModelRef("string"))
//                .parameterType("header")
//                .defaultValue("application/json")
//                .required(true)
//                .build();
//
//        List<Parameter> aParameters = new ArrayList<>();
//        aParameters.add(aParameterBuilder.build());
//
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//                .paths(PathSelectors.ant("/cidade/**")).build()
//                .globalOperationParameters(aParameters)
//                .useDefaultResponseMessages(false).globalResponseMessage(RequestMethod.GET, globalResponses)
//                .globalResponseMessage(RequestMethod.POST, globalResponses)
//                .globalResponseMessage(RequestMethod.GET, globalResponses).apiInfo(metaData());
//    }
//
//    private ApiInfo metaData() {
//        return new ApiInfoBuilder().title("Buscador de Tempo").description("Gerenciamento de cidades e seus respectivos tempos, para os próximos cinco dias").version("1.0.0")
//                .contact(new Contact("Thiago Belini", "https://thiagobelini.com.br/", "thibelini@gmail.com"))
//                .build();
//    }
//}