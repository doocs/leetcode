# [2821. 延迟每个 Promise 对象的解析](https://leetcode.cn/problems/delay-the-resolution-of-each-promise)

[English Version](/solution/2800-2899/2821.Delay%20the%20Resolution%20of%20Each%20Promise/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个函数数组 <code>functions</code> 和一个数字 <code>ms</code>，返回一个新的函数数组。</p>

<ul>
	<li><code>functions</code> 是一个返回 Promise 对象的函数数组。</li>
	<li><code>ms</code> 表示延迟的时间，以毫秒为单位。它决定了在新数组中的每个函数返回的 Promise 在解析之前等待的时间。</li>
</ul>

<p>新数组中的每个函数应该返回一个 Promise 对象，在延迟了 <code>ms</code> 毫秒后解析，保持原始 <code>functions</code> 数组中的顺序。<code>delayAll</code> 函数应确保从 <code>functions</code> 中的每个 Promise 都被延迟执行，形成返回延迟的 Promise 的函数的新数组。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>
functions = [
&nbsp;  () =&gt; new Promise((resolve) =&gt; setTimeout(resolve, 30))
], 
ms = 50
<b>输出：</b>[80]
<b>解释：</b>数组中的 Promise 在 30 毫秒后解析，但被延迟了 50 毫秒，所以总共延迟了 30 毫秒 + 50 毫秒 = 80 毫秒。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>
functions = [
&nbsp;   () =&gt; new Promise((resolve) =&gt; setTimeout(resolve, 50)),
&nbsp;   () =&gt; new Promise((resolve) =&gt; setTimeout(resolve, 80))
], 
ms = 70
<b>输出：</b>[120,150]
<b>解释：</b>数组中的 Promise 在 50 毫秒和 80 毫秒后解析，但它们被延迟了 70 毫秒，所以总共延迟了 50 毫秒 + 70 毫秒 = 120 毫秒 和 80 毫秒 + 70 毫秒 = 150 毫秒。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>functions</code>&nbsp;是一个返回 Promise 对象的函数数组</li>
	<li><code>10 &lt;= ms &lt;= 500</code></li>
	<li><code>1 &lt;= functions.length &lt;= 10</code></li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```ts
function delayAll(functions: Function[], ms: number): Function[] {
    return functions.map(fn => {
        return async function () {
            await new Promise(resolve => setTimeout(resolve, ms));
            return fn();
        };
    });
}
```

<!-- tabs:end -->

<!-- end -->
