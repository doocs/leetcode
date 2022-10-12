# [856. Score of Parentheses](https://leetcode.com/problems/score-of-parentheses)

[中文文档](/solution/0800-0899/0856.Score%20of%20Parentheses/README.md)

## Description

<p>Given a balanced parentheses string <code>s</code>, return <em>the <strong>score</strong> of the string</em>.</p>

<p>The <strong>score</strong> of a balanced parentheses string is based on the following rule:</p>

<ul>
	<li><code>&quot;()&quot;</code> has score <code>1</code>.</li>
	<li><code>AB</code> has score <code>A + B</code>, where <code>A</code> and <code>B</code> are balanced parentheses strings.</li>
	<li><code>(A)</code> has score <code>2 * A</code>, where <code>A</code> is a balanced parentheses string.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;()&quot;
<strong>Output:</strong> 1
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;(())&quot;
<strong>Output:</strong> 2
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;()()&quot;
<strong>Output:</strong> 2
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 50</code></li>
	<li><code>s</code> consists of only <code>&#39;(&#39;</code> and <code>&#39;)&#39;</code>.</li>
	<li><code>s</code> is a balanced parentheses string.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def scoreOfParentheses(self, s: str) -> int:
        ans = d = 0
        for i, c in enumerate(s):
            if c == '(':
                d += 1
            else:
                d -= 1
                if s[i - 1] == '(':
                    ans += 1 << d
        return ans
```

### **Java**

```java
class Solution {
    public int scoreOfParentheses(String s) {
        int ans = 0, d = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(') {
                ++d;
            } else {
                --d;
                if (s.charAt(i - 1) == '(') {
                    ans += 1 << d;
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
    int scoreOfParentheses(string s) {
        int ans = 0, d = 0;
        for (int i = 0; i < s.size(); ++i) {
            if (s[i] == '(') {
                ++d;
            } else {
                --d;
                if (s[i - 1] == '(') {
                    ans += 1 << d;
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func scoreOfParentheses(s string) int {
	ans, d := 0, 0
	for i, c := range s {
		if c == '(' {
			d++
		} else {
			d--
			if s[i-1] == '(' {
				ans += 1 << d
			}
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
