# [01.09. String Rotation](https://leetcode-cn.com/problems/string-rotation-lcci)

[中文文档](/lcci/01.09.String%20Rotation/README.md)

## Description
<p>Given two strings, <code>s1</code>&nbsp;and <code>s2</code>, write code to check if <code>s2</code> is a rotation of <code>s1</code> (e.g.,&quot;waterbottle&quot; is a rotation of&quot;erbottlewat&quot;).&nbsp;Can you use&nbsp;only one call to the method that&nbsp;checks if one word is a substring of another?</p>



<p><strong>Example 1:</strong></p>



<pre>

<strong>Input: </strong>s1 = &quot;waterbottle&quot;, s2 = &quot;erbottlewat&quot;

<strong>Output: </strong>True

</pre>



<p><strong>Example 2:</strong></p>



<pre>

<strong>Input: </strong>s1 = &quot;aa&quot;, &quot;aba&quot;

<strong>Output: </strong>False

</pre>



<p>&nbsp;</p>



<p><strong>Note:</strong></p>



<ol>
	<li><code><font face="monospace">0 &lt;= s1.length, s1.length &lt;=&nbsp;</font>100000</code></li>
</ol>




## Solutions

### **Java**

```java
class Solution {
    public boolean isFlipedString(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        if (len1 != len2) {
            return false;
        }
        if ((len1 == 0 && len2 == 0) || (s1.equals(s2))) {
            return true;
        }

        for (int i = 0; i < len1; ++i) {
            s1 = flip(s1);
            if (s1.equals(s2)) {
                return true;
            }
        }
        return false;

    }

    private String flip(String s) {
        return s.substring(1) + s.charAt(0);
    }
}
```

### **...**
```

```

<!-- tabs:end -->