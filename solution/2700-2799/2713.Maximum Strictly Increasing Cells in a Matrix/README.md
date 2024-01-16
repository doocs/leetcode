# [2713. 矩阵中严格递增的单元格数](https://leetcode.cn/problems/maximum-strictly-increasing-cells-in-a-matrix)

[English Version](/solution/2700-2799/2713.Maximum%20Strictly%20Increasing%20Cells%20in%20a%20Matrix/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>1</strong> 开始、大小为 <code>m x n</code> 的整数矩阵 <code>mat</code>，你可以选择任一单元格作为 <strong>起始单元格</strong> 。</p>

<p>从起始单元格出发，你可以移动到 <strong>同一行或同一列</strong> 中的任何其他单元格，但前提是目标单元格的值<strong> 严格大于 </strong>当前单元格的值。</p>

<p>你可以多次重复这一过程，从一个单元格移动到另一个单元格，直到无法再进行任何移动。</p>

<p>请你找出从某个单元开始访问矩阵所能访问的 <strong>单元格的最大数量</strong> 。</p>

<p>返回一个表示可访问单元格最大数量的整数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2713.Maximum%20Strictly%20Increasing%20Cells%20in%20a%20Matrix/images/diag1drawio.png" style="width: 200px; height: 176px;"></strong></p>

<pre><strong>输入：</strong>mat = [[3,1],[3,4]]
<strong>输出：</strong>2
<strong>解释：</strong>上图展示了从第 1 行、第 2 列的单元格开始，可以访问 2 个单元格。可以证明，无论从哪个单元格开始，最多只能访问 2 个单元格，因此答案是 2 。 
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2713.Maximum%20Strictly%20Increasing%20Cells%20in%20a%20Matrix/images/diag3drawio.png" style="width: 200px; height: 176px;"></strong></p>

<pre><strong>输入：</strong>mat = [[1,1],[1,1]]
<strong>输出：</strong>1
<strong>解释：</strong>由于目标单元格必须严格大于当前单元格，在本示例中只能访问 1 个单元格。 
</pre>

<p><strong>示例 3：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2713.Maximum%20Strictly%20Increasing%20Cells%20in%20a%20Matrix/images/diag4drawio.png" style="width: 350px; height: 250px;"></strong></p>

<pre><strong>输入：</strong>mat = [[3,1,6],[-9,5,7]]
<strong>输出：</strong>4
<strong>解释：</strong>上图展示了从第 2 行、第 1 列的单元格开始，可以访问 4 个单元格。可以证明，无论从哪个单元格开始，最多只能访问 4 个单元格，因此答案是 4 。  
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == mat.length&nbsp;</code></li>
	<li><code>n == mat[i].length&nbsp;</code></li>
	<li><code>1 &lt;= m, n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup>&nbsp;&lt;= mat[i][j] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python
class Solution:
    def maxIncreasingCells(self, mat: List[List[int]]) -> int:
        m, n = len(mat), len(mat[0])
        g = defaultdict(list)
        for i in range(m):
            for j in range(n):
                g[mat[i][j]].append((i, j))
        rowMax = [0] * m
        colMax = [0] * n
        ans = 0
        for _, pos in sorted(g.items()):
            mx = []
            for i, j in pos:
                mx.append(1 + max(rowMax[i], colMax[j]))
                ans = max(ans, mx[-1])
            for k, (i, j) in enumerate(pos):
                rowMax[i] = max(rowMax[i], mx[k])
                colMax[j] = max(colMax[j], mx[k])
        return ans
```

```java
class Solution {
    public int maxIncreasingCells(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        TreeMap<Integer, List<int[]>> g = new TreeMap<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                g.computeIfAbsent(mat[i][j], k -> new ArrayList<>()).add(new int[] {i, j});
            }
        }
        int[] rowMax = new int[m];
        int[] colMax = new int[n];
        int ans = 0;
        for (var e : g.entrySet()) {
            var pos = e.getValue();
            int[] mx = new int[pos.size()];
            int k = 0;
            for (var p : pos) {
                int i = p[0], j = p[1];
                mx[k] = Math.max(rowMax[i], colMax[j]) + 1;
                ans = Math.max(ans, mx[k++]);
            }
            for (k = 0; k < mx.length; ++k) {
                int i = pos.get(k)[0], j = pos.get(k)[1];
                rowMax[i] = Math.max(rowMax[i], mx[k]);
                colMax[j] = Math.max(colMax[j], mx[k]);
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maxIncreasingCells(vector<vector<int>>& mat) {
        int m = mat.size(), n = mat[0].size();
        map<int, vector<pair<int, int>>> g;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                g[mat[i][j]].emplace_back(i, j);
            }
        }
        vector<int> rowMax(m);
        vector<int> colMax(n);
        int ans = 0;
        for (auto& [_, pos] : g) {
            vector<int> mx;
            for (auto& [i, j] : pos) {
                mx.push_back(max(rowMax[i], colMax[j]) + 1);
                ans = max(ans, mx.back());
            }
            for (int k = 0; k < mx.size(); ++k) {
                auto& [i, j] = pos[k];
                rowMax[i] = max(rowMax[i], mx[k]);
                colMax[j] = max(colMax[j], mx[k]);
            }
        }
        return ans;
    }
};
```

```go
func maxIncreasingCells(mat [][]int) (ans int) {
	m, n := len(mat), len(mat[0])
	g := map[int][][2]int{}
	for i, row := range mat {
		for j, v := range row {
			g[v] = append(g[v], [2]int{i, j})
		}
	}
	nums := make([]int, 0, len(g))
	for k := range g {
		nums = append(nums, k)
	}
	sort.Ints(nums)
	rowMax := make([]int, m)
	colMax := make([]int, n)
	for _, k := range nums {
		pos := g[k]
		mx := make([]int, len(pos))
		for i, p := range pos {
			mx[i] = max(rowMax[p[0]], colMax[p[1]]) + 1
			ans = max(ans, mx[i])
		}
		for i, p := range pos {
			rowMax[p[0]] = max(rowMax[p[0]], mx[i])
			colMax[p[1]] = max(colMax[p[1]], mx[i])
		}
	}
	return
}
```

<!-- tabs:end -->

<!-- end -->
