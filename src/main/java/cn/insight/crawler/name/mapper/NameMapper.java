package cn.insight.crawler.name.mapper;

import cn.insight.crawler.name.entity.NameEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by zqykj on 2017/5/5.
 */
public interface NameMapper {

    @Select("select * from person_names where name = #{name}")
    NameEntity selectOneByName(String name);

    @Insert("INSERT INTO person_names(`name`, `surname`, `sex`) VALUES(#{name},#{surname},#{sex})")
    void insertOne(NameEntity entity);

    @Insert("INSERT INTO person_names(`name`, `surname`, `sex`) VALUES(#{name},#{surname},#{sex})")
    void insertList(List<NameEntity> nameMapperList);
}
