# [1106. 解析布尔表达式](https://leetcode.cn/problems/parsing-a-boolean-expression)

[English Version](/solution/1100-1199/1106.Parsing%20A%20Boolean%20Expression/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><strong>布尔表达式</strong> 是计算结果不是 <code>true</code> 就是 <code>false</code> 的表达式。有效的表达式需遵循以下约定：</p>

<ul>
	<li><code>'t'</code>，运算结果为 <code>true</code></li>
	<li><code>'f'</code>，运算结果为 <code>false</code></li>
	<li><code>'!(subExpr)'</code>，运算过程为对内部表达式 <code>subExpr</code> 进行 <strong>逻辑非</strong>（NOT）运算</li>
	<li><code>'&amp;(subExpr<sub>1</sub>, subExpr<sub>2</sub>, ..., subExpr<sub>n</sub>)'</code>，运算过程为对 2 个或以上内部表达式 <code>subExpr<sub>1</sub>, subExpr<sub>2</sub>, ..., subExpr<sub>n</sub></code> 进行 <strong>逻辑与</strong>（AND）运算</li>
	<li><code>'|(subExpr<sub>1</sub>, subExpr<sub>2</sub>, ..., subExpr<sub>n</sub>)'</code>，运算过程为对 2 个或以上内部表达式 <code>subExpr<sub>1</sub>, subExpr<sub>2</sub>, ..., subExpr<sub>n</sub></code> 进行 <strong>逻辑或</strong>（OR）运算</li>
</ul>

<p>给你一个以字符串形式表述的&nbsp;<a href="https://baike.baidu.com/item/%E5%B8%83%E5%B0%94%E8%A1%A8%E8%BE%BE%E5%BC%8F/1574380?fr=aladdin" target="_blank">布尔表达式</a> <code>expression</code>，返回该式的运算结果。</p>

<p>题目测试用例所给出的表达式均为有效的布尔表达式，遵循上述约定。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>expression = "&amp;(|(f))"
<strong>输出：</strong>false
<strong>解释：</strong>
首先，计算 |(f) --&gt; f ，表达式变为 "&amp;(f)" 。
接着，计算 &amp;(f) --&gt; f ，表达式变为 "f" 。
最后，返回 false 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>expression = "|(f,f,f,t)"
<strong>输出：</strong>true
<strong>解释：</strong>计算 (false OR false OR false OR true) ，结果为 true 。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>expression = "!(&amp;(f,t))"
<strong>输出：</strong>true
<strong>解释：</strong>
首先，计算 &amp;(f,t) --&gt; (false AND true) --&gt; false --&gt; f ，表达式变为 "!(f)" 。
接着，计算 !(f) --&gt; NOT false --&gt; true ，返回 true 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= expression.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>expression[i]</code> 为 <code>'('</code>、<code>')'</code>、<code>'&amp;'</code>、<code>'|'</code>、<code>'!'</code>、<code>'t'</code>、<code>'f'</code> 和 <code>','</code> 之一</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：栈**

对于这种表达式解析问题，我们可以使用栈来辅助解决。

从左到右遍历表达式 `expression`，对于遍历到的每个字符 $c$：

-   如果 $c$ 是 `"tf!&|"` 中的一个，我们直接将其入栈；
-   如果 $c$ 是右括号 `')'`，我们将栈中元素依次出栈，直到遇到操作符 `'!'` 或 `'&'` 或 `'|'`。过程中我们用变量 $t$ 和 $f$ 记录出栈字符中 `'t'` 和 `'f'` 的个数。最后根据出栈字符的个数和操作符计算得到新的字符 `'t'` 或 `'f'`，并将其入栈。

遍历完表达式 `expression` 后，栈中只剩下一个字符，如果是 `'t'`，返回 `true`，否则返回 `false`。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def parseBoolExpr(self, expression: str) -> bool:
        stk = []
        for c in expression:
            if c in 'tf!&|':
                stk.append(c)
            elif c == ')':
                t = f = 0
                while stk[-1] in 'tf':
                    t += stk[-1] == 't'
                    f += stk[-1] == 'f'
                    stk.pop()
                match stk.pop():
                    case '!':
                        c = 't' if f else 'f'
                    case '&':
                        c = 'f' if f else 't'
                    case '|':
                        c = 't' if t else 'f'
                stk.append(c)
        return stk[0] == 't'
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean parseBoolExpr(String expression) {
        Deque<Character> stk = new ArrayDeque<>();
        for (char c : expression.toCharArray()) {
            if (c != '(' && c != ')' && c != ',') {
                stk.push(c);
            } else if (c == ')') {
                int t = 0, f = 0;
                while (stk.peek() == 't' || stk.peek() == 'f') {
                    t += stk.peek() == 't' ? 1 : 0;
                    f += stk.peek() == 'f' ? 1 : 0;
                    stk.pop();
                }
                char op = stk.pop();
                c = 'f';
                if ((op == '!' && f > 0) || (op == '&' && f == 0) || (op == '|' && t > 0)) {
                    c = 't';
                }
                stk.push(c);
            }
        }
        return stk.peek() == 't';
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool parseBoolExpr(string expression) {
        stack<char> stk;
        for (char c : expression) {
            if (c != '(' && c != ')' && c != ',') stk.push(c);
            else if (c == ')') {
                int t = 0, f = 0;
                while (stk.top() == 't' || stk.top() == 'f') {
                    t += stk.top() == 't';
                    f += stk.top() == 'f';
                    stk.pop();
                }
                char op = stk.top();
                stk.pop();
                if (op == '!') c = f ? 't' : 'f';
                if (op == '&') c = f ? 'f' : 't';
                if (op == '|') c = t ? 't' : 'f';
                stk.push(c);
            }
        }
        return stk.top() == 't';
    }
};
```

### **Go**

```go
func parseBoolExpr(expression string) bool {
	stk := []rune{}
	for _, c := range expression {
		if c != '(' && c != ')' && c != ',' {
			stk = append(stk, c)
		} else if c == ')' {
			var t, f int
			for stk[len(stk)-1] == 't' || stk[len(stk)-1] == 'f' {
				if stk[len(stk)-1] == 't' {
					t++
				} else {
					f++
				}
				stk = stk[:len(stk)-1]
			}
			op := stk[len(stk)-1]
			stk = stk[:len(stk)-1]
			c = 'f'
			if (op == '!' && f > 0) || (op == '&' && f == 0) || (op == '|' && t > 0) {
				c = 't'
			}
			stk = append(stk, c)
		}
	}
	return stk[0] == 't'
}
```

### **TypeScript**

```ts
function parseBoolExpr(expression: string): boolean {
    const expr = expression;
    const n = expr.length;
    let i = 0;
    const dfs = () => {
        let res: boolean[] = [];
        while (i < n) {
            const c = expr[i++];
            if (c === ')') {
                break;
            }

            if (c === '!') {
                res.push(!dfs()[0]);
            } else if (c === '|') {
                res.push(dfs().some(v => v));
            } else if (c === '&') {
                res.push(dfs().every(v => v));
            } else if (c === 't') {
                res.push(true);
            } else if (c === 'f') {
                res.push(false);
            }
        }
        return res;
    };
    return dfs()[0];
}
```

### **Rust**

```rust
impl Solution {
    fn dfs(i: &mut usize, expr: &[u8]) -> Vec<bool> {
        let n = expr.len();
        let mut res = Vec::new();
        while *i < n {
            let c = expr[*i];
            *i += 1;
            match c {
                b')' => {
                    break;
                }
                b't' => {
                    res.push(true);
                }
                b'f' => {
                    res.push(false);
                }
                b'!' => {
                    res.push(!Self::dfs(i, expr)[0]);
                }
                b'&' => {
                    res.push(Self::dfs(i, expr).iter().all(|v| *v));
                }
                b'|' => {
                    res.push(Self::dfs(i, expr).iter().any(|v| *v));
                }
                _ => {}
            }
        }
        res
    }

    pub fn parse_bool_expr(expression: String) -> bool {
        let expr = expression.as_bytes();
        let mut i = 0;
        Self::dfs(&mut i, expr)[0]
    }
}
```

### **...**

```

```

<!-- tabs:end -->
