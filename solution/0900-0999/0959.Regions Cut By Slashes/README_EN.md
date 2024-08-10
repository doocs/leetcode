---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0959.Regions%20Cut%20By%20Slashes/README_EN.md
tags:
    - Depth-First Search
    - Breadth-First Search
    - Union Find
    - Array
    - Hash Table
    - Matrix
---

<!-- problem:start -->

# [959. Regions Cut By Slashes](https://leetcode.com/problems/regions-cut-by-slashes)

[中文文档](/solution/0900-0999/0959.Regions%20Cut%20By%20Slashes/README.md)

## Description

<!-- description:start -->

<p>An <code>n x n</code> grid is composed of <code>1 x 1</code> squares where each <code>1 x 1</code> square consists of a <code>&#39;/&#39;</code>, <code>&#39;\&#39;</code>, or blank space <code>&#39; &#39;</code>. These characters divide the square into contiguous regions.</p>

<p>Given the grid <code>grid</code> represented as a string array, return <em>the number of regions</em>.</p>

<p>Note that backslash characters are escaped, so a <code>&#39;\&#39;</code> is represented as <code>&#39;\\&#39;</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0959.Regions%20Cut%20By%20Slashes/images/1.png" style="width: 200px; height: 200px;" />
<pre>
<strong>Input:</strong> grid = [&quot; /&quot;,&quot;/ &quot;]
<strong>Output:</strong> 2
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0959.Regions%20Cut%20By%20Slashes/images/2.png" style="width: 200px; height: 198px;" />
<pre>
<strong>Input:</strong> grid = [&quot; /&quot;,&quot;  &quot;]
<strong>Output:</strong> 1
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0959.Regions%20Cut%20By%20Slashes/images/4.png" style="width: 200px; height: 200px;" />
<pre>
<strong>Input:</strong> grid = [&quot;/\\&quot;,&quot;\\/&quot;]
<strong>Output:</strong> 5
<strong>Explanation: </strong>Recall that because \ characters are escaped, &quot;\\/&quot; refers to \/, and &quot;/\\&quot; refers to /\.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == grid.length == grid[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 30</code></li>
	<li><code>grid[i][j]</code> is either <code>&#39;/&#39;</code>, <code>&#39;\&#39;</code>, or <code>&#39; &#39;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Union-Find

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def regionsBySlashes(self, grid: List[str]) -> int:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        def union(a, b):
            pa, pb = find(a), find(b)
            if pa != pb:
                p[pa] = pb
                nonlocal size
                size -= 1

        n = len(grid)
        size = n * n * 4
        p = list(range(size))
        for i, row in enumerate(grid):
            for j, v in enumerate(row):
                k = i * n + j
                if i < n - 1:
                    union(4 * k + 2, (k + n) * 4)
                if j < n - 1:
                    union(4 * k + 1, (k + 1) * 4 + 3)
                if v == '/':
                    union(4 * k, 4 * k + 3)
                    union(4 * k + 1, 4 * k + 2)
                elif v == '\\':
                    union(4 * k, 4 * k + 1)
                    union(4 * k + 2, 4 * k + 3)
                else:
                    union(4 * k, 4 * k + 1)
                    union(4 * k + 1, 4 * k + 2)
                    union(4 * k + 2, 4 * k + 3)
        return size
```

#### Java

```java
class Solution {
    private int[] p;
    private int size;

    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        size = n * n * 4;
        p = new int[size];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                int k = i * n + j;
                if (i < n - 1) {
                    union(4 * k + 2, (k + n) * 4);
                }
                if (j < n - 1) {
                    union(4 * k + 1, (k + 1) * 4 + 3);
                }
                char v = grid[i].charAt(j);
                if (v == '/') {
                    union(4 * k, 4 * k + 3);
                    union(4 * k + 1, 4 * k + 2);
                } else if (v == '\\') {
                    union(4 * k, 4 * k + 1);
                    union(4 * k + 2, 4 * k + 3);
                } else {
                    union(4 * k, 4 * k + 1);
                    union(4 * k + 1, 4 * k + 2);
                    union(4 * k + 2, 4 * k + 3);
                }
            }
        }
        return size;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    private void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa == pb) {
            return;
        }
        p[pa] = pb;
        --size;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> p;
    int size;

    int regionsBySlashes(vector<string>& grid) {
        int n = grid.size();
        size = n * n * 4;
        p.resize(size);
        for (int i = 0; i < size; ++i) p[i] = i;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                int k = i * n + j;
                if (i < n - 1) merge(4 * k + 2, (k + n) * 4);
                if (j < n - 1) merge(4 * k + 1, (k + 1) * 4 + 3);
                char v = grid[i][j];
                if (v == '/') {
                    merge(4 * k, 4 * k + 3);
                    merge(4 * k + 1, 4 * k + 2);
                } else if (v == '\\') {
                    merge(4 * k, 4 * k + 1);
                    merge(4 * k + 2, 4 * k + 3);
                } else {
                    merge(4 * k, 4 * k + 1);
                    merge(4 * k + 1, 4 * k + 2);
                    merge(4 * k + 2, 4 * k + 3);
                }
            }
        }
        return size;
    }

    void merge(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa == pb) return;
        p[pa] = pb;
        --size;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};
```

#### Go

```go
func regionsBySlashes(grid []string) int {
	n := len(grid)
	size := n * n * 4
	p := make([]int, size)
	for i := range p {
		p[i] = i
	}
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	union := func(a, b int) {
		pa, pb := find(a), find(b)
		if pa == pb {
			return
		}
		p[pa] = pb
		size--
	}
	for i, row := range grid {
		for j, v := range row {
			k := i*n + j
			if i < n-1 {
				union(4*k+2, (k+n)*4)
			}
			if j < n-1 {
				union(4*k+1, (k+1)*4+3)
			}
			if v == '/' {
				union(4*k, 4*k+3)
				union(4*k+1, 4*k+2)
			} else if v == '\\' {
				union(4*k, 4*k+1)
				union(4*k+2, 4*k+3)
			} else {
				union(4*k, 4*k+1)
				union(4*k+1, 4*k+2)
				union(4*k+2, 4*k+3)
			}
		}
	}
	return size
}
```

#### TypeScript

```ts
function regionsBySlashes(grid: string[]): number {
    const find = (x: number) => {
        if (p[x] !== x) {
            p[x] = find(p[x]);
        }
        return p[x];
    };

    const union = (a: number, b: number) => {
        const pa = find(a);
        const pb = find(b);
        if (pa !== pb) {
            p[pa] = pb;
            size--;
        }
    };

    const n = grid.length;
    let size = n * n * 4;
    const p = Array.from({ length: size }, (_, i) => i);

    for (let i = 0; i < n; i++) {
        for (let j = 0; j < n; j++) {
            const k = i * n + j;
            if (i < n - 1) {
                union(4 * k + 2, (k + n) * 4);
            }
            if (j < n - 1) {
                union(4 * k + 1, (k + 1) * 4 + 3);
            }
            if (grid[i][j] === '/') {
                union(4 * k, 4 * k + 3);
                union(4 * k + 1, 4 * k + 2);
            } else if (grid[i][j] === '\\') {
                union(4 * k, 4 * k + 1);
                union(4 * k + 2, 4 * k + 3);
            } else {
                union(4 * k, 4 * k + 1);
                union(4 * k + 1, 4 * k + 2);
                union(4 * k + 2, 4 * k + 3);
            }
        }
    }

    return size;
}
```

#### JavaScript

```js
/**
 * @param {string[]} grid
 * @return {number}
 */

function regionsBySlashes(grid) {
    const find = x => {
        if (p[x] !== x) {
            p[x] = find(p[x]);
        }
        return p[x];
    };

    const union = (a, b) => {
        const pa = find(a);
        const pb = find(b);
        if (pa !== pb) {
            p[pa] = pb;
            size--;
        }
    };

    const n = grid.length;
    let size = n * n * 4;
    const p = Array.from({ length: size }, (_, i) => i);

    for (let i = 0; i < n; i++) {
        for (let j = 0; j < n; j++) {
            const k = i * n + j;
            if (i < n - 1) {
                union(4 * k + 2, (k + n) * 4);
            }
            if (j < n - 1) {
                union(4 * k + 1, (k + 1) * 4 + 3);
            }
            if (grid[i][j] === '/') {
                union(4 * k, 4 * k + 3);
                union(4 * k + 1, 4 * k + 2);
            } else if (grid[i][j] === '\\') {
                union(4 * k, 4 * k + 1);
                union(4 * k + 2, 4 * k + 3);
            } else {
                union(4 * k, 4 * k + 1);
                union(4 * k + 1, 4 * k + 2);
                union(4 * k + 2, 4 * k + 3);
            }
        }
    }

    return size;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: DFS

<!-- tabs:start -->

#### TypeScript

```ts
function regionsBySlashes(grid: string[]): number {
    const createGraph = () => {
        const n = grid.length;
        const g = Array.from({ length: n * 2 }, () => Array(n * 2).fill(0));

        for (let i = 0; i < n; i++) {
            for (let j = 0; j < n; j++) {
                const [y, x] = [i * 2, j * 2];

                switch (grid[i][j]) {
                    case '/':
                        g[y][x] = g[y + 1][x + 1] = 0;
                        g[y][x + 1] = g[y + 1][x] = 1;
                        break;

                    case '\\':
                        g[y][x] = g[y + 1][x + 1] = 2;
                        g[y][x + 1] = g[y + 1][x] = 0;
                        break;

                    default:
                        g[y][x] = g[y][x + 1] = g[y + 1][x] = g[y + 1][x + 1] = 0;
                        break;
                }
            }
        }

        return g;
    };

    const isValid = (x: number) => 0 <= x && x < n;
    const dfs = (i: number, j: number) => {
        if (!isValid(i) || !isValid(j) || g[i][j]) return;

        g[i][j] = -1;
        const dirs = [-1, 0, 1, 0, -1];
        const neighbours: number[] = [];

        for (let d = 0; d < 4; d++) {
            const [y, x] = [i + dirs[d], j + dirs[d + 1]];

            if (isValid(y) && isValid(x)) {
                dfs(y, x);
                neighbours.push(g[y][x]);
            } else {
                neighbours.push(-1);
            }
        }

        const [top, right, bottom, left] = neighbours;
        if (top === 1 && right === 1) dfs(i - 1, j + 1);
        if (bottom === 1 && left === 1) dfs(i + 1, j - 1);
        if (top === 2 && left === 2) dfs(i - 1, j - 1);
        if (bottom === 2 && right === 2) dfs(i + 1, j + 1);
    };

    const g = createGraph();
    const n = g.length;
    let res = 0;

    for (let i = 0; i < n; i++) {
        for (let j = 0; j < n; j++) {
            if (g[i][j] === 0) {
                dfs(i, j);
                res++;
            }
        }
    }

    return res;
}
```

#### JavaScript

```js
function regionsBySlashes(grid) {
    const createGraph = () => {
        const n = grid.length;
        const g = Array.from({ length: n * 2 }, () => Array(n * 2).fill(0));

        for (let i = 0; i < n; i++) {
            for (let j = 0; j < n; j++) {
                const [y, x] = [i * 2, j * 2];

                switch (grid[i][j]) {
                    case '/':
                        g[y][x] = g[y + 1][x + 1] = 0;
                        g[y][x + 1] = g[y + 1][x] = 1;
                        break;

                    case '\\':
                        g[y][x] = g[y + 1][x + 1] = 2;
                        g[y][x + 1] = g[y + 1][x] = 0;
                        break;

                    default:
                        g[y][x] = g[y][x + 1] = g[y + 1][x] = g[y + 1][x + 1] = 0;
                        break;
                }
            }
        }

        return g;
    };

    const isValid = x => 0 <= x && x < n;
    const dfs = (i, j) => {
        if (!isValid(i) || !isValid(j) || g[i][j]) return;

        g[i][j] = -1;
        const dirs = [-1, 0, 1, 0, -1];
        const neighbours = [];

        for (let d = 0; d < 4; d++) {
            const [y, x] = [i + dirs[d], j + dirs[d + 1]];

            if (isValid(y) && isValid(x)) {
                dfs(y, x);
                neighbours.push(g[y][x]);
            } else {
                neighbours.push(-1);
            }
        }

        const [top, right, bottom, left] = neighbours;
        if (top === 1 && right === 1) dfs(i - 1, j + 1);
        if (bottom === 1 && left === 1) dfs(i + 1, j - 1);
        if (top === 2 && left === 2) dfs(i - 1, j - 1);
        if (bottom === 2 && right === 2) dfs(i + 1, j + 1);
    };

    const g = createGraph();
    const n = g.length;
    let res = 0;

    for (let i = 0; i < n; i++) {
        for (let j = 0; j < n; j++) {
            if (g[i][j] === 0) {
                dfs(i, j);
                res++;
            }
        }
    }

    return res;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
