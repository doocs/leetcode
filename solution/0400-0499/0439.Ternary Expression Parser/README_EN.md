# [439. Ternary Expression Parser](https://leetcode.com/problems/ternary-expression-parser)

[中文文档](/solution/0400-0499/0439.Ternary%20Expression%20Parser/README.md)

## Description

<p>Given a string <code>expression</code> representing arbitrarily nested ternary expressions, evaluate the expression, and return <em>the result of it</em>.</p>

<p>You can always assume that the given expression is valid and only contains digits, <code>&#39;?&#39;</code>, <code>&#39;:&#39;</code>, <code>&#39;T&#39;</code>, and <code>&#39;F&#39;</code> where <code>&#39;T&#39;</code> is true and <code>&#39;F&#39;</code> is false. All the numbers in the expression are <strong>one-digit</strong> numbers (i.e., in the range <code>[0, 9]</code>).</p>

<p>The conditional expressions group right-to-left (as usual in most languages), and the result of the expression will always evaluate to either a digit, <code>&#39;T&#39;</code> or <code>&#39;F&#39;</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> expression = &quot;T?2:3&quot;
<strong>Output:</strong> &quot;2&quot;
<strong>Explanation:</strong> If true, then result is 2; otherwise result is 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> expression = &quot;F?1:T?4:5&quot;
<strong>Output:</strong> &quot;4&quot;
<strong>Explanation:</strong> The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:
&quot;(F ? 1 : (T ? 4 : 5))&quot; --&gt; &quot;(F ? 1 : 4)&quot; --&gt; &quot;4&quot;
or &quot;(F ? 1 : (T ? 4 : 5))&quot; --&gt; &quot;(T ? 4 : 5)&quot; --&gt; &quot;4&quot;
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> expression = &quot;T?T?F:5:3&quot;
<strong>Output:</strong> &quot;F&quot;
<strong>Explanation:</strong> The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:
&quot;(T ? (T ? F : 5) : 3)&quot; --&gt; &quot;(T ? F : 3)&quot; --&gt; &quot;F&quot;
&quot;(T ? (T ? F : 5) : 3)&quot; --&gt; &quot;(T ? F : 5)&quot; --&gt; &quot;F&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>5 &lt;= expression.length &lt;= 10<sup>4</sup></code></li>
	<li><code>expression</code> consists of digits, <code>&#39;T&#39;</code>, <code>&#39;F&#39;</code>, <code>&#39;?&#39;</code>, and <code>&#39;:&#39;</code>.</li>
	<li>It is <strong>guaranteed</strong> that <code>expression</code> is a valid ternary expression and that each number is a <strong>one-digit number</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def parseTernary(self, expression: str) -> str:
        stk = []
        cond = False
        for c in expression[::-1]:
            if c == ':':
                continue
            if c == '?':
                cond = True
            else:
                if cond:
                    if c == 'T':
                        x = stk.pop()
                        stk.pop()
                        stk.append(x)
                    else:
                        stk.pop()
                    cond = False
                else:
                    stk.append(c)
        return stk[0]
```

### **Java**

```java
class Solution {
    public String parseTernary(String expression) {
        Deque<Character> stk = new ArrayDeque<>();
        boolean cond = false;
        for (int i = expression.length() - 1; i >= 0; --i) {
            char c = expression.charAt(i);
            if (c == ':') {
                continue;
            }
            if (c == '?') {
                cond = true;
            } else {
                if (cond) {
                    if (c == 'T') {
                        char x = stk.pop();
                        stk.pop();
                        stk.push(x);
                    } else {
                        stk.pop();
                    }
                    cond = false;
                } else {
                    stk.push(c);
                }
            }
        }
        return String.valueOf(stk.peek());
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string parseTernary(string expression) {
        string stk;
        bool cond = false;
        reverse(expression.begin(), expression.end());
        for (char& c : expression) {
            if (c == ':') {
                continue;
            }
            if (c == '?') {
                cond = true;
            } else {
                if (cond) {
                    if (c == 'T') {
                        char x = stk.back();
                        stk.pop_back();
                        stk.pop_back();
                        stk.push_back(x);
                    } else {
                        stk.pop_back();
                    }
                    cond = false;
                } else {
                    stk.push_back(c);
                }
            }
        }
        return {stk[0]};
    }
};
```

### **Go**

```go
func parseTernary(expression string) string {
	stk := []byte{}
	cond := false
	for i := len(expression) - 1; i >= 0; i-- {
		c := expression[i]
		if c == ':' {
			continue
		}
		if c == '?' {
			cond = true
		} else {
			if cond {
				if c == 'T' {
					x := stk[len(stk)-1]
					stk = stk[:len(stk)-2]
					stk = append(stk, x)
				} else {
					stk = stk[:len(stk)-1]
				}
				cond = false
			} else {
				stk = append(stk, c)
			}
		}
	}
	return string(stk[0])
}
```

### **...**

```

```

<!-- tabs:end -->
