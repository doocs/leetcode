# [1062. 最长重复子串](https://leetcode.cn/problems/longest-repeating-substring)

[English Version](/solution/1000-1099/1062.Longest%20Repeating%20Substring/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定字符串&nbsp;<code>S</code>，找出最长重复子串的长度。如果不存在重复子串就返回 <code>0</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>&quot;abcd&quot;
<strong>输出：</strong>0
<strong>解释：</strong>没有重复子串。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>&quot;abbaba&quot;
<strong>输出：</strong>2
<strong>解释：</strong>最长的重复子串为 &quot;ab&quot; 和 &quot;ba&quot;，每个出现 2 次。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>&quot;aabcaabdaab&quot;
<strong>输出：</strong>3
<strong>解释：</strong>最长的重复子串为 &quot;aab&quot;，出现 3 次。
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>&quot;aaaaa&quot;
<strong>输出：</strong>4
<strong>解释：</strong>最长的重复子串为 &quot;aaaa&quot;，出现 2 次。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li>字符串&nbsp;<code>S</code>&nbsp;仅包含从&nbsp;<code>&#39;a&#39;</code> 到&nbsp;<code>&#39;z&#39;</code>&nbsp;的小写英文字母。</li>
	<li><code>1 &lt;= S.length &lt;= 1500</code></li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

定义 $dp[i][j]$ 表示以 $s[i]$ 和 $s[j]$ 结尾的最长重复子串的长度。状态转移方程为：

$$
dp[i][j]=
\begin{cases}
dp[i-1][j-1]+1, & i>0 \cap s[i]=s[j] \\
1, & i=0 \cap s[i]=s[j] \\
0, &  s[i] \neq s[j]
\end{cases}
$$

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。

其中 $n$ 为字符串 $s$ 的长度。

相似题目：[1044. 最长重复子串](/solution/1000-1099/1044.Longest%20Duplicate%20Substring/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def longestRepeatingSubstring(self, s: str) -> int:
        n = len(s)
        dp = [[0] * n for _ in range(n)]
        ans = 0
        for i in range(n):
            for j in range(i + 1, n):
                if s[i] == s[j]:
                    dp[i][j] = dp[i - 1][j - 1] + 1 if i else 1
                    ans = max(ans, dp[i][j])
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int longestRepeatingSubstring(String s) {
        int n = s.length();
        int ans = 0;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = i > 0 ? dp[i - 1][j - 1] + 1 : 1;
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int longestRepeatingSubstring(string s) {
        int n = s.size();
        vector<vector<int>> dp(n, vector<int>(n));
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (s[i] == s[j]) {
                    dp[i][j] = i ? dp[i - 1][j - 1] + 1 : 1;
                    ans = max(ans, dp[i][j]);
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func longestRepeatingSubstring(s string) int {
	n := len(s)
	dp := make([][]int, n)
	for i := range dp {
		dp[i] = make([]int, n)
	}
	ans := 0
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			if s[i] == s[j] {
				if i == 0 {
					dp[i][j] = 1
				} else {
					dp[i][j] = dp[i-1][j-1] + 1
				}
				ans = max(ans, dp[i][j])
			}
		}
	}
	return ans
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
