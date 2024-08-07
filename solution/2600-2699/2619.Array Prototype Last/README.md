---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2619.Array%20Prototype%20Last/README.md
tags:
    - JavaScript
---

<!-- problem:start -->

# [2619. 数组原型对象的最后一个元素](https://leetcode.cn/problems/array-prototype-last)

[English Version](/solution/2600-2699/2619.Array%20Prototype%20Last/README_EN.md)

## 题目描述

<!-- description:start -->

<p>请你编写一段代码实现一个数组方法，使任何数组都可以调用 <code>array.last()</code> 方法，这个方法将返回数组最后一个元素。如果数组中没有元素，则返回&nbsp;<code>-1</code>&nbsp;。</p>

<p>你可以假设数组是 <code>JSON.parse</code> 的输出结果。</p>

<p>&nbsp;</p>

<p><strong>示例 1 ：</strong></p>

<pre>
<b>输入：</b>nums = [null, {}, 3]
<b>输出：</b>3
<b>解释</b>：调用 nums.last() 后返回最后一个元素： 3。
</pre>

<p><strong>示例 2 ：</strong></p>

<pre>
<b>输入：</b>nums = []
<b>输出：</b>-1
<strong>解释：</strong>因为此数组没有元素，所以应该返回 -1。
</pre>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>arr</code> 是一个有效的 JSON 数组</li>
	<li><code>0 &lt;= arr.length &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### TypeScript

```ts
declare global {
    interface Array<T> {
        last(): T | -1;
    }
}

Array.prototype.last = function () {
    return this.length ? this.at(-1) : -1;
};

/**
 * const arr = [1, 2, 3];
 * arr.last(); // 3
 */

export {};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
