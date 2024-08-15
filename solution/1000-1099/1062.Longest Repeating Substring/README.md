---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1062.Longest%20Repeating%20Substring/README.md
tags:
    - 字符串
    - 二分查找
    - 动态规划
    - 后缀数组
    - 哈希函数
    - 滚动哈希
---

<!-- problem:start -->

# [1062. 最长重复子串 🔒](https://leetcode.cn/problems/longest-repeating-substring)

[English Version](/solution/1000-1099/1062.Longest%20Repeating%20Substring/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定字符串&nbsp;<code>s</code>，找出最长重复子串的长度。如果不存在重复子串就返回 <code>0</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>"abcd"
<strong>输出：</strong>0
<strong>解释：</strong>没有重复子串。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>"abbaba"
<strong>输出：</strong>2
<strong>解释：</strong>最长的重复子串为 "ab" 和 "ba"，每个出现 2 次。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>"aabcaabdaab"
<strong>输出：</strong>3
<strong>解释：</strong>最长的重复子串为 "aab"，出现 3 次。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 2000</code></li>
	<li>字符串&nbsp;<code>s</code>&nbsp;仅包含从&nbsp;<code>'a'</code> 到&nbsp;<code>'z'</code>&nbsp;的小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

定义 $dp[i][j]$ 表示以 $s[i]$ 和 $s[j]$ 结尾的最长重复子串 🔒 的长度。状态转移方程为：

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

相似题目：

-   [1044. 最长重复子串 🔒](https://github.com/doocs/leetcode/blob/main/solution/1000-1099/1044.Longest%20Duplicate%20Substring/README.md)

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
