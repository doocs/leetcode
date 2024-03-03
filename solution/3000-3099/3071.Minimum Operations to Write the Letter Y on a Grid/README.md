# [3071. 在矩阵上写出字母 Y 所需的最少操作次数](https://leetcode.cn/problems/minimum-operations-to-write-the-letter-y-on-a-grid)

[English Version](/solution/3000-3099/3071.Minimum%20Operations%20to%20Write%20the%20Letter%20Y%20on%20a%20Grid/README_EN.md)

<!-- tags: -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始、大小为 <code>n x n</code> 的矩阵 <code>grid</code> ，其中 <code>n</code> 为奇数，且 <code>grid[r][c]</code> 的值为 <code>0</code> 、<code>1</code> 或 <code>2</code> 。</p>

<p>如果一个单元格属于以下三条线中的任一一条，我们就认为它是字母 <strong>Y</strong> 的一部分：</p>

<ul>
	<li>从左上角单元格开始到矩阵中心单元格结束的对角线。</li>
	<li>从右上角单元格开始到矩阵中心单元格结束的对角线。</li>
	<li>从中心单元格开始到矩阵底部边界结束的垂直线。</li>
</ul>

<p>当且仅当满足以下全部条件时，可以判定矩阵上写有字母 <strong>Y </strong>：</p>

<ul>
	<li>属于 Y 的所有单元格的值相等。</li>
	<li>不属于 Y 的所有单元格的值相等。</li>
	<li>属于 Y 的单元格的值与不属于Y的单元格的值不同。</li>
</ul>

<p>每次操作你可以将任意单元格的值改变为 <code>0</code> 、<code>1</code> 或 <code>2</code> 。返回在矩阵上写出字母 Y 所需的 <strong>最少 </strong>操作次数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3071.Minimum%20Operations%20to%20Write%20the%20Letter%20Y%20on%20a%20Grid/images/y2.png" style="width: 461px; height: 121px;" />
<pre>
<strong>输入：</strong>grid = [[1,2,2],[1,1,0],[0,1,0]]
<strong>输出：</strong>3
<strong>解释：</strong>将在矩阵上写出字母 Y 需要执行的操作用蓝色高亮显示。操作后，所有属于 Y 的单元格（加粗显示）的值都为 1 ，而不属于 Y 的单元格的值都为 0 。
可以证明，写出 Y 至少需要进行 3 次操作。
</pre>

<p><strong class="example">示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3071.Minimum%20Operations%20to%20Write%20the%20Letter%20Y%20on%20a%20Grid/images/y3.png" style="width: 701px; height: 201px;" />
<pre>
<strong>输入：</strong>grid = [[0,1,0,1,0],[2,1,0,1,2],[2,2,2,0,1],[2,2,2,2,2],[2,1,2,2,2]]
<strong>输出：</strong>12
<strong>解释：</strong>将在矩阵上写出字母 Y 需要执行的操作用蓝色高亮显示。操作后，所有属于 Y 的单元格（加粗显示）的值都为 0 ，而不属于 Y 的单元格的值都为 2 。
可以证明，写出 Y 至少需要进行 12 次操作。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= n &lt;= 49</code></li>
	<li><code>n == grid.length == grid[i].length</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 2</code></li>
	<li><code>n</code> 为奇数。</li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python

```

```java

```

```cpp

```

```go

```

<!-- tabs:end -->

<!-- end -->
