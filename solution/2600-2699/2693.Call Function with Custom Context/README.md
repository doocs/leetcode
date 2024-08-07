---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2693.Call%20Function%20with%20Custom%20Context/README.md
tags:
    - JavaScript
---

<!-- problem:start -->

# [2693. 使用自定义上下文调用函数](https://leetcode.cn/problems/call-function-with-custom-context)

[English Version](/solution/2600-2699/2693.Call%20Function%20with%20Custom%20Context/README_EN.md)

## 题目描述

<!-- description:start -->

<p>增强所有函数，使其具有&nbsp;<code>callPolyfill</code>&nbsp;方法。该方法接受一个对象&nbsp;<code>obj</code>&nbsp;作为第一个参数，以及任意数量的附加参数。<code>obj</code>&nbsp;成为函数的&nbsp;<code>this</code>&nbsp;上下文。附加参数将传递给该函数（即&nbsp;<code>callPolyfill</code>&nbsp;方法所属的函数）。</p>

<p>例如，如果有以下函数：</p>

<pre>
function tax(price, taxRate) {
  const totalCost = price * (1 + taxRate);
&nbsp; console.log(`The cost of ${this.item} is ${totalCost}`);
}
</pre>

<p>调用&nbsp;<code>tax(10, 0.1)</code>&nbsp;将输出&nbsp;<code>"The cost of undefined is 11"</code>&nbsp;。这是因为&nbsp;<code>this</code>&nbsp;上下文未定义。</p>

<p>然而，调用&nbsp;<code>tax.callPolyfill({item: "salad"}, 10, 0.1)</code>&nbsp;将输出&nbsp;<code>"The cost of salad is 11"</code>&nbsp;。<code>this</code>&nbsp;上下文被正确设置，函数输出了适当的结果。</p>

<p>请在不使用内置的&nbsp;<code>Function.call</code>&nbsp;方法的情况下解决这个问题。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>
fn = function add(b) {
  return this.a + b;
}
args = [{"a": 5}, 7]
<b>输出：</b>12
<strong>解释：</strong>
fn.callPolyfill({"a": 5}, 7); // 12
<code>callPolyfill </code>将 "this" 上下文设置为 <code>{"a": 5} </code>，并将 7 作为参数传递。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>
fn = function tax(price, taxRate) { 
&nbsp;return `The cost of the ${this.item} is ${price * taxRate}`; 
}
args = [{"item": "burger"}, 10, 1,1]
<b>输出：</b>"The cost of the burger is 11"
<b>解释：</b><code>callPolyfill </code>将 "this" 上下文设置为 <code>{"item": "burger"} </code>，并将 10 和 1.1 作为附加参数传递。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul style="list-style-type:square;">
	<li><code><font face="monospace">typeof args[0] == 'object' and args[0] != null</font></code></li>
	<li><code>1 &lt;= args.length &lt;= 100</code></li>
	<li><code>2 &lt;= JSON.stringify(args[0]).length &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### TypeScript

```ts
declare global {
    interface Function {
        callPolyfill(context: Record<any, any>, ...args: any[]): any;
    }
}

Function.prototype.callPolyfill = function (context, ...args): any {
    const fn = this.bind(context);
    return fn(...args);
};

/**
 * function increment() { this.count++; return this.count; }
 * increment.callPolyfill({count: 1}); // 2
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
