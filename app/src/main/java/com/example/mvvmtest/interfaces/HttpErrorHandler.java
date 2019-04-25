package com.example.mvvmtest.interfaces;

/**
 * Common http error handler
 *
 * @author gabriel.betancourt
 */

public interface HttpErrorHandler {
    void handleHttpError(int errorCode);
}
