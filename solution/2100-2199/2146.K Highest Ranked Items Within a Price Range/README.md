# [2146. 价格范围内最高排名的 K 样物品](https://leetcode-cn.com/problems/k-highest-ranked-items-within-a-price-range)

[English Version](/solution/2100-2199/2146.K%20Highest%20Ranked%20Items%20Within%20a%20Price%20Range/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的二维整数数组&nbsp;<code>grid</code>&nbsp;，它的大小为&nbsp;<code>m x n</code>&nbsp;，表示一个商店中物品的分布图。数组中的整数含义为：</p>

<ul>
	<li><code>0</code>&nbsp;表示无法穿越的一堵墙。</li>
	<li><code>1</code>&nbsp;表示可以自由通过的一个空格子。</li>
	<li>所有其他正整数表示该格子内的一样物品的价格。你可以自由经过这些格子。</li>
</ul>

<p>从一个格子走到上下左右相邻格子花费&nbsp;<code>1</code>&nbsp;步。</p>

<p>同时给你一个整数数组&nbsp;<code>pricing</code> 和&nbsp;<code>start</code>&nbsp;，其中&nbsp;<code>pricing = [low, high]</code> 且&nbsp;<code>start = [row, col]</code>&nbsp;，表示你开始位置为&nbsp;<code>(row, col)</code>&nbsp;，同时你只对物品价格在<strong>&nbsp;闭区间</strong>&nbsp;<code>[low, high]</code>&nbsp;之内的物品感兴趣。同时给你一个整数&nbsp;<code>k</code>&nbsp;。</p>

<p>你想知道给定范围 <strong>内</strong>&nbsp;且 <strong>排名最高</strong>&nbsp;的 <code>k</code>&nbsp;件物品的 <strong>位置</strong>&nbsp;。排名按照优先级从高到低的以下规则制定：</p>

<ol>
	<li>距离：定义为从&nbsp;<code>start</code>&nbsp;到一件物品的最短路径需要的步数（<strong>较近</strong>&nbsp;距离的排名更高）。</li>
	<li>价格：<strong>较低</strong>&nbsp;价格的物品有更高优先级，但只考虑在给定范围之内的价格。</li>
	<li>行坐标：<strong>较小</strong>&nbsp;行坐标的有更高优先级。</li>
	<li>列坐标：<strong>较小</strong>&nbsp;列坐标的有更高优先级。</li>
</ol>

<p>请你返回给定价格内排名最高的 <code>k</code>&nbsp;件物品的坐标，将它们按照排名排序后返回。如果给定价格内少于 <code>k</code>&nbsp;件物品，那么请将它们的坐标&nbsp;<strong>全部</strong>&nbsp;返回。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2146.K%20Highest%20Ranked%20Items%20Within%20a%20Price%20Range/images/example1drawio.png" style="width: 200px; height: 151px;"></p>

<pre><b>输入：</b>grid = [[1,2,0,1],[1,3,0,1],[0,2,5,1]], pricing = [2,5], start = [0,0], k = 3
<b>输出：</b>[[0,1],[1,1],[2,1]]
<b>解释：</b>起点为 (0,0) 。
价格范围为 [2,5] ，我们可以选择的物品坐标为 (0,1)，(1,1)，(2,1) 和 (2,2) 。
这些物品的排名为：
- (0,1) 距离为 1
- (1,1) 距离为 2
- (2,1) 距离为 3
- (2,2) 距离为 4
所以，给定价格范围内排名最高的 3 件物品的坐标为 (0,1)，(1,1) 和 (2,1) 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2146.K%20Highest%20Ranked%20Items%20Within%20a%20Price%20Range/images/example2drawio1.png" style="width: 200px; height: 151px;"></p>

<pre><b>输入：</b>grid = [[1,2,0,1],[1,3,3,1],[0,2,5,1]], pricing = [2,3], start = [2,3], k = 2
<b>输出：</b>[[2,1],[1,2]]
<b>解释：</b>起点为 (2,3) 。
价格范围为 [2,3] ，我们可以选择的物品坐标为 (0,1)，(1,1)，(1,2) 和 (2,1) 。
这些物品的排名为： 
- (2,1) 距离为 2 ，价格为 2
- (1,2) 距离为 2 ，价格为 3
- (1,1) 距离为 3
- (0,1) 距离为 4
所以，给定价格范围内排名最高的 2 件物品的坐标为 (2,1) 和 (1,2) 。
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2146.K%20Highest%20Ranked%20Items%20Within%20a%20Price%20Range/images/example3.png" style="width: 149px; height: 150px;"></p>

<pre><b>输入：</b>grid = [[1,1,1],[0,0,1],[2,3,4]], pricing = [2,3], start = [0,0], k = 3
<b>输出：</b>[[2,1],[2,0]]
<b>解释：</b>起点为 (0,0) 。
价格范围为 [2,3] ，我们可以选择的物品坐标为 (2,0) 和 (2,1) 。
这些物品的排名为：
- (2,1) 距离为 5
- (2,0) 距离为 6
所以，给定价格范围内排名最高的 2 件物品的坐标为 (2,1) 和 (2,0) 。
注意，k = 3 但给定价格范围内只有 2 件物品。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 10<sup>5</sup></code></li>
	<li><code>pricing.length == 2</code></li>
	<li><code>2 &lt;= low &lt;= high &lt;= 10<sup>5</sup></code></li>
	<li><code>start.length == 2</code></li>
	<li><code>0 &lt;= row &lt;= m - 1</code></li>
	<li><code>0 &lt;= col &lt;= n - 1</code></li>
	<li><code>grid[row][col] &gt; 0</code></li>
	<li><code>1 &lt;= k &lt;= m * n</code></li>
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

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts

```

### **...**

```

```

<!-- tabs:end -->
