package com.tcs.edu.service;

import com.tcs.edu.domain.Message;
import com.tcs.edu.enums.OutputOrder;

public interface MessageService {
    void log(OutputOrder order,Message message, Message... messages);
}