---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0224.Basic%20Calculator/README.md
tags:
    - 栈
    - 递归
    - 数学
    - 字符串
---

<!-- problem:start -->

# [224. 基本计算器](https://leetcode.cn/problems/basic-calculator)

[English Version](/solution/0200-0299/0224.Basic%20Calculator/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串表达式 <code>s</code> ，请你实现一个基本计算器来计算并返回它的值。</p>

<p>注意:不允许使用任何将字符串作为数学表达式计算的内置函数，比如 <code>eval()</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "1 + 1"
<strong>输出：</strong>2
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = " 2-1 + 2 "
<strong>输出：</strong>3
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "(1+(4+5+2)-3)+(6+8)"
<strong>输出：</strong>23
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 3&nbsp;* 10<sup>5</sup></code></li>
	<li><code>s</code> 由数字、<code>'+'</code>、<code>'-'</code>、<code>'('</code>、<code>')'</code>、和 <code>' '</code> 组成</li>
	<li><code>s</code> 表示一个有效的表达式</li>
	<li><font color="#c7254e"><font face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size:12.6px"><span style="background-color:#f9f2f4">'+'</span></span></font></font> 不能用作一元运算(例如， <font color="#c7254e"><font face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size:12.6px"><span style="background-color:#f9f2f4">"+1"</span></span></font></font>&nbsp;和 <code>"+(2 + 3)"</code>&nbsp;无效)</li>
	<li><font color="#c7254e"><font face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size:12.6px"><span style="background-color:#f9f2f4">'-'</span></span></font></font> 可以用作一元运算(即 <font color="#c7254e"><font face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size:12.6px"><span style="background-color:#f9f2f4">"-1"</span></span></font></font>&nbsp;和 <code>"-(2 + 3)"</code>&nbsp;是有效的)</li>
	<li>输入中不存在两个连续的操作符</li>
	<li>每个数字和运行的计算将适合于一个有符号的 32位 整数</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：栈

我们用一个栈 $stk$ 来保存当前的计算结果和操作符，用一个变量 $sign$ 保存当前的符号，变量 $ans$ 保存最终的计算结果。

接下来，我们遍历字符串 $s$ 的每一个字符：

-   如果当前字符是数字，那么我们用一个循环将后面的连续数字都读进来，然后用当前的符号将其加或者减到 $ans$ 中。
-   如果当前字符是 `'+'`，我们修改变量 $sign$ 为正号。
-   如果当前字符是 `'-'`，我们修改变量 $sign$ 为负号。
-   如果当前字符是 `'('`，我们把当前的 $ans$ 和 $sign$ 入栈，并分别置空置 1，重新开始计算新的 $ans$ 和 $sign$。
-   如果当前字符是 `')'`，我们弹出栈顶的两个元素，一个是操作符，一个是括号前计算好的数字，我们将当前的数字乘上操作符，再加上之前的数字，作为新的 $ans$。

遍历完字符串 $s$ 之后，我们返回 $ans$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是字符串 $s$ 的长度。

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

<!-- solution:end -->

<!-- problem:end -->
