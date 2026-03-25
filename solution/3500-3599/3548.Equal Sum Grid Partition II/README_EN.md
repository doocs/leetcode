---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3548.Equal%20Sum%20Grid%20Partition%20II/README_EN.md
rating: 2245
source: Weekly Contest 449 Q4
tags:
    - Array
    - Hash Table
    - Enumeration
    - Matrix
    - Prefix Sum
---

<!-- problem:start -->

# [3548. Equal Sum Grid Partition II](https://leetcode.com/problems/equal-sum-grid-partition-ii)

[中文文档](/solution/3500-3599/3548.Equal%20Sum%20Grid%20Partition%20II/README.md)

## Description

<!-- description:start -->

<p>You are given an <code>m x n</code> matrix <code>grid</code> of positive integers. Your task is to determine if it is possible to make <strong>either one horizontal or one vertical cut</strong> on the grid such that:</p>

<ul>
	<li>Each of the two resulting sections formed by the cut is <strong>non-empty</strong>.</li>
	<li>The sum of elements in both sections is <b>equal</b>, or can be made equal by discounting <strong>at most</strong> one single cell in total (from either section).</li>
	<li>If a cell is discounted, the rest of the section must <strong>remain connected</strong>.</li>
</ul>

<p>Return <code>true</code> if such a partition exists; otherwise, return <code>false</code>.</p>

<p><strong>Note:</strong> A section is <strong>connected</strong> if every cell in it can be reached from any other cell by moving up, down, left, or right through other cells in the section.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[1,4],[2,3]]</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3548.Equal%20Sum%20Grid%20Partition%20II/images/lc.jpeg" style="height: 180px; width: 180px;" /></p>

<ul>
	<li>A horizontal cut after the first row gives sums <code>1 + 4 = 5</code> and <code>2 + 3 = 5</code>, which are equal. Thus, the answer is <code>true</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[1,2],[3,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3548.Equal%20Sum%20Grid%20Partition%20II/images/chatgpt-image-apr-1-2025-at-05_28_12-pm.png" style="height: 180px; width: 180px;" /></p>

<ul>
	<li>A vertical cut after the first column gives sums <code>1 + 3 = 4</code> and <code>2 + 4 = 6</code>.</li>
	<li>By discounting 2 from the right section (<code>6 - 2 = 4</code>), both sections have equal sums and remain connected. Thus, the answer is <code>true</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[1,2,4],[2,3,5]]</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3548.Equal%20Sum%20Grid%20Partition%20II/images/chatgpt-image-apr-2-2025-at-02_50_29-am.png" style="height: 180px; width: 180px;" /></strong></p>

<ul>
	<li>A horizontal cut after the first row gives <code>1 + 2 + 4 = 7</code> and <code>2 + 3 + 5 = 10</code>.</li>
	<li>By discounting 3 from the bottom section (<code>10 - 3 = 7</code>), both sections have equal sums, but they do not remain connected as it splits the bottom section into two parts (<code>[2]</code> and <code>[5]</code>). Thus, the answer is <code>false</code>.</li>
</ul>
</div>

<p><strong class="example">Example 4:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[4,1,8],[3,2,6]]</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<p>No valid cut exists, so the answer is <code>false</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m == grid.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= n == grid[i].length &lt;= 10<sup>5</sup></code></li>
	<li><code>2 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Method 1: Enumerate Partition Lines

We can first enumerate horizontal partition lines, compute the element sum of each resulting part, and use hash maps to record the occurrence count of elements in each part.

For each partition line, we need to determine whether the sums of the two parts are equal, or whether removing one cell can make them equal. If the sums are equal, we directly return $\text{true}$. If the sums are not equal, we compute their difference $\textit{diff}$. If $\textit{diff}$ exists in the hash map of the larger part and satisfies the connectivity condition after removing that cell, we also return $\text{true}$.

The connectivity condition can be checked using the following criteria:

- The part has more than $1$ row and more than $1$ column.
- The part has exactly $1$ row, and the removed cell is on the boundary of that part (i.e., the first column or the last column).
- The part has exactly $1$ row, and the removed cell is on the boundary of that part (i.e., the first row or the last row).

Satisfying any one of the above conditions guarantees that the part remains connected after removing the cell.

We also need to enumerate vertical partition lines, which is similar to the horizontal case. To simplify the enumeration of vertical partition lines, we can first transpose the matrix and then apply the same logic.

The time complexity is $O(m \times n)$ and the space complexity is $O(m \times n)$, where $m$ and $n$ are the number of rows and columns of the matrix, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canPartitionGrid(self, grid: List[List[int]]) -> bool:
        def check(g: List[List[int]]) -> bool:
            m, n = len(g), len(g[0])
            s1 = s2 = 0
            cnt1 = defaultdict(int)
            cnt2 = defaultdict(int)
            for i, row in enumerate(g):
                for j, x in enumerate(row):
                    s2 += x
                    cnt2[x] += 1
            for i, row in enumerate(g[: m - 1]):
                for x in row:
                    s1 += x
                    s2 -= x
                    cnt1[x] += 1
                    cnt2[x] -= 1
                if s1 == s2:
                    return True
                if s1 < s2:
                    diff = s2 - s1
                    if cnt2[diff]:
                        if (
                            (m - i - 1 > 1 and n > 1)
                            or (
                                i == m - 2
                                and (g[i + 1][0] == diff or g[i + 1][-1] == diff)
                            )
                            or (n == 1 and (g[i + 1][0] == diff or g[-1][0] == diff))
                        ):
                            return True
                else:
                    diff = s1 - s2
                    if cnt1[diff]:
                        if (
                            (i + 1 > 1 and n > 1)
                            or (i == 0 and (g[0][0] == diff or g[0][-1] == diff))
                            or (n == 1 and (g[0][0] == diff or g[i][0] == diff))
                        ):
                            return True
            return False

        return check(grid) or check(list(zip(*grid)))
```

#### Java

```java
class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        return check(grid) || check(rotate(grid));
    }

    private boolean check(int[][] g) {
        int m = g.length, n = g[0].length;
        long s1 = 0, s2 = 0;

        Map<Long, Integer> cnt1 = new HashMap<>();
        Map<Long, Integer> cnt2 = new HashMap<>();

        for (int[] row : g) {
            for (int x : row) {
                s2 += x;
                cnt2.merge((long) x, 1, Integer::sum);
            }
        }

        for (int i = 0; i < m - 1; i++) {
            for (int x : g[i]) {
                s1 += x;
                s2 -= x;

                cnt1.merge((long) x, 1, Integer::sum);
                cnt2.merge((long) x, -1, Integer::sum);
            }

            if (s1 == s2) {
                return true;
            }

            if (s1 < s2) {
                long diff = s2 - s1;
                if (cnt2.getOrDefault(diff, 0) > 0) {
                    if ((m - i - 1 > 1 && n > 1)
                        || (i == m - 2 && (g[i + 1][0] == diff || g[i + 1][n - 1] == diff))
                        || (n == 1 && (g[i + 1][0] == diff || g[m - 1][0] == diff))) {
                        return true;
                    }
                }
            } else {
                long diff = s1 - s2;
                if (cnt1.getOrDefault(diff, 0) > 0) {
                    if ((i + 1 > 1 && n > 1) || (i == 0 && (g[0][0] == diff || g[0][n - 1] == diff))
                        || (n == 1 && (g[0][0] == diff || g[i][0] == diff))) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private int[][] rotate(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] t = new int[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                t[j][i] = grid[i][j];
            }
        }
        return t;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool canPartitionGrid(vector<vector<int>>& grid) {
        return check(grid) || check(rotate(grid));
    }

private:
    bool check(const vector<vector<int>>& g) {
        int m = g.size(), n = g[0].size();
        long long s1 = 0, s2 = 0;

        unordered_map<long long, int> cnt1, cnt2;

        for (auto& row : g) {
            for (int x : row) {
                s2 += x;
                cnt2[x]++;
            }
        }

        for (int i = 0; i < m - 1; i++) {
            for (int x : g[i]) {
                s1 += x;
                s2 -= x;
                cnt1[x]++;
                cnt2[x]--;
            }

            if (s1 == s2) return true;

            if (s1 < s2) {
                long long diff = s2 - s1;
                if (cnt2[diff] > 0) {
                    if (
                        (m - i - 1 > 1 && n > 1) || (i == m - 2 && (g[i + 1][0] == diff || g[i + 1][n - 1] == diff)) || (n == 1 && (g[i + 1][0] == diff || g[m - 1][0] == diff))) return true;
                }
            } else {
                long long diff = s1 - s2;
                if (cnt1[diff] > 0) {
                    if (
                        (i + 1 > 1 && n > 1) || (i == 0 && (g[0][0] == diff || g[0][n - 1] == diff)) || (n == 1 && (g[0][0] == diff || g[i][0] == diff))) return true;
                }
            }
        }

        return false;
    }

    vector<vector<int>> rotate(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<int>> t(n, vector<int>(m));
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                t[j][i] = grid[i][j];
            }
        }
        return t;
    }
};
```

#### Go

```go
func canPartitionGrid(grid [][]int) bool {
	return check(grid) || check(rotate(grid))
}

func check(g [][]int) bool {
	m, n := len(g), len(g[0])
	var s1, s2 int64

	cnt1 := map[int64]int{}
	cnt2 := map[int64]int{}

	for _, row := range g {
		for _, x := range row {
			v := int64(x)
			s2 += v
			cnt2[v]++
		}
	}

	for i := 0; i < m-1; i++ {
		for _, x := range g[i] {
			v := int64(x)
			s1 += v
			s2 -= v
			cnt1[v]++
			cnt2[v]--
		}

		if s1 == s2 {
			return true
		}

		if s1 < s2 {
			diff := s2 - s1
			if cnt2[diff] > 0 {
				if (m-i-1 > 1 && n > 1) ||
					(i == m-2 && (int64(g[i+1][0]) == diff || int64(g[i+1][n-1]) == diff)) ||
					(n == 1 && (int64(g[i+1][0]) == diff || int64(g[m-1][0]) == diff)) {
					return true
				}
			}
		} else {
			diff := s1 - s2
			if cnt1[diff] > 0 {
				if (i+1 > 1 && n > 1) ||
					(i == 0 && (int64(g[0][0]) == diff || int64(g[0][n-1]) == diff)) ||
					(n == 1 && (int64(g[0][0]) == diff || int64(g[i][0]) == diff)) {
					return true
				}
			}
		}
	}

	return false
}

func rotate(grid [][]int) [][]int {
	m, n := len(grid), len(grid[0])
	t := make([][]int, n)
	for i := range t {
		t[i] = make([]int, m)
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			t[j][i] = grid[i][j]
		}
	}
	return t
}
```

#### TypeScript

```ts
function canPartitionGrid(grid: number[][]): boolean {
    return check(grid) || check(rotate(grid));
}

function check(g: number[][]): boolean {
    const m = g.length,
        n = g[0].length;
    let s1 = 0,
        s2 = 0;

    const cnt1 = new Map<number, number>();
    const cnt2 = new Map<number, number>();

    for (const row of g) {
        for (const x of row) {
            s2 += x;
            cnt2.set(x, (cnt2.get(x) || 0) + 1);
        }
    }

    for (let i = 0; i < m - 1; i++) {
        for (const x of g[i]) {
            s1 += x;
            s2 -= x;

            cnt1.set(x, (cnt1.get(x) || 0) + 1);
            cnt2.set(x, (cnt2.get(x) || 0) - 1);
        }

        if (s1 === s2) return true;

        if (s1 < s2) {
            const diff = s2 - s1;
            if ((cnt2.get(diff) || 0) > 0) {
                if (
                    (m - i - 1 > 1 && n > 1) ||
                    (i === m - 2 && (g[i + 1][0] === diff || g[i + 1][n - 1] === diff)) ||
                    (n === 1 && (g[i + 1][0] === diff || g[m - 1][0] === diff))
                )
                    return true;
            }
        } else {
            const diff = s1 - s2;
            if ((cnt1.get(diff) || 0) > 0) {
                if (
                    (i + 1 > 1 && n > 1) ||
                    (i === 0 && (g[0][0] === diff || g[0][n - 1] === diff)) ||
                    (n === 1 && (g[0][0] === diff || g[i][0] === diff))
                )
                    return true;
            }
        }
    }

    return false;
}

function rotate(grid: number[][]): number[][] {
    const m = grid.length,
        n = grid[0].length;
    const t: number[][] = Array.from({ length: n }, () => Array(m).fill(0));

    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            t[j][i] = grid[i][j];
        }
    }

    return t;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
