---
comments: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcp/LCP%2025.%20%E5%8F%A4%E8%91%A3%E9%94%AE%E7%9B%98/README.md
---

<!-- problem:start -->

# [LCP 25. 古董键盘](https://leetcode.cn/problems/Uh984O)

## 题目描述

<!-- description:start -->

小扣在秋日市集购买了一个古董键盘。由于古董键盘年久失修，键盘上只有 26 个字母 **a~z** 可以按下，且每个字母最多仅能被按 `k` 次。

小扣随机按了 `n` 次按键，请返回小扣总共有可能按出多少种内容。由于数字较大，最终答案需要对 1000000007 (1e9 + 7) 取模。

**示例 1：**

> 输入：`k = 1, n = 1`
>
> 输出：`26`
>
> 解释：由于只能按一次按键，所有可能的字符串为 "a", "b", ... "z"

**示例 2：**

> 输入：`k = 1, n = 2`
>
> 输出：`650`
>
> 解释：由于只能按两次按键，且每个键最多只能按一次，所有可能的字符串（按字典序排序）为 "ab", "ac", ... "zy"

**提示：**

-   `1 <= k <= 5`
-   `1 <= n <= 26*k`

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

我们定义 $f[i][j]$ 表示按了 $i$ 次按键，且使用了前 $j$ 个字母的方案数。初始时 $f[0]$ 全部为 $1$，表示没有按键时只有一种方案，其余 $f[i][j] = 0$。

对于 $f[i][j]$，我们考虑其转移方式。我们可以枚举第 $j$ 个字母一共使用了多少次，设为 $h$，其中 $0 \leq h \leq \min(i, k)$，那么我们可以从 $f[i - h][j - 1]$ 转移过来，且第 $j$ 个字母可以在 $i$ 次按键中使用 $h$ 次，方案数为 $\binom{i}{h}$。即：

$$
f[i][j] = \sum_{h = 0}^{\min(i, k)} f[i - h][j - 1] \cdot \binom{i}{h}
$$

最终答案即为 $f[n][26]$。

时间复杂度 $O(n \times k \times |\Sigma|)$，空间复杂度 $O(n \times |\Sigma|)$。其中 $|\Sigma|$ 表示字母表大小。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def keyboard(self, k: int, n: int) -> int:
        f = [[0] * 27 for _ in range(n + 1)]
        f[0] = [1] * 27
        mod = 10**9 + 7
        for i in range(1, n + 1):
            for j in range(1, 27):
                for h in range(min(k, i) + 1):
                    f[i][j] += f[i - h][j - 1] * comb(i, h)
                    f[i][j] %= mod
        return f[n][26]
```

#### Java

```java
class Solution {
    public int keyboard(int k, int n) {
        int[][] c = new int[n + 1][k + 1];
        for (int i = 0; i <= n; ++i) {
            c[i][0] = 1;
        }
        final int mod = (int) 1e9 + 7;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= k; ++j) {
                c[i][j] = (c[i - 1][j - 1] + c[i - 1][j]) % mod;
            }
        }
        int[][] f = new int[n + 1][27];
        Arrays.fill(f[0], 1);
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j < 27; ++j) {
                for (int h = 0; h <= Math.min(i, k); ++h) {
                    f[i][j] = (f[i][j] + (int) ((long) f[i - h][j - 1] * c[i][h] % mod)) % mod;
                }
            }
        }
        return f[n][26];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int keyboard(int k, int n) {
        int f[n + 1][27];
        memset(f, 0, sizeof(f));
        for (int j = 0; j < 27; ++j) {
            f[0][j] = 1;
        }
        int c[n + 1][k + 1];
        memset(c, 0, sizeof(c));
        c[0][0] = 1;
        const int mod = 1e9 + 7;
        for (int i = 1; i <= n; ++i) {
            c[i][0] = 1;
            for (int j = 1; j <= k; ++j) {
                c[i][j] = (c[i - 1][j] + c[i - 1][j - 1]) % mod;
            }
        }
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j < 27; ++j) {
                for (int h = 0; h <= min(i, k); ++h) {
                    f[i][j] = (f[i][j] + 1LL * f[i - h][j - 1] * c[i][h] % mod) % mod;
                }
            }
        }
        return f[n][26];
    }
};
```

#### Go

```go
func keyboard(k int, n int) int {
	c := make([][]int, n+1)
	for i := range c {
		c[i] = make([]int, k+1)
		c[i][0] = 1
	}
	const mod int = 1e9 + 7
	for i := 1; i <= n; i++ {
		for j := 1; j <= k; j++ {
			c[i][j] = (c[i-1][j-1] + c[i-1][j]) % mod
		}
	}
	f := make([][27]int, n+1)
	for j := range f[0] {
		f[0][j] = 1
	}
	for i := 1; i <= n; i++ {
		for j := 1; j < 27; j++ {
			for h := 0; h <= min(i, k); h++ {
				f[i][j] = (f[i][j] + f[i-h][j-1]*c[i][h]%mod) % mod
			}
		}
	}
	return f[n][26]
}
```

#### TypeScript

```ts
function keyboard(k: number, n: number): number {
    const c: number[][] = Array.from({ length: n + 1 }, () => Array(k + 1).fill(0));
    c[0][0] = 1;
    const mod = 10 ** 9 + 7;
    for (let i = 1; i <= n; ++i) {
        c[i][0] = 1;
        for (let j = 1; j <= k; ++j) {
            c[i][j] = (c[i - 1][j - 1] + c[i - 1][j]) % mod;
        }
    }
    const f: number[][] = Array.from({ length: n + 1 }, () => Array(27).fill(0));
    f[0].fill(1);
    for (let i = 1; i <= n; ++i) {
        for (let j = 1; j < 27; ++j) {
            for (let h = 0; h <= Math.min(i, k); ++h) {
                const v = Number((BigInt(f[i - h][j - 1]) * BigInt(c[i][h])) % BigInt(mod));
                f[i][j] = (f[i][j] + v) % mod;
            }
        }
    }
    return f[n][26];
}
```

<!--- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
