package cn.insight.crawler.name.service;

import cn.insight.crawler.name.entity.NameEntity;
import cn.insight.crawler.name.mapper.NameMapper;
import cn.insight.crawler.util.DataSourceUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by zqykj on 2017/5/5.
 */
public class NameService {

    public NameEntity selectOneByName(String name) {
        NameEntity entity = null;
        SqlSession session = DataSourceUtils.getSession();
        try {
            NameMapper mapper = session.getMapper(NameMapper.class);
            entity = mapper.selectOneByName(name);
        } finally {
            session.close();
        }
        return entity;
    }

    public void insertOne(NameEntity nameEntity) {
        SqlSession session = DataSourceUtils.getSession();
        try {
            NameMapper mapper = session.getMapper(NameMapper.class);
            mapper.insertOne(nameEntity);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void insertList(List<NameEntity> list) {
        SqlSession session = DataSourceUtils.getSession();
        try {
            NameMapper mapper = session.getMapper(NameMapper.class);
            mapper.insertList(list);
        } finally {
            session.close();
        }
    }
}
