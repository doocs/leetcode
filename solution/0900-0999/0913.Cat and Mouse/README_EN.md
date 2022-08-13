# [913. Cat and Mouse](https://leetcode.com/problems/cat-and-mouse)

[中文文档](/solution/0900-0999/0913.Cat%20and%20Mouse/README.md)

## Description

<p>A game on an <strong>undirected</strong> graph is played by two players, Mouse and Cat, who alternate turns.</p>

<p>The graph is given as follows: <code>graph[a]</code> is a list of all nodes <code>b</code> such that <code>ab</code> is an edge of the graph.</p>

<p>The mouse starts at node <code>1</code> and goes first, the cat starts at node <code>2</code> and goes second, and there is a hole at node <code>0</code>.</p>

<p>During each player&#39;s turn, they <strong>must</strong> travel along one&nbsp;edge of the graph that meets where they are.&nbsp; For example, if the Mouse is at node 1, it <strong>must</strong> travel to any node in <code>graph[1]</code>.</p>

<p>Additionally, it is not allowed for the Cat to travel to the Hole (node 0.)</p>

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
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0913.Cat%20and%20Mouse/images/cat1.jpg" style="width: 300px; height: 300px;" />
<pre>
<strong>Input:</strong> graph = [[2,5],[3],[0,4,5],[1,4,5],[2,3],[0,2,3]]
<strong>Output:</strong> 0
</pre>

<p><strong>Example 2:</strong></p>
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

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def catMouseGame(self, graph: List[List[int]]) -> int:
        @cache
        def dfs(i, j, k):
            # mouse / cat / steps
            if k >= 2 * len(graph):
                return 0  # tie
            if i == 0:
                return 1  # mouse wins
            if i == j:
                return 2  # cat wins
            if k % 2:  # cat’s turn
                tie = False
                for next in graph[j]:
                    if next == 0:
                        continue
                    x = dfs(i, next, k + 1)
                    if x == 2:
                        return 2
                    if x == 0:
                        # continue to find if exists winning case
                        tie = True
                if tie:
                    return 0
                return 1
            else:  # mouse's turn
                tie = False
                for next in graph[i]:
                    x = dfs(next, j, k + 1)
                    if x == 1:
                        return 1
                    if x == 0:
                        # continue to find if exists winning case
                        tie = True
                if tie:
                    return 0
                return 2

        return dfs(1, 2, 0)
```

### **Java**

```java
class Solution {
    private int[][][] memo;
    private int[][] graph;

    public int catMouseGame(int[][] graph) {
        int n = graph.length;
        this.graph = graph;
        memo = new int[n][n][2 * n + 10];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < 2 * n + 10; ++k) {
                    memo[i][j][k] = -1;
                }
            }
        }
        return dfs(1, 2, 0);
    }

    private int dfs(int i, int j, int k) {
        // mouse / cat / steps
        if (memo[i][j][k] != -1) {
            return memo[i][j][k];
        }
        if (k >= 2 * graph.length) {
            // tie
            memo[i][j][k] = 0;
        } else if (i == 0) {
            // mouse wins
            memo[i][j][k] = 1;
        } else if (i == j) {
            // cat wins
            memo[i][j][k] = 2;
        } else if (k % 2 == 1) {
            // cat's turn
            boolean tie = false;
            boolean win = false;
            for (int next : graph[j]) {
                if (next == 0) {
                    continue;
                }
                int x = dfs(i, next, k + 1);
                if (x == 2) {
                    win = true;
                    memo[i][j][k] = 2;
                    break;
                }
                if (x == 0) {
                    // continue to find if exists winning case
                    tie = true;
                }
            }
            if (!win) {
                memo[i][j][k] = tie ? 0 : 1;
            }
        } else {
            // mouse's turn
            boolean tie = false;
            boolean win = false;
            for (int next : graph[i]) {
                int x = dfs(next, j, k + 1);
                if (x == 1) {
                    win = true;
                    memo[i][j][k] = 1;
                    break;
                }
                if (x == 0) {
                    // continue to find if exists winning case
                    tie = true;
                }
            }
            if (!win) {
                memo[i][j][k] = tie ? 0 : 2;
            }
        }
        return memo[i][j][k];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<vector<int>>> memo;
    vector<vector<int>> graph;

    int catMouseGame(vector<vector<int>>& graph) {
        int n = graph.size();
        this->graph = graph;
        memo.resize(n, vector<vector<int>>(n, vector<int>(2 * n + 10, -1)));
        return dfs(1, 2, 0);
    }

    int dfs(int i, int j, int k) {
        if (memo[i][j][k] != -1) return memo[i][j][k];
        if (k >= 2 * graph.size())
            memo[i][j][k] = 0;
        else if (i == 0)
            memo[i][j][k] = 1;
        else if (i == j)
            memo[i][j][k] = 2;
        else if (k % 2) {
            bool tie = false, win = false;
            for (int next : graph[j]) {
                if (next == 0) continue;
                int x = dfs(i, next, k + 1);
                if (x == 2) {
                    win = true;
                    memo[i][j][k] = 2;
                    break;
                }
                if (x == 0) tie = true;
            }
            if (!win) memo[i][j][k] = tie ? 0 : 1;
        } else {
            bool tie = false, win = false;
            for (int next : graph[i]) {
                int x = dfs(next, j, k + 1);
                if (x == 1) {
                    win = true;
                    memo[i][j][k] = 1;
                    break;
                }
                if (x == 0) tie = true;
            }
            if (!win) memo[i][j][k] = tie ? 0 : 2;
        }
        return memo[i][j][k];
    }
};
```

### **Go**

```go
func catMouseGame(graph [][]int) int {
	n := len(graph)
	memo := make([][][]int, n)
	for i := range memo {
		memo[i] = make([][]int, n)
		for j := range memo[i] {
			memo[i][j] = make([]int, 2*n+10)
			for k := range memo[i][j] {
				memo[i][j][k] = -1
			}
		}
	}
	var dfs func(i, j, k int) int
	dfs = func(i, j, k int) int {
		if memo[i][j][k] != -1 {
			return memo[i][j][k]
		}
		if k >= 2*len(graph) {
			memo[i][j][k] = 0
		} else if i == 0 {
			memo[i][j][k] = 1
		} else if i == j {
			memo[i][j][k] = 2
		} else if k%2 == 1 {
			tie, win := false, false
			for _, next := range graph[j] {
				if next == 0 {
					continue
				}
				x := dfs(i, next, k+1)
				if x == 2 {
					win = true
					memo[i][j][k] = 2
					break
				}
				if x == 0 {
					tie = true
				}
			}
			if !win {
				if tie {
					memo[i][j][k] = 0
				} else {
					memo[i][j][k] = 1
				}
			}
		} else {
			tie, win := false, false
			for _, next := range graph[i] {
				x := dfs(next, j, k+1)
				if x == 1 {
					win = true
					memo[i][j][k] = 1
					break
				}
				if x == 0 {
					tie = true
				}
			}
			if !win {
				if tie {
					memo[i][j][k] = 0
				} else {
					memo[i][j][k] = 2
				}
			}
		}
		return memo[i][j][k]
	}
	return dfs(1, 2, 0)
}
```

### **...**

```

```

<!-- tabs:end -->
