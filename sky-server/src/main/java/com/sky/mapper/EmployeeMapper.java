package com.sky.mapper;

import com.sky.annotation.AopAnnotation;
import com.sky.dto.EmployeeDTO;
import com.sky.entity.Employee;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    //新增员工
    @AopAnnotation(OperationType.INSERT)
    @Insert("insert into employee(name, username, password, phone, sex, id_number, create_time, update_time, create_user, update_user)values (#{name},#{username},#{password},#{phone}" +
            ",#{sex},#{idNumber},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    public void insert(Employee employee);

    public List<Employee> selectPage(String name);

    @AopAnnotation(OperationType.UPDATE)
    @Update("update employee set status =#{status} where id=#{id}")
    public void updateEmployee(Integer status,Integer id);

    //显示回显
    @Select("select * from employee where id=#{id}")
    public Employee selectUser(Integer id);

    //修改员工
    @AopAnnotation(OperationType.UPDATE)
    public void update(Employee employee);
}
