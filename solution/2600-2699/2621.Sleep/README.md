---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2621.Sleep/README.md
---

<!-- problem:start -->

# [2621. 睡眠函数](https://leetcode.cn/problems/sleep)

[English Version](/solution/2600-2699/2621.Sleep/README_EN.md)

## 题目描述

<!-- description:start -->

<p>请你编写一个异步函数，它接收一个正整数参数 <code>millis</code>&nbsp;，并休眠 <code>millis</code> 毫秒。要求此函数可以解析任何值。</p>

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<pre>
<b>输入：</b>millis = 100
<b>输出：</b>100
<b>解释：</b>
在 100ms 后此异步函数执行完时返回一个 Promise 对象
let t = Date.now();
sleep(100).then(() =&gt; {
  console.log(Date.now() - t); // 100
});
</pre>

<p><b>示例 2：</b></p>

<pre>
<b>输入：</b>millis = 200
<b>输出：</b>200
<b>解释：</b>在 200ms 后函数执行完时返回一个 Promise 对象
</pre>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>1 &lt;= millis &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### TypeScript

```ts
async function sleep(millis: number): Promise<void> {
    return new Promise(r => setTimeout(r, millis));
}

/**
 * let t = Date.now()
 * sleep(100).then(() => console.log(Date.now() - t)) // 100
 */
```

#### JavaScript

```js
/**
 * @param {number} millis
 * @return {Promise}
 */
async function sleep(millis) {
    return new Promise(r => setTimeout(r, millis));
}

/**
 * let t = Date.now()
 * sleep(100).then(() => console.log(Date.now() - t)) // 100
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
