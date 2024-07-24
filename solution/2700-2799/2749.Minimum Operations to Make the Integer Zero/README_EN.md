---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2749.Minimum%20Operations%20to%20Make%20the%20Integer%20Zero/README_EN.md
rating: 2132
source: Weekly Contest 351 Q2
tags:
    - Bit Manipulation
    - Brainteaser
---

<!-- problem:start -->

# [2749. Minimum Operations to Make the Integer Zero](https://leetcode.com/problems/minimum-operations-to-make-the-integer-zero)

[中文文档](/solution/2700-2799/2749.Minimum%20Operations%20to%20Make%20the%20Integer%20Zero/README.md)

## Description

<!-- description:start -->

<p>You are given two integers <code>num1</code> and <code>num2</code>.</p>

<p>In one operation, you can choose integer <code>i</code> in the range <code>[0, 60]</code> and subtract <code>2<sup>i</sup> + num2</code> from <code>num1</code>.</p>

<p>Return <em>the integer denoting the <strong>minimum</strong> number of operations needed to make</em> <code>num1</code> <em>equal to</em> <code>0</code>.</p>

<p>If it is impossible to make <code>num1</code> equal to <code>0</code>, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num1 = 3, num2 = -2
<strong>Output:</strong> 3
<strong>Explanation:</strong> We can make 3 equal to 0 with the following operations:
- We choose i = 2 and substract 2<sup>2</sup> + (-2) from 3, 3 - (4 + (-2)) = 1.
- We choose i = 2 and substract 2<sup>2</sup>&nbsp;+ (-2) from 1, 1 - (4 + (-2)) = -1.
- We choose i = 0 and substract 2<sup>0</sup>&nbsp;+ (-2) from -1, (-1) - (1 + (-2)) = 0.
It can be proven, that 3 is the minimum number of operations that we need to perform.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num1 = 5, num2 = 7
<strong>Output:</strong> -1
<strong>Explanation:</strong> It can be proven, that it is impossible to make 5 equal to 0 with the given operation.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num1 &lt;= 10<sup>9</sup></code></li>
	<li><code><font face="monospace">-10<sup>9</sup>&nbsp;&lt;= num2 &lt;= 10<sup>9</sup></font></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

If we operate $k$ times, then the problem essentially becomes: determining whether $\textit{num1} - k \times \textit{num2}$ can be split into the sum of $k$ $2^i$s.

Let's assume $x = \textit{num1} - k \times \textit{num2}$. Next, we discuss in categories:

-   If $x < 0$, then $x$ cannot be split into the sum of $k$ $2^i$s, because $2^i > 0$, which obviously has no solution;
-   If the number of $1$s in the binary representation of $x$ is greater than $k$, there is also no solution in this case;
-   Otherwise, for the current $k$, there must exist a splitting scheme.

Therefore, we start enumerating $k$ from $1$. Once we find a $k$ that meets the condition, we can directly return the answer.

The time complexity is $O(\log x)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def makeTheIntegerZero(self, num1: int, num2: int) -> int:
        for k in count(1):
            x = num1 - k * num2
            if x < 0:
                break
            if x.bit_count() <= k <= x:
                return k
        return -1
```

#### Java

```java
class Solution {
    public int makeTheIntegerZero(int num1, int num2) {
        for (long k = 1;; ++k) {
            long x = num1 - k * num2;
            if (x < 0) {
                break;
            }
            if (Long.bitCount(x) <= k && k <= x) {
                return (int) k;
            }
        }
        return -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int makeTheIntegerZero(int num1, int num2) {
        using ll = long long;
        for (ll k = 1;; ++k) {
            ll x = num1 - k * num2;
            if (x < 0) {
                break;
            }
            if (__builtin_popcountll(x) <= k && k <= x) {
                return k;
            }
        }
        return -1;
    }
};
```

#### Go

```go
func makeTheIntegerZero(num1 int, num2 int) int {
	for k := 1; ; k++ {
		x := num1 - k*num2
		if x < 0 {
			break
		}
		if bits.OnesCount(uint(x)) <= k && k <= x {
			return k
		}
	}
	return -1
}
```

#### TypeScript

```ts
function makeTheIntegerZero(num1: number, num2: number): number {
    for (let k = 1; ; ++k) {
        let x = num1 - k * num2;
        if (x < 0) {
            break;
        }
        if (x.toString(2).replace(/0/g, '').length <= k && k <= x) {
            return k;
        }
    }
    return -1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
