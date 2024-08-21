---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3247.Number%20of%20Subsequences%20with%20Odd%20Sum/README_EN.md
tags:
    - Array
    - Math
    - Dynamic Programming
    - Combinatorics
---

<!-- problem:start -->

# [3247. Number of Subsequences with Odd Sum 🔒](https://leetcode.com/problems/number-of-subsequences-with-odd-sum)

[中文文档](/solution/3200-3299/3247.Number%20of%20Subsequences%20with%20Odd%20Sum/README.md)

## Description

<!-- description:start -->

<p>Given an array <code>nums</code>, return the number of <span data-keyword="subsequence-array">subsequences</span> with an odd sum of elements.</p>

<p>Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>The odd-sum subsequences are: <code>[<u><strong>1</strong></u>, 1, 1]</code>, <code>[1, <u><strong>1</strong></u>, 1],</code> <code>[1, 1, <u><strong>1</strong></u>]</code>, <code>[<u><strong>1, 1, 1</strong></u>]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>The odd-sum subsequences are: <code>[<u><strong>1</strong></u>, 2, 2]</code>, <code>[<u><strong>1, 2</strong></u>, 2],</code> <code>[<u><strong>1</strong></u>, 2, <b><u>2</u></b>]</code>, <code>[<u><strong>1, 2, 2</strong></u>]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

We define $f[0]$ to represent the number of subsequences with an even sum so far, and $f[1]$ to represent the number of subsequences with an odd sum so far. Initially, $f[0] = 0$ and $f[1] = 0$.

Traverse the array $\textit{nums}$, for each number $x$:

If $x$ is odd, the update rules for $f[0]$ and $f[1]$ are:

$$
\begin{aligned}
f[0] & = (f[0] + f[1]) \bmod 10^9 + 7, \\
f[1] & = (f[0] + f[1] + 1) \bmod 10^9 + 7.
\end{aligned}
$$

That is, the current number of subsequences with an even sum is equal to the previous number of subsequences with an even sum plus the number of subsequences with an odd sum concatenated with the current number $x$; the current number of subsequences with an odd sum is equal to the previous number of subsequences with an even sum concatenated with the current number $x$ plus the previous number of subsequences with an odd sum, plus one subsequence containing only the current number $x$.

If $x$ is even, the update rules for $f[0]$ and $f[1]$ are:

$$
\begin{aligned}
f[0] & = (f[0] + f[0] + 1) \bmod 10^9 + 7, \\
f[1] & = (f[1] + f[1]) \bmod 10^9 + 7.
\end{aligned}
$$

That is, the current number of subsequences with an even sum is equal to the previous number of subsequences with an even sum plus the number of subsequences with an even sum concatenated with the current number $x$, plus one subsequence containing only the current number $x$; the current number of subsequences with an odd sum is equal to the previous number of subsequences with an odd sum concatenated with the current number $x$ plus the previous number of subsequences with an odd sum.

Finally, return $f[1]$.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def subsequenceCount(self, nums: List[int]) -> int:
        mod = 10**9 + 7
        f = [0] * 2
        for x in nums:
            if x % 2:
                f[0], f[1] = (f[0] + f[1]) % mod, (f[0] + f[1] + 1) % mod
            else:
                f[0], f[1] = (f[0] + f[0] + 1) % mod, (f[1] + f[1]) % mod
        return f[1]
```

#### Java

```java
class Solution {
    public int subsequenceCount(int[] nums) {
        final int mod = (int) 1e9 + 7;
        int[] f = new int[2];
        for (int x : nums) {
            int[] g = new int[2];
            if (x % 2 == 1) {
                g[0] = (f[0] + f[1]) % mod;
                g[1] = (f[0] + f[1] + 1) % mod;
            } else {
                g[0] = (f[0] + f[0] + 1) % mod;
                g[1] = (f[1] + f[1]) % mod;
            }
            f = g;
        }
        return f[1];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int subsequenceCount(vector<int>& nums) {
        const int mod = 1e9 + 7;
        vector<int> f(2);
        for (int x : nums) {
            vector<int> g(2);
            if (x % 2 == 1) {
                g[0] = (f[0] + f[1]) % mod;
                g[1] = (f[0] + f[1] + 1) % mod;
            } else {
                g[0] = (f[0] + f[0] + 1) % mod;
                g[1] = (f[1] + f[1]) % mod;
            }
            f = g;
        }
        return f[1];
    }
};
```

#### Go

```go
func subsequenceCount(nums []int) int {
	mod := int(1e9 + 7)
	f := [2]int{}
	for _, x := range nums {
		g := [2]int{}
		if x%2 == 1 {
			g[0] = (f[0] + f[1]) % mod
			g[1] = (f[0] + f[1] + 1) % mod
		} else {
			g[0] = (f[0] + f[0] + 1) % mod
			g[1] = (f[1] + f[1]) % mod
		}
		f = g
	}
	return f[1]
}
```

#### TypeScript

```ts
function subsequenceCount(nums: number[]): number {
    const mod = 1e9 + 7;
    let f = [0, 0];
    for (const x of nums) {
        const g = [0, 0];
        if (x % 2 === 1) {
            g[0] = (f[0] + f[1]) % mod;
            g[1] = (f[0] + f[1] + 1) % mod;
        } else {
            g[0] = (f[0] + f[0] + 1) % mod;
            g[1] = (f[1] + f[1]) % mod;
        }
        f = g;
    }
    return f[1];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
