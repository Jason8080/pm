package com.gm.pm.ex;

/**
 * @author Jason
 */
public class TokenException extends RuntimeException {
    public TokenException() {
        super("TOKEN: 无效或已超时!");
    }
}
