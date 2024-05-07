package se.nording.studentwebclient.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.ClientResponse;

import java.util.function.Supplier;

public class WebClientHelper {

    private static final Logger logger = LoggerFactory.getLogger(WebClientHelper.class);

    public static <T> T handleRequest(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (Exception e) {
            logError(e);
            throw new RuntimeException("Error occurred while processing the request", e);
        }
    }

    private static void logError(Throwable error) {
        logger.error("Error: {}", error.getMessage(), error);
    }

    // HTTP Error Handler
    public static Throwable handleHttpError(ClientResponse response) {
        String body = response.bodyToMono(String.class).block();
        if (response.statusCode().is4xxClientError()) {
            return new RuntimeException("Client error: " + body);
        } else if (response.statusCode().is5xxServerError()) {
            return new RuntimeException("Server error: " + body);
        } else {
            return new RuntimeException("Unexpected error: " + body);
        }
    }



}
