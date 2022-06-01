package com.github.yu.autoconfigure.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author yu
 * 2022/5/30
 */
public class FieldDiff {
    private static final Logger LOGGER = LoggerFactory.getLogger(FieldDiff.class);

    public static List<Diff> diffField(Object o1, Object o2) {
        Class<?> c1 = o1.getClass();
        Class<?> c2 = o2.getClass();

        if (!Objects.equals(c1, c2)) {
            throw new ClassDifferentException(c1.getName() + " don't equals " + c2.getName());
        }

        List<Diff> list = new ArrayList<>();
        try {
            findDiff(list, c1, c2, o1, o2);
        } catch (IllegalAccessException e) {
            LOGGER.warn("", e);
        }

        return list;
    }

    private static void findDiff(List<Diff> list, Class<?> c1, Class<?> c2, Object o1, Object o2) throws IllegalAccessException {
        Field[] fs1 = c1.getDeclaredFields();
        Field[] fs2 = c2.getDeclaredFields();

        for (int i = 0; i < fs1.length; i++) {
            Field f1 = fs1[i];
            Field f2 = fs2[i];

            f1.setAccessible(true);
            f2.setAccessible(true);

            Object v1 = f1.get(o1);
            Object v2 = f2.get(o2);

            if (!Objects.equals(v1, v2)) {
                Diff diff = new Diff(f1.getName(), v1, v2);
                list.add(diff);
            }
        }

        Class<?> cs1 = c1.getSuperclass();
        Class<?> cs2 = c2.getSuperclass();
        if (!Objects.equals(cs1, Object.class)) {
            findDiff(list, cs1, cs2, o1, o2);
        }
    }
}
