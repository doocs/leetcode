# [20. 有效的括号](https://leetcode-cn.com/problems/valid-parentheses)

[English Version](/solution/0000-0099/0020.Valid%20Parentheses/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定一个只包括 <code>&#39;(&#39;</code>，<code>&#39;)&#39;</code>，<code>&#39;{&#39;</code>，<code>&#39;}&#39;</code>，<code>&#39;[&#39;</code>，<code>&#39;]&#39;</code>&nbsp;的字符串，判断字符串是否有效。</p>

<p>有效字符串需满足：</p>

<ol>
	<li>左括号必须用相同类型的右括号闭合。</li>
	<li>左括号必须以正确的顺序闭合。</li>
</ol>

<p>注意空字符串可被认为是有效字符串。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> &quot;()&quot;
<strong>输出:</strong> true
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre><strong>输入:</strong> &quot;()[]{}&quot;
<strong>输出:</strong> true
</pre>

<p><strong>示例&nbsp;3:</strong></p>

<pre><strong>输入:</strong> &quot;(]&quot;
<strong>输出:</strong> false
</pre>

<p><strong>示例&nbsp;4:</strong></p>

<pre><strong>输入:</strong> &quot;([)]&quot;
<strong>输出:</strong> false
</pre>

<p><strong>示例&nbsp;5:</strong></p>

<pre><strong>输入:</strong> &quot;{[]}&quot;
<strong>输出:</strong> true</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isValid(self, s: str) -> bool:
        if not s:
            return True
        helper = []
        for c in s:
            if c in '([{':
                helper.append(c)
            else:
                if len(helper) == 0 or (helper.pop() + c) not in ["()", "[]", "{}"]:
                    return False
        return len(helper) == 0
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isValid(String s) {
        if (s == null || s == "") {
            return true;
        }
        char[] chars = s.toCharArray();
        Stack<Character> helper = new Stack<>();
        for (char c : chars) {
            boolean isLeft = c == '(' || c == '[' || c == '{';
            if (isLeft) {
                helper.push(c);
            } else {
                if (helper.isEmpty() || !match(helper.pop(), c)) {
                    return false;
                }
            }
        }
        return helper.isEmpty();
    }

    private boolean match(char left, char right) {
        return (left == '(' && right == ')')
            || (left == '[' && right == ']')
            || (left == '{' && right == '}');
    }
}
```

### **...**

```

```

<!-- tabs:end -->
