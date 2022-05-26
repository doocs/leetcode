# [1047. Remove All Adjacent Duplicates In String](https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string)

[中文文档](/solution/1000-1099/1047.Remove%20All%20Adjacent%20Duplicates%20In%20String/README.md)

## Description

<p>You are given a string <code>s</code> consisting of lowercase English letters. A <strong>duplicate removal</strong> consists of choosing two <strong>adjacent</strong> and <strong>equal</strong> letters and removing them.</p>

<p>We repeatedly make <strong>duplicate removals</strong> on <code>s</code> until we no longer can.</p>

<p>Return <em>the final string after all such duplicate removals have been made</em>. It can be proven that the answer is <strong>unique</strong>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abbaca&quot;
<strong>Output:</strong> &quot;ca&quot;
<strong>Explanation:</strong> 
For example, in &quot;abbaca&quot; we could remove &quot;bb&quot; since the letters are adjacent and equal, and this is the only possible move.  The result of this move is that the string is &quot;aaca&quot;, of which only &quot;aa&quot; is possible, so the final string is &quot;ca&quot;.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;azxxzy&quot;
<strong>Output:</strong> &quot;ay&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def removeDuplicates(self, S: str) -> str:
        res = []
        for s in S:
            if not res or res[-1] != s:
                res.append(s)
            else:
                res.pop()
        return ''.join(res)
```

### **Java**

```java
class Solution {
    public String removeDuplicates(String S) {
        StringBuilder sb = new StringBuilder();
        int top = -1;
        for (int i = 0, n = S.length(); i < n; ++i) {
            char s = S.charAt(i);
            if (top == -1 || sb.charAt(top) != s) {
                sb.append(s);
                ++top;
            } else {
                sb.deleteCharAt(top);
                --top;
            }
        }
        return sb.toString();
    }
}
```

### **...**

```

```

<!-- tabs:end -->
