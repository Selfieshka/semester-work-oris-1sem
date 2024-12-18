package ru.kpfu.itis.kirillakhmetov.util.annotations;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class SingletonFactory {
    private static Map<Class<?>, Object> singletons = new HashMap<>();

    public static <T> T getInstance(Class<T> clazz) {
        if (singletons.containsKey(clazz)) {
            return (T) singletons.get(clazz);
        }

        if (clazz.isAnnotationPresent(Singleton.class)) {
            try {
                Constructor<T> constructor = clazz.getDeclaredConstructor();
                constructor.setAccessible(true);
                T instance = constructor.newInstance();
                singletons.put(clazz, instance);
                return instance;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        throw new IllegalArgumentException("Class is not a singleton: " + clazz);
    }
}