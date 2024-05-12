package se.nording.studentwebclient.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.function.Supplier;

public class WebClientHelper {

    private static final Logger logger = LoggerFactory.getLogger(WebClientHelper.class);

    public static <T> T handleRequest(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (WebClientResponseException e) {
            logger.error("HTTP Error: Status {}, Body {}", e.getStatusCode(), e.getResponseBodyAsString());
            throw new RuntimeException("HTTP Error occurred: " + e.getMessage(), e);
        } catch (Exception e) {
            logError(e);
            throw new RuntimeException("Error occurred while processing the request", e);
        }
    }

    private static void logError(Throwable error) {
        logger.error("Error: {}", error.getMessage(), error);
    }
}
