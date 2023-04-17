# [2636. Promise Pool](https://leetcode.com/problems/promise-pool)

[中文文档](/solution/2600-2699/2636.Promise%20Pool/README.md)

## Description

<p>Given an array&nbsp;of asyncronous functions&nbsp;<code>functions</code>&nbsp;and a <strong>pool limit</strong>&nbsp;<code>n</code>, return an asyncronous function&nbsp;<code>promisePool</code>. It should return&nbsp;a promise that resolves when all the input&nbsp;functions resolve.</p>

<p><b>Pool limit</b> is defined as the maximum number promises that can be pending at once.&nbsp;<code>promisePool</code>&nbsp;should begin execution of as many functions as possible and continue executing new functions when old promises&nbsp;resolve.&nbsp;<code>promisePool</code>&nbsp;should execute <code>functions[i]</code>&nbsp;then <code>functions[i + 1]</code>&nbsp;then <code>functions[i + 2]</code>, etc. When the last promise resolves,&nbsp;<code>promisePool</code>&nbsp;should also resolve.</p>

<p>For example, if&nbsp;<code>n = 1</code>, <code>promisePool</code>&nbsp;will execute one function at&nbsp;a time in&nbsp;series. However, if&nbsp;<code>n = 2</code>, it first executes two functions. When either of the two functions resolve, a 3rd function should be executed (if available), and so on until there are no functions left to execute.</p>

<p>You can assume all&nbsp;<code>functions</code>&nbsp;never reject. It is acceptable for&nbsp;<code>promisePool</code>&nbsp;to return a promise that resolves any value.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
functions = [
&nbsp; () =&gt; new Promise(res =&gt; setTimeout(res, 300)),
&nbsp; () =&gt; new Promise(res =&gt; setTimeout(res, 400)),
&nbsp; () =&gt; new Promise(res =&gt; setTimeout(res, 200))
]
n = 2
<strong>Output:</strong> [[300,400,500],500]
<strong>Explanation:</strong>
Three functions are passed in. They sleep for 300ms, 400ms, and 200ms respectively.
At t=0, the first 2 functions are executed. The pool size limit of 2 is reached.
At t=300, the 1st function resolves, and the 3rd function is executed. Pool size is 2.
At t=400, the 2nd function resolves. There is nothing left to execute. Pool size is 1.
At t=500, the 3rd function resolves. Pool size is zero so the returned promise also resolves.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:
</strong>functions = [
&nbsp; () =&gt; new Promise(res =&gt; setTimeout(res, 300)),
&nbsp; () =&gt; new Promise(res =&gt; setTimeout(res, 400)),
&nbsp; () =&gt; new Promise(res =&gt; setTimeout(res, 200))
]
n = 5
<strong>Output:</strong> [[300,400,200],400]
<strong>Explanation:</strong>
At t=0, all 3 functions are executed. The pool limit of 5 is never met.
At t=200, the 3rd function resolves. Pool size is 2.
At t=300, the 1st function resolved. Pool size is 1.
At t=400, the 2nd function resolves. Pool size is 0, so the returned promise also resolves.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong>
functions = [
&nbsp; () =&gt; new Promise(res =&gt; setTimeout(res, 300)),
&nbsp; () =&gt; new Promise(res =&gt; setTimeout(res, 400)),
&nbsp; () =&gt; new Promise(res =&gt; setTimeout(res, 200))
]
n = 1
<strong>Output:</strong> [[300,700,900],900]
<strong>Explanation:</strong>
At t=0, the 1st function is executed. Pool size is 1.
At t=300, the 1st function resolves and the 2nd function is executed. Pool size is 1.
At t=700, the 2nd function resolves and the 3rd function is executed. Pool size is 1.
At t=900, the 3rd function resolves. Pool size is 0 so the returned promise resolves.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= functions.length &lt;= 10</code></li>
	<li><code><font face="monospace">1 &lt;= n &lt;= 10</font></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **TypeScript**

```ts
type F = () => Promise<any>;

function promisePool(functions: F[], n: number): Promise<any> {
    const wrappers = functions.map(fn => async () => {
        await fn();
        const nxt = waiting.shift();
        nxt && (await nxt());
    });

    const running = wrappers.slice(0, n);
    const waiting = wrappers.slice(n);
    return Promise.all(running.map(fn => fn()));
}
```

### **...**

```

```

<!-- tabs:end -->
