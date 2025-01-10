---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0836.Rectangle%20Overlap/README_EN.md
tags:
    - Geometry
    - Math
---

<!-- problem:start -->

# [836. Rectangle Overlap](https://leetcode.com/problems/rectangle-overlap)

[中文文档](/solution/0800-0899/0836.Rectangle%20Overlap/README.md)

## Description

<!-- description:start -->

<p>An axis-aligned rectangle is represented as a list <code>[x1, y1, x2, y2]</code>, where <code>(x1, y1)</code> is the coordinate of its bottom-left corner, and <code>(x2, y2)</code> is the coordinate of its top-right corner. Its top and bottom edges are parallel to the X-axis, and its left and right edges are parallel to the Y-axis.</p>

<p>Two rectangles overlap if the area of their intersection is <strong>positive</strong>. To be clear, two rectangles that only touch at the corner or edges do not overlap.</p>

<p>Given two axis-aligned rectangles <code>rec1</code> and <code>rec2</code>, return <code>true</code><em> if they overlap, otherwise return </em><code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> rec1 = [0,0,2,2], rec2 = [1,1,3,3]
<strong>Output:</strong> true
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> rec1 = [0,0,1,1], rec2 = [1,0,2,1]
<strong>Output:</strong> false
</pre><p><strong class="example">Example 3:</strong></p>
<pre><strong>Input:</strong> rec1 = [0,0,1,1], rec2 = [2,2,3,3]
<strong>Output:</strong> false
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>rec1.length == 4</code></li>
	<li><code>rec2.length == 4</code></li>
	<li><code>-10<sup>9</sup> &lt;= rec1[i], rec2[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>rec1</code> and <code>rec2</code> represent a valid rectangle with a non-zero area.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Determine Non-Overlap Cases

Let the coordinates of rectangle $\text{rec1}$ be $(x_1, y_1, x_2, y_2)$, and the coordinates of rectangle $\text{rec2}$ be $(x_3, y_3, x_4, y_4)$.

The rectangles $\text{rec1}$ and $\text{rec2}$ do not overlap if any of the following conditions are met:

-   $y_3 \geq y_2$: $\text{rec2}$ is above $\text{rec1}$;
-   $y_4 \leq y_1$: $\text{rec2}$ is below $\text{rec1}$;
-   $x_3 \geq x_2$: $\text{rec2}$ is to the right of $\text{rec1}$;
-   $x_4 \leq x_1$: $\text{rec2}$ is to the left of $\text{rec1}$.

If none of the above conditions are met, the rectangles $\text{rec1}$ and $\text{rec2}$ overlap.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isRectangleOverlap(self, rec1: List[int], rec2: List[int]) -> bool:
        x1, y1, x2, y2 = rec1
        x3, y3, x4, y4 = rec2
        return not (y3 >= y2 or y4 <= y1 or x3 >= x2 or x4 <= x1)
```

#### Java

```java
class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        int x1 = rec1[0], y1 = rec1[1], x2 = rec1[2], y2 = rec1[3];
        int x3 = rec2[0], y3 = rec2[1], x4 = rec2[2], y4 = rec2[3];
        return !(y3 >= y2 || y4 <= y1 || x3 >= x2 || x4 <= x1);
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isRectangleOverlap(vector<int>& rec1, vector<int>& rec2) {
        int x1 = rec1[0], y1 = rec1[1], x2 = rec1[2], y2 = rec1[3];
        int x3 = rec2[0], y3 = rec2[1], x4 = rec2[2], y4 = rec2[3];
        return !(y3 >= y2 || y4 <= y1 || x3 >= x2 || x4 <= x1);
    }
};
```

#### Go

```go
func isRectangleOverlap(rec1 []int, rec2 []int) bool {
	x1, y1, x2, y2 := rec1[0], rec1[1], rec1[2], rec1[3]
	x3, y3, x4, y4 := rec2[0], rec2[1], rec2[2], rec2[3]
	return !(y3 >= y2 || y4 <= y1 || x3 >= x2 || x4 <= x1)
}
```

#### TypeScript

```ts
function isRectangleOverlap(rec1: number[], rec2: number[]): boolean {
    const [x1, y1, x2, y2] = rec1;
    const [x3, y3, x4, y4] = rec2;
    return !(y3 >= y2 || y4 <= y1 || x3 >= x2 || x4 <= x1);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
