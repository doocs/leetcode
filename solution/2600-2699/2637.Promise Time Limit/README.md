# [2637. 有时间限制的 Promise 对象](https://leetcode.cn/problems/promise-time-limit)

[English Version](/solution/2600-2699/2637.Promise%20Time%20Limit/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>请你编写一个函数，它接收一个异步函数 <code>fn</code>&nbsp;和一个以毫秒为单位的时间 <code>t</code>。它应根据限时函数返回一个有 <strong>限时</strong> 效果的函数。</p>

<p>限时函数是与原函数相同的函数，除非它需要 <code>t</code> 毫秒以上的时间来完成。如果出现了这种情况，请你返回 <code>"Time Limit Exceeded"</code>&nbsp;拒绝这次函数的调用。注意，它应该返回一个字符串拒绝，而不是一个&nbsp;<code>Error</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<pre>
<b>输入：</b>
fn = async (n) =&gt; { 
&nbsp; await new Promise(res =&gt; setTimeout(res, 100)); 
&nbsp; return n * n; 
}
inputs = [5]
t = 50
<b>输出：</b>{"rejected":"Time Limit Exceeded","time":50}
<b>解释：
</b>提供的函数设置在 100ms 后执行完成，但是设置的超时时间为 50ms，所以在 t=50ms 时拒绝因为达到了超时时间。
</pre>

<p><b>示例 2：</b></p>

<pre>
<b>输入：</b>
fn = async (n) =&gt; { 
&nbsp; await new Promise(res =&gt; setTimeout(res, 100)); 
&nbsp; return n * n; 
}
inputs = [5]
t = 150
<b>输出：</b>{"resolved":25,"time":100}
<b>解释：</b>
在 t=100ms 时执行 5*5=25 ，没有达到超时时间。
</pre>

<p><b>示例 3：</b></p>

<pre>
<b>输入：</b>
fn = async (a, b) =&gt; { 
&nbsp; await new Promise(res =&gt; setTimeout(res, 120)); 
&nbsp; return a + b; 
}
inputs = [5,10]
t = 150
<b>输出：</b>{"resolved":15,"time":120}
<b>解释：
</b>在 t=120ms 时执行 5+10=15，没有达到超时时间。
</pre>

<p><b>示例 4：</b></p>

<pre>
<b>输入：</b>
fn = async () =&gt; { 
&nbsp; throw "Error";
}
inputs = []
t = 1000
<b>输出：</b>{"rejected":"Error","time":0}
<b>解释：</b>
此函数始终丢出 Error</pre>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>0 &lt;= inputs.length &lt;= 10</code></li>
	<li><code>0 &lt;= t &lt;= 1000</code></li>
	<li><code>fn 返回一个 Promise 对象</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts
type Fn = (...params: any[]) => Promise<any>;

function timeLimit(fn: Fn, t: number): Fn {
    return async function (...args) {
        return Promise.race([
            fn(...args),
            new Promise((_, reject) =>
                setTimeout(() => reject('Time Limit Exceeded'), t),
            ),
        ]);
    };
}

/**
 * const limited = timeLimit((t) => new Promise(res => setTimeout(res, t)), 100);
 * limited(150).catch(console.log) // "Time Limit Exceeded" at t=100ms
 */
```

### **...**

```

```

<!-- tabs:end -->
