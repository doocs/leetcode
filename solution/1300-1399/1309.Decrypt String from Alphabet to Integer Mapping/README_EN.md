# [1309. Decrypt String from Alphabet to Integer Mapping](https://leetcode.com/problems/decrypt-string-from-alphabet-to-integer-mapping)

[中文文档](/solution/1300-1399/1309.Decrypt%20String%20from%20Alphabet%20to%20Integer%20Mapping/README.md)

## Description

<p>Given a string <code>s</code> formed by digits (<code>&#39;0&#39;</code> - <code>&#39;9&#39;</code>)&nbsp;and <code>&#39;#&#39;</code>&nbsp;.&nbsp;We want to map <code>s</code> to English lowercase characters as follows:</p>

<ul>
	<li>Characters (<code>&#39;a&#39;</code> to <code>&#39;i&#39;)</code> are&nbsp;represented by&nbsp;(<code>&#39;1&#39;</code> to&nbsp;<code>&#39;9&#39;</code>)&nbsp;respectively.</li>
	<li>Characters (<code>&#39;j&#39;</code> to <code>&#39;z&#39;)</code> are represented by (<code>&#39;10#&#39;</code>&nbsp;to&nbsp;<code>&#39;26#&#39;</code>)&nbsp;respectively.&nbsp;</li>
</ul>

<p>Return the string formed after mapping.</p>

<p>It&#39;s guaranteed that a unique mapping will always exist.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;10#11#12&quot;
<strong>Output:</strong> &quot;jkab&quot;
<strong>Explanation:</strong> &quot;j&quot; -&gt; &quot;10#&quot; , &quot;k&quot; -&gt; &quot;11#&quot; , &quot;a&quot; -&gt; &quot;1&quot; , &quot;b&quot; -&gt; &quot;2&quot;.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;1326#&quot;
<strong>Output:</strong> &quot;acz&quot;
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;25#&quot;
<strong>Output:</strong> &quot;y&quot;
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;12345678910#11#12#13#14#15#16#17#18#19#20#21#22#23#24#25#26#&quot;
<strong>Output:</strong> &quot;abcdefghijklmnopqrstuvwxyz&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s[i]</code> only contains digits letters (<code>&#39;0&#39;</code>-<code>&#39;9&#39;</code>) and <code>&#39;#&#39;</code>&nbsp;letter.</li>
	<li><code>s</code> will be valid string&nbsp;such that mapping is always possible.</li>
</ul>


## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def freqAlphabets(self, s: str) -> str:
        def get(s):
            return chr(ord('a') + int(s) - 1)

        i, n = 0, len(s)
        res = []
        while i < n:
            if i + 2 < n and s[i + 2] == '#':
                res.append(get(s[i: i + 2]))
                i += 3
            else:
                res.append(get(s[i]))
                i += 1
        return ''.join(res)
```

### **Java**

```java
class Solution {
    public String freqAlphabets(String s) {
        int i = 0, n = s.length();
        StringBuilder res = new StringBuilder();
        while (i < n) {
            if (i + 2 < n && s.charAt(i + 2) == '#') {
                res.append(get(s.substring(i, i + 2)));
                i += 3;
            } else {
                res.append(get(s.substring(i, i + 1)));
                i += 1;
            }
        }
        return res.toString();
    }

    private char get(String s) {
        return (char) ('a' + Integer.parseInt(s) - 1);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
