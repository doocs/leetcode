# [2629. Function Composition](https://leetcode.com/problems/function-composition)

[中文文档](/solution/2600-2699/2629.Function%20Composition/README.md)

## Description

<p>Given an array of functions&nbsp;<code>[f<span style="font-size: 10.8333px;">1</span>, f<sub>2</sub>, f<sub>3</sub>,&nbsp;..., f<sub>n</sub>]</code>, return&nbsp;a new function&nbsp;<code>fn</code>&nbsp;that is the <strong>function&nbsp;composition</strong> of the array of functions.</p>

<p>The&nbsp;<strong>function&nbsp;composition</strong>&nbsp;of&nbsp;<code>[f(x), g(x), h(x)]</code>&nbsp;is&nbsp;<code>fn(x) = f(g(h(x)))</code>.</p>

<p>The&nbsp;<strong>function&nbsp;composition</strong>&nbsp;of an empty list of functions is the&nbsp;<strong>identity function</strong>&nbsp;<code>f(x) = x</code>.</p>

<p>You may assume each&nbsp;function&nbsp;in the array accepts one integer as input&nbsp;and returns one integer as output.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> functions = [x =&gt; x + 1, x =&gt; x * x, x =&gt; 2 * x], x = 4
<strong>Output:</strong> 65
<strong>Explanation:</strong>
Evaluating from right to left ...
Starting with x = 4.
2 * (4) = 8
(8) * (8) = 64
(64) + 1 = 65
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> functions = [x =&gt; 10 * x, x =&gt; 10 * x, x =&gt; 10 * x], x = 1
<strong>Output:</strong> 1000
<strong>Explanation:</strong>
Evaluating from right to left ...
10 * (1) = 10
10 * (10) = 100
10 * (100) = 1000
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> functions = [], x = 42
<strong>Output:</strong> 42
<strong>Explanation:</strong>
The composition of zero functions is the identity function</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code><font face="monospace">-1000 &lt;= x &lt;= 1000</font></code></li>
	<li><code><font face="monospace">0 &lt;= functions.length &lt;= 1000</font></code></li>
	<li><font face="monospace"><code>all functions accept and return a single integer</code></font></li>
</ul>

## Solutions

<!-- tabs:start -->

### **TypeScript**

```ts
type F = (x: number) => number;

function compose(functions: F[]): F {
    return function (x) {
        return functions.reduceRight((acc, fn) => fn(acc), x);
    };
}

/**
 * const fn = compose([x => x + 1, x => 2 * x])
 * fn(4) // 9
 */
```

### **...**

```

```

<!-- tabs:end -->
