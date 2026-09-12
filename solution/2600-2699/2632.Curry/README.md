---
comments: true
difficulty: 中等
tags:
    - JavaScript
---

<!-- problem:start -->

# [2632. 柯里化 🔒](https://leetcode.cn/problems/curry)

[English Version](/solution/2600-2699/2632.Curry/README_EN.md)

## 题目描述

<!-- description:start -->

<p>请你编写一个函数，它接收一个其他的函数，并返回该函数的&nbsp;<strong>柯里化&nbsp;</strong>后的形式。</p>

<p><strong>柯里化&nbsp;</strong>函数的定义是接受与原函数相同数量或更少数量的参数，并返回另一个 <strong>柯里化</strong> 后的函数或与原函数相同的值。</p>

<p>实际上，当你调用原函数，如 <code>sum(1,2,3)</code>&nbsp;时，它将调用 <strong>柯里化</strong> 函数的某个形式，如 <code>csum(1)(2)(3)</code>， <code>csum(1)(2,3)</code>， <code>csum(1,2)(3)</code>，或 <code>csum(1,2,3)</code> 。所有调用 <strong>柯里化</strong> 函数的方法都应该返回与原始函数相同的值。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>
fn = function sum(a, b, c) { return a + b + c; }
inputs = [[1],[2],[3]]
<b>输出：</b>6
<strong>解释：</strong>
执行的代码是：
const curriedSum = curry(fn);
curriedSum(1)(2)(3) === 6;
curriedSum(1)(2)(3) 应该返回像原函数 sum(1, 2, 3) 一样的值。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>
fn = function sum(a, b, c) { return a + b + c; }
inputs = [[1,2],[3]]]
<b>输出：</b>6
<strong>解释：</strong>
curriedSum(1, 2)(3) 应该返回像原函数 sum(1, 2, 3) 一样的值。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>
fn = function sum(a, b, c) { return a + b + c; }
inputs = [[],[],[1,2,3]]
<b>输出：</b>6
<strong>解释：</strong>
你应该能够以任何方式传递参数，包括一次性传递所有参数或完全不传递参数。
curriedSum()()(1, 2, 3) 应该返回像原函数 sum(1, 2, 3) 一样的值。
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>
fn = function life() { return 42; }
inputs = [[]]
<b>输出：</b>42
<strong>解释：</strong>
柯里化一个没有接收参数，没做有效操作的函数。
curriedLife() === 42
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= inputs.length &lt;= 1000</code></li>
	<li><code>0 &lt;= inputs[i][j] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= fn.length &lt;= 1000</code></li>
	<li><code>inputs.flat().length == fn.length</code></li>
	<li><code>函数参数需要被显式定义</code></li>
	<li>如果&nbsp;<code>fn.length &gt; 0</code>&nbsp;则最后一个数组&nbsp;<code>inputs</code>&nbsp;不为空</li>
	<li>如果&nbsp;<code>fn.length === 0</code>&nbsp;则&nbsp;<code>inputs.length === 1</code>&nbsp;</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 参数可分若干次传入，凑满原函数 arity 后再求值。若每次都立即调用，无法支持 `csum(1)(2)` 这种形式。
>
> 比较已收集参数个数与 `fn.length`：不足则返回继续收集的函数，足够则一次展开调用。

<!-- thinking:end -->

<!-- tabs:start -->

#### TypeScript

```ts
function curry(fn: Function): Function {
    return function curried(...args) {
        if (args.length >= fn.length) {
            return fn(...args);
        }
        return (...nextArgs) => curried(...args, ...nextArgs);
    };
}

/**
 * function sum(a, b) { return a + b; }
 * const csum = curry(sum);
 * csum(1)(2) // 3
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
