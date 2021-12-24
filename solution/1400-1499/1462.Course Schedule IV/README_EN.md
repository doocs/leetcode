# [1462. Course Schedule IV](https://leetcode.com/problems/course-schedule-iv)

[中文文档](/solution/1400-1499/1462.Course%20Schedule%20IV/README.md)

## Description

<p>There are a total of <code>n</code> courses you have to take, labeled from <code>0</code> to <code>n-1</code>.</p>

<p>Some courses may have direct prerequisites, for example, to take course 0 you have first to take course 1, which is expressed as a pair: <code>[1,0]</code></p>

<p>Given the total number of courses <code>n</code>,&nbsp;a list of direct&nbsp;<code>prerequisite</code> <strong>pairs</strong> and a list of <code>queries</code> <strong>pairs</strong>.</p>

<p>You should answer for each <code>queries[i]</code> whether the course <code>queries[i][0]</code> is a&nbsp;prerequisite of the course&nbsp;<code>queries[i][1]</code> or not.</p>

<p>Return <em>a list of boolean</em>, the answers to the given <code>queries</code>.</p>

<p>Please note that if course <strong>a</strong> is a prerequisite of course <strong>b</strong> and course <strong>b</strong> is a prerequisite&nbsp;of course <strong>c</strong>, then, course <strong>a</strong> is a&nbsp;prerequisite of course <strong>c</strong>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1462.Course%20Schedule%20IV/images/graph.png" style="width: 300px; height: 300px;" />
<pre>
<strong>Input:</strong> n = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
<strong>Output:</strong> [false,true]
<strong>Explanation:</strong> course 0 is not a prerequisite of course 1 but the opposite is true.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2, prerequisites = [], queries = [[1,0],[0,1]]
<strong>Output:</strong> [false,false]
<strong>Explanation:</strong> There are no prerequisites and each course is independent.
</pre>

<p><strong>Example 3:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1462.Course%20Schedule%20IV/images/graph-1.png" style="width: 300px; height: 300px;" />
<pre>
<strong>Input:</strong> n = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
<strong>Output:</strong> [true,true]
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> n = 3, prerequisites = [[1,0],[2,0]], queries = [[0,1],[2,0]]
<strong>Output:</strong> [false,true]
</pre>

<p><strong>Example 5:</strong></p>

<pre>
<strong>Input:</strong> n = 5, prerequisites = [[0,1],[1,2],[2,3],[3,4]], queries = [[0,4],[4,0],[1,3],[3,0]]
<strong>Output:</strong> [true,false,true,false]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 100</code></li>
	<li><code>0 &lt;= prerequisite.length &lt;= (n * (n - 1) / 2)</code></li>
	<li><code>0 &lt;= prerequisite[i][0], prerequisite[i][1] &lt; n</code></li>
	<li><code>prerequisite[i][0] != prerequisite[i][1]</code></li>
	<li>The prerequisites graph has no cycles.</li>
	<li>The prerequisites graph has no repeated edges.</li>
	<li><code>1 &lt;= queries.length &lt;= 10^4</code></li>
	<li><code>queries[i][0] != queries[i][1]</code></li>
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
