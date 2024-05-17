---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/16.13.Bisect%20Squares/README.md
---

<!-- problem:start -->

# [面试题 16.13. 平分正方形](https://leetcode.cn/problems/bisect-squares-lcci)

[English Version](/lcci/16.13.Bisect%20Squares/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定两个正方形及一个二维平面。请找出将这两个正方形分割成两半的一条直线。假设正方形顶边和底边与 x 轴平行。</p>
<p>每个正方形的数据<code>square</code>包含3个数值，正方形的左下顶点坐标<code>[X,Y] = [square[0],square[1]]</code>，以及正方形的边长<code>square[2]</code>。所求直线穿过两个正方形会形成4个交点，请返回4个交点形成线段的两端点坐标（两个端点即为4个交点中距离最远的2个点，这2个点所连成的线段一定会穿过另外2个交点）。2个端点坐标<code>[X<sub>1</sub>,Y<sub>1</sub>]</code>和<code>[X<sub>2</sub>,Y<sub>2</sub>]</code>的返回格式为<code>{X<sub>1</sub>,Y<sub>1</sub>,X<sub>2</sub>,Y<sub>2</sub>}</code>，要求若<code>X<sub>1</sub> != X<sub>2</sub></code>，需保证<code>X<sub>1</sub> &lt; X<sub>2</sub></code>，否则需保证<code>Y<sub>1</sub> &lt;= Y<sub>2</sub></code>。</p>
<p>若同时有多条直线满足要求，则选择斜率最大的一条计算并返回（与Y轴平行的直线视为斜率无穷大）。</p>
<p><strong>示例：</strong></p>
<pre><strong>输入：</strong>
square1 = {-1, -1, 2}
square2 = {0, -1, 2}
<strong>输出：</strong> {-1,0,2,0}
<strong>解释：</strong> 直线 y = 0 能将两个正方形同时分为等面积的两部分，返回的两线段端点为[-1,0]和[2,0]
</pre>
<p><strong>提示：</strong></p>
<ul>
	<li><code>square.length == 3</code></li>
	<li><code>square[2] &gt; 0</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：几何数学

我们知道，如果一条直线可以将两个正方形平分，那么这条直线一定会经过两个正方形的中心点。因此，我们可以先求出两个正方形的中心点，分别记为 $(x_1, y_1)$ 和 $(x_2, y_2)$。

如果 $x_1 = x_2$，那么这条直线一定垂直于 $x$ 轴，此时我们只需要求出两个正方形的上下边界的交点即可。

否则，我们可以根据两个中心点求出这条直线的斜率 $k$ 和截距 $b$，然后根据斜率的绝对值的大小，分为两种情况：

-   当 $|k| \gt 1$ 时，经过两个中心点的直线与两个正方形的上下边相交，我们求出上下边的纵坐标的最大值和最小值，然后根据直线方程求出对应的横坐标，即为两个正方形的交点。
-   当 $|k| \le 1$ 时，经过两个中心点的直线与两个正方形的左右边相交，我们求出左右边的横坐标的最大值和最小值，然后根据直线方程求出对应的纵坐标，即为两个正方形的交点。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def cutSquares(self, square1: List[int], square2: List[int]) -> List[float]:
        x1, y1 = square1[0] + square1[2] / 2, square1[1] + square1[2] / 2
        x2, y2 = square2[0] + square2[2] / 2, square2[1] + square2[2] / 2
        if x1 == x2:
            y3 = min(square1[1], square2[1])
            y4 = max(square1[1] + square1[2], square2[1] + square2[2])
            return [x1, y3, x2, y4]
        k = (y2 - y1) / (x2 - x1)
        b = y1 - k * x1
        if abs(k) > 1:
            y3 = min(square1[1], square2[1])
            x3 = (y3 - b) / k
            y4 = max(square1[1] + square1[2], square2[1] + square2[2])
            x4 = (y4 - b) / k
            if x3 > x4 or (x3 == x4 and y3 > y4):
                x3, y3, x4, y4 = x4, y4, x3, y3
        else:
            x3 = min(square1[0], square2[0])
            y3 = k * x3 + b
            x4 = max(square1[0] + square1[2], square2[0] + square2[2])
            y4 = k * x4 + b
        return [x3, y3, x4, y4]
```

#### Java

```java
class Solution {
    public double[] cutSquares(int[] square1, int[] square2) {
        double x1 = square1[0] + square1[2] / 2.0;
        double y1 = square1[1] + square1[2] / 2.0;
        double x2 = square2[0] + square2[2] / 2.0;
        double y2 = square2[1] + square2[2] / 2.0;
        if (x1 == x2) {
            double y3 = Math.min(square1[1], square2[1]);
            double y4 = Math.max(square1[1] + square1[2], square2[1] + square2[2]);
            return new double[] {x1, y3, x2, y4};
        }
        double k = (y2 - y1) / (x2 - x1);
        double b = y1 - k * x1;
        if (Math.abs(k) > 1) {
            double y3 = Math.min(square1[1], square2[1]);
            double x3 = (y3 - b) / k;
            double y4 = Math.max(square1[1] + square1[2], square2[1] + square2[2]);
            double x4 = (y4 - b) / k;
            if (x3 > x4 || (x3 == x4 && y3 > y4)) {
                return new double[] {x4, y4, x3, y3};
            }
            return new double[] {x3, y3, x4, y4};
        } else {
            double x3 = Math.min(square1[0], square2[0]);
            double y3 = k * x3 + b;
            double x4 = Math.max(square1[0] + square1[2], square2[0] + square2[2]);
            double y4 = k * x4 + b;
            return new double[] {x3, y3, x4, y4};
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<double> cutSquares(vector<int>& square1, vector<int>& square2) {
        double x1 = square1[0] + square1[2] / 2.0;
        double y1 = square1[1] + square1[2] / 2.0;
        double x2 = square2[0] + square2[2] / 2.0;
        double y2 = square2[1] + square2[2] / 2.0;
        if (x1 == x2) {
            double y3 = min(square1[1], square2[1]);
            double y4 = max(square1[1] + square1[2], square2[1] + square2[2]);
            return {x1, y3, x2, y4};
        }
        double k = (y2 - y1) / (x2 - x1);
        double b = y1 - k * x1;
        if (abs(k) > 1) {
            double y3 = min(square1[1], square2[1]);
            double x3 = (y3 - b) / k;
            double y4 = max(square1[1] + square1[2], square2[1] + square2[2]);
            double x4 = (y4 - b) / k;
            if (x3 > x4 || (x3 == x4 && y3 > y4)) {
                return {x4, y4, x3, y3};
            }
            return {x3, y3, x4, y4};
        } else {
            double x3 = min(square1[0], square2[0]);
            double y3 = k * x3 + b;
            double x4 = max(square1[0] + square1[2], square2[0] + square2[2]);
            double y4 = k * x4 + b;
            return {x3, y3, x4, y4};
        }
    }
};
```

#### Go

```go
func cutSquares(square1 []int, square2 []int) []float64 {
	x1, y1 := float64(square1[0])+float64(square1[2])/2, float64(square1[1])+float64(square1[2])/2
	x2, y2 := float64(square2[0])+float64(square2[2])/2, float64(square2[1])+float64(square2[2])/2
	if x1 == x2 {
		y3 := math.Min(float64(square1[1]), float64(square2[1]))
		y4 := math.Max(float64(square1[1]+square1[2]), float64(square2[1]+square2[2]))
		return []float64{x1, y3, x2, y4}
	}
	k := (y2 - y1) / (x2 - x1)
	b := y1 - k*x1
	if math.Abs(k) > 1 {
		y3 := math.Min(float64(square1[1]), float64(square2[1]))
		x3 := (y3 - b) / k
		y4 := math.Max(float64(square1[1]+square1[2]), float64(square2[1]+square2[2]))
		x4 := (y4 - b) / k
		if x3 > x4 || (x3 == x4 && y3 > y4) {
			return []float64{x4, y4, x3, y3}
		}
		return []float64{x3, y3, x4, y4}
	} else {
		x3 := math.Min(float64(square1[0]), float64(square2[0]))
		y3 := k*x3 + b
		x4 := math.Max(float64(square1[0]+square1[2]), float64(square2[0]+square2[2]))
		y4 := k*x4 + b
		return []float64{x3, y3, x4, y4}
	}
}
```

#### TypeScript

```ts
function cutSquares(square1: number[], square2: number[]): number[] {
    const x1 = square1[0] + square1[2] / 2;
    const y1 = square1[1] + square1[2] / 2;
    const x2 = square2[0] + square2[2] / 2;
    const y2 = square2[1] + square2[2] / 2;
    if (x1 === x2) {
        const y3 = Math.min(square1[1], square2[1]);
        const y4 = Math.max(square1[1] + square1[2], square2[1] + square2[2]);
        return [x1, y3, x2, y4];
    }
    const k = (y2 - y1) / (x2 - x1);
    const b = y1 - k * x1;
    if (Math.abs(k) > 1) {
        const y3 = Math.min(square1[1], square2[1]);
        const x3 = (y3 - b) / k;
        const y4 = Math.max(square1[1] + square1[2], square2[1] + square2[2]);
        const x4 = (y4 - b) / k;
        if (x3 > x4 || (x3 === x4 && y3 > y4)) {
            return [x4, y4, x3, y3];
        }
        return [x3, y3, x4, y4];
    } else {
        const x3 = Math.min(square1[0], square2[0]);
        const y3 = k * x3 + b;
        const x4 = Math.max(square1[0] + square1[2], square2[0] + square2[2]);
        const y4 = k * x4 + b;
        return [x3, y3, x4, y4];
    }
}
```

#### Swift

```swift
class Solution {
    func cutSquares(_ square1: [Int], _ square2: [Int]) -> [Double] {
        let x1 = Double(square1[0]) + Double(square1[2]) / 2.0
        let y1 = Double(square1[1]) + Double(square1[2]) / 2.0
        let x2 = Double(square2[0]) + Double(square2[2]) / 2.0
        let y2 = Double(square2[1]) + Double(square2[2]) / 2.0

        if x1 == x2 {
            let y3 = min(Double(square1[1]), Double(square2[1]))
            let y4 = max(Double(square1[1]) + Double(square1[2]), Double(square2[1]) + Double(square2[2]))
            return [x1, y3, x2, y4]
        }

        let k = (y2 - y1) / (x2 - x1)
        let b = y1 - k * x1

        if abs(k) > 1 {
            let y3 = min(Double(square1[1]), Double(square2[1]))
            let x3 = (y3 - b) / k
            let y4 = max(Double(square1[1]) + Double(square1[2]), Double(square2[1]) + Double(square2[2]))
            let x4 = (y4 - b) / k
            if x3 > x4 || (x3 == x4 && y3 > y4) {
                return [x4, y4, x3, y3]
            }
            return [x3, y3, x4, y4]
        } else {
            let x3 = min(Double(square1[0]), Double(square2[0]))
            let y3 = k * x3 + b
            let x4 = max(Double(square1[0]) + Double(square1[2]), Double(square2[0]) + Double(square2[2]))
            let y4 = k * x4 + b
            return [x3, y3, x4, y4]
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
