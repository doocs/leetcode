# [920. Number of Music Playlists](https://leetcode.com/problems/number-of-music-playlists)

[中文文档](/solution/0900-0999/0920.Number%20of%20Music%20Playlists/README.md)

<!-- tags:Math,Dynamic Programming,Combinatorics -->

## Description

<p>Your music player contains <code>n</code> different songs. You want to listen to <code>goal</code> songs (not necessarily different) during your trip. To avoid boredom, you will create a playlist so that:</p>

<ul>
	<li>Every song is played <strong>at least once</strong>.</li>
	<li>A song can only be played again only if <code>k</code> other songs have been played.</li>
</ul>

<p>Given <code>n</code>, <code>goal</code>, and <code>k</code>, return <em>the number of possible playlists that you can create</em>. Since the answer can be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 3, goal = 3, k = 1
<strong>Output:</strong> 6
<strong>Explanation:</strong> There are 6 possible playlists: [1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], and [3, 2, 1].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2, goal = 3, k = 0
<strong>Output:</strong> 6
<strong>Explanation:</strong> There are 6 possible playlists: [1, 1, 2], [1, 2, 1], [2, 1, 1], [2, 2, 1], [2, 1, 2], and [1, 2, 2].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 2, goal = 3, k = 1
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are 2 possible playlists: [1, 2, 1] and [2, 1, 2].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= k &lt; n &lt;= goal &lt;= 100</code></li>
</ul>

## Solutions

### Solution 1: Dynamic Programming

We define $f[i][j]$ to be the number of playlists that can be made from $i$ songs with exactly $j$ different songs. We have $f[0][0] = 1$ and the answer is $f[goal][n]$.

For $f[i][j]$, we can choose a song that we have not listened before, so the previous state is $f[i - 1][j - 1]$, and there are $n - (j - 1) = n - j + 1$ options. Thus, $f[i][j] += f[i - 1][j - 1] \times (n - j + 1)$. We can also choose a song that we have listened before, so the previous state is $f[i - 1][j]$, and there are $j - k$ options. Thus, $f[i][j] += f[i - 1][j] \times (j - k)$, where $j \geq k$.

Therefore, we have the transition equation:

$$
f[i][j] = \begin{cases}
1 & i = 0, j = 0 \\
f[i - 1][j - 1] \times (n - j + 1) + f[i - 1][j] \times (j - k) & i \geq 1, j \geq 1
\end{cases}
$$

The final answer is $f[goal][n]$.

The time complexity is $O(goal \times n)$, and the space complexity is $O(goal \times n)$. Here, $goal$ and $n$ are the parameters given in the problem.

Notice that $f[i][j]$ only depends on $f[i - 1][j - 1]$ and $f[i - 1][j]$, so we can use rolling array to optimize the space complexity. The time complexity is unchanged.

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

### Solution 2

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

<!-- end -->
