# [2397. 被列覆盖的最多行数](https://leetcode.cn/problems/maximum-rows-covered-by-columns)

[English Version](/solution/2300-2399/2397.Maximum%20Rows%20Covered%20by%20Columns/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的&nbsp;<code>m x n</code>&nbsp;二进制矩阵&nbsp;<code>mat</code>&nbsp;和一个整数&nbsp;<code>cols</code>&nbsp;，表示你需要选出的列数。</p>

<p>如果一行中，所有的 <code>1</code> 都被你选中的列所覆盖，那么我们称这一行 <strong>被覆盖</strong>&nbsp;了。</p>

<p>请你返回在选择 <code>cols</code>&nbsp;列的情况下，<strong>被覆盖</strong>&nbsp;的行数 <strong>最大</strong>&nbsp;为多少。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2397.Maximum%20Rows%20Covered%20by%20Columns/images/rowscovered.png" style="width: 250px; height: 417px;"></strong></p>

<pre><b>输入：</b>mat = [[0,0,0],[1,0,1],[0,1,1],[0,0,1]], cols = 2
<b>输出：</b>3
<strong>解释：</strong>
如上图所示，覆盖 3 行的一种可行办法是选择第 0 和第 2 列。
可以看出，不存在大于 3 行被覆盖的方案，所以我们返回 3 。
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2397.Maximum%20Rows%20Covered%20by%20Columns/images/rowscovered2.png" style="width: 83px; height: 247px;"></strong></p>

<pre><b>输入：</b>mat = [[1],[0]], cols = 1
<b>输出：</b>2
<strong>解释：</strong>
选择唯一的一列，两行都被覆盖了，原因是整个矩阵都被覆盖了。
所以我们返回 2 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == mat.length</code></li>
	<li><code>n == mat[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 12</code></li>
	<li><code>mat[i][j]</code>&nbsp;要么是&nbsp;<code>0</code>&nbsp;要么是&nbsp;<code>1</code>&nbsp;。</li>
	<li><code>1 &lt;= cols &lt;= n</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：二进制枚举**

我们先将矩阵中的每一行转换成一个二进制数，记录在数组 $rows$ 中，其中 $rows[i]$ 表示第 $i$ 行对应的二进制数，而 $rows[i]$ 的第 $j$ 位表示第 $i$ 行第 $j$ 列的值。

接下来，我们枚举所有的 $2^n$ 种列选择方案，其中 $n$ 为矩阵的列数。对于每一种列选择方案，我们判断是否选中了 `numSelect` 列，如果不是，则跳过。否则，我们统计矩阵中有多少行中的所有 $1$ 都被选中的列覆盖，即统计有多少行的二进制数 $rows[i]$ 与列选择方案 $mask$ 按位与的结果等于 $rows[i]$，并更新最大的行数。

时间复杂度 $O(2^n \times m)$，空间复杂度 $O(m)$。其中 $m$ 和 $n$ 分别为矩阵的行数和列数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximumRows(self, matrix: List[List[int]], numSelect: int) -> int:
        rows = []
        for row in matrix:
            mask = reduce(or_, (1 << j for j, x in enumerate(row) if x), 0)
            rows.append(mask)

        ans = 0
        for mask in range(1 << len(matrix[0])):
            if mask.bit_count() != numSelect:
                continue
            t = sum((x & mask) == x for x in rows)
            ans = max(ans, t)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maximumRows(int[][] matrix, int numSelect) {
        int m = matrix.length, n = matrix[0].length;
        int[] rows = new int[m];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == 1) {
                    rows[i] |= 1 << j;
                }
            }
        }
        int ans = 0;
        for (int mask = 1; mask < 1 << n; ++mask) {
            if (Integer.bitCount(mask) != numSelect) {
                continue;
            }
            int t = 0;
            for (int x : rows) {
                if ((x & mask) == x) {
                    ++t;
                }
            }
            ans = Math.max(ans, t);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumRows(vector<vector<int>>& matrix, int numSelect) {
        int m = matrix.size(), n = matrix[0].size();
        int rows[m];
        memset(rows, 0, sizeof(rows));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j]) {
                    rows[i] |= 1 << j;
                }
            }
        }
        int ans = 0;
        for (int mask = 1; mask < 1 << n; ++mask) {
            if (__builtin_popcount(mask) != numSelect) {
                continue;
            }
            int t = 0;
            for (int x : rows) {
                t += (x & mask) == x;
            }
            ans = max(ans, t);
        }
        return ans;
    }
};
```

### **Go**

```go
func maximumRows(matrix [][]int, numSelect int) (ans int) {
	m, n := len(matrix), len(matrix[0])
	rows := make([]int, m)
	for i, row := range matrix {
		for j, x := range row {
			if x == 1 {
				rows[i] |= 1 << j
			}
		}
	}
	for mask := 1; mask < 1<<n; mask++ {
		if bits.OnesCount(uint(mask)) != numSelect {
			continue
		}
		t := 0
		for _, x := range rows {
			if (x & mask) == x {
				t++
			}
		}
		if ans < t {
			ans = t
		}
	}
	return
}
```

### **TypeScript**

```ts

```

### **...**

```


```

<!-- tabs:end -->
