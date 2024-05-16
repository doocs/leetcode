---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2834.Find%20the%20Minimum%20Possible%20Sum%20of%20a%20Beautiful%20Array/README_EN.md
rating: 1409
source: Weekly Contest 360 Q2
tags:
    - Greedy
    - Math
---

<!-- problem:start -->

# [2834. Find the Minimum Possible Sum of a Beautiful Array](https://leetcode.com/problems/find-the-minimum-possible-sum-of-a-beautiful-array)

[中文文档](/solution/2800-2899/2834.Find%20the%20Minimum%20Possible%20Sum%20of%20a%20Beautiful%20Array/README.md)

## Description

<p>You are given positive integers <code>n</code> and <code>target</code>.</p>

<p>An array <code>nums</code> is <strong>beautiful</strong> if it meets the following conditions:</p>

<ul>
	<li><code>nums.length == n</code>.</li>
	<li><code>nums</code> consists of pairwise <strong>distinct</strong> <strong>positive</strong> integers.</li>
	<li>There doesn&#39;t exist two <strong>distinct</strong> indices, <code>i</code> and <code>j</code>, in the range <code>[0, n - 1]</code>, such that <code>nums[i] + nums[j] == target</code>.</li>
</ul>

<p>Return <em>the <strong>minimum</strong> possible sum that a beautiful array could have modulo </em><code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 2, target = 3
<strong>Output:</strong> 4
<strong>Explanation:</strong> We can see that nums = [1,3] is beautiful.
- The array nums has length n = 2.
- The array nums consists of pairwise distinct positive integers.
- There doesn&#39;t exist two distinct indices, i and j, with nums[i] + nums[j] == 3.
It can be proven that 4 is the minimum possible sum that a beautiful array could have.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 3, target = 3
<strong>Output:</strong> 8
<strong>Explanation:</strong> We can see that nums = [1,3,4] is beautiful.
- The array nums has length n = 3.
- The array nums consists of pairwise distinct positive integers.
- There doesn&#39;t exist two distinct indices, i and j, with nums[i] + nums[j] == 3.
It can be proven that 8 is the minimum possible sum that a beautiful array could have.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 1, target = 1
<strong>Output:</strong> 1
<strong>Explanation:</strong> We can see, that nums = [1] is beautiful.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= target &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Mathematics

We can greedily construct the array `nums` starting from $x = 1$, choosing $x$ each time and excluding $target - x$.

Let's denote $m = \left\lfloor \frac{target}{2} \right\rfloor$.

If $x <= m$, then the numbers we can choose are $1, 2, \cdots, n$, so the sum of the array is $\left\lfloor \frac{(1+n)n}{2} \right\rfloor$.

If $x > m$, then the numbers we can choose are $1, 2, \cdots, m$, a total of $m$ numbers, and $n - m$ numbers starting from $target$, so the sum of the array is $\left\lfloor \frac{(1+m)m}{2} \right\rfloor + \left\lfloor \frac{(target + target + n - m - 1)(n-m)}{2} \right\rfloor$.

Note that we need to take the modulus of $10^9 + 7$ for the result.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def minimumPossibleSum(self, n: int, target: int) -> int:
        mod = 10**9 + 7
        m = target // 2
        if n <= m:
            return ((1 + n) * n // 2) % mod
        return ((1 + m) * m // 2 + (target + target + n - m - 1) * (n - m) // 2) % mod
```

```java
class Solution {
    public int minimumPossibleSum(int n, int target) {
        final int mod = (int) 1e9 + 7;
        int m = target / 2;
        if (n <= m) {
            return (int) ((1L + n) * n / 2 % mod);
        }
        long a = (1L + m) * m / 2 % mod;
        long b = ((1L * target + target + n - m - 1) * (n - m) / 2) % mod;
        return (int) ((a + b) % mod);
    }
}
```

```cpp
class Solution {
public:
    int minimumPossibleSum(int n, int target) {
        const int mod = 1e9 + 7;
        int m = target / 2;
        if (n <= m) {
            return (1LL + n) * n / 2 % mod;
        }
        long long a = (1LL + m) * m / 2 % mod;
        long long b = (1LL * target + target + n - m - 1) * (n - m) / 2 % mod;
        return (a + b) % mod;
    }
};
```

```go
func minimumPossibleSum(n int, target int) int {
	const mod int = 1e9 + 7
	m := target / 2
	if n <= m {
		return (n + 1) * n / 2 % mod
	}
	a := (m + 1) * m / 2 % mod
	b := (target + target + n - m - 1) * (n - m) / 2 % mod
	return (a + b) % mod
}
```

```ts
function minimumPossibleSum(n: number, target: number): number {
    const mod = 10 ** 9 + 7;
    const m = target >> 1;
    if (n <= m) {
        return (((1 + n) * n) / 2) % mod;
    }
    return (((1 + m) * m) / 2 + ((target + target + n - m - 1) * (n - m)) / 2) % mod;
}
```

```cs
public class Solution {
    public int MinimumPossibleSum(int n, int target) {
        const int mod = (int) 1e9 + 7;
        int m = target / 2;
        if (n <= m) {
            return (int) ((1L + n) * n / 2 % mod);
        }
        long a = (1L + m) * m / 2 % mod;
        long b = ((1L * target + target + n - m - 1) * (n - m) / 2) % mod;
        return (int) ((a + b) % mod);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
