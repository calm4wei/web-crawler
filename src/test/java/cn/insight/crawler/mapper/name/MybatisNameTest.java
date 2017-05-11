package cn.insight.crawler.mapper.name;

import cn.insight.crawler.job.entity.Position;
import cn.insight.crawler.job.mapper.PositionMapper;
import cn.insight.crawler.name.entity.NameEntity;
import cn.insight.crawler.name.mapper.NameMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengwei on 17/4/27.
 */
public class MybatisNameTest {

    SqlSessionFactory sqlSessionFactory = null;

    @Before
    public void init() {
        String resource = "zqy-mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test_select() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            NameMapper mapper = session.getMapper(NameMapper.class);
            NameEntity entity = mapper.selectOneByName("张李");
            System.out.println(entity);
        } finally {
            session.close();
        }
    }

    @Test
    public void test_insert_one() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            NameMapper mapper = session.getMapper(NameMapper.class);
            NameEntity nameEntity = new NameEntity("李四", "李", "男");
            mapper.insertOne(nameEntity);
            session.commit();
        } finally {
            session.close();
        }
    }

    @Test
    public void test_insert_list() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            List<NameEntity> list = new ArrayList<NameEntity>();
            NameMapper mapper = session.getMapper(NameMapper.class);
            NameEntity nameEntity = new NameEntity("李四", "李", "男");
            NameEntity namEntity1 = new NameEntity("东方不败", "东方", "女");
            list.add(nameEntity);
            list.add(namEntity1);
            mapper.insertList(list);
            session.commit();
        } finally {
            session.close();
        }
    }
}
