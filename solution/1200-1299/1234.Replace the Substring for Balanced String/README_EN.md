# [1234. Replace the Substring for Balanced String](https://leetcode.com/problems/replace-the-substring-for-balanced-string)

[中文文档](/solution/1200-1299/1234.Replace%20the%20Substring%20for%20Balanced%20String/README.md)

## Description

<p>You are given a string s of length <code>n</code> containing only four kinds of characters: <code>&#39;Q&#39;</code>, <code>&#39;W&#39;</code>, <code>&#39;E&#39;</code>, and <code>&#39;R&#39;</code>.</p>

<p>A string is said to be <strong>balanced</strong><em> </em>if each of its characters appears <code>n / 4</code> times where <code>n</code> is the length of the string.</p>

<p>Return <em>the minimum length of the substring that can be replaced with <strong>any</strong> other string of the same length to make </em><code>s</code><em> <strong>balanced</strong></em>. If s is already <strong>balanced</strong>, return <code>0</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;QWER&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> s is already balanced.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;QQWE&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> We need to replace a &#39;Q&#39; to &#39;R&#39;, so that &quot;RQWE&quot; (or &quot;QRWE&quot;) is balanced.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;QQQW&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> We can replace the first &quot;QQ&quot; to &quot;ER&quot;. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == s.length</code></li>
	<li><code>4 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>n</code> is a multiple of <code>4</code>.</li>
	<li><code>s</code> contains only <code>&#39;Q&#39;</code>, <code>&#39;W&#39;</code>, <code>&#39;E&#39;</code>, and <code>&#39;R&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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

```java

```

### **...**

```

```

<!-- tabs:end -->
