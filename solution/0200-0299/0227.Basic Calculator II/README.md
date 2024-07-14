---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0227.Basic%20Calculator%20II/README.md
tags:
    - 栈
    - 数学
    - 字符串
---

<!-- problem:start -->

# [227. 基本计算器 II](https://leetcode.cn/problems/basic-calculator-ii)

[English Version](/solution/0200-0299/0227.Basic%20Calculator%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串表达式 <code>s</code> ，请你实现一个基本计算器来计算并返回它的值。</p>

<p>整数除法仅保留整数部分。</p>

<p>你可以假设给定的表达式总是有效的。所有中间结果将在&nbsp;<code>[-2<sup>31</sup>, 2<sup>31</sup>&nbsp;- 1]</code> 的范围内。</p>

<p><strong>注意：</strong>不允许使用任何将字符串作为数学表达式计算的内置函数，比如 <code>eval()</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "3+2*2"
<strong>输出：</strong>7
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = " 3/2 "
<strong>输出：</strong>1
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = " 3+5 / 2 "
<strong>输出：</strong>5
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 3 * 10<sup>5</sup></code></li>
	<li><code>s</code> 由整数和算符 <code>('+', '-', '*', '/')</code> 组成，中间由一些空格隔开</li>
	<li><code>s</code> 表示一个 <strong>有效表达式</strong></li>
	<li>表达式中的所有整数都是非负整数，且在范围 <code>[0, 2<sup>31</sup> - 1]</code> 内</li>
	<li>题目数据保证答案是一个 <strong>32-bit 整数</strong></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：栈

遍历字符串 $s$，并用变量 `sign` 记录每个数字之前的运算符，对于第一个数字，其之前的运算符视为加号。每次遍历到数字末尾时，根据 `sign` 来决定计算方式：

-   加号：将数字压入栈；
-   减号：将数字的相反数压入栈；
-   乘除号：计算数字与栈顶元素，并将栈顶元素替换为计算结果。

遍历结束后，将栈中元素求和即为答案。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 $s$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def calculate(self, s: str) -> int:
        v, n = 0, len(s)
        sign = '+'
        stk = []
        for i, c in enumerate(s):
            if c.isdigit():
                v = v * 10 + int(c)
            if i == n - 1 or c in '+-*/':
                match sign:
                    case '+':
                        stk.append(v)
                    case '-':
                        stk.append(-v)
                    case '*':
                        stk.append(stk.pop() * v)
                    case '/':
                        stk.append(int(stk.pop() / v))
                sign = c
                v = 0
        return sum(stk)
```

#### Java

```java
class Solution {
    public int calculate(String s) {
        Deque<Integer> stk = new ArrayDeque<>();
        char sign = '+';
        int v = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                v = v * 10 + (c - '0');
            }
            if (i == s.length() - 1 || c == '+' || c == '-' || c == '*' || c == '/') {
                if (sign == '+') {
                    stk.push(v);
                } else if (sign == '-') {
                    stk.push(-v);
                } else if (sign == '*') {
                    stk.push(stk.pop() * v);
                } else {
                    stk.push(stk.pop() / v);
                }
                sign = c;
                v = 0;
            }
        }
        int ans = 0;
        while (!stk.isEmpty()) {
            ans += stk.pop();
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int calculate(string s) {
        int v = 0, n = s.size();
        char sign = '+';
        stack<int> stk;
        for (int i = 0; i < n; ++i) {
            char c = s[i];
            if (isdigit(c)) v = v * 10 + (c - '0');
            if (i == n - 1 || c == '+' || c == '-' || c == '*' || c == '/') {
                if (sign == '+')
                    stk.push(v);
                else if (sign == '-')
                    stk.push(-v);
                else if (sign == '*') {
                    int t = stk.top();
                    stk.pop();
                    stk.push(t * v);
                } else {
                    int t = stk.top();
                    stk.pop();
                    stk.push(t / v);
                }
                sign = c;
                v = 0;
            }
        }
        int ans = 0;
        while (!stk.empty()) {
            ans += stk.top();
            stk.pop();
        }
        return ans;
    }
};
```

#### Go

```go
func calculate(s string) int {
	sign := '+'
	stk := []int{}
	v := 0
	for i, c := range s {
		digit := '0' <= c && c <= '9'
		if digit {
			v = v*10 + int(c-'0')
		}
		if i == len(s)-1 || !digit && c != ' ' {
			switch sign {
			case '+':
				stk = append(stk, v)
			case '-':
				stk = append(stk, -v)
			case '*':
				stk[len(stk)-1] *= v
			case '/':
				stk[len(stk)-1] /= v
			}
			sign = c
			v = 0
		}
	}
	ans := 0
	for _, v := range stk {
		ans += v
	}
	return ans
}
```

#### C#

```cs
using System.Collections.Generic;
using System.Linq;

struct Element
{
    public char Op;
    public int Number;
    public Element(char op, int number)
    {
        Op = op;
        Number = number;
    }
}

public class Solution {
    public int Calculate(string s) {
        var stack = new Stack<Element>();
        var readingNumber = false;
        var number = 0;
        var op = '+';
        foreach (var ch in ((IEnumerable<char>)s).Concat(Enumerable.Repeat('+', 1)))
        {
            if (ch >= '0' && ch <= '9')
            {
                if (!readingNumber)
                {
                    readingNumber = true;
                    number = 0;
                }
                number = (number * 10) + (ch - '0');
            }
            else if (ch != ' ')
            {
                readingNumber = false;
                if (op == '+' || op == '-')
                {
                    if (stack.Count == 2)
                    {
                        var prev = stack.Pop();
                        var first = stack.Pop();
                        if (prev.Op == '+')
                        {
                            stack.Push(new Element(first.Op, first.Number + prev.Number));
                        }
                        else // '-'
                        {
                            stack.Push(new Element(first.Op, first.Number - prev.Number));
                        }
                    }
                    stack.Push(new Element(op, number));
                }
                else
                {
                    var prev = stack.Pop();
                    if (op == '*')
                    {
                        stack.Push(new Element(prev.Op, prev.Number * number));
                    }
                    else // '/'
                    {
                        stack.Push(new Element(prev.Op, prev.Number / number));
                    }
                }
                op = ch;
            }
        }

        if (stack.Count == 2)
        {
            var second = stack.Pop();
            var first = stack.Pop();
            if (second.Op == '+')
            {
                stack.Push(new Element(first.Op, first.Number + second.Number));
            }
            else // '-'
            {
                stack.Push(new Element(first.Op, first.Number - second.Number));
            }
        }

        return stack.Peek().Number;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
