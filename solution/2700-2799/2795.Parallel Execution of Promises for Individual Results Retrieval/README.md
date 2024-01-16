# [2795. 并行执行 Promise 以获取独有的结果](https://leetcode.cn/problems/parallel-execution-of-promises-for-individual-results-retrieval)

[English Version](/solution/2700-2799/2795.Parallel%20Execution%20of%20Promises%20for%20Individual%20Results%20Retrieval/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个数组 <code>functions</code>，返回一个 promise 对象 <code>promise</code>。<code>functions</code> 是一个返回多个 promise&nbsp;对象 <code>fnPromise</code> 的函数数组。每个 <code>fnPromise</code> 可以被解析（resolved）或拒绝（rejected）。</p>

<p>如果 <code>fnPromise</code> 被解析：</p>

<p>&nbsp; &nbsp; <code>obj = { status: "fulfilled", value:&nbsp;<em>resolved value</em>}</code></p>

<p>如果 <code>fnPromise</code> 被拒绝：</p>

<p>&nbsp; &nbsp;&nbsp;<code>obj = { status: "rejected", reason: 拒绝的原因（捕获的错误消息）}</code></p>

<p>该 <code>promise</code> 应该返回一个包含这些对象 <code>obj</code> 的数组。数组中的每个 <code>obj</code> 应该对应原始函数数组中的多个 promise 对象，并保持相同的顺序。</p>

<p>请在不使用内置方法 <code>Promise.allSettled()</code> 的情况下实现它。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>functions = [
    () =&gt; new Promise(resolve =&gt; setTimeout(() =&gt; resolve(15), 100))
]
<strong>输出：</strong>{"t":100,"values":[{"status":"fulfilled","value":15}]}
<b>解释：</b>
const time = performance.now()
const promise = promiseAllSettled(functions);
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
promise.then(res =&gt; {
    const out = {t: Math.floor(performance.now() - time), values: res}
    console.log(out) // {"t":100,"values":[{"status":"fulfilled","value":15}]}
})

返回的 promise 在 100 毫秒内解析。由于函数数组中的 promise 被解析，返回的 promise 的解析值设置为[{"status":"fulfilled","value":15}]。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>functions = [
    () =&gt; new Promise(resolve =&gt; setTimeout(() =&gt; resolve(20), 100)), 
    () =&gt; new Promise(resolve =&gt; setTimeout(() =&gt; resolve(15), 100))
]
<strong>输出：
</strong>{
    "t":100,
    "values": [
&nbsp;       {"status":"fulfilled","value":20},
&nbsp;       {"status":"fulfilled","value":15}
    ]
}
<b>解释：</b>返回的 promise 在 100 毫秒内解析，因为解析时间取决于需要最长时间来解析的 promise。由于函数数组中的 promises 被解析，返回的 promise 的解析值设置为[{"status":"fulfilled","value":20},{"status":"fulfilled","value":15}]。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>functions = [
&nbsp;   () =&gt; new Promise(resolve =&gt; setTimeout(() =&gt; resolve(30), 200)), 
&nbsp;   () =&gt; new Promise((resolve, reject) =&gt; setTimeout(() =&gt; reject("Error"), 100))
]
<strong>输出：</strong>
{
    "t":200,
    "values": [
        {"status":"fulfilled","value":30},
        {"status":"rejected","reason":"Error"}
    ]
}
<b>解释：</b>返回的 promise 在 200 毫秒内解析，因为解析时间取决于需要最长时间来解析的 promise。由于函数数组中的一个 promise 被解析，另一个被拒绝，返回的 promise 的解析值设置为[{"status":"fulfilled","value":30},{"status":"rejected","reason":"Error"}]。数组中的每个对象对应原始函数数组中的 promise，并保持相同的顺序。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= functions.length &lt;= 10</code></li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

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

<!-- end -->
