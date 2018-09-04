package com.how2java.springboot.mapper;
 
import java.util.List;
 
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
 
import com.how2java.springboot.pojo.User;
import com.how2java.springboot.pojo.UserInformation;
 
@Mapper
public interface UserMapper {
 
    @Select("select * from user ")
    List<User> findAll();
     
    @Insert(" insert into user ( name,password ) values (#{name},#{password}) ")
    public void save(User user); 

    @Delete(" delete from user where id= #{id} ")
    public void delete(int id);
         
    @Select("select * from user where name= #{name} ")
    public User getUserId(String name);
    
    @Select("select * from user where id= #{id} ")
    public User getUserName(int id);
       
    @Update("update user set name=#{name} where id=#{id} ")
    public int update(User user);
    
    @Update("update user set password=#{password} where id=#{id} ")
    public int setPassword(User user);
    
    @Select("select * from user  where name = #{name} and password = #{password}")
    public User verification(User user); //密码验证

    @Select("select * from user  where name = #{name}")
    public User checkName(User user); //重名验证

}