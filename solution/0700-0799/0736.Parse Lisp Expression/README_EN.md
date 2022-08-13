# [736. Parse Lisp Expression](https://leetcode.com/problems/parse-lisp-expression)

[中文文档](/solution/0700-0799/0736.Parse%20Lisp%20Expression/README.md)

## Description

<p>You are given a string expression representing a Lisp-like expression to return the integer value of.</p>

<p>The syntax for these expressions is given as follows.</p>

<ul>
	<li>An expression is either an integer, let expression, add expression, mult expression, or an assigned variable. Expressions always evaluate to a single integer.</li>
	<li>(An integer could be positive or negative.)</li>
	<li>A let expression takes the form <code>&quot;(let v<sub>1</sub> e<sub>1</sub> v<sub>2</sub> e<sub>2</sub> ... v<sub>n</sub> e<sub>n</sub> expr)&quot;</code>, where let is always the string <code>&quot;let&quot;</code>, then there are one or more pairs of alternating variables and expressions, meaning that the first variable <code>v<sub>1</sub></code> is assigned the value of the expression <code>e<sub>1</sub></code>, the second variable <code>v<sub>2</sub></code> is assigned the value of the expression <code>e<sub>2</sub></code>, and so on sequentially; and then the value of this let expression is the value of the expression <code>expr</code>.</li>
	<li>An add expression takes the form <code>&quot;(add e<sub>1</sub> e<sub>2</sub>)&quot;</code> where add is always the string <code>&quot;add&quot;</code>, there are always two expressions <code>e<sub>1</sub></code>, <code>e<sub>2</sub></code> and the result is the addition of the evaluation of <code>e<sub>1</sub></code> and the evaluation of <code>e<sub>2</sub></code>.</li>
	<li>A mult expression takes the form <code>&quot;(mult e<sub>1</sub> e<sub>2</sub>)&quot;</code> where mult is always the string <code>&quot;mult&quot;</code>, there are always two expressions <code>e<sub>1</sub></code>, <code>e<sub>2</sub></code> and the result is the multiplication of the evaluation of e1 and the evaluation of e2.</li>
	<li>For this question, we will use a smaller subset of variable names. A variable starts with a lowercase letter, then zero or more lowercase letters or digits. Additionally, for your convenience, the names <code>&quot;add&quot;</code>, <code>&quot;let&quot;</code>, and <code>&quot;mult&quot;</code> are protected and will never be used as variable names.</li>
	<li>Finally, there is the concept of scope. When an expression of a variable name is evaluated, within the context of that evaluation, the innermost scope (in terms of parentheses) is checked first for the value of that variable, and then outer scopes are checked sequentially. It is guaranteed that every expression is legal. Please see the examples for more details on the scope.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> expression = &quot;(let x 2 (mult x (let x 3 y 4 (add x y))))&quot;
<strong>Output:</strong> 14
<strong>Explanation:</strong> In the expression (add x y), when checking for the value of the variable x,
we check from the innermost scope to the outermost in the context of the variable we are trying to evaluate.
Since x = 3 is found first, the value of x is 3.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> expression = &quot;(let x 3 x 2 x)&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> Assignment in let statements is processed sequentially.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> expression = &quot;(let x 1 y 2 x (add x y) (add x y))&quot;
<strong>Output:</strong> 5
<strong>Explanation:</strong> The first (add x y) evaluates as 3, and is assigned to x.
The second (add x y) evaluates as 3+2 = 5.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= expression.length &lt;= 2000</code></li>
	<li>There are no leading or trailing spaces in <code>expression</code>.</li>
	<li>All tokens are separated by a single space in <code>expression</code>.</li>
	<li>The answer and all intermediate calculations of that answer are guaranteed to fit in a <strong>32-bit</strong> integer.</li>
	<li>The expression is guaranteed to be legal and evaluate to an integer.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def evaluate(self, expression: str) -> int:
        def parseVar():
            nonlocal i
            j = i
            while i < n and expression[i] not in " )":
                i += 1
            return expression[j:i]

        def parseInt():
            nonlocal i
            sign, v = 1, 0
            if expression[i] == "-":
                sign = -1
                i += 1
            while i < n and expression[i].isdigit():
                v = v * 10 + int(expression[i])
                i += 1
            return sign * v

        def eval():
            nonlocal i
            if expression[i] != "(":
                return scope[parseVar()][-1] if expression[i].islower() else parseInt()
            i += 1
            if expression[i] == "l":
                i += 4
                vars = []
                while 1:
                    var = parseVar()
                    if expression[i] == ")":
                        ans = scope[var][-1]
                        break
                    vars.append(var)
                    i += 1
                    scope[var].append(eval())
                    i += 1
                    if not expression[i].islower():
                        ans = eval()
                        break
                for v in vars:
                    scope[v].pop()
            else:
                add = expression[i] == "a"
                i += 4 if add else 5
                a = eval()
                i += 1
                b = eval()
                ans = a + b if add else a * b
            i += 1
            return ans

        i, n = 0, len(expression)
        scope = defaultdict(list)
        return eval()
```

### **Java**

```java
class Solution {
    private int i;
    private String expr;
    private Map<String, Deque<Integer>> scope = new HashMap<>();

    public int evaluate(String expression) {
        expr = expression;
        return eval();
    }

    private int eval() {
        char c = expr.charAt(i);
        if (c != '(') {
            return Character.isLowerCase(c) ? scope.get(parseVar()).peekLast() : parseInt();
        }
        ++i;
        c = expr.charAt(i);
        int ans = 0;
        if (c == 'l') {
            i += 4;
            List<String> vars = new ArrayList<>();
            while (true) {
                String var = parseVar();
                if (expr.charAt(i) == ')') {
                    ans = scope.get(var).peekLast();
                    break;
                }
                vars.add(var);
                ++i;
                scope.computeIfAbsent(var, k -> new ArrayDeque<>()).offer(eval());
                ++i;
                if (!Character.isLowerCase(expr.charAt(i))) {
                    ans = eval();
                    break;
                }
            }
            for (String v : vars) {
                scope.get(v).pollLast();
            }
        } else {
            boolean add = c == 'a';
            i += add ? 4 : 5;
            int a = eval();
            ++i;
            int b = eval();
            ans = add ? a + b : a * b;
        }
        ++i;
        return ans;
    }

    private String parseVar() {
        int j = i;
        while (i < expr.length() && expr.charAt(i) != ' ' && expr.charAt(i) != ')') {
            ++i;
        }
        return expr.substring(j, i);
    }

    private int parseInt() {
        int sign = 1;
        if (expr.charAt(i) == '-') {
            sign = -1;
            ++i;
        }
        int v = 0;
        while (i < expr.length() && Character.isDigit(expr.charAt(i))) {
            v = v * 10 + (expr.charAt(i) - '0');
            ++i;
        }
        return sign * v;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int i = 0;
    string expr;
    unordered_map<string, vector<int>> scope;

    int evaluate(string expression) {
        expr = expression;
        return eval();
    }

    int eval() {
        if (expr[i] != '(') return islower(expr[i]) ? scope[parseVar()].back() : parseInt();
        int ans = 0;
        ++i;
        if (expr[i] == 'l') {
            i += 4;
            vector<string> vars;
            while (1) {
                string var = parseVar();
                if (expr[i] == ')') {
                    ans = scope[var].back();
                    break;
                }
                ++i;
                vars.push_back(var);
                scope[var].push_back(eval());
                ++i;
                if (!islower(expr[i])) {
                    ans = eval();
                    break;
                }
            }
            for (string v : vars) scope[v].pop_back();
        } else {
            bool add = expr[i] == 'a';
            i += add ? 4 : 5;
            int a = eval();
            ++i;
            int b = eval();
            ans = add ? a + b : a * b;
        }
        ++i;
        return ans;
    }

    string parseVar() {
        int j = i;
        while (i < expr.size() && expr[i] != ' ' && expr[i] != ')') ++i;
        return expr.substr(j, i - j);
    }

    int parseInt() {
        int sign = 1, v = 0;
        if (expr[i] == '-') {
            sign = -1;
            ++i;
        }
        while (i < expr.size() && expr[i] >= '0' && expr[i] <= '9') {
            v = v * 10 + (expr[i] - '0');
            ++i;
        }
        return sign * v;
    }
};
```

### **Go**

```go
func evaluate(expression string) int {
	i, n := 0, len(expression)
	scope := map[string][]int{}

	parseVar := func() string {
		j := i
		for ; i < n && expression[i] != ' ' && expression[i] != ')'; i++ {
		}
		return expression[j:i]
	}

	parseInt := func() int {
		sign, v := 1, 0
		if expression[i] == '-' {
			sign = -1
			i++
		}
		for ; i < n && expression[i] >= '0' && expression[i] <= '9'; i++ {
			v = (v * 10) + int(expression[i]-'0')
		}
		return sign * v
	}

	var eval func() int
	eval = func() int {
		if expression[i] != '(' {
			if unicode.IsLower(rune(expression[i])) {
				t := scope[parseVar()]
				return t[len(t)-1]
			}
			return parseInt()
		}
		i++
		ans := 0
		if expression[i] == 'l' {
			i += 4
			vars := []string{}
			for {
				v := parseVar()
				if expression[i] == ')' {
					t := scope[v]
					ans = t[len(t)-1]
					break
				}
				i++
				vars = append(vars, v)
				scope[v] = append(scope[v], eval())
				i++
				if !unicode.IsLower(rune(expression[i])) {
					ans = eval()
					break
				}
			}
			for _, v := range vars {
				scope[v] = scope[v][:len(scope[v])-1]
			}
		} else {
			add := expression[i] == 'a'
			if add {
				i += 4
			} else {
				i += 5
			}
			a := eval()
			i++
			b := eval()
			if add {
				ans = a + b
			} else {
				ans = a * b
			}
		}
		i++
		return ans
	}
	return eval()
}
```

### **...**

```

```

<!-- tabs:end -->
