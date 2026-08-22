---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4024.Nearest%20Available%20Drone/README_EN.md
tags:
    - Array
    - Enumeration
---

<!-- problem:start -->

# [4024. Nearest Available Drone](https://leetcode.com/problems/nearest-available-drone)

[中文文档](/solution/4000-4099/4024.Nearest%20Available%20Drone/README.md)

## Description

<!-- description:start -->

<p>You are given a 2D integer array <code>drones</code>, where <code>drones[i] = [x<sub>i</sub>, y<sub>i</sub>, range<sub>i</sub>]</code> represents the x-coordinate, y-coordinate, and travel range of the <code>i<sup>th</sup></code> drone.</p>

<p>You are also given an integer array <code>target = [tx, ty]</code>, representing the coordinates of the target.</p>

<p>A drone <code>drones[i]</code> can reach the target if the <span data-keyword="manhattan-distance">Manhattan distance</span> between its coordinates and the target coordinates is <strong>less than or equal</strong> to its <code>range<sub>i</sub></code>.</p>

<p>Return the <strong>index</strong> of the reachable drone with the <strong>minimum Manhattan distance</strong> to the target. If there is a tie, return the <strong>smallest index</strong>. If no drone can reach the target, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">drones = [[0,0,8],[2,2,9]], target = [3,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The distance between <code>drones[0]</code> and <code>target</code> is <code>|0 - 3| + |0 - 4| = 7</code>, which is within its range of 8.</li>
	<li>The distance between <code>drones[1]</code> and <code>target</code> is <code>|2 - 3| + |2 - 4| = 3</code>, which is within its range of 9.</li>
	<li>Since <code>drones[1]</code> is the nearest drone, the answer is 1.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">drones = [[2,1,5],[4,4,5],[6,6,8]], target = [5,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The distance between <code>drones[0]</code> and <code>target</code> is <code>|2 - 5| + |1 - 5| = 7</code>, which is greater than its range of 5.</li>
	<li>The distance between <code>drones[1]</code> and <code>target</code> is <code>|4 - 5| + |4 - 5| = 2</code>, which is within its range of 5.</li>
	<li>The distance between <code>drones[2]</code> and <code>target</code> is <code>|6 - 5| + |6 - 5| = 2</code>, which is within its range of 8.</li>
	<li>Both <code>drones[1]</code> and <code>drones[2]</code> are the nearest drones. Since we should return the smallest index, the answer is 1.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">drones = [[4,4,5]], target = [8,6]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The distance between <code>drones[0]</code> and <code>target</code> is <code>|4 - 8| + |4 - 6| = 6</code>, which is greater than its range of 5.</li>
	<li>No drone can reach the target, so the answer is -1.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= drones.length &lt;= 100</code></li>
	<li><code>drones[i] = [x<sub>i</sub>, y<sub>i</sub>, range<sub>i</sub>]</code></li>
	<li><code>target = [tx, ty]</code></li>
	<li><code>-25 &lt;= x<sub>i</sub>, y<sub>i</sub>, tx, ty &lt;= 25</code></li>
	<li><code>1 &lt;= range<sub>i</sub> &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Traversal

We iterate through each drone and compute the Manhattan distance $d = |x_i - t_x| + |y_i - t_y|$ to the target. If $d \le \textit{range}_i$, the drone can reach the target. Among all reachable drones, we choose the one with the minimum distance. If there is a tie, we keep the smaller index because we scan from left to right and only update when the distance is strictly smaller. If no drone can reach the target, return $-1$.

The time complexity is $O(n)$, and the space complexity is $O(1)$, where $n$ is the number of drones.

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
