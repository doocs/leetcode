# [290. Word Pattern](https://leetcode.com/problems/word-pattern)

[中文文档](/solution/0200-0299/0290.Word%20Pattern/README.md)

## Description

<p>Given a <code>pattern</code> and a string <code>str</code>, find if <code>str</code> follows the same pattern.</p>

<p>Here <b>follow</b> means a full match, such that there is a bijection between a letter in <code>pattern</code> and a <b>non-empty</b> word in <code>str</code>.</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> pattern = <code>&quot;abba&quot;</code>, str = <code>&quot;dog cat cat dog&quot;</code>

<strong>Output:</strong> true</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong>pattern = <code>&quot;abba&quot;</code>, str = <code>&quot;dog cat cat fish&quot;</code>

<strong>Output:</strong> false</pre>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input:</strong> pattern = <code>&quot;aaaa&quot;</code>, str = <code>&quot;dog cat cat dog&quot;</code>

<strong>Output:</strong> false</pre>

<p><strong>Example 4:</strong></p>

<pre>

<strong>Input:</strong> pattern = <code>&quot;abba&quot;</code>, str = <code>&quot;dog dog dog dog&quot;</code>

<strong>Output:</strong> false</pre>

<p><b>Notes:</b><br />

You may assume <code>pattern</code> contains only lowercase letters, and <code>str</code> contains lowercase letters that may be separated by a single space.</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def wordPattern(self, pattern: str, s: str) -> bool:
        ch2str, str2ch = {}, {}
        ss = s.split(' ')
        n = len(pattern)
        if n != len(ss):
            return False
        for i in range(n):
            if ch2str.get(pattern[i]) is not None and ch2str.get(pattern[i]) != ss[i]:
                return False
            if str2ch.get(ss[i]) is not None and str2ch.get(ss[i]) != pattern[i]:
                return False
            ch2str[pattern[i]] = ss[i]
            str2ch[ss[i]] = pattern[i]
        return True
```

### **Java**

```java
class Solution {
    public boolean wordPattern(String pattern, String s) {
        Map<Character, String> ch2str = new HashMap<>();
        Map<String, Character> str2ch = new HashMap<>();
        String[] ss = s.split(" ");
        int n = pattern.length();
        if (n != ss.length) {
            return false;
        }
        for (int i = 0; i < n; ++i) {
            char ch = pattern.charAt(i);
            if (ch2str.containsKey(ch) && !ch2str.get(ch).equals(ss[i])) {
                return false;
            }
            if (str2ch.containsKey(ss[i]) && !str2ch.get(ss[i]).equals(ch)) {
                return false;
            }
            ch2str.put(ch, ss[i]);
            str2ch.put(ss[i], ch);
        }
        return true;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
