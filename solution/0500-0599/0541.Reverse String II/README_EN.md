# [541. Reverse String II](https://leetcode.com/problems/reverse-string-ii)

[中文文档](/solution/0500-0599/0541.Reverse%20String%20II/README.md)

## Description

<p>Given a string <code>s</code> and an integer <code>k</code>, reverse the first <code>k</code> characters for every <code>2k</code> characters counting from the start of the string.</p>

<p>If there are fewer than <code>k</code> characters left, reverse all of them. If there are less than <code>2k</code> but greater than or equal to <code>k</code> characters, then reverse the first <code>k</code> characters and leave the other as original.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> s = "abcdefg", k = 2
<strong>Output:</strong> "bacdfeg"
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> s = "abcd", k = 2
<strong>Output:</strong> "bacd"
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code> consists of only lowercase English letters.</li>
	<li><code>1 &lt;= k &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def reverseStr(self, s: str, k: int) -> str:
        t = list(s)
        for i in range(0, len(t), k << 1):
            t[i : i + k] = reversed(t[i : i + k])
        return ''.join(t)
```

### **Java**

```java
class Solution {
    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i += (k << 1)) {
            for (int st = i, ed = Math.min(chars.length - 1, i + k - 1); st < ed; ++st, --ed) {
                char t = chars[st];
                chars[st] = chars[ed];
                chars[ed] = t;
            }
        }
        return new String(chars);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string reverseStr(string s, int k) {
        for (int i = 0, n = s.size(); i < n; i += (k << 1)) {
            reverse(s.begin() + i, s.begin() + min(i + k, n));
        }
        return s;
    }
};
```

### **Go**

```go
func reverseStr(s string, k int) string {
	t := []byte(s)
	for i := 0; i < len(t); i += (k << 1) {
		for st, ed := i, min(i+k-1, len(t)-1); st < ed; st, ed = st+1, ed-1 {
			t[st], t[ed] = t[ed], t[st]
		}
	}
	return string(t)
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
