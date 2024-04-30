# [227. Basic Calculator II](https://leetcode.com/problems/basic-calculator-ii)

[中文文档](/solution/0200-0299/0227.Basic%20Calculator%20II/README.md)

<!-- tags:Stack,Math,String -->

## Description

<p>Given a string <code>s</code> which represents an expression, <em>evaluate this expression and return its value</em>.&nbsp;</p>

<p>The integer division should truncate toward zero.</p>

<p>You may assume that the given expression is always valid. All intermediate results will be in the range of <code>[-2<sup>31</sup>, 2<sup>31</sup> - 1]</code>.</p>

<p><strong>Note:</strong> You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as <code>eval()</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> s = "3+2*2"
<strong>Output:</strong> 7
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> s = " 3/2 "
<strong>Output:</strong> 1
</pre><p><strong class="example">Example 3:</strong></p>
<pre><strong>Input:</strong> s = " 3+5 / 2 "
<strong>Output:</strong> 5
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 3 * 10<sup>5</sup></code></li>
	<li><code>s</code> consists of integers and operators <code>(&#39;+&#39;, &#39;-&#39;, &#39;*&#39;, &#39;/&#39;)</code> separated by some number of spaces.</li>
	<li><code>s</code> represents <strong>a valid expression</strong>.</li>
	<li>All the integers in the expression are non-negative integers in the range <code>[0, 2<sup>31</sup> - 1]</code>.</li>
	<li>The answer is <strong>guaranteed</strong> to fit in a <strong>32-bit integer</strong>.</li>
</ul>

## Solutions

### Solution 1: Stack

We traverse the string $s$, and use a variable `sign` to record the operator before each number. For the first number, its previous operator is considered as a plus sign. Each time we traverse to the end of a number, we decide the calculation method based on `sign`:

-   Plus sign: push the number into the stack;
-   Minus sign: push the opposite number into the stack;
-   Multiplication and division signs: calculate the number with the top element of the stack, and replace the top element of the stack with the calculation result.

After the traversal ends, the sum of the elements in the stack is the answer.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the string $s$.

<!-- tabs:start -->

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

<!-- end -->
