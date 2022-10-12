# [1975. Maximum Matrix Sum](https://leetcode.com/problems/maximum-matrix-sum)

[中文文档](/solution/1900-1999/1975.Maximum%20Matrix%20Sum/README.md)

## Description

<p>You are given an <code>n x n</code> integer <code>matrix</code>. You can do the following operation <strong>any</strong> number of times:</p>

<ul>
	<li>Choose any two <strong>adjacent</strong> elements of <code>matrix</code> and <strong>multiply</strong> each of them by <code>-1</code>.</li>
</ul>

<p>Two elements are considered <strong>adjacent</strong> if and only if they share a <strong>border</strong>.</p>

<p>Your goal is to <strong>maximize</strong> the summation of the matrix&#39;s elements. Return <em>the <strong>maximum</strong> sum of the matrix&#39;s elements using the operation mentioned above.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1975.Maximum%20Matrix%20Sum/images/pc79-q2ex1.png" style="width: 401px; height: 81px;" />
<pre>
<strong>Input:</strong> matrix = [[1,-1],[-1,1]]
<strong>Output:</strong> 4
<b>Explanation:</b> We can follow the following steps to reach sum equals 4:
- Multiply the 2 elements in the first row by -1.
- Multiply the 2 elements in the first column by -1.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1975.Maximum%20Matrix%20Sum/images/pc79-q2ex2.png" style="width: 321px; height: 121px;" />
<pre>
<strong>Input:</strong> matrix = [[1,2,3],[-1,-2,-3],[1,2,3]]
<strong>Output:</strong> 16
<b>Explanation:</b> We can follow the following step to reach sum equals 16:
- Multiply the 2 last elements in the second row by -1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == matrix.length == matrix[i].length</code></li>
	<li><code>2 &lt;= n &lt;= 250</code></li>
	<li><code>-10<sup>5</sup> &lt;= matrix[i][j] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxMatrixSum(self, matrix: List[List[int]]) -> int:
        s = cnt = 0
        mi = inf
        for row in matrix:
            for v in row:
                s += abs(v)
                mi = min(mi, abs(v))
                if v < 0:
                    cnt += 1
        if cnt % 2 == 0 or mi == 0:
            return s
        return s - mi * 2
```

### **Java**

```java
class Solution {
    public long maxMatrixSum(int[][] matrix) {
        long s = 0;
        int cnt = 0;
        int mi = Integer.MAX_VALUE;
        for (var row : matrix) {
            for (var v : row) {
                s += Math.abs(v);
                mi = Math.min(mi, Math.abs(v));
                if (v < 0) {
                    ++cnt;
                }
            }
        }
        if (cnt % 2 == 0 || mi == 0) {
            return s;
        }
        return s - mi * 2;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long maxMatrixSum(vector<vector<int>>& matrix) {
        long long s = 0;
        int cnt = 0, mi = INT_MAX;
        for (auto& row : matrix) {
            for (int& v : row) {
                s += abs(v);
                mi = min(mi, abs(v));
                cnt += v < 0;
            }
        }
        if (cnt % 2 == 0 || mi == 0) return s;
        return s - mi * 2;
    }
};
```

### **Go**

```go
func maxMatrixSum(matrix [][]int) int64 {
	s := 0
	cnt, mi := 0, math.MaxInt32
	for _, row := range matrix {
		for _, v := range row {
			s += abs(v)
			mi = min(mi, abs(v))
			if v < 0 {
				cnt++
			}
		}
	}
	if cnt%2 == 1 {
		s -= mi * 2
	}
	return int64(s)
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **JavaScript**

```js
/**
 * @param {number[][]} matrix
 * @return {number}
 */
var maxMatrixSum = function (matrix) {
    let cnt = 0;
    let s = 0;
    let mi = Infinity;
    for (const row of matrix) {
        for (const v of row) {
            s += Math.abs(v);
            mi = Math.min(mi, Math.abs(v));
            cnt += v < 0;
        }
    }
    if (cnt % 2 == 0) {
        return s;
    }
    return s - mi * 2;
};
```

### **...**

```

```

<!-- tabs:end -->
