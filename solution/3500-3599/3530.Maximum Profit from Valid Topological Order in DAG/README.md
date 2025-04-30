---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3530.Maximum%20Profit%20from%20Valid%20Topological%20Order%20in%20DAG/README.md
tags:
    - 位运算
    - 图
    - 拓扑排序
    - 数组
    - 动态规划
    - 状态压缩
---

<!-- problem:start -->

# [3530. 有向无环图中合法拓扑排序的最大利润](https://leetcode.cn/problems/maximum-profit-from-valid-topological-order-in-dag)

[English Version](/solution/3500-3599/3530.Maximum%20Profit%20from%20Valid%20Topological%20Order%20in%20DAG/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个由 <code>n</code> 个节点组成的<strong>有向无环图（DAG）</strong>，节点编号从 <code>0</code> 到 <code>n - 1</code>，通过二维数组 <code>edges</code> 表示，其中 <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> 表示一条从节点 <code>u<sub>i</sub></code> 指向节点 <code>v<sub>i</sub></code> 的有向边。每个节点都有一个对应的&nbsp;<strong>得分&nbsp;</strong>，由数组 <code>score</code> 给出，其中 <code>score[i]</code> 表示节点 <code>i</code> 的得分。</p>

<p>你需要以&nbsp;<strong>有效的拓扑排序&nbsp;</strong>顺序处理这些节点。每个节点在处理顺序中被分配一个编号从 <strong>1</strong> 开始的位置。</p>

<p>将每个节点的得分乘以其在拓扑排序中的位置，然后求和，得到的值称为&nbsp;<strong>利润</strong>。</p>

<p>请返回在所有合法拓扑排序中可获得的&nbsp;<strong>最大利润&nbsp;</strong>。</p>

<p><strong>拓扑排序&nbsp;</strong>是一个对 DAG 中所有节点的线性排序，使得每条有向边 <code>u → v</code> 中，节点 <code>u</code> 都出现在 <code>v</code> 之前。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 2, edges = [[0,1]], score = [2,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">8</span></p>

<p><strong>解释：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3530.Maximum%20Profit%20from%20Valid%20Topological%20Order%20in%20DAG/images/1745660258-BXXGjv-screenshot-2025-03-11-at-021131.png" style="width: 200px; height: 89px;" /></p>

<p>节点 1 依赖于节点 0，因此一个合法顺序是 <code>[0, 1]</code>。</p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">节点</th>
			<th style="border: 1px solid black;">处理顺序</th>
			<th style="border: 1px solid black;">得分</th>
			<th style="border: 1px solid black;">乘数</th>
			<th style="border: 1px solid black;">利润计算</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">第 1 个</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">2 × 1 = 2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">第 2 个</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">3 × 2 = 6</td>
		</tr>
	</tbody>
</table>

<p>所有合法拓扑排序中可获得的最大总利润是 <code>2 + 6 = 8</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 3, edges = [[0,1],[0,2]], score = [1,6,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">25</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3530.Maximum%20Profit%20from%20Valid%20Topological%20Order%20in%20DAG/images/1745660268-mJrEKY-screenshot-2025-03-11-at-023558.png" style="width: 200px; height: 124px;" /></p>

<p>节点 1 和 2 都依赖于节点 0，因此最优的合法顺序是 <code>[0, 2, 1]</code>。</p>

<table style="border: 1px solid black;">
	<thead>
		<tr>
			<th style="border: 1px solid black;">节点</th>
			<th style="border: 1px solid black;">处理顺序</th>
			<th style="border: 1px solid black;">得分</th>
			<th style="border: 1px solid black;">乘数</th>
			<th style="border: 1px solid black;">利润计算</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">第 1 个</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1 × 1 = 1</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">第 2 个</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">3 × 2 = 6</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">第 3 个</td>
			<td style="border: 1px solid black;">6</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">6 × 3 = 18</td>
		</tr>
	</tbody>
</table>

<p>所有合法拓扑排序中可获得的最大总利润是 <code>1 + 6 + 18 = 25</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == score.length &lt;= 22</code></li>
	<li><code>1 &lt;= score[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= edges.length &lt;= n * (n - 1) / 2</code></li>
	<li><code>edges[i] == [u<sub>i</sub>, v<sub>i</sub>]</code> 表示一条从 <code>u<sub>i</sub></code> 到 <code>v<sub>i</sub></code> 的有向边。</li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt; n</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
	<li>输入图&nbsp;<strong>保证&nbsp;</strong>是一个 <strong>DAG</strong>。</li>
	<li>不存在重复的边。</li>
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
