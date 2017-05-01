package cn.insight.crawler.mapper;

import cn.insight.crawler.job.entity.Position;
import cn.insight.crawler.job.mapper.PositionMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by fengwei on 17/4/27.
 */
public class MybatisTest {

    SqlSessionFactory sqlSessionFactory = null;

    @Before
    public void init() {
        String resource = "mybatis-config.xml";
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
            PositionMapper mapper = session.getMapper(PositionMapper.class);
            Position p = mapper.selectPositinById(1);
            System.out.println(p);
        } finally {
            session.close();
        }
    }

    @Test
    public void test_insert_position() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            PositionMapper mapper = session.getMapper(PositionMapper.class);
            Position p = new Position();
            p.setPosition("JavaEE4");
            p.setCity("上海");
            p.setCompany("上图");
            p.setPublish_date("2017-05-01");
            p.setSalary("10k-18k");
            p.setNature("");
            p.setDuty("开发");
            p.setIndustry("互联网");
            p.setSource_url("");
            p.setScale("50人");
            mapper.insertPosition(p);
//            mapper.insert(p);
            session.commit();
        } finally {
            session.close();
        }
    }
}
