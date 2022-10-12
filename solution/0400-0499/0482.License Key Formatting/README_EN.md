# [482. License Key Formatting](https://leetcode.com/problems/license-key-formatting)

[中文文档](/solution/0400-0499/0482.License%20Key%20Formatting/README.md)

## Description

<p>You are given a license key represented as a string <code>s</code> that consists of only alphanumeric characters and dashes. The string is separated into <code>n + 1</code> groups by <code>n</code> dashes. You are also given an integer <code>k</code>.</p>

<p>We want to reformat the string <code>s</code> such that each group contains exactly <code>k</code> characters, except for the first group, which could be shorter than <code>k</code> but still must contain at least one character. Furthermore, there must be a dash inserted between two groups, and you should convert all lowercase letters to uppercase.</p>

<p>Return <em>the reformatted license key</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;5F3Z-2e-9-w&quot;, k = 4
<strong>Output:</strong> &quot;5F3Z-2E9W&quot;
<strong>Explanation:</strong> The string s has been split into two parts, each part has 4 characters.
Note that the two extra dashes are not needed and can be removed.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;2-5g-3-J&quot;, k = 2
<strong>Output:</strong> &quot;2-5G-3J&quot;
<strong>Explanation:</strong> The string s has been split into three parts, each part has 2 characters except the first part as it could be shorter as mentioned above.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of English letters, digits, and dashes <code>&#39;-&#39;</code>.</li>
	<li><code>1 &lt;= k &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def licenseKeyFormatting(self, s: str, k: int) -> str:
        s = s.replace('-', '').upper()
        res = []
        cnt = (len(s) % k) or k
        t = 0
        for i, c in enumerate(s):
            res.append(c)
            t += 1
            if t == cnt:
                t = 0
                cnt = k
                if i != len(s) - 1:
                    res.append('-')
        return ''.join(res)
```

### **Java**

```java
class Solution {
    public String licenseKeyFormatting(String s, int k) {
        s = s.replace("-", "").toUpperCase();
        StringBuilder sb = new StringBuilder();
        int t = 0;
        int cnt = s.length() % k;
        if (cnt == 0) {
            cnt = k;
        }
        for (int i = 0; i < s.length(); ++i) {
            sb.append(s.charAt(i));
            ++t;
            if (t == cnt) {
                t = 0;
                cnt = k;
                if (i != s.length() - 1) {
                    sb.append('-');
                }
            }
        }
        return sb.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string licenseKeyFormatting(string s, int k) {
        string ss = "";
        for (char c : s) {
            if (c == '-') continue;
            if ('a' <= c && c <= 'z') c += 'A' - 'a';
            ss += c;
        }
        int cnt = ss.size() % k;
        if (cnt == 0) cnt = k;
        int t = 0;
        string res = "";
        for (int i = 0; i < ss.size(); ++i) {
            res += ss[i];
            ++t;
            if (t == cnt) {
                t = 0;
                cnt = k;
                if (i != ss.size() - 1) res += '-';
            }
        }
        return res;
    }
};
```

### **Go**

```go
func licenseKeyFormatting(s string, k int) string {
	s = strings.ReplaceAll(s, "-", "")
	cnt := len(s) % k
	if cnt == 0 {
		cnt = k
	}
	t := 0
	res := []byte{}
	for i, c := range s {
		res = append(res, byte(unicode.ToUpper(c)))
		t++
		if t == cnt {
			t = 0
			cnt = k
			if i != len(s)-1 {
				res = append(res, byte('-'))
			}
		}
	}
	return string(res)
}
```

### **...**

```

```

<!-- tabs:end -->
