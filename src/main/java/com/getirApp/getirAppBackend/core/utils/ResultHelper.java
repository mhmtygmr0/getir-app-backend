package com.getirApp.getirAppBackend.core.utils;

public class ResultHelper {

    public static <T> ResultData<T> created(T data) {
        return new ResultData<>(true, Msg.CREATED, "201", data);
    }

    public static <T> ResultData<T> validateError(T data) {
        return new ResultData<>(false, Msg.VALIDATE_ERROR, "400", data);
    }

    public static <T> ResultData<T> success(T data) {
        return new ResultData<>(true, Msg.SUCCESS, "200", data);
    }

    public static Result notFoundError() {
        return new Result(false, Msg.NOT_FOUND, "404");
    }
}
