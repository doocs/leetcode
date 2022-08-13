# [640. Solve the Equation](https://leetcode.com/problems/solve-the-equation)

[中文文档](/solution/0600-0699/0640.Solve%20the%20Equation/README.md)

## Description

<p>Solve a given equation and return the value of <code>&#39;x&#39;</code> in the form of a string <code>&quot;x=#value&quot;</code>. The equation contains only <code>&#39;+&#39;</code>, <code>&#39;-&#39;</code> operation, the variable <code>&#39;x&#39;</code> and its coefficient. You should return <code>&quot;No solution&quot;</code> if there is no solution for the equation, or <code>&quot;Infinite solutions&quot;</code> if there are infinite solutions for the equation.</p>

<p>If there is exactly one solution for the equation, we ensure that the value of <code>&#39;x&#39;</code> is an integer.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> equation = &quot;x+5-3+x=6+x-2&quot;
<strong>Output:</strong> &quot;x=2&quot;
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> equation = &quot;x=x&quot;
<strong>Output:</strong> &quot;Infinite solutions&quot;
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> equation = &quot;2x=x&quot;
<strong>Output:</strong> &quot;x=0&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= equation.length &lt;= 1000</code></li>
	<li><code>equation</code> has exactly one <code>&#39;=&#39;</code>.</li>
	<li><code>equation</code> consists of integers with an absolute value in the range <code>[0, 100]</code> without any leading zeros, and the variable <code>&#39;x&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
