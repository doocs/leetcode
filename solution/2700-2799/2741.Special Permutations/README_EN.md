---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2741.Special%20Permutations/README_EN.md
rating: 2020
tags:
    - Bit Manipulation
    - Array
    - Bitmask
---

# [2741. Special Permutations](https://leetcode.com/problems/special-permutations)

[中文文档](/solution/2700-2799/2741.Special%20Permutations/README.md)

## Description

<p>You are given a&nbsp;<strong>0-indexed</strong>&nbsp;integer array&nbsp;<code>nums</code>&nbsp;containing&nbsp;<code>n</code>&nbsp;<strong>distinct</strong> positive integers. A permutation of&nbsp;<code>nums</code>&nbsp;is called special if:</p>

<ul>
	<li>For all indexes&nbsp;<code>0 &lt;= i &lt; n - 1</code>, either&nbsp;<code>nums[i] % nums[i+1] == 0</code>&nbsp;or&nbsp;<code>nums[i+1] % nums[i] == 0</code>.</li>
</ul>

<p>Return&nbsp;<em>the total number of special permutations.&nbsp;</em>As the answer could be large, return it&nbsp;<strong>modulo&nbsp;</strong><code>10<sup>9&nbsp;</sup>+ 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,6]
<strong>Output:</strong> 2
<strong>Explanation:</strong> [3,6,2] and [2,6,3] are the two special permutations of nums.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,4,3]
<strong>Output:</strong> 2
<strong>Explanation:</strong> [3,1,4] and [4,1,3] are the two special permutations of nums.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 14</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

### Solution 1: State Compression Dynamic Programming

We notice that the maximum length of the array in the problem does not exceed $14$. Therefore, we can use an integer to represent the current state, where the $i$-th bit is $1$ if the $i$-th number in the array has been selected, and $0$ if it has not been selected.

We define $f[i][j]$ as the number of schemes where the current selected integer state is $i$, and the index of the last selected integer is $j$. Initially, $f[0][0]=0$, and the answer is $\sum_{j=0}^{n-1}f[2^n-1][j]$.

Considering $f[i][j]$, if only one number is currently selected, then $f[i][j]=1$. Otherwise, we can enumerate the index $k$ of the last selected number. If the numbers corresponding to $k$ and $j$ meet the requirements of the problem, then $f[i][j]$ can be transferred from $f[i \oplus 2^j][k]$. That is:

$$
f[i][j]=
\begin{cases}
1, & i=2^j\\
\sum_{k=0}^{n-1}f[i \oplus 2^j][k], & i \neq 2^j \text{ and nums}[j] \text{ and nums}[k] \text{ meet the requirements of the problem}\\
\end{cases}
$$

The final answer is $\sum_{j=0}^{n-1}f[2^n-1][j]$. Note that the answer may be very large, so we need to take the modulus of $10^9+7$.

The time complexity is $O(n^2 \times 2^n)$, and the space complexity is $O(n \times 2^n)$. Here, $n$ is the length of the array.

<!-- tabs:start -->

```python
class Solution:
    def specialPerm(self, nums: List[int]) -> int:
        mod = 10**9 + 7
        n = len(nums)
        m = 1 << n
        f = [[0] * n for _ in range(m)]
        for i in range(1, m):
            for j, x in enumerate(nums):
                if i >> j & 1:
                    ii = i ^ (1 << j)
                    if ii == 0:
                        f[i][j] = 1
                        continue
                    for k, y in enumerate(nums):
                        if x % y == 0 or y % x == 0:
                            f[i][j] = (f[i][j] + f[ii][k]) % mod
        return sum(f[-1]) % mod
```

```java
class Solution {
    public int specialPerm(int[] nums) {
        final int mod = (int) 1e9 + 7;
        int n = nums.length;
        int m = 1 << n;
        int[][] f = new int[m][n];
        for (int i = 1; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if ((i >> j & 1) == 1) {
                    int ii = i ^ (1 << j);
                    if (ii == 0) {
                        f[i][j] = 1;
                        continue;
                    }
                    for (int k = 0; k < n; ++k) {
                        if (nums[j] % nums[k] == 0 || nums[k] % nums[j] == 0) {
                            f[i][j] = (f[i][j] + f[ii][k]) % mod;
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int x : f[m - 1]) {
            ans = (ans + x) % mod;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int specialPerm(vector<int>& nums) {
        const int mod = 1e9 + 7;
        int n = nums.size();
        int m = 1 << n;
        int f[m][n];
        memset(f, 0, sizeof(f));
        for (int i = 1; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if ((i >> j & 1) == 1) {
                    int ii = i ^ (1 << j);
                    if (ii == 0) {
                        f[i][j] = 1;
                        continue;
                    }
                    for (int k = 0; k < n; ++k) {
                        if (nums[j] % nums[k] == 0 || nums[k] % nums[j] == 0) {
                            f[i][j] = (f[i][j] + f[ii][k]) % mod;
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int x : f[m - 1]) {
            ans = (ans + x) % mod;
        }
        return ans;
    }
};
```

```go
func specialPerm(nums []int) (ans int) {
	const mod int = 1e9 + 7
	n := len(nums)
	m := 1 << n
	f := make([][]int, m)
	for i := range f {
		f[i] = make([]int, n)
	}
	for i := 1; i < m; i++ {
		for j, x := range nums {
			if i>>j&1 == 1 {
				ii := i ^ (1 << j)
				if ii == 0 {
					f[i][j] = 1
					continue
				}
				for k, y := range nums {
					if x%y == 0 || y%x == 0 {
						f[i][j] = (f[i][j] + f[ii][k]) % mod
					}
				}
			}
		}
	}
	for _, x := range f[m-1] {
		ans = (ans + x) % mod
	}
	return
}
```

<!-- tabs:end -->

<!-- end -->
