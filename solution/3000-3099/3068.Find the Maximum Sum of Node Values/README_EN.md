---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3068.Find%20the%20Maximum%20Sum%20of%20Node%20Values/README_EN.md
rating: 2267
source: Biweekly Contest 125 Q4
tags:
    - Greedy
    - Bit Manipulation
    - Tree
    - Array
    - Dynamic Programming
    - Sorting
---

<!-- problem:start -->

# [3068. Find the Maximum Sum of Node Values](https://leetcode.com/problems/find-the-maximum-sum-of-node-values)

[中文文档](/solution/3000-3099/3068.Find%20the%20Maximum%20Sum%20of%20Node%20Values/README.md)

## Description

<p>There exists an <strong>undirected</strong> tree with <code>n</code> nodes numbered <code>0</code> to <code>n - 1</code>. You are given a <strong>0-indexed</strong> 2D integer array <code>edges</code> of length <code>n - 1</code>, where <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> indicates that there is an edge between nodes <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code> in the tree. You are also given a <strong>positive</strong> integer <code>k</code>, and a <strong>0-indexed</strong> array of <strong>non-negative</strong> integers <code>nums</code> of length <code>n</code>, where <code>nums[i]</code> represents the <strong>value</strong> of the node numbered <code>i</code>.</p>

<p>Alice wants the sum of values of tree nodes to be <strong>maximum</strong>, for which Alice can perform the following operation <strong>any</strong> number of times (<strong>including zero</strong>) on the tree:</p>

<ul>
	<li>Choose any edge <code>[u, v]</code> connecting the nodes <code>u</code> and <code>v</code>, and update their values as follows:

    <ul>
    	<li><code>nums[u] = nums[u] XOR k</code></li>
    	<li><code>nums[v] = nums[v] XOR k</code></li>
    </ul>
    </li>

</ul>

<p>Return <em>the <strong>maximum</strong> possible <strong>sum</strong> of the <strong>values</strong> Alice can achieve by performing the operation <strong>any</strong> number of times</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3068.Find%20the%20Maximum%20Sum%20of%20Node%20Values/images/screenshot-2023-11-10-012513.png" style="width: 300px; height: 277px;padding: 10px; background: #fff; border-radius: .5rem;" />
<pre>
<strong>Input:</strong> nums = [1,2,1], k = 3, edges = [[0,1],[0,2]]
<strong>Output:</strong> 6
<strong>Explanation:</strong> Alice can achieve the maximum sum of 6 using a single operation:
- Choose the edge [0,2]. nums[0] and nums[2] become: 1 XOR 3 = 2, and the array nums becomes: [1,2,1] -&gt; [2,2,2].
The total sum of values is 2 + 2 + 2 = 6.
It can be shown that 6 is the maximum achievable sum of values.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3068.Find%20the%20Maximum%20Sum%20of%20Node%20Values/images/screenshot-2024-01-09-220017.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 300px; height: 239px;" />
<pre>
<strong>Input:</strong> nums = [2,3], k = 7, edges = [[0,1]]
<strong>Output:</strong> 9
<strong>Explanation:</strong> Alice can achieve the maximum sum of 9 using a single operation:
- Choose the edge [0,1]. nums[0] becomes: 2 XOR 7 = 5 and nums[1] become: 3 XOR 7 = 4, and the array nums becomes: [2,3] -&gt; [5,4].
The total sum of values is 5 + 4 = 9.
It can be shown that 9 is the maximum achievable sum of values.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3068.Find%20the%20Maximum%20Sum%20of%20Node%20Values/images/screenshot-2023-11-10-012641.png" style="width: 600px; height: 233px;padding: 10px; background: #fff; border-radius: .5rem;" />
<pre>
<strong>Input:</strong> nums = [7,7,7,7,7,7], k = 3, edges = [[0,1],[0,2],[0,3],[0,4],[0,5]]
<strong>Output:</strong> 42
<strong>Explanation:</strong> The maximum achievable sum is 42 which can be achieved by Alice performing no operations.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n == nums.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= edges[i][0], edges[i][1] &lt;= n - 1</code></li>
	<li>The input is generated such that <code>edges</code> represent&nbsp;a valid tree.</li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

```python

```

```java

```

```cpp

```

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
