---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3380.Maximum%20Area%20Rectangle%20With%20Point%20Constraints%20I/README_EN.md
tags:
    - Binary Indexed Tree
    - Segment Tree
    - Geometry
    - Array
    - Math
    - Enumeration
    - Sorting
---

<!-- problem:start -->

# [3380. Maximum Area Rectangle With Point Constraints I](https://leetcode.com/problems/maximum-area-rectangle-with-point-constraints-i)

[中文文档](/solution/3300-3399/3380.Maximum%20Area%20Rectangle%20With%20Point%20Constraints%20I/README.md)

## Description

<!-- description:start -->

<p>You are given an array <code>points</code> where <code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> represents the coordinates of a point on an infinite plane.</p>

<p>Your task is to find the <strong>maximum </strong>area of a rectangle that:</p>

<ul>
	<li>Can be formed using <strong>four</strong> of these points as its corners.</li>
	<li>Does <strong>not</strong> contain any other point inside or on its border.</li>
	<li>Has its edges&nbsp;<strong>parallel</strong> to the axes.</li>
</ul>

<p>Return the <strong>maximum area</strong> that you can obtain or -1 if no such rectangle is possible.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">points = [[1,1],[1,3],[3,1],[3,3]]</span></p>

<p><strong>Output: </strong>4</p>

<p><strong>Explanation:</strong></p>

<p><strong class="example"><img alt="Example 1 diagram" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3380.Maximum%20Area%20Rectangle%20With%20Point%20Constraints%20I/images/example1.png" style="width: 229px; height: 228px;" /></strong></p>

<p>We can make a rectangle with these 4 points as corners and there is no other point that lies inside or on the border<!-- notionvc: f270d0a3-a596-4ed6-9997-2c7416b2b4ee -->. Hence, the maximum possible area would be 4.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">points = [[1,1],[1,3],[3,1],[3,3],[2,2]]</span></p>

<p><strong>Output:</strong><b> </b>-1</p>

<p><strong>Explanation:</strong></p>

<p><strong class="example"><img alt="Example 2 diagram" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3380.Maximum%20Area%20Rectangle%20With%20Point%20Constraints%20I/images/example2.png" style="width: 229px; height: 228px;" /></strong></p>

<p>There is only one rectangle possible is with points <code>[1,1], [1,3], [3,1]</code> and <code>[3,3]</code> but <code>[2,2]</code> will always lie inside it. Hence, returning -1.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">points = [[1,1],[1,3],[3,1],[3,3],[1,2],[3,2]]</span></p>

<p><strong>Output: </strong>2</p>

<p><strong>Explanation:</strong></p>

<p><strong class="example"><img alt="Example 3 diagram" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3380.Maximum%20Area%20Rectangle%20With%20Point%20Constraints%20I/images/example3.png" style="width: 229px; height: 228px;" /></strong></p>

<p>The maximum area rectangle is formed by the points <code>[1,3], [1,2], [3,2], [3,3]</code>, which has an area of 2. Additionally, the points <code>[1,1], [1,2], [3,1], [3,2]</code> also form a valid rectangle with the same area.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= points.length &lt;= 10</code></li>
	<li><code>points[i].length == 2</code></li>
	<li><code>0 &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 100</code></li>
	<li>All the given points are <strong>unique</strong>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

We can enumerate the bottom-left corner $(x_3, y_3)$ and the top-right corner $(x_4, y_4)$ of the rectangle. Then, we enumerate all points $(x, y)$ and check if the point is inside or on the boundary of the rectangle. If it is, it does not meet the condition. Otherwise, we exclude the points outside the rectangle and check if there are 4 remaining points. If there are, these 4 points can form a rectangle. We calculate the area of the rectangle and take the maximum value.

The time complexity is $O(n^3)$, where $n$ is the length of the array $\textit{points}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxRectangleArea(self, points: List[List[int]]) -> int:
        def check(x1: int, y1: int, x2: int, y2: int) -> bool:
            cnt = 0
            for x, y in points:
                if x < x1 or x > x2 or y < y1 or y > y2:
                    continue
                if (x == x1 or x == x2) and (y == y1 or y == y2):
                    cnt += 1
                    continue
                return False
            return cnt == 4

        ans = -1
        for i, (x1, y1) in enumerate(points):
            for x2, y2 in points[:i]:
                x3, y3 = min(x1, x2), min(y1, y2)
                x4, y4 = max(x1, x2), max(y1, y2)
                if check(x3, y3, x4, y4):
                    ans = max(ans, (x4 - x3) * (y4 - y3))
        return ans
```

#### Java

```java
class Solution {
    public int maxRectangleArea(int[][] points) {
        int ans = -1;
        for (int i = 0; i < points.length; ++i) {
            int x1 = points[i][0], y1 = points[i][1];
            for (int j = 0; j < i; ++j) {
                int x2 = points[j][0], y2 = points[j][1];
                int x3 = Math.min(x1, x2), y3 = Math.min(y1, y2);
                int x4 = Math.max(x1, x2), y4 = Math.max(y1, y2);
                if (check(points, x3, y3, x4, y4)) {
                    ans = Math.max(ans, (x4 - x3) * (y4 - y3));
                }
            }
        }
        return ans;
    }

    private boolean check(int[][] points, int x1, int y1, int x2, int y2) {
        int cnt = 0;
        for (var p : points) {
            int x = p[0];
            int y = p[1];
            if (x < x1 || x > x2 || y < y1 || y > y2) {
                continue;
            }
            if ((x == x1 || x == x2) && (y == y1 || y == y2)) {
                cnt++;
                continue;
            }
            return false;
        }
        return cnt == 4;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxRectangleArea(vector<vector<int>>& points) {
        auto check = [&](int x1, int y1, int x2, int y2) -> bool {
            int cnt = 0;
            for (const auto& point : points) {
                int x = point[0];
                int y = point[1];
                if (x < x1 || x > x2 || y < y1 || y > y2) {
                    continue;
                }
                if ((x == x1 || x == x2) && (y == y1 || y == y2)) {
                    cnt++;
                    continue;
                }
                return false;
            }
            return cnt == 4;
        };

        int ans = -1;
        for (int i = 0; i < points.size(); i++) {
            int x1 = points[i][0], y1 = points[i][1];
            for (int j = 0; j < i; j++) {
                int x2 = points[j][0], y2 = points[j][1];
                int x3 = min(x1, x2), y3 = min(y1, y2);
                int x4 = max(x1, x2), y4 = max(y1, y2);
                if (check(x3, y3, x4, y4)) {
                    ans = max(ans, (x4 - x3) * (y4 - y3));
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxRectangleArea(points [][]int) int {
	check := func(x1, y1, x2, y2 int) bool {
		cnt := 0
		for _, point := range points {
			x, y := point[0], point[1]
			if x < x1 || x > x2 || y < y1 || y > y2 {
				continue
			}
			if (x == x1 || x == x2) && (y == y1 || y == y2) {
				cnt++
				continue
			}
			return false
		}
		return cnt == 4
	}

	ans := -1
	for i := 0; i < len(points); i++ {
		x1, y1 := points[i][0], points[i][1]
		for j := 0; j < i; j++ {
			x2, y2 := points[j][0], points[j][1]
			x3, y3 := min(x1, x2), min(y1, y2)
			x4, y4 := max(x1, x2), max(y1, y2)
			if check(x3, y3, x4, y4) {
				ans = max(ans, (x4-x3)*(y4-y3))
			}
		}
	}
	return ans
}
```

#### TypeScript

```ts
function maxRectangleArea(points: number[][]): number {
    const check = (x1: number, y1: number, x2: number, y2: number): boolean => {
        let cnt = 0;
        for (const point of points) {
            const [x, y] = point;
            if (x < x1 || x > x2 || y < y1 || y > y2) {
                continue;
            }
            if ((x === x1 || x === x2) && (y === y1 || y === y2)) {
                cnt++;
                continue;
            }
            return false;
        }
        return cnt === 4;
    };

    let ans = -1;
    for (let i = 0; i < points.length; i++) {
        const [x1, y1] = points[i];
        for (let j = 0; j < i; j++) {
            const [x2, y2] = points[j];
            const [x3, y3] = [Math.min(x1, x2), Math.min(y1, y2)];
            const [x4, y4] = [Math.max(x1, x2), Math.max(y1, y2)];
            if (check(x3, y3, x4, y4)) {
                ans = Math.max(ans, (x4 - x3) * (y4 - y3));
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
