package com.tcs.edu.decorator;

import com.tcs.edu.domain.Message;

/**
 * @author a.a.krasnov
 */
public interface Decorator {
    String decorate(Message message);
}