package com.my.blog;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 找不到界面的异常处理
 * @auther 周经明
 * @date 2020/3/16 10:28
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    public NotFoundException() {
    }

    public NotFoundException(String message) {

        super(message);
    }


    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}

