---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3197.Find%20the%20Minimum%20Area%20to%20Cover%20All%20Ones%20II/README_EN.md
rating: 2540
source: Weekly Contest 403 Q4
tags:
    - Array
    - Enumeration
    - Matrix
---

<!-- problem:start -->

# [3197. Find the Minimum Area to Cover All Ones II](https://leetcode.com/problems/find-the-minimum-area-to-cover-all-ones-ii)

[中文文档](/solution/3100-3199/3197.Find%20the%20Minimum%20Area%20to%20Cover%20All%20Ones%20II/README.md)

## Description

<!-- description:start -->

<p>You are given a 2D <strong>binary</strong> array <code>grid</code>. You need to find 3 <strong>non-overlapping</strong> rectangles having <strong>non-zero</strong> areas with horizontal and vertical sides such that all the 1&#39;s in <code>grid</code> lie inside these rectangles.</p>

<p>Return the <strong>minimum</strong> possible sum of the area of these rectangles.</p>

<p><strong>Note</strong> that the rectangles are allowed to touch.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[1,0,1],[1,1,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3197.Find%20the%20Minimum%20Area%20to%20Cover%20All%20Ones%20II/images/example0rect21.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 280px; height: 198px;" /></p>

<ul>
	<li>The 1&#39;s at <code>(0, 0)</code> and <code>(1, 0)</code> are covered by a rectangle of area 2.</li>
	<li>The 1&#39;s at <code>(0, 2)</code> and <code>(1, 2)</code> are covered by a rectangle of area 2.</li>
	<li>The 1 at <code>(1, 1)</code> is covered by a rectangle of area 1.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[1,0,1,0],[0,1,0,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3197.Find%20the%20Minimum%20Area%20to%20Cover%20All%20Ones%20II/images/example1rect2.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 356px; height: 198px;" /></p>

<ul>
	<li>The 1&#39;s at <code>(0, 0)</code> and <code>(0, 2)</code> are covered by a rectangle of area 3.</li>
	<li>The 1 at <code>(1, 1)</code> is covered by a rectangle of area 1.</li>
	<li>The 1 at <code>(1, 3)</code> is covered by a rectangle of area 1.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= grid.length, grid[i].length &lt;= 30</code></li>
	<li><code>grid[i][j]</code> is either 0 or 1.</li>
	<li>The input is generated such that there are at least three 1&#39;s in <code>grid</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

According to the problem description, we can use two dividing lines to split the rectangle into three parts. We calculate the minimum rectangular area containing all $1$s for each part and then take the minimum sum of the areas of the three parts.

We can enumerate the positions of the two dividing lines, which have $6$ possibilities:

1. Two horizontal splits
2. Two vertical splits
3. First perform a horizontal split, then a vertical split on the upper part
4. First perform a horizontal split, then a vertical split on the lower part
5. First perform a vertical split, then a horizontal split on the left part
6. First perform a vertical split, then a horizontal split on the right part

We can use a function $\textit{f}(i_1, j_1, i_2, j_2)$ to calculate the minimum rectangular area containing all $1$s from $(i_1, j_1)$ to $(i_2, j_2)$.

The time complexity is $O(m^2 \times n^2)$, where $m$ and $n$ are the number of rows and columns of the rectangle, respectively. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumSum(self, grid: List[List[int]]) -> int:
        def f(i1: int, j1: int, i2: int, j2: int) -> int:
            x1 = y1 = inf
            x2 = y2 = -inf
            for i in range(i1, i2 + 1):
                for j in range(j1, j2 + 1):
                    if grid[i][j] == 1:
                        x1 = min(x1, i)
                        y1 = min(y1, j)
                        x2 = max(x2, i)
                        y2 = max(y2, j)
            return (x2 - x1 + 1) * (y2 - y1 + 1)

        m, n = len(grid), len(grid[0])
        ans = m * n
        for i1 in range(m - 1):
            for i2 in range(i1 + 1, m - 1):
                ans = min(
                    ans,
                    f(0, 0, i1, n - 1)
                    + f(i1 + 1, 0, i2, n - 1)
                    + f(i2 + 1, 0, m - 1, n - 1),
                )
        for j1 in range(n - 1):
            for j2 in range(j1 + 1, n - 1):
                ans = min(
                    ans,
                    f(0, 0, m - 1, j1)
                    + f(0, j1 + 1, m - 1, j2)
                    + f(0, j2 + 1, m - 1, n - 1),
                )
        for i in range(m - 1):
            for j in range(n - 1):
                ans = min(
                    ans,
                    f(0, 0, i, j) + f(0, j + 1, i, n - 1) + f(i + 1, 0, m - 1, n - 1),
                )
                ans = min(
                    ans,
                    f(0, 0, i, n - 1)
                    + f(i + 1, 0, m - 1, j)
                    + f(i + 1, j + 1, m - 1, n - 1),
                )

                ans = min(
                    ans,
                    f(0, 0, i, j) + f(i + 1, 0, m - 1, j) + f(0, j + 1, m - 1, n - 1),
                )
                ans = min(
                    ans,
                    f(0, 0, m - 1, j)
                    + f(0, j + 1, i, n - 1)
                    + f(i + 1, j + 1, m - 1, n - 1),
                )
        return ans
```

#### Java

```java
class Solution {
    private final int inf = 1 << 30;
    private int[][] grid;

    public int minimumSum(int[][] grid) {
        this.grid = grid;
        int m = grid.length;
        int n = grid[0].length;
        int ans = m * n;

        for (int i1 = 0; i1 < m - 1; i1++) {
            for (int i2 = i1 + 1; i2 < m - 1; i2++) {
                ans = Math.min(
                    ans, f(0, 0, i1, n - 1) + f(i1 + 1, 0, i2, n - 1) + f(i2 + 1, 0, m - 1, n - 1));
            }
        }

        for (int j1 = 0; j1 < n - 1; j1++) {
            for (int j2 = j1 + 1; j2 < n - 1; j2++) {
                ans = Math.min(
                    ans, f(0, 0, m - 1, j1) + f(0, j1 + 1, m - 1, j2) + f(0, j2 + 1, m - 1, n - 1));
            }
        }

        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                ans = Math.min(
                    ans, f(0, 0, i, j) + f(0, j + 1, i, n - 1) + f(i + 1, 0, m - 1, n - 1));
                ans = Math.min(
                    ans, f(0, 0, i, n - 1) + f(i + 1, 0, m - 1, j) + f(i + 1, j + 1, m - 1, n - 1));

                ans = Math.min(
                    ans, f(0, 0, i, j) + f(i + 1, 0, m - 1, j) + f(0, j + 1, m - 1, n - 1));
                ans = Math.min(
                    ans, f(0, 0, m - 1, j) + f(0, j + 1, i, n - 1) + f(i + 1, j + 1, m - 1, n - 1));
            }
        }
        return ans;
    }

    private int f(int i1, int j1, int i2, int j2) {
        int x1 = inf, y1 = inf;
        int x2 = -inf, y2 = -inf;
        for (int i = i1; i <= i2; i++) {
            for (int j = j1; j <= j2; j++) {
                if (grid[i][j] == 1) {
                    x1 = Math.min(x1, i);
                    y1 = Math.min(y1, j);
                    x2 = Math.max(x2, i);
                    y2 = Math.max(y2, j);
                }
            }
        }
        return (x2 - x1 + 1) * (y2 - y1 + 1);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumSum(vector<vector<int>>& grid) {
        int m = grid.size();
        int n = grid[0].size();
        int ans = m * n;
        int inf = INT_MAX / 4;
        auto f = [&](int i1, int j1, int i2, int j2) {
            int x1 = inf, y1 = inf;
            int x2 = -inf, y2 = -inf;
            for (int i = i1; i <= i2; i++) {
                for (int j = j1; j <= j2; j++) {
                    if (grid[i][j] == 1) {
                        x1 = min(x1, i);
                        y1 = min(y1, j);
                        x2 = max(x2, i);
                        y2 = max(y2, j);
                    }
                }
            }
            return x1 > x2 || y1 > y2 ? inf : (x2 - x1 + 1) * (y2 - y1 + 1);
        };

        for (int i1 = 0; i1 < m - 1; i1++) {
            for (int i2 = i1 + 1; i2 < m - 1; i2++) {
                ans = min(ans, f(0, 0, i1, n - 1) + f(i1 + 1, 0, i2, n - 1) + f(i2 + 1, 0, m - 1, n - 1));
            }
        }

        for (int j1 = 0; j1 < n - 1; j1++) {
            for (int j2 = j1 + 1; j2 < n - 1; j2++) {
                ans = min(ans, f(0, 0, m - 1, j1) + f(0, j1 + 1, m - 1, j2) + f(0, j2 + 1, m - 1, n - 1));
            }
        }

        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                ans = min(ans, f(0, 0, i, j) + f(0, j + 1, i, n - 1) + f(i + 1, 0, m - 1, n - 1));
                ans = min(ans, f(0, 0, i, n - 1) + f(i + 1, 0, m - 1, j) + f(i + 1, j + 1, m - 1, n - 1));
                ans = min(ans, f(0, 0, i, j) + f(i + 1, 0, m - 1, j) + f(0, j + 1, m - 1, n - 1));
                ans = min(ans, f(0, 0, m - 1, j) + f(0, j + 1, i, n - 1) + f(i + 1, j + 1, m - 1, n - 1));
            }
        }

        return ans;
    }
};
```

#### Go

```go
func minimumSum(grid [][]int) int {
	m := len(grid)
	n := len(grid[0])
	ans := m * n
	inf := math.MaxInt32

	f := func(i1, j1, i2, j2 int) int {
		x1, y1 := inf, inf
		x2, y2 := -inf, -inf
		for i := i1; i <= i2; i++ {
			for j := j1; j <= j2; j++ {
				if grid[i][j] == 1 {
					x1 = min(x1, i)
					y1 = min(y1, j)
					x2 = max(x2, i)
					y2 = max(y2, j)
				}
			}
		}
		if x1 == inf {
			return 0
		}
		return (x2 - x1 + 1) * (y2 - y1 + 1)
	}

	for i1 := 0; i1 < m-1; i1++ {
		for i2 := i1 + 1; i2 < m-1; i2++ {
			ans = min(ans, f(0, 0, i1, n-1)+f(i1+1, 0, i2, n-1)+f(i2+1, 0, m-1, n-1))
		}
	}

	for j1 := 0; j1 < n-1; j1++ {
		for j2 := j1 + 1; j2 < n-1; j2++ {
			ans = min(ans, f(0, 0, m-1, j1)+f(0, j1+1, m-1, j2)+f(0, j2+1, m-1, n-1))
		}
	}

	for i := 0; i < m-1; i++ {
		for j := 0; j < n-1; j++ {
			ans = min(ans, f(0, 0, i, j)+f(0, j+1, i, n-1)+f(i+1, 0, m-1, n-1))
			ans = min(ans, f(0, 0, i, n-1)+f(i+1, 0, m-1, j)+f(i+1, j+1, m-1, n-1))
			ans = min(ans, f(0, 0, i, j)+f(i+1, 0, m-1, j)+f(0, j+1, m-1, n-1))
			ans = min(ans, f(0, 0, m-1, j)+f(0, j+1, i, n-1)+f(i+1, j+1, m-1, n-1))
		}
	}

	return ans
}
```

#### TypeScript

```ts
function minimumSum(grid: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    let ans = m * n;
    const inf = Number.MAX_SAFE_INTEGER;
    const f = (i1: number, j1: number, i2: number, j2: number): number => {
        let [x1, y1] = [inf, inf];
        let [x2, y2] = [-inf, -inf];
        for (let i = i1; i <= i2; i++) {
            for (let j = j1; j <= j2; j++) {
                if (grid[i][j] === 1) {
                    x1 = Math.min(x1, i);
                    y1 = Math.min(y1, j);
                    x2 = Math.max(x2, i);
                    y2 = Math.max(y2, j);
                }
            }
        }
        return x1 === inf ? 0 : (x2 - x1 + 1) * (y2 - y1 + 1);
    };

    for (let i1 = 0; i1 < m - 1; i1++) {
        for (let i2 = i1 + 1; i2 < m - 1; i2++) {
            ans = Math.min(
                ans,
                f(0, 0, i1, n - 1) + f(i1 + 1, 0, i2, n - 1) + f(i2 + 1, 0, m - 1, n - 1),
            );
        }
    }

    for (let j1 = 0; j1 < n - 1; j1++) {
        for (let j2 = j1 + 1; j2 < n - 1; j2++) {
            ans = Math.min(
                ans,
                f(0, 0, m - 1, j1) + f(0, j1 + 1, m - 1, j2) + f(0, j2 + 1, m - 1, n - 1),
            );
        }
    }

    for (let i = 0; i < m - 1; i++) {
        for (let j = 0; j < n - 1; j++) {
            ans = Math.min(ans, f(0, 0, i, j) + f(0, j + 1, i, n - 1) + f(i + 1, 0, m - 1, n - 1));
            ans = Math.min(
                ans,
                f(0, 0, i, n - 1) + f(i + 1, 0, m - 1, j) + f(i + 1, j + 1, m - 1, n - 1),
            );
            ans = Math.min(ans, f(0, 0, i, j) + f(i + 1, 0, m - 1, j) + f(0, j + 1, m - 1, n - 1));
            ans = Math.min(
                ans,
                f(0, 0, m - 1, j) + f(0, j + 1, i, n - 1) + f(i + 1, j + 1, m - 1, n - 1),
            );
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
