# [516. 最长回文子序列](https://leetcode-cn.com/problems/longest-palindromic-subsequence)

[English Version](/solution/0500-0599/0516.Longest%20Palindromic%20Subsequence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串 <code>s</code> ，找到其中最长的回文子序列，并返回该序列的长度。可以假设 <code>s</code> 的最大长度为 <code>1000</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong><br>
输入:</p>

<pre>&quot;bbbab&quot;
</pre>

<p>输出:</p>

<pre>4
</pre>

<p>一个可能的最长回文子序列为 &quot;bbbb&quot;。</p>

<p><strong>示例 2:</strong><br>
输入:</p>

<pre>&quot;cbbd&quot;
</pre>

<p>输出:</p>

<pre>2
</pre>

<p>一个可能的最长回文子序列为 &quot;bb&quot;。</p>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code> 只包含小写英文字母</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

动态规划。

设 `dp[i][j]` 表示字符串 `s[i..j]` 中的最长回文子序列的长度。初始化 `dp[i][i] = 1`(`i∈[0, n-1]`)。

- 对于 `s[i] == s[j]`，`dp[i][j] = dp[i + 1][j - 1] + 2`；
- 对于 `s[i] != s[j]`，`dp[i][j] = max(dp[i + 1][j], dp[i][j - 1])`。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def longestPalindromeSubseq(self, s: str) -> int:
        n = len(s)
        dp = [[0] * n for _ in range(n)]
        for i in range(n):
            dp[i][i] = 1
        for j in range(1, n):
            for i in range(j - 1, -1, -1):
                if s[i] == s[j]:
                    dp[i][j] = dp[i + 1][j - 1] + 2
                else:
                    dp[i][j] = max(dp[i + 1][j], dp[i][j - 1])
        return dp[0][-1]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; ++i) {
            dp[i][i] = 1;
        }
        for (int j = 1; j < n; ++j) {
            for (int i = j - 1; i >= 0; --i) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int longestPalindromeSubseq(string s) {
        int n = s.size();
        vector<vector<int>> dp(n, vector<int>(n, 0));
        for (int i = 0; i < n; ++i) {
            dp[i][i] = 1;
        }
        for (int j = 1; j < n; ++j) {
            for (int i = j - 1; i >= 0; --i) {
                if (s[i] == s[j]) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }
};
```

### **Go**

```go
func longestPalindromeSubseq(s string) int {
	n := len(s)
	dp := make([][]int, n)
	for i := 0; i < n; i++ {
		dp[i] = make([]int, n)
		dp[i][i] = 1
	}
	for j := 1; j < n; j++ {
		for i := j - 1; i >= 0; i-- {
			if s[i] == s[j] {
				dp[i][j] = dp[i+1][j-1] + 2
			} else {
				dp[i][j] = max(dp[i+1][j], dp[i][j-1])
			}
		}
	}
	return dp[0][n-1]
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
