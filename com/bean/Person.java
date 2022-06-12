
//导包，IDE自动生成
//需要导包

//1.定义一个person的父类，里面有成员变量，构造方法，重写tostring（为了展示信息），get和set方法，
// 除了成员变量，上述其他均可按alt+insert自动构建
//知识点：面向对象，类，如何抽取父类，抽象类，私有成员变量，重写tostring原理。模板化开发-get和set方法
public abstract class Person {
    //2.定义成员变量
    private String sid;
    private String name;
    private String birthday;
    private int age;
    private String sex;
    //无参构造
    public Person() {
    }
    //满参构造，涉及this的含义与用法，关键-谁使用this，this指向的就是这个对象
    public Person(String sid, String name, String birthday, int age, String sex) {
        this.sid = sid;
        this.name = name;
        this.birthday = birthday;
        this.age = age;
        this.sex = sex;
    }
    //get和set方法
    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    //抽象方法-展示信息，为后续多态用法基础
    public abstract String describe();
    //重写tostring方法
    @Override
    public String toString() {
        return "Person{" +
                "sid='" + sid + '\'' +
                ", name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
