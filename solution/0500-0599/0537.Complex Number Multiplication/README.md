# [537. 复数乘法](https://leetcode.cn/problems/complex-number-multiplication)

[English Version](/solution/0500-0599/0537.Complex%20Number%20Multiplication/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><a href="https://baike.baidu.com/item/%E5%A4%8D%E6%95%B0/254365?fr=aladdin" target="_blank">复数</a> 可以用字符串表示，遵循 <code>"<strong>实部</strong>+<strong>虚部</strong>i"</code> 的形式，并满足下述条件：</p>

<ul>
	<li><code>实部</code> 是一个整数，取值范围是 <code>[-100, 100]</code></li>
	<li><code>虚部</code> 也是一个整数，取值范围是 <code>[-100, 100]</code></li>
	<li><code>i<sup>2</sup> == -1</code></li>
</ul>

<p>给你两个字符串表示的复数 <code>num1</code> 和 <code>num2</code> ，请你遵循复数表示形式，返回表示它们乘积的字符串。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>num1 = "1+1i", num2 = "1+1i"
<strong>输出：</strong>"0+2i"
<strong>解释：</strong>(1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i ，你需要将它转换为 0+2i 的形式。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>num1 = "1+-1i", num2 = "1+-1i"
<strong>输出：</strong>"0+-2i"
<strong>解释：</strong>(1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i ，你需要将它转换为 0+-2i 的形式。 
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>num1</code> 和 <code>num2</code> 都是有效的复数表示。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

`(a+bi)(c+di) = ac-bd+(ad+cb)i`。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def complexNumberMultiply(self, num1: str, num2: str) -> str:
        a, b = map(int, num1[:-1].split('+'))
        c, d = map(int, num2[:-1].split('+'))
        return f'{a * c - b * d}+{a * d + c * b}i'
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
