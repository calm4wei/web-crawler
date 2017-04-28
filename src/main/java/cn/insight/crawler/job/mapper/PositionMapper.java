package cn.insight.crawler.job.mapper;

import cn.insight.crawler.job.entity.Position;
import org.apache.ibatis.annotations.Select;

/**
 * Created by fengwei on 17/4/27.
 */
public interface PositionMapper {


    @Select("select * from insight_position_result where id = #{id}")
    Position selectPositinById(int id);
}
