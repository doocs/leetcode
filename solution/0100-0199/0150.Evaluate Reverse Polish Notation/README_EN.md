# [150. Evaluate Reverse Polish Notation](https://leetcode.com/problems/evaluate-reverse-polish-notation)

[中文文档](/solution/0100-0199/0150.Evaluate%20Reverse%20Polish%20Notation/README.md)

## Description

<p>Evaluate the value of an arithmetic expression in <a href="http://en.wikipedia.org/wiki/Reverse_Polish_notation" target="_blank">Reverse Polish Notation</a>.</p>

<p>Valid operators are <code>+</code>, <code>-</code>, <code>*</code>, and <code>/</code>. Each operand may be an integer or another expression.</p>

<p><strong>Note</strong> that division between two integers should truncate toward zero.</p>

<p>It is guaranteed that the given RPN expression is always valid. That means the expression would always evaluate to a result, and there will not be any division by zero operation.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> tokens = [&quot;2&quot;,&quot;1&quot;,&quot;+&quot;,&quot;3&quot;,&quot;*&quot;]
<strong>Output:</strong> 9
<strong>Explanation:</strong> ((2 + 1) * 3) = 9
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> tokens = [&quot;4&quot;,&quot;13&quot;,&quot;5&quot;,&quot;/&quot;,&quot;+&quot;]
<strong>Output:</strong> 6
<strong>Explanation:</strong> (4 + (13 / 5)) = 6
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> tokens = [&quot;10&quot;,&quot;6&quot;,&quot;9&quot;,&quot;3&quot;,&quot;+&quot;,&quot;-11&quot;,&quot;*&quot;,&quot;/&quot;,&quot;*&quot;,&quot;17&quot;,&quot;+&quot;,&quot;5&quot;,&quot;+&quot;]
<strong>Output:</strong> 22
<strong>Explanation:</strong> ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= tokens.length &lt;= 10<sup>4</sup></code></li>
	<li><code>tokens[i]</code> is either an operator: <code>&quot;+&quot;</code>, <code>&quot;-&quot;</code>, <code>&quot;*&quot;</code>, or <code>&quot;/&quot;</code>, or an integer in the range <code>[-200, 200]</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
import operator


class Solution:
    def evalRPN(self, tokens: List[str]) -> int:
        opt = {
            "+": operator.add,
            "-": operator.sub,
            "*": operator.mul,
            "/": operator.truediv,
        }
        s = []
        for token in tokens:
            if token in opt:
                s.append(int(opt[token](s.pop(-2), s.pop(-1))))
            else:
                s.append(int(token))
        return s[0]
```

```python
class Solution:
    def evalRPN(self, tokens: List[str]) -> int:
        nums = []
        for t in tokens:
            if len(t) > 1 or t.isdigit():
                nums.append(int(t))
            else:
                if t == "+":
                    nums[-2] += nums[-1]
                elif t == "-":
                    nums[-2] -= nums[-1]
                elif t == "*":
                    nums[-2] *= nums[-1]
                else:
                    nums[-2] = int(nums[-2] / nums[-1])
                nums.pop()
        return nums[0]
```

### **Java**

```java
class Solution {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stk = new ArrayDeque<>();
        for (String t : tokens) {
            if (t.length() > 1 || Character.isDigit(t.charAt(0))) {
                stk.push(Integer.parseInt(t));
            } else {
                int y = stk.pop();
                int x = stk.pop();
                switch (t) {
                    case "+":
                        stk.push(x + y);
                        break;
                    case "-":
                        stk.push(x - y);
                        break;
                    case "*":
                        stk.push(x * y);
                        break;
                    default:
                        stk.push(x / y);
                        break;
                }
            }
        }
        return stk.pop();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int evalRPN(vector<string>& tokens) {
        stack<int> stk;
        for (auto& t : tokens) {
            if (t.size() > 1 || isdigit(t[0])) {
                stk.push(stoi(t));
            } else {
                int y = stk.top();
                stk.pop();
                int x = stk.top();
                stk.pop();
                if (t[0] == '+')
                    stk.push(x + y);
                else if (t[0] == '-')
                    stk.push(x - y);
                else if (t[0] == '*')
                    stk.push(x * y);
                else
                    stk.push(x / y);
            }
        }
        return stk.top();
    }
};
```

### **Go**

```go
func evalRPN(tokens []string) int {
	// https://github.com/emirpasic/gods#arraystack
	stk := arraystack.New()
	for _, token := range tokens {
		if len(token) > 1 || token[0] >= '0' && token[0] <= '9' {
			num, _ := strconv.Atoi(token)
			stk.Push(num)
		} else {
			y := popInt(stk)
			x := popInt(stk)
			switch token {
			case "+":
				stk.Push(x + y)
			case "-":
				stk.Push(x - y)
			case "*":
				stk.Push(x * y)
			default:
				stk.Push(x / y)
			}
		}
	}
	return popInt(stk)
}

func popInt(stack *arraystack.Stack) int {
	v, _ := stack.Pop()
	return v.(int)
}
```

### **C#**

```cs
using System.Collections.Generic;

public class Solution {
    public int EvalRPN(string[] tokens) {
        var stack = new Stack<int>();
        foreach (var token in tokens)
        {
            switch (token)
            {
                case "+":
                    stack.Push(stack.Pop() + stack.Pop());
                    break;
                case "-":
                    stack.Push(-stack.Pop() + stack.Pop());
                    break;
                case "*":
                    stack.Push(stack.Pop() * stack.Pop());
                    break;
                case "/":
                    var right = stack.Pop();
                    stack.Push(stack.Pop() / right);
                    break;
                default:
                    stack.Push(int.Parse(token));
                    break;
            }
        }
        return stack.Pop();
    }
}
```

### **TypeScript**

```ts
function evalRPN(tokens: string[]): number {
    const stack = [];
    for (const token of tokens) {
        if (/\d/.test(token)) {
            stack.push(Number(token));
        } else {
            const a = stack.pop();
            const b = stack.pop();
            switch (token) {
                case '+':
                    stack.push(b + a);
                    break;
                case '-':
                    stack.push(b - a);
                    break;
                case '*':
                    stack.push(b * a);
                    break;
                case '/':
                    stack.push(~~(b / a));
                    break;
            }
        }
    }
    return stack[0];
}
```

### **Rust**

```rust
impl Solution {
    pub fn eval_rpn(tokens: Vec<String>) -> i32 {
        let mut stack = vec![];
        for token in tokens {
            match token.parse() {
                Ok(num) => stack.push(num),
                Err(_) => {
                    let a = stack.pop().unwrap();
                    let b = stack.pop().unwrap();
                    stack.push(match token.as_str() {
                        "+" => b + a,
                        "-" => b - a,
                        "*" => b * a,
                        "/" => b / a,
                        _ => 0,
                    })
                }
            }
        }
        stack[0]
    }
}
```

### **...**

```

```

<!-- tabs:end -->
