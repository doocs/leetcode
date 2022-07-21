# [面试题 04. 二维数组中的查找](https://leetcode.cn/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/)

## 题目描述

<p>在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。</p>

<p> </p>

<p><strong>示例:</strong></p>

<p>现有矩阵 matrix 如下：</p>

<pre>
[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
</pre>

<p>给定 target = <code>5</code>，返回 <code>true</code>。</p>

<p>给定 target = <code>20</code>，返回 <code>false</code>。</p>

<p> </p>

<p><strong>限制：</strong></p>

<p><code>0 <= n <= 1000</code></p>

<p><code>0 <= m <= 1000</code></p>

<p> </p>

<p><strong>注意：</strong>本题与主站 240 题相同：<a href="https://leetcode.cn/problems/search-a-2d-matrix-ii/">https://leetcode.cn/problems/search-a-2d-matrix-ii/</a></p>

## 解法

-   换一种观察角度，以右上角位置为基点，往左数值逐渐变小，往下数值逐渐变大。
-   且该角度放在数组任意位置都成立，相当于模拟了一棵**二叉搜索树（Binary Search Tree）**。
-   根据二叉搜索树特点，从右上角（或左下角）开始查找即可。

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findNumberIn2DArray(self, matrix: List[List[int]], target: int) -> bool:
        if not matrix or not matrix[0]:
            return False
        m, n = len(matrix), len(matrix[0])
        i, j = m - 1, 0
        while i >= 0 and j < n:
            if matrix[i][j] == target:
                return True
            if matrix[i][j] > target:
                i -= 1
            else:
                j += 1
        return False
```

### **Java**

```java
class Solution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int m = matrix.length, n = matrix[0].length;
        for (int i = m - 1, j = 0; i >= 0 && j < n;) {
            if (matrix[i][j] == target) {
                return true;
            }
            if (matrix[i][j] > target) {
                --i;
            } else {
                ++j;
            }
        }
        return false;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[][]} matrix
 * @param {number} target
 * @return {boolean}
 */
var findNumberIn2DArray = function (matrix, target) {
    if (matrix.length == 0 || matrix[0].length == 0) {
        return false;
    }
    const m = matrix.length;
    const n = matrix[0].length;
    for (let i = 0, j = n - 1; i < m && j >= 0; ) {
        if (matrix[i][j] == target) {
            return true;
        }
        if (matrix[i][j] < target) {
            ++i;
        } else {
            --j;
        }
    }
    return false;
};
```

### **Go**

```go
func findNumberIn2DArray(matrix [][]int, target int) bool {
	if len(matrix) == 0 {
		return false
	}
	m, n := len(matrix), len(matrix[0])
	for i, j := 0, n-1; i < m && j >= 0; {
		if matrix[i][j] == target {
			return true
		}
		if matrix[i][j] < target {
			i++
		} else {
			j--
		}
	}
	return false
}
```

### **C++**

```cpp
class Solution {
public:
    bool findNumberIn2DArray(vector<vector<int>>& matrix, int target) {
        if (matrix.empty()) {
            return false;
        }
        int m = matrix.size(), n = matrix[0].size();
        int i = 0, j = n - 1;
        while (i < m && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] < target) {
                ++i;
            } else {
                --j;
            }
        }
        return false;
    }
};
```

### **TypeScript**

```ts
function findNumberIn2DArray(matrix: number[][], target: number): boolean {
    if (matrix.length == 0 || matrix[0].length == 0) {
        return false;
    }
    const m = matrix.length;
    const n = matrix[0].length;
    for (let i = 0, j = n - 1; i < m && j >= 0; ) {
        if (matrix[i][j] == target) {
            return true;
        }
        if (matrix[i][j] < target) {
            ++i;
        } else {
            --j;
        }
    }
    return false;
}
```

### **Rust**

```rust
use std::cmp::Ordering;
impl Solution {
    pub fn find_number_in2_d_array(matrix: Vec<Vec<i32>>, target: i32) -> bool {
        if matrix.len() == 0 || matrix[0].len() == 0 {
            return false;
        }
        let (m, n) = (matrix.len(), matrix[0].len());
        let (mut i, mut j) = (0, n);
        while i < m && j > 0 {
            match target.cmp(&matrix[i][j - 1]) {
                Ordering::Less => j -= 1,
                Ordering::Greater => i += 1,
                Ordering::Equal => return true,
            }
        }
        false
    }
}
```

### **C#**

```cs
public class Solution {
    public bool FindNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.Length == 0 || matrix[0].Length == 0) {
            return false;
        }
        int i = 0, j = matrix[0].Length - 1;
        while (i < matrix.Length && j >= 0) {
            if (target == matrix[i][j]) {
                return true;
            } else if (target > matrix[i][j]) {
                i += 1;
            } else {
                j -= 1;
            }
        }
        return false;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
