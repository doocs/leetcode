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

我们定义 $f[i]$ 表示以 $s[i-1]$ 结尾的最长有效括号的长度，那么答案就是 $max(f[i])$。

当 $i \lt 2$ 时，字符串长度小于 $2$，不存在有效括号，因此 $f[i] = 0$。

当 $i \ge 2$ 时，我们考虑以 $s[i-1]$ 结尾的最长有效括号的长度 $f[i]$：

-   如果 $s[i-1]$ 是左括号，那么以 $s[i-1]$ 结尾的最长有效括号的长度一定为 $0$，因此 $f[i] = 0$。
-   如果 $s[i-1]$ 是右括号，有以下两种情况：
    -   如果 $s[i-2]$ 是左括号，那么以 $s[i-1]$ 结尾的最长有效括号的长度为 $f[i-2] + 2$。
    -   如果 $s[i-2]$ 是右括号，那么以 $s[i-1]$ 结尾的最长有效括号的长度为 $f[i-1] + 2$，但是还需要考虑 $s[i-f[i-1]-2]$ 是否是左括号，如果是左括号，那么以 $s[i-1]$ 结尾的最长有效括号的长度为 $f[i-1] + 2 + f[i-f[i-1]-2]$。

因此，我们可以得到状态转移方程：

$$
\begin{cases}
f[i] = 0, & \text{if } s[i-1] = '(',\\
f[i] = f[i-2] + 2, & \text{if } s[i-1] = ')' \text{ and } s[i-2] = '(',\\
f[i] = f[i-1] + 2 + f[i-f[i-1]-2], & \text{if } s[i-1] = ')' \text{ and } s[i-2] = ')' \text{ and } s[i-f[i-1]-2] = '(',\\
\end{cases}
$$

最后，我们只需要返回 $max(f)$ 即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def longestValidParentheses(self, s: str) -> int:
        n = len(s)
        if n < 2:
            return 0
        f = [0] * (n + 1)
        for i in range(2, n + 1):
            if s[i - 1] == ')':
                if s[i - 2] == '(':
                    f[i] = f[i - 2] + 2
                else:
                    j = i - f[i - 1] - 1
                    if j > 0 and s[j - 1] == '(':
                        f[i] = f[i - 1] + 2 + f[j - 1]
        return max(f)
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
        int[] f = new int[n + 1];
        int ans = 0;
        for (int i = 2; i <= n; ++i) {
            if (s.charAt(i - 1) == ')') {
                if (s.charAt(i - 2) == '(') {
                    f[i] = f[i - 2] + 2;
                } else {
                    int j = i - f[i - 1] - 1;
                    if (j > 0 && s.charAt(j - 1) == '(') {
                        f[i] = f[i - 1] + 2 + f[j - 1];
                    }
                }
                ans = Math.max(ans, f[i]);
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
        if (n < 2) {
            return 0;
        }
        int f[n + 1];
        memset(f, 0, sizeof(f));
        for (int i = 2; i <= n; ++i) {
            if (s[i - 1] == ')') {
                if (s[i - 2] == '(') {
                    f[i] = f[i - 2] + 2;
                } else {
                    int j = i - f[i - 1] - 1;
                    if (j && s[j - 1] == '(') {
                        f[i] = f[i - 1] + 2 + f[j - 1];
                    }
                }
            }
        }
        return *max_element(f, f + n + 1);
    }
};
```

### **Go**

```go
func longestValidParentheses(s string) (ans int) {
	n := len(s)
	if n < 2 {
		return 0
	}
	f := make([]int, n+1)
	for i := 2; i <= n; i++ {
		if s[i-1] == ')' {
			if s[i-2] == '(' {
				f[i] = f[i-2] + 2
			} else {
				j := i - f[i-1] - 1
				if j > 0 && s[j-1] == '(' {
					f[i] = f[i-1] + 2 + f[j-1]
				}
			}
			ans = max(ans, f[i])
		}
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **C#**

```cs
public class Solution {
    public int LongestValidParentheses(string s) {
        int n = s.Length;
        if (n < 2) {
            return 0;
        }
        int[] f = new int[n + 1];
        int ans = 0;
        for (int i = 2; i <= n; ++i) {
            if (s[i - 1] == ')') {
                if (s[i - 2] == '(') {
                    f[i] = f[i - 2] + 2;
                } else {
                    int j = i - f[i - 1] - 1;
                    if (j > 0 && s[j - 1] == '(') {
                        f[i] = f[i - 1] + 2 + f[j - 1];
                    }
                }
                ans = Math.Max(ans, f[i]);
            }
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function longestValidParentheses(s: string): number {
    const n = s.length;
    const f: number[] = new Array(n + 1).fill(0);
    for (let i = 2; i <= n; ++i) {
        if (s[i - 1] === ')') {
            if (s[i - 2] === '(') {
                f[i] = f[i - 2] + 2;
            } else {
                const j = i - f[i - 1] - 1;
                if (j && s[j - 1] === '(') {
                    f[i] = f[i - 1] + 2 + f[j - 1];
                }
            }
        }
    }
    return Math.max(...f);
}
```

### **...**

```

```

<!-- tabs:end -->
