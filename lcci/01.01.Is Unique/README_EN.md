# [01.01. Is Unique](https://leetcode-cn.com/problems/is-unique-lcci)

[中文文档](/lcci/01.01.Is%20Unique/README.md)

## Description

<p>Implement an algorithm to determine if a string has all unique characters. What if you cannot use additional data structures?</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong><code>s</code> = &quot;leetcode&quot;

<strong>Output: </strong>false

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong><code>s</code> = &quot;abc&quot;

<strong>Output: </strong>true

</pre>

<p><strong>Note:</strong></p>

<ul>
	<li><code>0 &lt;= len(s) &lt;= 100 </code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def isUnique(self, astr: str) -> bool:
        sets = set(astr)
        return len(sets) == len(astr)
```

### **Java**

```java
class Solution {
    public boolean isUnique(String astr) {
        char[] chars = astr.toCharArray();
        int len = chars.length;
        for (int i = 0; i < len - 1; ++i) {
            for (int j = i + 1; j < len; ++j) {
                if (chars[i] == chars[j]) {
                    return false;
                }
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
