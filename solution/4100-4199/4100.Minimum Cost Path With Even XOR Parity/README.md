---
comments: true
difficulty: 困难
rating: 2281
source: 第 526 场周赛 Q4
---

<!-- problem:start -->

# [4100. 平衡异或路径的最小开销](https://leetcode.cn/problems/minimum-cost-path-with-even-xor-parity)

[English Version](/solution/4100-4199/4100.Minimum%20Cost%20Path%20With%20Even%20XOR%20Parity/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一棵含有 <code>n</code> 个节点的树，节点编号从 0 到 <code>n - 1</code>，用一个二维整数数组 <code>edges</code> 表示。<code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, c<sub>i</sub>]</code> 表示节点 <code>u<sub>i</sub></code> 和 <code>v<sub>i</sub></code> 之间的一条边，其开销为 <code>c<sub>i</sub></code>。</p>

<p>同时给你一个长度为 <code>n</code> 的整数数组 <code>values</code>，其中 <code>values[i]</code> 是节点 <code>i</code> 的值。</p>

<p>如果两个&nbsp;<strong>不同&nbsp;</strong>节点之间的路径上所有节点（包括两个端点）的值的按位异或结果在二进制表示中含有&nbsp;<strong>偶数&nbsp;</strong>个置位，则称该路径为 <strong>平衡异或</strong> 路径。</p>

<p>一条路径的&nbsp;<strong>开销&nbsp;</strong>是其所有边开销的总和。</p>

<p>返回一条平衡异或路径的&nbsp;<strong>最小开销&nbsp;</strong>。如果不存在这样的路径，返回 -1。</p>

<p><strong>置位&nbsp;</strong>是指一个数字的二进制表示中值为 1 的位。数字 0 含有零个置位。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4100-4199/4100.Minimum%20Cost%20Path%20With%20Even%20XOR%20Parity/images/4763e1ffdrawio.png" style="width: 300px; height: 300px;" /></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">edges = [[0,1,4],[0,2,6],[0,3,3],[3,4,5]], values = [3,1,7,1,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">7</span></p>

<p><strong>解释：</strong></p>
<table>
	<thead>
		<tr>
			<th style="padding: 5px; text-align: center;">平衡异或路径</th>
			<th style="padding: 5px; text-align: center;">异或值</th>
			<th style="padding: 5px; text-align: center;">总开销</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="padding: 5px; text-align: center;"><code>1 - 0 - 2</code></td>
			<td style="padding: 5px; text-align: center;"><code>1 XOR 3 XOR 7 = 5</code></td>
			<td style="padding: 5px; text-align: center;"><code>4 + 6 = 10</code></td>
		</tr>
		<tr>
			<td style="padding: 5px; text-align: center;"><code>1 - 0 - 3</code></td>
			<td style="padding: 5px; text-align: center;"><code>1 XOR 3 XOR 1 = 3</code></td>
			<td style="padding: 5px; text-align: center;"><code>4 + 3 = 7</code></td>
		</tr>
		<tr>
			<td style="padding: 5px; text-align: center;"><code>1 - 0 - 3 - 4</code></td>
			<td style="padding: 5px; text-align: center;"><code>1 XOR 3 XOR 1 XOR 3 = 0</code></td>
			<td style="padding: 5px; text-align: center;"><code>4 + 3 + 5 = 12</code></td>
		</tr>
		<tr>
			<td style="padding: 5px; text-align: center;"><code>2 - 0 - 3</code></td>
			<td style="padding: 5px; text-align: center;"><code>7 XOR 3 XOR 1 = 5</code></td>
			<td style="padding: 5px; text-align: center;"><code>6 + 3 = 9</code></td>
		</tr>
		<tr>
			<td style="padding: 5px; text-align: center;"><code>2 - 0 - 3 - 4</code></td>
			<td style="padding: 5px; text-align: center;"><code>7 XOR 3 XOR 1 XOR 3 = 6</code></td>
			<td style="padding: 5px; text-align: center;"><code>6 + 3 + 5 = 14</code></td>
		</tr>
	</tbody>
</table>
<p>最小开销为 7，由路径 <code>1 - 0 - 3</code> 达成。因此，答案为 7。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4100-4199/4100.Minimum%20Cost%20Path%20With%20Even%20XOR%20Parity/images/4763e2drawio.png" style="width: 300px; height: 132px;" /></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">edges = [[0,1,5],[1,2,10]], values = [1,7,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">5</span></p>

<p><strong>解释：</strong></p>

<table>
	<thead>
		<tr>
			<th style="padding: 5px; text-align: center;">平衡异或路径</th>
			<th style="padding: 5px; text-align: center;">异或值</th>
			<th style="padding: 5px; text-align: center;">总开销</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="padding: 5px; text-align: center;"><code>0 - 1</code></td>
			<td style="padding: 5px; text-align: center;"><code>1 XOR 7 = 6</code></td>
			<td style="padding: 5px; text-align: center;"><code>5</code></td>
		</tr>
		<tr>
			<td style="padding: 5px; text-align: center;"><code>0 - 1 - 2</code></td>
			<td style="padding: 5px; text-align: center;"><code>1 XOR 7 XOR 3 = 5</code></td>
			<td style="padding: 5px; text-align: center;"><code>5 + 10 = 15</code></td>
		</tr>
	</tbody>
</table>

<p>最小开销为 5，由路径 <code>0 - 1</code> 达成。因此，答案为 5。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4100-4199/4100.Minimum%20Cost%20Path%20With%20Even%20XOR%20Parity/images/4743e3drawio.png" style="width: 300px; height: 215px;" /></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">edges = [[0,1,5],[0,2,7],[0,3,9]], values = [1,3,5,9]</span></p>

<p><strong>输出：</strong> <span class="example-io">-1</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>任意两个不同节点之间的路径异或值在二进制下都含有奇数个置位。</li>
	<li>因此，不存在平衡异或路径，所以答案为 -1。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, c<sub>i</sub>]</code>
	<ul>
		<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n - 1</code></li>
		<li><code>1 &lt;= c<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	</ul>
	</li>
	<li><code>values.length == n</code></li>
	<li><code>1 &lt;= values[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>edges</code> 表示一棵合法的树。</li>
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
