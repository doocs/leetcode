# [1541. Minimum Insertions to Balance a Parentheses String](https://leetcode.com/problems/minimum-insertions-to-balance-a-parentheses-string)

[中文文档](/solution/1500-1599/1541.Minimum%20Insertions%20to%20Balance%20a%20Parentheses%20String/README.md)

## Description

<p>Given a parentheses string <code>s</code> containing only the characters <code>&#39;(&#39;</code> and <code>&#39;)&#39;</code>. A parentheses string is <strong>balanced</strong> if:</p>

<ul>
	<li>Any left parenthesis <code>&#39;(&#39;</code> must have a corresponding two consecutive right parenthesis <code>&#39;))&#39;</code>.</li>
	<li>Left parenthesis <code>&#39;(&#39;</code> must go before the corresponding two consecutive right parenthesis <code>&#39;))&#39;</code>.</li>
</ul>

<p>In other words, we treat <code>&#39;(&#39;</code> as an opening parenthesis and <code>&#39;))&#39;</code> as a closing parenthesis.</p>

<ul>
	<li>For example, <code>&quot;())&quot;</code>, <code>&quot;())(())))&quot;</code> and <code>&quot;(())())))&quot;</code> are balanced, <code>&quot;)()&quot;</code>, <code>&quot;()))&quot;</code> and <code>&quot;(()))&quot;</code> are not balanced.</li>
</ul>

<p>You can insert the characters <code>&#39;(&#39;</code> and <code>&#39;)&#39;</code> at any position of the string to balance it if needed.</p>

<p>Return <em>the minimum number of insertions</em> needed to make <code>s</code> balanced.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;(()))&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> The second &#39;(&#39; has two matching &#39;))&#39;, but the first &#39;(&#39; has only &#39;)&#39; matching. We need to add one more &#39;)&#39; at the end of the string to be &quot;(())))&quot; which is balanced.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;())&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> The string is already balanced.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;))())(&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> Add &#39;(&#39; to match the first &#39;))&#39;, Add &#39;))&#39; to match the last &#39;(&#39;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of <code>&#39;(&#39;</code> and <code>&#39;)&#39;</code> only.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minInsertions(self, s: str) -> int:
        ans = x = 0
        i, n = 0, len(s)
        while i < n:
            if s[i] == '(':
                x += 1
            else:
                if i < n - 1 and s[i + 1] == ')':
                    i += 1
                else:
                    ans += 1
                if x == 0:
                    ans += 1
                else:
                    x -= 1
            i += 1
        ans += x << 1
        return ans
```

### **Java**

```java
class Solution {
    public int minInsertions(String s) {
        int ans = 0, x = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '(') {
                ++x;
            } else {
                if (i < n - 1 && s.charAt(i + 1) == ')') {
                    ++i;
                } else {
                    ++ans;
                }
                if (x == 0) {
                    ++ans;
                } else {
                    --x;
                }
            }
        }
        ans += x << 1;
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minInsertions(string s) {
        int ans = 0, x = 0;
        int n = s.size();
        for (int i = 0; i < n; ++i) {
            if (s[i] == '(') {
                ++x;
            } else {
                if (i < n - 1 && s[i + 1] == ')') {
                    ++i;
                } else {
                    ++ans;
                }
                if (x == 0) {
                    ++ans;
                } else {
                    --x;
                }
            }
        }
        ans += x << 1;
        return ans;
    }
};
```

### **Go**

```go
func minInsertions(s string) int {
	ans, x, n := 0, 0, len(s)
	for i := 0; i < n; i++ {
		if s[i] == '(' {
			x++
		} else {
			if i < n-1 && s[i+1] == ')' {
				i++
			} else {
				ans++
			}
			if x == 0 {
				ans++
			} else {
				x--
			}
		}
	}
	ans += x << 1
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
