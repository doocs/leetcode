---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3947.Maximum%20Number%20of%20Items%20From%20Sale%20II/README_EN.md
rating: 2215
source: Weekly Contest 504 Q3
tags:
    - Greedy
    - Array
    - Sorting
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [3947. Maximum Number of Items From Sale II](https://leetcode.com/problems/maximum-number-of-items-from-sale-ii)

[中文文档](/solution/3900-3999/3947.Maximum%20Number%20of%20Items%20From%20Sale%20II/README.md)

## Description

<!-- description:start -->

<p>You are given a 2D integer array <code>items</code>, where <code>items[i] = [factor<sub>i</sub>, price<sub>i</sub>]</code> represents the <code>i<sup>th</sup></code> item. You are also given an integer <code>budget</code>.</p>

<p>There are unlimited copies of each item available for purchase. You may buy any number of copies of any items such that the total cost of the purchased copies is at most <code>budget</code>.</p>

<p>After buying items, you may receive free copies according to the following rules:</p>

<ul>
	<li>Each purchased copy of item <code>i</code> can give you <strong>at most one</strong> free copy of another item <code>j</code>.</li>
	<li>The free item must satisfy <code>i != j</code> and <code>factor<sub>i</sub></code> divides <code>factor<sub>j</sub></code>.</li>
	<li>For each ordered pair <code>(i, j)</code>, you can receive a free copy of item <code>j</code> from purchases of item <code>i</code> <strong>at most once</strong>, regardless of how many copies of item <code>i</code> you buy.</li>
	<li>The same item <code>j</code> can be received multiple times for free if it is received from purchases of different item types.</li>
</ul>

<p>Return the <strong>maximum total number of item copies</strong> you can obtain, including both purchased copies and free copies, while spending at most <code>budget</code> on purchased items.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">items = [[1,6],[2,4],[3,5]], budget = 19</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>You can buy 2 copies of item 0 and 1 copy of item 1 for a total cost of <code>2 * 6 + 4 = 16</code>, which is not greater than <code>budget = 19</code>.</li>
	<li>One purchased copy of item 0 gives 1 free copy of item 1, because <code>factor<sub>0</sub> = 1</code> divides <code>factor<sub>1</sub> = 2</code>.</li>
	<li>The other purchased copy of item 0 gives 1 free copy of item 2, because <code>factor<sub>0</sub> = 1</code> divides <code>factor<sub>2</sub> = 3</code>.</li>
	<li>You leave with 3 purchased copies and 2 free copies, for a total of 5 item copies.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">items = [[2,8],[1,10],[6,6],[4,12],[5,20],[5,17]], budget = 35</span></p>

<p><strong>Output:</strong> <span class="example-io">7</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>You can buy 2 copies of item 0, 1 copy of item 1, and 1 copy of item 2 for a total cost of <code>2 * 8 + 10 + 6 = 32</code>, which is not greater than <code>budget = 35</code>.</li>
	<li>One purchased copy of item 0 gives 1 free copy of item 2, because <code>factor<sub>0</sub> = 2</code> divides <code>factor<sub>2</sub> = 6</code>.</li>
	<li>The other purchased copy of item 0 gives 1 free copy of item 3, because <code>factor<sub>0</sub> = 2</code> divides <code>factor<sub>3</sub> = 4</code>.</li>
	<li>The purchased copy of item 1 gives 1 free copy of item 2, because <code>factor<sub>1</sub> = 1</code> divides <code>factor<sub>2</sub> = 6</code>.</li>
	<li>Buying item 2 gives no free copy, because <code>factor<sub>2</sub> = 6</code> does not divide the factor of any other item.</li>
	<li>You leave with 4 purchased copies and 3 free copies, for a total of 7 item copies.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= items.length &lt;= 10<sup>5</sup></code></li>
	<li><code>items[i] = [factor<sub>i</sub>, price<sub>i</sub>]</code></li>
	<li><code>1 &lt;= factor<sub>i</sub> &lt;= items.length</code></li>
	<li><code>1 &lt;= price<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= budget &lt;= 10<sup>9</sup></code></li>
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
