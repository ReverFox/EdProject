package ru.fokinde.mock;

import com.github.tomakehurst.wiremock.client.WireMock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.common.Metadata.metadata;

public class EducationApiMock {
    public static final String TAG = "test_mock";

    public static void initWireMock() {
        WireMock.configureFor("localhost", 8080);
    }

    public static void deleteMocksByTag(String tagName) {
        removeStubsByMetadata(matchingJsonPath("$.tag", equalTo(tagName)));
    }

    public static void deleteAllTestMocks() {
        deleteMocksByTag(TAG);
    }

    public static void buildUserAddAvailable() {
        stubFor(get(urlEqualTo("/user/add/available"))
                .withMetadata(metadata().attr("tag", TAG))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"isUserAddAvailable\": true}")
                        .withStatus(200)));

    }
}
