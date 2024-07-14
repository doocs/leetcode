---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1222.Queens%20That%20Can%20Attack%20the%20King/README.md
rating: 1391
source: 第 158 场周赛 Q2
tags:
    - 数组
    - 矩阵
    - 模拟
---

<!-- problem:start -->

# [1222. 可以攻击国王的皇后](https://leetcode.cn/problems/queens-that-can-attack-the-king)

[English Version](/solution/1200-1299/1222.Queens%20That%20Can%20Attack%20the%20King/README_EN.md)

## 题目描述

<!-- description:start -->

<p>在一个 <strong>下标从 0 开始</strong> 的 <code>8 x 8</code> 棋盘上，可能有多个黑皇后和一个白国王。</p>

<p>给你一个二维整数数组 <code>queens</code>，其中 <code>queens[i] = [xQueeni, yQueeni]</code> 表示第 <code>i</code> 个黑皇后在棋盘上的位置。还给你一个长度为 <code>2</code> 的整数数组 <code>king</code>，其中 <code>king = [xKing, yKing]</code> 表示白国王的位置。</p>

<p>返回 <em>能够直接攻击国王的黑皇后的坐标</em>。你可以以 <strong>任何顺序</strong> 返回答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1222.Queens%20That%20Can%20Attack%20the%20King/images/1703052515-HqjAJq-chess1.jpg" style="width: 400px; height: 400px;" /></p>

<pre>
<strong>输入：</strong>queens = [[0,1],[1,0],[4,0],[0,4],[3,3],[2,4]], king = [0,0]
<strong>输出：</strong>[[0,1],[1,0],[3,3]]
<strong>解释：</strong>上面的图示显示了三个可以直接攻击国王的皇后和三个不能攻击国王的皇后（用红色虚线标记）。
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1222.Queens%20That%20Can%20Attack%20the%20King/images/1703052660-bPPflt-chess2.jpg" style="width: 400px; height: 400px;" /></strong></p>

<pre>
<strong>输入：</strong>queens = [[0,0],[1,1],[2,2],[3,4],[3,5],[4,4],[4,5]], king = [3,3]
<strong>输出：</strong>[[2,2],[3,4],[4,4]]
<strong>解释：</strong>上面的图示显示了三个能够直接攻击国王的黑皇后和三个不能攻击国王的黑皇后（用红色虚线标记）。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><meta charset="UTF-8" /><code>1 &lt;= queens.length &lt; 64</code></li>
	<li><code>queens[i].length == king.length == 2</code></li>
	<li><code>0 &lt;= xQueen<sub>i</sub>, yQueen<sub>i</sub>, xKing, yKing &lt; 8</code></li>
	<li>所有给定的位置都是 <strong>唯一</strong> 的。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：直接搜索

我们先将所有皇后的位置存入哈希表或者二维数组 $s$ 中。

接下来，我们从国王的位置开始，依次向上、下、左、右、左上、右上、左下、右下八个方向搜索，如果某个方向上存在皇后，那么就将其位置加入答案中，并且停止继续搜索该方向。

搜索结束后，返回答案即可。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。本题中 $n = 8$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def queensAttacktheKing(
        self, queens: List[List[int]], king: List[int]
    ) -> List[List[int]]:
        n = 8
        s = {(i, j) for i, j in queens}
        ans = []
        for a in range(-1, 2):
            for b in range(-1, 2):
                if a or b:
                    x, y = king
                    while 0 <= x + a < n and 0 <= y + b < n:
                        x, y = x + a, y + b
                        if (x, y) in s:
                            ans.append([x, y])
                            break
        return ans
```

#### Java

```java
class Solution {
    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        final int n = 8;
        var s = new boolean[n][n];
        for (var q : queens) {
            s[q[0]][q[1]] = true;
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int a = -1; a <= 1; ++a) {
            for (int b = -1; b <= 1; ++b) {
                if (a != 0 || b != 0) {
                    int x = king[0] + a, y = king[1] + b;
                    while (x >= 0 && x < n && y >= 0 && y < n) {
                        if (s[x][y]) {
                            ans.add(List.of(x, y));
                            break;
                        }
                        x += a;
                        y += b;
                    }
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
    vector<vector<int>> queensAttacktheKing(vector<vector<int>>& queens, vector<int>& king) {
        int n = 8;
        bool s[8][8]{};
        for (auto& q : queens) {
            s[q[0]][q[1]] = true;
        }
        vector<vector<int>> ans;
        for (int a = -1; a <= 1; ++a) {
            for (int b = -1; b <= 1; ++b) {
                if (a || b) {
                    int x = king[0] + a, y = king[1] + b;
                    while (x >= 0 && x < n && y >= 0 && y < n) {
                        if (s[x][y]) {
                            ans.push_back({x, y});
                            break;
                        }
                        x += a;
                        y += b;
                    }
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func queensAttacktheKing(queens [][]int, king []int) (ans [][]int) {
	n := 8
	s := [8][8]bool{}
	for _, q := range queens {
		s[q[0]][q[1]] = true
	}
	for a := -1; a <= 1; a++ {
		for b := -1; b <= 1; b++ {
			if a != 0 || b != 0 {
				x, y := king[0]+a, king[1]+b
				for 0 <= x && x < n && 0 <= y && y < n {
					if s[x][y] {
						ans = append(ans, []int{x, y})
						break
					}
					x += a
					y += b
				}
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function queensAttacktheKing(queens: number[][], king: number[]): number[][] {
    const n = 8;
    const s: boolean[][] = Array.from({ length: n }, () => Array.from({ length: n }, () => false));
    queens.forEach(([x, y]) => (s[x][y] = true));
    const ans: number[][] = [];
    for (let a = -1; a <= 1; ++a) {
        for (let b = -1; b <= 1; ++b) {
            if (a || b) {
                let [x, y] = [king[0] + a, king[1] + b];
                while (x >= 0 && x < n && y >= 0 && y < n) {
                    if (s[x][y]) {
                        ans.push([x, y]);
                        break;
                    }
                    x += a;
                    y += b;
                }
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
