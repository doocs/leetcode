# [344. Reverse String](https://leetcode.com/problems/reverse-string)

[中文文档](/solution/0300-0399/0344.Reverse%20String/README.md)

## Description

<p>Write a function that reverses a string. The input string is given as an array of characters <code>s</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> s = ["h","e","l","l","o"]
<strong>Output:</strong> ["o","l","l","e","h"]
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> s = ["H","a","n","n","a","h"]
<strong>Output:</strong> ["h","a","n","n","a","H"]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> is a <a href="https://en.wikipedia.org/wiki/ASCII#Printable_characters" target="_blank">printable ascii character</a>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Do not allocate extra space for another array. You must do this by modifying the input array <a href="https://en.wikipedia.org/wiki/In-place_algorithm" target="_blank">in-place</a> with <code>O(1)</code> extra memory.</p>


## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def reverseString(self, s: List[str]) -> None:
        """
        Do not return anything, modify s in-place instead.
        """
        s[:] = s[::-1]
```

### **Java**

```java
class Solution {
    public void reverseString(char[] s) {
        int n;
        if (s == null || (n = s.length) < 2) return;
        int i = 0, j = n - 1;
        while (i < j) {
            char t = s[i];
            s[i] = s[j];
            s[j] = t;
            ++i;
            --j;
        }
    }
}
```

### **...**

```

```

<!-- tabs:end -->
