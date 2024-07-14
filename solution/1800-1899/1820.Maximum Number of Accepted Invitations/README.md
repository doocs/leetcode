---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1820.Maximum%20Number%20of%20Accepted%20Invitations/README.md
tags:
    - 数组
    - 回溯
    - 矩阵
---

<!-- problem:start -->

# [1820. 最多邀请的个数 🔒](https://leetcode.cn/problems/maximum-number-of-accepted-invitations)

[English Version](/solution/1800-1899/1820.Maximum%20Number%20of%20Accepted%20Invitations/README_EN.md)

## 题目描述

<!-- description:start -->

<p>某一个班级有 <code>m</code> 个男孩和 <code>n</code> 个女孩，即将举行一个派对。</p>

<p>给定一个 <code>m x n</code> 的整数矩阵 <code>grid</code> ，其中 <code>grid[i][j]</code> 等于 <code>0</code> 或 <code>1</code> 。 若 <code>grid[i][j] == 1</code> ，则表示第 <code>i</code> 个男孩可以邀请第 <code>j</code> 个女孩参加派对。 一个男孩最多可以邀请<strong>一个女孩</strong>，一个女孩最多可以接受一个男孩的<strong>一个邀请</strong>。</p>

<p>返回可能的最多邀请的个数。</p>

<p> </p>

<p><b>示例 1:</b></p>

<pre><strong>输入:</strong> grid = [[1,1,1],
               [1,0,1],
               [0,0,1]]
<strong>输出:</strong> 3<strong>
解释:</strong> 按下列方式邀请：
- 第 1 个男孩邀请第 2 个女孩。
- 第 2 个男孩邀请第 1 个女孩。
- 第 3 个男孩邀请第 3 个女孩。</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> grid = [[1,0,1,0],
               [1,0,0,0],
               [0,0,1,0],
               [1,1,1,0]]
<strong>输出:</strong> 3
<strong>解释: </strong>按下列方式邀请：
- 第 1 个男孩邀请第 3 个女孩。
- 第 2 个男孩邀请第 1 个女孩。
- 第 3 个男孩未邀请任何人。
- 第 4 个男孩邀请第 2 个女孩。</pre>

<p> </p>

<p><b>提示：</b></p>

<ul>
	<li><code>grid.length == m</code></li>
	<li><code>grid[i].length == n</code></li>
	<li><code>1 &lt;= m, n &lt;= 200</code></li>
	<li><code>grid[i][j]</code> 是 <code>0</code> 或 <code>1</code> 之一。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：匈牙利算法

本题属于二分图最大匹配问题，适合用匈牙利算法来求解。

匈牙利算法的核心思想是，不断地从未匹配的点出发，寻找增广路径，直到没有增广路径为止，就得到了最大匹配。

时间复杂度 $O(m \times n)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumInvitations(self, grid: List[List[int]]) -> int:
        def find(i):
            for j, v in enumerate(grid[i]):
                if v and j not in vis:
                    vis.add(j)
                    if match[j] == -1 or find(match[j]):
                        match[j] = i
                        return True
            return False

        m, n = len(grid), len(grid[0])
        match = [-1] * n
        ans = 0
        for i in range(m):
            vis = set()
            ans += find(i)
        return ans
```

#### Java

```java
class Solution {
    private int[][] grid;
    private boolean[] vis;
    private int[] match;
    private int n;

    public int maximumInvitations(int[][] grid) {
        int m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        vis = new boolean[n];
        match = new int[n];
        Arrays.fill(match, -1);
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            Arrays.fill(vis, false);
            if (find(i)) {
                ++ans;
            }
        }
        return ans;
    }

    private boolean find(int i) {
        for (int j = 0; j < n; ++j) {
            if (grid[i][j] == 1 && !vis[j]) {
                vis[j] = true;
                if (match[j] == -1 || find(match[j])) {
                    match[j] = i;
                    return true;
                }
            }
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximumInvitations(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        bool vis[210];
        int match[210];
        memset(match, -1, sizeof match);
        int ans = 0;
        function<bool(int)> find = [&](int i) -> bool {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] && !vis[j]) {
                    vis[j] = true;
                    if (match[j] == -1 || find(match[j])) {
                        match[j] = i;
                        return true;
                    }
                }
            }
            return false;
        };
        for (int i = 0; i < m; ++i) {
            memset(vis, 0, sizeof vis);
            ans += find(i);
        }
        return ans;
    }
};
```

#### Go

```go
func maximumInvitations(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	var vis map[int]bool
	match := make([]int, n)
	for i := range match {
		match[i] = -1
	}
	var find func(i int) bool
	find = func(i int) bool {
		for j, v := range grid[i] {
			if v == 1 && !vis[j] {
				vis[j] = true
				if match[j] == -1 || find(match[j]) {
					match[j] = i
					return true
				}
			}
		}
		return false
	}
	ans := 0
	for i := 0; i < m; i++ {
		vis = map[int]bool{}
		if find(i) {
			ans++
		}
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
