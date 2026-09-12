---
comments: true
difficulty: 简单
tags:
    - JavaScript
---

<!-- problem:start -->

# [2667. 创建 Hello World 函数](https://leetcode.cn/problems/create-hello-world-function)

[English Version](/solution/2600-2699/2667.Create%20Hello%20World%20Function/README_EN.md)

## 题目描述

<!-- description:start -->

请你编写一个名为 <code>createHelloWorld</code> 的函数。它应该返回一个新的函数，该函数总是返回 <code>"Hello World"</code>&nbsp;。
<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>args = []
<b>输出：</b>"Hello World"
<strong>解释：</strong>
const f = createHelloWorld();
f(); // "Hello World"

createHelloWorld 返回的函数应始终返回 "Hello World"。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>args = [{},null,42]
<b>输出：</b>"Hello World"
<strong>解释：</strong>
const f = createHelloWorld();
f({}, null, 42); // "Hello World"

可以传递任何参数给函数，但它应始终返回 "Hello World"。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= args.length &lt;= 10</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 工厂须返回忽略全部参数、恒输出固定字符串的函数。内层函数不读取 `args`，即可满足「任意输入同一结果」。

<!-- thinking:end -->

<!-- tabs:start -->

#### TypeScript

```ts
function createHelloWorld() {
    return function (...args): string {
        return 'Hello World';
    };
}

/**
 * const f = createHelloWorld();
 * f(); // "Hello World"
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
