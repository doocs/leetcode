# [1763. Longest Nice Substring](https://leetcode.com/problems/longest-nice-substring)

[中文文档](/solution/1700-1799/1763.Longest%20Nice%20Substring/README.md)

## Description

<p>A string <code>s</code> is <strong>nice</strong> if, for every letter of the alphabet that <code>s</code> contains, it appears <strong>both</strong> in uppercase and lowercase. For example, <code>&quot;abABB&quot;</code> is nice because <code>&#39;A&#39;</code> and <code>&#39;a&#39;</code> appear, and <code>&#39;B&#39;</code> and <code>&#39;b&#39;</code> appear. However, <code>&quot;abA&quot;</code> is not because <code>&#39;b&#39;</code> appears, but <code>&#39;B&#39;</code> does not.</p>

<p>Given a string <code>s</code>, return <em>the longest <strong>substring</strong> of <code>s</code> that is <strong>nice</strong>. If there are multiple, return the substring of the <strong>earliest</strong> occurrence. If there are none, return an empty string</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;YazaAay&quot;
<strong>Output:</strong> &quot;aAa&quot;
<strong>Explanation: </strong>&quot;aAa&quot; is a nice string because &#39;A/a&#39; is the only letter of the alphabet in s, and both &#39;A&#39; and &#39;a&#39; appear.
&quot;aAa&quot; is the longest nice substring.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;Bb&quot;
<strong>Output:</strong> &quot;Bb&quot;
<strong>Explanation:</strong> &quot;Bb&quot; is a nice string because both &#39;B&#39; and &#39;b&#39; appear. The whole string is a substring.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;c&quot;
<strong>Output:</strong> &quot;&quot;
<strong>Explanation:</strong> There are no nice substrings.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> consists of uppercase and lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def longestNiceSubstring(self, s: str) -> str:
        n = len(s)
        ans = ''
        for i in range(n):
            lower = upper = 0
            for j in range(i, n):
                if s[j].islower():
                    lower |= 1 << (ord(s[j]) - ord('a'))
                else:
                    upper |= 1 << (ord(s[j]) - ord('A'))
                if lower == upper and j - i + 1 > len(ans):
                    ans = s[i : j + 1]
        return ans
```

### **Java**

```java
class Solution {

    public String longestNiceSubstring(String s) {
        int n = s.length();
        String ans = "";
        for (int i = 0; i < n; ++i) {
            int lower = 0, upper = 0;
            for (int j = i; j < n; ++j) {
                char c = s.charAt(j);
                if (Character.isLowerCase(c)) {
                    lower |= 1 << (c - 'a');
                } else {
                    upper |= 1 << (c - 'A');
                }
                if (lower == upper && j - i + 1 > ans.length()) {
                    ans = s.substring(i, j + 1);
                }
            }
        }
        return ans;
    }
}

```

### **TypeScript**

```ts
function longestNiceSubstring(s: string): string {
    const n = s.length;
    let ans = '';
    for (let i = 0; i < n; i++) {
        let lower = 0,
            upper = 0;
        for (let j = i; j < n; j++) {
            const c = s.charCodeAt(j);
            if (c > 96) {
                lower |= 1 << (c - 97);
            } else {
                upper |= 1 << (c - 65);
            }
            if (lower == upper && j - i + 1 > ans.length) {
                ans = s.substring(i, j + 1);
            }
        }
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    string longestNiceSubstring(string s) {
        int n = s.size();
        string ans = "";
        for (int i = 0; i < n; ++i) {
            int lower = 0, upper = 0;
            for (int j = i; j < n; ++j) {
                if (islower(s[j]))
                    lower |= 1 << (s[j] - 'a');
                else
                    upper |= 1 << (s[j] - 'A');
                if (lower == upper && j - i + 1 > ans.size()) ans = s.substr(i, j - i + 1);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func longestNiceSubstring(s string) (ans string) {
	for i := range s {
		lower, upper := 0, 0
		for j := i; j < len(s); j++ {
			if unicode.IsLower(rune(s[j])) {
				lower |= 1 << (s[j] - 'a')
			} else {
				upper |= 1 << (s[j] - 'A')
			}
			if lower == upper && j-i+1 > len(ans) {
				ans = s[i : j+1]
			}
		}
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
