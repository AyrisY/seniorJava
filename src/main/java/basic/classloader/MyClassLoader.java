package basic.classloader;

/**
 * @author yangjie
 * @date 2019-11-22
 * @time 13:44
 */
public class MyClassLoader extends ClassLoader{

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
}
