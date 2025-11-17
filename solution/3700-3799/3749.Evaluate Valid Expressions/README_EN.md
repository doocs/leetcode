---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3749.Evaluate%20Valid%20Expressions/README_EN.md
---

<!-- problem:start -->

# [3749. Evaluate Valid Expressions 🔒](https://leetcode.com/problems/evaluate-valid-expressions)

[中文文档](/solution/3700-3799/3749.Evaluate%20Valid%20Expressions/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>expression</code> that represents a nested mathematical expression in a simplified form.</p>

<p>A <strong>valid</strong> expression is either an integer <strong>literal</strong> or follows the format <code>op(a,b)</code>, where:</p>

<ul>
	<li><code>op</code> is one of <code>&quot;add&quot;</code>, <code>&quot;sub&quot;</code>, <code>&quot;mul&quot;</code>, or <code>&quot;div&quot;</code>.</li>
	<li><code>a</code> and <code>b</code> are each valid expressions.</li>
</ul>

<p>The <strong>operations</strong> are defined as follows:</p>

<ul>
	<li><code>add(a,b) = a + b</code></li>
	<li><code>sub(a,b) = a - b</code></li>
	<li><code>mul(a,b) = a * b</code></li>
	<li><code>div(a,b) = a / b</code></li>
</ul>

<p>Return an integer representing the <strong>result</strong> after fully evaluating the expression.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">expression = &quot;add(2,3)&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p>The operation <code>add(2,3)</code> means <code>2 + 3 = 5</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">expression = &quot;-42&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">-42</span></p>

<p><strong>Explanation:</strong></p>

<p>The expression is a single integer literal, so the result is -42.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">expression = &quot;div(mul(4,sub(9,5)),add(1,1))&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">8</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>First, evaluate the inner expression: <code>sub(9,5) = 9 - 5 = 4</code></li>
	<li>Next, multiply the results: <code>mul(4,4) = 4 * 4 = 16</code></li>
	<li>Then, compute the addition on the right: <code>add(1,1) = 1 + 1 = 2</code></li>
	<li>Finally, divide the two main results: <code>div(16,2) = 16 / 2 = 8</code></li>
</ul>

<p>Therefore, the entire expression evaluates to 8.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= expression.length &lt;= 10<sup>5</sup></code></li>
	<li><code>expression</code> is valid and consists of digits, commas, parentheses, the minus sign <code>&#39;-&#39;</code>, and the lowercase strings <code>&quot;add&quot;</code>, <code>&quot;sub&quot;</code>, <code>&quot;mul&quot;</code>, <code>&quot;div&quot;</code>.</li>
	<li>All intermediate results fit within the range of a long integer.</li>
	<li>All divisions result in integer values.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Recursion

We define a recursive function $\text{parse}(i)$ to parse the subexpression starting from index $i$ and return the computed result along with the next unprocessed index position. The answer is $\text{parse}(0)[0]$.

The implementation of the function $\text{parse}(i)$ is as follows:

1. If the current position $i$ is a digit or a negative sign `-`, continue scanning forward until a non-digit character is encountered, parse an integer, and return that integer along with the next unprocessed index position.
2. Otherwise, the current position $i$ is the starting position of an operator `op`. We continue scanning forward until we encounter a left parenthesis `(`, parsing the operator string `op`. Then we skip the left parenthesis, recursively call $\text{parse}$ to parse the first parameter $a$, skip the comma, recursively call $\text{parse}$ to parse the second parameter $b$, and finally skip the right parenthesis `)`.
3. Based on the operator `op`, calculate the result of $a$ and $b$, and return that result along with the next unprocessed index position.

The time complexity is $O(n)$ and the space complexity is $O(n)$, where $n$ is the length of the expression string.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def evaluateExpression(self, expression: str) -> int:
        def parse(i: int) -> (int, int):
            if expression[i].isdigit() or expression[i] == "-":
                j = i
                if expression[j] == "-":
                    j += 1
                while j < len(expression) and expression[j].isdigit():
                    j += 1
                return int(expression[i:j]), j

            j = i
            while expression[j] != "(":
                j += 1
            op = expression[i:j]
            j += 1
            val1, j = parse(j)

            j += 1
            val2, j = parse(j)
            j += 1
            res = 0
            match op:
                case "add":
                    res = val1 + val2
                case "sub":
                    res = val1 - val2
                case "mul":
                    res = val1 * val2
                case "div":
                    res = val1 // val2
            return res, j

        return parse(0)[0]
```

#### Java

```java
class Solution {
    private String expression;

    public long evaluateExpression(String expression) {
        this.expression = expression;
        return parse(0)[0];
    }

    private long[] parse(int i) {
        if (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '-') {
            int j = i;
            if (expression.charAt(j) == '-') {
                j++;
            }
            while (j < expression.length() && Character.isDigit(expression.charAt(j))) {
                j++;
            }
            long num = Long.parseLong(expression.substring(i, j));
            return new long[] {num, j};
        }

        int j = i;
        while (expression.charAt(j) != '(') {
            j++;
        }
        String op = expression.substring(i, j);
        j++;

        long[] result1 = parse(j);
        long val1 = result1[0];
        j = (int) result1[1];
        j++;

        long[] result2 = parse(j);
        long val2 = result2[0];
        j = (int) result2[1];
        j++;

        long res = 0;
        switch (op) {
        case "add":
            res = val1 + val2;
            break;
        case "sub":
            res = val1 - val2;
            break;
        case "mul":
            res = val1 * val2;
            break;
        case "div":
            res = val1 / val2;
            break;
        }

        return new long[] {res, j};
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long evaluateExpression(string expression) {
        auto parse = [&](this auto&& parse, int i) -> pair<long long, int> {
            if (isdigit(expression[i]) || expression[i] == '-') {
                int j = i;
                if (expression[j] == '-') {
                    j++;
                }
                while (j < expression.size() && isdigit(expression[j])) {
                    j++;
                }
                long long num = stoll(expression.substr(i, j - i));
                return {num, j};
            }

            int j = i;
            while (expression[j] != '(') {
                j++;
            }
            string op = expression.substr(i, j - i);
            j++;

            auto [val1, next_j1] = parse(j);
            j = next_j1 + 1;

            auto [val2, next_j2] = parse(j);
            j = next_j2 + 1;

            long long res = 0;
            if (op == "add") {
                res = val1 + val2;
            } else if (op == "sub") {
                res = val1 - val2;
            } else if (op == "mul") {
                res = val1 * val2;
            } else if (op == "div") {
                res = val1 / val2;
            }

            return {res, j};
        };

        return parse(0).first;
    }
};
```

#### Go

```go
func evaluateExpression(expression string) int64 {
	var parse func(int) (int64, int)
	parse = func(i int) (int64, int) {
		if expression[i] >= '0' && expression[i] <= '9' || expression[i] == '-' {
			j := i
			if expression[j] == '-' {
				j++
			}
			for j < len(expression) && expression[j] >= '0' && expression[j] <= '9' {
				j++
			}
			num, _ := strconv.ParseInt(expression[i:j], 10, 64)
			return num, j
		}

		j := i
		for expression[j] != '(' {
			j++
		}
		op := expression[i:j]
		j++

		val1, nextJ1 := parse(j)
		j = nextJ1 + 1

		val2, nextJ2 := parse(j)
		j = nextJ2 + 1

		var res int64
		switch op {
		case "add":
			res = val1 + val2
		case "sub":
			res = val1 - val2
		case "mul":
			res = val1 * val2
		case "div":
			res = val1 / val2
		}

		return res, j
	}

	result, _ := parse(0)
	return result
}
```

#### TypeScript

```ts
function evaluateExpression(expression: string): number {
    function parse(i: number): [number, number] {
        if (/\d/.test(expression[i]) || expression[i] === '-') {
            let j = i;
            if (expression[j] === '-') {
                j++;
            }
            while (j < expression.length && /\d/.test(expression[j])) {
                j++;
            }
            const num = +expression.slice(i, j);
            return [num, j];
        }

        let j = i;
        while (expression[j] !== '(') {
            j++;
        }
        const op = expression.slice(i, j);
        j++;

        const [val1, nextJ1] = parse(j);
        j = nextJ1 + 1;

        const [val2, nextJ2] = parse(j);
        j = nextJ2 + 1;

        let res: number;
        switch (op) {
            case 'add':
                res = val1 + val2;
                break;
            case 'sub':
                res = val1 - val2;
                break;
            case 'mul':
                res = val1 * val2;
                break;
            case 'div':
                res = Math.floor(val1 / val2);
                break;
            default:
                res = 0;
        }

        return [res, j];
    }

    return parse(0)[0];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
