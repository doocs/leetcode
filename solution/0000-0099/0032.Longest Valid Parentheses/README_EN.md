# [32. Longest Valid Parentheses](https://leetcode.com/problems/longest-valid-parentheses)

[中文文档](/solution/0000-0099/0032.Longest%20Valid%20Parentheses/README.md)

## Description

<p>Given a string containing just the characters <code>&#39;(&#39;</code> and <code>&#39;)&#39;</code>, return <em>the length of the longest valid (well-formed) parentheses </em><span data-keyword="substring-nonempty"><em>substring</em></span>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;(()&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> The longest valid parentheses substring is &quot;()&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;)()())&quot;
<strong>Output:</strong> 4
<strong>Explanation:</strong> The longest valid parentheses substring is &quot;()()&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;&quot;
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= s.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>s[i]</code> is <code>&#39;(&#39;</code>, or <code>&#39;)&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
        if (s[i - 1] == ')') {
            if (s[i - 2] == '(') {
                f[i] = f[i - 2] + 2;
            } else {
                const j = i - f[i - 1] - 1;
                if (j && s[j - 1] == '(') {
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
