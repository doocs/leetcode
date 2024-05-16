---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2634.Filter%20Elements%20from%20Array/README_EN.md
---

<!-- problem:start -->

# [2634. Filter Elements from Array](https://leetcode.com/problems/filter-elements-from-array)

[中文文档](/solution/2600-2699/2634.Filter%20Elements%20from%20Array/README.md)

## Description

<!-- description:start -->

<p>Given an integer array <code>arr</code> and a filtering function <code>fn</code>, return a filtered array <code>filteredArr</code>.</p>

<p>The <code>fn</code> function takes one or two arguments:</p>

<ul>
	<li><code>arr[i]</code> - number&nbsp;from&nbsp;the <code>arr</code></li>
	<li><code>i</code>&nbsp;- index of <code>arr[i]</code></li>
</ul>

<p><code>filteredArr</code> should only contain the elements from the&nbsp;<code>arr</code> for which the expression <code>fn(arr[i], i)</code> evaluates to a <strong>truthy</strong> value. A&nbsp;<strong>truthy</strong>&nbsp;value is a value where&nbsp;<code>Boolean(value)</code>&nbsp;returns&nbsp;<code>true</code>.</p>

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
	<li><code>-10<sup>9</sup>&nbsp;&lt;= arr[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Traversal

We traverse the array $arr$ and for each element $arr[i]$, if $fn(arr[i], i)$ is true, we add it to the answer array. Finally, we return the answer array.

The time complexity is $O(n)$, where $n$ is the length of the array $arr$. Ignoring the space consumption of the answer, the space complexity is $O(1)$.

<!-- tabs:start -->

```ts
function filter(arr: number[], fn: (n: number, i: number) => any): number[] {
    const ans: number[] = [];
    for (let i = 0; i < arr.length; ++i) {
        if (fn(arr[i], i)) {
            ans.push(arr[i]);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
