---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1728.Cat%20and%20Mouse%20II/README_EN.md
rating: 2849
source: Weekly Contest 224 Q4
tags:
    - Graph
    - Topological Sort
    - Memoization
    - Array
    - Math
    - Dynamic Programming
    - Game Theory
    - Matrix
---

<!-- problem:start -->

# [1728. Cat and Mouse II](https://leetcode.com/problems/cat-and-mouse-ii)

[中文文档](/solution/1700-1799/1728.Cat%20and%20Mouse%20II/README.md)

## Description

<!-- description:start -->

<p>A game is played by a cat and a mouse named Cat and Mouse.</p>

<p>The environment is represented by a <code>grid</code> of size <code>rows x cols</code>, where each element is a wall, floor, player (Cat, Mouse), or food.</p>

<ul>
	<li>Players are represented by the characters <code>&#39;C&#39;</code>(Cat)<code>,&#39;M&#39;</code>(Mouse).</li>
	<li>Floors are represented by the character <code>&#39;.&#39;</code> and can be walked on.</li>
	<li>Walls are represented by the character <code>&#39;#&#39;</code> and cannot be walked on.</li>
	<li>Food is represented by the character <code>&#39;F&#39;</code> and can be walked on.</li>
	<li>There is only one of each character <code>&#39;C&#39;</code>, <code>&#39;M&#39;</code>, and <code>&#39;F&#39;</code> in <code>grid</code>.</li>
</ul>

<p>Mouse and Cat play according to the following rules:</p>

<ul>
	<li>Mouse <strong>moves first</strong>, then they take turns to move.</li>
	<li>During each turn, Cat and Mouse can jump in one of the four directions (left, right, up, down). They cannot jump over the wall nor outside of the <code>grid</code>.</li>
	<li><code>catJump, mouseJump</code> are the maximum lengths Cat and Mouse can jump at a time, respectively. Cat and Mouse can jump less than the maximum length.</li>
	<li>Staying in the same position is allowed.</li>
	<li>Mouse can jump over Cat.</li>
</ul>

<p>The game can end in 4 ways:</p>

<ul>
	<li>If Cat occupies the same position as Mouse, Cat wins.</li>
	<li>If Cat reaches the food first, Cat wins.</li>
	<li>If Mouse reaches the food first, Mouse wins.</li>
	<li>If Mouse cannot get to the food within 1000 turns, Cat wins.</li>
</ul>

<p>Given a <code>rows x cols</code> matrix <code>grid</code> and two integers <code>catJump</code> and <code>mouseJump</code>, return <code>true</code><em> if Mouse can win the game if both Cat and Mouse play optimally, otherwise return </em><code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1728.Cat%20and%20Mouse%20II/images/sample_111_1955.png" style="width: 580px; height: 239px;" />
<pre>
<strong>Input:</strong> grid = [&quot;####F&quot;,&quot;#C...&quot;,&quot;M....&quot;], catJump = 1, mouseJump = 2
<strong>Output:</strong> true
<strong>Explanation:</strong> Cat cannot catch Mouse on its turn nor can it get the food before Mouse.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1728.Cat%20and%20Mouse%20II/images/sample_2_1955.png" style="width: 580px; height: 175px;" />
<pre>
<strong>Input:</strong> grid = [&quot;M.C...F&quot;], catJump = 1, mouseJump = 4
<strong>Output:</strong> true
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> grid = [&quot;M.C...F&quot;], catJump = 1, mouseJump = 3
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>rows == grid.length</code></li>
	<li><code>cols = grid[i].length</code></li>
	<li><code>1 &lt;= rows, cols &lt;= 8</code></li>
	<li><code>grid[i][j]</code> consist only of characters <code>&#39;C&#39;</code>, <code>&#39;M&#39;</code>, <code>&#39;F&#39;</code>, <code>&#39;.&#39;</code>, and <code>&#39;#&#39;</code>.</li>
	<li>There is only one of each character <code>&#39;C&#39;</code>, <code>&#39;M&#39;</code>, and <code>&#39;F&#39;</code> in <code>grid</code>.</li>
	<li><code>1 &lt;= catJump, mouseJump &lt;= 8</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Topological Sorting

According to the problem description, the state of the game is determined by the mouse's position, the cat's position, and whose turn it is. The following states can be determined directly:

- When the cat and the mouse are at the same position, the cat wins — this is a winning state for the cat and a losing state for the mouse.
- When the cat reaches the food first, the cat wins — this is a winning state for the cat and a losing state for the mouse.
- When the mouse reaches the food first, the mouse wins — this is a winning state for the mouse and a losing state for the cat.

To determine the result of the initial state, we need to traverse all states starting from the boundary states. Each state contains the mouse's position, the cat's position, and whose turn it is. From the current state, we can derive all possible previous states: the mover in the previous state is the opposite of the current mover, and the mover's position in the previous state differs from that in the current state.

We use the tuple $(m, c, t)$ to represent the current state, and $(pm, pc, pt)$ to represent a possible previous state. All possible previous states are:

- If the current mover is the mouse, then the previous mover was the cat, the mouse's position in the previous state equals the current mouse position, and the cat's position in the previous state is any neighbor of the current cat position.
- If the current mover is the cat, then the previous mover was the mouse, the cat's position in the previous state equals the current cat position, and the mouse's position in the previous state is any neighbor of the current mouse position.

Initially, except for the boundary states, the results of all other states are unknown. Starting from the boundary states, for each state we derive all possible previous states and update their results according to the following logic:

1. If the previous mover is the same as the current winner, then the previous mover can reach the current state and win — directly update the previous state to the current winner.
2. If the previous mover is different from the current winner, and all states reachable by the previous mover are losing states for the previous mover, then we update the previous state to the current winner.

For the second update rule, we need to record the degree of each state. Initially, the degree of a state represents the number of nodes the mover of that state can move to, i.e., the number of neighbors of the node where the mover is located. If the mover is the cat and the cat's node is adjacent to the hole, the degree of that state should be decremented by $1$.

When all states have been updated, the result of the initial state is the final answer.

The time complexity is $O(m^2 \times n^2 \times (m + n))$ and the space complexity is $O(m^2 \times n^2)$, where $m$ and $n$ are the number of rows and columns of the grid, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canMouseWin(self, grid: List[str], catJump: int, mouseJump: int) -> bool:
        m, n = len(grid), len(grid[0])
        cat_start = mouse_start = food = 0
        dirs = (-1, 0, 1, 0, -1)
        g_mouse = [[] for _ in range(m * n)]
        g_cat = [[] for _ in range(m * n)]

        for i, row in enumerate(grid):
            for j, c in enumerate(row):
                if c == "#":
                    continue
                v = i * n + j
                if c == "C":
                    cat_start = v
                elif c == "M":
                    mouse_start = v
                elif c == "F":
                    food = v
                for a, b in pairwise(dirs):
                    for k in range(mouseJump + 1):
                        x, y = i + k * a, j + k * b
                        if not (0 <= x < m and 0 <= y < n and grid[x][y] != "#"):
                            break
                        g_mouse[v].append(x * n + y)
                    for k in range(catJump + 1):
                        x, y = i + k * a, j + k * b
                        if not (0 <= x < m and 0 <= y < n and grid[x][y] != "#"):
                            break
                        g_cat[v].append(x * n + y)
        return self.calc(g_mouse, g_cat, mouse_start, cat_start, food) == 1

    def calc(
        self,
        g_mouse: List[List[int]],
        g_cat: List[List[int]],
        mouse_start: int,
        cat_start: int,
        hole: int,
    ) -> int:
        def get_prev_states(state):
            m, c, t = state
            pt = t ^ 1
            pre = []
            if pt == 1:
                for pc in g_cat[c]:
                    if ans[m][pc][1] == 0:
                        pre.append((m, pc, pt))
            else:
                for pm in g_mouse[m]:
                    if ans[pm][c][0] == 0:
                        pre.append((pm, c, 0))
            return pre

        n = len(g_mouse)
        degree = [[[0, 0] for _ in range(n)] for _ in range(n)]
        for i in range(n):
            for j in range(n):
                degree[i][j][0] = len(g_mouse[i])
                degree[i][j][1] = len(g_cat[j])

        ans = [[[0, 0] for _ in range(n)] for _ in range(n)]
        q = deque()
        for i in range(n):
            ans[hole][i][1] = 1
            ans[i][hole][0] = 2
            ans[i][i][1] = ans[i][i][0] = 2
            q.append((hole, i, 1))
            q.append((i, hole, 0))
            q.append((i, i, 0))
            q.append((i, i, 1))
        while q:
            state = q.popleft()
            t = ans[state[0]][state[1]][state[2]]
            for prev_state in get_prev_states(state):
                pm, pc, pt = prev_state
                if pt == t - 1:
                    ans[pm][pc][pt] = t
                    q.append(prev_state)
                else:
                    degree[pm][pc][pt] -= 1
                    if degree[pm][pc][pt] == 0:
                        ans[pm][pc][pt] = t
                        q.append(prev_state)
        return ans[mouse_start][cat_start][0]
```

#### Java

```java
class Solution {
    private final int[] dirs = {-1, 0, 1, 0, -1};

    public boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
        int m = grid.length;
        int n = grid[0].length();
        int catStart = 0, mouseStart = 0, food = 0;
        List<Integer>[] gMouse = new List[m * n];
        List<Integer>[] gCat = new List[m * n];
        Arrays.setAll(gMouse, i -> new ArrayList<>());
        Arrays.setAll(gCat, i -> new ArrayList<>());

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = grid[i].charAt(j);
                if (c == '#') {
                    continue;
                }
                int v = i * n + j;
                if (c == 'C') {
                    catStart = v;
                } else if (c == 'M') {
                    mouseStart = v;
                } else if (c == 'F') {
                    food = v;
                }

                for (int d = 0; d < 4; ++d) {
                    for (int k = 0; k <= mouseJump; k++) {
                        int x = i + k * dirs[d];
                        int y = j + k * dirs[d + 1];
                        if (x < 0 || x >= m || y < 0 || y >= n || grid[x].charAt(y) == '#') {
                            break;
                        }
                        gMouse[v].add(x * n + y);
                    }
                    for (int k = 0; k <= catJump; k++) {
                        int x = i + k * dirs[d];
                        int y = j + k * dirs[d + 1];
                        if (x < 0 || x >= m || y < 0 || y >= n || grid[x].charAt(y) == '#') {
                            break;
                        }
                        gCat[v].add(x * n + y);
                    }
                }
            }
        }

        return calc(gMouse, gCat, mouseStart, catStart, food) == 1;
    }

    private int calc(
        List<Integer>[] gMouse, List<Integer>[] gCat, int mouseStart, int catStart, int hole) {
        int n = gMouse.length;
        int[][][] degree = new int[n][n][2];
        int[][][] ans = new int[n][n][2];
        Deque<int[]> q = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                degree[i][j][0] = gMouse[i].size();
                degree[i][j][1] = gCat[j].size();
            }
        }

        for (int i = 0; i < n; i++) {
            ans[hole][i][1] = 1;
            ans[i][hole][0] = 2;
            ans[i][i][1] = 2;
            ans[i][i][0] = 2;
            q.offer(new int[] {hole, i, 1});
            q.offer(new int[] {i, hole, 0});
            q.offer(new int[] {i, i, 0});
            q.offer(new int[] {i, i, 1});
        }

        while (!q.isEmpty()) {
            int[] state = q.poll();
            int m = state[0], c = state[1], t = state[2];
            int result = ans[m][c][t];
            for (int[] prevState : getPrevStates(gMouse, gCat, state, ans)) {
                int pm = prevState[0], pc = prevState[1], pt = prevState[2];
                if (pt == result - 1) {
                    ans[pm][pc][pt] = result;
                    q.offer(prevState);
                } else {
                    degree[pm][pc][pt]--;
                    if (degree[pm][pc][pt] == 0) {
                        ans[pm][pc][pt] = result;
                        q.offer(prevState);
                    }
                }
            }
        }

        return ans[mouseStart][catStart][0];
    }

    private List<int[]> getPrevStates(
        List<Integer>[] gMouse, List<Integer>[] gCat, int[] state, int[][][] ans) {
        int m = state[0], c = state[1], t = state[2];
        int pt = t ^ 1;
        List<int[]> pre = new ArrayList<>();
        if (pt == 1) {
            for (int pc : gCat[c]) {
                if (ans[m][pc][1] == 0) {
                    pre.add(new int[] {m, pc, pt});
                }
            }
        } else {
            for (int pm : gMouse[m]) {
                if (ans[pm][c][0] == 0) {
                    pre.add(new int[] {pm, c, 0});
                }
            }
        }
        return pre;
    }
}
```

#### C++

```cpp
class Solution {
private:
    const int dirs[5] = {-1, 0, 1, 0, -1};

    int calc(vector<vector<int>>& gMouse, vector<vector<int>>& gCat, int mouseStart, int catStart, int hole) {
        int n = gMouse.size();
        vector<vector<vector<int>>> degree(n, vector<vector<int>>(n, vector<int>(2)));
        vector<vector<vector<int>>> ans(n, vector<vector<int>>(n, vector<int>(2)));
        queue<tuple<int, int, int>> q;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                degree[i][j][0] = gMouse[i].size();
                degree[i][j][1] = gCat[j].size();
            }
        }

        for (int i = 0; i < n; i++) {
            ans[hole][i][1] = 1;
            ans[i][hole][0] = 2;
            ans[i][i][1] = 2;
            ans[i][i][0] = 2;
            q.push(make_tuple(hole, i, 1));
            q.push(make_tuple(i, hole, 0));
            q.push(make_tuple(i, i, 0));
            q.push(make_tuple(i, i, 1));
        }

        while (!q.empty()) {
            auto state = q.front();
            q.pop();
            int m = get<0>(state), c = get<1>(state), t = get<2>(state);
            int result = ans[m][c][t];
            for (auto& prevState : getPrevStates(gMouse, gCat, state, ans)) {
                int pm = get<0>(prevState), pc = get<1>(prevState), pt = get<2>(prevState);
                if (pt == result - 1) {
                    ans[pm][pc][pt] = result;
                    q.push(prevState);
                } else {
                    degree[pm][pc][pt]--;
                    if (degree[pm][pc][pt] == 0) {
                        ans[pm][pc][pt] = result;
                        q.push(prevState);
                    }
                }
            }
        }

        return ans[mouseStart][catStart][0];
    }

    vector<tuple<int, int, int>> getPrevStates(vector<vector<int>>& gMouse, vector<vector<int>>& gCat, tuple<int, int, int>& state, vector<vector<vector<int>>>& ans) {
        int m = get<0>(state), c = get<1>(state), t = get<2>(state);
        int pt = t ^ 1;
        vector<tuple<int, int, int>> pre;
        if (pt == 1) {
            for (int pc : gCat[c]) {
                if (ans[m][pc][1] == 0) {
                    pre.push_back(make_tuple(m, pc, pt));
                }
            }
        } else {
            for (int pm : gMouse[m]) {
                if (ans[pm][c][0] == 0) {
                    pre.push_back(make_tuple(pm, c, 0));
                }
            }
        }
        return pre;
    }

public:
    bool canMouseWin(vector<string>& grid, int catJump, int mouseJump) {
        int m = grid.size();
        int n = grid[0].length();
        int catStart = 0, mouseStart = 0, food = 0;
        vector<vector<int>> gMouse(m * n);
        vector<vector<int>> gCat(m * n);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = grid[i][j];
                if (c == '#') {
                    continue;
                }
                int v = i * n + j;
                if (c == 'C') {
                    catStart = v;
                } else if (c == 'M') {
                    mouseStart = v;
                } else if (c == 'F') {
                    food = v;
                }

                for (int d = 0; d < 4; ++d) {
                    for (int k = 0; k <= mouseJump; k++) {
                        int x = i + k * dirs[d];
                        int y = j + k * dirs[d + 1];
                        if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == '#') {
                            break;
                        }
                        gMouse[v].push_back(x * n + y);
                    }
                    for (int k = 0; k <= catJump; k++) {
                        int x = i + k * dirs[d];
                        int y = j + k * dirs[d + 1];
                        if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == '#') {
                            break;
                        }
                        gCat[v].push_back(x * n + y);
                    }
                }
            }
        }

        return calc(gMouse, gCat, mouseStart, catStart, food) == 1;
    }
};
```

#### Go

```go
func canMouseWin(grid []string, catJump int, mouseJump int) bool {
	m, n := len(grid), len(grid[0])
	catStart, mouseStart, food := 0, 0, 0
	dirs := []int{-1, 0, 1, 0, -1}
	gMouse := make([][]int, m*n)
	gCat := make([][]int, m*n)

	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			c := grid[i][j]
			if c == '#' {
				continue
			}
			v := i*n + j
			if c == 'C' {
				catStart = v
			} else if c == 'M' {
				mouseStart = v
			} else if c == 'F' {
				food = v
			}
			for d := 0; d < 4; d++ {
				a, b := dirs[d], dirs[d+1]
				for k := 0; k <= mouseJump; k++ {
					x, y := i+k*a, j+k*b
					if !(0 <= x && x < m && 0 <= y && y < n && grid[x][y] != '#') {
						break
					}
					gMouse[v] = append(gMouse[v], x*n+y)
				}
				for k := 0; k <= catJump; k++ {
					x, y := i+k*a, j+k*b
					if !(0 <= x && x < m && 0 <= y && y < n && grid[x][y] != '#') {
						break
					}
					gCat[v] = append(gCat[v], x*n+y)
				}
			}
		}
	}
	return calc(gMouse, gCat, mouseStart, catStart, food) == 1
}

func calc(gMouse, gCat [][]int, mouseStart, catStart, hole int) int {
	n := len(gMouse)
	degree := make([][][]int, n)
	ans := make([][][]int, n)
	for i := 0; i < n; i++ {
		degree[i] = make([][]int, n)
		ans[i] = make([][]int, n)
		for j := 0; j < n; j++ {
			degree[i][j] = make([]int, 2)
			ans[i][j] = make([]int, 2)
			degree[i][j][0] = len(gMouse[i])
			degree[i][j][1] = len(gCat[j])
		}
	}

	q := list.New()
	for i := 0; i < n; i++ {
		ans[hole][i][1] = 1
		ans[i][hole][0] = 2
		ans[i][i][1] = 2
		ans[i][i][0] = 2
		q.PushBack([]int{hole, i, 1})
		q.PushBack([]int{i, hole, 0})
		q.PushBack([]int{i, i, 0})
		q.PushBack([]int{i, i, 1})
	}

	for q.Len() > 0 {
		front := q.Front()
		q.Remove(front)
		state := front.Value.([]int)
		m, c, t := state[0], state[1], state[2]
		currentAns := ans[m][c][t]
		for _, prevState := range getPrevStates(gMouse, gCat, m, c, t, ans) {
			pm, pc, pt := prevState[0], prevState[1], prevState[2]
			if pt == currentAns-1 {
				ans[pm][pc][pt] = currentAns
				q.PushBack([]int{pm, pc, pt})
			} else {
				degree[pm][pc][pt]--
				if degree[pm][pc][pt] == 0 {
					ans[pm][pc][pt] = currentAns
					q.PushBack([]int{pm, pc, pt})
				}
			}
		}
	}
	return ans[mouseStart][catStart][0]
}

func getPrevStates(gMouse, gCat [][]int, m, c, t int, ans [][][]int) [][]int {
	pt := t ^ 1
	pre := [][]int{}
	if pt == 1 {
		for _, pc := range gCat[c] {
			if ans[m][pc][1] == 0 {
				pre = append(pre, []int{m, pc, pt})
			}
		}
	} else {
		for _, pm := range gMouse[m] {
			if ans[pm][c][0] == 0 {
				pre = append(pre, []int{pm, c, pt})
			}
		}
	}
	return pre
}
```

#### TypeScript

```ts
function canMouseWin(grid: string[], catJump: number, mouseJump: number): boolean {
    const m = grid.length;
    const n = grid[0].length;

    let catStart = 0;
    let mouseStart = 0;
    let food = 0;

    const dirs = [-1, 0, 1, 0, -1];

    const gMouse: number[][] = Array.from({ length: m * n }, () => []);
    const gCat: number[][] = Array.from({ length: m * n }, () => []);

    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            const c = grid[i][j];

            if (c === '#') continue;

            const v = i * n + j;

            if (c === 'C') catStart = v;
            else if (c === 'M') mouseStart = v;
            else if (c === 'F') food = v;

            for (let d = 0; d < 4; d++) {
                const a = dirs[d];
                const b = dirs[d + 1];

                for (let k = 0; k <= mouseJump; k++) {
                    const x = i + k * a;
                    const y = j + k * b;

                    if (!(0 <= x && x < m && 0 <= y && y < n && grid[x][y] !== '#')) break;

                    gMouse[v].push(x * n + y);
                }

                for (let k = 0; k <= catJump; k++) {
                    const x = i + k * a;
                    const y = j + k * b;

                    if (!(0 <= x && x < m && 0 <= y && y < n && grid[x][y] !== '#')) break;

                    gCat[v].push(x * n + y);
                }
            }
        }
    }

    function getPrevStates(m: number, c: number, t: number, ans: number[][][]): number[][] {
        const pt = t ^ 1;
        const pre: number[][] = [];

        if (pt === 1) {
            for (const pc of gCat[c]) {
                if (ans[m][pc][1] === 0) pre.push([m, pc, pt]);
            }
        } else {
            for (const pm of gMouse[m]) {
                if (ans[pm][c][0] === 0) pre.push([pm, c, pt]);
            }
        }

        return pre;
    }

    function calc(): number {
        const N = m * n;

        const degree: number[][][] = Array.from({ length: N }, () =>
            Array.from({ length: N }, () => [0, 0]),
        );

        const ans: number[][][] = Array.from({ length: N }, () =>
            Array.from({ length: N }, () => [0, 0]),
        );

        for (let i = 0; i < N; i++) {
            for (let j = 0; j < N; j++) {
                degree[i][j][0] = gMouse[i].length;
                degree[i][j][1] = gCat[j].length;
            }
        }

        const q: number[][] = [];

        for (let i = 0; i < N; i++) {
            ans[food][i][1] = 1;
            ans[i][food][0] = 2;
            ans[i][i][1] = 2;
            ans[i][i][0] = 2;

            q.push([food, i, 1]);
            q.push([i, food, 0]);
            q.push([i, i, 0]);
            q.push([i, i, 1]);
        }

        while (q.length) {
            const [mPos, cPos, t] = q.shift()!;
            const currentAns = ans[mPos][cPos][t];

            for (const [pm, pc, pt] of getPrevStates(mPos, cPos, t, ans)) {
                if (pt === currentAns - 1) {
                    ans[pm][pc][pt] = currentAns;
                    q.push([pm, pc, pt]);
                } else {
                    degree[pm][pc][pt]--;
                    if (degree[pm][pc][pt] === 0) {
                        ans[pm][pc][pt] = currentAns;
                        q.push([pm, pc, pt]);
                    }
                }
            }
        }

        return ans[mouseStart][catStart][0];
    }

    return calc() === 1;
}
```

#### Rust

```rust
impl Solution {
    pub fn can_mouse_win(grid: Vec<String>, cat_jump: i32, mouse_jump: i32) -> bool {
        let m = grid.len();
        let n = grid[0].len();

        let grid: Vec<Vec<char>> = grid.iter().map(|s| s.chars().collect()).collect();

        let mut cat_start = 0usize;
        let mut mouse_start = 0usize;
        let mut food = 0usize;

        let dirs = [-1, 0, 1, 0, -1];

        let mut g_mouse = vec![Vec::<usize>::new(); m * n];
        let mut g_cat = vec![Vec::<usize>::new(); m * n];

        for i in 0..m {
            for j in 0..n {
                let c = grid[i][j];
                if c == '#' {
                    continue;
                }

                let v = i * n + j;

                if c == 'C' {
                    cat_start = v;
                } else if c == 'M' {
                    mouse_start = v;
                } else if c == 'F' {
                    food = v;
                }

                for d in 0..4 {
                    let a = dirs[d];
                    let b = dirs[d + 1];

                    for k in 0..=mouse_jump {
                        let x = i as i32 + k * a;
                        let y = j as i32 + k * b;

                        if !(x >= 0
                            && x < m as i32
                            && y >= 0
                            && y < n as i32
                            && grid[x as usize][y as usize] != '#')
                        {
                            break;
                        }

                        g_mouse[v].push((x as usize) * n + y as usize);
                    }

                    for k in 0..=cat_jump {
                        let x = i as i32 + k * a;
                        let y = j as i32 + k * b;

                        if !(x >= 0
                            && x < m as i32
                            && y >= 0
                            && y < n as i32
                            && grid[x as usize][y as usize] != '#')
                        {
                            break;
                        }

                        g_cat[v].push((x as usize) * n + y as usize);
                    }
                }
            }
        }

        use std::collections::VecDeque;

        let n2 = m * n;

        let mut degree = vec![vec![vec![0i32; 2]; n2]; n2];
        let mut ans = vec![vec![vec![0i32; 2]; n2]; n2];

        for i in 0..n2 {
            for j in 0..n2 {
                degree[i][j][0] = g_mouse[i].len() as i32;
                degree[i][j][1] = g_cat[j].len() as i32;
            }
        }

        let mut q = VecDeque::new();

        for i in 0..n2 {
            ans[food][i][1] = 1;
            ans[i][food][0] = 2;
            ans[i][i][1] = 2;
            ans[i][i][0] = 2;

            q.push_back((food, i, 1));
            q.push_back((i, food, 0));
            q.push_back((i, i, 0));
            q.push_back((i, i, 1));
        }

        while let Some((m_pos, c_pos, t)) = q.pop_front() {
            let current_ans = ans[m_pos][c_pos][t];

            let pt = t ^ 1;

            if pt == 1 {
                for &pc in &g_cat[c_pos] {
                    if ans[m_pos][pc][1] != 0 {
                        continue;
                    }

                    if pt as i32 == current_ans - 1 {
                        ans[m_pos][pc][1] = current_ans;
                        q.push_back((m_pos, pc, 1));
                    } else {
                        degree[m_pos][pc][1] -= 1;
                        if degree[m_pos][pc][1] == 0 {
                            ans[m_pos][pc][1] = current_ans;
                            q.push_back((m_pos, pc, 1));
                        }
                    }
                }
            } else {
                for &pm in &g_mouse[m_pos] {
                    if ans[pm][c_pos][0] != 0 {
                        continue;
                    }

                    if pt as i32 == current_ans - 1 {
                        ans[pm][c_pos][0] = current_ans;
                        q.push_back((pm, c_pos, 0));
                    } else {
                        degree[pm][c_pos][0] -= 1;
                        if degree[pm][c_pos][0] == 0 {
                            ans[pm][c_pos][0] = current_ans;
                            q.push_back((pm, c_pos, 0));
                        }
                    }
                }
            }
        }

        ans[mouse_start][cat_start][0] == 1
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
