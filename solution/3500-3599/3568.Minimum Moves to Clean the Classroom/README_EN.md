---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3568.Minimum%20Moves%20to%20Clean%20the%20Classroom/README_EN.md
rating: 2143
source: Weekly Contest 452 Q3
tags:
    - Bit Manipulation
    - Breadth-First Search
    - Array
    - Hash Table
    - Matrix
---

<!-- problem:start -->

# [3568. Minimum Moves to Clean the Classroom](https://leetcode.com/problems/minimum-moves-to-clean-the-classroom)

[中文文档](/solution/3500-3599/3568.Minimum%20Moves%20to%20Clean%20the%20Classroom/README.md)

## Description

<!-- description:start -->

<p data-end="324" data-start="147">You are given an <code>m x n</code> grid <code>classroom</code> where a student volunteer is tasked with cleaning up litter scattered around the room. Each cell in the grid is one of the following:</p>

<ul>
	<li><code>&#39;S&#39;</code>: Starting position of the student</li>
	<li><code>&#39;L&#39;</code>: Litter that must be collected (once collected, the cell becomes empty)</li>
	<li><code>&#39;R&#39;</code>: Reset area that restores the student&#39;s energy to full capacity, regardless of their current energy level (can be used multiple times)</li>
	<li><code>&#39;X&#39;</code>: Obstacle the student cannot pass through</li>
	<li><code>&#39;.&#39;</code>: Empty space</li>
</ul>

<p>You are also given an integer <code>energy</code>, representing the student&#39;s maximum energy capacity. The student starts with this energy from the starting position <code>&#39;S&#39;</code>.</p>

<p>Each move to an adjacent cell (up, down, left, or right) costs 1 unit of energy. If the energy reaches 0, the student can only continue if they are on a reset area <code>&#39;R&#39;</code>, which resets the energy to its <strong>maximum</strong> capacity <code>energy</code>.</p>

<p>Return the <strong>minimum</strong> number of moves required to collect all litter items, or <code>-1</code> if it&#39;s impossible.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">classroom = [&quot;S.&quot;, &quot;XL&quot;], energy = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The student starts at cell <code data-end="262" data-start="254">(0, 0)</code> with 2 units of energy.</li>
	<li>Since cell <code>(1, 0)</code> contains an obstacle &#39;X&#39;, the student cannot move directly downward.</li>
	<li>A valid sequence of moves to collect all litter is as follows:
	<ul>
		<li>Move 1: From <code>(0, 0)</code> &rarr; <code>(0, 1)</code> with 1 unit of energy and 1 unit remaining.</li>
		<li>Move 2: From <code>(0, 1)</code> &rarr; <code>(1, 1)</code> to collect the litter <code>&#39;L&#39;</code>.</li>
	</ul>
	</li>
	<li>The student collects all the litter using 2 moves. Thus, the output is 2.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">classroom = [&quot;LS&quot;, &quot;RL&quot;], energy = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The student starts at cell <code data-end="262" data-start="254">(0, 1)</code> with 4 units of energy.</li>
	<li>A valid sequence of moves to collect all litter is as follows:
	<ul>
		<li>Move 1: From <code>(0, 1)</code> &rarr; <code>(0, 0)</code> to collect the first litter <code>&#39;L&#39;</code> with 1 unit of energy used and 3 units remaining.</li>
		<li>Move 2: From <code>(0, 0)</code> &rarr; <code>(1, 0)</code> to <code>&#39;R&#39;</code> to reset and restore energy back to 4.</li>
		<li>Move 3: From <code>(1, 0)</code> &rarr; <code>(1, 1)</code> to collect the second litter <code data-end="1068" data-start="1063">&#39;L&#39;</code>.</li>
	</ul>
	</li>
	<li>The student collects all the litter using 3 moves. Thus, the output is 3.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">classroom = [&quot;L.S&quot;, &quot;RXL&quot;], energy = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>No valid path collects all <code>&#39;L&#39;</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m == classroom.length &lt;= 20</code></li>
	<li><code>1 &lt;= n == classroom[i].length &lt;= 20</code></li>
	<li><code>classroom[i][j]</code> is one of <code>&#39;S&#39;</code>, <code>&#39;L&#39;</code>, <code>&#39;R&#39;</code>, <code>&#39;X&#39;</code>, or <code>&#39;.&#39;</code></li>
	<li><code>1 &lt;= energy &lt;= 50</code></li>
	<li>There is exactly <strong>one</strong> <code>&#39;S&#39;</code> in the grid.</li>
	<li>There are <strong>at most</strong> 10 <code>&#39;L&#39;</code> cells in the grid.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: BFS

We can use Breadth-First Search (BFS) to solve this problem. First, we need to find the student's starting position and record the locations of all garbage. Then, we can use BFS to explore all possible paths starting from the initial position, while tracking the current energy and the collected garbage.

In BFS, we need to maintain a state that includes the current position, remaining energy, and a bitmask representing the collected garbage. We can use a queue to store these states and a set to record visited states to avoid revisiting them.

We start from the initial position and try to move in four directions. If we move to a garbage cell, we update the collected garbage bitmask. If we move to a reset area, we restore the energy to its maximum value. Each move consumes 1 unit of energy.

If we find a state in BFS where the garbage bitmask is 0 (meaning all garbage has been collected), we return the current number of moves. If BFS completes without finding such a state, we return -1.

The time complexity is $O(m \times n \times \textit{energy} \times 2^{\textit{count}})$, and the space complexity is $O(m \times n \times \textit{energy} \times 2^{\textit{count}})$, where $m$ and $n$ are the number of rows and columns in the grid, and $\textit{count}$ is the number of garbage cells.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minMoves(self, classroom: List[str], energy: int) -> int:
        m, n = len(classroom), len(classroom[0])
        d = [[0] * n for _ in range(m)]
        x = y = cnt = 0
        for i, row in enumerate(classroom):
            for j, c in enumerate(row):
                if c == "S":
                    x, y = i, j
                elif c == "L":
                    d[i][j] = cnt
                    cnt += 1
        if cnt == 0:
            return 0
        vis = [
            [[[False] * (1 << cnt) for _ in range(energy + 1)] for _ in range(n)]
            for _ in range(m)
        ]
        q = [(x, y, energy, (1 << cnt) - 1)]
        vis[x][y][energy][(1 << cnt) - 1] = True
        dirs = (-1, 0, 1, 0, -1)
        ans = 0
        while q:
            t = q
            q = []
            for i, j, cur_energy, mask in t:
                if mask == 0:
                    return ans
                if cur_energy <= 0:
                    continue
                for k in range(4):
                    x, y = i + dirs[k], j + dirs[k + 1]
                    if 0 <= x < m and 0 <= y < n and classroom[x][y] != "X":
                        nxt_energy = (
                            energy if classroom[x][y] == "R" else cur_energy - 1
                        )
                        nxt_mask = mask
                        if classroom[x][y] == "L":
                            nxt_mask &= ~(1 << d[x][y])
                        if not vis[x][y][nxt_energy][nxt_mask]:
                            vis[x][y][nxt_energy][nxt_mask] = True
                            q.append((x, y, nxt_energy, nxt_mask))
            ans += 1
        return -1
```

#### Java

```java
class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length, n = classroom[0].length();
        int[][] d = new int[m][n];
        int x = 0, y = 0, cnt = 0;
        for (int i = 0; i < m; i++) {
            String row = classroom[i];
            for (int j = 0; j < n; j++) {
                char c = row.charAt(j);
                if (c == 'S') {
                    x = i;
                    y = j;
                } else if (c == 'L') {
                    d[i][j] = cnt;
                    cnt++;
                }
            }
        }
        if (cnt == 0) {
            return 0;
        }
        boolean[][][][] vis = new boolean[m][n][energy + 1][1 << cnt];
        List<int[]> q = new ArrayList<>();
        q.add(new int[] {x, y, energy, (1 << cnt) - 1});
        vis[x][y][energy][(1 << cnt) - 1] = true;
        int[] dirs = {-1, 0, 1, 0, -1};
        int ans = 0;
        while (!q.isEmpty()) {
            List<int[]> t = q;
            q = new ArrayList<>();
            for (int[] state : t) {
                int i = state[0], j = state[1], curEnergy = state[2], mask = state[3];
                if (mask == 0) {
                    return ans;
                }
                if (curEnergy <= 0) {
                    continue;
                }
                for (int k = 0; k < 4; k++) {
                    int nx = i + dirs[k], ny = j + dirs[k + 1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && classroom[nx].charAt(ny) != 'X') {
                        int nxtEnergy = classroom[nx].charAt(ny) == 'R' ? energy : curEnergy - 1;
                        int nxtMask = mask;
                        if (classroom[nx].charAt(ny) == 'L') {
                            nxtMask &= ~(1 << d[nx][ny]);
                        }
                        if (!vis[nx][ny][nxtEnergy][nxtMask]) {
                            vis[nx][ny][nxtEnergy][nxtMask] = true;
                            q.add(new int[] {nx, ny, nxtEnergy, nxtMask});
                        }
                    }
                }
            }
            ans++;
        }
        return -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minMoves(vector<string>& classroom, int energy) {
        int m = classroom.size(), n = classroom[0].size();
        vector<vector<int>> d(m, vector<int>(n, 0));
        int x = 0, y = 0, cnt = 0;
        for (int i = 0; i < m; ++i) {
            string& row = classroom[i];
            for (int j = 0; j < n; ++j) {
                char c = row[j];
                if (c == 'S') {
                    x = i;
                    y = j;
                } else if (c == 'L') {
                    d[i][j] = cnt;
                    cnt++;
                }
            }
        }
        if (cnt == 0) {
            return 0;
        }
        vector<vector<vector<vector<bool>>>> vis(m, vector<vector<vector<bool>>>(n, vector<vector<bool>>(energy + 1, vector<bool>(1 << cnt, false))));
        queue<tuple<int, int, int, int>> q;
        q.emplace(x, y, energy, (1 << cnt) - 1);
        vis[x][y][energy][(1 << cnt) - 1] = true;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        int ans = 0;
        while (!q.empty()) {
            int sz = q.size();
            while (sz--) {
                auto [i, j, cur_energy, mask] = q.front();
                q.pop();
                if (mask == 0) {
                    return ans;
                }
                if (cur_energy <= 0) {
                    continue;
                }
                for (int k = 0; k < 4; ++k) {
                    int nx = i + dirs[k], ny = j + dirs[k + 1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && classroom[nx][ny] != 'X') {
                        int nxt_energy = classroom[nx][ny] == 'R' ? energy : cur_energy - 1;
                        int nxt_mask = mask;
                        if (classroom[nx][ny] == 'L') {
                            nxt_mask &= ~(1 << d[nx][ny]);
                        }
                        if (!vis[nx][ny][nxt_energy][nxt_mask]) {
                            vis[nx][ny][nxt_energy][nxt_mask] = true;
                            q.emplace(nx, ny, nxt_energy, nxt_mask);
                        }
                    }
                }
            }
            ans++;
        }
        return -1;
    }
};
```

#### Go

```go
func minMoves(classroom []string, energy int) int {
	m, n := len(classroom), len(classroom[0])
	d := make([][]int, m)
	for i := range d {
		d[i] = make([]int, n)
	}
	x, y, cnt := 0, 0, 0
	for i := 0; i < m; i++ {
		row := classroom[i]
		for j := 0; j < n; j++ {
			c := row[j]
			if c == 'S' {
				x, y = i, j
			} else if c == 'L' {
				d[i][j] = cnt
				cnt++
			}
		}
	}
	if cnt == 0 {
		return 0
	}

	vis := make([][][][]bool, m)
	for i := range vis {
		vis[i] = make([][][]bool, n)
		for j := range vis[i] {
			vis[i][j] = make([][]bool, energy+1)
			for e := range vis[i][j] {
				vis[i][j][e] = make([]bool, 1<<cnt)
			}
		}
	}
	type state struct {
		i, j, curEnergy, mask int
	}
	q := []state{{x, y, energy, (1 << cnt) - 1}}
	vis[x][y][energy][(1<<cnt)-1] = true
	dirs := []int{-1, 0, 1, 0, -1}
	ans := 0

	for len(q) > 0 {
		t := q
		q = []state{}
		for _, s := range t {
			i, j, curEnergy, mask := s.i, s.j, s.curEnergy, s.mask
			if mask == 0 {
				return ans
			}
			if curEnergy <= 0 {
				continue
			}
			for k := 0; k < 4; k++ {
				nx, ny := i+dirs[k], j+dirs[k+1]
				if nx >= 0 && nx < m && ny >= 0 && ny < n && classroom[nx][ny] != 'X' {
					var nxtEnergy int
					if classroom[nx][ny] == 'R' {
						nxtEnergy = energy
					} else {
						nxtEnergy = curEnergy - 1
					}
					nxtMask := mask
					if classroom[nx][ny] == 'L' {
						nxtMask &= ^(1 << d[nx][ny])
					}
					if !vis[nx][ny][nxtEnergy][nxtMask] {
						vis[nx][ny][nxtEnergy][nxtMask] = true
						q = append(q, state{nx, ny, nxtEnergy, nxtMask})
					}
				}
			}
		}
		ans++
	}
	return -1
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
