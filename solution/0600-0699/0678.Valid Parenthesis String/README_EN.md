# [678. Valid Parenthesis String](https://leetcode.com/problems/valid-parenthesis-string)

[中文文档](/solution/0600-0699/0678.Valid%20Parenthesis%20String/README.md)

## Description

<p>Given a string <code>s</code> containing only three types of characters: <code>&#39;(&#39;</code>, <code>&#39;)&#39;</code> and <code>&#39;*&#39;</code>, return <code>true</code> <em>if</em> <code>s</code> <em>is <strong>valid</strong></em>.</p>

<p>The following rules define a <strong>valid</strong> string:</p>

<ul>
	<li>Any left parenthesis <code>&#39;(&#39;</code> must have a corresponding right parenthesis <code>&#39;)&#39;</code>.</li>
	<li>Any right parenthesis <code>&#39;)&#39;</code> must have a corresponding left parenthesis <code>&#39;(&#39;</code>.</li>
	<li>Left parenthesis <code>&#39;(&#39;</code> must go before the corresponding right parenthesis <code>&#39;)&#39;</code>.</li>
	<li><code>&#39;*&#39;</code> could be treated as a single right parenthesis <code>&#39;)&#39;</code> or a single left parenthesis <code>&#39;(&#39;</code> or an empty string <code>&quot;&quot;</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> s = "()"
<strong>Output:</strong> true
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> s = "(*)"
<strong>Output:</strong> true
</pre><p><strong>Example 3:</strong></p>
<pre><strong>Input:</strong> s = "(*))"
<strong>Output:</strong> true
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s[i]</code> is <code>&#39;(&#39;</code>, <code>&#39;)&#39;</code> or <code>&#39;*&#39;</code>.</li>
</ul>

## Solutions

Scan twice, first from left to right to make sure that each of the closing brackets is matched successfully, and second from right to left to make sure that each of the opening brackets is matched successfully

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def checkValidString(self, s: str) -> bool:
        n = len(s)
        left, asterisk = 0, 0
        for i in range(n):
            if s[i] == "(":
                left += 1
            elif s[i] == ")":
                if left > 0:
                    left -= 1
                elif asterisk > 0:
                    asterisk -= 1
                else:
                    return False
            else:
                asterisk += 1
        right, asterisk = 0, 0
        for i in range(n - 1, -1, -1):
            if s[i] == ")":
                right += 1
            elif s[i] == "(":
                if right > 0:
                    right -= 1
                elif asterisk > 0:
                    asterisk -= 1
                else:
                    return False
            else:
                asterisk += 1
        return True
```

### **Java**

```java
class Solution {
    public boolean checkValidString(String s) {
        int n = s.length();
        char[] a = s.toCharArray();
        int left = 0, asterisk = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] == '(') {
                left++;
            } else if (a[i] == ')') {
                if (left > 0) {
                    left--;
                } else if (asterisk > 0) {
                    asterisk--;
                } else {
                    return false;
                }
            } else {
                asterisk++;
            }
        }
        int right = 0;
        asterisk = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (a[i] == ')') {
                right++;
            } else if (a[i] == '(') {
                if (right > 0) {
                    right--;
                } else if (asterisk > 0) {
                    asterisk--;
                } else {
                    return false;
                }
            } else {
                asterisk++;
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool checkValidString(string s) {
        int n = s.size();
        int left = 0, asterisk = 0;
        for (int i = 0; i < n; ++i) {
            if (s[i] == '(') {
                ++left;
            } else if (s[i] == ')') {
                if (left > 0)
                    --left;
                else if (asterisk > 0)
                    --asterisk;
                else
                    return false;
            } else {
                ++asterisk;
            }
        }
        int right = 0;
        asterisk = 0;
        for (int i = n - 1; i >= 0; --i) {
            if (s[i] == ')') {
                ++right;
            } else if (s[i] == '(') {
                if (right > 0)
                    --right;
                else if (asterisk > 0)
                    --asterisk;
                else
                    return false;
            } else {
                ++asterisk;
            }
        }
        return true;
    }
};
```

### **Go**

```go
func checkValidString(s string) bool {
	n := len(s)
	left, asterisk := 0, 0
	for i := 0; i < n; i++ {
		if s[i] == '(' {
			left++
		} else if s[i] == ')' {
			if left > 0 {
				left--
			} else if asterisk > 0 {
				asterisk--
			} else {
				return false
			}
		} else {
			asterisk++
		}
	}
	asterisk = 0
	right := 0
	for i := n - 1; i >= 0; i-- {
		if s[i] == ')' {
			right++
		} else if s[i] == '(' {
			if right > 0 {
				right--
			} else if asterisk > 0 {
				asterisk--
			} else {
				return false
			}
		} else {
			asterisk++
		}
	}
	return true
}
```

### **...**

```

```

<!-- tabs:end -->
