---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3345.Smallest%20Divisible%20Digit%20Product%20I/README_EN.md
tags:
    - Math
    - Enumeration
---

<!-- problem:start -->

# [3345. Smallest Divisible Digit Product I](https://leetcode.com/problems/smallest-divisible-digit-product-i)

[中文文档](/solution/3300-3399/3345.Smallest%20Divisible%20Digit%20Product%20I/README.md)

## Description

<!-- description:start -->

<p>You are given two integers <code>n</code> and <code>t</code>. Return the <strong>smallest</strong> number greater than or equal to <code>n</code> such that the <strong>product of its digits</strong> is divisible by <code>t</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 10, t = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">10</span></p>

<p><strong>Explanation:</strong></p>

<p>The digit product of 10 is 0, which is divisible by 2, making it the smallest number greater than or equal to 10 that satisfies the condition.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 15, t = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">16</span></p>

<p><strong>Explanation:</strong></p>

<p>The digit product of 16 is 6, which is divisible by 3, making it the smallest number greater than or equal to 15 that satisfies the condition.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= t &lt;= 10</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

We note that within every $10$ numbers, there will definitely be an integer whose digit product is $0$. Therefore, we can directly enumerate integers greater than or equal to $n$ until we find an integer whose digit product is divisible by $t$.

The time complexity is $O(\log n)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def smallestNumber(self, n: int, t: int) -> int:
        for i in count(n):
            p = 1
            x = i
            while x:
                p *= x % 10
                x //= 10
            if p % t == 0:
                return i
```

#### Java

```java
class Solution {
    public int smallestNumber(int n, int t) {
        for (int i = n;; ++i) {
            int p = 1;
            for (int x = i; x > 0; x /= 10) {
                p *= (x % 10);
            }
            if (p % t == 0) {
                return i;
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    int smallestNumber(int n, int t) {
        for (int i = n;; ++i) {
            int p = 1;
            for (int x = i; x > 0; x /= 10) {
                p *= (x % 10);
            }
            if (p % t == 0) {
                return i;
            }
        }
    }
};
```

#### Go

```go
func smallestNumber(n int, t int) int {
	for i := n; ; i++ {
		p := 1
		for x := i; x > 0; x /= 10 {
			p *= x % 10
		}
		if p%t == 0 {
			return i
		}
	}
}
```

#### TypeScript

```ts
function smallestNumber(n: number, t: number): number {
    for (let i = n; ; ++i) {
        let p = 1;
        for (let x = i; x; x = Math.floor(x / 10)) {
            p *= x % 10;
        }
        if (p % t === 0) {
            return i;
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
