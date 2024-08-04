---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3243.Shortest%20Distance%20After%20Road%20Addition%20Queries%20I/README_EN.md
---

<!-- problem:start -->

# [3243. Shortest Distance After Road Addition Queries I](https://leetcode.com/problems/shortest-distance-after-road-addition-queries-i)

[中文文档](/solution/3200-3299/3243.Shortest%20Distance%20After%20Road%20Addition%20Queries%20I/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code> and a 2D integer array <code>queries</code>.</p>

<p>There are <code>n</code> cities numbered from <code>0</code> to <code>n - 1</code>. Initially, there is a <strong>unidirectional</strong> road from city <code>i</code> to city <code>i + 1</code> for all <code>0 &lt;= i &lt; n - 1</code>.</p>

<p><code>queries[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> represents the addition of a new <strong>unidirectional</strong> road from city <code>u<sub>i</sub></code> to city <code>v<sub>i</sub></code>. After each query, you need to find the <strong>length</strong> of the <strong>shortest path</strong> from city <code>0</code> to city <code>n - 1</code>.</p>

<p>Return an array <code>answer</code> where for each <code>i</code> in the range <code>[0, queries.length - 1]</code>, <code>answer[i]</code> is the <em>length of the shortest path</em> from city <code>0</code> to city <code>n - 1</code> after processing the <strong>first </strong><code>i + 1</code> queries.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5, queries = [[2,4],[0,2],[0,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[3,2,1]</span></p>

<p><strong>Explanation: </strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3243.Shortest%20Distance%20After%20Road%20Addition%20Queries%20I/images/image8.jpg" style="width: 350px; height: 60px;" /></p>

<p>After the addition of the road from 2 to 4, the length of the shortest path from 0 to 4 is 3.</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3243.Shortest%20Distance%20After%20Road%20Addition%20Queries%20I/images/image9.jpg" style="width: 350px; height: 60px;" /></p>

<p>After the addition of the road from 0 to 2, the length of the shortest path from 0 to 4 is 2.</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3243.Shortest%20Distance%20After%20Road%20Addition%20Queries%20I/images/image10.jpg" style="width: 350px; height: 96px;" /></p>

<p>After the addition of the road from 0 to 4, the length of the shortest path from 0 to 4 is 1.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, queries = [[0,3],[0,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,1]</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3243.Shortest%20Distance%20After%20Road%20Addition%20Queries%20I/images/image11.jpg" style="width: 300px; height: 70px;" /></p>

<p>After the addition of the road from 0 to 3, the length of the shortest path from 0 to 3 is 1.</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3243.Shortest%20Distance%20After%20Road%20Addition%20Queries%20I/images/image12.jpg" style="width: 300px; height: 70px;" /></p>

<p>After the addition of the road from 0 to 2, the length of the shortest path remains 1.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= n &lt;= 500</code></li>
	<li><code>1 &lt;= queries.length &lt;= 500</code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>0 &lt;= queries[i][0] &lt; queries[i][1] &lt; n</code></li>
	<li><code>1 &lt; queries[i][1] - queries[i][0]</code></li>
	<li>There are no repeated roads among the queries.</li>
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
