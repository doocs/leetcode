---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3025.Find%20the%20Number%20of%20Ways%20to%20Place%20People%20I/README.md
rating: 1707
tags:
    - 几何
    - 数组
    - 数学
    - 枚举
    - 排序
---

# [3025. 人员站位的方案数 I](https://leetcode.cn/problems/find-the-number-of-ways-to-place-people-i)

[English Version](/solution/3000-3099/3025.Find%20the%20Number%20of%20Ways%20to%20Place%20People%20I/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个&nbsp;&nbsp;<code>n x 2</code>&nbsp;的二维数组 <code>points</code>&nbsp;，它表示二维平面上的一些点坐标，其中&nbsp;<code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code>&nbsp;。</p>

<p>我们定义 x 轴的正方向为 <strong>右</strong>&nbsp;（<strong>x 轴递增的方向</strong>），x 轴的负方向为 <strong>左</strong>&nbsp;（<strong>x 轴递减的方向</strong>）。类似的，我们定义 y 轴的正方向为 <strong>上</strong>&nbsp;（<strong>y 轴递增的方向</strong>），y 轴的负方向为 <strong>下</strong>&nbsp;（<strong>y 轴递减的方向</strong>）。</p>

<p>你需要安排这 <code>n</code>&nbsp;个人的站位，这 <code>n</code>&nbsp;个人中包括&nbsp;Alice 和&nbsp;Bob 。你需要确保每个点处&nbsp;<strong>恰好</strong>&nbsp;有&nbsp;<strong>一个</strong>&nbsp;人。同时，Alice 想跟 Bob 单独玩耍，所以&nbsp;Alice&nbsp;会以 Alice<b>&nbsp;</b>的坐标为 <strong>左上角</strong>&nbsp;， Bob 的坐标为 <strong>右下角</strong>&nbsp;建立一个矩形的围栏（<strong>注意</strong>，围栏可能&nbsp;<strong>不</strong> 包含任何区域，也就是说围栏可能是一条线段）。如果围栏的 <strong>内部</strong>&nbsp;或者 <strong>边缘</strong>&nbsp;上有任何其他人，Alice 都会难过。</p>

<p>请你在确保 Alice&nbsp;<strong>不会</strong> 难过的前提下，返回 Alice 和 Bob 可以选择的 <strong>点对</strong>&nbsp;数目。</p>

<p><b>注意</b>，Alice 建立的围栏必须确保 Alice 的位置是矩形的左上角，Bob 的位置是矩形的右下角。比方说，以&nbsp;<code>(1, 1)</code>&nbsp;，<code>(1, 3)</code>&nbsp;，<code>(3, 1)</code>&nbsp;和&nbsp;<code>(3, 3)</code>&nbsp;为矩形的四个角，给定下图的两个输入，Alice 都不能建立围栏，原因如下：</p>

<ul>
	<li>图一中，Alice 在&nbsp;<code>(3, 3)</code>&nbsp;且 Bob 在&nbsp;<code>(1, 1)</code>&nbsp;，Alice&nbsp;的位置不是左上角且 Bob 的位置不是右下角。</li>
	<li>图二中，Alice 在&nbsp;<code>(1, 3)</code>&nbsp;且 Bob 在&nbsp;<code>(1, 1)</code>&nbsp;，Bob 的位置不是在围栏的右下角。</li>
</ul>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3025.Find%20the%20Number%20of%20Ways%20to%20Place%20People%20I/images/example0alicebob-1.png" style="width: 750px; height: 308px;padding: 10px; background: #fff; border-radius: .5rem;" />
<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3025.Find%20the%20Number%20of%20Ways%20to%20Place%20People%20I/images/example1alicebob.png" style="width: 376px; height: 308px; padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem;" /></p>

<pre>
<b>输入：</b>points = [[1,1],[2,2],[3,3]]
<b>输出：</b>0
<strong>解释：</strong>没有办法可以让 Alice 的围栏以 Alice 的位置为左上角且 Bob 的位置为右下角。所以我们返回 0 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3025.Find%20the%20Number%20of%20Ways%20to%20Place%20People%20I/images/1708226715-CxjXKb-20240218-112338.jpeg" style="width: 900px; height: 248px;" /></p>

<pre>
<b>输入：</b>points = [[6,2],[4,4],[2,6]]
<b>输出：</b>2
<b>解释：</b>总共有 2 种方案安排 Alice 和 Bob 的位置，使得 Alice 不会难过：
- Alice 站在 (4, 4) ，Bob 站在 (6, 2) 。
- Alice 站在 (2, 6) ，Bob 站在 (4, 4) 。
不能安排 Alice 站在 (2, 6) 且 Bob 站在 (6, 2) ，因为站在 (4, 4) 的人处于围栏内。
</pre>

<p><strong class="example">示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3025.Find%20the%20Number%20of%20Ways%20to%20Place%20People%20I/images/1708226721-wTbEuK-20240218-112351.jpeg" style="width: 900px; height: 247px;" /></p>

<pre>
<b>输入：</b>points = [[3,1],[1,3],[1,1]]
<b>输出：</b>2
<b>解释：</b>总共有 2 种方案安排 Alice 和 Bob 的位置，使得 Alice 不会难过：
- Alice 站在 (1, 1) ，Bob 站在 (3, 1) 。
- Alice 站在 (1, 3) ，Bob 站在 (1, 1) 。
不能安排 Alice 站在 (1, 3) 且 Bob 站在 (3, 1) ，因为站在 (1, 1) 的人处于围栏内。
注意围栏是可以不包含任何面积的，上图中第一和第二个围栏都是合法的。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 50</code></li>
	<li><code>points[i].length == 2</code></li>
	<li><code>0 &lt;= points[i][0], points[i][1] &lt;= 50</code></li>
	<li><code>points[i]</code>&nbsp;点对两两不同。</li>
</ul>

## 解法

### 方法一：排序 + 枚举

我们不妨考虑枚举矩形左上角的点 $(x_1, y_1)$，那么根据题目，右下角的点 $(x_2, y_2)$ 随着 $x$ 的增大，纵坐标 $y$ 也会要严格增大，才符合题意。

因此，我们对所有点按照 $x$ 坐标升序排序，如果 $x$ 坐标相同，按照 $y$ 坐标降序排序。

然后我们枚举左上角的点 $(x_1, y_1)$，并且维护一个最大的 $y_2$，记为 $maxY$，表示所有右下角的点的纵坐标的最大值。然后我们枚举右下角的点 $(x_2, y_2)$，如果 $y_2$ 大于 $maxY$ 并且小于等于 $y_1$，那么我们就找到了一个合法的方案，将答案加一，然后更新 $maxY$ 为 $y_2$。

枚举完所有的点对后，我们就得到了答案。

时间复杂度 $O(n^2)$，空间复杂度 $O(\log n)$。其中 $n$ 是点的数量。

<!-- tabs:start -->

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

<!-- end -->
