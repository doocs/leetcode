---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1947.Maximum%20Compatibility%20Score%20Sum/README.md
rating: 1704
source: 第 251 场周赛 Q3
tags:
    - 位运算
    - 数组
    - 动态规划
    - 回溯
    - 状态压缩
---

<!-- problem:start -->

# [1947. 最大兼容性评分和](https://leetcode.cn/problems/maximum-compatibility-score-sum)

[English Version](/solution/1900-1999/1947.Maximum%20Compatibility%20Score%20Sum/README_EN.md)

## 题目描述

<!-- description:start -->

<p>有一份由 <code>n</code> 个问题组成的调查问卷，每个问题的答案要么是 <code>0</code>（no，否），要么是 <code>1</code>（yes，是）。</p>

<p>这份调查问卷被分发给 <code>m</code> 名学生和 <code>m</code> 名导师，学生和导师的编号都是从 <code>0</code> 到 <code>m - 1</code> 。学生的答案用一个二维整数数组 <code>students</code> 表示，其中 <code>students[i]</code> 是一个整数数组，包含第 <code>i</code> 名学生对调查问卷给出的答案（<strong>下标从 0 开始</strong>）。导师的答案用一个二维整数数组 <code>mentors</code> 表示，其中 <code>mentors[j]</code> 是一个整数数组，包含第 <code>j</code> 名导师对调查问卷给出的答案（<strong>下标从 0 开始</strong>）。</p>

<p>每个学生都会被分配给 <strong>一名</strong> 导师，而每位导师也会分配到 <strong>一名</strong> 学生。配对的学生与导师之间的兼容性评分等于学生和导师答案相同的次数。</p>

<ul>
	<li>例如，学生答案为<code>[1, <strong><em>0</em></strong>, <strong><em>1</em></strong>]</code> 而导师答案为 <code>[0, <strong><em>0</em></strong>, <strong><em>1</em></strong>]</code> ，那么他们的兼容性评分为 2 ，因为只有第二个和第三个答案相同。</li>
</ul>

<p>请你找出最优的学生与导师的配对方案，以 <strong>最大程度上</strong> 提高 <strong>兼容性评分和</strong> 。</p>

<p>给你 <code>students</code> 和 <code>mentors</code> ，返回可以得到的<em> </em><strong>最大兼容性评分和</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>students = [[1,1,0],[1,0,1],[0,0,1]], mentors = [[1,0,0],[0,0,1],[1,1,0]]
<strong>输出：</strong>8
<strong>解释：</strong>按下述方式分配学生和导师：
- 学生 0 分配给导师 2 ，兼容性评分为 3 。
- 学生 1 分配给导师 0 ，兼容性评分为 2 。
- 学生 2 分配给导师 1 ，兼容性评分为 3 。
最大兼容性评分和为 3 + 2 + 3 = 8 。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>students = [[0,0],[0,0],[0,0]], mentors = [[1,1],[1,1],[1,1]]
<strong>输出：</strong>0
<strong>解释：</strong>任意学生与导师配对的兼容性评分都是 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == students.length == mentors.length</code></li>
	<li><code>n == students[i].length == mentors[j].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 8</code></li>
	<li><code>students[i][k]</code> 为 <code>0</code> 或 <code>1</code></li>
	<li><code>mentors[j][k]</code> 为 <code>0</code> 或 <code>1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：预处理 + 回溯

我们可以先预处理出每个学生 $i$ 和导师 $j$ 之间的兼容性评分 $g[i][j]$，然后使用回溯算法求解。

定义一个函数 $\textit{dfs}(i, s)$，其中 $i$ 表示当前处理到第 $i$ 个学生，$s$ 表示当前的兼容性评分和。

在 $\textit{dfs}(i, s)$ 中，如果 $i \geq m$，表示所有学生都已经分配完毕，此时更新答案为 $\max(\textit{ans}, s)$。否则，我们枚举第 $i$ 个学生可以分配给哪个导师，然后递归处理下一个学生。过程中，我们用一个数组 $\textit{vis}$ 记录哪些导师已经被分配过，以避免重复分配。

我们调用 $\textit{dfs}(0, 0)$ 即可得到最大的兼容性评分和。

时间复杂度 $O(m!)$，空间复杂度 $O(m^2)$。其中 $m$ 为学生和导师的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxCompatibilitySum(
        self, students: List[List[int]], mentors: List[List[int]]
    ) -> int:
        def dfs(i: int, s: int):
            if i >= m:
                nonlocal ans
                ans = max(ans, s)
                return
            for j in range(m):
                if not vis[j]:
                    vis[j] = True
                    dfs(i + 1, s + g[i][j])
                    vis[j] = False

        ans = 0
        m = len(students)
        vis = [False] * m
        g = [[0] * m for _ in range(m)]
        for i, x in enumerate(students):
            for j, y in enumerate(mentors):
                g[i][j] = sum(a == b for a, b in zip(x, y))
        dfs(0, 0)
        return ans
```

#### Java

```java
class Solution {
    private int m;
    private int ans;
    private int[][] g;
    private boolean[] vis;

    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        m = students.length;
        g = new int[m][m];
        vis = new boolean[m];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < m; ++j) {
                for (int k = 0; k < students[i].length; ++k) {
                    if (students[i][k] == mentors[j][k]) {
                        ++g[i][j];
                    }
                }
            }
        }
        dfs(0, 0);
        return ans;
    }

    private void dfs(int i, int s) {
        if (i >= m) {
            ans = Math.max(ans, s);
            return;
        }
        for (int j = 0; j < m; ++j) {
            if (!vis[j]) {
                vis[j] = true;
                dfs(i + 1, s + g[i][j]);
                vis[j] = false;
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxCompatibilitySum(vector<vector<int>>& students, vector<vector<int>>& mentors) {
        int m = students.size();
        int n = students[0].size();
        vector<vector<int>> g(m, vector<int>(m));
        vector<bool> vis(m);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < m; ++j) {
                for (int k = 0; k < n; ++k) {
                    g[i][j] += students[i][k] == mentors[j][k];
                }
            }
        }
        int ans = 0;
        auto dfs = [&](this auto&& dfs, int i, int s) {
            if (i >= m) {
                ans = max(ans, s);
                return;
            }
            for (int j = 0; j < m; ++j) {
                if (!vis[j]) {
                    vis[j] = true;
                    dfs(i + 1, s + g[i][j]);
                    vis[j] = false;
                }
            }
        };
        dfs(0, 0);
        return ans;
    }
};
```

#### Go

```go
func maxCompatibilitySum(students [][]int, mentors [][]int) (ans int) {
	m, n := len(students), len(students[0])
	g := make([][]int, m)
	vis := make([]bool, m)
	for i, x := range students {
		g[i] = make([]int, m)
		for j, y := range mentors {
			for k := 0; k < n; k++ {
				if x[k] == y[k] {
					g[i][j]++
				}
			}
		}
	}
	var dfs func(int, int)
	dfs = func(i, s int) {
		if i == m {
			ans = max(ans, s)
			return
		}
		for j := 0; j < m; j++ {
			if !vis[j] {
				vis[j] = true
				dfs(i+1, s+g[i][j])
				vis[j] = false
			}
		}
	}
	dfs(0, 0)
	return
}
```

#### TypeScript

```ts
function maxCompatibilitySum(students: number[][], mentors: number[][]): number {
    let ans = 0;
    const m = students.length;
    const vis: boolean[] = Array(m).fill(false);
    const g: number[][] = Array.from({ length: m }, () => Array(m).fill(0));
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < m; ++j) {
            for (let k = 0; k < students[i].length; ++k) {
                if (students[i][k] === mentors[j][k]) {
                    g[i][j]++;
                }
            }
        }
    }
    const dfs = (i: number, s: number): void => {
        if (i >= m) {
            ans = Math.max(ans, s);
            return;
        }
        for (let j = 0; j < m; ++j) {
            if (!vis[j]) {
                vis[j] = true;
                dfs(i + 1, s + g[i][j]);
                vis[j] = false;
            }
        }
    };
    dfs(0, 0);
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_compatibility_sum(students: Vec<Vec<i32>>, mentors: Vec<Vec<i32>>) -> i32 {
        let mut ans = 0;
        let m = students.len();
        let mut vis = vec![false; m];
        let mut g = vec![vec![0; m]; m];

        for i in 0..m {
            for j in 0..m {
                for k in 0..students[i].len() {
                    if students[i][k] == mentors[j][k] {
                        g[i][j] += 1;
                    }
                }
            }
        }

        fn dfs(i: usize, s: i32, m: usize, g: &Vec<Vec<i32>>, vis: &mut Vec<bool>, ans: &mut i32) {
            if i >= m {
                *ans = (*ans).max(s);
                return;
            }
            for j in 0..m {
                if !vis[j] {
                    vis[j] = true;
                    dfs(i + 1, s + g[i][j], m, g, vis, ans);
                    vis[j] = false;
                }
            }
        }

        dfs(0, 0, m, &g, &mut vis, &mut ans);
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number[][]} students
 * @param {number[][]} mentors
 * @return {number}
 */
var maxCompatibilitySum = function (students, mentors) {
    let ans = 0;
    const m = students.length;
    const vis = Array(m).fill(false);
    const g = Array.from({ length: m }, () => Array(m).fill(0));

    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < m; ++j) {
            for (let k = 0; k < students[i].length; ++k) {
                if (students[i][k] === mentors[j][k]) {
                    g[i][j]++;
                }
            }
        }
    }

    const dfs = function (i, s) {
        if (i >= m) {
            ans = Math.max(ans, s);
            return;
        }
        for (let j = 0; j < m; ++j) {
            if (!vis[j]) {
                vis[j] = true;
                dfs(i + 1, s + g[i][j]);
                vis[j] = false;
            }
        }
    };

    dfs(0, 0);
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
