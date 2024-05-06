# [16.09. Operations](https://leetcode.cn/problems/operations-lcci)

[中文文档](/lcci/16.09.Operations/README.md)

## Description

<p>rite methods to implement the multiply, subtract, and divide operations for integers. The results of all of these are integers. Use only the add operator.</p>
<p>You should implement following methods:</p>
<ul>
	<li><code>Operations()</code>&nbsp; constructor</li>
	<li><code>minus(a, b)</code>&nbsp; Subtraction, returns&nbsp;<code>a - b</code></li>
	<li><code>multiply(a, b)</code>&nbsp; Multiplication, returns&nbsp;<code>a * b</code></li>
	<li><code>divide(a, b)</code>&nbsp; Division, returns&nbsp;<code>a / b</code></li>
</ul>
<p><strong>Example: </strong></p>
<pre>

Operations operations = new Operations();

operations.minus(1, 2); //returns -1

operations.multiply(3, 4); //returns 12

operations.divide(5, -2); //returns -2

</pre>
<p><strong>Note: </strong></p>
<ul>
    <li>You can assume inputs are always valid, that is, e.g., denominator will not be 0 in division.</li>
    <li>The number of calls will not exceed 1000.</li>
</ul>

## Solutions

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
