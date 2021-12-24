# [1765. 地图中的最高点](https://leetcode-cn.com/problems/map-of-highest-peak)

[English Version](/solution/1700-1799/1765.Map%20of%20Highest%20Peak/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个大小为 <code>m x n</code> 的整数矩阵 <code>isWater</code> ，它代表了一个由 <strong>陆地</strong> 和 <strong>水域</strong> 单元格组成的地图。</p>

<ul>
	<li>如果 <code>isWater[i][j] == 0</code> ，格子 <code>(i, j)</code> 是一个 <strong>陆地</strong> 格子。</li>
	<li>如果 <code>isWater[i][j] == 1</code> ，格子 <code>(i, j)</code> 是一个 <strong>水域</strong> 格子。</li>
</ul>

<p>你需要按照如下规则给每个单元格安排高度：</p>

<ul>
	<li>每个格子的高度都必须是非负的。</li>
	<li>如果一个格子是是 <strong>水域</strong> ，那么它的高度必须为 <code>0</code> 。</li>
	<li>任意相邻的格子高度差 <strong>至多</strong> 为 <code>1</code> 。当两个格子在正东、南、西、北方向上相互紧挨着，就称它们为相邻的格子。（也就是说它们有一条公共边）</li>
</ul>

<p>找到一种安排高度的方案，使得矩阵中的最高高度值 <strong>最大</strong> 。</p>

<p>请你返回一个大小为 <code>m x n</code> 的整数矩阵 <code>height</code> ，其中 <code>height[i][j]</code> 是格子 <code>(i, j)</code> 的高度。如果有多种解法，请返回 <strong>任意一个</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1765.Map%20of%20Highest%20Peak/images/screenshot-2021-01-11-at-82045-am.png" style="width: 220px; height: 219px;" /></strong></p>

<pre>
<b>输入：</b>isWater = [[0,1],[0,0]]
<b>输出：</b>[[1,0],[2,1]]
<b>解释：</b>上图展示了给各个格子安排的高度。
蓝色格子是水域格，绿色格子是陆地格。
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1765.Map%20of%20Highest%20Peak/images/screenshot-2021-01-11-at-82050-am.png" style="width: 300px; height: 296px;" /></strong></p>

<pre>
<b>输入：</b>isWater = [[0,0,1],[1,0,0],[0,0,0]]
<b>输出：</b>[[1,1,0],[0,1,1],[1,2,2]]
<b>解释：</b>所有安排方案中，最高可行高度为 2 。
任意安排方案中，只要最高高度为 2 且符合上述规则的，都为可行方案。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == isWater.length</code></li>
	<li><code>n == isWater[i].length</code></li>
	<li><code>1 <= m, n <= 1000</code></li>
	<li><code>isWater[i][j]</code> 要么是 <code>0</code> ，要么是 <code>1</code> 。</li>
	<li>至少有 <strong>1</strong> 个水域格子。</li>
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
