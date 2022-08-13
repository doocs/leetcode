# [1706. 球会落何处](https://leetcode.cn/problems/where-will-the-ball-fall)

[English Version](/solution/1700-1799/1706.Where%20Will%20the%20Ball%20Fall/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>用一个大小为 <code>m x n</code> 的二维网格 <code>grid</code> 表示一个箱子。你有 <code>n</code> 颗球。箱子的顶部和底部都是开着的。</p>

<p>箱子中的每个单元格都有一个对角线挡板，跨过单元格的两个角，可以将球导向左侧或者右侧。</p>

<ul>
	<li>将球导向右侧的挡板跨过左上角和右下角，在网格中用 <code>1</code> 表示。</li>
	<li>将球导向左侧的挡板跨过右上角和左下角，在网格中用 <code>-1</code> 表示。</li>
</ul>

<p>在箱子每一列的顶端各放一颗球。每颗球都可能卡在箱子里或从底部掉出来。如果球恰好卡在两块挡板之间的 "V" 形图案，或者被一块挡导向到箱子的任意一侧边上，就会卡住。</p>

<p>返回一个大小为 <code>n</code> 的数组 <code>answer</code> ，其中 <code>answer[i]</code> 是球放在顶部的第 <code>i</code> 列后从底部掉出来的那一列对应的下标，如果球卡在盒子里，则返回 <code>-1</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1706.Where%20Will%20the%20Ball%20Fall/images/ball.jpg" style="width: 500px; height: 385px;" /></strong></p>

<pre>
<strong>输入：</strong>grid = [[1,1,1,-1,-1],[1,1,1,-1,-1],[-1,-1,-1,1,1],[1,1,1,1,-1],[-1,-1,-1,-1,-1]]
<strong>输出：</strong>[1,-1,-1,-1,-1]
<strong>解释：</strong>示例如图：
b0 球开始放在第 0 列上，最终从箱子底部第 1 列掉出。
b1 球开始放在第 1 列上，会卡在第 2、3 列和第 1 行之间的 "V" 形里。
b2 球开始放在第 2 列上，会卡在第 2、3 列和第 0 行之间的 "V" 形里。
b3 球开始放在第 3 列上，会卡在第 2、3 列和第 0 行之间的 "V" 形里。
b4 球开始放在第 4 列上，会卡在第 2、3 列和第 1 行之间的 "V" 形里。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>grid = [[-1]]
<strong>输出：</strong>[-1]
<strong>解释：</strong>球被卡在箱子左侧边上。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>grid = [[1,1,1,1,1,1],[-1,-1,-1,-1,-1,-1],[1,1,1,1,1,1],[-1,-1,-1,-1,-1,-1]]
<strong>输出：</strong>[0,1,2,3,4,-1]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 <= m, n <= 100</code></li>
	<li><code>grid[i][j]</code> 为 <code>1</code> 或 <code>-1</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

球被卡住共有 4 种情况：

1. 球位于最左一列，并且球所在的单元格单元格挡板将球导向左侧
1. 球位于最右一列，并且此单元格挡板将球导向右侧
1. 球所在的单元格挡板将球导向右侧，并且球右侧相邻单元格挡板将球导向左侧
1. 球所在的单元格挡板将球导向左侧，并且球左侧相邻单元格挡板将球导向右侧

DFS 搜索即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findBall(self, grid: List[List[int]]) -> List[int]:
        m, n = len(grid), len(grid[0])

        def dfs(i, j):
            nonlocal m, n
            if i == m:
                return j
            if j == 0 and grid[i][j] == -1:
                return -1
            if j == n - 1 and grid[i][j] == 1:
                return -1
            if grid[i][j] == 1 and grid[i][j + 1] == -1:
                return -1
            if grid[i][j] == -1 and grid[i][j - 1] == 1:
                return -1
            return dfs(i + 1, j + 1) if grid[i][j] == 1 else dfs(i + 1, j - 1)

        return [dfs(0, j) for j in range(n)]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int m;
    private int n;
    private int[][] grid;

    public int[] findBall(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        int[] ans = new int[n];
        for (int j = 0; j < n; ++j) {
            ans[j] = dfs(0, j);
        }
        return ans;
    }

    private int dfs(int i, int j) {
        if (i == m) {
            return j;
        }
        if (j == 0 && grid[i][j] == -1) {
            return -1;
        }
        if (j == n - 1 && grid[i][j] == 1) {
            return -1;
        }
        if (grid[i][j] == 1 && grid[i][j + 1] == -1) {
            return -1;
        }
        if (grid[i][j] == -1 && grid[i][j - 1] == 1) {
            return -1;
        }
        return grid[i][j] == 1 ? dfs(i + 1, j + 1) : dfs(i + 1, j - 1);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int m, n;
    vector<vector<int>> grid;

    vector<int> findBall(vector<vector<int>>& grid) {
        this->grid = grid;
        m = grid.size();
        n = grid[0].size();
        vector<int> ans(n);
        for (int j = 0; j < n; ++j) ans[j] = dfs(0, j);
        return ans;
    }

    int dfs(int i, int j) {
        if (i == m) return j;
        if (j == 0 && grid[i][j] == -1) return -1;
        if (j == n - 1 && grid[i][j] == 1) return -1;
        if (grid[i][j] == 1 && grid[i][j + 1] == -1) return -1;
        if (grid[i][j] == -1 && grid[i][j - 1] == 1) return -1;
        return grid[i][j] == 1 ? dfs(i + 1, j + 1) : dfs(i + 1, j - 1);
    }
};
```

### **Go**

```go
func findBall(grid [][]int) []int {
	m, n := len(grid), len(grid[0])

	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i == m {
			return j
		}
		if j == 0 && grid[i][j] == -1 {
			return -1
		}
		if j == n-1 && grid[i][j] == 1 {
			return -1
		}
		if grid[i][j] == 1 && grid[i][j+1] == -1 {
			return -1
		}
		if grid[i][j] == -1 && grid[i][j-1] == 1 {
			return -1
		}
		if grid[i][j] == 1 {
			return dfs(i+1, j+1)
		}
		return dfs(i+1, j-1)
	}

	var ans []int
	for j := 0; j < n; j++ {
		ans = append(ans, dfs(0, j))
	}
	return ans
}
```

### **TypeScript**

```ts
function findBall(grid: number[][]): number[] {
    const m = grid.length;
    const n = grid[0].length;
    const res = new Array(n).fill(0);
    const dfs = (i: number, j: number) => {
        if (i === m) {
            return j;
        }
        if (grid[i][j] === 1) {
            if (j === n - 1 || grid[i][j + 1] === -1) {
                return -1;
            }
            return dfs(i + 1, j + 1);
        } else {
            if (j === 0 || grid[i][j - 1] === 1) {
                return -1;
            }
            return dfs(i + 1, j - 1);
        }
    };
    for (let i = 0; i < n; i++) {
        res[i] = dfs(0, i);
    }
    return res;
}
```

### **Rust**

```rust
impl Solution {
    fn dfs(grid: &Vec<Vec<i32>>, i: usize, j: usize) -> i32 {
        if i == grid.len() {
            return j as i32;
        }
        if grid[i][j] == 1 {
            if j == grid[0].len() - 1 || grid[i][j + 1] == -1 {
                return -1;
            }
            Self::dfs(grid, i + 1, j + 1)
        } else {
            if j == 0 || grid[i][j - 1] == 1 {
                return -1;
            }
            Self::dfs(grid, i + 1, j - 1)
        }
    }

    pub fn find_ball(grid: Vec<Vec<i32>>) -> Vec<i32> {
        let m = grid.len();
        let n = grid[0].len();
        let mut res = vec![0; n];
        for i in 0..n {
            res[i] = Self::dfs(&grid, 0, i);
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
