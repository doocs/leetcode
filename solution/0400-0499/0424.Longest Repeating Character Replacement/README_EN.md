# [424. Longest Repeating Character Replacement](https://leetcode.com/problems/longest-repeating-character-replacement)

[中文文档](/solution/0400-0499/0424.Longest%20Repeating%20Character%20Replacement/README.md)

## Description

<p>You are given a string <code>s</code> and an integer <code>k</code>. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most <code>k</code> times.</p>

<p>Return <em>the length of the longest substring containing the same letter you can get after performing the above operations</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;ABAB&quot;, k = 2
<strong>Output:</strong> 4
<strong>Explanation:</strong> Replace the two &#39;A&#39;s with two &#39;B&#39;s or vice versa.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;AABABBA&quot;, k = 1
<strong>Output:</strong> 4
<strong>Explanation:</strong> Replace the one &#39;A&#39; in the middle with &#39;B&#39; and form &quot;AABBBBA&quot;.
The substring &quot;BBBB&quot; has the longest repeating letters, which is 4.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of only uppercase English letters.</li>
	<li><code>0 &lt;= k &lt;= s.length</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def characterReplacement(self, s: str, k: int) -> int:
        counter = [0] * 26
        i = j = maxCnt = 0
        while i < len(s):
            counter[ord(s[i]) - ord('A')] += 1
            maxCnt = max(maxCnt, counter[ord(s[i]) - ord('A')])
            if i - j + 1 > maxCnt + k:
                counter[ord(s[j]) - ord('A')] -= 1
                j += 1
            i += 1
        return i - j
```

### **Java**

```java
class Solution {
    public int characterReplacement(String s, int k) {
        int[] counter = new int[26];
        int i = 0;
        int j = 0;
        for (int maxCnt = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            ++counter[c - 'A'];
            maxCnt = Math.max(maxCnt, counter[c - 'A']);
            if (i - j + 1 - maxCnt > k) {
                --counter[s.charAt(j) - 'A'];
                ++j;
            }
        }
        return i - j;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int characterReplacement(string s, int k) {
        vector<int> counter(26);
        int i = 0, j = 0, maxCnt = 0;
        for (char& c : s) {
            ++counter[c - 'A'];
            maxCnt = max(maxCnt, counter[c - 'A']);
            if (i - j + 1 > maxCnt + k) {
                --counter[s[j] - 'A'];
                ++j;
            }
            ++i;
        }
        return i - j;
    }
};
```

### **Go**

```go
func characterReplacement(s string, k int) int {
	counter := make([]int, 26)
	j, maxCnt := 0, 0
	for i := range s {
		c := s[i] - 'A'
		counter[c]++
		if maxCnt < counter[c] {
			maxCnt = counter[c]
		}
		if i-j+1 > maxCnt+k {
			counter[s[j]-'A']--
			j++
		}
	}
	return len(s) - j
}
```

### **...**

```

```

<!-- tabs:end -->
