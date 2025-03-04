---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0629.K%20Inverse%20Pairs%20Array/README_EN.md
tags:
    - Dynamic Programming
---

<!-- problem:start -->

# [629. K Inverse Pairs Array](https://leetcode.com/problems/k-inverse-pairs-array)

[中文文档](/solution/0600-0699/0629.K%20Inverse%20Pairs%20Array/README.md)

## Description

<!-- description:start -->

<p>For an integer array <code>nums</code>, an <strong>inverse pair</strong> is a pair of integers <code>[i, j]</code> where <code>0 &lt;= i &lt; j &lt; nums.length</code> and <code>nums[i] &gt; nums[j]</code>.</p>

<p>Given two integers n and k, return the number of different arrays consisting of numbers from <code>1</code> to <code>n</code> such that there are exactly <code>k</code> <strong>inverse pairs</strong>. Since the answer can be huge, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 3, k = 0
<strong>Output:</strong> 1
<strong>Explanation:</strong> Only the array [1,2,3] which consists of numbers from 1 to 3 has exactly 0 inverse pairs.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 3, k = 1
<strong>Output:</strong> 2
<strong>Explanation:</strong> The array [1,3,2] and [2,1,3] have exactly 1 inverse pair.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>0 &lt;= k &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming + Prefix Sum

We define $f[i][j]$ as the number of arrays of length $i$ with $j$ inverse pairs. Initially, $f[0][0] = 1$, and the rest $f[i][j] = 0$.

Next, we consider how to obtain $f[i][j]$.

Assume the first $i-1$ numbers are already determined, and now we need to insert the number $i$. We discuss the cases of inserting $i$ into each position:

-   If $i$ is inserted into the 1st position, the number of inverse pairs increases by $i-1$, so $f[i][j] += f[i-1][j-(i-1)]$.
-   If $i$ is inserted into the 2nd position, the number of inverse pairs increases by $i-2$, so $f[i][j] += f[i-1][j-(i-2)]$.
-   ...
-   If $i$ is inserted into the $(i-1)$th position, the number of inverse pairs increases by 1, so $f[i][j] += f[i-1][j-1]$.
-   If $i$ is inserted into the $i$th position, the number of inverse pairs does not change, so $f[i][j] += f[i-1][j]$.

Therefore, $f[i][j] = \sum_{k=1}^{i} f[i-1][j-(i-k)]$.

We notice that the calculation of $f[i][j]$ actually involves prefix sums, so we can use prefix sums to optimize the calculation process. Moreover, since $f[i][j]$ only depends on $f[i-1][j]$, we can use a one-dimensional array to optimize the space complexity.

The time complexity is $O(n \times k)$, and the space complexity is $O(k)$. Here, $n$ and $k$ are the array length and the number of inverse pairs, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def kInversePairs(self, n: int, k: int) -> int:
        mod = 10**9 + 7
        f = [1] + [0] * k
        s = [0] * (k + 2)
        for i in range(1, n + 1):
            for j in range(1, k + 1):
                f[j] = (s[j + 1] - s[max(0, j - (i - 1))]) % mod
            for j in range(1, k + 2):
                s[j] = (s[j - 1] + f[j - 1]) % mod
        return f[k]
```

#### Java

```java
class Solution {
    public int kInversePairs(int n, int k) {
        final int mod = (int) 1e9 + 7;
        int[] f = new int[k + 1];
        int[] s = new int[k + 2];
        f[0] = 1;
        Arrays.fill(s, 1);
        s[0] = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= k; ++j) {
                f[j] = (s[j + 1] - s[Math.max(0, j - (i - 1))] + mod) % mod;
            }
            for (int j = 1; j <= k + 1; ++j) {
                s[j] = (s[j - 1] + f[j - 1]) % mod;
            }
        }
        return f[k];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int kInversePairs(int n, int k) {
        int f[k + 1];
        int s[k + 2];
        memset(f, 0, sizeof(f));
        f[0] = 1;
        fill(s, s + k + 2, 1);
        s[0] = 0;
        const int mod = 1e9 + 7;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= k; ++j) {
                f[j] = (s[j + 1] - s[max(0, j - (i - 1))] + mod) % mod;
            }
            for (int j = 1; j <= k + 1; ++j) {
                s[j] = (s[j - 1] + f[j - 1]) % mod;
            }
        }
        return f[k];
    }
};
```

#### Go

```go
func kInversePairs(n int, k int) int {
	f := make([]int, k+1)
	s := make([]int, k+2)
	f[0] = 1
	for i, x := range f {
		s[i+1] = s[i] + x
	}
	const mod = 1e9 + 7
	for i := 1; i <= n; i++ {
		for j := 1; j <= k; j++ {
			f[j] = (s[j+1] - s[max(0, j-(i-1))] + mod) % mod
		}
		for j := 1; j <= k+1; j++ {
			s[j] = (s[j-1] + f[j-1]) % mod
		}
	}
	return f[k]
}
```

#### TypeScript

```ts
function kInversePairs(n: number, k: number): number {
    const f: number[] = Array(k + 1).fill(0);
    f[0] = 1;
    const s: number[] = Array(k + 2).fill(1);
    s[0] = 0;
    const mod: number = 1e9 + 7;
    for (let i = 1; i <= n; ++i) {
        for (let j = 1; j <= k; ++j) {
            f[j] = (s[j + 1] - s[Math.max(0, j - (i - 1))] + mod) % mod;
        }
        for (let j = 1; j <= k + 1; ++j) {
            s[j] = (s[j - 1] + f[j - 1]) % mod;
        }
    }
    return f[k];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
