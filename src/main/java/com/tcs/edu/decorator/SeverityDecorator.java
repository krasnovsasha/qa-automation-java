package com.tcs.edu.decorator;

import com.tcs.edu.enums.Severity;

/**
 * @author a.a.krasnov
 * <p>
 * The {@code SeverityDecorator} class contains method {@code decorateSeverityLevel} for change
 * base message output.
 */
public class SeverityDecorator {
    /**
     * @param level incoming severity
     * @return severityLevel
     * <p>
     * side-effect method changes console output cause it adds string severity designation
     */
    public static String decorateSeverityLevel(Severity level){
        String severityLevel = null;
        switch (level) {
            case MINOR:severityLevel = "()"; break;
            case REGULAR:severityLevel = "(!)"; break;
            case MAJOR:severityLevel = "(!!!)"; break;
        }
        return severityLevel;
    }
}