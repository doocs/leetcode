# [150. 逆波兰表达式求值](https://leetcode-cn.com/problems/evaluate-reverse-polish-notation)

[English Version](/solution/0100-0199/0150.Evaluate%20Reverse%20Polish%20Notation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>根据<a href="https://baike.baidu.com/item/%E9%80%86%E6%B3%A2%E5%85%B0%E5%BC%8F/128437" target="_blank">逆波兰表示法</a>，求表达式的值。</p>

<p>有效的运算符包括&nbsp;<code>+</code>,&nbsp;<code>-</code>,&nbsp;<code>*</code>,&nbsp;<code>/</code>&nbsp;。每个运算对象可以是整数，也可以是另一个逆波兰表达式。</p>

<p><strong>说明：</strong></p>

<ul>
	<li>整数除法只保留整数部分。</li>
	<li>给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。</li>
</ul>

<p><strong>示例&nbsp;1：</strong></p>

<pre><strong>输入:</strong> [&quot;2&quot;, &quot;1&quot;, &quot;+&quot;, &quot;3&quot;, &quot;*&quot;]
<strong>输出:</strong> 9
<strong>解释:</strong> ((2 + 1) * 3) = 9
</pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre><strong>输入:</strong> [&quot;4&quot;, &quot;13&quot;, &quot;5&quot;, &quot;/&quot;, &quot;+&quot;]
<strong>输出:</strong> 6
<strong>解释:</strong> (4 + (13 / 5)) = 6
</pre>

<p><strong>示例&nbsp;3：</strong></p>

<pre><strong>输入:</strong> [&quot;10&quot;, &quot;6&quot;, &quot;9&quot;, &quot;3&quot;, &quot;+&quot;, &quot;-11&quot;, &quot;*&quot;, &quot;/&quot;, &quot;*&quot;, &quot;17&quot;, &quot;+&quot;, &quot;5&quot;, &quot;+&quot;]
<strong>输出:</strong> 22
<strong>解释:</strong> 
  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

栈实现。

遍历数组，遇到数字则压入栈中，遇到运算符号，则从栈中弹出右、左操作数，运算过后，将结果压入栈中。

遍历结束后，返回栈中的唯一元素。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
import operator

class Solution:
    def evalRPN(self, tokens: List[str]) -> int:
        opt = {
            "+": operator.add,
            "-": operator.sub,
            "*": operator.mul,
            "/": operator.truediv
        }
        s = []
        for token in tokens:
            if token in opt:
                s.append(int(opt[token](s.pop(-2), s.pop(-1))))
            else:
                s.append(int(token))
        return s[0]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int evalRPN(String[] tokens) {
        Deque<Integer> s = new ArrayDeque<>();
        int left, right;
        for (String token : tokens) {
            switch(token) {
            case "+":
                right = s.pop();
                left = s.pop();
                s.push(left + right);
                break;
            case "-":
                right = s.pop();
                left = s.pop();
                s.push(left - right);
                break;
            case "*":
                right = s.pop();
                left = s.pop();
                s.push(left * right);
                break;
            case "/":
                right = s.pop();
                left = s.pop();
                s.push(left / right);
                break;
            default:
                s.push(Integer.valueOf(token));
            }
        }
        return s.pop();
    }
}
```

### **...**

```

```

<!-- tabs:end -->
