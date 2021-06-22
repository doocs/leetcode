# [200. 岛屿数量](https://leetcode-cn.com/problems/number-of-islands)

[English Version](/solution/0200-0299/0200.Number%20of%20Islands/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个由 <code>'1'</code>（陆地）和 <code>'0'</code>（水）组成的的二维网格，请你计算网格中岛屿的数量。</p>

<p>岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。</p>

<p>此外，你可以假设该网格的四条边均被水包围。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
<strong>输出：</strong>1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
<strong>输出：</strong>3
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 <= m, n <= 300</code></li>
	<li><code>grid[i][j]</code> 的值为 <code>'0'</code> 或 <code>'1'</code></li>
</ul>


## 解法

<!-- 这里可写通用的实现逻辑 -->

BFS/DFS 均可

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **TypeScript**

```ts
function numIslands(grid: string[][]): number {
    let m = grid.length, n = grid[0].length;
    let ans = 0;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (grid[i][j] == '1') {
                bfs(grid, i, j)
                ++ans;
            }
        }
    }
    return ans;
};

function bfs(grid: string[][], r: number, c: number): void {
    let m = grid.length, n = grid[0].length;
    let queue = new Array();
    queue.push([r, c]);
    while (queue.length > 0) {
        let [i, j] = queue.shift();
        for (let [dx, dy] of [[0, 1], [0, -1], [1, 0], [-1, 0]]) {
            let x = i + dx, y = j + dy;
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1') {
                grid[x][y] = '0';
                queue.push([x, y]);
            }
        }
    }
}
```

### **...**

```

```

<!-- tabs:end -->
