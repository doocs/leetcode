# [2715. 执行可取消的延迟函数](https://leetcode.cn/problems/execute-cancellable-function-with-delay)

[English Version](/solution/2700-2799/2715.Execute%20Cancellable%20Function%20With%20Delay/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>现给定一个函数 <code>fn</code>&nbsp;，一个参数数组 <code>args</code> 和一个以毫秒为单位的超时时间 <code>t</code> ，返回一个取消函数 <code>cancelFn</code> 。</p>

<p>在经过 <code>t</code> 毫秒的延迟后，<strong>除非</strong> 先调用 <code>cancelFn</code> ，否则&nbsp;<code>fn</code> 应该以 <code>args</code> 作为参数被调用。并且在这种情况下，<code>fn</code> 不应该被调用。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<pre>
<b>输入：</b>fn = (x) =&gt; x * 5, args = [2], t = 20
<b>输出：</b>[{"time": 20, "returned": 10}]
<b>解释：</b>
const cancelTime = 50
const cancel = cancellable((x) =&gt; x * 5, [2], 20); // 在 t=20ms 时调用 fn(2)
setTimeout(cancel, 50);

cancelTime（50ms）在延迟时间（20ms）之后，所以 fn(2) 应该在 t=20ms 时调用。fn 的返回值是 10。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>fn = (x) =&gt; x**2, args = [2], t = 100
<b>输出：</b>[]
<b>解释：</b>
const cancelTime = 50 
const cancel = cancellable((x) =&gt; x**2, [2], 100); // fn(2) 没被调用
setTimeout(cancel, cancelTime);

fn(2) 从未被调用，因为 cancelTime（50ms）在延迟时间（100ms）之前。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>fn = (x1, x2) =&gt; x1 * x2, args = [2,4], t = 30, cancelTime = 100
<b>输出：</b>[{"time": 30, "returned": 8}]
<b>解释：</b>
const cancelTime = 100
const cancel = cancellable((x1, x2) =&gt; x1 * x2, [2,4], 30); // fn(2,4) 在 t=30ms 时被调用
setTimeout(cancel, cancelTime);

fn(2) 从未被调用，因为 cancelTime（50ms）在延迟时间（100ms）之前。
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

```

<!-- tabs:end -->
