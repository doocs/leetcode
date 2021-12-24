# [1461. Check If a String Contains All Binary Codes of Size K](https://leetcode.com/problems/check-if-a-string-contains-all-binary-codes-of-size-k)

[中文文档](/solution/1400-1499/1461.Check%20If%20a%20String%20Contains%20All%20Binary%20Codes%20of%20Size%20K/README.md)

## Description

<p>Given a binary string <code>s</code> and an integer <code>k</code>.</p>

<p>Return <code>true</code> <em>if every binary code of length</em> <code>k</code> <em>is a substring of</em> <code>s</code>. Otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;00110110&quot;, k = 2
<strong>Output:</strong> true
<strong>Explanation:</strong> The binary codes of length 2 are &quot;00&quot;, &quot;01&quot;, &quot;10&quot; and &quot;11&quot;. They can be all found as substrings at indicies 0, 1, 3 and 2 respectively.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;00110&quot;, k = 2
<strong>Output:</strong> true
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;0110&quot;, k = 1
<strong>Output:</strong> true
<strong>Explanation:</strong> The binary codes of length 1 are &quot;0&quot; and &quot;1&quot;, it is clear that both exist as a substring. 
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;0110&quot;, k = 2
<strong>Output:</strong> false
<strong>Explanation:</strong> The binary code &quot;00&quot; is of length 2 and doesn&#39;t exist in the array.
</pre>

<p><strong>Example 5:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;0000000001011100&quot;, k = 4
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 5 * 10<sup>5</sup></code></li>
	<li><code>s[i]</code> is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
	<li><code>1 &lt;= k &lt;= 20</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def hasAllCodes(self, s: str, k: int) -> bool:
        counter = 1 << k
        exists = set()
        for i in range(k, len(s) + 1):
            if s[i - k: i] not in exists:
                exists.add(s[i - k: i])
                counter -= 1
            if counter == 0:
                return True
        return False
```

### **Java**

```java
class Solution {
    public boolean hasAllCodes(String s, int k) {
        int counter = 1 << k;
        Set<String> exists = new HashSet<>();
        for (int i = k; i <= s.length(); ++i) {
            String t = s.substring(i - k, i);
            if (!exists.contains(t)) {
                exists.add(t);
                --counter;
            }
            if (counter == 0) {
                return true;
            }
        }
        return false;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
