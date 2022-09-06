# [1632. 矩阵转换后的秩](https://leetcode.cn/problems/rank-transform-of-a-matrix)

[English Version](/solution/1600-1699/1632.Rank%20Transform%20of%20a%20Matrix/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <code>m x n</code> 的矩阵 <code>matrix</code> ，请你返回一个新的矩阵<em> </em><code>answer</code> ，其中<em> </em><code>answer[row][col]</code> 是 <code>matrix[row][col]</code> 的秩。</p>

<p>每个元素的 <b>秩</b> 是一个整数，表示这个元素相对于其他元素的大小关系，它按照如下规则计算：</p>

<ul>
	<li>秩是从 1 开始的一个整数。</li>
	<li>如果两个元素 <code>p</code> 和 <code>q</code> 在 <strong>同一行</strong> 或者 <strong>同一列</strong> ，那么：
	<ul>
		<li>如果 <code>p < q</code> ，那么 <code>rank(p) < rank(q)</code></li>
		<li>如果 <code>p == q</code> ，那么 <code>rank(p) == rank(q)</code></li>
		<li>如果 <code>p > q</code> ，那么 <code>rank(p) > rank(q)</code></li>
	</ul>
	</li>
	<li><b>秩</b> 需要越 <strong>小</strong> 越好。</li>
</ul>

<p>题目保证按照上面规则 <code>answer</code> 数组是唯一的。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1632.Rank%20Transform%20of%20a%20Matrix/images/rank1.jpg" style="width: 442px; height: 162px;" />
<pre>
<b>输入：</b>matrix = [[1,2],[3,4]]
<b>输出：</b>[[1,2],[2,3]]
<strong>解释：</strong>
matrix[0][0] 的秩为 1 ，因为它是所在行和列的最小整数。
matrix[0][1] 的秩为 2 ，因为 matrix[0][1] > matrix[0][0] 且 matrix[0][0] 的秩为 1 。
matrix[1][0] 的秩为 2 ，因为 matrix[1][0] > matrix[0][0] 且 matrix[0][0] 的秩为 1 。
matrix[1][1] 的秩为 3 ，因为 matrix[1][1] > matrix[0][1]， matrix[1][1] > matrix[1][0] 且 matrix[0][1] 和 matrix[1][0] 的秩都为 2 。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1632.Rank%20Transform%20of%20a%20Matrix/images/rank2.jpg" style="width: 442px; height: 162px;" />
<pre>
<b>输入：</b>matrix = [[7,7],[7,7]]
<b>输出：</b>[[1,1],[1,1]]
</pre>

<p><strong>示例 3：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1632.Rank%20Transform%20of%20a%20Matrix/images/rank3.jpg" style="width: 601px; height: 322px;" />
<pre>
<b>输入：</b>matrix = [[20,-21,14],[-19,4,19],[22,-47,24],[-19,4,19]]
<b>输出：</b>[[4,2,3],[1,3,4],[5,1,6],[1,3,4]]
</pre>

<p><strong>示例 4：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1632.Rank%20Transform%20of%20a%20Matrix/images/rank4.jpg" style="width: 601px; height: 242px;" />
<pre>
<b>输入：</b>matrix = [[7,3,6],[1,4,5],[9,8,2]]
<b>输出：</b>[[5,1,4],[1,2,3],[6,3,1]]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == matrix.length</code></li>
	<li><code>n == matrix[i].length</code></li>
	<li><code>1 <= m, n <= 500</code></li>
	<li><code>-10<sup>9</sup> <= matrix[row][col] <= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 并查集**

先考虑简化情形：没有相同的元素。那么显然最小的元素的秩为 $1$，第二小的元素则要考虑是否和最小元素同行或同列。于是得到贪心解法：从小到大遍历元素，并维护每行、每列的最大秩，该元素的秩即为同行、同列的最大秩加 $1$。见题目：[2371. Minimize Maximum Value in a Grid](/solution/2300-2399/2371.Minimize%20Maximum%20Value%20in%20a%20Grid/README.md)。

存在相同元素时则较为复杂，假设两个相同元素同行（或同列），那么就要考虑到两个元素分别对应的行（或列）的最大秩。同时还可能出现联动，比如元素 `a` 和 `b` 同行，`b` 和 `c` 同列，那么要同时考虑这三个元素。

这种联动容易想到并查集，于是用并查集将相同元素分为几个连通块，对于每个连通块，里面所有元素对应的行或列的最大秩加 $1$，即为该连通块内所有元素的秩。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def matrixRankTransform(self, matrix: List[List[int]]) -> List[List[int]]:
        def find(x):
            if p.setdefault(x, x) != x:
                p[x] = find(p[x])
            return p[x]

        def union(a, b):
            p[find(a)] = find(b)

        m, n = len(matrix), len(matrix[0])
        d = defaultdict(list)
        for i, row in enumerate(matrix):
            for j, v in enumerate(row):
                d[v].append((i, j))
        row_max = [0] * m
        col_max = [0] * n
        ans = [[0] * n for _ in range(m)]
        for v in sorted(d):
            p, rank = {}, defaultdict(int)
            for i, j in d[v]:
                union(i, j + 500)
            for i, j in d[v]:
                rank[find(i)] = max(rank[find(i)], row_max[i], col_max[j])
            for i, j in d[v]:
                ans[i][j] = row_max[i] = col_max[j] = 1 + rank[find(i)]
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private Map<Integer, Integer> p = new HashMap<>();
    private Map<Integer, Integer> rank = new HashMap<>();

    public int[][] matrixRankTransform(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        TreeMap<Integer, List<int[]>> d = new TreeMap<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                d.computeIfAbsent(matrix[i][j], k -> new ArrayList<>()).add(new int[] {i, j});
            }
        }
        int[] rowMax = new int[m];
        int[] colMax = new int[n];
        int[][] ans = new int[m][n];
        for (var e : d.entrySet()) {
            int v = e.getKey();
            var g = e.getValue();
            p.clear();
            rank.clear();
            for (int[] x : g) {
                union(x[0], x[1] + 500);
            }
            for (int[] x : g) {
                int i = x[0], j = x[1];
                rank.put(find(i),
                    Math.max(rank.getOrDefault(find(i), 0), Math.max(rowMax[i], colMax[j])));
            }
            for (int[] x : g) {
                int i = x[0], j = x[1];
                ans[i][j] = 1 + rank.getOrDefault(find(i), 0);
                rowMax[i] = ans[i][j];
                colMax[j] = ans[i][j];
            }
        }
        return ans;
    }

    private void union(int a, int b) {
        p.put(find(a), find(b));
    }

    private int find(int x) {
        p.putIfAbsent(x, x);
        if (p.get(x) != x) {
            p.put(x, find(p.get(x)));
        }
        return p.get(x);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> matrixRankTransform(vector<vector<int>>& matrix) {
        int m = matrix.size(), n = matrix[0].size();
        map<int, vector<pair<int, int>>> d;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                d[matrix[i][j]].push_back({i, j});
            }
        }
        vector<int> rowMax(m);
        vector<int> colMax(n);
        vector<vector<int>> ans(m, vector<int>(n));
        for (auto& [v, g] : d) {
            unordered_map<int, int> p;
            unordered_map<int, int> rank;
            for (auto [i, j] : g) {
                unite(i, j + 500, p);
            }
            for (auto [i, j] : g) {
                rank[find(i, p)] = max(rank[find(i, p)], max(rowMax[i], colMax[j]));
            }
            for (auto [i, j] : g) {
                ans[i][j] = rowMax[i] = colMax[j] = 1 + rank[find(i, p)];
            }
        }
        return ans;
    }

    void unite(int a, int b, unordered_map<int, int>& p) {
        p[find(a, p)] = find(b, p);
    }

    int find(int x, unordered_map<int, int>& p) {
        if (!p.count(x)) p[x] = x;
        if (p[x] != x) p[x] = find(p[x], p);
        return p[x];
    }
};
```

### **...**

```

```

<!-- tabs:end -->
