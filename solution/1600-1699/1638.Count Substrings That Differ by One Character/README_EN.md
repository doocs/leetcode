# [1638. Count Substrings That Differ by One Character](https://leetcode.com/problems/count-substrings-that-differ-by-one-character)

[中文文档](/solution/1600-1699/1638.Count%20Substrings%20That%20Differ%20by%20One%20Character/README.md)

## Description

<p>Given two strings <code>s</code> and <code>t</code>, find the number of ways you can choose a non-empty substring of <code>s</code> and replace a <strong>single character</strong> by a different character such that the resulting substring is a substring of <code>t</code>. In other words, find the number of substrings in <code>s</code> that differ from some substring in <code>t</code> by <strong>exactly</strong> one character.</p>

<p>For example, the underlined substrings in <code>&quot;<u>compute</u>r&quot;</code> and <code>&quot;<u>computa</u>tion&quot;</code> only differ by the <code>&#39;e&#39;</code>/<code>&#39;a&#39;</code>, so this is a valid way.</p>

<p>Return <em>the number of substrings that satisfy the condition above.</em></p>

<p>A <strong>substring</strong> is a contiguous sequence of characters within a string.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aba&quot;, t = &quot;baba&quot;
<strong>Output:</strong> 6
<strong>Explanation:</strong> The following are the pairs of substrings from s and t that differ by exactly 1 character:
(&quot;<u>a</u>ba&quot;, &quot;<u>b</u>aba&quot;)
(&quot;<u>a</u>ba&quot;, &quot;ba<u>b</u>a&quot;)
(&quot;ab<u>a</u>&quot;, &quot;<u>b</u>aba&quot;)
(&quot;ab<u>a</u>&quot;, &quot;ba<u>b</u>a&quot;)
(&quot;a<u>b</u>a&quot;, &quot;b<u>a</u>ba&quot;)
(&quot;a<u>b</u>a&quot;, &quot;bab<u>a</u>&quot;)
The underlined portions are the substrings that are chosen from s and t.
</pre>

​​<strong>Example 2:</strong>

<pre>
<strong>Input:</strong> s = &quot;ab&quot;, t = &quot;bb&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> The following are the pairs of substrings from s and t that differ by 1 character:
(&quot;<u>a</u>b&quot;, &quot;<u>b</u>b&quot;)
(&quot;<u>a</u>b&quot;, &quot;b<u>b</u>&quot;)
(&quot;<u>ab</u>&quot;, &quot;<u>bb</u>&quot;)
​​​​The underlined portions are the substrings that are chosen from s and t.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length, t.length &lt;= 100</code></li>
	<li><code>s</code> and <code>t</code> consist of lowercase English letters only.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countSubstrings(self, s: str, t: str) -> int:
        m, n = len(s), len(t)
        ans = 0
        for i in range(m):
            for j in range(n):
                if s[i] != t[j]:
                    l = r = 1
                    while i - l >= 0 and j - l >= 0 and s[i - l] == t[j - l]:
                        l += 1
                    while i + r < m and j + r < n and s[i + r] == t[j + r]:
                        r += 1
                    ans += l * r
        return ans
```

### **Java**

```java
class Solution {
    public int countSubstrings(String s, String t) {
        int m = s.length(), n = t.length();
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (s.charAt(i) != t.charAt(j)) {
                    int l = 1, r = 1;
                    while (i - l >= 0 && j - l >= 0 && s.charAt(i - l) == t.charAt(j - l)) {
                        ++l;
                    }
                    while (i + r < m && j + r < n && s.charAt(i + r) == t.charAt(j + r)) {
                        ++r;
                    }
                    ans += l * r;
                }
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countSubstrings(string s, string t) {
        int m = s.size(), n = t.size();
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (s[i] == t[j]) continue;
                int l = 1, r = 1;
                while (i - l >= 0 && j - l >= 0 && s[i - l] == t[j - l]) ++l;
                while (i + r < m && j + r < n && s[i + r] == t[j + r]) ++r;
                ans += l * r;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func countSubstrings(s string, t string) int {
	m, n := len(s), len(t)
	ans := 0
	for i := range s {
		for j := range t {
			if s[i] == t[j] {
				continue
			}
			l, r := 1, 1
			for i-l >= 0 && j-l >= 0 && s[i-l] == t[j-l] {
				l++
			}
			for i+r < m && j+r < n && s[i+r] == t[j+r] {
				r++
			}
			ans += l * r
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
