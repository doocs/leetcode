# [2630. 记忆函数 II](https://leetcode.cn/problems/memoize-ii)

[English Version](/solution/2600-2699/2630.Memoize%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>请你编写一个函数，它接收一个函数参数&nbsp;<code>fn</code>，并返回该函数的 <strong>记忆化</strong> 后的结果。</p>

<p><strong>记忆函数</strong> 是一个对于相同的输入永远不会被调用两次的函数。相反，它将返回一个缓存值。</p>

<p><code>fn</code> 可以是任何函数，对于它接受什么类型的值没有限制。如果输入为&nbsp;<code>===</code>，则认为输入相同。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong> 
getInputs = () =&gt; [[2,2],[2,2],[1,2]]
fn = function (a, b) { return a + b; }
<b>输出：</b>[{"val":4,"calls":1},{"val":4,"calls":1},{"val":3,"calls":2}]
<strong>解释：</strong>
const inputs = getInputs();
const memoized = memoize(fn);
for (const arr of inputs) {
  memoized(...arr);
}

对于参数为 (2, 2) 的输入: 2 + 2 = 4，需要调用 fn() 。
对于参数为 (2, 2) 的输入: 2 + 2 = 4，这些输入此前调用过，因此不需要调用 fn() 。
对于参数为 (1, 2) 的输入: 1 + 2 = 3，需要再次调用 fn()，总调用数为 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>
getInputs = () =&gt; [[{},{}],[{},{}],[{},{}]] 
fn = function (a, b) { return a + b; }
<b>输出：</b>[{"val":{},"calls":1},{"val":{},"calls":2},{"val":{},"calls":3}]
<strong>解释：</strong>
合并两个空对象总是会得到一个空对象。因为缓存命中，所以只有 1 次对 fn() 的调用，尽管这些对象之间没有一个是相同的（===）。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong> 
getInputs = () =&gt; { const o = {}; return [[o,o],[o,o],[o,o]]; }
fn = function (a, b) { return ({...a, ...b}); }
<b>输出：</b>[{"val":{},"calls":1},{"val":{},"calls":1},{"val":{},"calls":1}]
<strong>解释：</strong>
合并两个空对象总是会得到一个空对象。第 2 和第 3 个函数调用导致缓存命中。这是因为传入的每个对象都是相同的。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= inputs.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= inputs.flat().length &lt;= 10<sup>5</sup></code></li>
	<li><code>inputs[i][j] != NaN</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts

```

### **...**

```

```

<!-- tabs:end -->
