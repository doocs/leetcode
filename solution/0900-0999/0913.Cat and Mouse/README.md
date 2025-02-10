---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0913.Cat%20and%20Mouse/README.md
tags:
    - 图
    - 拓扑排序
    - 记忆化搜索
    - 数学
    - 动态规划
    - 博弈
---

<!-- problem:start -->

# [913. 猫和老鼠](https://leetcode.cn/problems/cat-and-mouse)

[English Version](/solution/0900-0999/0913.Cat%20and%20Mouse/README_EN.md)

## 题目描述

<!-- description:start -->

<p>两位玩家分别扮演猫和老鼠，在一张 <strong>无向</strong> 图上进行游戏，两人轮流行动。</p>

<p>图的形式是：<code>graph[a]</code> 是一个列表，由满足&nbsp;<code>ab</code> 是图中的一条边的所有节点 <code>b</code> 组成。</p>

<p>老鼠从节点 <code>1</code> 开始，第一个出发；猫从节点 <code>2</code> 开始，第二个出发。在节点 <code>0</code> 处有一个洞。</p>

<p>在每个玩家的行动中，他们 <strong>必须</strong> 沿着图中与所在当前位置连通的一条边移动。例如，如果老鼠在节点 <code>1</code> ，那么它必须移动到 <code>graph[1]</code> 中的任一节点。</p>

<p>此外，猫无法移动到洞中（节点 <code>0</code>）。</p>

<p>然后，游戏在出现以下三种情形之一时结束：</p>

<ul>
	<li>如果猫和老鼠出现在同一个节点，猫获胜。</li>
	<li>如果老鼠到达洞中，老鼠获胜。</li>
	<li>如果某一位置重复出现（即，玩家的位置和移动顺序都与上一次行动相同），游戏平局。</li>
</ul>

<p>给你一张图 <code>graph</code> ，并假设两位玩家都都以最佳状态参与游戏：</p>

<ul>
	<li>如果老鼠获胜，则返回&nbsp;<code>1</code>；</li>
	<li>如果猫获胜，则返回 <code>2</code>；</li>
	<li>如果平局，则返回 <code>0</code> 。</li>
</ul>
&nbsp;

<p><strong class="example">示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0913.Cat%20and%20Mouse/images/cat1.jpg" style="width: 300px; height: 300px;" />
<pre>
<strong>输入：</strong>graph = [[2,5],[3],[0,4,5],[1,4,5],[2,3],[0,2,3]]
<strong>输出：</strong>0
</pre>

<p><strong class="example">示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0913.Cat%20and%20Mouse/images/cat2.jpg" style="width: 200px; height: 200px;" />
<pre>
<strong>输入：</strong>graph = [[1,3],[0],[3],[0,2]]
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= graph.length &lt;= 50</code></li>
	<li><code>1&nbsp;&lt;= graph[i].length &lt; graph.length</code></li>
	<li><code>0 &lt;= graph[i][j] &lt; graph.length</code></li>
	<li><code>graph[i][j] != i</code></li>
	<li><code>graph[i]</code> 互不相同</li>
	<li>猫和老鼠在游戏中总是可以移动</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：拓扑排序

根据题目描述，游戏中的状态由老鼠的位置、猫的位置和移动方决定。当状态为以下情况，可以直接确定胜负：

-   当猫和老鼠的位置相同时，猫获胜，这是猫的必胜状态，老鼠的必败状态。
-   当老鼠位于洞时，老鼠获胜，这是老鼠的必胜状态，猫的必败状态。

为了得到初始状态的游戏结果，需要从边界状态开始遍历所有的状态。每个状态包含老鼠的位置、猫的位置和移动方，根据当前状态可以得到上一轮的所有可能状态，上一轮状态的移动方和当前状态的移动方相反，上一轮状态的移动方在上一轮状态的位置和当前状态的位置不同。

我们用元组 $(m, c, t)$ 表示本轮的状态，用 $(pm, pc, pt)$ 表示上一轮可能的状态，那么上一轮的所有可能状态有：

-   如果本轮的移动方是老鼠，那么上一轮的移动方是猫，上一轮的老鼠位置是本轮老鼠位置，上一轮的猫位置是本轮猫位置的所有邻接点。
-   如果本轮的移动方是猫，那么上一轮的移动方是老鼠，上一轮的猫位置是本轮猫位置，上一轮的老鼠位置是本轮老鼠位置的所有邻接点。

初始时，除了边界状态以外，其他所有状态的结果都是未知的。我们从边界状态开始，对于每个状态，得到上一轮的所有可能状态并更新结果，更新的逻辑如下：

1. 如果上一轮的移动方与本轮的获胜方相同，那么上一轮的移动方可以到达当前状态并获胜，直接更新上一轮的状态为本轮的获胜方。
1. 如果上一轮的移动方与本轮的获胜方不同，且上一轮的移动方可以到达的所有状态都是上一轮的移动方的必败状态，那么我们将上一轮的状态更新为本轮的获胜方。

对于第 $2$ 个更新逻辑，我们需要记录每个状态的度。初始时，每个状态的度表示该状态的移动方可以移动到的结点数，即移动方所在节点的相邻结点数，如果移动方是猫且所在结点与洞相邻则需要将该状态的度减 $1$。

当所有状态的结果都更新完毕时，初始状态的结果即为最终结果。

时间复杂度 $O(n^3)$，空间复杂度 $O(n^2)$。其中 $n$ 是图中的结点数。

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
