---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3622.Check%20Divisibility%20by%20Digit%20Sum%20and%20Product/README_EN.md
rating: 1148
source: Weekly Contest 459 Q1
tags:
    - Math
---

<!-- problem:start -->

# [3622. Check Divisibility by Digit Sum and Product](https://leetcode.com/problems/check-divisibility-by-digit-sum-and-product)

[中文文档](/solution/3600-3699/3622.Check%20Divisibility%20by%20Digit%20Sum%20and%20Product/README.md)

## Description

<!-- description:start -->

<p>You are given a positive integer <code>n</code>. Determine whether <code>n</code> is divisible by the <strong>sum </strong>of the following two values:</p>

<ul>
	<li>
	<p>The <strong>digit sum</strong> of <code>n</code> (the sum of its digits).</p>
	</li>
	<li>
	<p>The <strong>digit</strong> <strong>product</strong> of <code>n</code> (the product of its digits).</p>
	</li>
</ul>

<p>Return <code>true</code> if <code>n</code> is divisible by this sum; otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 99</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p>Since 99 is divisible by the sum (9 + 9 = 18) plus product (9 * 9 = 81) of its digits (total 99), the output is true.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 23</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<p>Since 23 is not divisible by the sum (2 + 3 = 5) plus product (2 * 3 = 6) of its digits (total 11), the output is false.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We can iterate through each digit of the integer $n$, calculating the digit sum $s$ and digit product $p$. Finally, we check whether $n$ is divisible by $s + p$.

The time complexity is $O(\log n)$, where $n$ is the value of the integer $n$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def checkDivisibility(self, n: int) -> bool:
        s, p = 0, 1
        x = n
        while x:
            x, v = divmod(x, 10)
            s += v
            p *= v
        return n % (s + p) == 0
```

#### Java

```java
class Solution {
    public boolean checkDivisibility(int n) {
        int s = 0, p = 1;
        int x = n;
        while (x != 0) {
            int v = x % 10;
            x /= 10;
            s += v;
            p *= v;
        }
        return n % (s + p) == 0;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool checkDivisibility(int n) {
        int s = 0, p = 1;
        int x = n;
        while (x != 0) {
            int v = x % 10;
            x /= 10;
            s += v;
            p *= v;
        }
        return n % (s + p) == 0;
    }
};
```

#### Go

```go
func checkDivisibility(n int) bool {
	s, p := 0, 1
	x := n
	for x != 0 {
		v := x % 10
		x /= 10
		s += v
		p *= v
	}
	return n%(s+p) == 0
}
```

#### TypeScript

```ts
function checkDivisibility(n: number): boolean {
    let [s, p] = [0, 1];
    let x = n;
    while (x !== 0) {
        const v = x % 10;
        x = Math.floor(x / 10);
        s += v;
        p *= v;
    }
    return n % (s + p) === 0;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
