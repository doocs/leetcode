# [2725. Interval Cancellation](https://leetcode.com/problems/interval-cancellation)

[中文文档](/solution/2700-2799/2725.Interval%20Cancellation/README.md)

## Description

<p>Given a function <code>fn</code>, an array of arguments&nbsp;<code>args</code>, and&nbsp;an interval time <code>t</code>, return a cancel function <code>cancelFn</code>.</p>

<p>The function <code>fn</code> should be called with <code>args</code> immediately and then called again every&nbsp;<code>t</code> milliseconds&nbsp;until&nbsp;<code>cancelFn</code>&nbsp;is called at <code>cancelT</code> ms.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> fn = (x) =&gt; x * 2, args = [4], t = 20, cancelT = 110
<strong>Output:</strong> 
[
   {&quot;time&quot;: 0, &quot;returned&quot;: 8},
   {&quot;time&quot;: 20, &quot;returned&quot;: 8},
   {&quot;time&quot;: 40, &quot;returned&quot;: 8},
   {&quot;time&quot;: 60, &quot;returned&quot;: 8},
   {&quot;time&quot;: 80, &quot;returned&quot;: 8},
   {&quot;time&quot;: 100, &quot;returned&quot;: 8}
]
<strong>Explanation:</strong> 
const cancel = cancellable(x =&gt; x * 2, [4], 20);
setTimeout(cancel, 110);

Every 20ms, fn(4) is called. Until t=110ms, then it is cancelled.
1st fn call is at 0ms. fn(4) returns 8.
2nd fn call is at 20ms. fn(4) returns 8.
3rd fn call is at 40ms. fn(4) returns 8.
4th fn call is at&nbsp;60ms. fn(4) returns 8.
5th fn call is at 80ms. fn(4) returns 8.
6th fn call is at 100ms. fn(4) returns 8.
Cancelled at 110ms
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> fn = (x1, x2) =&gt; (x1 * x2), args = [2, 5], t = 25, cancelT = 140
<strong>Output:</strong> 
[
   {&quot;time&quot;: 0, &quot;returned&quot;: 10},
   {&quot;time&quot;: 25, &quot;returned&quot;: 10},
   {&quot;time&quot;: 50, &quot;returned&quot;: 10},
   {&quot;time&quot;: 75, &quot;returned&quot;: 10},
   {&quot;time&quot;: 100, &quot;returned&quot;: 10},
   {&quot;time&quot;: 125, &quot;returned&quot;: 10}
]
<strong>Explanation:</strong> 
const cancel = cancellable((x1, x2) =&gt; (x1 * x2), [2, 5], 25); 
setTimeout(cancel, 140);

Every 25ms, fn(2, 5) is called. Until t=140ms, then it is cancelled.
1st fn call is at 0ms&nbsp;
2nd fn call is at 25ms&nbsp;
3rd fn call is at 50ms&nbsp;
4th fn call is at&nbsp;75ms&nbsp;
5th fn call is at 100ms&nbsp;
6th fn call is at 125ms
Cancelled at 140ms
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> fn = (x1, x2, x3) =&gt; (x1 + x2 + x3), args = [5, 1, 3], t = 50, cancelT = 180
<strong>Output:</strong> 
[
   {&quot;time&quot;: 0, &quot;returned&quot;: 9},
   {&quot;time&quot;: 50, &quot;returned&quot;: 9},
   {&quot;time&quot;: 100, &quot;returned&quot;: 9},
   {&quot;time&quot;: 150, &quot;returned&quot;: 9}
]
<strong>Explanation:</strong> 
const cancel = cancellable((x1, x2, x3) =&gt; (x1 + x2 + x3), [5, 1, 3], 50);
setTimeout(cancel, 180);

Every 50ms, fn(5, 1, 3) is called. Until t=180ms, then it is cancelled. 
1st fn call is at 0ms
2nd fn call is at 50ms
3rd fn call is at 100ms
4th fn call is at&nbsp;150ms
Cancelled at 180ms
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>fn is a function</code></li>
	<li><code>args is a valid JSON array</code></li>
	<li><code>1 &lt;= args.length &lt;= 10</code></li>
	<li><code><font face="monospace">20 &lt;= t &lt;= 1000</font></code></li>
	<li><code><font face="monospace">10 &lt;= cancelT &lt;= 1000</font></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **TypeScript**

```ts
function cancellable(fn: Function, args: any[], t: number): Function {
    fn(...args);
    const time = setInterval(() => fn(...args), t);
    return () => clearInterval(time);
}

/**
 *  const result = []
 *
 *  const fn = (x) => x * 2
 *  const args = [4], t = 20, cancelT = 110
 *
 *  const log = (...argsArr) => {
 *      result.push(fn(...argsArr))
 *  }
 *
 *  const cancel = cancellable(fn, args, t);
 *
 *  setTimeout(() => {
 *     cancel()
 *     console.log(result) // [
 *                         //      {"time":0,"returned":8},
 *                         //      {"time":20,"returned":8},
 *                         //      {"time":40,"returned":8},
 *                         //      {"time":60,"returned":8},
 *                         //      {"time":80,"returned":8},
 *                         //      {"time":100,"returned":8}
 *                         //  ]
 *  }, cancelT)
 */
```

<!-- tabs:end -->
