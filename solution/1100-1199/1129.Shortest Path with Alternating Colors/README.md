# [1129. 颜色交替的最短路径](https://leetcode-cn.com/problems/shortest-path-with-alternating-colors)

[English Version](/solution/1100-1199/1129.Shortest%20Path%20with%20Alternating%20Colors/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在一个有向图中，节点分别标记为&nbsp;<code>0, 1, ..., n-1</code>。这个图中的每条边不是红色就是蓝色，且存在自环或平行边。</p>

<p><code>red_edges</code>&nbsp;中的每一个&nbsp;<code>[i, j]</code>&nbsp;对表示从节点 <code>i</code> 到节点 <code>j</code> 的红色有向边。类似地，<code>blue_edges</code>&nbsp;中的每一个&nbsp;<code>[i, j]</code>&nbsp;对表示从节点 <code>i</code> 到节点 <code>j</code> 的蓝色有向边。</p>

<p>返回长度为 <code>n</code> 的数组&nbsp;<code>answer</code>，其中&nbsp;<code>answer[X]</code>&nbsp;是从节点&nbsp;<code>0</code>&nbsp;到节点&nbsp;<code>X</code>&nbsp;的红色边和蓝色边交替出现的最短路径的长度。如果不存在这样的路径，那么 <code>answer[x] = -1</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 3, red_edges = [[0,1],[1,2]], blue_edges = []
<strong>输出：</strong>[0,1,-1]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 3, red_edges = [[0,1]], blue_edges = [[2,1]]
<strong>输出：</strong>[0,1,-1]
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>n = 3, red_edges = [[1,0]], blue_edges = [[2,1]]
<strong>输出：</strong>[0,-1,-1]
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>n = 3, red_edges = [[0,1]], blue_edges = [[1,2]]
<strong>输出：</strong>[0,1,2]
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>n = 3, red_edges = [[0,1],[0,2]], blue_edges = [[1,0]]
<strong>输出：</strong>[0,1,1]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>red_edges.length &lt;= 400</code></li>
	<li><code>blue_edges.length &lt;= 400</code></li>
	<li><code>red_edges[i].length == blue_edges[i].length == 2</code></li>
	<li><code>0 &lt;= red_edges[i][j], blue_edges[i][j] &lt; n</code></li>
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
