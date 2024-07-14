---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1401.Circle%20and%20Rectangle%20Overlapping/README.md
rating: 1708
source: 第 23 场双周赛 Q3
tags:
    - 几何
    - 数学
---

<!-- problem:start -->

# [1401. 圆和矩形是否有重叠](https://leetcode.cn/problems/circle-and-rectangle-overlapping)

[English Version](/solution/1400-1499/1401.Circle%20and%20Rectangle%20Overlapping/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个以 <code>(radius, xCenter, yCenter)</code> 表示的圆和一个与坐标轴平行的矩形 <code>(x1, y1, x2, y2)</code> ，其中 <code>(x1, y1)</code> 是矩形左下角的坐标，而 <code>(x2, y2)</code> 是右上角的坐标。</p>

<p>如果圆和矩形有重叠的部分，请你返回 <code>true</code> ，否则返回 <code>false</code>&nbsp;。</p>

<p>换句话说，请你检测是否 <strong>存在</strong> 点 <code>(x<sub>i</sub>, y<sub>i</sub>)</code> ，它既在圆上也在矩形上（两者都包括点落在边界上的情况）。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1 ：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1401.Circle%20and%20Rectangle%20Overlapping/images/sample_4_1728.png" style="width: 258px; height: 167px;" />
<pre>
<strong>输入：</strong>radius = 1, xCenter = 0, yCenter = 0, x1 = 1, y1 = -1, x2 = 3, y2 = 1
<strong>输出：</strong>true
<strong>解释：</strong>圆和矩形存在公共点 (1,0) 。
</pre>

<p><strong class="example">示例 2 ：</strong></p>

<pre>
<strong>输入：</strong>radius = 1, xCenter = 1, yCenter = 1, x1 = 1, y1 = -3, x2 = 2, y2 = -1
<strong>输出：</strong>false
</pre>

<p><strong class="example">示例 3 ：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1401.Circle%20and%20Rectangle%20Overlapping/images/sample_2_1728.png" style="width: 150px; height: 135px;" />
<pre>
<strong>输入：</strong>radius = 1, xCenter = 0, yCenter = 0, x1 = -1, y1 = 0, x2 = 0, y2 = 1
<strong>输出：</strong>true
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= radius &lt;= 2000</code></li>
	<li><code>-10<sup>4</sup> &lt;= xCenter, yCenter &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= x1 &lt; x2 &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= y1 &lt; y2 &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数学

对于一个点 $(x, y)$，它到圆心 $(xCenter, yCenter)$ 的最短距离为 $\sqrt{(x - xCenter)^2 + (y - yCenter)^2}$，如果这个距离小于等于半径 $radius$，那么这个点就在圆内（包括边界）。

而对于矩形内（包括边界）的点，它们的横坐标 $x$ 满足 $x_1 \leq x \leq x_2$，纵坐标 $y$ 满足 $y_1 \leq y \leq y_2$。要判断圆和矩形是否有重叠的部分，相当于在矩形内找到一个点 $(x, y)$，使得 $a = |x - xCenter|$ 和 $b = |y - yCenter|$ 都取到最小值，此时若 $a^2 + b^2 \leq radius^2$，则说明圆和矩形有重叠的部分。

因此，问题转化为求 $x \in [x_1, x_2]$ 时 $a = |x - xCenter|$ 的最小值，以及 $y \in [y_1, y_2]$ 时 $b = |y - yCenter|$ 的最小值。

对于 $x \in [x_1, x_2]$：

-   如果 $x_1 \leq xCenter \leq x_2$，那么 $|x - xCenter|$ 的最小值为 $0$；
-   如果 $xCenter \lt x_1$，那么 $|x - xCenter|$ 的最小值为 $x_1 - xCenter$；
-   如果 $xCenter \gt x_2$，那么 $|x - xCenter|$ 的最小值为 $xCenter - x_2$。

同理，我们可以求出 $y \in [y_1, y_2]$ 时 $|y - yCenter|$ 的最小值。以上我们可以统一用函数 $f(i, j, k)$ 来处理。

即 $a = f(x_1, x_2, xCenter)$, $b = f(y_1, y_2, yCenter)$，如果 $a^2 + b^2 \leq radius^2$，则说明圆和矩形有重叠的部分。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def checkOverlap(
        self,
        radius: int,
        xCenter: int,
        yCenter: int,
        x1: int,
        y1: int,
        x2: int,
        y2: int,
    ) -> bool:
        def f(i: int, j: int, k: int) -> int:
            if i <= k <= j:
                return 0
            return i - k if k < i else k - j

        a = f(x1, x2, xCenter)
        b = f(y1, y2, yCenter)
        return a * a + b * b <= radius * radius
```

#### Java

```java
class Solution {
    public boolean checkOverlap(
        int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        int a = f(x1, x2, xCenter);
        int b = f(y1, y2, yCenter);
        return a * a + b * b <= radius * radius;
    }

    private int f(int i, int j, int k) {
        if (i <= k && k <= j) {
            return 0;
        }
        return k < i ? i - k : k - j;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        auto f = [](int i, int j, int k) -> int {
            if (i <= k && k <= j) {
                return 0;
            }
            return k < i ? i - k : k - j;
        };
        int a = f(x1, x2, xCenter);
        int b = f(y1, y2, yCenter);
        return a * a + b * b <= radius * radius;
    }
};
```

#### Go

```go
func checkOverlap(radius int, xCenter int, yCenter int, x1 int, y1 int, x2 int, y2 int) bool {
	f := func(i, j, k int) int {
		if i <= k && k <= j {
			return 0
		}
		if k < i {
			return i - k
		}
		return k - j
	}
	a := f(x1, x2, xCenter)
	b := f(y1, y2, yCenter)
	return a*a+b*b <= radius*radius
}
```

#### TypeScript

```ts
function checkOverlap(
    radius: number,
    xCenter: number,
    yCenter: number,
    x1: number,
    y1: number,
    x2: number,
    y2: number,
): boolean {
    const f = (i: number, j: number, k: number) => {
        if (i <= k && k <= j) {
            return 0;
        }
        return k < i ? i - k : k - j;
    };
    const a = f(x1, x2, xCenter);
    const b = f(y1, y2, yCenter);
    return a * a + b * b <= radius * radius;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
