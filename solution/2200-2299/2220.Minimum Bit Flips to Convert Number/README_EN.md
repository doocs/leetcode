---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2220.Minimum%20Bit%20Flips%20to%20Convert%20Number/README_EN.md
rating: 1282
source: Biweekly Contest 75 Q1
tags:
    - Bit Manipulation
---

<!-- problem:start -->

# [2220. Minimum Bit Flips to Convert Number](https://leetcode.com/problems/minimum-bit-flips-to-convert-number)

[中文文档](/solution/2200-2299/2220.Minimum%20Bit%20Flips%20to%20Convert%20Number/README.md)

## Description

<!-- description:start -->

<p>A <strong>bit flip</strong> of a number <code>x</code> is choosing a bit in the binary representation of <code>x</code> and <strong>flipping</strong> it from either <code>0</code> to <code>1</code> or <code>1</code> to <code>0</code>.</p>

<ul>
	<li>For example, for <code>x = 7</code>, the binary representation is <code>111</code> and we may choose any bit (including any leading zeros not shown) and flip it. We can flip the first bit from the right to get <code>110</code>, flip the second bit from the right to get <code>101</code>, flip the fifth bit from the right (a leading zero) to get <code>10111</code>, etc.</li>
</ul>

<p>Given two integers <code>start</code> and <code>goal</code>, return<em> the <strong>minimum</strong> number of <strong>bit flips</strong> to convert </em><code>start</code><em> to </em><code>goal</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> start = 10, goal = 7
<strong>Output:</strong> 3
<strong>Explanation:</strong> The binary representation of 10 and 7 are 1010 and 0111 respectively. We can convert 10 to 7 in 3 steps:
- Flip the first bit from the right: 101<u>0</u> -&gt; 101<u>1</u>.
- Flip the third bit from the right: 1<u>0</u>11 -&gt; 1<u>1</u>11.
- Flip the fourth bit from the right: <u>1</u>111 -&gt; <u>0</u>111.
It can be shown we cannot convert 10 to 7 in less than 3 steps. Hence, we return 3.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> start = 3, goal = 4
<strong>Output:</strong> 3
<strong>Explanation:</strong> The binary representation of 3 and 4 are 011 and 100 respectively. We can convert 3 to 4 in 3 steps:
- Flip the first bit from the right: 01<u>1</u> -&gt; 01<u>0</u>.
- Flip the second bit from the right: 0<u>1</u>0 -&gt; 0<u>0</u>0.
- Flip the third bit from the right: <u>0</u>00 -&gt; <u>1</u>00.
It can be shown we cannot convert 3 to 4 in less than 3 steps. Hence, we return 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= start, goal &lt;= 10<sup>9</sup></code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Note:</strong> This question is the same as <a href="https://leetcode.com/problems/hamming-distance/description/" target="_blank">461: Hamming Distance.</a></p>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Bit Manipulation

According to the problem description, we only need to count the number of 1s in the binary representation of $\textit{start} \oplus \textit{goal}$.

The time complexity is $O(\log n)$, where $n$ is the size of the integers in the problem. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minBitFlips(self, start: int, goal: int) -> int:
        return (start ^ goal).bit_count()
```

#### Java

```java
class Solution {
    public int minBitFlips(int start, int goal) {
        return Integer.bitCount(start ^ goal);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minBitFlips(int start, int goal) {
        return __builtin_popcount(start ^ goal);
    }
};
```

#### Go

```go
func minBitFlips(start int, goal int) int {
	return bits.OnesCount(uint(start ^ goal))
}
```

#### TypeScript

```ts
function minBitFlips(start: number, goal: number): number {
    return bitCount(start ^ goal);
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

#### Rust

```rust
impl Solution {
    pub fn min_bit_flips(start: i32, goal: i32) -> i32 {
        (start ^ goal).count_ones() as i32
    }
}
```

#### JavaScript

```js
/**
 * @param {number} start
 * @param {number} goal
 * @return {number}
 */
var minBitFlips = function (start, goal) {
    return bitCount(start ^ goal);
};

function bitCount(i) {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
```

#### C

```c
int minBitFlips(int start, int goal) {
    int x = start ^ goal;
    int ans = 0;
    while (x) {
        ans += (x & 1);
        x >>= 1;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
