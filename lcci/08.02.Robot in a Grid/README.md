# [面试题 08.02. 迷路的机器人](https://leetcode.cn/problems/robot-in-a-grid-lcci)

[English Version](/lcci/08.02.Robot%20in%20a%20Grid/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>设想有个机器人坐在一个网格的左上角，网格 r 行 c 列。机器人只能向下或向右移动，但不能走到一些被禁止的网格（有障碍物）。设计一种算法，寻找机器人从左上角移动到右下角的路径。</p>

![](./images/robot_maze.png)

<p>网格中的障碍物和空位置分别用 <code>1</code> 和 <code>0</code> 来表示。</p>

<p>返回一条可行的路径，路径由经过的网格的行号和列号组成。左上角为 0 行 0 列。</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre><strong>输入:
</strong>[
&nbsp; [<strong>0</strong>,<strong>0</strong>,<strong>0</strong>],
&nbsp; [0,1,<strong>0</strong>],
&nbsp; [0,0,<strong>0</strong>]
]
<strong>输出:</strong> [[0,0],[0,1],[0,2],[1,2],[2,2]]
<strong>解释: 
</strong>输入中标粗的位置即为输出表示的路径，即
0行0列（左上角） -&gt; 0行1列 -&gt; 0行2列 -&gt; 1行2列 -&gt; 2行2列（右下角）</pre>

<p><strong>说明：</strong><em>r</em>&nbsp;和 <em>c </em>的值均不超过 100。</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

DFS 思路

**剪枝：**

防止多次进入同一个位置，走过的位置要将其置为 1

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
function pathWithObstacles(obstacleGrid: number[][]): number[][] {
    const m = obstacleGrid.length;
    const n = obstacleGrid[0].length;
    const res = [];
    const dfs = (i: number, j: number): boolean => {
        if (i === m || j === n || obstacleGrid[i][j] === 1) {
            return false;
        }
        res.push([i, j]);
        obstacleGrid[i][j] = 1;
        if ((i + 1 === m && j + 1 === n) || dfs(i + 1, j) || dfs(i, j + 1)) {
            return true;
        }
        res.pop();
        return false;
    };
    if (dfs(0, 0)) {
        return res;
    }
    return [];
}
```

### **Rust**

```rust
impl Solution {
    fn dfs(grid: &mut Vec<Vec<i32>>, path: &mut Vec<Vec<i32>>, i: usize, j: usize) -> bool {
        if i == grid.len() || j == grid[0].len() || grid[i][j] == 1 {
            return false;
        }
        path.push(vec![i as i32, j as i32]);
        grid[i as usize][j as usize] = 1;
        if (i + 1 == grid.len() && j + 1 == grid[0].len())
            || Self::dfs(grid, path, i + 1, j)
            || Self::dfs(grid, path, i, j + 1)
        {
            return true;
        }
        path.pop();
        false
    }



    pub fn path_with_obstacles(mut obstacle_grid: Vec<Vec<i32>>) -> Vec<Vec<i32>> {
        let mut res = vec![];
        if Self::dfs(&mut obstacle_grid, &mut res, 0, 0) {
            return res;
        }
        vec![]
    }
}
```

### **...**

```


```

<!-- tabs:end -->
