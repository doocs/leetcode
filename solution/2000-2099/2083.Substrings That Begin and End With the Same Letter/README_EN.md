# [2083. Substrings That Begin and End With the Same Letter](https://leetcode.com/problems/substrings-that-begin-and-end-with-the-same-letter)

[中文文档](/solution/2000-2099/2083.Substrings%20That%20Begin%20and%20End%20With%20the%20Same%20Letter/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> string <code>s</code> consisting of only lowercase English letters. Return <em>the number of <strong>substrings</strong> in </em><code>s</code> <em>that begin and end with the <strong>same</strong> character.</em></p>

<p>A <strong>substring</strong> is a contiguous non-empty sequence of characters within a string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcba&quot;
<strong>Output:</strong> 7
<strong>Explanation:</strong>
The substrings of length 1 that start and end with the same letter are: &quot;a&quot;, &quot;b&quot;, &quot;c&quot;, &quot;b&quot;, and &quot;a&quot;.
The substring of length 3 that starts and ends with the same letter is: &quot;bcb&quot;.
The substring of length 5 that starts and ends with the same letter is: &quot;abcba&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abacad&quot;
<strong>Output:</strong> 9
<strong>Explanation:</strong>
The substrings of length 1 that start and end with the same letter are: &quot;a&quot;, &quot;b&quot;, &quot;a&quot;, &quot;c&quot;, &quot;a&quot;, and &quot;d&quot;.
The substrings of length 3 that start and end with the same letter are: &quot;aba&quot; and &quot;aca&quot;.
The substring of length 5 that starts and ends with the same letter is: &quot;abaca&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;a&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong>
The substring of length 1 that starts and ends with the same letter is: &quot;a&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists only of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numberOfSubstrings(self, s: str) -> int:
        cnt = Counter()
        ans = 0
        for c in s:
            cnt[c] += 1
            ans += cnt[c]
        return ans
```

### **Java**

```java
class Solution {
    public long numberOfSubstrings(String s) {
        int[] cnt = new int[26];
        long ans = 0;
        for (int i = 0; i < s.length(); ++i) {
            int j = s.charAt(i) - 'a';
            ++cnt[j];
            ans += cnt[j];
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long numberOfSubstrings(string s) {
        int cnt[26]{};
        long long ans = 0;
        for (char& c : s) {
            ans += ++cnt[c - 'a'];
        }
        return ans;
    }
};
```

### **Go**

```go
func numberOfSubstrings(s string) (ans int64) {
	cnt := [26]int{}
	for _, c := range s {
		c -= 'a'
		cnt[c]++
		ans += int64(cnt[c])
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
