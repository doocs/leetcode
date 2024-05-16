---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1087.Brace%20Expansion/README.md
rating: 1480
source: 第 2 场双周赛 Q3
tags:
    - 广度优先搜索
    - 字符串
    - 回溯
---

# [1087. 花括号展开 🔒](https://leetcode.cn/problems/brace-expansion)

[English Version](/solution/1000-1099/1087.Brace%20Expansion/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个表示单词列表的字符串&nbsp;<code>s</code>&nbsp;。单词中的每个字母都有一个或多个选项。</p>

<ul>
	<li>如果有一个选项，则字母按原样表示。</li>
	<li>如果有多个选项，则用大括号分隔选项。例如,<meta charset="UTF-8" />&nbsp;&nbsp;<code>"{a,b,c}"</code>&nbsp; 表示选项<meta charset="UTF-8" />&nbsp;&nbsp;<code>["a", "b", "c"]</code>&nbsp; 。</li>
</ul>

<p>例如，如果<meta charset="UTF-8" />&nbsp;&nbsp;<code>s = "a{b,c}"</code>&nbsp; ，第一个字符总是 <code>'a'</code> ，但第二个字符可以是 <code>'b'</code> 或 <code>'c'</code> 。原来的列表是<meta charset="UTF-8" />&nbsp;<code>["ab", "ac"]</code>&nbsp;。</p>

<p>请你 <strong>按字典顺序</strong> ，返回所有以这种方式形成的单词。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "{a,b}c{d,e}f"
<strong>输出：</strong>["acdf","acef","bcdf","bcef"]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "abcd"
<strong>输出：</strong>["abcd"]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= S.length &lt;= 50</code></li>
	<li><meta charset="UTF-8" /><code>s</code>&nbsp;由括号 <code>'{}'</code>&nbsp;, <code>','</code> 和小写英文字母组成。</li>
	<li><meta charset="UTF-8" /><code>s</code>&nbsp;保证是一个有效的输入。</li>
	<li>没有嵌套的大括号。</li>
	<li>在一对连续的左括号和右括号内的所有字符都是不同的。</li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

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

<!-- tabs:end -->

<!-- end -->
