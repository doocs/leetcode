# [1001. 网格照明](https://leetcode.cn/problems/grid-illumination)

[English Version](/solution/1000-1099/1001.Grid%20Illumination/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在大小为 <code>n x n</code> 的网格 <code>grid</code> 上，每个单元格都有一盏灯，最初灯都处于 <strong>关闭</strong> 状态。</p>

<p>给你一个由灯的位置组成的二维数组&nbsp;<code>lamps</code> ，其中 <code>lamps[i] = [row<sub>i</sub>, col<sub>i</sub>]</code> 表示 <strong>打开</strong> 位于 <code>grid[row<sub>i</sub>][col<sub>i</sub>]</code> 的灯。即便同一盏灯可能在 <code>lamps</code> 中多次列出，不会影响这盏灯处于 <strong>打开</strong> 状态。</p>

<p>当一盏灯处于打开状态，它将会照亮 <strong>自身所在单元格</strong> 以及同一 <strong>行</strong> 、同一 <strong>列</strong> 和两条 <strong>对角线</strong> 上的 <strong>所有其他单元格</strong> 。</p>

<p>另给你一个二维数组 <code>queries</code> ，其中 <code>queries[j] = [row<sub>j</sub>, col<sub>j</sub>]</code> 。对于第 <code>j</code> 个查询，如果单元格 <code>[row<sub>j</sub>, col<sub>j</sub>]</code> 是被照亮的，则查询结果为 <code>1</code> ，否则为 <code>0</code> 。在第 <code>j</code> 次查询之后 [按照查询的顺序] ，<strong>关闭</strong> 位于单元格 <code>grid[row<sub>j</sub>][col<sub>j</sub>]</code> 上及相邻 8 个方向上（与单元格 <code>grid[row<sub>i</sub>][col<sub>i</sub>]</code> 共享角或边）的任何灯。</p>

<p>返回一个整数数组 <code>ans</code> 作为答案， <code>ans[j]</code> 应等于第 <code>j</code> 次查询&nbsp;<code>queries[j]</code>&nbsp;的结果，<code>1</code> 表示照亮，<code>0</code> 表示未照亮。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1001.Grid%20Illumination/images/illu_1.jpg" style="height: 209px; width: 750px;" />
<pre>
<strong>输入：</strong>n = 5, lamps = [[0,0],[4,4]], queries = [[1,1],[1,0]]
<strong>输出：</strong>[1,0]
<strong>解释：</strong>最初所有灯都是关闭的。在执行查询之前，打开位于 [0, 0] 和 [4, 4] 的灯。第 0&nbsp;次查询检查 grid[1][1] 是否被照亮（蓝色方框）。该单元格被照亮，所以 ans[0] = 1 。然后，关闭红色方框中的所有灯。
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1001.Grid%20Illumination/images/illu_step1.jpg" style="height: 218px; width: 500px;" />
第 1&nbsp;次查询检查 grid[1][0] 是否被照亮（蓝色方框）。该单元格没有被照亮，所以 ans[1] = 0 。然后，关闭红色矩形中的所有灯。
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1001.Grid%20Illumination/images/illu_step2.jpg" style="height: 219px; width: 500px;" />
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 5, lamps = [[0,0],[4,4]], queries = [[1,1],[1,1]]
<strong>输出：</strong>[1,1]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 5, lamps = [[0,0],[0,4]], queries = [[0,4],[0,1],[1,4]]
<strong>输出：</strong>[1,1,0]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= lamps.length &lt;= 20000</code></li>
	<li><code>0 &lt;= queries.length &lt;= 20000</code></li>
	<li><code>lamps[i].length == 2</code></li>
	<li><code>0 &lt;= row<sub>i</sub>, col<sub>i</sub> &lt; n</code></li>
	<li><code>queries[j].length == 2</code></li>
	<li><code>0 &lt;= row<sub>j</sub>, col<sub>j</sub> &lt; n</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

哈希表。

假设一盏灯的坐标为 `(x, y)`，那么它所在的行的数值为 x，列的数值为 y，正对角线的数值为 x-y，反对角线的数值为 x+y。确定某一直线的唯一数值标识后，我们就可以通过哈希表来记录某一直线所拥有的灯的数目。

遍历 lamps，将当前遍历到的灯所在的行、列和正、反对角线拥有灯的数目分别加 1。

在处理 lamps 时，需要进行去重，因为我们将重复的灯看作同一盏灯。

遍历 queries，判断当前查询点所在的行，列和正、反对角线是否有灯，如果有，则置 1，即该点在查询时是被照亮的。然后进行关闭操作，查找查询点所在的八近邻点及它本身是否有灯，如果有，将该点所在的行、列和正、反对角线的灯数目分别减 1，并且将灯从网格中去掉。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
