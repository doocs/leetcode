---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0963.Minimum%20Area%20Rectangle%20II/README.md
tags:
    - 几何
    - 数组
    - 哈希表
    - 数学
---

<!-- problem:start -->

# [963. 最小面积矩形 II](https://leetcode.cn/problems/minimum-area-rectangle-ii)

[English Version](/solution/0900-0999/0963.Minimum%20Area%20Rectangle%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个&nbsp;<strong>X-Y&nbsp;</strong>平面上的点数组 <code>points</code>，其中 <code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code>。</p>

<p>返回由这些点形成的任意矩形的最小面积，矩形的边&nbsp;<strong>不一定 </strong>平行于 X 轴和 Y 轴。如果不存在这样的矩形，则返回 <code>0</code>。</p>

<p>答案只需在<code>10<sup>-5</sup></code> 的误差范围内即可被视作正确答案。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0963.Minimum%20Area%20Rectangle%20II/images/1a.png" style="width: 398px; height: 400px;" />
<pre>
<strong>输入：</strong> points = [[1,2],[2,1],[1,0],[0,1]]
<strong>输出：</strong> 2.00000
<strong>解释：</strong> 最小面积矩形由 [1,2]、[2,1]、[1,0]、[0,1] 组成，其面积为 2。
</pre>

<p><strong class="example">示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0963.Minimum%20Area%20Rectangle%20II/images/2.png" style="width: 400px; height: 251px;" />
<pre>
<strong>输入：</strong> points = [[0,1],[2,1],[1,1],[1,0],[2,0]]
<strong>输出：</strong> 1.00000
<strong>解释：</strong> 最小面积矩形由 [1,0]、[1,1]、[2,1]、[2,0] 组成，其面积为 1。
</pre>

<p><strong class="example">示例 3：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0963.Minimum%20Area%20Rectangle%20II/images/3.png" style="width: 383px; height: 400px;" />
<pre>
<strong>输入：</strong> points = [[0,3],[1,2],[3,1],[1,3],[2,1]]
<strong>输出：</strong> 0
<strong>解释：</strong> 无法由这些点组成任何矩形。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= points.length &lt;= 50</code></li>
	<li><code>points[i].length == 2</code></li>
	<li><code>0 &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 4 * 10<sup>4</sup></code></li>
	<li>所有给定的点都是&nbsp;<strong>唯一&nbsp;</strong>的。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 枚举

我们用哈希表存放所有的点，然后枚举三个点 $p_1 = (x_1, y_1)$, $p_2 = (x_2, y_2)$, $p_3 = (x_3, y_3)$，其中 $p_2$ 和 $p_3$ 是矩形的对角线的两个端点。如果 $p_1$ 和 $p_2$ 构成的直线以及 $p_1$ 和 $p_3$ 构成的直线垂直，并且第四个点 $(x_4, y_4)=(x_2 - x_1 + x_3, y_2 - y_1 + y_3)$ 存在于哈希表中，那么就找到了一个矩形。此时，我们可以计算出矩形的面积，并更新答案。

最后，如果找到满足条件的矩形，返回其中面积的最小值。否则，返回 $0$。

时间复杂度 $O(n^3)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $points$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minAreaFreeRect(self, points: List[List[int]]) -> float:
        s = {(x, y) for x, y in points}
        n = len(points)
        ans = inf
        for i in range(n):
            x1, y1 = points[i]
            for j in range(n):
                if j != i:
                    x2, y2 = points[j]
                    for k in range(j + 1, n):
                        if k != i:
                            x3, y3 = points[k]
                            x4 = x2 - x1 + x3
                            y4 = y2 - y1 + y3
                            if (x4, y4) in s:
                                v21 = (x2 - x1, y2 - y1)
                                v31 = (x3 - x1, y3 - y1)
                                if v21[0] * v31[0] + v21[1] * v31[1] == 0:
                                    w = sqrt(v21[0] ** 2 + v21[1] ** 2)
                                    h = sqrt(v31[0] ** 2 + v31[1] ** 2)
                                    ans = min(ans, w * h)
        return 0 if ans == inf else ans
```

#### Java

```java
class Solution {
    public double minAreaFreeRect(int[][] points) {
        int n = points.length;
        Set<Integer> s = new HashSet<>(n);
        for (int[] p : points) {
            s.add(f(p[0], p[1]));
        }
        double ans = Double.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            int x1 = points[i][0], y1 = points[i][1];
            for (int j = 0; j < n; ++j) {
                if (j != i) {
                    int x2 = points[j][0], y2 = points[j][1];
                    for (int k = j + 1; k < n; ++k) {
                        if (k != i) {
                            int x3 = points[k][0], y3 = points[k][1];
                            int x4 = x2 - x1 + x3, y4 = y2 - y1 + y3;
                            if (s.contains(f(x4, y4))) {
                                if ((x2 - x1) * (x3 - x1) + (y2 - y1) * (y3 - y1) == 0) {
                                    int ww = (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
                                    int hh = (x3 - x1) * (x3 - x1) + (y3 - y1) * (y3 - y1);
                                    ans = Math.min(ans, Math.sqrt(1L * ww * hh));
                                }
                            }
                        }
                    }
                }
            }
        }
        return ans == Double.MAX_VALUE ? 0 : ans;
    }

    private int f(int x, int y) {
        return x * 40001 + y;
    }
}
```

#### C++

```cpp
class Solution {
public:
    double minAreaFreeRect(vector<vector<int>>& points) {
        auto f = [](int x, int y) {
            return x * 40001 + y;
        };
        int n = points.size();
        unordered_set<int> s;
        for (auto& p : points) {
            s.insert(f(p[0], p[1]));
        }
        double ans = 1e20;
        for (int i = 0; i < n; ++i) {
            int x1 = points[i][0], y1 = points[i][1];
            for (int j = 0; j < n; ++j) {
                if (j != i) {
                    int x2 = points[j][0], y2 = points[j][1];
                    for (int k = j + 1; k < n; ++k) {
                        if (k != i) {
                            int x3 = points[k][0], y3 = points[k][1];
                            int x4 = x2 - x1 + x3, y4 = y2 - y1 + y3;
                            if (x4 >= 0 && x4 < 40000 && y4 >= 0 && y4 <= 40000 && s.count(f(x4, y4))) {
                                if ((x2 - x1) * (x3 - x1) + (y2 - y1) * (y3 - y1) == 0) {
                                    int ww = (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
                                    int hh = (x3 - x1) * (x3 - x1) + (y3 - y1) * (y3 - y1);
                                    ans = min(ans, sqrt(1LL * ww * hh));
                                }
                            }
                        }
                    }
                }
            }
        }
        return ans == 1e20 ? 0 : ans;
    }
};
```

#### Go

```go
func minAreaFreeRect(points [][]int) float64 {
	n := len(points)
	f := func(x, y int) int {
		return x*40001 + y
	}
	s := map[int]bool{}
	for _, p := range points {
		s[f(p[0], p[1])] = true
	}
	ans := 1e20
	for i := 0; i < n; i++ {
		x1, y1 := points[i][0], points[i][1]
		for j := 0; j < n; j++ {
			if j != i {
				x2, y2 := points[j][0], points[j][1]
				for k := j + 1; k < n; k++ {
					if k != i {
						x3, y3 := points[k][0], points[k][1]
						x4, y4 := x2-x1+x3, y2-y1+y3
						if s[f(x4, y4)] {
							if (x2-x1)*(x3-x1)+(y2-y1)*(y3-y1) == 0 {
								ww := (x2-x1)*(x2-x1) + (y2-y1)*(y2-y1)
								hh := (x3-x1)*(x3-x1) + (y3-y1)*(y3-y1)
								ans = math.Min(ans, math.Sqrt(float64(ww*hh)))
							}
						}
					}
				}
			}
		}
	}
	if ans == 1e20 {
		return 0
	}
	return ans
}
```

#### TypeScript

```ts
function minAreaFreeRect(points: number[][]): number {
    const n = points.length;
    const f = (x: number, y: number): number => x * 40001 + y;
    const s: Set<number> = new Set();
    for (const [x, y] of points) {
        s.add(f(x, y));
    }
    let ans = Number.MAX_VALUE;
    for (let i = 0; i < n; ++i) {
        const [x1, y1] = points[i];
        for (let j = 0; j < n; ++j) {
            if (j !== i) {
                const [x2, y2] = points[j];
                for (let k = j + 1; k < n; ++k) {
                    if (k !== i) {
                        const [x3, y3] = points[k];
                        const x4 = x2 - x1 + x3;
                        const y4 = y2 - y1 + y3;
                        if (s.has(f(x4, y4))) {
                            if ((x2 - x1) * (x3 - x1) + (y2 - y1) * (y3 - y1) === 0) {
                                const ww = (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
                                const hh = (x3 - x1) * (x3 - x1) + (y3 - y1) * (y3 - y1);
                                ans = Math.min(ans, Math.sqrt(ww * hh));
                            }
                        }
                    }
                }
            }
        }
    }
    return ans === Number.MAX_VALUE ? 0 : ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
