package com.wowotoffer.shelf.common.core.entity;

import java.util.HashMap;

/**
 * @author of
 * @version 1.0
 * @date 2021/3/19 12:56
 */
public class ShelfResponse extends HashMap<String, Object> {
    private static final long serialVersionUID = -8713837118340960775L;

    public ShelfResponse message(String message) {
        this.put("message", message);
        return this;
    }

    public ShelfResponse data(Object data) {
        this.put("data", data);
        return this;
    }

    @Override
    public ShelfResponse put(String key, Object value) {
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
