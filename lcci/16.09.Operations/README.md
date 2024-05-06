# [面试题 16.09. 运算](https://leetcode.cn/problems/operations-lcci)

[English Version](/lcci/16.09.Operations/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>请实现整数数字的乘法、减法和除法运算，运算结果均为整数数字，程序中只允许使用加法运算符和逻辑运算符，允许程序中出现正负常数，不允许使用位运算。</p>
<p>你的实现应该支持如下操作：</p>
<ul>
<li><code>Operations()</code> 构造函数</li>
<li><code>minus(a, b)</code> 减法，返回<code>a - b</code></li>
<li><code>multiply(a, b)</code> 乘法，返回<code>a * b</code></li>
<li><code>divide(a, b)</code> 除法，返回<code>a / b</code></li>
</ul>
<p><strong>示例：</strong></p>
<pre>Operations operations = new Operations();
operations.minus(1, 2); //返回-1
operations.multiply(3, 4); //返回12
operations.divide(5, -2); //返回-2
</pre>
<p><strong>提示：</strong></p>
<ul>
<li>你可以假设函数输入一定是有效的，例如不会出现除法分母为0的情况</li>
<li>单个用例的函数调用次数不会超过1000次</li>
</ul>

## 解法

<!-- tabs:start -->

```swift
class Solution {
    init() {}

    func minus(_ a: Int, _ b: Int) -> Int {
        var b = b
        let sign = b > 0 ? -1 : 1
        var result = a
        while b != 0 {
            result += sign
            b += sign
        }
        return result
    }

    func multiply(_ a: Int, _ b: Int) -> Int {
        if a == 0 || b == 0 {
            return 0
        }

        let absA = a > 0 ? a : minus(0, a)
        let absB = b > 0 ? b : minus(0, b)
        var result = 0

        for _ in 0..<absB {
            result += absA
        }

        if (a > 0) != (b > 0) {
            result = minus(0, result)
        }

        return result
    }

    func divide(_ a: Int, _ b: Int) -> Int {
        let absA = a > 0 ? a : minus(0, a)
        let absB = b > 0 ? b : minus(0, b)
        var result = 0
        var sum = absB

        while sum <= absA {
            result += 1
            sum += absB
        }

        if (a > 0) != (b > 0) {
            result = minus(0, result)
        }

        return result
    }
}
```

<!-- tabs:end -->

<!-- end -->
