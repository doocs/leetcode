# [1684. Count the Number of Consistent Strings](https://leetcode.com/problems/count-the-number-of-consistent-strings)

[中文文档](/solution/1600-1699/1684.Count%20the%20Number%20of%20Consistent%20Strings/README.md)

## Description

<p>You are given a string <code>allowed</code> consisting of <strong>distinct</strong> characters and an array of strings <code>words</code>. A string is <strong>consistent </strong>if all characters in the string appear in the string <code>allowed</code>.</p>

<p>Return<em> the number of <strong>consistent</strong> strings in the array </em><code>words</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> allowed = &quot;ab&quot;, words = [&quot;ad&quot;,&quot;bd&quot;,&quot;aaab&quot;,&quot;baa&quot;,&quot;badab&quot;]
<strong>Output:</strong> 2
<strong>Explanation:</strong> Strings &quot;aaab&quot; and &quot;baa&quot; are consistent since they only contain characters &#39;a&#39; and &#39;b&#39;.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> allowed = &quot;abc&quot;, words = [&quot;a&quot;,&quot;b&quot;,&quot;c&quot;,&quot;ab&quot;,&quot;ac&quot;,&quot;bc&quot;,&quot;abc&quot;]
<strong>Output:</strong> 7
<strong>Explanation:</strong> All strings are consistent.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> allowed = &quot;cad&quot;, words = [&quot;cc&quot;,&quot;acd&quot;,&quot;b&quot;,&quot;ba&quot;,&quot;bac&quot;,&quot;bad&quot;,&quot;ac&quot;,&quot;d&quot;]
<strong>Output:</strong> 4
<strong>Explanation:</strong> Strings &quot;cc&quot;, &quot;acd&quot;, &quot;ac&quot;, and &quot;d&quot; are consistent.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= allowed.length &lt;=<sup> </sup>26</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 10</code></li>
	<li>The characters in <code>allowed</code> are <strong>distinct</strong>.</li>
	<li><code>words[i]</code> and <code>allowed</code> contain only lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countConsistentStrings(self, allowed: str, words: List[str]) -> int:
        res = 0
        chars = set(allowed)
        for word in words:
            find = True
            for c in word:
                if c not in chars:
                    find = False
                    break
            if find:
                res += 1
        return res
```

### **Java**

```java
class Solution {
    public int countConsistentStrings(String allowed, String[] words) {
        boolean[] chars = new boolean[26];
        for (char c : allowed.toCharArray()) {
            chars[c - 'a'] = true;
        }
        int res = 0;
        for (String word : words) {
            boolean find = true;
            for (char c : word.toCharArray()) {
                if (!chars[c - 'a']) {
                    find = false;
                    break;
                }
            }
            if (find) {
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
    int countConsistentStrings(string allowed, vector<string>& words) {
        vector<bool> chars(26, false);
        for (char c : allowed) {
            chars[c - 'a'] = true;
        }
        int res = 0;
        for (string word : words) {
            bool find = true;
            for (char c : word) {
                if (!chars[c - 'a']) {
                    find = false;
                    break;
                }
            }
            if (find) ++res;
        }
        return res;
    }
};
```

### **Go**

```go
func countConsistentStrings(allowed string, words []string) int {
	chars := [26]bool{}
	for _, c := range allowed {
		chars[c-'a'] = true
	}
	res := 0
	for _, word := range words {
		find := true
		for _, c := range word {
			if !chars[c-'a'] {
				find = false
				break
			}
		}
		if find {
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
