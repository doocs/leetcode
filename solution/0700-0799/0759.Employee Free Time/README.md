# [759. 员工空闲时间](https://leetcode.cn/problems/employee-free-time)

[English Version](/solution/0700-0799/0759.Employee%20Free%20Time/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定员工的 <code>schedule</code> 列表，表示每个员工的工作时间。</p>

<p>每个员工都有一个非重叠的时间段&nbsp; <code>Intervals</code> 列表，这些时间段已经排好序。</p>

<p>返回表示 <em>所有 </em>员工的 <strong>共同，正数长度的空闲时间 </strong>的有限时间段的列表，同样需要排好序。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
<strong>输出：</strong>[[3,4]]
<strong>解释：</strong>
共有 3 个员工，并且所有共同的
空间时间段是 [-inf, 1], [3, 4], [10, inf]。
我们去除所有包含 inf 的时间段，因为它们不是有限的时间段。
</pre>

<p>&nbsp;</p>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
<strong>输出：</strong>[[5,6],[7,9]]
</pre>

<p>&nbsp;</p>

<p>（尽管我们用 <code>[x, y]</code> 的形式表示 <code>Intervals</code> ，内部的对象是 <code>Intervals</code> 而不是列表或数组。例如，<code>schedule[0][0].start = 1, schedule[0][0].end = 2</code>，并且 <code>schedule[0][0][0]</code>&nbsp;是未定义的）</p>

<p>而且，答案中不包含 [5, 5] ，因为长度为 0。</p>

<p>&nbsp;</p>

<p><strong>注：</strong></p>

<ol>
	<li><code>schedule</code> 和&nbsp;<code>schedule[i]</code>&nbsp;为长度范围在&nbsp;<code>[1, 50]</code>的列表。</li>
	<li><code>0 &lt;= schedule[i].start &lt; schedule[i].end &lt;= 10^8</code>。</li>
</ol>

<p><strong>注：</strong>输入类型于&nbsp;2019 年&nbsp;4 月 15 日 改变。请重置为默认代码的定义以获取新方法。</p>

<p>&nbsp;</p>

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
