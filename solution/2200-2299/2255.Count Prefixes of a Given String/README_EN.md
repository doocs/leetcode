# [2255. Count Prefixes of a Given String](https://leetcode.com/problems/count-prefixes-of-a-given-string)

[中文文档](/solution/2200-2299/2255.Count%20Prefixes%20of%20a%20Given%20String/README.md)

## Description

<p>You are given a string array <code>words</code> and a string <code>s</code>, where <code>words[i]</code> and <code>s</code> comprise only of <strong>lowercase English letters</strong>.</p>

<p>Return <em>the <strong>number of strings</strong> in</em> <code>words</code> <em>that are a <strong>prefix</strong> of</em> <code>s</code>.</p>

<p>A <strong>prefix</strong> of a string is a substring that occurs at the beginning of the string. A <b>substring</b> is a contiguous sequence of characters within a string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;a&quot;,&quot;b&quot;,&quot;c&quot;,&quot;ab&quot;,&quot;bc&quot;,&quot;abc&quot;], s = &quot;abc&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong>
The strings in words which are a prefix of s = &quot;abc&quot; are:
&quot;a&quot;, &quot;ab&quot;, and &quot;abc&quot;.
Thus the number of strings in words which are a prefix of s is 3.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;a&quot;,&quot;a&quot;], s = &quot;aa&quot;
<strong>Output:</strong> 2
<strong>Explanation:
</strong>Both of the strings are a prefix of s. 
Note that the same string can occur multiple times in words, and it should be counted each time.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 1000</code></li>
	<li><code>1 &lt;= words[i].length, s.length &lt;= 10</code></li>
	<li><code>words[i]</code> and <code>s</code> consist of lowercase English letters <strong>only</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countPrefixes(self, words: List[str], s: str) -> int:
        return sum(word == s[: len(word)] for word in words)
```

### **Java**

```java
class Solution {
    public int countPrefixes(String[] words, String s) {
        int ans = 0;
        for (String word : words) {
            if (word.equals(s.substring(0, Math.min(s.length(), word.length())))) {
                ++ans;
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
    int countPrefixes(vector<string>& words, string s) {
        int ans = 0;
        for (auto& word : words)
            if (s.substr(0, word.size()) == word)
                ++ans;
        return ans;
    }
};
```

### **Go**

```go
func countPrefixes(words []string, s string) int {
	ans := 0
	for _, word := range words {
		if strings.HasPrefix(s, word) {
			ans++
		}
	}
	return ans
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
