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

从外往里一圈一圈遍历并存储矩阵元素即可。

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
        def add(i1, j1, i2, j2):
            if i1 == i2:
                return [matrix[i1][j] for j in range(j1, j2 + 1)]
            if j1 == j2:
                return [matrix[i][j1] for i in range(i1, i2 + 1)]
            return (
                [matrix[i1][j] for j in range(j1, j2)]
                + [matrix[i][j2] for i in range(i1, i2)]
                + [matrix[i2][j] for j in range(j2, j1, -1)]
                + [matrix[i][j1] for i in range(i2, i1, -1)]
            )

        if not matrix or not matrix[0]:
            return []
        m, n = len(matrix), len(matrix[0])
        i1, j1, i2, j2 = 0, 0, m - 1, n - 1
        res = []
        while i1 <= i2 and j1 <= j2:
            res += add(i1, j1, i2, j2)
            i1, j1, i2, j2 = i1 + 1, j1 + 1, i2 - 1, j2 - 1
        return res
```

### **Java**

```java
class Solution {
    private int[] res;
    private int index;

    public int[] spiralOrder(int[][] matrix) {
        int m, n;
        if (matrix == null || (m = matrix.length) == 0 || matrix[0] == null || (n = matrix[0].length) == 0)
            return new int[]{};
        res = new int[m * n];
        index = 0;
        int i1 = 0, i2 = m - 1;
        int j1 = 0, j2 = n - 1;
        while (i1 <= i2 && j1 <= j2) {
            add(matrix, i1++, j1++, i2--, j2--);
        }
        return res;
    }

    private void add(int[][] matrix, int i1, int j1, int i2, int j2) {
        if (i1 == i2) {
            for (int j = j1; j <= j2; ++j) {
                res[index++] = matrix[i1][j];
            }
            return;
        }
        if (j1 == j2) {
            for (int i = i1; i <= i2; ++i) {
                res[index++] = matrix[i][j1];
            }
            return;
        }
        for (int j = j1; j < j2; ++j) {
            res[index++] = matrix[i1][j];
        }
        for (int i = i1; i < i2; ++i) {
            res[index++] = matrix[i][j2];
        }
        for (int j = j2; j > j1; --j) {
            res[index++] = matrix[i2][j];
        }
        for (int i = i2; i > i1; --i) {
            res[index++] = matrix[i][j1];
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> spiralOrder(vector<vector<int>>& matrix) {
        vector<int> ans;
        if (matrix.size() == 0)
            return ans;
        int left = 0, top = 0, bottom = matrix.size() - 1, right = matrix[0].size() - 1;
        while (true) {
            for (int i = left; i <= right; i++)
                ans.push_back(matrix[top][i]);
            top++;
            if (top > bottom)
                break;
            for (int i = top; i <= bottom; i++)
                ans.push_back(matrix[i][right]);
            right--;
            if (right < left)
                break;
            for (int i = right; i >= left; i--)
                ans.push_back(matrix[bottom][i]);
            bottom--;
            if (bottom < top)
                break;
            for (int i = bottom; i >= top; i--)
                ans.push_back(matrix[i][left]);
            left++;
            if (left > right)
                break;
        }
        return ans;
    }
};
```

### **JavaScript**

```js
/**
 * @param {number[][]} matrix
 * @return {number[]}
 */
var spiralOrder = function (matrix) {
    if (!matrix || !matrix.length) return [];
    let row = matrix.length;
    let col = matrix[0].length;
    let res = [];
    let moves = {
        right: [0, 1],
        down: [1, 0],
        left: [0, -1],
        up: [-1, 0],
    };
    let k = 0;
    function dfs(i, j, dir) {
        if (
            i < 0 ||
            j < 0 ||
            i >= row ||
            j >= col ||
            res.length === row * col
        ) {
            return;
        }
        res.push(matrix[i][j]);
        switch (dir) {
            case 'right':
                if (j === col - 1 - k) dir = 'down';
                break;
            case 'down':
                if (i === row - 1 - k) dir = 'left';
                break;
            case 'left':
                if (j === k) {
                    dir = 'up';
                    k++;
                }
                break;
            case 'up':
                if (i === k) dir = 'right';
                break;
        }
        let x = i + moves[dir][0];
        let y = j + moves[dir][1];
        dfs(x, y, dir);
    }
    dfs(0, 0, 'right');
    return res;
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
