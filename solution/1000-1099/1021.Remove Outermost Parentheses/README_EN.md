# [1021. Remove Outermost Parentheses](https://leetcode.com/problems/remove-outermost-parentheses)

[中文文档](/solution/1000-1099/1021.Remove%20Outermost%20Parentheses/README.md)

## Description

<p>A valid parentheses string is either empty <code>&quot;&quot;</code>, <code>&quot;(&quot; + A + &quot;)&quot;</code>, or <code>A + B</code>, where <code>A</code> and <code>B</code> are valid parentheses strings, and <code>+</code> represents string concatenation.</p>

<ul>
	<li>For example, <code>&quot;&quot;</code>, <code>&quot;()&quot;</code>, <code>&quot;(())()&quot;</code>, and <code>&quot;(()(()))&quot;</code> are all valid parentheses strings.</li>
</ul>

<p>A valid parentheses string <code>s</code> is primitive if it is nonempty, and there does not exist a way to split it into <code>s = A + B</code>, with <code>A</code> and <code>B</code> nonempty valid parentheses strings.</p>

<p>Given a valid parentheses string <code>s</code>, consider its primitive decomposition: <code>s = P<sub>1</sub> + P<sub>2</sub> + ... + P<sub>k</sub></code>, where <code>P<sub>i</sub></code> are primitive valid parentheses strings.</p>

<p>Return <code>s</code> <em>after removing the outermost parentheses of every primitive string in the primitive decomposition of </em><code>s</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;(()())(())&quot;
<strong>Output:</strong> &quot;()()()&quot;
<strong>Explanation:</strong> 
The input string is &quot;(()())(())&quot;, with primitive decomposition &quot;(()())&quot; + &quot;(())&quot;.
After removing outer parentheses of each part, this is &quot;()()&quot; + &quot;()&quot; = &quot;()()()&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;(()())(())(()(()))&quot;
<strong>Output:</strong> &quot;()()()()(())&quot;
<strong>Explanation:</strong> 
The input string is &quot;(()())(())(()(()))&quot;, with primitive decomposition &quot;(()())&quot; + &quot;(())&quot; + &quot;(()(()))&quot;.
After removing outer parentheses of each part, this is &quot;()()&quot; + &quot;()&quot; + &quot;()(())&quot; = &quot;()()()()(())&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;()()&quot;
<strong>Output:</strong> &quot;&quot;
<strong>Explanation:</strong> 
The input string is &quot;()()&quot;, with primitive decomposition &quot;()&quot; + &quot;()&quot;.
After removing outer parentheses of each part, this is &quot;&quot; + &quot;&quot; = &quot;&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> is either <code>&#39;(&#39;</code> or <code>&#39;)&#39;</code>.</li>
	<li><code>s</code> is a valid parentheses string.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def removeOuterParentheses(self, s: str) -> str:
        ans = []
        cnt = 0
        for c in s:
            if c == '(':
                cnt += 1
                if cnt > 1:
                    ans.append(c)
            else:
                cnt -= 1
                if cnt > 0:
                    ans.append(c)
        return ''.join(ans)
```

```python
class Solution:
    def removeOuterParentheses(self, s: str) -> str:
        ans = []
        cnt = 0
        for c in s:
            if c == '(':
                cnt += 1
            if cnt > 1:
                ans.append(c)
            if c == ')':
                cnt -= 1
        return ''.join(ans)
```

### **Java**

```java
class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder ans = new StringBuilder();
        int cnt = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '(') {
                if (++cnt > 1) {
                    ans.append(c);
                }
            } else {
                if (--cnt > 0) {
                    ans.append(c);
                }
            }
        }
        return ans.toString();
    }
}
```

```java
class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder ans = new StringBuilder();
        int cnt = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '(') {
                ++cnt;
            }
            if (cnt > 1) {
                ans.append(c);
            }
            if (c == ')') {
                --cnt;
            }
        }
        return ans.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string removeOuterParentheses(string s) {
        string ans;
        int cnt = 0;
        for (char& c : s) {
            if (c == '(') {
                if (++cnt > 1) {
                    ans.push_back(c);
                }
            } else {
                if (--cnt) {
                    ans.push_back(c);
                }
            }
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    string removeOuterParentheses(string s) {
        string ans;
        int cnt = 0;
        for (char& c : s) {
            if (c == '(') {
                ++cnt;
            }
            if (cnt > 1) {
                ans.push_back(c);
            }
            if (c == ')') {
                --cnt;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func removeOuterParentheses(s string) string {
	ans := []rune{}
	cnt := 0
	for _, c := range s {
		if c == '(' {
			cnt++
			if cnt > 1 {
				ans = append(ans, c)
			}
		} else {
			cnt--
			if cnt > 0 {
				ans = append(ans, c)
			}
		}
	}
	return string(ans)
}
```

```go
func removeOuterParentheses(s string) string {
	ans := []rune{}
	cnt := 0
	for _, c := range s {
		if c == '(' {
			cnt++
		}
		if cnt > 1 {
			ans = append(ans, c)
		}
		if c == ')' {
			cnt--
		}
	}
	return string(ans)
}
```

### **TypeScript**

```ts
function removeOuterParentheses(s: string): string {
    let res = '';
    let depth = 0;
    for (const c of s) {
        if (c === '(') {
            depth++;
        }
        if (depth !== 1) {
            res += c;
        }
        if (c === ')') {
            depth--;
        }
    }
    return res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn remove_outer_parentheses(s: String) -> String {
        let mut res = String::new();
        let mut depth = 0;
        for c in s.chars() {
            if c == '(' {
                depth += 1;
            }
            if depth != 1 {
                res.push(c);
            }
            if c == ')' {
                depth -= 1;
            }
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
