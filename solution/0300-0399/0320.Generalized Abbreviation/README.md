# [320. 列举单词的全部缩写](https://leetcode.cn/problems/generalized-abbreviation)

[English Version](/solution/0300-0399/0320.Generalized%20Abbreviation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>单词的 <strong>广义缩写词</strong> 可以通过下述步骤构造：先取任意数量的 <strong>不重叠、不相邻</strong> 的子字符串，再用它们各自的长度进行替换。</p>

<ul>
	<li>例如，<code>"abcde"</code> 可以缩写为：
    <ul>
    	<li><code>"a3e"</code>（<code>"bcd"</code> 变为 <code>"3"</code> ）</li>
    	<li><code>"1bcd1"</code>（<code>"a"</code> 和 <code>"e"</code> 都变为 <code>"1"</code>）<meta charset="UTF-8" /></li>
    	<li><code>"5"</code>&nbsp;(<code>"abcde"</code>&nbsp;变为&nbsp;<code>"5"</code>)</li>
    	<li><code>"abcde"</code>&nbsp;(没有子字符串被代替)</li>
    </ul>
    </li>
    <li>然而，这些缩写是 <strong>无效的</strong> ：
    <ul>
    	<li><code>"23"</code>（<code>"ab"</code> 变为 <code>"2"</code> ，<code>"cde"</code> 变为 <code>"3"</code> ）是无效的，因为被选择的字符串是相邻的</li>
    	<li><meta charset="UTF-8" /><code>"22de"</code>&nbsp;(<code>"ab"</code> 变为&nbsp;<code>"2"</code>&nbsp;，&nbsp;<code>"bc"</code>&nbsp;变为&nbsp;<code>"2"</code>) &nbsp;是无效的，因为被选择的字符串是重叠的</li>
    </ul>
    </li>
</ul>

<p>给你一个字符串&nbsp;<code>word</code> ，返回&nbsp;<em>一个由</em>&nbsp;<code>word</code> 的<em>所有可能 <strong>广义缩写词</strong> 组成的列表</em>&nbsp;。按 <strong>任意顺序</strong> 返回答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>word = "word"
<strong>输出：</strong>["4","3d","2r1","2rd","1o2","1o1d","1or1","1ord","w3","w2d","w1r1","w1rd","wo2","wo1d","wor1","word"]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>word = "a"
<strong>输出：</strong>["1","a"]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 15</code></li>
	<li><code>word</code> 仅由小写英文字母组成</li>
</ul>

## 解法

回溯法。

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
