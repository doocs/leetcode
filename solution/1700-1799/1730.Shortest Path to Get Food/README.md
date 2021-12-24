# [1730. 获取食物的最短路径](https://leetcode-cn.com/problems/shortest-path-to-get-food)

[English Version](/solution/1700-1799/1730.Shortest%20Path%20to%20Get%20Food/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你现在很饿，想要尽快找东西吃。你需要找到最短的路径到达一个食物所在的格子。</p>

<p>给定一个 <code>m x n</code> 的字符矩阵 <code>grid</code> ，包含下列不同类型的格子：</p>

<ul>
	<li><code>'*'</code> 是你的位置。矩阵中<strong>有且只有一个 </strong><code>'*'</code> 格子。</li>
	<li><code>'#'</code> 是食物。矩阵中可能存在<strong>多个</strong>食物。</li>
	<li><code>'O'</code> 是空地，你可以穿过这些格子。</li>
	<li><code>'X'</code> 是障碍，你不可以穿过这些格子。</li>
</ul>

<p>返回你到任意食物的最短路径的长度。如果不存在你到任意食物的路径，返回 <code>-1</code>。</p>

<p> </p>

<p><b>示例 1:</b></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1730.Shortest%20Path%20to%20Get%20Food/images/img1.jpg" style="width: 300px; height: 201px;">
<pre><b>输入：</b> grid = [["X","X","X","X","X","X"],["X","*","O","O","O","X"],["X","O","O","#","O","X"],["X","X","X","X","X","X"]]
<b>输出：</b> 3
<b>解释： </b>要拿到食物，你需要走 3 步。</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1730.Shortest%20Path%20to%20Get%20Food/images/img2.jpg" style="width: 300px; height: 241px;">
<pre><b>输入：</b> grid = [["X","X","X","X","X"],["X","*","X","O","X"],["X","O","X","#","X"],["X","X","X","X","X"]]
<b>输出：</b> -1
<b>解释：</b> 你不可能拿到食物。
</pre>

<p><strong>示例 3:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1730.Shortest%20Path%20to%20Get%20Food/images/img3.jpg" style="width: 300px; height: 188px;">
<pre><strong>输入:</strong> grid = [["X","X","X","X","X","X","X","X"],["X","*","O","X","O","#","O","X"],["X","O","O","X","O","O","X","X"],["X","O","O","O","O","#","O","X"],["X","X","X","X","X","X","X","X"]]
<strong>输出:</strong> 6
<strong>解释:</strong> 这里有多个食物。拿到下边的食物仅需走 6 步。</pre>

<p><strong>示例 4:</strong></p>

<pre><strong>输入:</strong> grid = [["O","*"],["#","O"]]
<strong>输出:</strong> 2
</pre>

<p><strong>示例 5:</strong></p>

<pre><strong>输入:</strong> grid = [["X","*"],["#","X"]]
<b>输出:</b> -1</pre>

<p> </p>

<p><b>提示：</b></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 200</code></li>
	<li><code>grid[row][col]</code> 是 <code>'*'</code>、 <code>'X'</code>、 <code>'O'</code> 或 <code>'#'</code> 。</li>
	<li><code>grid</code> 中<strong>有且只有一个</strong> <code>'*'</code> 。</li>
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
