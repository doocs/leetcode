---
comment: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/16.13.Bisect%20Squares/README_EN.md
---

# [16.13. Bisect Squares](https://leetcode.cn/problems/bisect-squares-lcci)

[中文文档](/lcci/16.13.Bisect%20Squares/README.md)

## Description

<p>Given two squares on a two-dimensional plane, find a line that would cut these two squares in half. Assume that the top and the bottom sides of the square run parallel to the x-axis.</p>
<p>Each square consists of three values,&nbsp;the coordinate of bottom left corner&nbsp;<code>[X,Y] = [square[0],square[1]]</code>, and the side length&nbsp;of the square <code>square[2]</code>. The line will intersect to the two squares in four points. Return the coordinates of two intersection points <code>[X<sub>1</sub>,Y<sub>1</sub>]</code>&nbsp;and&nbsp;<code>[X<sub>2</sub>,Y<sub>2</sub>]</code>&nbsp;that the forming segment covers the other two intersection points in format of <code>{X<sub>1</sub>,Y<sub>1</sub>,X<sub>2</sub>,Y<sub>2</sub>}</code>. If <code>X<sub>1</sub> != X<sub>2</sub></code>, there should be&nbsp;<code>X<sub>1</sub> &lt; X<sub>2</sub></code>, otherwise there should be&nbsp;<code>Y<sub>1</sub> &lt;= Y<sub>2</sub></code>.</p>
<p>If there are more than one line that can cut these two squares in half, return the one that has biggest slope (slope of a line parallel to the y-axis is considered as infinity).</p>
<p><strong>Example: </strong></p>
<pre>

<strong>Input: </strong>

square1 = {-1, -1, 2}

square2 = {0, -1, 2}

<strong>Output:</strong> {-1,0,2,0}

<strong>Explanation:</strong> y = 0 is the line that can cut these two squares in half.

</pre>
<p><strong>Note: </strong></p>
<ul>
	<li><code>square.length == 3</code></li>
	<li><code>square[2] &gt; 0</code></li>
</ul>

## Solutions

### Solution 1: Geometric Mathematics

We know that if a line can bisect two squares, then the line must pass through the centers of the two squares. Therefore, we can first calculate the centers of the two squares, denoted as $(x_1, y_1)$ and $(x_2, y_2)$, respectively.

If $x_1 = x_2$, then the line is perpendicular to the $x$-axis, and we only need to find the intersection point of the top and bottom edges of the two squares.

Otherwise, we can calculate the slope $k$ and the intercept $b$ of the line passing through the two centers, and then divide into two cases based on the absolute value of the slope:

-   When $|k| \gt 1$, the line passing through the two centers intersects with the top and bottom edges of the two squares. We calculate the maximum and minimum values of the vertical coordinates of the top and bottom edges, and then calculate the corresponding horizontal coordinate using the equation of the line, which is the intersection point of the two squares.
-   When $|k| \le 1$, the line passing through the two centers intersects with the left and right edges of the two squares. We calculate the maximum and minimum values of the horizontal coordinates of the left and right edges, and then calculate the corresponding vertical coordinate using the equation of the line, which is the intersection point of the two squares.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

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

<!-- end -->
