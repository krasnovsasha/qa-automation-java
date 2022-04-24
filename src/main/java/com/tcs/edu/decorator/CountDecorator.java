package com.tcs.edu.decorator;

/**
 * @author a.a.krasnov
 * <p>
 * The {@code CountDecorator} class contains method {@code decorateMessageCount} for change
 * base message output. This method add count for every message row
 */
public class CountDecorator {
    private static int counter = 1;
    /**
     * @return counter which was increased
     */
    public static int decorateCount(){
        return counter++;
    }
}