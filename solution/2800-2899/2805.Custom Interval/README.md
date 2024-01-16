# [2805. 自定义间隔](https://leetcode.cn/problems/custom-interval)

[English Version](/solution/2800-2899/2805.Custom%20Interval/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><strong>函数</strong>&nbsp;<code>customInterval</code></p>

<p>给定一个函数 <code>fn</code>、一个数字 <code>delay</code> 和一个数字 <code>period</code>，返回一个数字 <code>id</code>。<code>customInterval</code> 是一个函数，它应该根据公式 <code>delay + period * count</code> 在间隔中执行提供的函数 <code>fn</code>，公式中的 <code>count</code> 表示从初始值 0 开始执行间隔的次数。</p>

<p><strong>函数</strong> <code>customClearInterval</code></p>

<p>给定 <code>id</code>。<code>id</code> 是从函数 <code>customInterval</code> 返回的值。<code>customClearInterval</code> 应该停止在间隔中执行提供的函数 <code>fn</code>。</p>

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<pre>
<strong>输入：</strong>delay = 50, period = 20, stopTime = 225
<strong>输出：</strong>[50,120,210]
<strong>解释：</strong>
const t = performance.now()&nbsp;&nbsp;
const result = []
&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;
const fn = () =&gt; {
    result.push(Math.floor(performance.now() - t))
}
const id = customInterval(fn, delay, period)
        
setTimeout(() =&gt; {
    customClearInterval(id)
}, 225)

50 + 20 * 0 = 50 // 50ms - 第一个函数调用
50 + 20&nbsp;* 1 = 70 // 50ms + 70ms = 120ms - 第二个函数调用
50 + 20 * 2 = 90 // 50ms + 70ms + 90ms = 210ms - 第三个函数调用
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>delay = 20, period = 20, stopTime = 150
<strong>输出：</strong>[20,60,120]
<strong>解释：</strong>
20 + 20 * 0 = 20 // 20ms - 第一个函数调用
20 + 20&nbsp;* 1 = 40 // 20ms + 40ms = 60ms - 第二个函数调用
20 + 20 * 2 = 60 // 20ms + 40ms + 60ms = 120ms - 第三个函数调用
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>delay = 100, period = 200, stopTime = 500
<strong>输出：</strong>[100,400]
<strong>解释：</strong>
100 + 200 * 0 = 100 // 100ms - 第一个函数调用
100 + 200&nbsp;* 1 = 300 // 100ms + 300ms = 400ms - 第二个函数调用
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>20 &lt;= delay, period &lt;= 250</code></li>
	<li><code>20 &lt;= stopTime &lt;= 1000</code></li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```ts
const intervalMap = new Map<number, NodeJS.Timeout>();

function customInterval(fn: Function, delay: number, period: number): number {
    let count = 0;
    function recursiveTimeout() {
        intervalMap.set(
            id,
            setTimeout(() => {
                fn();
                count++;
                recursiveTimeout();
            }, delay + period * count),
        );
    }

    const id = Date.now();
    recursiveTimeout();
    return id;
}

function customClearInterval(id: number) {
    if (intervalMap.has(id)) {
        clearTimeout(intervalMap.get(id)!);
        intervalMap.delete(id);
    }
}
```

<!-- tabs:end -->

<!-- end -->
