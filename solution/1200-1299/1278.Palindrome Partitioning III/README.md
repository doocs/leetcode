# [1278. 分割回文串 III](https://leetcode.cn/problems/palindrome-partitioning-iii)

[English Version](/solution/1200-1299/1278.Palindrome%20Partitioning%20III/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个由小写字母组成的字符串&nbsp;<code>s</code>，和一个整数&nbsp;<code>k</code>。</p>

<p>请你按下面的要求分割字符串：</p>

<ul>
	<li>首先，你可以将&nbsp;<code>s</code>&nbsp;中的部分字符修改为其他的小写英文字母。</li>
	<li>接着，你需要把&nbsp;<code>s</code>&nbsp;分割成&nbsp;<code>k</code>&nbsp;个非空且不相交的子串，并且每个子串都是回文串。</li>
</ul>

<p>请返回以这种方式分割字符串所需修改的最少字符数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = &quot;abc&quot;, k = 2
<strong>输出：</strong>1
<strong>解释：</strong>你可以把字符串分割成 &quot;ab&quot; 和 &quot;c&quot;，并修改 &quot;ab&quot; 中的 1 个字符，将它变成回文串。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = &quot;aabbc&quot;, k = 3
<strong>输出：</strong>0
<strong>解释：</strong>你可以把字符串分割成 &quot;aa&quot;、&quot;bb&quot; 和 &quot;c&quot;，它们都是回文串。</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>s = &quot;leetcode&quot;, k = 8
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code>&nbsp;中只含有小写英文字母。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

定义 $dp[i][j]$ 表示将字符串 $s$ 的前 $i$ 个字符分割成 $j$ 个回文串所需要的最少修改次数，我们假定 $i$ 下标从 $1$ 开始，答案为 $dp[n][k]$。

对于 $dp[i][j]$，我们可以枚举第 $j-1$ 个回文串的最后一个字符的位置 $h$，那么 $dp[i][j]$ 就等于 $dp[h][j-1] + g[h][i-1]$ 的较小值，其中 $g[h][i-1]$ 表示将字符串 $s[h..i-1]$ 变成回文串所需要的最少修改次数（这一部分我们可以通过预处理得到，时间复杂度 $O(n^2)$。

时间复杂度 $O(n^2\times k)$。其中 $n$ 为字符串 $s$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def palindromePartition(self, s: str, k: int) -> int:
        n = len(s)
        g = [[0] * n for _ in range(n)]
        for i in range(n - 1, -1, -1):
            for j in range(i + 1, n):
                g[i][j] = int(s[i] != s[j])
                if i + 1 < j:
                    g[i][j] += g[i + 1][j - 1]

        f = [[0] * (k + 1) for _ in range(n + 1)]
        for i in range(1, n + 1):
            for j in range(1, min(i, k) + 1):
                if j == 1:
                    f[i][j] = g[0][i - 1]
                else:
                    f[i][j] = inf
                    for h in range(j - 1, i):
                        f[i][j] = min(f[i][j], f[h][j - 1] + g[h][i - 1])
        return f[n][k]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int palindromePartition(String s, int k) {
        int n = s.length();
        int[][] g = new int[n][n];
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i; j < n; ++j) {
                g[i][j] = s.charAt(i) != s.charAt(j) ? 1 : 0;
                if (i + 1 < j) {
                    g[i][j] += g[i + 1][j - 1];
                }
            }
        }
        int[][] f = new int[n + 1][k + 1];
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= Math.min(i, k); ++j) {
                if (j == 1) {
                    f[i][j] = g[0][i - 1];
                } else {
                    f[i][j] = 10000;
                    for (int h = j - 1; h < i; ++h) {
                        f[i][j] = Math.min(f[i][j], f[h][j - 1] + g[h][i - 1]);
                    }
                }
            }
        }
        return f[n][k];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int palindromePartition(string s, int k) {
        int n = s.size();
        vector<vector<int>> g(n, vector<int>(n));
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i; j < n; ++j) {
                g[i][j] = s[i] != s[j] ? 1 : 0;
                if (i + 1 < j) g[i][j] += g[i + 1][j - 1];
            }
        }
        vector<vector<int>> f(n + 1, vector<int>(k + 1));
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= min(i, k); ++j) {
                if (j == 1) {
                    f[i][j] = g[0][i - 1];
                } else {
                    f[i][j] = 10000;
                    for (int h = j - 1; h < i; ++h) {
                        f[i][j] = min(f[i][j], f[h][j - 1] + g[h][i - 1]);
                    }
                }
            }
        }
        return f[n][k];
    }
};
```

### **Go**

```go
func palindromePartition(s string, k int) int {
	n := len(s)
	g := make([][]int, n)
	for i := range g {
		g[i] = make([]int, n)
	}
	for i := n - 1; i >= 0; i-- {
		for j := 1; j < n; j++ {
			if s[i] != s[j] {
				g[i][j] = 1
			}
			if i+1 < j {
				g[i][j] += g[i+1][j-1]
			}
		}
	}
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, k+1)
	}
	for i := 1; i <= n; i++ {
		for j := 1; j <= min(i, k); j++ {
			if j == 1 {
				f[i][j] = g[0][i-1]
			} else {
				f[i][j] = 100000
				for h := j - 1; h < i; h++ {
					f[i][j] = min(f[i][j], f[h][j-1]+g[h][i-1])
				}
			}
		}
	}
	return f[n][k]
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
