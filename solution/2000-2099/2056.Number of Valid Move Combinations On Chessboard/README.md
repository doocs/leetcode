---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2056.Number%20of%20Valid%20Move%20Combinations%20On%20Chessboard/README.md
rating: 2610
source: 第 64 场双周赛 Q4
tags:
    - 数组
    - 字符串
    - 回溯
    - 模拟
---

<!-- problem:start -->

# [2056. 棋盘上有效移动组合的数目](https://leetcode.cn/problems/number-of-valid-move-combinations-on-chessboard)

[English Version](/solution/2000-2099/2056.Number%20of%20Valid%20Move%20Combinations%20On%20Chessboard/README_EN.md)

## 题目描述

<!-- description:start -->

<p>有一个&nbsp;<code>8 x 8</code>&nbsp;的棋盘，它包含&nbsp;<code>n</code>&nbsp;个棋子（棋子包括车，后和象三种）。给你一个长度为 <code>n</code>&nbsp;的字符串数组&nbsp;<code>pieces</code>&nbsp;，其中&nbsp;<code>pieces[i]</code>&nbsp;表示第 <code>i</code>&nbsp;个棋子的类型（车，后或象）。除此以外，还给你一个长度为 <code>n</code>&nbsp;的二维整数数组&nbsp;<code>positions</code>&nbsp;，其中 <code>positions[i] = [r<sub>i</sub>, c<sub>i</sub>]</code>&nbsp;表示第 <code>i</code>&nbsp;个棋子现在在棋盘上的位置为&nbsp;<code>(r<sub>i</sub>, c<sub>i</sub>)</code>&nbsp;，棋盘下标从 <strong>1</strong>&nbsp;开始。</p>

<p>每个棋子的移动中，首先选择移动的 <strong>方向</strong>&nbsp;，然后选择 <strong>移动的步数</strong>&nbsp;，同时你要确保移动过程中棋子不能移到棋盘以外的地方。棋子需按照以下规则移动：</p>

<ul>
	<li>车可以 <strong>水平或者竖直</strong>&nbsp;从&nbsp;<code>(r, c)</code>&nbsp;沿着方向&nbsp;<code>(r+1, c)</code>，<code>(r-1, c)</code>，<code>(r, c+1)</code>&nbsp;或者&nbsp;<code>(r, c-1)</code>&nbsp;移动。</li>
	<li>后可以 <strong>水平竖直或者斜对角</strong>&nbsp;从&nbsp;<code>(r, c)</code> 沿着方向&nbsp;<code>(r+1, c)</code>，<code>(r-1, c)</code>，<code>(r, c+1)</code>，<code>(r, c-1)</code>，<code>(r+1, c+1)</code>，<code>(r+1, c-1)</code>，<code>(r-1, c+1)</code>，<code>(r-1, c-1)</code>&nbsp;移动。</li>
	<li>象可以 <strong>斜对角</strong>&nbsp;从&nbsp;<code>(r, c)</code>&nbsp;沿着方向&nbsp;<code>(r+1, c+1)</code>，<code>(r+1, c-1)</code>，<code>(r-1, c+1)</code>，<code>(r-1, c-1)</code>&nbsp;移动。</li>
</ul>

<p>你必须同时 <strong>移动</strong> 棋盘上的每一个棋子。<strong>移动组合</strong>&nbsp;包含所有棋子的 <strong>移动</strong>&nbsp;。每一秒，每个棋子都沿着它们选择的方向往前移动 <strong>一步</strong>&nbsp;，直到它们到达目标位置。所有棋子从时刻 <code>0</code>&nbsp;开始移动。如果在某个时刻，两个或者更多棋子占据了同一个格子，那么这个移动组合 <strong>不有效</strong>&nbsp;。</p>

<p>请你返回 <strong>有效</strong>&nbsp;移动组合的数目。</p>

<p><strong>注意：</strong></p>

<ul>
	<li>初始时，<strong>不会有两个棋子</strong>&nbsp;在 <strong>同一个位置 。</strong></li>
	<li>有可能在一个移动组合中，有棋子不移动。</li>
	<li>如果两个棋子 <strong>直接相邻</strong>&nbsp;且两个棋子下一秒要互相占据对方的位置，可以将它们在同一秒内 <strong>交换位置</strong>&nbsp;。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2056.Number%20of%20Valid%20Move%20Combinations%20On%20Chessboard/images/a1.png" style="width: 215px; height: 215px;" /></p>

<pre>
<b>输入：</b>pieces = ["rook"], positions = [[1,1]]
<b>输出：</b>15
<b>解释：</b>上图展示了棋子所有可能的移动。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2056.Number%20of%20Valid%20Move%20Combinations%20On%20Chessboard/images/a2.png" style="width: 215px; height: 215px;" /></p>

<pre>
<b>输入：</b>pieces = ["queen"], positions = [[1,1]]
<b>输出：</b>22
<b>解释：</b>上图展示了棋子所有可能的移动。
</pre>

<p><strong>示例 3:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2056.Number%20of%20Valid%20Move%20Combinations%20On%20Chessboard/images/a3.png" style="width: 214px; height: 215px;" /></p>

<pre>
<b>输入：</b>pieces = ["bishop"], positions = [[4,3]]
<b>输出：</b>12
<b>解释：</b>上图展示了棋子所有可能的移动。
</pre>

<p><strong>示例 4:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2056.Number%20of%20Valid%20Move%20Combinations%20On%20Chessboard/images/a4.png" style="width: 216px; height: 219px;" /></p>

<pre>
<b>输入：</b>pieces = ["rook","rook"], positions = [[1,1],[8,8]]
<b>输出：</b>223
<b>解释：</b>每个车有 15 种移动，所以总共有 15 * 15 = 225 种移动组合。
但是，有两个是不有效的移动组合：
- 将两个车都移动到 (8, 1) ，会导致它们在同一个格子相遇。
- 将两个车都移动到 (1, 8) ，会导致它们在同一个格子相遇。
所以，总共有 225 - 2 = 223 种有效移动组合。
注意，有两种有效的移动组合，分别是一个车在 (1, 8) ，另一个车在 (8, 1) 。
即使棋盘状态是相同的，这两个移动组合被视为不同的，因为每个棋子移动操作是不相同的。
</pre>

<p><strong>示例 5：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2056.Number%20of%20Valid%20Move%20Combinations%20On%20Chessboard/images/a5.png" style="width: 214px; height: 213px;" /></p>

<pre>
<b>输入：</b>pieces = ["queen","bishop"], positions = [[5,7],[3,4]]
<b>输出：</b>281
<b>解释：</b>总共有 12 * 24 = 288 种移动组合。
但是，有一些不有效的移动组合：
- 如果后停在 (6, 7) ，它会阻挡象到达 (6, 7) 或者 (7, 8) 。
- 如果后停在 (5, 6) ，它会阻挡象到达 (5, 6) ，(6, 7) 或者 (7, 8) 。
- 如果象停在 (5, 2) ，它会阻挡后到达 (5, 2) 或者 (5, 1) 。
在 288 个移动组合当中，281 个是有效的。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == pieces.length </code></li>
	<li><code>n == positions.length</code></li>
	<li><code>1 &lt;= n &lt;= 4</code></li>
	<li><code>pieces</code>&nbsp;只包含字符串&nbsp;<code>"rook"</code>&nbsp;，<code>"queen"</code>&nbsp;和&nbsp;<code>"bishop"</code>&nbsp;。</li>
	<li>棋盘上最多只有一个后。</li>
	<li><code>1 &lt;= r<sub>i</sub>, c<sub>i</sub> &lt;= 8</code></li>
	<li>每一个&nbsp;<code>positions[i]</code>&nbsp;互不相同。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS

题目最多只有 $4$ 个棋子，每个棋子的移动方向最多有 $8$ 种，我们可以考虑使用 DFS 搜索所有的移动组合。

我们按照顺序依次枚举每个棋子，对于每个棋子，我们可以选择不移动，或者按照规则移动，用数组 $\textit{dist}[i]$ 记录第 $i$ 个棋子的移动情况，其中 $\textit{dist}[i][x][y]$ 表示第 $i$ 个棋子经过坐标 $(x, y)$ 时的时间，用数组 $\textit{end}[i]$ 记录第 $i$ 个棋子的终点坐标和时间。在搜索时，我们需要分别判断当前棋子是否可以停止移动，以及当前棋子是否可以继续在当前方向移动。

我们定义方法 $\text{checkStop}(i, x, y, t)$ 判断第 $i$ 个棋子是否在时间 $t$ 时停在坐标 $(x, y)$，如果对于此前的所有棋子 $j$，都有 $\textit{dist}[j][x][y] < t$，那么第 $i$ 个棋子可以停止移动。

另外，我们定义方法 $\text{checkPass}(i, x, y, t)$ 判断第 $i$ 个棋子是否可以在时间 $t$ 时经过坐标 $(x, y)$。如果此前有其它棋子 $j$ 也在时间 $t$ 经过坐标 $(x, y)$，或者有其它棋子 $j$ 停在 $(x, y)$，且时间不超过 $t$，那么第 $i$ 个棋子不能在时间 $t$ 时经过坐标 $(x, y)$。

时间复杂度 $O((n \times M)^n)$，空间复杂度 $O(n \times M)$。其中 $n$ 是棋子的数量，而 $M$ 是每个棋子的移动范围。

<!-- tabs:start -->

#### Python3

```python
rook_dirs = [(1, 0), (-1, 0), (0, 1), (0, -1)]
bishop_dirs = [(1, 1), (1, -1), (-1, 1), (-1, -1)]
queue_dirs = rook_dirs + bishop_dirs


def get_dirs(piece: str) -> List[Tuple[int, int]]:
    match piece[0]:
        case "r":
            return rook_dirs
        case "b":
            return bishop_dirs
        case _:
            return queue_dirs


class Solution:
    def countCombinations(self, pieces: List[str], positions: List[List[int]]) -> int:
        def check_stop(i: int, x: int, y: int, t: int) -> bool:
            return all(dist[j][x][y] < t for j in range(i))

        def check_pass(i: int, x: int, y: int, t: int) -> bool:
            for j in range(i):
                if dist[j][x][y] == t:
                    return False
                if end[j][0] == x and end[j][1] == y and end[j][2] <= t:
                    return False
            return True

        def dfs(i: int) -> None:
            if i >= n:
                nonlocal ans
                ans += 1
                return
            x, y = positions[i]
            dist[i][:] = [[-1] * m for _ in range(m)]
            dist[i][x][y] = 0
            end[i] = (x, y, 0)
            if check_stop(i, x, y, 0):
                dfs(i + 1)
            dirs = get_dirs(pieces[i])
            for dx, dy in dirs:
                dist[i][:] = [[-1] * m for _ in range(m)]
                dist[i][x][y] = 0
                nx, ny, nt = x + dx, y + dy, 1
                while 1 <= nx < m and 1 <= ny < m and check_pass(i, nx, ny, nt):
                    dist[i][nx][ny] = nt
                    end[i] = (nx, ny, nt)
                    if check_stop(i, nx, ny, nt):
                        dfs(i + 1)
                    nx += dx
                    ny += dy
                    nt += 1

        n = len(pieces)
        m = 9
        dist = [[[-1] * m for _ in range(m)] for _ in range(n)]
        end = [(0, 0, 0) for _ in range(n)]
        ans = 0
        dfs(0)
        return ans
```

#### Java

```java
class Solution {
    int n, m = 9, ans;
    int[][][] dist;
    int[][] end;
    String[] pieces;
    int[][] positions;
    int[][] rookDirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int[][] bishopDirs = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    int[][] queenDirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    public int countCombinations(String[] pieces, int[][] positions) {
        n = pieces.length;
        dist = new int[n][m][m];
        end = new int[n][3];
        ans = 0;
        this.pieces = pieces;
        this.positions = positions;

        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i >= n) {
            ans++;
            return;
        }

        int x = positions[i][0], y = positions[i][1];
        resetDist(i);
        dist[i][x][y] = 0;
        end[i] = new int[] {x, y, 0};

        if (checkStop(i, x, y, 0)) {
            dfs(i + 1);
        }

        int[][] dirs = getDirs(pieces[i]);
        for (int[] dir : dirs) {
            resetDist(i);
            dist[i][x][y] = 0;
            int nx = x + dir[0], ny = y + dir[1], nt = 1;

            while (isValid(nx, ny) && checkPass(i, nx, ny, nt)) {
                dist[i][nx][ny] = nt;
                end[i] = new int[] {nx, ny, nt};
                if (checkStop(i, nx, ny, nt)) {
                    dfs(i + 1);
                }
                nx += dir[0];
                ny += dir[1];
                nt++;
            }
        }
    }

    private void resetDist(int i) {
        for (int j = 0; j < m; j++) {
            for (int k = 0; k < m; k++) {
                dist[i][j][k] = -1;
            }
        }
    }

    private boolean checkStop(int i, int x, int y, int t) {
        for (int j = 0; j < i; j++) {
            if (dist[j][x][y] >= t) {
                return false;
            }
        }
        return true;
    }

    private boolean checkPass(int i, int x, int y, int t) {
        for (int j = 0; j < i; j++) {
            if (dist[j][x][y] == t) {
                return false;
            }
            if (end[j][0] == x && end[j][1] == y && end[j][2] <= t) {
                return false;
            }
        }
        return true;
    }

    private boolean isValid(int x, int y) {
        return x >= 1 && x < m && y >= 1 && y < m;
    }

    private int[][] getDirs(String piece) {
        char c = piece.charAt(0);
        return switch (c) {
            case 'r' -> rookDirs;
            case 'b' -> bishopDirs;
            default -> queenDirs;
        };
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countCombinations(vector<string>& pieces, vector<vector<int>>& positions) {
        int n = pieces.size();
        const int m = 9;
        int ans = 0;

        vector<vector<vector<int>>> dist(n, vector<vector<int>>(m, vector<int>(m, -1)));
        vector<vector<int>> end(n, vector<int>(3));

        const int rookDirs[4][2] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        const int bishopDirs[4][2] = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        const int queenDirs[8][2] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

        auto resetDist = [&](int i) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < m; k++) {
                    dist[i][j][k] = -1;
                }
            }
        };

        auto checkStop = [&](int i, int x, int y, int t) -> bool {
            for (int j = 0; j < i; j++) {
                if (dist[j][x][y] >= t) {
                    return false;
                }
            }
            return true;
        };

        auto checkPass = [&](int i, int x, int y, int t) -> bool {
            for (int j = 0; j < i; j++) {
                if (dist[j][x][y] == t) {
                    return false;
                }
                if (end[j][0] == x && end[j][1] == y && end[j][2] <= t) {
                    return false;
                }
            }
            return true;
        };

        auto isValid = [&](int x, int y) -> bool {
            return x >= 1 && x < m && y >= 1 && y < m;
        };

        auto getDirs = [&](const string& piece) -> const int(*)[2] {
            char c = piece[0];
            if (c == 'r') {
                return rookDirs;
            }
            if (c == 'b') {
                return bishopDirs;
            }
            return queenDirs;
        };

        auto dfs = [&](this auto&& dfs, int i) -> void {
            if (i >= n) {
                ans++;
                return;
            }

            int x = positions[i][0], y = positions[i][1];
            resetDist(i);
            dist[i][x][y] = 0;
            end[i] = {x, y, 0};

            if (checkStop(i, x, y, 0)) {
                dfs(i + 1);
            }

            const int(*dirs)[2] = getDirs(pieces[i]);
            int dirsSize = (pieces[i][0] == 'q') ? 8 : 4;

            for (int d = 0; d < dirsSize; d++) {
                resetDist(i);
                dist[i][x][y] = 0;
                int nx = x + dirs[d][0], ny = y + dirs[d][1], nt = 1;

                while (isValid(nx, ny) && checkPass(i, nx, ny, nt)) {
                    dist[i][nx][ny] = nt;
                    end[i] = {nx, ny, nt};
                    if (checkStop(i, nx, ny, nt)) {
                        dfs(i + 1);
                    }
                    nx += dirs[d][0];
                    ny += dirs[d][1];
                    nt++;
                }
            }
        };

        dfs(0);
        return ans;
    }
};
```

#### Go

```go
func countCombinations(pieces []string, positions [][]int) (ans int) {
	n := len(pieces)
	m := 9
	dist := make([][][]int, n)
	for i := range dist {
		dist[i] = make([][]int, m)
		for j := range dist[i] {
			dist[i][j] = make([]int, m)
		}
	}

	end := make([][3]int, n)

	rookDirs := [][2]int{{1, 0}, {-1, 0}, {0, 1}, {0, -1}}
	bishopDirs := [][2]int{{1, 1}, {1, -1}, {-1, 1}, {-1, -1}}
	queenDirs := [][2]int{{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}}

	resetDist := func(i int) {
		for j := 0; j < m; j++ {
			for k := 0; k < m; k++ {
				dist[i][j][k] = -1
			}
		}
	}

	checkStop := func(i, x, y, t int) bool {
		for j := 0; j < i; j++ {
			if dist[j][x][y] >= t {
				return false
			}
		}
		return true
	}

	checkPass := func(i, x, y, t int) bool {
		for j := 0; j < i; j++ {
			if dist[j][x][y] == t {
				return false
			}
			if end[j][0] == x && end[j][1] == y && end[j][2] <= t {
				return false
			}
		}
		return true
	}

	isValid := func(x, y int) bool {
		return x >= 1 && x < m && y >= 1 && y < m
	}

	getDirs := func(piece string) [][2]int {
		switch piece[0] {
		case 'r':
			return rookDirs
		case 'b':
			return bishopDirs
		default:
			return queenDirs
		}
	}

	var dfs func(i int)
	dfs = func(i int) {
		if i >= n {
			ans++
			return
		}

		x, y := positions[i][0], positions[i][1]
		resetDist(i)
		dist[i][x][y] = 0
		end[i] = [3]int{x, y, 0}

		if checkStop(i, x, y, 0) {
			dfs(i + 1)
		}

		dirs := getDirs(pieces[i])
		for _, dir := range dirs {
			resetDist(i)
			dist[i][x][y] = 0
			nx, ny, nt := x+dir[0], y+dir[1], 1

			for isValid(nx, ny) && checkPass(i, nx, ny, nt) {
				dist[i][nx][ny] = nt
				end[i] = [3]int{nx, ny, nt}
				if checkStop(i, nx, ny, nt) {
					dfs(i + 1)
				}
				nx += dir[0]
				ny += dir[1]
				nt++
			}
		}
	}

	dfs(0)
	return
}
```

#### TypeScript

```ts
const rookDirs: [number, number][] = [
    [1, 0],
    [-1, 0],
    [0, 1],
    [0, -1],
];
const bishopDirs: [number, number][] = [
    [1, 1],
    [1, -1],
    [-1, 1],
    [-1, -1],
];
const queenDirs = [...rookDirs, ...bishopDirs];

function countCombinations(pieces: string[], positions: number[][]): number {
    const n = pieces.length;
    const m = 9;
    let ans = 0;

    const dist = Array.from({ length: n }, () =>
        Array.from({ length: m }, () => Array(m).fill(-1)),
    );

    const end: [number, number, number][] = Array(n).fill([0, 0, 0]);

    const resetDist = (i: number) => {
        for (let j = 0; j < m; j++) {
            for (let k = 0; k < m; k++) {
                dist[i][j][k] = -1;
            }
        }
    };

    const checkStop = (i: number, x: number, y: number, t: number): boolean => {
        for (let j = 0; j < i; j++) {
            if (dist[j][x][y] >= t) {
                return false;
            }
        }
        return true;
    };

    const checkPass = (i: number, x: number, y: number, t: number): boolean => {
        for (let j = 0; j < i; j++) {
            if (dist[j][x][y] === t) {
                return false;
            }
            if (end[j][0] === x && end[j][1] === y && end[j][2] <= t) {
                return false;
            }
        }
        return true;
    };

    const isValid = (x: number, y: number): boolean => {
        return x >= 1 && x < m && y >= 1 && y < m;
    };

    const getDirs = (piece: string): [number, number][] => {
        switch (piece[0]) {
            case 'r':
                return rookDirs;
            case 'b':
                return bishopDirs;
            default:
                return queenDirs;
        }
    };

    const dfs = (i: number) => {
        if (i >= n) {
            ans++;
            return;
        }

        const [x, y] = positions[i];
        resetDist(i);
        dist[i][x][y] = 0;
        end[i] = [x, y, 0];

        if (checkStop(i, x, y, 0)) {
            dfs(i + 1);
        }

        const dirs = getDirs(pieces[i]);
        for (const [dx, dy] of dirs) {
            resetDist(i);
            dist[i][x][y] = 0;
            let nx = x + dx,
                ny = y + dy,
                nt = 1;

            while (isValid(nx, ny) && checkPass(i, nx, ny, nt)) {
                dist[i][nx][ny] = nt;
                end[i] = [nx, ny, nt];
                if (checkStop(i, nx, ny, nt)) {
                    dfs(i + 1);
                }
                nx += dx;
                ny += dy;
                nt++;
            }
        }
    };

    dfs(0);
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
