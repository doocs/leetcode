---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2457.Minimum%20Addition%20to%20Make%20Integer%20Beautiful/README_EN.md
rating: 1680
source: Weekly Contest 317 Q3
tags:
    - Greedy
    - Math
---

<!-- problem:start -->

# [2457. Minimum Addition to Make Integer Beautiful](https://leetcode.com/problems/minimum-addition-to-make-integer-beautiful)

[中文文档](/solution/2400-2499/2457.Minimum%20Addition%20to%20Make%20Integer%20Beautiful/README.md)

## Description

<p>You are given two positive integers <code>n</code> and <code>target</code>.</p>

<p>An integer is considered <strong>beautiful</strong> if the sum of its digits is less than or equal to <code>target</code>.</p>

<p>Return the <em>minimum <strong>non-negative</strong> integer </em><code>x</code><em> such that </em><code>n + x</code><em> is beautiful</em>. The input will be generated such that it is always possible to make <code>n</code> beautiful.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 16, target = 6
<strong>Output:</strong> 4
<strong>Explanation:</strong> Initially n is 16 and its digit sum is 1 + 6 = 7. After adding 4, n becomes 20 and digit sum becomes 2 + 0 = 2. It can be shown that we can not make n beautiful with adding non-negative integer less than 4.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 467, target = 6
<strong>Output:</strong> 33
<strong>Explanation:</strong> Initially n is 467 and its digit sum is 4 + 6 + 7 = 17. After adding 33, n becomes 500 and digit sum becomes 5 + 0 + 0 = 5. It can be shown that we can not make n beautiful with adding non-negative integer less than 33.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 1, target = 1
<strong>Output:</strong> 0
<strong>Explanation:</strong> Initially n is 1 and its digit sum is 1, which is already smaller than or equal to target.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>12</sup></code></li>
	<li><code>1 &lt;= target &lt;= 150</code></li>
	<li>The input will be generated such that it is always possible to make <code>n</code> beautiful.</li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Greedy Algorithm

We define a function $f(x)$ to represent the sum of the digits of an integer $x$. The problem is to find the minimum non-negative integer $x$ such that $f(n + x) \leq target$.

If the sum of the digits of $y = n+x$ is greater than $target$, we can loop through the following operations to reduce the sum of the digits of $y$ to less than or equal to $target$:

-   Find the lowest non-zero digit of $y$, reduce it to $0$, and add $1$ to the digit one place higher;
-   Update $x$ and continue the above operation until the sum of the digits of $n+x$ is less than or equal to $target$.

After the loop ends, return $x$.

For example, if $n=467$ and $target=6$, the change process of $n$ is as follows:

$$
\begin{aligned}
& 467 \rightarrow 470 \rightarrow 500 \\
\end{aligned}
$$

The time complexity is $O(\log^2 n)$, where $n$ is the integer given in the problem. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def makeIntegerBeautiful(self, n: int, target: int) -> int:
        def f(x: int) -> int:
            y = 0
            while x:
                y += x % 10
                x //= 10
            return y

        x = 0
        while f(n + x) > target:
            y = n + x
            p = 10
            while y % 10 == 0:
                y //= 10
                p *= 10
            x = (y // 10 + 1) * p - n
        return x
```

```java
class Solution {
    public long makeIntegerBeautiful(long n, int target) {
        long x = 0;
        while (f(n + x) > target) {
            long y = n + x;
            long p = 10;
            while (y % 10 == 0) {
                y /= 10;
                p *= 10;
            }
            x = (y / 10 + 1) * p - n;
        }
        return x;
    }

    private int f(long x) {
        int y = 0;
        while (x > 0) {
            y += x % 10;
            x /= 10;
        }
        return y;
    }
}
```

```cpp
class Solution {
public:
    long long makeIntegerBeautiful(long long n, int target) {
        using ll = long long;
        auto f = [](ll x) {
            int y = 0;
            while (x) {
                y += x % 10;
                x /= 10;
            }
            return y;
        };

        ll x = 0;
        while (f(n + x) > target) {
            ll y = n + x;
            ll p = 10;
            while (y % 10 == 0) {
                y /= 10;
                p *= 10;
            }
            x = (y / 10 + 1) * p - n;
        }
        return x;
    }
};
```

```go
func makeIntegerBeautiful(n int64, target int) (x int64) {
	f := func(x int64) (y int) {
		for ; x > 0; x /= 10 {
			y += int(x % 10)
		}
		return
	}
	for f(n+x) > target {
		y := n + x
		var p int64 = 10
		for y%10 == 0 {
			y /= 10
			p *= 10
		}
		x = (y/10+1)*p - n
	}
	return
}
```

```ts
function makeIntegerBeautiful(n: number, target: number): number {
    const f = (x: number): number => {
        let y = 0;
        for (; x > 0; x = Math.floor(x / 10)) {
            y += x % 10;
        }
        return y;
    };

    let x = 0;
    while (f(n + x) > target) {
        let y = n + x;
        let p = 10;
        while (y % 10 === 0) {
            y = Math.floor(y / 10);
            p *= 10;
        }
        x = (Math.floor(y / 10) + 1) * p - n;
    }
    return x;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
