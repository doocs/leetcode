# [2725. 间隔取消](https://leetcode.cn/problems/interval-cancellation)

[English Version](/solution/2700-2799/2725.Interval%20Cancellation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>现给定一个函数 <code>fn</code>，一个参数数组 <code>args</code> 和一个时间间隔 <code>t</code>，返回一个取消函数 <code>cancelFn</code>。</p>

<p>函数 <code>fn</code> 应该立即使用 <code>args</code> 调用，并且在每个 <code>t</code> 毫秒内再次调用，直到调用 <code>cancelFn</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>fn = (x) =&gt; x * 2, args = [4], t = 20, cancelT = 110
<b>输出：</b>
[
   {"time": 0, "returned": 8},
   {"time": 20, "returned": 8},
   {"time": 40, "returned": 8},
   {"time": 60, "returned": 8},
   {"time": 80, "returned": 8},
   {"time": 100, "returned": 8}
]
<strong>解释：</strong> 
const cancel = cancellable(x =&gt; x * 2, [4], 20);
setTimeout(cancel, cancelT);
每隔 20ms，调用 fn(4)。
第一次调用 fn 是在 0ms。fn(4) 返回 8。
第二次调用 fn 是在 20ms。fn(4) 返回 8。
第三次调用 fn 是在 40ms。fn(4) 返回 8。
第四次调用 fn 是在&nbsp;60ms。fn(4) 返回 8。
第五次调用 fn 是在 80ms。fn(4) 返回 8。
第六次调用 fn 是在 100ms。fn(4) 返回 8。
在 t=110ms 时取消。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>fn = (x1, x2) =&gt; (x1 * x2), args = [2, 5], t = 25, cancelT = 140
<strong>输出：</strong> 
[
   {"time": 0, "returned": 10},
   {"time": 25, "returned": 10},
   {"time": 50, "returned": 10},
   {"time": 75, "returned": 10},
   {"time": 100, "returned": 10},
   {"time": 125, "returned": 10}
]
<strong>解释：</strong>
const cancel = cancellable((x1, x2) =&gt; (x1 * x2), [2, 5], 25); 
setTimeout(cancel, cancelT);

每隔 25ms，调用 fn(2, 5)。直到 t=140ms，然后取消。
第一次调用 fn 是在 0ms
第二次调用 fn 是在 25ms
第三次调用 fn 是在 50ms
第四次调用 fn 是在&nbsp;75ms
第五次调用 fn 是在 100ms
第六次调用 fn 是在 125ms
在 140ms 取消
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
