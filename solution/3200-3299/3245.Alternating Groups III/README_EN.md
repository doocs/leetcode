---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3245.Alternating%20Groups%20III/README_EN.md
---

<!-- problem:start -->

# [3245. Alternating Groups III](https://leetcode.com/problems/alternating-groups-iii)

[中文文档](/solution/3200-3299/3245.Alternating%20Groups%20III/README.md)

## Description

<!-- description:start -->

<p>There are some red and blue tiles arranged circularly. You are given an array of integers <code>colors</code> and a 2D integers array <code>queries</code>.</p>

<p>The color of tile <code>i</code> is represented by <code>colors[i]</code>:</p>

<ul>
	<li><code>colors[i] == 0</code> means that tile <code>i</code> is <strong>red</strong>.</li>
	<li><code>colors[i] == 1</code> means that tile <code>i</code> is <strong>blue</strong>.</li>
</ul>

<p>An <strong>alternating</strong> group is a contiguous subset of tiles in the circle with <strong>alternating</strong> colors (each tile in the group except the first and last one has a different color from its <b>adjacent</b> tiles in the group).</p>

<p>You have to process queries of two types:</p>

<ul>
	<li><code>queries[i] = [1, size<sub>i</sub>]</code>, determine the count of <strong>alternating</strong> groups with size <code>size<sub>i</sub></code>.</li>
	<li><code>queries[i] = [2, index<sub>i</sub>, color<sub>i</sub>]</code>, change <code>colors[index<sub>i</sub>]</code> to <code>color<font face="monospace"><sub>i</sub></font></code>.</li>
</ul>

<p>Return an array <code>answer</code> containing the results of the queries of the first type <em>in order</em>.</p>

<p><strong>Note</strong> that since <code>colors</code> represents a <strong>circle</strong>, the <strong>first</strong> and the <strong>last</strong> tiles are considered to be next to each other.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">colors = [0,1,1,0,1], queries = [[2,1,0],[1,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[2]</span></p>

<p><strong>Explanation:</strong></p>

<p><strong class="example"><img alt="" data-darkreader-inline-bgcolor="" data-darkreader-inline-bgimage="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3245.Alternating%20Groups%20III/images/screenshot-from-2024-06-03-20-14-44.png" style="width: 150px; height: 150px; padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; --darkreader-inline-bgimage: initial; --darkreader-inline-bgcolor: #181a1b;" /></strong></p>

<p>First query:</p>

<p>Change <code>colors[1]</code> to 0.</p>

<p><img alt="" data-darkreader-inline-bgcolor="" data-darkreader-inline-bgimage="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3245.Alternating%20Groups%20III/images/screenshot-from-2024-06-03-20-20-25.png" style="width: 150px; height: 150px; padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; --darkreader-inline-bgimage: initial; --darkreader-inline-bgcolor: #181a1b;" /></p>

<p>Second query:</p>

<p>Count of the alternating groups with size 4:</p>

<p><img alt="" data-darkreader-inline-bgcolor="" data-darkreader-inline-bgimage="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3245.Alternating%20Groups%20III/images/screenshot-from-2024-06-03-20-25-02-2.png" style="width: 150px; height: 150px; padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; --darkreader-inline-bgimage: initial; --darkreader-inline-bgcolor: #181a1b;" /><img alt="" data-darkreader-inline-bgcolor="" data-darkreader-inline-bgimage="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3245.Alternating%20Groups%20III/images/screenshot-from-2024-06-03-20-24-12.png" style="width: 150px; height: 150px; padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; --darkreader-inline-bgimage: initial; --darkreader-inline-bgcolor: #181a1b;" /></p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">colors = [0,0,1,0,1,1], queries = [[1,3],[2,3,0],[1,5]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,0]</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" data-darkreader-inline-bgcolor="" data-darkreader-inline-bgimage="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3245.Alternating%20Groups%20III/images/screenshot-from-2024-06-03-20-35-50.png" style="width: 150px; height: 150px; padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; --darkreader-inline-bgimage: initial; --darkreader-inline-bgcolor: #181a1b;" /></p>

<p>First query:</p>

<p>Count of the alternating groups with size 3:</p>

<p><img alt="" data-darkreader-inline-bgcolor="" data-darkreader-inline-bgimage="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3245.Alternating%20Groups%20III/images/screenshot-from-2024-06-03-20-37-13.png" style="width: 150px; height: 150px; padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; --darkreader-inline-bgimage: initial; --darkreader-inline-bgcolor: #181a1b;" /><img alt="" data-darkreader-inline-bgcolor="" data-darkreader-inline-bgimage="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3245.Alternating%20Groups%20III/images/screenshot-from-2024-06-03-20-36-40.png" style="width: 150px; height: 150px; padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; --darkreader-inline-bgimage: initial; --darkreader-inline-bgcolor: #181a1b;" /></p>

<p>Second query: <code>colors</code> will not change.</p>

<p>Third query: There is no alternating group with size 5.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>4 &lt;= colors.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= colors[i] &lt;= 1</code></li>
	<li><code>1 &lt;= queries.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>queries[i][0] == 1</code> or <code>queries[i][0] == 2</code></li>
	<li>For all <code>i</code> that:
	<ul>
		<li><code>queries[i][0] == 1</code>: <code>queries[i].length == 2</code>, <code>3 &lt;= queries[i][1] &lt;= colors.length - 1</code></li>
		<li><code>queries[i][0] == 2</code>: <code>queries[i].length == 3</code>, <code>0 &lt;= queries[i][1] &lt;= colors.length - 1</code>, <code>0 &lt;= queries[i][2] &lt;= 1</code></li>
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
