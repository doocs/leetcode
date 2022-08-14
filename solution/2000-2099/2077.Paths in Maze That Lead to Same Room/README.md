# [2077. Paths in Maze That Lead to Same Room](https://leetcode.cn/problems/paths-in-maze-that-lead-to-same-room)

[English Version](/solution/2000-2099/2077.Paths%20in%20Maze%20That%20Lead%20to%20Same%20Room/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>A maze consists of <code>n</code> rooms numbered from <code>1</code> to <code>n</code>, and some rooms are connected by corridors. You are given a 2D integer array <code>corridors</code> where <code>corridors[i] = [room1<sub>i</sub>, room2<sub>i</sub>]</code> indicates that there is a corridor connecting <code>room1<sub>i</sub></code> and <code>room2<sub>i</sub></code>, allowing a person in the maze to go from <code>room1<sub>i</sub></code> to <code>room2<sub>i</sub></code> <strong>and vice versa</strong>.</p>

<p>The designer of the maze wants to know how confusing the maze is. The <strong>confusion</strong> <strong>score</strong> of the maze is the number of different cycles of <strong>length 3</strong>.</p>

<ul>
	<li>For example, <code>1 &rarr; 2 &rarr; 3 &rarr; 1</code> is a cycle of length 3, but <code>1 &rarr; 2 &rarr; 3 &rarr; 4</code> and <code>1 &rarr; 2 &rarr; 3 &rarr; 2 &rarr; 1</code> are not.</li>
</ul>

<p>Two cycles are considered to be <strong>different</strong> if one or more of the rooms visited in the first cycle is <strong>not</strong> in the second cycle.</p>

<p>Return <em>the</em> <em><strong>confusion</strong><strong> score</strong> of the maze.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2077.Paths%20in%20Maze%20That%20Lead%20to%20Same%20Room/images/image-20211114164827-1.png" style="width: 440px; height: 350px;" />
<pre>
<strong>Input:</strong> n = 5, corridors = [[1,2],[5,2],[4,1],[2,4],[3,1],[3,4]]
<strong>Output:</strong> 2
<strong>Explanation:</strong>
One cycle of length 3 is 4 &rarr; 1 &rarr; 3 &rarr; 4, denoted in red.
Note that this is the same cycle as 3 &rarr; 4 &rarr; 1 &rarr; 3 or 1 &rarr; 3 &rarr; 4 &rarr; 1 because the rooms are the same.
Another cycle of length 3 is 1 &rarr; 2 &rarr; 4 &rarr; 1, denoted in blue.
Thus, there are two different cycles of length 3.
</pre>

<p><strong>Example 2:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2077.Paths%20in%20Maze%20That%20Lead%20to%20Same%20Room/images/image-20211114164851-2.png" style="width: 329px; height: 250px;" />
<pre>
<strong>Input:</strong> n = 4, corridors = [[1,2],[3,4]]
<strong>Output:</strong> 0
<strong>Explanation:</strong>
There are no cycles of length 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= corridors.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>corridors[i].length == 2</code></li>
	<li><code>1 &lt;= room1<sub>i</sub>, room2<sub>i</sub> &lt;= n</code></li>
	<li><code>room1<sub>i</sub> != room2<sub>i</sub></code></li>
	<li>There are no duplicate corridors.</li>
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
