# [1180. Count Substrings with Only One Distinct Letter](https://leetcode.com/problems/count-substrings-with-only-one-distinct-letter)

[中文文档](/solution/1100-1199/1180.Count%20Substrings%20with%20Only%20One%20Distinct%20Letter/README.md)

## Description

<p>Given a string <code>s</code>, return <em>the number of substrings that have only <strong>one distinct</strong> letter</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aaaba&quot;
<strong>Output:</strong> 8
<strong>Explanation: </strong>The substrings with one distinct letter are &quot;aaa&quot;, &quot;aa&quot;, &quot;a&quot;, &quot;b&quot;.
&quot;aaa&quot; occurs 1 time.
&quot;aa&quot; occurs 2 times.
&quot;a&quot; occurs 4 times.
&quot;b&quot; occurs 1 time.
So the answer is 1 + 2 + 4 + 1 = 8.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aaaaaaaaaa&quot;
<strong>Output:</strong> 55
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s[i]</code> consists of only lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countLetters(self, s: str) -> int:
        n = len(s)
        i = ans = 0
        while i < n:
            j = i
            while j < n and s[j] == s[i]:
                j += 1
            ans += (1 + j - i) * (j - i) // 2
            i = j
        return ans
```

### **Java**

```java
class Solution {
    public int countLetters(String s) {
        int ans = 0;
        for (int i = 0, n = s.length(); i < n;) {
            int j = i;
            while (j < n && s.charAt(j) == s.charAt(i)) {
                ++j;
            }
            ans += (1 + j - i) * (j - i) / 2;
            i = j;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countLetters(string s) {
        int ans = 0;
        for (int i = 0, n = s.size(); i < n;) {
            int j = i;
            while (j < n && s[j] == s[i]) ++j;
            ans += (1 + j - i) * (j - i) / 2;
            i = j;
        }
        return ans;
    }
};
```

### **Go**

```go
func countLetters(s string) int {
	ans := 0
	for i, n := 0, len(s); i < n; {
		j := i
		for j < n && s[j] == s[i] {
			j++
		}
		ans += (1 + j - i) * (j - i) / 2
		i = j
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
