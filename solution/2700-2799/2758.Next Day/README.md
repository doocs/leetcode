---
comments: true
difficulty: 简单
tags:
    - JavaScript
---

<!-- problem:start -->

# [2758. 下一天 🔒](https://leetcode.cn/problems/next-day)

[English Version](/solution/2700-2799/2758.Next%20Day/README_EN.md)

## 题目描述

<!-- description:start -->

<p>请你编写一个有关日期对象的方法，使得任何日期对象都可以调用 <code>date.nextDay()</code> 方法，然后返回调用日期对象的下一天，格式为 YYYY-MM-DD 。</p>

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<pre>
<b>输入：</b>date = "2014-06-20"
<b>输出：</b>"2014-06-21"
<b>解释：</b>
const date = new Date("2014-06-20");
date.nextDay(); // "2014-06-21"
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>date = "2017-10-31"
<strong>输出：</strong>"2017-11-01"
<b>解释：</b>日期 2017-10-31 的下一天是 2017-11-01.
</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
	<li><code>new Date(date)</code> 是一个有效的日期对象</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 给 $Date$ 增加返回次日 $YYYY\text{-}MM\text{-}DD$ 的方法。手写闰年与月份天数容易出错，标准库已处理进位。
>
> 复制当前时间戳后 $setDate(getDate()+1)$，再取 ISO 字符串的日期部分，避免改动原对象。

<!-- thinking:end -->

<!-- tabs:start -->

#### TypeScript

```ts
declare global {
    interface Date {
        nextDay(): string;
    }
}

Date.prototype.nextDay = function () {
    const date = new Date(this.valueOf());
    date.setDate(date.getDate() + 1);
    return date.toISOString().slice(0, 10);
};

/**
 * const date = new Date("2014-06-20");
 * date.nextDay(); // "2014-06-21"
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
