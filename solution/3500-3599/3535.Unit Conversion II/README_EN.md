---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3535.Unit%20Conversion%20II/README_EN.md
---

<!-- problem:start -->

# [3535. Unit Conversion II 🔒](https://leetcode.com/problems/unit-conversion-ii)

[中文文档](/solution/3500-3599/3535.Unit%20Conversion%20II/README.md)

## Description

<!-- description:start -->

<p>There are <code>n</code> types of units indexed from <code>0</code> to <code>n - 1</code>.</p>

<p>You are given a 2D integer array <code>conversions</code> of length <code>n - 1</code>, where <code>conversions[i] = [sourceUnit<sub>i</sub>, targetUnit<sub>i</sub>, conversionFactor<sub>i</sub>]</code>. This indicates that a single unit of type <code>sourceUnit<sub>i</sub></code> is equivalent to <code>conversionFactor<sub>i</sub></code> units of type <code>targetUnit<sub>i</sub></code>.</p>

<p>You are also given a 2D integer array <code>queries</code> of length <code>q</code>, where <code>queries[i] = [unitA<sub>i</sub>, unitB<sub>i</sub>]</code>.</p>

<p>Return an array <code face="monospace">answer</code> of length <code>q</code> where <code>answer[i]</code> is the number of units of type <code>unitB<sub>i</sub></code> equivalent to 1 unit of type <code>unitA<sub>i</sub></code>, and can be represented as <code>p/q</code> where <code>p</code> and <code>q</code> are coprime. Return each <code>answer[i]</code> as <code>pq<sup>-1</sup></code> <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>, where <code>q<sup>-1</sup></code> represents the multiplicative inverse of <code>q</code> modulo <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">conversions = [[0,1,2],[0,2,6]], queries = [[1,2],[1,0]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[3,500000004]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>In the first query, we can convert unit 1 into 3 units of type 2 using the inverse of <code>conversions[0]</code>, then <code>conversions[1]</code>.</li>
	<li>In the second query, we can convert unit 1 into 1/2 units of type 0 using the inverse of <code>conversions[0]</code>. We return 500000004 since it is the multiplicative inverse of 2.</li>
</ul>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3535.Unit%20Conversion%20II/images/example1.png" style="width: 500px; height: 500px;" /></div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">conversions = [[0,1,2],[0,2,6],[0,3,8],[2,4,2],[2,5,4],[3,6,3]], queries = [[1,2],[0,4],[6,5],[4,6],[6,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[3,12,1,2,83333334]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>In the first query, we can convert unit 1 into 3 units of type 2 using the inverse of <code>conversions[0]</code>, then <code>conversions[1]</code>.</li>
	<li>In the second query, we can convert unit 0 into 12 units of type 4 using <code>conversions[1]</code>, then <code>conversions[3]</code>.</li>
	<li>In the third query, we can convert unit 6 into 1 unit of type 5 using the inverse of <code>conversions[5]</code>, the inverse of <code>conversions[2]</code>, <code>conversions[1]</code>, then <code>conversions[4]</code>.</li>
	<li>In the fourth query, we can convert unit 4 into 2 units of type 6 using the inverse of <code>conversions[3]</code>, the inverse of <code>conversions[1]</code>, <code>conversions[2]</code>, then <code>conversions[5]</code>.</li>
	<li>In the fifth query, we can convert unit 6 into 1/12 units of type 1 using the inverse of <code>conversions[5]</code>, the inverse of <code>conversions[2]</code>, then <code>conversions[0]</code>. We return 83333334 since it is the multiplicative inverse of 12.</li>
</ul>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3535.Unit%20Conversion%20II/images/example2.png" style="width: 504px; height: 493px;" /></div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>conversions.length == n - 1</code></li>
	<li><code>0 &lt;= sourceUnit<sub>i</sub>, targetUnit<sub>i</sub> &lt; n</code></li>
	<li><code>1 &lt;= conversionFactor<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= q &lt;= 10<sup>5</sup></code></li>
	<li><code>queries.length == q</code></li>
	<li><code>0 &lt;= unitA<sub>i</sub>, unitB<sub>i</sub> &lt; n</code></li>
	<li>It is guaranteed that unit 0 can be <strong>uniquely</strong> converted into any other unit through a combination of forward or backward conversions.</li>
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
