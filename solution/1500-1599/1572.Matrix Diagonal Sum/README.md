# [1572. 矩阵对角线元素的和](https://leetcode.cn/problems/matrix-diagonal-sum)

[English Version](/solution/1500-1599/1572.Matrix%20Diagonal%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个正方形矩阵 <code>mat</code>，请你返回矩阵对角线元素的和。</p>

<p>请你返回在矩阵主对角线上的元素和副对角线上且不在主对角线上元素的和。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp; 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1572.Matrix%20Diagonal%20Sum/images/sample_1911.png" style="height:174px; width:336px" /></p>

<pre>
<strong>输入：</strong>mat = [[<strong>1</strong>,2,<strong>3</strong>],
&nbsp;           [4,<strong>5</strong>,6],
&nbsp;           [<strong>7</strong>,8,<strong>9</strong>]]
<strong>输出：</strong>25
<strong>解释：</strong>对角线的和为：1 + 5 + 9 + 3 + 7 = 25
请注意，元素 mat[1][1] = 5 只会被计算一次。
</pre>

<p><strong>示例&nbsp; 2：</strong></p>

<pre>
<strong>输入：</strong>mat = [[<strong>1</strong>,1,1,<strong>1</strong>],
&nbsp;           [1,<strong>1</strong>,<strong>1</strong>,1],
&nbsp;           [1,<strong>1</strong>,<strong>1</strong>,1],
&nbsp;           [<strong>1</strong>,1,1,<strong>1</strong>]]
<strong>输出：</strong>8
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>mat = [[<strong>5</strong>]]
<strong>输出：</strong>5
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == mat.length == mat[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= mat[i][j] &lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def diagonalSum(self, mat: List[List[int]]) -> int:
        n = len(mat)
        res = 0
        for i in range(n):
            res += mat[i][i] + (0 if n - i - 1 == i else mat[i][n - i - 1])
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int diagonalSum(int[][] mat) {
        int n = mat.length;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            res += mat[i][i] + (n - i - 1 == i ? 0 : mat[i][n - i - 1]);
        }
        return res;
    }
}
```

### **TypeScript**

```ts
function diagonalSum(mat: number[][]): number {
    let n = mat.length;
    let ans = 0;
    for (let i = 0; i < n; i++) {
        ans += mat[i][i];
        let j = n - 1 - i;
        if (i != j) {
            ans += mat[i][j];
        }
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    int diagonalSum(vector<vector<int>>& mat) {
        int n = mat.size();
        int res = 0;
        for (int i = 0; i < n; ++i) {
            res += mat[i][i] + (n - i - 1 == i ? 0 : mat[i][n - i - 1]);
        }
        return res;
    }
};
```

### **Go**

```go
func diagonalSum(mat [][]int) int {
	n, res := len(mat), 0
	for i := 0; i < n; i++ {
		res += mat[i][i]
		if n-i-1 != i {
			res += mat[i][n-i-1]
		}
	}
	return res
}
```

### **Rust**

```rust
impl Solution {
    pub fn diagonal_sum(mat: Vec<Vec<i32>>) -> i32 {
        let n = mat.len();
        let mut res = 0;
        for i in 0..n {
            res += mat[i][i] + mat[n - i - 1][i];
        }
        if n & 1 == 1 {
            return res - mat[n / 2][n / 2];
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
