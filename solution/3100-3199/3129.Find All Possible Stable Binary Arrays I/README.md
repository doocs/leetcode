---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3129.Find%20All%20Possible%20Stable%20Binary%20Arrays%20I/README.md
rating: 2200
source: 第 129 场双周赛 Q3
tags:
    - 动态规划
    - 前缀和
---

<!-- problem:start -->

# [3129. 找出所有稳定的二进制数组 I](https://leetcode.cn/problems/find-all-possible-stable-binary-arrays-i)

[English Version](/solution/3100-3199/3129.Find%20All%20Possible%20Stable%20Binary%20Arrays%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你 3 个正整数&nbsp;<code>zero</code>&nbsp;，<code>one</code>&nbsp;和&nbsp;<code>limit</code>&nbsp;。</p>

<p>一个 <span data-keyword="binary-array">二进制数组</span> <code>arr</code> 如果满足以下条件，那么我们称它是 <strong>稳定的</strong> ：</p>

<ul>
	<li>0 在&nbsp;<code>arr</code>&nbsp;中出现次数 <strong>恰好</strong>&nbsp;为<strong>&nbsp;</strong><code>zero</code>&nbsp;。</li>
	<li>1 在&nbsp;<code>arr</code>&nbsp;中出现次数 <strong>恰好</strong>&nbsp;为&nbsp;<code>one</code>&nbsp;。</li>
	<li><code>arr</code> 中每个长度超过 <code>limit</code>&nbsp;的 <span data-keyword="subarray-nonempty">子数组</span> 都 <strong>同时</strong> 包含 0 和 1 。</li>
</ul>

<p>请你返回 <strong>稳定</strong>&nbsp;二进制数组的 <em>总</em> 数目。</p>

<p>由于答案可能很大，将它对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;<b>取余</b>&nbsp;后返回。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>zero = 1, one = 1, limit = 2</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><strong>解释：</strong></p>

<p>两个稳定的二进制数组为&nbsp;<code>[1,0]</code> 和&nbsp;<code>[0,1]</code>&nbsp;，两个数组都有一个 0 和一个 1 ，且没有子数组长度大于 2 。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">zero = 1, one = 2, limit = 1</span></p>

<p><span class="example-io"><b>输出：</b>1</span></p>

<p><strong>解释：</strong></p>

<p>唯一稳定的二进制数组是&nbsp;<code>[1,0,1]</code>&nbsp;。</p>

<p>二进制数组&nbsp;<code>[1,1,0]</code> 和&nbsp;<code>[0,1,1]</code>&nbsp;都有长度为 2 且元素全都相同的子数组，所以它们不稳定。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>zero = 3, one = 3, limit = 2</span></p>

<p><span class="example-io"><b>输出：</b>14</span></p>

<p><strong>解释：</strong></p>

<p>所有稳定的二进制数组包括&nbsp;<code>[0,0,1,0,1,1]</code>&nbsp;，<code>[0,0,1,1,0,1]</code>&nbsp;，<code>[0,1,0,0,1,1]</code>&nbsp;，<code>[0,1,0,1,0,1]</code>&nbsp;，<code>[0,1,0,1,1,0]</code>&nbsp;，<code>[0,1,1,0,0,1]</code>&nbsp;，<code>[0,1,1,0,1,0]</code>&nbsp;，<code>[1,0,0,1,0,1]</code>&nbsp;，<code>[1,0,0,1,1,0]</code>&nbsp;，<code>[1,0,1,0,0,1]</code>&nbsp;，<code>[1,0,1,0,1,0]</code>&nbsp;，<code>[1,0,1,1,0,0]</code>&nbsp;，<code>[1,1,0,0,1,0]</code>&nbsp;和&nbsp;<code>[1,1,0,1,0,0]</code>&nbsp;。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= zero, one, limit &lt;= 200</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：记忆化搜索

我们设计一个函数 $dfs(i, j, k)$ 表示还剩下 $i$ 个 $0$ 和 $j$ 个 $1$ 且接下来待填的数字是 $k$ 的情况下，满足题目条件的稳定二进制数组的个数。那么答案就是 $dfs(zero, one, 0) + dfs(zero, one, 1)$。

函数 $dfs(i, j, k)$ 的计算过程如下：

-   如果 $i \lt 0$ 或 $j \lt 0$，返回 $0$。
-   如果 $i = 0$，那么当 $k = 1$ 且 $j \leq \text{limit}$ 时返回 $1$，否则返回 $0$。
-   如果 $j = 0$，那么当 $k = 0$ 且 $i \leq \text{limit}$ 时返回 $1$，否则返回 $0$。
-   如果 $k = 0$，我们考虑前一个数字是 $0$ 的情况 $dfs(i - 1, j, 0)$ 和前一个数字是 $1$ 的情况 $dfs(i - 1, j, 1)$，如果前一个数是 $0$，那么有可能使得子数组中有超过 $\text{limit}$ 个 $0$，即不允许出现倒数第 $\text{limit} + 1$ 个数是 $1$ 的情况，所以我们要减去这种情况，即 $dfs(i - \text{limit} - 1, j, 1)$。
-   如果 $k = 1$，我们考虑前一个数字是 $0$ 的情况 $dfs(i, j - 1, 0)$ 和前一个数字是 $1$ 的情况 $dfs(i, j - 1, 1)$，如果前一个数是 $1$，那么有可能使得子数组中有超过 $\text{limit}$ 个 $1$，即不允许出现倒数第 $\text{limit} + 1$ 个数是 $0$ 的情况，所以我们要减去这种情况，即 $dfs(i, j - \text{limit} - 1, 0)$。

为了避免重复计算，我们使用记忆化搜索的方法。

时间复杂度 $O(zero \times one)$，空间复杂度 $O(zero \times one)$。

<!-- tabs:start -->

```python
class Solution:
    def numberOfStableArrays(self, zero: int, one: int, limit: int) -> int:
        @cache
        def dfs(i: int, j: int, k: int) -> int:
            if i == 0:
                return int(k == 1 and j <= limit)
            if j == 0:
                return int(k == 0 and i <= limit)
            if k == 0:
                return (
                    dfs(i - 1, j, 0)
                    + dfs(i - 1, j, 1)
                    - (0 if i - limit - 1 < 0 else dfs(i - limit - 1, j, 1))
                )
            return (
                dfs(i, j - 1, 0)
                + dfs(i, j - 1, 1)
                - (0 if j - limit - 1 < 0 else dfs(i, j - limit - 1, 0))
            )

        mod = 10**9 + 7
        ans = (dfs(zero, one, 0) + dfs(zero, one, 1)) % mod
        dfs.cache_clear()
        return ans
```

```java
class Solution {
    private final int mod = (int) 1e9 + 7;
    private Long[][][] f;
    private int limit;

    public int numberOfStableArrays(int zero, int one, int limit) {
        f = new Long[zero + 1][one + 1][2];
        this.limit = limit;
        return (int) ((dfs(zero, one, 0) + dfs(zero, one, 1)) % mod);
    }

    private long dfs(int i, int j, int k) {
        if (i < 0 || j < 0) {
            return 0;
        }
        if (i == 0) {
            return k == 1 && j <= limit ? 1 : 0;
        }
        if (j == 0) {
            return k == 0 && i <= limit ? 1 : 0;
        }
        if (f[i][j][k] != null) {
            return f[i][j][k];
        }
        if (k == 0) {
            f[i][j][k]
                = (dfs(i - 1, j, 0) + dfs(i - 1, j, 1) - dfs(i - limit - 1, j, 1) + mod) % mod;
        } else {
            f[i][j][k]
                = (dfs(i, j - 1, 0) + dfs(i, j - 1, 1) - dfs(i, j - limit - 1, 0) + mod) % mod;
        }
        return f[i][j][k];
    }
}
```

```cpp
using ll = long long;

class Solution {
public:
    int numberOfStableArrays(int zero, int one, int limit) {
        this->limit = limit;
        f = vector<vector<array<ll, 2>>>(zero + 1, vector<array<ll, 2>>(one + 1, {-1, -1}));
        return (dfs(zero, one, 0) + dfs(zero, one, 1)) % mod;
    }

private:
    const int mod = 1e9 + 7;
    int limit;
    vector<vector<array<ll, 2>>> f;

    ll dfs(int i, int j, int k) {
        if (i < 0 || j < 0) {
            return 0;
        }
        if (i == 0) {
            return k == 1 && j <= limit;
        }
        if (j == 0) {
            return k == 0 && i <= limit;
        }
        ll& res = f[i][j][k];
        if (res != -1) {
            return res;
        }
        if (k == 0) {
            res = (dfs(i - 1, j, 0) + dfs(i - 1, j, 1) - dfs(i - limit - 1, j, 1) + mod) % mod;
        } else {
            res = (dfs(i, j - 1, 0) + dfs(i, j - 1, 1) - dfs(i, j - limit - 1, 0) + mod) % mod;
        }
        return res;
    }
};
```

```go
func numberOfStableArrays(zero int, one int, limit int) int {
	const mod int = 1e9 + 7
	f := make([][][2]int, zero+1)
	for i := range f {
		f[i] = make([][2]int, one+1)
		for j := range f[i] {
			f[i][j] = [2]int{-1, -1}
		}
	}
	var dfs func(i, j, k int) int
	dfs = func(i, j, k int) int {
		if i < 0 || j < 0 {
			return 0
		}
		if i == 0 {
			if k == 1 && j <= limit {
				return 1
			}
			return 0
		}
		if j == 0 {
			if k == 0 && i <= limit {
				return 1
			}
			return 0
		}
		res := &f[i][j][k]
		if *res != -1 {
			return *res
		}
		if k == 0 {
			*res = (dfs(i-1, j, 0) + dfs(i-1, j, 1) - dfs(i-limit-1, j, 1) + mod) % mod
		} else {
			*res = (dfs(i, j-1, 0) + dfs(i, j-1, 1) - dfs(i, j-limit-1, 0) + mod) % mod
		}
		return *res
	}
	return (dfs(zero, one, 0) + dfs(zero, one, 1)) % mod
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：动态规划

我们也可以将方法一的记忆化搜索转换为动态规划。

我们定义 $f[i][j][k]$ 表示使用 $i$ 个 $0$ 和 $j$ 个 $1$ 且最后一个数字是 $k$ 的稳定二进制数组的个数。那么答案就是 $f[zero][one][0] + f[zero][one][1]$。

初始时，我们有 $f[i][0][0] = 1$，其中 $1 \leq i \leq \min(\text{limit}, \text{zero})$；有 $f[0][j][1] = 1$，其中 $1 \leq j \leq \min(\text{limit}, \text{one})$。

状态转移方程如下：

-   $f[i][j][0] = f[i - 1][j][0] + f[i - 1][j][1] - f[i - \text{limit} - 1][j][1]$。
-   $f[i][j][1] = f[i][j - 1][0] + f[i][j - 1][1] - f[i][j - \text{limit} - 1][0]$。

时间复杂度 $O(zero \times one)$，空间复杂度 $O(zero \times one)$。

<!-- tabs:start -->

```python
class Solution:
    def numberOfStableArrays(self, zero: int, one: int, limit: int) -> int:
        mod = 10**9 + 7
        f = [[[0, 0] for _ in range(one + 1)] for _ in range(zero + 1)]
        for i in range(1, min(limit, zero) + 1):
            f[i][0][0] = 1
        for j in range(1, min(limit, one) + 1):
            f[0][j][1] = 1
        for i in range(1, zero + 1):
            for j in range(1, one + 1):
                f[i][j][0] = (
                    f[i - 1][j][0]
                    + f[i - 1][j][1]
                    - (0 if i - limit - 1 < 0 else f[i - limit - 1][j][1])
                ) % mod
                f[i][j][1] = (
                    f[i][j - 1][0]
                    + f[i][j - 1][1]
                    - (0 if j - limit - 1 < 0 else f[i][j - limit - 1][0])
                ) % mod
        return sum(f[zero][one]) % mod
```

```java
class Solution {
    public int numberOfStableArrays(int zero, int one, int limit) {
        final int mod = (int) 1e9 + 7;
        long[][][] f = new long[zero + 1][one + 1][2];
        for (int i = 1; i <= Math.min(zero, limit); ++i) {
            f[i][0][0] = 1;
        }
        for (int j = 1; j <= Math.min(one, limit); ++j) {
            f[0][j][1] = 1;
        }
        for (int i = 1; i <= zero; ++i) {
            for (int j = 1; j <= one; ++j) {
                f[i][j][0] = (f[i - 1][j][0] + f[i - 1][j][1]
                                 - (i - limit - 1 < 0 ? 0 : f[i - limit - 1][j][1]) + mod)
                    % mod;
                f[i][j][1] = (f[i][j - 1][0] + f[i][j - 1][1]
                                 - (j - limit - 1 < 0 ? 0 : f[i][j - limit - 1][0]) + mod)
                    % mod;
            }
        }
        return (int) ((f[zero][one][0] + f[zero][one][1]) % mod);
    }
}
```

```cpp
class Solution {
public:
    int numberOfStableArrays(int zero, int one, int limit) {
        const int mod = 1e9 + 7;
        using ll = long long;
        ll f[zero + 1][one + 1][2];
        memset(f, 0, sizeof(f));
        for (int i = 1; i <= min(zero, limit); ++i) {
            f[i][0][0] = 1;
        }
        for (int j = 1; j <= min(one, limit); ++j) {
            f[0][j][1] = 1;
        }
        for (int i = 1; i <= zero; ++i) {
            for (int j = 1; j <= one; ++j) {
                f[i][j][0] = (f[i - 1][j][0] + f[i - 1][j][1]
                                 - (i - limit - 1 < 0 ? 0 : f[i - limit - 1][j][1]) + mod)
                    % mod;
                f[i][j][1] = (f[i][j - 1][0] + f[i][j - 1][1]
                                 - (j - limit - 1 < 0 ? 0 : f[i][j - limit - 1][0]) + mod)
                    % mod;
            }
        }
        return (f[zero][one][0] + f[zero][one][1]) % mod;
    }
};
```

```go
func numberOfStableArrays(zero int, one int, limit int) int {
	const mod int = 1e9 + 7
	f := make([][][2]int, zero+1)
	for i := range f {
		f[i] = make([][2]int, one+1)
	}
	for i := 1; i <= min(zero, limit); i++ {
		f[i][0][0] = 1
	}
	for j := 1; j <= min(one, limit); j++ {
		f[0][j][1] = 1
	}
	for i := 1; i <= zero; i++ {
		for j := 1; j <= one; j++ {
			f[i][j][0] = (f[i-1][j][0] + f[i-1][j][1]) % mod
			if i-limit-1 >= 0 {
				f[i][j][0] = (f[i][j][0] - f[i-limit-1][j][1] + mod) % mod
			}
			f[i][j][1] = (f[i][j-1][0] + f[i][j-1][1]) % mod
			if j-limit-1 >= 0 {
				f[i][j][1] = (f[i][j][1] - f[i][j-limit-1][0] + mod) % mod
			}
		}
	}
	return (f[zero][one][0] + f[zero][one][1]) % mod
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
