# [1263. Minimum Moves to Move a Box to Their Target Location](https://leetcode.com/problems/minimum-moves-to-move-a-box-to-their-target-location)

[中文文档](/solution/1200-1299/1263.Minimum%20Moves%20to%20Move%20a%20Box%20to%20Their%20Target%20Location/README.md)

## Description

<p>A storekeeper is a game in which the player pushes boxes around in a warehouse trying to get them to target locations.</p>

<p>The game is represented by an <code>m x n</code> grid of characters <code>grid</code> where each element is a wall, floor, or box.</p>

<p>Your task is to move the box <code>&#39;B&#39;</code> to the target position <code>&#39;T&#39;</code> under the following rules:</p>

<ul>
	<li>The character <code>&#39;S&#39;</code> represents the player. The player can move up, down, left, right in <code>grid</code> if it is a floor (empty cell).</li>
	<li>The character <code>&#39;.&#39;</code> represents the floor which means a free cell to walk.</li>
	<li>The character<font face="monospace">&nbsp;</font><code>&#39;#&#39;</code><font face="monospace">&nbsp;</font>represents the wall which means an obstacle (impossible to walk there).</li>
	<li>There is only one box <code>&#39;B&#39;</code> and one target cell <code>&#39;T&#39;</code> in the <code>grid</code>.</li>
	<li>The box can be moved to an adjacent free cell by standing next to the box and then moving in the direction of the box. This is a <strong>push</strong>.</li>
	<li>The player cannot walk through the box.</li>
</ul>

<p>Return <em>the minimum number of <strong>pushes</strong> to move the box to the target</em>. If there is no way to reach the target, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1263.Minimum%20Moves%20to%20Move%20a%20Box%20to%20Their%20Target%20Location/images/sample_1_1620.png" style="width: 500px; height: 335px;" />
<pre>
<strong>Input:</strong> grid = [[&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;],
               [&quot;#&quot;,&quot;T&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;],
               [&quot;#&quot;,&quot;.&quot;,&quot;.&quot;,&quot;B&quot;,&quot;.&quot;,&quot;#&quot;],
               [&quot;#&quot;,&quot;.&quot;,&quot;#&quot;,&quot;#&quot;,&quot;.&quot;,&quot;#&quot;],
               [&quot;#&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;S&quot;,&quot;#&quot;],
               [&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> We return only the number of times the box is pushed.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> grid = [[&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;],
               [&quot;#&quot;,&quot;T&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;],
               [&quot;#&quot;,&quot;.&quot;,&quot;.&quot;,&quot;B&quot;,&quot;.&quot;,&quot;#&quot;],
               [&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;.&quot;,&quot;#&quot;],
               [&quot;#&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;S&quot;,&quot;#&quot;],
               [&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;]]
<strong>Output:</strong> -1
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> grid = [[&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;],
               [&quot;#&quot;,&quot;T&quot;,&quot;.&quot;,&quot;.&quot;,&quot;#&quot;,&quot;#&quot;],
               [&quot;#&quot;,&quot;.&quot;,&quot;#&quot;,&quot;B&quot;,&quot;.&quot;,&quot;#&quot;],
               [&quot;#&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;#&quot;],
               [&quot;#&quot;,&quot;.&quot;,&quot;.&quot;,&quot;.&quot;,&quot;S&quot;,&quot;#&quot;],
               [&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;]]
<strong>Output:</strong> 5
<strong>Explanation:</strong> push the box down, left, left, up and up.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 20</code></li>
	<li><code>grid</code> contains only characters <code>&#39;.&#39;</code>, <code>&#39;#&#39;</code>, <code>&#39;S&#39;</code>, <code>&#39;T&#39;</code>, or <code>&#39;B&#39;</code>.</li>
	<li>There is only one character <code>&#39;S&#39;</code>, <code>&#39;B&#39;</code>, and <code>&#39;T&#39;</code> in the <code>grid</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minPushBox(self, grid: List[List[str]]) -> int:
        def f(i: int, j: int) -> int:
            return i * n + j

        def check(i: int, j: int) -> bool:
            return 0 <= i < m and 0 <= j < n and grid[i][j] != "#"

        for i, row in enumerate(grid):
            for j, c in enumerate(row):
                if c == "S":
                    si, sj = i, j
                elif c == "B":
                    bi, bj = i, j
        m, n = len(grid), len(grid[0])
        dirs = (-1, 0, 1, 0, -1)
        q = deque([(f(si, sj), f(bi, bj), 0)])
        vis = [[False] * (m * n) for _ in range(m * n)]
        vis[f(si, sj)][f(bi, bj)] = True
        while q:
            s, b, d = q.popleft()
            bi, bj = b // n, b % n
            if grid[bi][bj] == "T":
                return d
            si, sj = s // n, s % n
            for a, b in pairwise(dirs):
                sx, sy = si + a, sj + b
                if not check(sx, sy):
                    continue
                if sx == bi and sy == bj:
                    bx, by = bi + a, bj + b
                    if not check(bx, by) or vis[f(sx, sy)][f(bx, by)]:
                        continue
                    vis[f(sx, sy)][f(bx, by)] = True
                    q.append((f(sx, sy), f(bx, by), d + 1))
                elif not vis[f(sx, sy)][f(bi, bj)]:
                    vis[f(sx, sy)][f(bi, bj)] = True
                    q.appendleft((f(sx, sy), f(bi, bj), d))
        return -1
```

### **Java**

```java
class Solution {
    private int m;
    private int n;
    private char[][] grid;

    public int minPushBox(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        int si = 0, sj = 0, bi = 0, bj = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 'S') {
                    si = i;
                    sj = j;
                } else if (grid[i][j] == 'B') {
                    bi = i;
                    bj = j;
                }
            }
        }
        int[] dirs = {-1, 0, 1, 0, -1};
        Deque<int[]> q = new ArrayDeque<>();
        boolean[][] vis = new boolean[m * n][m * n];
        q.offer(new int[] {f(si, sj), f(bi, bj), 0});
        vis[f(si, sj)][f(bi, bj)] = true;
        while (!q.isEmpty()) {
            var p = q.poll();
            int d = p[2];
            bi = p[1] / n;
            bj = p[1] % n;
            if (grid[bi][bj] == 'T') {
                return d;
            }
            si = p[0] / n;
            sj = p[0] % n;
            for (int k = 0; k < 4; ++k) {
                int sx = si + dirs[k], sy = sj + dirs[k + 1];
                if (!check(sx, sy)) {
                    continue;
                }
                if (sx == bi && sy == bj) {
                    int bx = bi + dirs[k], by = bj + dirs[k + 1];
                    if (!check(bx, by) || vis[f(sx, sy)][f(bx, by)]) {
                        continue;
                    }
                    vis[f(sx, sy)][f(bx, by)] = true;
                    q.offer(new int[] {f(sx, sy), f(bx, by), d + 1});
                } else if (!vis[f(sx, sy)][f(bi, bj)]) {
                    vis[f(sx, sy)][f(bi, bj)] = true;
                    q.offerFirst(new int[] {f(sx, sy), f(bi, bj), d});
                }
            }
        }
        return -1;
    }

    private int f(int i, int j) {
        return i * n + j;
    }

    private boolean check(int i, int j) {
        return i >= 0 && i < m && j >= 0 && j < n && grid[i][j] != '#';
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minPushBox(vector<vector<char>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int si, sj, bi, bj;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 'S') {
                    si = i, sj = j;
                } else if (grid[i][j] == 'B') {
                    bi = i, bj = j;
                }
            }
        }
        auto f = [&](int i, int j) {
            return i * n + j;
        };
        auto check = [&](int i, int j) {
            return i >= 0 && i < m && j >= 0 && j < n && grid[i][j] != '#';
        };
        int dirs[5] = {-1, 0, 1, 0, -1};
        deque<tuple<int, int, int>> q;
        q.emplace_back(f(si, sj), f(bi, bj), 0);
        bool vis[m * n][m * n];
        memset(vis, false, sizeof(vis));
        vis[f(si, sj)][f(bi, bj)] = true;
        while (!q.empty()) {
            auto [s, b, d] = q.front();
            q.pop_front();
            si = s / n, sj = s % n;
            bi = b / n, bj = b % n;
            if (grid[bi][bj] == 'T') {
                return d;
            }
            for (int k = 0; k < 4; ++k) {
                int sx = si + dirs[k], sy = sj + dirs[k + 1];
                if (!check(sx, sy)) {
                    continue;
                }
                if (sx == bi && sy == bj) {
                    int bx = bi + dirs[k], by = bj + dirs[k + 1];
                    if (!check(bx, by) || vis[f(sx, sy)][f(bx, by)]) {
                        continue;
                    }
                    vis[f(sx, sy)][f(bx, by)] = true;
                    q.emplace_back(f(sx, sy), f(bx, by), d + 1);
                } else if (!vis[f(sx, sy)][f(bi, bj)]) {
                    vis[f(sx, sy)][f(bi, bj)] = true;
                    q.emplace_front(f(sx, sy), f(bi, bj), d);
                }
            }
        }
        return -1;
    }
};
```

### **Go**

```go
func minPushBox(grid [][]byte) int {
	m, n := len(grid), len(grid[0])
	var si, sj, bi, bj int
	for i, row := range grid {
		for j, c := range row {
			if c == 'S' {
				si, sj = i, j
			} else if c == 'B' {
				bi, bj = i, j
			}
		}
	}
	f := func(i, j int) int {
		return i*n + j
	}
	check := func(i, j int) bool {
		return i >= 0 && i < m && j >= 0 && j < n && grid[i][j] != '#'
	}
	q := [][]int{[]int{f(si, sj), f(bi, bj), 0}}
	vis := make([][]bool, m*n)
	for i := range vis {
		vis[i] = make([]bool, m*n)
	}
	vis[f(si, sj)][f(bi, bj)] = true
	dirs := [5]int{-1, 0, 1, 0, -1}
	for len(q) > 0 {
		p := q[0]
		q = q[1:]
		si, sj, bi, bj = p[0]/n, p[0]%n, p[1]/n, p[1]%n
		d := p[2]
		if grid[bi][bj] == 'T' {
			return d
		}
		for k := 0; k < 4; k++ {
			sx, sy := si+dirs[k], sj+dirs[k+1]
			if !check(sx, sy) {
				continue
			}
			if sx == bi && sy == bj {
				bx, by := bi+dirs[k], bj+dirs[k+1]
				if !check(bx, by) || vis[f(sx, sy)][f(bx, by)] {
					continue
				}
				vis[f(sx, sy)][f(bx, by)] = true
				q = append(q, []int{f(sx, sy), f(bx, by), d + 1})
			} else if !vis[f(sx, sy)][f(bi, bj)] {
				vis[f(sx, sy)][f(bi, bj)] = true
				q = append([][]int{[]int{f(sx, sy), f(bi, bj), d}}, q...)
			}
		}
	}
	return -1
}
```

### **TypeScript**

```ts
function minPushBox(grid: string[][]): number {
    const [m, n] = [grid.length, grid[0].length];
    let [si, sj, bi, bj] = [0, 0, 0, 0];
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (grid[i][j] === 'S') {
                [si, sj] = [i, j];
            } else if (grid[i][j] === 'B') {
                [bi, bj] = [i, j];
            }
        }
    }
    const f = (i: number, j: number): number => i * n + j;
    const check = (i: number, j: number): boolean =>
        i >= 0 && i < m && j >= 0 && j < n && grid[i][j] !== '#';

    const q: Deque<[number, number, number]> = new Deque();
    const vis: boolean[][] = new Array(m * n)
        .fill(0)
        .map(() => new Array(m * n).fill(false));
    q.push([f(si, sj), f(bi, bj), 0]);
    vis[f(si, sj)][f(bi, bj)] = true;
    const dirs: number[] = [-1, 0, 1, 0, -1];
    while (q.size() > 0) {
        const [s, b, d] = q.shift()!;
        const [si, sj] = [Math.floor(s / n), s % n];
        const [bi, bj] = [Math.floor(b / n), b % n];
        if (grid[bi][bj] === 'T') {
            return d;
        }
        for (let k = 0; k < 4; ++k) {
            const [sx, sy] = [si + dirs[k], sj + dirs[k + 1]];
            if (!check(sx, sy)) {
                continue;
            }
            if (sx === bi && sy === bj) {
                const [bx, by] = [bi + dirs[k], bj + dirs[k + 1]];
                if (!check(bx, by) || vis[f(sx, sy)][f(bx, by)]) {
                    continue;
                }
                vis[f(sx, sy)][f(bx, by)] = true;
                q.push([f(sx, sy), f(bx, by), d + 1]);
            } else if (!vis[f(sx, sy)][f(bi, bj)]) {
                vis[f(sx, sy)][f(bi, bj)] = true;
                q.unshift([f(sx, sy), f(bi, bj), d]);
            }
        }
    }
    return -1;
}

/* 以下是双向列队模板类 */
class CircularDeque<T> {
    prev: CircularDeque<T> | null;
    next: CircularDeque<T> | null;
    begin: number;
    end: number;
    empty: boolean;
    data: T[];
    constructor(N: number) {
        this.prev = this.next = null;
        this.begin = this.end = 0;
        this.empty = true;
        this.data = Array(N);
    }

    isFull(): boolean {
        return this.end === this.begin && !this.empty;
    }

    isEmpty(): boolean {
        return this.empty;
    }

    push(val: T): boolean {
        if (this.isFull()) return false;
        this.empty = false;
        this.data[this.end] = val;
        this.end = (this.end + 1) % this.data.length;
        return true;
    }

    front(): T | undefined {
        return this.isEmpty() ? undefined : this.data[this.begin];
    }

    back(): T | undefined {
        return this.isEmpty() ? undefined : this.data[this.end - 1];
    }

    pop(): T | undefined {
        if (this.isEmpty()) return undefined;
        const value = this.data[this.end - 1];
        this.end = (this.end - 1) % this.data.length;
        if (this.end < 0) this.end += this.data.length;
        if (this.end === this.begin) this.empty = true;
        return value;
    }

    unshift(val: T): boolean {
        if (this.isFull()) return false;
        this.empty = false;
        this.begin = (this.begin - 1) % this.data.length;
        if (this.begin < 0) this.begin += this.data.length;
        this.data[this.begin] = val;
        return true;
    }

    shift(): T | undefined {
        if (this.isEmpty()) return undefined;
        const value = this.data[this.begin];
        this.begin = (this.begin + 1) % this.data.length;
        if (this.end === this.begin) this.empty = true;
        return value;
    }

    *values(): Generator<T, void, undefined> {
        if (this.isEmpty()) return undefined;
        let i = this.begin;
        do {
            yield this.data[i];
            i = (i + 1) % this.data.length;
        } while (i !== this.end);
    }
}

class Deque<T> {
    head: CircularDeque<T>;
    tail: CircularDeque<T>;
    _size: number;
    constructor(collection: T[] = []) {
        this.head = new CircularDeque<T>(128);
        this.tail = new CircularDeque<T>(128);
        this.tail.empty = this.head.empty = false;
        this.tail.prev = this.head;
        this.head.next = this.tail;
        this._size = 0;
        for (const item of collection) this.push(item);
    }

    size(): number {
        return this._size;
    }

    push(val: T): void {
        let last = this.tail.prev!;
        if (last.isFull()) {
            const inserted = new CircularDeque<T>(128);

            this.tail.prev = inserted;
            inserted.next = this.tail;

            last.next = inserted;
            inserted.prev = last;

            last = inserted;
        }
        last.push(val);
        this._size++;
    }

    back(): T | undefined {
        if (this._size === 0) return;
        return this.tail.prev!.back();
    }

    pop(): T | undefined {
        if (this.head.next === this.tail) return undefined;
        const last = this.tail.prev!;
        const value = last.pop();
        if (last.isEmpty()) {
            this.tail.prev = last.prev;
            last.prev!.next = this.tail;
        }
        this._size--;
        return value;
    }

    unshift(val: T): void {
        let first = this.head.next!;
        if (first.isFull()) {
            const inserted = new CircularDeque<T>(128);

            this.head.next = inserted;
            inserted.prev = this.head;

            inserted.next = first;
            first.prev = inserted;

            first = inserted;
        }
        first.unshift(val);
        this._size++;
    }

    shift(): T | undefined {
        if (this.head.next === this.tail) return undefined;
        const first = this.head.next!;
        const value = first.shift();
        if (first.isEmpty()) {
            this.head.next = first.next;
            first.next!.prev = this.head;
        }
        this._size--;
        return value;
    }

    front(): T | undefined {
        if (this._size === 0) return undefined;
        return this.head.next!.front();
    }

    *values(): Generator<T, void, undefined> {
        let node = this.head.next!;
        while (node !== this.tail) {
            for (const value of node.values()) yield value;
            node = node.next!;
        }
    }
}
```

### **...**

```

```

<!-- tabs:end -->
