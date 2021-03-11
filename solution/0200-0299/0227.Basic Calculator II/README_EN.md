# [227. Basic Calculator II](https://leetcode.com/problems/basic-calculator-ii)

[中文文档](/solution/0200-0299/0227.Basic%20Calculator%20II/README.md)

## Description

<p>Implement a basic calculator to evaluate a simple expression string.</p>

<p>The expression string contains only <b>non-negative</b> integers, <code>+</code>, <code>-</code>, <code>*</code>, <code>/</code> operators and empty spaces <code> </code>. The integer division should truncate toward zero.</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong>&quot;3+2*2&quot;

<strong>Output:</strong> 7

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> &quot; 3/2 &quot;

<strong>Output:</strong> 1</pre>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input:</strong> &quot; 3+5 / 2 &quot;

<strong>Output:</strong> 5

</pre>

<p><b>Note:</b></p>

<ul>
    <li>You may assume that the given expression is always valid.</li>
    <li><b>Do not</b> use the <code>eval</code> built-in library function.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def calculate(self, s: str) -> int:
        num, n = 0, len(s)
        pre_sign = '+'
        stack = []
        for i in range(n):
            if s[i].isdigit():
                num = num * 10 + int(s[i])
            if i == n - 1 or (not s[i].isdigit() and s[i] != ' '):
                if pre_sign == '+':
                    stack.append(num)
                elif pre_sign == '-':
                    stack.append(-num)
                elif pre_sign == '*':
                    stack.append(stack.pop() * num)
                else:
                    stack.append(int(stack.pop() / num))
                pre_sign = s[i]
                num = 0
        res = 0
        while stack:
            res += stack.pop()
        return res
```

### **Java**

```java
class Solution {
    public int calculate(String s) {
        int num = 0;
        char preSign = '+';
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0, n = s.length(); i < n; ++i) {
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + (s.charAt(i) - '0');
            }
            if (i == n - 1 || (!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ')) {
                switch (preSign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    case '/':
                        stack.push(stack.pop() / num);
                        break;
                }
                preSign = s.charAt(i);
                num = 0;
            }
        }

        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
