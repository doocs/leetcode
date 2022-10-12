# [1087. Brace Expansion](https://leetcode.com/problems/brace-expansion)

[中文文档](/solution/1000-1099/1087.Brace%20Expansion/README.md)

## Description

<p>You are given a string <code>s</code> representing a list of words. Each letter in the word has one or more options.</p>

<ul>
	<li>If there is one option, the letter is represented as is.</li>
	<li>If there is more than one option, then curly braces delimit the options. For example, <code>&quot;{a,b,c}&quot;</code> represents options <code>[&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]</code>.</li>
</ul>

<p>For example, if <code>s = &quot;a{b,c}&quot;</code>, the first character is always <code>&#39;a&#39;</code>, but the second character can be <code>&#39;b&#39;</code> or <code>&#39;c&#39;</code>. The original list is <code>[&quot;ab&quot;, &quot;ac&quot;]</code>.</p>

<p>Return all words that can be formed in this manner, <strong>sorted</strong> in lexicographical order.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> s = "{a,b}c{d,e}f"
<strong>Output:</strong> ["acdf","acef","bcdf","bcef"]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> s = "abcd"
<strong>Output:</strong> ["abcd"]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 50</code></li>
	<li><code>s</code> consists of curly brackets <code>&#39;{}&#39;</code>, commas&nbsp;<code>&#39;,&#39;</code>, and lowercase English letters.</li>
	<li><code>s</code> is guaranteed to be a valid input.</li>
	<li>There are no nested curly brackets.</li>
	<li>All characters inside a pair of consecutive opening and ending curly brackets are different.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def expand(self, s: str) -> List[str]:
        def convert(s):
            if not s:
                return
            if s[0] == '{':
                j = s.find('}')
                items.append(s[1:j].split(','))
                convert(s[j + 1 :])
            else:
                j = s.find('{')
                if j != -1:
                    items.append(s[:j].split(','))
                    convert(s[j:])
                else:
                    items.append(s.split(','))

        def dfs(i, t):
            if i == len(items):
                ans.append(''.join(t))
                return
            for c in items[i]:
                t.append(c)
                dfs(i + 1, t)
                t.pop()

        items = []
        convert(s)
        ans = []
        dfs(0, [])
        ans.sort()
        return ans
```

### **Java**

```java
class Solution {
    private List<String> ans;
    private List<String[]> items;

    public String[] expand(String s) {
        ans = new ArrayList<>();
        items = new ArrayList<>();
        convert(s);
        dfs(0, new ArrayList<>());
        Collections.sort(ans);
        return ans.toArray(new String[0]);
    }

    private void convert(String s) {
        if ("".equals(s)) {
            return;
        }
        if (s.charAt(0) == '{') {
            int j = s.indexOf("}");
            items.add(s.substring(1, j).split(","));
            convert(s.substring(j + 1));
        } else {
            int j = s.indexOf("{");
            if (j != -1) {
                items.add(s.substring(0, j).split(","));
                convert(s.substring(j));
            } else {
                items.add(s.split(","));
            }
        }
    }

    private void dfs(int i, List<String> t) {
        if (i == items.size()) {
            ans.add(String.join("", t));
            return;
        }
        for (String c : items.get(i)) {
            t.add(c);
            dfs(i + 1, t);
            t.remove(t.size() - 1);
        }
    }
}
```

### **...**

```

```

<!-- tabs:end -->
