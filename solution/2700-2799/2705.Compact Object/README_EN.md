# [2705. Compact Object](https://leetcode.com/problems/compact-object)

[中文文档](/solution/2700-2799/2705.Compact%20Object/README.md)

## Description

<p>Given an object or array&nbsp;<code>obj</code>, return a <strong>compact object</strong>. A <strong>compact object</strong>&nbsp;is the same as the original object, except with keys containing <strong>falsy</strong> values removed. This operation applies to the object and any nested objects. Arrays are considered objects where&nbsp;the indices are&nbsp;keys. A value is&nbsp;considered <strong>falsy</strong>&nbsp;when <code>Boolean(value)</code> returns <code>false</code>.</p>

<p>You may assume the&nbsp;<code>obj</code> is&nbsp;the output of&nbsp;<code>JSON.parse</code>. In other words, it is valid JSON.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> obj = [null, 0, false, 1]
<strong>Output:</strong> [1]
<strong>Explanation:</strong> All falsy values have been removed from the array.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> obj = {&quot;a&quot;: null, &quot;b&quot;: [false, 1]}
<strong>Output:</strong> {&quot;b&quot;: [1]}
<strong>Explanation:</strong> obj[&quot;a&quot;] and obj[&quot;b&quot;][0] had falsy values and were removed.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> obj = [null, 0, 5, [0], [false, 16]]
<strong>Output:</strong> [5, [], [16]]
<strong>Explanation:</strong> obj[0], obj[1], obj[3][0], and obj[4][0] were falsy and removed.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>obj is a valid JSON object</code></li>
	<li><code>2 &lt;= JSON.stringify(obj).length &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **TypeScript**

```ts

```

<!-- tabs:end -->
