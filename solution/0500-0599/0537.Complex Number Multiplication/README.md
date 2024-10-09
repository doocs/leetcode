---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0537.Complex%20Number%20Multiplication/README.md
tags:
    - 数学
    - 字符串
    - 模拟
---

<!-- problem:start -->

# [537. 复数乘法](https://leetcode.cn/problems/complex-number-multiplication)

[English Version](/solution/0500-0599/0537.Complex%20Number%20Multiplication/README_EN.md)

## 题目描述

<!-- description:start -->

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

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们可以将复数字符串转换成对应的实部 $a$ 和虚部 $b$，然后根据复数乘法的公式 $(a_1 + b_1i) \times (a_2 + b_2i) = (a_1a_2 - b_1b_2) + (a_1b_2 + a_2b_1)i$ 计算出结果。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def complexNumberMultiply(self, num1: str, num2: str) -> str:
        a1, b1 = map(int, num1[:-1].split("+"))
        a2, b2 = map(int, num2[:-1].split("+"))
        return f"{a1 * a2 - b1 * b2}+{a1 * b2 + a2 * b1}i"
```

#### Java

```java
class Solution {
    public String complexNumberMultiply(String num1, String num2) {
        int[] x = parse(num1);
        int[] y = parse(num2);
        int a1 = x[0], b1 = x[1], a2 = y[0], b2 = y[1];
        return (a1 * a2 - b1 * b2) + "+" + (a1 * b2 + a2 * b1) + "i";
    }

    private int[] parse(String s) {
        var cs = s.substring(0, s.length() - 1).split("\\+");
        return new int[] {Integer.parseInt(cs[0]), Integer.parseInt(cs[1])};
    }
}
```

#### C++

```cpp
class Solution {
public:
    string complexNumberMultiply(string num1, string num2) {
        int a1, b1, a2, b2;
        sscanf(num1.c_str(), "%d+%di", &a1, &b1);
        sscanf(num2.c_str(), "%d+%di", &a2, &b2);
        return to_string(a1 * a2 - b1 * b2) + "+" + to_string(a1 * b2 + a2 * b1) + "i";
    }
};
```

#### Go

```go
func complexNumberMultiply(num1 string, num2 string) string {
	x, _ := strconv.ParseComplex(num1, 64)
	y, _ := strconv.ParseComplex(num2, 64)
	return fmt.Sprintf("%d+%di", int(real(x*y)), int(imag(x*y)))
}
```

#### TypeScript

```ts
function complexNumberMultiply(num1: string, num2: string): string {
    const [a1, b1] = num1.slice(0, -1).split('+').map(Number);
    const [a2, b2] = num2.slice(0, -1).split('+').map(Number);
    return `${a1 * a2 - b1 * b2}+${a1 * b2 + a2 * b1}i`;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
