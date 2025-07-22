---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3332.Maximum%20Points%20Tourist%20Can%20Earn/README_EN.md
rating: 1827
source: Biweekly Contest 142 Q3
tags:
    - Array
    - Dynamic Programming
    - Matrix
---

<!-- problem:start -->

# [3332. Maximum Points Tourist Can Earn](https://leetcode.com/problems/maximum-points-tourist-can-earn)

[中文文档](/solution/3300-3399/3332.Maximum%20Points%20Tourist%20Can%20Earn/README.md)

## Description

<!-- description:start -->

<p>You are given two integers, <code>n</code> and <code>k</code>, along with two 2D integer arrays, <code>stayScore</code> and <code>travelScore</code>.</p>

<p>A tourist is visiting a country with <code>n</code> cities, where each city is <strong>directly</strong> connected to every other city. The tourist&#39;s journey consists of <strong>exactly</strong> <code>k</code> <strong>0-indexed</strong> days, and they can choose <strong>any</strong> city as their starting point.</p>

<p>Each day, the tourist has two choices:</p>

<ul>
	<li><strong>Stay in the current city</strong>: If the tourist stays in their current city <code>curr</code> during day <code>i</code>, they will earn <code>stayScore[i][curr]</code> points.</li>
	<li><strong>Move to another city</strong>: If the tourist moves from their current city <code>curr</code> to city <code>dest</code>, they will earn <code>travelScore[curr][dest]</code> points.</li>
</ul>

<p>Return the <strong>maximum</strong> possible points the tourist can earn.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 2, k = 1, stayScore = [[2,3]], travelScore = [[0,2],[1,0]]</span></p>

<p><strong>Output:</strong> 3</p>

<p><strong>Explanation:</strong></p>

<p>The tourist earns the maximum number of points by starting in city 1 and staying in that city.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, k = 2, stayScore = [[3,4,2],[2,1,2]], travelScore = [[0,2,1],[2,0,4],[3,2,0]]</span></p>

<p><strong>Output:</strong> <span class="example-io">8</span></p>

<p><strong>Explanation:</strong></p>

<p>The tourist earns the maximum number of points by starting in city 1, staying in that city on day 0, and traveling to city 2 on day 1.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 200</code></li>
	<li><code>1 &lt;= k &lt;= 200</code></li>
	<li><code>n == travelScore.length == travelScore[i].length == stayScore[i].length</code></li>
	<li><code>k == stayScore.length</code></li>
	<li><code>1 &lt;= stayScore[i][j] &lt;= 100</code></li>
	<li><code>0 &lt;= travelScore[i][j] &lt;= 100</code></li>
	<li><code>travelScore[i][i] == 0</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxScore(
        self, n: int, k: int, stayScore: List[List[int]], travelScore: List[List[int]]
    ) -> int:
        f = [[-inf] * n for _ in range(k + 1)]
        f[0] = [0] * n
        for i in range(1, k + 1):
            for j in range(n):
                for h in range(n):
                    f[i][j] = max(
                        f[i][j],
                        f[i - 1][h]
                        + (stayScore[i - 1][j] if j == h else travelScore[h][j]),
                    )
        return max(f[k])
```

#### Java

```java
class Solution {
    public int maxScore(int n, int k, int[][] stayScore, int[][] travelScore) {
        int[][] f = new int[k + 1][n];
        for (var g : f) {
            Arrays.fill(g, Integer.MIN_VALUE);
        }
        Arrays.fill(f[0], 0);
        for (int i = 1; i <= k; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int h = 0; h < n; ++h) {
                    f[i][j] = Math.max(
                        f[i][j], f[i - 1][h] + (j == h ? stayScore[i - 1][j] : travelScore[h][j]));
                }
            }
        }
        return Arrays.stream(f[k]).max().getAsInt();
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxScore(int n, int k, vector<vector<int>>& stayScore, vector<vector<int>>& travelScore) {
        int f[k + 1][n];
        memset(f, 0xc0, sizeof(f));
        memset(f[0], 0, sizeof(f[0]));
        for (int i = 1; i <= k; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int h = 0; h < n; ++h) {
                    f[i][j] = max(f[i][j], f[i - 1][h] + (j == h ? stayScore[i - 1][j] : travelScore[h][j]));
                }
            }
        }
        return *max_element(f[k], f[k] + n);
    }
};
```

#### Go

```go
func maxScore(n int, k int, stayScore [][]int, travelScore [][]int) (ans int) {
	f := make([][]int, k+1)
	for i := range f {
		f[i] = make([]int, n)
		for j := range f[i] {
			f[i][j] = math.MinInt32
		}
	}
	for j := 0; j < n; j++ {
		f[0][j] = 0
	}
	for i := 1; i <= k; i++ {
		for j := 0; j < n; j++ {
			f[i][j] = f[i-1][j] + stayScore[i-1][j]
			for h := 0; h < n; h++ {
				if h != j {
					f[i][j] = max(f[i][j], f[i-1][h]+travelScore[h][j])
				}
			}
		}
	}
	for j := 0; j < n; j++ {
		ans = max(ans, f[k][j])
	}
	return
}
```

#### TypeScript

```ts
function maxScore(n: number, k: number, stayScore: number[][], travelScore: number[][]): number {
    const f: number[][] = Array.from({ length: k + 1 }, () => Array(n).fill(-Infinity));
    f[0].fill(0);
    for (let i = 1; i <= k; ++i) {
        for (let j = 0; j < n; ++j) {
            for (let h = 0; h < n; ++h) {
                f[i][j] = Math.max(
                    f[i][j],
                    f[i - 1][h] + (j == h ? stayScore[i - 1][j] : travelScore[h][j]),
                );
            }
        }
    }
    return Math.max(...f[k]);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
