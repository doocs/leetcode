# [2397. 被列覆盖的最多行数](https://leetcode.cn/problems/maximum-rows-covered-by-columns)

[English Version](/solution/2300-2399/2397.Maximum%20Rows%20Covered%20by%20Columns/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的&nbsp;<code>m x n</code>&nbsp;二进制矩阵&nbsp;<code>mat</code>&nbsp;和一个整数&nbsp;<code>cols</code>&nbsp;，表示你需要选出的列数。</p>

<p>如果一行中，所有的 <code>1</code> 都被你选中的列所覆盖，那么我们称这一行 <strong>被覆盖</strong>&nbsp;了。</p>

<p>请你返回在选择 <code>cols</code>&nbsp;列的情况下，<strong>被覆盖</strong>&nbsp;的行数 <strong>最大</strong>&nbsp;为多少。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://assets.leetcode.com/uploads/2022/07/14/rowscovered.png" style="width: 250px; height: 417px;"></strong></p>

<pre><b>输入：</b>mat = [[0,0,0],[1,0,1],[0,1,1],[0,0,1]], cols = 2
<b>输出：</b>3
<strong>解释：</strong>
如上图所示，覆盖 3 行的一种可行办法是选择第 0 和第 2 列。
可以看出，不存在大于 3 行被覆盖的方案，所以我们返回 3 。
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://assets.leetcode.com/uploads/2022/07/14/rowscovered2.png" style="width: 83px; height: 247px;"></strong></p>

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

**方法一：DFS 或二进制枚举**

直接二进制枚举选中的列，然后判断是否覆盖所有行中的 `1`，若是，更新答案。

时间复杂度 $O(2^n\times n)$，空间复杂度 $O(m)$，其中 $m$ 和 $n$ 分别为矩阵的行数和列数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximumRows(self, mat: List[List[int]], cols: int) -> int:
        def dfs(mask, i):
            if i > n or mask.bit_count() > cols:
                return
            nonlocal ans
            if i == n:
                t = sum((v & mask) == v for v in arr)
                ans = max(ans, t)
                return
            dfs(mask, i + 1)
            dfs(mask | 1 << i, i + 1)

        arr = []
        ans, n = 0, len(mat[0])
        for i, row in enumerate(mat):
            x = 0
            for j, v in enumerate(row):
                x |= v << j
            arr.append(x)
        dfs(0, 0)
        return ans
```

```python
class Solution:
    def maximumRows(self, mat: List[List[int]], cols: int) -> int:
        arr = []
        for i, row in enumerate(mat):
            x = 0
            for j, v in enumerate(row):
                x |= v << j
            arr.append(x)
        ans, n = 0, len(mat[0])
        for mask in range(1, 1 << n | 1):
            if mask.bit_count() > cols:
                continue
            t = sum((v & mask) == v for v in arr)
            ans = max(ans, t)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int ans;
    public int maximumRows(int[][] mat, int cols) {
        int m = mat.length, n = mat[0].length;
        int[] arr = new int[m];
        for (int i = 0; i < m; ++i) {
            int x = 0;
            for (int j = 0; j < n; ++j) {
                x |= mat[i][j] << j;
            }
            arr[i] = x;
        }
        int ans = 0;
        for (int mask = 1; mask <= 1 << n; ++mask) {
            if (Integer.bitCount(mask) > cols) {
                continue;
            }
            int t = 0;
            for (int v : arr) {
                if ((v & mask) == v) {
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
    int maximumRows(vector<vector<int>>& mat, int cols) {
        int m = mat.size(), n = mat[0].size();
        vector<int> arr(m);
        for (int i = 0; i < m; ++i) {
            int x = 0;
            for (int j = 0; j < n; ++j) x |= mat[i][j] << j;
            arr[i] = x;
        }
        int ans = 0;
        for (int mask = 1; mask <= 1 << n; ++mask) {
            if (__builtin_popcount(mask) > cols) continue;
            int t = 0;
            for (int v : arr) t += (v & mask) == v;
            ans = max(ans, t);
        }
        return ans;
    }
};
```

### **Go**

```go
func maximumRows(mat [][]int, cols int) int {
	m, n := len(mat), len(mat[0])
	arr := make([]int, m)
	for i, row := range mat {
		x := 0
		for j, v := range row {
			x |= v << j
		}
		arr[i] = x
	}
	ans := 0
	for mask := 1; mask <= 1<<n; mask++ {
		if bits.OnesCount(uint(mask)) != cols {
			continue
		}
		t := 0
		for _, v := range arr {
			if (v & mask) == v {
				t++
			}
		}
		ans = max(ans, t)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts

```

### **...**

```


```

<!-- tabs:end -->
