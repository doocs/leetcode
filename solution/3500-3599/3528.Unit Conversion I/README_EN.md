---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3528.Unit%20Conversion%20I/README_EN.md
tags:
    - Depth-First Search
    - Breadth-First Search
    - Graph
---

<!-- problem:start -->

# [3528. Unit Conversion I](https://leetcode.com/problems/unit-conversion-i)

[中文文档](/solution/3500-3599/3528.Unit%20Conversion%20I/README.md)

## Description

<!-- description:start -->

<p>There are <code>n</code> types of units indexed from <code>0</code> to <code>n - 1</code>. You are given a 2D integer array <code>conversions</code> of length <code>n - 1</code>, where <code>conversions[i] = [sourceUnit<sub>i</sub>, targetUnit<sub>i</sub>, conversionFactor<sub>i</sub>]</code>. This indicates that a single unit of type <code>sourceUnit<sub>i</sub></code> is equivalent to <code>conversionFactor<sub>i</sub></code> units of type <code>targetUnit<sub>i</sub></code>.</p>

<p>Return an array <code>baseUnitConversion</code> of length <code>n</code>, where <code>baseUnitConversion[i]</code> is the number of units of type <code>i</code> equivalent to a single unit of type 0. Since the answer may be large, return each <code>baseUnitConversion[i]</code> <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">conversions = [[0,1,2],[1,2,3]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,2,6]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Convert a single unit of type 0 into 2 units of type 1 using <code>conversions[0]</code>.</li>
	<li>Convert a single unit of type 0 into 6 units of type 2 using <code>conversions[0]</code>, then <code>conversions[1]</code>.</li>
</ul>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3528.Unit%20Conversion%20I/images/example1.png" style="width: 545px; height: 118px;" /></div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">conversions = [[0,1,2],[0,2,3],[1,3,4],[1,4,5],[2,5,2],[4,6,3],[5,7,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,2,3,8,10,6,30,24]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Convert a single unit of type 0 into 2 units of type 1 using <code>conversions[0]</code>.</li>
	<li>Convert a single unit of type 0 into 3 units of type 2 using <code>conversions[1]</code>.</li>
	<li>Convert a single unit of type 0 into 8 units of type 3 using <code>conversions[0]</code>, then <code>conversions[2]</code>.</li>
	<li>Convert a single unit of type 0 into 10 units of type 4 using <code>conversions[0]</code>, then <code>conversions[3]</code>.</li>
	<li>Convert a single unit of type 0 into 6 units of type 5 using <code>conversions[1]</code>, then <code>conversions[4]</code>.</li>
	<li>Convert a single unit of type 0 into 30 units of type 6 using <code>conversions[0]</code>, <code>conversions[3]</code>, then <code>conversions[5]</code>.</li>
	<li>Convert a single unit of type 0 into 24 units of type 7 using <code>conversions[1]</code>, <code>conversions[4]</code>, then <code>conversions[6]</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>conversions.length == n - 1</code></li>
	<li><code>0 &lt;= sourceUnit<sub>i</sub>, targetUnit<sub>i</sub> &lt; n</code></li>
	<li><code>1 &lt;= conversionFactor<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li>It is guaranteed that unit 0 can be converted into any other unit through a <strong>unique</strong> combination of conversions without using any conversions in the opposite direction.</li>
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
