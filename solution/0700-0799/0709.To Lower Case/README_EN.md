# [709. To Lower Case](https://leetcode.com/problems/to-lower-case)

[中文文档](/solution/0700-0799/0709.To%20Lower%20Case/README.md)

## Description

<p>Implement function ToLowerCase() that has a string parameter str, and returns the same string in lowercase.</p>



<p>&nbsp;</p>



<div>

<p><strong>Example 1:</strong></p>



<pre>

<strong>Input: </strong><span id="example-input-1-1">&quot;Hello&quot;</span>

<strong>Output: </strong><span id="example-output-1">&quot;hello&quot;</span>

</pre>



<div>

<p><strong>Example 2:</strong></p>



<pre>

<strong>Input: </strong><span id="example-input-2-1">&quot;here&quot;</span>

<strong>Output: </strong><span id="example-output-2">&quot;here&quot;</span>

</pre>



<div>

<p><strong>Example 3:</strong></p>



<pre>

<strong>Input: </strong><span id="example-input-3-1">&quot;LOVELY&quot;</span>

<strong>Output: </strong><span id="example-output-3">&quot;lovely&quot;</span>

</pre>

</div>

</div>

</div>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def toLowerCase(self, str: str) -> str:
        if not str:
            return str
        n = len(str)
        res = []
        for i in range(n):
            c = ord(str[i])
            if c >= 65 and c <= 90:
               c += 32
            res.append(chr(c))
        return ''.join(res)
```

### **Java**

```java
class Solution {
    public String toLowerCase(String str) {
        int n;
        if (str == null || (n = str.length()) == 0) return str;
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; ++i) {
            boolean isUpper = chars[i] >= 'A' && chars[i] <= 'Z';
            if (isUpper) chars[i] += 32;
        }
        return new String(chars);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
