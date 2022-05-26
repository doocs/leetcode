# [205. Isomorphic Strings](https://leetcode.com/problems/isomorphic-strings)

[中文文档](/solution/0200-0299/0205.Isomorphic%20Strings/README.md)

## Description

<p>Given two strings <b><i>s</i></b> and <b><i>t</i></b>, determine if they are isomorphic.</p>

<p>Two strings are isomorphic if the characters in <b><i>s</i></b> can be replaced to get <b><i>t</i></b>.</p>

<p>All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> <b><i>s</i></b> = <code>&quot;egg&quot;, </code><b><i>t = </i></b><code>&quot;add&quot;</code>

<strong>Output:</strong> true

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> <b><i>s</i></b> = <code>&quot;foo&quot;, </code><b><i>t = </i></b><code>&quot;bar&quot;</code>

<strong>Output:</strong> false</pre>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input:</strong> <b><i>s</i></b> = <code>&quot;paper&quot;, </code><b><i>t = </i></b><code>&quot;title&quot;</code>

<strong>Output:</strong> true</pre>

<p><b>Note:</b><br />

You may assume both <b><i>s&nbsp;</i></b>and <b><i>t&nbsp;</i></b>have the same length.</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def isIsomorphic(self, s: str, t: str) -> bool:
        a2b, b2a = {}, {}
        n = len(s)
        for i in range(n):
            a, b = s[i], t[i]
            if (a in a2b and a2b[a] != b) or (b in b2a and b2a[b] != a):
                return False
            a2b[a] = b
            b2a[b] = a
        return True
```

### **Java**

```java
class Solution {
    public boolean isIsomorphic(String s, String t) {
        int n = s.length();
        Map<Character, Character> a2b = new HashMap<>();
        Map<Character, Character> b2a = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            char a = s.charAt(i), b = t.charAt(i);
            if ((a2b.containsKey(a) && a2b.get(a) != b) || (b2a.containsKey(b) && b2a.get(b) != a)) return false;
            a2b.put(a, b);
            b2a.put(b, a);
        }
        return true;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
