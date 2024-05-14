# [3047. Find the Largest Area of Square Inside Two Rectangles](https://leetcode.com/problems/find-the-largest-area-of-square-inside-two-rectangles)

[中文文档](/solution/3000-3099/3047.Find%20the%20Largest%20Area%20of%20Square%20Inside%20Two%20Rectangles/README.md)

<!-- tags:Geometry,Array,Math -->

<!-- difficulty:Medium -->

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

### Solution 1: Enumeration

We can enumerate two rectangles, where the coordinates of the bottom left and top right corners of rectangle 1 are $(x_1, y_1)$ and $(x_2, y_2)$ respectively, and the coordinates of the bottom left and top right corners of rectangle 2 are $(x_3, y_3)$ and $(x_4, y_4)$ respectively.

If rectangle 1 and rectangle 2 intersect, then the coordinates of the intersection are:

-   The x-coordinate of the bottom left corner is the maximum of the x-coordinates of the bottom left corners of the two rectangles, i.e., $\max(x_1, x_3)$;
-   The y-coordinate of the bottom left corner is the maximum of the y-coordinates of the bottom left corners of the two rectangles, i.e., $\max(y_1, y_3)$;
-   The x-coordinate of the top right corner is the minimum of the x-coordinates of the top right corners of the two rectangles, i.e., $\min(x_2, x_4)$;
-   The y-coordinate of the top right corner is the minimum of the y-coordinates of the top right corners of the two rectangles, i.e., $\min(y_2, y_4)$.

Then the width and height of the intersection are $w = \min(x_2, x_4) - \max(x_1, x_3)$ and $h = \min(y_2, y_4) - \max(y_1, y_3)$ respectively. We take the minimum of the two as the side length, i.e., $e = \min(w, h)$. If $e > 0$, then we can get a square with an area of $e^2$. We take the maximum area of all squares.

The time complexity is $O(n^2)$, where $n$ is the number of rectangles. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def largestSquareArea(
        self, bottomLeft: List[List[int]], topRight: List[List[int]]
    ) -> int:
        ans = 0
        for ((x1, y1), (x2, y2)), ((x3, y3), (x4, y4)) in combinations(
            zip(bottomLeft, topRight), 2
        ):
            w = min(x2, x4) - max(x1, x3)
            h = min(y2, y4) - max(y1, y3)
            e = min(w, h)
            if e > 0:
                ans = max(ans, e * e)
        return ans
```

```java
class Solution {
    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        long ans = 0;
        for (int i = 0; i < bottomLeft.length; ++i) {
            int x1 = bottomLeft[i][0], y1 = bottomLeft[i][1];
            int x2 = topRight[i][0], y2 = topRight[i][1];
            for (int j = i + 1; j < bottomLeft.length; ++j) {
                int x3 = bottomLeft[j][0], y3 = bottomLeft[j][1];
                int x4 = topRight[j][0], y4 = topRight[j][1];
                int w = Math.min(x2, x4) - Math.max(x1, x3);
                int h = Math.min(y2, y4) - Math.max(y1, y3);
                int e = Math.min(w, h);
                if (e > 0) {
                    ans = Math.max(ans, 1L * e * e);
                }
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    long long largestSquareArea(vector<vector<int>>& bottomLeft, vector<vector<int>>& topRight) {
        long long ans = 0;
        for (int i = 0; i < bottomLeft.size(); ++i) {
            int x1 = bottomLeft[i][0], y1 = bottomLeft[i][1];
            int x2 = topRight[i][0], y2 = topRight[i][1];
            for (int j = i + 1; j < bottomLeft.size(); ++j) {
                int x3 = bottomLeft[j][0], y3 = bottomLeft[j][1];
                int x4 = topRight[j][0], y4 = topRight[j][1];
                int w = min(x2, x4) - max(x1, x3);
                int h = min(y2, y4) - max(y1, y3);
                int e = min(w, h);
                if (e > 0) {
                    ans = max(ans, 1LL * e * e);
                }
            }
        }
        return ans;
    }
};
```

```go
func largestSquareArea(bottomLeft [][]int, topRight [][]int) (ans int64) {
	for i, b1 := range bottomLeft {
		t1 := topRight[i]
		x1, y1 := b1[0], b1[1]
		x2, y2 := t1[0], t1[1]
		for j := i + 1; j < len(bottomLeft); j++ {
			x3, y3 := bottomLeft[j][0], bottomLeft[j][1]
			x4, y4 := topRight[j][0], topRight[j][1]
			w := min(x2, x4) - max(x1, x3)
			h := min(y2, y4) - max(y1, y3)
			e := min(w, h)
			if e > 0 {
				ans = max(ans, int64(e)*int64(e))
			}
		}
	}
	return
}
```

```ts
function largestSquareArea(bottomLeft: number[][], topRight: number[][]): number {
    let ans = 0;
    for (let i = 0; i < bottomLeft.length; ++i) {
        const [x1, y1] = bottomLeft[i];
        const [x2, y2] = topRight[i];
        for (let j = i + 1; j < bottomLeft.length; ++j) {
            const [x3, y3] = bottomLeft[j];
            const [x4, y4] = topRight[j];
            const w = Math.min(x2, x4) - Math.max(x1, x3);
            const h = Math.min(y2, y4) - Math.max(y1, y3);
            const e = Math.min(w, h);
            if (e > 0) {
                ans = Math.max(ans, e * e);
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
