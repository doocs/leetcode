---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1266.Minimum%20Time%20Visiting%20All%20Points/README.md
rating: 1302
source: 第 164 场周赛 Q1
tags:
    - 几何
    - 数组
    - 数学
---

<!-- problem:start -->

# [1266. 访问所有点的最小时间](https://leetcode.cn/problems/minimum-time-visiting-all-points)

[English Version](/solution/1200-1299/1266.Minimum%20Time%20Visiting%20All%20Points/README_EN.md)

## 题目描述

<!-- description:start -->

<p>平面上有 <code>n</code> 个点，点的位置用整数坐标表示 <code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> 。请你计算访问所有这些点需要的 <strong>最小时间</strong>（以秒为单位）。</p>

<p>你需要按照下面的规则在平面上移动：</p>

<ul>
	<li>每一秒内，你可以：
	<ul>
		<li>沿水平方向移动一个单位长度，或者</li>
		<li>沿竖直方向移动一个单位长度，或者</li>
		<li>跨过对角线移动 <code>sqrt(2)</code> 个单位长度（可以看作在一秒内向水平和竖直方向各移动一个单位长度）。</li>
	</ul>
	</li>
	<li>必须按照数组中出现的顺序来访问这些点。</li>
	<li>在访问某个点时，可以经过该点后面出现的点，但经过的那些点不算作有效访问。</li>
</ul>

<p> </p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1266.Minimum%20Time%20Visiting%20All%20Points/images/1626_example_1.png" style="height: 428px; width: 500px;" /></p>

<pre>
<strong>输入：</strong>points = [[1,1],[3,4],[-1,0]]
<strong>输出：</strong>7
<strong>解释：</strong>一条最佳的访问路径是： <strong>[1,1]</strong> -> [2,2] -> [3,3] -> <strong>[3,4] </strong>-> [2,3] -> [1,2] -> [0,1] -> <strong>[-1,0]</strong>
从 [1,1] 到 [3,4] 需要 3 秒
从 [3,4] 到 [-1,0] 需要 4 秒
一共需要 7 秒</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>points = [[3,2],[-2,2]]
<strong>输出：</strong>5
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>points.length == n</code></li>
	<li><code>1 <= n <= 100</code></li>
	<li><code>points[i].length == 2</code></li>
	<li><code>-1000 <= points[i][0], points[i][1] <= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

对于两个点 $p_1=(x_1, y_1)$ 和 $p_2=(x_2, y_2)$，横坐标和纵坐标分别移动的距离分别为 $d_x = |x_1 - x_2|$ 和 $d_y = |y_1 - y_2|$。

如果 $d_x \ge d_y$，则沿对角线移动 $d_y$，再沿水平方向移动 $d_x - d_y$；如果 $d_x < d_y$，则沿对角线移动 $d_x$，再沿竖直方向移动 $d_y - d_x$。因此，两个点之间的最短距离为 $\max(d_x, d_y)$。

我们可以遍历所有的点对，计算出每个点对之间的最短距离，然后求和即可。

时间复杂度 $O(n)$，其中 $n$ 为点的个数。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minTimeToVisitAllPoints(self, points: List[List[int]]) -> int:
        return sum(
            max(abs(p1[0] - p2[0]), abs(p1[1] - p2[1])) for p1, p2 in pairwise(points)
        )
```

#### Java

```java
class Solution {
    public int minTimeToVisitAllPoints(int[][] points) {
        int ans = 0;
        for (int i = 1; i < points.length; ++i) {
            int dx = Math.abs(points[i][0] - points[i - 1][0]);
            int dy = Math.abs(points[i][1] - points[i - 1][1]);
            ans += Math.max(dx, dy);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minTimeToVisitAllPoints(vector<vector<int>>& points) {
        int ans = 0;
        for (int i = 1; i < points.size(); ++i) {
            int dx = abs(points[i][0] - points[i - 1][0]);
            int dy = abs(points[i][1] - points[i - 1][1]);
            ans += max(dx, dy);
        }
        return ans;
    }
};
```

#### Go

```go
func minTimeToVisitAllPoints(points [][]int) (ans int) {
	for i, p := range points[1:] {
		dx := abs(p[0] - points[i][0])
		dy := abs(p[1] - points[i][1])
		ans += max(dx, dy)
	}
	return
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
function minTimeToVisitAllPoints(points: number[][]): number {
    let ans = 0;
    for (let i = 1; i < points.length; i++) {
        const dx = Math.abs(points[i][0] - points[i - 1][0]);
        const dy = Math.abs(points[i][1] - points[i - 1][1]);
        ans += Math.max(dx, dy);
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn min_time_to_visit_all_points(points: Vec<Vec<i32>>) -> i32 {
        let mut ans = 0;
        for i in 1..points.len() {
            let dx = (points[i][0] - points[i - 1][0]).abs();
            let dy = (points[i][1] - points[i - 1][1]).abs();
            ans += dx.max(dy);
        }
        ans
    }
}
```

#### C

```c
#define max(a, b) ((a) > (b) ? (a) : (b))

int minTimeToVisitAllPoints(int** points, int pointsSize, int* pointsColSize) {
    int ans = 0;
    for (int i = 1; i < pointsSize; ++i) {
        int dx = abs(points[i][0] - points[i - 1][0]);
        int dy = abs(points[i][1] - points[i - 1][1]);
        ans += max(dx, dy);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
