# [1986. 完成任务的最少工作时间段](https://leetcode.cn/problems/minimum-number-of-work-sessions-to-finish-the-tasks)

[English Version](/solution/1900-1999/1986.Minimum%20Number%20of%20Work%20Sessions%20to%20Finish%20the%20Tasks/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你被安排了 <code>n</code>&nbsp;个任务。任务需要花费的时间用长度为 <code>n</code>&nbsp;的整数数组&nbsp;<code>tasks</code>&nbsp;表示，第 <code>i</code>&nbsp;个任务需要花费&nbsp;<code>tasks[i]</code>&nbsp;小时完成。一个 <strong>工作时间段</strong>&nbsp;中，你可以 <strong>至多</strong>&nbsp;连续工作&nbsp;<code>sessionTime</code>&nbsp;个小时，然后休息一会儿。</p>

<p>你需要按照如下条件完成给定任务：</p>

<ul>
	<li>如果你在某一个时间段开始一个任务，你需要在 <strong>同一个</strong>&nbsp;时间段完成它。</li>
	<li>完成一个任务后，你可以 <strong>立马</strong>&nbsp;开始一个新的任务。</li>
	<li>你可以按 <strong>任意顺序</strong>&nbsp;完成任务。</li>
</ul>

<p>给你&nbsp;<code>tasks</code> 和&nbsp;<code>sessionTime</code>&nbsp;，请你按照上述要求，返回完成所有任务所需要的&nbsp;<strong>最少</strong>&nbsp;数目的&nbsp;<strong>工作时间段</strong>&nbsp;。</p>

<p>测试数据保证&nbsp;<code>sessionTime</code> <strong>大于等于</strong>&nbsp;<code>tasks[i]</code>&nbsp;中的&nbsp;<strong>最大值</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>tasks = [1,2,3], sessionTime = 3
<b>输出：</b>2
<b>解释：</b>你可以在两个工作时间段内完成所有任务。
- 第一个工作时间段：完成第一和第二个任务，花费 1 + 2 = 3 小时。
- 第二个工作时间段：完成第三个任务，花费 3 小时。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>tasks = [3,1,3,1,1], sessionTime = 8
<b>输出：</b>2
<b>解释：</b>你可以在两个工作时间段内完成所有任务。
- 第一个工作时间段：完成除了最后一个任务以外的所有任务，花费 3 + 1 + 3 + 1 = 8 小时。
- 第二个工作时间段，完成最后一个任务，花费 1 小时。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>tasks = [1,2,3,4,5], sessionTime = 15
<b>输出：</b>1
<b>解释：</b>你可以在一个工作时间段以内完成所有任务。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == tasks.length</code></li>
	<li><code>1 &lt;= n &lt;= 14</code></li>
	<li><code>1 &lt;= tasks[i] &lt;= 10</code></li>
	<li><code>max(tasks[i]) &lt;= sessionTime &lt;= 15</code></li>
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
