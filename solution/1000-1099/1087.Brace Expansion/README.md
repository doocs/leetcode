# [1087. 花括号展开](https://leetcode-cn.com/problems/brace-expansion)

[English Version](/solution/1000-1099/1087.Brace%20Expansion/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>我们用一个特殊的字符串&nbsp;<code>S</code>&nbsp;来表示一份单词列表，之所以能展开成为一个列表，是因为这个字符串&nbsp;<code>S</code>&nbsp;中存在一个叫做「选项」的概念：</p>

<p>单词中的每个字母可能只有一个选项或存在多个备选项。如果只有一个选项，那么该字母按原样表示。</p>

<p>如果存在多个选项，就会以花括号包裹来表示这些选项（使它们与其他字母分隔开），例如 <code>&quot;{a,b,c}&quot;</code> 表示&nbsp;<code>[&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]</code>。</p>

<p><strong>例子：</strong><code>&quot;{a,b,c}d{e,f}&quot;</code>&nbsp;可以表示单词列表&nbsp;<code>[&quot;ade&quot;, &quot;adf&quot;, &quot;bde&quot;, &quot;bdf&quot;, &quot;cde&quot;, &quot;cdf&quot;]</code>。</p>

<p>请你按字典顺序，返回所有以这种方式形成的单词。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>&quot;{a,b}c{d,e}f&quot;
<strong>输出：</strong>[&quot;acdf&quot;,&quot;acef&quot;,&quot;bcdf&quot;,&quot;bcef&quot;]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>&quot;abcd&quot;
<strong>输出：</strong>[&quot;abcd&quot;]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= S.length &lt;= 50</code></li>
	<li>你可以假设题目中不存在嵌套的花括号</li>
	<li>在一对连续的花括号（开花括号与闭花括号）之间的所有字母都不会相同</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

先将字符串 s 进行 convert 转换，比如 `"{a,b}{z,x,y}"` 转换为 `[['a', 'b'], ['z', 'x', 'y']]`，然后利用 DFS 回溯获取每一个单词，放到 ans 中，最后对 ans 进行排序并返回即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def expand(self, s: str) -> List[str]:
        def convert(s):
            if not s:
                return
            if s[0] == '{':
                j = s.find('}')
                items.append(s[1: j].split(','))
                convert(s[j + 1:])
            else:
                j = s.find('{')

                if j != -1:
                    items.append(s[: j].split(','))
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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
