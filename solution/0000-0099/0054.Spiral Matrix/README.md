# [54. 螺旋矩阵](https://leetcode.cn/problems/spiral-matrix)

[English Version](/solution/0000-0099/0054.Spiral%20Matrix/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <code>m</code> 行 <code>n</code> 列的矩阵 <code>matrix</code> ，请按照 <strong>顺时针螺旋顺序</strong> ，返回矩阵中的所有元素。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0054.Spiral%20Matrix/images/spiral1.jpg" style="width: 242px; height: 242px;" />
<pre>
<strong>输入：</strong>matrix = [[1,2,3],[4,5,6],[7,8,9]]
<strong>输出：</strong>[1,2,3,6,9,8,7,4,5]
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0054.Spiral%20Matrix/images/spiral.jpg" style="width: 322px; height: 242px;" />
<pre>
<strong>输入：</strong>matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
<strong>输出：</strong>[1,2,3,4,8,12,11,10,9,5,6,7]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == matrix.length</code></li>
	<li><code>n == matrix[i].length</code></li>
	<li><code>1 <= m, n <= 10</code></li>
	<li><code>-100 <= matrix[i][j] <= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：逐层模拟**

从外往里一圈一圈遍历并存储矩阵元素即可。

时间复杂度 $O(m \times n)$，空间复杂度 $O(1)$。其中 $m$ 和 $n$ 分别是矩阵的行数和列数。

**方法二：模拟**

我们用 $i$ 和 $j$ 分别表示当前访问到的元素的行和列，用 $k$ 表示当前的方向，用数组或哈希表 $vis$ 记录每个元素是否被访问过。每次我们访问到一个元素后，将其标记为已访问，然后按照当前的方向前进一步，如果前进一步后发现越界或者已经访问过，则改变方向继续前进，直到遍历完整个矩阵。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是矩阵的行数和列数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
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

```python
class Solution:
    def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
        m, n = len(matrix), len(matrix[0])
        dirs = ((0, 1), (1, 0), (0, -1), (-1, 0))
        i = j = k = 0
        ans = []
        vis = [[False] * n for _ in range(m)]
        for _ in range(m * n):
            ans.append(matrix[i][j])
            vis[i][j] = True
            x, y = i + dirs[k][0], j + dirs[k][1]
            if x < 0 or y < 0 or x >= m or y >= n or vis[x][y]:
                k = (k + 1) % 4
                x, y = i + dirs[k][0], j + dirs[k][1]
            i, j = x, y
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int top = 0, bottom = m - 1, left = 0, right = n - 1;
        List<Integer> ans = new ArrayList<>();
        while (left <= right && top <= bottom) {
            for (int j = left; j <= right; ++j) {
                ans.add(matrix[top][j]);
            }
            for (int i = top + 1; i <= bottom; ++i) {
                ans.add(matrix[i][right]);
            }
            if (left < right && top < bottom) {
                for (int j = right - 1; j >= left; --j) {
                    ans.add(matrix[bottom][j]);
                }
                for (int i = bottom - 1; i > top; --i) {
                    ans.add(matrix[i][left]);
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

```java
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int i = 0, j = 0, k = 0;
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        List<Integer> ans = new ArrayList<>();
        boolean[][] vis = new boolean[m][n];
        for (int h = 0; h < m * n; ++h) {
            ans.add(matrix[i][j]);
            vis[i][j] = true;
            int x = i + dirs[k][0], y = j + dirs[k][1];
            if (x < 0 || y < 0 || x >= m || y >= n || vis[x][y]) {
                k = (k + 1) % 4;
                x = i + dirs[k][0];
                y = j + dirs[k][1];
            }
            i = x;
            j = y;
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

```cpp
class Solution {
public:
    const int dirs[4][2] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    vector<int> spiralOrder(vector<vector<int>>& matrix) {
        int m = matrix.size(), n = matrix[0].size();
        int i = 0, j = 0, k = 0;
        vector<int> ans;
        bool vis[11][11] = {0};
        for (int h = 0; h < m * n; ++h) {
            ans.push_back(matrix[i][j]);
            vis[i][j] = 1;
            int x = i + dirs[k][0], y = j + dirs[k][1];
            if (x < 0 || x >= m || y < 0 || y >= n || vis[x][y]) {
                k = (k + 1) % 4;
                x = i + dirs[k][0], y = j + dirs[k][1];
            }
            i = x, j = y;
        }
        return ans;
    }
};
```

### **Go**

```go
func spiralOrder(matrix [][]int) []int {
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

```go
func spiralOrder(matrix [][]int) (ans []int) {
	m, n := len(matrix), len(matrix[0])
	var i, j, k int
	dirs := [4][2]int{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}
	vis := [11][11]bool{}
	for h := 0; h < m*n; h++ {
		ans = append(ans, matrix[i][j])
		vis[i][j] = true
		x, y := i+dirs[k][0], j+dirs[k][1]
		if x < 0 || x >= m || y < 0 || y >= n || vis[x][y] {
			k = (k + 1) % 4
			x, y = i+dirs[k][0], j+dirs[k][1]
		}
		i, j = x, y
	}
	return
}
```

### **JavaScript**

```js
/**
 * @param {number[][]} matrix
 * @return {number[]}
 */
var spiralOrder = function (matrix) {
    const m = matrix.length;
    const n = matrix[0].length;
    let [top, bottom, left, right] = [0, m - 1, 0, n - 1];
    let ans = [];
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
    public IList<int> SpiralOrder(int[][] matrix) {
        int m = matrix.Length;
        int n = matrix[0].Length;
        int top = 0, bottom = m - 1, left = 0, right = n - 1;
        var ans = new List<int>(m * n);
        while (top <= bottom && left <= right)
        {
            for (int j = left; j <= right; ++j)
            {
                ans.Add(matrix[top][j]);
            }
            for (int i = top + 1; i <= bottom; ++i)
            {
                ans.Add(matrix[i][right]);
            }
            if (left < right && top < bottom)
            {
                for (int j = right - 1; j >= left; --j)
                {
                    ans.Add(matrix[bottom][j]);
                }
                for (int i = bottom - 1; i > top; --i)
                {
                    ans.Add(matrix[i][left]);
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

### **TypeScript**

```ts
function spiralOrder(matrix: number[][]): number[] {
    const m = matrix.length;
    const n = matrix[0].length;
    const res = [];
    for (let i = 0; i <= m >> 1; i++) {
        for (let j = i; j < n - i - 1; j++) {
            res.push(matrix[i][j]);
        }
        for (let j = i; j < m - i - 1; j++) {
            res.push(matrix[j][n - i - 1]);
        }
        for (let j = i; j < n - i - 1; j++) {
            res.push(matrix[m - i - 1][n - j - 1]);
        }
        for (let j = i; j < m - i - 1; j++) {
            res.push(matrix[m - j - 1][i]);
        }
    }
    if (m & 1) {
        res.push(matrix[m >> 1][n >> 1]);
    }
    return res.slice(0, m * n);
}
```

### **...**

```

```

<!-- tabs:end -->
