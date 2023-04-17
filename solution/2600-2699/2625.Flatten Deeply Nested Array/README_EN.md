# [2625. Flatten Deeply Nested Array](https://leetcode.com/problems/flatten-deeply-nested-array)

[中文文档](/solution/2600-2699/2625.Flatten%20Deeply%20Nested%20Array/README.md)

## Description

<p>Given a&nbsp;<strong>multi-dimensional</strong> array&nbsp;<code>arr</code>&nbsp;and a depth <code>n</code>, return&nbsp;a&nbsp;<strong>flattened</strong>&nbsp;version of that array.</p>

<p>A <strong>multi-dimensional</strong>&nbsp;array is a recursive data structure that contains integers or other&nbsp;<strong>multi-dimensional</strong>&nbsp;arrays.</p>

<p>A&nbsp;<strong>flattened</strong>&nbsp;array is a version of that array with some or all of the sub-arrays removed and replaced with the actual elements in that sub-array. This flattening operation should only be done if the current depth of nesting&nbsp;is greater than&nbsp;<code>n</code>. The depth of the elements in the first array are considered to be&nbsp;<code>0</code>.</p>

<p>Please solve it without the built-in&nbsp;<code>Array.flat</code> method.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
arr = [1, 2, 3, [4, 5, 6], [7, 8, [9, 10, 11], 12], [13, 14, 15]]
n = 0
<strong>Output</strong>
[1, 2, 3, [4, 5, 6], [7, 8, [9, 10, 11], 12], [13, 14, 15]]

<strong>Explanation</strong>
Passing a depth of n=0 will always result in the original array. This is because the smallest possible depth of a subarray (0) is not less than n=0. Thus, no subarray should be flattened. </pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input</strong>
arr = [1, 2, 3, [4, 5, 6], [7, 8, [9, 10, 11], 12], [13, 14, 15]]
n = 1
<strong>Output</strong>
[1, 2, 3, 4, 5, 6, 7, 8, [9, 10, 11], 12, 13, 14, 15]

<strong>Explanation</strong>
The subarrays starting with 4, 7, and 13 are all flattened. This is because their depth of 0 is less than 1. However [9, 10, 11] remains unflattened because its depth is 1.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input</strong>
arr = [[1, 2, 3], [4, 5, 6], [7, 8, [9, 10, 11], 12], [13, 14, 15]]
n = 2
<strong>Output</strong>
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]

<strong>Explanation</strong>
The maximum depth of any subarray is 1. Thus, all of them are flattened.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= count of numbers in arr &lt;=&nbsp;10<sup>5</sup></code></li>
	<li><code>0 &lt;= count of subarrays in arr &lt;=&nbsp;10<sup>5</sup></code></li>
	<li><code>maxDepth &lt;= 1000</code></li>
	<li><code>-1000 &lt;= each number &lt;= 1000</code></li>
	<li><code><font face="monospace">0 &lt;= n &lt;= 1000</font></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **TypeScript**

```ts
type MultiDimensionalArray = (number | MultiDimensionalArray)[];

var flat = function (
    arr: MultiDimensionalArray,
    n: number,
): MultiDimensionalArray {
    if (n <= 0) {
        return arr;
    }
    const ans: MultiDimensionalArray = [];
    for (const x of arr) {
        if (Array.isArray(x)) {
            ans.push(...flat(x, n - 1));
        } else {
            ans.push(x);
        }
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
