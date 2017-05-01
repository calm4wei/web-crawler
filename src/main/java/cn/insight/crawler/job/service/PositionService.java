package cn.insight.crawler.job.service;

import cn.insight.crawler.job.entity.Position;
import cn.insight.crawler.job.mapper.PositionMapper;
import cn.insight.crawler.util.DataSourceUtils;
import org.apache.ibatis.session.SqlSession;

/**
 * Created by fengwei on 17/5/1.
 */
public class PositionService {


    public void insertPosition(Position p) {
        SqlSession session = DataSourceUtils.getSession();
        PositionMapper mapper = session.getMapper(PositionMapper.class);
        mapper.insertPosition(p);
        session.close();
    }

}
