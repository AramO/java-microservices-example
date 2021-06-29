package com.aram.http;

import com.aram.mapper.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The class to build response entity.
 */
public final class ResponseBuilder {

    private ResponseBuilder() {
        // Do nothing
    }

    /**
     * The method to build response entity.
     * @param status the status.
     * @param body the body.
     * @param mapper the com.aram.mapper.
     * @param <T> the body type.
     * @return Returns response entity.
     */
    public static <T, V> ResponseEntity<V> build(HttpStatus status, T body, Mapper<T, V> mapper) {
        return ResponseEntity.status(status).body(mapper.mapToDTO(body));
    }

    /**
     * The method to build response entity.
     * @param status the status.
     * @param page the page.
     * @param mapper the com.aram.mapper.
     * @param <T> the body type.
     * @return Returns response entity.
     */
    public static <T, V> ResponseEntity<Page<V>> build(HttpStatus status, Page<T> page, Mapper<T, V> mapper) {
        return ResponseEntity.status(status).body(page.map(body -> mapper.mapToDTO(body)));
    }

    /**
     * The method to build response entity.
     * @param status the status.
     * @param list the list.
     * @param mapper the com.aram.mapper.
     * @param <T> the body type.
     * @return Returns response entity.
     */
    public static <T, V> ResponseEntity<List<V>> build(HttpStatus status, List<T> list, Mapper<T, V> mapper) {
        return ResponseEntity.status(status).body(
                list.stream()
                        .map(body -> mapper.mapToDTO(body))
                        .collect(Collectors.toList())
        );
    }

}
