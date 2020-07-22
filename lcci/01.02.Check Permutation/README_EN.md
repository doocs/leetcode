# [01.02. Check Permutation](https://leetcode-cn.com/problems/check-permutation-lcci)

[中文文档](/lcci/01.02.Check%20Permutation/README.md)

## Description
<p>Given two strings,write a method to decide if one is a permutation of the other.</p>



<p><strong>Example 1:</strong></p>



<pre>

<strong>Input: </strong><code>s1</code> = &quot;abc&quot;, <code>s2</code> = &quot;bca&quot;

<strong>Output: </strong>true

</pre>



<p><strong>Example 2:</strong></p>



<pre>

<strong>Input: </strong><code>s1</code> = &quot;abc&quot;, <code>s2</code> = &quot;bad&quot;

<strong>Output: </strong>false

</pre>



<p><strong>Note:</strong></p>
<ol>
	<li><code>0 &lt;= len(s1) &lt;= 100 </code></li>
	<li><code>0 &lt;= len(s2) &lt;= 100</code></li>
</ol>




## Solutions


<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def CheckPermutation(self, s1: str, s2: str) -> bool:
        return sorted(s1) == sorted(s2)
```

### **Java**

```java
class Solution {
    public boolean CheckPermutation(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);
        return Arrays.equals(c1, c2);
    }
}
```

### **...**
```

```

<!-- tabs:end -->