package cn.insight.crawler.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by fengwei on 17/5/1.
 */
public class DataSourceUtils {

    private static SqlSessionFactory sqlSessionFactory = null;
    private static final String resource = "zqy-mybatis-config.xml";

    private DataSourceUtils() {

    }

    static {
        if (sqlSessionFactory == null) {
            synchronized (DataSourceUtils.class) {
                if (sqlSessionFactory == null) {
                    InputStream inputStream = null;
                    try {
                        inputStream = Resources.getResourceAsStream(resource);
                        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static SqlSession getSession() {
        return sqlSessionFactory.openSession(true);
    }

    public static void close() {

    }
}
