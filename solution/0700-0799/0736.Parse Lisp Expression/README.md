# [736. Lisp 语法解析](https://leetcode.cn/problems/parse-lisp-expression)

[English Version](/solution/0700-0799/0736.Parse%20Lisp%20Expression/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个类似 Lisp 语句的字符串表达式 <code>expression</code>，求出其计算结果。</p>

<p>表达式语法如下所示:</p>

<ul>
	<li>表达式可以为整数，<strong>let</strong> 表达式，<strong>add</strong> 表达式，<strong>mult</strong> 表达式，或赋值的变量。表达式的结果总是一个整数。</li>
	<li>(整数可以是正整数、负整数、0)</li>
	<li><strong>let</strong> 表达式采用&nbsp;<code>"(let v<sub>1</sub> e<sub>1</sub> v<sub>2</sub> e<sub>2</sub> ... v<sub>n</sub> e<sub>n</sub> expr)"</code> 的形式，其中&nbsp;<code>let</code> 总是以字符串&nbsp;<code>"let"</code>来表示，接下来会跟随一对或多对交替的变量和表达式，也就是说，第一个变量&nbsp;<code>v<sub>1</sub></code>被分配为表达式&nbsp;<code>e<sub>1</sub></code>&nbsp;的值，第二个变量&nbsp;<code>v<sub>2</sub></code>&nbsp;被分配为表达式&nbsp;<code>e<sub>2</sub></code>&nbsp;的值，<strong>依次类推</strong>；最终 <code>let</code> 表达式的值为&nbsp;<code>expr</code>表达式的值。</li>
	<li><strong>add </strong>表达式表示为&nbsp;<code>"(add e<sub>1</sub> e<sub>2</sub>)"</code> ，其中&nbsp;<code>add</code> 总是以字符串&nbsp;<code>"add"</code> 来表示，该表达式总是包含两个表达式 <code>e<sub>1</sub></code>、<code>e<sub>2</sub></code> ，最终结果是&nbsp;<code>e<sub>1</sub></code> 表达式的值与&nbsp;<code>e<sub>2</sub></code>&nbsp;表达式的值之 <strong>和 </strong>。</li>
	<li><strong>mult</strong> 表达式表示为&nbsp;<code>"(mult e<sub>1</sub> e<sub>2</sub>)"</code>&nbsp;，其中&nbsp;<code>mult</code> 总是以字符串 <code>"mult"</code> 表示，该表达式总是包含两个表达式 <code>e<sub>1</sub></code>、<code>e<sub>2</sub></code>，最终结果是&nbsp;<code>e<sub>1</sub></code> 表达式的值与&nbsp;<code>e<sub>2</sub></code>&nbsp;表达式的值之<strong> 积 </strong>。</li>
	<li>在该题目中，变量名以小写字符开始，之后跟随 0 个或多个小写字符或数字。为了方便，<code>"add"</code> ，<code>"let"</code> ，<code>"mult"</code> 会被定义为 "关键字" ，不会用作变量名。</li>
	<li>最后，要说一下作用域的概念。计算变量名所对应的表达式时，在计算上下文中，首先检查最内层作用域（按括号计），然后按顺序依次检查外部作用域。测试用例中每一个表达式都是合法的。有关作用域的更多详细信息，请参阅示例。</li>
</ul>
&nbsp;

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>expression = "(let x 2 (mult x (let x 3 y 4 (add x y))))"
<strong>输出：</strong>14
<strong>解释：</strong>
计算表达式 (add x y), 在检查变量 x 值时，
在变量的上下文中由最内层作用域依次向外检查。
首先找到 x = 3, 所以此处的 x 值是 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>expression = "(let x 3 x 2 x)"
<strong>输出：</strong>2
<strong>解释：</strong>let 语句中的赋值运算按顺序处理即可。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>expression = "(let x 1 y 2 x (add x y) (add x y))"
<strong>输出：</strong>5
<strong>解释：</strong>
第一个 (add x y) 计算结果是 3，并且将此值赋给了 x 。 
第二个 (add x y) 计算结果是 3 + 2 = 5 。
</pre>

&nbsp;

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= expression.length &lt;= 2000</code></li>
	<li><code>exprssion</code> 中不含前导和尾随空格</li>
	<li><code>expressoin</code> 中的不同部分（token）之间用单个空格进行分隔</li>
	<li>答案和所有中间计算结果都符合 <strong>32-bit</strong> 整数范围</li>
	<li>测试用例中的表达式均为合法的且最终结果为整数</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：递归**

时间复杂度 $O(n)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
