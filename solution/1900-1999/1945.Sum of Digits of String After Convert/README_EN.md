# [1945. Sum of Digits of String After Convert](https://leetcode.com/problems/sum-of-digits-of-string-after-convert)

[中文文档](/solution/1900-1999/1945.Sum%20of%20Digits%20of%20String%20After%20Convert/README.md)

## Description

<p>You are given a string <code>s</code> consisting of lowercase English letters, and an integer <code>k</code>.</p>

<p>First, <strong>convert</strong> <code>s</code> into an integer by replacing each letter with its position in the alphabet (i.e., replace <code>&#39;a&#39;</code> with <code>1</code>, <code>&#39;b&#39;</code> with <code>2</code>, ..., <code>&#39;z&#39;</code> with <code>26</code>). Then, <strong>transform</strong> the integer by replacing it with the <strong>sum of its digits</strong>. Repeat the <strong>transform</strong> operation <code>k</code><strong> times</strong> in total.</p>

<p>For example, if <code>s = &quot;zbax&quot;</code> and <code>k = 2</code>, then the resulting integer would be <code>8</code> by the following operations:</p>

<ul>
	<li><strong>Convert</strong>: <code>&quot;zbax&quot; ➝ &quot;(26)(2)(1)(24)&quot; ➝ &quot;262124&quot; ➝ 262124</code></li>
	<li><strong>Transform #1</strong>: <code>262124 ➝ 2 + 6 + 2 + 1 + 2 + 4&nbsp;➝ 17</code></li>
	<li><strong>Transform #2</strong>: <code>17 ➝ 1 + 7 ➝ 8</code></li>
</ul>

<p>Return <em>the resulting integer after performing the operations described above</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;iiii&quot;, k = 1
<strong>Output:</strong> 36
<strong>Explanation:</strong> The operations are as follows:
- Convert: &quot;iiii&quot; ➝ &quot;(9)(9)(9)(9)&quot; ➝ &quot;9999&quot; ➝ 9999
- Transform #1: 9999 ➝ 9 + 9 + 9 + 9 ➝ 36
Thus the resulting integer is 36.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;leetcode&quot;, k = 2
<strong>Output:</strong> 6
<strong>Explanation:</strong> The operations are as follows:
- Convert: &quot;leetcode&quot; ➝ &quot;(12)(5)(5)(20)(3)(15)(4)(5)&quot; ➝ &quot;12552031545&quot; ➝ 12552031545
- Transform #1: 12552031545 ➝ 1 + 2 + 5 + 5 + 2 + 0 + 3 + 1 + 5 + 4 + 5 ➝ 33
- Transform #2: 33 ➝ 3 + 3 ➝ 6
Thus the resulting integer is 6.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;zbax&quot;, k = 2
<strong>Output:</strong> 8
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>1 &lt;= k &lt;= 10</code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def getLucky(self, s: str, k: int) -> int:
        s = ''.join([str(ord(c) - ord('a') + 1) for c in s])
        for _ in range(k):
            t = 0
            for c in s:
                t += ord(c) - ord('0')
            s = str(t)
        return int(s)
```

### **Java**

```java
class Solution {
    public int getLucky(String s, int k) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            sb.append(c - 'a' + 1);
        }
        s = sb.toString();
        while (k-- > 0) {
            int t = 0;
            for (int i = 0; i < s.length(); ++i) {
                t += s.charAt(i) - '0';
            }
            s = String.valueOf(t);
        }
        return Integer.parseInt(s);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
