# [10. Regular Expression Matching](https://leetcode.com/problems/regular-expression-matching)

[中文文档](/solution/0000-0099/0010.Regular%20Expression%20Matching/README.md)

<!-- tags:Recursion,String,Dynamic Programming -->

## Description

<p>Given an input string <code>s</code>&nbsp;and a pattern <code>p</code>, implement regular expression matching with support for <code>&#39;.&#39;</code> and <code>&#39;*&#39;</code> where:</p>

<ul>
	<li><code>&#39;.&#39;</code> Matches any single character.​​​​</li>
	<li><code>&#39;*&#39;</code> Matches zero or more of the preceding element.</li>
</ul>

<p>The matching should cover the <strong>entire</strong> input string (not partial).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aa&quot;, p = &quot;a&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> &quot;a&quot; does not match the entire string &quot;aa&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aa&quot;, p = &quot;a*&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> &#39;*&#39; means zero or more of the preceding element, &#39;a&#39;. Therefore, by repeating &#39;a&#39; once, it becomes &quot;aa&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;ab&quot;, p = &quot;.*&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> &quot;.*&quot; means &quot;zero or more (*) of any character (.)&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length&nbsp;&lt;= 20</code></li>
	<li><code>1 &lt;= p.length&nbsp;&lt;= 20</code></li>
	<li><code>s</code> contains only lowercase English letters.</li>
	<li><code>p</code> contains only lowercase English letters, <code>&#39;.&#39;</code>, and&nbsp;<code>&#39;*&#39;</code>.</li>
	<li>It is guaranteed for each appearance of the character <code>&#39;*&#39;</code>, there will be a previous valid character to match.</li>
</ul>

## Solutions

### Solution 1: Memoization Search

We design a function $dfs(i, j)$, which indicates whether the $i$-th character of $s$ matches the $j$-th character of $p$. The answer is $dfs(0, 0)$.

The calculation process of the function $dfs(i, j)$ is as follows:

-   If $j$ has reached the end of $p$, then if $i$ has also reached the end of $s$, the match is successful, otherwise, the match fails.
-   If the next character of $j$ is `'*'`, we can choose to match $0$ $s[i]$ characters, which is $dfs(i, j + 2)$. If $i \lt m$ and $s[i]$ matches $p[j]$, we can choose to match $1$ $s[i]$ character, which is $dfs(i + 1, j)$.
-   If the next character of $j$ is not `'*'`, then if $i \lt m$ and $s[i]$ matches $p[j]$, it is $dfs(i + 1, j + 1)$. Otherwise, the match fails.

During the process, we can use memoization search to avoid repeated calculations.

The time complexity is $O(m \times n)$, and the space complexity is $O(m \times n)$. Here, $m$ and $n$ are the lengths of $s$ and $p$ respectively.

<!-- tabs:start -->

```python
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        @cache
        def dfs(i, j):
            if j >= n:
                return i == m
            if j + 1 < n and p[j + 1] == '*':
                return dfs(i, j + 2) or (
                    i < m and (s[i] == p[j] or p[j] == '.') and dfs(i + 1, j)
                )
            return i < m and (s[i] == p[j] or p[j] == '.') and dfs(i + 1, j + 1)

        m, n = len(s), len(p)
        return dfs(0, 0)
```

```java
class Solution {
    private Boolean[][] f;
    private String s;
    private String p;
    private int m;
    private int n;

    public boolean isMatch(String s, String p) {
        m = s.length();
        n = p.length();
        f = new Boolean[m + 1][n + 1];
        this.s = s;
        this.p = p;
        return dfs(0, 0);
    }

    private boolean dfs(int i, int j) {
        if (j >= n) {
            return i == m;
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        boolean res = false;
        if (j + 1 < n && p.charAt(j + 1) == '*') {
            res = dfs(i, j + 2)
                || (i < m && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') && dfs(i + 1, j));
        } else {
            res = i < m && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') && dfs(i + 1, j + 1);
        }
        return f[i][j] = res;
    }
}
```

```cpp
class Solution {
public:
    bool isMatch(string s, string p) {
        int m = s.size(), n = p.size();
        int f[m + 1][n + 1];
        memset(f, 0, sizeof f);
        function<bool(int, int)> dfs = [&](int i, int j) -> bool {
            if (j >= n) {
                return i == m;
            }
            if (f[i][j]) {
                return f[i][j] == 1;
            }
            int res = -1;
            if (j + 1 < n && p[j + 1] == '*') {
                if (dfs(i, j + 2) or (i < m and (s[i] == p[j] or p[j] == '.') and dfs(i + 1, j))) {
                    res = 1;
                }
            } else if (i < m and (s[i] == p[j] or p[j] == '.') and dfs(i + 1, j + 1)) {
                res = 1;
            }
            f[i][j] = res;
            return res == 1;
        };
        return dfs(0, 0);
    }
};
```

```go
func isMatch(s string, p string) bool {
	m, n := len(s), len(p)
	f := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}
	var dfs func(i, j int) bool
	dfs = func(i, j int) bool {
		if j >= n {
			return i == m
		}
		if f[i][j] != 0 {
			return f[i][j] == 1
		}
		res := -1
		if j+1 < n && p[j+1] == '*' {
			if dfs(i, j+2) || (i < m && (s[i] == p[j] || p[j] == '.') && dfs(i+1, j)) {
				res = 1
			}
		} else if i < m && (s[i] == p[j] || p[j] == '.') && dfs(i+1, j+1) {
			res = 1
		}
		f[i][j] = res
		return res == 1
	}
	return dfs(0, 0)
}
```

```rust
impl Solution {
    #[allow(dead_code)]
    pub fn is_match(s: String, p: String) -> bool {
        let n = s.len();
        let m = p.len();
        let s = s.chars().collect::<Vec<char>>();
        let p = p.chars().collect::<Vec<char>>();

        let mut dp = vec![vec![false; m + 1]; n + 1];

        // Initialize the dp vector
        dp[0][0] = true;

        for i in 1..=m {
            if p[i - 1] == '*' {
                dp[0][i] = dp[0][i - 2];
            }
        }

        // Begin the actual dp process
        for i in 1..=n {
            for j in 1..=m {
                if s[i - 1] == p[j - 1] || p[j - 1] == '.' {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                if p[j - 1] == '*' {
                    if j >= 2 && (s[i - 1] == p[j - 2] || p[j - 2] == '.') {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 2];
                    } else if j >= 2 && s[i - 1] != p[j - 2] {
                        dp[i][j] = dp[i][j - 2];
                    }
                }
            }
        }

        dp[n][m]
    }
}
```

```js
/**
 * @param {string} s
 * @param {string} p
 * @return {boolean}
 */
var isMatch = function (s, p) {
    const m = s.length;
    const n = p.length;
    const f = Array.from({ length: m + 1 }, () => Array(n + 1).fill(0));
    const dfs = (i, j) => {
        if (j >= n) {
            return i === m;
        }
        if (f[i][j]) {
            return f[i][j] === 1;
        }
        let res = -1;
        if (j + 1 < n && p[j + 1] === '*') {
            if (dfs(i, j + 2) || (i < m && (s[i] === p[j] || p[j] === '.') && dfs(i + 1, j))) {
                res = 1;
            }
        } else if (i < m && (s[i] === p[j] || p[j] === '.') && dfs(i + 1, j + 1)) {
            res = 1;
        }
        f[i][j] = res;
        return res === 1;
    };
    return dfs(0, 0);
};
```

```cs
public class Solution {
    private string s;
    private string p;
    private int m;
    private int n;
    private int[,] f;

    public bool IsMatch(string s, string p) {
        m = s.Length;
        n = p.Length;
        f = new int[m + 1, n + 1];
        this.s = s;
        this.p = p;
        return dfs(0, 0);
    }

    private bool dfs(int i, int j) {
        if (j >= n) {
            return i == m;
        }
        if (f[i, j] != 0) {
            return f[i, j] == 1;
        }
        int res = -1;
        if (j + 1 < n && p[j + 1] == '*') {
            if (dfs(i, j + 2) || (i < m && (s[i] == p[j] || p[j] == '.') && dfs(i + 1, j))) {
                res = 1;
            }
        } else if (i < m && (s[i] == p[j] || p[j] == '.') && dfs(i + 1, j + 1)) {
            res = 1;
        }
        f[i, j] = res;
        return res == 1;
    }
}
```

<!-- tabs:end -->

### Solution 2: Dynamic Programming

We can convert the memoization search in Solution 1 into dynamic programming.

Define $f[i][j]$ to represent whether the first $i$ characters of string $s$ match the first $j$ characters of string $p$. The answer is $f[m][n]$. Initialize $f[0][0] = true$, indicating that the empty string and the empty regular expression match.

Similar to Solution 1, we can discuss different cases.

-   If $p[j - 1]$ is `'*'`, we can choose to match $0$ $s[i - 1]$ characters, which is $f[i][j] = f[i][j - 2]$. If $s[i - 1]$ matches $p[j - 2]$, we can choose to match $1$ $s[i - 1]$ character, which is $f[i][j] = f[i][j] \lor f[i - 1][j]$.
-   If $p[j - 1]$ is not `'*'`, then if $s[i - 1]$ matches $p[j - 1]$, it is $f[i][j] = f[i - 1][j - 1]$. Otherwise, the match fails.

The time complexity is $O(m \times n)$, and the space complexity is $O(m \times n)$. Here, $m$ and $n$ are the lengths of $s$ and $p$ respectively.

<!-- tabs:start -->

```python
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        m, n = len(s), len(p)
        f = [[False] * (n + 1) for _ in range(m + 1)]
        f[0][0] = True
        for i in range(m + 1):
            for j in range(1, n + 1):
                if p[j - 1] == "*":
                    f[i][j] = f[i][j - 2]
                    if i > 0 and (p[j - 2] == "." or s[i - 1] == p[j - 2]):
                        f[i][j] |= f[i - 1][j]
                elif i > 0 and (p[j - 1] == "." or s[i - 1] == p[j - 1]):
                    f[i][j] = f[i - 1][j - 1]
        return f[m][n]
```

```java
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (i > 0 && (p.charAt(j - 2) == '.' || p.charAt(j - 2) == s.charAt(i - 1))) {
                        f[i][j] |= f[i - 1][j];
                    }
                } else if (i > 0
                    && (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i - 1))) {
                    f[i][j] = f[i - 1][j - 1];
                }
            }
        }
        return f[m][n];
    }
}
```

```cpp
class Solution {
public:
    bool isMatch(string s, string p) {
        int m = s.size(), n = p.size();
        bool f[m + 1][n + 1];
        memset(f, false, sizeof f);
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p[j - 1] == '*') {
                    f[i][j] = f[i][j - 2];
                    if (i && (p[j - 2] == '.' || p[j - 2] == s[i - 1])) {
                        f[i][j] |= f[i - 1][j];
                    }
                } else if (i && (p[j - 1] == '.' || p[j - 1] == s[i - 1])) {
                    f[i][j] = f[i - 1][j - 1];
                }
            }
        }
        return f[m][n];
    }
};
```

```go
func isMatch(s string, p string) bool {
	m, n := len(s), len(p)
	f := make([][]bool, m+1)
	for i := range f {
		f[i] = make([]bool, n+1)
	}
	f[0][0] = true
	for i := 0; i <= m; i++ {
		for j := 1; j <= n; j++ {
			if p[j-1] == '*' {
				f[i][j] = f[i][j-2]
				if i > 0 && (p[j-2] == '.' || p[j-2] == s[i-1]) {
					f[i][j] = f[i][j] || f[i-1][j]
				}
			} else if i > 0 && (p[j-1] == '.' || p[j-1] == s[i-1]) {
				f[i][j] = f[i-1][j-1]
			}
		}
	}
	return f[m][n]
}
```

```js
/**
 * @param {string} s
 * @param {string} p
 * @return {boolean}
 */
var isMatch = function (s, p) {
    const m = s.length;
    const n = p.length;
    const f = Array.from({ length: m + 1 }, () => Array(n + 1).fill(false));
    f[0][0] = true;
    for (let i = 0; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            if (p[j - 1] === '*') {
                f[i][j] = f[i][j - 2];
                if (i && (p[j - 2] === '.' || p[j - 2] === s[i - 1])) {
                    f[i][j] |= f[i - 1][j];
                }
            } else if (i && (p[j - 1] === '.' || p[j - 1] === s[i - 1])) {
                f[i][j] = f[i - 1][j - 1];
            }
        }
    }
    return f[m][n];
};
```

```cs
public class Solution {
    public bool IsMatch(string s, string p) {
        int m = s.Length, n = p.Length;
        bool[,] f = new bool[m + 1, n + 1];
        f[0, 0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p[j - 1] == '*') {
                    f[i, j] = f[i, j - 2];
                    if (i > 0 && (p[j - 2] == '.' || p[j - 2] == s[i - 1])) {
                        f[i, j] |= f[i - 1, j];
                    }
                } else if (i > 0 && (p[j - 1] == '.' || p[j - 1] == s[i - 1])) {
                    f[i, j] = f[i - 1, j - 1];
                }
            }
        }
        return f[m, n];
    }
}
```

```php
class Solution {
    /**
     * @param string $s
     * @param string $p
     * @return boolean
     */

    function isMatch($s, $p) {
        $m = strlen($s);
        $n = strlen($p);

        $dp = array_fill(0, $m + 1, array_fill(0, $n + 1, false));
        $dp[0][0] = true;

        for ($j = 1; $j <= $n; $j++) {
            if ($p[$j - 1] == '*') {
                $dp[0][$j] = $dp[0][$j - 2];
            }
        }

        for ($i = 1; $i <= $m; $i++) {
            for ($j = 1; $j <= $n; $j++) {
                if ($p[$j - 1] == '.' || $p[$j - 1] == $s[$i - 1]) {
                    $dp[$i][$j] = $dp[$i - 1][$j - 1];
                } elseif ($p[$j - 1] == '*') {
                    $dp[$i][$j] = $dp[$i][$j - 2];
                    if ($p[$j - 2] == '.' || $p[$j - 2] == $s[$i - 1]) {
                        $dp[$i][$j] = $dp[$i][$j] || $dp[$i - 1][$j];
                    }
                } else {
                    $dp[$i][$j] = false;
                }
            }
        }

        return $dp[$m][$n];
    }
}
```

<!-- tabs:end -->

<!-- end -->
