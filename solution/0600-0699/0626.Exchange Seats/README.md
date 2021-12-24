# [626. 换座位](https://leetcode-cn.com/problems/exchange-seats)

[English Version](/solution/0600-0699/0626.Exchange%20Seats/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>小美是一所中学的信息科技老师，她有一张 <code>seat</code>&nbsp;座位表，平时用来储存学生名字和与他们相对应的座位 id。</p>

<p>其中纵列的&nbsp;<strong>id&nbsp;</strong>是连续递增的</p>

<p>小美想改变相邻俩学生的座位。</p>

<p>你能不能帮她写一个 SQL query&nbsp;来输出小美想要的结果呢？</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
+---------+---------+
|    id   | student |
+---------+---------+
|    1    | Abbot   |
|    2    | Doris   |
|    3    | Emerson |
|    4    | Green   |
|    5    | Jeames  |
+---------+---------+
</pre>

<p>假如数据输入的是上表，则输出结果如下：</p>

<pre>
+---------+---------+
|    id   | student |
+---------+---------+
|    1    | Doris   |
|    2    | Abbot   |
|    3    | Green   |
|    4    | Emerson |
|    5    | Jeames  |
+---------+---------+</pre>

<p><strong>注意：</strong></p>

<p>如果学生人数是奇数，则不需要改变最后一个同学的座位。</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **SQL**

```
SELECT
    s1.id, COALESCE(s2.student, s1.student) AS student
FROM
    seat s1
        LEFT JOIN
    seat s2 ON (s1.id+1)^1-1 = s2.id
ORDER BY s1.id;
```

<!-- tabs:end -->
