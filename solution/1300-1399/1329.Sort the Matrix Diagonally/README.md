---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1329.Sort%20the%20Matrix%20Diagonally/README.md
rating: 1548
source: 第 18 场双周赛 Q3
tags:
    - 数组
    - 矩阵
    - 排序
---

# [1329. 将矩阵按对角线排序](https://leetcode.cn/problems/sort-the-matrix-diagonally)

[English Version](/solution/1300-1399/1329.Sort%20the%20Matrix%20Diagonally/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><strong>矩阵对角线</strong> 是一条从矩阵最上面行或者最左侧列中的某个元素开始的对角线，沿右下方向一直到矩阵末尾的元素。例如，矩阵 <code>mat</code> 有 <code>6</code> 行 <code>3</code> 列，从 <code>mat[2][0]</code> 开始的 <strong>矩阵对角线</strong> 将会经过 <code>mat[2][0]</code>、<code>mat[3][1]</code> 和 <code>mat[4][2]</code> 。</p>

<p>给你一个 <code>m * n</code> 的整数矩阵 <code>mat</code> ，请你将同一条 <strong>矩阵对角线 </strong>上的元素按升序排序后，返回排好序的矩阵。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1329.Sort%20the%20Matrix%20Diagonally/images/1482_example_1_2.png" style="height: 198px; width: 500px;" /></p>

<pre>
<strong>输入：</strong>mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
<strong>输出：</strong>[[1,1,1,1],[1,2,2,2],[1,2,3,3]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>mat = [[11,25,66,1,69,7],[23,55,17,45,15,52],[75,31,36,44,58,8],[22,27,33,25,68,4],[84,28,14,11,5,50]]
<strong>输出：</strong>[[5,17,4,1,52,7],[11,11,25,45,8,69],[14,23,25,44,58,15],[22,27,31,36,50,66],[84,28,75,33,55,68]]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == mat.length</code></li>
	<li><code>n == mat[i].length</code></li>
	<li><code>1 <= m, n <= 100</code></li>
	<li><code>1 <= mat[i][j] <= 100</code></li>
</ul>

## 解法

### 方法一：排序

我们可以将矩阵中的每条对角线看作一个数组，然后对这些数组进行排序，最后再将排序后的元素填回原矩阵中。

具体地，我们记矩阵的行数为 $m$，列数为 $n$。由于同一条对角线上的任意两个元素 $(i_1, j_1)$ 和 $(i_2, j_2)$ 满足 $j_1 - i_1 = j_2 - i_2$，我们可以根据 $j - i$ 的值来确定每条对角线。为了保证值为正数，我们加上一个偏移量 $m$，即 $m - i + j$。

最后，我们将每条对角线上的元素排序后填回原矩阵中即可。

时间复杂度 $O(m \times n \times \log \min(m, n))$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是矩阵的行数和列数。

<!-- tabs:start -->

```python
class Solution:
    def diagonalSort(self, mat: List[List[int]]) -> List[List[int]]:
        m, n = len(mat), len(mat[0])
        g = [[] for _ in range(m + n)]
        for i, row in enumerate(mat):
            for j, x in enumerate(row):
                g[m - i + j].append(x)
        for e in g:
            e.sort(reverse=True)
        for i in range(m):
            for j in range(n):
                mat[i][j] = g[m - i + j].pop()
        return mat
```

```java
class Solution {
    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        List<Integer>[] g = new List[m + n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                g[m - i + j].add(mat[i][j]);
            }
        }
        for (var e : g) {
            Collections.sort(e, (a, b) -> b - a);
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                mat[i][j] = g[m - i + j].removeLast();
            }
        }
        return mat;
    }
}
```

```cpp
class Solution {
public:
    vector<vector<int>> diagonalSort(vector<vector<int>>& mat) {
        int m = mat.size(), n = mat[0].size();
        vector<int> g[m + n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                g[m - i + j].push_back(mat[i][j]);
            }
        }
        for (auto& e : g) {
            sort(e.rbegin(), e.rend());
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                mat[i][j] = g[m - i + j].back();
                g[m - i + j].pop_back();
            }
        }
        return mat;
    }
};
```

```go
func diagonalSort(mat [][]int) [][]int {
	m, n := len(mat), len(mat[0])
	g := make([][]int, m+n)
	for i, row := range mat {
		for j, x := range row {
			g[m-i+j] = append(g[m-i+j], x)
		}
	}
	for _, e := range g {
		sort.Sort(sort.Reverse(sort.IntSlice(e)))
	}
	for i, row := range mat {
		for j := range row {
			k := len(g[m-i+j])
			mat[i][j] = g[m-i+j][k-1]
			g[m-i+j] = g[m-i+j][:k-1]
		}
	}
	return mat
}
```

```ts
function diagonalSort(mat: number[][]): number[][] {
    const [m, n] = [mat.length, mat[0].length];
    const g: number[][] = Array.from({ length: m + n }, () => []);
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            g[m - i + j].push(mat[i][j]);
        }
    }
    for (const e of g) {
        e.sort((a, b) => b - a);
    }
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            mat[i][j] = g[m - i + j].pop()!;
        }
    }
    return mat;
}
```

```rust
impl Solution {
    pub fn diagonal_sort(mut mat: Vec<Vec<i32>>) -> Vec<Vec<i32>> {
        let m = mat.len();
        let n = mat[0].len();
        let mut g: Vec<Vec<i32>> = vec![vec![]; m + n];
        for i in 0..m {
            for j in 0..n {
                g[m - i + j].push(mat[i][j]);
            }
        }
        for e in &mut g {
            e.sort_by(|a, b| b.cmp(a));
        }
        for i in 0..m {
            for j in 0..n {
                mat[i][j] = g[m - i + j].pop().unwrap();
            }
        }
        mat
    }
}
```

```cs
public class Solution {
    public int[][] DiagonalSort(int[][] mat) {
        int m = mat.Length;
        int n = mat[0].Length;
        List<List<int>> g = new List<List<int>>();
        for (int i = 0; i < m + n; i++) {
            g.Add(new List<int>());
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                g[m - i + j].Add(mat[i][j]);
            }
        }
        foreach (var e in g) {
            e.Sort((a, b) => b.CompareTo(a));
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int val = g[m - i + j][g[m - i + j].Count - 1];
                g[m - i + j].RemoveAt(g[m - i + j].Count - 1);
                mat[i][j] = val;
            }
        }
        return mat;
    }
}
```

<!-- tabs:end -->

<!-- end -->
