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
    def toLowerCase(self, s: str) -> str:
        return ''.join([chr(ord(c) | 32) if ord('A') <= ord(c) <= ord('Z') else c for c in s])
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String toLowerCase(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; ++i) {
            if (chars[i] >= 'A' && chars[i] <= 'Z') {
                chars[i] |= 32;
            }
        }
        return new String(chars);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string toLowerCase(string s) {
        for (char& c : s)
            if (c >= 'A' && c <= 'Z')
                c |= 32;
        return s;
    }
};
```

### **Go**

```go
func toLowerCase(s string) string {
	sb := &strings.Builder{}
	sb.Grow(len(s))
	for _, c := range s {
		if c >= 'A' && c <= 'Z' {
			c |= 32
		}
		sb.WriteRune(c)
	}
	return sb.String()
}
```

### **...**

```

```

<!-- tabs:end -->
