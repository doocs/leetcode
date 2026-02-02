---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3047.Find%20the%20Largest%20Area%20of%20Square%20Inside%20Two%20Rectangles/README_EN.md
rating: 1601
source: Weekly Contest 386 Q2
tags:
    - Geometry
    - Array
    - Math
---

<!-- problem:start -->

# [3047. Find the Largest Area of Square Inside Two Rectangles](https://leetcode.com/problems/find-the-largest-area-of-square-inside-two-rectangles)

[中文文档](/solution/3000-3099/3047.Find%20the%20Largest%20Area%20of%20Square%20Inside%20Two%20Rectangles/README.md)

## Description

<!-- description:start -->

<p>There exist <code>n</code> rectangles in a 2D plane with edges parallel to the x and y axis. You are given two 2D integer arrays&nbsp;<code>bottomLeft</code> and <code>topRight</code>&nbsp;where <code>bottomLeft[i] = [a_i, b_i]</code> and <code>topRight[i] = [c_i, d_i]</code> represent&nbsp;the <strong>bottom-left</strong> and <strong>top-right</strong> coordinates of the <code>i<sup>th</sup></code> rectangle, respectively.</p>

<p>You need to find the <strong>maximum</strong> area of a <strong>square</strong> that can fit inside the intersecting region of at least two rectangles. Return <code>0</code> if such a square does not exist.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3047.Find%20the%20Largest%20Area%20of%20Square%20Inside%20Two%20Rectangles/images/example12.png" style="width: 443px; height: 364px; padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem;" />
<p><strong>Input:</strong> bottomLeft = [[1,1],[2,2],[3,1]], topRight = [[3,3],[4,4],[6,6]]</p>

<p><strong>Output:</strong> 1</p>

<p><strong>Explanation:</strong></p>

<p>A square with side length 1 can fit inside either the intersecting region of rectangles 0 and 1 or the intersecting region of rectangles 1 and 2. Hence the maximum area is 1. It can be shown that a square with a greater side length can not fit inside any intersecting region of two rectangles.</p>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3047.Find%20the%20Largest%20Area%20of%20Square%20Inside%20Two%20Rectangles/images/diag.png" style="width: 451px; height: 470px; padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem;" />
<p><strong>Input:</strong> bottomLeft = [[1,1],[1,3],[1,5]], topRight = [[5,5],[5,7],[5,9]]</p>

<p><strong>Output:</strong> 4</p>

<p><strong>Explanation:</strong></p>

<p>A square with side length 2 can fit inside either the intersecting region of rectangles 0 and 1 or the intersecting region of rectangles 1 and 2. Hence the maximum area is <code>2 * 2 = 4</code>. It can be shown that a square with a greater side length can not fit inside any intersecting region of two rectangles.</p>

<p><strong class="example">Example 3:</strong></p>
<code> <img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3047.Find%20the%20Largest%20Area%20of%20Square%20Inside%20Two%20Rectangles/images/rectanglesexample2.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 445px; height: 365px;" /> </code>

<p><strong>Input:</strong> bottomLeft = [[1,1],[2,2],[1,2]], topRight = [[3,3],[4,4],[3,4]]</p>

<p><strong>Output:</strong> 1</p>

<p><strong>Explanation:</strong></p>

<p>A square with side length 1 can fit inside the intersecting region of any two rectangles. Also, no larger square can, so the maximum area is 1. Note that the region can be formed by the intersection of more than 2 rectangles.</p>

<p><strong class="example">Example 4:</strong></p>
<code> <img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3047.Find%20the%20Largest%20Area%20of%20Square%20Inside%20Two%20Rectangles/images/rectanglesexample3.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 444px; height: 364px;" /> </code>

<p><strong>Input:&nbsp;</strong>bottomLeft = [[1,1],[3,3],[3,1]], topRight = [[2,2],[4,4],[4,2]]</p>

<p><strong>Output:</strong> 0</p>

<p><strong>Explanation:</strong></p>

<p>No pair of rectangles intersect, hence, the answer is 0.</p>

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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

We can enumerate two rectangles, where the coordinates of the bottom left and top right corners of rectangle 1 are $(x_1, y_1)$ and $(x_2, y_2)$ respectively, and the coordinates of the bottom left and top right corners of rectangle 2 are $(x_3, y_3)$ and $(x_4, y_4)$ respectively.

If rectangle 1 and rectangle 2 intersect, then the coordinates of the intersection are:

- The x-coordinate of the bottom left corner is the maximum of the x-coordinates of the bottom left corners of the two rectangles, i.e., $\max(x_1, x_3)$;
- The y-coordinate of the bottom left corner is the maximum of the y-coordinates of the bottom left corners of the two rectangles, i.e., $\max(y_1, y_3)$;
- The x-coordinate of the top right corner is the minimum of the x-coordinates of the top right corners of the two rectangles, i.e., $\min(x_2, x_4)$;
- The y-coordinate of the top right corner is the minimum of the y-coordinates of the top right corners of the two rectangles, i.e., $\min(y_2, y_4)$.

Then the width and height of the intersection are $w = \min(x_2, x_4) - \max(x_1, x_3)$ and $h = \min(y_2, y_4) - \max(y_1, y_3)$ respectively. We take the minimum of the two as the side length, i.e., $e = \min(w, h)$. If $e > 0$, then we can get a square with an area of $e^2$. We take the maximum area of all squares.

The time complexity is $O(n^2)$, where $n$ is the number of rectangles. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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

#### TypeScript

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

#### Rust

```rust
impl Solution {
    pub fn largest_square_area(bottom_left: Vec<Vec<i32>>, top_right: Vec<Vec<i32>>) -> i64 {
        let mut ans: i64 = 0;
        let n = bottom_left.len();

        for i in 0..n {
            let x1 = bottom_left[i][0];
            let y1 = bottom_left[i][1];
            let x2 = top_right[i][0];
            let y2 = top_right[i][1];

            for j in (i + 1)..n {
                let x3 = bottom_left[j][0];
                let y3 = bottom_left[j][1];
                let x4 = top_right[j][0];
                let y4 = top_right[j][1];

                let w = (x2.min(x4) - x1.max(x3)) as i64;
                let h = (y2.min(y4) - y1.max(y3)) as i64;
                let e = w.min(h);

                if e > 0 {
                    ans = ans.max(e * e);
                }
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
