---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3125.Maximum%20Number%20That%20Makes%20Result%20of%20Bitwise%20AND%20Zero/README_EN.md
tags:
    - Greedy
    - String
    - Sorting
---

<!-- problem:start -->

# [3125. Maximum Number That Makes Result of Bitwise AND Zero 🔒](https://leetcode.com/problems/maximum-number-that-makes-result-of-bitwise-and-zero)

[中文文档](/solution/3100-3199/3125.Maximum%20Number%20That%20Makes%20Result%20of%20Bitwise%20AND%20Zero/README.md)

## Description

Given an integer <code>n</code>, return the <strong>maximum</strong> integer <code>x</code> such that <code>x &lt;= n</code>, and the bitwise <code>AND</code> of all the numbers in the range <code>[x, n]</code> is 0.

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 7</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>The bitwise <code>AND</code> of <code>[6, 7]</code> is 6.<br />
The bitwise <code>AND</code> of <code>[5, 6, 7]</code> is 4.<br />
The bitwise <code>AND</code> of <code>[4, 5, 6, 7]</code> is 4.<br />
The bitwise <code>AND</code> of <code>[3, 4, 5, 6, 7]</code> is 0.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 9</span></p>

<p><strong>Output:</strong> <span class="example-io">7</span></p>

<p><strong>Explanation:</strong></p>

<p>The bitwise <code>AND</code> of <code>[7, 8, 9]</code> is 0.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 17</span></p>

<p><strong>Output:</strong> <span class="example-io">15</span></p>

<p><strong>Explanation:</strong></p>

<p>The bitwise <code>AND</code> of <code>[15, 16, 17]</code> is 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>15</sup></code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Bit Manipulation

We can find the highest bit of $1$ in the binary representation of $n$. The maximum $x$ must be less than $n$ and this bit is $0$, and all other lower bits are $1$, i.e., $x = 2^{\text{number of the highest bit}} - 1$. This is because $x \text{ and } (x + 1) = 0$ must hold.

The time complexity is $O(\log n)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def maxNumber(self, n: int) -> int:
        return (1 << (n.bit_length() - 1)) - 1
```

```java
class Solution {
    public long maxNumber(long n) {
        return (1L << (63 - Long.numberOfLeadingZeros(n))) - 1;
    }
}
```

```cpp
class Solution {
public:
    long long maxNumber(long long n) {
        return (1LL << (63 - __builtin_clzll(n))) - 1;
    }
};
```

```go
func maxNumber(n int64) int64 {
	return int64(1<<(bits.Len64(uint64(n))-1)) - 1
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
