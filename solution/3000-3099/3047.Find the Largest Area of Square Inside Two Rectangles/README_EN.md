# [3047. Find the Largest Area of Square Inside Two Rectangles](https://leetcode.com/problems/find-the-largest-area-of-square-inside-two-rectangles)

[中文文档](/solution/3000-3099/3047.Find%20the%20Largest%20Area%20of%20Square%20Inside%20Two%20Rectangles/README.md)

<!-- tags: -->

## Description

<p>There exist <code>n</code> rectangles in a 2D plane. You are given two <strong>0-indexed</strong> 2D integer arrays <code>bottomLeft</code> and <code>topRight</code>, both of size <code>n x 2</code>, where <code>bottomLeft[i]</code> and <code>topRight[i]</code> represent the <strong>bottom-left</strong> and <strong>top-right</strong> coordinates of the <code>i<sup>th</sup></code> rectangle respectively.</p>

<p>You can select a region formed from the <strong>intersection</strong> of&nbsp;two of the given rectangles. You need to find the <strong>largest </strong>area of a <strong>square</strong> that can fit <strong>inside</strong> this region if you select the region optimally.</p>

<p>Return <em>the <strong>largest </strong>possible area of a square, or </em><code>0</code><em> if there <strong>do not</strong> exist any intersecting regions between the rectangles</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3047.Find%20the%20Largest%20Area%20of%20Square%20Inside%20Two%20Rectangles/images/example12.png" style="width: 443px; height: 364px; padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem;" />
<pre>
<strong>Input:</strong> bottomLeft = [[1,1],[2,2],[3,1]], topRight = [[3,3],[4,4],[6,6]]
<strong>Output:</strong> 1
<strong>Explanation:</strong> A square with side length 1 can fit inside either the intersecting region of rectangle 0 and rectangle 1, or the intersecting region of rectangle 1 and rectangle 2. Hence the largest area is side * side which is 1 * 1 == 1.
It can be shown that a square with a greater side length can not fit inside any intersecting region.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3047.Find%20the%20Largest%20Area%20of%20Square%20Inside%20Two%20Rectangles/images/rectanglesexample2.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 445px; height: 365px;" />
<pre>
<strong>Input:</strong> bottomLeft = [[1,1],[2,2],[1,2]], topRight = [[3,3],[4,4],[3,4]]
<strong>Output:</strong> 1
<strong>Explanation:</strong> A square with side length 1 can fit inside either the intersecting region of rectangle 0 and rectangle 1, the intersecting region of rectangle 1 and rectangle 2, or the intersection region of all 3 rectangles. Hence the largest area is side * side which is 1 * 1 == 1.
It can be shown that a square with a greater side length can not fit inside any intersecting region.
Note that the region can be formed by the intersection of more than 2 rectangles.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3047.Find%20the%20Largest%20Area%20of%20Square%20Inside%20Two%20Rectangles/images/rectanglesexample3.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 444px; height: 364px;" />
<pre>
<strong>Input:</strong> bottomLeft = [[1,1],[3,3],[3,1]], topRight = [[2,2],[4,4],[4,2]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> No pair of rectangles intersect, hence, we return 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == bottomLeft.length == topRight.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>3</sup></code></li>
	<li><code>bottomLeft[i].length == topRight[i].length == 2</code></li>
	<li><code>1 &lt;= bottomLeft[i][0], bottomLeft[i][1] &lt;= 10<sup>7</sup></code></li>
	<li><code>1 &lt;= topRight[i][0], topRight[i][1] &lt;= 10<sup>7</sup></code></li>
	<li><code>bottomLeft[i][0] &lt; topRight[i][0]</code></li>
	<li><code>bottomLeft[i][1] &lt; topRight[i][1]</code></li>
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
