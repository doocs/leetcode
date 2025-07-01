---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3596.Minimum%20Cost%20Path%20with%20Alternating%20Directions%20I/README_EN.md
tags:
    - Brainteaser
    - Math
---

<!-- problem:start -->

# [3596. Minimum Cost Path with Alternating Directions I 🔒](https://leetcode.com/problems/minimum-cost-path-with-alternating-directions-i)

[中文文档](/solution/3500-3599/3596.Minimum%20Cost%20Path%20with%20Alternating%20Directions%20I/README.md)

## Description

<!-- description:start -->

<p>You are given two integers <code>m</code> and <code>n</code> representing the number of rows and columns of a grid, respectively.</p>

<p>The cost to enter cell <code>(i, j)</code> is defined as <code>(i + 1) * (j + 1)</code>.</p>

<p>You start at cell <code>(0, 0)</code> on move 1.</p>

<p>At each step, you move to an <strong>adjacent</strong> cell, following an alternating pattern:</p>

<ul>
	<li>On <strong>odd-numbered</strong> moves, you must move either <strong>right</strong> or <strong>down</strong>.</li>
	<li>On <strong>even-numbered</strong> moves, you must move either<strong> left</strong> or <strong>up</strong>.</li>
</ul>

<p>Return the <strong>minimum</strong> total cost required to reach <code>(m - 1, n - 1)</code>. If it is impossible, return -1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">m = 1, n = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>You start at cell <code>(0, 0)</code>.</li>
	<li>The cost to enter <code>(0, 0)</code> is <code>(0 + 1) * (0 + 1) = 1</code>.</li>
	<li>Since you&#39;re at the destination, the total cost is 1.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">m = 2, n = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>You start at cell <code>(0, 0)</code> with cost <code>(0 + 1) * (0 + 1) = 1</code>.</li>
	<li>Move 1 (odd): You can move down to <code>(1, 0)</code> with cost <code>(1 + 1) * (0 + 1) = 2</code>.</li>
	<li>Thus, the total cost is <code>1 + 2 = 3</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m, n &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Brain Teaser

Due to the movement rules given in the problem, in fact, only the following three cases can reach the target cell:

1. A $1 \times 1$ grid, with a cost of $1$.
2. A grid with $2$ rows and $1$ column, with a cost of $3$.
3. A grid with $1$ row and $2$ columns, with a cost of $3$.

For all other cases, it is impossible to reach the target cell, so return $-1$.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minCost(self, m: int, n: int) -> int:
        if m == 1 and n == 1:
            return 1
        if m == 2 and n == 1:
            return 3
        if m == 1 and n == 2:
            return 3
        return -1
```

#### Java

```java
class Solution {
    public int minCost(int m, int n) {
        if (m == 1 && n == 1) {
            return 1;
        }
        if (m == 1 && n == 2) {
            return 3;
        }
        if (m == 2 && n == 1) {
            return 3;
        }
        return -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minCost(int m, int n) {
        if (m == 1 && n == 1) {
            return 1;
        }
        if (m == 1 && n == 2) {
            return 3;
        }
        if (m == 2 && n == 1) {
            return 3;
        }
        return -1;
    }
};
```

#### Go

```go
func minCost(m int, n int) int {
	if m == 1 && n == 1 {
		return 1
	}
	if m == 1 && n == 2 {
		return 3
	}
	if m == 2 && n == 1 {
		return 3
	}
	return -1
}
```

#### TypeScript

```ts
function minCost(m: number, n: number): number {
    if (m === 1 && n === 1) {
        return 1;
    }
    if (m === 1 && n === 2) {
        return 3;
    }
    if (m === 2 && n === 1) {
        return 3;
    }
    return -1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
