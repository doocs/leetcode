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
class Solution {
    public int calculate(String s) {
        Deque<Character> q = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            q.offer(c);
        }
        return dfs(q);
    }

    private int dfs(Deque<Character> q) {
        long num = 0;
        char sign = '+';
        List<Long> stk = new ArrayList<>();
        while (!q.isEmpty()) {
            char c = q.poll();
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }
            if (c == '(') {
                num = dfs(q);
            }
            if ("+-*/)".indexOf(c) >= 0 || q.isEmpty()) {
                if (sign == '+') {
                    stk.add(num);
                } else if (sign == '-') {
                    stk.add(-num);
                } else if (sign == '*') {
                    stk.set(stk.size() - 1, stk.get(stk.size() - 1) * num);
                } else {
                    stk.set(stk.size() - 1, stk.get(stk.size() - 1) / num);
                }
                num = 0;
                sign = c;
            }
            if (c == ')') {
                break;
            }
        }
        long ans = 0;
        for (long x : stk) {
            ans += x;
        }
        return (int) ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int calculate(string s) {
        queue<char> q;
        for (char c : s) {
            q.push(c);
        }
        return dfs(q);
    }

private:
    int dfs(queue<char>& q) {
        long long num = 0;
        char sign = '+';
        vector<long long> stk;
        while (!q.empty()) {
            char c = q.front();
            q.pop();
            if (isdigit(c)) {
                num = num * 10 + (c - '0');
            }
            if (c == '(') {
                num = dfs(q);
            }
            if (c == '+' || c == '-' || c == '*' || c == '/' || c == ')' || q.empty()) {
                if (sign == '+') {
                    stk.push_back(num);
                } else if (sign == '-') {
                    stk.push_back(-num);
                } else if (sign == '*') {
                    stk.back() *= num;
                } else {
                    stk.back() /= num;
                }
                num = 0;
                sign = c;
            }
            if (c == ')') {
                break;
            }
        }
        return accumulate(stk.begin(), stk.end(), 0LL);
    }
};
```

#### Go

```go
func calculate(s string) int {
	q := []byte(s)
	var dfs func() int
	dfs = func() int {
		num, sign := 0, byte('+')
		var stk []int
		for len(q) > 0 {
			c := q[0]
			q = q[1:]
			if c >= '0' && c <= '9' {
				num = num*10 + int(c-'0')
			}
			if c == '(' {
				num = dfs()
			}
			if c == '+' || c == '-' || c == '*' || c == '/' || c == ')' || len(q) == 0 {
				switch sign {
				case '+':
					stk = append(stk, num)
				case '-':
					stk = append(stk, -num)
				case '*':
					stk[len(stk)-1] *= num
				default:
					stk[len(stk)-1] /= num
				}
				num, sign = 0, c
			}
			if c == ')' {
				break
			}
		}
		ans := 0
		for _, x := range stk {
			ans += x
		}
		return ans
	}
	return dfs()
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
