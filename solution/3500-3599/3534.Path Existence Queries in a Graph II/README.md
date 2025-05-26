---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3534.Path%20Existence%20Queries%20in%20a%20Graph%20II/README.md
tags:
    - 贪心
    - 图
    - 数组
    - 二分查找
    - 排序
---

<!-- problem:start -->

# [3534. 针对图的路径存在性查询 II](https://leetcode.cn/problems/path-existence-queries-in-a-graph-ii)

[English Version](/solution/3500-3599/3534.Path%20Existence%20Queries%20in%20a%20Graph%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数 <code>n</code>，表示图中的节点数量，这些节点按从 <code>0</code> 到 <code>n - 1</code>&nbsp;编号。</p>

<p>同时给你一个长度为 <code>n</code> 的整数数组 <code>nums</code>，以及一个整数 <code>maxDiff</code>。</p>

<p>如果满足 <code>|nums[i] - nums[j]| &lt;= maxDiff</code>（即 <code>nums[i]</code> 和 <code>nums[j]</code> 的&nbsp;<strong>绝对差&nbsp;</strong>至多为 <code>maxDiff</code>），则节点 <code>i</code> 和节点 <code>j</code> 之间存在一条&nbsp;<strong>无向边&nbsp;</strong>。</p>

<p>此外，给你一个二维整数数组 <code>queries</code>。对于每个 <code>queries[i] = [u<sub>i</sub>, v<sub>i</sub>]</code>，找到节点 <code>u<sub>i</sub></code> 和节点 <code>v<sub>i</sub></code> 之间的&nbsp;<strong>最短距离&nbsp;</strong>。如果两节点之间不存在路径，则返回 -1。</p>

<p>返回一个数组 <code>answer</code>，其中 <code>answer[i]</code> 是第 <code>i</code> 个查询的结果。</p>

<p><strong>注意：</strong>节点之间的边是无权重（unweighted）的。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">n = 5, nums = [1,8,3,4,2], maxDiff = 3, queries = [[0,3],[2,4]]</span></p>

<p><strong>输出:</strong> <span class="example-io">[1,1]</span></p>

<p><strong>解释:</strong></p>

<p>生成的图如下：</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3534.Path%20Existence%20Queries%20in%20a%20Graph%20II/images/1745660620-PauXMH-4149example1drawio.png" style="width: 281px; height: 161px;" /></p>

<table>
	<tbody>
		<tr>
			<th>查询</th>
			<th>最短路径</th>
			<th>最短距离</th>
		</tr>
		<tr>
			<td>[0, 3]</td>
			<td>0 → 3</td>
			<td>1</td>
		</tr>
		<tr>
			<td>[2, 4]</td>
			<td>2 → 4</td>
			<td>1</td>
		</tr>
	</tbody>
</table>

<p>因此，输出为 <code>[1, 1]</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">n = 5, nums = [5,3,1,9,10], maxDiff = 2, queries = [[0,1],[0,2],[2,3],[4,3]]</span></p>

<p><strong>输出:</strong> <span class="example-io">[1,2,-1,1]</span></p>

<p><strong>解释:</strong></p>

<p>生成的图如下：</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3534.Path%20Existence%20Queries%20in%20a%20Graph%20II/images/1745660627-mSVsDs-4149example2drawio.png" style="width: 281px; height: 121px;" /></p>

<table>
	<tbody>
		<tr>
			<th>查询</th>
			<th>最短路径</th>
			<th>最短距离</th>
		</tr>
		<tr>
			<td>[0, 1]</td>
			<td>0 → 1</td>
			<td>1</td>
		</tr>
		<tr>
			<td>[0, 2]</td>
			<td>0 → 1 → 2</td>
			<td>2</td>
		</tr>
		<tr>
			<td>[2, 3]</td>
			<td>无</td>
			<td>-1</td>
		</tr>
		<tr>
			<td>[4, 3]</td>
			<td>3 → 4</td>
			<td>1</td>
		</tr>
	</tbody>
</table>

<p>因此，输出为 <code>[1, 2, -1, 1]</code>。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">n = 3, nums = [3,6,1], maxDiff = 1, queries = [[0,0],[0,1],[1,2]]</span></p>

<p><strong>输出:</strong> <span class="example-io">[0,-1,-1]</span></p>

<p><strong>解释:</strong></p>

<p>由于以下原因，任意两个节点之间都不存在边：</p>

<ul>
	<li>节点 0 和节点 1：<code>|nums[0] - nums[1]| = |3 - 6| = 3 &gt; 1</code></li>
	<li>节点 0 和节点 2：<code>|nums[0] - nums[2]| = |3 - 1| = 2 &gt; 1</code></li>
	<li>节点 1 和节点 2：<code>|nums[1] - nums[2]| = |6 - 1| = 5 &gt; 1</code></li>
</ul>

<p>因此，不存在任何可以到达其他节点的节点，输出为 <code>[0, -1, -1]</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= maxDiff &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i] == [u<sub>i</sub>, v<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt; n</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python

```

#### Java

```java

```

#### C++

```cpp

```

#### Go

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
