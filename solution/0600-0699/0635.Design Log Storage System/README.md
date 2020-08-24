# [635. 设计日志存储系统](https://leetcode-cn.com/problems/design-log-storage-system)

[English Version](/solution/0600-0699/0635.Design%20Log%20Storage%20System/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
<p>你将获得多条日志，每条日志都有唯一的 id 和 timestamp，timestamp 是形如 <code>Year:Month:Day:Hour:Minute:Second</code> 的字符串，例如 <code>2017:01:01:23:59:59</code>，所有值域都是零填充的十进制数。</p>

<p>设计一个日志存储系统实现如下功能：</p>

<p><code>void Put(int id, string timestamp)</code>：给定日志的 id 和 timestamp，将这个日志存入你的存储系统中。</p>

<p> </p>

<p><code>int[] Retrieve(String start, String end, String granularity)</code>：返回在给定时间区间内的所有日志的 id。start 、 end 和 timestamp 的格式相同，granularity 表示考虑的时间级。比如，start = "2017:01:01:23:59:59", end = "2017:01:02:23:59:59", granularity = "Day" 代表区间 2017 年 1 月 1 日到 2017 年 1 月 2 日。</p>

<p> </p>

<p> </p>

<p><strong>样例 1 ：</strong></p>

<pre>put(1, "2017:01:01:23:59:59");
put(2, "2017:01:01:22:59:59");
put(3, "2016:01:01:00:00:00");
retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year"); // 返回值 [1,2,3]，返回从 2016 年到 2017 年所有的日志。
retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Hour"); // 返回值 [1,2], 返回从 2016:01:01:01 到 2017:01:01:23 区间内的日志，日志 3 不在区间内。
</pre>

<p> </p>

<p><strong>注释 ：</strong></p>

<ol>
	<li>Put 和 Retrieve 的指令总数不超过 300。</li>
	<li>年份的区间是 [2000,2017]，小时的区间是 [00,23]。</li>
	<li>Retrieve 的输出顺序不作要求。</li>
</ol>

<p> </p>



## 解法
<!-- 这里可写通用的实现逻辑 -->


<!-- tabs:start -->

### **Python3**
<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**
<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **...**
```

```

<!-- tabs:end -->