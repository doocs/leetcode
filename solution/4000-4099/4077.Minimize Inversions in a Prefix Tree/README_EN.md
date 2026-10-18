---
comments: true
difficulty: Hard
rating: 2485
source: Biweekly Contest 193 Q4
---

<!-- problem:start -->

# [4077. Minimize Inversions in a Prefix Tree](https://leetcode.com/problems/minimize-inversions-in-a-prefix-tree)

[中文文档](/solution/4000-4099/4077.Minimize%20Inversions%20in%20a%20Prefix%20Tree/README.md)

## Description

<!-- description:start -->

<p>You are given an array <code>words</code> of <code>n</code> <strong>unique</strong> strings containing only lowercase English letters from <code>&#39;a&#39;</code> to <code>&#39;j&#39;</code>. You are also given an integer array <code>target</code> containing each index from 0 to <code>n - 1</code> exactly once.</p>

<p>Build a <strong>prefix tree</strong> from <code>words</code>:</p>

<ul>
	<li>The root represents the empty string <code>&quot;&quot;</code>.</li>
	<li>Each other node represents a different non-empty <span data-keyword="string-prefix">prefix</span> of a word. The parent of a node is the prefix obtained by removing its last character.</li>
	<li>The node representing the complete word <code>words[i]</code> is marked with index <code>i</code>. A marked node may also have children.</li>
</ul>

<p>You may choose the order of the children of <strong>each node independently</strong>. You cannot change which node is a child&#39;s parent.</p>

<p>Traverse the tree starting at the root. When visiting a node, first record its index if it is marked. Then visit its children in the chosen order, completing the traversal of each child before moving to the next. This is a <strong>pre-order traversal</strong>.</p>

<p>The recorded indices form an array <code>traversal</code> of length <code>n</code>. Let <code>pos[i]</code> be the position of index <code>i</code> in <code>target</code>.</p>

<p>An <strong>inversion</strong> is a pair of positions <code>(a, b)</code> such that <code>0 &lt;= a &lt; b &lt; n</code> and <code>pos[traversal[a]] &gt; pos[traversal[b]]</code>.</p>

<p>Return the <strong>minimum possible number of inversions</strong> over all choices of child orderings.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4000-4099/4077.Minimize%20Inversions%20in%20a%20Prefix%20Tree/images/screenshot-2026-03-04-at-55332pm.png" style="width: 264px; height: 250px;" />​​​​​​​​​​​​​​​​​​​​​</p>

<p><strong>Input:</strong> <span class="example-io">words = [&quot;bad&quot;,&quot;bag&quot;,&quot;fig&quot;,&quot;fed&quot;], target = [3,1,0,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>Visit the root&#39;s <code>&quot;f&quot;</code> child before its <code>&quot;b&quot;</code> child. Within the <code>&quot;f&quot;</code> branch, visit <code>&quot;fed&quot;</code> before <code>&quot;fig&quot;</code>. Within the <code>&quot;b&quot;</code> branch, visit <code>&quot;bag&quot;</code> before <code>&quot;bad&quot;</code>.</p>

<p>This produces <code>traversal = [3,2,1,0]</code>:</p>

<table>
	<thead>
		<tr>
			<th>Traversal position</th>
			<th>Word index</th>
			<th>Word</th>
			<th>Position in target</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>0</td>
			<td>3</td>
			<td><code>&quot;fed&quot;</code></td>
			<td>0</td>
		</tr>
		<tr>
			<td>1</td>
			<td>2</td>
			<td><code>&quot;fig&quot;</code></td>
			<td>3</td>
		</tr>
		<tr>
			<td>2</td>
			<td>1</td>
			<td><code>&quot;bag&quot;</code></td>
			<td>1</td>
		</tr>
		<tr>
			<td>3</td>
			<td>0</td>
			<td><code>&quot;bad&quot;</code></td>
			<td>2</td>
		</tr>
	</tbody>
</table>

<p>The positions in <code>target</code> are <code>[0,3,1,2]</code>. The inversions are the pairs of <strong>traversal positions</strong> <code>(1,2)</code> and <code>(1,3)</code>, since <code>3 &gt; 1</code> and <code>3 &gt; 2</code>.</p>

<p>No child ordering produces fewer than 2 inversions.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">words = [&quot;ab&quot;,&quot;ac&quot;,&quot;ba&quot;,&quot;bc&quot;], target = [0,3,1,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>Visit the root&#39;s <code>&quot;a&quot;</code> child before its <code>&quot;b&quot;</code> child. Within these branches, visit <code>&quot;ab&quot;</code> before <code>&quot;ac&quot;</code>, and <code>&quot;bc&quot;</code> before <code>&quot;ba&quot;</code>.</p>

<p>This produces <code>traversal = [0,1,3,2]</code>:</p>

<table>
	<thead>
		<tr>
			<th>Traversal position</th>
			<th>Word index</th>
			<th>Word</th>
			<th>Position in target</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>0</td>
			<td>0</td>
			<td><code>&quot;ab&quot;</code></td>
			<td>0</td>
		</tr>
		<tr>
			<td>1</td>
			<td>1</td>
			<td><code>&quot;ac&quot;</code></td>
			<td>2</td>
		</tr>
		<tr>
			<td>2</td>
			<td>3</td>
			<td><code>&quot;bc&quot;</code></td>
			<td>1</td>
		</tr>
		<tr>
			<td>3</td>
			<td>2</td>
			<td><code>&quot;ba&quot;</code></td>
			<td>3</td>
		</tr>
	</tbody>
</table>

<p>The positions in <code>target</code> are <code>[0,2,1,3]</code>. The only inversion is the pair of <strong>traversal positions</strong> <code>(1,2)</code>, since <code>2 &gt; 1</code>.</p>

<p>No child ordering produces zero inversions.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == words.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= words[i].length &lt;= 20</code></li>
	<li><code>words[i]</code> contains only lowercase English letters from <code>&#39;a&#39;</code> to <code>&#39;j&#39;</code>​​​​​​​</li>
	<li>All <code>words[i]</code> are <strong>unique</strong></li>
	<li><code>0 &lt;= target[i] &lt;= n - 1</code></li>
	<li><code>target</code> is a permutation of integers from 0 to <code>n - 1</code></li>
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
