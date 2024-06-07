---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1066.Campus%20Bikes%20II/README_EN.md
rating: 1885
source: Biweekly Contest 1 Q3
tags:
    - Bit Manipulation
    - Array
    - Dynamic Programming
    - Backtracking
    - Bitmask
---

<!-- problem:start -->

# [1066. Campus Bikes II 🔒](https://leetcode.com/problems/campus-bikes-ii)

[中文文档](/solution/1000-1099/1066.Campus%20Bikes%20II/README.md)

## Description

<!-- description:start -->

<p>On a campus represented as a 2D grid, there are <code>n</code> workers and <code>m</code> bikes, with <code>n &lt;= m</code>. Each worker and bike is a 2D coordinate on this grid.</p>

<p>We assign one unique bike to each worker so that the sum of the <strong>Manhattan distances</strong> between each worker and their assigned bike is minimized.</p>

<p>Return <code>the minimum possible sum of Manhattan distances between each worker and their assigned bike</code>.</p>

<p>The <strong>Manhattan distance</strong> between two points <code>p1</code> and <code>p2</code> is <code>Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1066.Campus%20Bikes%20II/images/1261_example_1_v2.png" style="width: 376px; height: 366px;" />
<pre>
<strong>Input:</strong> workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
<strong>Output:</strong> 6
<strong>Explanation:</strong> 
We assign bike 0 to worker 0, bike 1 to worker 1. The Manhattan distance of both assignments is 3, so the output is 6.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1066.Campus%20Bikes%20II/images/1261_example_2_v2.png" style="width: 376px; height: 366px;" />
<pre>
<strong>Input:</strong> workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
<strong>Output:</strong> 4
<strong>Explanation: </strong>
We first assign bike 0 to worker 0, then assign bike 1 to worker 1 or worker 2, bike 2 to worker 2 or worker 1. Both assignments lead to sum of the Manhattan distances as 4.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> workers = [[0,0],[1,0],[2,0],[3,0],[4,0]], bikes = [[0,999],[1,999],[2,999],[3,999],[4,999]]
<strong>Output:</strong> 4995
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == workers.length</code></li>
	<li><code>m == bikes.length</code></li>
	<li><code>1 &lt;= n &lt;= m &lt;= 10</code></li>
	<li><code>workers[i].length == 2</code></li>
	<li><code>bikes[i].length == 2</code></li>
	<li><code>0 &lt;= workers[i][0], workers[i][1], bikes[i][0], bikes[i][1] &lt; 1000</code></li>
	<li>All the workers and the bikes locations are <strong>unique</strong>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> int:
        n, m = len(workers), len(bikes)
        f = [[inf] * (1 << m) for _ in range(n + 1)]
        f[0][0] = 0
        for i, (x1, y1) in enumerate(workers, 1):
            for j in range(1 << m):
                for k, (x2, y2) in enumerate(bikes):
                    if j >> k & 1:
                        f[i][j] = min(
                            f[i][j],
                            f[i - 1][j ^ (1 << k)] + abs(x1 - x2) + abs(y1 - y2),
                        )
        return min(f[n])
```

#### Java

```java
class Solution {
    public int assignBikes(int[][] workers, int[][] bikes) {
        int n = workers.length;
        int m = bikes.length;
        int[][] f = new int[n + 1][1 << m];
        for (var g : f) {
            Arrays.fill(g, 1 << 30);
        }
        f[0][0] = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < 1 << m; ++j) {
                for (int k = 0; k < m; ++k) {
                    if ((j >> k & 1) == 1) {
                        int d = Math.abs(workers[i - 1][0] - bikes[k][0])
                            + Math.abs(workers[i - 1][1] - bikes[k][1]);
                        f[i][j] = Math.min(f[i][j], f[i - 1][j ^ (1 << k)] + d);
                    }
                }
            }
        }
        return Arrays.stream(f[n]).min().getAsInt();
    }
}
```

#### C++

```cpp
class Solution {
public:
    int assignBikes(vector<vector<int>>& workers, vector<vector<int>>& bikes) {
        int n = workers.size(), m = bikes.size();
        int f[n + 1][1 << m];
        memset(f, 0x3f, sizeof(f));
        f[0][0] = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < 1 << m; ++j) {
                for (int k = 0; k < m; ++k) {
                    if (j >> k & 1) {
                        int d = abs(workers[i - 1][0] - bikes[k][0]) + abs(workers[i - 1][1] - bikes[k][1]);
                        f[i][j] = min(f[i][j], f[i - 1][j ^ (1 << k)] + d);
                    }
                }
            }
        }
        return *min_element(f[n], f[n] + (1 << m));
    }
};
```

#### Go

```go
func assignBikes(workers [][]int, bikes [][]int) int {
	n, m := len(workers), len(bikes)
	f := make([][]int, n+1)
	const inf = 1 << 30
	for i := range f {
		f[i] = make([]int, 1<<m)
		for j := range f[i] {
			f[i][j] = inf
		}
	}
	f[0][0] = 0
	for i := 1; i <= n; i++ {
		for j := 0; j < 1<<m; j++ {
			for k := 0; k < m; k++ {
				if j>>k&1 == 1 {
					d := abs(workers[i-1][0]-bikes[k][0]) + abs(workers[i-1][1]-bikes[k][1])
					f[i][j] = min(f[i][j], f[i-1][j^(1<<k)]+d)
				}
			}
		}
	}
	return slices.Min(f[n])
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
function assignBikes(workers: number[][], bikes: number[][]): number {
    const n = workers.length;
    const m = bikes.length;
    const inf = 1 << 30;
    const f: number[][] = new Array(n + 1).fill(0).map(() => new Array(1 << m).fill(inf));
    f[0][0] = 0;
    for (let i = 1; i <= n; ++i) {
        for (let j = 0; j < 1 << m; ++j) {
            for (let k = 0; k < m; ++k) {
                if (((j >> k) & 1) === 1) {
                    const d =
                        Math.abs(workers[i - 1][0] - bikes[k][0]) +
                        Math.abs(workers[i - 1][1] - bikes[k][1]);
                    f[i][j] = Math.min(f[i][j], f[i - 1][j ^ (1 << k)] + d);
                }
            }
        }
    }
    return Math.min(...f[n]);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
