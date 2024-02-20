# [2756. 批处理查询](https://leetcode.cn/problems/query-batching)

[English Version](/solution/2700-2799/2756.Query%20Batching/README_EN.md)

<!-- tags: -->

## 题目描述

<!-- 这里写题目描述 -->

<p>将多个小查询批处理为单个大查询可以是一种有用的优化。请编写一个名为&nbsp;<code>QueryBatcher</code>&nbsp;的类来实现这个功能。</p>

<p>它的构造函数应接受两个参数：</p>

<ul>
	<li>一个异步函数&nbsp;<code>queryMultiple</code>&nbsp;，它接受一个字符串键的数组作为输入。它将返回一个与输入数组长度相同的值数组。每个索引对应于与&nbsp;<code>input[i]</code>&nbsp;相关联的值。可以假设该异步函数永远不会被拒绝。</li>
	<li>一个以毫秒为单位的节流时间<code>t</code>。</li>
</ul>

<p>该类有一个方法：</p>

<ul>
	<li><code>async getValue(key)</code>：接受一个字符串键，并返回一个解析后的字符串值。传递给此函数的键值最终应传递给&nbsp;<code>queryMultiple</code>&nbsp;函数。在&nbsp;<code>t</code>&nbsp;毫秒内不应连续调用&nbsp;<code>queryMultiple</code>&nbsp;。第一次调用&nbsp;<code>getValue</code>&nbsp;时，应立即使用该单个键调用&nbsp;<code>queryMultiple</code>&nbsp;。如果在&nbsp;<code>t</code>&nbsp;毫秒后再次调用了&nbsp;<code>getValue</code>&nbsp;，则所有传递的键应传递给&nbsp;<code>queryMultiple</code>&nbsp;，并返回最终结果。可以假设传递给该方法的每个键都是唯一的。</li>
</ul>

<p>下图说明了节流算法的工作原理。每个矩形代表 100毫秒。节流时间为 400毫秒。</p>

<p><img alt="Throttle info" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2756.Query%20Batching/images/throttle.png" style="width: 622px; height: 200px;" /></p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>
queryMultiple = async function(keys) { 
&nbsp; return keys.map(key =&gt; key + '!');
}
t = 100 
calls = [
&nbsp;{"key": "a", "time": 10}, 
&nbsp;{"key": "b", "time": 20}, 
&nbsp;{"key": "c", "time": 30}
]
<b>输出：</b>[
&nbsp;{"resolved": "a!", "time": 10},
&nbsp;{"resolved": "b!", "time": 110},
&nbsp;{"resolved": "c!", "time": 110}
]
<strong>解释：</strong>
const batcher = new QueryBatcher(queryMultiple, 100);
setTimeout(() =&gt; batcher.getValue('a'), 10); // "a!" at t=10ms
setTimeout(() =&gt; batcher.getValue('b'), 20); // "b!" at t=110ms
setTimeout(() =&gt; batcher.getValue('c'), 30); // "c!" at t=110ms

<code>queryMultiple </code>简单地给键添加了"!"。 
在 t=10ms 时，调用 <code>getValue('a')</code>，立即调用 <code>queryMultiple(['a']) </code>并立即返回结果。 
在 t=20ms 时，调用 <code>getValue('b')</code>，但查询需要等待。 
在 t=30ms 时，调用 <code>getValue('c')</code>，但查询需要等待。 
在 t=110ms 时，调用 <code>queryMultiple(['b', 'c']) </code>并立即返回结果。
</pre>

<p><strong class="example">示例 2；</strong></p>

<pre>
<b>输入：</b>
queryMultiple = async function(keys) {
&nbsp; await new Promise(res =&gt; setTimeout(res, 100));
&nbsp; return keys.map(key =&gt; key + '!');
}
t = 100
calls = [
&nbsp;{"key": "a", "time": 10},
&nbsp;{"key": "b", "time": 20},
&nbsp;{"key": "c", "time": 30}
]
<b>输出：</b>[
&nbsp; {"resolved": "a!", "time": 110},
&nbsp; {"resolved": "b!", "time": 210},
&nbsp; {"resolved": "c!", "time": 210}
]
<strong>解释：</strong>
这个例子与示例 1 相同，只是在 <code>queryMultiple </code>中有一个 100ms 的延迟。结果也相同，只是 promise 的解析时间延迟了 100ms。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>
queryMultiple = async function(keys) { 
&nbsp; await new Promise(res =&gt; setTimeout(res, keys.length * 100)); 
&nbsp; return keys.map(key =&gt; key + '!');
}
t = 100
calls = [
&nbsp; {"key": "a", "time": 10}, 
  {"key": "b", "time": 20}, 
&nbsp; {"key": "c", "time": 30}, 
  {"key": "d", "time": 40}, 
&nbsp; {"key": "e", "time": 250}
&nbsp; {"key": "f", "time": 300}
]
<b>输出：</b>[
&nbsp; {"resolved":"a!","time":110},
&nbsp; {"resolved":"e!","time":350},
&nbsp; {"resolved":"b!","time":410},
&nbsp; {"resolved":"c!","time":410},
&nbsp; {"resolved":"d!","time":410},
  {"resolved":"f!","time":450}
]
<strong>解释：
</strong>在 t=10ms 时，调用 <code>queryMultiple(['a']) </code>，在 t=110ms 时解析。 
在 t=110ms 时，调用 <code>queryMultiple(['b', 'c', 'd']) </code>，在 t=410ms 时解析。 
在 t=250ms 时，调用 <code>queryMultiple(['e']) </code>，在 t=350ms 时解析。 
在 t=350ms 时，调用 <code>queryMultiple(['f']) </code>，在 t=450ms 时解析。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= t &lt;= 1000</code></li>
	<li><code>0 &lt;= calls.length &lt;= 10</code></li>
	<li><code>1 &lt;= key.length&nbsp;&lt;= 100</code></li>
	<li>所有的键值都是唯一的</li>
</ul>

## 解法

<!-- end -->
