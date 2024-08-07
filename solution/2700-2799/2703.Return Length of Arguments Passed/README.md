---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2703.Return%20Length%20of%20Arguments%20Passed/README.md
tags:
    - JavaScript
---

<!-- problem:start -->

# [2703. 返回传递的参数的长度](https://leetcode.cn/problems/return-length-of-arguments-passed)

[English Version](/solution/2700-2799/2703.Return%20Length%20of%20Arguments%20Passed/README_EN.md)

## 题目描述

<!-- description:start -->

请你编写一个函数 <code>argumentsLength</code>，返回传递给该函数的参数数量。

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>args = [5]
<b>输出：</b>1
<strong>解释：</strong>
argumentsLength(5); // 1

只传递了一个值给函数，因此它应返回 1。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>args = [{}, null, "3"]
<b>输出：</b>3
<b>解释：</b>
argumentsLength({}, null, "3"); // 3

传递了三个值给函数，因此它应返回 3。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>args</code>&nbsp;是一个有效的 JSON 数组</li>
	<li><code>0 &lt;= args.length &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### TypeScript

```ts
function argumentsLength(...args: any[]): number {
    return args.length;
}

/**
 * argumentsLength(1, 2, 3); // 3
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
