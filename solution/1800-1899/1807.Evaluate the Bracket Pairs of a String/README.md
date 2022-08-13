# [1807. 替换字符串中的括号内容](https://leetcode.cn/problems/evaluate-the-bracket-pairs-of-a-string)

[English Version](/solution/1800-1899/1807.Evaluate%20the%20Bracket%20Pairs%20of%20a%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串&nbsp;<code>s</code>&nbsp;，它包含一些括号对，每个括号中包含一个 <strong>非空</strong>&nbsp;的键。</p>

<ul>
	<li>比方说，字符串&nbsp;<code>"(name)is(age)yearsold"</code>&nbsp;中，有&nbsp;<strong>两个</strong>&nbsp;括号对，分别包含键&nbsp;<code>"name"</code> 和&nbsp;<code>"age"</code>&nbsp;。</li>
</ul>

<p>你知道许多键对应的值，这些关系由二维字符串数组&nbsp;<code>knowledge</code>&nbsp;表示，其中&nbsp;<code>knowledge[i] = [key<sub>i</sub>, value<sub>i</sub>]</code>&nbsp;，表示键&nbsp;<code>key<sub>i</sub></code>&nbsp;对应的值为&nbsp;<code>value<sub>i</sub></code><sub>&nbsp;</sub>。</p>

<p>你需要替换 <strong>所有</strong>&nbsp;的括号对。当你替换一个括号对，且它包含的键为&nbsp;<code>key<sub>i</sub></code>&nbsp;时，你需要：</p>

<ul>
	<li>将&nbsp;<code>key<sub>i</sub></code>&nbsp;和括号用对应的值&nbsp;<code>value<sub>i</sub></code>&nbsp;替换。</li>
	<li>如果从 <code>knowledge</code>&nbsp;中无法得知某个键对应的值，你需要将&nbsp;<code>key<sub>i</sub></code>&nbsp;和括号用问号&nbsp;<code>"?"</code>&nbsp;替换（不需要引号）。</li>
</ul>

<p><code>knowledge</code>&nbsp;中每个键最多只会出现一次。<code>s</code>&nbsp;中不会有嵌套的括号。</p>

<p>请你返回替换 <strong>所有</strong>&nbsp;括号对后的结果字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>s = "(name)is(age)yearsold", knowledge = [["name","bob"],["age","two"]]
<b>输出：</b>"bobistwoyearsold"
<strong>解释：</strong>
键 "name" 对应的值为 "bob" ，所以将 "(name)" 替换为 "bob" 。
键 "age" 对应的值为 "two" ，所以将 "(age)" 替换为 "two" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>s = "hi(name)", knowledge = [["a","b"]]
<b>输出：</b>"hi?"
<b>解释：</b>由于不知道键 "name" 对应的值，所以用 "?" 替换 "(name)" 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>s = "(a)(a)(a)aaa", knowledge = [["a","yes"]]
<b>输出：</b>"yesyesyesaaa"
<b>解释：</b>相同的键在 s 中可能会出现多次。
键 "a" 对应的值为 "yes" ，所以将所有的 "(a)" 替换为 "yes" 。
注意，不在括号里的 "a" 不需要被替换。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= knowledge.length &lt;= 10<sup>5</sup></code></li>
	<li><code>knowledge[i].length == 2</code></li>
	<li><code>1 &lt;= key<sub>i</sub>.length, value<sub>i</sub>.length &lt;= 10</code></li>
	<li><code>s</code>&nbsp;只包含小写英文字母和圆括号&nbsp;<code>'('</code>&nbsp;和&nbsp;<code>')'</code>&nbsp;。</li>
	<li><code>s</code>&nbsp;中每一个左圆括号&nbsp;<code>'('</code>&nbsp;都有对应的右圆括号&nbsp;<code>')'</code>&nbsp;。</li>
	<li><code>s</code>&nbsp;中每对括号内的键都不会为空。</li>
	<li><code>s</code>&nbsp;中不会有嵌套括号对。</li>
	<li><code>key<sub>i</sub></code>&nbsp;和&nbsp;<code>value<sub>i</sub></code>&nbsp;只包含小写英文字母。</li>
	<li><code>knowledge</code>&nbsp;中的&nbsp;<code>key<sub>i</sub></code>&nbsp;不会重复。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

先将 `knowledge` 转为哈希字典。

然后遍历字符串每个字符 `s[i]`：

-   若 `s[i] == '('`，说明遇到了左括号，因此要找到右括号 `)` 的位置，然后截取括号间的子串作为 `key`，在哈希字典中查找 `key` 对应的 `value`，有则追加 `value` 到结果中，没有则追加 `?`，然后指针跳到右括号位置的下一个位置；
-   若 `s[i]` 是其他字符，则正常追加即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def evaluate(self, s: str, knowledge: List[List[str]]) -> str:
        def find_right_bracket(s, start, end):
            for i in range(start, end):
                if s[i] == ')':
                    return i

        knowledge_dict = {item[0]: item[1] for item in knowledge}
        res, n = [], len(s)
        i = 0
        while i < n:
            if s[i] == '(':
                right_bracket_pos = find_right_bracket(s, i + 1, n)
                key = s[i + 1 : right_bracket_pos]
                res.append(knowledge_dict.get(key, '?'))
                i = right_bracket_pos + 1
            else:
                res.append(s[i])
                i += 1
        return ''.join(res)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> knowledgeDict = new HashMap<>();
        for (List<String> item : knowledge) {
            knowledgeDict.put(item.get(0), item.get(1));
        }
        StringBuilder res = new StringBuilder();
        int i = 0, n = s.length();
        while (i < n) {
            if (s.charAt(i) == '(') {
                int rightBracketPos = findRightBracket(s, i + 1, n);
                String key = s.substring(i + 1, rightBracketPos);
                res.append(knowledgeDict.getOrDefault(key, "?"));
                i = rightBracketPos + 1;
            } else {
                res.append(s.charAt(i));
                i += 1;
            }
        }
        return res.toString();
    }

    private int findRightBracket(String s, int start, int end) {
        for (int i =  start; i < end; ++i) {
            if (s.charAt(i) == ')') {
                return i;
            }
        }
        return -1;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
