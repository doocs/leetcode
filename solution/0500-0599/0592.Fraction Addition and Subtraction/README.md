# [592. 分数加减运算](https://leetcode.cn/problems/fraction-addition-and-subtraction)

[English Version](/solution/0500-0599/0592.Fraction%20Addition%20and%20Subtraction/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个表示分数加减运算的字符串&nbsp;<code>expression</code>&nbsp;，你需要返回一个字符串形式的计算结果。&nbsp;</p>

<p>这个结果应该是不可约分的分数，即<a href="https://baike.baidu.com/item/%E6%9C%80%E7%AE%80%E5%88%86%E6%95%B0" target="_blank">最简分数</a>。&nbsp;如果最终结果是一个整数，例如&nbsp;<code>2</code>，你需要将它转换成分数形式，其分母为&nbsp;<code>1</code>。所以在上述例子中, <code>2</code>&nbsp;应该被转换为&nbsp;<code>2/1</code>。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre>
<strong>输入:</strong>&nbsp;<code>expression</code>&nbsp;= "-1/2+1/2"
<strong>输出:</strong> "0/1"
</pre>

<p><strong>&nbsp;示例 2:</strong></p>

<pre>
<strong>输入:</strong>&nbsp;<code>expression</code>&nbsp;= "-1/2+1/2+1/3"
<strong>输出:</strong> "1/3"
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong>&nbsp;<code>expression</code>&nbsp;= "1/3-1/2"
<strong>输出:</strong> "-1/6"
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li>输入和输出字符串只包含&nbsp;<code>'0'</code> 到&nbsp;<code>'9'</code>&nbsp;的数字，以及&nbsp;<code>'/'</code>, <code>'+'</code> 和&nbsp;<code>'-'</code>。&nbsp;</li>
	<li>输入和输出分数格式均为&nbsp;<code>±分子/分母</code>。如果输入的第一个分数或者输出的分数是正数，则&nbsp;<code>'+'</code>&nbsp;会被省略掉。</li>
	<li>输入只包含合法的<strong>最简分数</strong>，每个分数的<strong>分子</strong>与<strong>分母</strong>的范围是&nbsp;&nbsp;[1,10]。&nbsp;如果分母是1，意味着这个分数实际上是一个整数。</li>
	<li>输入的分数个数范围是 [1,10]。</li>
	<li><strong>最终结果</strong>的分子与分母保证是 32 位整数范围内的有效整数。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数学**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def fractionAddition(self, expression: str) -> str:
        x, y = 0, 6 * 7 * 8 * 9 * 10
        if expression[0].isdigit():
            expression = '+' + expression
        i, n = 0, len(expression)
        while i < n:
            sign = -1 if expression[i] == '-' else 1
            i += 1
            j = i
            while j < n and expression[j] not in '+-':
                j += 1
            s = expression[i:j]
            a, b = s.split('/')
            x += sign * int(a) * y // int(b)
            i = j
        z = gcd(x, y)
        x //= z
        y //= z
        return f'{x}/{y}'
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String fractionAddition(String expression) {
        int x = 0, y = 6 * 7 * 8 * 9 * 10;
        if (Character.isDigit(expression.charAt(0))) {
            expression = "+" + expression;
        }
        int i = 0, n = expression.length();
        while (i < n) {
            int sign = expression.charAt(i) == '-' ? -1 : 1;
            ++i;
            int j = i;
            while (j < n && expression.charAt(j) != '+' && expression.charAt(j) != '-') {
                ++j;
            }
            String s = expression.substring(i, j);
            String[] t = s.split("/");
            int a = Integer.parseInt(t[0]), b = Integer.parseInt(t[1]);
            x += sign * a * y / b;
            i = j;
        }
        int z = gcd(Math.abs(x), y);
        x /= z;
        y /= z;
        return x + "/" + y;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

### **Go**

```go
func fractionAddition(expression string) string {
	x, y := 0, 6*7*8*9*10
	if unicode.IsDigit(rune(expression[0])) {
		expression = "+" + expression
	}
	i, n := 0, len(expression)
	for i < n {
		sign := 1
		if expression[i] == '-' {
			sign = -1
		}
		i++
		j := i
		for j < n && expression[j] != '+' && expression[j] != '-' {
			j++
		}
		s := expression[i:j]
		t := strings.Split(s, "/")
		a, _ := strconv.Atoi(t[0])
		b, _ := strconv.Atoi(t[1])
		x += sign * a * y / b
		i = j
	}
	z := gcd(abs(x), y)
	x /= z
	y /= z
	return fmt.Sprintf("%d/%d", x, y)
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}
```

### **...**

```

```

<!-- tabs:end -->
