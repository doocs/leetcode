# [2088. 统计农场中肥沃金字塔的数目](https://leetcode.cn/problems/count-fertile-pyramids-in-a-land)

[English Version](/solution/2000-2099/2088.Count%20Fertile%20Pyramids%20in%20a%20Land/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有一个 <strong>矩形网格</strong>&nbsp;状的农场，划分为&nbsp;<code>m</code>&nbsp;行&nbsp;<code>n</code>&nbsp;列的单元格。每个格子要么是 <strong>肥沃的</strong>&nbsp;（用 <code>1</code>&nbsp;表示），要么是 <strong>贫瘠</strong>&nbsp;的（用 <code>0</code>&nbsp;表示）。网格图以外的所有与格子都视为贫瘠的。</p>

<p>农场中的&nbsp;<strong>金字塔</strong>&nbsp;区域定义如下：</p>

<ol>
	<li>区域内格子数目 <strong>大于&nbsp;</strong><code>1</code>&nbsp;且所有格子都是 <strong>肥沃的</strong>&nbsp;。</li>
	<li>金字塔 <strong>顶端</strong>&nbsp;是这个金字塔 <strong>最上方</strong>&nbsp;的格子。金字塔的高度是它所覆盖的行数。令&nbsp;<code>(r, c)</code>&nbsp;为金字塔的顶端且高度为 <code>h</code>&nbsp;，那么金字塔区域内包含的任一格子&nbsp;<code>(i, j)</code>&nbsp;需满足&nbsp;<code>r &lt;= i &lt;= r + h - 1</code>&nbsp;<strong>且</strong>&nbsp;<code>c - (i - r) &lt;= j &lt;= c + (i - r)</code>&nbsp;。</li>
</ol>

<p>一个 <strong>倒金字塔</strong>&nbsp;类似定义如下：</p>

<ol>
	<li>区域内格子数目 <strong>大于</strong>&nbsp;<code>1</code>&nbsp;且所有格子都是 <b>肥沃的</b>&nbsp;。</li>
	<li>倒金字塔的 <strong>顶端</strong>&nbsp;是这个倒金字塔 <strong>最下方</strong>&nbsp;的格子。倒金字塔的高度是它所覆盖的行数。令&nbsp;<code>(r, c)</code>&nbsp;为金字塔的顶端且高度为 <code>h</code>&nbsp;，那么金字塔区域内包含的任一格子&nbsp;<code>(i, j)</code>&nbsp;需满足&nbsp;<code>r - h + 1 &lt;= i &lt;= r</code> <strong>且</strong> <code>c - (r - i) &lt;= j &lt;= c + (r - i)</code>&nbsp;。</li>
</ol>

<p>下图展示了部分符合定义和不符合定义的金字塔区域。黑色区域表示肥沃的格子。</p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2088.Count%20Fertile%20Pyramids%20in%20a%20Land/images/image.png" style="width: 700px; height: 156px;"></p>

<p>给你一个下标从 <strong>0</strong>&nbsp;开始且大小为 <code>m x n</code>&nbsp;的二进制矩阵&nbsp;<code>grid</code>&nbsp;，它表示农场，请你返回 <code>grid</code>&nbsp;中金字塔和倒金字塔的&nbsp;<strong>总数目</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2088.Count%20Fertile%20Pyramids%20in%20a%20Land/images/eg11.png" style="width: 200px; height: 102px;">&nbsp;<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2088.Count%20Fertile%20Pyramids%20in%20a%20Land/images/exa12.png" style="width: 200px; height: 102px;">&nbsp;<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2088.Count%20Fertile%20Pyramids%20in%20a%20Land/images/exa13.png" style="width: 200px; height: 102px;"></p>

<pre><b>输入：</b>grid = [[0,1,1,0],[1,1,1,1]]
<b>输出：</b>2
<strong>解释：</strong>
2 个可能的金字塔区域分别如上图蓝色和红色区域所示。
这个网格图中没有倒金字塔区域。
所以金字塔区域总数为 2 + 0 = 2 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2088.Count%20Fertile%20Pyramids%20in%20a%20Land/images/eg21.png" style="width: 180px; height: 122px;">&nbsp;<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2088.Count%20Fertile%20Pyramids%20in%20a%20Land/images/exa22.png" style="width: 180px; height: 122px;">&nbsp;<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2088.Count%20Fertile%20Pyramids%20in%20a%20Land/images/exa23.png" style="width: 180px; height: 122px;"></p>

<pre><b>输入：</b>grid = [[1,1,1],[1,1,1]]
<b>输出：</b>2
<strong>解释：</strong>
金字塔区域如上图蓝色区域所示，倒金字塔如上图红色区域所示。
所以金字塔区域总数目为 1 + 1 = 2 。
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2088.Count%20Fertile%20Pyramids%20in%20a%20Land/images/eg3.png" style="width: 149px; height: 150px;"></p>

<pre><b>输入：</b>grid = [[1,0,1],[0,0,0],[1,0,1]]
<b>输出：</b>0
<strong>解释：</strong>
网格图中没有任何金字塔或倒金字塔区域。
</pre>

<p><strong>示例 4：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2088.Count%20Fertile%20Pyramids%20in%20a%20Land/images/eg41.png" style="width: 180px; height: 144px;">&nbsp;<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2088.Count%20Fertile%20Pyramids%20in%20a%20Land/images/eg42.png" style="width: 180px; height: 144px;">&nbsp;<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2088.Count%20Fertile%20Pyramids%20in%20a%20Land/images/eg43.png" style="width: 180px; height: 144px;">&nbsp;<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2088.Count%20Fertile%20Pyramids%20in%20a%20Land/images/eg44.png" style="width: 180px; height: 144px;"></p>

<pre><strong>输入：</strong>grid = [[1,1,1,1,0],[1,1,1,1,1],[1,1,1,1,1],[0,1,0,0,1]]
<b>输出：</b>13
<strong>解释：</strong>
有 7 个金字塔区域。上图第二和第三张图中展示了它们中的 3 个。
有 6 个倒金字塔区域。上图中最后一张图展示了它们中的 2 个。
所以金字塔区域总数目为 7 + 6 = 13.
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 1000</code></li>
	<li><code>1 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>grid[i][j]</code>&nbsp;要么是&nbsp;<code>0</code>&nbsp;，要么是&nbsp;<code>1</code> 。</li>
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
