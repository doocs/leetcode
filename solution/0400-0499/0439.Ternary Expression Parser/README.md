# [439. 三元表达式解析器](https://leetcode.cn/problems/ternary-expression-parser)

[English Version](/solution/0400-0499/0439.Ternary%20Expression%20Parser/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个表示任意嵌套三元表达式的字符串&nbsp;<code>expression</code>&nbsp;，求值并返回其结果。</p>

<p>你可以总是假设给定的表达式是有效的，并且只包含数字，&nbsp;<code>'?'</code>&nbsp;，&nbsp;&nbsp;<code>':'</code>&nbsp;，&nbsp;&nbsp;<code>'T'</code>&nbsp;和 <code>'F'</code> ，其中 <code>'T'</code> 为真， <code>'F'</code> 为假。表达式中的所有数字都是 <strong>一位</strong> 数(即在 <strong>[0,9] </strong>范围内)。</p>

<p>条件表达式从右到左分组(大多数语言中都是这样)，表达式的结果总是为数字 <code>'T'</code> 或 <code>'F'</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong> expression = "T?2:3"
<strong>输出：</strong> "2"
<strong>解释：</strong> 如果条件为真，结果为 2；否则，结果为 3。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong> expression = "F?1:T?4:5"
<strong>输出：</strong> "4"
<strong>解释：</strong> 条件表达式自右向左结合。使用括号的话，相当于：
 "(F ? 1 : (T ? 4 : 5))" --&gt; "(F ? 1 : 4)" --&gt; "4"
or "(F ? 1 : (T ? 4 : 5))" --&gt; "(T ? 4 : 5)" --&gt; "4"
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong> expression = "T?T?F:5:3"
<strong>输出：</strong> "F"
<strong>解释：</strong> 条件表达式自右向左结合。使用括号的话，相当于：
"(T ? (T ? F : 5) : 3)" --&gt; "(T ? F : 3)" --&gt; "F"
"(T ? (T ? F : 5) : 3)" --&gt; "(T ? F : 5)" --&gt; "F"</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>5 &lt;= expression.length &lt;= 10<sup>4</sup></code></li>
	<li><code>expression</code>&nbsp;由数字,&nbsp;<code>'T'</code>,&nbsp;<code>'F'</code>,&nbsp;<code>'?'</code>&nbsp;和&nbsp;<code>':'</code>&nbsp;组成</li>
	<li><strong>保证&nbsp;</strong>了表达式是一个有效的三元表达式，并且每个数字都是 <strong>一位数</strong>&nbsp;</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：栈**

我们从右到左遍历字符串 `expression`，对于当前遍历到的字符 $c$：

-   如果 $c$ 是字符 `':'`，则跳过；
-   如果 $c$ 是字符 `'?'`，那么意味着下一个即将遍历到的字符是条件表达式的条件，我们用一个布尔变量 `cond` 标记；
-   如果 $c$ 的上一个遍历到的字符是 `'?'`，也即布尔变量 `cond` 为 `true`，那么我们判断当前字符 $c$ 是否为字符 `'T'`，如果是，那么我们要保留栈顶第一个元素，弹出栈顶第二个元素；否则，我们要保留栈顶第二个元素，弹出栈顶第一个元素。最后，将 `cond` 置为 `false`；
-   否则，我们将当前字符 $c$ 入栈。

最后，栈中只剩下一个元素，即为表达式的结果。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 `expression` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def parseTernary(self, expression: str) -> str:
        stk = []
        cond = False
        for c in expression[::-1]:
            if c == ':':
                continue
            if c == '?':
                cond = True
            else:
                if cond:
                    if c == 'T':
                        x = stk.pop()
                        stk.pop()
                        stk.append(x)
                    else:
                        stk.pop()
                    cond = False
                else:
                    stk.append(c)
        return stk[0]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String parseTernary(String expression) {
        Deque<Character> stk = new ArrayDeque<>();
        boolean cond = false;
        for (int i = expression.length() - 1; i >= 0; --i) {
            char c = expression.charAt(i);
            if (c == ':') {
                continue;
            }
            if (c == '?') {
                cond = true;
            } else {
                if (cond) {
                    if (c == 'T') {
                        char x = stk.pop();
                        stk.pop();
                        stk.push(x);
                    } else {
                        stk.pop();
                    }
                    cond = false;
                } else {
                    stk.push(c);
                }
            }
        }
        return String.valueOf(stk.peek());
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string parseTernary(string expression) {
        string stk;
        bool cond = false;
        reverse(expression.begin(), expression.end());
        for (char& c : expression) {
            if (c == ':') {
                continue;
            }
            if (c == '?') {
                cond = true;
            } else {
                if (cond) {
                    if (c == 'T') {
                        char x = stk.back();
                        stk.pop_back();
                        stk.pop_back();
                        stk.push_back(x);
                    } else {
                        stk.pop_back();
                    }
                    cond = false;
                } else {
                    stk.push_back(c);
                }
            }
        }
        return {stk[0]};
    }
};
```

### **Go**

```go
func parseTernary(expression string) string {
	stk := []byte{}
	cond := false
	for i := len(expression) - 1; i >= 0; i-- {
		c := expression[i]
		if c == ':' {
			continue
		}
		if c == '?' {
			cond = true
		} else {
			if cond {
				if c == 'T' {
					x := stk[len(stk)-1]
					stk = stk[:len(stk)-2]
					stk = append(stk, x)
				} else {
					stk = stk[:len(stk)-1]
				}
				cond = false
			} else {
				stk = append(stk, c)
			}
		}
	}
	return string(stk[0])
}
```

### **...**

```

```

<!-- tabs:end -->
