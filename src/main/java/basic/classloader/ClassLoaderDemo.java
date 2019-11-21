package basic.classloader;

/**
 * @author yangjie
 * @date 2019-11-21
 * @time 17:38
 */
public class ClassLoaderDemo {
    public static void main(String[] args) {
        // sun.misc.Launcher$AppClassLoader@2a139a55 ，应用程序类加载器
        System.out.println("ClassLoaderDemo---" + ClassLoaderDemo.class.getClassLoader());
        // sun.misc.Launcher$ExtClassLoader@7852e922， 扩展类加载器
        System.out.println("ClassLoaderDemo---parent:" + ClassLoaderDemo.class.getClassLoader().getParent());
        // null,实际是BootstrapClassLoader，启动类加载器
        System.out.println("ClassLoaderDemo---parent---parent:" + ClassLoaderDemo.class.getClassLoader().getParent().getParent());

    }
}
