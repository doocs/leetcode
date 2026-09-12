---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2796.Repeat%20String/README.md
tags:
    - JavaScript
---

<!-- problem:start -->

# [2796. 重复字符串 🔒](https://leetcode.cn/problems/repeat-string)

[English Version](/solution/2700-2799/2796.Repeat%20String/README_EN.md)

## 题目描述

<!-- description:start -->

<p>编写代码实现字符串方法 <code>string.replicate(x)</code> ，它将返回重复的字符串 <code>x</code> 次。</p>

<p>请尝试在不使用内置方法 <code>string.repeat</code> 的情况下实现它。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>str = "hello", times = 2
<b>输出：</b>"hellohello"
<b>解释：</b>"hello" 被重复了 2 次
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>str = "code", times = 3
<b>输出：</b>codecodecode"
<b>解释：</b> "code" 被重复了 3 次
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>str = "js", times = 1
<b>输出：</b>"js"
<b>解释：</b>"js" 被重复了 1 次
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= times &lt;=&nbsp;10<sup>5</sup></code></li>
	<li><code>1 &lt;= str.length &lt;= 1000</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶</strong>：为了简化分析，让我们假设连接字符串是一个常数时间操作 <code>O(1)</code>。考虑到这个假设，您能编写时间复杂度为 <code>O(log n)</code>&nbsp;的算法吗？</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 给字符串原型增加重复 $times$ 次的方法。循环拼接在 $times$ 较大时反复分配中间串。
>
> 用 $times$ 个当前字符串填入数组再 $join$，一次分配即可。

<!-- thinking:end -->

<!-- tabs:start -->

#### TypeScript

```ts
declare global {
    interface String {
        replicate(times: number): string;
    }
}

String.prototype.replicate = function (times: number) {
    return new Array(times).fill(this).join('');
};
```

#### JavaScript

```js
String.prototype.replicate = function (times) {
    return Array(times).fill(this).join('');
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
