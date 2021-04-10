# [709. 转换成小写字母](https://leetcode-cn.com/problems/to-lower-case)

[English Version](/solution/0700-0799/0709.To%20Lower%20Case/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>实现函数 ToLowerCase()，该函数接收一个字符串参数 str，并将该字符串中的大写字母转换成小写字母，之后返回新的字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入: </strong>&quot;Hello&quot;
<strong>输出: </strong>&quot;hello&quot;</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入: </strong>&quot;here&quot;
<strong>输出: </strong>&quot;here&quot;</pre>

<p><strong>示例</strong><strong>&nbsp;3：</strong></p>

<pre>
<strong>输入: </strong>&quot;LOVELY&quot;
<strong>输出: </strong>&quot;lovely&quot;
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

遍历字符串，遇到大写的字符，转小写。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
