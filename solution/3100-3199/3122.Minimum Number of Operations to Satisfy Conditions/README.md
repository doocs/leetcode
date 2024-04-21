# [3122. 使矩阵满足条件的最少操作次数](https://leetcode.cn/problems/minimum-number-of-operations-to-satisfy-conditions)

[English Version](/solution/3100-3199/3122.Minimum%20Number%20of%20Operations%20to%20Satisfy%20Conditions/README_EN.md)

<!-- tags: -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个大小为 <code>m x n</code>&nbsp;的二维矩形&nbsp;<code>grid</code>&nbsp;。每次 <strong>操作</strong>&nbsp;中，你可以将 <strong>任一</strong> 格子的值修改为 <strong>任意</strong>&nbsp;非负整数。完成所有操作后，你需要确保每个格子&nbsp;<code>grid[i][j]</code>&nbsp;的值满足：</p>

<ul>
	<li>如果下面相邻格子存在的话，它们的值相等，也就是&nbsp;<code>grid[i][j] == grid[i + 1][j]</code>（如果存在）。</li>
	<li>如果右边相邻格子存在的话，它们的值不相等，也就是&nbsp;<code>grid[i][j] != grid[i][j + 1]</code>（如果存在）。</li>
</ul>

<p>请你返回需要的 <strong>最少</strong>&nbsp;操作数目。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>grid = [[1,0,2],[1,0,2]]</span></p>

<p><b>输出：</b>0</p>

<p><b>解释：</b></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3122.Minimum%20Number%20of%20Operations%20to%20Satisfy%20Conditions/images/examplechanged.png" style="width: 254px; height: 186px;padding: 10px; background: #fff; border-radius: .5rem;" /></strong></p>

<p>矩阵中所有格子已经满足要求。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>grid = [[1,1,1],[0,0,0]]</span></p>

<p><b>输出：</b>3</p>

<p><strong>解释：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3122.Minimum%20Number%20of%20Operations%20to%20Satisfy%20Conditions/images/example21.png" style="width: 254px; height: 186px;padding: 10px; background: #fff; border-radius: .5rem;" /></strong></p>

<p>将矩阵变成&nbsp;<code>[[1,0,1],[1,0,1]]</code>&nbsp;，它满足所有要求，需要 3 次操作：</p>

<ul>
	<li>将&nbsp;<code>grid[1][0]</code>&nbsp;变为 1 。</li>
	<li>将&nbsp;<code>grid[0][1]</code> 变为 0 。</li>
	<li>将&nbsp;<code>grid[1][2]</code>&nbsp;变为 1 。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>grid = [[1],[2],[3]]</span></p>

<p><b>输出：</b>2</p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3122.Minimum%20Number%20of%20Operations%20to%20Satisfy%20Conditions/images/changed.png" style="width: 86px; height: 277px;padding: 10px; background: #fff; border-radius: .5rem;" /></p>

<p>这个矩阵只有一列，我们可以通过 2 次操作将所有格子里的值变为 1 。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n, m &lt;= 1000</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 9</code></li>
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
