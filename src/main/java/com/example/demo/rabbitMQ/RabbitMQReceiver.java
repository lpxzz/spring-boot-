package com.example.demo.rabbitMQ;

import java.util.concurrent.CountDownLatch;

public class RabbitMQReceiver {
    //线程
    private CountDownLatch latch = new CountDownLatch(10);

    public void receiveMessage(String message) {
        System.out.println("收到消息 :" + message);
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
