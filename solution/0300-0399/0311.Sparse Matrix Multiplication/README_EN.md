# [311. Sparse Matrix Multiplication](https://leetcode.com/problems/sparse-matrix-multiplication)

[中文文档](/solution/0300-0399/0311.Sparse%20Matrix%20Multiplication/README.md)

## Description

<p>Given two <a href="https://en.wikipedia.org/wiki/Sparse_matrix" target="_blank">sparse matrices</a> <code>mat1</code> of size <code>m x k</code> and <code>mat2</code> of size <code>k x n</code>, return the result of <code>mat1 x mat2</code>. You may assume that multiplication is always possible.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0311.Sparse%20Matrix%20Multiplication/images/mult-grid.jpg" style="width: 500px; height: 142px;" />
<pre>
<strong>Input:</strong> mat1 = [[1,0,0],[-1,0,3]], mat2 = [[7,0,0],[0,0,0],[0,0,1]]
<strong>Output:</strong> [[7,0,0],[-7,0,3]]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> mat1 = [[0]], mat2 = [[0]]
<strong>Output:</strong> [[0]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == mat1.length</code></li>
	<li><code>k == mat1[i].length == mat2.length</code></li>
	<li><code>n == mat2[i].length</code></li>
	<li><code>1 &lt;= m, n, k &lt;= 100</code></li>
	<li><code>-100 &lt;= mat1[i][j], mat2[i][j] &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def multiply(self, mat1: List[List[int]], mat2: List[List[int]]) -> List[List[int]]:
        r1, c1, c2 = len(mat1), len(mat1[0]), len(mat2[0])
        res = [[0] * c2 for _ in range(r1)]
        for i in range(r1):
            for j in range(c2):
                for k in range(c1):
                    res[i][j] += mat1[i][k] * mat2[k][j]
        return res
```

```python
class Solution:
    def multiply(self, mat1: List[List[int]], mat2: List[List[int]]) -> List[List[int]]:
        r1, c1, c2 = len(mat1), len(mat1[0]), len(mat2[0])
        res = [[0] * c2 for _ in range(r1)]
        mp = defaultdict(list)
        for i in range(r1):
            for j in range(c1):
                if mat1[i][j] != 0:
                    mp[i].append(j)
        for i in range(r1):
            for j in range(c2):
                for k in mp[i]:
                    res[i][j] += mat1[i][k] * mat2[k][j]
        return res
```

### **Java**

```java
class Solution {
    public int[][] multiply(int[][] mat1, int[][] mat2) {
        int r1 = mat1.length, c1 = mat1[0].length, c2 = mat2[0].length;
        int[][] res = new int[r1][c2];
        Map<Integer, List<Integer>> mp = new HashMap<>();
        for (int i = 0; i < r1; ++i) {
            for (int j = 0; j < c1; ++j) {
                if (mat1[i][j] != 0) {
                    mp.computeIfAbsent(i, k -> new ArrayList<>()).add(j);
                }
            }
        }
        for (int i = 0; i < r1; ++i) {
            for (int j = 0; j < c2; ++j) {
                if (mp.containsKey(i)) {
                    for (int k : mp.get(i)) {
                        res[i][j] += mat1[i][k] * mat2[k][j];
                    }
                }
            }
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> multiply(vector<vector<int>>& mat1, vector<vector<int>>& mat2) {
        int r1 = mat1.size(), c1 = mat1[0].size(), c2 = mat2[0].size();
        vector<vector<int>> res(r1, vector<int>(c2));
        unordered_map<int, vector<int>> mp;
        for (int i = 0; i < r1; ++i) {
            for (int j = 0; j < c1; ++j) {
                if (mat1[i][j] != 0) mp[i].push_back(j);
            }
        }
        for (int i = 0; i < r1; ++i) {
            for (int j = 0; j < c2; ++j) {
                for (int k : mp[i]) res[i][j] += mat1[i][k] * mat2[k][j];
            }
        }
        return res;
    }
};
```

### **Go**

```go
func multiply(mat1 [][]int, mat2 [][]int) [][]int {
	r1, c1, c2 := len(mat1), len(mat1[0]), len(mat2[0])
	res := make([][]int, r1)
	for i := range res {
		res[i] = make([]int, c2)
	}
	mp := make(map[int][]int)
	for i := 0; i < r1; i++ {
		for j := 0; j < c1; j++ {
			if mat1[i][j] != 0 {
				mp[i] = append(mp[i], j)
			}
		}
	}
	for i := 0; i < r1; i++ {
		for j := 0; j < c2; j++ {
			for _, k := range mp[i] {
				res[i][j] += mat1[i][k] * mat2[k][j]
			}
		}
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
