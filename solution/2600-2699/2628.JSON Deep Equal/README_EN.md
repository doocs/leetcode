# [2628. JSON Deep Equal](https://leetcode.com/problems/json-deep-equal)

[中文文档](/solution/2600-2699/2628.JSON%20Deep%20Equal/README.md)

## Description

<p>Given two objects <code>o1</code>&nbsp;and <code>o2</code>, check if they are <strong>deeply equal</strong>.</p>

<p>For two objects to be <strong>deeply equal</strong>, they must contain the same keys, and the associated values must also be&nbsp;<strong>deeply equal</strong>. Two objects are also considered&nbsp;<strong>deeply equal</strong>&nbsp;if they pass the&nbsp;<code>===</code>&nbsp;equality check.</p>

<p>You may assume both objects are the output of&nbsp;<code>JSON.parse</code>. In other words, they are valid JSON.</p>

<p>Please solve it without using lodash&#39;s&nbsp;<code>_.isEqual()</code>&nbsp;function.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> o1 = {&quot;x&quot;:1,&quot;y&quot;:2}, o2 = {&quot;x&quot;:1,&quot;y&quot;:2}
<strong>Output:</strong> true
<strong>Explanation:</strong> The keys and values match exactly.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> o1 = {&quot;y&quot;:2,&quot;x&quot;:1}, o2 = {&quot;x&quot;:1,&quot;y&quot;:2}
<strong>Output:</strong> true
<strong>Explanation:</strong> Although the keys are in a different order, they still match exactly.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> o1 = {&quot;x&quot;:null,&quot;L&quot;:[1,2,3]}, o2 = {&quot;x&quot;:null,&quot;L&quot;:[&quot;1&quot;,&quot;2&quot;,&quot;3&quot;]}
<strong>Output:</strong> false
<strong>Explanation:</strong> The array of numbers is different from the array of strings.
</pre>

<p><strong class="example">Example 4:</strong></p>

<pre>
<strong>Input:</strong> o1 = true, o2 = false
<strong>Output:</strong> false
<strong>Explanation:</strong> true !== false</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= JSON.stringify(o1).length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= JSON.stringify(o2).length &lt;= 10<sup>5</sup></code></li>
	<li><code>maxNestingDepth &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
