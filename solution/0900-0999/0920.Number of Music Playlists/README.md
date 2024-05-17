---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0920.Number%20of%20Music%20Playlists/README.md
tags:
    - 数学
    - 动态规划
    - 组合数学
---

<!-- problem:start -->

# [920. 播放列表的数量](https://leetcode.cn/problems/number-of-music-playlists)

[English Version](/solution/0900-0999/0920.Number%20of%20Music%20Playlists/README_EN.md)

## 题目描述

<!-- description:start -->

<p>你的音乐播放器里有 <code>n</code> 首不同的歌，在旅途中，你计划听 <code>goal</code> 首歌（不一定不同，即，允许歌曲重复）。你将会按如下规则创建播放列表：</p>

<ul>
	<li>每首歌 <strong>至少播放一次</strong> 。</li>
	<li>一首歌只有在其他 <code>k</code> 首歌播放完之后才能再次播放。</li>
</ul>

<p>给你 <code>n</code>、<code>goal</code> 和 <code>k</code> ，返回可以满足要求的播放列表的数量。由于答案可能非常大，请返回对 <code>10<sup>9</sup> + 7</code> <strong>取余</strong> 的结果。</p>
&nbsp;

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 3, goal = 3, k = 1
<strong>输出：</strong>6
<strong>解释：</strong>有 6 种可能的播放列表。[1, 2, 3]，[1, 3, 2]，[2, 1, 3]，[2, 3, 1]，[3, 1, 2]，[3, 2, 1] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 2, goal = 3, k = 0
<strong>输出：</strong>6
<strong>解释：</strong>有 6 种可能的播放列表。[1, 1, 2]，[1, 2, 1]，[2, 1, 1]，[2, 2, 1]，[2, 1, 2]，[1, 2, 2] 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 2, goal = 3, k = 1
<strong>输出：</strong>2
<strong>解释：</strong>有 2 种可能的播放列表。[1, 2, 1]，[2, 1, 2] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= k &lt; n &lt;= goal &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

我们定义 $f[i][j]$ 表示听 $i$ 首歌，且这 $i$ 首歌中有 $j$ 首不同歌曲的播放列表的数量。初始时 $f[0][0]=1$。答案为 $f[goal][n]$。

对于 $f[i][j]$，我们可以选择没听过的歌，那么上一个状态为 $f[i - 1][j - 1]$，这样的选择有 $n - (j - 1) = n - j + 1$ 种，因此 $f[i][j] += f[i - 1][j - 1] \times (n - j + 1)$。我们也可以选择听过的歌，那么上一个状态为 $f[i - 1][j]$，这样的选择有 $j - k$ 种，因此 $f[i][j] += f[i - 1][j] \times (j - k)$，其中 $j \geq k$。

综上，我们可以得到状态转移方程：

$$
f[i][j] = \begin{cases}
1 & i = 0, j = 0 \\
f[i - 1][j - 1] \times (n - j + 1) + f[i - 1][j] \times (j - k) & i \geq 1, j \geq 1
\end{cases}
$$

最终的答案为 $f[goal][n]$。

时间复杂度 $O(goal \times n)$，空间复杂度 $O(goal \times n)$。其中 $goal$ 和 $n$ 为题目中给定的参数。

注意到 $f[i][j]$ 只与 $f[i - 1][j - 1]$ 和 $f[i - 1][j]$ 有关，因此我们可以使用滚动数组优化空间复杂度，时间复杂度不变。

<!-- tabs:start -->

```python
class Solution:
    def numMusicPlaylists(self, n: int, goal: int, k: int) -> int:
        mod = 10**9 + 7
        f = [[0] * (n + 1) for _ in range(goal + 1)]
        f[0][0] = 1
        for i in range(1, goal + 1):
            for j in range(1, n + 1):
                f[i][j] = f[i - 1][j - 1] * (n - j + 1)
                if j > k:
                    f[i][j] += f[i - 1][j] * (j - k)
                f[i][j] %= mod
        return f[goal][n]
```

```java
class Solution {
    public int numMusicPlaylists(int n, int goal, int k) {
        final int mod = (int) 1e9 + 7;
        long[][] f = new long[goal + 1][n + 1];
        f[0][0] = 1;
        for (int i = 1; i <= goal; ++i) {
            for (int j = 1; j <= n; ++j) {
                f[i][j] = f[i - 1][j - 1] * (n - j + 1);
                if (j > k) {
                    f[i][j] += f[i - 1][j] * (j - k);
                }
                f[i][j] %= mod;
            }
        }
        return (int) f[goal][n];
    }
}
```

```cpp
class Solution {
public:
    int numMusicPlaylists(int n, int goal, int k) {
        const int mod = 1e9 + 7;
        long long f[goal + 1][n + 1];
        memset(f, 0, sizeof(f));
        f[0][0] = 1;
        for (int i = 1; i <= goal; ++i) {
            for (int j = 1; j <= n; ++j) {
                f[i][j] = f[i - 1][j - 1] * (n - j + 1);
                if (j > k) {
                    f[i][j] += f[i - 1][j] * (j - k);
                }
                f[i][j] %= mod;
            }
        }
        return f[goal][n];
    }
};
```

```go
func numMusicPlaylists(n int, goal int, k int) int {
	const mod = 1e9 + 7
	f := make([][]int, goal+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}
	f[0][0] = 1
	for i := 1; i <= goal; i++ {
		for j := 1; j <= n; j++ {
			f[i][j] = f[i-1][j-1] * (n - j + 1)
			if j > k {
				f[i][j] += f[i-1][j] * (j - k)
			}
			f[i][j] %= mod
		}
	}
	return f[goal][n]
}
```

```ts
function numMusicPlaylists(n: number, goal: number, k: number): number {
    const mod = 1e9 + 7;
    const f = new Array(goal + 1).fill(0).map(() => new Array(n + 1).fill(0));
    f[0][0] = 1;
    for (let i = 1; i <= goal; ++i) {
        for (let j = 1; j <= n; ++j) {
            f[i][j] = f[i - 1][j - 1] * (n - j + 1);
            if (j > k) {
                f[i][j] += f[i - 1][j] * (j - k);
            }
            f[i][j] %= mod;
        }
    }
    return f[goal][n];
}
```

```rust
impl Solution {
    #[allow(dead_code)]
    pub fn num_music_playlists(n: i32, goal: i32, k: i32) -> i32 {
        let mut dp: Vec<Vec<i64>> = vec![vec![0; n as usize + 1]; goal as usize + 1];

        // Initialize the dp vector
        dp[0][0] = 1;

        // Begin the dp process
        for i in 1..=goal as usize {
            for j in 1..=n as usize {
                // Choose the song that has not been chosen before
                // We have n - (j - 1) songs to choose
                dp[i][j] += dp[i - 1][j - 1] * ((n - ((j as i32) - 1)) as i64);

                // Choose the song that has been chosen before
                // We have j - k songs to choose if j > k
                if (j as i32) > k {
                    dp[i][j] += dp[i - 1][j] * (((j as i32) - k) as i64);
                }

                // Update dp[i][j]
                dp[i][j] %= ((1e9 as i32) + 7) as i64;
            }
        }

        dp[goal as usize][n as usize] as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- tabs:start -->

```python
class Solution:
    def numMusicPlaylists(self, n: int, goal: int, k: int) -> int:
        mod = 10**9 + 7
        f = [0] * (goal + 1)
        f[0] = 1
        for i in range(1, goal + 1):
            g = [0] * (goal + 1)
            for j in range(1, n + 1):
                g[j] = f[j - 1] * (n - j + 1)
                if j > k:
                    g[j] += f[j] * (j - k)
                g[j] %= mod
            f = g
        return f[n]
```

```java
class Solution {
    public int numMusicPlaylists(int n, int goal, int k) {
        final int mod = (int) 1e9 + 7;
        long[] f = new long[n + 1];
        f[0] = 1;
        for (int i = 1; i <= goal; ++i) {
            long[] g = new long[n + 1];
            for (int j = 1; j <= n; ++j) {
                g[j] = f[j - 1] * (n - j + 1);
                if (j > k) {
                    g[j] += f[j] * (j - k);
                }
                g[j] %= mod;
            }
            f = g;
        }
        return (int) f[n];
    }
}
```

```cpp
class Solution {
public:
    int numMusicPlaylists(int n, int goal, int k) {
        const int mod = 1e9 + 7;
        vector<long long> f(n + 1);
        f[0] = 1;
        for (int i = 1; i <= goal; ++i) {
            vector<long long> g(n + 1);
            for (int j = 1; j <= n; ++j) {
                g[j] = f[j - 1] * (n - j + 1);
                if (j > k) {
                    g[j] += f[j] * (j - k);
                }
                g[j] %= mod;
            }
            f = move(g);
        }
        return f[n];
    }
};
```

```go
func numMusicPlaylists(n int, goal int, k int) int {
	const mod = 1e9 + 7
	f := make([]int, goal+1)
	f[0] = 1
	for i := 1; i <= goal; i++ {
		g := make([]int, goal+1)
		for j := 1; j <= n; j++ {
			g[j] = f[j-1] * (n - j + 1)
			if j > k {
				g[j] += f[j] * (j - k)
			}
			g[j] %= mod
		}
		f = g
	}
	return f[n]
}
```

```ts
function numMusicPlaylists(n: number, goal: number, k: number): number {
    const mod = 1e9 + 7;
    let f = new Array(goal + 1).fill(0);
    f[0] = 1;
    for (let i = 1; i <= goal; ++i) {
        const g = new Array(goal + 1).fill(0);
        for (let j = 1; j <= n; ++j) {
            g[j] = f[j - 1] * (n - j + 1);
            if (j > k) {
                g[j] += f[j] * (j - k);
            }
            g[j] %= mod;
        }
        f = g;
    }
    return f[n];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
