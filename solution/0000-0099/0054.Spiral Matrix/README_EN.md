# [54. Spiral Matrix](https://leetcode.com/problems/spiral-matrix)

[中文文档](/solution/0000-0099/0054.Spiral%20Matrix/README.md)

## Description

<p>Given an <code>m x n</code> <code>matrix</code>, return <em>all elements of the</em> <code>matrix</code> <em>in spiral order</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0054.Spiral%20Matrix/images/spiral1.jpg" style="width: 242px; height: 242px;" />
<pre>
<strong>Input:</strong> matrix = [[1,2,3],[4,5,6],[7,8,9]]
<strong>Output:</strong> [1,2,3,6,9,8,7,4,5]
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0054.Spiral%20Matrix/images/spiral.jpg" style="width: 322px; height: 242px;" />
<pre>
<strong>Input:</strong> matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
<strong>Output:</strong> [1,2,3,4,8,12,11,10,9,5,6,7]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == matrix.length</code></li>
	<li><code>n == matrix[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 10</code></li>
	<li><code>-100 &lt;= matrix[i][j] &lt;= 100</code></li>
</ul>

## Solutions

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
            return [matrix[i1][j] for j in range(j1, j2)] + [matrix[i][j2] for i in range(i1, i2)] + [matrix[i2][j] for j in range(j2, j1, -1)] + [matrix[i][j1] for i in range(i2, i1, -1)]

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
    private List<Integer> res;

    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        res = new ArrayList<>();
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
                res.add(matrix[i1][j]);
            }
            return;
        }
        if (j1 == j2) {
            for (int i = i1; i <= i2; ++i) {
                res.add(matrix[i][j1]);
            }
            return;
        }
        for (int j = j1; j < j2; ++j) {
            res.add(matrix[i1][j]);
        }
        for (int i = i1; i < i2; ++i) {
            res.add(matrix[i][j2]);
        }
        for (int j = j2; j > j1; --j) {
            res.add(matrix[i2][j]);
        }
        for (int i = i2; i > i1; --i) {
            res.add(matrix[i][j1]);
        }
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[][]} matrix
 * @return {number[]}
 */
var spiralOrder = function (matrix) {
    let m = matrix.length;
    if (m === 0) return [];
    let res = [];
    let top = 0,
        bottom = m - 1,
        left = 0,
        right = matrix[0].length - 1;
    while (left < right && bottom > top) {
        for (let i = left; i < right; i++) res.push(matrix[top][i]);
        for (let i = top; i < bottom; i++) res.push(matrix[i][right]);
        for (let i = right; i > left; i--) res.push(matrix[bottom][i]);
        for (let i = bottom; i > top; i--) res.push(matrix[i][left]);
        top++;
        bottom--;
        left++;
        right--;
    }
    if (left === right) {
        for (i = top; i <= bottom; i++) res.push(matrix[i][left]);
    } else if (top === bottom) {
        for (i = left; i <= right; i++) res.push(matrix[top][i]);
    }
    return res;
};
```

### **Go**

```go
func spiralOrder(matrix [][]int) []int {
	if len(matrix) == 0 {
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

### **C++**

```cpp
class Solution {
public:
    vector<int> spiralOrder(vector<vector<int>>& matrix) {
        int row=matrix.size();
        if(row==0)
        {
            vector<int> zero;
            zero.clear();
            return zero;
        }
        int col=matrix[0].size();
        if(row==1)
            return matrix[0];
        if(col==0)
        {
            vector<int> zero;
            zero.clear();
            return zero;
        }
        if(col==1)
        {
            vector<int> temp;
            for(int i=0;i<row;i++)
                temp.push_back(matrix[i][0]);
            return temp;
        }
        vector<int> result;
        result=matrix[0];//result存储第一行
        //temp=matirx.pop_back();
        for(int i=1;i<matrix.size()-1;i++)
        {
            result.push_back(matrix[i][matrix[0].size()-1]);//存储每行最后一个
        }
        for(int i=0;i<col;i++)
        {
            result.push_back(matrix[row-1][col-1-i]);//倒序存储最后一行
        }
        for(int i=1;i<row-1;i++)
        {
            result.push_back(matrix[row-i-1][0]);//倒序存储每一行第一个
            vector<int> zz(matrix[row-i-1].begin()+1,matrix[row-i-1].end()-1);//将中间行去除第一个和最后一个数
            matrix[row-i-1]=zz;
        }
        vector<vector<int>> m2(matrix.begin()+1,matrix.end()-1);//将matrix去除第一行和最后一行，递归调用
        //cout<<m2.size()<<"  "<<m2[0].size();
        vector<int> l=spiralOrder(m2);
        result.insert(result.end(),l.begin(),l.end());//将递归结果插入result后面
        return result;
        
    }
};
```

### **C#**

```cs
using System;
using System.Collections.Generic;

public class Solution {
    public IList<int> SpiralOrder(int[][] matrix) {
        var lenI = matrix.Length;
        var lenJ = lenI == 0 ? 0 : matrix[0].Length;
        var result = new List<int>(lenI * lenJ);
        var rounds = (Math.Min(lenI, lenJ) + 1) / 2;
        for (var r = 0; r < rounds; ++r)
        {
            if (lenI - r * 2 == 1)
            {
                for (var j = r; j < lenJ - r; ++j)
                {
                    result.Add(matrix[r][j]);
                }
            }
            else if (lenJ - r * 2 == 1)
            {
                for (var i = r; i < lenI - r; ++i)
                {
                    result.Add(matrix[i][r]);
                }
            }
            else
            {
                for (var j = r; j < lenJ - r - 1; ++j)
                {
                    result.Add(matrix[r][j]);
                }
                for (var i = r; i < lenI - r - 1; ++i)
                {
                    result.Add(matrix[i][lenJ - r - 1]);
                }
                for (var j = lenJ - r - 1; j > r; --j)
                {
                    result.Add(matrix[lenI - r - 1][j]);
                }
                for (var i = lenI - r - 1; i > r; --i)
                {
                    result.Add(matrix[i][r]);
                }
            }
        }
        return result;
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
