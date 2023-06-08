# [2725. Interval Cancellation](https://leetcode.cn/problems/interval-cancellation)

[English Version](/solution/2700-2799/2725.Interval%20Cancellation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

Given a function <code>fn,</code> an array of arguments&nbsp;<code>args</code>, and&nbsp;an interval time <code>t</code>, return a cancel function <code>cancelFn</code>. The function <code>fn</code> should be called with <code>args</code> immediately and then called again every&nbsp;<code>t</code> milliseconds&nbsp;until <code>cancelFn</code> is called.

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
<strong>Explanation:</strong> Every 20ms, fn(4) is called. Until t=110ms, then it is cancelled.

const cancel = cancellable(x =&gt; x * 2, [4], 20);
setTimeout(cancel, 110);

1st fn call is at 0ms. fn(4) returns 8.
2nd fn call is at 20ms. fn(4) returns 8.
3rd fn call is at 40ms. fn(4) returns 8.
4th fn call is at&nbsp;60ms. fn(4) returns 8.
5th fn call is at 80ms. fn(4) returns 8.
6th fn call is at 100ms. fn(4) returns 8.
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
<strong>Explanation:</strong> Every 25ms, fn(2, 5) is called. Until t=140ms, then it is cancelled.
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
<strong>Explanation:</strong> Every 50ms, fn(5, 1, 3) is called. Until t=180ms, then it is cancelled. 
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

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts

```

<!-- tabs:end -->
