---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3726.Remove%20Zeros%20in%20Decimal%20Representation/README_EN.md
rating: 1175
source: Weekly Contest 473 Q1
tags:
    - Math
    - Simulation
---

<!-- problem:start -->

# [3726. Remove Zeros in Decimal Representation](https://leetcode.com/problems/remove-zeros-in-decimal-representation)

[中文文档](/solution/3700-3799/3726.Remove%20Zeros%20in%20Decimal%20Representation/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>positive</strong> integer <code>n</code>.</p>

<p>Return the integer obtained by removing all zeros from the decimal representation of <code>n</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 1020030</span></p>

<p><strong>Output:</strong> <span class="example-io">123</span></p>

<p><strong>Explanation:</strong></p>

<p>After removing all zeros from 1<strong><u>0</u></strong>2<strong><u>00</u></strong>3<strong><u>0</u></strong>, we get 123.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>1 has no zero in its decimal representation. Therefore, the answer is 1.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>15</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We start from the lowest digit of $n$ and check each digit one by one. If the digit is not zero, we add it to the result. We also need a variable to keep track of the current digit position in order to correctly construct the final integer.

Specifically, we can use a variable $k$ to represent the current digit position, then check each digit from the lowest to the highest. If the digit is not zero, we multiply it by $k$ and add it to the result, and then multiply $k$ by 10 for the next digit.

In the end, we obtain an integer without any zeros.

The time complexity is $O(d)$, where $d$ is the number of digits in $n$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def removeZeros(self, n: int) -> int:
        k = 1
        ans = 0
        while n:
            x = n % 10
            if x:
                ans = k * x + ans
                k *= 10
            n //= 10
        return ans
```

#### Java

```java
class Solution {
    public long removeZeros(long n) {
        long k = 1;
        long ans = 0;
        while (n > 0) {
            long x = n % 10;
            if (x > 0) {
                ans = k * x + ans;
                k *= 10;
            }
            n /= 10;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long removeZeros(long long n) {
        long long k = 1;
        long long ans = 0;
        while (n > 0) {
            long x = n % 10;
            if (x > 0) {
                ans = k * x + ans;
                k *= 10;
            }
            n /= 10;
        }
        return ans;
    }
};
```

#### Go

```go
func removeZeros(n int64) (ans int64) {
	k := int64(1)
	for n > 0 {
		x := n % 10
		if x > 0 {
			ans = k*x + ans
			k *= 10
		}
		n /= 10
	}
	return
}
```

#### TypeScript

```ts
function removeZeros(n: number): number {
    let k = 1;
    let ans = 0;
    while (n) {
        const x = n % 10;
        if (x) {
            ans = k * x + ans;
            k *= 10;
        }
        n = Math.floor(n / 10);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
