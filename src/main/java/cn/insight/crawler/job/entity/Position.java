package cn.insight.crawler.job.entity;

import java.io.Serializable;

/**
 * Created by fengwei on 17/4/25.
 */
public class Position implements Serializable {
    private Integer id;
    private String position;
    private String salary;
    private String city;
    private String exp;
    private String edu;
    private String company;
    private String scale;
    /**
     * 企业性质
     */
    private String nature;
    /**
     * 所属行业
     */
    private String industry;
    /**
     * 发布日期
     */
    private String publish_date;
    /**
     * 岗位职责
     */
    private String duty;
    /**
     *
     */
    private String welfare;
    /**
     * 来源网站
     */
    private Integer data_source;
    /**
     * 职位链接
     */
    private String source_url;

}
