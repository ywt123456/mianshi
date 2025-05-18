# mianshi
mianshi
20250517学习记录:

mybatis   spring  多线程   ===了解   需要再看几遍

线程生命周期状态：


线程池生命周期状态：


线程池创建方式 和 一堆参数   ，  拒绝策略 ， 无界队列问题

ThreadLocal内存泄漏问题及解决方案

Java中如何检测死锁？如何预防和避免线程死锁?
https://www.nowcoder.com/exam/interview/88220569/test?paperId=61536919&order=0





ReentrantLock可以通过newCondition()方法创建多个Condition对象（条件变量）

ReentrantReadWriteLock的使用场景是？




---- 20250518   JVM面试题   慕课网G1垃圾回收器
jvm内存模型包括：
        类加载子系统、
        执行引擎、
        运行时数据区

运行时数据区：  堆 栈  本地方法栈  元空间（方法区）  程序计数器   


new : 
edon   标记清除 会存在内存不连续性   标记复制  （占内存但内存连续）  标记： 正向可达性分析   启动类，静态常量 开始找 能找到的类都是不回收的类  可能存在循环引用问题，如何解决循环引用呢？ 自动解决
so 
s1      
old   标记清除 （标记复制占内存）


三色标记法
垃圾回收器: 
分代回收:  eden  s0 s1  old
新生代:  serizal  partel serizal             serial
老年代: serizal old   partel serizal old 
新生代用复制算法  老年代用标记清除-移动

CMS回收器   标记（stw）   并行标记  重新标记（stw）  清除  只用于老年代  没有配合的新生代算 法  ， 开发出了  partNew 

G1 jdk9+默认垃圾收集器  分代信息有变更   将整个内存堆区域分为2048块region   每个region里再分新生代 老年代（每个region动态扮演eden s01 old） ，垃圾回收只回收部分region(垃圾最多的  garbage-first 原则)  效率高  可配置垃圾回收最大stw时间（--XX：最大停顿时间） ， 




打破双亲委派机制方式   获取线程上下文对应的类加载器加载   重写loadClass方法  自定义findClass

启动类加载器（bootstrap classloader）   java_home/lib目录下的包
扩展类加载器（ext classloader）      java_home/ext目录下包
应用类加载器（applicaiton classloader）   应用目录下的类
自定义类加载器（）       重写loadClass方法  实现具体类加载逻辑  比如类是网络的

full gc的场景： 内存新生代老年代比例设置不合理，   内存泄漏   ， 大对象过多 ， 方法区内存满了

常用gc参数:  --xms  --xmx     
内存设置：-Xms（初始堆）、-Xmx（最大堆）、-Xss（线程栈）
垃圾回收器：-XX:+UseG1GC（G1回收器）、-XX:+UseParallelGC（并行回收）
监控调试：-XX:+HeapDumpOnOutOfMemoryError（OOM生成堆转储）
性能优化：-XX:MaxMetaspaceSize（元空间上限）
日志参数：-Xloggc（GC日志路径）
MaxGCPauseMillis


-- java基础
介绍一下Java基本数据类型和引用类型。 
基本数据类型:   byte 1     short 2  int  4 long 8    最大范围 2的 (x*8 - 1 ) 次方 -1    double 32  float  64     char ASCII   boolean  true  false
引用类型:  类  接口 数组  枚举 


请你说一下抽象类和接口的区别
相同点: 不能实例化，需要继承或实现 ， 
不同点: 1.修饰符  abstract  interface 
         2. 默认方法  jdk8之前（不包含）接口不能有默认方法  ， 后边支持了 
         3. 抽象类可以写普通方法，接口不可以
         4. 抽象类可以多实现 且不用必须实现默认方法   接口必须实现方法
![PixPin_2025-05-18_18-06-09](https://github.com/user-attachments/assets/f940b8d0-0a20-4c07-b288-74dabd0d8d1a)

请你说一下final关键字。

final 修饰符
修饰类，方法，变量
类： 不能被继承 如String
方法： 不能被实现，防止重写
变量： 不能修改，如果是引用类型， 引用地址不能修改 ，引用对象里的属性可以修改

介绍下Java中的static关键字。静态方法能不能调用非静态成员?
静态   ， 可以修饰变量或者方法，变量或者方法属于类型的信息，
可以不初始化对象直接通过类型调用，
静态方法创建位于类加载时，
此时非静态成员对应的对象可能没有初始化，
所以不能在静态方法调用非静态成员。

String、StringBuffer、Stringbuilder有什么区别？
String  字符串 不可变 内部维护了final修饰的字符数组 jdk后期优化为char数组
stringBuilder 可变字符串 效率高 多线程访问环境下 线程不安全  无锁
stringBuffer 可变字符串  效率低 多线程访问环境下 线程安全 有锁

说一下Java中==与equals()的区别。

== 基础数据类型是比较的值  引用数据类型比较的是对象的引用是否相等
equals  继承自object，没有重写时比较对象地址值  和==等效  ， 重写时根据重写逻辑判断 
 
编程题目: 反转链表
