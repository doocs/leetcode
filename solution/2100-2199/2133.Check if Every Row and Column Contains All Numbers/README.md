---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2133.Check%20if%20Every%20Row%20and%20Column%20Contains%20All%20Numbers/README.md
rating: 1264
source: 第 275 场周赛 Q1
tags:
    - 数组
    - 哈希表
    - 矩阵
---

<!-- problem:start -->

# [2133. 检查是否每一行每一列都包含全部整数](https://leetcode.cn/problems/check-if-every-row-and-column-contains-all-numbers)

[English Version](/solution/2100-2199/2133.Check%20if%20Every%20Row%20and%20Column%20Contains%20All%20Numbers/README_EN.md)

## 题目描述

<!-- description:start -->

<p>对一个大小为 <code>n x n</code> 的矩阵而言，如果其每一行和每一列都包含从 <code>1</code> 到 <code>n</code> 的 <strong>全部</strong> 整数（含 <code>1</code> 和 <code>n</code>），则认为该矩阵是一个 <strong>有效</strong> 矩阵。</p>

<p>给你一个大小为 <code>n x n</code> 的整数矩阵 <code>matrix</code> ，请你判断矩阵是否为一个有效矩阵：如果是，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2133.Check%20if%20Every%20Row%20and%20Column%20Contains%20All%20Numbers/images/example1drawio.png" style="width: 250px; height: 251px;" /></p>

<pre>
<strong>输入：</strong>matrix = [[1,2,3],[3,1,2],[2,3,1]]
<strong>输出：</strong>true
<strong>解释：</strong>在此例中，n = 3 ，每一行和每一列都包含数字 1、2、3 。
因此，返回 true 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2133.Check%20if%20Every%20Row%20and%20Column%20Contains%20All%20Numbers/images/example2drawio.png" style="width: 250px; height: 251px;" /></p>

<pre>
<strong>输入：</strong>matrix = [[1,1,1],[1,2,3],[1,2,3]]
<strong>输出：</strong>false
<strong>解释：</strong>在此例中，n = 3 ，但第一行和第一列不包含数字 2 和 3 。
因此，返回 false 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == matrix.length == matrix[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= matrix[i][j] &lt;= n</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

遍历矩阵的每一行和每一列，使用哈希表记录每个数字是否出现过，如果某一行或某一列中有数字重复出现，则返回 `false`，否则返回 `true`。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 为矩阵的大小。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def checkValid(self, matrix: List[List[int]]) -> bool:
        n = len(matrix)
        return all(len(set(row)) == n for row in chain(matrix, zip(*matrix)))
```

#### Java

```java
class Solution {
    public boolean checkValid(int[][] matrix) {
        int n = matrix.length;
        boolean[] vis = new boolean[n + 1];
        for (var row : matrix) {
            Arrays.fill(vis, false);
            for (int x : row) {
                if (vis[x]) {
                    return false;
                }
                vis[x] = true;
            }
        }
        for (int j = 0; j < n; ++j) {
            Arrays.fill(vis, false);
            for (int i = 0; i < n; ++i) {
                if (vis[matrix[i][j]]) {
                    return false;
                }
                vis[matrix[i][j]] = true;
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool checkValid(vector<vector<int>>& matrix) {
        int n = matrix.size();
        bool vis[n + 1];
        for (const auto& row : matrix) {
            memset(vis, false, sizeof(vis));
            for (int x : row) {
                if (vis[x]) {
                    return false;
                }
                vis[x] = true;
            }
        }
        for (int j = 0; j < n; ++j) {
            memset(vis, false, sizeof(vis));
            for (int i = 0; i < n; ++i) {
                if (vis[matrix[i][j]]) {
                    return false;
                }
                vis[matrix[i][j]] = true;
            }
        }
        return true;
    }
};
```

#### Go

```go
func checkValid(matrix [][]int) bool {
	n := len(matrix)
	for _, row := range matrix {
		vis := make([]bool, n+1)
		for _, x := range row {
			if vis[x] {
				return false
			}
			vis[x] = true
		}
	}
	for j := 0; j < n; j++ {
		vis := make([]bool, n+1)
		for i := 0; i < n; i++ {
			if vis[matrix[i][j]] {
				return false
			}
			vis[matrix[i][j]] = true
		}
	}
	return true
}
```

#### TypeScript

```ts
function checkValid(matrix: number[][]): boolean {
    const n = matrix.length;
    const vis: boolean[] = Array(n + 1).fill(false);
    for (const row of matrix) {
        vis.fill(false);
        for (const x of row) {
            if (vis[x]) {
                return false;
            }
            vis[x] = true;
        }
    }
    for (let j = 0; j < n; ++j) {
        vis.fill(false);
        for (let i = 0; i < n; ++i) {
            if (vis[matrix[i][j]]) {
                return false;
            }
            vis[matrix[i][j]] = true;
        }
    }
    return true;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
