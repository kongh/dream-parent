package com.coder.dream.base.validator;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by konghang on 15-6-14.
 */
public class ErrorMessageUtils {

    /**
     * 将验证错误消息转化成业务对象
     *
     * @param errors
     * @return
     */
    public static List<ErrorMessage> translateObjectErrors(List<ObjectError> errors){
        List<ErrorMessage> messages = new ArrayList<ErrorMessage>(errors.size());
        for (ObjectError error: errors){
            messages.add(translateObjectError(error));
        }
        return messages;
    }

    /**
     * 将验证错误消息转化成业务对象
     *
     * @param error
     * @return
     */
    public static ErrorMessage translateObjectError(ObjectError error){
        ErrorMessage message = new ErrorMessage();
        if(error instanceof FieldError){
            FieldError fieldError = (FieldError)error;
            message.setParam(fieldError.getField());
        }else{
            message.setParam(error.getObjectName());
        }
        message.setError(error.getDefaultMessage());
        return message;
    }
}
