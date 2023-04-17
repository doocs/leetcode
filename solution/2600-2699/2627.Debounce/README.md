# [2627. 函数防抖](https://leetcode.cn/problems/debounce)

[English Version](/solution/2600-2699/2627.Debounce/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>请你编写一个函数，接收参数为另一个函数和一个以毫秒为单位的时间 <code>t</code> ，并返回该函数的&nbsp;<b>函数防抖&nbsp;</b>后的结果。</p>

<p><b>函数防抖 </b>方法是一个函数，它的执行被延迟了 <code>t</code> 毫秒，如果在这个时间窗口内再次调用它，它的执行将被取消。你编写的防抖函数也应该接收传递的参数。</p>

<p>例如，假设 <code>t = 50ms</code> ，函数分别在 <code>30ms</code> 、 <code>60ms</code> 和 <code>100ms</code> 时调用。前两个函数调用将被取消，第三个函数调用将在 <code>150ms</code> 执行。如果改为 <code>t = 35ms</code> ，则第一个调用将被取消，第二个调用将在 <code>95ms</code> 执行，第三个调用将在 <code>135ms</code> 执行。</p>

<p><img alt="Debounce Schematic" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2627.Debounce/images/screen-shot-2023-04-08-at-11048-pm.png" style="width: 800px; height: 242px;" /></p>

<p>上图展示了了防抖函数是如何转换事件的。其中，每个矩形表示 100ms，反弹时间为 400ms。每种颜色代表一组不同的输入。</p>

<p>请在不使用 lodash 的 <code>_.debounce()</code> 函数的前提下解决该问题。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>
t = 50
calls = [
&nbsp; {"t": 50, inputs: [1]},
&nbsp; {"t": 75, inputs: [2]}
]
<b>输出：</b>[{"t": 125, inputs: [2]}]
<strong>解释：</strong>
let start = Date.now();
function log(...inputs) { 
&nbsp; console.log([Date.now() - start, inputs ])
}
const dlog = debounce(log, 50);
setTimeout(() =&gt; dlog(1), 50);
setTimeout(() =&gt; dlog(2), 75);

第一次调用被第二次调用取消，因为第二次调用发生在 100ms 之前
第二次调用延迟 50ms，在 125ms 执行。输入为 (2)。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>
t = 20
calls = [
&nbsp; {"t": 50, inputs: [1]},
&nbsp; {"t": 100, inputs: [2]}
]
<b>输出：</b>[{"t": 70, inputs: [1]}, {"t": 120, inputs: [2]}]
<strong>解释：</strong>
第一次调用延迟到 70ms。输入为 (1)。
第二次调用延迟到 120ms。输入为 (2)。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>
t = 150
calls = [
&nbsp; {"t": 50, inputs: [1, 2]},
&nbsp; {"t": 300, inputs: [3, 4]},
&nbsp; {"t": 300, inputs: [5, 6]}
]
<b>输出：</b>[{"t": 200, inputs: [1,2]}, {"t": 450, inputs: [5, 6]}]
<strong>解释：</strong>
第一次调用延迟了 150ms，运行时间为 200ms。输入为 (1, 2)。
第二次调用被第三次调用取消
第三次调用延迟了 150ms，运行时间为 450ms。输入为 (5, 6)。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= t &lt;= 1000</code></li>
	<li><code>1 &lt;= calls.length &lt;= 10</code></li>
	<li><code>0 &lt;= calls[i].t &lt;= 1000</code></li>
	<li><code>0 &lt;= calls[i].inputs.length &lt;= 10</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts
type F = (...p: any[]) => any;

function debounce(fn: F, t: number): F {
    let timeout: ReturnType<typeof setTimeout> | undefined;

    return function (...args) {
        if (timeout !== undefined) {
            clearTimeout(timeout);
        }
        timeout = setTimeout(() => {
            fn.apply(this, args);
        }, t);
    };
}

/**
 * const log = debounce(console.log, 100);
 * log('Hello'); // cancelled
 * log('Hello'); // cancelled
 * log('Hello'); // Logged at t=100ms
 */
```

### **...**

```

```

<!-- tabs:end -->
