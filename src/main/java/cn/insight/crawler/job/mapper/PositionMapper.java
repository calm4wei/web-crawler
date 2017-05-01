package cn.insight.crawler.job.mapper;

import cn.insight.crawler.job.entity.Position;
import javafx.geometry.Pos;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * Created by fengwei on 17/4/27.
 */
public interface PositionMapper {


    @Select("select * from insight_position_result where id = #{id}")
    Position selectPositinById(int id);

    @Insert("INSERT INTO insight_position_result(position, city, salary,company,nature,scale,industry,publish_date,duty,source_url,data_source)" +
            " VALUES(#{position},#{city},#{salary},#{company},#{nature},#{scale},#{industry},#{publish_date},#{duty},#{source_url},#{data_source})")
    void insertPosition(Position p);

    @Insert("INSERT INTO insight_position_result(position, city, salary) VALUES(#{position},#{city},#{salary})")
    void insert(Position p);
}
