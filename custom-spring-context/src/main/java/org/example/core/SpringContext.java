package org.example.core;

import org.example.Bean;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SpringContext {
    private final static Map<Class<?>, Map<String, Class<?>>> BEAN_STORE = new HashMap<>();

    static {
        System.out.println("SpringContext.static initializer");
        //get all the types from class path with @Bean
        Set<Class<?>> beans = new Reflections("org.example")
                .getTypesAnnotatedWith(Bean.class);
        beans.forEach(aClass -> {
            //collecting beans
            String name = aClass.getAnnotation(Bean.class).name();
            Map<String, Class<?>> sub = new HashMap<>();
            sub.put(name,aClass);
            BEAN_STORE.put(aClass, sub);
        });
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<?> aClass, String name) {
        if (!BEAN_STORE.containsKey(aClass)) {
            throw new RuntimeException("Bean not found");
        }
        T t = null;
        Map<String, Class<?>> subStore = BEAN_STORE.get(aClass);
        if (!subStore.containsKey(name)) {
            throw new RuntimeException("Bean name not found");
        }

        Class<?> realBean = subStore.get(name);
        for (Constructor<?> constructor : realBean.getConstructors()) {
            try {
                t = (T) constructor.newInstance();
                //fill the instance variables
//                for (Field declaredField : realBean.getDeclaredFields()) {
//                    Class<?> type = declaredField.getType();
//                    if (!BEAN_STORE.containsKey(type)) {
//                        throw new RuntimeException("The been has not been annotated by @Bean");
//                    }
//                    if (declaredField.isAnnotationPresent(Autowired.class)) {
////                        Autowired annotation = declaredField.getAnnotation(Autowired.class);
//                        Class<?> instanceVar = BEAN_STORE.get(declaredField.getType());
//                        declaredField.setAccessible(true);
//                        declaredField.set(t,instanceVar.newInstance() );
//                        declaredField.setAccessible(false);
//                    }
//                }
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        return t;
    }
}
