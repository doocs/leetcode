---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3320.Count%20The%20Number%20of%20Winning%20Sequences/README_EN.md
rating: 2153
source: Weekly Contest 419 Q3
tags:
    - String
    - Dynamic Programming
---

<!-- problem:start -->

# [3320. Count The Number of Winning Sequences](https://leetcode.com/problems/count-the-number-of-winning-sequences)

[中文文档](/solution/3300-3399/3320.Count%20The%20Number%20of%20Winning%20Sequences/README.md)

## Description

<!-- description:start -->

<p>Alice and Bob are playing a fantasy battle game consisting of <code>n</code> rounds where they summon one of three magical creatures each round: a Fire Dragon, a Water Serpent, or an Earth Golem. In each round, players <strong>simultaneously</strong> summon their creature and are awarded points as follows:</p>

<ul>
	<li>If one player summons a Fire Dragon and the other summons an Earth Golem, the player who summoned the <strong>Fire Dragon</strong> is awarded a point.</li>
	<li>If one player summons a Water Serpent and the other summons a Fire Dragon, the player who summoned the <strong>Water Serpent</strong> is awarded a point.</li>
	<li>If one player summons an Earth Golem and the other summons a Water Serpent, the player who summoned the <strong>Earth Golem</strong> is awarded a point.</li>
	<li>If both players summon the same creature, no player is awarded a point.</li>
</ul>

<p>You are given a string <code>s</code> consisting of <code>n</code> characters <code>&#39;F&#39;</code>, <code>&#39;W&#39;</code>, and <code>&#39;E&#39;</code>, representing the sequence of creatures Alice will summon in each round:</p>

<ul>
	<li>If <code>s[i] == &#39;F&#39;</code>, Alice summons a Fire Dragon.</li>
	<li>If <code>s[i] == &#39;W&#39;</code>, Alice summons a Water Serpent.</li>
	<li>If <code>s[i] == &#39;E&#39;</code>, Alice summons an Earth Golem.</li>
</ul>

<p>Bob&rsquo;s sequence of moves is unknown, but it is guaranteed that Bob will never summon the same creature in two consecutive rounds. Bob <em>beats</em> Alice if the total number of points awarded to Bob after <code>n</code> rounds is <strong>strictly greater</strong> than the points awarded to Alice.</p>

<p>Return the number of distinct sequences Bob can use to beat Alice.</p>

<p>Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;FFF&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>Bob can beat Alice by making one of the following sequences of moves: <code>&quot;WFW&quot;</code>, <code>&quot;FWF&quot;</code>, or <code>&quot;WEW&quot;</code>. Note that other winning sequences like <code>&quot;WWE&quot;</code> or <code>&quot;EWW&quot;</code> are invalid since Bob cannot make the same move twice in a row.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;FWEFW&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">18</span></p>

<p><strong>Explanation:</strong></p>

<p><w>Bob can beat Alice by making one of the following sequences of moves: <code>&quot;FWFWF&quot;</code>, <code>&quot;FWFWE&quot;</code>, <code>&quot;FWEFE&quot;</code>, <code>&quot;FWEWE&quot;</code>, <code>&quot;FEFWF&quot;</code>, <code>&quot;FEFWE&quot;</code>, <code>&quot;FEFEW&quot;</code>, <code>&quot;FEWFE&quot;</code>, <code>&quot;WFEFE&quot;</code>, <code>&quot;WFEWE&quot;</code>, <code>&quot;WEFWF&quot;</code>, <code>&quot;WEFWE&quot;</code>, <code>&quot;WEFEF&quot;</code>, <code>&quot;WEFEW&quot;</code>, <code>&quot;WEWFW&quot;</code>, <code>&quot;WEWFE&quot;</code>, <code>&quot;EWFWE&quot;</code>, or <code>&quot;EWEWE&quot;</code>.</w></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s[i]</code> is one of <code>&#39;F&#39;</code>, <code>&#39;W&#39;</code>, or <code>&#39;E&#39;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Memoization Search

We design a function $\textit{dfs}(i, j, k)$, where $i$ represents starting from the $i$-th character of the string $s$, $j$ represents the current score difference between $\textit{Alice}$ and $\textit{Bob}$, and $k$ represents the last creature summoned by $\textit{Bob}$. The function calculates how many sequences of moves $\textit{Bob}$ can make to defeat $\textit{Alice}$.

The answer is $\textit{dfs}(0, 0, -1)$, where $-1$ indicates that $\textit{Bob}$ has not summoned any creatures yet. In languages other than Python, since the score difference can be negative, we can add $n$ to the score difference to ensure it is non-negative.

The calculation process of the function $\textit{dfs}(i, j, k)$ is as follows:

- If $n - i \leq j$, then the remaining rounds are not enough for $\textit{Bob}$ to surpass $\textit{Alice}$'s score, so return $0$.
- If $i \geq n$, then all rounds have ended. If $\textit{Bob}$'s score is less than $0$, return $1$; otherwise, return $0$.
- Otherwise, we enumerate the creatures $\textit{Bob}$ can summon this round. If the creature summoned this round is the same as the one summoned in the previous round, $\textit{Bob}$ cannot win this round, so we skip it. Otherwise, we recursively calculate $\textit{dfs}(i + 1, j + \textit{calc}(d[s[i]], l), l)$, where $\textit{calc}(x, y)$ represents the outcome between $x$ and $y$, and $d$ is a mapping that maps characters to $\textit{012}$. We sum all the results and take the modulo $10^9 + 7$.

The time complexity is $O(n^2 \times k^2)$, where $n$ is the length of the string $s$, and $k$ represents the size of the character set. The space complexity is $O(n^2 \times k)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countWinningSequences(self, s: str) -> int:
        def calc(x: int, y: int) -> int:
            if x == y:
                return 0
            if x < y:
                return 1 if x == 0 and y == 2 else -1
            return -1 if x == 2 and y == 0 else 1

        @cache
        def dfs(i: int, j: int, k: int) -> int:
            if len(s) - i <= j:
                return 0
            if i >= len(s):
                return int(j < 0)
            res = 0
            for l in range(3):
                if l == k:
                    continue
                res = (res + dfs(i + 1, j + calc(d[s[i]], l), l)) % mod
            return res

        mod = 10**9 + 7
        d = {"F": 0, "W": 1, "E": 2}
        ans = dfs(0, 0, -1)
        dfs.cache_clear()
        return ans
```

#### Java

```java
class Solution {
    private int n;
    private char[] s;
    private int[] d = new int[26];
    private Integer[][][] f;
    private final int mod = (int) 1e9 + 7;

    public int countWinningSequences(String s) {
        d['W' - 'A'] = 1;
        d['E' - 'A'] = 2;
        this.s = s.toCharArray();
        n = this.s.length;
        f = new Integer[n][n + n + 1][4];
        return dfs(0, n, 3);
    }

    private int dfs(int i, int j, int k) {
        if (n - i <= j - n) {
            return 0;
        }
        if (i >= n) {
            return j - n < 0 ? 1 : 0;
        }
        if (f[i][j][k] != null) {
            return f[i][j][k];
        }

        int ans = 0;
        for (int l = 0; l < 3; ++l) {
            if (l == k) {
                continue;
            }
            ans = (ans + dfs(i + 1, j + calc(d[s[i] - 'A'], l), l)) % mod;
        }
        return f[i][j][k] = ans;
    }

    private int calc(int x, int y) {
        if (x == y) {
            return 0;
        }
        if (x < y) {
            return x == 0 && y == 2 ? 1 : -1;
        }
        return x == 2 && y == 0 ? -1 : 1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countWinningSequences(string s) {
        int n = s.size();
        int d[26]{};
        d['W' - 'A'] = 1;
        d['E' - 'A'] = 2;
        int f[n][n + n + 1][4];
        memset(f, -1, sizeof(f));
        auto calc = [](int x, int y) -> int {
            if (x == y) {
                return 0;
            }
            if (x < y) {
                return x == 0 && y == 2 ? 1 : -1;
            }
            return x == 2 && y == 0 ? -1 : 1;
        };
        const int mod = 1e9 + 7;
        auto dfs = [&](this auto&& dfs, int i, int j, int k) -> int {
            if (n - i <= j - n) {
                return 0;
            }
            if (i >= n) {
                return j - n < 0 ? 1 : 0;
            }
            if (f[i][j][k] != -1) {
                return f[i][j][k];
            }
            int ans = 0;
            for (int l = 0; l < 3; ++l) {
                if (l == k) {
                    continue;
                }
                ans = (ans + dfs(i + 1, j + calc(d[s[i] - 'A'], l), l)) % mod;
            }
            return f[i][j][k] = ans;
        };
        return dfs(0, n, 3);
    }
};
```

#### Go

```go
func countWinningSequences(s string) int {
	const mod int = 1e9 + 7
	d := [26]int{}
	d['W'-'A'] = 1
	d['E'-'A'] = 2
	n := len(s)
	f := make([][][4]int, n)
	for i := range f {
		f[i] = make([][4]int, n+n+1)
		for j := range f[i] {
			for k := range f[i][j] {
				f[i][j][k] = -1
			}
		}
	}
	calc := func(x, y int) int {
		if x == y {
			return 0
		}
		if x < y {
			if x == 0 && y == 2 {
				return 1
			}
			return -1
		}
		if x == 2 && y == 0 {
			return -1
		}
		return 1
	}
	var dfs func(int, int, int) int
	dfs = func(i, j, k int) int {
		if n-i <= j-n {
			return 0
		}
		if i >= n {
			if j-n < 0 {
				return 1
			}
			return 0
		}
		if v := f[i][j][k]; v != -1 {
			return v
		}
		ans := 0
		for l := 0; l < 3; l++ {
			if l == k {
				continue
			}
			ans = (ans + dfs(i+1, j+calc(d[s[i]-'A'], l), l)) % mod
		}
		f[i][j][k] = ans
		return ans
	}
	return dfs(0, n, 3)
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
