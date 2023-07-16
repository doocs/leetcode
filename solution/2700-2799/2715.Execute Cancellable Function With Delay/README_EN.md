# [2715. Execute Cancellable Function With Delay](https://leetcode.com/problems/execute-cancellable-function-with-delay)

[中文文档](/solution/2700-2799/2715.Execute%20Cancellable%20Function%20With%20Delay/README.md)

## Description

<p>Given a function <code>fn</code>, an array or arguments&nbsp;<code>args</code>, and a timeout&nbsp;<code>t</code>&nbsp;in milliseconds, return a cancel function <code>cancelFn</code>.</p>

<p>After a delay of&nbsp;<code>t</code>,&nbsp;<code>fn</code>&nbsp;should be called with <code>args</code> passed as parameters <strong>unless</strong> <code>cancelFn</code> was called first. In that case,&nbsp;<code>fn</code> should never be called.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> fn = (x) =&gt; x * 5, args = [2], t = 20
<strong>Output:</strong> [{&quot;time&quot;: 20, &quot;returned&quot;: 10}]
<strong>Explanation:</strong> 
const cancelTime = 50
const cancel = cancellable((x) =&gt; x * 5, [2], 20); // fn(2) called at t=20ms
setTimeout(cancel, cancelTime);

The cancellation was scheduled to occur after a delay of cancelTime (50ms), which happened after the execution of fn(2) at 20ms.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> fn = (x) =&gt; x**2, args = [2], t = 100
<strong>Output:</strong> []
<strong>Explanation:</strong> 
const cancelTime = 50 
const cancel = cancellable((x) =&gt; x**2, [2], 100); // fn(2) not called
setTimeout(cancel, cancelTime);

The cancellation was scheduled to occur after a delay of cancelTime (50ms), which happened before the execution of fn(2) at 100ms, resulting in fn(2) never being called.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> fn = (x1, x2) =&gt; x1 * x2, args = [2,4], t = 30
<strong>Output:</strong> [{&quot;time&quot;: 30, &quot;returned&quot;: 8}]
<strong>Explanation:</strong>
const cancelTime = 100
const cancel = cancellable((x1, x2) =&gt; x1 * x2, [2,4], 30); // fn(2,4) called at t=30ms
setTimeout(cancel, cancelTime);

The cancellation was scheduled to occur after a delay of cancelTime (100ms), which happened after the execution of fn(2,4) at 30ms.
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

```

<!-- tabs:end -->
