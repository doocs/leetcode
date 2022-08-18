# [640. 求解方程](https://leetcode.cn/problems/solve-the-equation)

[English Version](/solution/0600-0699/0640.Solve%20the%20Equation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>求解一个给定的方程，将<code>x</code>以字符串 <code>"x=#value"</code>&nbsp;的形式返回。该方程仅包含 <code>'+'</code> ， <code>'-'</code> 操作，变量&nbsp;<code>x</code>&nbsp;和其对应系数。</p>

<p>如果方程没有解或存在的解不为整数，请返回&nbsp;<code>"No solution"</code>&nbsp;。如果方程有无限解，则返回 <code>“Infinite solutions”</code> 。</p>

<p>题目保证，如果方程中只有一个解，则 <font color="#c7254e"><font face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size:12.6px"><span style="background-color:#f9f2f4">'x'</span></span></font></font> 的值是一个整数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong> equation = "x+5-3+x=6+x-2"
<strong>输出:</strong> "x=2"
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> equation = "x=x"
<strong>输出:</strong> "Infinite solutions"
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> equation = "2x=x"
<strong>输出:</strong> "x=0"
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>3 &lt;= equation.length &lt;= 1000</code></li>
	<li><code>equation</code>&nbsp;只有一个&nbsp;<code>'='</code>.&nbsp;</li>
	<li>方程由绝对值在&nbsp;<code>[0, 100]</code>&nbsp; 范围内且无任何前导零的整数和变量 <code>'x'</code>&nbsp;组成。<span style="display:block"><span style="height:0px"><span style="position:absolute">​​​</span></span></span></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数学**

将方程 $equation$ 按照等号 “=” 切分为左右两个式子，分别算出左右两个式子中 "x" 的系数 $x_i$，以及常数的值 $y_i$。

那么方程转换为等式 $x_1 \times x + y_1 = x_2 \times x + y_2$。

-   当 $x_1 = x_2$：若 $y_1 \neq y_2$，方程无解；若 $y_1=y_2$，方程有无限解。
-   当 $x_1 \neq x_2$：方程有唯一解 $x=\frac{y_2-y_1}{x_1-x_2}$。

相似题目：[592. 分数加减运算](/solution/0500-0599/0592.Fraction%20Addition%20and%20Subtraction/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def solveEquation(self, equation: str) -> str:
        def f(s):
            x = y = 0
            if s[0] != '-':
                s = '+' + s
            i, n = 0, len(s)
            while i < n:
                sign = 1 if s[i] == '+' else -1
                i += 1
                j = i
                while j < n and s[j] not in '+-':
                    j += 1
                v = s[i:j]
                if v[-1] == 'x':
                    x += sign * (int(v[:-1]) if len(v) > 1 else 1)
                else:
                    y += sign * int(v)
                i = j
            return x, y

        a, b = equation.split('=')
        x1, y1 = f(a)
        x2, y2 = f(b)
        if x1 == x2:
            return 'Infinite solutions' if y1 == y2 else 'No solution'
        return f'x={(y2 - y1) // (x1 - x2)}'
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String solveEquation(String equation) {
        String[] es = equation.split("=");
        int[] a = f(es[0]), b = f(es[1]);
        int x1 = a[0], y1 = a[1];
        int x2 = b[0], y2 = b[1];
        if (x1 == x2) {
            return y1 == y2 ? "Infinite solutions" : "No solution";
        }
        return "x=" + (y2 - y1) / (x1 - x2);
    }

    private int[] f(String s) {
        int x = 0, y = 0;
        if (s.charAt(0) != '-') {
            s = "+" + s;
        }
        int i = 0, n = s.length();
        while (i < n) {
            int sign = s.charAt(i) == '+' ? 1 : -1;
            ++i;
            int j = i;
            while (j < n && s.charAt(j) != '+' && s.charAt(j) != '-') {
                ++j;
            }
            String v = s.substring(i, j);
            if (s.charAt(j - 1) == 'x') {
                x += sign * (v.length() > 1 ? Integer.parseInt(v.substring(0, v.length() - 1)) : 1);
            } else {
                y += sign * Integer.parseInt(v);
            }
            i = j;
        }
        return new int[]{x, y};
    }
}
```

### **Go**

```go
func solveEquation(equation string) string {
	f := func(s string) []int {
		x, y := 0, 0
		if s[0] != '-' {
			s = "+" + s
		}
		i, n := 0, len(s)
		for i < n {
			sign := 1
			if s[i] == '-' {
				sign = -1
			}
			i++
			j := i
			for j < n && s[j] != '+' && s[j] != '-' {
				j++
			}
			v := s[i:j]
			if s[j-1] == 'x' {
				a := 1
				if len(v) > 1 {
					a, _ = strconv.Atoi(v[:len(v)-1])
				}
				x += sign * a
			} else {
				a, _ := strconv.Atoi(v)
				y += sign * a
			}
			i = j
		}
		return []int{x, y}
	}

	es := strings.Split(equation, "=")
	a, b := f(es[0]), f(es[1])
	x1, y1 := a[0], a[1]
	x2, y2 := b[0], b[1]
	if x1 == x2 {
		if y1 == y2 {
			return "Infinite solutions"
		} else {
			return "No solution"
		}
	}
	return fmt.Sprintf("x=%d", (y2-y1)/(x1-x2))
}
```

### **TypeScript**

```ts
function solveEquation(equation: string): string {
    const [left, right] = equation.split('=');
    const createExpr = (s: string) => {
        let x = 0;
        let n = 0;
        let i = 0;
        let sym = 1;
        let cur = 0;
        let isX = false;
        for (const c of s) {
            if (c === '+' || c === '-') {
                if (isX) {
                    if (i === 0 && cur === 0) {
                        cur = 1;
                    }
                    x += cur * sym;
                } else {
                    n += cur * sym;
                }
                isX = false;
                cur = 0;
                i = 0;
                if (c === '+') {
                    sym = 1;
                } else {
                    sym = -1;
                }
            } else if (c === 'x') {
                isX = true;
            } else {
                i++;
                cur *= 10;
                cur += Number(c);
            }
        }
        if (isX) {
            if (i === 0 && cur === 0) {
                cur = 1;
            }
            x += cur * sym;
        } else {
            n += cur * sym;
        }
        return [x, n];
    };
    const lExpr = createExpr(left);
    const rExpr = createExpr(right);
    if (lExpr[0] === rExpr[0]) {
        if (lExpr[1] !== rExpr[1]) {
            return 'No solution';
        }
        return 'Infinite solutions';
    }
    return `x=${(lExpr[1] - rExpr[1]) / (rExpr[0] - lExpr[0])}`;
}
```

### **...**

```

```

<!-- tabs:end -->
