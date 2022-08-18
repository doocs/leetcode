# [635. 设计日志存储系统](https://leetcode.cn/problems/design-log-storage-system)

[English Version](/solution/0600-0699/0635.Design%20Log%20Storage%20System/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你将获得多条日志，每条日志都有唯一的 <code>id</code> 和 <code>timestamp</code> ，<code>timestamp</code> 是形如 <code>Year:Month:Day:Hour:Minute:Second</code> 的字符串，<code>2017:01:01:23:59:59</code> ，所有值域都是零填充的十进制数。</p>

<p>实现 <code>LogSystem</code> 类：</p>

<ul>
	<li><code>LogSystem()</code> 初始化 <code>LogSystem</code><b> </b>对象</li>
	<li><code>void put(int id, string timestamp)</code> 给定日志的 <code>id</code> 和 <code>timestamp</code> ，将这个日志存入你的存储系统中。</li>
	<li><code>int[] retrieve(string start, string end, string granularity)</code> 返回在给定时间区间 <code>[start, end]</code> （包含两端）内的所有日志的 <code>id</code> 。<code>start</code> 、<code>end</code> 和 <code>timestamp</code> 的格式相同，<code>granularity</code> 表示考虑的时间粒度（例如，精确到 <code>Day</code>、<code>Minute</code> 等）。例如 <code>start = "2017:01:01:23:59:59"</code>、<code>end = "2017:01:02:23:59:59"</code> 且 <code>granularity = "Day"</code> 意味着需要查找从 <strong>Jan. 1st 2017</strong> 到 <strong>Jan. 2nd 2017 </strong>范围内的日志，可以忽略日志的 <code>Hour</code>、<code>Minute</code> 和 <code>Second</code> 。</li>
</ul>
 

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>
["LogSystem", "put", "put", "put", "retrieve", "retrieve"]
[[], [1, "2017:01:01:23:59:59"], [2, "2017:01:01:22:59:59"], [3, "2016:01:01:00:00:00"], ["2016:01:01:01:01:01", "2017:01:01:23:00:00", "Year"], ["2016:01:01:01:01:01", "2017:01:01:23:00:00", "Hour"]]
<strong>输出：</strong>
[null, null, null, null, [3, 2, 1], [2, 1]]

<strong>解释：</strong>
LogSystem logSystem = new LogSystem();
logSystem.put(1, "2017:01:01:23:59:59");
logSystem.put(2, "2017:01:01:22:59:59");
logSystem.put(3, "2016:01:01:00:00:00");

// 返回 [3,2,1]，返回从 2016 年到 2017 年所有的日志。
logSystem.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Year");

// 返回 [2,1]，返回从 Jan. 1, 2016 01:XX:XX 到 Jan. 1, 2017 23:XX:XX 之间的所有日志
// 不返回日志 3 因为记录时间 Jan. 1, 2016 00:00:00 超过范围的起始时间
logSystem.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Hour");
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= id <= 500</code></li>
	<li><code>2000 <= Year <= 2017</code></li>
	<li><code>1 <= Month <= 12</code></li>
	<li><code>1 <= Day <= 31</code></li>
	<li><code>0 <= Hour <= 23</code></li>
	<li><code>0 <= Minute, Second <= 59</code></li>
	<li><code>granularity</code> 是这些值 <code>["Year", "Month", "Day", "Hour", "Minute", "Second"]</code> 之一</li>
	<li>最多调用 <code>500</code> 次 <code>put</code> 和 <code>retrieve</code></li>
</ul>

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
