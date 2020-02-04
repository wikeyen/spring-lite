package org.litespring.util;

import java.util.Objects;

public class ClassUtils {
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader classLoader = null;
        try {
            classLoader = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ex) {
        }

        if (Objects.isNull(classLoader)) {
            classLoader = ClassUtils.class.getClassLoader();
            if (Objects.isNull(classLoader)) {
                try {
                    classLoader = ClassLoader.getSystemClassLoader();
                } catch (Throwable ex) {
                }
            }
        }

        return classLoader;
    }
}
