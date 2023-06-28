# [2635. Apply Transform Over Each Element in Array](https://leetcode.com/problems/apply-transform-over-each-element-in-array)

[中文文档](/solution/2600-2699/2635.Apply%20Transform%20Over%20Each%20Element%20in%20Array/README.md)

## Description

<p>Given an integer array&nbsp;<code>arr</code>&nbsp;and a mapping function&nbsp;<code>fn</code>, return&nbsp;a new array with a transformation applied to each element.</p>

<p>The returned array should be created such that&nbsp;<code>returnedArray[i] = fn(arr[i], i)</code>.</p>

<p>Please solve it without the built-in <code>Array.map</code> method.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2,3], fn = function plusone(n) { return n + 1; }
<strong>Output:</strong> [2,3,4]
<strong>Explanation:</strong>
const newArray = map(arr, plusone); // [2,3,4]
The function increases each value in the array by one. 
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2,3], fn = function plusI(n, i) { return n + i; }
<strong>Output:</strong> [1,3,5]
<strong>Explanation:</strong> The function increases each value by the index it resides in.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [10,20,30], fn = function constant() { return 42; }
<strong>Output:</strong> [42,42,42]
<strong>Explanation:</strong> The function always returns 42.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= arr.length &lt;= 1000</code></li>
	<li><code><font face="monospace">-10<sup>9</sup>&nbsp;&lt;= arr[i] &lt;= 10<sup>9</sup></font></code></li>
	<li><font face="monospace"><code>fn returns a number</code></font></li>
</ul>

## Solutions

**Solution 1: traversal**

We traverse the array $arr$, for each element $arr[i]$, replace it with $fn(arr[i], i)$. Finally, return the array $arr$.

The time complexity is $O(n)$, where $n$ is the length of the array $arr$. The space complexity is $O(1)$.

<!-- tabs:start -->

### **TypeScript**

```ts
function map(arr: number[], fn: (n: number, i: number) => number): number[] {
    for (let i = 0; i < arr.length; ++i) {
        arr[i] = fn(arr[i], i);
    }
    return arr;
}
```

### **...**

```

```

<!-- tabs:end -->
