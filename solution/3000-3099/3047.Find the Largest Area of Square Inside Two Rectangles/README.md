# [3047. 求交集区域内的最大正方形面积](https://leetcode.cn/problems/find-the-largest-area-of-square-inside-two-rectangles)

[English Version](/solution/3000-3099/3047.Find%20the%20Largest%20Area%20of%20Square%20Inside%20Two%20Rectangles/README_EN.md)

<!-- tags:几何,数组,数学 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>在二维平面上存在 <code>n</code> 个矩形。给你两个下标从 <strong>0</strong> 开始的二维整数数组 <code>bottomLeft</code> 和 <code>topRight</code>，两个数组的大小都是 <code>n x 2</code> ，其中 <code>bottomLeft[i]</code> 和 <code>topRight[i]</code> 分别代表第 <code>i</code> 个矩形的<strong> 左下角 </strong>和 <strong>右上角 </strong>坐标。</p>

<p>我们定义 <strong>向右 </strong>的方向为 x 轴正半轴（<strong>x 坐标增加</strong>），<strong>向左 </strong>的方向为 x 轴负半轴（<strong>x 坐标减少</strong>）。同样地，定义 <strong>向上 </strong>的方向为 y 轴正半轴（<strong>y 坐标增加</strong>）<strong>，向下 </strong>的方向为 y 轴负半轴（<strong>y 坐标减少</strong>）。</p>

<p>你可以选择一个区域，该区域由两个矩形的 <strong>交集</strong>&nbsp;形成。你需要找出能够放入该区域 <strong>内 </strong>的<strong> 最大 </strong>正方形面积，并选择最优解。</p>

<p>返回能够放入交集区域的正方形的 <strong>最大 </strong>可能面积，如果矩形之间不存在任何交集区域，则返回 <code>0</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3047.Find%20the%20Largest%20Area%20of%20Square%20Inside%20Two%20Rectangles/images/example12.png" style="width: 443px; height: 364px; padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem;" />
<pre>
<strong>输入：</strong>bottomLeft = [[1,1],[2,2],[3,1]], topRight = [[3,3],[4,4],[6,6]]
<strong>输出：</strong>1
<strong>解释：</strong>边长为 1 的正方形可以放入矩形 0 和矩形 1 的交集区域，或矩形 1 和矩形 2 的交集区域。因此最大面积是边长 * 边长，即 1 * 1 = 1。
可以证明，边长更大的正方形无法放入任何交集区域。
</pre>

<p><strong class="example">示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3047.Find%20the%20Largest%20Area%20of%20Square%20Inside%20Two%20Rectangles/images/rectanglesexample2.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 445px; height: 365px;" />
<pre>
<strong>输入：</strong>bottomLeft = [[1,1],[2,2],[1,2]], topRight = [[3,3],[4,4],[3,4]]
<strong>输出：</strong>1
<strong>解释：</strong>边长为 1 的正方形可以放入矩形 0 和矩形 1，矩形 1 和矩形 2，或所有三个矩形的交集区域。因此最大面积是边长 * 边长，即 1 * 1 = 1。
可以证明，边长更大的正方形无法放入任何交集区域。
请注意，区域可以由多于两个矩形的交集构成。
</pre>

<p><strong class="example">示例 3：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3047.Find%20the%20Largest%20Area%20of%20Square%20Inside%20Two%20Rectangles/images/rectanglesexample3.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 444px; height: 364px;" />
<pre>
<strong>输入：</strong>bottomLeft = [[1,1],[3,3],[3,1]], topRight = [[2,2],[4,4],[4,2]]
<strong>输出：</strong>0
<strong>解释：</strong>不存在相交的矩形，因此，返回 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == bottomLeft.length == topRight.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>3</sup></code></li>
	<li><code>bottomLeft[i].length == topRight[i].length == 2</code></li>
	<li><code>1 &lt;= bottomLeft[i][0], bottomLeft[i][1] &lt;= 10<sup>7</sup></code></li>
	<li><code>1 &lt;= topRight[i][0], topRight[i][1] &lt;= 10<sup>7</sup></code></li>
	<li><code>bottomLeft[i][0] &lt; topRight[i][0]</code></li>
	<li><code>bottomLeft[i][1] &lt; topRight[i][1]</code></li>
</ul>

## 解法

### 方法一：枚举

我们可以枚举两个矩形，其中矩形 $1$ 的左下角和右上角坐标分别为 $(x_1, y_1)$ 和 $(x_2, y_2)$，矩形 $2$ 的左下角和右上角坐标分别为 $(x_3, y_3)$ 和 $(x_4, y_4)$。

如果矩形 $1$ 和矩形 $2$ 有交集，那么交集的坐标分别为：

-   左下角横坐标是两个矩形左下角横坐标的最大值，即 $\max(x_1, x_3)$；
-   左下角纵坐标是两个矩形左下角纵坐标的最大值，即 $\max(y_1, y_3)$；
-   右上角横坐标是两个矩形右上角横坐标的最小值，即 $\min(x_2, x_4)$；
-   右上角纵坐标是两个矩形右上角纵坐标的最小值，即 $\min(y_2, y_4)$。

那么交集的宽和高分别为 $w = \min(x_2, x_4) - \max(x_1, x_3)$ 和 $h = \min(y_2, y_4) - \max(y_1, y_3)$。我们取两者的最小值作为边长，即 $e = \min(w, h)$，如果 $e > 0$，那么我们就可以得到一个正方形，其面积为 $e^2$，我们取所有正方形的最大面积即可。

时间复杂度 $O(n^2)$，其中 $n$ 是矩形的数量。空间复杂度 $O(1)$。

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
