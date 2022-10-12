# [1410. HTML Entity Parser](https://leetcode.com/problems/html-entity-parser)

[中文文档](/solution/1400-1499/1410.HTML%20Entity%20Parser/README.md)

## Description

<p><strong>HTML entity parser</strong> is the parser that takes HTML code as input and replace all the entities of the special characters by the characters itself.</p>

<p>The special characters and their entities for HTML are:</p>

<ul>
	<li><strong>Quotation Mark:</strong> the entity is <code>&amp;quot;</code> and symbol character is <code>&quot;</code>.</li>
	<li><strong>Single Quote Mark:</strong> the entity is <code>&amp;apos;</code> and symbol character is <code>&#39;</code>.</li>
	<li><strong>Ampersand:</strong> the entity is <code>&amp;amp;</code> and symbol character is <code>&amp;</code>.</li>
	<li><strong>Greater Than Sign:</strong> the entity is <code>&amp;gt;</code> and symbol character is <code>&gt;</code>.</li>
	<li><strong>Less Than Sign:</strong> the entity is <code>&amp;lt;</code> and symbol character is <code>&lt;</code>.</li>
	<li><strong>Slash:</strong> the entity is <code>&amp;frasl;</code> and symbol character is <code>/</code>.</li>
</ul>

<p>Given the input <code>text</code> string to the HTML parser, you have to implement the entity parser.</p>

<p>Return <em>the text after replacing the entities by the special characters</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> text = &quot;&amp;amp; is an HTML entity but &amp;ambassador; is not.&quot;
<strong>Output:</strong> &quot;&amp; is an HTML entity but &amp;ambassador; is not.&quot;
<strong>Explanation:</strong> The parser will replace the &amp;amp; entity by &amp;
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> text = &quot;and I quote: &amp;quot;...&amp;quot;&quot;
<strong>Output:</strong> &quot;and I quote: \&quot;...\&quot;&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= text.length &lt;= 10<sup>5</sup></code></li>
	<li>The string may contain any possible characters out of all the 256 ASCII characters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def entityParser(self, text: str) -> str:
        d = {
            '&quot;': '"',
            '&apos;': "'",
            '&amp;': "&",
            "&gt;": '>',
            "&lt;": '<',
            "&frasl;": '/',
        }
        i, n = 0, len(text)
        ans = []
        while i < n:
            for l in range(1, 8):
                j = i + l
                if text[i:j] in d:
                    ans.append(d[text[i:j]])
                    i = j
                    break
            else:
                ans.append(text[i])
                i += 1
        return ''.join(ans)
```

### **Java**

```java
class Solution {
    public String entityParser(String text) {
        Map<String, String> d = new HashMap<>();
        d.put("&quot;", "\"");
        d.put("&apos;", "'");
        d.put("&amp;", "&");
        d.put("&gt;", ">");
        d.put("&lt;", "<");
        d.put("&frasl;", "/");
        StringBuilder ans = new StringBuilder();
        int i = 0;
        int n = text.length();
        while (i < n) {
            boolean find = false;
            for (int l = 1; l < 8; ++l) {
                int j = i + l;
                if (j <= n) {
                    String t = text.substring(i, j);
                    if (d.containsKey(t)) {
                        ans.append(d.get(t));
                        i = j;
                        find = true;
                        break;
                    }
                }
            }
            if (!find) {
                ans.append(text.charAt(i++));
            }
        }
        return ans.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string entityParser(string text) {
        unordered_map<string, string> d;
        d["&quot;"] = "\"";
        d["&apos;"] = "'";
        d["&amp;"] = "&";
        d["&gt;"] = ">";
        d["&lt;"] = "<";
        d["&frasl;"] = "/";
        string ans = "";
        int i = 0, n = text.size();
        while (i < n) {
            bool find = false;
            for (int l = 1; l < 8; ++l) {
                int j = i + l;
                if (j <= n) {
                    string t = text.substr(i, l);
                    if (d.count(t)) {
                        ans += d[t];
                        i = j;
                        find = true;
                        break;
                    }
                }
            }
            if (!find) ans += text[i++];
        }
        return ans;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
