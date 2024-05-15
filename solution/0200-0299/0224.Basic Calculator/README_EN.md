---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0224.Basic%20Calculator/README_EN.md
tags:
    - Stack
    - Recursion
    - Math
    - String
---

# [224. Basic Calculator](https://leetcode.com/problems/basic-calculator)

[中文文档](/solution/0200-0299/0224.Basic%20Calculator/README.md)

## Description

<p>Given a string <code>s</code> representing a valid expression, implement a basic calculator to evaluate it, and return <em>the result of the evaluation</em>.</p>

<p><strong>Note:</strong> You are <strong>not</strong> allowed to use any built-in function which evaluates strings as mathematical expressions, such as <code>eval()</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;1 + 1&quot;
<strong>Output:</strong> 2
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot; 2-1 + 2 &quot;
<strong>Output:</strong> 3
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;(1+(4+5+2)-3)+(6+8)&quot;
<strong>Output:</strong> 23
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 3 * 10<sup>5</sup></code></li>
	<li><code>s</code> consists of digits, <code>&#39;+&#39;</code>, <code>&#39;-&#39;</code>, <code>&#39;(&#39;</code>, <code>&#39;)&#39;</code>, and <code>&#39; &#39;</code>.</li>
	<li><code>s</code> represents a valid expression.</li>
	<li><code>&#39;+&#39;</code> is <strong>not</strong> used as a unary operation (i.e., <code>&quot;+1&quot;</code> and <code>&quot;+(2 + 3)&quot;</code> is invalid).</li>
	<li><code>&#39;-&#39;</code> could be used as a unary operation (i.e., <code>&quot;-1&quot;</code> and <code>&quot;-(2 + 3)&quot;</code> is valid).</li>
	<li>There will be no two consecutive operators in the input.</li>
	<li>Every number and running calculation will fit in a signed 32-bit integer.</li>
</ul>

## Solutions

### Solution 1: Stack

We use a stack $stk$ to save the current calculation result and operator, a variable $sign$ to save the current sign, and a variable $ans$ to save the final calculation result.

Next, we traverse each character of the string $s$:

-   If the current character is a number, we use a loop to read the following consecutive numbers, and then add or subtract it to $ans$ according to the current sign.
-   If the current character is `'+'`, we change the variable $sign$ to positive.
-   If the current character is `'-'`, we change the variable $sign$ to negative.
-   If the current character is `'('`, we push the current $ans$ and $sign$ into the stack, and reset them to empty and 1, and start to calculate the new $ans$ and $sign$.
-   If the current character is `')'`, we pop the top two elements of the stack, one is the operator, and the other is the number calculated before the bracket. We multiply the current number by the operator, and add the previous number to get the new $ans$.

After traversing the string $s$, we return $ans$.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the string $s$.

<!-- tabs:start -->

```python
class Solution:
    def calculate(self, s: str) -> int:
        stk = []
        ans, sign = 0, 1
        i, n = 0, len(s)
        while i < n:
            if s[i].isdigit():
                x = 0
                j = i
                while j < n and s[j].isdigit():
                    x = x * 10 + int(s[j])
                    j += 1
                ans += sign * x
                i = j - 1
            elif s[i] == "+":
                sign = 1
            elif s[i] == "-":
                sign = -1
            elif s[i] == "(":
                stk.append(ans)
                stk.append(sign)
                ans, sign = 0, 1
            elif s[i] == ")":
                ans = stk.pop() * ans + stk.pop()
            i += 1
        return ans
```

```java
class Solution {
    public int calculate(String s) {
        Deque<Integer> stk = new ArrayDeque<>();
        int sign = 1;
        int ans = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int j = i;
                int x = 0;
                while (j < n && Character.isDigit(s.charAt(j))) {
                    x = x * 10 + s.charAt(j) - '0';
                    j++;
                }
                ans += sign * x;
                i = j - 1;
            } else if (c == '+') {
                sign = 1;
            } else if (c == '-') {
                sign = -1;
            } else if (c == '(') {
                stk.push(ans);
                stk.push(sign);
                ans = 0;
                sign = 1;
            } else if (c == ')') {
                ans = stk.pop() * ans + stk.pop();
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int calculate(string s) {
        stack<int> stk;
        int ans = 0, sign = 1;
        int n = s.size();
        for (int i = 0; i < n; ++i) {
            if (isdigit(s[i])) {
                int x = 0;
                int j = i;
                while (j < n && isdigit(s[j])) {
                    x = x * 10 + (s[j] - '0');
                    ++j;
                }
                ans += sign * x;
                i = j - 1;
            } else if (s[i] == '+') {
                sign = 1;
            } else if (s[i] == '-') {
                sign = -1;
            } else if (s[i] == '(') {
                stk.push(ans);
                stk.push(sign);
                ans = 0;
                sign = 1;
            } else if (s[i] == ')') {
                ans *= stk.top();
                stk.pop();
                ans += stk.top();
                stk.pop();
            }
        }
        return ans;
    }
};
```

```go
func calculate(s string) (ans int) {
	stk := []int{}
	sign := 1
	n := len(s)
	for i := 0; i < n; i++ {
		switch s[i] {
		case ' ':
		case '+':
			sign = 1
		case '-':
			sign = -1
		case '(':
			stk = append(stk, ans)
			stk = append(stk, sign)
			ans, sign = 0, 1
		case ')':
			ans *= stk[len(stk)-1]
			stk = stk[:len(stk)-1]
			ans += stk[len(stk)-1]
			stk = stk[:len(stk)-1]
		default:
			x := 0
			j := i
			for ; j < n && '0' <= s[j] && s[j] <= '9'; j++ {
				x = x*10 + int(s[j]-'0')
			}
			ans += sign * x
			i = j - 1
		}
	}
	return
}
```

```ts
function calculate(s: string): number {
    const stk: number[] = [];
    let sign = 1;
    let ans = 0;
    const n = s.length;
    for (let i = 0; i < n; ++i) {
        if (s[i] === ' ') {
            continue;
        }
        if (s[i] === '+') {
            sign = 1;
        } else if (s[i] === '-') {
            sign = -1;
        } else if (s[i] === '(') {
            stk.push(ans);
            stk.push(sign);
            ans = 0;
            sign = 1;
        } else if (s[i] === ')') {
            ans *= stk.pop() as number;
            ans += stk.pop() as number;
        } else {
            let x = 0;
            let j = i;
            for (; j < n && !isNaN(Number(s[j])) && s[j] !== ' '; ++j) {
                x = x * 10 + (s[j].charCodeAt(0) - '0'.charCodeAt(0));
            }
            ans += sign * x;
            i = j - 1;
        }
    }
    return ans;
}
```

```cs
public class Solution {
    public int Calculate(string s) {
        var stk = new Stack<int>();
        int sign = 1;
        int n = s.Length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (s[i] == ' ') {
                continue;
            }
            if (s[i] == '+') {
                sign = 1;
            } else if (s[i] == '-') {
                sign = -1;
            } else if (s[i] == '(') {
                stk.Push(ans);
                stk.Push(sign);
                ans = 0;
                sign = 1;
            } else if (s[i] == ')') {
                ans *= stk.Pop();
                ans += stk.Pop();
            } else {
                int num = 0;
                while (i < n && char.IsDigit(s[i])) {
                    num = num * 10 + s[i] - '0';
                    ++i;
                }
                --i;
                ans += sign * num;
            }
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- end -->
