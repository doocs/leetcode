---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0840.Magic%20Squares%20In%20Grid/README_EN.md
tags:
    - Array
    - Hash Table
    - Math
    - Matrix
---

<!-- problem:start -->

# [840. Magic Squares In Grid](https://leetcode.com/problems/magic-squares-in-grid)

[中文文档](/solution/0800-0899/0840.Magic%20Squares%20In%20Grid/README.md)

## Description

<!-- description:start -->

<p>A <code>3 x 3</code> <strong>magic square</strong> is a <code>3 x 3</code> grid filled with distinct numbers <strong>from </strong>1<strong> to </strong>9 such that each row, column, and both diagonals all have the same sum.</p>

<p>Given a <code>row x col</code> <code>grid</code> of integers, how many <code>3 x 3</code> contiguous magic square subgrids are there?</p>

<p>Note: while a magic square can only contain numbers from 1 to 9, <code>grid</code> may contain numbers up to 15.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0840.Magic%20Squares%20In%20Grid/images/magic_main.jpg" style="width: 322px; height: 242px;" />
<pre>
<strong>Input:</strong> grid = [[4,3,8,4],[9,5,1,9],[2,7,6,2]]
<strong>Output:</strong> 1
<strong>Explanation: </strong>
The following subgrid is a 3 x 3 magic square:
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0840.Magic%20Squares%20In%20Grid/images/magic_valid.jpg" style="width: 242px; height: 242px;" />
while this one is not:
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0840.Magic%20Squares%20In%20Grid/images/magic_invalid.jpg" style="width: 242px; height: 242px;" />
In total, there is only one magic square inside the given grid.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> grid = [[8]]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>row == grid.length</code></li>
	<li><code>col == grid[i].length</code></li>
	<li><code>1 &lt;= row, col &lt;= 10</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 15</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

We directly enumerate the top-left coordinates $(i, j)$ of each $3 \times 3$ sub-matrix, then check whether the sub-matrix satisfies the "magic square" condition. If it does, increment the answer by one. After enumeration, return the answer.

Time complexity is $O(m \times n)$, where $m$ and $n$ are the number of rows and columns of the matrix, respectively. Space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numMagicSquaresInside(self, grid: List[List[int]]) -> int:
        def check(i: int, j: int) -> int:
            if i + 3 > m or j + 3 > n:
                return 0
            s = set()
            row = [0] * 3
            col = [0] * 3
            a = b = 0
            for x in range(i, i + 3):
                for y in range(j, j + 3):
                    v = grid[x][y]
                    if v < 1 or v > 9:
                        return 0
                    s.add(v)
                    row[x - i] += v
                    col[y - j] += v
                    if x - i == y - j:
                        a += v
                    if x - i == 2 - (y - j):
                        b += v
            if len(s) != 9 or a != b:
                return 0
            if any(x != a for x in row) or any(x != a for x in col):
                return 0
            return 1

        m, n = len(grid), len(grid[0])
        return sum(check(i, j) for i in range(m) for j in range(n))
```

#### Java

```java
class Solution {
    private int m;
    private int n;
    private int[][] grid;

    public int numMagicSquaresInside(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans += check(i, j);
            }
        }
        return ans;
    }

    private int check(int i, int j) {
        if (i + 3 > m || j + 3 > n) {
            return 0;
        }
        int[] cnt = new int[16];
        int[] row = new int[3];
        int[] col = new int[3];
        int a = 0, b = 0;
        for (int x = i; x < i + 3; ++x) {
            for (int y = j; y < j + 3; ++y) {
                int v = grid[x][y];
                if (v < 1 || v > 9 || ++cnt[v] > 1) {
                    return 0;
                }
                row[x - i] += v;
                col[y - j] += v;
                if (x - i == y - j) {
                    a += v;
                }
                if (x - i + y - j == 2) {
                    b += v;
                }
            }
        }
        if (a != b) {
            return 0;
        }
        for (int k = 0; k < 3; ++k) {
            if (row[k] != a || col[k] != a) {
                return 0;
            }
        }
        return 1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numMagicSquaresInside(vector<vector<int>>& grid) {
        int m = grid.size();
        int n = grid[0].size();
        int ans = 0;
        auto check = [&](int i, int j) {
            if (i + 3 > m || j + 3 > n) {
                return 0;
            }
            vector<int> cnt(16);
            vector<int> row(3);
            vector<int> col(3);
            int a = 0, b = 0;
            for (int x = i; x < i + 3; ++x) {
                for (int y = j; y < j + 3; ++y) {
                    int v = grid[x][y];
                    if (v < 1 || v > 9 || ++cnt[v] > 1) {
                        return 0;
                    }
                    row[x - i] += v;
                    col[y - j] += v;
                    if (x - i == y - j) {
                        a += v;
                    }
                    if (x - i + y - j == 2) {
                        b += v;
                    }
                }
            }
            if (a != b) {
                return 0;
            }
            for (int k = 0; k < 3; ++k) {
                if (row[k] != a || col[k] != a) {
                    return 0;
                }
            }
            return 1;
        };
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans += check(i, j);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func numMagicSquaresInside(grid [][]int) (ans int) {
	m, n := len(grid), len(grid[0])
	check := func(i, j int) int {
		if i+3 > m || j+3 > n {
			return 0
		}
		cnt := [16]int{}
		row := [3]int{}
		col := [3]int{}
		a, b := 0, 0
		for x := i; x < i+3; x++ {
			for y := j; y < j+3; y++ {
				v := grid[x][y]
				if v < 1 || v > 9 || cnt[v] > 0 {
					return 0
				}
				cnt[v]++
				row[x-i] += v
				col[y-j] += v
				if x-i == y-j {
					a += v
				}
				if x-i == 2-(y-j) {
					b += v
				}
			}
		}
		if a != b {
			return 0
		}
		for k := 0; k < 3; k++ {
			if row[k] != a || col[k] != a {
				return 0
			}
		}
		return 1
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			ans += check(i, j)
		}
	}
	return
}
```

#### TypeScript

```ts
function numMagicSquaresInside(grid: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    const check = (i: number, j: number): number => {
        if (i + 3 > m || j + 3 > n) {
            return 0;
        }
        const cnt: number[] = Array(16).fill(0);
        const row: number[] = Array(3).fill(0);
        const col: number[] = Array(3).fill(0);
        let [a, b] = [0, 0];
        for (let x = i; x < i + 3; ++x) {
            for (let y = j; y < j + 3; ++y) {
                const v = grid[x][y];
                if (v < 1 || v > 9 || ++cnt[v] > 1) {
                    return 0;
                }
                row[x - i] += v;
                col[y - j] += v;
                if (x - i === y - j) {
                    a += v;
                }
                if (x - i === 2 - (y - j)) {
                    b += v;
                }
            }
        }
        if (a !== b) {
            return 0;
        }
        for (let k = 0; k < 3; ++k) {
            if (row[k] !== a || col[k] !== a) {
                return 0;
            }
        }
        return 1;
    };
    let ans = 0;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            ans += check(i, j);
        }
    }
    return ans;
}
```

#### JavaScript

```js
function numMagicSquaresInside(grid) {
    const m = grid.length;
    const n = grid[0].length;
    const check = (i, j) => {
        if (i + 3 > m || j + 3 > n) {
            return 0;
        }
        const cnt = Array(16).fill(0);
        const row = Array(3).fill(0);
        const col = Array(3).fill(0);
        let [a, b] = [0, 0];
        for (let x = i; x < i + 3; ++x) {
            for (let y = j; y < j + 3; ++y) {
                const v = grid[x][y];
                if (v < 1 || v > 9 || ++cnt[v] > 1) {
                    return 0;
                }
                row[x - i] += v;
                col[y - j] += v;
                if (x - i === y - j) {
                    a += v;
                }
                if (x - i === 2 - (y - j)) {
                    b += v;
                }
            }
        }
        if (a !== b) {
            return 0;
        }
        for (let k = 0; k < 3; ++k) {
            if (row[k] !== a || col[k] !== a) {
                return 0;
            }
        }
        return 1;
    };
    let ans = 0;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            ans += check(i, j);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2

<!-- tabs:start -->

#### TypeScript

```ts
export function numMagicSquaresInside(grid: number[][]): number {
    const [m, n] = [grid.length, grid[0].length];
    if (m < 3 || n < 3) return 0;

    const check = (y: number, x: number) => {
        const g = grid;
        if (g[y + 1][x + 1] !== 5) return 0;

        const cells = [
            g[y][x],
            g[y][x + 1],
            g[y][x + 2],
            g[y + 1][x + 2],
            g[y + 2][x + 2],
            g[y + 2][x + 1],
            g[y + 2][x],
            g[y + 1][x],
        ];

        const i = cells.indexOf(2);
        if (i === -1) return 0;
        cells.push(...cells.splice(0, i));

        const circle = [2, 9, 4, 3, 8, 1, 6, 7];
        const reverseCircle = [2, 7, 6, 1, 8, 3, 4, 9];

        if (cells.every((x, i) => x === circle[i])) return 1;
        if (cells.every((x, i) => x === reverseCircle[i])) return 1;

        return 0;
    };

    let res = 0;
    for (let i = 0; i < m - 2; i++) {
        for (let j = 0; j < n - 2; j++) {
            res += check(i, j);
        }
    }

    return res;
}
```

#### JavaScript

```js
function numMagicSquaresInside(grid) {
    const [m, n] = [grid.length, grid[0].length];
    if (m < 3 || n < 3) return 0;

    const check = (y, x) => {
        const g = grid;
        if (g[y + 1][x + 1] !== 5) return false;

        const cells = [
            g[y][x],
            g[y][x + 1],
            g[y][x + 2],
            g[y + 1][x + 2],
            g[y + 2][x + 2],
            g[y + 2][x + 1],
            g[y + 2][x],
            g[y + 1][x],
        ];

        const i = cells.indexOf(2);
        if (i === -1) return false;
        cells.push(...cells.splice(0, i));

        const circle = [2, 9, 4, 3, 8, 1, 6, 7];
        const reverseCircle = [2, 7, 6, 1, 8, 3, 4, 9];

        if (cells.every((x, i) => x === circle[i])) return true;
        if (cells.every((x, i) => x === reverseCircle[i])) return true;

        return false;
    };

    let res = 0;
    for (let i = 0; i < m - 2; i++) {
        for (let j = 0; j < n - 2; j++) {
            res += +check(i, j);
        }
    }

    return res;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
