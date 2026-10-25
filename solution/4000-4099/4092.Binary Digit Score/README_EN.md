---
comments: true
difficulty: Easy
---

<!-- problem:start -->

# [4092. Binary Digit Score](https://leetcode.com/problems/binary-digit-score)

[中文文档](/solution/4000-4099/4092.Binary%20Digit%20Score/README.md)

## Description

<!-- description:start -->

<p>You are given two integers <code>a</code> and <code>b</code>.</p>

<p>Let <code>s1</code> and <code>s2</code> be the number of <strong>set bits</strong> in the binary representations of <code>a</code> and <code>b</code>, respectively.</p>

<p>The <strong>score</strong> of these integers is defined as follows:</p>

<ul>
	<li>If <code>s1 &gt;= s2</code>, the score is <code>s1 * s2</code>.</li>
	<li>Otherwise, the score is <code>s2 / s1</code> using integer division.</li>
</ul>

<p>Return an integer representing the score of <code>a</code> and <code>b</code>.</p>

<p>A <strong>set bit</strong> refers to a bit in the binary representation of a number that has a value of 1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">a = 14, b = 6</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The binary representation of <code>a = 14</code> is <code>&quot;1110&quot;</code>, so <code>s1 = 3</code>.</li>
	<li>The binary representation of <code>b = 6</code> is <code>&quot;110&quot;</code>, so <code>s2 = 2</code>.</li>
	<li>Since <code>s1 &gt; s2</code>, the score of <code>a</code> and <code>b</code> is <code>s1 * s2 = 3 * 2 = 6</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">a = 5, b = 15</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The binary representation of <code>a = 5</code> is <code>&quot;101&quot;</code>, so <code>s1 = 2</code>.</li>
	<li>The binary representation of <code>b = 15</code> is <code>&quot;1111&quot;</code>, so <code>s2 = 4</code>.</li>
	<li>Since <code>s1 &lt; s2</code>, the score of <code>a</code> and <code>b</code> is <code>s2 / s1 = 4 / 2 = 2</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">a = 10, b = 12</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The binary representation of <code>a = 10</code> is <code>&quot;1010&quot;</code>, so <code>s1 = 2</code>.</li>
	<li>The binary representation of <code>b = 12</code> is <code>&quot;1100&quot;</code>, so <code>s2 = 2</code>.</li>
	<li>Since <code>s1 == s2</code>, the score of <code>a</code> and <code>b</code> is <code>s1 * s2 = 2 * 2 = 4</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= a, b &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Bit Count

<!-- thinking:start -->

> **Thinking**
>
> Both $a$ and $b$ are at most $100$, so the number of $1$s in their binary representations can be counted directly. The score depends only on how those two popcounts compare.
>
> When $s_1\ge s_2$ the score is the product; otherwise it is the integer quotient of $s_2$ divided by $s_1$. Both inputs are at least $1$, so $s_1$ is never $0$.
>
> Count the set bits of each number once, then return according to that comparison.

<!-- thinking:end -->

Let $s_1$ and $s_2$ be the number of $1$s in the binary representations of $a$ and $b$. If $s_1\ge s_2$, the score is $s_1\cdot s_2$. Otherwise it is the integer quotient $s_2/s_1$.

The time complexity is $O(1)$ and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def calculateScore(self, a: int, b: int) -> int:
        s1 = a.bit_count()
        s2 = b.bit_count()
        return s1 * s2 if s1 >= s2 else s2 // s1
```

#### Java

```java
class Solution {
    public int calculateScore(int a, int b) {
        int s1 = Integer.bitCount(a);
        int s2 = Integer.bitCount(b);
        return s1 >= s2 ? s1 * s2 : s2 / s1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int calculateScore(int a, int b) {
        int s1 = __builtin_popcount(a);
        int s2 = __builtin_popcount(b);
        return s1 >= s2 ? s1 * s2 : s2 / s1;
    }
};
```

#### Go

```go
func calculateScore(a int, b int) int {
	s1 := bits.OnesCount(uint(a))
	s2 := bits.OnesCount(uint(b))
	if s1 >= s2 {
		return s1 * s2
	}
	return s2 / s1
}
```

#### TypeScript

```ts
function calculateScore(a: number, b: number): number {
    const s1 = a.toString(2).replace(/0/g, '').length;
    const s2 = b.toString(2).replace(/0/g, '').length;
    return s1 >= s2 ? s1 * s2 : Math.floor(s2 / s1);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
