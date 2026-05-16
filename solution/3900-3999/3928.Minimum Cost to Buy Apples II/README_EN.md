---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3928.Minimum%20Cost%20to%20Buy%20Apples%20II/README_EN.md
rating: 2186
source: Weekly Contest 501 Q4
---

<!-- problem:start -->

# [3928. Minimum Cost to Buy Apples II](https://leetcode.com/problems/minimum-cost-to-buy-apples-ii)

[中文文档](/solution/3900-3999/3928.Minimum%20Cost%20to%20Buy%20Apples%20II/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code> and an integer array <code>prices</code> of length <code>n</code>, where <code>prices[i]</code> is the price of apples at shop <code>i</code>.</p>

<p>You are also given a 2D integer array <code>roads</code>, where <code>roads[i] = [u<sub>i</sub>, v<sub>i</sub>, cost<sub>i</sub>, tax<sub>i</sub>]</code> represents a <strong>bidirectional</strong> road:</p>

<ul>
	<li><code>u<sub>i</sub></code> and <code>v<sub>i</sub></code> are the shops connected by the road.</li>
	<li><code>cost<sub>i</sub></code> is the cost to travel the road <strong>without</strong> carrying apples.</li>
	<li><code>tax<sub>i</sub></code> is the multiplier applied to <code>cost<sub>i</sub></code> when traveling <strong>with</strong> apples.</li>
</ul>

<p>For each shop <code>i</code>, you can either:</p>

<ul>
	<li>Buy apples locally at shop <code>i</code> for <code>prices[i]</code>.</li>
	<li>Travel <strong>empty</strong> to any shop <code>j</code> using <strong>any</strong> number of roads, buy apples for <code>prices[j]</code>, and return to shop <code>i</code> while carrying apples, paying <code>cost * tax</code> on each road used for the return trip.</li>
</ul>

<p>The forward path, where you travel empty, and the return path may be <strong>different</strong>.</p>

<p>Return an integer array <code>ans</code> of length <code>n</code>, where <code>ans[i]</code> is the <strong>minimum</strong> total cost to buy apples starting from shop <code>i</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 2, prices = [8,3], roads = [[0,1,1,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[6,3]</span></p>

<p><strong>Explanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3900-3999/3928.Minimum%20Cost%20to%20Buy%20Apples%20II/images/screenshot-2025-08-23-at-23341-am.png" style="width: 230px; height: 85px;" /></p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
	<thead>
		<tr>
			<th>Shop <code inline="">i</code></th>
			<th><code inline="">prices[i]</code></th>
			<th>Shop <code inline="">j</code></th>
			<th><code>prices[j]</code></th>
			<th><code inline="">cost<sub>i</sub></code></th>
			<th><code inline="">tax<sub>i</sub></code></th>
			<th>Travel cost</th>
			<th>Return cost</th>
			<th>Total</th>
			<th>Minimum</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>0</td>
			<td>8</td>
			<td>1</td>
			<td>3</td>
			<td>1</td>
			<td>2</td>
			<td>1</td>
			<td><code>1 * 2 = 2</code></td>
			<td><code>1 + 2 + 3 = 6</code></td>
			<td><code>min(8, 6) = 6</code></td>
		</tr>
		<tr>
			<td>1</td>
			<td>3</td>
			<td>0</td>
			<td>8</td>
			<td>1</td>
			<td>2</td>
			<td>1</td>
			<td><code>1 * 2 = 2</code></td>
			<td><code>1 + 2 + 8 = 11</code></td>
			<td><code>min(3, 11) = 3</code></td>
		</tr>
	</tbody>
</table>

<p>Thus, the answer is <code>[6, 3]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, prices = [9,4,6], roads = [[0,1,1,3],[1,2,4,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[8,4,6]</span></p>

<p><strong>Explanation:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3900-3999/3928.Minimum%20Cost%20to%20Buy%20Apples%20II/images/screenshot-2025-08-23-at-23736-am.png" style="width: 346px; height: 80px;" /><strong>​​​​​​​</strong></p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
	<thead>
		<tr>
			<th>Shop <code inline="">i</code></th>
			<th><code inline="">prices[i]</code></th>
			<th>Shop <code inline="">j</code></th>
			<th><code>prices[j]</code></th>
			<th><code inline="">cost<sub>i</sub></code></th>
			<th><code inline="">tax<sub>i</sub></code></th>
			<th>Travel cost</th>
			<th>Return cost</th>
			<th>Total</th>
			<th>Minimum</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>0</td>
			<td>9</td>
			<td>1</td>
			<td>4</td>
			<td>1</td>
			<td>3</td>
			<td>1</td>
			<td><code>1 * 3 = 3</code></td>
			<td><code>1 + 3 + 4 = 8</code></td>
			<td><code>min(9, 8) = 8</code></td>
		</tr>
		<tr>
			<td>1</td>
			<td>4</td>
			<td>2</td>
			<td>6</td>
			<td>4</td>
			<td>2</td>
			<td>4</td>
			<td><code>4 * 2 = 8</code></td>
			<td><code>4 + 8 + 6 = 18</code></td>
			<td><code>min(4, 18) = 4</code></td>
		</tr>
		<tr>
			<td>2</td>
			<td>6</td>
			<td>1</td>
			<td>4</td>
			<td>4</td>
			<td>2</td>
			<td>4</td>
			<td><code>4 * 2 = 8</code></td>
			<td><code>4 + 8 + 4 = 16</code></td>
			<td><code>min(6, 16) = 6</code></td>
		</tr>
	</tbody>
</table>

<p>Thus, the answer is <code>[8, 4, 6]</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, prices = [10,11,1], roads = [[0,2,1,3],[1,2,3,4],[0,1,5,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[5,11,1]</span></p>

<p><strong>Explanation:</strong></p>

<p><strong>​​​​​​​​​​​​​​</strong><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3900-3999/3928.Minimum%20Cost%20to%20Buy%20Apples%20II/images/screenshot-2025-08-23-at-24644-am.png" style="width: 250px; height: 181px;" /></p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
	<thead>
		<tr>
			<th>Shop <code inline="">i</code></th>
			<th><code inline="">prices[i]</code></th>
			<th>Shop <code inline="">j</code></th>
			<th><code>prices[j]</code></th>
			<th><code inline="">cost<sub>i</sub></code></th>
			<th><code inline="">tax<sub>i</sub></code></th>
			<th>Travel cost</th>
			<th>Return cost</th>
			<th>Total</th>
			<th>Minimum</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>0</td>
			<td>10</td>
			<td>2</td>
			<td>1</td>
			<td>1</td>
			<td>3</td>
			<td>1</td>
			<td><code>1 * 3 = 3</code></td>
			<td><code>1 + 3 + 1 = 5</code></td>
			<td><code>min(10, 5) = 5</code></td>
		</tr>
		<tr>
			<td>1</td>
			<td>11</td>
			<td>2</td>
			<td>1</td>
			<td>3</td>
			<td>4</td>
			<td>3</td>
			<td><code>3 * 4 = 12</code></td>
			<td><code>3 + 12 + 1 = 16</code></td>
			<td><code>min(11, 16) = 11</code></td>
		</tr>
		<tr>
			<td>2</td>
			<td>1</td>
			<td>0</td>
			<td>10</td>
			<td>1</td>
			<td>3</td>
			<td>1</td>
			<td><code>1 * 3 = 3</code></td>
			<td><code>1 + 3 + 10 = 14</code></td>
			<td><code>min(1, 14) = 1</code></td>
		</tr>
	</tbody>
</table>

<p>Thus, the answer is <code>[5, 11, 1]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>prices.length == n</code></li>
	<li><code>1 &lt;= prices[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= roads.length &lt;= min(n &times; (n - 1) / 2, 2000)</code></li>
	<li><code>roads[i] = [u<sub>i</sub>, v<sub>i</sub>, cost<sub>i</sub>, tax<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
	<li><code>1 &lt;= cost<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>​​​​​​​1 &lt;= tax<sub>​​​​​​​i</sub> &lt;= 100</code>​​​​​​​</li>
	<li>There are no <strong>repeated</strong> edges.</li>
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
