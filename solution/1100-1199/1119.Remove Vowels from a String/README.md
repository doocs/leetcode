# [1119. 删去字符串中的元音](https://leetcode.cn/problems/remove-vowels-from-a-string)

[English Version](/solution/1100-1199/1119.Remove%20Vowels%20from%20a%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code>&nbsp;，请你删去其中的所有元音字母&nbsp;<code>'a'</code>，<code>'e'</code>，<code>'i'</code>，<code>'o'</code>，<code>'u'</code>，并返回这个新字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "leetcodeisacommunityforcoders"
<strong>输出：</strong>"ltcdscmmntyfrcdrs"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "aeiou"
<strong>输出：</strong>""
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= S.length &lt;= 1000</code></li>
	<li><code>s</code>&nbsp;仅由小写英文字母组成</li>
</ul>

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
