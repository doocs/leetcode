# [1136. Parallel Courses](https://leetcode.com/problems/parallel-courses)

[中文文档](/solution/1100-1199/1136.Parallel%20Courses/README.md)

## Description

<p>You are given an integer <code>n</code> which indicates that we have <code>n</code> courses, labeled from <code>1</code> to <code>n</code>. You are also given an array <code>relations</code> where <code>relations[i] = [a, b]</code>, representing a prerequisite relationship between course <code>a</code> and course <code>b</code>: course <code>a</code> has to be studied before course <code>b</code>.</p>

<p>In one semester, you can study any number of courses as long as you have studied all the prerequisites for the course you are studying.</p>

<p>Return <em>the minimum number of semesters needed to study all courses</em>. If there is no way to study all the courses, return <code>-1</code>.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1136.Parallel%20Courses/images/course1graph.jpg" style="width: 222px; height: 222px;" />

<pre>

<strong>Input:</strong> n = 3, relations = [[1,3],[2,3]]

<strong>Output:</strong> 2

<strong>Explanation:</strong> In the first semester, courses 1 and 2 are studied. In the second semester, course 3 is studied.

</pre>

<p><strong>Example 2:</strong></p>

<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1136.Parallel%20Courses/images/course2graph.jpg" style="width: 222px; height: 222px;" />

<pre>

<strong>Input:</strong> n = 3, relations = [[1,2],[2,3],[3,1]]

<strong>Output:</strong> -1

<strong>Explanation:</strong> No course can be studied because they depend on each other.

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 5000</code></li>
	<li><code>1 &lt;= relations.length &lt;= 5000</code></li>
	<li><code>1 &lt;= a, b &lt;= n</code></li>
	<li><code>a != b</code></li>
	<li>All the pairs <code>[a, b]</code> are <strong>unique</strong>.</li>
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
