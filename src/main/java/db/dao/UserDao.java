package db.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import vo.UserVo;

/**
 * Created by yangjie on 2017/6/2.
 */
public interface UserDao {

    public UserVo selectById(int id);

    @Insert(value="insert into user(name,age) values(#{name},#{age})")
    public void  insert(UserVo vo);

    @Update(value="update user set name=#{name},age=#{age} where id=#{id}")
    public void update(UserVo vo);

    @Select(value = "select * from user where name=#{name}")
    public UserVo selectByName(String name);

    @Delete(value="delete from user where id=#{id}")
    public void delete(UserVo vo);
}
