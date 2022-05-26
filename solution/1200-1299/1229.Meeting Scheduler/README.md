# [1229. 安排会议日程](https://leetcode.cn/problems/meeting-scheduler)

[English Version](/solution/1200-1299/1229.Meeting%20Scheduler/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个人的空闲时间表：<code>slots1</code> 和 <code>slots2</code>，以及会议的预计持续时间&nbsp;<code>duration</code>，请你为他们安排&nbsp;<strong>时间段最早&nbsp;且</strong>合适的会议时间。</p>

<p>如果没有满足要求的会议时间，就请返回一个 <strong>空数组</strong>。</p>

<p>「空闲时间」的格式是&nbsp;<code>[start, end]</code>，由开始时间&nbsp;<code>start</code>&nbsp;和结束时间&nbsp;<code>end</code>&nbsp;组成，表示从&nbsp;<code>start</code>&nbsp;开始，到 <code>end</code>&nbsp;结束。&nbsp;</p>

<p>题目保证数据有效：同一个人的空闲时间不会出现交叠的情况，也就是说，对于同一个人的两个空闲时间&nbsp;<code>[start1, end1]</code>&nbsp;和&nbsp;<code>[start2, end2]</code>，要么&nbsp;<code>start1 &gt; end2</code>，要么&nbsp;<code>start2 &gt; end1</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
<strong>输出：</strong>[60,68]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12
<strong>输出：</strong>[]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= slots1.length, slots2.length &lt;= 10<sup>4</sup></code></li>
	<li><code>slots1[i].length, slots2[i].length == 2</code></li>
	<li><code>slots1[i][0] &lt; slots1[i][1]</code></li>
	<li><code>slots2[i][0] &lt; slots2[i][1]</code></li>
	<li><code>0 &lt;= slots1[i][j], slots2[i][j] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= duration &lt;= 10<sup>6</sup></code></li>
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
