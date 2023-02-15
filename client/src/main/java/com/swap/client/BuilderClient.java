package com.swap.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import feign.*;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

public class BuilderClient {

    public static GitHubClient create(String token) {
        return Feign.builder()
                .logLevel(Logger.Level.HEADERS)
                .encoder(new JacksonEncoder(jacksonConfigMapper()))
                .decoder(new CustomDecoder(jacksonConfigMapper()))
                .logger(new Logger.ErrorLogger())
                .options(new Request.Options(10, TimeUnit.SECONDS, 60, TimeUnit.SECONDS, true))
                .retryer(new Retryer.Default(SECONDS.toMillis(10), SECONDS.toMillis(15), 10))
                .requestInterceptor(template -> template.header("Authorization", "Bearer "+token))
                .target(GitHubClient.class, "https://api.github.com");
    }

    private static ObjectMapper jacksonConfigMapper() {
        return new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .configure(SerializationFeature.INDENT_OUTPUT, true)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static class CustomDecoder implements Decoder {
        private ObjectMapper objectMapper;
        public CustomDecoder(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
        }
        public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
            if(response.status()==200 &&
                    response.request().url().endsWith("/stats/contributors")){
                throw new RetryableException(
                        response.status(),
                        "Waiting GitHub",
                        response.request().httpMethod(),
                        null,
                        response.request());
            }
            return new JacksonEncoder(objectMapper);
        }
    }
}
