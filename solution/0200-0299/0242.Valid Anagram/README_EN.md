# [242. Valid Anagram](https://leetcode.com/problems/valid-anagram)

[中文文档](/solution/0200-0299/0242.Valid%20Anagram/README.md)

## Description

<p>Given two strings <em>s</em> and <em>t&nbsp;</em>, write a function to determine if <em>t</em> is an anagram of <em>s</em>.</p>

<p><b>Example 1:</b></p>

<pre>

<b>Input:</b> <em>s</em> = &quot;anagram&quot;, <em>t</em> = &quot;nagaram&quot;

<b>Output:</b> true

</pre>

<p><b>Example 2:</b></p>

<pre>

<b>Input:</b> <em>s</em> = &quot;rat&quot;, <em>t</em> = &quot;car&quot;

<b>Output: </b>false

</pre>

<p><strong>Note:</strong><br />

You may assume the string contains only lowercase alphabets.</p>

<p><strong>Follow up:</strong><br />

What if the inputs contain unicode characters? How would you adapt your solution to such case?</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def isAnagram(self, s: str, t: str) -> bool:
        if len(s) != len(t):
            return False
        n = len(s)
        chars = [0] * 26
        for i in range(n):
            chars[ord(s[i]) - ord('a')] += 1
            chars[ord(t[i]) - ord('a')] -= 1
        for i in range(26):
            if chars[i] != 0:
                return False
        return True
```

### **Java**

```java
class Solution {
    public boolean isAnagram(String s, String t) {
        int n;
        if ((n = s.length()) != t.length()) {
            return false;
        }
        int[] chars = new int[26];
        for (int i = 0; i < n; ++i) {
            ++chars[s.charAt(i) - 'a'];
            --chars[t.charAt(i) - 'a'];
        }
        for (int i = 0; i < 26; ++i) {
            if (chars[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
