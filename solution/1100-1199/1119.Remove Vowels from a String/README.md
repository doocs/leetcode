# [1119. 删去字符串中的元音](https://leetcode-cn.com/problems/remove-vowels-from-a-string)

[English Version](/solution/1100-1199/1119.Remove%20Vowels%20from%20a%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串&nbsp;<code>S</code>，请你删去其中的所有元音字母（&nbsp;<code>&#39;a&#39;</code>，<code>&#39;e&#39;</code>，<code>&#39;i&#39;</code>，<code>&#39;o&#39;</code>，<code>&#39;u&#39;</code>），并返回这个新字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>&quot;leetcodeisacommunityforcoders&quot;
<strong>输出：</strong>&quot;ltcdscmmntyfrcdrs&quot;
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>&quot;aeiou&quot;
<strong>输出：</strong>&quot;&quot;
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>S</code> 仅由小写英文字母组成。</li>
	<li><code>1 &lt;= S.length &lt;= 1000</code></li>
</ol>


## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def removeVowels(self, s: str) -> str:
        res = []
        for c in s:
            if c not in {'a', 'e', 'i', 'o', 'u'}:
                res.append(c)
        return ''.join(res)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String removeVowels(String s) {
        StringBuilder res = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (!(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')) {
                res.append(c);
            }
        }
        return res.toString();
    }
}
```

### **...**

```

```

<!-- tabs:end -->
