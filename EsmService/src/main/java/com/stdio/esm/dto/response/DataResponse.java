package com.stdio.esm.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
/**
 * @author AnhKhoa
 * @since 19/05/2022 - 11:11
 */
@Getter
@Setter
@AllArgsConstructor
public class DataResponse<T> {

    private int code;

    private T data;

    public static <T> DataResponse<T> build(HttpStatus status, T data) {
        return new DataResponse<>(status.value(), data);
    }

    public static <T> DataResponse<T> success() {
        return build(HttpStatus.OK, null);
    }

    public static <T> DataResponse<T> success(T data) {
        return build(HttpStatus.OK, data);
    }

    public static <T> DataResponse<T> fail(T data) {
        return build(HttpStatus.BAD_REQUEST, data);
    }
}
