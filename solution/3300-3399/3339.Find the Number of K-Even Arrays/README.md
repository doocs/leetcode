---
comments: true
difficulty: 中等
tags:
    - 动态规划
---

<!-- problem:start -->

# [3339. 查找 K 偶数数组的数量 🔒](https://leetcode.cn/problems/find-the-number-of-k-even-arrays)

[English Version](/solution/3300-3399/3339.Find%20the%20Number%20of%20K-Even%20Arrays/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定三个整数&nbsp;<code>n</code>，<code>m</code>&nbsp;和&nbsp;<code>k</code>。</p>

<p>一个数组&nbsp;<code>arr</code>&nbsp;如果 <strong>恰好</strong>&nbsp;有&nbsp;<code>k</code>&nbsp;个下标，其中的每个下标&nbsp;<code>i</code> (<code>0 &lt;= i &lt; n - 1</code>) 满足下述条件，则被称为是 <strong>K 偶数</strong>的：</p>

<ul>
	<li><code>(arr[i] * arr[i + 1]) - arr[i] - arr[i + 1]</code>&nbsp;是偶数。</li>
</ul>

<p>返回长度为 <code>n</code>&nbsp;的满足&nbsp;<strong>K 偶数</strong> 的数组的数量，其中所有元素的范围在&nbsp;<code>[1, m]</code>。</p>

<p>因为答案可能很大，返回答案对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;取模。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">n = 3, m = 4, k = 2</span></p>

<p><span class="example-io"><b>输出：</b>8</span></p>

<p><strong>解释：</strong></p>

<p>8 个满足的 2 偶数的数组是：</p>

<ul>
	<li><code>[2, 2, 2]</code></li>
	<li><code>[2, 2, 4]</code></li>
	<li><code>[2, 4, 2]</code></li>
	<li><code>[2, 4, 4]</code></li>
	<li><code>[4, 2, 2]</code></li>
	<li><code>[4, 2, 4]</code></li>
	<li><code>[4, 4, 2]</code></li>
	<li><code>[4, 4, 4]</code></li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">n = 5, m = 1, k = 0</span></p>

<p><span class="example-io"><b>输出：</b>1</span></p>

<p><strong>解释：</strong></p>

<p>仅有的 0 偶数的数组是&nbsp;<code>[1, 1, 1, 1, 1]</code>。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">n = 7, m = 7, k = 5</span></p>

<p><span class="example-io"><b>输出：</b>5832</span></p>
</div>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>1 &lt;= n &lt;= 750</code></li>
	<li><code>0 &lt;= k &lt;= n - 1</code></li>
	<li><code>1 &lt;= m &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：记忆化搜索

<!-- thinking:start -->

> **思考**
>
> 长度为 $n$、值在 $[1,m]$ 的数组中，相邻偶数对恰好 $k$ 个。奇偶比具体数值更重要：偶数 $\lfloor m/2 \rfloor$ 个，奇数 $m-\lfloor m/2 \rfloor$ 个。
>
> 状态 $(i,j,\textit{last})$ 表示已填 $i$ 位、还剩 $j$ 个偶数对、上一位奇偶。填偶数时仅当上一位也是偶数才消耗一次。
>
> 边界：$j<0$ 为 $0$；填满时 $j=0$ 计 $1$。初始上一位视为奇数，以免凭空计对。

<!-- thinking:end -->

由于有 $[1, m]$ 个数，那么偶数有 $\textit{cnt0} = \lfloor \frac{m}{2} \rfloor$ 个，奇数有 $\textit{cnt1} = m - \textit{cnt0}$ 个。

我们设计一个函数 $\textit{dfs}(i, j, k)$，表示当前已经填到第 $i$ 个位置，剩余 $j$ 个位置需要满足条件，且上一个位置的奇偶性为 $k$ 的方案数，其中 $k = 0$ 表示上一个位置为偶数，而 $k = 1$ 表示上一个位置为奇数。那么答案就是 $\textit{dfs}(0, k, 1)$。

函数 $\textit{dfs}(i, j, k)$ 的执行逻辑如下：

- 如果 $j < 0$，表示剩余位置数小于 $0$，直接返回 $0$；
- 如果 $i \ge n$，表示已经填完了，如果此时 $j = 0$，表示满足条件，返回 $1$，否则返回 $0$；
- 否则，我们可以选择填奇数或者偶数，分别计算填奇数和填偶数的方案数，最后返回两者之和。

时间复杂度 $O(n \times k)$，空间复杂度 $O(n \times k)$。其中 $n$ 和 $k$ 为题目给定的参数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countOfArrays(self, n: int, m: int, k: int) -> int:
        @cache
        def dfs(i: int, j: int, k: int) -> int:
            if j < 0:
                return 0
            if i >= n:
                return int(j == 0)
            return (
                cnt1 * dfs(i + 1, j, 1) + cnt0 * dfs(i + 1, j - (k & 1 ^ 1), 0)
            ) % mod

        cnt0 = m // 2
        cnt1 = m - cnt0
        mod = 10**9 + 7
        ans = dfs(0, k, 1)
        dfs.cache_clear()
        return ans
```

#### Java

```java
class Solution {
    private Integer[][][] f;
    private long cnt0, cnt1;
    private final int mod = (int) 1e9 + 7;

    public int countOfArrays(int n, int m, int k) {
        f = new Integer[n][k + 1][2];
        cnt0 = m / 2;
        cnt1 = m - cnt0;
        return dfs(0, k, 1);
    }

    private int dfs(int i, int j, int k) {
        if (j < 0) {
            return 0;
        }
        if (i >= f.length) {
            return j == 0 ? 1 : 0;
        }
        if (f[i][j][k] != null) {
            return f[i][j][k];
        }
        int a = (int) (cnt1 * dfs(i + 1, j, 1) % mod);
        int b = (int) (cnt0 * dfs(i + 1, j - (k & 1 ^ 1), 0) % mod);
        return f[i][j][k] = (a + b) % mod;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countOfArrays(int n, int m, int k) {
        int f[n][k + 1][2];
        memset(f, -1, sizeof(f));
        const int mod = 1e9 + 7;
        int cnt0 = m / 2;
        int cnt1 = m - cnt0;
        auto dfs = [&](this auto&& dfs, int i, int j, int k) -> int {
            if (j < 0) {
                return 0;
            }
            if (i >= n) {
                return j == 0 ? 1 : 0;
            }
            if (f[i][j][k] != -1) {
                return f[i][j][k];
            }
            int a = 1LL * cnt1 * dfs(i + 1, j, 1) % mod;
            int b = 1LL * cnt0 * dfs(i + 1, j - (k & 1 ^ 1), 0) % mod;
            return f[i][j][k] = (a + b) % mod;
        };
        return dfs(0, k, 1);
    }
};
```

#### Go

```go
func countOfArrays(n int, m int, k int) int {
	f := make([][][2]int, n)
	for i := range f {
		f[i] = make([][2]int, k+1)
		for j := range f[i] {
			f[i][j] = [2]int{-1, -1}
		}
	}
	const mod int = 1e9 + 7
	cnt0 := m / 2
	cnt1 := m - cnt0
	var dfs func(int, int, int) int
	dfs = func(i, j, k int) int {
		if j < 0 {
			return 0
		}
		if i >= n {
			if j == 0 {
				return 1
			}
			return 0
		}
		if f[i][j][k] != -1 {
			return f[i][j][k]
		}
		a := cnt1 * dfs(i+1, j, 1) % mod
		b := cnt0 * dfs(i+1, j-(k&1^1), 0) % mod
		f[i][j][k] = (a + b) % mod
		return f[i][j][k]
	}
	return dfs(0, k, 1)
}
```

#### TypeScript

```ts
function countOfArrays(n: number, m: number, k: number): number {
    const f = Array.from({ length: n }, () =>
        Array.from({ length: k + 1 }, () => Array(2).fill(-1)),
    );
    const mod = 1e9 + 7;
    const cnt0 = Math.floor(m / 2);
    const cnt1 = m - cnt0;
    const dfs = (i: number, j: number, k: number): number => {
        if (j < 0) {
            return 0;
        }
        if (i >= n) {
            return j === 0 ? 1 : 0;
        }
        if (f[i][j][k] !== -1) {
            return f[i][j][k];
        }
        const a = (cnt1 * dfs(i + 1, j, 1)) % mod;
        const b = (cnt0 * dfs(i + 1, j - ((k & 1) ^ 1), 0)) % mod;
        return (f[i][j][k] = (a + b) % mod);
    };
    return dfs(0, k, 1);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：动态规划

<!-- thinking:start -->

> **思考**
>
> 方法一的递归在部分语言中常数偏大。同一转移可按层递推：$f[i][j][0/1]$ 表示填完 $i$ 位、已有 $j$ 个偶数对、末位奇偶。
>
> 填偶数从「末位奇」或「已有 $j-1$ 对且末位偶」转移；填奇数不增加对数。初始 $f[0][0][1]=1$ 与搜索的起点一致。
>
> 答案为 $f[n][k][0]+f[n][k][1]$。

<!-- thinking:end -->

我们可以将方法一的记忆化搜索转换为动态规划。

定义 $f[i][j][k]$ 表示当前已经填完第 $i$ 个位置，且有 $j$ 个位置满足条件，且上一个位置的奇偶性为 $k$ 的方案数。那么答案就是 $\sum_{k = 0}^{1} f[n][k]$。

初始时，我们将 $f[0][0][1]$ 置为 $1$，表示填完第 $0$ 个位置，且有 $0$ 个位置满足条件，且上一个位置的奇偶性为奇数的方案数为 $1$。其余 $f[i][j][k] = 0$。

状态转移方程如下：

$$
\begin{aligned}
f[i][j][0] &= \left( f[i - 1][j][1] + \left( f[i - 1][j - 1][0] \text{ if } j > 0 \right) \right) \times \textit{cnt0} \bmod \textit{mod}, \\
f[i][j][1] &= \left( f[i - 1][j][0] + f[i - 1][j][1] \right) \times \textit{cnt1} \bmod \textit{mod}.
\end{aligned}
$$

时间复杂度 $O(n \times k)$，空间复杂度 $O(n \times k)$。其中 $n$ 和 $k$ 为题目给定的参数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countOfArrays(self, n: int, m: int, k: int) -> int:
        f = [[[0] * 2 for _ in range(k + 1)] for _ in range(n + 1)]
        cnt0 = m // 2
        cnt1 = m - cnt0
        mod = 10**9 + 7
        f[0][0][1] = 1
        for i in range(1, n + 1):
            for j in range(k + 1):
                f[i][j][0] = (
                    (f[i - 1][j][1] + (f[i - 1][j - 1][0] if j else 0)) * cnt0 % mod
                )
                f[i][j][1] = (f[i - 1][j][0] + f[i - 1][j][1]) * cnt1 % mod
        return sum(f[n][k]) % mod
```

#### Java

```java
class Solution {
    public int countOfArrays(int n, int m, int k) {
        int[][][] f = new int[n + 1][k + 1][2];
        int cnt0 = m / 2;
        int cnt1 = m - cnt0;
        final int mod = (int) 1e9 + 7;
        f[0][0][1] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= k; ++j) {
                f[i][j][0]
                    = (int) (1L * cnt0 * (f[i - 1][j][1] + (j > 0 ? f[i - 1][j - 1][0] : 0)) % mod);
                f[i][j][1] = (int) (1L * cnt1 * (f[i - 1][j][0] + f[i - 1][j][1]) % mod);
            }
        }
        return (f[n][k][0] + f[n][k][1]) % mod;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countOfArrays(int n, int m, int k) {
        int f[n + 1][k + 1][2];
        memset(f, 0, sizeof(f));
        f[0][0][1] = 1;
        const int mod = 1e9 + 7;
        int cnt0 = m / 2;
        int cnt1 = m - cnt0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= k; ++j) {
                f[i][j][0] = 1LL * (f[i - 1][j][1] + (j ? f[i - 1][j - 1][0] : 0)) * cnt0 % mod;
                f[i][j][1] = 1LL * (f[i - 1][j][0] + f[i - 1][j][1]) * cnt1 % mod;
            }
        }
        return (f[n][k][0] + f[n][k][1]) % mod;
    }
};
```

#### Go

```go
func countOfArrays(n int, m int, k int) int {
	f := make([][][2]int, n+1)
	for i := range f {
		f[i] = make([][2]int, k+1)
	}
	f[0][0][1] = 1
	cnt0 := m / 2
	cnt1 := m - cnt0
	const mod int = 1e9 + 7
	for i := 1; i <= n; i++ {
		for j := 0; j <= k; j++ {
			f[i][j][0] = cnt0 * f[i-1][j][1] % mod
			if j > 0 {
				f[i][j][0] = (f[i][j][0] + cnt0*f[i-1][j-1][0]%mod) % mod
			}
			f[i][j][1] = cnt1 * (f[i-1][j][0] + f[i-1][j][1]) % mod
		}
	}
	return (f[n][k][0] + f[n][k][1]) % mod
}
```

#### TypeScript

```ts
function countOfArrays(n: number, m: number, k: number): number {
    const f: number[][][] = Array.from({ length: n + 1 }, () =>
        Array.from({ length: k + 1 }, () => Array(2).fill(0)),
    );
    f[0][0][1] = 1;
    const mod = 1e9 + 7;
    const cnt0 = Math.floor(m / 2);
    const cnt1 = m - cnt0;
    for (let i = 1; i <= n; ++i) {
        for (let j = 0; j <= k; ++j) {
            f[i][j][0] = (cnt0 * (f[i - 1][j][1] + (j ? f[i - 1][j - 1][0] : 0))) % mod;
            f[i][j][1] = (cnt1 * (f[i - 1][j][0] + f[i - 1][j][1])) % mod;
        }
    }
    return (f[n][k][0] + f[n][k][1]) % mod;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法三：动态规划（空间优化）

<!-- thinking:start -->

> **思考**
>
> $f[i]$ 只依赖 $f[i-1]$，三维数组中的第一维可以去掉。
>
> 用 $g$ 接收本层转移后整体替换 $f$，空间降为 $O(k)$，时间阶不变。
>
> 答案仍为最后一层 $j=k$ 的两个奇偶状态之和。

<!-- thinking:end -->

我们注意到，对于 $f[i]$ 的计算只与 $f[i - 1]$ 有关，因此我们可以使用滚动数组优化空间。

时间复杂度 $O(n \times k)$，空间复杂度 $O(k)$。其中 $n$ 和 $k$ 为题目给定的参数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countOfArrays(self, n: int, m: int, k: int) -> int:
        f = [[0] * 2 for _ in range(k + 1)]
        cnt0 = m // 2
        cnt1 = m - cnt0
        mod = 10**9 + 7
        f[0][1] = 1
        for _ in range(n):
            g = [[0] * 2 for _ in range(k + 1)]
            for j in range(k + 1):
                g[j][0] = (f[j][1] + (f[j - 1][0] if j else 0)) * cnt0 % mod
                g[j][1] = (f[j][0] + f[j][1]) * cnt1 % mod
            f = g
        return sum(f[k]) % mod
```

#### Java

```java
class Solution {
    public int countOfArrays(int n, int m, int k) {
        int[][] f = new int[k + 1][2];
        int cnt0 = m / 2;
        int cnt1 = m - cnt0;
        final int mod = (int) 1e9 + 7;
        f[0][1] = 1;
        for (int i = 0; i < n; ++i) {
            int[][] g = new int[k + 1][2];
            for (int j = 0; j <= k; ++j) {
                g[j][0] = (int) (1L * cnt0 * (f[j][1] + (j > 0 ? f[j - 1][0] : 0)) % mod);
                g[j][1] = (int) (1L * cnt1 * (f[j][0] + f[j][1]) % mod);
            }
            f = g;
        }
        return (f[k][0] + f[k][1]) % mod;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countOfArrays(int n, int m, int k) {
        vector<vector<int>> f(k + 1, vector<int>(2));
        int cnt0 = m / 2;
        int cnt1 = m - cnt0;
        const int mod = 1e9 + 7;
        f[0][1] = 1;

        for (int i = 0; i < n; ++i) {
            vector<vector<int>> g(k + 1, vector<int>(2));
            for (int j = 0; j <= k; ++j) {
                g[j][0] = (1LL * cnt0 * (f[j][1] + (j > 0 ? f[j - 1][0] : 0)) % mod) % mod;
                g[j][1] = (1LL * cnt1 * (f[j][0] + f[j][1]) % mod) % mod;
            }
            f = g;
        }
        return (f[k][0] + f[k][1]) % mod;
    }
};
```

#### Go

```go
func countOfArrays(n int, m int, k int) int {
	const mod = 1e9 + 7
	cnt0 := m / 2
	cnt1 := m - cnt0
	f := make([][2]int, k+1)
	f[0][1] = 1

	for i := 0; i < n; i++ {
		g := make([][2]int, k+1)
		for j := 0; j <= k; j++ {
			g[j][0] = (cnt0 * (f[j][1] + func() int {
				if j > 0 {
					return f[j-1][0]
				}
				return 0
			}()) % mod) % mod
			g[j][1] = (cnt1 * (f[j][0] + f[j][1]) % mod) % mod
		}
		f = g
	}

	return (f[k][0] + f[k][1]) % mod
}
```

#### TypeScript

```ts
function countOfArrays(n: number, m: number, k: number): number {
    const mod = 1e9 + 7;
    const cnt0 = Math.floor(m / 2);
    const cnt1 = m - cnt0;
    const f: number[][] = Array.from({ length: k + 1 }, () => [0, 0]);
    f[0][1] = 1;
    for (let i = 0; i < n; i++) {
        const g: number[][] = Array.from({ length: k + 1 }, () => [0, 0]);
        for (let j = 0; j <= k; j++) {
            g[j][0] = ((cnt0 * (f[j][1] + (j > 0 ? f[j - 1][0] : 0))) % mod) % mod;
            g[j][1] = ((cnt1 * (f[j][0] + f[j][1])) % mod) % mod;
        }
        f.splice(0, f.length, ...g);
    }
    return (f[k][0] + f[k][1]) % mod;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
