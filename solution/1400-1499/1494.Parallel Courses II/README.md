# [1494. 并行课程 II](https://leetcode.cn/problems/parallel-courses-ii)

[English Version](/solution/1400-1499/1494.Parallel%20Courses%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数&nbsp;<code>n</code>&nbsp;表示某所大学里课程的数目，编号为&nbsp;<code>1</code>&nbsp;到&nbsp;<code>n</code>&nbsp;，数组&nbsp;<code>dependencies</code>&nbsp;中，&nbsp;<code>dependencies[i] = [x<sub>i</sub>, y<sub>i</sub>]</code>&nbsp; 表示一个先修课的关系，也就是课程&nbsp;<code>x<sub>i</sub></code>&nbsp;必须在课程&nbsp;<code>y<sub>i</sub></code><sub>&nbsp;</sub>之前上。同时你还有一个整数&nbsp;<code>k</code>&nbsp;。</p>

<p>在一个学期中，你 <strong>最多</strong>&nbsp;可以同时上 <code>k</code>&nbsp;门课，前提是这些课的先修课在之前的学期里已经上过了。</p>

<p>请你返回上完所有课最少需要多少个学期。题目保证一定存在一种上完所有课的方式。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1494.Parallel%20Courses%20II/images/leetcode_parallel_courses_1.png" style="height: 164px; width: 300px;"></strong></p>

<pre><strong>输入：</strong>n = 4, dependencies = [[2,1],[3,1],[1,4]], k = 2
<strong>输出：</strong>3 
<strong>解释：</strong>上图展示了题目输入的图。在第一个学期中，我们可以上课程 2 和课程 3 。然后第二个学期上课程 1 ，第三个学期上课程 4 。
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1494.Parallel%20Courses%20II/images/leetcode_parallel_courses_2.png" style="height: 234px; width: 300px;"></strong></p>

<pre><strong>输入：</strong>n = 5, dependencies = [[2,1],[3,1],[4,1],[1,5]], k = 2
<strong>输出：</strong>4 
<strong>解释：</strong>上图展示了题目输入的图。一个最优方案是：第一学期上课程 2 和 3，第二学期上课程 4 ，第三学期上课程 1 ，第四学期上课程 5 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>n = 11, dependencies = [], k = 2
<strong>输出：</strong>6
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 15</code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
	<li><code>0 &lt;=&nbsp;dependencies.length &lt;= n * (n-1) / 2</code></li>
	<li><code>dependencies[i].length == 2</code></li>
	<li><code>1 &lt;= x<sub>i</sub>, y<sub>i</sub>&nbsp;&lt;= n</code></li>
	<li><code>x<sub>i</sub> != y<sub>i</sub></code></li>
	<li>所有先修关系都是不同的，也就是说&nbsp;<code>dependencies[i] != dependencies[j]</code>&nbsp;。</li>
	<li>题目输入的图是个有向无环图。</li>
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
