---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2795.Parallel%20Execution%20of%20Promises%20for%20Individual%20Results%20Retrieval/README_EN.md
tags:
    - JavaScript
---

<!-- problem:start -->

# [2795. Parallel Execution of Promises for Individual Results Retrieval 🔒](https://leetcode.com/problems/parallel-execution-of-promises-for-individual-results-retrieval)

[中文文档](/solution/2700-2799/2795.Parallel%20Execution%20of%20Promises%20for%20Individual%20Results%20Retrieval/README.md)

## Description

<!-- description:start -->

<p>Given an array&nbsp;<code>functions</code>, return a promise <code>promise</code>. <code>functions</code>&nbsp;is an array of functions that return promises <code>fnPromise.</code>&nbsp;Each <code>fnPromise</code>&nbsp;can be resolved or rejected.&nbsp;&nbsp;</p>

<p>If&nbsp;<code>fnPromise</code> is resolved:</p>

<p>&nbsp; &nbsp; <code>obj = { status: &quot;fulfilled&quot;, value: <em>resolved value</em>}</code></p>

<p>If&nbsp;<code>fnPromise</code> is rejected:</p>

<p>&nbsp; &nbsp;&nbsp;<code>obj = { status: &quot;rejected&quot;, reason: <em>reason of rejection (catched error message)</em>}</code></p>

<p>The <code>promise</code>&nbsp;should resolve with an array of these objects <code>obj</code>.&nbsp;Each <code>obj</code> in the array should correspond&nbsp;to the promises in the original array function, <strong>maintaining the same order</strong>.</p>

<p>Try to implement it without using the built-in method&nbsp;<code>Promise.allSettled()</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> functions = [
    () =&gt; new Promise(resolve =&gt; setTimeout(() =&gt; resolve(15), 100))
]
<strong>Output: </strong>{&quot;t&quot;:100,&quot;values&quot;:[{&quot;status&quot;:&quot;fulfilled&quot;,&quot;value&quot;:15}]}
<strong>Explanation:</strong> 
const time = performance.now()
const promise = promiseAllSettled(functions);
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
promise.then(res =&gt; {
    const out = {t: Math.floor(performance.now() - time), values: res}
    console.log(out) // {&quot;t&quot;:100,&quot;values&quot;:[{&quot;status&quot;:&quot;fulfilled&quot;,&quot;value&quot;:15}]}
})

The returned promise resolves within 100 milliseconds. Since promise from the array functions is fulfilled, the resolved value of the returned promise is set to [{&quot;status&quot;:&quot;fulfilled&quot;,&quot;value&quot;:15}].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> functions = [
    () =&gt; new Promise(resolve =&gt; setTimeout(() =&gt; resolve(20), 100)), 
    () =&gt; new Promise(resolve =&gt; setTimeout(() =&gt; resolve(15), 100))
]
<strong>Output: 
</strong>{
    &quot;t&quot;:100,
    &quot;values&quot;: [
&nbsp;       {&quot;status&quot;:&quot;fulfilled&quot;,&quot;value&quot;:20},
&nbsp;       {&quot;status&quot;:&quot;fulfilled&quot;,&quot;value&quot;:15}
    ]
}
<strong>Explanation:</strong> The returned promise resolves within 100 milliseconds, because the resolution time is determined by the promise that takes the longest time to fulfill. Since promises from the array functions are fulfilled, the resolved value of the returned promise is set to [{&quot;status&quot;:&quot;fulfilled&quot;,&quot;value&quot;:20},{&quot;status&quot;:&quot;fulfilled&quot;,&quot;value&quot;:15}].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> functions = [
&nbsp;   () =&gt; new Promise(resolve =&gt; setTimeout(() =&gt; resolve(30), 200)), 
&nbsp;   () =&gt; new Promise((resolve, reject) =&gt; setTimeout(() =&gt; reject(&quot;Error&quot;), 100))
]
<strong>Output:</strong>
{
    &quot;t&quot;:200,
    &quot;values&quot;: [
        {&quot;status&quot;:&quot;fulfilled&quot;,&quot;value&quot;:30},
        {&quot;status&quot;:&quot;rejected&quot;,&quot;reason&quot;:&quot;Error&quot;}
    ]
}
<strong>Explanation:</strong> The returned promise resolves within 200 milliseconds, as its resolution time is determined by the promise that takes the longest time to fulfill. Since one promise from the array function is fulfilled and another is rejected, the resolved value of the returned promise is set to an array containing objects in the following order: [{&quot;status&quot;:&quot;fulfilled&quot;,&quot;value&quot;:30}, {&quot;status&quot;:&quot;rejected&quot;,&quot;reason&quot;:&quot;Error&quot;}]. Each object in the array corresponds to the promises in the original array function, maintaining the same order.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= functions.length &lt;= 10</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### TypeScript

```ts
type FulfilledObj = {
    status: 'fulfilled';
    value: string;
};
type RejectedObj = {
    status: 'rejected';
    reason: string;
};
type Obj = FulfilledObj | RejectedObj;

function promiseAllSettled(functions: Function[]): Promise<Obj[]> {
    return new Promise(resolve => {
        const res: Obj[] = [];
        let count = 0;
        for (let i in functions) {
            functions[i]()
                .then(value => ({ status: 'fulfilled', value }))
                .catch(reason => ({ status: 'rejected', reason }))
                .then(obj => {
                    res[i] = obj;
                    if (++count === functions.length) {
                        resolve(res);
                    }
                });
        }
    });
}

/**
 * const functions = [
 *    () => new Promise(resolve => setTimeout(() => resolve(15), 100))
 * ]
 * const time = performance.now()
 *
 * const promise = promiseAllSettled(functions);
 *
 * promise.then(res => {
 *     const out = {t: Math.floor(performance.now() - time), values: res}
 *     console.log(out) // {"t":100,"values":[{"status":"fulfilled","value":15}]}
 * })
 */
```

#### JavaScript

```js
/**
 * @param {Array<Function>} functions
 * @return {Promise}
 */
var promiseAllSettled = function (functions) {
    return new Promise(resolve => {
        const res = [];
        let count = 0;
        for (let i in functions) {
            functions[i]()
                .then(value => ({ status: 'fulfilled', value }))
                .catch(reason => ({ status: 'rejected', reason }))
                .then(obj => {
                    res[i] = obj;
                    if (++count === functions.length) {
                        resolve(res);
                    }
                });
        }
    });
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
