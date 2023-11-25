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

**Solution 1: Dynamic Programming**

We define $f[i]$ to be the length of the longest valid parentheses that ends with $s[i-1]$, and the answer is $max(f[i])$.

When $i \lt 2$, the length of the string is less than $2$, and there is no valid parentheses, so $f[i] = 0$.

When $i \ge 2$, we consider the length of the longest valid parentheses that ends with $s[i-1]$, that is, $f[i]$:

-   If $s[i-1]$ is a left parenthesis, then the length of the longest valid parentheses that ends with $s[i-1]$ must be $0$, so $f[i] = 0$.
-   If $s[i-1]$ is a right parenthesis, there are the following two cases:
    -   If $s[i-2]$ is a left parenthesis, then the length of the longest valid parentheses that ends with $s[i-1]$ is $f[i-2] + 2$.
    -   If $s[i-2]$ is a right parenthesis, then the length of the longest valid parentheses that ends with $s[i-1]$ is $f[i-1] + 2$, but we also need to consider whether $s[i-f[i-1]-2]$ is a left parenthesis. If it is a left parenthesis, then the length of the longest valid parentheses that ends with $s[i-1]$ is $f[i-1] + 2 + f[i-f[i-1]-2]$.

Therefore, we can get the state transition equation:

$$
\begin{cases}
f[i] = 0, & \text{if } s[i-1] = '(',\\
f[i] = f[i-2] + 2, & \text{if } s[i-1] = ')' \text{ and } s[i-2] = '(',\\
f[i] = f[i-1] + 2 + f[i-f[i-1]-2], & \text{if } s[i-1] = ')' \text{ and } s[i-2] = ')' \text{ and } s[i-f[i-1]-2] = '(',\\
\end{cases}
$$

Finally, we only need to return $max(f)$.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the string.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def longestValidParentheses(self, s: str) -> int:
        n = len(s)
        f = [0] * (n + 1)
        for i, c in enumerate(s, 1):
            if c == ")":
                if i > 1 and s[i - 2] == "(":
                    f[i] = f[i - 2] + 2
                else:
                    j = i - f[i - 1] - 1
                    if j and s[j - 1] == "(":
                        f[i] = f[i - 1] + 2 + f[j - 1]
        return max(f)
```

### **Java**

```java
class Solution {
    public int longestValidParentheses(String s) {
        int n = s.length();
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
func longestValidParentheses(s string) int {
	n := len(s)
	f := make([]int, n+1)
	for i := 2; i <= n; i++ {
		if s[i-1] == ')' {
			if s[i-2] == '(' {
				f[i] = f[i-2] + 2
			} else if j := i - f[i-1] - 1; j > 0 && s[j-1] == '(' {
				f[i] = f[i-1] + 2 + f[j-1]
			}
		}
	}
	return slices.Max(f)
}
```

### **C#**

```cs
public class Solution {
    public int LongestValidParentheses(string s) {
        int n = s.Length;
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

### **JavaScript**

```js
/**
 * @param {string} s
 * @return {number}
 */
var longestValidParentheses = function (s) {
    const n = s.length;
    const f = new Array(n + 1).fill(0);
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
};
```

### **...**

```

```

<!-- tabs:end -->
