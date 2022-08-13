# [2083. Substrings That Begin and End With the Same Letter](https://leetcode.com/problems/substrings-that-begin-and-end-with-the-same-letter)

[中文文档](/solution/2000-2099/2083.Substrings%20That%20Begin%20and%20End%20With%20the%20Same%20Letter/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> string <code>s</code> consisting of only lowercase English letters. Return <em>the number of <strong>substrings</strong> in </em><code>s</code> <em>that begin and end with the <strong>same</strong> character.</em></p>

<p>A <strong>substring</strong> is a contiguous non-empty sequence of characters within a string.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcba&quot;
<strong>Output:</strong> 7
<strong>Explanation:</strong>
The substrings of length 1 that start and end with the same letter are: &quot;a&quot;, &quot;b&quot;, &quot;c&quot;, &quot;b&quot;, and &quot;a&quot;.
The substring of length 3 that starts and ends with the same letter is: &quot;bcb&quot;.
The substring of length 5 that starts and ends with the same letter is: &quot;abcba&quot;.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abacad&quot;
<strong>Output:</strong> 9
<strong>Explanation:</strong>
The substrings of length 1 that start and end with the same letter are: &quot;a&quot;, &quot;b&quot;, &quot;a&quot;, &quot;c&quot;, &quot;a&quot;, and &quot;d&quot;.
The substrings of length 3 that start and end with the same letter are: &quot;aba&quot; and &quot;aca&quot;.
The substring of length 5 that starts and ends with the same letter is: &quot;abaca&quot;.
</pre>

<p><strong>Example 3:</strong></p>

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
        counter = [0] * 26
        ans = 0
        for c in s:
            i = ord(c) - ord('a')
            counter[i] += 1
            ans += counter[i]
        return ans
```

### **Java**

```java
class Solution {
    public long numberOfSubstrings(String s) {
        int[] counter = new int[26];
        long ans = 0;
        for (char c : s.toCharArray()) {
            int i = c - 'a';
            ++counter[i];
            ans += counter[i];
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
        vector<int> counter(26);
        long long ans = 0;
        for (char c : s) {
            int i = c - 'a';
            ++counter[i];
            ans += counter[i];
        }
        return ans;
    }
};
```

### **Go**

```go
func numberOfSubstrings(s string) int64 {
	var ans int64
	counter := make([]int64, 26)
	for _, c := range s {
		i := c - 'a'
		counter[i]++
		ans += counter[i]
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
