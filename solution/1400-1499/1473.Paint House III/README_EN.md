---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1473.Paint%20House%20III/README_EN.md
rating: 2056
source: Weekly Contest 192 Q4
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [1473. Paint House III](https://leetcode.com/problems/paint-house-iii)

[中文文档](/solution/1400-1499/1473.Paint%20House%20III/README.md)

## Description

<!-- description:start -->

<p>There is a row of <code>m</code> houses in a small city, each house must be painted with one of the <code>n</code> colors (labeled from <code>1</code> to <code>n</code>), some houses that have been painted last summer should not be painted again.</p>

<p>A neighborhood is a maximal group of continuous houses that are painted with the same color.</p>

<ul>
	<li>For example: <code>houses = [1,2,2,3,3,2,1,1]</code> contains <code>5</code> neighborhoods <code>[{1}, {2,2}, {3,3}, {2}, {1,1}]</code>.</li>
</ul>

<p>Given an array <code>houses</code>, an <code>m x n</code> matrix <code>cost</code> and an integer <code>target</code> where:</p>

<ul>
	<li><code>houses[i]</code>: is the color of the house <code>i</code>, and <code>0</code> if the house is not painted yet.</li>
	<li><code>cost[i][j]</code>: is the cost of paint the house <code>i</code> with the color <code>j + 1</code>.</li>
</ul>

<p>Return <em>the minimum cost of painting all the remaining houses in such a way that there are exactly</em> <code>target</code> <em>neighborhoods</em>. If it is not possible, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> houses = [0,0,0,0,0], cost = [[1,10],[10,1],[10,1],[1,10],[5,1]], m = 5, n = 2, target = 3
<strong>Output:</strong> 9
<strong>Explanation:</strong> Paint houses of this way [1,2,2,1,1]
This array contains target = 3 neighborhoods, [{1}, {2,2}, {1,1}].
Cost of paint all houses (1 + 1 + 1 + 1 + 5) = 9.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> houses = [0,2,1,2,0], cost = [[1,10],[10,1],[10,1],[1,10],[5,1]], m = 5, n = 2, target = 3
<strong>Output:</strong> 11
<strong>Explanation:</strong> Some houses are already painted, Paint the houses of this way [2,2,1,2,2]
This array contains target = 3 neighborhoods, [{2,2}, {1}, {2,2}]. 
Cost of paint the first and last house (10 + 1) = 11.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> houses = [3,1,2,3], cost = [[1,1,1],[1,1,1],[1,1,1],[1,1,1]], m = 4, n = 3, target = 3
<strong>Output:</strong> -1
<strong>Explanation:</strong> Houses are already painted with a total of 4 neighborhoods [{3},{1},{2},{3}] different of target = 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == houses.length == cost.length</code></li>
	<li><code>n == cost[i].length</code></li>
	<li><code>1 &lt;= m &lt;= 100</code></li>
	<li><code>1 &lt;= n &lt;= 20</code></li>
	<li><code>1 &lt;= target &lt;= m</code></li>
	<li><code>0 &lt;= houses[i] &lt;= n</code></li>
	<li><code>1 &lt;= cost[i][j] &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

We define $f[i][j][k]$ to represent the minimum cost to paint houses from index $0$ to $i$, with the last house painted in color $j$, and exactly forming $k$ blocks. The answer is $f[m-1][j][\textit{target}]$, where $j$ ranges from $1$ to $n$. Initially, we check if the house at index $0$ is already painted. If it is not painted, then $f[0][j][1] = \textit{cost}[0][j - 1]$, where $j \in [1,..n]$. If it is already painted, then $f[0][\textit{houses}[0]][1] = 0$. All other values of $f[i][j][k]$ are initialized to $\infty$.

Next, we start iterating from index $i=1$. For each $i$, we check if the house at index $i$ is already painted:

If it is not painted, we can paint the house at index $i$ with color $j$. We enumerate the number of blocks $k$, where $k \in [1,..\min(\textit{target}, i + 1)]$, and enumerate the color of the previous house $j_0$, where $j_0 \in [1,..n]$. Then we can derive the state transition equation:

$$
f[i][j][k] = \min_{j_0 \in [1,..n]} \{ f[i - 1][j_0][k - (j \neq j_0)] + \textit{cost}[i][j - 1] \}
$$

If it is already painted, we can paint the house at index $i$ with color $j$. We enumerate the number of blocks $k$, where $k \in [1,..\min(\textit{target}, i + 1)]$, and enumerate the color of the previous house $j_0$, where $j_0 \in [1,..n]$. Then we can derive the state transition equation:

$$
f[i][j][k] = \min_{j_0 \in [1,..n]} \{ f[i - 1][j_0][k - (j \neq j_0)] \}
$$

Finally, we return $f[m - 1][j][\textit{target}]$, where $j \in [1,..n]$. If all values of $f[m - 1][j][\textit{target}]$ are $\infty$, then return $-1$.

The time complexity is $O(m \times n^2 \times \textit{target})$, and the space complexity is $O(m \times n \times \textit{target})$. Here, $m$, $n$, and $\textit{target}$ represent the number of houses, the number of colors, and the number of blocks, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minCost(
        self, houses: List[int], cost: List[List[int]], m: int, n: int, target: int
    ) -> int:
        f = [[[inf] * (target + 1) for _ in range(n + 1)] for _ in range(m)]
        if houses[0] == 0:
            for j, c in enumerate(cost[0], 1):
                f[0][j][1] = c
        else:
            f[0][houses[0]][1] = 0
        for i in range(1, m):
            if houses[i] == 0:
                for j in range(1, n + 1):
                    for k in range(1, min(target + 1, i + 2)):
                        for j0 in range(1, n + 1):
                            if j == j0:
                                f[i][j][k] = min(
                                    f[i][j][k], f[i - 1][j][k] + cost[i][j - 1]
                                )
                            else:
                                f[i][j][k] = min(
                                    f[i][j][k], f[i - 1][j0][k - 1] + cost[i][j - 1]
                                )
            else:
                j = houses[i]
                for k in range(1, min(target + 1, i + 2)):
                    for j0 in range(1, n + 1):
                        if j == j0:
                            f[i][j][k] = min(f[i][j][k], f[i - 1][j][k])
                        else:
                            f[i][j][k] = min(f[i][j][k], f[i - 1][j0][k - 1])

        ans = min(f[-1][j][target] for j in range(1, n + 1))
        return -1 if ans >= inf else ans
```

#### Java

```java
class Solution {
    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        int[][][] f = new int[m][n + 1][target + 1];
        final int inf = 1 << 30;
        for (int[][] g : f) {
            for (int[] e : g) {
                Arrays.fill(e, inf);
            }
        }
        if (houses[0] == 0) {
            for (int j = 1; j <= n; ++j) {
                f[0][j][1] = cost[0][j - 1];
            }
        } else {
            f[0][houses[0]][1] = 0;
        }
        for (int i = 1; i < m; ++i) {
            if (houses[i] == 0) {
                for (int j = 1; j <= n; ++j) {
                    for (int k = 1; k <= Math.min(target, i + 1); ++k) {
                        for (int j0 = 1; j0 <= n; ++j0) {
                            if (j == j0) {
                                f[i][j][k] = Math.min(f[i][j][k], f[i - 1][j][k] + cost[i][j - 1]);
                            } else {
                                f[i][j][k]
                                    = Math.min(f[i][j][k], f[i - 1][j0][k - 1] + cost[i][j - 1]);
                            }
                        }
                    }
                }
            } else {
                int j = houses[i];
                for (int k = 1; k <= Math.min(target, i + 1); ++k) {
                    for (int j0 = 1; j0 <= n; ++j0) {
                        if (j == j0) {
                            f[i][j][k] = Math.min(f[i][j][k], f[i - 1][j][k]);
                        } else {
                            f[i][j][k] = Math.min(f[i][j][k], f[i - 1][j0][k - 1]);
                        }
                    }
                }
            }
        }
        int ans = inf;
        for (int j = 1; j <= n; ++j) {
            ans = Math.min(ans, f[m - 1][j][target]);
        }
        return ans >= inf ? -1 : ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minCost(vector<int>& houses, vector<vector<int>>& cost, int m, int n, int target) {
        int f[m][n + 1][target + 1];
        memset(f, 0x3f, sizeof(f));
        if (houses[0] == 0) {
            for (int j = 1; j <= n; ++j) {
                f[0][j][1] = cost[0][j - 1];
            }
        } else {
            f[0][houses[0]][1] = 0;
        }
        for (int i = 1; i < m; ++i) {
            if (houses[i] == 0) {
                for (int j = 1; j <= n; ++j) {
                    for (int k = 1; k <= min(target, i + 1); ++k) {
                        for (int j0 = 1; j0 <= n; ++j0) {
                            if (j == j0) {
                                f[i][j][k] = min(f[i][j][k], f[i - 1][j][k] + cost[i][j - 1]);
                            } else {
                                f[i][j][k] = min(f[i][j][k], f[i - 1][j0][k - 1] + cost[i][j - 1]);
                            }
                        }
                    }
                }
            } else {
                int j = houses[i];
                for (int k = 1; k <= min(target, i + 1); ++k) {
                    for (int j0 = 1; j0 <= n; ++j0) {
                        if (j == j0) {
                            f[i][j][k] = min(f[i][j][k], f[i - 1][j][k]);
                        } else {
                            f[i][j][k] = min(f[i][j][k], f[i - 1][j0][k - 1]);
                        }
                    }
                }
            }
        }
        int ans = 0x3f3f3f3f;
        for (int j = 1; j <= n; ++j) {
            ans = min(ans, f[m - 1][j][target]);
        }
        return ans == 0x3f3f3f3f ? -1 : ans;
    }
};
```

#### Go

```go
func minCost(houses []int, cost [][]int, m int, n int, target int) int {
	f := make([][][]int, m)
	const inf = 1 << 30
	for i := range f {
		f[i] = make([][]int, n+1)
		for j := range f[i] {
			f[i][j] = make([]int, target+1)
			for k := range f[i][j] {
				f[i][j][k] = inf
			}
		}
	}
	if houses[0] == 0 {
		for j := 1; j <= n; j++ {
			f[0][j][1] = cost[0][j-1]
		}
	} else {
		f[0][houses[0]][1] = 0
	}
	for i := 1; i < m; i++ {
		if houses[i] == 0 {
			for j := 1; j <= n; j++ {
				for k := 1; k <= target && k <= i+1; k++ {
					for j0 := 1; j0 <= n; j0++ {
						if j == j0 {
							f[i][j][k] = min(f[i][j][k], f[i-1][j][k]+cost[i][j-1])
						} else {
							f[i][j][k] = min(f[i][j][k], f[i-1][j0][k-1]+cost[i][j-1])
						}
					}
				}
			}
		} else {
			j := houses[i]
			for k := 1; k <= target && k <= i+1; k++ {
				for j0 := 1; j0 <= n; j0++ {
					if j == j0 {
						f[i][j][k] = min(f[i][j][k], f[i-1][j][k])
					} else {
						f[i][j][k] = min(f[i][j][k], f[i-1][j0][k-1])
					}
				}
			}
		}
	}
	ans := inf
	for j := 1; j <= n; j++ {
		ans = min(ans, f[m-1][j][target])
	}
	if ans == inf {
		return -1
	}
	return ans
}
```

#### TypeScript

```ts
function minCost(houses: number[], cost: number[][], m: number, n: number, target: number): number {
    const inf = 1 << 30;
    const f: number[][][] = new Array(m)
        .fill(0)
        .map(() => new Array(n + 1).fill(0).map(() => new Array(target + 1).fill(inf)));
    if (houses[0] === 0) {
        for (let j = 1; j <= n; ++j) {
            f[0][j][1] = cost[0][j - 1];
        }
    } else {
        f[0][houses[0]][1] = 0;
    }
    for (let i = 1; i < m; ++i) {
        if (houses[i] === 0) {
            for (let j = 1; j <= n; ++j) {
                for (let k = 1; k <= Math.min(target, i + 1); ++k) {
                    for (let j0 = 1; j0 <= n; ++j0) {
                        if (j0 === j) {
                            f[i][j][k] = Math.min(f[i][j][k], f[i - 1][j][k] + cost[i][j - 1]);
                        } else {
                            f[i][j][k] = Math.min(f[i][j][k], f[i - 1][j0][k - 1] + cost[i][j - 1]);
                        }
                    }
                }
            }
        } else {
            const j = houses[i];
            for (let k = 1; k <= Math.min(target, i + 1); ++k) {
                for (let j0 = 1; j0 <= n; ++j0) {
                    if (j0 === j) {
                        f[i][j][k] = Math.min(f[i][j][k], f[i - 1][j][k]);
                    } else {
                        f[i][j][k] = Math.min(f[i][j][k], f[i - 1][j0][k - 1]);
                    }
                }
            }
        }
    }
    let ans = inf;
    for (let j = 1; j <= n; ++j) {
        ans = Math.min(ans, f[m - 1][j][target]);
    }
    return ans >= inf ? -1 : ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
