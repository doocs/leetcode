# [1992. Find All Groups of Farmland](https://leetcode.com/problems/find-all-groups-of-farmland)

[中文文档](/solution/1900-1999/1992.Find%20All%20Groups%20of%20Farmland/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> <code>m x n</code> binary matrix <code>land</code> where a <code>0</code> represents a hectare of forested land and a <code>1</code> represents a hectare of farmland.</p>

<p>To keep the land organized, there are designated rectangular areas of hectares that consist <strong>entirely</strong> of farmland. These rectangular areas are called <strong>groups</strong>. No two groups are adjacent, meaning farmland in one group is <strong>not</strong> four-directionally adjacent to another farmland in a different group.</p>

<p><code>land</code> can be represented by a coordinate system where the top left corner of <code>land</code> is <code>(0, 0)</code> and the bottom right corner of <code>land</code> is <code>(m-1, n-1)</code>. Find the coordinates of the top left and bottom right corner of each <strong>group</strong> of farmland. A <strong>group</strong> of farmland with a top left corner at <code>(r<sub>1</sub>, c<sub>1</sub>)</code> and a bottom right corner at <code>(r<sub>2</sub>, c<sub>2</sub>)</code> is represented by the 4-length array <code>[r<sub>1</sub>, c<sub>1</sub>, r<sub>2</sub>, c<sub>2</sub>].</code></p>

<p>Return <em>a 2D array containing the 4-length arrays described above for each <strong>group</strong> of farmland in </em><code>land</code><em>. If there are no groups of farmland, return an empty array. You may return the answer in <strong>any order</strong></em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1992.Find%20All%20Groups%20of%20Farmland/images/screenshot-2021-07-27-at-12-23-15-copy-of-diagram-drawio-diagrams-net.png" style="width: 300px; height: 300px;" />
<pre>
<strong>Input:</strong> land = [[1,0,0],[0,1,1],[0,1,1]]
<strong>Output:</strong> [[0,0,0,0],[1,1,2,2]]
<strong>Explanation:</strong>
The first group has a top left corner at land[0][0] and a bottom right corner at land[0][0].
The second group has a top left corner at land[1][1] and a bottom right corner at land[2][2].
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1992.Find%20All%20Groups%20of%20Farmland/images/screenshot-2021-07-27-at-12-30-26-copy-of-diagram-drawio-diagrams-net.png" style="width: 200px; height: 200px;" />
<pre>
<strong>Input:</strong> land = [[1,1],[1,1]]
<strong>Output:</strong> [[0,0,1,1]]
<strong>Explanation:</strong>
The first group has a top left corner at land[0][0] and a bottom right corner at land[1][1].
</pre>

<p><strong>Example 3:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1992.Find%20All%20Groups%20of%20Farmland/images/screenshot-2021-07-27-at-12-32-24-copy-of-diagram-drawio-diagrams-net.png" style="width: 100px; height: 100px;" />
<pre>
<strong>Input:</strong> land = [[0]]
<strong>Output:</strong> []
<strong>Explanation:</strong>
There are no groups of farmland.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == land.length</code></li>
	<li><code>n == land[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 300</code></li>
	<li><code>land</code> consists of only <code>0</code>&#39;s and <code>1</code>&#39;s.</li>
	<li>Groups of farmland are <strong>rectangular</strong> in shape.</li>
</ul>


## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **...**

```

```

<!-- tabs:end -->
