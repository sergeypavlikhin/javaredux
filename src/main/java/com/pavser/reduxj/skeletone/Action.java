package com.pavser.reduxj.skeletone;

import java.io.Serializable;
import java.util.Map;

/**
 * {
 *     type: ...
 *     payload: {
 *         ... : ...,
 *         ... : ...
 *     }
 * }
 * @param <T>
 */
public interface Action<T extends Serializable> extends Serializable {

    /**
     * Согласно redux, у каждого action есть тайп,
     * который однозначно идентифирует действие для редьюсера
     * @return
     */
    T getType();

    /**
     * Полезная нагрузка. По сути, инфа для редьюсеров
     * @return
     */
    Map<String, String> getPayload();
}
