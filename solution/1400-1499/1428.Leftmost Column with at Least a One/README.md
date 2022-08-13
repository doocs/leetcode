# [1428. 至少有一个 1 的最左端列](https://leetcode.cn/problems/leftmost-column-with-at-least-a-one)

[English Version](/solution/1400-1499/1428.Leftmost%20Column%20with%20at%20Least%20a%20One/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><em>（这是一个<strong>交互题</strong>）</em></p>

<p>我们称只包含元素&nbsp;<code>0</code>&nbsp;或&nbsp;<code>1</code>&nbsp;的矩阵为二进制矩阵。矩阵中每个<strong>单独</strong>的行都按非递减顺序排序。</p>

<p>给定一个这样的二进制矩阵，返回至少包含一个&nbsp;<code>1</code>&nbsp;的最左端列的索引（从 0 开始）。如果这样的列不存在，返回&nbsp;<code>-1</code>。</p>

<p><strong>您不能直接访问该二进制矩阵。</strong>你只可以通过&nbsp;<code>BinaryMatrix</code>&nbsp;接口来访问。</p>

<ul>
	<li><code>BinaryMatrix.get(row, col)</code>&nbsp;返回位于索引&nbsp;<code>(row, col)</code>&nbsp;（从 0 开始）的元素。</li>
	<li><code>BinaryMatrix.dimensions()</code>&nbsp;返回含有 2 个元素的列表&nbsp;<code>[rows, cols]</code>，表示这是一个&nbsp;<code>rows *&nbsp;cols</code>的矩阵。</li>
</ul>

<p>如果提交的答案调用&nbsp;<code>BinaryMatrix.get</code>&nbsp;超过 <code>1000</code> 次，则该答案会被判定为<em>错误答案</em>。提交任何试图规避判定机制的答案将会被取消资格。</p>

<p>下列示例中，&nbsp;<code>mat</code>&nbsp;为给定的二进制矩阵。您不能直接访问该矩阵。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1428.Leftmost%20Column%20with%20at%20Least%20a%20One/images/untitled-diagram-5.jpg" style="height:81px; width:81px" /></strong></p>

<pre>
<strong>输入:</strong> mat = [[0,0],[1,1]]
<strong>输出:</strong> 0
</pre>

<p><strong>示例 2:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1428.Leftmost%20Column%20with%20at%20Least%20a%20One/images/untitled-diagram-4.jpg" style="height:81px; width:81px" /></strong></p>

<pre>
<strong>输入:</strong> mat = [[0,0],[0,1]]
<strong>输出:</strong> 1
</pre>

<p><strong>示例 3:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1428.Leftmost%20Column%20with%20at%20Least%20a%20One/images/untitled-diagram-3.jpg" style="height:81px; width:81px" /></strong></p>

<pre>
<strong>输入:</strong> mat = [[0,0],[0,0]]
<strong>输出:</strong> -1</pre>

<p><strong>示例 4:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1428.Leftmost%20Column%20with%20at%20Least%20a%20One/images/untitled-diagram-6.jpg" style="height:121px; width:161px" /></strong></p>

<pre>
<strong>输入:</strong> mat = [[0,0,0,1],[0,0,1,1],[0,1,1,1]]
<strong>输出:</strong> 1
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>rows == mat.length</code></li>
	<li><code>cols == mat[i].length</code></li>
	<li><code>1 &lt;= rows, cols&nbsp;&lt;= 100</code></li>
	<li><code>mat[i][j]</code>&nbsp;只会是&nbsp;<code>0</code>&nbsp;或&nbsp;<code>1</code>。</li>
	<li><code>mat[i]</code>&nbsp;已按非递减顺序排序。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

二分查找。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
