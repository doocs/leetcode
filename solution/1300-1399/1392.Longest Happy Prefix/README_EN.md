# [1392. Longest Happy Prefix](https://leetcode.com/problems/longest-happy-prefix)

[中文文档](/solution/1300-1399/1392.Longest%20Happy%20Prefix/README.md)

## Description

<p>A string is called a&nbsp;<em>happy prefix</em>&nbsp;if is a <strong>non-empty</strong> prefix which is also a suffix (excluding itself).</p>

<p>Given a string <code>s</code>. Return the <strong>longest happy prefix</strong>&nbsp;of <code>s</code>&nbsp;.</p>

<p>Return an empty string if no such prefix exists.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;level&quot;
<strong>Output:</strong> &quot;l&quot;
<strong>Explanation:</strong> s contains 4 prefix excluding itself (&quot;l&quot;, &quot;le&quot;, &quot;lev&quot;, &quot;leve&quot;), and suffix (&quot;l&quot;, &quot;el&quot;, &quot;vel&quot;, &quot;evel&quot;). The largest prefix which is also suffix is given by &quot;l&quot;.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;ababab&quot;
<strong>Output:</strong> &quot;abab&quot;
<strong>Explanation:</strong> &quot;abab&quot; is the largest prefix which is also suffix. They can overlap in the original string.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;leetcodeleet&quot;
<strong>Output:</strong> &quot;leet&quot;
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;a&quot;
<strong>Output:</strong> &quot;&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10^5</code></li>
	<li><code>s</code> contains only lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def longestPrefix(self, s: str) -> str:
        for i in range(1, len(s)):
            if s[:-i] == s[i:]:
                return s[i:]
        return ''
```

### **Java**

```java
class Solution {
    private long[] p;
    private long[] h;

    public String longestPrefix(String s) {
        int base = 131;
        int n = 100010;
        p = new long[n];
        h = new long[n];
        p[0] = 1;
        for (int i = 1; i <= s.length(); ++i) {
            p[i] = p[i - 1] * base;
            h[i] = h[i - 1] * base + s.charAt(i - 1);
        }
        int l = s.length();
        for (int i = l - 1; i > 0; --i) {
            if (get(1, i) == get(l - i + 1, l)) {
                return s.substring(0, i);
            }
        }
        return "";
    }

    private long get(int l, int r) {
        return h[r] - h[l - 1] * p[r - l + 1];
    }
}
```

### **C++**

```cpp
typedef unsigned long long ULL;
class Solution {
public:
    string longestPrefix(string s) {
        int base = 131;
        int n = 100010;
        ULL p[100010];
        p[0] = 1;
        ULL h[100010];
        h[0] = 0;
        for (int i = 1; i <= s.size(); i++)
        {
            p[i] = p[i - 1] * base;
            h[i] = h[i - 1] * base + s[i - 1];
        }
        int l = s.size();
        for (int i = l - 1; i >= 1; i--)
        {
            ULL prefix = h[i];
            ULL suffix = h[l] - h[l - i] * p[i];
            if (prefix == suffix)
            {
                return s.substr(0, i);
            }
        }
        return "";
    }
};
```

### **Go**

```go
func longestPrefix(s string) string {
	base := 131
	n := 100010
	p := make([]int, n)
	h := make([]int, n)
	p[0] = 1
	for i := 1; i <= len(s); i++ {
		p[i] = p[i-1] * base
		h[i] = h[i-1]*base + int(s[i-1])
	}
	l := len(s)
	for i := l - 1; i > 0; i-- {
		prefix := h[i]
		suffix := h[l] - h[l-i]*p[i]
		if prefix == suffix {
			return s[:i]
		}
	}
	return ""
}
```

### **...**

```

```

<!-- tabs:end -->
