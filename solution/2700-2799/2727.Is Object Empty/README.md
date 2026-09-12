---
comments: true
difficulty: 简单
tags:
    - JavaScript
---

<!-- problem:start -->

# [2727. 判断对象是否为空](https://leetcode.cn/problems/is-object-empty)

[English Version](/solution/2700-2799/2727.Is%20Object%20Empty/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个对象或数组，判断它是否为空。</p>

<ul>
	<li>一个空对象不包含任何键值对。</li>
	<li>一个空数组不包含任何元素。</li>
</ul>

<p>你可以假设对象或数组是通过 <code>JSON.parse</code> 解析得到的。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>obj = {"x": 5, "y": 42}
<b>输出：</b>false
<b>解释：</b>这个对象有两个键值对，所以它不为空。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>obj = {}
<b>输出：</b>true
<b>解释：</b>这个对象没有任何键值对，所以它为空。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>obj = [null, false, 0]
<b>输出：</b>false
<b>解释：</b>这个数组有 3 个元素，所以它不为空。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>obj</code> 是一个有效的 JSON 对象或数组</li>
	<li><code>2 &lt;= JSON.stringify(obj).length &lt;= 10<sup>5</sup></code></li>
</ul>

<p>&nbsp;</p>
<strong>你可以在 O(1) 时间复杂度内解决这个问题吗？</strong>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：遍历

<!-- thinking:start -->

> **思考**
>
> 判断对象或数组是否没有任何可枚举键。取 $Object.keys$ 再比长度可以得到答案，同时会先物化全部键。
>
> 用 $for\cdots in$ 一旦碰到第一个自有或继承的可枚举键即返回 $false$，否则为空。对 JSON 解析得到的普通对象与数组，这一遍历即为是否为空。

<!-- thinking:end -->

我们可以遍历对象或数组，如果遍历到了第一个元素，就返回 `false`，否则返回 `true`。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### TypeScript

```ts
function isEmpty(obj: Record<string, any> | any[]): boolean {
    for (const x in obj) {
        return false;
    }
    return true;
}
```

#### JavaScript

```js
/**
 * @param {Object | Array} obj
 * @return {boolean}
 */
var isEmpty = function (obj) {
    for (const x in obj) {
        return false;
    }
    return true;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- thinking:start -->

> **思考**
>
> 方法一依赖遍历短路。直接比较 $Object.keys(obj).length$ 与 $0$，语义更直观，代价是先收集键名。

<!-- thinking:end -->

<!-- tabs:start -->

#### TypeScript

```ts
function isEmpty(obj: Record<string, any> | any[]): boolean {
    return Object.keys(obj).length === 0;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
