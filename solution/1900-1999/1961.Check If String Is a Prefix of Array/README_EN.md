# [1961. Check If String Is a Prefix of Array](https://leetcode.com/problems/check-if-string-is-a-prefix-of-array)

[中文文档](/solution/1900-1999/1961.Check%20If%20String%20Is%20a%20Prefix%20of%20Array/README.md)

## Description

<p>Given a string <code>s</code> and an array of strings <code>words</code>, determine whether <code>s</code> is a <strong>prefix string</strong> of <code>words</code>.</p>

<p>A string <code>s</code> is a <strong>prefix string</strong> of <code>words</code> if <code>s</code> can be made by concatenating the first <code>k</code> strings in <code>words</code> for some <strong>positive</strong> <code>k</code> no larger than <code>words.length</code>.</p>

<p>Return <code>true</code><em> if </em><code>s</code><em> is a <strong>prefix string</strong> of </em><code>words</code><em>, or </em><code>false</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;iloveleetcode&quot;, words = [&quot;i&quot;,&quot;love&quot;,&quot;leetcode&quot;,&quot;apples&quot;]
<strong>Output:</strong> true
<strong>Explanation:</strong>
s can be made by concatenating &quot;i&quot;, &quot;love&quot;, and &quot;leetcode&quot; together.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;iloveleetcode&quot;, words = [&quot;apples&quot;,&quot;i&quot;,&quot;love&quot;,&quot;leetcode&quot;]
<strong>Output:</strong> false
<strong>Explanation:</strong>
It is impossible to make s using a prefix of arr.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 20</code></li>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>words[i]</code> and <code>s</code> consist of only lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def isPrefixString(self, s: str, words: List[str]) -> bool:
        t = 0
        for i, w in enumerate(words):
            t += len(w)
            if len(s) == t:
                return ''.join(words[: i + 1]) == s
        return False
```

### **Java**

```java
class Solution {
    public boolean isPrefixString(String s, String[] words) {
        StringBuilder t = new StringBuilder();
        for (String w : words) {
            t.append(w);
            if (s.length() == t.length()) {
                return s.equals(t.toString());
            }
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isPrefixString(string s, vector<string>& words) {
        string t = "";
        for (string& w : words) {
            t += w;
            if (t.size() == s.size()) return t == s;
        }
        return false;
    }
};
```

### **Go**

```go
func isPrefixString(s string, words []string) bool {
	t := ""
	for _, w := range words {
		t += w
		if t == s {
			return true
		}
	}
	return false
}
```

### **...**

```

```

<!-- tabs:end -->
