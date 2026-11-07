package oop;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ProductValidator {

    static List<String> validate(Object obj) {
        List<String> errors = new ArrayList<>();
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
//        Validation
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object value = field.get(obj);
                if (field.isAnnotationPresent(NotBlank.class)) {
                    if (!(value instanceof String str) || str.isBlank()) {
                        errors.add(field.getName() + "must not be blank");
                    }
                }

                if (field.isAnnotationPresent(Positive.class)) {
                    if (!(value instanceof Number number) || number.doubleValue() <= 0) {
                        errors.add(field.getName() + "must be positive");
                    }
                }

                if (field.isAnnotationPresent(MaxLength.class)) {
                    MaxLength ann = field.getAnnotation(MaxLength.class);
                    if (!(value instanceof String str) || str.length() > ann.value()) {
                        errors.add(field.getName() + "maximum length exceeded");

                    }
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Cannot access field: " + field.getName(), e);

            }
        }

        return errors;
    }

}
