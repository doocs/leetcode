# [44. 通配符匹配](https://leetcode.cn/problems/wildcard-matching)

[English Version](/solution/0000-0099/0044.Wildcard%20Matching/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<div class="title__3Vvk">给你一个输入字符串 (<code>s</code>) 和一个字符模式 (<code>p</code>) ，请你实现一个支持 <code>'?'</code> 和 <code>'*'</code> 匹配规则的通配符匹配：</div>

<ul>
	<li class="title__3Vvk"><code>'?'</code> 可以匹配任何单个字符。</li>
	<li class="title__3Vvk"><code>'*'</code> 可以匹配任意字符序列（包括空字符序列）。</li>
</ul>

<div class="original__bRMd">
<div>
<p>判定匹配成功的充要条件是：字符模式必须能够 <strong>完全匹配</strong> 输入字符串（而不是部分匹配）。</p>
</div>
</div>
&nbsp;

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "aa", p = "a"
<strong>输出：</strong>false
<strong>解释：</strong>"a" 无法匹配 "aa" 整个字符串。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "aa", p = "*"
<strong>输出：</strong>true
<strong>解释：</strong>'*' 可以匹配任意字符串。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "cb", p = "?a"
<strong>输出：</strong>false
<strong>解释：</strong>'?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= s.length, p.length &lt;= 2000</code></li>
	<li><code>s</code> 仅由小写英文字母组成</li>
	<li><code>p</code> 仅由小写英文字母、<code>'?'</code> 或 <code>'*'</code> 组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

定义状态 $dp[i][j]$ 表示 $s$ 的前 $i$ 个字符和 $p$ 的前 $j$ 个字符是否匹配。

状态转移方程如下：

$$
dp[i][j]=
\begin{cases}
dp[i-1][j-1] & \text{if } s[i-1]=p[j-1] \text{ or } p[j-1]=\text{?} \\
dp[i-1][j-1] \lor dp[i-1][j] \lor dp[i][j-1] & \text{if } p[j-1]=\text{*} \\
\text{false} & \text{otherwise}
\end{cases}
$$

时间复杂度 $O(m\times n)$，空间复杂度 $O(m\times n)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **C++**

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

### **Go**

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

### **...**

```

```

<!-- tabs:end -->
