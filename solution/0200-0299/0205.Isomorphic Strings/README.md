# [205. 同构字符串](https://leetcode-cn.com/problems/isomorphic-strings)

[English Version](/solution/0200-0299/0205.Isomorphic%20Strings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个字符串 <em><strong>s </strong></em>和 <strong><em>t</em></strong>，判断它们是否是同构的。</p>

<p>如果 <em><strong>s </strong></em>中的字符可以按某种映射关系替换得到 <strong><em>t </em></strong>，那么这两个字符串是同构的。</p>

<p>每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。</p>

<p> </p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入：</strong><strong><em>s</em></strong> = <code>"egg", </code><strong><em>t = </em></strong><code>"add"</code>
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong><strong><em>s</em></strong> = <code>"foo", </code><strong><em>t = </em></strong><code>"bar"</code>
<strong>输出：</strong>false</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong><strong><em>s</em></strong> = <code>"paper", </code><strong><em>t = </em></strong><code>"title"</code>
<strong>输出：</strong>true</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>可以假设 <em><strong>s </strong></em>和 <strong><em>t </em></strong>长度相同。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isIsomorphic(self, s: str, t: str) -> bool:
        a2b, b2a = {}, {}
        n = len(s)
        for i in range(n):
            a, b = s[i], t[i]
            if (a in a2b and a2b[a] != b) or (b in b2a and b2a[b] != a):
                return False
            a2b[a] = b
            b2a[b] = a
        return True
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isIsomorphic(String s, String t) {
        int n = s.length();
        Map<Character, Character> a2b = new HashMap<>();
        Map<Character, Character> b2a = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            char a = s.charAt(i), b = t.charAt(i);
            if ((a2b.containsKey(a) && a2b.get(a) != b) || (b2a.containsKey(b) && b2a.get(b) != a)) return false;
            a2b.put(a, b);
            b2a.put(b, a);
        }
        return true;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
