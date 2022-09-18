# [1975. 最大方阵和](https://leetcode.cn/problems/maximum-matrix-sum)

[English Version](/solution/1900-1999/1975.Maximum%20Matrix%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个&nbsp;<code>n x n</code>&nbsp;的整数方阵&nbsp;<code>matrix</code>&nbsp;。你可以执行以下操作&nbsp;<strong>任意次</strong>&nbsp;：</p>

<ul>
	<li>选择&nbsp;<code>matrix</code>&nbsp;中&nbsp;<strong>相邻</strong>&nbsp;两个元素，并将它们都 <strong>乘以</strong>&nbsp;<code>-1</code>&nbsp;。</li>
</ul>

<p>如果两个元素有 <strong>公共边</strong>&nbsp;，那么它们就是 <strong>相邻</strong>&nbsp;的。</p>

<p>你的目的是 <strong>最大化</strong>&nbsp;方阵元素的和。请你在执行以上操作之后，返回方阵的&nbsp;<strong>最大</strong>&nbsp;和。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1975.Maximum%20Matrix%20Sum/images/pc79-q2ex1.png" style="width: 401px; height: 81px;">
<pre><b>输入：</b>matrix = [[1,-1],[-1,1]]
<b>输出：</b>4
<b>解释：</b>我们可以执行以下操作使和等于 4 ：
- 将第一行的 2 个元素乘以 -1 。
- 将第一列的 2 个元素乘以 -1 。
</pre>

<p><strong>示例&nbsp;2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1975.Maximum%20Matrix%20Sum/images/pc79-q2ex2.png" style="width: 321px; height: 121px;">
<pre><b>输入：</b>matrix = [[1,2,3],[-1,-2,-3],[1,2,3]]
<b>输出：</b>16
<b>解释：</b>我们可以执行以下操作使和等于 16 ：
- 将第二行的最后 2 个元素乘以 -1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == matrix.length == matrix[i].length</code></li>
	<li><code>2 &lt;= n &lt;= 250</code></li>
	<li><code>-10<sup>5</sup> &lt;= matrix[i][j] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心**

如果矩阵中存在零，或者矩阵中负数的个数为偶数，那么最大和就是矩阵中所有元素的绝对值之和。

否则，说明矩阵中有奇数个负数，最终一定会剩下一个负数，我们选择绝对值最小的数，将其变为负数，这样可以使得最终的和最大。

时间复杂度 $O(m\times n)$，其中 $m$ 和 $n$ 分别是矩阵的行数和列数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
