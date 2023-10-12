# [311. 稀疏矩阵的乘法](https://leetcode.cn/problems/sparse-matrix-multiplication)

[English Version](/solution/0300-0399/0311.Sparse%20Matrix%20Multiplication/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个&nbsp;<a href="https://baike.baidu.com/item/%E7%A8%80%E7%96%8F%E7%9F%A9%E9%98%B5" target="_blank">稀疏矩阵</a>&nbsp;：大小为 <code>m x k</code> 的稀疏矩阵 <code>mat1</code> 和大小为 <code>k x n</code> 的稀疏矩阵 <code>mat2</code> ，返回 <code>mat1 x mat2</code> 的结果。你可以假设乘法总是可能的。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0311.Sparse%20Matrix%20Multiplication/images/mult-grid.jpg" style="height: 142px; width: 500px;" /></p>

<pre>
<strong>输入：</strong>mat1 = [[1,0,0],[-1,0,3]], mat2 = [[7,0,0],[0,0,0],[0,0,1]]
<strong>输出：</strong>[[7,0,0],[-7,0,3]]
</pre>

<p><strong>&nbsp;示例 2:</strong></p>

<pre>
<b>输入：</b>mat1 = [[0]], mat2 = [[0]]
<b>输出：</b>[[0]]
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>m == mat1.length</code></li>
	<li><code>k == mat1[i].length == mat2.length</code></li>
	<li><code>n == mat2[i].length</code></li>
	<li><code>1 &lt;= m, n, k &lt;= 100</code></li>
	<li><code>-100 &lt;= mat1[i][j], mat2[i][j] &lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：直接相乘**

我们可以直接按照矩阵乘法的定义，计算出结果矩阵中的每一个元素。

时间复杂度 $O(m \times n \times k)$，空间复杂度 $O(m \times n)$。

**方法二：预处理**

我们可以预处理出两个矩阵的稀疏表示，即 $g1[i]$ 表示矩阵 $mat1$ 第 $i$ 行中所有非零元素的列下标和值，而 $g2[i]$ 表示矩阵 $mat2$ 第 $i$ 行中所有非零元素的列下标和值。

接下来，我们遍历每一行 $i$，遍历 $g1[i]$ 中的每一个元素 $(k, x)$，遍历 $g2[k]$ 中的每一个元素 $(j, y)$，那么最终 $mat1[i][k] \times mat2[k][j]$ 就会对应到结果矩阵中的 $ans[i][j]$，我们将所有的结果累加即可。

时间复杂度 $O(m \times n \times k)$，空间复杂度 $O(m \times n)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def multiply(self, mat1: List[List[int]], mat2: List[List[int]]) -> List[List[int]]:
        m, n = len(mat1), len(mat2[0])
        ans = [[0] * n for _ in range(m)]
        for i in range(m):
            for j in range(n):
                for k in range(len(mat2)):
                    ans[i][j] += mat1[i][k] * mat2[k][j]
        return ans
```

```python
class Solution:
    def multiply(self, mat1: List[List[int]], mat2: List[List[int]]) -> List[List[int]]:
        def f(mat: List[List[int]]) -> List[List[int]]:
            g = [[] for _ in range(len(mat))]
            for i, row in enumerate(mat):
                for j, x in enumerate(row):
                    if x:
                        g[i].append((j, x))
            return g

        g1 = f(mat1)
        g2 = f(mat2)
        m, n = len(mat1), len(mat2[0])
        ans = [[0] * n for _ in range(m)]
        for i in range(m):
            for k, x in g1[i]:
                for j, y in g2[k]:
                    ans[i][j] += x * y
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[][] multiply(int[][] mat1, int[][] mat2) {
        int m = mat1.length, n = mat2[0].length;
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < mat2.length; ++k) {
                    ans[i][j] += mat1[i][k] * mat2[k][j];
                }
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int[][] multiply(int[][] mat1, int[][] mat2) {
        int m = mat1.length, n = mat2[0].length;
        int[][] ans = new int[m][n];
        var g1 = f(mat1);
        var g2 = f(mat2);
        for (int i = 0; i < m; ++i) {
            for (int[] p : g1[i]) {
                int k = p[0], x = p[1];
                for (int[] q : g2[k]) {
                    int j = q[0], y = q[1];
                    ans[i][j] += x * y;
                }
            }
        }
        return ans;
    }

    private List<int[]>[] f(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        List<int[]>[] g = new List[m];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (mat[i][j] != 0) {
                    g[i].add(new int[] {j, mat[i][j]});
                }
            }
        }
        return g;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> multiply(vector<vector<int>>& mat1, vector<vector<int>>& mat2) {
        int m = mat1.size(), n = mat2[0].size();
        vector<vector<int>> ans(m, vector<int>(n));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < mat2.size(); ++k) {
                    ans[i][j] += mat1[i][k] * mat2[k][j];
                }
            }
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    vector<vector<int>> multiply(vector<vector<int>>& mat1, vector<vector<int>>& mat2) {
        int m = mat1.size(), n = mat2[0].size();
        vector<vector<int>> ans(m, vector<int>(n));
        auto g1 = f(mat1), g2 = f(mat2);
        for (int i = 0; i < m; ++i) {
            for (auto& [k, x] : g1[i]) {
                for (auto& [j, y] : g2[k]) {
                    ans[i][j] += x * y;
                }
            }
        }
        return ans;
    }

    vector<vector<pair<int, int>>> f(vector<vector<int>>& mat) {
        int m = mat.size(), n = mat[0].size();
        vector<vector<pair<int, int>>> g(m);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (mat[i][j]) {
                    g[i].emplace_back(j, mat[i][j]);
                }
            }
        }
        return g;
    }
};
```

### **Go**

```go
func multiply(mat1 [][]int, mat2 [][]int) [][]int {
	m, n := len(mat1), len(mat2[0])
	ans := make([][]int, m)
	for i := range ans {
		ans[i] = make([]int, n)
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			for k := 0; k < len(mat2); k++ {
				ans[i][j] += mat1[i][k] * mat2[k][j]
			}
		}
	}
	return ans
}
```

```go
func multiply(mat1 [][]int, mat2 [][]int) [][]int {
	m, n := len(mat1), len(mat2[0])
	ans := make([][]int, m)
	for i := range ans {
		ans[i] = make([]int, n)
	}
	f := func(mat [][]int) [][][2]int {
		m, n := len(mat), len(mat[0])
		g := make([][][2]int, m)
		for i := range g {
			g[i] = make([][2]int, 0, n)
			for j := range mat[i] {
				if mat[i][j] != 0 {
					g[i] = append(g[i], [2]int{j, mat[i][j]})
				}
			}
		}
		return g
	}
	g1, g2 := f(mat1), f(mat2)
	for i := range g1 {
		for _, p := range g1[i] {
			k, x := p[0], p[1]
			for _, q := range g2[k] {
				j, y := q[0], q[1]
				ans[i][j] += x * y
			}
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function multiply(mat1: number[][], mat2: number[][]): number[][] {
    const [m, n] = [mat1.length, mat2[0].length];
    const ans: number[][] = Array.from({ length: m }, () => Array.from({ length: n }, () => 0));
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            for (let k = 0; k < mat2.length; ++k) {
                ans[i][j] += mat1[i][k] * mat2[k][j];
            }
        }
    }
    return ans;
}
```

```ts
function multiply(mat1: number[][], mat2: number[][]): number[][] {
    const [m, n] = [mat1.length, mat2[0].length];
    const ans: number[][] = Array.from({ length: m }, () => Array.from({ length: n }, () => 0));
    const f = (mat: number[][]): number[][][] => {
        const [m, n] = [mat.length, mat[0].length];
        const ans: number[][][] = Array.from({ length: m }, () => []);
        for (let i = 0; i < m; ++i) {
            for (let j = 0; j < n; ++j) {
                if (mat[i][j] !== 0) {
                    ans[i].push([j, mat[i][j]]);
                }
            }
        }
        return ans;
    };
    const g1 = f(mat1);
    const g2 = f(mat2);
    for (let i = 0; i < m; ++i) {
        for (const [k, x] of g1[i]) {
            for (const [j, y] of g2[k]) {
                ans[i][j] += x * y;
            }
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
