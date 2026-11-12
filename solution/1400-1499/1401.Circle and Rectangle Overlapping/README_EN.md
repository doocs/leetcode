---
comments: true
difficulty: Medium
rating: 1708
source: Biweekly Contest 23 Q3
tags:
    - Geometry
    - Math
---

<!-- problem:start -->

# [1401. Circle and Rectangle Overlapping](https://leetcode.com/problems/circle-and-rectangle-overlapping)

[中文文档](/solution/1400-1499/1401.Circle%20and%20Rectangle%20Overlapping/README.md)

## Description

<!-- description:start -->

<p>You are given a circle represented by its radius <code>radius</code> and center coordinates <code>(xCenter, yCenter)</code>. You are also given an axis-aligned rectangle represented by <code>(x1, y1, x2, y2)</code>, where <code>(x1, y1)</code> is the bottom-left corner and <code>(x2, y2)</code> is the top-right corner of the rectangle.</p>

<p>Return <code>true</code> if the circle and rectangle overlap; otherwise, return <code>false</code>.</p>

<p>Two shapes overlap if they share at least one point. Points on the boundaries are included, so touching at a single point also counts as overlapping.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1401.Circle%20and%20Rectangle%20Overlapping/images/sample_4_1728.png" style="width: 258px; height: 167px;" />
<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">radius = 1, xCenter = 0, yCenter = 0, x1 = 1, y1 = -1, x2 = 3, y2 = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The circle is centered at <code>(0, 0)</code> with radius <code>1</code>, and the rectangle extends from <code>(1, -1)</code> to <code>(3, 1)</code>.</li>
	<li>The circle and rectangle touch at the point <code>(1, 0)</code>, which lies on the circle&#39;s boundary and the rectangle&#39;s left edge.</li>
	<li>Since they share at least one point, they overlap. Therefore, return <code>true</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">radius = 1, xCenter = 1, yCenter = 1, x1 = 1, y1 = -3, x2 = 2, y2 = -1</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The circle is centered at <code>(1, 1)</code> with radius <code>1</code>, while the rectangle extends from <code>(1, -3)</code> to <code>(2, -1)</code>.</li>
	<li>The closest point on the rectangle to the circle&#39;s center is <code>(1, -1)</code>. The distance between these points is <code>2</code>, which is greater than the circle&#39;s radius of <code>1</code>.</li>
	<li>Since the circle cannot reach the rectangle, they do not overlap. Therefore, return <code>false</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1401.Circle%20and%20Rectangle%20Overlapping/images/sample_2_1728.png" style="width: 150px; height: 135px;" />
<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">radius = 1, xCenter = 0, yCenter = 0, x1 = -1, y1 = 0, x2 = 0, y2 = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The circle is centered at <code>(0, 0)</code> with radius <code>1</code>, and the rectangle extends from <code>(-1, 0)</code> to <code>(0, 1)</code>.</li>
	<li>The point <code>(0, 0)</code> is both the center of the circle and the bottom-right corner of the rectangle.</li>
	<li>Since this point belongs to both shapes, they overlap. Therefore, return <code>true</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= radius &lt;= 2000</code></li>
	<li><code>-10<sup>4</sup> &lt;= xCenter, yCenter &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= x1 &lt; x2 &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= y1 &lt; y2 &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Mathematics

<!-- thinking:start -->

> **Thinking**
>
> Sampling grid points or checking only the four corners misses edge and interior intersections. Coordinates go to $10^4$, so we need an exact geometric test.
>
> The circle meets the rectangle iff the closest point of the rectangle to the center lies inside the circle. That point's $x$ and $y$ can be clamped independently to $[x_1,x_2]$ and $[y_1,y_2]$.
>
> A coordinate that already lies in its interval contributes distance $0$; otherwise we take the nearer endpoint. Compare the sum of squared distances with $\textit{radius}^2$.

<!-- thinking:end -->

For a point $(x, y)$, its shortest distance to the center of the circle $(\textit{xCenter}, \textit{yCenter})$ is $\sqrt{(x - \textit{xCenter})^2 + (y - \textit{yCenter})^2}$. If this distance is less than or equal to the radius $\textit{radius}$, then this point is within the circle (including the boundary).

For points within the rectangle (including the boundary), their x-coordinates $x$ satisfy $x_1 \leq x \leq x_2$, and their y-coordinates $y$ satisfy $y_1 \leq y \leq y_2$. To determine whether the circle and rectangle overlap, we need to find a point $(x, y)$ within the rectangle such that $a = |x - \textit{xCenter}|$ and $b = |y - \textit{yCenter}|$ are minimized. If $a^2 + b^2 \leq \textit{radius}^2$, then the circle and rectangle overlap.

Therefore, the problem is transformed into finding the minimum value of $a = |x - \textit{xCenter}|$ when $x \in [x_1, x_2]$, and the minimum value of $b = |y - \textit{yCenter}|$ when $y \in [y_1, y_2]$.

For $x \in [x_1, x_2]$:

- If $x_1 \leq \textit{xCenter} \leq x_2$, then the minimum value of $|x - \textit{xCenter}|$ is $0$;
- If $\textit{xCenter} < x_1$, then the minimum value of $|x - \textit{xCenter}|$ is $x_1 - \textit{xCenter}$;
- If $\textit{xCenter} > x_2$, then the minimum value of $|x - \textit{xCenter}|$ is $\textit{xCenter} - x_2$.

Similarly, we can find the minimum value of $|y - \textit{yCenter}|$ when $y \in [y_1, y_2]$. We can use a function $f(i, j, k)$ to handle the above situations.

That is, $a = f(x_1, x_2, \textit{xCenter})$, $b = f(y_1, y_2, \textit{yCenter})$. If $a^2 + b^2 \leq \textit{radius}^2$, then the circle and rectangle overlap.

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
