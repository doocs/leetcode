# [2629. 复合函数](https://leetcode.cn/problems/function-composition)

[English Version](/solution/2600-2699/2629.Function%20Composition/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>请你编写一个函数，它接收一个函数数组 <code>[f1, f2, f3，…]， fn]</code> ，并返回一个新的函数 <code>fn</code>&nbsp;，它是函数数组的 <strong>复合函数</strong> 。</p>

<p><code>[f(x)， g(x)， h(x)]</code> 的 <strong>复合函数</strong> 为 <code>fn(x) = f(g(h(x)))</code>&nbsp;。</p>

<p>一个空函数列表的 <strong>复合函数</strong> 是 <strong>恒等函数</strong> <code>f(x) = x</code> 。</p>

<p>你可以假设数组中的每个函数接受一个整型参数作为输入，并返回一个整型作为输出。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>functions = [x =&gt; x + 1, x =&gt; x * x, x =&gt; 2 * x], x = 4
<b>输出：</b>65
<strong>解释：</strong>
从右向左计算......
Starting with x = 4.
2 * (4) = 8
(8) * (8) = 64
(64) + 1 = 65
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输出：</b>functions = [x =&gt; 10 * x, x =&gt; 10 * x, x =&gt; 10 * x], x = 1
<b>输入：</b>1000
<strong>解释：</strong>
从右向左计算......
10 * (1) = 10
10 * (10) = 100
10 * (100) = 1000
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>functions = [], x = 42
<b>输出：</b>42
<strong>解释：</strong>
空函数列表的复合函数就是恒等函数</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code><font face="monospace">-1000 &lt;= x &lt;= 1000</font></code></li>
	<li><code><font face="monospace">0 &lt;= functions.length &lt;= 1000</font></code></li>
	<li><font face="monospace"><code>所有函数都接受并返回一个整型</code></font></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
