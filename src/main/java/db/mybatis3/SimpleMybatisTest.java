package db.mybatis3;

import db.dao.UserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import vo.UserVo;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by yangjie on 2017/6/1.
 */
public class SimpleMybatisTest {

    public static void main(String[] args){

    }


    @Test
    public void testXmlSelect() throws IOException {
        SqlSessionFactoryBuilder ssfb=new SqlSessionFactoryBuilder();
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory ssf=ssfb.build(reader);
        SqlSession session=ssf.openSession();

        try{
            UserDao userDao=session.getMapper(UserDao.class);
            UserVo user=userDao.selectById(1);
            System.out.println(user);

        }catch (Exception e){
            System.out.println(e);
        }finally {
            session.close();
        }
    }

    @Test
    public void testAnnotationInsert() throws IOException {
        SqlSessionFactoryBuilder ssfb=new SqlSessionFactoryBuilder();
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory ssf=ssfb.build(reader);
        SqlSession session=ssf.openSession();

        try{
            UserDao userDao=session.getMapper(UserDao.class);
            UserVo user=new UserVo();
            user.setAge(21);
            user.setName("harry");
            userDao.insert(user);
            session.commit();

            UserVo suser=userDao.selectByName("harry");
            System.out.println(suser);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }

    }

    @Test
    public void testAnnotationUpdate() throws IOException {
        SqlSessionFactoryBuilder ssfb=new SqlSessionFactoryBuilder();
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory ssf=ssfb.build(reader);
        SqlSession session=ssf.openSession();

        try{
            UserDao userDao=session.getMapper(UserDao.class);
            UserVo user=userDao.selectByName("harry");

            user.setAge(33);
            userDao.update(user);
            session.commit();

            UserVo result=userDao.selectByName("harry");

            System.out.println(result);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }

    }


    @Test
    public void testAnnotationSelect() throws IOException {
        SqlSessionFactoryBuilder ssfb=new SqlSessionFactoryBuilder();
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory ssf=ssfb.build(reader);
        SqlSession session=ssf.openSession();

        try{
            UserDao userDao=session.getMapper(UserDao.class);
            UserVo user=userDao.selectByName("test2");
            System.out.println(user);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }

    }


    @Test
    public void testAnnotationDelete() throws IOException {
        SqlSessionFactoryBuilder ssfb=new SqlSessionFactoryBuilder();
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory ssf=ssfb.build(reader);
        SqlSession session=ssf.openSession();

        try{
            UserDao userDao=session.getMapper(UserDao.class);
            UserVo user=userDao.selectByName("harry");
            userDao.delete(user);
            session.commit();

            UserVo result=userDao.selectByName("harry");
            System.out.println(result);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }

    }
}
