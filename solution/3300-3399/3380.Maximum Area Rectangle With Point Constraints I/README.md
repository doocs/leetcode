---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3380.Maximum%20Area%20Rectangle%20With%20Point%20Constraints%20I/README.md
rating: 1743
source: 第 427 场周赛 Q2
tags:
    - 树状数组
    - 线段树
    - 几何
    - 数组
    - 数学
    - 枚举
    - 排序
---

<!-- problem:start -->

# [3380. 用点构造面积最大的矩形 I](https://leetcode.cn/problems/maximum-area-rectangle-with-point-constraints-i)

[English Version](/solution/3300-3399/3380.Maximum%20Area%20Rectangle%20With%20Point%20Constraints%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个数组 <code>points</code>，其中 <code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> 表示无限平面上一点的坐标。</p>

<p>你的任务是找出满足以下条件的矩形可能的&nbsp;<strong>最大&nbsp;</strong>面积：</p>

<ul>
	<li>矩形的四个顶点必须是数组中的&nbsp;<strong>四个&nbsp;</strong>点。</li>
	<li>矩形的内部或边界上&nbsp;<strong>不能&nbsp;</strong>包含任何其他点。</li>
	<li>矩形的边与坐标轴&nbsp;<strong>平行&nbsp;</strong>。</li>
</ul>

<p>返回可以获得的&nbsp;<strong>最大面积&nbsp;</strong>，如果无法形成这样的矩形，则返回 -1。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">points = [[1,1],[1,3],[3,1],[3,3]]</span></p>

<p><strong>输出：</strong>4</p>

<p><strong>解释：</strong></p>

<p><strong class="example"><img alt="示例 1 图示" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3380.Maximum%20Area%20Rectangle%20With%20Point%20Constraints%20I/images/example1.png" style="width: 229px; height: 228px;" /></strong></p>

<p>我们可以用这 4 个点作为顶点构成一个矩形，并且矩形内部或边界上没有其他点。因此，最大面积为 4 。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">points = [[1,1],[1,3],[3,1],[3,3],[2,2]]</span></p>

<p><strong>输出：</strong>-1</p>

<p><strong>解释：</strong></p>

<p><strong class="example"><img alt="示例 2 图示" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3380.Maximum%20Area%20Rectangle%20With%20Point%20Constraints%20I/images/example2.png" style="width: 229px; height: 228px;" /></strong></p>

<p>唯一一组可能构成矩形的点为 <code>[1,1], [1,3], [3,1]</code> 和 <code>[3,3]</code>，但点 <code>[2,2]</code> 总是位于矩形内部。因此，返回 -1 。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">points = [[1,1],[1,3],[3,1],[3,3],[1,2],[3,2]]</span></p>

<p><strong>输出：</strong>2</p>

<p><strong>解释：</strong></p>

<p><strong class="example"><img alt="示例 3 图示" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3380.Maximum%20Area%20Rectangle%20With%20Point%20Constraints%20I/images/example3.png" style="width: 229px; height: 228px;" /></strong></p>

<p>点 <code>[1,3], [1,2], [3,2], [3,3]</code>&nbsp;可以构成面积最大的矩形，面积为 2。此外，点 <code>[1,1], [1,2], [3,1], [3,2]</code> 也可以构成一个符合题目要求的矩形，面积相同。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= points.length &lt;= 10</code></li>
	<li><code>points[i].length == 2</code></li>
	<li><code>0 &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 100</code></li>
	<li>给定的所有点都是 <strong>唯一</strong> 的。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

我们可以枚举矩形的左下角下标 $(x_3, y_3)$ 和右上角下标 $(x_4, y_4)$，然后枚举所有的点 $(x, y)$，判断点是否在矩形的内部或边界上，如果是，说明不满足条件，否则，我们排除掉在矩形外部的点，然后判断剩下的点是否有 4 个，如果有，说明这 4 个点可以构成一个矩形，计算矩形的面积，取最大值即可。

时间复杂度 $O(n^3)$，其中 $n$ 是数组 $\textit{points}$ 的长度。空间复杂度 $O(1)$。

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
