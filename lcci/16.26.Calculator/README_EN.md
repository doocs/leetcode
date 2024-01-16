# [16.26. Calculator](https://leetcode.cn/problems/calculator-lcci)

[中文文档](/lcci/16.26.Calculator/README.md)

## Description

<p>Given an arithmetic equation consisting of positive integers, +, -, * and / (no paren&shy;theses), compute the result.</p>
<p>The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.</p>
<p><strong>Example&nbsp;1:</strong></p>
<pre>

<strong>Input: </strong>&quot;3+2\*2&quot;

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
<p><strong>Note:</strong></p>
<ul>
	<li>You may assume that the given expression is always valid.</li>
	<li>Do not use the eval built-in library function.</li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def calculate(self, s: str) -> int:
        n = len(s)
        x = 0
        sign = "+"
        stk = []
        for i, c in enumerate(s):
            if c.isdigit():
                x = x * 10 + ord(c) - ord("0")
            if i == n - 1 or c in "+-*/":
                match sign:
                    case "+":
                        stk.append(x)
                    case "-":
                        stk.append(-x)
                    case "*":
                        stk.append(stk.pop() * x)
                    case "/":
                        stk.append(int(stk.pop() / x))
                x = 0
                sign = c
        return sum(stk)
```

```java
class Solution {
    public int calculate(String s) {
        int n = s.length();
        int x = 0;
        char sign = '+';
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                x = x * 10 + (c - '0');
            }
            if (i == n - 1 || !Character.isDigit(c) && c != ' ') {
                switch (sign) {
                    case '+' -> stk.push(x);
                    case '-' -> stk.push(-x);
                    case '*' -> stk.push(stk.pop() * x);
                    case '/' -> stk.push(stk.pop() / x);
                }
                x = 0;
                sign = c;
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
        int n = s.size();
        int x = 0;
        char sign = '+';
        stack<int> stk;
        for (int i = 0; i < n; ++i) {
            char c = s[i];
            if (isdigit(c)) {
                x = x * 10 + (c - '0');
            }
            if (i == n - 1 || !isdigit(c) && c != ' ') {
                if (sign == '+') {
                    stk.push(x);
                } else if (sign == '-') {
                    stk.push(-x);
                } else if (sign == '*') {
                    int y = stk.top();
                    stk.pop();
                    stk.push(y * x);
                } else if (sign == '/') {
                    int y = stk.top();
                    stk.pop();
                    stk.push(y / x);
                }
                x = 0;
                sign = c;
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
func calculate(s string) (ans int) {
	n := len(s)
	x := 0
	sign := '+'
	stk := []int{}
	for i := range s {
		if s[i] >= '0' && s[i] <= '9' {
			x = x*10 + int(s[i]-'0')
		}
		if i == n-1 || (s[i] != ' ' && (s[i] < '0' || s[i] > '9')) {
			switch sign {
			case '+':
				stk = append(stk, x)
			case '-':
				stk = append(stk, -x)
			case '*':
				stk[len(stk)-1] *= x
			case '/':
				stk[len(stk)-1] /= x
			}
			x = 0
			sign = rune(s[i])
		}
	}
	for _, x := range stk {
		ans += x
	}
	return
}
```

```ts
function calculate(s: string): number {
    const n = s.length;
    let x = 0;
    let sign = '+';
    const stk: number[] = [];
    for (let i = 0; i < n; ++i) {
        if (!isNaN(Number(s[i])) && s[i] !== ' ') {
            x = x * 10 + s[i].charCodeAt(0) - '0'.charCodeAt(0);
        }
        if (i === n - 1 || (isNaN(Number(s[i])) && s[i] !== ' ')) {
            switch (sign) {
                case '+':
                    stk.push(x);
                    break;
                case '-':
                    stk.push(-x);
                    break;
                case '*':
                    stk.push(stk.pop()! * x);
                    break;
                default:
                    stk.push((stk.pop()! / x) | 0);
            }
            x = 0;
            sign = s[i];
        }
    }
    return stk.reduce((x, y) => x + y);
}
```

<!-- tabs:end -->

<!-- end -->
