---
comments: true
difficulty: 简单
tags:
    - JavaScript
---

<!-- problem:start -->

# [2620. 计数器](https://leetcode.cn/problems/counter)

[English Version](/solution/2600-2699/2620.Counter/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个整型参数 <code>n</code>，请你编写并返回一个 <code>counter</code><strong>&nbsp;</strong>函数。这个&nbsp;<code>counter</code><strong>&nbsp;</strong>函数最初返回 <code>n</code>，每次调用它时会返回前一个值加 1 的值 ( <code>n</code> ,&nbsp; <code>n + 1</code> ,&nbsp; <code>n + 2</code> ，等等)。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
n = 10 
["call","call","call"]
<strong>输出：</strong>[10,11,12]
<strong>解释：</strong>
counter() = 10 // 第一次调用 counter()，返回 n。
counter() = 11 // 返回上次调用的值加 1。
counter() = 12 // 返回上次调用的值加 1。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>
n = -2
["call","call","call","call","call"]
<strong>输出：</strong>[-2,-1,0,1,2]
<strong>解释：</strong>counter() 最初返回 -2。然后在每个后续调用后增加 1。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>-1000<sup>&nbsp;</sup>&lt;= n &lt;= 1000</code></li>
	<li><code>0 &lt;= calls.length &lt;= 1000</code></li>
	<li><code>calls[i] === "call"</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 每次调用须返回一个递增整数，且起始值由闭包捕获。若用全局变量，多次 `createCounter` 会互相干扰。
>
> 闭包保存当前值 $i$，每次调用返回后再自增，第一次恰好得到 $n$。
>
> 外层函数只负责初始化，内层函数持有可变状态。

<!-- thinking:end -->

<!-- tabs:start -->

#### TypeScript

```ts
function createCounter(n: number): () => number {
    let i = n;
    return function () {
        return i++;
    };
}

/**
 * const counter = createCounter(10)
 * counter() // 10
 * counter() // 11
 * counter() // 12
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
