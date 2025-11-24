---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1015.Smallest%20Integer%20Divisible%20by%20K/README_EN.md
rating: 1874
source: Weekly Contest 129 Q2
tags:
    - Hash Table
    - Math
---

<!-- problem:start -->

# [1015. Smallest Integer Divisible by K](https://leetcode.com/problems/smallest-integer-divisible-by-k)

[中文文档](/solution/1000-1099/1015.Smallest%20Integer%20Divisible%20by%20K/README.md)

## Description

<!-- description:start -->

<p>Given a positive integer <code>k</code>, you need to find the <strong>length</strong> of the <strong>smallest</strong> positive integer <code>n</code> such that <code>n</code> is divisible by <code>k</code>, and <code>n</code> only contains the digit <code>1</code>.</p>

<p>Return <em>the <strong>length</strong> of </em><code>n</code>. If there is no such <code>n</code>, return -1.</p>

<p><strong>Note:</strong> <code>n</code> may not fit in a 64-bit signed integer.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> k = 1
<strong>Output:</strong> 1
<strong>Explanation:</strong> The smallest answer is n = 1, which has length 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> k = 2
<strong>Output:</strong> -1
<strong>Explanation:</strong> There is no such positive integer n divisible by 2.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> k = 3
<strong>Output:</strong> 3
<strong>Explanation:</strong> The smallest answer is n = 111, which has length 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Mathematics

We observe that the positive integer $n$ starts with an initial value of $1$, and each time it is multiplied by $10$ and then $1$ is added, i.e., $n = n \times 10 + 1$. Since $(n \times 10 + 1) \bmod k = ((n \bmod k) \times 10 + 1) \bmod k$, we can determine whether $n$ is divisible by $k$ by calculating $n \bmod k$.

We start from $n = 1$ and calculate $n \bmod k$ each time until $n \bmod k = 0$. At this point, $n$ is the smallest positive integer we are looking for, and its length is the number of digits in $n$. Otherwise, we update $n = (n \times 10 + 1) \bmod k$. If after looping $k$ times we still haven't found $n \bmod k = 0$, it means no such $n$ exists, and we return $-1$.

The time complexity is $O(k)$ and the space complexity is $O(1)$, where $k$ is the given positive integer.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def smallestRepunitDivByK(self, k: int) -> int:
        n = 1 % k
        for i in range(1, k + 1):
            if n == 0:
                return i
            n = (n * 10 + 1) % k
        return -1
```

#### Java

```java
class Solution {
    public int smallestRepunitDivByK(int k) {
        int n = 1 % k;
        for (int i = 1; i <= k; ++i) {
            if (n == 0) {
                return i;
            }
            n = (n * 10 + 1) % k;
        }
        return -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int smallestRepunitDivByK(int k) {
        int n = 1 % k;
        for (int i = 1; i <= k; ++i) {
            if (n == 0) {
                return i;
            }
            n = (n * 10 + 1) % k;
        }
        return -1;
    }
};
```

#### Go

```go
func smallestRepunitDivByK(k int) int {
	n := 1 % k
	for i := 1; i <= k; i++ {
		if n == 0 {
			return i
		}
		n = (n*10 + 1) % k
	}
	return -1
}
```

#### TypeScript

```ts
function smallestRepunitDivByK(k: number): number {
    let n = 1 % k;
    for (let i = 1; i <= k; ++i) {
        if (n === 0) {
            return i;
        }
        n = (n * 10 + 1) % k;
    }
    return -1;
}
```

#### Rust

```rust
impl Solution {
    pub fn smallest_repunit_div_by_k(k: i32) -> i32 {
        let mut n = 1 % k;
        for i in 1..=k {
            if n == 0 {
                return i;
            }
            n = (n * 10 + 1) % k;
        }
        -1
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
