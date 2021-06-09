package org.roc.roc.common.entity;


import java.util.HashMap;

public class RocResponse extends HashMap<String, Object> {

    private static final long serialVersionUID = -8713837118340960775L;

    public RocResponse message(String message) {
        this.put("message", message);
        return this;
    }

    public RocResponse data(Object data) {
        this.put("data", data);
        return this;
    }

    @Override
    public RocResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public String getMessage() {
        return String.valueOf(get("message"));
    }

    public Object getData() {
        return get("data");
    }
}
