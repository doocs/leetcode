# [371. 两整数之和](https://leetcode.cn/problems/sum-of-two-integers)

[English Version](/solution/0300-0399/0371.Sum%20of%20Two%20Integers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个整数 <code>a</code> 和 <code>b</code> ，<strong>不使用 </strong>运算符&nbsp;<code>+</code> 和&nbsp;<code>-</code>&nbsp;​​​​​​​，计算并返回两整数之和。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>a = 1, b = 2
<strong>输出：</strong>3
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>a = 2, b = 3
<strong>输出：</strong>5
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>-1000 &lt;= a, b &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

两数字的二进制形式 a,b ，求和 s = a + b ，a(i)、b(i) 分别表示 a、b 的第 i 个二进制位。一共有 4 种情况：

| a(i) | b(i) | 不进位的和 | 进位 |
| ---- | ---- | ---------- | ---- |
| 0    | 0    | 0          | 0    |
| 0    | 1    | 1          | 0    |
| 1    | 0    | 1          | 0    |
| 1    | 1    | 0          | 1    |

观察可以发现，“不进位的和”与“异或运算”有相同规律，而进位则与“与”运算规律相同，并且需要左移一位。

-   对两数进行按位 `^` 异或运算，得到不进位的和；
-   对两数进行按位 `&` 与运算，然后左移一位，得到进位；
-   问题转换为求：“不进位的数 + 进位” 之和；
-   循环，直至进位为 0，返回不进位的数即可（也可以用递归实现）。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

由于 python `int` 是无限长整型，左移不会自动溢出，因此需要特殊处理。

```python
class Solution:
    def getSum(self, a: int, b: int) -> int:
        a, b = a & 0xFFFFFFFF, b & 0xFFFFFFFF
        while b:
            carry = ((a & b) << 1) & 0xFFFFFFFF
            a, b = a ^ b, carry
        return a if a < 0x80000000 else ~(a ^ 0xFFFFFFFF)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int getSum(int a, int b) {
        return b == 0 ? a : getSum(a ^ b, (a & b) << 1);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int getSum(int a, int b) {
        while (b) {
            unsigned int carry = (unsigned int)(a & b) << 1;
            a = a ^ b;
            b = carry;
        }
        return a;
    }
};
```

### **Go**

```go
func getSum(a int, b int) int {
	for b != 0 {
		s := a ^ b
		b = (a & b) << 1
		a = s
	}
	return a
}
```

### **...**

```

```

<!-- tabs:end -->
