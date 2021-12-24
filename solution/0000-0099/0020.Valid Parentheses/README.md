# [20. 有效的括号](https://leetcode-cn.com/problems/valid-parentheses)

[English Version](/solution/0000-0099/0020.Valid%20Parentheses/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个只包括 <code>'('</code>，<code>')'</code>，<code>'{'</code>，<code>'}'</code>，<code>'['</code>，<code>']'</code> 的字符串 <code>s</code> ，判断字符串是否有效。</p>

<p>有效字符串需满足：</p>

<ol>
	<li>左括号必须用相同类型的右括号闭合。</li>
	<li>左括号必须以正确的顺序闭合。</li>
</ol>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "()"
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "()[]{}"
<strong>输出：</strong>true
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "(]"
<strong>输出：</strong>false
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>s = "([)]"
<strong>输出：</strong>false
</pre>

<p><strong>示例 5：</strong></p>

<pre>
<strong>输入：</strong>s = "{[]}"
<strong>输出：</strong>true</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length <= 10<sup>4</sup></code></li>
	<li><code>s</code> 仅由括号 <code>'()[]{}'</code> 组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

栈实现。

遍历括号字符串，遇到左括号时，将左括号压入栈中；遇到右括号时，弹出栈顶元素（栈若为空，直接返回 false），判断是否是相同类型的括号。若不匹配，直接返回 false。

遍历结束，栈若为空，说明括号字符串有效。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isValid(self, s: str) -> bool:
        q = []
        parentheses = {'()', '[]', '{}'}
        for ch in s:
            if ch in '([{':
                q.append(ch)
            elif not q or q.pop() + ch not in parentheses:
                return False
        return not q
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isValid(String s) {
        char[] chars = s.toCharArray();
        Deque<Character> q = new ArrayDeque<>();
        for (char ch : chars) {
            boolean left = ch == '(' || ch == '[' || ch == '{';
            if (left) q.push(ch);
            else if (q.isEmpty() || !match(q.pop(), ch)) return false;
        }
        return q.isEmpty();
    }

    private boolean match(char l, char r) {
        return (l == '(' && r == ')') || (l == '{' && r == '}') || (l == '[' && r == ']');
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isValid(string s) {
        stack<char> q;
        for (int i = 0, n = s.length(); i < n; ++i) {
            if (s[i] == '{' || s[i] == '[' || s[i] == '(') q.push(s[i]);
            else if (q.empty() || !match(q.top(), s[i])) return false;
            else q.pop();
        }
        return q.empty();
    }
private:
    bool match(char l, char r) {
        return (l == '(' && r == ')') || (l == '[' && r == ']') || (l == '{' && r == '}');
    }
};
```

### **...**

```

```

<!-- tabs:end -->
