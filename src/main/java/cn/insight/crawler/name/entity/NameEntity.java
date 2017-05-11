package cn.insight.crawler.name.entity;

/**
 * Created by zqykj on 2017/5/5.
 */
public class NameEntity {

    private String name;
    private String surname;
    private String sex;

    public NameEntity() {
    }

    public NameEntity(String name, String surname, String sex) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "NameEntity{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
