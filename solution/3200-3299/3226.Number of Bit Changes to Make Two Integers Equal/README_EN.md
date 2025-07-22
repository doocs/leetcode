---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3226.Number%20of%20Bit%20Changes%20to%20Make%20Two%20Integers%20Equal/README_EN.md
rating: 1247
source: Weekly Contest 407 Q1
tags:
    - Bit Manipulation
---

<!-- problem:start -->

# [3226. Number of Bit Changes to Make Two Integers Equal](https://leetcode.com/problems/number-of-bit-changes-to-make-two-integers-equal)

[中文文档](/solution/3200-3299/3226.Number%20of%20Bit%20Changes%20to%20Make%20Two%20Integers%20Equal/README.md)

## Description

<!-- description:start -->

<p>You are given two positive integers <code>n</code> and <code>k</code>.</p>

<p>You can choose <strong>any</strong> bit in the <strong>binary representation</strong> of <code>n</code> that is equal to 1 and change it to 0.</p>

<p>Return the <em>number of changes</em> needed to make <code>n</code> equal to <code>k</code>. If it is impossible, return -1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 13, k = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong><br />
Initially, the binary representations of <code>n</code> and <code>k</code> are <code>n = (1101)<sub>2</sub></code> and <code>k = (0100)<sub>2</sub></code>.<br />
We can change the first and fourth bits of <code>n</code>. The resulting integer is <code>n = (<u><strong>0</strong></u>10<u><strong>0</strong></u>)<sub>2</sub> = k</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 21, k = 21</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong><br />
<code>n</code> and <code>k</code> are already equal, so no changes are needed.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 14, k = 13</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong><br />
It is not possible to make <code>n</code> equal to <code>k</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n, k &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Bit Manipulation

If the bitwise AND result of $n$ and $k$ is not equal to $k$, it indicates that there exists at least one bit where $k$ is $1$ and the corresponding bit in $n$ is $0$. In this case, it is impossible to modify a bit in $n$ to make $n$ equal to $k$, and we return $-1$. Otherwise, we count the number of $1$s in the binary representation of $n \oplus k$.

The time complexity is $O(\log n)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minChanges(self, n: int, k: int) -> int:
        return -1 if n & k != k else (n ^ k).bit_count()
```

#### Java

```java
class Solution {
    public int minChanges(int n, int k) {
        return (n & k) != k ? -1 : Integer.bitCount(n ^ k);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minChanges(int n, int k) {
        return (n & k) != k ? -1 : __builtin_popcount(n ^ k);
    }
};
```

#### Go

```go
func minChanges(n int, k int) int {
	if n&k != k {
		return -1
	}
	return bits.OnesCount(uint(n ^ k))
}
```

#### TypeScript

```ts
function minChanges(n: number, k: number): number {
    return (n & k) !== k ? -1 : bitCount(n ^ k);
}

function bitCount(i: number): number {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
