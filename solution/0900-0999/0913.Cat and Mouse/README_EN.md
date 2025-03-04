---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0913.Cat%20and%20Mouse/README_EN.md
tags:
    - Graph
    - Topological Sort
    - Memoization
    - Math
    - Dynamic Programming
    - Game Theory
---

<!-- problem:start -->

# [913. Cat and Mouse](https://leetcode.com/problems/cat-and-mouse)

[中文文档](/solution/0900-0999/0913.Cat%20and%20Mouse/README.md)

## Description

<!-- description:start -->

<p>A game on an <strong>undirected</strong> graph is played by two players, Mouse and Cat, who alternate turns.</p>

<p>The graph is given as follows: <code>graph[a]</code> is a list of all nodes <code>b</code> such that <code>ab</code> is an edge of the graph.</p>

<p>The mouse starts at node <code>1</code> and goes first, the cat starts at node <code>2</code> and goes second, and there is a hole at node <code>0</code>.</p>

<p>During each player&#39;s turn, they <strong>must</strong> travel along one&nbsp;edge of the graph that meets where they are.&nbsp; For example, if the Mouse is at node 1, it <strong>must</strong> travel to any node in <code>graph[1]</code>.</p>

<p>Additionally, it is not allowed for the Cat to travel to the Hole (node <code>0</code>).</p>

<p>Then, the game can end in three&nbsp;ways:</p>

<ul>
	<li>If ever the Cat occupies the same node as the Mouse, the Cat wins.</li>
	<li>If ever the Mouse reaches the Hole, the Mouse wins.</li>
	<li>If ever a position is repeated (i.e., the players are in the same position as a previous turn, and&nbsp;it is the same player&#39;s turn to move), the game is a draw.</li>
</ul>

<p>Given a <code>graph</code>, and assuming both players play optimally, return</p>

<ul>
	<li><code>1</code>&nbsp;if the mouse wins the game,</li>
	<li><code>2</code>&nbsp;if the cat wins the game, or</li>
	<li><code>0</code>&nbsp;if the game is a draw.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0913.Cat%20and%20Mouse/images/cat1.jpg" style="width: 300px; height: 300px;" />
<pre>
<strong>Input:</strong> graph = [[2,5],[3],[0,4,5],[1,4,5],[2,3],[0,2,3]]
<strong>Output:</strong> 0
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0913.Cat%20and%20Mouse/images/cat2.jpg" style="width: 200px; height: 200px;" />
<pre>
<strong>Input:</strong> graph = [[1,3],[0],[3],[0,2]]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= graph.length &lt;= 50</code></li>
	<li><code>1&nbsp;&lt;= graph[i].length &lt; graph.length</code></li>
	<li><code>0 &lt;= graph[i][j] &lt; graph.length</code></li>
	<li><code>graph[i][j] != i</code></li>
	<li><code>graph[i]</code> is unique.</li>
	<li>The mouse and the cat can always move.&nbsp;</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Topological Sorting

According to the problem description, the state of the game is determined by the position of the mouse, the position of the cat, and the player who is moving. The outcome can be directly determined in the following situations:

-   When the positions of the cat and the mouse are the same, the cat wins. This is a winning state for the cat and a losing state for the mouse.
-   When the mouse is at the hole, the mouse wins. This is a winning state for the mouse and a losing state for the cat.

To determine the result of the initial state, we need to traverse all states starting from the boundary states. Each state includes the position of the mouse, the position of the cat, and the player who is moving. Based on the current state, we can determine all possible states from the previous round. The player who moved in the previous round is the opposite of the player who is moving in the current state, and the positions of the players in the previous round are different from their positions in the current state.

We use the tuple $(m, c, t)$ to represent the current state and $(pm, pc, pt)$ to represent a possible state from the previous round. The possible states from the previous round are:

-   If the player moving in the current round is the mouse, then the player moving in the previous round is the cat. The position of the mouse in the previous round is the same as the current position of the mouse, and the position of the cat in the previous round is any adjacent node of the current position of the cat.
-   If the player moving in the current round is the cat, then the player moving in the previous round is the mouse. The position of the cat in the previous round is the same as the current position of the cat, and the position of the mouse in the previous round is any adjacent node of the current position of the mouse.

Initially, all states except the boundary states are unknown. Starting from the boundary states, for each state, we determine all possible states from the previous round and update the results. The update logic is as follows:

1. If the player moving in the previous round is the same as the winner in the current round, then the player moving in the previous round can reach the current state and win. We directly update the state of the previous round to the winner of the current round.
2. If the player moving in the previous round is different from the winner in the current round, and all states that the player moving in the previous round can reach are losing states for that player, then we update the state of the previous round to the winner of the current round.

For the second update logic, we need to record the degree of each state. Initially, the degree of each state represents the number of nodes the player moving in that state can move to, which is the number of adjacent nodes of the node where the player is located. If the player is the cat and the node is adjacent to the hole, the degree of that state is reduced by $1$.

When all states have been updated, the result of the initial state is the final result.

The time complexity is $O(n^3)$, and the space complexity is $O(n^2)$. Here, $n$ is the number of nodes in the graph.

<!-- tabs:start -->

#### Python3

```python
HOLE, MOUSE_START, CAT_START = 0, 1, 2
MOUSE_TURN, CAT_TURN = 0, 1
MOUSE_WIN, CAT_WIN, TIE = 1, 2, 0


class Solution:
    def catMouseGame(self, graph: List[List[int]]) -> int:
        def get_prev_states(state):
            m, c, t = state
            pt = t ^ 1
            pre = []
            if pt == CAT_TURN:
                for pc in graph[c]:
                    if pc != HOLE:
                        pre.append((m, pc, pt))
            else:
                for pm in graph[m]:
                    pre.append((pm, c, pt))
            return pre

        n = len(graph)
        ans = [[[0, 0] for _ in range(n)] for _ in range(n)]
        degree = [[[0, 0] for _ in range(n)] for _ in range(n)]
        for i in range(n):
            for j in range(1, n):
                degree[i][j][MOUSE_TURN] = len(graph[i])
                degree[i][j][CAT_TURN] = len(graph[j])
            for j in graph[HOLE]:
                degree[i][j][CAT_TURN] -= 1
        q = deque()
        for j in range(1, n):
            ans[0][j][MOUSE_TURN] = ans[0][j][CAT_TURN] = MOUSE_WIN
            q.append((0, j, MOUSE_TURN))
            q.append((0, j, CAT_TURN))
        for i in range(1, n):
            ans[i][i][MOUSE_TURN] = ans[i][i][CAT_TURN] = CAT_WIN
            q.append((i, i, MOUSE_TURN))
            q.append((i, i, CAT_TURN))
        while q:
            state = q.popleft()
            t = ans[state[0]][state[1]][state[2]]
            for prev_state in get_prev_states(state):
                pm, pc, pt = prev_state
                if ans[pm][pc][pt] == TIE:
                    win = (t == MOUSE_WIN and pt == MOUSE_TURN) or (
                        t == CAT_WIN and pt == CAT_TURN
                    )
                    if win:
                        ans[pm][pc][pt] = t
                        q.append(prev_state)
                    else:
                        degree[pm][pc][pt] -= 1
                        if degree[pm][pc][pt] == 0:
                            ans[pm][pc][pt] = t
                            q.append(prev_state)
        return ans[MOUSE_START][CAT_START][MOUSE_TURN]
```

#### Java

```java
class Solution {
    private int n;
    private int[][] g;
    private int[][][] ans;
    private int[][][] degree;

    private static final int HOLE = 0, MOUSE_START = 1, CAT_START = 2;
    private static final int MOUSE_TURN = 0, CAT_TURN = 1;
    private static final int MOUSE_WIN = 1, CAT_WIN = 2, TIE = 0;

    public int catMouseGame(int[][] graph) {
        n = graph.length;
        g = graph;
        ans = new int[n][n][2];
        degree = new int[n][n][2];
        for (int i = 0; i < n; ++i) {
            for (int j = 1; j < n; ++j) {
                degree[i][j][MOUSE_TURN] = g[i].length;
                degree[i][j][CAT_TURN] = g[j].length;
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j : g[HOLE]) {
                --degree[i][j][CAT_TURN];
            }
        }
        Deque<int[]> q = new ArrayDeque<>();
        for (int j = 1; j < n; ++j) {
            ans[0][j][MOUSE_TURN] = MOUSE_WIN;
            ans[0][j][CAT_TURN] = MOUSE_WIN;
            q.offer(new int[] {0, j, MOUSE_TURN});
            q.offer(new int[] {0, j, CAT_TURN});
        }
        for (int i = 1; i < n; ++i) {
            ans[i][i][MOUSE_TURN] = CAT_WIN;
            ans[i][i][CAT_TURN] = CAT_WIN;
            q.offer(new int[] {i, i, MOUSE_TURN});
            q.offer(new int[] {i, i, CAT_TURN});
        }
        while (!q.isEmpty()) {
            int[] state = q.poll();
            int t = ans[state[0]][state[1]][state[2]];
            List<int[]> prevStates = getPrevStates(state);
            for (var prevState : prevStates) {
                int pm = prevState[0], pc = prevState[1], pt = prevState[2];
                if (ans[pm][pc][pt] == TIE) {
                    boolean win
                        = (t == MOUSE_WIN && pt == MOUSE_TURN) || (t == CAT_WIN && pt == CAT_TURN);
                    if (win) {
                        ans[pm][pc][pt] = t;
                        q.offer(prevState);
                    } else {
                        if (--degree[pm][pc][pt] == 0) {
                            ans[pm][pc][pt] = t;
                            q.offer(prevState);
                        }
                    }
                }
            }
        }
        return ans[MOUSE_START][CAT_START][MOUSE_TURN];
    }

    private List<int[]> getPrevStates(int[] state) {
        List<int[]> pre = new ArrayList<>();
        int m = state[0], c = state[1], t = state[2];
        int pt = t ^ 1;
        if (pt == CAT_TURN) {
            for (int pc : g[c]) {
                if (pc != HOLE) {
                    pre.add(new int[] {m, pc, pt});
                }
            }
        } else {
            for (int pm : g[m]) {
                pre.add(new int[] {pm, c, pt});
            }
        }
        return pre;
    }
}
```

#### C++

```cpp
const int HOLE = 0;
const int MOUSE_START = 1;
const int CAT_START = 2;
const int MOUSE_TURN = 0;
const int CAT_TURN = 1;
const int MOUSE_WIN = 1;
const int CAT_WIN = 2;
const int TIE = 0;

class Solution {
public:
    int catMouseGame(vector<vector<int>>& graph) {
        int n = graph.size();
        int ans[n][n][2];
        int degree[n][n][2];
        memset(ans, 0, sizeof ans);
        memset(degree, 0, sizeof degree);
        for (int i = 0; i < n; ++i) {
            for (int j = 1; j < n; ++j) {
                degree[i][j][MOUSE_TURN] = graph[i].size();
                degree[i][j][CAT_TURN] = graph[j].size();
            }
            for (int j : graph[HOLE]) {
                --degree[i][j][CAT_TURN];
            }
        }
        auto getPrevStates = [&](int m, int c, int t) {
            int pt = t ^ 1;
            vector<tuple<int, int, int>> pre;
            if (pt == CAT_TURN) {
                for (int pc : graph[c]) {
                    if (pc != HOLE) {
                        pre.emplace_back(m, pc, pt);
                    }
                }
            } else {
                for (int pm : graph[m]) {
                    pre.emplace_back(pm, c, pt);
                }
            }
            return pre;
        };
        queue<tuple<int, int, int>> q;
        for (int j = 1; j < n; ++j) {
            ans[0][j][MOUSE_TURN] = ans[0][j][CAT_TURN] = MOUSE_WIN;
            q.emplace(0, j, MOUSE_TURN);
            q.emplace(0, j, CAT_TURN);
        }
        for (int i = 1; i < n; ++i) {
            ans[i][i][MOUSE_TURN] = ans[i][i][CAT_TURN] = CAT_WIN;
            q.emplace(i, i, MOUSE_TURN);
            q.emplace(i, i, CAT_TURN);
        }
        while (!q.empty()) {
            auto [m, c, t] = q.front();
            q.pop();
            int x = ans[m][c][t];
            for (auto [pm, pc, pt] : getPrevStates(m, c, t)) {
                if (ans[pm][pc][pt] == TIE) {
                    bool win = (x == MOUSE_WIN && pt == MOUSE_TURN) || (x == CAT_WIN && pt == CAT_TURN);
                    if (win) {
                        ans[pm][pc][pt] = x;
                        q.emplace(pm, pc, pt);
                    } else {
                        if (--degree[pm][pc][pt] == 0) {
                            ans[pm][pc][pt] = x;
                            q.emplace(pm, pc, pt);
                        }
                    }
                }
            }
        }
        return ans[MOUSE_START][CAT_START][MOUSE_TURN];
    }
};
```

#### Go

```go
const (
	hole       = 0
	mouseStart = 1
	catStart   = 2
	mouseTurn  = 0
	catTurn    = 1
	mouseWin   = 1
	catWin     = 2
	tie        = 0
)

func catMouseGame(graph [][]int) int {
	ans := [50][50][2]int{}
	degree := [50][50][2]int{}
	n := len(graph)
	for i := 0; i < n; i++ {
		for j := 1; j < n; j++ {
			degree[i][j][mouseTurn] = len(graph[i])
			degree[i][j][catTurn] = len(graph[j])
		}
		for _, j := range graph[hole] {
			degree[i][j][catTurn]--
		}
	}
	type tuple struct{ m, c, t int }
	q := []tuple{}
	for j := 1; j < n; j++ {
		ans[0][j][mouseTurn], ans[0][j][catTurn] = mouseWin, mouseWin
		q = append(q, tuple{0, j, mouseTurn})
		q = append(q, tuple{0, j, catTurn})
	}
	for i := 1; i < n; i++ {
		ans[i][i][mouseTurn], ans[i][i][catTurn] = catWin, catWin
		q = append(q, tuple{i, i, mouseTurn})
		q = append(q, tuple{i, i, catTurn})
	}
	getPrevStates := func(m, c, t int) []tuple {
		pre := []tuple{}
		pt := t ^ 1
		if pt == catTurn {
			for _, pc := range graph[c] {
				if pc != hole {
					pre = append(pre, tuple{m, pc, pt})
				}
			}
		} else {
			for _, pm := range graph[m] {
				pre = append(pre, tuple{pm, c, pt})
			}
		}
		return pre
	}
	for len(q) > 0 {
		state := q[0]
		m, c, t := state.m, state.c, state.t
		q = q[1:]
		x := ans[m][c][t]
		for _, prevState := range getPrevStates(m, c, t) {
			pm, pc, pt := prevState.m, prevState.c, prevState.t
			if ans[pm][pc][pt] == tie {
				win := (x == mouseWin && pt == mouseTurn) || (x == catWin && pt == catTurn)
				if win {
					ans[pm][pc][pt] = x
					q = append(q, tuple{pm, pc, pt})
				} else {
					degree[pm][pc][pt]--
					if degree[pm][pc][pt] == 0 {
						ans[pm][pc][pt] = x
						q = append(q, tuple{pm, pc, pt})
					}
				}
			}
		}
	}
	return ans[mouseStart][catStart][mouseTurn]
}
```

#### TypeScript

```ts
function catMouseGame(graph: number[][]): number {
    const [HOLE, MOUSE_START, CAT_START] = [0, 1, 2];
    const [MOUSE_TURN, CAT_TURN] = [0, 1];
    const [MOUSE_WIN, CAT_WIN, TIE] = [1, 2, 0];

    function get_prev_states(state: [number, number, number]): [number, number, number][] {
        const [m, c, t] = state;
        const pt = t ^ 1;
        const pre = [] as [number, number, number][];

        if (pt === CAT_TURN) {
            for (const pc of graph[c]) {
                if (pc !== HOLE) {
                    pre.push([m, pc, pt]);
                }
            }
        } else {
            for (const pm of graph[m]) {
                pre.push([pm, c, pt]);
            }
        }
        return pre;
    }

    const n = graph.length;
    const ans: number[][][] = Array.from({ length: n }, () =>
        Array.from({ length: n }, () => [TIE, TIE]),
    );
    const degree: number[][][] = Array.from({ length: n }, () =>
        Array.from({ length: n }, () => [0, 0]),
    );

    for (let i = 0; i < n; i++) {
        for (let j = 1; j < n; j++) {
            degree[i][j][MOUSE_TURN] = graph[i].length;
            degree[i][j][CAT_TURN] = graph[j].length;
        }
        for (const j of graph[HOLE]) {
            degree[i][j][CAT_TURN] -= 1;
        }
    }

    const q: [number, number, number][] = [];

    for (let j = 1; j < n; j++) {
        ans[0][j][MOUSE_TURN] = ans[0][j][CAT_TURN] = MOUSE_WIN;
        q.push([0, j, MOUSE_TURN], [0, j, CAT_TURN]);
    }
    for (let i = 1; i < n; i++) {
        ans[i][i][MOUSE_TURN] = ans[i][i][CAT_TURN] = CAT_WIN;
        q.push([i, i, MOUSE_TURN], [i, i, CAT_TURN]);
    }

    while (q.length > 0) {
        const state = q.shift()!;
        const [m, c, t] = state;
        const result = ans[m][c][t];

        for (const prev_state of get_prev_states(state)) {
            const [pm, pc, pt] = prev_state;
            if (ans[pm][pc][pt] === TIE) {
                const win =
                    (result === MOUSE_WIN && pt === MOUSE_TURN) ||
                    (result === CAT_WIN && pt === CAT_TURN);
                if (win) {
                    ans[pm][pc][pt] = result;
                    q.push(prev_state);
                } else {
                    degree[pm][pc][pt] -= 1;
                    if (degree[pm][pc][pt] === 0) {
                        ans[pm][pc][pt] = result;
                        q.push(prev_state);
                    }
                }
            }
        }
    }

    return ans[MOUSE_START][CAT_START][MOUSE_TURN];
}
```

#### C#

```cs
public class Solution {
    private int n;
    private int[][] g;
    private int[,,] ans;
    private int[,,] degree;

    private const int HOLE = 0, MOUSE_START = 1, CAT_START = 2;
    private const int MOUSE_TURN = 0, CAT_TURN = 1;
    private const int MOUSE_WIN = 1, CAT_WIN = 2, TIE = 0;

    public int CatMouseGame(int[][] graph) {
        n = graph.Length;
        g = graph;
        ans = new int[n, n, 2];
        degree = new int[n, n, 2];

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                degree[i, j, MOUSE_TURN] = g[i].Length;
                degree[i, j, CAT_TURN] = g[j].Length;
            }
        }

        for (int i = 0; i < n; i++) {
            foreach (int j in g[HOLE]) {
                degree[i, j, CAT_TURN]--;
            }
        }

        Queue<int[]> q = new Queue<int[]>();

        for (int j = 1; j < n; j++) {
            ans[0, j, MOUSE_TURN] = MOUSE_WIN;
            ans[0, j, CAT_TURN] = MOUSE_WIN;
            q.Enqueue(new int[] { 0, j, MOUSE_TURN });
            q.Enqueue(new int[] { 0, j, CAT_TURN });
        }

        for (int i = 1; i < n; i++) {
            ans[i, i, MOUSE_TURN] = CAT_WIN;
            ans[i, i, CAT_TURN] = CAT_WIN;
            q.Enqueue(new int[] { i, i, MOUSE_TURN });
            q.Enqueue(new int[] { i, i, CAT_TURN });
        }

        while (q.Count > 0) {
            int[] state = q.Dequeue();
            int t = ans[state[0], state[1], state[2]];
            List<int[]> prevStates = GetPrevStates(state);

            foreach (var prevState in prevStates) {
                int pm = prevState[0], pc = prevState[1], pt = prevState[2];
                if (ans[pm, pc, pt] == TIE) {
                    bool win = (t == MOUSE_WIN && pt == MOUSE_TURN) || (t == CAT_WIN && pt == CAT_TURN);
                    if (win) {
                        ans[pm, pc, pt] = t;
                        q.Enqueue(prevState);
                    } else {
                        if (--degree[pm, pc, pt] == 0) {
                            ans[pm, pc, pt] = t;
                            q.Enqueue(prevState);
                        }
                    }
                }
            }
        }

        return ans[MOUSE_START, CAT_START, MOUSE_TURN];
    }

    private List<int[]> GetPrevStates(int[] state) {
        List<int[]> pre = new List<int[]>();
        int m = state[0], c = state[1], t = state[2];
        int pt = t ^ 1;

        if (pt == CAT_TURN) {
            foreach (int pc in g[c]) {
                if (pc != HOLE) {
                    pre.Add(new int[] { m, pc, pt });
                }
            }
        } else {
            foreach (int pm in g[m]) {
                pre.Add(new int[] { pm, c, pt });
            }
        }

        return pre;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
