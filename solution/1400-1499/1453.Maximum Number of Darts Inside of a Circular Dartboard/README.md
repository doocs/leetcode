---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1453.Maximum%20Number%20of%20Darts%20Inside%20of%20a%20Circular%20Dartboard/README.md
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

```

#### Go

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
