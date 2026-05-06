---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1861.Rotating%20the%20Box/README.md
rating: 1536
source: 第 52 场双周赛 Q3
tags:
    - 数组
    - 双指针
    - 矩阵
---

<!-- problem:start -->

# [1861. 旋转盒子](https://leetcode.cn/problems/rotating-the-box)

[English Version](/solution/1800-1899/1861.Rotating%20the%20Box/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个&nbsp;<code>m x n</code>&nbsp;的字符矩阵&nbsp;<code>boxGrid</code>&nbsp;，它表示一个箱子的侧视图。箱子的每一个格子可能为：</p>

<ul>
	<li><code>'#'</code>&nbsp;表示石头</li>
	<li><code>'*'</code>&nbsp;表示固定的障碍物</li>
	<li><code>'.'</code>&nbsp;表示空位置</li>
</ul>

<p>这个箱子被 <strong>顺时针旋转 90 度</strong>&nbsp;，由于重力原因，部分石头的位置会发生改变。每个石头会垂直掉落，直到它遇到障碍物，另一个石头或者箱子的底部。重力 <strong>不会</strong>&nbsp;影响障碍物的位置，同时箱子旋转不会产生<strong>惯性</strong>&nbsp;，也就是说石头的水平位置不会发生改变。</p>

<p>题目保证初始时&nbsp;<code>boxGrid</code>&nbsp;中的石头要么在一个障碍物上，要么在另一个石头上，要么在箱子的底部。</p>

<p>请你返回一个<em>&nbsp;</em><code>n x m</code>&nbsp;的矩阵，表示按照上述旋转后，箱子内的结果。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1861.Rotating%20the%20Box/images/rotatingtheboxleetcodewithstones.png" style="width: 300px; height: 150px;" /></p>

<pre>
<b>输入：</b>box = [["#",".","#"]]
<b>输出：</b>[["."],
&nbsp;     ["#"],
&nbsp;     ["#"]]
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1861.Rotating%20the%20Box/images/rotatingtheboxleetcode2withstones.png" style="width: 375px; height: 195px;" /></p>

<pre>
<b>输入：</b>box = [["#",".","*","."],
&nbsp;           ["#","#","*","."]]
<b>输出：</b>[["#","."],
&nbsp;     ["#","#"],
&nbsp;     ["*","*"],
&nbsp;     [".","."]]
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1861.Rotating%20the%20Box/images/rotatingtheboxleetcode3withstone.png" style="width: 400px; height: 218px;" /></p>

<pre>
<b>输入：</b>box = [["#","#","*",".","*","."],
&nbsp;           ["#","#","#","*",".","."],
&nbsp;           ["#","#","#",".","#","."]]
<b>输出：</b>[[".","#","#"],
&nbsp;     [".","#","#"],
&nbsp;     ["#","#","*"],
&nbsp;     ["#","*","."],
&nbsp;     ["#",".","*"],
&nbsp;     ["#",".","."]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == boxGrid.length</code></li>
	<li><code>n == boxGrid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 500</code></li>
	<li><code>boxGrid[i][j]</code>&nbsp;只可能是&nbsp;<code>'#'</code>&nbsp;，<code>'*'</code>&nbsp;或者&nbsp;<code>'.'</code>&nbsp;。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：队列模拟

我们先将矩阵顺时针旋转 90 度，然后模拟每一列石头的下落过程。

具体地，我们使用一个队列 $q$ 来存储当前列中空位置的行号。遍历每一列，我们从下往上扫描，如果遇到一个石头，我们就将它掉落到 $q$ 中第一个空位置，并将这个空位置从 $q$ 中移除，由于当前位置变成了空位置，我们就将它的行号加入 $q$ 中；如果遇到一个障碍物，我们就清空 $q$，因为石头无法穿过障碍物；如果遇到一个空位置，我们就将它的行号加入 $q$ 中。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是矩阵的行数和列数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def rotateTheBox(self, boxGrid: List[List[str]]) -> List[List[str]]:
        m, n = len(boxGrid), len(boxGrid[0])
        ans = [[None] * m for _ in range(n)]
        for i in range(m):
            for j in range(n):
                ans[j][m - i - 1] = boxGrid[i][j]
        for j in range(m):
            q = deque()
            for i in range(n - 1, -1, -1):
                if ans[i][j] == "*":
                    q.clear()
                elif ans[i][j] == ".":
                    q.append(i)
                elif q:
                    ans[q.popleft()][j] = "#"
                    ans[i][j] = "."
                    q.append(i)
        return ans
```

#### Java

```java
class Solution {
    public char[][] rotateTheBox(char[][] boxGrid) {
        int m = boxGrid.length, n = boxGrid[0].length;
        char[][] ans = new char[n][m];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans[j][m - i - 1] = boxGrid[i][j];
            }
        }
        for (int j = 0; j < m; ++j) {
            Deque<Integer> q = new ArrayDeque<>();
            for (int i = n - 1; i >= 0; --i) {
                if (ans[i][j] == '*') {
                    q.clear();
                } else if (ans[i][j] == '.') {
                    q.offer(i);
                } else if (!q.isEmpty()) {
                    ans[q.pollFirst()][j] = '#';
                    ans[i][j] = '.';
                    q.offer(i);
                }
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<char>> rotateTheBox(vector<vector<char>>& boxGrid) {
        int m = boxGrid.size(), n = boxGrid[0].size();
        vector<vector<char>> ans(n, vector<char>(m));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans[j][m - i - 1] = boxGrid[i][j];
            }
        }
        for (int j = 0; j < m; ++j) {
            queue<int> q;
            for (int i = n - 1; ~i; --i) {
                if (ans[i][j] == '*') {
                    queue<int> t;
                    swap(t, q);
                } else if (ans[i][j] == '.') {
                    q.push(i);
                } else if (!q.empty()) {
                    ans[q.front()][j] = '#';
                    q.pop();
                    ans[i][j] = '.';
                    q.push(i);
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func rotateTheBox(boxGrid [][]byte) [][]byte {
	m, n := len(boxGrid), len(boxGrid[0])
	ans := make([][]byte, n)
	for i := range ans {
		ans[i] = make([]byte, m)
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			ans[j][m-i-1] = boxGrid[i][j]
		}
	}
	for j := 0; j < m; j++ {
		q := []int{}
		for i := n - 1; i >= 0; i-- {
			if ans[i][j] == '*' {
				q = []int{}
			} else if ans[i][j] == '.' {
				q = append(q, i)
			} else if len(q) > 0 {
				ans[q[0]][j] = '#'
				q = q[1:]
				ans[i][j] = '.'
				q = append(q, i)
			}
		}
	}
	return ans
}
```

#### TypeScript

```ts
function rotateTheBox(boxGrid: string[][]): string[][] {
    const m = boxGrid.length;
    const n = boxGrid[0].length;
    const ans: string[][] = Array.from({ length: n }, () => Array(m));

    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            ans[j][m - i - 1] = boxGrid[i][j];
        }
    }

    for (let j = 0; j < m; j++) {
        const q: number[] = [];
        for (let i = n - 1; i >= 0; i--) {
            if (ans[i][j] === '*') {
                q.length = 0;
            } else if (ans[i][j] === '.') {
                q.push(i);
            } else if (q.length > 0) {
                const t = q.shift()!;
                ans[t][j] = '#';
                ans[i][j] = '.';
                q.push(i);
            }
        }
    }

    return ans;
}
```

#### Rust

```rust
use std::collections::VecDeque;

impl Solution {
    pub fn rotate_the_box(box_grid: Vec<Vec<char>>) -> Vec<Vec<char>> {
        let m: usize = box_grid.len();
        let n: usize = box_grid[0].len();
        let mut ans: Vec<Vec<char>> = vec![vec![' '; m]; n];

        for i in 0..m {
            for j in 0..n {
                ans[j][m - i - 1] = box_grid[i][j];
            }
        }

        for j in 0..m {
            let mut q: VecDeque<usize> = VecDeque::new();
            for i in (0..n).rev() {
                if ans[i][j] == '*' {
                    q.clear();
                } else if ans[i][j] == '.' {
                    q.push_back(i);
                } else if !q.is_empty() {
                    let t = q.pop_front().unwrap();
                    ans[t][j] = '#';
                    ans[i][j] = '.';
                    q.push_back(i);
                }
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
