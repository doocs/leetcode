# [320. Generalized Abbreviation](https://leetcode.com/problems/generalized-abbreviation)

[中文文档](/solution/0300-0399/0320.Generalized%20Abbreviation/README.md)

## Description

<p>A word&#39;s <strong>generalized abbreviation</strong> can be constructed by taking any number of <strong>non-overlapping</strong> and <strong>non-adjacent</strong> <span data-keyword="substring-nonempty">substrings</span> and replacing them with their respective lengths.</p>

<ul>
	<li>For example, <code>&quot;abcde&quot;</code> can be abbreviated into:
    <ul>
    	<li><code>&quot;a3e&quot;</code> (<code>&quot;bcd&quot;</code> turned into <code>&quot;3&quot;</code>)</li>
    	<li><code>&quot;1bcd1&quot;</code> (<code>&quot;a&quot;</code> and <code>&quot;e&quot;</code> both turned into <code>&quot;1&quot;</code>)</li>
    	<li><code>&quot;5&quot;</code> (<code>&quot;abcde&quot;</code> turned into <code>&quot;5&quot;</code>)</li>
    	<li><code>&quot;abcde&quot;</code> (no substrings replaced)</li>
    </ul>
    </li>
    <li>However, these abbreviations are <strong>invalid</strong>:
    <ul>
    	<li><code>&quot;23&quot;</code> (<code>&quot;ab&quot;</code> turned into <code>&quot;2&quot;</code> and <code>&quot;cde&quot;</code> turned into <code>&quot;3&quot;</code>) is invalid as the substrings chosen are adjacent.</li>
    	<li><code>&quot;22de&quot;</code> (<code>&quot;ab&quot;</code> turned into <code>&quot;2&quot;</code> and <code>&quot;bc&quot;</code> turned into <code>&quot;2&quot;</code>) is invalid as the substring chosen overlap.</li>
    </ul>
    </li>
</ul>

<p>Given a string <code>word</code>, return <em>a list of all the possible <strong>generalized abbreviations</strong> of</em> <code>word</code>. Return the answer in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> word = "word"
<strong>Output:</strong> ["4","3d","2r1","2rd","1o2","1o1d","1or1","1ord","w3","w2d","w1r1","w1rd","wo2","wo1d","wor1","word"]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> word = "a"
<strong>Output:</strong> ["1","a"]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 15</code></li>
	<li><code>word</code> consists of only lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def generateAbbreviations(self, word: str) -> List[str]:
        def dfs(s, t):
            if not s:
                ans.append(''.join(t))
                return
            for i in range(1, len(s) + 1):
                t.append(str(i))
                if i < len(s):
                    t.append(s[i])
                    dfs(s[i + 1 :], t)
                    t.pop()
                else:
                    dfs(s[i:], t)
                t.pop()

            t.append(s[0])
            dfs(s[1:], t)
            t.pop()

        ans = []
        dfs(word, [])
        return ans
```

### **Java**

```java
class Solution {
    private List<String> ans;

    public List<String> generateAbbreviations(String word) {
        ans = new ArrayList<>();
        List<String> t = new ArrayList<>();
        dfs(word, t);
        return ans;
    }

    private void dfs(String s, List<String> t) {
        if ("".equals(s)) {
            ans.add(String.join("", t));
            return;
        }
        for (int i = 1; i < s.length() + 1; ++i) {
            t.add(i + "");
            if (i < s.length()) {
                t.add(String.valueOf(s.charAt(i)));
                dfs(s.substring(i + 1), t);
                t.remove(t.size() - 1);
            } else {
                dfs(s.substring(i), t);
            }
            t.remove(t.size() - 1);
        }
        t.add(String.valueOf(s.charAt(0)));
        dfs(s.substring(1), t);
        t.remove(t.size() - 1);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
