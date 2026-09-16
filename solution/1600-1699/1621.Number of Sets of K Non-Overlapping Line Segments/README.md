---
comments: true
difficulty: 中等
rating: 2198
source: 第 37 场双周赛 Q3
tags:
    - 数学
    - 动态规划
    - 组合数学
    - 前缀和
---

<!-- problem:start -->

# [1621. 大小为 K 的不重叠线段的数目](https://leetcode.cn/problems/number-of-sets-of-k-non-overlapping-line-segments)

[English Version](/solution/1600-1699/1621.Number%20of%20Sets%20of%20K%20Non-Overlapping%20Line%20Segments/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一维空间的 <code>n</code> 个点，其中第 <code>i</code> 个点（编号从 <code>0</code> 到 <code>n-1</code>）位于 <code>x = i</code> 处，请你找到 <strong>恰好</strong> <code>k</code> <strong>个不重叠</strong> 线段且每个线段至少覆盖两个点的方案数。线段的两个端点必须都是 <strong>整数坐标</strong> 。这 <code>k</code> 个线段不需要全部覆盖全部 <code>n</code> 个点，且它们的端点 <strong>可以 </strong>重合。</p>

<p>请你返回 <code>k</code> 个不重叠线段的方案数。由于答案可能很大，请将结果对 <code>10<sup>9</sup> + 7</code> <strong>取余</strong> 后返回。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1621.Number%20of%20Sets%20of%20K%20Non-Overlapping%20Line%20Segments/images/ex1.png" style="width: 179px; height: 222px;" />
<pre>
<b>输入：</b>n = 4, k = 2
<b>输出：</b>5
<strong>解释：
</strong>如图所示，两个线段分别用红色和蓝色标出。
上图展示了 5 种不同的方案 {(0,2),(2,3)}，{(0,1),(1,3)}，{(0,1),(2,3)}，{(1,2),(2,3)}，{(0,1),(1,2)} 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>n = 3, k = 1
<b>输出：</b>3
<strong>解释：</strong>总共有 3 种不同的方案 {(0,1)}, {(0,2)}, {(1,2)} 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>n = 30, k = 7
<b>输出：</b>796297179
<strong>解释：</strong>画 7 条线段的总方案数为 3796297200 种。将这个数对 10<sup>9</sup> + 7 取余得到 796297179 。
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<b>输入：</b>n = 5, k = 3
<b>输出：</b>7
</pre>

<p><strong>示例 5：</strong></p>

<pre>
<b>输入：</b>n = 3, k = 2
<b>输出：</b>1</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 <= n <= 1000</code></li>
	<li><code>1 <= k <= n-1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

<!-- thinking:start -->

> **思考**
>
> 题目要求在 $n$ 个点上画出恰好 $k$ 条互不重叠（端点可以重合）的线段。若直接枚举每条线段的两个端点，即使 $n,k\le 1000$，组合量也会迅速膨胀，还要额外保证线段从左到右有序且不相交。
>
> 线段只能沿数轴依次排列，因此可以按点从左到右决策，并把状态按「当前点是不是某条线段的右端点」拆开。记 $f[i][j]$ 为前 $i$ 个点已画 $j$ 条且不以 $i$ 为右端，$g[i][j]$ 为以 $i$ 为右端。
>
> 这样转移到 $i$ 只依赖 $i-1$ 的两类状态：不以 $i$ 结束时，直接继承前 $i-1$ 个点上的 $j$ 条方案；以 $i$ 结束时，既可以把停在 $i-1$ 的那条线段再延长一格，也可以在 $i-1$ 与 $i$ 之间新开一条长度为 $1$ 的线段。

<!-- thinking:end -->

记 $f[i][j]$ 表示使用前 $i$ 个点构造了 $j$ 条线段，且最后一条线段的右端点不为 $i$ 的方案数；记 $g[i][j]$ 表示使用前 $i$ 个点构造了 $j$ 条线段，且最后一条线段的右端点为 $i$ 的方案数。初始时 $f[1][0]=1$。

考虑 $f[i][j]$，由于第 $j$ 条线段的右端点不为 $i$，因此前 $i-1$ 个点已经构造了 $j$ 条线段，有：

$$
f[i][j] = f[i-1][j] + g[i - 1][j]
$$

考虑 $g[i][j]$，第 $j$ 条线段的右端点为 $i$。此时有两种来源，需要累加：将原先以 $i-1$ 为右端点的第 $j$ 条线段延长到 $i$（长度大于 $1$）；或者在前 $i-1$ 个点上已有 $j-1$ 条线段的基础上，新开一条覆盖 $i-1$ 与 $i$ 的线段（长度为 $1$）。当 $j=0$ 时不存在右端点，故第二项不计入。因此当 $j \ge 1$ 时有：

$$
g[i][j] = g[i - 1][j] + f[i - 1][j - 1] + g[i - 1][j - 1]
$$

答案为 $f[n][k]+g[n][k]$。

时间复杂度 $O(n\times k)$，空间复杂度 $O(n\times k)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfSets(self, n: int, k: int) -> int:
        mod = 10**9 + 7
        f = [[0] * (k + 1) for _ in range(n + 1)]
        g = [[0] * (k + 1) for _ in range(n + 1)]
        f[1][0] = 1
        for i in range(2, n + 1):
            for j in range(k + 1):
                f[i][j] = (f[i - 1][j] + g[i - 1][j]) % mod
                g[i][j] = g[i - 1][j]
                if j:
                    g[i][j] += f[i - 1][j - 1] + g[i - 1][j - 1]
                    g[i][j] %= mod
        return (f[n][k] + g[n][k]) % mod
```

#### Java

```java
class Solution {
    public int numberOfSets(int n, int k) {
        final int mod = (int) 1e9 + 7;
        int[][] f = new int[n + 1][k + 1];
        int[][] g = new int[n + 1][k + 1];
        f[1][0] = 1;
        for (int i = 2; i <= n; ++i) {
            for (int j = 0; j <= k; ++j) {
                f[i][j] = (f[i - 1][j] + g[i - 1][j]) % mod;
                g[i][j] = g[i - 1][j];
                if (j > 0) {
                    g[i][j] = (g[i][j] + f[i - 1][j - 1]) % mod;
                    g[i][j] = (g[i][j] + g[i - 1][j - 1]) % mod;
                }
            }
        }
        return (f[n][k] + g[n][k]) % mod;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numberOfSets(int n, int k) {
        const int mod = 1e9 + 7;
        vector<vector<int>> f(n + 1, vector<int>(k + 1));
        vector<vector<int>> g(n + 1, vector<int>(k + 1));
        f[1][0] = 1;
        for (int i = 2; i <= n; ++i) {
            for (int j = 0; j <= k; ++j) {
                f[i][j] = (f[i - 1][j] + g[i - 1][j]) % mod;
                g[i][j] = g[i - 1][j];
                if (j) {
                    g[i][j] = (g[i][j] + f[i - 1][j - 1]) % mod;
                    g[i][j] = (g[i][j] + g[i - 1][j - 1]) % mod;
                }
            }
        }
        return (f[n][k] + g[n][k]) % mod;
    }
};
```

#### Go

```go
func numberOfSets(n int, k int) int {
	const mod int = 1e9 + 7
	f := make([][]int, n+1)
	g := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, k+1)
		g[i] = make([]int, k+1)
	}
	f[1][0] = 1
	for i := 2; i <= n; i++ {
		for j := 0; j <= k; j++ {
			f[i][j] = (f[i-1][j] + g[i-1][j]) % mod
			g[i][j] = g[i-1][j]
			if j > 0 {
				g[i][j] = (g[i][j] + f[i-1][j-1]) % mod
				g[i][j] = (g[i][j] + g[i-1][j-1]) % mod
			}
		}
	}
	return (f[n][k] + g[n][k]) % mod
}
```

#### TypeScript

```ts
function numberOfSets(n: number, k: number): number {
    const mod = 10 ** 9 + 7;
    const f: number[][] = Array.from({ length: n + 1 }, () => Array(k + 1).fill(0));
    const g: number[][] = Array.from({ length: n + 1 }, () => Array(k + 1).fill(0));
    f[1][0] = 1;
    for (let i = 2; i <= n; ++i) {
        for (let j = 0; j <= k; ++j) {
            f[i][j] = (f[i - 1][j] + g[i - 1][j]) % mod;
            g[i][j] = g[i - 1][j];
            if (j) {
                g[i][j] = (g[i][j] + f[i - 1][j - 1]) % mod;
                g[i][j] = (g[i][j] + g[i - 1][j - 1]) % mod;
            }
        }
    }
    return (f[n][k] + g[n][k]) % mod;
}
```

#### Rust

```rust
impl Solution {
    pub fn number_of_sets(n: i32, k: i32) -> i32 {
        const MOD: i64 = 1_000_000_007;
        let n = n as usize;
        let k = k as usize;
        let mut f = vec![vec![0i64; k + 1]; n + 1];
        let mut g = vec![vec![0i64; k + 1]; n + 1];
        f[1][0] = 1;
        for i in 2..=n {
            for j in 0..=k {
                f[i][j] = (f[i - 1][j] + g[i - 1][j]) % MOD;
                g[i][j] = g[i - 1][j];
                if j > 0 {
                    g[i][j] = (g[i][j] + f[i - 1][j - 1]) % MOD;
                    g[i][j] = (g[i][j] + g[i - 1][j - 1]) % MOD;
                }
            }
        }
        ((f[n][k] + g[n][k]) % MOD) as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
