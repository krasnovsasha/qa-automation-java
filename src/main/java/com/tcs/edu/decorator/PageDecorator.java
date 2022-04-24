package com.tcs.edu.decorator;

/**
 * @author a.a.krasnov
 * <p>
 * The {@code PageDecorator} class contains method {@code separateByPage} for change
 * base message output.
 */
public class PageDecorator {
    private static int messageCount = 1;
    private static final int PAGE_SIZE = 2;
    /**
     * @return separator
     * <p>
     * side-effect method can change console output because it may add the line between rows
     */
    public static String separateByPage() {
        String separator = "";
        if (messageCount++ % PAGE_SIZE == 0) {
            separator = "\n---";
        }
        return separator;
    }
}