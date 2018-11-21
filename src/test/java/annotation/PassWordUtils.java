package annotation;

import basic.annotation.UseCase;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author yangjie
 * @date 2018/11/21
 * @time 下午2:21
 */
public class PassWordUtils {

    @UseCase(id = 47, description = "password must contain as least a numeric")
    public boolean validPassword(String password) {

        return password.matches("\\w*\\d\\w*");
    }

    @UseCase(id = 48)
    public String encryptPassword(String password) {
        return new StringBuilder(password).reverse().toString();
    }

    public static void trackUseCase(List<Integer> useCases, Class<?> c1) {
        for (Method m : c1.getDeclaredMethods()) {
            UseCase uc = m.getAnnotation(UseCase.class);
            if (uc != null) {
                System.out.println("found UseCase:"+uc);
                useCases.remove(new Integer(uc.id()));
            }
        }

        for (int i : useCases) {
            System.out.println("Warning:Miss UseCase-" + i);
        }
    }

    public static void main(String[] args) {
        List<Integer> useCases = new ArrayList<>();
        Collections.addAll(useCases, 47, 48, 49, 50);
        trackUseCase(useCases, PassWordUtils.class);
    }
}
