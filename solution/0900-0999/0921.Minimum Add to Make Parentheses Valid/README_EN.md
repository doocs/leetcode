# [921. Minimum Add to Make Parentheses Valid](https://leetcode.com/problems/minimum-add-to-make-parentheses-valid)

[中文文档](/solution/0900-0999/0921.Minimum%20Add%20to%20Make%20Parentheses%20Valid/README.md)

## Description

<p>A parentheses string is valid if and only if:</p>

<ul>
	<li>It is the empty string,</li>
	<li>It can be written as <code>AB</code> (<code>A</code> concatenated with <code>B</code>), where <code>A</code> and <code>B</code> are valid strings, or</li>
	<li>It can be written as <code>(A)</code>, where <code>A</code> is a valid string.</li>
</ul>

<p>You are given a parentheses string <code>s</code>. In one move, you can insert a parenthesis at any position of the string.</p>

<ul>
	<li>For example, if <code>s = &quot;()))&quot;</code>, you can insert an opening parenthesis to be <code>&quot;(<strong>(</strong>)))&quot;</code> or a closing parenthesis to be <code>&quot;())<strong>)</strong>)&quot;</code>.</li>
</ul>

<p>Return <em>the minimum number of moves required to make </em><code>s</code><em> valid</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;())&quot;
<strong>Output:</strong> 1
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;(((&quot;
<strong>Output:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s[i]</code> is either <code>&#39;(&#39;</code> or <code>&#39;)&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minAddToMakeValid(self, s: str) -> int:
        stk = []
        for c in s:
            if c == '(':
                stk.append(c)
            else:
                if stk and stk[-1] == '(':
                    stk.pop()
                else:
                    stk.append(c)
        return len(stk)
```

### **Java**

```java
class Solution {
    public int minAddToMakeValid(String s) {
        Deque<Character> stk = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stk.push(c);
            } else {
                if (!stk.isEmpty() && stk.peek() == '(') {
                    stk.pop();
                } else {
                    stk.push(c);
                }
            }
        }
        return stk.size();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minAddToMakeValid(string s) {
        stack<char> stk;
        for (char& c : s) {
            if (c == '(')
                stk.push(c);
            else {
                if (!stk.empty() && stk.top() == '(') {
                    stk.pop();
                } else
                    stk.push(c);
            }
        }
        return stk.size();
    }
};
```

### **Go**

```go
func minAddToMakeValid(s string) int {
	var stk []rune
	for _, c := range s {
		if c == '(' {
			stk = append(stk, c)
		} else {
			if len(stk) > 0 && stk[len(stk)-1] == '(' {
				stk = stk[:len(stk)-1]
			} else {
				stk = append(stk, c)
			}
		}
	}
	return len(stk)
}
```

### **...**

```

```

<!-- tabs:end -->
