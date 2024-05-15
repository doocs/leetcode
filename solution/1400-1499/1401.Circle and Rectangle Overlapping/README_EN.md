---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1401.Circle%20and%20Rectangle%20Overlapping/README_EN.md
rating: 1708
tags:
    - Geometry
    - Math
---

# [1401. Circle and Rectangle Overlapping](https://leetcode.com/problems/circle-and-rectangle-overlapping)

[中文文档](/solution/1400-1499/1401.Circle%20and%20Rectangle%20Overlapping/README.md)

## Description

<p>You are given a circle represented as <code>(radius, xCenter, yCenter)</code> and an axis-aligned rectangle represented as <code>(x1, y1, x2, y2)</code>, where <code>(x1, y1)</code> are the coordinates of the bottom-left corner, and <code>(x2, y2)</code> are the coordinates of the top-right corner of the rectangle.</p>

<p>Return <code>true</code><em> if the circle and rectangle are overlapped otherwise return </em><code>false</code>. In other words, check if there is <strong>any</strong> point <code>(x<sub>i</sub>, y<sub>i</sub>)</code> that belongs to the circle and the rectangle at the same time.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1401.Circle%20and%20Rectangle%20Overlapping/images/sample_4_1728.png" style="width: 258px; height: 167px;" />
<pre>
<strong>Input:</strong> radius = 1, xCenter = 0, yCenter = 0, x1 = 1, y1 = -1, x2 = 3, y2 = 1
<strong>Output:</strong> true
<strong>Explanation:</strong> Circle and rectangle share the point (1,0).
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> radius = 1, xCenter = 1, yCenter = 1, x1 = 1, y1 = -3, x2 = 2, y2 = -1
<strong>Output:</strong> false
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1401.Circle%20and%20Rectangle%20Overlapping/images/sample_2_1728.png" style="width: 150px; height: 135px;" />
<pre>
<strong>Input:</strong> radius = 1, xCenter = 0, yCenter = 0, x1 = -1, y1 = 0, x2 = 0, y2 = 1
<strong>Output:</strong> true
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= radius &lt;= 2000</code></li>
	<li><code>-10<sup>4</sup> &lt;= xCenter, yCenter &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= x1 &lt; x2 &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= y1 &lt; y2 &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

### Solution 1: Mathematics

For a point $(x, y)$, its shortest distance to the center of the circle $(xCenter, yCenter)$ is $\sqrt{(x - xCenter)^2 + (y - yCenter)^2}$. If this distance is less than or equal to the radius $radius$, then this point is within the circle (including the boundary).

For points within the rectangle (including the boundary), their x-coordinates $x$ satisfy $x_1 \leq x \leq x_2$, and their y-coordinates $y$ satisfy $y_1 \leq y \leq y_2$. To determine whether the circle and rectangle overlap, we need to find a point $(x, y)$ within the rectangle such that $a = |x - xCenter|$ and $b = |y - yCenter|$ are minimized. If $a^2 + b^2 \leq radius^2$, then the circle and rectangle overlap.

Therefore, the problem is transformed into finding the minimum value of $a = |x - xCenter|$ when $x \in [x_1, x_2]$, and the minimum value of $b = |y - yCenter|$ when $y \in [y_1, y_2]$.

For $x \in [x_1, x_2]$:

-   If $x_1 \leq xCenter \leq x_2$, then the minimum value of $|x - xCenter|$ is $0$;
-   If $xCenter < x_1$, then the minimum value of $|x - xCenter|$ is $x_1 - xCenter$;
-   If $xCenter > x_2$, then the minimum value of $|x - xCenter|$ is $xCenter - x_2$.

Similarly, we can find the minimum value of $|y - yCenter|$ when $y \in [y_1, y_2]$. We can use a function $f(i, j, k)$ to handle the above situations.

That is, $a = f(x_1, x_2, xCenter)$, $b = f(y_1, y_2, yCenter)$. If $a^2 + b^2 \leq radius^2$, then the circle and rectangle overlap.

<!-- tabs:start -->

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

<!-- end -->
