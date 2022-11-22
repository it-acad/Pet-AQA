package ui;

import org.testcontainers.containers.BrowserWebDriverContainer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ContainerHolder {
    private static Map<String, BrowserWebDriverContainer> containers2 = new ConcurrentHashMap<>();

    public static BrowserWebDriverContainer getContainers(String testId) {
        return containers2.get(testId);
    }
}
