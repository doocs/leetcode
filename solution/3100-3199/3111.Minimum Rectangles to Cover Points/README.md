---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3111.Minimum%20Rectangles%20to%20Cover%20Points/README.md
rating: 1401
source: 第 128 场双周赛 Q2
tags:
    - 贪心
    - 数组
    - 排序
---

<!-- problem:start -->

# [3111. 覆盖所有点的最少矩形数目](https://leetcode.cn/problems/minimum-rectangles-to-cover-points)

[English Version](/solution/3100-3199/3111.Minimum%20Rectangles%20to%20Cover%20Points/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个二维整数数组&nbsp;<code>point</code>&nbsp;，其中&nbsp;<code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code>&nbsp;表示二维平面内的一个点。同时给你一个整数&nbsp;<code>w</code>&nbsp;。你需要用矩形&nbsp;<strong>覆盖所有</strong>&nbsp;点。</p>

<p>每个矩形的左下角在某个点&nbsp;<code>(x<sub>1</sub>, 0)</code>&nbsp;处，且右上角在某个点&nbsp;<code>(x<sub>2</sub>, y<sub>2</sub>)</code>&nbsp;处，其中&nbsp;<code>x<sub>1</sub> &lt;= x<sub>2</sub></code>&nbsp;且&nbsp;<code>y<sub>2</sub> &gt;= 0</code>&nbsp;，同时对于每个矩形都&nbsp;<strong>必须</strong>&nbsp;满足&nbsp;<code>x<sub>2</sub> - x<sub>1</sub> &lt;= w</code>&nbsp;。</p>

<p>如果一个点在矩形内或者在边上，我们说这个点被矩形覆盖了。</p>

<p>请你在确保每个点都 <strong>至少</strong>&nbsp;被一个矩形覆盖的前提下，<strong>最少</strong>&nbsp;需要多少个矩形。</p>

<p><strong>注意：</strong>一个点可以被多个矩形覆盖。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3111.Minimum%20Rectangles%20to%20Cover%20Points/images/screenshot-from-2024-03-04-20-33-05.png" style="width: 205px; height: 300px;" /></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p style=""><span class="example-io" style="font-size: 8.75px;"><b>输入：</b></span><span class="example-io" style="font-size: 0.85rem; font-family: Menlo, sans-serif;">points = [[2,1],[1,0],[1,4],[1,8],[3,5],[4,6]], w = 1</span></p>

<p style=""><span class="example-io" style="font-size: 8.75px;"><b>输出：</b></span><span class="example-io" style="font-size: 0.85rem; font-family: Menlo, sans-serif;">2</span></p>

<p style="font-size: 0.875rem;"><strong>解释：</strong></p>

<p style="font-size: 0.875rem;">上图展示了一种可行的矩形放置方案：</p>

<ul style="font-size: 0.875rem;">
	<li>一个矩形的左下角在&nbsp;<code>(1, 0)</code>&nbsp;，右上角在&nbsp;<code>(2, 8)</code>&nbsp;。</li>
	<li>一个矩形的左下角在&nbsp;<code>(3, 0)</code>&nbsp;，右上角在&nbsp;<code>(4, 8)</code>&nbsp;。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3111.Minimum%20Rectangles%20to%20Cover%20Points/images/screenshot-from-2024-03-04-18-59-12.png" style="width: 260px; height: 250px;" /></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p style=""><span class="example-io" style="font-size: 8.75px;"><b>输入：</b></span><span class="example-io" style="font-size: 0.85rem; font-family: Menlo, sans-serif;">points = [[0,0],[1,1],[2,2],[3,3],[4,4],[5,5],[6,6]], w = 2</span></p>

<p style=""><span class="example-io" style="font-size: 8.75px;"><b>输出：</b></span><span class="example-io" style="font-size: 0.85rem; font-family: Menlo, sans-serif;">3</span></p>

<p style="font-size: 0.875rem;"><b>解释：</b></p>

<p style="font-size: 0.875rem;">上图展示了一种可行的矩形放置方案：</p>

<ul style="font-size: 0.875rem;">
	<li>一个矩形的左下角在&nbsp;<code>(0, 0)</code>&nbsp;，右上角在&nbsp;<code>(2, 2)</code>&nbsp;。</li>
	<li>一个矩形的左下角在&nbsp;<code>(3, 0)</code>&nbsp;，右上角在&nbsp;<code>(5, 5)</code>&nbsp;。</li>
	<li>一个矩形的左下角在&nbsp;<code>(6, 0)</code>&nbsp;，右上角在&nbsp;<code>(6, 6)</code>&nbsp;。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3111.Minimum%20Rectangles%20to%20Cover%20Points/images/screenshot-from-2024-03-04-20-24-03.png" style="height: 150px; width: 127px;" /></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p style=""><span class="example-io" style="font-size: 8.75px;"><b>输入：</b></span><span class="example-io" style="font-size: 0.85rem; font-family: Menlo, sans-serif;">points = [[2,3],[1,2]], w = 0</span></p>

<p style=""><span class="example-io" style="font-size: 8.75px;"><b>输出：</b></span><span class="example-io" style="font-size: 0.85rem; font-family: Menlo, sans-serif;">2</span></p>

<p style="font-size: 0.875rem;"><strong>解释：</strong></p>

<p style="font-size: 0.875rem;">上图展示了一种可行的矩形放置方案：</p>

<ul style="font-size: 0.875rem;">
	<li>一个矩形的左下角在&nbsp;<code>(1, 0)</code>&nbsp;，右上角在&nbsp;<code>(1, 2)</code>&nbsp;。</li>
	<li>一个矩形的左下角在&nbsp;<code>(2, 0)</code>&nbsp;，右上角在&nbsp;<code>(2, 3)</code>&nbsp;。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= points.length &lt;= 10<sup>5</sup></code></li>
	<li><code>points[i].length == 2</code></li>
	<li><code>0 &lt;= x<sub>i</sub> == points[i][0] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= y<sub>i</sub> == points[i][1] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= w &lt;= 10<sup>9</sup></code></li>
	<li>所有点坐标&nbsp;<code>(x<sub>i</sub>, y<sub>i</sub>)</code>&nbsp;互不相同。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 排序

根据题目描述，我们不需要考虑矩形的高度，只需要考虑矩形的宽度。

我们可以将所有的点按照横坐标进行排序，用一个变量 $x_1$ 记录当前矩形所能覆盖的最右边的横坐标，初始时 $x_1 = -1$。

接下来我们遍历所有的点，如果当前点的横坐标 $x$ 大于 $x_1$，说明已有的矩形无法覆盖当前点，我们就需要增加一个矩形，答案加一，然后我们更新 $x_1 = x + w$。

遍历完成后，我们就得到了最少需要的矩形数目。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 是点的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minRectanglesToCoverPoints(self, points: List[List[int]], w: int) -> int:
        points.sort()
        ans, x1 = 0, -1
        for x, _ in points:
            if x > x1:
                ans += 1
                x1 = x + w
        return ans
```

#### Java

```java
class Solution {
    public int minRectanglesToCoverPoints(int[][] points, int w) {
        Arrays.sort(points, (a, b) -> a[0] - b[0]);
        int ans = 0, x1 = -1;
        for (int[] p : points) {
            int x = p[0];
            if (x > x1) {
                ++ans;
                x1 = x + w;
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
    int minRectanglesToCoverPoints(vector<vector<int>>& points, int w) {
        sort(points.begin(), points.end());
        int ans = 0, x1 = -1;
        for (const auto& p : points) {
            int x = p[0];
            if (x > x1) {
                ++ans;
                x1 = x + w;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minRectanglesToCoverPoints(points [][]int, w int) (ans int) {
	sort.Slice(points, func(i, j int) bool { return points[i][0] < points[j][0] })
	x1 := -1
	for _, p := range points {
		if x := p[0]; x > x1 {
			ans++
			x1 = x + w
		}
	}
	return
}
```

#### TypeScript

```ts
function minRectanglesToCoverPoints(points: number[][], w: number): number {
    points.sort((a, b) => a[0] - b[0]);
    let [ans, x1] = [0, -1];
    for (const [x, _] of points) {
        if (x > x1) {
            ++ans;
            x1 = x + w;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn min_rectangles_to_cover_points(mut points: Vec<Vec<i32>>, w: i32) -> i32 {
        points.sort_by(|a, b| a[0].cmp(&b[0]));
        let mut ans = 0;
        let mut x1 = -1;
        for p in points {
            let x = p[0];
            if x > x1 {
                ans += 1;
                x1 = x + w;
            }
        }
        ans
    }
}
```

#### C#

```cs
public class Solution {
    public int MinRectanglesToCoverPoints(int[][] points, int w) {
        Array.Sort(points, (a, b) => a[0] - b[0]);
        int ans = 0, x1 = -1;
        foreach (int[] p in points) {
            int x = p[0];
            if (x > x1) {
                ans++;
                x1 = x + w;
            }
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
