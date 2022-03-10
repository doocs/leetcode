# [1136. Parallel Courses](https://leetcode.com/problems/parallel-courses)

[中文文档](/solution/1100-1199/1136.Parallel%20Courses/README.md)

## Description

<p>You are given an integer <code>n</code>, which indicates that there are <code>n</code> courses labeled from <code>1</code> to <code>n</code>. You are also given an array <code>relations</code> where <code>relations[i] = [prevCourse<sub>i</sub>, nextCourse<sub>i</sub>]</code>, representing a prerequisite relationship between course <code>prevCourse<sub>i</sub></code> and course <code>nextCourse<sub>i</sub></code>: course <code>prevCourse<sub>i</sub></code> has to be taken before course <code>nextCourse<sub>i</sub></code>.</p>

<p>In one semester, you can take <strong>any number</strong> of courses as long as you have taken all the prerequisites in the <strong>previous</strong> semester for the courses you are taking.</p>

<p>Return <em>the <strong>minimum</strong> number of semesters needed to take all courses</em>. If there is no way to take all the courses, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1136.Parallel%20Courses/images/course1graph.jpg" style="width: 222px; height: 222px;" />
<pre>
<strong>Input:</strong> n = 3, relations = [[1,3],[2,3]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The figure above represents the given graph.
In the first semester, you can take courses 1 and 2.
In the second semester, you can take course 3.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1136.Parallel%20Courses/images/course2graph.jpg" style="width: 222px; height: 222px;" />
<pre>
<strong>Input:</strong> n = 3, relations = [[1,2],[2,3],[3,1]]
<strong>Output:</strong> -1
<strong>Explanation:</strong> No course can be studied because they are prerequisites of each other.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 5000</code></li>
	<li><code>1 &lt;= relations.length &lt;= 5000</code></li>
	<li><code>relations[i].length == 2</code></li>
	<li><code>1 &lt;= prevCourse<sub>i</sub>, nextCourse<sub>i</sub> &lt;= n</code></li>
	<li><code>prevCourse<sub>i</sub> != nextCourse<sub>i</sub></code></li>
	<li>All the pairs <code>[prevCourse<sub>i</sub>, nextCourse<sub>i</sub>]</code> are <strong>unique</strong>.</li>
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
