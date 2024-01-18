# [LCS 03. 主题空间](https://leetcode.cn/problems/YesdPw/)

## 题目描述

<!-- 这里写题目描述 -->

「以扣会友」线下活动所在场地由若干主题空间与走廊组成，场地的地图记作由一维字符串型数组 `grid`，字符串中仅包含 `"0"～"5"` 这 6 个字符。地图上每一个字符代表面积为 1 的区域，其中 `"0"` 表示走廊，其他字符表示主题空间。相同且连续（连续指上、下、左、右四个方向连接）的字符组成同一个主题空间。

假如整个 `grid` 区域的外侧均为走廊。请问，不与走廊直接相邻的主题空间的最大面积是多少？如果不存在这样的空间请返回 `0`。

**示例 1:**

> 输入：`grid = ["110","231","221"]`
>
> 输出：`1`
>
> 解释：4 个主题空间中，只有 1 个不与走廊相邻，面积为 1。
> ![image.png](https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcs/LCS%2003.%20主题空间/images/1613708145-rscctN-image.png)

**示例 2:**

> 输入：`grid = ["11111100000","21243101111","21224101221","11111101111"]`
>
> 输出：`3`
>
> 解释：8 个主题空间中，有 5 个不与走廊相邻，面积分别为 3、1、1、1、2，最大面积为 3。
> ![image.png](https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcs/LCS%2003.%20主题空间/images/1613707985-KJyiXJ-image.png)

**提示：**

-   `1 <= grid.length <= 500`
-   `1 <= grid[i].length <= 500`
-   `grid[i][j]` 仅可能是 `"0"～"5"`

## 解法

### 方法一

<!-- tabs:start -->

```python
class Solution:
    def largestArea(self, grid: List[str]) -> int:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        m, n = len(grid), len(grid[0])
        p = list(range(m * n + 1))
        size = [1] * (m * n + 1)
        dirs = [[0, -1], [0, 1], [1, 0], [-1, 0]]
        for i in range(m):
            for j in range(n):
                if i == 0 or i == m - 1 or j == 0 or j == n - 1 or grid[i][j] == '0':
                    p[find(i * n + j)] = find(m * n)
                else:
                    for a, b in dirs:
                        x, y = i + a, j + b
                        if (grid[x][y] == '0' or grid[i][j] == grid[x][y]) and find(
                            x * n + y
                        ) != find(i * n + j):
                            size[find(x * n + y)] += size[find(i * n + j)]
                            p[find(i * n + j)] = find(x * n + y)
        return max(
            [
                size[i * n + j]
                for i in range(m)
                for j in range(n)
                if find(i * n + j) != find(m * n)
            ],
            default=0,
        )
```

```java
class Solution {
    private int[] p;

    public int largestArea(String[] grid) {
        int m = grid.length;
        int n = grid[0].length();
        p = new int[m * n + 1];
        int[] size = new int[m * n + 1];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
            size[i] = 1;
        }
        int[] dirs = {0, 1, 0, -1, 0};
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1 || grid[i].charAt(j) == '0') {
                    p[find(i * n + j)] = find(m * n);
                } else {
                    for (int k = 0; k < 4; ++k) {
                        int x = i + dirs[k];
                        int y = j + dirs[k + 1];
                        if (grid[x].charAt(y) == '0' || grid[i].charAt(j) == grid[x].charAt(y)) {
                            if (find(x * n + y) != find(i * n + j)) {
                                size[find(x * n + y)] += size[find(i * n + j)];
                                p[find(i * n + j)] = find(x * n + y);
                            }
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (find(i * n + j) != find(m * n)) {
                    ans = Math.max(ans, size[i * n + j]);
                }
            }
        }
        return ans;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

```cpp
class Solution {
public:
    vector<int> p;

    int largestArea(vector<string>& grid) {
        int m = grid.size(), n = grid[0].size();
        p.resize(m * n + 1);
        for (int i = 0; i < p.size(); ++i) p[i] = i;
        vector<int> size(m * n + 1, 1);
        vector<int> dirs = {-1, 0, 1, 0, -1};
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1 || grid[i][j] == '0')
                    p[find(i * n + j)] = find(m * n);
                else {
                    for (int k = 0; k < 4; ++k) {
                        int x = i + dirs[k], y = j + dirs[k + 1];
                        if ((grid[x][y] == '0' || grid[i][j] == grid[x][y]) && find(x * n + y) != find(i * n + j)) {
                            size[find(x * n + y)] += size[find(i * n + j)];
                            p[find(i * n + j)] = find(x * n + y);
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                if (find(i * n + j) != find(m * n))
                    ans = max(ans, size[i * n + j]);
        return ans;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};
```

```go
func largestArea(grid []string) int {
	m, n := len(grid), len(grid[0])
	p := make([]int, m*n+1)
	size := make([]int, m*n+1)
	for i := range p {
		p[i] = i
		size[i] = 1
	}
	dirs := []int{-1, 0, 1, 0, -1}
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if i == 0 || i == m-1 || j == 0 || j == n-1 || grid[i][j] == '0' {
				p[find(i*n+j)] = find(m * n)
			} else {
				for k := 0; k < 4; k++ {
					x, y := i+dirs[k], j+dirs[k+1]
					if (grid[x][y] == '0' || grid[i][j] == grid[x][y]) && find(x*n+y) != find(i*n+j) {
						size[find(x*n+y)] += size[find(i*n+j)]
						p[find(i*n+j)] = find(x*n + y)
					}
				}
			}
		}
	}
	ans := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if find(i*n+j) != find(m*n) && ans < size[i*n+j] {
				ans = size[i*n+j]
			}
		}
	}
	return ans
}
```

```js
/**
 * @param {string[]} grid
 * @return {number}
 */
var largestArea = function (grid) {
    const m = grid.length;
    const n = grid[0].length;
    let p = new Array(m * n + 1).fill(0);
    let size = new Array(m * n + 1).fill(1);
    for (let i = 0; i < p.length; ++i) {
        p[i] = i;
    }
    const dirs = [-1, 0, 1, 0, -1];
    function find(x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (i == 0 || i == m - 1 || j == 0 || j == n - 1 || grid[i][j] == '0') {
                p[find(i * n + j)] = find(m * n);
            } else {
                for (let k = 0; k < 4; ++k) {
                    const x = i + dirs[k];
                    const y = j + dirs[k + 1];
                    if (
                        (grid[x][y] == '0' || grid[i][j] == grid[x][y]) &&
                        find(x * n + y) != find(i * n + j)
                    ) {
                        size[find(x * n + y)] += size[find(i * n + j)];
                        p[find(i * n + j)] = find(x * n + y);
                    }
                }
            }
        }
    }
    let ans = 0;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (find(i * n + j) != find(m * n) && ans < size[i * n + j]) {
                ans = size[i * n + j];
            }
        }
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- end -->
