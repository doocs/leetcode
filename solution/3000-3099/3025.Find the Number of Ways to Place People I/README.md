---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3025.Find%20the%20Number%20of%20Ways%20to%20Place%20People%20I/README.md
rating: 1707
source: 第 123 场双周赛 Q2
tags:
    - 几何
    - 数组
    - 数学
    - 枚举
    - 排序
---

<!-- problem:start -->

# [3025. 人员站位的方案数 I](https://leetcode.cn/problems/find-the-number-of-ways-to-place-people-i)

[English Version](/solution/3000-3099/3025.Find%20the%20Number%20of%20Ways%20to%20Place%20People%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个&nbsp;&nbsp;<code>n x 2</code>&nbsp;的二维数组 <code>points</code>&nbsp;，它表示二维平面上的一些点坐标，其中&nbsp;<code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code>&nbsp;。</p>

<p>&nbsp;</p>

<p>计算点对&nbsp;<code>(A, B)</code>&nbsp;的数量，其中</p>

<ul>
	<li><code>A</code> 在 <code>B</code> 的左上角，并且</li>
	<li>它们形成的长方形中（或直线上）没有其它点（<strong>包括边界</strong>），除了点&nbsp;<code>A</code> 和点 <code>B</code>。</li>
</ul>

<p>返回数量。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>points = [[1,1],[2,2],[3,3]]</span></p>

<p><span class="example-io"><b>输出：</b>0</span></p>

<p><strong>解释：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3025.Find%20the%20Number%20of%20Ways%20to%20Place%20People%20I/images/example1alicebob.png" style="width: 427px; height: 350px;" /></p>

<p>没有办法选择&nbsp;<code>A</code> 和&nbsp;<code>B</code>，使得&nbsp;<code>A</code>&nbsp;在&nbsp;<code>B</code>&nbsp;的左上角。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b></span><span class="example-io">points = [[6,2],[4,4],[2,6]]</span></p>

<p><span class="example-io"><b>输出：</b></span><span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p><img height="365" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3025.Find%20the%20Number%20of%20Ways%20to%20Place%20People%20I/images/t2.jpg" width="1321" /></p>

<ul>
	<li>左边的是点对&nbsp;<code>(points[1], points[0])</code>，其中&nbsp;<code>points[1]</code>&nbsp;在&nbsp;<code>points[0]</code>&nbsp;的左上角，并且形成的长方形内部是空的。</li>
	<li>中间的是点对&nbsp;<code>(points[2], points[1])</code>，和左边的一样是合法的点对。</li>
	<li>右边的是点对 <code>(points[2], points[0])</code>，其中 <code>points[2]</code> 在 <code>points[0]</code>&nbsp;的左上角，但&nbsp;<code>points[1]</code>&nbsp;在长方形内部，所以不是一个合法的点对。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b></span><span class="example-io">points = [[3,1],[1,3],[1,1]]</span></p>

<p><span class="example-io"><b>输出：</b></span><span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3025.Find%20the%20Number%20of%20Ways%20to%20Place%20People%20I/images/t3.jpg" style="width: 1269px; height: 350px;" /></p>

<ul>
	<li>左边的是点对 <code>(points[2], points[0])</code>，其中&nbsp;<code>points[2]</code>&nbsp;在&nbsp;<code>points[0]</code>&nbsp;的左上角并且在它们形成的直线上没有其它点。注意两个点形成一条线的情况是合法的。</li>
	<li>中间的是点对 <code>(points[1], points[2])</code>，和左边一样也是合法的点对。</li>
	<li>右边的是点对 <code>(points[1], points[0])</code>，它不是合法的点对，因为&nbsp;<code>points[2]</code>&nbsp;在长方形的边上。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 50</code></li>
	<li><code>points[i].length == 2</code></li>
	<li><code>0 &lt;= points[i][0], points[i][1] &lt;= 50</code></li>
	<li><code>points[i]</code>&nbsp;点对两两不同。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 枚举

我们不妨考虑枚举矩形左上角的点 $(x_1, y_1)$，那么根据题目，右下角的点 $(x_2, y_2)$ 随着 $x$ 的增大，纵坐标 $y$ 也会要严格增大，才符合题意。

因此，我们对所有点按照 $x$ 坐标升序排序，如果 $x$ 坐标相同，按照 $y$ 坐标降序排序。

然后我们枚举左上角的点 $(x_1, y_1)$，并且维护一个最大的 $y_2$，记为 $maxY$，表示所有右下角的点的纵坐标的最大值。然后我们枚举右下角的点 $(x_2, y_2)$，如果 $y_2$ 大于 $maxY$ 并且小于等于 $y_1$，那么我们就找到了一个合法的方案，将答案加一，然后更新 $maxY$ 为 $y_2$。

枚举完所有的点对后，我们就得到了答案。

时间复杂度 $O(n^2)$，空间复杂度 $O(\log n)$。其中 $n$ 是点的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfPairs(self, points: List[List[int]]) -> int:
        points.sort(key=lambda x: (x[0], -x[1]))
        ans = 0
        for i, (_, y1) in enumerate(points):
            max_y = -inf
            for _, y2 in points[i + 1 :]:
                if max_y < y2 <= y1:
                    max_y = y2
                    ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int numberOfPairs(int[][] points) {
        Arrays.sort(points, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int ans = 0;
        int n = points.length;
        final int inf = 1 << 30;
        for (int i = 0; i < n; ++i) {
            int y1 = points[i][1];
            int maxY = -inf;
            for (int j = i + 1; j < n; ++j) {
                int y2 = points[j][1];
                if (maxY < y2 && y2 <= y1) {
                    maxY = y2;
                    ++ans;
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
    int numberOfPairs(vector<vector<int>>& points) {
        sort(points.begin(), points.end(), [](const vector<int>& a, const vector<int>& b) {
            return a[0] < b[0] || (a[0] == b[0] && b[1] < a[1]);
        });
        int n = points.size();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int y1 = points[i][1];
            int maxY = INT_MIN;
            for (int j = i + 1; j < n; ++j) {
                int y2 = points[j][1];
                if (maxY < y2 && y2 <= y1) {
                    maxY = y2;
                    ++ans;
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func numberOfPairs(points [][]int) (ans int) {
	sort.Slice(points, func(i, j int) bool {
		return points[i][0] < points[j][0] || points[i][0] == points[j][0] && points[j][1] < points[i][1]
	})
	for i, p1 := range points {
		y1 := p1[1]
		maxY := math.MinInt32
		for _, p2 := range points[i+1:] {
			y2 := p2[1]
			if maxY < y2 && y2 <= y1 {
				maxY = y2
				ans++
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function numberOfPairs(points: number[][]): number {
    points.sort((a, b) => (a[0] === b[0] ? b[1] - a[1] : a[0] - b[0]));
    const n = points.length;
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        const [_, y1] = points[i];
        let maxY = -Infinity;
        for (let j = i + 1; j < n; ++j) {
            const [_, y2] = points[j];
            if (maxY < y2 && y2 <= y1) {
                maxY = y2;
                ++ans;
            }
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn number_of_pairs(mut points: Vec<Vec<i32>>) -> i32 {
        points.sort_by(|a, b| {
            if a[0] == b[0] {
                b[1].cmp(&a[1])
            } else {
                a[0].cmp(&b[0])
            }
        });

        let n = points.len();
        let mut ans = 0;
        for i in 0..n {
            let y1 = points[i][1];
            let mut max_y = i32::MIN;
            for j in (i + 1)..n {
                let y2 = points[j][1];
                if max_y < y2 && y2 <= y1 {
                    max_y = y2;
                    ans += 1;
                }
            }
        }
        ans
    }
}
```

#### C#

```cs
public class Solution {
    public int NumberOfPairs(int[][] points) {
        Array.Sort(points, (a, b) => a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int ans = 0;
        int n = points.Length;
        int inf = 1 << 30;
        for (int i = 0; i < n; ++i) {
            int y1 = points[i][1];
            int maxY = -inf;
            for (int j = i + 1; j < n; ++j) {
                int y2 = points[j][1];
                if (maxY < y2 && y2 <= y1) {
                    maxY = y2;
                    ++ans;
                }
            }
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
