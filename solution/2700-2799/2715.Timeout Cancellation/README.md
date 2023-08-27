# [2715. 执行可取消的延迟函数](https://leetcode.cn/problems/execute-cancellable-function-with-delay)

[English Version](/solution/2700-2799/2715.Timeout%20Cancellation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>现给定一个函数 <code>fn</code>&nbsp;，一个参数数组 <code>args</code> 和一个以毫秒为单位的超时时间 <code>t</code> ，返回一个取消函数 <code>cancelFn</code> 。</p>

<p>在经过 <code>t</code> 毫秒的延迟后，应该调用 <code>fn</code> 函数，并将 <code>args</code> 作为参数传递。<strong>除非</strong> 在 <code>t</code> 毫秒的延迟过程中，在 <code>cancelT</code> 毫秒时调用了 <code>cancelFn</code>。并且在这种情况下，<code>fn</code> 函数不应该被调用。</p>

<p><strong class="example">示例 1:</strong></p>

<pre>
<b>输入：</b>fn = (x) =&gt; x * 5, args = [2], t = 20, cancelT = 50
<b>输出：</b>[{"time": 20, "returned": 10}]
<b>解释：</b>
const cancel = cancellable((x) =&gt; x * 5, [2], 20); // fn(2) 在 t=20ms 时被调用
setTimeout(cancel, 50);

取消操作被安排在延迟了 cancelT（50毫秒）后进行，这发生在 fn(2) 在20毫秒时执行之后。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>fn = (x) =&gt; x**2, args = [2], t = 100, cancelT = 50
<b>输出：</b>[]
<b>解释：</b>
const cancel = cancellable((x) =&gt; x**2, [2], 100); // fn(2) 没被调用
setTimeout(cancel, 50);

取消操作被安排在延迟了 cancelT（50毫秒）后进行，这发生在 fn(2) 在100毫秒时执行之前，导致 fn(2) 从未被调用。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>fn = (x1, x2) =&gt; x1 * x2, args = [2,4], t = 30, cancelTime = 100
<b>输出：</b>[{"time": 30, "returned": 8}]
<b>解释：</b>
const cancel = cancellable((x1, x2) =&gt; x1 * x2, [2,4], 30); // fn(2,4) 在 t=30ms 时被调用
setTimeout(cancel, 100);

取消操作被安排在延迟了 cancelT（100毫秒）后进行，这发生在 fn(2,4) 在30毫秒时执行之后。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>fn 是一个函数</code></li>
	<li><code>args 是一个有效的 JSON 数组</code></li>
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
