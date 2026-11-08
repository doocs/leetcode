---
comments: true
difficulty: Hard
rating: 2281
source: Weekly Contest 526 Q4
---

<!-- problem:start -->

# [4100. Minimum Cost Path With Even XOR Parity](https://leetcode.com/problems/minimum-cost-path-with-even-xor-parity)

[中文文档](/solution/4100-4199/4100.Minimum%20Cost%20Path%20With%20Even%20XOR%20Parity/README.md)

## Description

<!-- description:start -->

<p>You are given a tree with <code>n</code> nodes numbered from 0 to <code>n - 1</code>, represented by a 2D integer array <code>edges</code>. Each <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, c<sub>i</sub>]</code> represents an edge between nodes <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code> with cost <code>c<sub>i</sub></code>.</p>

<p>You are also given an integer array <code>values</code> of length <code>n</code>, where <code>values[i]</code> is the value of node <code>i</code>.</p>

<p>A path between two <strong>distinct</strong> nodes is <strong>Balanced-XOR</strong> if the bitwise XOR of the values of all nodes on the path, including both endpoints, has an <strong>even</strong> number of <span data-keyword="set-bit">set bits</span>.</p>

<p>The <strong>cost</strong> of a path is the sum of its edge costs.</p>

<p>Return the <strong>minimum cost</strong> of a Balanced-XOR path. If no such path exists, return -1.</p>

<p>The number 0 has zero set bits.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4100-4199/4100.Minimum%20Cost%20Path%20With%20Even%20XOR%20Parity/images/4763e1ffdrawio.png" style="width: 300px; height: 300px;" /></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">edges = [[0,1,4],[0,2,6],[0,3,3],[3,4,5]], values = [3,1,7,1,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">7</span></p>

<p><strong>Explanation:</strong></p>

<table>
	<thead>
		<tr>
			<th style="padding: 5px; text-align: center;">Balanced-XOR Path</th>
			<th style="padding: 5px; text-align: center;">XOR Value</th>
			<th style="padding: 5px; text-align: center;">Total Cost</th>
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

<p>The minimum cost is 7, achieved by the path <code>1 - 0 - 3</code>. Thus, the answer is 7.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4100-4199/4100.Minimum%20Cost%20Path%20With%20Even%20XOR%20Parity/images/4763e2drawio.png" style="width: 300px; height: 132px;" /></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">edges = [[0,1,5],[1,2,10]], values = [1,7,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<table>
	<thead>
		<tr>
			<th style="padding: 5px; text-align: center;">Balanced-XOR Path</th>
			<th style="padding: 5px; text-align: center;">XOR Value</th>
			<th style="padding: 5px; text-align: center;">Total Cost</th>
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

<p>The minimum cost is 5, achieved by the path <code>0 - 1</code>. Thus, the answer is 5.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4100-4199/4100.Minimum%20Cost%20Path%20With%20Even%20XOR%20Parity/images/4743e3drawio.png" style="width: 300px; height: 215px;" /></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">edges = [[0,1,5],[0,2,7],[0,3,9]], values = [1,3,5,9]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Every path between two distinct nodes has an XOR value with an odd number of set bits.</li>
	<li>Therefore, no Balanced-XOR path exists, so the answer is -1.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, c<sub>i</sub>​​​​​​​]</code>
	<ul>
		<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n - 1</code></li>
		<li><code>1 &lt;= c<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	</ul>
	</li>
	<li><code>values.length == n</code></li>
	<li><code>1 &lt;= values[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>edges</code> represents a valid tree.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

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
