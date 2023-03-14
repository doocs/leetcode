# [1605. 给定行和列的和求可行矩阵](https://leetcode.cn/problems/find-valid-matrix-given-row-and-column-sums)

[English Version](/solution/1600-1699/1605.Find%20Valid%20Matrix%20Given%20Row%20and%20Column%20Sums/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个非负整数数组&nbsp;<code>rowSum</code> 和&nbsp;<code>colSum</code>&nbsp;，其中&nbsp;<code>rowSum[i]</code>&nbsp;是二维矩阵中第 <code>i</code>&nbsp;行元素的和， <code>colSum[j]</code>&nbsp;是第 <code>j</code>&nbsp;列元素的和。换言之你不知道矩阵里的每个元素，但是你知道每一行和每一列的和。</p>

<p>请找到大小为&nbsp;<code>rowSum.length x colSum.length</code>&nbsp;的任意 <strong>非负整数</strong>&nbsp;矩阵，且该矩阵满足&nbsp;<code>rowSum</code> 和&nbsp;<code>colSum</code>&nbsp;的要求。</p>

<p>请你返回任意一个满足题目要求的二维矩阵，题目保证存在 <strong>至少一个</strong>&nbsp;可行矩阵。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>rowSum = [3,8], colSum = [4,7]
<strong>输出：</strong>[[3,0],
      [1,7]]
<strong>解释：</strong>
第 0 行：3 + 0 = 3 == rowSum[0]
第 1 行：1 + 7 = 8 == rowSum[1]
第 0 列：3 + 1 = 4 == colSum[0]
第 1 列：0 + 7 = 7 == colSum[1]
行和列的和都满足题目要求，且所有矩阵元素都是非负的。
另一个可行的矩阵为：[[1,2],
                  [3,5]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>rowSum = [5,7,10], colSum = [8,6,8]
<strong>输出：</strong>[[0,5,0],
      [6,1,0],
      [2,0,8]]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>rowSum = [14,9], colSum = [6,9,8]
<strong>输出：</strong>[[0,9,5],
      [6,0,3]]
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>rowSum = [1,0], colSum = [1]
<strong>输出：</strong>[[1],
      [0]]
</pre>

<p><strong>示例 5：</strong></p>

<pre>
<strong>输入：</strong>rowSum = [0], colSum = [0]
<strong>输出：</strong>[[0]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= rowSum.length, colSum.length &lt;= 500</code></li>
	<li><code>0 &lt;= rowSum[i], colSum[i] &lt;= 10<sup>8</sup></code></li>
	<li><code>sum(rowSum) == sum(colSum)</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 构造**

我们可以先初始化一个 $m$ 行 $n$ 列的答案矩阵 $ans$。

接下来，遍历矩阵的每一个位置 $(i, j)$，将该位置的元素设为 $x = min(rowSum[i], colSum[j])$，并将 $rowSum[i]$ 和 $colSum[j]$ 分别减去 $x$。遍历完所有的位置后，我们就可以得到一个满足题目要求的矩阵 $ans$。

以上策略的正确性说明如下：

根据题目的要求，我们知道 $rowSum$ 和 $colSum$ 的和是相等的，那么 $rowSum[0]$ 一定小于等于 $\sum_{j = 0}^{n - 1} colSum[j]$。所以，在经过 $n$ 次操作后，一定能够使得 $rowSum[0]$ 为 $0$，并且保证对任意 $j \in [0, n - 1]$，都有 $colSum[j] \geq 0$。

因此，我们把原问题缩小为一个 $m-1$ 行和 $n$ 列的子问题，继续进行上述的操作，直到 $rowSum$ 和 $colSum$ 中的所有元素都为 $0$，就可以得到一个满足题目要求的矩阵 $ans$。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别为 $rowSum$ 和 $colSum$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def restoreMatrix(self, rowSum: List[int], colSum: List[int]) -> List[List[int]]:
        m, n = len(rowSum), len(colSum)
        ans = [[0] * n for _ in range(m)]
        for i in range(m):
            for j in range(n):
                x = min(rowSum[i], colSum[j])
                ans[i][j] = x
                rowSum[i] -= x
                colSum[j] -= x
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int m = rowSum.length;
        int n = colSum.length;
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int x = Math.min(rowSum[i], colSum[j]);
                ans[i][j] = x;
                rowSum[i] -= x;
                colSum[j] -= x;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> restoreMatrix(vector<int>& rowSum, vector<int>& colSum) {
        int m = rowSum.size(), n = colSum.size();
        vector<vector<int>> ans(m, vector<int>(n));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int x = min(rowSum[i], colSum[j]);
                ans[i][j] = x;
                rowSum[i] -= x;
                colSum[j] -= x;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func restoreMatrix(rowSum []int, colSum []int) [][]int {
	m, n := len(rowSum), len(colSum)
	ans := make([][]int, m)
	for i := range ans {
		ans[i] = make([]int, n)
	}
	for i := range rowSum {
		for j := range colSum {
			x := min(rowSum[i], colSum[j])
			ans[i][j] = x
			rowSum[i] -= x
			colSum[j] -= x
		}
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **JavaScript**

```js
/**
 * @param {number[]} rowSum
 * @param {number[]} colSum
 * @return {number[][]}
 */
var restoreMatrix = function (rowSum, colSum) {
    const m = rowSum.length;
    const n = colSum.length;
    const ans = Array.from(new Array(m), () => new Array(n).fill(0));
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            const x = Math.min(rowSum[i], colSum[j]);
            ans[i][j] = x;
            rowSum[i] -= x;
            colSum[j] -= x;
        }
    }
    return ans;
};
```

### **TypeScript**

```ts
function restoreMatrix(rowSum: number[], colSum: number[]): number[][] {
    const m = rowSum.length;
    const n = colSum.length;
    const ans = Array.from(new Array(m), () => new Array(n).fill(0));
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            const x = Math.min(rowSum[i], colSum[j]);
            ans[i][j] = x;
            rowSum[i] -= x;
            colSum[j] -= x;
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
