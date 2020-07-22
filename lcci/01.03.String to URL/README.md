# [面试题 01.03. URL化](https://leetcode-cn.com/problems/string-to-url-lcci)

[English Version](/lcci/01.03.String%20to%20URL/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
<p>URL化。编写一种方法，将字符串中的空格全部替换为<code>%20</code>。假定该字符串尾部有足够的空间存放新增字符，并且知道字符串的&ldquo;真实&rdquo;长度。（注：用<code>Java</code>实现的话，请使用字符数组实现，以便直接在数组上操作。）</p>

<p><strong>示例1:</strong></p>

<pre><strong> 输入</strong>：&quot;Mr John Smith    &quot;, 13
<strong> 输出</strong>：&quot;Mr%20John%20Smith&quot;
</pre>

<p><strong>示例2:</strong></p>

<pre><strong> 输入</strong>：&quot;               &quot;, 5
<strong> 输出</strong>：&quot;%20%20%20%20%20&quot;
</pre>

<p><strong>提示：</strong></p>

<ol>
	<li>字符串长度在[0, 500000]范围内。</li>
</ol>


## 解法
<!-- 这里可写通用的实现逻辑 -->


<!-- tabs:start -->

### **Python3**
<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def replaceSpaces(self, S: str, length: int) -> str:
        S = S[:length] if length < len(S) else S
        return S.replace(' ', '%20')
```

### **Java**
<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String replaceSpaces(String S, int length) {
        char[] c = S.toCharArray();
        int j = c.length;
        for (int i = length - 1; i >= 0; i--) {
            if (c[i] == ' ') {
                c[--j] = '0';
                c[--j] = '2';
                c[--j] = '%';
            } else {
                c[--j] = c[i];
            }
        }
        return new String(c, j, c.length - j);
    }
}
```

### **...**
```

```

<!-- tabs:end -->