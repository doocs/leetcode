# [1314. 矩阵区域和](https://leetcode.cn/problems/matrix-block-sum)

[English Version](/solution/1300-1399/1314.Matrix%20Block%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <code>m x n</code> 的矩阵 <code>mat</code> 和一个整数 <code>k</code> ，请你返回一个矩阵 <code>answer</code> ，其中每个 <code>answer[i][j]</code> 是所有满足下述条件的元素 <code>mat[r][c]</code> 的和： </p>

<ul>
	<li><code>i - k <= r <= i + k, </code></li>
	<li><code>j - k <= c <= j + k</code> 且</li>
	<li><code>(r, c)</code> 在矩阵内。</li>
</ul>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>mat = [[1,2,3],[4,5,6],[7,8,9]], k = 1
<strong>输出：</strong>[[12,21,16],[27,45,33],[24,39,28]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>mat = [[1,2,3],[4,5,6],[7,8,9]], k = 2
<strong>输出：</strong>[[45,45,45],[45,45,45],[45,45,45]]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == mat.length</code></li>
	<li><code>n == mat[i].length</code></li>
	<li><code>1 <= m, n, k <= 100</code></li>
	<li><code>1 <= mat[i][j] <= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

动态规划-二维前缀和。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def matrixBlockSum(self, mat: List[List[int]], k: int) -> List[List[int]]:
        m, n = len(mat), len(mat[0])
        pre = [[0] * (n + 1) for _ in range(m + 1)]
        for i in range(1, m + 1):
            for j in range(1, n + 1):
                pre[i][j] = (
                    pre[i - 1][j]
                    + pre[i][j - 1]
                    - pre[i - 1][j - 1]
                    + mat[i - 1][j - 1]
                )

        def get(i, j):
            i = max(min(m, i), 0)
            j = max(min(n, j), 0)
            return pre[i][j]

        ans = [[0] * n for _ in range(m)]
        for i in range(m):
            for j in range(n):
                ans[i][j] = (
                    get(i + k + 1, j + k + 1)
                    - get(i + k + 1, j - k)
                    - get(i - k, j + k + 1)
                    + get(i - k, j - k)
                )
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[][] pre;
    private int m;
    private int n;
    public int[][] matrixBlockSum(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        int[][] pre = new int[m + 1][n + 1];
        for (int i = 1; i < m + 1; ++i) {
            for (int j = 1; j < n + 1; ++j) {
                pre[i][j] = pre[i - 1][j] + pre[i][j - 1] + -pre[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }
        this.pre = pre;
        this.m = m;
        this.n = n;
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans[i][j] = get(i + k + 1, j + k + 1) - get(i + k + 1, j - k)
                    - get(i - k, j + k + 1) + get(i - k, j - k);
            }
        }
        return ans;
    }

    private int get(int i, int j) {
        i = Math.max(Math.min(m, i), 0);
        j = Math.max(Math.min(n, j), 0);
        return pre[i][j];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> matrixBlockSum(vector<vector<int>>& mat, int k) {
        int m = mat.size(), n = mat[0].size();
        vector<vector<int>> pre(m + 1, vector<int>(n + 1));
        for (int i = 1; i < m + 1; ++i) {
            for (int j = 1; j < n + 1; ++j) {
                pre[i][j] = pre[i - 1][j] + pre[i][j - 1] + -pre[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }
        vector<vector<int>> ans(m, vector<int>(n));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans[i][j] = get(i + k + 1, j + k + 1, m, n, pre) - get(i + k + 1, j - k, m, n, pre) - get(i - k, j + k + 1, m, n, pre) + get(i - k, j - k, m, n, pre);
            }
        }
        return ans;
    }

    int get(int i, int j, int m, int n, vector<vector<int>>& pre) {
        i = max(min(m, i), 0);
        j = max(min(n, j), 0);
        return pre[i][j];
    }
};
```

### **Go**

```go
func matrixBlockSum(mat [][]int, k int) [][]int {
	m, n := len(mat), len(mat[0])
	pre := make([][]int, m+1)
	for i := 0; i < m+1; i++ {
		pre[i] = make([]int, n+1)
	}
	for i := 1; i < m+1; i++ {
		for j := 1; j < n+1; j++ {
			pre[i][j] = pre[i-1][j] + pre[i][j-1] + -pre[i-1][j-1] + mat[i-1][j-1]
		}
	}

	get := func(i, j int) int {
		i = max(min(m, i), 0)
		j = max(min(n, j), 0)
		return pre[i][j]
	}

	ans := make([][]int, m)
	for i := 0; i < m; i++ {
		ans[i] = make([]int, n)
		for j := 0; j < n; j++ {
			ans[i][j] = get(i+k+1, j+k+1) - get(i+k+1, j-k) - get(i-k, j+k+1) + get(i-k, j-k)
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
