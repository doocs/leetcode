# [1632. Rank Transform of a Matrix](https://leetcode.com/problems/rank-transform-of-a-matrix)

[中文文档](/solution/1600-1699/1632.Rank%20Transform%20of%20a%20Matrix/README.md)

## Description

<p>Given an <code>m x n</code> <code>matrix</code>, return <em>a new matrix </em><code>answer</code><em> where </em><code>answer[row][col]</code><em> is the </em><em><strong>rank</strong> of </em><code>matrix[row][col]</code>.</p>

<p>The <strong>rank</strong> is an <strong>integer</strong> that represents how large an element is compared to other elements. It is calculated using the following rules:</p>

<ul>
	<li>The rank is an integer starting from <code>1</code>.</li>
	<li>If two elements <code>p</code> and <code>q</code> are in the <strong>same row or column</strong>, then:
	<ul>
		<li>If <code>p &lt; q</code> then <code>rank(p) &lt; rank(q)</code></li>
		<li>If <code>p == q</code> then <code>rank(p) == rank(q)</code></li>
		<li>If <code>p &gt; q</code> then <code>rank(p) &gt; rank(q)</code></li>
	</ul>
	</li>
	<li>The <strong>rank</strong> should be as <strong>small</strong> as possible.</li>
</ul>

<p>The test cases are generated so that <code>answer</code> is unique under the given rules.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1632.Rank%20Transform%20of%20a%20Matrix/images/rank1.jpg" style="width: 442px; height: 162px;" />
<pre>
<strong>Input:</strong> matrix = [[1,2],[3,4]]
<strong>Output:</strong> [[1,2],[2,3]]
<strong>Explanation:</strong>
The rank of matrix[0][0] is 1 because it is the smallest integer in its row and column.
The rank of matrix[0][1] is 2 because matrix[0][1] &gt; matrix[0][0] and matrix[0][0] is rank 1.
The rank of matrix[1][0] is 2 because matrix[1][0] &gt; matrix[0][0] and matrix[0][0] is rank 1.
The rank of matrix[1][1] is 3 because matrix[1][1] &gt; matrix[0][1], matrix[1][1] &gt; matrix[1][0], and both matrix[0][1] and matrix[1][0] are rank 2.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1632.Rank%20Transform%20of%20a%20Matrix/images/rank2.jpg" style="width: 442px; height: 162px;" />
<pre>
<strong>Input:</strong> matrix = [[7,7],[7,7]]
<strong>Output:</strong> [[1,1],[1,1]]
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1632.Rank%20Transform%20of%20a%20Matrix/images/rank3.jpg" style="width: 601px; height: 322px;" />
<pre>
<strong>Input:</strong> matrix = [[20,-21,14],[-19,4,19],[22,-47,24],[-19,4,19]]
<strong>Output:</strong> [[4,2,3],[1,3,4],[5,1,6],[1,3,4]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == matrix.length</code></li>
	<li><code>n == matrix[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 500</code></li>
	<li><code>-10<sup>9</sup> &lt;= matrix[row][col] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
