# [537. Complex Number Multiplication](https://leetcode.com/problems/complex-number-multiplication)

[中文文档](/solution/0500-0599/0537.Complex%20Number%20Multiplication/README.md)

## Description

<p>A <a href="https://en.wikipedia.org/wiki/Complex_number" target="_blank">complex number</a> can be represented as a string on the form <code>&quot;<strong>real</strong>+<strong>imaginary</strong>i&quot;</code> where:</p>

<ul>
	<li><code>real</code> is the real part and is an integer in the range <code>[-100, 100]</code>.</li>
	<li><code>imaginary</code> is the imaginary part and is an integer in the range <code>[-100, 100]</code>.</li>
	<li><code>i<sup>2</sup> == -1</code>.</li>
</ul>

<p>Given two complex numbers <code>num1</code> and <code>num2</code> as strings, return <em>a string of the complex number that represents their multiplications</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num1 = &quot;1+1i&quot;, num2 = &quot;1+1i&quot;
<strong>Output:</strong> &quot;0+2i&quot;
<strong>Explanation:</strong> (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you need convert it to the form of 0+2i.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num1 = &quot;1+-1i&quot;, num2 = &quot;1+-1i&quot;
<strong>Output:</strong> &quot;0+-2i&quot;
<strong>Explanation:</strong> (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>num1</code> and <code>num2</code> are valid complex numbers.</li>
</ul>

## Solutions

`(a+bi)(c+di) = ac-bd+(ad+cb)i`

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def complexNumberMultiply(self, num1: str, num2: str) -> str:
        a, b = map(int, num1[:-1].split('+'))
        c, d = map(int, num2[:-1].split('+'))
        return f'{a * c - b * d}+{a * d + c * b}i'
```

### **Java**

```java
class Solution {
    public String complexNumberMultiply(String num1, String num2) {
        String[] c1 = num1.split("\\+|i");
        String[] c2 = num2.split("\\+|i");
        int a = Integer.parseInt(c1[0]);
        int b = Integer.parseInt(c1[1]);
        int c = Integer.parseInt(c2[0]);
        int d = Integer.parseInt(c2[1]);
        return String.format("%d+%di", a * c - b * d, a * d + c * b);
    }
}
```

### **TypeScript**

```ts
function complexNumberMultiply(num1: string, num2: string): string {
    let arr1 = num1.split('+'),
        arr2 = num2.split('+');
    let r1 = Number(arr1[0]),
        r2 = Number(arr2[0]);
    let v1 = Number(arr1[1].substring(0, arr1[1].length - 1)),
        v2 = Number(arr2[1].substring(0, arr2[1].length - 1));
    let ansR = r1 * r2 - v1 * v2;
    let ansV = r1 * v2 + r2 * v1;
    return `${ansR}+${ansV}i`;
}
```

### **C++**

```cpp
class Solution {
public:
    string complexNumberMultiply(string num1, string num2) {
        int a, b, c, d;
        sscanf(num1.c_str(), "%d+%di", &a, &b);
        sscanf(num2.c_str(), "%d+%di", &c, &d);
        return string(to_string(a * c - b * d) + "+" + to_string(a * d + c * b) + "i");
    }
};
```

### **Go**

```go
func complexNumberMultiply(num1, num2 string) string {
	parse := func(num string) (a, b int) {
		i := strings.IndexByte(num, '+')
		a, _ = strconv.Atoi(num[:i])
		b, _ = strconv.Atoi(num[i+1 : len(num)-1])
		return
	}
	a, b := parse(num1)
	c, d := parse(num2)
	return fmt.Sprintf("%d+%di", a*c-b*d, a*d+b*c)
}
```

### **...**

```

```

<!-- tabs:end -->
