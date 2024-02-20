# [44. Wildcard Matching](https://leetcode.com/problems/wildcard-matching)

[中文文档](/solution/0000-0099/0044.Wildcard%20Matching/README.md)

<!-- tags:Greedy,Recursion,String,Dynamic Programming -->

## Description

<p>Given an input string (<code>s</code>) and a pattern (<code>p</code>), implement wildcard pattern matching with support for <code>&#39;?&#39;</code> and <code>&#39;*&#39;</code> where:</p>

<ul>
	<li><code>&#39;?&#39;</code> Matches any single character.</li>
	<li><code>&#39;*&#39;</code> Matches any sequence of characters (including the empty sequence).</li>
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
<strong>Input:</strong> s = &quot;aa&quot;, p = &quot;*&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong>&nbsp;&#39;*&#39; matches any sequence.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;cb&quot;, p = &quot;?a&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong>&nbsp;&#39;?&#39; matches &#39;c&#39;, but the second letter is &#39;a&#39;, which does not match &#39;b&#39;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= s.length, p.length &lt;= 2000</code></li>
	<li><code>s</code> contains only lowercase English letters.</li>
	<li><code>p</code> contains only lowercase English letters, <code>&#39;?&#39;</code> or <code>&#39;*&#39;</code>.</li>
</ul>

## Solutions

### Solution 1: Dynamic Programming

We define the state $dp[i][j]$ to represent whether the first $i$ characters of $s$ match the first $j$ characters of $p$.

The state transition equation is as follows:

$$
dp[i][j]=
\begin{cases}
dp[i-1][j-1] & \text{if } s[i-1]=p[j-1] \text{ or } p[j-1]=\text{?} \\
dp[i-1][j-1] \lor dp[i-1][j] \lor dp[i][j-1] & \text{if } p[j-1]=\text{*} \\
\text{false} & \text{otherwise}
\end{cases}
$$

The time complexity is $O(m \times n)$, and the space complexity is $O(m \times n)$. Here, $m$ and $n$ are the lengths of $s$ and $p$ respectively.

<!-- tabs:start -->

```python
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        m, n = len(s), len(p)
        dp = [[False] * (n + 1) for _ in range(m + 1)]
        dp[0][0] = True
        for j in range(1, n + 1):
            if p[j - 1] == '*':
                dp[0][j] = dp[0][j - 1]
        for i in range(1, m + 1):
            for j in range(1, n + 1):
                if s[i - 1] == p[j - 1] or p[j - 1] == '?':
                    dp[i][j] = dp[i - 1][j - 1]
                elif p[j - 1] == '*':
                    dp[i][j] = dp[i - 1][j] or dp[i][j - 1]
        return dp[m][n]
```

```java
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int j = 1; j <= n; ++j) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1];
            }
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }
        return dp[m][n];
    }
}
```

```cpp
class Solution {
public:
    bool isMatch(string s, string p) {
        int m = s.size(), n = p.size();
        vector<vector<bool>> dp(m + 1, vector<bool>(n + 1));
        dp[0][0] = true;
        for (int j = 1; j <= n; ++j) {
            if (p[j - 1] == '*') {
                dp[0][j] = dp[0][j - 1];
            }
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (s[i - 1] == p[j - 1] || p[j - 1] == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p[j - 1] == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }
        return dp[m][n];
    }
};
```

```go
func isMatch(s string, p string) bool {
	m, n := len(s), len(p)
	dp := make([][]bool, m+1)
	for i := range dp {
		dp[i] = make([]bool, n+1)
	}
	dp[0][0] = true
	for j := 1; j <= n; j++ {
		if p[j-1] == '*' {
			dp[0][j] = dp[0][j-1]
		}
	}
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			if s[i-1] == p[j-1] || p[j-1] == '?' {
				dp[i][j] = dp[i-1][j-1]
			} else if p[j-1] == '*' {
				dp[i][j] = dp[i-1][j] || dp[i][j-1]
			}
		}
	}
	return dp[m][n]
}
```

```cs
using System.Linq;

public class Solution {
    public bool IsMatch(string s, string p) {
        if (p.Count(ch => ch != '*') > s.Length)
        {
            return false;
        }

        bool[,] f = new bool[s.Length + 1, p.Length + 1];
        bool[] d = new bool[s.Length + 1]; // d[i] means f[0, j] || f[1, j] || ... || f[i, j]
        for (var j = 0; j <= p.Length; ++j)
        {
            d[0] = j == 0 ? true : d[0] && p[j - 1] == '*';
            for (var i = 0; i <= s.Length; ++i)
            {
                if (j == 0)
                {
                    f[i, j] = i == 0;
                    continue;
                }

                if (p[j - 1] == '*')
                {
                    if (i > 0)
                    {
                        d[i] = f[i, j - 1] || d[i - 1];
                    }
                    f[i, j] = d[i];
                }
                else if (p[j - 1] == '?')
                {
                    f[i, j] = i > 0 && f[i - 1, j - 1];
                }
                else
                {
                    f[i, j] = i > 0 && f[i - 1, j - 1] && s[i - 1] == p[j - 1];
                }
            }
        }
        return f[s.Length, p.Length];
    }
}
```

<!-- tabs:end -->

<!-- end -->
