package Task;

import Task.Annatation.MyColumn;
import Task.Annatation.MyTable;

import java.lang.reflect.Field;
import java.util.Objects;

public class Reflection {

    public static String getTable(Object obj) {
        return obj.getClass().getAnnotation(MyTable.class).value();
    }

    public static <T> T getValue(String s, Object obj) throws IllegalAccessException {
        if (obj.getClass().isAnnotationPresent(MyTable.class)) {
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (Objects.equals(field.getAnnotation(MyColumn.class).value(), s)){
                    field.setAccessible(true);
                    return (T) field.get(obj);
                }
            }
        }
        return null ;}
}
