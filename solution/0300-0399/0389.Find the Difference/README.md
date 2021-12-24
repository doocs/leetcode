# [389. 找不同](https://leetcode-cn.com/problems/find-the-difference)

[English Version](/solution/0300-0399/0389.Find%20the%20Difference/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个字符串 <em><strong>s</strong></em> 和 <em><strong>t</strong></em>，它们只包含小写字母。</p>

<p>字符串&nbsp;<strong><em>t</em></strong>&nbsp;由字符串&nbsp;<strong><em>s</em></strong>&nbsp;随机重排，然后在随机位置添加一个字母。</p>

<p>请找出在 <em><strong>t</strong></em> 中被添加的字母。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = &quot;abcd&quot;, t = &quot;abcde&quot;
<strong>输出：</strong>&quot;e&quot;
<strong>解释：</strong>&#39;e&#39; 是那个被添加的字母。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = &quot;&quot;, t = &quot;y&quot;
<strong>输出：</strong>&quot;y&quot;
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>s = &quot;a&quot;, t = &quot;aa&quot;
<strong>输出：</strong>&quot;a&quot;
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>s = &quot;ae&quot;, t = &quot;aea&quot;
<strong>输出：</strong>&quot;a&quot;
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= s.length &lt;= 1000</code></li>
	<li><code>t.length == s.length + 1</code></li>
	<li><code>s</code> 和 <code>t</code> 只包含小写字母</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

计数器实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findTheDifference(self, s: str, t: str) -> str:
        counter = Counter(s)
        for c in t:
            if counter[c] <= 0:
                return c
            counter[c] -= 1
        return None
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public char findTheDifference(String s, String t) {
        int[] counter = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            int index = s.charAt(i) - 'a';
            ++counter[index];
        }
        for (int i = 0; i < t.length(); ++i) {
            int index = t.charAt(i) - 'a';
            if (counter[index] <= 0) {
                return t.charAt(i);
            }
            --counter[index];
        }
        return ' ';
    }
}
```

### **...**

```

```

<!-- tabs:end -->
