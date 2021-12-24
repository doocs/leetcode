# [286. 墙与门](https://leetcode-cn.com/problems/walls-and-gates)

[English Version](/solution/0200-0299/0286.Walls%20and%20Gates/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你被给定一个 <code>m × n</code> 的二维网格 <code>rooms</code> ，网格中有以下三种可能的初始化值：</p>

<ol>
	<li><code>-1</code> 表示墙或是障碍物</li>
	<li><code>0</code> 表示一扇门</li>
	<li><code>INF</code> 无限表示一个空的房间。然后，我们用 <code>2<sup>31</sup> - 1 = 2147483647</code> 代表 <code>INF</code>。你可以认为通往门的距离总是小于 <code>2147483647</code> 的。</li>
</ol>

<p>你要给每个空房间位上填上该房间到 <strong>最近门的距离</strong> ，如果无法到达门，则填 <code>INF</code> 即可。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0286.Walls%20and%20Gates/images/grid.jpg" style="width: 500px; height: 223px;" />
<pre>
<strong>输入：</strong>rooms = [[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1],[2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]
<strong>输出：</strong>[[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>rooms = [[-1]]
<strong>输出：</strong>[[-1]]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>rooms = [[2147483647]]
<strong>输出：</strong>[[2147483647]]
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>rooms = [[0]]
<strong>输出：</strong>[[0]]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == rooms.length</code></li>
	<li><code>n == rooms[i].length</code></li>
	<li><code>1 <= m, n <= 250</code></li>
	<li><code>rooms[i][j]</code> 是 <code>-1</code>、<code>0</code> 或 <code>2<sup>31</sup> - 1</code></li>
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
