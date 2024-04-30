# [2803. 阶乘生成器 🔒](https://leetcode.cn/problems/factorial-generator)

[English Version](/solution/2800-2899/2803.Factorial%20Generator/README_EN.md)

<!-- tags: -->

## 题目描述

<!-- 这里写题目描述 -->

<p>编写一个生成器函数，该函数以一个整数 <code>n</code> 作为参数，并返回一个生成器对象，该生成器对象可以生成 <strong>阶乘序列</strong> 。</p>

<p><strong>阶乘序列</strong> 的定义如下：<code>n! = n * (n-1) * (n-2) * ... * 2 * 1</code>&nbsp;。</p>

<p>0 的阶乘被定义为 1。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>n = 5
<b>输出：</b>[1,2,6,24,120]
<b>解释：</b>
const gen = factorial(5)
gen.next().value // 1
gen.next().value // 2
gen.next().value // 6
gen.next().value // 24
gen.next().value // 120
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>n = 2
<b>输出：</b>[1,2]
<b>解释：</b>
const gen = factorial(2) 
gen.next().value // 1 
gen.next().value // 2 
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>n = 0
<b>输出：</b>[1]
<b>解释：</b>
const gen = factorial(0) 
gen.next().value // 1 
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 18</code></li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```ts
function* factorial(n: number): Generator<number> {
    if (n === 0) {
        yield 1;
    }
    let ans = 1;
    for (let i = 1; i <= n; ++i) {
        ans *= i;
        yield ans;
    }
}

/**
 * const gen = factorial(2);
 * gen.next().value; // 1
 * gen.next().value; // 2
 */
```

<!-- tabs:end -->

<!-- end -->
