---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3789.Minimum%20Cost%20to%20Acquire%20Required%20Items/README_EN.md
rating: 1579
source: Weekly Contest 482 Q2
tags:
    - Greedy
    - Math
---

<!-- problem:start -->

# [3789. Minimum Cost to Acquire Required Items](https://leetcode.com/problems/minimum-cost-to-acquire-required-items)

[中文文档](/solution/3700-3799/3789.Minimum%20Cost%20to%20Acquire%20Required%20Items/README.md)

## Description

<!-- description:start -->

<p>You are given five integers <code>cost1</code>, <code>cost2</code>, <code>costBoth</code>, <code>need1</code>, and <code>need2</code>.</p>

<p>There are three types of items available:</p>

<ul>
	<li>An item of <strong>type 1</strong> costs <code>cost1</code> and contributes 1 unit to the type 1 requirement only.</li>
	<li>An item of <strong>type 2</strong> costs <code>cost2</code> and contributes 1 unit to the type 2 requirement only.</li>
	<li>An item of <strong>type 3</strong> costs <code>costBoth</code> and contributes 1 unit to <strong>both</strong> type 1 and type 2 requirements.</li>
</ul>

<p>You must collect enough items so that the total contribution toward type 1 is <strong>at least</strong> <code>need1</code> and the total contribution toward type 2 is <strong>at least</strong> <code>need2</code>.</p>

<p>Return an integer representing the <strong>minimum</strong> possible total cost to achieve these requirements.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">cost1 = 3, cost2 = 2, costBoth = 1, need1 = 3, need2 = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>After buying three type 3 items, which cost <code>3 * 1 = 3</code>, the total contribution to type 1 is 3 (<code>&gt;= need1 = 3</code>) and to type 2 is 3 (<code>&gt;= need2 = 2</code>).<br data-end="229" data-start="226" />
Any other valid combination would cost more, so the minimum total cost is 3.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">cost1 = 5, cost2 = 4, costBoth = 15, need1 = 2, need2 = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">22</span></p>

<p><strong>Explanation:</strong></p>

<p>We buy <code>need1 = 2</code> items of type 1 and <code>need2 = 3</code> items of type 2: <code>2 * 5 + 3 * 4 = 10 + 12 = 22</code>.<br />
Any other valid combination would cost more, so the minimum total cost is 22.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">cost1 = 5, cost2 = 4, costBoth = 15, need1 = 0, need2 = 0</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>Since no items are required (<code>need1 = need2 = 0</code>), we buy nothing and pay 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= cost1, cost2, costBoth &lt;= 10<sup>6</sup></code></li>
	<li><code>0 &lt;= need1, need2 &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Case Analysis

We can divide the purchasing strategy into three cases:

1. Only buy Type 1 and Type 2 items. The total cost is $a = \textit{need1} \times \textit{cost1} + \textit{need2} \times \textit{cost2}$.
2. Only buy Type 3 items. The total cost is $b = \textit{costBoth} \times \max(\textit{need1}, \textit{need2})$.
3. Buy some Type 3 items, and purchase Type 1 and Type 2 items separately for the remaining needs. Let $\textit{mn} = \min(\textit{need1}, \textit{need2})$, then the total cost is $c = \textit{costBoth} \times \textit{mn} + (\textit{need1} - \textit{mn}) \times \textit{cost1} + (\textit{need2} - \textit{mn}) \times \textit{cost2}$.

Finally, we return the minimum value among the three cases, $\min(a, b, c)$.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumCost(
        self, cost1: int, cost2: int, costBoth: int, need1: int, need2: int
    ) -> int:
        a = need1 * cost1 + need2 * cost2
        b = costBoth * max(need1, need2)
        mn = min(need1, need2)
        c = costBoth * mn + (need1 - mn) * cost1 + (need2 - mn) * cost2
        return min(a, b, c)
```

#### Java

```java
class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long a = (long) need1 * cost1 + (long) need2 * cost2;
        long b = (long) costBoth * Math.max(need1, need2);
        int mn = Math.min(need1, need2);
        long c = (long) costBoth * mn + (long) (need1 - mn) * cost1 + (long) (need2 - mn) * cost2;
        return Math.min(a, Math.min(b, c));
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long long a = 1LL * need1 * cost1 + 1LL * need2 * cost2;
        long long b = 1LL * costBoth * max(need1, need2);
        int mn = min(need1, need2);
        long long c = 1LL * costBoth * mn
            + 1LL * (need1 - mn) * cost1
            + 1LL * (need2 - mn) * cost2;
        return min({a, b, c});
    }
};
```

#### Go

```go
func minimumCost(cost1 int, cost2 int, costBoth int, need1 int, need2 int) int64 {
	a := int64(need1)*int64(cost1) + int64(need2)*int64(cost2)
	b := int64(costBoth) * int64(max(need1, need2))
	mn := min(need1, need2)
	c := int64(costBoth)*int64(mn) +
		int64(need1-mn)*int64(cost1) +
		int64(need2-mn)*int64(cost2)
	return min(a, min(b, c))
}
```

#### TypeScript

```ts
function minimumCost(
    cost1: number,
    cost2: number,
    costBoth: number,
    need1: number,
    need2: number,
): number {
    const a = need1 * cost1 + need2 * cost2;
    const b = costBoth * Math.max(need1, need2);
    const mn = Math.min(need1, need2);
    const c = costBoth * mn + (need1 - mn) * cost1 + (need2 - mn) * cost2;
    return Math.min(a, b, c);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
