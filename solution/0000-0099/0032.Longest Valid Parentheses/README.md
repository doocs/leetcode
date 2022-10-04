# [32. 最长有效括号](https://leetcode.cn/problems/longest-valid-parentheses)

[English Version](/solution/0000-0099/0032.Longest%20Valid%20Parentheses/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个只包含 <code>'('</code> 和 <code>')'</code> 的字符串，找出最长有效（格式正确且连续）括号子串的长度。</p>

<p> </p>

<div class="original__bRMd">
<div>
<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "(()"
<strong>输出：</strong>2
<strong>解释：</strong>最长有效括号子串是 "()"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = ")()())"
<strong>输出：</strong>4
<strong>解释：</strong>最长有效括号子串是 "()()"
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = ""
<strong>输出：</strong>0
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 <= s.length <= 3 * 10<sup>4</sup></code></li>
	<li><code>s[i]</code> 为 <code>'('</code> 或 <code>')'</code></li>
</ul>
</div>
</div>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

定义 `dp[i]` 表示以 `s[i]` 结尾的最长有效括号的长度，答案为 `max(dp[i])`。

`dp[i]` 的值有以下几种情况：

-   若 `s[i]` 为 `(`，那么 `dp[i] = 0`；
-   若 `s[i]` 为 `)`，且 `s[i - 1]` 为 `(`，那么 `dp[i] = dp[i - 2] + 2`；
-   若 `s[i]` 为 `)`，且 `s[i - 1]` 为 `)` 且 `s[i - dp[i - 1] - 1]` 为 `(`，那么 `dp[i] = dp[i - 1] + 2 + dp[i - dp[i - 1] - 2]`。

以上需要注意边界的判断处理。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 `s` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def longestValidParentheses(self, s: str) -> int:
        n = len(s)
        if n < 2:
            return 0
        dp = [0] * n
        for i in range(1, n):
            if s[i] == ')':
                if s[i - 1] == '(':
                    dp[i] = 2 + (dp[i - 2] if i > 1 else 0)
                else:
                    j = i - dp[i - 1] - 1
                    if j >= 0 and s[j] == '(':
                        dp[i] = 2 + dp[i - 1] + (dp[j - 1] if j else 0)
        return max(dp)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int longestValidParentheses(String s) {
        int n = s.length();
        if (n < 2) {
            return 0;
        }
        char[] cs = s.toCharArray();
        int[] dp = new int[n];
        int ans = 0;
        for (int i = 1; i < n; ++i) {
            if (cs[i] == ')') {
                if (cs[i - 1] == '(') {
                    dp[i] = 2 + (i > 1 ? dp[i - 2] : 0);
                } else {
                    int j = i - dp[i - 1] - 1;
                    if (j >= 0 && cs[j] == '(') {
                        dp[i] = 2 + dp[i - 1] + (j > 0 ? dp[j - 1] : 0);
                    }
                }
                ans = Math.max(ans, dp[i]);
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
    int longestValidParentheses(string s) {
        int n = s.size();
        if (n < 2) return 0;
        vector<int> dp(n);
        int ans = 0;
        for (int i = 1; i < n; ++i) {
            if (s[i] == ')') {
                if (s[i - 1] == '(') {
                    dp[i] = 2 + (i > 1 ? dp[i - 2] : 0);
                } else {
                    int j = i - dp[i - 1] - 1;
                    if (~j && s[j] == '(') {
                        dp[i] = 2 + dp[i - 1] + (j ? dp[j - 1] : 0);
                    }
                }
                ans = max(ans, dp[i]);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func longestValidParentheses(s string) int {
	n := len(s)
	if n < 2 {
		return 0
	}
	dp := make([]int, n)
	ans := 0
	for i := 1; i < n; i++ {
		if s[i] == ')' {
			if s[i-1] == '(' {
				dp[i] = 2
				if i > 1 {
					dp[i] += dp[i-2]
				}
			} else {
				j := i - dp[i-1] - 1
				if j >= 0 && s[j] == '(' {
					dp[i] = 2 + dp[i-1]
					if j > 0 {
						dp[i] += dp[j-1]
					}
				}
			}
			ans = max(ans, dp[i])
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
