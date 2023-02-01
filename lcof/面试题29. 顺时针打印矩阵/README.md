# [面试题 29. 顺时针打印矩阵](https://leetcode.cn/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/)

## 题目描述

<p>输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>matrix = [[1,2,3],[4,5,6],[7,8,9]]
<strong>输出：</strong>[1,2,3,6,9,8,7,4,5]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>matrix =&nbsp;[[1,2,3,4],[5,6,7,8],[9,10,11,12]]
<strong>输出：</strong>[1,2,3,4,8,12,11,10,9,5,6,7]
</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<ul>
	<li><code>0 &lt;= matrix.length &lt;= 100</code></li>
	<li><code>0 &lt;= matrix[i].length&nbsp;&lt;= 100</code></li>
</ul>

<p>注意：本题与主站 54 题相同：<a href="https://leetcode.cn/problems/spiral-matrix/">https://leetcode.cn/problems/spiral-matrix/</a></p>

## 解法

**方法一：模拟**

我们用 $i$ 和 $j$ 分别表示当前访问到的元素的行和列，用 $k$ 表示当前的方向，用数组或哈希表 $vis$ 记录每个元素是否被访问过。每次我们访问到一个元素后，将其标记为已访问，然后按照当前的方向前进一步，如果前进一步后发现越界或者已经访问过，则改变方向继续前进，直到遍历完整个矩阵。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是矩阵的行数和列数。

**方法二：逐层模拟**

从外往里一圈一圈遍历并存储矩阵元素即可。

时间复杂度 $O(m \times n)$，空间复杂度 $O(1)$。其中 $m$ 和 $n$ 分别是矩阵的行数和列数。

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
        if not matrix or not matrix[0]:
            return []
        dirs = (0, 1, 0, -1, 0)
        m, n = len(matrix), len(matrix[0])
        vis = [[False] * n for _ in range(m)]
        ans = []
        i = j = k = 0
        for _ in range(m * n):
            ans.append(matrix[i][j])
            vis[i][j] = True
            x, y = i + dirs[k], j + dirs[k + 1]
            if x < 0 or y < 0 or x >= m or y >= n or vis[x][y]:
                k = (k + 1) % 4
                x, y = i + dirs[k], j + dirs[k + 1]
            i, j = x, y
        return ans
```

```python
class Solution:
    def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
        if not matrix or not matrix[0]:
            return []
        m, n = len(matrix), len(matrix[0])
        ans = []
        top, bottom, left, right = 0, m - 1, 0, n - 1
        while left <= right and top <= bottom:
            ans.extend([matrix[top][j] for j in range(left, right + 1)])
            ans.extend([matrix[i][right] for i in range(top + 1, bottom + 1)])
            if left < right and top < bottom:
                ans.extend([matrix[bottom][j] for j in range(right - 1, left - 1, -1)])
                ans.extend([matrix[i][left] for i in range(bottom - 1, top, -1)])
            top, bottom, left, right = top + 1, bottom - 1, left + 1, right - 1
        return ans
```

### **Java**

```java
class Solution {
    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return new int[] {};
        }
        int m = matrix.length, n = matrix[0].length;
        boolean[][] vis = new boolean[m][n];
        int[] ans = new int[m * n];
        int i = 0, j = 0, k = 0;
        int[] dirs = {0, 1, 0, -1, 0};
        for (int h = 0; h < m * n; ++h) {
            ans[h] = matrix[i][j];
            vis[i][j] = true;
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x < 0 || y < 0 || x >= m || y >= n || vis[x][y]) {
                k = (k + 1) % 4;
                x = i + dirs[k];
                y = j + dirs[k + 1];
            }
            i = x;
            j = y;
        }
        return ans;
    }
}
```

```java
class Solution {
    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return new int[] {};
        }
        int m = matrix.length, n = matrix[0].length;
        int top = 0, bottom = m - 1, left = 0, right = n - 1;
        int[] ans = new int[m * n];
        int k = 0;
        while (left <= right && top <= bottom) {
            for (int j = left; j <= right; ++j) {
                ans[k++] = matrix[top][j];
            }
            for (int i = top + 1; i <= bottom; ++i) {
                ans[k++] = matrix[i][right];
            }
            if (left < right && top < bottom) {
                for (int j = right - 1; j >= left; --j) {
                    ans[k++] = matrix[bottom][j];
                }
                for (int i = bottom - 1; i > top; --i) {
                    ans[k++] = matrix[i][left];
                }
            }
            ++top;
            --bottom;
            ++left;
            --right;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> spiralOrder(vector<vector<int>>& matrix) {
        if (matrix.size() == 0 || matrix[0].size() == 0) {
            return {};
        }
        int m = matrix.size(), n = matrix[0].size();
        bool vis[m][n];
        memset(vis, false, sizeof vis);
        int i = 0, j = 0, k = 0;
        int dirs[5] = {0, 1, 0, -1, 0};
        vector<int> ans(m * n);
        for (int h = 0; h < m * n; ++h) {
            ans[h] = matrix[i][j];
            vis[i][j] = true;
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x < 0 || y < 0 || x >= m || y >= n || vis[x][y]) {
                k = (k + 1) % 4;
                x = i + dirs[k];
                y = j + dirs[k + 1];
            }
            i = x;
            j = y;
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    vector<int> spiralOrder(vector<vector<int>>& matrix) {
        if (matrix.size() == 0 || matrix[0].size() == 0) {
            return {};
        }
        int m = matrix.size(), n = matrix[0].size();
        int top = 0, bottom = m - 1, left = 0, right = n - 1;
        vector<int> ans;
        while (top <= bottom && left <= right) {
            for (int j = left; j <= right; ++j) ans.push_back(matrix[top][j]);
            for (int i = top + 1; i <= bottom; ++i) ans.push_back(matrix[i][right]);
            if (left < right && top < bottom) {
                for (int j = right - 1; j >= left; --j) ans.push_back(matrix[bottom][j]);
                for (int i = bottom - 1; i > top; --i) ans.push_back(matrix[i][left]);
            }
            ++top;
            --bottom;
            ++left;
            --right;
        }
        return ans;
    }
};
```

### **Go**

```go
func spiralOrder(matrix [][]int) []int {
	if len(matrix) == 0 || len(matrix[0]) == 0 {
		return []int{}
	}
	m, n := len(matrix), len(matrix[0])
	vis := make([][]bool, m)
	for i := range vis {
		vis[i] = make([]bool, n)
	}
	i, j, k := 0, 0, 0
	dirs := [5]int{0, 1, 0, -1, 0}
	ans := make([]int, m*n)
	for h := 0; h < m*n; h++ {
		ans[h] = matrix[i][j]
		vis[i][j] = true
		x, y := i+dirs[k], j+dirs[k+1]
		if x < 0 || y < 0 || x >= m || y >= n || vis[x][y] {
			k = (k + 1) % 4
			x, y = i+dirs[k], j+dirs[k+1]
		}
		i, j = x, y
	}
	return ans
}
```

```go
func spiralOrder(matrix [][]int) []int {
	if len(matrix) == 0 || len(matrix[0]) == 0 {
		return []int{}
	}
	m, n := len(matrix), len(matrix[0])
	ans := make([]int, 0, m*n)

	top, bottom, left, right := 0, m-1, 0, n-1
	for left <= right && top <= bottom {
		for i := left; i <= right; i++ {
			ans = append(ans, matrix[top][i])
		}
		for i := top + 1; i <= bottom; i++ {
			ans = append(ans, matrix[i][right])
		}
		if left < right && top < bottom {
			for i := right - 1; i >= left; i-- {
				ans = append(ans, matrix[bottom][i])
			}
			for i := bottom - 1; i > top; i-- {
				ans = append(ans, matrix[i][left])
			}
		}
		top++
		bottom--
		left++
		right--
	}

	return ans
}
```

### **JavaScript**

```js
/**
 * @param {number[][]} matrix
 * @return {number[]}
 */
var spiralOrder = function (matrix) {
    const ans = [];
    if (matrix.length == 0 || matrix[0].length == 0) {
        return ans;
    }
    const m = matrix.length;
    const n = matrix[0].length;
    let [top, bottom, left, right] = [0, m - 1, 0, n - 1];
    while (top <= bottom && left <= right) {
        for (let j = left; j <= right; ++j) {
            ans.push(matrix[top][j]);
        }
        for (let i = top + 1; i <= bottom; ++i) {
            ans.push(matrix[i][right]);
        }
        if (left < right && top < bottom) {
            for (let j = right - 1; j >= left; --j) {
                ans.push(matrix[bottom][j]);
            }
            for (let i = bottom - 1; i > top; --i) {
                ans.push(matrix[i][left]);
            }
        }
        [top, bottom, left, right] = [top + 1, bottom - 1, left + 1, right - 1];
    }
    return ans;
};
```

### **C#**

```cs
public class Solution {
    public int[] SpiralOrder(int[][] matrix) {
        List<int> ans = new List<int>();
        if (matrix.Length == 0) {
            return ans.ToArray();
        }
        int left = 0, top = 0, bottom = matrix.Length - 1, right = matrix[0].Length - 1;
        while (true) {
            for (int i = left; i <= right; i++) {
                ans.Add(matrix[top][i]);
            }
            top += 1;
            if (top > bottom) {
                break;
            }
            for (int i = top; i <= bottom; i++) {
                ans.Add(matrix[i][right]);
            }
            right -= 1;
            if (right < left) {
                break;
            }
            for (int i = right; i >= left; i--) {
                ans.Add(matrix[bottom][i]);
            }
            bottom -= 1;
            if (bottom < top) {
                break;
            }
            for (int i = bottom; i >= top; i--) {
                ans.Add(matrix[i][left]);
            }
            left += 1;
            if (left > right) {
                break;
            }
        }
        return ans.ToArray();
    }
}
```

### **...**

```

```

<!-- tabs:end -->
