# [2637. Promise Time Limit](https://leetcode.com/problems/promise-time-limit)

[中文文档](/solution/2600-2699/2637.Promise%20Time%20Limit/README.md)

## Description

<p>Given an&nbsp;asyncronous function&nbsp;<code>fn</code>&nbsp;and a time <code>t</code>&nbsp;in milliseconds, return&nbsp;a new&nbsp;<strong>time limited</strong>&nbsp;version of the input function.</p>

<p>A&nbsp;<strong>time limited</strong>&nbsp;function is a function that is identical to the original unless it takes longer than&nbsp;<code>t</code>&nbsp;milliseconds to fullfill. In that case, it will reject with&nbsp;<code>&quot;Time Limit Exceeded&quot;</code>.&nbsp; Note that it should reject with a string, not an&nbsp;<code>Error</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
fn = async (n) =&gt; { 
&nbsp; await new Promise(res =&gt; setTimeout(res, 100)); 
&nbsp; return n * n; 
}
inputs = [5]
t = 50
<strong>Output:</strong> {&quot;rejected&quot;:&quot;Time Limit Exceeded&quot;,&quot;time&quot;:50}
<strong>Explanation:</strong>
The provided function is set to resolve after 100ms. However, the time limit is set to 50ms. It rejects at t=50ms because the time limit was reached.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> 
fn = async (n) =&gt; { 
&nbsp; await new Promise(res =&gt; setTimeout(res, 100)); 
&nbsp; return n * n; 
}
inputs = [5]
t = 150
<strong>Output:</strong> {&quot;resolved&quot;:25,&quot;time&quot;:100}
<strong>Explanation:</strong>
The function resolved 5 * 5 = 25 at t=100ms. The time limit is never reached.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> 
fn = async (a, b) =&gt; { 
&nbsp; await new Promise(res =&gt; setTimeout(res, 120)); 
&nbsp; return a + b; 
}
inputs = [5,10]
t = 150
<strong>Output:</strong> {&quot;resolved&quot;:15,&quot;time&quot;:120}
<strong>Explanation:</strong>
The function resolved 5 + 10 = 15 at t=120ms. The time limit is never reached.
</pre>

<p><strong class="example">Example 4:</strong></p>

<pre>
<strong>Input:</strong> 
fn = async () =&gt; { 
&nbsp; throw &quot;Error&quot;;
}
inputs = []
t = 1000
<strong>Output:</strong> {&quot;rejected&quot;:&quot;Error&quot;,&quot;time&quot;:0}
<strong>Explanation:</strong>
The function immediately throws an error.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= inputs.length &lt;= 10</code></li>
	<li><code>0 &lt;= t &lt;= 1000</code></li>
	<li><code>fn returns a promise</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **TypeScript**

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
