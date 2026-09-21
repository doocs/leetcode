---
comments: true
difficulty: 困难
rating: 2201
source: 第 189 场周赛 Q4
tags:
    - 几何
    - 数组
    - 数学
---

<!-- problem:start -->

# [1453. 圆形靶内的最大飞镖数量](https://leetcode.cn/problems/maximum-number-of-darts-inside-of-a-circular-dartboard)

[English Version](/solution/1400-1499/1453.Maximum%20Number%20of%20Darts%20Inside%20of%20a%20Circular%20Dartboard/README_EN.md)

## 题目描述

<!-- description:start -->

<p>Alice 向一面非常大的墙上掷出 <code>n</code> 支飞镖。给你一个数组 <code>darts</code> ，其中 <code>darts[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> 表示 Alice 掷出的第 <code>i</code> 支飞镖落在墙上的位置。</p>

<p>Bob 知道墙上所有 <code>n</code> 支飞镖的位置。他想要往墙上放置一个半径为 <code>r</code> 的圆形靶。使 Alice 掷出的飞镖尽可能多地落在靶上。</p>

<p>给你整数 <code>r</code> ，请返回能够落在 <strong>任意</strong> 半径为 <code>r</code> 的圆形靶内或靶上的最大飞镖数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1 ：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1453.Maximum%20Number%20of%20Darts%20Inside%20of%20a%20Circular%20Dartboard/images/sample_1_1806.png" style="width: 248px; height: 211px;" />
<pre>
<strong>输入：</strong>darts = [[-2,0],[2,0],[0,2],[0,-2]], r = 2
<strong>输出：</strong>4
<strong>解释：</strong>如果圆形靶的圆心为 (0,0) ，半径为 2 ，所有的飞镖都落在靶上，此时落在靶上的飞镖数最大，值为 4 。
</pre>

<p><strong class="example">示例 2 ：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1453.Maximum%20Number%20of%20Darts%20Inside%20of%20a%20Circular%20Dartboard/images/sample_2_1806.png" style="width: 306px; height: 244px;" />
<pre>
<strong>输入：</strong>darts = [[-3,0],[3,0],[2,6],[5,4],[0,9],[7,8]], r = 5
<strong>输出：</strong>5
<strong>解释：</strong>如果圆形靶的圆心为 (0,4) ，半径为 5 ，则除了 (7,8) 之外的飞镖都落在靶上，此时落在靶上的飞镖数最大，值为 5 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= darts.length &lt;= 100</code></li>
	<li><code>darts[i].length == 2</code></li>
	<li><code>-10<sup>4</sup> &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
	<li><code>darts</code> 中的元素互不相同</li>
	<li><code>1 &lt;= r &lt;= 5000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> $n\le 100$，覆盖最多点的半径为 $r$ 的圆，其最优位置往往使至少两点落在圆周上。枚举点对，若距离不超过 $2r$，可求出两个候选圆心，再数有多少点落入圆内。
>
> 单点答案至少为 $1$。浮点比较时对半径加微小误差以免边界漏计。

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numPoints(self, darts: list[list[int]], r: int) -> int:
        def countDarts(x, y):
            count = 0
            for x1, y1 in darts:
                if dist((x, y), (x1, y1)) <= r + 1e-7:
                    count += 1
            return count

        def possibleCenters(x1, y1, x2, y2):
            dx, dy = x2 - x1, y2 - y1
            d = sqrt(dx * dx + dy * dy)
            if d > 2 * r:
                return []
            mid_x, mid_y = (x1 + x2) / 2, (y1 + y2) / 2
            dist_to_center = sqrt(r * r - (d / 2) * (d / 2))
            offset_x = dist_to_center * dy / d
            offset_y = dist_to_center * -dx / d
            return [
                (mid_x + offset_x, mid_y + offset_y),
                (mid_x - offset_x, mid_y - offset_y),
            ]

        n = len(darts)
        max_darts = 1

        for i in range(n):
            for j in range(i + 1, n):
                centers = possibleCenters(
                    darts[i][0], darts[i][1], darts[j][0], darts[j][1]
                )
                for center in centers:
                    max_darts = max(max_darts, countDarts(center[0], center[1]))

        return max_darts
```

#### Java

```java
class Solution {
    public int numPoints(int[][] darts, int r) {
        int n = darts.length;
        int maxDarts = 1;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                List<double[]> centers
                    = possibleCenters(darts[i][0], darts[i][1], darts[j][0], darts[j][1], r);
                for (double[] center : centers) {
                    maxDarts = Math.max(maxDarts, countDarts(center[0], center[1], darts, r));
                }
            }
        }
        return maxDarts;
    }

    private List<double[]> possibleCenters(int x1, int y1, int x2, int y2, int r) {
        List<double[]> centers = new ArrayList<>();
        double dx = x2 - x1;
        double dy = y2 - y1;
        double d = Math.sqrt(dx * dx + dy * dy);
        if (d > 2 * r) {
            return centers;
        }
        double midX = (x1 + x2) / 2.0;
        double midY = (y1 + y2) / 2.0;
        double distToCenter = Math.sqrt(r * r - (d / 2.0) * (d / 2.0));
        double offsetX = distToCenter * dy / d;
        double offsetY = distToCenter * -dx / d;

        centers.add(new double[] {midX + offsetX, midY + offsetY});
        centers.add(new double[] {midX - offsetX, midY - offsetY});
        return centers;
    }

    private int countDarts(double x, double y, int[][] darts, int r) {
        int count = 0;
        for (int[] dart : darts) {
            if (Math.sqrt(Math.pow(dart[0] - x, 2) + Math.pow(dart[1] - y, 2)) <= r + 1e-7) {
                count++;
            }
        }
        return count;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numPoints(vector<vector<int>>& darts, int r) {
        int n = darts.size();
        int maxDarts = 1;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                auto centers = possibleCenters(darts[i][0], darts[i][1], darts[j][0], darts[j][1], r);
                for (auto& c : centers) {
                    maxDarts = max(maxDarts, countDarts(c[0], c[1], darts, r));
                }
            }
        }
        return maxDarts;
    }

private:
    vector<array<double, 2>> possibleCenters(int x1, int y1, int x2, int y2, int r) {
        vector<array<double, 2>> centers;
        double dx = x2 - x1, dy = y2 - y1;
        double d = sqrt(dx * dx + dy * dy);
        if (d > 2.0 * r) {
            return centers;
        }
        double midX = (x1 + x2) / 2.0, midY = (y1 + y2) / 2.0;
        double distToCenter = sqrt(1.0 * r * r - (d / 2.0) * (d / 2.0));
        double offsetX = distToCenter * dy / d;
        double offsetY = distToCenter * -dx / d;
        centers.push_back({midX + offsetX, midY + offsetY});
        centers.push_back({midX - offsetX, midY - offsetY});
        return centers;
    }

    int countDarts(double x, double y, vector<vector<int>>& darts, int r) {
        int count = 0;
        for (auto& dart : darts) {
            if (hypot(dart[0] - x, dart[1] - y) <= r + 1e-7) {
                ++count;
            }
        }
        return count;
    }
};
```

#### Go

```go
func numPoints(darts [][]int, r int) int {
	n := len(darts)
	maxDarts := 1
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			for _, c := range possibleCenters(darts[i][0], darts[i][1], darts[j][0], darts[j][1], r) {
				maxDarts = max(maxDarts, countDarts(c[0], c[1], darts, r))
			}
		}
	}
	return maxDarts
}

func possibleCenters(x1, y1, x2, y2, r int) [][2]float64 {
	dx := float64(x2 - x1)
	dy := float64(y2 - y1)
	d := math.Sqrt(dx*dx + dy*dy)
	if d > 2*float64(r) {
		return nil
	}
	midX := float64(x1+x2) / 2
	midY := float64(y1+y2) / 2
	distToCenter := math.Sqrt(float64(r*r) - (d/2)*(d/2))
	offsetX := distToCenter * dy / d
	offsetY := distToCenter * -dx / d
	return [][2]float64{{midX + offsetX, midY + offsetY}, {midX - offsetX, midY - offsetY}}
}

func countDarts(x, y float64, darts [][]int, r int) (count int) {
	for _, dart := range darts {
		if math.Hypot(float64(dart[0])-x, float64(dart[1])-y) <= float64(r)+1e-7 {
			count++
		}
	}
	return
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
