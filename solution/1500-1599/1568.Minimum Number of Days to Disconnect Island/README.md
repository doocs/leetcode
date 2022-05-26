# [1568. 使陆地分离的最少天数](https://leetcode.cn/problems/minimum-number-of-days-to-disconnect-island)

[English Version](/solution/1500-1599/1568.Minimum%20Number%20of%20Days%20to%20Disconnect%20Island/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个由若干 <code>0</code> 和 <code>1</code> 组成的二维网格 <code>grid</code> ，其中 <code>0</code> 表示水，而 <code>1</code> 表示陆地。岛屿由水平方向或竖直方向上相邻的 <code>1</code> （陆地）连接形成。</p>

<p>如果 <strong>恰好只有一座岛屿 </strong>，则认为陆地是 <strong>连通的</strong> ；否则，陆地就是 <strong>分离的</strong> 。</p>

<p>一天内，可以将任何单个陆地单元（<code>1</code>）更改为水单元（<code>0</code>）。</p>

<p>返回使陆地分离的最少天数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1568.Minimum%20Number%20of%20Days%20to%20Disconnect%20Island/images/1926_island.png" style="height: 139px; width: 498px;"></strong></p>

<pre><strong>输入：</strong>grid = [[0,1,1,0],[0,1,1,0],[0,0,0,0]]
<strong>输出：</strong>2
<strong>解释：</strong>至少需要 2 天才能得到分离的陆地。
将陆地 grid[1][1] 和 grid[0][2] 更改为水，得到两个分离的岛屿。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>grid = [[1,1]]
<strong>输出：</strong>2
<strong>解释：</strong>如果网格中都是水，也认为是分离的 ([[1,1]] -&gt; [[0,0]])，0 岛屿。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>grid = [[1,0,1,0]]
<strong>输出：</strong>0
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>grid = [[1,1,0,1,1],
&nbsp;            [1,1,1,1,1],
&nbsp;            [1,1,0,1,1],
&nbsp;            [1,1,0,1,1]]
<strong>输出：</strong>1
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>grid = [[1,1,0,1,1],
&nbsp;            [1,1,1,1,1],
&nbsp;            [1,1,0,1,1],
&nbsp;            [1,1,1,1,1]]
<strong>输出：</strong>2
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= grid.length, grid[i].length &lt;= 30</code></li>
	<li><code>grid[i][j]</code> 为 <code>0</code> 或 <code>1</code></li>
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
