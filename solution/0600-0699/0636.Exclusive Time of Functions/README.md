# [636. 函数的独占时间](https://leetcode-cn.com/problems/exclusive-time-of-functions)

[English Version](/solution/0600-0699/0636.Exclusive%20Time%20of%20Functions/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给出一个非抢占单线程CPU的 <strong>n </strong>个函数运行日志，找到函数的独占时间。</p>

<p>每个函数都有一个唯一的 Id，从 <strong>0</strong> 到<strong> n-1</strong>，函数可能会递归调用或者被其他函数调用。</p>

<p>日志是具有以下格式的字符串：<code>function_id：start_or_end：timestamp</code>。例如：<code>&quot;0:start:0&quot;</code>&nbsp;表示函数 0 从 0 时刻开始运行。<code>&quot;0:end:0&quot;</code>&nbsp;表示函数 0 在 0 时刻结束。</p>

<p>函数的独占时间定义是在该方法中花费的时间，调用其他函数花费的时间不算该函数的独占时间。你需要根据函数的 Id 有序地返回每个函数的独占时间。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong>
n = 2
logs = 
[&quot;0:start:0&quot;,
 &quot;1:start:2&quot;,
 &quot;1:end:5&quot;,
 &quot;0:end:6&quot;]
<strong>输出:</strong>[3, 4]
<strong>说明：</strong>
函数 0 在时刻 0 开始，在执行了  2个时间单位结束于时刻 1。
现在函数 0 调用函数 1，函数 1 在时刻 2 开始，执行 4 个时间单位后结束于时刻 5。
函数 0 再次在时刻 6 开始执行，并在时刻 6 结束运行，从而执行了 1 个时间单位。
所以函数 0 总共的执行了 2 +1 =3 个时间单位，函数 1 总共执行了 4 个时间单位。
</pre>

<p><strong>说明：</strong></p>

<ol>
	<li>输入的日志会根据时间戳排序，而不是根据日志Id排序。</li>
	<li>你的输出会根据函数Id排序，也就意味着你的输出数组中序号为 0 的元素相当于函数 0 的执行时间。</li>
	<li>两个函数不会在同时开始或结束。</li>
	<li>函数允许被递归调用，直到运行结束。</li>
	<li>1 &lt;= n &lt;= 100</li>
</ol>

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
