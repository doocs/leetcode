---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3552.Grid%20Teleportation%20Traversal/README_EN.md
rating: 2036
source: Weekly Contest 450 Q3
tags:
    - Breadth-First Search
    - Array
    - Hash Table
    - Matrix
---

<!-- problem:start -->

# [3552. Grid Teleportation Traversal](https://leetcode.com/problems/grid-teleportation-traversal)

[中文文档](/solution/3500-3599/3552.Grid%20Teleportation%20Traversal/README.md)

## Description

<!-- description:start -->

<p>You are given a 2D character grid <code>matrix</code> of size <code>m x n</code>, represented as an array of strings, where <code>matrix[i][j]</code> represents the cell at the intersection of the <code>i<sup>th</sup></code> row and <code>j<sup>th</sup></code> column. Each cell is one of the following:</p>

<ul>
	<li><code>&#39;.&#39;</code> representing an empty cell.</li>
	<li><code>&#39;#&#39;</code> representing an obstacle.</li>
	<li>An uppercase letter (<code>&#39;A&#39;</code>-<code>&#39;Z&#39;</code>) representing a teleportation portal.</li>
</ul>

<p>You start at the top-left cell <code>(0, 0)</code>, and your goal is to reach the bottom-right cell <code>(m - 1, n - 1)</code>. You can move from the current cell to any adjacent cell (up, down, left, right) as long as the destination cell is within the grid bounds and is not an obstacle<strong>.</strong></p>

<p>If you step on a cell containing a portal letter and you haven&#39;t used that portal letter before, you may instantly teleport to any other cell in the grid with the same letter. This teleportation does not count as a move, but each portal letter can be used<strong> at most </strong>once during your journey.</p>

<p>Return the <strong>minimum</strong> number of moves required to reach the bottom-right cell. If it is not possible to reach the destination, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">matrix = [&quot;A..&quot;,&quot;.A.&quot;,&quot;...&quot;]</span></p>

<p><strong>Output:</strong> 2</p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3552.Grid%20Teleportation%20Traversal/images/example04140.png" style="width: 151px; height: 151px;" /></p>

<ul>
	<li>Before the first move, teleport from <code>(0, 0)</code> to <code>(1, 1)</code>.</li>
	<li>In the first move, move from <code>(1, 1)</code> to <code>(1, 2)</code>.</li>
	<li>In the second move, move from <code>(1, 2)</code> to <code>(2, 2)</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">matrix = [&quot;.#...&quot;,&quot;.#.#.&quot;,&quot;.#.#.&quot;,&quot;...#.&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">13</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3552.Grid%20Teleportation%20Traversal/images/ezgifcom-animated-gif-maker.gif" style="width: 251px; height: 201px;" /></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m == matrix.length &lt;= 10<sup>3</sup></code></li>
	<li><code>1 &lt;= n == matrix[i].length &lt;= 10<sup>3</sup></code></li>
	<li><code>matrix[i][j]</code> is either <code>&#39;#&#39;</code>, <code>&#39;.&#39;</code>, or an uppercase English letter.</li>
	<li><code>matrix[0][0]</code> is not an obstacle.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: 0-1 BFS

We can use 0-1 BFS to solve this problem. We start from the top-left cell and use a double-ended queue to store the coordinates of the current cell. Each time we dequeue a cell, we check its four adjacent cells. If an adjacent cell is an empty cell and has not been visited, we add it to the queue and update its distance.

If an adjacent cell is a portal, we add it to the front of the queue and update its distance. We also need to maintain a dictionary to store the positions of each portal so that we can quickly find them when using a portal.

We also need a 2D array to store the distance for each cell, initialized to infinity. We set the distance of the starting point to 0 and then start BFS.

During the BFS process, we check whether each cell is the destination. If it is, we return its distance. If the queue is empty and the destination has not been reached, we return -1.

The time complexity is $O(m \times n)$, and the space complexity is $O(m \times n)$, where $m$ and $n$ are the number of rows and columns of the matrix, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minMoves(self, matrix: List[str]) -> int:
        m, n = len(matrix), len(matrix[0])
        g = defaultdict(list)
        for i, row in enumerate(matrix):
            for j, c in enumerate(row):
                if c.isalpha():
                    g[c].append((i, j))
        dirs = (-1, 0, 1, 0, -1)
        dist = [[inf] * n for _ in range(m)]
        dist[0][0] = 0
        q = deque([(0, 0)])
        while q:
            i, j = q.popleft()
            d = dist[i][j]
            if i == m - 1 and j == n - 1:
                return d
            c = matrix[i][j]
            if c in g:
                for x, y in g[c]:
                    if d < dist[x][y]:
                        dist[x][y] = d
                        q.appendleft((x, y))
                del g[c]
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if (
                    0 <= x < m
                    and 0 <= y < n
                    and matrix[x][y] != "#"
                    and d + 1 < dist[x][y]
                ):
                    dist[x][y] = d + 1
                    q.append((x, y))
        return -1
```

#### Java

```java
class Solution {
    public int minMoves(String[] matrix) {
        int m = matrix.length, n = matrix[0].length();
        Map<Character, List<int[]>> g = new HashMap<>();
        for (int i = 0; i < m; i++) {
            String row = matrix[i];
            for (int j = 0; j < n; j++) {
                char c = row.charAt(j);
                if (Character.isAlphabetic(c)) {
                    g.computeIfAbsent(c, k -> new ArrayList<>()).add(new int[] {i, j});
                }
            }
        }
        int[] dirs = {-1, 0, 1, 0, -1};
        int INF = Integer.MAX_VALUE / 2;
        int[][] dist = new int[m][n];
        for (int[] arr : dist) Arrays.fill(arr, INF);
        dist[0][0] = 0;
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[] {0, 0});
        while (!q.isEmpty()) {
            int[] cur = q.pollFirst();
            int i = cur[0], j = cur[1];
            int d = dist[i][j];
            if (i == m - 1 && j == n - 1) return d;
            char c = matrix[i].charAt(j);
            if (g.containsKey(c)) {
                for (int[] pos : g.get(c)) {
                    int x = pos[0], y = pos[1];
                    if (d < dist[x][y]) {
                        dist[x][y] = d;
                        q.addFirst(new int[] {x, y});
                    }
                }
                g.remove(c);
            }
            for (int idx = 0; idx < 4; idx++) {
                int a = dirs[idx], b = dirs[idx + 1];
                int x = i + a, y = j + b;
                if (0 <= x && x < m && 0 <= y && y < n && matrix[x].charAt(y) != '#'
                    && d + 1 < dist[x][y]) {
                    dist[x][y] = d + 1;
                    q.addLast(new int[] {x, y});
                }
            }
        }
        return -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minMoves(vector<string>& matrix) {
        int m = matrix.size(), n = matrix[0].size();
        unordered_map<char, vector<pair<int, int>>> g;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j) {
                char c = matrix[i][j];
                if (isalpha(c)) g[c].push_back({i, j});
            }
        int dirs[5] = {-1, 0, 1, 0, -1};
        int INF = numeric_limits<int>::max() / 2;
        vector<vector<int>> dist(m, vector<int>(n, INF));
        dist[0][0] = 0;
        deque<pair<int, int>> q;
        q.push_back({0, 0});
        while (!q.empty()) {
            auto [i, j] = q.front();
            q.pop_front();
            int d = dist[i][j];
            if (i == m - 1 && j == n - 1) return d;
            char c = matrix[i][j];
            if (g.count(c)) {
                for (auto [x, y] : g[c])
                    if (d < dist[x][y]) {
                        dist[x][y] = d;
                        q.push_front({x, y});
                    }
                g.erase(c);
            }
            for (int idx = 0; idx < 4; ++idx) {
                int x = i + dirs[idx], y = j + dirs[idx + 1];
                if (0 <= x && x < m && 0 <= y && y < n && matrix[x][y] != '#' && d + 1 < dist[x][y]) {
                    dist[x][y] = d + 1;
                    q.push_back({x, y});
                }
            }
        }
        return -1;
    }
};
```

#### Go

```go
type pair struct{ x, y int }

func minMoves(matrix []string) int {
	m, n := len(matrix), len(matrix[0])
	g := make(map[rune][]pair)
	for i := 0; i < m; i++ {
		for j, c := range matrix[i] {
			if unicode.IsLetter(c) {
				g[c] = append(g[c], pair{i, j})
			}
		}
	}
	dirs := []int{-1, 0, 1, 0, -1}
	INF := 1 << 30
	dist := make([][]int, m)
	for i := range dist {
		dist[i] = make([]int, n)
		for j := range dist[i] {
			dist[i][j] = INF
		}
	}
	dist[0][0] = 0
	q := list.New()
	q.PushBack(pair{0, 0})
	for q.Len() > 0 {
		cur := q.Remove(q.Front()).(pair)
		i, j := cur.x, cur.y
		d := dist[i][j]
		if i == m-1 && j == n-1 {
			return d
		}
		c := rune(matrix[i][j])
		if v, ok := g[c]; ok {
			for _, p := range v {
				x, y := p.x, p.y
				if d < dist[x][y] {
					dist[x][y] = d
					q.PushFront(pair{x, y})
				}
			}
			delete(g, c)
		}
		for idx := 0; idx < 4; idx++ {
			x, y := i+dirs[idx], j+dirs[idx+1]
			if 0 <= x && x < m && 0 <= y && y < n && matrix[x][y] != '#' && d+1 < dist[x][y] {
				dist[x][y] = d + 1
				q.PushBack(pair{x, y})
			}
		}
	}
	return -1
}
```

#### TypeScript

```ts
function minMoves(matrix: string[]): number {
    const m = matrix.length,
        n = matrix[0].length;
    const g = new Map<string, [number, number][]>();
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            const c = matrix[i][j];
            if (/^[A-Za-z]$/.test(c)) {
                if (!g.has(c)) g.set(c, []);
                g.get(c)!.push([i, j]);
            }
        }
    }

    const dirs = [-1, 0, 1, 0, -1];
    const INF = Number.MAX_SAFE_INTEGER;
    const dist: number[][] = Array.from({ length: m }, () => Array(n).fill(INF));
    dist[0][0] = 0;

    const cap = m * n * 2 + 5;
    const dq = new Array<[number, number]>(cap);
    let l = cap >> 1,
        r = cap >> 1;
    const pushFront = (v: [number, number]) => {
        dq[--l] = v;
    };
    const pushBack = (v: [number, number]) => {
        dq[r++] = v;
    };
    const popFront = (): [number, number] => dq[l++];
    const empty = () => l === r;

    pushBack([0, 0]);

    while (!empty()) {
        const [i, j] = popFront();
        const d = dist[i][j];
        if (i === m - 1 && j === n - 1) return d;

        const c = matrix[i][j];
        if (g.has(c)) {
            for (const [x, y] of g.get(c)!) {
                if (d < dist[x][y]) {
                    dist[x][y] = d;
                    pushFront([x, y]);
                }
            }
            g.delete(c);
        }

        for (let idx = 0; idx < 4; idx++) {
            const x = i + dirs[idx],
                y = j + dirs[idx + 1];
            if (0 <= x && x < m && 0 <= y && y < n && matrix[x][y] !== '#' && d + 1 < dist[x][y]) {
                dist[x][y] = d + 1;
                pushBack([x, y]);
            }
        }
    }
    return -1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
