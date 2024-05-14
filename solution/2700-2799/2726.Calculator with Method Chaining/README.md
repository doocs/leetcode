# [2726. 使用方法链的计算器](https://leetcode.cn/problems/calculator-with-method-chaining)

[English Version](/solution/2700-2799/2726.Calculator%20with%20Method%20Chaining/README_EN.md)

<!-- tags: -->

<!-- difficulty:简单 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>设计一个类 <code>Calculator</code> 。该类应提供加法、减法、乘法、除法和乘方等数学运算功能。同时，它还应支持连续操作的方法链式调用。<code>Calculator</code> 类的构造函数应接受一个数字作为 <code>result</code> 的初始值。</p>

<p>你的 <code>Calculator</code> 类应包含以下方法：</p>

<ul>
	<li><code>add</code> - 将给定的数字 <code>value</code> 与 <code>result</code> 相加，并返回更新后的 <code>Calculator</code> 对象。</li>
	<li><code>subtract</code> - 从 <code>result</code> 中减去给定的数字 <code>value</code>&nbsp;，并返回更新后的 <code>Calculator</code> 对象。</li>
	<li><code>multiply</code> - 将 <code>result</code> 乘以给定的数字 <code>value</code> ，并返回更新后的&nbsp;<code>Calculator</code> 对象。</li>
	<li><code>divide</code> - 将 <code>result</code> 除以给定的数字 <code>value</code> ，并返回更新后的&nbsp;<code>Calculator</code> 对象。如果传入的值为 <code>0</code> ，则抛出错误 <code>"Division by zero is not allowed"</code> 。</li>
	<li><code>power</code> - 计算 <code>result</code> 的幂，指数为给定的数字 <code>value</code> ，并返回更新后的&nbsp;<code>Calculator</code> 对象。（<code>result = result ^ value</code> ）</li>
	<li><code>getResult</code> - 返回 <code>result</code> 的值。</li>
</ul>

<p>结果与实际结果相差在 <code>10<sup>-5</sup></code><sup>&nbsp;</sup>范围内的解被认为是正确的。</p>

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<pre>
<b>输入：</b>actions = ["Calculator", "add", "subtract", "getResult"], 
values = [10, 5, 7]
<b>输出：</b>8
<b>解释：</b>
new Calculator(10).add(5).subtract(7).getResult() // 10 + 5 - 7 = 8
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>actions = ["Calculator", "multiply", "power", "getResult"], 
values = [2, 5, 2]
<b>输出：</b>100
<b>解释：</b>
new Calculator(2).multiply(5).power(2).getResult() // (2 * 5) ^ 2 = 100
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>actions = ["Calculator", "divide", "getResult"], 
values = [20, 0]
<b>输出：</b>"Division by zero is not allowed"
<b>解释：</b>
new Calculator(20).divide(0).getResult() // 20 / 0 

由于不能除以零，因此应抛出错误。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>actions</code>&nbsp;是一个有效的 JSON 字符串数组</li>
	<li><code>values</code>&nbsp;是一个有效的 JSON 字符串数组</li>
	<li><code>2 &lt;= actions.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= values.length &lt;= 2 * 10<sup>4</sup>&nbsp;- 1</code></li>
	<li><code>actions[i]</code>&nbsp;是 "Calculator", "add", "subtract", "multiply", "divide", "power", 和 "getResult" 其中的元素</li>
	<li>第一个操作总是 "Calculator"</li>
	<li>最后一个操作总是&nbsp;"getResult"</li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```ts
class Calculator {
    private x: number;

    constructor(value: number) {
        this.x = value;
    }

    add(value: number): Calculator {
        this.x += value;
        return this;
    }

    subtract(value: number): Calculator {
        this.x -= value;
        return this;
    }

    multiply(value: number): Calculator {
        this.x *= value;
        return this;
    }

    divide(value: number): Calculator {
        if (value === 0) {
            throw new Error('Division by zero is not allowed');
        }
        this.x /= value;
        return this;
    }

    power(value: number): Calculator {
        this.x **= value;
        return this;
    }

    getResult(): number {
        return this.x;
    }
}
```

<!-- tabs:end -->

<!-- end -->
