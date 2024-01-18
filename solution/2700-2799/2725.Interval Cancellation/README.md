# [2725. 间隔取消](https://leetcode.cn/problems/interval-cancellation)

[English Version](/solution/2700-2799/2725.Interval%20Cancellation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>现给定一个函数 <code>fn</code>，一个参数数组 <code>args</code> 和一个时间间隔 <code>t</code>，返回一个取消函数 <code>cancelFn</code>。</p>

<p>函数 <code>fn</code> 应该立即使用 <code>args</code> 调用，并且在每个 <code>t</code> 毫秒内再次调用，直到调用 <code>cancelFn</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>fn = (x) =&gt; x * 2, args = [4], t = 35, cancelT = 190
<b>输出：</b>
[
   {"time": 0, "returned": 8},
   {"time": 35, "returned": 8},
   {"time": 70, "returned": 8},
   {"time": 105, "returned": 8},
   {"time": 140, "returned": 8},
   {"time": 175, "returned": 8}
]
<strong>解释：</strong> 
const result = []
const fn = (x) =&gt; x * 2
const args = [4], t = 35, cancelT = 190

const start = performance.now()

const log = (...argsArr) =&gt; {
    const diff = Math.floor(performance.now() - start)
    result.push({"time": diff, "returned": fn(...argsArr)})
}

const cancel = cancellable(log, [4], 35);
setTimeout(cancel, 190);

setTimeout(() =&gt; {
    console.log(result) // Output
 }, cancelT + t + 15) 

每隔 35ms，调用 fn(4)。直到 t=190ms，然后取消。
第一次调用 fn 是在 0ms。fn(4) 返回 8。
第二次调用 fn 是在 35ms。fn(4) 返回 8。
第三次调用 fn 是在 70ms。fn(4) 返回 8。
第四次调用 fn 是在&nbsp;105ms。fn(4) 返回 8。
第五次调用 fn 是在 140ms。fn(4) 返回 8。
第六次调用 fn 是在 175ms。fn(4) 返回 8。
在 t=190ms 时取消
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>fn = (x1, x2) =&gt; (x1 * x2), args = [2, 5], t = 30, cancelT = 165
<strong>输出：</strong> 
[
   {"time": 0, "returned": 10},
   {"time": 30, "returned": 10},
   {"time": 60, "returned": 10},
   {"time": 90, "returned": 10},
   {"time": 120, "returned": 10},
   {"time": 150, "returned": 10}
]
<strong>解释：</strong>
const cancel = cancellable((x1, x2) =&gt; (x1 * x2), [2, 5], 30); 
setTimeout(cancel, 165);

每隔 30ms，调用 fn(2, 5)。直到 t=165ms，然后取消。
第一次调用 fn 是在 0ms
第二次调用 fn 是在 30ms
第三次调用 fn 是在 60ms
第四次调用 fn 是在&nbsp;90ms
第五次调用 fn 是在 120ms
第六次调用 fn 是在 150ms
在 165ms 取消
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>fn = (x1, x2, x3) =&gt; (x1 + x2 + x3), args = [5, 1, 3], t = 50, cancelT = 180
<b>输出：</b>
[
   {"time": 0, "returned": 9},
   {"time": 50, "returned": 9},
   {"time": 100, "returned": 9},
   {"time": 150, "returned": 9}
]
<b>解释：</b>
const cancel = cancellable((x1, x2, x3) =&gt; (x1 + x2 + x3), [5, 1, 3], 50);
setTimeout(cancel, cancelT);

每隔 50ms，调用 fn(5, 1, 3)。直到 t=180ms，然后取消。
第一次调用 fn 是在 0ms
第二次调用 fn 是在 50ms
第三次调用 fn 是在 100ms
第四次调用 fn 是在&nbsp;150ms
在 180ms 取消
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>fn</code> 是一个函数</li>
	<li><code>args</code> 是一个有效的 JSON 数组</li>
	<li><code>1 &lt;= args.length &lt;= 10</code></li>
	<li><code><font face="monospace">30 &lt;= t &lt;= 100</font></code></li>
	<li><code><font face="monospace">10 &lt;= cancelT &lt;= 500</font></code></li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

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

<!-- end -->
