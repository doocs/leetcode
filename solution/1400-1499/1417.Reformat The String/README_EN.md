# [1417. Reformat The String](https://leetcode.com/problems/reformat-the-string)

[中文文档](/solution/1400-1499/1417.Reformat%20The%20String/README.md)

## Description

<p>You are given an alphanumeric string <code>s</code>. (<strong>Alphanumeric string</strong> is a string consisting of lowercase English letters and digits).</p>

<p>You have to find a permutation of the string where no letter is followed by another letter and no digit is followed by another digit. That is, no two adjacent characters have the same type.</p>

<p>Return <em>the reformatted string</em> or return <strong>an empty string</strong> if it is impossible to reformat the string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;a0b1c2&quot;
<strong>Output:</strong> &quot;0a1b2c&quot;
<strong>Explanation:</strong> No two adjacent characters have the same type in &quot;0a1b2c&quot;. &quot;a0b1c2&quot;, &quot;0a1b2c&quot;, &quot;0c2a1b&quot; are also valid permutations.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;leetcode&quot;
<strong>Output:</strong> &quot;&quot;
<strong>Explanation:</strong> &quot;leetcode&quot; has only characters so we cannot separate them by digits.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;1229857369&quot;
<strong>Output:</strong> &quot;&quot;
<strong>Explanation:</strong> &quot;1229857369&quot; has only digits so we cannot separate them by characters.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 500</code></li>
	<li><code>s</code> consists of only lowercase English letters and/or digits.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def reformat(self, s: str) -> str:
        a = [c for c in s if c.islower()]
        b = [c for c in s if c.isdigit()]
        if abs(len(a) - len(b)) > 1:
            return ''
        if len(a) < len(b):
            a, b = b, a
        ans = []
        for x, y in zip(a, b):
            ans.append(x + y)
        if len(a) > len(b):
            ans.append(a[-1])
        return ''.join(ans)
```

### **Java**

```java
class Solution {
    public String reformat(String s) {
        StringBuilder a = new StringBuilder();
        StringBuilder b = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                a.append(c);
            } else {
                b.append(c);
            }
        }
        int m = a.length(), n = b.length();
        if (Math.abs(m - n) > 1) {
            return "";
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < Math.min(m, n); ++i) {
            if (m > n) {
                ans.append(a.charAt(i));
                ans.append(b.charAt(i));
            } else {
                ans.append(b.charAt(i));
                ans.append(a.charAt(i));
            }
        }
        if (m > n) {
            ans.append(a.charAt(m - 1));
        }
        if (m < n) {
            ans.append(b.charAt(n - 1));
        }
        return ans.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string reformat(string s) {
        string a = "", b = "";
        for (char c : s) {
            if (isdigit(c))
                a += c;
            else
                b += c;
        }
        int m = a.size(), n = b.size();
        if (abs(m - n) > 1) return "";
        string ans = "";
        for (int i = 0; i < min(m, n); ++i) {
            if (m > n) {
                ans += a[i];
                ans += b[i];
            } else {
                ans += b[i];
                ans += a[i];
            }
        }
        if (m > n) ans += a[m - 1];
        if (m < n) ans += b[n - 1];
        return ans;
    }
};
```

### **Go**

```go
func reformat(s string) string {
	a := []byte{}
	b := []byte{}
	for _, c := range s {
		if unicode.IsLetter(c) {
			a = append(a, byte(c))
		} else {
			b = append(b, byte(c))
		}
	}
	if len(a) < len(b) {
		a, b = b, a
	}
	if len(a)-len(b) > 1 {
		return ""
	}
	var ans strings.Builder
	for i := range b {
		ans.WriteByte(a[i])
		ans.WriteByte(b[i])
	}
	if len(a) > len(b) {
		ans.WriteByte(a[len(a)-1])
	}
	return ans.String()
}
```

### **...**

```

```

<!-- tabs:end -->
