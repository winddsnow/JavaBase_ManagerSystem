//需要导包

import java.util.ArrayList;
import java.util.Scanner;


//入口类
public class MainApp {
    //存储学生的集合对象
    //之所以将数组集合定义在这里，是因为各个方法均需要使用这个集合
    //根据变量作用域等相关知识，确定将初始化集合放在main方法外mainapp类中
    //可查看deleteStudent()方法代码引用情景
    static ArrayList<Student> studentArray = new ArrayList<>();
    static ArrayList<Teacher> teachArray = new ArrayList<>();
    //在这里，可以利用static静态代码块原理，添加测试数据，测试查询等功能
    //格式: static {}//位置:  类中,方法外//执行:  随着类的加载而执行,并且只执行一次
    //使用场景: 加载驱动,或者放只需要执行一次的代码
    //static {
    //    //添加测试查询功能的数据
    //    Student s1 = new Student("01", "张三", "2000-12-12", 18, "男");
    //    Student s2 = new Student("02", "李四", "2000-12-12", 20, "女");
    //    stuArray.add(s1);
    //    stuArray.add(s2);
    //}
    //mian方法
    public static void main(String[] args) {
        //使用while死循环生成一级主页面，可重复展示主页面
        //在idea中，选中需要使用的代码块，可使用ctrl+alt+t快捷键 选择不同循环，快速构建
        while (true) {
            //操作提示，直接使用打印输出生成
            System.out.println("1.学生信息管理系统  2教师信息管理系统  3.查看所有学生与教师信息  4.退出");
            //输入选择
            //这里是新建一个Scanner对象，固定用法
            Scanner sc = new Scanner(System.in);
            //提示用户输入
            System.out.println("请输入您的选择...");
            //将键盘获取的数据赋予定义的int类型变量choose
            //不同的类型获取可查看Scanner类的用法
            int choose = sc.nextInt();
            //匹配选择
            //通过switch（）{case x: default}的选择语法来选择进入对应功能
            //知识点：switch用法
            switch (choose) {
                //匹配数字为1的用户选择
                case 1:
                    System.out.println("进入学生管理系统");
                    //将学习管理系统的操作抽取到一个方法中。
                    //在新建方法时，可直接书写方法名，idea将会报错，按alt+enter，可自动构建方法
                    //可方便在大括号之间自动构建方法
                    studentManager();
                    System.out.println("结束学生管理系统");
                    //break固定用法，匹配到运行完后跳出选择
                    break;
                case 2:
                    System.out.println("进入教师管理系统");
                    //自己写
                    teacherManager();
                    System.out.println("结束教师管理系统");
                    break;
                case 3:
                    findAllStudent();
                    findAllTeacher();
                    break;
                case 4:
                    System.out.println("感谢使用");
                    //System.exit(0);//不推荐是因为这个方法将结束整个java虚拟机
                    //如果虚拟机中运行有其他程序，将被一同结束掉
                    return;
                default:
                    System.out.println("您输入的选项有误!");
            }
        }
    }

    //教师管理系统界面
    public static void teacherManager() {
        while (true) {
            System.out.println("------------------------------------------------");
            System.out.println("1.添加教师  2.修改教师  3.删除教师  4.查询教师  5.返回");
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入功能序号...");
            int choose = sc.nextInt();
            switch (choose) {
                case 1:
                    System.out.println("进入添加教师功能");
                    addTeacher();
                    System.out.println("结束添加教师功能");
                    break;
                case 2:
                    System.out.println("进入修改教师功能");
                    updataTeacher();
                    System.out.println("结束修改教师功能");
                    break;
                case 3:
                    System.out.println("进入删除教师功能");
                    deleteTeacher();
                    System.out.println("结束删除教师功能");
                    break;
                case 4:
                    System.out.println("进入查询所有教师功能");
                    findAllTeacher();
                    System.out.println("结束查询所有教师功能");
                    break;
                case 5:
                    System.out.println("返回");
                    //由于当前的方法是在一级界面调用的，想要回到一级界面，只需要将当前方法结束掉。
                    //break;//只能结束当前的switch
                    return;
                default:
                    System.out.println("您输入的选项有误!");
            }
        }
    }

    public static void findAllTeacher() {
        //判断是否有数据(判断集合的长度),无数据，显示无数据,有数据，打印表头和具体数据。
        if (teachArray.size() == 0 || teachArray == null) {
            System.out.println("无数据");
        } else {
            System.out.println("【查询结果】");
            Utils.showAllTeacher(teachArray);
        }

    }

    public static void deleteTeacher() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要删除的教师ID");
        String tid = sc.nextLine();

        //找位置
        //查找指定学号对应的学员在集合中的位置，因为这个过程需要重复使用，在后面通过操作
        //抽取出getIndex（）方法，通过传入集合，以及一个int参数
        //即可判断此数是否存在于集合中，返回值为-1即不存在
        // 将sid作为索引传入数组进行判断
        int index = getTeaIndex(teachArray, tid);
        //判断位置是否存在
        if (index == -1) {
            //没找到
            System.out.println("【错误】教师ID:" + tid + "没找到");
        } else {
            //找到了
            System.out.println("【查询结果】要删除的教师信息");
            Teacher tea = teachArray.get(index);
            //形参多态，通过person类传入学生子类形参，得到子类的信息展示
            Utils.showPerson(tea);
            //死循环确认删除信息
            while (true) {
                System.out.println("【确认】您要删除这条信息么(y/n)?");
                String choose = sc.nextLine();
                //通过equals（）方法匹配用户的选择，判断是否删除
                if (choose.equals("y")) {
                    //删除
                    //通过集合中的remove（）方法，传入索引index，删除集合中的元素
                    teachArray.remove(index);
                    System.out.println("【成功】数据已被删除");
                    break;
                } else if (choose.equals("n")) {
                    //取消删除  啥也不做，打印输出取消即可
                    //什么也不做，就是不删除
                    System.out.println("【取消】操作被取消");
                    break;
                } else {
                    //这里的if else判断要说明
                    //需要完全匹配用户输入的y和n，输入其他字母要判断成错误输入
                    System.out.println("您输入的选项有误,请重新输入");
                }
            }
        }

    }

    public static void updataTeacher() {
        //输入要修改的教师的学号
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要修改的学员ID:");
        String sid = sc.nextLine();
        //判断学号是否存在
        //此处为抽取出的判断一个int参数是否存在于集合中的方法
        //通过传入集合，int参数（这里是用户输入sid）两个形参
        //返回的是索引值，-1表示没找到
        // (1.找学号对应的学生在集合中的位置(如果没找到返回-1，找到了返回是索引值)  2.判断找到的位置是否是-1)
        //Ctrl+Alt+M 抽取方法
        int index = getTeaIndex(teachArray, sid);   //使用抽取的getIndex（）方法

        //判断找到的位置是否是-1,如果是-1，表示没找到
        if (index == -1) {
            //没找到
            System.out.println("【错误】教师ID:" + sid + "没找到");
        } else {
            //找到了
            System.out.println("【查询结果】要修改的教师信息");
            //展示单个学员信息
            //获取学生对象
            Teacher tea = teachArray.get(index);
            //形参多态，不再解释
            Utils.showPerson(tea);

            //录入修改后的内容
            System.out.println("请输入新姓名");
            String newName = sc.nextLine();
            System.out.println("请输入新性别");
            String newSex = sc.nextLine();
            System.out.println("请输入新生日(格式:2000-12-12)");
            String newBirthday = sc.nextLine();

            //获取新年龄
            //传入新的生日日期，调用birthdayToAge（）方法，得到新年龄
            int newAge = Utils.birthdayToAge(newBirthday);
            //因为是修改操作，编号不需要改变，使用原来的编号即可。

            //创建新学生对象
            //通过形参赋值
            Teacher newTea = new Teacher(sid, newName, newBirthday, newAge, newSex);

            //用新的学生对象替换旧的学生对象(集合中的set(索引,修改后的对象))
            //这里使用的是集合的set（）方法
            teachArray.set(index, newTea);

            //提示成功
            System.out.println("【成功】教师信息修改成功");
        }

    }

    public static void addTeacher() {
        //录入学生信息
        System.out.println("请输入要添加的教师信息");
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入姓名...");
        String name = sc.nextLine();
        System.out.println("请输入性别...");
        String sex = sc.nextLine();
        System.out.println("请输入生日(格式:2020-12-12)...");
        String birthday = sc.nextLine();
        //获取学号
        //这里通过空字符串，就是双引号，通过+加号连接符号，将int型的sid转化成string类型
        String tid = Utils.tid + "";
        //获取年龄
        int age = Utils.birthdayToAge(birthday);

        //创建学生对象，封装学生的信息。
       Teacher tea = new Teacher(tid, name, birthday, age, sex);

        //添加学生
        //集合的add（）方法，传入参数是学生对象
        teachArray.add(tea);

        //提示成功
        System.out.println("【成功】教师信息添加成功");

        //学生编号自增
        //注意这个自增位置，需要在确认成功添加后自增
        //不能在程序执行中间，不确定的位置添加自增，有可能错误添加
        Utils.tid++;

    }


    //学生管理系统界面
    public static void studentManager() {

        //由于我们需求,需要用户在学生信息管理系统里，输入1-4的时候，结束对应操作的时候,需要重写回到学生信息管理的界面。
        //需要通过循环，让用户完成1-4的操作以后，能重新回到当前界面

        while (true) {
            System.out.println("------------------------------------------------");
            System.out.println("1.添加学生  2.修改学生  3.删除学生  4.查询学生  5.返回");
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入功能序号...");
            int choose = sc.nextInt();
            switch (choose) {
                case 1:
                    System.out.println("进入添加学生功能");
                    addStudent();
                    System.out.println("结束添加学生功能");
                    break;
                case 2:
                    System.out.println("进入修改学生功能");
                    updataStudent();
                    System.out.println("结束修改学生功能");
                    break;
                case 3:
                    System.out.println("进入删除学生功能");
                    deleteStudent();
                    System.out.println("结束删除学生功能");
                    break;
                case 4:
                    System.out.println("进入查询所有学生功能");
                    findAllStudent();
                    System.out.println("结束查询所有学生功能");
                    break;
                case 5:
                    System.out.println("返回");
                    //由于当前的方法是在一级界面调用的，想要回到一级界面，只需要将当前方法结束掉。
                    //break;//只能结束当前的switch
                    return;
                default:
                    System.out.println("您输入的选项有误!");
            }
        }
    }

    //删除学员功能
    public static void deleteStudent() {
        //录入要删除的学员的id
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要删除的学员ID");
        String sid = sc.nextLine();

        //找位置
        //查找指定学号对应的学员在集合中的位置，因为这个过程需要重复使用，在后面通过操作
        //抽取出getIndex（）方法，通过传入集合，以及一个int参数
        //即可判断此数是否存在于集合中，返回值为-1即不存在
        // 将sid作为索引传入数组进行判断
        int index = getIndex(studentArray, sid);
        //判断位置是否存在
        if (index == -1) {
            //没找到
            System.out.println("【错误】学员ID:" + sid + "没找到");
        } else {
            //找到了
            System.out.println("【查询结果】要删除的学员信息");
            //使用集合的get方法，传入索引，得到一个student对象
            Student stu = studentArray.get(index);
            //形参多态，通过person类传入学生子类形参，得到子类的信息展示
            Utils.showPerson(stu);
            //死循环确认删除信息
            while (true) {
                System.out.println("【确认】您要删除这条信息么(y/n)?");
                String choose = sc.nextLine();
                //通过equals（）方法匹配用户的选择，判断是否删除
                if (choose.equals("y")) {
                    //删除
                    //通过集合中的remove（）方法，传入索引index，删除集合中的元素
                    studentArray.remove(index);
                    System.out.println("【成功】数据已被删除");
                    break;
                } else if (choose.equals("n")) {
                    //取消删除  啥也不做，打印输出取消即可
                    //什么也不做，就是不删除
                    System.out.println("【取消】操作被取消");
                    break;
                } else {
                    //这里的if else判断要说明
                    //需要完全匹配用户输入的y和n，输入其他字母要判断成错误输入
                    System.out.println("您输入的选项有误,请重新输入");
                }
            }
        }
    }

    //修改学员功能
    public static void updataStudent() {
        //输入要修改的学员的学号
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要修改的学员ID:");
        String sid = sc.nextLine();
        //判断学号是否存在
        //此处为抽取出的判断一个int参数是否存在于集合中的方法
        //通过传入集合，int参数（这里是用户输入sid）两个形参
        //返回的是索引值，-1表示没找到
        // (1.找学号对应的学生在集合中的位置(如果没找到返回-1，找到了返回是索引值)  2.判断找到的位置是否是-1)
        //Ctrl+Alt+M 抽取方法
        int index = getIndex(studentArray, sid);   //使用抽取的getIndex（）方法

        //判断找到的位置是否是-1,如果是-1，表示没找到
        if (index == -1) {
            //没找到
            System.out.println("【错误】学员ID:" + sid + "没找到");
        } else {
            //找到了
            System.out.println("【查询结果】要修改的学员信息");
            //展示单个学员信息
            //获取学生对象
            Student stu = studentArray.get(index);
            //形参多态，不再解释
            Utils.showPerson(stu);

            //录入修改后的内容
            System.out.println("请输入新姓名");
            String newName = sc.nextLine();
            System.out.println("请输入新性别");
            String newSex = sc.nextLine();
            System.out.println("请输入新生日(格式:2000-12-12)");
            String newBirthday = sc.nextLine();

            //获取新年龄
            //传入新的生日日期，调用birthdayToAge（）方法，得到新年龄
            int newAge = Utils.birthdayToAge(newBirthday);
            //因为是修改操作，编号不需要改变，使用原来的编号即可。

            //创建新学生对象
            //通过形参赋值
            Student newStu = new Student(sid, newName, newBirthday, newAge, newSex);

            //用新的学生对象替换旧的学生对象(集合中的set(索引,修改后的对象))
            //这里使用的是集合的set（）方法
            studentArray.set(index, newStu);

            //提示成功
            System.out.println("【成功】学员信息修改成功");
        }
    }

    //查找指定学号对应的学员在集合中的位置
    //抽取的方法
    public static int getIndex(ArrayList<Student> studentArray, String sid) {
        //定义表示最终查找位置的变量
        //定义值为-1，最后判断是否依旧为-1就可以判断 index是否赋值成功
        //不能为0，因为0是合法值，0索引也是有值的
        int index = -1;
        //遍历集合，查找指定学号对应的学员在集合中的位置
        //这是使用集合的size方法来获取集合的长度
        for (int i = 0; i < studentArray.size(); i++) {
            //获取每个学生
            //通过集合的get（）方法，获取每一个学生对象
            Student s = studentArray.get(i);
            //判断学生的id与当前录入的id是否相同，如果相同，当前的索引就是我们要找的位置
            //通过student的get（）方法，获取每一个学生对象的sid，通过循环，一一比较
            if (s.getSid().equals(sid)) {
                //如果相等，就说明找到相同sid的学生对象，将i值赋予index
                index = i;
                //找到了，就不用再找了，立刻结束循环。
                break;
            }
        }
        //最后返回找到的集合元素的索引位置的值，如果没找到，返回的就是-1
        return index;
    }

    public static int getTeaIndex(ArrayList<Teacher> teachArray, String tid) {
        //定义表示最终查找位置的变量
        //定义值为-1，最后判断是否依旧为-1就可以判断 index是否赋值成功
        //不能为0，因为0是合法值，0索引也是有值的
        int index = -1;
        //遍历集合，查找指定学号对应的学员在集合中的位置
        //这是使用集合的size方法来获取集合的长度
        for (int i = 0; i < teachArray.size(); i++) {
            //获取每个学生
            //通过集合的get（）方法，获取每一个学生对象
            Teacher t = teachArray.get(i);
            //判断学生的id与当前录入的id是否相同，如果相同，当前的索引就是我们要找的位置
            //通过student的get（）方法，获取每一个学生对象的sid，通过循环，一一比较
            if (t.getSid().equals(tid)) {
                //如果相等，就说明找到相同sid的学生对象，将i值赋予index
                index = i;
                //找到了，就不用再找了，立刻结束循环。
                break;
            }
        }
        //最后返回找到的集合元素的索引位置的值，如果没找到，返回的就是-1
        return index;
    }

    //添加学员功能
    public static void addStudent() {
        //录入学生信息
        System.out.println("请输入要添加的学员信息");
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入姓名...");
        String name = sc.nextLine();
        System.out.println("请输入性别...");
        String sex = sc.nextLine();
        System.out.println("请输入生日(格式:2020-12-12)...");
        String birthday = sc.nextLine();
        //获取学号
        //这里通过空字符串，就是双引号，通过+加号连接符号，将int型的sid转化成string类型
        String sid = Utils.sid + "";
        //获取年龄
        int age = Utils.birthdayToAge(birthday);

        //创建学生对象，封装学生的信息。
        Student stu = new Student(sid, name, birthday, age, sex);

        //添加学生
        //集合的add（）方法，传入参数是学生对象
        studentArray.add(stu);

        //提示成功
        System.out.println("【成功】学生信息添加成功");

        //学生编号自增
        //注意这个自增位置，需要在确认成功添加后自增
        //不能在程序执行中间，不确定的位置添加自增，有可能错误添加
        Utils.sid++;
    }

    //查询所有学员功能
    public static void findAllStudent() {
        //判断是否有数据(判断集合的长度),无数据，显示无数据,有数据，打印表头和具体数据。
        if (studentArray.size() == 0 || studentArray == null) {
            System.out.println("无数据");
        } else {
            System.out.println("【查询结果】");
            Utils.showAllStudent(studentArray);
        }
    }

}
