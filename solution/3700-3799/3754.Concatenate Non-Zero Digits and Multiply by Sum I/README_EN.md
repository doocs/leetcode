---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3754.Concatenate%20Non-Zero%20Digits%20and%20Multiply%20by%20Sum%20I/README_EN.md
rating: 1247
source: Weekly Contest 477 Q1
tags:
    - Math
---

<!-- problem:start -->

# [3754. Concatenate Non-Zero Digits and Multiply by Sum I](https://leetcode.com/problems/concatenate-non-zero-digits-and-multiply-by-sum-i)

[中文文档](/solution/3700-3799/3754.Concatenate%20Non-Zero%20Digits%20and%20Multiply%20by%20Sum%20I/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code>.</p>

<p>Form a new integer <code>x</code> by concatenating all the <strong>non-zero digits</strong> of <code>n</code> in their original order. If there are no <strong>non-zero</strong> digits, <code>x = 0</code>.</p>

<p>Let <code>sum</code> be the <strong>sum of digits</strong> in <code>x</code>.</p>

<p>Return an integer representing the value of <code>x * sum</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 10203004</span></p>

<p><strong>Output:</strong> <span class="example-io">12340</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The non-zero digits are 1, 2, 3, and 4. Thus, <code>x = 1234</code>.</li>
	<li>The sum of digits is <code>sum = 1 + 2 + 3 + 4 = 10</code>.</li>
	<li>Therefore, the answer is <code>x * sum = 1234 * 10 = 12340</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 1000</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The non-zero digit is 1, so <code>x = 1</code> and <code>sum = 1</code>.</li>
	<li>Therefore, the answer is <code>x * sum = 1 * 1 = 1</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We can simulate the required operation by processing the number digit by digit. While processing each digit, we concatenate non-zero digits to form a new integer $x$ and calculate the digit sum $s$. Finally, we return $x \times s$.

The time complexity is $O(\log n)$ and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sumAndMultiply(self, n: int) -> int:
        p = 1
        x = s = 0
        while n:
            v = n % 10
            s += v
            if v:
                x += p * v
                p *= 10
            n //= 10
        return x * s
```

#### Java

```java
class Solution {
    public long sumAndMultiply(int n) {
        int p = 1;
        int x = 0, s = 0;
        for (; n > 0; n /= 10) {
            int v = n % 10;
            s += v;
            if (v != 0) {
                x += p * v;
                p *= 10;
            }
        }
        return 1L * x * s;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long sumAndMultiply(int n) {
        int p = 1;
        int x = 0, s = 0;
        for (; n > 0; n /= 10) {
            int v = n % 10;
            s += v;
            if (v != 0) {
                x += p * v;
                p *= 10;
            }
        }
        return 1LL * x * s;
    }
};
```

#### Go

```go
func sumAndMultiply(n int) int64 {
	p := 1
	x := 0
	s := 0
	for n > 0 {
		v := n % 10
		s += v
		if v != 0 {
			x += p * v
			p *= 10
		}
		n /= 10
	}
	return int64(x) * int64(s)
}
```

#### TypeScript

```ts
function sumAndMultiply(n: number): number {
    let p = 1;
    let x = 0;
    let s = 0;

    while (n > 0) {
        const v = n % 10;
        s += v;
        if (v !== 0) {
            x += p * v;
            p *= 10;
        }
        n = Math.floor(n / 10);
    }

    return x * s;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
