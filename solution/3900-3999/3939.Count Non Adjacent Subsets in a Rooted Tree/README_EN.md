---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3939.Count%20Non%20Adjacent%20Subsets%20in%20a%20Rooted%20Tree/README_EN.md
rating: 2354
source: Biweekly Contest 183 Q4
---

<!-- problem:start -->

# [3939. Count Non Adjacent Subsets in a Rooted Tree](https://leetcode.com/problems/count-non-adjacent-subsets-in-a-rooted-tree)

[中文文档](/solution/3900-3999/3939.Count%20Non%20Adjacent%20Subsets%20in%20a%20Rooted%20Tree/README.md)

## Description

<!-- description:start -->

<p data-end="186" data-start="43">You are given a rooted tree with <code data-end="79" data-start="76">n</code> nodes labeled from 0 to <code data-end="113" data-start="106">n - 1</code>, represented by an integer array <code data-end="164" data-start="156">parent</code> of length <code data-end="178" data-start="175">n</code>, where:</p>

<ul>
	<li data-end="227" data-start="190"><code data-end="206" data-start="190">parent[0] = -1</code> (node 0 is the root).</li>
	<li data-end="311" data-start="230">For each <code data-end="250" data-start="239">1 &lt;= i &lt; n</code>, <code data-end="263" data-start="252">parent[i]</code> is the parent of node <code data-end="289" data-start="286">i</code> (<code data-end="310" data-start="291">0 &lt;= parent[i] &lt; i</code>).</li>
</ul>

<p data-end="439" data-start="313">You are also given an integer array <font face="monospace">nums</font> of length <code data-end="377" data-start="374">n</code>, where <code><font face="monospace">nums[i]</font></code> is the value of node <code data-end="418" data-start="415">i</code>, and an integer <code data-end="438" data-start="435">k</code>.</p>

<p data-end="488" data-start="441">A non-empty subset of nodes is called <strong>valid</strong> if:</p>

<ul>
	<li data-end="555" data-start="491">The <strong>sum</strong> of the values of the selected nodes is <strong>divisible</strong> by <code data-end="554" data-start="551">k</code>.</li>
	<li data-end="669" data-start="558">No <strong>two</strong> selected nodes are <strong>adjacent</strong> in the tree (no node and its direct parent are both included in the subset).</li>
</ul>

<p data-end="721" data-start="671">Return the number of valid subsets modulo <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">parent = [-1,0,1], nums = [1,2,3], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3900-3999/3939.Count%20Non%20Adjacent%20Subsets%20in%20a%20Rooted%20Tree/images/image1.png" style="width: 230px; height: 75px;" />​​​​​​​</strong></p>

<p>The only valid subset is <code>{2}</code>. It contains node 2 with value 3, which is divisible by 3.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">parent = [-1,0,0,0], nums = [2,1,2,1], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3900-3999/3939.Count%20Non%20Adjacent%20Subsets%20in%20a%20Rooted%20Tree/images/image2.png" style="width: 250px; height: 180px;" />​​​​​​​</strong>​​​​​​​</p>

<p>The valid subsets are:</p>

<ul>
	<li><code>{1, 2}</code>: Nodes 1 and 2 are both children of node 0 and not directly connected to each other. Their values sum to <code>1 + 2 = 3</code>, which is divisible by 3.</li>
	<li><code>{2, 3}</code>: Nodes 2 and 3 are also non-adjacent. Their values sum to <code>2 + 1 = 3</code>, which is divisible by 3.</li>
</ul>

<p>No other subset satisfies both conditions. Therefore, the answer is 2.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li data-end="57" data-start="20"><code data-end="55" data-start="20">n == parent.length == nums.length</code></li>
	<li data-end="78" data-start="60"><code data-end="76" data-start="60">1 &lt;= n &lt;= 1000</code></li>
	<li data-end="100" data-start="81"><code data-end="98" data-start="81">parent[0] == -1</code></li>
	<li data-end="147" data-start="103">For all <code data-end="123" data-start="111">1 &lt;= i &lt; n</code>:
	<ul>
		<li data-end="147" data-start="103"><code data-end="145" data-start="125">0 &lt;= parent[i] &lt; i</code></li>
	</ul>
	</li>
	<li data-end="174" data-start="150"><code data-end="172" data-start="150">1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li data-end="195" data-start="177"><code data-end="193" data-start="177">1 &lt;= k &lt;= 100</code>​​​​​​​​​​​​​​<code data-end="193" data-start="177">​​​​​​​</code></li>
	<li data-end="195" data-start="177"><code data-end="206" data-start="198">parent</code> describes a valid rooted tree.</li>
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
