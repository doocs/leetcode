# [面试题 04. 二维数组中的查找](https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/)

## 题目描述

在一个 n \* m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。

**示例:**

现有矩阵 matrix 如下：

```
[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
```

给定 target = 5，返回  `true`。

给定  target = 20，返回  `false`。

**限制：**

- `0 <= n <= 1000`

- `0 <= m <= 1000`

## 解法

- 换一种观察角度，以右上角位置为基点，往左数值逐渐变小，往下数值逐渐变大。
- 且该角度放在数组任意位置都成立，相当于模拟了一颗**二叉搜索树（Binary Search Tree）**。
- 根据二叉搜索树特点，从右上角（或左下角）开始查找即可。

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findNumberIn2DArray(self, matrix: List[List[int]], target: int) -> bool:
        if not matrix or not matrix[0]:
            return False
        rows, cols = len(matrix), len(matrix[0])
        i, j = rows - 1, 0
        while i >= 0 and j < cols:
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
        int m, n;
        if (matrix == null || (m = matrix.length) == 0 || matrix[0] == null || (n = matrix[0].length) == 0) {
            return false;
        }
        for (int i = 0, j = n - 1; i < m && j >= 0;) {
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
    let m, n;
    if (
        matrix == null ||
        (m = matrix.length) == 0 ||
        matrix[0] == null ||
        (n = matrix[0].length) == 0
    ) {
        return false;
    }
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
    let m: number = matrix.length,
        n: number;
    if (!matrix || !m || !matrix[0] || !(n = matrix[0].length)) return false;
    let i: number = 0,
        j: number = n - 1;
    while (i < m && j >= 0) {
        let cur: number = matrix[i][j];
        if (cur == target) return true;
        if (cur > target) {
            j--;
        } else {
            i++;
        }
    }
    return false;
}
```

### **Rust**

```rust
impl Solution {
    pub fn find_number_in2_d_array(matrix: Vec<Vec<i32>>, target: i32) -> bool {
        let len_y = matrix.len();
        if len_y == 0 {
            return false;
        }
        let len_x = matrix[0].len();
        if len_x == 0 {
            return false;
        }

        let mut x = len_x - 1;
        let mut y = 0;
        while y < len_y {
            match target.cmp(&matrix[y][x]) {
                std::cmp::Ordering::Greater => y += 1,
                std::cmp::Ordering::Equal => return true,
                std::cmp::Ordering::Less => match x {
                    0 => return false,
                    _ => x -= 1,
                },
            }
        }
        false
    }
}
```

### **...**

```

```

<!-- tabs:end -->
