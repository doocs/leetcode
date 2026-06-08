---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3225.Maximum%20Score%20From%20Grid%20Operations/README_EN.md
rating: 3027
source: Biweekly Contest 135 Q4
tags:
    - Array
    - Dynamic Programming
    - Matrix
    - Prefix Sum
---

<!-- problem:start -->

# [3225. Maximum Score From Grid Operations](https://leetcode.com/problems/maximum-score-from-grid-operations)

[中文文档](/solution/3200-3299/3225.Maximum%20Score%20From%20Grid%20Operations/README.md)

## Description

<!-- description:start -->

<p>You are given a 2D matrix <code>grid</code> of size <code>n x n</code>. Initially, all cells of the grid are colored white. In one operation, you can select any cell of indices <code>(i, j)</code>, and color black all the cells of the <code>j<sup>th</sup></code> column starting from the top row down to the <code>i<sup>th</sup></code> row.</p>

<p>The grid score is the sum of all <code>grid[i][j]</code> such that cell <code>(i, j)</code> is white and it has a horizontally adjacent black cell.</p>

<p>Return the <strong>maximum</strong> score that can be achieved after some number of operations.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[0,0,0,0,0],[0,0,3,0,0],[0,1,0,0,0],[5,0,0,3,0],[0,0,0,0,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">11</span></p>

<p><strong>Explanation:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3225.Maximum%20Score%20From%20Grid%20Operations/images/one.png" style="width: 300px; height: 200px;" />
<p>In the first operation, we color all cells in column 1 down to row 3, and in the second operation, we color all cells in column 4 down to the last row. The score of the resulting grid is <code>grid[3][0] + grid[1][2] + grid[3][3]</code> which is equal to 11.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[10,9,0,0,15],[7,1,0,8,0],[5,20,0,11,0],[0,0,0,1,2],[8,12,1,10,3]]</span></p>

<p><strong>Output:</strong> <span class="example-io">94</span></p>

<p><strong>Explanation:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3225.Maximum%20Score%20From%20Grid%20Operations/images/two-1.png" style="width: 300px; height: 200px;" />
<p>We perform operations on 1, 2, and 3 down to rows 1, 4, and 0, respectively. The score of the resulting grid is <code>grid[0][0] + grid[1][0] + grid[2][1] + grid[4][1] + grid[1][3] + grid[2][3] + grid[3][3] + grid[4][3] + grid[0][4]</code> which is equal to 94.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;=&nbsp;n == grid.length &lt;= 100</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming + Prefix Sum

For each column $j$, we define a **fill height** $k[j] \in \{0, 1, \dots, n\}$, representing how many cells from the top are colored black.

A white cell $(i, j)$ contributes $\textit{grid}[i][j]$ to the score if it has at least one horizontally adjacent black cell. Using the union formula:

$$\text{contribution of column } j = \max\left(0,\ \textit{prefix}[j][\max(k[j-1],\ k[j+1])] - \textit{prefix}[j][k[j]]\right)$$

This formula correctly handles double-counting — a white cell adjacent to black on **both** sides is still counted only **once**.

We define $\textit{dp}[h_1][h_2]$ as the maximum score for columns $0\ldots j$, where $k[j] = h_1$ and $k[j-1] = h_2$.

**Transition** (choosing $k[j+1] = hp$):

$$\textit{dp\_new}[hp][h_1] = \max_{h_2}\left(\textit{dp}[h_1][h_2] + \max\left(0,\ \textit{prefix}[j][\max(h_2, hp)] - \textit{prefix}[j][h_1]\right)\right)$$

We split this into two cases:

- **$h_2 \le hp$**: contribution $= \max(0,\ \textit{prefix}[j][hp] - \textit{prefix}[j][h_1])$ (independent of $h_2$), so we need $\max_{h_2 \le hp} \textit{dp}[h_1][h_2]$ — a **prefix max**.
- **$h_2 > hp$**: contribution $= \max(0,\ \textit{prefix}[j][h_2] - \textit{prefix}[j][h_1])$ (depends on $h_2$), so we need $\max_{h_2 > hp}\left(\textit{dp}[h_1][h_2] + \max(0,\ \textit{prefix}[j][h_2] - \textit{prefix}[j][h_1])\right)$ — a **suffix max**.

Precomputing these two arrays reduces the transition from $O(n^3)$ per column to $O(n^2)$ per column.

The time complexity is $O(n^3)$, and the space complexity is $O(n^2)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumScore(self, grid: List[List[int]]) -> int:
        n = len(grid)
        NEG = float('-inf')
        prefix = [[0] * (n + 1) for _ in range(n)]
        for j in range(n):
            for i in range(n):
                prefix[j][i + 1] = prefix[j][i] + grid[i][j]
        dp = [[NEG] * (n + 1) for _ in range(n + 1)]
        for h in range(n + 1):
            dp[h][0] = 0
        for j in range(n - 1):
            new = [[NEG] * (n + 1) for _ in range(n + 1)]
            for h1 in range(n + 1):
                pref_max = [NEG] * (n + 2)
                pref_max[0] = dp[h1][0]
                for h2 in range(1, n + 1):
                    pref_max[h2] = max(pref_max[h2 - 1], dp[h1][h2])
                suf_max = [NEG] * (n + 2)
                for h2 in range(n, -1, -1):
                    val = dp[h1][h2] + max(0, prefix[j][h2] - prefix[j][h1]) if dp[h1][h2] != NEG else NEG
                    suf_max[h2] = max(suf_max[h2 + 1], val)
                for hp in range(n + 1):
                    right_c = max(0, prefix[j][hp] - prefix[j][h1])
                    v1 = pref_max[hp] + right_c if pref_max[hp] != NEG else NEG
                    v2 = suf_max[hp + 1]
                    best = max(v1, v2)
                    if best > new[hp][h1]:
                        new[hp][h1] = best
            dp = new
        ans = 0
        for h1 in range(n + 1):
            for h2 in range(n + 1):
                if dp[h1][h2] == NEG:
                    continue
                c = max(0, prefix[n - 1][h2] - prefix[n - 1][h1])
                ans = max(ans, dp[h1][h2] + c)
        return ans
```

#### Java

```java
class Solution {
    public long maximumScore(int[][] grid) {
        int n = grid.length;
        final long NEG = Long.MIN_VALUE / 2;
        long[][] prefix = new long[n][n + 1];
        for (int j = 0; j < n; j++)
            for (int i = 0; i < n; i++)
                prefix[j][i + 1] = prefix[j][i] + grid[i][j];
        long[][] dp = new long[n + 1][n + 1];
        for (long[] row : dp) java.util.Arrays.fill(row, NEG);
        for (int h = 0; h <= n; h++) dp[h][0] = 0;
        for (int j = 0; j < n - 1; j++) {
            long[][] newDp = new long[n + 1][n + 1];
            for (long[] row : newDp) java.util.Arrays.fill(row, NEG);
            for (int h1 = 0; h1 <= n; h1++) {
                long[] prefMax = new long[n + 2];
                prefMax[0] = dp[h1][0];
                for (int h2 = 1; h2 <= n; h2++)
                    prefMax[h2] = Math.max(prefMax[h2 - 1], dp[h1][h2]);
                long[] sufMax = new long[n + 2];
                sufMax[n + 1] = NEG;
                for (int h2 = n; h2 >= 0; h2--) {
                    long val = dp[h1][h2] == NEG ? NEG : dp[h1][h2] + Math.max(0, prefix[j][h2] - prefix[j][h1]);
                    sufMax[h2] = Math.max(sufMax[h2 + 1], val);
                }
                for (int hp = 0; hp <= n; hp++) {
                    long rightC = Math.max(0, prefix[j][hp] - prefix[j][h1]);
                    long v1 = prefMax[hp] == NEG ? NEG : prefMax[hp] + rightC;
                    long v2 = sufMax[hp + 1];
                    long best = Math.max(v1, v2);
                    if (best > newDp[hp][h1]) newDp[hp][h1] = best;
                }
            }
            dp = newDp;
        }
        long ans = 0;
        for (int h1 = 0; h1 <= n; h1++)
            for (int h2 = 0; h2 <= n; h2++) {
                if (dp[h1][h2] == NEG) continue;
                long c = Math.max(0, prefix[n - 1][h2] - prefix[n - 1][h1]);
                ans = Math.max(ans, dp[h1][h2] + c);
            }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maximumScore(vector<vector<int>>& grid) {
        int n = grid.size();
        const long long NEG = LLONG_MIN / 2;
        vector<vector<long long>> prefix(n, vector<long long>(n + 1, 0));
        for (int j = 0; j < n; j++)
            for (int i = 0; i < n; i++)
                prefix[j][i + 1] = prefix[j][i] + grid[i][j];
        vector<vector<long long>> dp(n + 1, vector<long long>(n + 1, NEG));
        for (int h = 0; h <= n; h++) dp[h][0] = 0;
        for (int j = 0; j < n - 1; j++) {
            vector<vector<long long>> newDp(n + 1, vector<long long>(n + 1, NEG));
            for (int h1 = 0; h1 <= n; h1++) {
                vector<long long> prefMax(n + 2, NEG), sufMax(n + 2, NEG);
                prefMax[0] = dp[h1][0];
                for (int h2 = 1; h2 <= n; h2++)
                    prefMax[h2] = max(prefMax[h2 - 1], dp[h1][h2]);
                for (int h2 = n; h2 >= 0; h2--) {
                    long long val = dp[h1][h2] == NEG ? NEG : dp[h1][h2] + max(0LL, prefix[j][h2] - prefix[j][h1]);
                    sufMax[h2] = max(sufMax[h2 + 1], val);
                }
                for (int hp = 0; hp <= n; hp++) {
                    long long rc = max(0LL, prefix[j][hp] - prefix[j][h1]);
                    long long v1 = prefMax[hp] == NEG ? NEG : prefMax[hp] + rc;
                    long long best = max(v1, sufMax[hp + 1]);
                    newDp[hp][h1] = max(newDp[hp][h1], best);
                }
            }
            dp = newDp;
        }
        long long ans = 0;
        for (int h1 = 0; h1 <= n; h1++)
            for (int h2 = 0; h2 <= n; h2++) {
                if (dp[h1][h2] == NEG) continue;
                long long c = max(0LL, prefix[n - 1][h2] - prefix[n - 1][h1]);
                ans = max(ans, dp[h1][h2] + c);
            }
        return ans;
    }
};
```

#### GO

```go
func maximumScore(grid [][]int) int64 {
    n := len(grid)
    const NEG = math.MinInt64 / 2
    prefix := make([][]int64, n)
    for j := 0; j < n; j++ {
        prefix[j] = make([]int64, n+1)
        for i := 0; i < n; i++ {
            prefix[j][i+1] = prefix[j][i] + int64(grid[i][j])
        }
    }
    dp := make([][]int64, n+1)
    for i := range dp {
        dp[i] = make([]int64, n+1)
        for k := range dp[i] {
            dp[i][k] = NEG
        }
    }
    for h := 0; h <= n; h++ {
        dp[h][0] = 0
    }
    for j := 0; j < n-1; j++ {
        newDp := make([][]int64, n+1)
        for i := range newDp {
            newDp[i] = make([]int64, n+1)
            for k := range newDp[i] {
                newDp[i][k] = NEG
            }
        }
        for h1 := 0; h1 <= n; h1++ {
            prefMax := make([]int64, n+2)
            prefMax[0] = dp[h1][0]
            for h2 := 1; h2 <= n; h2++ {
                prefMax[h2] = max64(prefMax[h2-1], dp[h1][h2])
            }
            sufMax := make([]int64, n+2)
            sufMax[n+1] = NEG
            for h2 := n; h2 >= 0; h2-- {
                val := NEG
                if dp[h1][h2] != NEG {
                    add := prefix[j][h2] - prefix[j][h1]
                    if add < 0 { add = 0 }
                    val = dp[h1][h2] + add
                }
                sufMax[h2] = max64(sufMax[h2+1], val)
            }
            for hp := 0; hp <= n; hp++ {
                rc := prefix[j][hp] - prefix[j][h1]
                if rc < 0 { rc = 0 }
                v1 := int64(NEG)
                if prefMax[hp] != NEG { v1 = prefMax[hp] + rc }
                best := max64(v1, sufMax[hp+1])
                if best > newDp[hp][h1] { newDp[hp][h1] = best }
            }
        }
        dp = newDp
    }
    var ans int64
    for h1 := 0; h1 <= n; h1++ {
        for h2 := 0; h2 <= n; h2++ {
            if dp[h1][h2] == NEG { continue }
            c := prefix[n-1][h2] - prefix[n-1][h1]
            if c < 0 { c = 0 }
            if dp[h1][h2]+c > ans { ans = dp[h1][h2] + c }
        }
    }
    return ans
}

func max64(a, b int64) int64 {
    if a > b { return a }
    return b
}
```

#### TypeScript

```ts
function maximumScore(grid: number[][]): number {
    const n = grid.length;
    const NEG = -Infinity;
    const prefix: number[][] = Array.from({ length: n }, () => new Array(n + 1).fill(0));
    for (let j = 0; j < n; j++)
        for (let i = 0; i < n; i++)
            prefix[j][i + 1] = prefix[j][i] + grid[i][j];
    let dp: number[][] = Array.from({ length: n + 1 }, () => new Array(n + 1).fill(NEG));
    for (let h = 0; h <= n; h++) dp[h][0] = 0;
    for (let j = 0; j < n - 1; j++) {
        const newDp: number[][] = Array.from({ length: n + 1 }, () => new Array(n + 1).fill(NEG));
        for (let h1 = 0; h1 <= n; h1++) {
            const prefMax: number[] = new Array(n + 2).fill(NEG);
            prefMax[0] = dp[h1][0];
            for (let h2 = 1; h2 <= n; h2++)
                prefMax[h2] = Math.max(prefMax[h2 - 1], dp[h1][h2]);
            const sufMax: number[] = new Array(n + 2).fill(NEG);
            for (let h2 = n; h2 >= 0; h2--) {
                const val = dp[h1][h2] === NEG ? NEG : dp[h1][h2] + Math.max(0, prefix[j][h2] - prefix[j][h1]);
                sufMax[h2] = Math.max(sufMax[h2 + 1], val);
            }
            for (let hp = 0; hp <= n; hp++) {
                const rc = Math.max(0, prefix[j][hp] - prefix[j][h1]);
                const v1 = prefMax[hp] === NEG ? NEG : prefMax[hp] + rc;
                const best = Math.max(v1, sufMax[hp + 1]);
                if (best > newDp[hp][h1]) newDp[hp][h1] = best;
            }
        }
        dp = newDp;
    }
    let ans = 0;
    for (let h1 = 0; h1 <= n; h1++)
        for (let h2 = 0; h2 <= n; h2++) {
            if (dp[h1][h2] === NEG) continue;
            const c = Math.max(0, prefix[n - 1][h2] - prefix[n - 1][h1]);
            ans = Math.max(ans, dp[h1][h2] + c);
        }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
