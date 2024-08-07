---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2715.Timeout%20Cancellation/README.md
tags:
    - JavaScript
---

<!-- problem:start -->

# [2715. 执行可取消的延迟函数](https://leetcode.cn/problems/timeout-cancellation)

[English Version](/solution/2700-2799/2715.Timeout%20Cancellation/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个函数 <code>fn</code>&nbsp;，一个参数数组 <code>args</code> 和一个以毫秒为单位的超时时间 <code>t</code> ，返回一个取消函数 <code>cancelFn</code> 。</p>

<p>在 <code>cancelTimeMs</code>&nbsp;的延迟后，返回的取消函数 <code>cancelFn</code> 将被调用。</p>

<pre>
setTimeout(cancelFn, cancelTimeMs)
</pre>

<p>最初，函数 <code>fn</code> 的执行应该延迟 <code>t</code> 毫秒。</p>

<p>如果在 <code>t</code> 毫秒的延迟之前调用了函数 <code>cancelFn</code>，它应该取消 <code>fn</code> 的延迟执行。否则，如果在指定的延迟 <code>t</code> 内没有调用 <code>cancelFn</code>，则应执行 <code>fn</code>，并使用提供的 <code>args</code> 作为参数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<pre>
<b>输入：</b>fn = (x) =&gt; x * 5, args = [2], t = 20
<b>输出：</b>[{"time": 20, "returned": 10}]
<b>解释：</b>
const cancelTimeMs = 50;
const cancelFn = cancellable((x) =&gt; x * 5, [2], 20);
setTimeout(cancelFn, cancelTimeMs);

取消操作被安排在延迟了 cancelTimeMs（50毫秒）后进行，这发生在 fn(2) 在20毫秒时执行之后。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>fn = (x) =&gt; x**2, args = [2], t = 100
<b>输出：</b>[]
<b>解释：</b>
const cancelTimeMs = 50;
const cancelFn = cancellable((x) =&gt; x**2, [2], 100);
setTimeout(cancelFn, cancelTimeMs);

取消操作被安排在延迟了 cancelTimeMs（50毫秒）后进行，这发生在 fn(2) 在100毫秒时执行之前，导致 fn(2) 从未被调用。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>fn = (x1, x2) =&gt; x1 * x2, args = [2,4], t = 30
<b>输出：</b>[{"time": 30, "returned": 8}]
<b>解释：</b>
const cancelTimeMs = 100;
const cancelFn = cancellable((x1, x2) =&gt; x1 * x2, [2,4], 30);
setTimeout(cancelFn, cancelTimeMs);

取消操作被安排在延迟了 cancelTimeMs（100毫秒）后进行，这发生在 fn(2,4) 在30毫秒时执行之后。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>fn</code> 是一个函数</li>
	<li><code>args</code> 是一个有效的 JSON 数组</li>
	<li><code>1 &lt;= args.length &lt;= 10</code></li>
	<li><code><font face="monospace">20 &lt;= t &lt;= 1000</font></code></li>
	<li><code><font face="monospace">10 &lt;= cancelTimeMs&nbsp;&lt;= 1000</font></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### TypeScript

```ts
function cancellable(fn: Function, args: any[], t: number): Function {
    const timer = setTimeout(() => fn(...args), t);
    return () => {
        clearTimeout(timer);
    };
}

/**
 *  const result = []
 *
 *  const fn = (x) => x * 5
 *  const args = [2], t = 20, cancelT = 50
 *
 *  const start = performance.now()
 *
 *  const log = (...argsArr) => {
 *      const diff = Math.floor(performance.now() - start);
 *      result.push({"time": diff, "returned": fn(...argsArr))
 *  }
 *
 *  const cancel = cancellable(log, args, t);
 *
 *  const maxT = Math.max(t, cancelT)
 *
 *  setTimeout(() => {
 *     cancel()
 *  }, cancelT)
 *
 *  setTimeout(() => {
 *     console.log(result) // [{"time":20,"returned":10}]
 *  }, maxT + 15)
 */
```

#### JavaScript

```js
/**
 * @param {Function} fn
 * @param {Array} args
 * @param {number} t
 * @return {Function}
 */
var cancellable = function (fn, args, t) {
    const timer = setTimeout(() => fn(...args), t);
    return () => {
        clearTimeout(timer);
    };
};

/**
 *  const result = []
 *
 *  const fn = (x) => x * 5
 *  const args = [2], t = 20, cancelT = 50
 *
 *  const start = performance.now()
 *
 *  const log = (...argsArr) => {
 *      const diff = Math.floor(performance.now() - start);
 *      result.push({"time": diff, "returned": fn(...argsArr))
 *  }
 *
 *  const cancel = cancellable(log, args, t);
 *
 *  const maxT = Math.max(t, cancelT)
 *
 *  setTimeout(() => {
 *     cancel()
 *  }, cancelT)
 *
 *  setTimeout(() => {
 *     console.log(result) // [{"time":20,"returned":10}]
 *  }, maxT + 15)
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
