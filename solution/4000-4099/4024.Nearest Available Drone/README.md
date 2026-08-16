---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4024.Nearest%20Available%20Drone/README.md
---

<!-- problem:start -->

# [4024. 最近的可用无人机](https://leetcode.cn/problems/nearest-available-drone)

[English Version](/solution/4000-4099/4024.Nearest%20Available%20Drone/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个二维整数数组 <code>drones</code>，其中 <code>drones[i] = [x<sub>i</sub>, y<sub>i</sub>, range<sub>i</sub>]</code> 表示第 <code>i<sup>th</sup></code> 架无人机的横坐标、纵坐标和飞行范围。</p>

<p>另给你一个整数数组 <code>target = [tx, ty]</code>，表示目标的坐标。</p>

<p>如果无人机 <code>drones[i]</code> 的坐标与目标坐标之间的<strong>曼哈顿距离</strong><strong>小于或等于</strong>其 <code>range<sub>i</sub></code>，则该无人机能够到达目标。</p>

<p>返回能够到达目标且与目标之间<strong>曼哈顿距离最小</strong>的无人机的<strong>下标</strong>。如果存在多个符合条件的无人机，则返回其中<strong>最小的下标</strong>。如果没有无人机能够到达目标，则返回 -1。</p>

<p>两个坐标 <code>(x<sub>i</sub>, y<sub>i</sub>)</code> 和 <code>(x<sub>j</sub>, y<sub>j</sub>)</code> 之间的<strong>曼哈顿距离</strong>为 <code>|x<sub>i</sub> - x<sub>j</sub>| + |y<sub>i</sub> - y<sub>j</sub>|</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">drones = [[0,0,8],[2,2,9]], target = [3,4]</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>drones[0]</code> 与 <code>target</code> 之间的距离为 <code>|0 - 3| + |0 - 4| = 7</code>，没有超出其飞行范围 8。</li>
	<li><code>drones[1]</code> 与 <code>target</code> 之间的距离为 <code>|2 - 3| + |2 - 4| = 3</code>，没有超出其飞行范围 9。</li>
	<li>由于 <code>drones[1]</code> 是距离目标最近的无人机，因此答案为 1。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">drones = [[2,1,5],[4,4,5],[6,6,8]], target = [5,5]</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>drones[0]</code> 与 <code>target</code> 之间的距离为 <code>|2 - 5| + |1 - 5| = 7</code>，大于其飞行范围 5。</li>
	<li><code>drones[1]</code> 与 <code>target</code> 之间的距离为 <code>|4 - 5| + |4 - 5| = 2</code>，没有超出其飞行范围 5。</li>
	<li><code>drones[2]</code> 与 <code>target</code> 之间的距离为 <code>|6 - 5| + |6 - 5| = 2</code>，没有超出其飞行范围 8。</li>
	<li><code>drones[1]</code> 和 <code>drones[2]</code> 都是距离目标最近的无人机。由于需要返回最小下标，因此答案为 1。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">drones = [[4,4,5]], target = [8,6]</span></p>

<p><strong>输出：</strong> <span class="example-io">-1</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>drones[0]</code> 与 <code>target</code> 之间的距离为 <code>|4 - 8| + |4 - 6| = 6</code>，大于其飞行范围 5。</li>
	<li>没有无人机能够到达目标，因此答案为 -1。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= drones.length &lt;= 100</code></li>
	<li><code>drones[i] = [x<sub>i</sub>, y<sub>i</sub>, range<sub>i</sub>]</code></li>
	<li><code>target = [tx, ty]</code></li>
	<li><code>-25 &lt;= x<sub>i</sub>, y<sub>i</sub>, tx, ty &lt;= 25</code></li>
	<li><code>1 &lt;= range<sub>i</sub> &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：遍历

我们遍历每一架无人机，计算其与目标的曼哈顿距离 $d = |x_i - t_x| + |y_i - t_y|$。若 $d \le \textit{range}_i$，则该无人机可达。在所有可达无人机中，选择距离最小的一架；若距离相同，由于我们从左到右遍历且仅在距离严格更小时更新答案，因此会自动保留更小的下标。若没有可达无人机，返回 $-1$。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 是无人机的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def nearestDrone(self, drones: list[list[int]], target: list[int]) -> int:
        ans = -1
        mn = inf
        tx, ty = target
        for i, (x, y, r) in enumerate(drones):
            d = abs(x - tx) + abs(y - ty)
            if d <= r and mn > d:
                ans = i
                mn = d
        return ans
```

#### Java

```java
class Solution {
    public int nearestDrone(int[][] drones, int[] target) {
        int ans = -1;
        int mn = Integer.MAX_VALUE;
        int tx = target[0], ty = target[1];

        for (int i = 0; i < drones.length; i++) {
            int x = drones[i][0];
            int y = drones[i][1];
            int r = drones[i][2];

            int d = Math.abs(x - tx) + Math.abs(y - ty);

            if (d <= r && mn > d) {
                ans = i;
                mn = d;
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
    int nearestDrone(vector<vector<int>>& drones, vector<int>& target) {
        int ans = -1;
        int mn = INT_MAX;
        int tx = target[0], ty = target[1];

        for (int i = 0; i < drones.size(); i++) {
            int x = drones[i][0];
            int y = drones[i][1];
            int r = drones[i][2];

            int d = abs(x - tx) + abs(y - ty);

            if (d <= r && mn > d) {
                ans = i;
                mn = d;
            }
        }

        return ans;
    }
};
```

#### Go

```go
func nearestDrone(drones [][]int, target []int) int {
	ans := -1
	mn := math.MaxInt32
	tx, ty := target[0], target[1]

	for i, drone := range drones {
		x, y, r := drone[0], drone[1], drone[2]

		d := abs(x-tx) + abs(y-ty)

		if d <= r && mn > d {
			ans = i
			mn = d
		}
	}

	return ans
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
function nearestDrone(drones: number[][], target: number[]): number {
    let ans = -1;
    let mn = Infinity;
    const [tx, ty] = target;

    for (let i = 0; i < drones.length; i++) {
        const [x, y, r] = drones[i];

        const d = Math.abs(x - tx) + Math.abs(y - ty);

        if (d <= r && mn > d) {
            ans = i;
            mn = d;
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
