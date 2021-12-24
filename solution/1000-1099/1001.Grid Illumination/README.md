# [1001. 网格照明](https://leetcode-cn.com/problems/grid-illumination)

[English Version](/solution/1000-1099/1001.Grid%20Illumination/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在 <code>N x N</code> 的网格 <code>grid</code> 上，每个单元格都有一盏灯，最初灯都处于 <strong>关闭</strong> 状态。</p>

<p>数组 <code>lamps</code> 表示打开的灯的位置。<code>lamps[i] = [row<sub>i</sub>, col<sub>i</sub>]</code> 表示 <strong>打开</strong> 位于 <code>grid[row<sub>i</sub>][col<sub>i</sub>]</code> 的第 <code>i</code> 盏灯 。每盏灯都照亮自身单元格以及同一行、同一列和两条对角线上的所有其他单元格。</p>

<p>查询数组 <code>queries</code> 中，第 <code>i</code> 次查询 <code>queries[i] = [row<sub>i</sub>, col<sub>i</sub>]</code>，如果单元格 <code>[row<sub>i</sub>, col<sub>i</sub>]</code> 是被照亮的，则查询结果为 <code>1</code> ，否则为 <code>0</code> 。在第 <code>i</code> 次查询之后 [按照查询的顺序] ，<strong>关闭</strong> 位于单元格 <code>grid[row<sub>i</sub>][col<sub>i</sub>]</code> 上或其相邻 8 个方向上（与单元格 <code>grid[row<sub>i</sub>][col<sub>i</sub>]</code> 共享角或边）的任何灯。</p>

<p>返回答案数组 <code>ans</code> ， <code>answer[i]</code> 应等于第 <code>i</code> 次查询 <code>queries[i]</code> 的结果，<code>1</code> 表示照亮，<code>0</code> 表示未照亮。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1001.Grid%20Illumination/images/illu_1.jpg" style="width: 750px; height: 209px;" />
<pre>
<strong>输入：</strong>N = 5, lamps = [[0,0],[4,4]], queries = [[1,1],[1,0]]
<strong>输出：</strong>[1,0]
<strong>解释：</strong>最初所有灯都是关闭的。在执行查询之前，打开位于 [0, 0] 和 [4, 4] 的灯。第 0 次查询检查 grid[1][1] 是否被照亮（蓝色方框）。该单元格被照亮，所以 ans[0] = 1 。然后，关闭红色方框中的所有灯。
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1001.Grid%20Illumination/images/illu_step1.jpg" style="width: 500px; height: 218px;" />
第 1 次查询检查 grid[1][0] 是否被照亮（蓝色方框）。该单元格没有被照亮，所以 ans[1] = 0 。然后，关闭红色矩形中的所有灯。
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1001.Grid%20Illumination/images/illu_step2.jpg" style="width: 500px; height: 219px;" />
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>N = 5, lamps = [[0,0],[4,4]], queries = [[1,1],[1,1]]
<strong>输出：</strong>[1,1]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>N = 5, lamps = [[0,0],[0,4]], queries = [[0,4],[0,1],[1,4]]
<strong>输出：</strong>[1,1,0]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= N <= 10<sup>9</sup></code></li>
	<li><code>0 <= lamps.length <= 20000</code></li>
	<li><code>lamps[i].length == 2</code></li>
	<li><code>0 <= lamps[i][j] < N</code></li>
	<li><code>0 <= queries.length <= 20000</code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>0 <= queries[i][j] < N</code></li>
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
<!-- tabs:end -->
