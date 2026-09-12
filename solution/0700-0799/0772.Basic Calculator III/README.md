---
comments: true
difficulty: 困难
tags:
    - 栈
    - 递归
    - 数学
    - 字符串
---

<!-- problem:start -->

# [772. 基本计算器 III 🔒](https://leetcode.cn/problems/basic-calculator-iii)

[English Version](/solution/0700-0799/0772.Basic%20Calculator%20III/README_EN.md)

## 题目描述

<!-- description:start -->

<p>实现一个基本的计算器来计算简单的表达式字符串。</p>

<p>表达式字符串只包含非负整数，算符 <code>+</code>、<code>-</code>、<code>*</code>、<code>/</code> ，左括号 <code>(</code> 和右括号 <code>)</code> 。整数除法需要 <strong>向下截断</strong> 。</p>

<p>你可以假定给定的表达式总是有效的。所有的中间结果的范围均满足 <code>[-2<sup>31</sup>, 2<sup>31</sup> - 1]</code> 。</p>

<p><strong>注意：</strong>你不能使用任何将字符串作为表达式求值的内置函数，比如 <code>eval()</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "1+1"
<strong>输出：</strong>2
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "6-4/2"
<strong>输出：</strong>4
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "2*(5+5*2)/3+(6/2+8)"
<strong>输出：</strong>21
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code> 由整数、<code>'+'</code>、<code>'-'</code>、<code>'*'</code>、<code>'/'</code>、<code>'('</code> 和 <code>')'</code> 组成</li>
	<li><code>s</code> 是一个 <strong>有效的</strong> 表达式</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 表达式含加减乘除与括号，长度 $10^4$，需要按优先级求值。乘除应立即与栈顶结合，加减只把带符号的数入栈，最后求和。
>
> 括号相当于子表达式：遇到 `(` 递归算完到 `)`，返回值当作当前数字。队列顺序消费字符，避免反复切片。
>
> $\textit{sign}$ 记下上一个运算符，在运算符或结束时把 $\textit{num}$ 按该运算压栈。整数除法向零取整。

<!-- thinking:end -->

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
// 逆波兰表示法求解
class Solution {
public:
    // 定义一个操作函数，根据操作符进行数学运算
    int operate(int b, char ch, int a) {
        // 注意ab顺序
        switch (ch) {
        case '+':
            return a + b; // 加法
        case '-':
            return a - b; // 减法
        case '*':
            return a * b; // 乘法
        case '/':
            return a / b; // 除法
        default:
            break;
        }
        return 0; // 默认返回0，处理无效操作符
    }

    // 计算字符串表达式的值
    int calculate(string s) {
        int preority[250]; // 操作符优先级数组
        preority['+'] = 1;
        preority['-'] = 1;
        preority['*'] = 2;
        preority['/'] = 2;
        preority['('] = 0;
        preority[')'] = 0;

        stack<char> op; // 操作符栈
        stack<int> num; // 操作数栈
        int stringsize = s.size(); // 字符串长度
        int i = 0;
        char ch;

        // 遍历字符串
        for (; i < stringsize; i++) {
            ch = s[i];
            if (ch == ' ') {
                continue; // 跳过空格
            }
            if (ch >= '0' && ch <= '9') {
                int realnum = ch - '0'; // 将字符转换为数字
                // 处理多位数字
                while (s[i + 1] >= '0' && s[i + 1] <= '9') {
                    i++;
                    realnum *= 10;
                    realnum += s[i] - '0';
                }
                num.push(realnum); // 将数字压入栈
            } else {
                // 处理操作符
                if (op.empty() || ch == '(' || preority[ch] > preority[op.top()]) {
                    // 特殊情况，处理首个字符为'-'或'+'的情况
                    if (num.empty() && (ch == '-' || ch == '+')) {
                        num.push(0);
                    }
                    op.push(ch); // 将操作符压入栈
                    // 处理括号内的表达式
                    if (ch == '(') {
                        int j = i;
                        while (j + 1 < stringsize) {
                            // 预处理括号内的首个操作符
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
                    // 处理右括号
                    char ch2 = ')';
                    ch2 = op.top();
                    op.pop();
                    while (ch2 != '(') {
                        int a = num.top();
                        num.pop();
                        int b = num.top();
                        num.pop();
                        num.push(operate(a, ch2, b)); // 计算并压入结果
                        ch2 = op.top();
                        op.pop();
                    }
                } else if (preority[ch] <= preority[op.top()]) {
                    // 处理优先级小于等于栈顶操作符的情况
                    char ch2;
                    ch2 = op.top();
                    while (!op.empty() && preority[ch] <= preority[op.top()] && ch2 != '(') {
                        op.pop();
                        int a = num.top();
                        num.pop();
                        int b = num.top();
                        num.pop();
                        num.push(operate(a, ch2, b)); // 计算并压入结果
                        if (!op.empty()) {
                            ch2 = op.top();
                        } else {
                            break;
                        }
                    }
                    op.push(ch); // 将当前操作符压入栈
                }
            }
        }

        // 处理剩余在栈中的表达式
        while (!op.empty()) {
            ch = op.top();
            op.pop();
            int a = num.top();
            num.pop();
            int b = num.top();
            num.pop();
            num.push(operate(a, ch, b)); // 计算并压入结果
        }

        return num.top(); // 返回最终结果
    }
};
```

#### Go

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
