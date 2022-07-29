package org.example;

import lombok.SneakyThrows;
import org.junit.Test;

import java.io.FileWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Class<?> testClass = TakeResultsFromPagesTests.class;

        System.out.println(getMethodsAnnotatedWith(testClass, Test.class));

        writeArrayIntoFile(getMethodsAnnotatedWith(testClass, Test.class));
    }

    public static List<Method> getMethodsAnnotatedWith(final Class<?> type, final Class<? extends Annotation> annotation) {
        final List<Method> methods = new ArrayList<>();
        Class<?> klass = type;
        while (klass != Object.class) {
            for (final Method method : klass.getDeclaredMethods()) {
                if (method.isAnnotationPresent(annotation)) {
                    methods.add(method);
                }
            }
            klass = klass.getSuperclass();
        }
        return methods;
    }

    @SneakyThrows
    private static void writeArrayIntoFile(List<Method> list) {
        FileWriter writer = new FileWriter("output.txt");

        for (int i = 0; i < list.size(); i++) {
            writer.write(String.valueOf(list.get(i)));
            if ((list.get(i) != list.get(list.size() - 1))) {
                writer.write(", ");
            }
        }

        writer.close();
    }
}
