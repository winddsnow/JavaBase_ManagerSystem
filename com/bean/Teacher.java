//需要导包
//3.定义教师类，继承自person，一般继承的子类，同父类一样，alt+insert--提供无参以及满参构造方法
public class Teacher extends Person {

    //此类与学生类相同，可查看学生类理解
    public Teacher() {
    }

    public Teacher(String sid, String name, String birthday, int age, String sex) {
        super(sid, name, birthday, age, sex);
    }
    //重写了父类的describe方法，多态基础，相同父类方法，子类不同行为
    @Override
    public String describe() {
        return "我是一名老师,我的工作是:讲课";
    }
}
