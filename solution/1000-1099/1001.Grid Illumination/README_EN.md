# [1001. Grid Illumination](https://leetcode.com/problems/grid-illumination)

[中文文档](/solution/1000-1099/1001.Grid%20Illumination/README.md)

## Description

<p>There is a 2D <code>grid</code> of size <code>n x n</code> where each cell of this grid has a lamp that is initially <strong>turned off</strong>.</p>

<p>You are given a 2D array of lamp positions <code>lamps</code>, where <code>lamps[i] = [row<sub>i</sub>, col<sub>i</sub>]</code> indicates that the lamp at <code>grid[row<sub>i</sub>][col<sub>i</sub>]</code> is <strong>turned on</strong>. Even if the same lamp is listed more than once, it is turned on.</p>

<p>When a lamp is turned on, it <strong>illuminates its cell</strong> and <strong>all other cells</strong> in the same <strong>row, column, or diagonal</strong>.</p>

<p>You are also given another 2D array <code>queries</code>, where <code>queries[j] = [row<sub>j</sub>, col<sub>j</sub>]</code>. For the <code>j<sup>th</sup></code> query, determine whether <code>grid[row<sub>j</sub>][col<sub>j</sub>]</code> is illuminated or not. After answering the <code>j<sup>th</sup></code> query, <strong>turn off</strong> the lamp at <code>grid[row<sub>j</sub>][col<sub>j</sub>]</code> and its <strong>8 adjacent lamps</strong> if they exist. A lamp is adjacent if its cell shares either a side or corner with <code>grid[row<sub>j</sub>][col<sub>j</sub>]</code>.</p>

<p>Return <em>an array of integers </em><code>ans</code><em>,</em><em> where </em><code>ans[j]</code><em> should be </em><code>1</code><em> if the cell in the </em><code>j<sup>th</sup></code><em> query was illuminated, or </em><code>0</code><em> if the lamp was not.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1001.Grid%20Illumination/images/illu_1.jpg" style="width: 750px; height: 209px;" />
<pre>
<strong>Input:</strong> n = 5, lamps = [[0,0],[4,4]], queries = [[1,1],[1,0]]
<strong>Output:</strong> [1,0]
<strong>Explanation:</strong> We have the initial grid with all lamps turned off. In the above picture we see the grid after turning on the lamp at grid[0][0] then turning on the lamp at grid[4][4].
The 0<sup>th</sup>&nbsp;query asks if the lamp at grid[1][1] is illuminated or not (the blue square). It is illuminated, so set ans[0] = 1. Then, we turn off all lamps in the red square.
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1001.Grid%20Illumination/images/illu_step1.jpg" style="width: 500px; height: 218px;" />
The 1<sup>st</sup>&nbsp;query asks if the lamp at grid[1][0] is illuminated or not (the blue square). It is not illuminated, so set ans[1] = 0. Then, we turn off all lamps in the red rectangle.
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1001.Grid%20Illumination/images/illu_step2.jpg" style="width: 500px; height: 219px;" />
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 5, lamps = [[0,0],[4,4]], queries = [[1,1],[1,1]]
<strong>Output:</strong> [1,1]
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 5, lamps = [[0,0],[0,4]], queries = [[0,4],[0,1],[1,4]]
<strong>Output:</strong> [1,1,0]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= lamps.length &lt;= 20000</code></li>
	<li><code>0 &lt;= queries.length &lt;= 20000</code></li>
	<li><code>lamps[i].length == 2</code></li>
	<li><code>0 &lt;= row<sub>i</sub>, col<sub>i</sub> &lt; n</code></li>
	<li><code>queries[j].length == 2</code></li>
	<li><code>0 &lt;= row<sub>j</sub>, col<sub>j</sub> &lt; n</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def gridIllumination(
        self, n: int, lamps: List[List[int]], queries: List[List[int]]
    ) -> List[int]:
        points = set()
        rcnt, ccnt, dgcnt, udgcnt = Counter(), Counter(), Counter(), Counter()
        for r, c in lamps:
            if (r, c) not in points:
                points.add((r, c))
                rcnt[r] += 1
                ccnt[c] += 1
                dgcnt[r - c] += 1
                udgcnt[r + c] += 1
        ans = [0] * len(queries)
        for i, q in enumerate(queries):
            r, c = q
            if rcnt[r] or ccnt[c] or dgcnt[r - c] or udgcnt[r + c]:
                ans[i] = 1
                for a, b in [
                    (0, 1),
                    (1, 0),
                    (0, -1),
                    (-1, 0),
                    (0, 0),
                    (1, 1),
                    (-1, 1),
                    (1, -1),
                    (-1, -1),
                ]:
                    x, y = r + a, c + b
                    if (x, y) in points:
                        points.remove((x, y))
                        rcnt[x] -= 1
                        ccnt[y] -= 1
                        dgcnt[x - y] -= 1
                        udgcnt[x + y] -= 1
        return ans
```

### **Java**

```java
class Solution {
    public int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        Set<Long> points = new HashSet<>();
        Map<Integer, Integer> rcnt = new HashMap<>();
        Map<Integer, Integer> ccnt = new HashMap<>();
        Map<Integer, Integer> dgcnt = new HashMap<>();
        Map<Integer, Integer> udgcnt = new HashMap<>();
        for (int[] l : lamps) {
            int r = l[0], c = l[1];
            long v = r * n + c;
            if (!points.contains(v)) {
                points.add(v);
                rcnt.put(r, rcnt.getOrDefault(r, 0) + 1);
                ccnt.put(c, ccnt.getOrDefault(c, 0) + 1);
                dgcnt.put(r - c, dgcnt.getOrDefault(r - c, 0) + 1);
                udgcnt.put(r + c, udgcnt.getOrDefault(r + c, 0) + 1);
            }
        }
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {0, 0}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            int r = queries[i][0], c = queries[i][1];
            if (rcnt.getOrDefault(r, 0) > 0 || ccnt.getOrDefault(c, 0) > 0 || dgcnt.getOrDefault(r - c, 0) > 0 || udgcnt.getOrDefault(r + c, 0) > 0) {
                ans[i] = 1;
                for (int[] d : dirs) {
                    int x = r + d[0], y = c + d[1];
                    long v = x * n + y;
                    if (x < 0 || x >= n || y < 0 || y >= n || !points.contains(v)) {
                        continue;
                    }
                    points.remove(v);
                    rcnt.put(x, rcnt.get(x) - 1);
                    if (rcnt.get(x) == 0) {
                        rcnt.remove(x);
                    }
                    ccnt.put(y, ccnt.get(y) - 1);
                    if (ccnt.get(y) == 0) {
                        ccnt.remove(y);
                    }
                    dgcnt.put(x - y, dgcnt.get(x - y) - 1);
                    if (dgcnt.get(x - y) == 0) {
                        dgcnt.remove(x - y);
                    }
                    udgcnt.put(x + y, udgcnt.get(x + y) - 1);
                    if (udgcnt.get(x + y) == 0) {
                        udgcnt.remove(x + y);
                    }
                }
            }
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function gridIllumination(
    n: number,
    lamps: number[][],
    queries: number[][],
): number[] {
    let lights: Set<string> = new Set();
    let rows: Map<number, number> = new Map(); // i
    let cols: Map<number, number> = new Map(); // j
    let mainDiagonal: Map<number, number> = new Map(); // i - j
    let subDiagonal: Map<number, number> = new Map(); // i + j
    for (let [i, j] of lamps) {
        let key = `${i},${j}`;
        if (lights.has(key)) continue;
        lights.add(key);
        rows.set(i, (rows.get(i) || 0) + 1);
        cols.set(j, (cols.get(j) || 0) + 1);
        mainDiagonal.set(i - j, (mainDiagonal.get(i - j) || 0) + 1);
        subDiagonal.set(i + j, (subDiagonal.get(i + j) || 0) + 1);
    }

    let ans: Array<number> = [];
    let directions = [
        [-1, -1],
        [-1, 0],
        [-1, 1],
        [0, -1],
        [0, 0],
        [0, 1],
        [1, -1],
        [1, 0],
        [1, 1],
    ];
    for (let [i, j] of queries) {
        // check
        const check =
            lights.has(`${i},${j}`) ||
            rows.get(i) ||
            cols.get(j) ||
            mainDiagonal.get(i - j) ||
            subDiagonal.get(i + j);
        ans.push(check ? 1 : 0);
        // close lamp
        for (let [dx, dy] of directions) {
            const [x, y] = [i + dx, j + dy];
            let key = `${x},${y}`;
            if (x < 0 || x > n - 1 || y < 0 || y > n - 1 || !lights.has(key)) {
                continue;
            }
            lights.delete(key);
            rows.set(x, rows.get(x) - 1);
            cols.set(y, cols.get(y) - 1);
            mainDiagonal.set(x - y, mainDiagonal.get(x - y) - 1);
            subDiagonal.set(x + y, subDiagonal.get(x + y) - 1);
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
