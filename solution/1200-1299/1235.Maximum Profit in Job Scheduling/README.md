# [1235. 规划兼职工作](https://leetcode.cn/problems/maximum-profit-in-job-scheduling)

[English Version](/solution/1200-1299/1235.Maximum%20Profit%20in%20Job%20Scheduling/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你打算利用空闲时间来做兼职工作赚些零花钱。</p>

<p>这里有&nbsp;<code>n</code>&nbsp;份兼职工作，每份工作预计从&nbsp;<code>startTime[i]</code>&nbsp;开始到&nbsp;<code>endTime[i]</code>&nbsp;结束，报酬为&nbsp;<code>profit[i]</code>。</p>

<p>给你一份兼职工作表，包含开始时间&nbsp;<code>startTime</code>，结束时间&nbsp;<code>endTime</code>&nbsp;和预计报酬&nbsp;<code>profit</code>&nbsp;三个数组，请你计算并返回可以获得的最大报酬。</p>

<p>注意，时间上出现重叠的 2 份工作不能同时进行。</p>

<p>如果你选择的工作在时间&nbsp;<code>X</code>&nbsp;结束，那么你可以立刻进行在时间&nbsp;<code>X</code>&nbsp;开始的下一份工作。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1235.Maximum%20Profit%20in%20Job%20Scheduling/images/sample1_1584.png" style="width: 300px;"></strong></p>

<pre><strong>输入：</strong>startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
<strong>输出：</strong>120
<strong>解释：
</strong>我们选出第 1 份和第 4 份工作， 
时间范围是 [1-3]+[3-6]，共获得报酬 120 = 50 + 70。
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1235.Maximum%20Profit%20in%20Job%20Scheduling/images/sample22_1584.png" style="height: 112px; width: 600px;"> </strong></p>

<pre><strong>输入：</strong>startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
<strong>输出：</strong>150
<strong>解释：
</strong>我们选择第 1，4，5 份工作。 
共获得报酬 150 = 20 + 70 + 60。
</pre>

<p><strong>示例 3：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1235.Maximum%20Profit%20in%20Job%20Scheduling/images/sample3_1584.png" style="height: 112px; width: 400px;"></strong></p>

<pre><strong>输入：</strong>startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
<strong>输出：</strong>6
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= startTime.length == endTime.length ==&nbsp;profit.length&nbsp;&lt;= 5 * 10^4</code></li>
	<li><code>1 &lt;=&nbsp;startTime[i] &lt;&nbsp;endTime[i] &lt;= 10^9</code></li>
	<li><code>1 &lt;=&nbsp;profit[i] &lt;= 10^4</code></li>
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
