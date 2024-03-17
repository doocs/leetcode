# [3080. Mark Elements on Array by Performing Queries](https://leetcode.com/problems/mark-elements-on-array-by-performing-queries)

[中文文档](/solution/3000-3099/3080.Mark%20Elements%20on%20Array%20by%20Performing%20Queries/README.md)

<!-- tags: -->

## Description

<p>You are given a <strong>0-indexed</strong> array <code>nums</code> of size <code>n</code> consisting of positive integers.</p>

<p>You are also given a 2D array <code>queries</code> of size <code>m</code> where <code>queries[i] = [index<sub>i</sub>, k<sub>i</sub>]</code>.</p>

<p>Initially all elements of the array are <strong>unmarked</strong>.</p>

<p>You need to apply <code>m</code> queries on the array in order, where on the <code>i<sup>th</sup></code> query you do the following:</p>

<ul>
	<li>Mark the element at index <code>index<sub>i</sub></code> if it is not already marked.</li>
	<li>Then mark <code>k<sub>i</sub></code> unmarked elements in the array with the <strong>smallest</strong> values. If multiple such elements exist, mark the ones with the smallest indices. And if less than <code>k<sub>i</sub></code> unmarked elements exist, then mark all of them.</li>
</ul>

<p>Return <em>an array answer of size </em><code>m</code><em> where </em><code>answer[i]</code><em> is the <strong>sum</strong> of unmarked elements in the array after the </em><code>i<sup>th</sup></code><em> query</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">nums = [1,2,2,1,2,3,1], queries = [[1,2],[3,3],[4,2]]</span></p>

<p><strong>Output: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">[8,3,0]</span></p>

<p><strong>Explanation:</strong></p>

<p>We do the following queries on the array:</p>

<ul>
	<li>Mark the element at index <code>1</code>, and <code>2</code> of the smallest unmarked elements with the smallest indices if they exist, the marked elements now are <code>nums = [<strong><u>1</u></strong>,<u><strong>2</strong></u>,2,<u><strong>1</strong></u>,2,3,1]</code>. The sum of unmarked elements is <code>2 + 2 + 3 + 1 = 8</code>.</li>
	<li>Mark the element at index <code>3</code>, since it is already marked we skip it. Then we mark <code>3</code> of the smallest unmarked elements with the smallest indices, the marked elements now are <code>nums = [<strong><u>1</u></strong>,<u><strong>2</strong></u>,<u><strong>2</strong></u>,<u><strong>1</strong></u>,<u><strong>2</strong></u>,3,<strong><u>1</u></strong>]</code>. The sum of unmarked elements is <code>3</code>.</li>
	<li>Mark the element at index <code>4</code>, since it is already marked we skip it. Then we mark <code>2</code> of the smallest unmarked elements with the smallest indices if they exist, the marked elements now are <code>nums = [<strong><u>1</u></strong>,<u><strong>2</strong></u>,<u><strong>2</strong></u>,<u><strong>1</strong></u>,<u><strong>2</strong></u>,<strong><u>3</u></strong>,<u><strong>1</strong></u>]</code>. The sum of unmarked elements is <code>0</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); font-size: .875rem; margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><strong>Input: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">nums = [1,4,2,3], queries = [[0,1]]</span></p>

<p><strong>Output: </strong><span class="example-io" style="font-family: Menlo,sans-serif; font-size: 0.85rem;">[7]</span></p>

<p><strong>Explanation: </strong> We do one query which is mark the element at index <code>0</code> and mark the smallest element among unmarked elements. The marked elements will be <code>nums = [<strong><u>1</u></strong>,4,<u><strong>2</strong></u>,3]</code>, and the sum of unmarked elements is <code>4 + 3 = 7</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>m == queries.length</code></li>
	<li><code>1 &lt;= m &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>0 &lt;= index<sub>i</sub>, k<sub>i</sub> &lt;= n - 1</code></li>
</ul>

## Solutions

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

<!-- end -->
