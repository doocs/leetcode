---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0772.Basic%20Calculator%20III/README_EN.md
tags:
    - Stack
    - Recursion
    - Math
    - String
---

<!-- problem:start -->

# [772. Basic Calculator III 🔒](https://leetcode.com/problems/basic-calculator-iii)

[中文文档](/solution/0700-0799/0772.Basic%20Calculator%20III/README.md)

## Description

<!-- description:start -->

<p>Implement a basic calculator to evaluate a simple expression string.</p>

<p>The expression string contains only non-negative integers, <code>&#39;+&#39;</code>, <code>&#39;-&#39;</code>, <code>&#39;*&#39;</code>, <code>&#39;/&#39;</code> operators, and open <code>&#39;(&#39;</code> and closing parentheses <code>&#39;)&#39;</code>. The integer division should <strong>truncate toward zero</strong>.</p>

<p>You may assume that the given expression is always valid. All intermediate results will be in the range of <code>[-2<sup>31</sup>, 2<sup>31</sup> - 1]</code>.</p>

<p><strong>Note:</strong> You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as <code>eval()</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;1+1&quot;
<strong>Output:</strong> 2
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;6-4/2&quot;
<strong>Output:</strong> 4
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;2*(5+5*2)/3+(6/2+8)&quot;
<strong>Output:</strong> 21
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code> consists of digits, <code>&#39;+&#39;</code>, <code>&#39;-&#39;</code>, <code>&#39;*&#39;</code>, <code>&#39;/&#39;</code>, <code>&#39;(&#39;</code>,&nbsp;and&nbsp;<code>&#39;)&#39;</code>.</li>
	<li><code>s</code> is a <strong>valid</strong> expression.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def calculate(self, s: str) -> int:
        def dfs(q):
            num, sign, stk = 0, "+", []
            while q:
                c = q.popleft()
                if c.isdigit():
                    num = num * 10 + int(c)
                if c == "(":
                    num = dfs(q)
                if c in "+-*/)" or not q:
                    match sign:
                        case "+":
                            stk.append(num)
                        case "-":
                            stk.append(-num)
                        case "*":
                            stk.append(stk.pop() * num)
                        case "/":
                            stk.append(int(stk.pop() / num))
                    num, sign = 0, c
                if c == ")":
                    break
            return sum(stk)

        return dfs(deque(s))
```

#### Java

```java

```

#### C++

```cpp
class Solution {
public:
    // Define an operation function that performs mathematical operations based on the operator
    int operate(int b, char ch, int a) {
        // Note the order of ab
        switch (ch) {
        case '+':
            return a + b; // Addition
        case '-':
            return a - b; // Subtraction
        case '*':
            return a * b; // Multiplication
        case '/':
            return a / b; // Division
        default:
            break;
        }
        return 0; // Default return 0, handle invalid operators
    }

    // Calculate the value of the string expression
    int calculate(string s) {
        int preority[250]; // Operator precedence array
        preority['+'] = 1;
        preority['-'] = 1;
        preority['*'] = 2;
        preority['/'] = 2;
        preority['('] = 0;
        preority[')'] = 0;

        stack<char> op; // Operator stack
        stack<int> num; // Operand stack
        int stringsize = s.size(); // Length of the string
        int i = 0;
        char ch;

        // Traverse the string
        for (; i < stringsize; i++) {
            ch = s[i];
            if (ch == ' ') {
                continue; // Skip spaces
            }
            if (ch >= '0' && ch <= '9') {
                int realnum = ch - '0'; // Convert character to number
                // Handle multi-digit numbers
                while (s[i + 1] >= '0' && s[i + 1] <= '9') {
                    i++;
                    realnum *= 10;
                    realnum += s[i] - '0';
                }
                num.push(realnum); // Push the number onto the stack
            } else {
                // Handle operators
                if (op.empty() || ch == '(' || preority[ch] > preority[op.top()]) {
                    // Special case, handle the first character being '-' or '+'
                    if (num.empty() && (ch == '-' || ch == '+')) {
                        num.push(0);
                    }
                    op.push(ch); // Push the operator onto the stack
                    // Handle expressions inside parentheses
                    if (ch == '(') {
                        int j = i;
                        while (j + 1 < stringsize) {
                            // Preprocess the first operator inside the parentheses
                            if (s[j + 1] == '-' || s[j + 1] == '+') {
                                num.push(0);
                            }
                            if (s[j + 1] != ' ') {
                                break;
                            }
                            j++;
                        }
                    }
                } else if (ch == ')') {
                    // Handle right parentheses
                    char ch2 = ')';
                    ch2 = op.top();
                    op.pop();
                    while (ch2 != '(') {
                        int a = num.top();
                        num.pop();
                        int b = num.top();
                        num.pop();
                        num.push(operate(a, ch2, b)); // Calculate and push the result
                        ch2 = op.top();
                        op.pop();
                    }
                } else if (preority[ch] <= preority[op.top()]) {
                    // Handle cases where the precedence is less than or equal to the top of the stack
                    char ch2;
                    ch2 = op.top();
                    while (!op.empty() && preority[ch] <= preority[op.top()] && ch2 != '(') {
                        op.pop();
                        int a = num.top();
                        num.pop();
                        int b = num.top();
                        num.pop();
                        num.push(operate(a, ch2, b)); // Calculate and push the result
                        if (!op.empty()) {
                            ch2 = op.top();
                        } else {
                            break;
                        }
                    }
                    op.push(ch); // Push the current operator onto the stack
                }
            }
        }

        // Handle the remaining expressions in the stack
        while (!op.empty()) {
            ch = op.top();
            op.pop();
            int a = num.top();
            num.pop();
            int b = num.top();
            num.pop();
            num.push(operate(a, ch, b)); // Calculate and push the result
        }

        return num.top(); // Return the final result
    }
};
```

#### Go

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
