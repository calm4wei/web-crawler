package cn.insight.crawler.job.entity;

import java.io.Serializable;

/**
 * 职位实体类
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

    public Position() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getEdu() {
        return edu;
    }

    public void setEdu(String edu) {
        this.edu = edu;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getWelfare() {
        return welfare;
    }

    public void setWelfare(String welfare) {
        this.welfare = welfare;
    }

    public Integer getData_source() {
        return data_source;
    }

    public void setData_source(Integer data_source) {
        this.data_source = data_source;
    }

    public String getSource_url() {
        return source_url;
    }

    public void setSource_url(String source_url) {
        this.source_url = source_url;
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", position='" + position + '\'' +
                ", salary='" + salary + '\'' +
                ", city='" + city + '\'' +
                ", exp='" + exp + '\'' +
                ", edu='" + edu + '\'' +
                ", company='" + company + '\'' +
                ", scale='" + scale + '\'' +
                ", nature='" + nature + '\'' +
                ", industry='" + industry + '\'' +
                ", publish_date='" + publish_date + '\'' +
                ", duty='" + duty + '\'' +
                ", welfare='" + welfare + '\'' +
                ", data_source=" + data_source +
                ", source_url='" + source_url + '\'' +
                '}';
    }
}
