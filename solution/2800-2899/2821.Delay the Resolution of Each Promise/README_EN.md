---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2821.Delay%20the%20Resolution%20of%20Each%20Promise/README_EN.md
---

<!-- problem:start -->

# [2821. Delay the Resolution of Each Promise 🔒](https://leetcode.com/problems/delay-the-resolution-of-each-promise)

[中文文档](/solution/2800-2899/2821.Delay%20the%20Resolution%20of%20Each%20Promise/README.md)

## Description

<!-- description:start -->

<p>Given an array&nbsp;<code>functions</code>&nbsp;and a number <code>ms</code>, return a new&nbsp;array of functions.</p>

<ul>
	<li><code>functions</code>&nbsp;is an array of functions that return promises.</li>
	<li><code>ms</code>&nbsp;represents the delay duration in milliseconds. It determines the amount of time to wait before resolving or rejecting each promise in the new array.</li>
</ul>

<p>Each function in the new array should return a promise that resolves or rejects after an additional delay of <code>ms</code>&nbsp;milliseconds, preserving the order of the original <code>functions</code>&nbsp;array.</p>

<p>The&nbsp;<code>delayAll</code>&nbsp;function should ensure&nbsp;that each promise from&nbsp;<code>functions</code>&nbsp;is executed with a delay, forming the new array of functions returning delayed promises.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
functions = [
&nbsp;  () =&gt; new Promise((resolve) =&gt; setTimeout(resolve, 30))
], 
ms = 50
<strong>Output:</strong> [80]
<strong>Explanation:</strong> The promise from the array would have resolved after 30 ms, but it was delayed by 50 ms, thus 30 ms + 50 ms = 80 ms.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> 
functions = [
&nbsp;   () =&gt; new Promise((resolve) =&gt; setTimeout(resolve, 50)),
&nbsp;   () =&gt; new Promise((resolve) =&gt; setTimeout(resolve, 80))
], 
ms = 70
<strong>Output:</strong> [120,150]
<strong>Explanation:</strong> The promises from the array would have resolved after 50 ms and 80 ms, but they were delayed by 70 ms, thus 50 ms + 70 ms = 120 ms and 80 ms + 70 ms = 150 ms.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> 
functions = [
&nbsp;   () =&gt; new Promise((resolve, reject) =&gt; setTimeout(reject, 20)), 
&nbsp;   () =&gt; new Promise((resolve, reject) =&gt; setTimeout(reject, 100))
], 
ms = 30
<strong>Output: </strong>[50,130]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>functions</code> is an array of functions that return promises</li>
	<li><code>10 &lt;= ms &lt;= 500</code></li>
	<li><code>1 &lt;= functions.length &lt;= 10</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

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

<!-- solution:end -->

<!-- problem:end -->
