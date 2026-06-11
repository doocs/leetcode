---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3958.Minimum%20Cost%20to%20Split%20into%20Ones%20II/README_EN.md
---

<!-- problem:start -->

# [3958. Minimum Cost to Split into Ones II 🔒](https://leetcode.com/problems/minimum-cost-to-split-into-ones-ii)

[中文文档](/solution/3900-3999/3958.Minimum%20Cost%20to%20Split%20into%20Ones%20II/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code>.</p>

<p>In one operation, you may split an integer <code>x</code> into two positive integers <code>a</code> and <code>b</code> such that <code>a + b = x</code>.</p>

<p>The cost of this operation is <code>a * b</code>.</p>

<p>Return the <strong>minimum</strong> total cost required to split the integer <code>n</code> into <code>n</code> ones.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal set of operations is:</p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
	<tbody>
		<tr>
			<th><code>x</code></th>
			<th><code>a</code></th>
			<th><code>b</code></th>
			<th><code>a + b</code></th>
			<th><code>a * b</code></th>
			<th>Cost</th>
		</tr>
		<tr>
			<td>3</td>
			<td>1</td>
			<td>2</td>
			<td>3</td>
			<td>2</td>
			<td>2</td>
		</tr>
		<tr>
			<td>2</td>
			<td>1</td>
			<td>1</td>
			<td>2</td>
			<td>1</td>
			<td>1</td>
		</tr>
	</tbody>
</table>

<p>Thus, the minimum total cost is <code>2 + 1 = 3</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:​​​​​​​</strong></p>

<p>One optimal set of operations is:</p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
	<tbody>
		<tr>
			<th><code>x</code></th>
			<th><code>a</code></th>
			<th><code>b</code></th>
			<th><code>a + b</code></th>
			<th><code>a * b</code></th>
			<th>Cost</th>
		</tr>
		<tr>
			<td>4</td>
			<td>2</td>
			<td>2</td>
			<td>4</td>
			<td>4</td>
			<td>4</td>
		</tr>
		<tr>
			<td>2</td>
			<td>1</td>
			<td>1</td>
			<td>2</td>
			<td>1</td>
			<td>1</td>
		</tr>
	</tbody>
</table>

<p>Thus, the minimum total cost is <code>4 + 1 + 1 = 6</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>7</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Mathematics

To minimize the cost, we should first split $n$ into $1$ and $n - 1$, which costs $n - 1$; then split $n - 1$ into $1$ and $n - 2$, which costs $n - 2$. Following this pattern, the total cost is accumulated as $1 + 2 + \dots + (n - 1) = \frac{n \times (n - 1)}{2}$.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minCost(self, n: int) -> int:
        return n * (n - 1) // 2
```

#### Java

```java
class Solution {
    public long minCost(int n) {
        return 1L * n * (n - 1) / 2;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long minCost(int n) {
        return 1LL * n * (n - 1) / 2;
    }
};
```

#### Go

```go
func minCost(n int) int64 {
	return int64(n * (n - 1) / 2)
}
```

#### TypeScript

```ts
function minCost(n: number): number {
    return (n * (n - 1)) / 2;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
