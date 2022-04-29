# [1234. 替换子串得到平衡字符串](https://leetcode.cn/problems/replace-the-substring-for-balanced-string)

[English Version](/solution/1200-1299/1234.Replace%20the%20Substring%20for%20Balanced%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有一个只含有&nbsp;<code>&#39;Q&#39;, &#39;W&#39;, &#39;E&#39;,&nbsp;&#39;R&#39;</code>&nbsp;四种字符，且长度为 <code>n</code>&nbsp;的字符串。</p>

<p>假如在该字符串中，这四个字符都恰好出现&nbsp;<code>n/4</code>&nbsp;次，那么它就是一个「平衡字符串」。</p>

<p>&nbsp;</p>

<p>给你一个这样的字符串 <code>s</code>，请通过「替换一个子串」的方式，使原字符串 <code>s</code> 变成一个「平衡字符串」。</p>

<p>你可以用和「待替换子串」长度相同的&nbsp;<strong>任何</strong> 其他字符串来完成替换。</p>

<p>请返回待替换子串的最小可能长度。</p>

<p>如果原字符串自身就是一个平衡字符串，则返回 <code>0</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = &quot;QWER&quot;
<strong>输出：</strong>0
<strong>解释：</strong>s 已经是平衡的了。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = &quot;QQWE&quot;
<strong>输出：</strong>1
<strong>解释：</strong>我们需要把一个 &#39;Q&#39; 替换成 &#39;R&#39;，这样得到的 &quot;RQWE&quot; (或 &quot;QRWE&quot;) 是平衡的。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>s = &quot;QQQW&quot;
<strong>输出：</strong>2
<strong>解释：</strong>我们可以把前面的 &quot;QQ&quot; 替换成 &quot;ER&quot;。 
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>s = &quot;QQQQ&quot;
<strong>输出：</strong>3
<strong>解释：</strong>我们可以替换后 3 个 &#39;Q&#39;，使 s = &quot;QWER&quot;。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10^5</code></li>
	<li><code>s.length</code>&nbsp;是&nbsp;<code>4</code>&nbsp;的倍数</li>
	<li><code>s</code>&nbsp;中只含有&nbsp;<code>&#39;Q&#39;</code>, <code>&#39;W&#39;</code>, <code>&#39;E&#39;</code>,&nbsp;<code>&#39;R&#39;</code>&nbsp;四种字符</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def balancedString(self, s: str) -> int:
        # count the occurence of each char
        count_chars = Counter(s)

        required = len(s) // 4

        # hold the number of excessive occurences
        more_chars = defaultdict(int)
        for char, count_char in count_chars.items():
            more_chars[char] = max(0, count_char - required)

        min_len = len(s)

        # count the number of total replacements
        need_replace = sum(more_chars.values())
        if need_replace == 0:
            return 0

        # Sliding windows
        # First, move the second cursors until satisfy the conditions
        # Second, move the first_cursor so that it still satisfy the requirement

        first_cursor, second_cursor = 0, 0
        while second_cursor < len(s):
            # Move second_cursor
            if more_chars[s[second_cursor]] > 0:
                need_replace -= 1
            more_chars[s[second_cursor]] -= 1
            second_cursor += 1

            # Move first_cursor
            while first_cursor < second_cursor and need_replace == 0:
                min_len = min(min_len, second_cursor - first_cursor)
                if s[first_cursor] in more_chars:
                    more_chars[s[first_cursor]] += 1
                    if more_chars[s[first_cursor]] > 0:
                        need_replace += 1
                first_cursor += 1

        return min_len
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **...**

```

```

<!-- tabs:end -->
