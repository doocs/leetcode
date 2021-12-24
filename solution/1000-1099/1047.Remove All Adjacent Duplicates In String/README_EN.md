# [1047. Remove All Adjacent Duplicates In String](https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string)

[中文文档](/solution/1000-1099/1047.Remove%20All%20Adjacent%20Duplicates%20In%20String/README.md)

## Description

<p>Given a string <code>S</code> of lowercase letters, a <em>duplicate removal</em> consists of choosing two adjacent and equal letters, and removing&nbsp;them.</p>

<p>We repeatedly make duplicate removals on S until we no longer can.</p>

<p>Return the final string after all such duplicate removals have been made.&nbsp; It is guaranteed the answer is unique.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-1-1">&quot;abbaca&quot;</span>

<strong>Output: </strong><span id="example-output-1">&quot;ca&quot;</span>

<strong>Explanation: </strong>

For example, in &quot;abbaca&quot; we could remove &quot;bb&quot; since the letters are adjacent and equal, and this is the only possible move.&nbsp; The result of this move is that the string is &quot;aaca&quot;, of which only &quot;aa&quot; is possible, so the final string is &quot;ca&quot;.

</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>1 &lt;= S.length &lt;= 20000</code></li>
	<li><code>S</code> consists only of English lowercase letters.</li>
</ol>

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
