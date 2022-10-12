# [1738. Find Kth Largest XOR Coordinate Value](https://leetcode.com/problems/find-kth-largest-xor-coordinate-value)

[中文文档](/solution/1700-1799/1738.Find%20Kth%20Largest%20XOR%20Coordinate%20Value/README.md)

## Description

<p>You are given a 2D <code>matrix</code> of size <code>m x n</code>, consisting of non-negative integers. You are also given an integer <code>k</code>.</p>

<p>The <strong>value</strong> of coordinate <code>(a, b)</code> of the matrix is the XOR of all <code>matrix[i][j]</code> where <code>0 &lt;= i &lt;= a &lt; m</code> and <code>0 &lt;= j &lt;= b &lt; n</code> <strong>(0-indexed)</strong>.</p>

<p>Find the <code>k<sup>th</sup></code> largest value <strong>(1-indexed)</strong> of all the coordinates of <code>matrix</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> matrix = [[5,2],[1,6]], k = 1
<strong>Output:</strong> 7
<strong>Explanation:</strong> The value of coordinate (0,1) is 5 XOR 2 = 7, which is the largest value.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> matrix = [[5,2],[1,6]], k = 2
<strong>Output:</strong> 5
<strong>Explanation:</strong> The value of coordinate (0,0) is 5 = 5, which is the 2nd largest value.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> matrix = [[5,2],[1,6]], k = 3
<strong>Output:</strong> 4
<strong>Explanation:</strong> The value of coordinate (1,0) is 5 XOR 1 = 4, which is the 3rd largest value.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == matrix.length</code></li>
	<li><code>n == matrix[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 1000</code></li>
	<li><code>0 &lt;= matrix[i][j] &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= k &lt;= m * n</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def kthLargestValue(self, matrix: List[List[int]], k: int) -> int:
        m, n = len(matrix), len(matrix[0])
        s = [[0] * (n + 1) for _ in range(m + 1)]
        ans = []
        for i in range(m):
            for j in range(n):
                s[i + 1][j + 1] = s[i + 1][j] ^ s[i][j + 1] ^ s[i][j] ^ matrix[i][j]
                ans.append(s[i + 1][j + 1])
        return nlargest(k, ans)[-1]
```

### **Java**

```java
class Solution {

    public int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int[][] s = new int[m + 1][n + 1];
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                s[i + 1][j + 1] = s[i + 1][j] ^ s[i][j + 1] ^ s[i][j] ^ matrix[i][j];
                ans.add(s[i + 1][j + 1]);
            }
        }
        Collections.sort(ans);
        return ans.get(ans.size() - k);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int kthLargestValue(vector<vector<int>>& matrix, int k) {
        int m = matrix.size(), n = matrix[0].size();
        vector<vector<int>> s(m + 1, vector<int>(n + 1));
        vector<int> ans;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                s[i + 1][j + 1] = s[i + 1][j] ^ s[i][j + 1] ^ s[i][j] ^ matrix[i][j];
                ans.push_back(s[i + 1][j + 1]);
            }
        }
        sort(ans.begin(), ans.end());
        return ans[ans.size() - k];
    }
};
```

### **Go**

```go
func kthLargestValue(matrix [][]int, k int) int {
	m, n := len(matrix), len(matrix[0])
	s := make([][]int, m+1)
	for i := range s {
		s[i] = make([]int, n+1)
	}
	var ans []int
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			s[i+1][j+1] = s[i+1][j] ^ s[i][j+1] ^ s[i][j] ^ matrix[i][j]
			ans = append(ans, s[i+1][j+1])
		}
	}
	sort.Ints(ans)
	return ans[len(ans)-k]
}
```

### **...**

```

```

<!-- tabs:end -->
