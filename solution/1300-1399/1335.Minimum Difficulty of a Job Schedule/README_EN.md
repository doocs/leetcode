# [1335. Minimum Difficulty of a Job Schedule](https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule)

[中文文档](/solution/1300-1399/1335.Minimum%20Difficulty%20of%20a%20Job%20Schedule/README.md)

## Description

<p>You want to schedule a list of jobs in <code>d</code> days. Jobs are dependent (i.e To work on the <code>i-th</code> job, you have to finish all the jobs <code>j</code> where <code>0 &lt;= j &lt; i</code>).</p>

<p>You have to finish <strong>at least</strong> one task every day. The difficulty of a job schedule is the sum of difficulties of each day of the <code>d</code> days. The difficulty of a day is the maximum difficulty of a job done in that day.</p>

<p>Given an array of integers <code>jobDifficulty</code> and an integer <code>d</code>. The difficulty of the <code>i-th</code>&nbsp;job is&nbsp;<code>jobDifficulty[i]</code>.</p>

<p>Return <em>the minimum difficulty</em> of a job schedule. If you cannot find a schedule for the jobs return <strong>-1</strong>.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1335.Minimum%20Difficulty%20of%20a%20Job%20Schedule/images/untitled.png" style="width: 365px; height: 230px;" />

<pre>

<strong>Input:</strong> jobDifficulty = [6,5,4,3,2,1], d = 2

<strong>Output:</strong> 7

<strong>Explanation:</strong> First day you can finish the first 5 jobs, total difficulty = 6.

Second day you can finish the last job, total difficulty = 1.

The difficulty of the schedule = 6 + 1 = 7 

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> jobDifficulty = [9,9,9], d = 4

<strong>Output:</strong> -1

<strong>Explanation:</strong> If you finish a job per day you will still have a free day. you cannot find a schedule for the given jobs.

</pre>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input:</strong> jobDifficulty = [1,1,1], d = 3

<strong>Output:</strong> 3

<strong>Explanation:</strong> The schedule is one job per day. total difficulty will be 3.

</pre>

<p><strong>Example 4:</strong></p>

<pre>

<strong>Input:</strong> jobDifficulty = [7,1,7,1,7,1], d = 3

<strong>Output:</strong> 15

</pre>

<p><strong>Example 5:</strong></p>

<pre>

<strong>Input:</strong> jobDifficulty = [11,111,22,222,33,333,44,444], d = 6

<strong>Output:</strong> 843

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= jobDifficulty.length &lt;= 300</code></li>
	<li><code>0 &lt;=&nbsp;jobDifficulty[i] &lt;= 1000</code></li>
	<li><code>1 &lt;= d &lt;= 10</code></li>
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
