这个问题本身不难，但是被问到的频率很高，且大部分人的回答都不够全面，让人听了有种“恨铁不成钢”的感觉，所以今天咱们就来好好聊聊这个问题。# 1.典型回答对于 Object 来说，其 equals 方法底层实现就是“==”，如下 JDK 的 Object 源码如下：public boolean equals(Object obj) {
return (this == obj);
}
也就是说，对于 Object 对象来说，equals 和 == 都是一样的，都是比较对象的引用是否相同。但是，在 JDK 中的其他类中通常会重写 equals 以实现具体的值比较，例如 Integer 中的 equals 和 String 中的 equals 等，如下源码所示。 Integer 中的 equals 实现源码如下：public boolean equals(Object obj) {
if (obj instanceof Integer) {
return value == ((Integer)obj).intValue();
}
return false;
}
从上述源码可以看出，Integer 中会先将 Integer 对象转换成基础类型 int 值来进行比较，所以此时就不再是对比两个对象的引用了，而是对比两个对象的值是否相等。 String 中的 equals 实现源码如下：public boolean equals(Object anObject) {
if (this == anObject) { // 引用相同返回 true，引用相同，那么值肯定相同了
return true;
}
return (anObject instanceof String aString)
&& (!COMPACT_STRINGS || this.coder == aString.coder)
&& StringLatin1.equals(value, aString.value); // equals 为下面的 equals 方法
}
@IntrinsicCandidate
public static boolean equals(byte[] value, byte[] other) {
if (value.length == other.length) {
for (int i = 0; i < value.length; i++) { // 循环每个字符对比，本质是值比较
if (value[i] != other[i]) {
return false;
}
}
return true;
}
return false;
}
从 String 中的 equals 中可以看出，它和 Integer 一样，是将 Object 中的引用比较重写成了值比较了。# 2.考点分析所以，对于 Object 来说，== 和 equals 都是一样的，都是用来对比两个对象的引用是否相同的，而其他 Java 中的类中，如 String 或 Integer 等，通常都会重写 equals 让其变为比较具体的值是否相同，而非引用是否相同。 所以，我们通常会使用 == 来对比两个对象的引用是否相同，而使用 equals 对比两个值是否相同（前提条件是重写了 equals 方法）。# 3.知识扩展如果我们自定义一个类，并且想和 Integer 或 String 中的 equals 一样，用其对比值而非引用是否相同的实现代码如下：public class Person {
private String name;
private int age;
// 忽略构造方法和 Getter、Setter 方法......
@Override
public boolean equals(Object obj) {
if (this == obj) {
return true;
}
if (obj == null || getClass() != obj.getClass()) {
return false;
}
Person other = (Person) obj;
return this.age == other.age && this.name.equals(other.name);
}
}
# 小结对于 Object 来说，equals 是用 == 实现的，所以二者是相同的，都是用来比较两个对象的引用是否相同的，但 Java 中的其他类，都会重写 equals 让其变为值比较，而非引用比较，如 Integer 和 String 都是这样。# 特殊说明以上内容来自我的《Java 面试突击训练营》，这门课程是有着十几年工作经验（前 360 开发工程师），10 年面试官经验的我，花费 4 年时间打磨完成的一门视频面试课。学完训练营的课程之后，基本可以应对目前市面上绝大部分公司的面试了，并且课程配备了 9 大就业服务，帮助上千人找到 Java 工作，其中上百人拿到大厂 Offer，学员最高薪资 70W 年薪，面试课目录和 9 大服务如下：加我微信咨询：vipStone【备注：训练营】
------
著作权归 www.javacn.site 所有
原文链接：https://javacn.site/interview/basic/equals.html#%E7%89%B9%E6%AE%8A%E8%AF%B4%E6%98%8E