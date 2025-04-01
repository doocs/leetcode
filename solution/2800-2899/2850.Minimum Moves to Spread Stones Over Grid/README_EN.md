---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2850.Minimum%20Moves%20to%20Spread%20Stones%20Over%20Grid/README_EN.md
rating: 2001
source: Weekly Contest 362 Q3
tags:
    - Breadth-First Search
    - Array
    - Dynamic Programming
    - Matrix
---

<!-- problem:start -->

# [2850. Minimum Moves to Spread Stones Over Grid](https://leetcode.com/problems/minimum-moves-to-spread-stones-over-grid)

[中文文档](/solution/2800-2899/2850.Minimum%20Moves%20to%20Spread%20Stones%20Over%20Grid/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> 2D integer matrix <code>grid</code> of size <code>3 * 3</code>, representing the number of stones in each cell. The grid contains exactly <code>9</code> stones, and there can be <strong>multiple</strong> stones in a single cell.</p>

<p>In one move, you can move a single stone from its current cell to any other cell if the two cells share a side.</p>

<p>Return <em>the <strong>minimum number of moves</strong> required to place one stone in each cell</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2800-2899/2850.Minimum%20Moves%20to%20Spread%20Stones%20Over%20Grid/images/example1-3.svg" style="width: 401px; height: 281px;" />
<pre>
<strong>Input:</strong> grid = [[1,1,0],[1,1,1],[1,2,1]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> One possible sequence of moves to place one stone in each cell is: 
1- Move one stone from cell (2,1) to cell (2,2).
2- Move one stone from cell (2,2) to cell (1,2).
3- Move one stone from cell (1,2) to cell (0,2).
In total, it takes 3 moves to place one stone in each cell of the grid.
It can be shown that 3 is the minimum number of moves required to place one stone in each cell.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2800-2899/2850.Minimum%20Moves%20to%20Spread%20Stones%20Over%20Grid/images/example2-2.svg" style="width: 401px; height: 281px;" />
<pre>
<strong>Input:</strong> grid = [[1,3,0],[1,0,0],[1,0,3]]
<strong>Output:</strong> 4
<strong>Explanation:</strong> One possible sequence of moves to place one stone in each cell is:
1- Move one stone from cell (0,1) to cell (0,2).
2- Move one stone from cell (0,1) to cell (1,1).
3- Move one stone from cell (2,2) to cell (1,2).
4- Move one stone from cell (2,2) to cell (2,1).
In total, it takes 4 moves to place one stone in each cell of the grid.
It can be shown that 4 is the minimum number of moves required to place one stone in each cell.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>grid.length == grid[i].length == 3</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 9</code></li>
	<li>Sum of <code>grid</code> is equal to <code>9</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Naive BFS

The problem is essentially finding the shortest path from the initial state to the target state in a state graph, so we can use BFS to solve it. The initial state is `grid`, and the target state is `[[1, 1, 1], [1, 1, 1], [1, 1, 1]]`. In each operation, we can move a stone greater than $1$ from a cell to an adjacent cell that does not exceed $1$. If the target state is found, we can return the current layer number, which is the minimum number of moves.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumMoves(self, grid: List[List[int]]) -> int:
        q = deque([tuple(tuple(row) for row in grid)])
        vis = set(q)
        ans = 0
        dirs = (-1, 0, 1, 0, -1)
        while 1:
            for _ in range(len(q)):
                cur = q.popleft()
                if all(x for row in cur for x in row):
                    return ans
                for i in range(3):
                    for j in range(3):
                        if cur[i][j] > 1:
                            for a, b in pairwise(dirs):
                                x, y = i + a, j + b
                                if 0 <= x < 3 and 0 <= y < 3 and cur[x][y] < 2:
                                    nxt = [list(row) for row in cur]
                                    nxt[i][j] -= 1
                                    nxt[x][y] += 1
                                    nxt = tuple(tuple(row) for row in nxt)
                                    if nxt not in vis:
                                        vis.add(nxt)
                                        q.append(nxt)
            ans += 1
```

#### Java

```java
class Solution {
    public int minimumMoves(int[][] grid) {
        Deque<String> q = new ArrayDeque<>();
        q.add(f(grid));
        Set<String> vis = new HashSet<>();
        vis.add(f(grid));
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int ans = 0;; ++ans) {
            for (int k = q.size(); k > 0; --k) {
                String p = q.poll();
                if ("111111111".equals(p)) {
                    return ans;
                }
                int[][] cur = g(p);
                for (int i = 0; i < 3; ++i) {
                    for (int j = 0; j < 3; ++j) {
                        if (cur[i][j] > 1) {
                            for (int d = 0; d < 4; ++d) {
                                int x = i + dirs[d];
                                int y = j + dirs[d + 1];
                                if (x >= 0 && x < 3 && y >= 0 && y < 3 && cur[x][y] < 2) {
                                    int[][] nxt = new int[3][3];
                                    for (int r = 0; r < 3; ++r) {
                                        for (int c = 0; c < 3; ++c) {
                                            nxt[r][c] = cur[r][c];
                                        }
                                    }
                                    nxt[i][j]--;
                                    nxt[x][y]++;
                                    String s = f(nxt);
                                    if (!vis.contains(s)) {
                                        vis.add(s);
                                        q.add(s);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private String f(int[][] grid) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : grid) {
            for (int x : row) {
                sb.append(x);
            }
        }
        return sb.toString();
    }

    private int[][] g(String s) {
        int[][] grid = new int[3][3];
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                grid[i][j] = s.charAt(i * 3 + j) - '0';
            }
        }
        return grid;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumMoves(vector<vector<int>>& grid) {
        queue<string> q;
        q.push(f(grid));
        unordered_set<string> vis;
        vis.insert(f(grid));
        vector<int> dirs = {-1, 0, 1, 0, -1};

        for (int ans = 0;; ++ans) {
            int sz = q.size();
            while (sz--) {
                string p = q.front();
                q.pop();
                if (p == "111111111") {
                    return ans;
                }
                vector<vector<int>> cur = g(p);

                for (int i = 0; i < 3; ++i) {
                    for (int j = 0; j < 3; ++j) {
                        if (cur[i][j] > 1) {
                            for (int d = 0; d < 4; ++d) {
                                int x = i + dirs[d];
                                int y = j + dirs[d + 1];
                                if (x >= 0 && x < 3 && y >= 0 && y < 3 && cur[x][y] < 2) {
                                    vector<vector<int>> nxt = cur;
                                    nxt[i][j]--;
                                    nxt[x][y]++;
                                    string s = f(nxt);
                                    if (!vis.count(s)) {
                                        vis.insert(s);
                                        q.push(s);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

private:
    string f(const vector<vector<int>>& grid) {
        string s;
        for (const auto& row : grid) {
            for (int x : row) {
                s += to_string(x);
            }
        }
        return s;
    }

    vector<vector<int>> g(const string& s) {
        vector<vector<int>> grid(3, vector<int>(3));
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                grid[i][j] = s[i * 3 + j] - '0';
            }
        }
        return grid;
    }
};
```

#### Go

```go
type Queue []string

func (q *Queue) Push(s string) {
	*q = append(*q, s)
}

func (q *Queue) Pop() string {
	s := (*q)[0]
	*q = (*q)[1:]
	return s
}

func (q *Queue) Empty() bool {
	return len(*q) == 0
}

func minimumMoves(grid [][]int) int {
	q := Queue{f(grid)}
	vis := map[string]bool{f(grid): true}
	dirs := []int{-1, 0, 1, 0, -1}

	for ans := 0; ; ans++ {
		sz := len(q)
		for ; sz > 0; sz-- {
			p := q.Pop()
			if p == "111111111" {
				return ans
			}
			cur := g(p)

			for i := 0; i < 3; i++ {
				for j := 0; j < 3; j++ {
					if cur[i][j] > 1 {
						for d := 0; d < 4; d++ {
							x, y := i+dirs[d], j+dirs[d+1]
							if x >= 0 && x < 3 && y >= 0 && y < 3 && cur[x][y] < 2 {
								nxt := make([][]int, 3)
								for r := range nxt {
									nxt[r] = append([]int(nil), cur[r]...)
								}
								nxt[i][j]--
								nxt[x][y]++
								s := f(nxt)
								if !vis[s] {
									vis[s] = true
									q.Push(s)
								}
							}
						}
					}
				}
			}
		}
	}
}

func f(grid [][]int) string {
	var sb strings.Builder
	for _, row := range grid {
		for _, x := range row {
			sb.WriteByte(byte(x) + '0')
		}
	}
	return sb.String()
}

func g(s string) [][]int {
	grid := make([][]int, 3)
	for i := range grid {
		grid[i] = make([]int, 3)
		for j := 0; j < 3; j++ {
			grid[i][j] = int(s[i*3+j] - '0')
		}
	}
	return grid
}
```

#### TypeScript

```ts
function minimumMoves(grid: number[][]): number {
    const q: string[] = [f(grid)];
    const vis: Set<string> = new Set([f(grid)]);
    const dirs: number[] = [-1, 0, 1, 0, -1];

    for (let ans = 0; ; ans++) {
        let sz = q.length;
        while (sz-- > 0) {
            const p = q.shift()!;
            if (p === '111111111') {
                return ans;
            }
            const cur = g(p);

            for (let i = 0; i < 3; i++) {
                for (let j = 0; j < 3; j++) {
                    if (cur[i][j] > 1) {
                        for (let d = 0; d < 4; d++) {
                            const x = i + dirs[d],
                                y = j + dirs[d + 1];
                            if (x >= 0 && x < 3 && y >= 0 && y < 3 && cur[x][y] < 2) {
                                const nxt = cur.map(row => [...row]);
                                nxt[i][j]--;
                                nxt[x][y]++;
                                const s = f(nxt);
                                if (!vis.has(s)) {
                                    vis.add(s);
                                    q.push(s);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

function f(grid: number[][]): string {
    return grid.flat().join('');
}

function g(s: string): number[][] {
    return Array.from({ length: 3 }, (_, i) =>
        Array.from({ length: 3 }, (_, j) => Number(s[i * 3 + j])),
    );
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: State Compression Dynamic Programming

We can put all the coordinates $(i, j)$ of cells with a value of $0$ into an array $left$. If the value $v$ of a cell is greater than $1$, we put $v-1$ coordinates $(i, j)$ into an array $right$. The problem then becomes that each coordinate $(i, j)$ in $right$ needs to be moved to a coordinate $(x, y)$ in $left$, and we need to find the minimum number of moves.

Let's denote the length of $left$ as $n$. We can use an $n$-bit binary number to represent whether each coordinate in $left$ is filled by a coordinate in $right$, where $1$ represents being filled, and $0$ represents not being filled. Initially, $f[i] = \infty$, and the rest $f[0]=0$.

Consider $f[i]$, let the number of $1$s in the binary representation of $i$ be $k$. We enumerate $j$ in the range $[0..n)$, if the $j$th bit of $i$ is $1$, then $f[i]$ can be transferred from $f[i \oplus (1 << j)]$, and the cost of the transfer is $cal(left[k-1], right[j])$, where $cal$ represents the Manhattan distance between two coordinates. The final answer is $f[(1 << n) - 1]$.

The time complexity is $O(n \times 2^n)$, and the space complexity is $O(2^n)$. Here, $n$ is the length of $left$, and in this problem, $n \le 9$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumMoves(self, grid: List[List[int]]) -> int:
        def cal(a: tuple, b: tuple) -> int:
            return abs(a[0] - b[0]) + abs(a[1] - b[1])

        left, right = [], []
        for i in range(3):
            for j in range(3):
                if grid[i][j] == 0:
                    left.append((i, j))
                else:
                    for _ in range(grid[i][j] - 1):
                        right.append((i, j))

        n = len(left)
        f = [inf] * (1 << n)
        f[0] = 0
        for i in range(1, 1 << n):
            k = i.bit_count()
            for j in range(n):
                if i >> j & 1:
                    f[i] = min(f[i], f[i ^ (1 << j)] + cal(left[k - 1], right[j]))
        return f[-1]
```

#### Java

```java
class Solution {
    public int minimumMoves(int[][] grid) {
        List<int[]> left = new ArrayList<>();
        List<int[]> right = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (grid[i][j] == 0) {
                    left.add(new int[] {i, j});
                } else {
                    for (int k = 1; k < grid[i][j]; ++k) {
                        right.add(new int[] {i, j});
                    }
                }
            }
        }
        int n = left.size();
        int[] f = new int[1 << n];
        Arrays.fill(f, 1 << 30);
        f[0] = 0;
        for (int i = 1; i < 1 << n; ++i) {
            int k = Integer.bitCount(i);
            for (int j = 0; j < n; ++j) {
                if ((i >> j & 1) == 1) {
                    f[i] = Math.min(f[i], f[i ^ (1 << j)] + cal(left.get(k - 1), right.get(j)));
                }
            }
        }
        return f[(1 << n) - 1];
    }

    private int cal(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumMoves(vector<vector<int>>& grid) {
        using pii = pair<int, int>;
        vector<pii> left, right;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (grid[i][j] == 0) {
                    left.emplace_back(i, j);
                } else {
                    for (int k = 1; k < grid[i][j]; ++k) {
                        right.emplace_back(i, j);
                    }
                }
            }
        }
        auto cal = [](pii a, pii b) {
            return abs(a.first - b.first) + abs(a.second - b.second);
        };
        int n = left.size();
        int f[1 << n];
        memset(f, 0x3f, sizeof(f));
        f[0] = 0;
        for (int i = 1; i < 1 << n; ++i) {
            int k = __builtin_popcount(i);
            for (int j = 0; j < n; ++j) {
                if (i >> j & 1) {
                    f[i] = min(f[i], f[i ^ (1 << j)] + cal(left[k - 1], right[j]));
                }
            }
        }
        return f[(1 << n) - 1];
    }
};
```

#### Go

```go
func minimumMoves(grid [][]int) int {
	left := [][2]int{}
	right := [][2]int{}
	for i := 0; i < 3; i++ {
		for j := 0; j < 3; j++ {
			if grid[i][j] == 0 {
				left = append(left, [2]int{i, j})
			} else {
				for k := 1; k < grid[i][j]; k++ {
					right = append(right, [2]int{i, j})
				}
			}
		}
	}
	cal := func(a, b [2]int) int {
		return abs(a[0]-b[0]) + abs(a[1]-b[1])
	}
	n := len(left)
	f := make([]int, 1<<n)
	f[0] = 0
	for i := 1; i < 1<<n; i++ {
		f[i] = 1 << 30
		k := bits.OnesCount(uint(i))
		for j := 0; j < n; j++ {
			if i>>j&1 == 1 {
				f[i] = min(f[i], f[i^(1<<j)]+cal(left[k-1], right[j]))
			}
		}
	}
	return f[(1<<n)-1]
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
function minimumMoves(grid: number[][]): number {
    const left: number[][] = [];
    const right: number[][] = [];
    for (let i = 0; i < 3; ++i) {
        for (let j = 0; j < 3; ++j) {
            if (grid[i][j] === 0) {
                left.push([i, j]);
            } else {
                for (let k = 1; k < grid[i][j]; ++k) {
                    right.push([i, j]);
                }
            }
        }
    }
    const cal = (a: number[], b: number[]) => {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    };
    const n = left.length;
    const f: number[] = Array(1 << n).fill(1 << 30);
    f[0] = 0;
    for (let i = 0; i < 1 << n; ++i) {
        let k = 0;
        for (let j = 0; j < n; ++j) {
            if ((i >> j) & 1) {
                ++k;
            }
        }
        for (let j = 0; j < n; ++j) {
            if ((i >> j) & 1) {
                f[i] = Math.min(f[i], f[i ^ (1 << j)] + cal(left[k - 1], right[j]));
            }
        }
    }
    return f[(1 << n) - 1];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
