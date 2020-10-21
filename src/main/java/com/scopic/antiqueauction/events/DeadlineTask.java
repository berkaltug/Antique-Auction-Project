package com.scopic.antiqueauction.events;

public class DeadlineTask implements Runnable {
    @Override
    public void run() {
        System.out.println(" this deadline finished ");
    }
}
