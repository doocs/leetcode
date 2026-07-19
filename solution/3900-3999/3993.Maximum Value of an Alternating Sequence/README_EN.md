---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3993.Maximum%20Value%20of%20an%20Alternating%20Sequence/README_EN.md
---

<!-- problem:start -->

# [3993. Maximum Value of an Alternating Sequence](https://leetcode.com/problems/maximum-value-of-an-alternating-sequence)

[中文文档](/solution/3900-3999/3993.Maximum%20Value%20of%20an%20Alternating%20Sequence/README.md)

## Description

<!-- description:start -->

<p>You are given three integers <code>n</code>, <code>s</code>, and <code>m</code>.</p>

<p>A sequence <code>seq</code> of integers of length <code>n</code> is considered <strong>valid</strong> if:</p>

<ul>
	<li><code>seq[0] = s</code>.</li>
	<li>The sequence is <strong>alternating</strong>, meaning that either:
	<ul>
		<li><code>seq[0] &gt; seq[1] &lt; seq[2] &gt; ...</code>, or</li>
		<li><code>seq[0] &lt; seq[1] &gt; seq[2] &lt; ...</code>.</li>
	</ul>
	</li>
	<li>For every adjacent pair, <code>|seq[i] - seq[i - 1]| &lt;= m</code>.</li>
</ul>

<p>A sequence of length 1 is considered alternating.</p>

<p>Return the <strong>maximum</strong> possible element that can appear in any valid sequence.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, s = 3, m = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">12</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>One valid sequence is <code>[3, 8, 7, 12]</code>.</li>
	<li>The maximum element in the sequence is 12.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 2, s = 4, m = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">7</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>One valid sequence is <code>[4, 7]</code>.</li>
	<li>The maximum element in the sequence is 7.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n, s &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= m &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy

If $n = 1$, the sequence contains only the starting value $s$, so the answer is $s$.

Otherwise, the sequence length is at least $2$. Since the absolute difference between adjacent elements is at most $m$, and the sequence must strictly alternate up and down, to maximize some element we should repeatedly "rise by $m$, then fall by $1$": the fall step is taken as the minimum value $1$ so that the next rise has the largest possible room.

Construct the sequence in a "rise first" pattern:

$$
s,\ s+m,\ s+m-1,\ s+2m-1,\ s+2m-2,\ \ldots
$$

With length $n$, we can complete $\lfloor n / 2 \rfloor$ rises, and the peak after the $k$-th rise is $s + k(m - 1) + 1$. Therefore, the maximum element is:

$$
s + \left\lfloor \frac{n}{2} \right\rfloor (m - 1) + 1
$$

Starting with a fall only decreases the values first and cannot produce a larger peak, so the construction above is optimal.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumValue(self, n: int, s: int, m: int) -> int:
        if n == 1:
            return s
        return s + n // 2 * (m - 1) + 1
```

#### Java

```java
class Solution {
    public long maximumValue(int n, int s, int m) {
        if (n == 1) {
            return s;
        }
        return (long) s + (long) (n / 2) * (m - 1) + 1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maximumValue(int n, int s, int m) {
        if (n == 1) {
            return s;
        }
        return 1LL * s + 1LL * (n / 2) * (m - 1) + 1;
    }
};
```

#### Go

```go
func maximumValue(n int, s int, m int) int64 {
	if n == 1 {
		return int64(s)
	}
	return int64(s) + int64(n/2)*int64(m-1) + 1
}
```

#### TypeScript

```ts
function maximumValue(n: number, s: number, m: number): number {
    if (n === 1) {
        return s;
    }
    return s + Math.floor(n / 2) * (m - 1) + 1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
