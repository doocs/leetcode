---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3064.Guess%20the%20Number%20Using%20Bitwise%20Questions%20I/README_EN.md
tags:
    - Bit Manipulation
    - Interactive
---

# [3064. Guess the Number Using Bitwise Questions I 🔒](https://leetcode.com/problems/guess-the-number-using-bitwise-questions-i)

[中文文档](/solution/3000-3099/3064.Guess%20the%20Number%20Using%20Bitwise%20Questions%20I/README.md)

## Description

<p>There is a number <code>n</code> that you have to find.</p>

<p>There is also a pre-defined API <code>int commonSetBits(int num)</code>, which returns the number of bits where both <code>n</code> and <code>num</code> are <code>1</code> in that position of their binary representation. In other words, it returns the number of <span data-keyword="set-bit">set bits</span> in <code>n &amp; num</code>, where <code>&amp;</code> is the bitwise <code>AND</code> operator.</p>

<p>Return <em>the number</em> <code>n</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1: </strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input: </strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> n = 31 </span></p>

<p><strong>Output: </strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> 31 </span></p>

<p><strong>Explanation: </strong> It can be proven that it&#39;s possible to find <code>31</code> using the provided API.</p>
</div>

<p><strong class="example">Example 2: </strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input: </strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> n = 33 </span></p>

<p><strong>Output: </strong> <span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;"> 33 </span></p>

<p><strong>Explanation: </strong> It can be proven that it&#39;s possible to find <code>33</code> using the provided API.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2<sup>30</sup> - 1</code></li>
	<li><code>0 &lt;= num &lt;= 2<sup>30</sup> - 1</code></li>
	<li>If you ask for some <code>num</code> out of the given range, the output wouldn&#39;t be reliable.</li>
</ul>

## Solutions

### Solution 1: Enumeration

We can enumerate the powers of 2, and then call the `commonSetBits` method. If the return value is greater than 0, it means that the corresponding bit in the binary representation of `n` is 1.

The time complexity is $O(\log n)$, where $n \le 2^{30}$ in this problem. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
# Definition of commonSetBits API.
# def commonSetBits(num: int) -> int:


class Solution:
    def findNumber(self) -> int:
        return sum(1 << i for i in range(32) if commonSetBits(1 << i))
```

```java
/**
 * Definition of commonSetBits API (defined in the parent class Problem).
 * int commonSetBits(int num);
 */

public class Solution extends Problem {
    public int findNumber() {
        int n = 0;
        for (int i = 0; i < 32; ++i) {
            if (commonSetBits(1 << i) > 0) {
                n |= 1 << i;
            }
        }
        return n;
    }
}
```

```cpp
/**
 * Definition of commonSetBits API.
 * int commonSetBits(int num);
 */

class Solution {
public:
    int findNumber() {
        int n = 0;
        for (int i = 0; i < 32; ++i) {
            if (commonSetBits(1 << i)) {
                n |= 1 << i;
            }
        }
        return n;
    }
};
```

```go
/**
 * Definition of commonSetBits API.
 * func commonSetBits(num int) int;
 */

func findNumber() (n int) {
	for i := 0; i < 32; i++ {
		if commonSetBits(1<<i) > 0 {
			n |= 1 << i
		}
	}
	return
}
```

```ts
/**
 * Definition of commonSetBits API.
 * var commonSetBits = function(num: number): number {}
 */

function findNumber(): number {
    let n = 0;
    for (let i = 0; i < 32; ++i) {
        if (commonSetBits(1 << i)) {
            n |= 1 << i;
        }
    }
    return n;
}
```

<!-- tabs:end -->

<!-- end -->
