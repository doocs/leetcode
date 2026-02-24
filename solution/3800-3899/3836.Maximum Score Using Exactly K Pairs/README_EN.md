---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3836.Maximum%20Score%20Using%20Exactly%20K%20Pairs/README_EN.md
rating: 1987
source: Weekly Contest 488 Q4
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [3836. Maximum Score Using Exactly K Pairs](https://leetcode.com/problems/maximum-score-using-exactly-k-pairs)

[中文文档](/solution/3800-3899/3836.Maximum%20Score%20Using%20Exactly%20K%20Pairs/README.md)

## Description

<!-- description:start -->

<p>You are given two integer arrays <code>nums1</code> and <code>nums2</code> of lengths <code>n</code> and <code>m</code> respectively, and an integer <code>k</code>.</p>

<p>You must choose <strong>exactly</strong> <code>k</code> pairs of indices <code>(i<sub>1</sub>, j<sub>1</sub>), (i<sub>2</sub>, j<sub>2</sub>), ..., (i<sub>k</sub>, j<sub>k</sub>)</code> such that:</p>

<ul>
	<li><code>0 &lt;= i<sub>1</sub> &lt; i<sub>2</sub> &lt; ... &lt; i<sub>k</sub> &lt; n</code></li>
	<li><code>0 &lt;= j<sub>1</sub> &lt; j<sub>2</sub> &lt; ... &lt; j<sub>k</sub> &lt; m</code></li>
</ul>

<p>For each chosen pair <code>(i, j)</code>, you gain a score of <code>nums1[i] * nums2[j]</code>.</p>

<p>The total <strong>score</strong> is the <strong>sum</strong> of the products of all selected pairs.</p>

<p>Return an integer representing the <strong>maximum</strong> achievable total score.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums1 = [1,3,2], nums2 = [4,5,1], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">22</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal choice of index pairs is:</p>

<ul>
	<li><code>(i<sub>1</sub>, j<sub>1</sub>) = (1, 0)</code> which scores <code>3 * 4 = 12</code></li>
	<li><code>(i<sub>2</sub>, j<sub>2</sub>) = (2, 1)</code> which scores <code>2 * 5 = 10</code></li>
</ul>

<p>This gives a total score of <code>12 + 10 = 22</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums1 = [-2,0,5], nums2 = [-3,4,-1,2], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">26</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal choice of index pairs is:</p>

<ul>
	<li><code>(i<sub>1</sub>, j<sub>1</sub>) = (0, 0)</code> which scores <code>-2 * -3 = 6</code></li>
	<li><code>(i<sub>2</sub>, j<sub>2</sub>) = (2, 1)</code> which scores <code>5 * 4 = 20</code></li>
</ul>

<p>The total score is <code>6 + 20 = 26</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums1 = [-3,-2], nums2 = [1,2], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">-7</span></p>

<p><strong>Explanation:</strong></p>

<p>The optimal choice of index pairs is:</p>

<ul>
	<li><code>(i<sub>1</sub>, j<sub>1</sub>) = (0, 0)</code> which scores <code>-3 * 1 = -3</code></li>
	<li><code>(i<sub>2</sub>, j<sub>2</sub>) = (1, 1)</code> which scores <code>-2 * 2 = -4</code></li>
</ul>

<p>The total score is <code>-3 + (-4) = -7</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums1.length &lt;= 100</code></li>
	<li><code>1 &lt;= m == nums2.length &lt;= 100</code></li>
	<li><code>-10<sup>6</sup> &lt;= nums1[i], nums2[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= k &lt;= min(n, m)</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

We denote the lengths of arrays $\textit{nums1}$ and $\textit{nums2}$ as $n$ and $m$ respectively, and denote $k$ in the problem as $K$.

We define a three-dimensional array $f$, where $f[i][j][k]$ represents the maximum score of selecting exactly $k$ index pairs from the first $i$ elements of $\textit{nums1}$ and the first $j$ elements of $\textit{nums2}$. Initially, $f[0][0][0] = 0$, and all other values of $f[i][j][k]$ are negative infinity.

We can calculate $f[i][j][k]$ through the following state transition equation:

$$
f[i][j][k] = \max\begin{cases}
f[i-1][j][k], \\
f[i][j-1][k], \\
f[i-1][j-1][k-1] + nums1[i-1] * nums2[j-1]
\end{cases}
$$

The first case represents not selecting the $i$-th element of $\textit{nums1}$, the second case represents not selecting the $j$-th element of $\textit{nums2}$, and the third case represents selecting the $i$-th element of $\textit{nums1}$ and the $j$-th element of $\textit{nums2}$ as a pair of indices.

Finally, we need to return $f[n][m][K]$.

The time complexity is $O(m \times n \times K)$ and the space complexity is $O(m \times n \times K)$, where $n$ and $m$ are the lengths of arrays $\textit{nums1}$ and $\textit{nums2}$ respectively, and $K$ is $k$ in the problem.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxScore(self, nums1: List[int], nums2: List[int], K: int) -> int:
        n, m = len(nums1), len(nums2)
        f = [[[-inf] * (K + 1) for _ in range(m + 1)] for _ in range(n + 1)]
        f[0][0][0] = 0
        for i in range(n + 1):
            for j in range(m + 1):
                for k in range(K + 1):
                    if i > 0:
                        f[i][j][k] = max(f[i][j][k], f[i - 1][j][k])
                    if j > 0:
                        f[i][j][k] = max(f[i][j][k], f[i][j - 1][k])
                    if i > 0 and j > 0 and k > 0:
                        f[i][j][k] = max(f[i][j][k], f[i - 1][j - 1][k - 1] + nums1[i - 1] * nums2[j - 1])
        return f[n][m][K]
```

#### Java

```java
class Solution {
    public long maxScore(int[] nums1, int[] nums2, int K) {
        int n = nums1.length, m = nums2.length;
        long NEG = Long.MIN_VALUE / 4;
        long[][][] f = new long[n + 1][m + 1][K + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                Arrays.fill(f[i][j], NEG);
            }
        }
        f[0][0][0] = 0;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= K; k++) {
                    if (i > 0) {
                        f[i][j][k] = Math.max(f[i][j][k], f[i - 1][j][k]);
                    }
                    if (j > 0) {
                        f[i][j][k] = Math.max(f[i][j][k], f[i][j - 1][k]);
                    }
                    if (i > 0 && j > 0 && k > 0) {
                        f[i][j][k] = Math.max(f[i][j][k],
                            f[i - 1][j - 1][k - 1] + (long) nums1[i - 1] * nums2[j - 1]);
                    }
                }
            }
        }
        return f[n][m][K];
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxScore(vector<int>& nums1, vector<int>& nums2, int K) {
        int n = nums1.size(), m = nums2.size();
        long long NEG = LLONG_MIN / 4;
        vector f(n + 1, vector(m + 1, vector<long long>(K + 1, NEG)));
        f[0][0][0] = 0;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= K; k++) {
                    if (i > 0) {
                        f[i][j][k] = max(f[i][j][k], f[i - 1][j][k]);
                    }
                    if (j > 0) {
                        f[i][j][k] = max(f[i][j][k], f[i][j - 1][k]);
                    }
                    if (i > 0 && j > 0 && k > 0) {
                        f[i][j][k] = max(
                            f[i][j][k],
                            f[i - 1][j - 1][k - 1] + 1LL * nums1[i - 1] * nums2[j - 1]);
                    }
                }
            }
        }
        return f[n][m][K];
    }
};
```

#### Go

```go
func maxScore(nums1 []int, nums2 []int, K int) int64 {
	n, m := len(nums1), len(nums2)
	NEG := int64(math.MinInt64 / 4)
	f := make([][][]int64, n+1)
	for i := 0; i <= n; i++ {
		f[i] = make([][]int64, m+1)
		for j := 0; j <= m; j++ {
			f[i][j] = make([]int64, K+1)
			for k := 0; k <= K; k++ {
				f[i][j][k] = NEG
			}
		}
	}
	f[0][0][0] = 0
	for i := 0; i <= n; i++ {
		for j := 0; j <= m; j++ {
			for k := 0; k <= K; k++ {
				if i > 0 {
					f[i][j][k] = max(f[i][j][k], f[i-1][j][k])
				}
				if j > 0 {
					f[i][j][k] = max(f[i][j][k], f[i][j-1][k])
				}
				if i > 0 && j > 0 && k > 0 {
					f[i][j][k] = max(
						f[i][j][k],
						f[i-1][j-1][k-1]+int64(nums1[i-1])*int64(nums2[j-1]),
					)
				}
			}
		}
	}
	return f[n][m][K]
}
```

#### TypeScript

```ts
function maxScore(nums1: number[], nums2: number[], K: number): number {
    const n = nums1.length,
        m = nums2.length;
    const NEG = -1e18;
    const f = Array.from({ length: n + 1 }, () =>
        Array.from({ length: m + 1 }, () => Array(K + 1).fill(NEG)),
    );
    f[0][0][0] = 0;
    for (let i = 0; i <= n; i++) {
        for (let j = 0; j <= m; j++) {
            for (let k = 0; k <= K; k++) {
                if (i > 0) {
                    f[i][j][k] = Math.max(f[i][j][k], f[i - 1][j][k]);
                }
                if (j > 0) {
                    f[i][j][k] = Math.max(f[i][j][k], f[i][j - 1][k]);
                }
                if (i > 0 && j > 0 && k > 0) {
                    f[i][j][k] = Math.max(
                        f[i][j][k],
                        f[i - 1][j - 1][k - 1] + nums1[i - 1] * nums2[j - 1],
                    );
                }
            }
        }
    }
    return f[n][m][K];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
