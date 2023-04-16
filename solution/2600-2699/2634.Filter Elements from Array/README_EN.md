# [2634. Filter Elements from Array](https://leetcode.com/problems/filter-elements-from-array)

[中文文档](/solution/2600-2699/2634.Filter%20Elements%20from%20Array/README.md)

## Description

<p>Given an integer array&nbsp;<code>arr</code>&nbsp;and a filtering&nbsp;function&nbsp;<code>fn</code>,&nbsp;return&nbsp;a new array with a fewer or equal number of elements.</p>

<p>The returned array should only contain elements where&nbsp;<code>fn(arr[i],&nbsp;i)</code>&nbsp;evaluated to a truthy value.</p>

<p>Please solve it without the built-in <code>Array.filter</code> method.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [0,10,20,30], fn = function greaterThan10(n) { return n &gt; 10; }
<strong>Output:</strong> [20,30]
<strong>Explanation:</strong>
const newArray = filter(arr, fn); // [20, 30]
The function filters out values that are not greater than 10</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2,3], fn = function firstIndex(n, i) { return i === 0; }
<strong>Output:</strong> [1]
<strong>Explanation:</strong>
fn can also accept the index of each element
In this case, the function removes elements not at index 0
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [-2,-1,0,1,2], fn = function plusOne(n) { return n + 1 }
<strong>Output:</strong> [-2,0,1,2]
<strong>Explanation:</strong>
Falsey values such as 0 should be filtered out
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= arr.length &lt;= 1000</code></li>
	<li><code><font face="monospace">-10<sup>9</sup>&nbsp;&lt;= arr[i] &lt;= 10<sup>9</sup></font></code></li>
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
