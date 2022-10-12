# [592. Fraction Addition and Subtraction](https://leetcode.com/problems/fraction-addition-and-subtraction)

[中文文档](/solution/0500-0599/0592.Fraction%20Addition%20and%20Subtraction/README.md)

## Description

<p>Given a string <code>expression</code> representing an expression of fraction addition and subtraction, return the calculation result in string format.</p>

<p>The final result should be an <a href="https://en.wikipedia.org/wiki/Irreducible_fraction" target="_blank">irreducible fraction</a>. If your final result is an integer, change it to the format of a fraction that has a denominator <code>1</code>. So in this case, <code>2</code> should be converted to <code>2/1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> expression = &quot;-1/2+1/2&quot;
<strong>Output:</strong> &quot;0/1&quot;
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> expression = &quot;-1/2+1/2+1/3&quot;
<strong>Output:</strong> &quot;1/3&quot;
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> expression = &quot;1/3-1/2&quot;
<strong>Output:</strong> &quot;-1/6&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The input string only contains <code>&#39;0&#39;</code> to <code>&#39;9&#39;</code>, <code>&#39;/&#39;</code>, <code>&#39;+&#39;</code> and <code>&#39;-&#39;</code>. So does the output.</li>
	<li>Each fraction (input and output) has the format <code>&plusmn;numerator/denominator</code>. If the first input fraction or the output is positive, then <code>&#39;+&#39;</code> will be omitted.</li>
	<li>The input only contains valid <strong>irreducible fractions</strong>, where the <strong>numerator</strong> and <strong>denominator</strong> of each fraction will always be in the range <code>[1, 10]</code>. If the denominator is <code>1</code>, it means this fraction is actually an integer in a fraction format defined above.</li>
	<li>The number of given fractions will be in the range <code>[1, 10]</code>.</li>
	<li>The numerator and denominator of the <strong>final result</strong> are guaranteed to be valid and in the range of <strong>32-bit</strong> int.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
