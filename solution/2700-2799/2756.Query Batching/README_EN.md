---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2756.Query%20Batching/README_EN.md
---

<!-- problem:start -->

# [2756. Query Batching 🔒](https://leetcode.com/problems/query-batching)

[中文文档](/solution/2700-2799/2756.Query%20Batching/README.md)

## Description

<!-- description:start -->

<p>Batching multiple small queries into a single large query can be a useful optimization. Write a class&nbsp;<code>QueryBatcher</code>&nbsp;that implements this functionality.</p>

<p>The constructor should accept two parameters:</p>

<ul>
	<li>An asynchronous function&nbsp;<code>queryMultiple</code>&nbsp;which accepts an array of&nbsp;string keys <code>input</code>. It will resolve with an array of values that is the same length as the input array. Each index corresponds to the value associated with&nbsp;<code>input[i]</code>.&nbsp;You can assume the promise will never reject.</li>
	<li>A throttle time in milliseconds&nbsp;<code>t</code>.</li>
</ul>

<p>The class has a single method.</p>

<ul>
	<li><code>async getValue(key)</code>. Accepts a single string key and resolves with a single string value. The keys passed to this function should eventually get passed to the&nbsp;<code>queryMultiple</code>&nbsp;function.&nbsp;<code>queryMultiple</code>&nbsp;should never be called consecutively within&nbsp;<code>t</code>&nbsp;milliseconds. The first time&nbsp;<code>getValue</code>&nbsp;is called,&nbsp;<code>queryMultiple</code>&nbsp;should immediately be called with that single key. If after&nbsp;<code>t</code>&nbsp;milliseconds,&nbsp;<code>getValue</code>&nbsp;had been called again, all the passed keys should be passed to&nbsp;<code>queryMultiple</code>&nbsp;and ultimately returned. You can assume every key passed to this method is unique.</li>
</ul>

<p>The following diagram illustrates how the throttling algorithm works. Each rectangle represents 100ms. The throttle time is 400ms.</p>

<p><img alt="Throttle info" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2756.Query%20Batching/images/throttle.png" style="width: 622px; height: 200px;" /></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
queryMultiple = async function(keys) { 
&nbsp; return keys.map(key =&gt; key + &#39;!&#39;);
}
t = 100 
calls = [
&nbsp;{&quot;key&quot;: &quot;a&quot;, &quot;time&quot;: 10}, 
&nbsp;{&quot;key&quot;: &quot;b&quot;, &quot;time&quot;: 20}, 
&nbsp;{&quot;key&quot;: &quot;c&quot;, &quot;time&quot;: 30}
]
<strong>Output:</strong> [
&nbsp;{&quot;resolved&quot;: &quot;a!&quot;, &quot;time&quot;: 10},
&nbsp;{&quot;resolved&quot;: &quot;b!&quot;, &quot;time&quot;: 110},
&nbsp;{&quot;resolved&quot;: &quot;c!&quot;, &quot;time&quot;: 110}
]
<strong>Explanation:</strong>
const batcher = new QueryBatcher(queryMultiple, 100);
setTimeout(() =&gt; batcher.getValue(&#39;a&#39;), 10); // &quot;a!&quot; at t=10ms
setTimeout(() =&gt; batcher.getValue(&#39;b&#39;), 20); // &quot;b!&quot; at t=110ms
setTimeout(() =&gt; batcher.getValue(&#39;c&#39;), 30); // &quot;c!&quot; at t=110ms

queryMultiple simply adds an &quot;!&quot; to the key
At t=10ms, getValue(&#39;a&#39;) is called, queryMultiple([&#39;a&#39;]) is immediately called and the result is immediately returned.
At t=20ms, getValue(&#39;b&#39;) is called but the query is queued
At t=30ms, getValue(&#39;c&#39;) is called but the query is queued.
At t=110ms, queryMultiple([&#39;a&#39;, &#39;b&#39;]) is called and the results are immediately returned.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> 
queryMultiple = async function(keys) {
&nbsp; await new Promise(res =&gt; setTimeout(res, 100));
&nbsp; return keys.map(key =&gt; key + &#39;!&#39;);
}
t = 100
calls = [
&nbsp;{&quot;key&quot;: &quot;a&quot;, &quot;time&quot;: 10},
&nbsp;{&quot;key&quot;: &quot;b&quot;, &quot;time&quot;: 20},
&nbsp;{&quot;key&quot;: &quot;c&quot;, &quot;time&quot;: 30}
]
<strong>Output:</strong> [
&nbsp; {&quot;resolved&quot;: &quot;a!&quot;, &quot;time&quot;: 110},
&nbsp; {&quot;resolved&quot;: &quot;b!&quot;, &quot;time&quot;: 210},
&nbsp; {&quot;resolved&quot;: &quot;c!&quot;, &quot;time&quot;: 210}
]
<strong>Explanation:</strong>
This example is the same as example 1 except there is a 100ms delay in queryMultiple. The results are the same except the promises resolve 100ms later.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> 
queryMultiple = async function(keys) { 
&nbsp; await new Promise(res =&gt; setTimeout(res, keys.length * 100)); 
&nbsp; return keys.map(key =&gt; key + &#39;!&#39;);
}
t = 100
calls = [
&nbsp; {&quot;key&quot;: &quot;a&quot;, &quot;time&quot;: 10}, 
  {&quot;key&quot;: &quot;b&quot;, &quot;time&quot;: 20}, 
&nbsp; {&quot;key&quot;: &quot;c&quot;, &quot;time&quot;: 30}, 
  {&quot;key&quot;: &quot;d&quot;, &quot;time&quot;: 40}, 
&nbsp; {&quot;key&quot;: &quot;e&quot;, &quot;time&quot;: 250}
&nbsp; {&quot;key&quot;: &quot;f&quot;, &quot;time&quot;: 300}
]
<strong>Output:</strong> [
&nbsp; {&quot;resolved&quot;:&quot;a!&quot;,&quot;time&quot;:110},
&nbsp; {&quot;resolved&quot;:&quot;e!&quot;,&quot;time&quot;:350},
&nbsp; {&quot;resolved&quot;:&quot;b!&quot;,&quot;time&quot;:410},
&nbsp; {&quot;resolved&quot;:&quot;c!&quot;,&quot;time&quot;:410},
&nbsp; {&quot;resolved&quot;:&quot;d!&quot;,&quot;time&quot;:410},
  {&quot;resolved&quot;:&quot;f!&quot;,&quot;time&quot;:450}
]
<strong>Explanation:
</strong>queryMultiple([&#39;a&#39;]) is called at t=10ms, it is resolved at t=110ms
queryMultiple([&#39;b&#39;, &#39;c&#39;, &#39;d&#39;]) is called at t=110ms, it is resolved at 410ms
queryMultiple([&#39;e&#39;]) is called at t=250ms, it is resolved at 350ms
queryMultiple([&#39;f&#39;]) is called at t=350ms, it is resolved at 450ms
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= t &lt;= 1000</code></li>
	<li><code>0 &lt;= calls.length &lt;= 10</code></li>
	<li><code>1 &lt;= key.length&nbsp;&lt;= 100</code></li>
	<li>All keys are unique</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

```ts

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
