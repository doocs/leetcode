---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2715.Timeout%20Cancellation/README_EN.md
---

<!-- problem:start -->

# [2715. Timeout Cancellation](https://leetcode.com/problems/timeout-cancellation)

[中文文档](/solution/2700-2799/2715.Timeout%20Cancellation/README.md)

## Description

<p>Given a function <code>fn</code>, an array of&nbsp;arguments&nbsp;<code>args</code>, and a timeout&nbsp;<code>t</code>&nbsp;in milliseconds, return a cancel function <code>cancelFn</code>.</p>

<p>After a delay of <code>cancelTimeMs</code>, the returned cancel function <code>cancelFn</code> will be invoked.</p>

<pre>
setTimeout(cancelFn, cancelTimeMs)
</pre>

<p>Initially, the execution of the function <code>fn</code> should be delayed by <code>t</code> milliseconds.</p>

<p>If, before the delay of <code>t</code> milliseconds, the function <code>cancelFn</code> is invoked, it should cancel the delayed execution of <code>fn</code>. Otherwise, if <code>cancelFn</code> is not invoked within the specified delay <code>t</code>, <code>fn</code> should be executed with the provided <code>args</code> as arguments.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> fn = (x) =&gt; x * 5, args = [2], t = 20
<strong>Output:</strong> [{&quot;time&quot;: 20, &quot;returned&quot;: 10}]
<strong>Explanation:</strong> 
const cancelTimeMs = 50;
const cancelFn = cancellable((x) =&gt; x * 5, [2], 20);
setTimeout(cancelFn, cancelTimeMs);

The cancellation was scheduled to occur after a delay of cancelTimeMs (50ms), which happened after the execution of fn(2) at 20ms.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> fn = (x) =&gt; x**2, args = [2], t = 100
<strong>Output:</strong> []
<strong>Explanation:</strong> 
const cancelTimeMs = 50;
const cancelFn = cancellable((x) =&gt; x**2, [2], 100);
setTimeout(cancelFn, cancelTimeMs);

The cancellation was scheduled to occur after a delay of cancelTimeMs (50ms), which happened before the execution of fn(2) at 100ms, resulting in fn(2) never being called.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> fn = (x1, x2) =&gt; x1 * x2, args = [2,4], t = 30
<strong>Output:</strong> [{&quot;time&quot;: 30, &quot;returned&quot;: 8}]
<strong>Explanation: 
</strong>const cancelTimeMs = 100;
const cancelFn = cancellable((x1, x2) =&gt; x1 * x2, [2,4], 30);
setTimeout(cancelFn, cancelTimeMs);

The cancellation was scheduled to occur after a delay of cancelTimeMs (100ms), which happened after the execution of fn(2,4) at 30ms.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>fn</code> is a function</li>
	<li><code>args</code> is a valid JSON array</li>
	<li><code>1 &lt;= args.length &lt;= 10</code></li>
	<li><code><font face="monospace">20 &lt;= t &lt;= 1000</font></code></li>
	<li><code><font face="monospace">10 &lt;= cancelTimeMs &lt;= 1000</font></code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

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
