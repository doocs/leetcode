---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3749.Evaluate%20Valid%20Expressions/README.md
---

<!-- problem:start -->

# [3749. 计算有效表达式 🔒](https://leetcode.cn/problems/evaluate-valid-expressions)

[English Version](/solution/3700-3799/3749.Evaluate%20Valid%20Expressions/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个字符串&nbsp;<code>expression</code> 表示以简化形式嵌套的数学表达式。</p>

<p>一个 <strong>合法</strong>&nbsp;表达式要么是一个整数&nbsp;<strong>字面量</strong>，要么符合&nbsp;<code>op(a,b)</code>&nbsp;格式，其中：</p>

<ul>
	<li><code>op</code>&nbsp;是&nbsp;<code>"add"</code>，<code>"sub"</code>，<code>"mul"</code>，<code>"div"</code>&nbsp;中的一个。</li>
	<li><code>a</code> 和&nbsp;<code>b</code>&nbsp;都是合法表达式。</li>
</ul>

<p><strong>运算符</strong> 定义如下：</p>

<ul>
	<li><code>add(a,b) = a + b</code></li>
	<li><code>sub(a,b) = a - b</code></li>
	<li><code>mul(a,b) = a * b</code></li>
	<li><code>div(a,b) = a / b</code></li>
</ul>

<p>返回一个整数，表示在完全计算表达式后的结果。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">expression = "add(2,3)"</span></p>

<p><strong>输出：</strong><span class="example-io">5</span></p>

<p><strong>解释：</strong></p>

<p>运算&nbsp;<code>add(2,3)</code> 表示&nbsp;<code>2 + 3 = 5</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>expression = "-42"</span></p>

<p><strong>输出：</strong><span class="example-io">-42</span></p>

<p><strong>解释：</strong></p>

<p>这个表达式是单个整数字面量，所以结果是 -42。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>expression = "div(mul(4,sub(9,5)),add(1,1))"</span></p>

<p><span class="example-io"><b>输出：</b>8</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>首先，计算内部表达式：<code>sub(9,5) = 9 - 5 = 4</code></li>
	<li>接下来，将结果相乘：<code>mul(4,4) = 4 * 4 = 16</code></li>
	<li>然后，计算右边的加法：<code>add(1,1) = 1 + 1 = 2</code></li>
	<li>最后，将两个主要结果相除：<code>div(16,2) = 16 / 2 = 8</code></li>
</ul>

<p>因此，整个表达式的结果是 8。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= expression.length &lt;= 10<sup>5</sup></code></li>
	<li><code>expression</code> 是有效的，由数字、逗号、括号、减号 <code>'-'</code> 和小写字符串&nbsp;<code>"add"</code>，<code>"sub"</code>，<code>"mul"</code>，<code>"div"</code>&nbsp;组成。</li>
	<li>所有中间结果都位于长整型的范围内。</li>
	<li>所有除法运算结果都是整数值。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：递归

我们定义一个递归函数 $\text{parse}(i)$，用于解析从索引 $i$ 开始的子表达式并返回计算结果以及下一个未处理的索引位置。那么答案为 $\text{parse}(0)[0]$。

函数 $\text{parse}(i)$ 的实现如下：

1. 如果当前位置 $i$ 处是一个数字或负号 `-`，则继续向后扫描直到遇到非数字字符，解析出一个整数并返回该整数以及下一个未处理的索引位置。
2. 否则，当前位置 $i$ 处是一个操作符 `op` 的起始位置，我们继续向后扫描直到遇到左括号 `(`，解析出操作符字符串 `op`。然后跳过左括号，递归调用 $\text{parse}$ 解析第一个参数 $a$，再跳过逗号，递归调用 $\text{parse}$ 解析第二个参数 $b$，最后跳过右括号 `)`。
3. 根据操作符 `op`，计算 $a$ 和 $b$ 的结果，并返回该结果以及下一个未处理的索引位置。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是表达式字符串的长度。

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
