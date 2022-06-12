package com.itheima.utils;
//工具类，IDE自动导包

import com.itheima.bean.Person;
import com.itheima.bean.Student;
import com.itheima.bean.Teacher;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

//3.定义的工具类，工具类基本使用static修饰，方便其他类调用，将需要使用的工具方法封装在这个类中
//知识点：基本类型与引用类型  int与string的转换，基本类型默认值，静态变量
public class Utils {
    //学号
    //使用public和static是因为被static修饰的成员变量会被该类的所有对象共享，通过导包，其他类中，可以通过类名直接调用
    //使用int是因为后续要使用id自增，string无法自增，最后int通过添加"",可转换成字符串string
    //sid不赋值，默认值为0，赋值为0，可使程序更直观
    public static int sid = 0;
    public static int tid = 0;

    //展示单个学员或老师  形参多态
    //通过形参多态，传入父类的对象，可展示不同子类的信息
    /*知识点：形参多态---疑惑点，是如何使用的？查看下列使用场景
    //展示单个学员信息
    //tudent stu = stuArray.get(index);
    //Utils.showPerson(stu);
    通过查看使用场景如上，得知，调用这个showPerson方法时，传入的形参是子类的对象，返回的就是子类的信息，如此，实现了形参多态*/
    public static void showPerson(Person p) {
        System.out.println("************************************************");
        System.out.println("编号\t姓名\t\t性别\t生日\t\t\t年龄\t\t描述");
        System.out.println(p.getSid() + "\t" + p.getName() + "\t\t" + p.getSex() + "\t" + p.getBirthday() + "\t" + p.getAge() + "\t\t" + p.describe());
        System.out.println("************************************************");
    }

    //在这里，最开始展示通过子类来打印输出学生信息，后续发现如果需要展示所有父类成员的信息，可通过形参多态，来展示不同子类的信息
    /*
    public static void showStudent(Student s) {
        System.out.println("************************************************");
        System.out.println("编号\t姓名\t性别\t生日\t\t年龄\t\t描述");
        System.out.println(s.getSid()+"\t"+s.getName()+"\t"+s.getSex()+"\t"+s.getBirthday()+"\t"+s.getAge()+"\t"+s.describe());
        System.out.println("************************************************");
    }
     public static void showStudent(Teacher t) {
        System.out.println("************************************************");
        System.out.println("编号\t姓名\t性别\t生日\t\t年龄\t\t描述");
        System.out.println(t.getSid()+"\t"+t.getName()+"\t"+t.getSex()+"\t"+t.getBirthday()+"\t"+t.getAge()+"\t"+t.describe());
        System.out.println("************************************************");
    }
    */


    //这里是展示所有学员的方法，同样是静态方法
    //知识点：集合arraylist，for循环
    //学员存储:存储到ArrayList<Student>集合,要展示所有学员,需要获取到存储所有学员的集合。
    //泛型是不支持继承关系的
    //public static void showAllPerson(ArrayList<Person> array){    //这里设想如同单个展示那样，通过多态展示所有成员，发现泛型是不支持继承关系
    //    ArrayList<Person> arrayPerson= new ArrayList<>();   //新建父类集合
    //    ArrayList<Student> arrayStudent= new ArrayList<>();///新建子类集合
    //    arrayPerson= arrayStudent;//error   // 泛型不支持继承关系 现阶段知识无法多态
    //综上，展示所有成员只能单个写，写一个学生的，后续需要，再写教师的
    public static void showAllStudent(ArrayList<Student> array) {
        System.out.println("*************学生信息************************");
        System.out.println("编号\t姓名\t\t性别\t生日\t\t\t年龄\t\t描述");
        //展示每个学员的信息
        //1.遍历集合，获取每个学生  这里使用ide的快捷键是arry.fori   自动构建集合遍历的循环
        //知识点：这里的arry是传入的集合  size（）是获取集合长度的方法
        for (int i = 0; i < array.size(); i++) {
            //get（）方法是获取集合元素的方法，i是循环出来的数字，传入循环，即充当集合的索引
            //注意，获取出来的是对象，这里是对象s
            Student s = array.get(i);
            //2.打印每个学生的具体信息
            //这里通过对象调用getsid（）等方法，获取信息，再通过循环，遍历出所有学生信息
            System.out.println(s.getSid() + "\t" + s.getName() + "\t" + s.getSex() + "\t" + s.getBirthday() + "\t" + s.getAge() + "\t\t" + s.describe());

        }
        System.out.println("************************************************");
    }

    public static void showAllTeacher(ArrayList<Teacher> teachers) {
        System.out.println("***************教师信息***********************");
        System.out.println("编号\t姓名\t\t性别\t生日\t\t\t年龄\t\t描述");
        //展示每个学员的信息
        //1.遍历集合，获取每个学生  这里使用ide的快捷键是arry.fori   自动构建集合遍历的循环
        //知识点：这里的arry是传入的集合  size（）是获取集合长度的方法
        for (int i = 0; i < teachers.size(); i++) {
            //get（）方法是获取集合元素的方法，i是循环出来的数字，传入循环，即充当集合的索引
            //注意，获取出来的是对象，这里是对象s
            Teacher t = teachers.get(i);
            //2.打印每个学生的具体信息
            //这里通过对象调用getsid（）等方法，获取信息，再通过循环，遍历出所有学生信息
            System.out.println(t.getSid() + "\t" + t.getName() + "\t\t" + t.getSex() + "\t" + t.getBirthday() + "\t" + t.getAge() + "\t\t" + t.describe());

        }
        System.out.println("************************************************");
    }

    //根据生日(String)转换出一个年龄(int)
    //返回值是int，传入的形参是string
    public static int birthdayToAge(String birthday) {
        //定义年龄变量
        //赋值为-1的原因
        // 1.赋值为0有可能是合法数据，如同赋值数组索引不能为0，要是数组索引中存在的值
        // 2.-1即表示不存在，通过判断-1也可知道是否赋值成功
        int age = -1;

        //根据字符串内容获取生日所对应的日期对象
        //新建SimpleDateFormat对象，并规定格式为yyyy-MM-dd-
        //SimpleDateFormat是data时间日期相关的api
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //这里是初始化data类型的对象，取名birdata
        //放在这里的原因是下面判断异常时，会隔离birdata对象，需要提升birdata对象的作用域，因为后续也要使用
        Date birDate = null;
        try {
            //将用户输入的生日birthday
            // 通过impleDateFormat的parse方法
            // 格式化成data对象birDate
            //通过快捷键crtl+alt+t的try catch方法来处理异常
            birDate = sdf.parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //将日期对象转为日历对象
        //将得到的符合格式的日期对象birDate 转为Calendar对象
        //因为使用Calendar处理日期更加方便
        Calendar birCal = Calendar.getInstance();  //格式化得到Calendar对象birCal
        //这里做了什么事情？
        //将之前格式化得到的birdata对象，传入settime方法，之后得到birdata这个日期下的Calendar对象
        //如果不传参，Calendar对象就是现在当下这个时间，如下
        birCal.setTime(birDate);  //Calendar对象的settime方法，
        //获取当前的Calendar日历对象
        Calendar nowCal = Calendar.getInstance();

        //判断出生年龄是否合法(判断出生日期是否在当前日期的前面)
        //使用Calendar的 before方法，判断是否在当前日期之前，即判断birCal是不是未来日期，还没到的日期
        //返回时Boolean值，在当前日期之前即合法数据，否则就是不合法的数据
        if (birCal.before(nowCal)) {
            // 	合法
            //      准备年月日数据
            //这里使用Calendar的get方法，来得到现在以及输入生日年月日的int类型的数据
            int birYear = birCal.get(Calendar.YEAR);
            int nowYear = nowCal.get(Calendar.YEAR);
            int birMonth = birCal.get(Calendar.MONTH);
            int nowMonth = nowCal.get(Calendar.MONTH);
            int birDay = birCal.get(Calendar.DAY_OF_MONTH);
            int nowDay = nowCal.get(Calendar.DAY_OF_MONTH);
            // 		计算年龄差
            //正常年龄是现在年份减去生日年份，但不准确，先这样计算，后面的步骤，通过判断具体情况，来更改年龄
            age = nowYear - birYear;
            // 		判断，出生生日如果在当天之前，则年龄不变，生日在当天之后，则年龄-1
            //通过梳理思路，出生的月份，如果在当月之后，以及相同月份下，日期在当日之后，
            // 就表示生日已过，上述计算方式没有问题，仅需要判断剩下的情况
            //即出生的月份在当月之前，以及出生月份相同，出生日期在当日之前的情况
            //这两种情况，因为没过生日，上面计算出来的年龄需要减一
            if ((nowMonth < birMonth) || (nowMonth == birMonth && nowDay < birDay)) {
                age--;
            }
        } else {
            // 	不合法，提示输入有误
            System.out.println("您提供的出生日期，超过了当前日期，请重新输入!");
        }
        //返回年龄
        return age;
    }


}
