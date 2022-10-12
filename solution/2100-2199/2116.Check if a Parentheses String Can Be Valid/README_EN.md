# [2116. Check if a Parentheses String Can Be Valid](https://leetcode.com/problems/check-if-a-parentheses-string-can-be-valid)

[中文文档](/solution/2100-2199/2116.Check%20if%20a%20Parentheses%20String%20Can%20Be%20Valid/README.md)

## Description

<p>A parentheses string is a <strong>non-empty</strong> string consisting only of <code>&#39;(&#39;</code> and <code>&#39;)&#39;</code>. It is valid if <strong>any</strong> of the following conditions is <strong>true</strong>:</p>

<ul>
	<li>It is <code>()</code>.</li>
	<li>It can be written as <code>AB</code> (<code>A</code> concatenated with <code>B</code>), where <code>A</code> and <code>B</code> are valid parentheses strings.</li>
	<li>It can be written as <code>(A)</code>, where <code>A</code> is a valid parentheses string.</li>
</ul>

<p>You are given a parentheses string <code>s</code> and a string <code>locked</code>, both of length <code>n</code>. <code>locked</code> is a binary string consisting only of <code>&#39;0&#39;</code>s and <code>&#39;1&#39;</code>s. For <strong>each</strong> index <code>i</code> of <code>locked</code>,</p>

<ul>
	<li>If <code>locked[i]</code> is <code>&#39;1&#39;</code>, you <strong>cannot</strong> change <code>s[i]</code>.</li>
	<li>But if <code>locked[i]</code> is <code>&#39;0&#39;</code>, you <strong>can</strong> change <code>s[i]</code> to either <code>&#39;(&#39;</code> or <code>&#39;)&#39;</code>.</li>
</ul>

<p>Return <code>true</code> <em>if you can make <code>s</code> a valid parentheses string</em>. Otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2116.Check%20if%20a%20Parentheses%20String%20Can%20Be%20Valid/images/eg1.png" style="width: 311px; height: 101px;" />
<pre>
<strong>Input:</strong> s = &quot;))()))&quot;, locked = &quot;010100&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> locked[1] == &#39;1&#39; and locked[3] == &#39;1&#39;, so we cannot change s[1] or s[3].
We change s[0] and s[4] to &#39;(&#39; while leaving s[2] and s[5] unchanged to make s valid.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;()()&quot;, locked = &quot;0000&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> We do not need to make any changes because s is already valid.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;)&quot;, locked = &quot;0&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> locked permits us to change s[0]. 
Changing s[0] to either &#39;(&#39; or &#39;)&#39; will not make s valid.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == s.length == locked.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> is either <code>&#39;(&#39;</code> or <code>&#39;)&#39;</code>.</li>
	<li><code>locked[i]</code> is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def canBeValid(self, s: str, locked: str) -> bool:
        n = len(s)
        if n & 1:
            return False
        x = 0
        for i in range(n):
            if s[i] == '(' or locked[i] == '0':
                x += 1
            elif x:
                x -= 1
            else:
                return False
        x = 0
        for i in range(n - 1, -1, -1):
            if s[i] == ')' or locked[i] == '0':
                x += 1
            elif x:
                x -= 1
            else:
                return False
        return True
```

### **Java**

```java
class Solution {
    public boolean canBeValid(String s, String locked) {
        int n = s.length();
        if (n % 2 == 1) {
            return false;
        }
        int x = 0;
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '(' || locked.charAt(i) == '0') {
                ++x;
            } else if (x > 0) {
                --x;
            } else {
                return false;
            }
        }
        x = 0;
        for (int i = n - 1; i >= 0; --i) {
            if (s.charAt(i) == ')' || locked.charAt(i) == '0') {
                ++x;
            } else if (x > 0) {
                --x;
            } else {
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
    bool canBeValid(string s, string locked) {
        int n = s.size();
        if (n & 1) {
            return false;
        }
        int x = 0;
        for (int i = 0; i < n; ++i) {
            if (s[i] == '(' || locked[i] == '0') {
                ++x;
            } else if (x) {
                --x;
            } else {
                return false;
            }
        }
        x = 0;
        for (int i = n - 1; i >= 0; --i) {
            if (s[i] == ')' || locked[i] == '0') {
                ++x;
            } else if (x) {
                --x;
            } else {
                return false;
            }
        }
        return true;
    }
};
```

### **Go**

```go
func canBeValid(s string, locked string) bool {
	n := len(s)
	if n%2 == 1 {
		return false
	}
	x := 0
	for i := range s {
		if s[i] == '(' || locked[i] == '0' {
			x++
		} else if x > 0 {
			x--
		} else {
			return false
		}
	}
	x = 0
	for i := n - 1; i >= 0; i-- {
		if s[i] == ')' || locked[i] == '0' {
			x++
		} else if x > 0 {
			x--
		} else {
			return false
		}
	}
	return true
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
