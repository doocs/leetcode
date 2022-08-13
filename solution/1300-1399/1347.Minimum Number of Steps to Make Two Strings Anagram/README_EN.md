# [1347. Minimum Number of Steps to Make Two Strings Anagram](https://leetcode.com/problems/minimum-number-of-steps-to-make-two-strings-anagram)

[中文文档](/solution/1300-1399/1347.Minimum%20Number%20of%20Steps%20to%20Make%20Two%20Strings%20Anagram/README.md)

## Description

<p>You are given two strings of the same length <code>s</code> and <code>t</code>. In one step you can choose <strong>any character</strong> of <code>t</code> and replace it with <strong>another character</strong>.</p>

<p>Return <em>the minimum number of steps</em> to make <code>t</code> an anagram of <code>s</code>.</p>

<p>An <strong>Anagram</strong> of a string is a string that contains the same characters with a different (or the same) ordering.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;bab&quot;, t = &quot;aba&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> Replace the first &#39;a&#39; in t with b, t = &quot;bba&quot; which is anagram of s.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;leetcode&quot;, t = &quot;practice&quot;
<strong>Output:</strong> 5
<strong>Explanation:</strong> Replace &#39;p&#39;, &#39;r&#39;, &#39;a&#39;, &#39;i&#39; and &#39;c&#39; from t with proper characters to make t anagram of s.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;anagram&quot;, t = &quot;mangaar&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> &quot;anagram&quot; and &quot;mangaar&quot; are anagrams. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>s.length == t.length</code></li>
	<li><code>s</code> and <code>t</code> consist of lowercase English letters only.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minSteps(self, s: str, t: str) -> int:
        counter = Counter(s)
        res = 0
        for c in t:
            if counter[c] > 0:
                counter[c] -= 1
            else:
                res += 1
        return res
```

### **Java**

```java
class Solution {
    public int minSteps(String s, String t) {
        int[] counter = new int[26];
        for (char c : s.toCharArray()) {
            ++counter[c - 'a'];
        }
        int res = 0;
        for (char c : t.toCharArray()) {
            if (counter[c - 'a'] > 0) {
                --counter[c - 'a'];
            } else {
                ++res;
            }
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minSteps(string s, string t) {
        vector<int> counter(26);
        for (char c : s) ++counter[c - 'a'];
        int res = 0;
        for (char c : t) {
            if (counter[c - 'a'] > 0)
                --counter[c - 'a'];
            else
                ++res;
        }
        return res;
    }
};
```

### **Go**

```go
func minSteps(s string, t string) int {
	counter := make([]int, 26)
	for _, c := range s {
		counter[c-'a']++
	}
	res := 0
	for _, c := range t {
		if counter[c-'a'] > 0 {
			counter[c-'a']--
		} else {
			res++
		}
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
