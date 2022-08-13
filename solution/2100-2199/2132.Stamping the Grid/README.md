# [2132. 用邮票贴满网格图](https://leetcode.cn/problems/stamping-the-grid)

[English Version](/solution/2100-2199/2132.Stamping%20the%20Grid/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个&nbsp;<code>m x n</code>&nbsp;的二进制矩阵&nbsp;<code>grid</code>&nbsp;，每个格子要么为&nbsp;<code>0</code>&nbsp;（空）要么为&nbsp;<code>1</code>&nbsp;（被占据）。</p>

<p>给你邮票的尺寸为&nbsp;<code>stampHeight x stampWidth</code>&nbsp;。我们想将邮票贴进二进制矩阵中，且满足以下&nbsp;<strong>限制</strong>&nbsp;和&nbsp;<strong>要求</strong>&nbsp;：</p>

<ol>
	<li>覆盖所有 <strong>空</strong>&nbsp;格子。</li>
	<li>不覆盖任何 <strong>被占据&nbsp;</strong>的格子。</li>
	<li>我们可以放入任意数目的邮票。</li>
	<li>邮票可以相互有 <strong>重叠</strong>&nbsp;部分。</li>
	<li>邮票不允许 <strong>旋转</strong>&nbsp;。</li>
	<li>邮票必须完全在矩阵 <strong>内</strong>&nbsp;。</li>
</ol>

<p>如果在满足上述要求的前提下，可以放入邮票，请返回&nbsp;<code>true</code>&nbsp;，否则返回<i>&nbsp;</i><code>false</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2132.Stamping%20the%20Grid/images/ex1.png" style="width: 180px; height: 237px;"></p>

<pre><b>输入：</b>grid = [[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0]], stampHeight = 4, stampWidth = 3
<b>输出：</b>true
<b>解释：</b>我们放入两个有重叠部分的邮票（图中标号为 1 和 2），它们能覆盖所有与空格子。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2132.Stamping%20the%20Grid/images/ex2.png" style="width: 170px; height: 179px;"></p>

<pre><b>输入：</b>grid = [[1,0,0,0],[0,1,0,0],[0,0,1,0],[0,0,0,1]], stampHeight = 2, stampWidth = 2 
<b>输出：</b>false 
<b>解释：</b>没办法放入邮票覆盖所有的空格子，且邮票不超出网格图以外。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[r].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= m * n &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>grid[r][c]</code> 要么是&nbsp;<code>0</code>&nbsp;，要么是&nbsp;<code>1</code> 。</li>
	<li><code>1 &lt;= stampHeight, stampWidth &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：二维前缀和 + 二维差分**

`s[i + 1][j + 1]` 表示第 i 行第 j 列左上部分所有元素之和，其中 i, j 下标从 0 开始。

则 `s[i + 1][j + 1] = s[i + 1][j] + s[i][j + 1] - s[i][j] + nums[i][j]`。

以 (x1, y1) 为左上角，(x2, y2) 为右下角的子矩阵和 `sub = s[x2 + 1][y2 + 1] - s[x2 + 1][y1] - s[x1][y2 + 1] + s[x1][y1]`。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def possibleToStamp(
        self, grid: List[List[int]], stampHeight: int, stampWidth: int
    ) -> bool:
        m, n = len(grid), len(grid[0])
        s = [[0] * (n + 1) for _ in range(m + 1)]
        for i, row in enumerate(grid):
            for j, v in enumerate(row):
                s[i + 1][j + 1] = s[i + 1][j] + s[i][j + 1] - s[i][j] + v

        d = [[0] * (n + 1) for _ in range(m + 1)]
        for i, row in enumerate(grid):
            for j, v in enumerate(row):
                if v == 0:
                    x, y = i + stampHeight, j + stampWidth
                    if x <= m and y <= n and s[x][y] - s[x][j] - s[i][y] + s[i][j] == 0:
                        d[i][j] += 1
                        d[i][y] -= 1
                        d[x][j] -= 1
                        d[x][y] += 1

        cnt = [[0] * (n + 1) for _ in range(m + 1)]
        for i, row in enumerate(grid):
            for j, v in enumerate(row):
                cnt[i + 1][j + 1] = cnt[i + 1][j] + cnt[i][j + 1] - cnt[i][j] + d[i][j]
                if v == 0 and cnt[i + 1][j + 1] == 0:
                    return False
        return True
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean possibleToStamp(int[][] grid, int stampHeight, int stampWidth) {
        int m = grid.length, n = grid[0].length;
        int[][] s = new int[m + 1][n + 1];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                s[i + 1][j + 1] = s[i + 1][j] + s[i][j + 1] - s[i][j] + grid[i][j];
            }
        }
        int[][] d = new int[m + 1][n + 1];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    int x = i + stampHeight, y = j + stampWidth;
                    if (x <= m && y <= n && s[x][y] - s[x][j] - s[i][y] + s[i][j] == 0) {
                        d[i][j]++;
                        d[i][y]--;
                        d[x][j]--;
                        d[x][y]++;
                    }
                }
            }
        }
        int[][] cnt = new int[m + 1][n + 1];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                cnt[i + 1][j + 1] = cnt[i + 1][j] + cnt[i][j + 1] - cnt[i][j] + d[i][j];
                if (grid[i][j] == 0 && cnt[i + 1][j + 1] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool possibleToStamp(vector<vector<int>>& grid, int stampHeight, int stampWidth) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<int>> s(m + 1, vector<int>(n + 1));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                s[i + 1][j + 1] = s[i + 1][j] + s[i][j + 1] - s[i][j] + grid[i][j];
            }
        }
        vector<vector<int>> d(m + 1, vector<int>(n + 1));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j]) continue;
                int x = i + stampHeight, y = j + stampWidth;
                if (x <= m && y <= n && s[x][y] - s[i][y] - s[x][j] + s[i][j] == 0) {
                    d[i][j]++;
                    d[x][j]--;
                    d[i][y]--;
                    d[x][y]++;
                }
            }
        }
        vector<vector<int>> cnt(m + 1, vector<int>(n + 1));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                cnt[i + 1][j + 1] = cnt[i + 1][j] + cnt[i][j + 1] - cnt[i][j] + d[i][j];
                if (grid[i][j] == 0 && cnt[i + 1][j + 1] == 0) return false;
            }
        }
        return true;
    }
};
```

### **Go**

```go
func possibleToStamp(grid [][]int, stampHeight int, stampWidth int) bool {
	m, n := len(grid), len(grid[0])
	s := make([][]int, m+1)
	d := make([][]int, m+1)
	cnt := make([][]int, m+1)
	for i := range s {
		s[i] = make([]int, n+1)
		d[i] = make([]int, n+1)
		cnt[i] = make([]int, n+1)
	}
	for i, row := range grid {
		for j, v := range row {
			s[i+1][j+1] = s[i+1][j] + s[i][j+1] - s[i][j] + v
		}
	}
	for i, row := range grid {
		for j, v := range row {
			if v == 0 {
				x, y := i+stampHeight, j+stampWidth
				if x <= m && y <= n && s[x][y]-s[i][y]-s[x][j]+s[i][j] == 0 {
					d[i][j]++
					d[i][y]--
					d[x][j]--
					d[x][y]++
				}
			}
		}
	}
	for i, row := range grid {
		for j, v := range row {
			cnt[i+1][j+1] = cnt[i+1][j] + cnt[i][j+1] - cnt[i][j] + d[i][j]
			if v == 0 && cnt[i+1][j+1] == 0 {
				return false
			}
		}
	}
	return true
}
```

### **JavaScript**

```js
/**
 * @param {number[][]} grid
 * @param {number} stampHeight
 * @param {number} stampWidth
 * @return {boolean}
 */
var possibleToStamp = function (grid, stampHeight, stampWidth) {
    const m = grid.length;
    const n = grid[0].length;
    let s = new Array(m + 1).fill(0).map(() => new Array(n + 1).fill(0));
    let d = new Array(m + 1).fill(0).map(() => new Array(n + 1).fill(0));
    let cnt = new Array(m + 1).fill(0).map(() => new Array(n + 1).fill(0));
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            s[i + 1][j + 1] = s[i + 1][j] + s[i][j + 1] - s[i][j] + grid[i][j];
        }
    }
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (grid[i][j] == 0) {
                let [x, y] = [i + stampHeight, j + stampWidth];
                if (
                    x <= m &&
                    y <= n &&
                    s[x][y] - s[i][y] - s[x][j] + s[i][j] == 0
                ) {
                    d[i][j]++;
                    d[i][y]--;
                    d[x][j]--;
                    d[x][y]++;
                }
            }
        }
    }
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            cnt[i + 1][j + 1] =
                cnt[i + 1][j] + cnt[i][j + 1] - cnt[i][j] + d[i][j];
            if (grid[i][j] == 0 && cnt[i + 1][j + 1] == 0) {
                return false;
            }
        }
    }
    return true;
};
```

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts

```

### **...**

```

```

<!-- tabs:end -->
