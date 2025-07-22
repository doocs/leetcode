---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3526.Range%20XOR%20Queries%20with%20Subarray%20Reversals/README_EN.md
tags:
    - Tree
    - Array
    - Binary Tree
---

<!-- problem:start -->

# [3526. Range XOR Queries with Subarray Reversals 🔒](https://leetcode.com/problems/range-xor-queries-with-subarray-reversals)

[中文文档](/solution/3500-3599/3526.Range%20XOR%20Queries%20with%20Subarray%20Reversals/README.md)

## Description

<!-- description:start -->

<p data-end="207" data-start="54">You are given an integer array <code data-end="91" data-start="85">nums</code> of length <code data-end="105" data-start="102">n</code> and a 2D integer array <code data-end="138" data-start="129">queries</code> of length <code data-end="152" data-start="149">q</code>, where each query is one of the following three types:</p>

<ol data-end="563" data-start="209">
	<li data-end="288" data-start="209">
	<p data-end="288" data-start="212"><strong data-end="222" data-start="212">Update</strong>: <code data-end="256" data-start="224">queries[i] = [1, index, value]</code><br data-end="259" data-start="256" />
	Set <code data-end="287" data-start="266">nums[index] = value</code>.</p>
	</li>
	<li data-end="450" data-start="290">
	<p data-end="450" data-start="293"><strong data-end="312" data-start="293">Range XOR Query</strong>: <code data-end="345" data-start="314">queries[i] = [2, left, right]</code><br data-end="348" data-start="345" />
	Compute the bitwise XOR of all elements in the <span data-keyword="subarray">subarray</span> <code data-end="425" data-start="407">nums[left...right]</code>, and record this result.</p>
	</li>
	<li data-end="563" data-start="452">
	<p data-end="563" data-start="455"><strong data-end="475" data-start="455">Reverse Subarray</strong>: <code data-end="508" data-start="477">queries[i] = [3, left, right]</code><br data-end="511" data-start="508" />
	Reverse the <span data-keyword="subarray">subarray</span> <code data-end="553" data-start="535">nums[left...right]</code> in place.</p>
	</li>
</ol>

<p data-end="658" data-start="565">Return <em data-end="622" data-start="572">an array of the results of all range XOR queries</em> in the order they were encountered.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4,5], queries = [[2,1,3],[1,2,10],[3,0,4],[2,0,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[5,8]</span></p>

<p><strong>Explanation:</strong></p>

<ul data-end="1371" data-start="1014">
	<li data-end="1098" data-start="1014">
	<p data-end="1098" data-start="1016"><strong data-end="1028" data-start="1016">Query</strong><strong data-end="1028" data-start="1016"> 1</strong><strong data-end="1028" data-start="1016">:</strong> <code data-end="1040" data-start="1029">[2, 1, 3]</code> &ndash; Compute XOR of subarray <code data-end="1078" data-start="1067">[2, 3, 4]</code> resulting in 5.</p>
	</li>
	<li data-end="1198" data-start="1099">
	<p data-end="1198" data-start="1101"><strong data-end="1113" data-start="1101">Query 2:</strong> <code data-end="1126" data-start="1114">[1, 2, 10]</code> &ndash; Update <code data-end="1145" data-start="1136">nums[2]</code> to 10, updating the array to <code data-end="1197" data-start="1179">[1, 2, 10, 4, 5]</code>.</p>
	</li>
	<li data-end="1279" data-start="1199">
	<p data-end="1279" data-start="1201"><strong data-end="1213" data-start="1201">Query 3:</strong> <code data-end="1225" data-start="1214">[3, 0, 4]</code> &ndash; Reverse the entire array to get <code data-end="1278" data-start="1260">[5, 4, 10, 2, 1]</code>.</p>
	</li>
	<li data-end="1371" data-start="1280">
	<p data-end="1371" data-start="1282"><strong data-end="1294" data-start="1282">Query 4:</strong> <code data-end="1306" data-start="1295">[2, 0, 4]</code> &ndash; Compute XOR of subarray <code data-end="1351" data-start="1333">[5, 4, 10, 2, 1]</code> resulting in 8.</p>
	</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [7,8,9], queries = [[1,0,3],[2,0,2],[3,1,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[2]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li data-end="1621" data-start="1531">
	<p data-end="1621" data-start="1533"><strong data-end="1545" data-start="1533">Query 1:</strong> <code data-end="1557" data-start="1546">[1, 0, 3]</code> &ndash; Update <code data-end="1576" data-start="1567">nums[0]</code> to 3, updating the array to <code data-end="1620" data-start="1609">[3, 8, 9]</code>.</p>
	</li>
	<li data-end="1706" data-start="1622">
	<p data-end="1706" data-start="1624"><strong data-end="1636" data-start="1624">Query 2:</strong> <code data-end="1648" data-start="1637">[2, 0, 2]</code> &ndash; Compute XOR of subarray <code data-end="1686" data-start="1675">[3, 8, 9]</code> resulting in 2.</p>
	</li>
	<li data-end="1827" data-start="1707">
	<p data-end="1827" data-start="1709"><strong data-end="1721" data-start="1709">Query 3:</strong> <code data-end="1733" data-start="1722">[3, 1, 2]</code> &ndash; Reverse the subarray <code data-end="1765" data-start="1757">[8, 9]</code> to get <code data-end="1781" data-start="1773">[9, 8]</code>.</p>
	</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li data-end="173" data-start="92"><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li data-end="257" data-start="176"><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li data-end="341" data-start="260"><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li data-end="425" data-start="344"><code>queries[i].length == 3​</code></li>
	<li data-end="513" data-start="428"><code>queries[i][0] &isin; {1, 2, 3}​</code></li>
	<li data-end="601" data-start="516">If <code>queries[i][0] == 1</code>:<code>​</code>
	<ul>
		<li data-end="691" data-start="606"><code>0 &lt;= index &lt; nums.length​</code></li>
		<li data-end="781" data-start="696"><code>0 &lt;= value &lt;= 10<sup>9</sup></code></li>
	</ul>
	</li>
	<li>If <code>queries[i][0] == 2</code> or <code>queries[i][0] == 3</code>:<code>​</code>
	<ul>
		<li data-end="959" data-start="874"><code>0 &lt;= left &lt;= right &lt; nums.length​</code></li>
	</ul>
	</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python

```

#### Java

```java

```

#### C++

```cpp

```

#### Go

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
