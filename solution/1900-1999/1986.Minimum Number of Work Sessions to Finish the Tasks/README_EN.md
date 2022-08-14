# [1986. Minimum Number of Work Sessions to Finish the Tasks](https://leetcode.com/problems/minimum-number-of-work-sessions-to-finish-the-tasks)

[中文文档](/solution/1900-1999/1986.Minimum%20Number%20of%20Work%20Sessions%20to%20Finish%20the%20Tasks/README.md)

## Description

<p>There are <code>n</code> tasks assigned to you. The task times are represented as an integer array <code>tasks</code> of length <code>n</code>, where the <code>i<sup>th</sup></code> task takes <code>tasks[i]</code> hours to finish. A <strong>work session</strong> is when you work for <strong>at most</strong> <code>sessionTime</code> consecutive hours and then take a break.</p>

<p>You should finish the given tasks in a way that satisfies the following conditions:</p>

<ul>
	<li>If you start a task in a work session, you must complete it in the <strong>same</strong> work session.</li>
	<li>You can start a new task <strong>immediately</strong> after finishing the previous one.</li>
	<li>You may complete the tasks in <strong>any order</strong>.</li>
</ul>

<p>Given <code>tasks</code> and <code>sessionTime</code>, return <em>the <strong>minimum</strong> number of <strong>work sessions</strong> needed to finish all the tasks following the conditions above.</em></p>

<p>The tests are generated such that <code>sessionTime</code> is <strong>greater</strong> than or <strong>equal</strong> to the <strong>maximum</strong> element in <code>tasks[i]</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> tasks = [1,2,3], sessionTime = 3
<strong>Output:</strong> 2
<strong>Explanation:</strong> You can finish the tasks in two work sessions.
- First work session: finish the first and the second tasks in 1 + 2 = 3 hours.
- Second work session: finish the third task in 3 hours.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> tasks = [3,1,3,1,1], sessionTime = 8
<strong>Output:</strong> 2
<strong>Explanation:</strong> You can finish the tasks in two work sessions.
- First work session: finish all the tasks except the last one in 3 + 1 + 3 + 1 = 8 hours.
- Second work session: finish the last task in 1 hour.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> tasks = [1,2,3,4,5], sessionTime = 15
<strong>Output:</strong> 1
<strong>Explanation:</strong> You can finish all the tasks in one work session.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == tasks.length</code></li>
	<li><code>1 &lt;= n &lt;= 14</code></li>
	<li><code>1 &lt;= tasks[i] &lt;= 10</code></li>
	<li><code>max(tasks[i]) &lt;= sessionTime &lt;= 15</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **...**

```

```

<!-- tabs:end -->
