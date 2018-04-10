package pers.httptest.core.threads;

import java.util.HashMap;
import java.util.Map;

public class Variables {
    private final Map<String, String> variables = new HashMap<>();

    public void put(String key, String value) {
        variables.put(key, value);
    }

    public Object remove(String key) {
        return variables.remove(key);
    }

    public void putAll(Map<String, String> vars) {
        variables.putAll(vars);
    }
}
