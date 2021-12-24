# [596. 超过 5 名学生的课](https://leetcode-cn.com/problems/classes-more-than-5-students)

[English Version](/solution/0500-0599/0596.Classes%20More%20Than%205%20Students/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有一个<code>courses</code> 表 ，有: <strong>student&nbsp;(学生) </strong>和 <strong>class (课程)</strong>。</p>

<p>请列出所有超过或等于5名学生的课。</p>

<p>例如，表：</p>

<pre>+---------+------------+
| student | class      |
+---------+------------+
| A       | Math       |
| B       | English    |
| C       | Math       |
| D       | Biology    |
| E       | Math       |
| F       | Computer   |
| G       | Math       |
| H       | Math       |
| I       | Math       |
+---------+------------+
</pre>

<p>应该输出:</p>

<pre>+---------+
| class   |
+---------+
| Math    |
+---------+
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>学生在每个课中不应被重复计算。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```
select
    c.class
from
    (select distinct courses.student, courses.class from courses) c
group by
    c.class
having
    count(c.class)>=5
```

<!-- tabs:end -->
