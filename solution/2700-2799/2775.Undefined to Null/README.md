---
comments: true
difficulty: 中等
tags:
    - JavaScript
---

<!-- problem:start -->

# [2775. 将 undefined 转为 null 🔒](https://leetcode.cn/problems/undefined-to-null)

[English Version](/solution/2700-2799/2775.Undefined%20to%20Null/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个深层嵌套的对象或数组 <code>obj</code> ，并创建该对象&nbsp;<code>obj</code> 的副本，将其中的任何 <code>undefined</code> 值替换为 <code>null</code> 。</p>

<p>当使用 <code>JSON.stringify()</code> 将对象转换为 JSON 字符串时，<code>undefined</code> 值与&nbsp;<code>null</code> 值的处理方式不同。该函数有助于确保序列化数据不会出现意外错误。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>obj = {"a": undefined, "b": 3}
<b>输出：</b>{"a": null, "b": 3}
<b>解释：</b>obj.a 的值已从 undefined 更改为 null 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>obj = {"a": undefined, "b": ["a", undefined]}
<b>输出：</b>{"a": null,"b": ["a", null]}
<b>解释：</b>obj.a 和 obj.b[1] 的值已从 undefined 更改为 null 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>obj</code> 是一个有效的 JSON 对象或数组</li>
	<li><code>2 &lt;= JSON.stringify(obj).length &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 把对象或数组中的 $undefined$ 换成 $null$，并递归处理嵌套结构。序列化时 $undefined$ 会被丢键，不能靠 $JSON$ 往返。
>
> 遍历每个键：值仍是对象则先递归，再把仍然等于 $undefined$ 的槽位写成 $null$。数组下标同样被 $for\cdots in$ 覆盖。

<!-- thinking:end -->

<!-- tabs:start -->

#### TypeScript

```ts
function undefinedToNull(obj: Record<any, any>): Record<any, any> {
    for (const key in obj) {
        if (typeof obj[key] === 'object') {
            obj[key] = undefinedToNull(obj[key]);
        }
        if (obj[key] === undefined) {
            obj[key] = null;
        }
    }
    return obj;
}

/**
 * undefinedToNull({"a": undefined, "b": 3}) // {"a": null, "b": 3}
 * undefinedToNull([undefined, undefined]) // [null, null]
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
