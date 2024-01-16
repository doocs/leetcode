# [2721. 并行执行异步函数](https://leetcode.cn/problems/execute-asynchronous-functions-in-parallel)

[English Version](/solution/2700-2799/2721.Execute%20Asynchronous%20Functions%20in%20Parallel/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个异步函数数组 <code>functions</code>，返回一个新的 promise 对象&nbsp;<code>promise</code>。数组中的每个函数都不接受参数并返回一个 promise。所有的 promise 都应该并行执行。</p>

<p><code>promise</code> resolve 条件：</p>

<ul>
	<li>当所有从 <code>functions</code> 返回的 promise 都成功的并行解析时。<code>promise</code> 的解析值应该是一个按照它们在 <code>functions</code> 中的顺序排列的 promise 的解析值数组。<code>promise</code> 应该在数组中的所有异步函数并行执行完成时解析。</li>
</ul>

<p><code>promise</code>&nbsp;reject 条件：</p>

<ul>
	<li>当任何从 <code>functions</code> 返回的 promise 被拒绝时。<code>promise</code> 也会被拒绝，并返回第一个拒绝的原因。</li>
</ul>

<p>请在不使用内置的 <code>Promise.all</code> 函数的情况下解决。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>functions = [
&nbsp; () =&gt; new Promise(resolve =&gt; setTimeout(() =&gt; resolve(5), 200))
]
<b>输出：</b>{"t": 200, "resolved": [5]}
<b>解释：</b>
promiseAll(functions).then(console.log); // [5]

单个函数在 200 毫秒后以值 5 成功解析。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>functions = [
    () =&gt; new Promise(resolve =&gt; setTimeout(() =&gt; resolve(1), 200)), 
    () =&gt; new Promise((resolve, reject) =&gt; setTimeout(() =&gt; reject("Error"), 100))
]
<b>输出：</b>{"t": 100, "rejected": "Error"}
<b>解释：</b>由于其中一个 promise 被拒绝，返回的 promise 也在同一时间被拒绝并返回相同的错误。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>functions = [
    () =&gt; new Promise(resolve =&gt; setTimeout(() =&gt; resolve(4), 50)), 
    () =&gt; new Promise(resolve =&gt; setTimeout(() =&gt; resolve(10), 150)), 
    () =&gt; new Promise(resolve =&gt; setTimeout(() =&gt; resolve(16), 100))
]
<b>输出：</b>{"t": 150, "resolved": [4, 10, 16]}
<b>解释：</b>所有的 promise 都成功执行。当最后一个 promise 被解析时，返回的 promise 也被解析了。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>函数 <code>functions</code> 是一个返回 promise 的函数数组</li>
	<li><code>1 &lt;= functions.length &lt;= 10</code></li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```ts
async function promiseAll<T>(functions: (() => Promise<T>)[]): Promise<T[]> {
    return new Promise<T[]>((resolve, reject) => {
        let cnt = 0;
        const ans = new Array(functions.length);
        for (let i = 0; i < functions.length; ++i) {
            const f = functions[i];
            f()
                .then(res => {
                    ans[i] = res;
                    cnt++;
                    if (cnt === functions.length) {
                        resolve(ans);
                    }
                })
                .catch(err => {
                    reject(err);
                });
        }
    });
}

/**
 * const promise = promiseAll([() => new Promise(res => res(42))])
 * promise.then(console.log); // [42]
 */
```

<!-- tabs:end -->

<!-- end -->
