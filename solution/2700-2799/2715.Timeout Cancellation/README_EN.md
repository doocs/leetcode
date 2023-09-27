# [2715. Timeout Cancellation](https://leetcode.com/problems/timeout-cancellation)

[中文文档](/solution/2700-2799/2715.Timeout%20Cancellation/README.md)

## Description

<p>Given a function <code>fn</code>, an array of&nbsp;arguments&nbsp;<code>args</code>, and a timeout&nbsp;<code>t</code>&nbsp;in milliseconds, return a cancel function <code>cancelFn</code>.</p>

<p>After a delay of&nbsp;<code>t</code>,&nbsp;<code>fn</code>&nbsp;should be called with <code>args</code> passed as parameters <strong>unless</strong> <code>cancelFn</code> was invoked before the delay of <code>t</code> milliseconds elapses, specifically at <code>cancelT</code>&nbsp;ms.&nbsp;In that case,&nbsp;<code>fn</code> should never be called.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> fn = (x) =&gt; x * 5, args = [2], t = 20, cancelT = 50
<strong>Output:</strong> [{&quot;time&quot;: 20, &quot;returned&quot;: 10}]
<strong>Explanation:</strong> 
const result = []

const fn = (x) =&gt; x * 5

const start = performance.now()&nbsp;

const log = (...argsArr) =&gt; {
    const diff = Math.floor(performance.now() - start);
    result.push({&quot;time&quot;: diff, &quot;returned&quot;: fn(...argsArr)})
}
 &nbsp; &nbsp;&nbsp;
const cancel = cancellable(log, [2], 20);

const maxT = Math.max(t, 50)
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;
setTimeout(cancel, cancelT)

setTimeout(() =&gt; {
 &nbsp; &nbsp; console.log(result) // [{&quot;time&quot;:20,&quot;returned&quot;:10}]
}, 65)

The cancellation was scheduled to occur after a delay of cancelT (50ms), which happened after the execution of fn(2) at 20ms.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> fn = (x) =&gt; x**2, args = [2], t = 100, cancelT = 50 
<strong>Output:</strong> []
<strong>Explanation:</strong> The cancellation was scheduled to occur after a delay of cancelT (50ms), which happened before the execution of fn(2) at 100ms, resulting in fn(2) never being called.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> fn = (x1, x2) =&gt; x1 * x2, args = [2,4], t = 30, cancelT = 100
<strong>Output:</strong> [{&quot;time&quot;: 30, &quot;returned&quot;: 8}]
<strong>Explanation: </strong>The cancellation was scheduled to occur after a delay of cancelT (100ms), which happened after the execution of fn(2,4) at 30ms.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>fn</code> is a function</li>
	<li><code>args</code> is a valid JSON array</li>
	<li><code>1 &lt;= args.length &lt;= 10</code></li>
	<li><code><font face="monospace">20 &lt;= t &lt;= 1000</font></code></li>
	<li><code><font face="monospace">10 &lt;= cancelT &lt;= 1000</font></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **TypeScript**

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

### **JavaScript**

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
