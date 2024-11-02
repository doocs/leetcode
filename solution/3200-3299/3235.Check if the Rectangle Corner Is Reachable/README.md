---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3235.Check%20if%20the%20Rectangle%20Corner%20Is%20Reachable/README.md
rating: 3773
source: 第 408 场周赛 Q4
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 并查集
    - 几何
    - 数组
    - 数学
---

<!-- problem:start -->

# [3235. 判断矩形的两个角落是否可达](https://leetcode.cn/problems/check-if-the-rectangle-corner-is-reachable)

[English Version](/solution/3200-3299/3235.Check%20if%20the%20Rectangle%20Corner%20Is%20Reachable/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个正整数&nbsp;<code>xCorner</code> 和&nbsp;<code>yCorner</code>&nbsp;和一个二维整数数组&nbsp;<code>circles</code>&nbsp;，其中&nbsp;<code>circles[i] = [x<sub>i</sub>, y<sub>i</sub>, r<sub>i</sub>]</code>&nbsp;表示一个圆心在&nbsp;<code>(x<sub>i</sub>, y<sub>i</sub>)</code>&nbsp;半径为&nbsp;<code>r<sub>i</sub></code>&nbsp;的圆。</p>

<p>坐标平面内有一个左下角在原点，右上角在&nbsp;<code>(xCorner, yCorner)</code>&nbsp;的矩形。你需要判断是否存在一条从左下角到右上角的路径满足：路径&nbsp;<strong>完全</strong>&nbsp;在矩形内部，<strong>不会</strong>&nbsp;触碰或者经过 <strong>任何</strong>&nbsp;圆的内部和边界，同时&nbsp;<strong>只</strong> 在起点和终点接触到矩形。</p>

<p>如果存在这样的路径，请你返回&nbsp;<code>true</code>&nbsp;，否则返回&nbsp;<code>false</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>X = 3, Y = 4, circles = [[2,1,1]]</span></p>

<p><span class="example-io"><b>输出：</b>true</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3235.Check%20if%20the%20Rectangle%20Corner%20Is%20Reachable/images/example2circle1.png" style="width: 346px; height: 264px;" /></p>

<p>黑色曲线表示一条从&nbsp;<code>(0, 0)</code>&nbsp;到&nbsp;<code>(3, 4)</code>&nbsp;的路径。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>X = 3, Y = 3, circles = [[1,1,2]]</span></p>

<p><span class="example-io"><b>输出：</b>false</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3235.Check%20if%20the%20Rectangle%20Corner%20Is%20Reachable/images/example1circle.png" style="width: 346px; height: 264px;" /></p>

<p>不存在从&nbsp;<code>(0, 0)</code> 到&nbsp;<code>(3, 3)</code>&nbsp;的路径。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>X = 3, Y = 3, circles = [[2,1,1],[1,2,1]]</span></p>

<p><span class="example-io"><b>输出：</b>false</span></p>

<p><b>解释：</b></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3235.Check%20if%20the%20Rectangle%20Corner%20Is%20Reachable/images/example0circle.png" style="width: 346px; height: 264px;" /></p>

<p>不存在从&nbsp;<code>(0, 0)</code>&nbsp;到&nbsp;<code>(3, 3)</code>&nbsp;的路径。</p>
</div>

<p><strong class="example">示例 4：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">X = 4, Y = 4, circles = [[5,5,1]]</span></p>

<p><span class="example-io"><b>输出：</b>true</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3235.Check%20if%20the%20Rectangle%20Corner%20Is%20Reachable/images/rectangles.png" style="width: 346px; height: 264px;" /></p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= xCorner, yCorner&nbsp;&lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= circles.length &lt;= 1000</code></li>
	<li><code>circles[i].length == 3</code></li>
	<li><code>1 &lt;= x<sub>i</sub>, y<sub>i</sub>, r<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canReachCorner(
        self, xCorner: int, yCorner: int, circles: List[List[int]]
    ) -> bool:
        def in_circle(x: int, y: int, cx: int, cy: int, r: int) -> int:
            return (x - cx) ** 2 + (y - cy) ** 2 <= r**2

        def cross_left_top(cx: int, cy: int, r: int) -> bool:
            a = abs(cx) <= r and 0 <= cy <= yCorner
            b = abs(cy - yCorner) <= r and 0 <= cx <= xCorner
            return a or b

        def cross_right_bottom(cx: int, cy: int, r: int) -> bool:
            a = abs(cx - xCorner) <= r and 0 <= cy <= yCorner
            b = abs(cy) <= r and 0 <= cx <= xCorner
            return a or b

        def dfs(i: int) -> bool:
            x1, y1, r1 = circles[i]
            if cross_right_bottom(x1, y1, r1):
                return True
            vis[i] = True
            for j, (x2, y2, r2) in enumerate(circles):
                if vis[j] or not ((x1 - x2) ** 2 + (y1 - y2) ** 2 <= (r1 + r2) ** 2):
                    continue
                if (
                    (x1 * r2 + x2 * r1 < (r1 + r2) * xCorner)
                    and (y1 * r2 + y2 * r1 < (r1 + r2) * yCorner)
                    and dfs(j)
                ):
                    return True
            return False

        vis = [False] * len(circles)
        for i, (x, y, r) in enumerate(circles):
            if in_circle(0, 0, x, y, r) or in_circle(xCorner, yCorner, x, y, r):
                return False
            if (not vis[i]) and cross_left_top(x, y, r) and dfs(i):
                return False
        return True
```

#### Java

```java
class Solution {
    private int[][] circles;
    private int xCorner, yCorner;
    private boolean[] vis;

    public boolean canReachCorner(int xCorner, int yCorner, int[][] circles) {
        int n = circles.length;
        this.circles = circles;
        this.xCorner = xCorner;
        this.yCorner = yCorner;
        vis = new boolean[n];
        for (int i = 0; i < n; ++i) {
            var c = circles[i];
            int x = c[0], y = c[1], r = c[2];
            if (inCircle(0, 0, x, y, r) || inCircle(xCorner, yCorner, x, y, r)) {
                return false;
            }
            if (!vis[i] && crossLeftTop(x, y, r) && dfs(i)) {
                return false;
            }
        }
        return true;
    }

    private boolean inCircle(long x, long y, long cx, long cy, long r) {
        return (x - cx) * (x - cx) + (y - cy) * (y - cy) <= r * r;
    }

    private boolean crossLeftTop(long cx, long cy, long r) {
        boolean a = Math.abs(cx) <= r && (cy >= 0 && cy <= yCorner);
        boolean b = Math.abs(cy - yCorner) <= r && (cx >= 0 && cx <= xCorner);
        return a || b;
    }

    private boolean crossRightBottom(long cx, long cy, long r) {
        boolean a = Math.abs(cx - xCorner) <= r && (cy >= 0 && cy <= yCorner);
        boolean b = Math.abs(cy) <= r && (cx >= 0 && cx <= xCorner);
        return a || b;
    }

    private boolean dfs(int i) {
        var c = circles[i];
        long x1 = c[0], y1 = c[1], r1 = c[2];
        if (crossRightBottom(x1, y1, r1)) {
            return true;
        }
        vis[i] = true;
        for (int j = 0; j < circles.length; ++j) {
            var c2 = circles[j];
            long x2 = c2[0], y2 = c2[1], r2 = c2[2];
            if (vis[j]) {
                continue;
            }
            if ((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) > (r1 + r2) * (r1 + r2)) {
                continue;
            }
            if (x1 * r2 + x2 * r1 < (r1 + r2) * xCorner && y1 * r2 + y2 * r1 < (r1 + r2) * yCorner
                && dfs(j)) {
                return true;
            }
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool canReachCorner(int xCorner, int yCorner, vector<vector<int>>& circles) {
        using ll = long long;
        auto inCircle = [&](ll x, ll y, ll cx, ll cy, ll r) {
            return (x - cx) * (x - cx) + (y - cy) * (y - cy) <= r * r;
        };
        auto crossLeftTop = [&](ll cx, ll cy, ll r) {
            bool a = abs(cx) <= r && (cy >= 0 && cy <= yCorner);
            bool b = abs(cy - yCorner) <= r && (cx >= 0 && cx <= xCorner);
            return a || b;
        };
        auto crossRightBottom = [&](ll cx, ll cy, ll r) {
            bool a = abs(cx - xCorner) <= r && (cy >= 0 && cy <= yCorner);
            bool b = abs(cy) <= r && (cx >= 0 && cx <= xCorner);
            return a || b;
        };

        int n = circles.size();
        vector<bool> vis(n);
        auto dfs = [&](auto&& dfs, int i) -> bool {
            auto c = circles[i];
            ll x1 = c[0], y1 = c[1], r1 = c[2];
            if (crossRightBottom(x1, y1, r1)) {
                return true;
            }
            vis[i] = true;
            for (int j = 0; j < n; ++j) {
                if (vis[j]) {
                    continue;
                }
                auto c2 = circles[j];
                ll x2 = c2[0], y2 = c2[1], r2 = c2[2];
                if ((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) > (r1 + r2) * (r1 + r2)) {
                    continue;
                }
                if (x1 * r2 + x2 * r1 < (r1 + r2) * xCorner && y1 * r2 + y2 * r1 < (r1 + r2) * yCorner
                    && dfs(dfs, j)) {
                    return true;
                }
            }
            return false;
        };

        for (int i = 0; i < n; ++i) {
            auto c = circles[i];
            ll x = c[0], y = c[1], r = c[2];
            if (inCircle(0, 0, x, y, r) || inCircle(xCorner, yCorner, x, y, r)) {
                return false;
            }
            if (!vis[i] && crossLeftTop(x, y, r) && dfs(dfs, i)) {
                return false;
            }
        }
        return true;
    }
};
```

#### Go

```go
func canReachCorner(xCorner int, yCorner int, circles [][]int) bool {
	inCircle := func(x, y, cx, cy, r int) bool {
		dx, dy := x-cx, y-cy
		return dx*dx+dy*dy <= r*r
	}

	crossLeftTop := func(cx, cy, r int) bool {
		a := abs(cx) <= r && cy >= 0 && cy <= yCorner
		b := abs(cy-yCorner) <= r && cx >= 0 && cx <= xCorner
		return a || b
	}

	crossRightBottom := func(cx, cy, r int) bool {
		a := abs(cx-xCorner) <= r && cy >= 0 && cy <= yCorner
		b := abs(cy) <= r && cx >= 0 && cx <= xCorner
		return a || b
	}

	vis := make([]bool, len(circles))

	var dfs func(int) bool
	dfs = func(i int) bool {
		c := circles[i]
		x1, y1, r1 := c[0], c[1], c[2]
		if crossRightBottom(x1, y1, r1) {
			return true
		}
		vis[i] = true
		for j, c2 := range circles {
			if vis[j] {
				continue
			}
			x2, y2, r2 := c2[0], c2[1], c2[2]
			if (x1-x2)*(x1-x2)+(y1-y2)*(y1-y2) > (r1+r2)*(r1+r2) {
				continue
			}
			if x1*r2+x2*r1 < (r1+r2)*xCorner && y1*r2+y2*r1 < (r1+r2)*yCorner && dfs(j) {
				return true
			}
		}
		return false
	}

	for i, c := range circles {
		x, y, r := c[0], c[1], c[2]
		if inCircle(0, 0, x, y, r) || inCircle(xCorner, yCorner, x, y, r) {
			return false
		}
		if !vis[i] && crossLeftTop(x, y, r) && dfs(i) {
			return false
		}
	}
	return true
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function canReachCorner(xCorner: number, yCorner: number, circles: number[][]): boolean {
    const inCircle = (x: bigint, y: bigint, cx: bigint, cy: bigint, r: bigint): boolean => {
        const dx = x - cx;
        const dy = y - cy;
        return dx * dx + dy * dy <= r * r;
    };

    const crossLeftTop = (cx: bigint, cy: bigint, r: bigint): boolean => {
        const a = BigInt(Math.abs(Number(cx))) <= r && cy >= 0n && cy <= BigInt(yCorner);
        const b =
            BigInt(Math.abs(Number(cy - BigInt(yCorner)))) <= r &&
            cx >= 0n &&
            cx <= BigInt(xCorner);
        return a || b;
    };

    const crossRightBottom = (cx: bigint, cy: bigint, r: bigint): boolean => {
        const a =
            BigInt(Math.abs(Number(cx - BigInt(xCorner)))) <= r &&
            cy >= 0n &&
            cy <= BigInt(yCorner);
        const b = BigInt(Math.abs(Number(cy))) <= r && cx >= 0n && cx <= BigInt(xCorner);
        return a || b;
    };

    const n = circles.length;
    const vis: boolean[] = new Array(n).fill(false);

    const dfs = (i: number): boolean => {
        const [x1, y1, r1] = circles[i].map(BigInt);
        if (crossRightBottom(x1, y1, r1)) {
            return true;
        }
        vis[i] = true;
        for (let j = 0; j < n; j++) {
            if (vis[j]) continue;
            const [x2, y2, r2] = circles[j].map(BigInt);
            if ((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) > (r1 + r2) * (r1 + r2)) {
                continue;
            }
            if (
                x1 * r2 + x2 * r1 < (r1 + r2) * BigInt(xCorner) &&
                y1 * r2 + y2 * r1 < (r1 + r2) * BigInt(yCorner) &&
                dfs(j)
            ) {
                return true;
            }
        }
        return false;
    };

    for (let i = 0; i < n; i++) {
        const [x, y, r] = circles[i].map(BigInt);
        if (inCircle(0n, 0n, x, y, r) || inCircle(BigInt(xCorner), BigInt(yCorner), x, y, r)) {
            return false;
        }
        if (!vis[i] && crossLeftTop(x, y, r) && dfs(i)) {
            return false;
        }
    }

    return true;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
