# [490. 迷宫](https://leetcode-cn.com/problems/the-maze)

[English Version](/solution/0400-0499/0490.The%20Maze/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>由空地和墙组成的迷宫中有一个<strong>球</strong>。球可以向<strong>上下左右</strong>四个方向滚动，但在遇到墙壁前不会停止滚动。当球停下时，可以选择下一个方向。</p>

<p>给定球的<strong>起始位置，目的地</strong>和<strong>迷宫</strong>，判断球能否在目的地停下。</p>

<p>迷宫由一个0和1的二维数组表示。 1表示墙壁，0表示空地。你可以假定迷宫的边缘都是墙壁。起始位置和目的地的坐标通过行号和列号给出。</p>

<p> </p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入 1:</strong> 迷宫由以下二维数组表示

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

<strong>输入 2:</strong> 起始位置坐标 (rowStart, colStart) = (0, 4)
<strong>输入 3:</strong> 目的地坐标 (rowDest, colDest) = (4, 4)

<strong>输出:</strong> true

<strong>解析:</strong> 一个可能的路径是 : 左 -> 下 -> 左 -> 下 -> 右 -> 下 -> 右。
</pre>

![](./images/maze_1_example_1.png)

<p><strong>示例 2:</strong></p>

<pre><strong>输入 1:</strong> 迷宫由以下二维数组表示

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

<strong>输入 2:</strong> 起始位置坐标 (rowStart, colStart) = (0, 4)
<strong>输入 3:</strong> 目的地坐标 (rowDest, colDest) = (3, 2)

<strong>输出:</strong> false

<strong>解析:</strong> 没有能够使球停在目的地的路径。
</pre>

![](./images/maze_1_example_2.png)

<p> </p>

<p><strong>注意:</strong></p>

<ol>
	<li>迷宫中只有一个球和一个目的地。</li>
	<li>球和目的地都在空地上，且初始时它们不在同一位置。</li>
	<li>给定的迷宫不包括边界 (如图中的红色矩形), 但你可以假设迷宫的边缘都是墙壁。</li>
	<li>迷宫至少包括2块空地，行数和列数均不超过100。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

深度优先搜索或广度优先搜索实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

深度优先搜索。

```python
class Solution:
    def hasPath(self, maze: List[List[int]], start: List[int], destination: List[int]) -> bool:
        def dfs(maze, start, destination):
            if visited[start[0]][start[1]]:
                return False
            if start[0] == destination[0] and start[1] == destination[1]:
                return True
            visited[start[0]][start[1]] = True
            l, r, u, d = start[1] - 1, start[1] + 1, start[0] - 1, start[0] + 1
            while l >= 0 and maze[start[0]][l] == 0:
                l -= 1
            if dfs(maze, [start[0], l + 1], destination):
                return True
            while r < len(maze[0]) and maze[start[0]][r] == 0:
                r += 1
            if dfs(maze, [start[0], r - 1], destination):
                return True
            while u >= 0 and maze[u][start[1]] == 0:
                u -= 1
            if dfs(maze, [u + 1, start[1]], destination):
                return True
            while d < len(maze) and maze[d][start[1]] == 0:
                d += 1
            if dfs(maze, [d - 1, start[1]], destination):
                return True
            return False

        visited = [[False for _ in maze[0]] for _ in maze]
        return dfs(maze, start, destination)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private boolean[][] visited;

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int m = maze.length, n = maze[0].length;
        visited = new boolean[m][n];
        return dfs(maze, start, destination);
    }

    private boolean dfs(int[][] maze, int[] start, int[] destination) {
        if (visited[start[0]][start[1]]) return false;
        if (start[0] == destination[0] && start[1] == destination[1]) return true;
        visited[start[0]][start[1]] = true;

        int l = start[1] - 1, r = start[1] + 1, u = start[0] - 1, d = start[0] + 1;

        while (l >= 0 && maze[start[0]][l] == 0) --l;
        if (dfs(maze, new int[]{start[0], l + 1}, destination)) return true;

        while (r < maze[0].length && maze[start[0]][r] == 0) ++r;
        if (dfs(maze, new int[]{start[0], r - 1}, destination)) return true;

        while (u >= 0 && maze[u][start[1]] == 0) --u;
        if (dfs(maze, new int[]{u + 1, start[1]}, destination)) return true;

        while (d < maze.length && maze[d][start[1]] == 0) ++d;
        if (dfs(maze, new int[]{d - 1, start[1]}, destination)) return true;

        return false;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
