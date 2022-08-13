# [1428. Leftmost Column with at Least a One](https://leetcode.com/problems/leftmost-column-with-at-least-a-one)

[中文文档](/solution/1400-1499/1428.Leftmost%20Column%20with%20at%20Least%20a%20One/README.md)

## Description

<p>A <strong>row-sorted binary matrix</strong> means that all elements are <code>0</code> or <code>1</code> and each row of the matrix is sorted in non-decreasing order.</p>

<p>Given a <strong>row-sorted binary matrix</strong> <code>binaryMatrix</code>, return <em>the index (0-indexed) of the <strong>leftmost column</strong> with a 1 in it</em>. If such an index does not exist, return <code>-1</code>.</p>

<p><strong>You can&#39;t access the Binary Matrix directly.</strong> You may only access the matrix using a <code>BinaryMatrix</code> interface:</p>

<ul>
	<li><code>BinaryMatrix.get(row, col)</code> returns the element of the matrix at index <code>(row, col)</code> (0-indexed).</li>
	<li><code>BinaryMatrix.dimensions()</code> returns the dimensions of the matrix as a list of 2 elements <code>[rows, cols]</code>, which means the matrix is <code>rows x cols</code>.</li>
</ul>

<p>Submissions making more than <code>1000</code> calls to <code>BinaryMatrix.get</code> will be judged <em>Wrong Answer</em>. Also, any solutions that attempt to circumvent the judge will result in disqualification.</p>

<p>For custom testing purposes, the input will be the entire binary matrix <code>mat</code>. You will not have access to the binary matrix directly.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1428.Leftmost%20Column%20with%20at%20Least%20a%20One/images/untitled-diagram-5.jpg" style="width: 81px; height: 81px;" />
<pre>
<strong>Input:</strong> mat = [[0,0],[1,1]]
<strong>Output:</strong> 0
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1428.Leftmost%20Column%20with%20at%20Least%20a%20One/images/untitled-diagram-4.jpg" style="width: 81px; height: 81px;" />
<pre>
<strong>Input:</strong> mat = [[0,0],[0,1]]
<strong>Output:</strong> 1
</pre>

<p><strong>Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1428.Leftmost%20Column%20with%20at%20Least%20a%20One/images/untitled-diagram-3.jpg" style="width: 81px; height: 81px;" />
<pre>
<strong>Input:</strong> mat = [[0,0],[0,0]]
<strong>Output:</strong> -1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>rows == mat.length</code></li>
	<li><code>cols == mat[i].length</code></li>
	<li><code>1 &lt;= rows, cols &lt;= 100</code></li>
	<li><code>mat[i][j]</code> is either <code>0</code> or <code>1</code>.</li>
	<li><code>mat[i]</code> is sorted in non-decreasing order.</li>
</ul>

## Solutions

Binary search.

<!-- tabs:start -->

### **Python3**

```python
# """
# This is BinaryMatrix's API interface.
# You should not implement it, or speculate about its implementation
# """
# class BinaryMatrix(object):
#    def get(self, row: int, col: int) -> int:
#    def dimensions(self) -> list[]:


class Solution:
    def leftMostColumnWithOne(self, binaryMatrix: 'BinaryMatrix') -> int:
        rows, cols = binaryMatrix.dimensions()
        res = -1
        for row in range(rows):
            left, right = 0, cols - 1
            while left < right:
                mid = (left + right) >> 1
                if binaryMatrix.get(row, mid) == 1:
                    right = mid
                else:
                    left = mid + 1
            if binaryMatrix.get(row, left) == 1:
                if res == -1:
                    res = left
                else:
                    res = min(res, left)
        return res
```

### **Java**

```java
/**
 * // This is the BinaryMatrix's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface BinaryMatrix {
 *     public int get(int row, int col) {}
 *     public List<Integer> dimensions {}
 * };
 */

class Solution {
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> scale = binaryMatrix.dimensions();
        int rows = scale.get(0), cols = scale.get(1);
        int res = -1;
        for (int row = 0; row < rows; ++row) {
            int left = 0, right = cols - 1;
            while (left < right) {
                int mid = (left + right) >> 1;
                if (binaryMatrix.get(row, mid) == 1) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            if (binaryMatrix.get(row, left) == 1) {
                if (res == -1) {
                    res = left;
                } else {
                    res = Math.min(res, left);
                }
            }
        }
        return res;
    }
}
```

### **C++**

```cpp
/**
 * // This is the BinaryMatrix's API interface.
 * // You should not implement it, or speculate about its implementation
 * class BinaryMatrix {
 *   public:
 *     int get(int row, int col);
 *     vector<int> dimensions();
 * };
 */

class Solution {
public:
    int leftMostColumnWithOne(BinaryMatrix& binaryMatrix) {
        vector<int> scale = binaryMatrix.dimensions();
        int rows = scale[0], cols = scale[1];
        int res = -1;
        for (int row = 0; row < rows; ++row) {
            int left = 0, right = cols - 1;
            while (left < right) {
                int mid = left + right >> 1;
                if (binaryMatrix.get(row, mid) == 1) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            if (binaryMatrix.get(row, left) == 1) {
                if (res == -1) {
                    res = left;
                } else {
                    res = min(res, left);
                }
            }
        }
        return res;
    }
};
```

### **Go**

```go
/**
 * // This is the BinaryMatrix's API interface.
 * // You should not implement it, or speculate about its implementation
 * type BinaryMatrix struct {
 *     Get func(int, int) int
 *     Dimensions func() []int
 * }
 */

func leftMostColumnWithOne(binaryMatrix BinaryMatrix) int {
	scale := binaryMatrix.Dimensions()
	rows, cols := scale[0], scale[1]
	res := -1
	for row := 0; row < rows; row++ {
		left, right := 0, cols-1
		for left < right {
			mid := (left + right) >> 1
			if binaryMatrix.Get(row, mid) == 1 {
				right = mid
			} else {
				left = mid + 1
			}
		}
		if binaryMatrix.Get(row, left) == 1 {
			if res == -1 {
				res = left
			} else {
				res = min(res, left)
			}
		}
	}
	return res
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
