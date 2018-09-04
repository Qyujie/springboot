package com.how2java.springboot.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.how2java.springboot.pojo.UserInformation;

@Mapper
public interface UserInformationMapper {
   
    @Insert(" insert into userInformation (id) values (#{id})")
    public int saveUserInformation(UserInformation userInformation);//添加用户信息
    
    @Update("update userInformation set birthday=#{birthday},sex=#{sex},"
    		+ "phone=#{phone},prefecture=#{prefecture},introduce=#{introduce},"
    		+ "address=#{address},real_name=#{real_name},id_card=#{id_card} where id=#{id} ")
    public int updateUserInformation(UserInformation userInformation);
    
    @Select("select * from userinformation  where id = #{id}")
    public UserInformation getUserInformation(int id); //查询用户信息
    
    @Update("update userInformation set head_portrait=#{head_portrait} where id=#{id} ")
    public void updateHeadPortrait(UserInformation userInformation);
    
}
