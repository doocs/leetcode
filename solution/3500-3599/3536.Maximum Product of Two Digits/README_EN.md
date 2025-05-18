---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3536.Maximum%20Product%20of%20Two%20Digits/README_EN.md
tags:
    - Math
    - Sorting
---

<!-- problem:start -->

# [3536. Maximum Product of Two Digits](https://leetcode.com/problems/maximum-product-of-two-digits)

[中文文档](/solution/3500-3599/3536.Maximum%20Product%20of%20Two%20Digits/README.md)

## Description

<!-- description:start -->

<p>You are given a positive integer <code>n</code>.</p>

<p>Return the <strong>maximum</strong> product of any two digits in <code>n</code>.</p>

<p><strong>Note:</strong> You may use the <strong>same</strong> digit twice if it appears more than once in <code>n</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 31</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The digits of <code>n</code> are <code>[3, 1]</code>.</li>
	<li>The possible products of any two digits are: <code>3 * 1 = 3</code>.</li>
	<li>The maximum product is 3.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 22</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The digits of <code>n</code> are <code>[2, 2]</code>.</li>
	<li>The possible products of any two digits are: <code>2 * 2 = 4</code>.</li>
	<li>The maximum product is 4.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 124</span></p>

<p><strong>Output:</strong> <span class="example-io">8</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The digits of <code>n</code> are <code>[1, 2, 4]</code>.</li>
	<li>The possible products of any two digits are: <code>1 * 2 = 2</code>, <code>1 * 4 = 4</code>, <code>2 * 4 = 8</code>.</li>
	<li>The maximum product is 8.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>10 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Find the Largest and Second Largest Digits

We keep two variables, $a$ and $b$, to record the current largest and second‑largest digits, respectively. We iterate over every digit of $n$; if the current digit is larger than $a$, we assign $b$ the value of $a$ and then set $a$ to the current digit. Otherwise, if the current digit is larger than $b$, we set $b$ to the current digit. Finally, we return $a \times b$.

The time complexity is $O(\log n)$, where $n$ is the input number, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxProduct(self, n: int) -> int:
        a = b = 0
        while n:
            n, x = divmod(n, 10)
            if a < x:
                a, b = x, a
            elif b < x:
                b = x
        return a * b
```

#### Java

```java
class Solution {
    public int maxProduct(int n) {
        int a = 0, b = 0;
        for (; n > 0; n /= 10) {
            int x = n % 10;
            if (a < x) {
                b = a;
                a = x;
            } else if (b < x) {
                b = x;
            }
        }
        return a * b;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxProduct(int n) {
        int a = 0, b = 0;
        for (; n; n /= 10) {
            int x = n % 10;
            if (a < x) {
                b = a;
                a = x;
            } else if (b < x) {
                b = x;
            }
        }
        return a * b;
    }
};
```

#### Go

```go
func maxProduct(n int) int {
	a, b := 0, 0
	for ; n > 0; n /= 10 {
		x := n % 10
		if a < x {
			b, a = a, x
		} else if b < x {
			b = x
		}
	}
	return a * b
}
```

#### TypeScript

```ts
function maxProduct(n: number): number {
    let [a, b] = [0, 0];
    for (; n; n = Math.floor(n / 10)) {
        const x = n % 10;
        if (a < x) {
            [a, b] = [x, a];
        } else if (b < x) {
            b = x;
        }
    }
    return a * b;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
