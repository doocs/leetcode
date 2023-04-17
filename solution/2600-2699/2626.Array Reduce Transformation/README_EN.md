# [2626. Array Reduce Transformation](https://leetcode.com/problems/array-reduce-transformation)

[中文文档](/solution/2600-2699/2626.Array%20Reduce%20Transformation/README.md)

## Description

<p>Given an integer array&nbsp;<code>nums</code>, a reducer function&nbsp;<code>fn</code>, and an intial value&nbsp;<code>init</code>, return a&nbsp;<strong>reduced</strong>&nbsp;array.</p>

<p>A&nbsp;<strong>reduced</strong>&nbsp;array is created by applying the following operation:&nbsp;<code>val = fn(init, nums[0])</code>, <code>val&nbsp;= fn(val, nums[1])</code>,&nbsp;<code>val&nbsp;= fn(val, arr[2])</code>,&nbsp;<code>...</code>&nbsp;until every element in the array has been processed. The final value of&nbsp;<code>val</code>&nbsp;is returned.</p>

<p>If the length of the array is 0, it should return&nbsp;<code>init</code>.</p>

<p>Please solve it without using the built-in <code>Array.reduce</code> method.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
nums = [1,2,3,4]
fn = function sum(accum, curr) { return accum + curr; }
init = 0
<strong>Output:</strong> 10
<strong>Explanation:</strong>
initially, the value is init=0.
(0) + nums[0] = 1
(1) + nums[1] = 3
(3) + nums[2] = 6
(6) + nums[3] = 10
The final answer is 10.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> 
nums = [1,2,3,4]
fn = function sum(accum, curr) { return accum + curr * curr; }
init = 100
<strong>Output:</strong> 130
<strong>Explanation:</strong>
initially, the value is init=100.
(100) + nums[0]^2 = 101
(101) + nums[1]^2 = 105
(105) + nums[2]^2 = 114
(114) + nums[3]^2 = 130
The final answer is 130.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> 
nums = []
fn = function sum(accum, curr) { return 0; }
init = 25
<strong>Output:</strong> 25
<strong>Explanation:</strong> For empty arrays, the answer is always init.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>0 &lt;= init &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **TypeScript**

```ts
type Fn = (accum: number, curr: number) => number;

function reduce(nums: number[], fn: Fn, init: number): number {
    let acc: number = init;
    for (const x of nums) {
        acc = fn(acc, x);
    }
    return acc;
}
```

### **...**

```

```

<!-- tabs:end -->
