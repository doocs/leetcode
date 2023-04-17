# [2636. Promice 对象池](https://leetcode.cn/problems/promise-pool)

[English Version](/solution/2600-2699/2636.Promise%20Pool/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>请你编写一个异步函数 <code>promisePool</code> ，它接收一个异步函数数组 <code>functions</code> 和 <strong>池限制</strong> <code>n</code>。它应该返回一个 promise 对象，当所有输入函数都执行完毕后，promise 对象就执行完毕。</p>

<p><strong>池限制</strong> 定义是一次可以挂起的最多 promise 对象的数量。<code>promisePool</code> 应该开始执行尽可能多的函数，并在旧的 promise 执行完毕后继续执行新函数。<code>promisePool</code> 应该先执行 <code>functions[i]</code>，再执行 <code>functions[i + 1]</code>，然后执行&nbsp;<code>functions[i + 2]</code>，等等。当最后一个 promise 执行完毕时，<code>promisePool</code> 也应该执行完毕。</p>

<p>例如，如果 <code>n = 1</code> , <code>promisePool</code>&nbsp;在序列中每次执行一个函数。然而，如果 <code>n = 2</code> ，它首先执行两个函数。当两个函数中的任何一个执行完毕后，再执行第三个函数(如果它是可用的)，依此类推，直到没有函数要执行为止。</p>

<p>你可以假设所有的 <code>functions</code> 都不会被拒绝。对于 <code>promisePool</code> 来说，返回一个可以解析任何值的 promise 都是可以接受的。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>
functions = [
&nbsp; () =&gt; new Promise(res =&gt; setTimeout(res, 300)),
&nbsp; () =&gt; new Promise(res =&gt; setTimeout(res, 400)),
&nbsp; () =&gt; new Promise(res =&gt; setTimeout(res, 200))
]
n = 2
<b>输出：</b>[[300,400,500],500]
<strong>解释</strong>
传递了三个函数。它们的睡眠时间分别为 300ms、 400ms 和 200ms。
在 t=0 时，执行前两个函数。池大小限制达到 2。
当 t=300 时，第一个函数执行完毕后，执行第3个函数。池大小为 2。
在 t=400 时，第二个函数执行完毕后。没有什么可执行的了。池大小为 1。
在 t=500 时，第三个函数执行完毕后。池大小为 0，因此返回的 promise 也执行完成。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：
</strong>functions = [
&nbsp; () =&gt; new Promise(res =&gt; setTimeout(res, 300)),
&nbsp; () =&gt; new Promise(res =&gt; setTimeout(res, 400)),
&nbsp; () =&gt; new Promise(res =&gt; setTimeout(res, 200))
]
n = 5
<b>输出：</b>[[300,400,200],400]
<strong>解释：</strong>
在 t=0 时，所有3个函数都被执行。池的限制大小 5 永远不会满足。
在 t=200 时，第三个函数执行完毕后。池大小为 2。
在 t=300 时，第一个函数执行完毕后。池大小为 1。
在 t=400 时，第二个函数执行完毕后。池大小为 0，因此返回的 promise 也执行完成。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>
functions = [
&nbsp; () =&gt; new Promise(res =&gt; setTimeout(res, 300)),
&nbsp; () =&gt; new Promise(res =&gt; setTimeout(res, 400)),
&nbsp; () =&gt; new Promise(res =&gt; setTimeout(res, 200))
]
n = 1
<b>输出：</b>[[300,700,900],900]
<strong>解释：</strong>
在 t=0 时，执行第一个函数。池大小为1。
当 t=300 时，第一个函数执行完毕后，执行第二个函数。池大小为 1。
当 t=700 时，第二个函数执行完毕后，执行第三个函数。池大小为 1。
在 t=900 时，第三个函数执行完毕后。池大小为 0，因此返回的 Promise 也执行完成。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= functions.length &lt;= 10</code></li>
	<li><code><font face="monospace">1 &lt;= n &lt;= 10</font></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
