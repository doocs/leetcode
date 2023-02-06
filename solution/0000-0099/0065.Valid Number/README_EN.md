# [65. Valid Number](https://leetcode.com/problems/valid-number)

[中文文档](/solution/0000-0099/0065.Valid%20Number/README.md)

## Description

<p>A <strong>valid number</strong> can be split up into these components (in order):</p>

<ol>
	<li>A <strong>decimal number</strong> or an <strong>integer</strong>.</li>
	<li>(Optional) An <code>&#39;e&#39;</code> or <code>&#39;E&#39;</code>, followed by an <strong>integer</strong>.</li>
</ol>

<p>A <strong>decimal number</strong> can be split up into these components (in order):</p>

<ol>
	<li>(Optional) A sign character (either <code>&#39;+&#39;</code> or <code>&#39;-&#39;</code>).</li>
	<li>One of the following formats:
	<ol>
		<li>One or more digits, followed by a dot <code>&#39;.&#39;</code>.</li>
		<li>One or more digits, followed by a dot <code>&#39;.&#39;</code>, followed by one or more digits.</li>
		<li>A dot <code>&#39;.&#39;</code>, followed by one or more digits.</li>
	</ol>
	</li>
</ol>

<p>An <strong>integer</strong> can be split up into these components (in order):</p>

<ol>
	<li>(Optional) A sign character (either <code>&#39;+&#39;</code> or <code>&#39;-&#39;</code>).</li>
	<li>One or more digits.</li>
</ol>

<p>For example, all the following are valid numbers: <code>[&quot;2&quot;, &quot;0089&quot;, &quot;-0.1&quot;, &quot;+3.14&quot;, &quot;4.&quot;, &quot;-.9&quot;, &quot;2e10&quot;, &quot;-90E3&quot;, &quot;3e+7&quot;, &quot;+6e-1&quot;, &quot;53.5e93&quot;, &quot;-123.456e789&quot;]</code>, while the following are not valid numbers: <code>[&quot;abc&quot;, &quot;1a&quot;, &quot;1e&quot;, &quot;e3&quot;, &quot;99e2.5&quot;, &quot;--6&quot;, &quot;-+3&quot;, &quot;95a54e53&quot;]</code>.</p>

<p>Given a string <code>s</code>, return <code>true</code><em> if </em><code>s</code><em> is a <strong>valid number</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;0&quot;
<strong>Output:</strong> true
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;e&quot;
<strong>Output:</strong> false
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;.&quot;
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 20</code></li>
	<li><code>s</code> consists of only English letters (both uppercase and lowercase), digits (<code>0-9</code>), plus <code>&#39;+&#39;</code>, minus <code>&#39;-&#39;</code>, or dot <code>&#39;.&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def isNumber(self, s: str) -> bool:
        n = len(s)
        i = 0
        if s[i] in '+-':
            i += 1
        if i == n:
            return False
        if s[i] == '.' and (i + 1 == n or s[i + 1] in 'eE'):
            return False
        dot = e = 0
        j = i
        while j < n:
            if s[j] == '.':
                if e or dot:
                    return False
                dot += 1
            elif s[j] in 'eE':
                if e or j == i or j == n - 1:
                    return False
                e += 1
                if s[j + 1] in '+-':
                    j += 1
                    if j == n - 1:
                        return False
            elif not s[j].isnumeric():
                return False
            j += 1
        return True
```

### **Java**

```java
class Solution {
    public boolean isNumber(String s) {
        int n = s.length();
        int i = 0;
        if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            ++i;
        }
        if (i == n) {
            return false;
        }
        if (s.charAt(i) == '.'
            && (i + 1 == n || s.charAt(i + 1) == 'e' || s.charAt(i + 1) == 'E')) {
            return false;
        }
        int dot = 0, e = 0;
        for (int j = i; j < n; ++j) {
            if (s.charAt(j) == '.') {
                if (e > 0 || dot > 0) {
                    return false;
                }
                ++dot;
            } else if (s.charAt(j) == 'e' || s.charAt(j) == 'E') {
                if (e > 0 || j == i || j == n - 1) {
                    return false;
                }
                ++e;
                if (s.charAt(j + 1) == '+' || s.charAt(j + 1) == '-') {
                    if (++j == n - 1) {
                        return false;
                    }
                }
            } else if (s.charAt(j) < '0' || s.charAt(j) > '9') {
                return false;
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
    bool isNumber(string s) {
        int n = s.size();
        int i = 0;
        if (s[i] == '+' || s[i] == '-') ++i;
        if (i == n) return false;
        if (s[i] == '.' && (i + 1 == n || s[i + 1] == 'e' || s[i + 1] == 'E')) return false;
        int dot = 0, e = 0;
        for (int j = i; j < n; ++j) {
            if (s[j] == '.') {
                if (e || dot) return false;
                ++dot;
            } else if (s[j] == 'e' || s[j] == 'E') {
                if (e || j == i || j == n - 1) return false;
                ++e;
                if (s[j + 1] == '+' || s[j + 1] == '-') {
                    if (++j == n - 1) return false;
                }
            } else if (s[j] < '0' || s[j] > '9') return false;
        }
        return true;
    }
};
```

### **Go**

```go
func isNumber(s string) bool {
	i, n := 0, len(s)
	if s[i] == '+' || s[i] == '-' {
		i++
	}
	if i == n {
		return false
	}
	if s[i] == '.' && (i+1 == n || s[i+1] == 'e' || s[i+1] == 'E') {
		return false
	}
	var dot, e int
	for j := i; j < n; j++ {
		if s[j] == '.' {
			if e > 0 || dot > 0 {
				return false
			}
			dot++
		} else if s[j] == 'e' || s[j] == 'E' {
			if e > 0 || j == i || j == n-1 {
				return false
			}
			e++
			if s[j+1] == '+' || s[j+1] == '-' {
				j++
				if j == n-1 {
					return false
				}
			}
		} else if s[j] < '0' || s[j] > '9' {
			return false
		}
	}
	return true
}
```

### **C#**

```cs
using System.Text.RegularExpressions;

public class Solution {
    private readonly Regex _isNumber_Regex = new Regex(@"^\s*[+-]?(\d+(\.\d*)?|\.\d+)([Ee][+-]?\d+)?\s*$");

    public bool IsNumber(string s) {
        return _isNumber_Regex.IsMatch(s);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
