package ru.feytox.zoomify.list;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class OnlinePermissionList {
    private static final Set<String> VALID_NAMES = new HashSet<>();

    public static void loadValidator(String url) {
        try {
            VALID_NAMES.clear();
            HttpResponse<Stream<String>> response = HttpClient.newHttpClient().send(HttpRequest.newBuilder(new URI(url))
                    .timeout(Duration.ofSeconds(10L))
                    .build(), HttpResponse.BodyHandlers.ofLines());
            int code = response.statusCode();
            if (code < 200 || code > 299) return;
            VALID_NAMES.addAll(response.body()
                    .filter(Predicate.not(String::isBlank))
                    .map(String::strip)
                    .map(String::intern)
                    .toList());
        } catch (Throwable ignored) {
            VALID_NAMES.clear();
        }
    }

    public static boolean isValidName(String name) {
        return VALID_NAMES.contains(name);
    }
}
