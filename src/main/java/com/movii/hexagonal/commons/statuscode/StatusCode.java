package com.movii.hexagonal.commons.statuscode;


import com.movii.hexagonal.exceptions.customExceptions.ServiceException;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Constructor;

@Data
@AllArgsConstructor
public class StatusCode {

    private Level level;
    private String code;
    private String message;
    private String extCode;

    public <T extends ServiceException> StatusCode onErrorThrow(Class<T> serviceException) throws T {
        if (Level.FAIL.equals(this.level) || Level.WARN.equals(this.level)) {
            try {
                Constructor<T> constructor = serviceException.getDeclaredConstructor(StatusCode.class);
                throw constructor.newInstance(this);
            } catch (Exception ignore) {
            }
        }
        return this;
    }

    public enum Level {
        SUCCESS("00"),
        WARN("98"),
        FAIL("99");

        private String codeDefault;

        Level(String code) {
            this.codeDefault = code;
        }

        public String value() {
            return codeDefault;
        }

    }

}
