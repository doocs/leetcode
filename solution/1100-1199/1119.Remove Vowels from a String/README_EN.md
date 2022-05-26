# [1119. Remove Vowels from a String](https://leetcode.com/problems/remove-vowels-from-a-string)

[中文文档](/solution/1100-1199/1119.Remove%20Vowels%20from%20a%20String/README.md)

## Description

<p>Given a string <code>s</code>, remove the vowels <code>&#39;a&#39;</code>, <code>&#39;e&#39;</code>, <code>&#39;i&#39;</code>, <code>&#39;o&#39;</code>, and <code>&#39;u&#39;</code> from it, and return the new string.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;leetcodeisacommunityforcoders&quot;
<strong>Output:</strong> &quot;ltcdscmmntyfrcdrs&quot;
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aeiou&quot;
<strong>Output:</strong> &quot;&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code> consists of only lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def removeVowels(self, s: str) -> str:
        res = []
        for c in s:
            if c not in {'a', 'e', 'i', 'o', 'u'}:
                res.append(c)
        return ''.join(res)
```

### **Java**

```java
class Solution {
    public String removeVowels(String s) {
        StringBuilder res = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (!(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')) {
                res.append(c);
            }
        }
        return res.toString();
    }
}
```

### **...**

```

```

<!-- tabs:end -->
