# [861. 翻转矩阵后的得分](https://leetcode.cn/problems/score-after-flipping-matrix)

[English Version](/solution/0800-0899/0861.Score%20After%20Flipping%20Matrix/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个大小为 <code>m x n</code> 的二元矩阵 <code>grid</code> ，矩阵中每个元素的值为 <code>0</code> 或 <code>1</code> 。</p>

<p>一次 <strong>移动</strong> 是指选择任一行或列，并转换该行或列中的每一个值：将所有 <code>0</code> 都更改为 <code>1</code>，将所有 <code>1</code> 都更改为 <code>0</code>。</p>

<p>在做出任意次数的移动后，将该矩阵的每一行都按照二进制数来解释，矩阵的 <strong>得分</strong> 就是这些数字的总和。</p>

<p>在执行任意次 <strong>移动</strong> 后（含 0 次），返回可能的最高分数。</p>

<p>&nbsp;</p>

<ol>
</ol>

<p><strong class="example">示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0861.Score%20After%20Flipping%20Matrix/images/lc-toogle1.jpg" style="width: 500px; height: 299px;" />
<pre>
<strong>输入：</strong>grid = [[0,0,1,1],[1,0,1,0],[1,1,0,0]]
<strong>输出：</strong>39
<strong>解释：</strong>0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>grid = [[0]]
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 20</code></li>
	<li><code>grid[i][j]</code> 为 <code>0</code> 或 <code>1</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心**

每一行的数字要尽可能大，因此，遍历每一行，若行首元素为 0，则将该行每个元素进行翻转，即 `grid[i][j] ^= 1`。

接着，遍历每一列，若该列中 1 的个数小于 0 的个数，则将该列进行翻转。实际过程中，并不需要对列进行翻转，只需要取 `max(cnt, m - cnt)`，即表示 1 的个数，再乘上该位的大小 `1 << (n - j - 1)`，即求得当前列的大小。累加每一列大小即可。

时间复杂度 $O(m\times n)$，空间复杂度 $O(1)$。其中 $m$, $n$ 分别为矩阵的行数和列数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def matrixScore(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        for i in range(m):
            if grid[i][0] == 0:
                for j in range(n):
                    grid[i][j] ^= 1
        ans = 0
        for j in range(n):
            cnt = sum(grid[i][j] for i in range(m))
            ans += max(cnt, m - cnt) * (1 << (n - j - 1))
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int matrixScore(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; ++i) {
            if (grid[i][0] == 0) {
                for (int j = 0; j < n; ++j) {
                    grid[i][j] ^= 1;
                }
            }
        }
        int ans = 0;
        for (int j = 0; j < n; ++j) {
            int cnt = 0;
            for (int i = 0; i < m; ++i) {
                cnt += grid[i][j];
            }
            ans += Math.max(cnt, m - cnt) * (1 << (n - j - 1));
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int matrixScore(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        for (int i = 0; i < m; ++i) {
            if (grid[i][0] == 0) {
                for (int j = 0; j < n; ++j) {
                    grid[i][j] ^= 1;
                }
            }
        }
        int ans = 0;
        for (int j = 0; j < n; ++j) {
            int cnt = 0;
            for (int i = 0; i < m; ++i) {
                cnt += grid[i][j];
            }
            ans += max(cnt, m - cnt) * (1 << (n - j - 1));
        }
        return ans;
    }
};
```

### **Go**

```go
func matrixScore(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	for i := 0; i < m; i++ {
		if grid[i][0] == 0 {
			for j := 0; j < n; j++ {
				grid[i][j] ^= 1
			}
		}
	}
	ans := 0
	for j := 0; j < n; j++ {
		cnt := 0
		for i := 0; i < m; i++ {
			cnt += grid[i][j]
		}
		if cnt < m-cnt {
			cnt = m - cnt
		}
		ans += cnt * (1 << (n - j - 1))
	}
	return ans
}
```

### **TypeScript**

```ts
function matrixScore(grid: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    for (let i = 0; i < m; ++i) {
        if (grid[i][0] == 0) {
            for (let j = 0; j < n; ++j) {
                grid[i][j] ^= 1;
            }
        }
    }
    let ans = 0;
    for (let j = 0; j < n; ++j) {
        let cnt = 0;
        for (let i = 0; i < m; ++i) {
            cnt += grid[i][j];
        }
        ans += Math.max(cnt, m - cnt) * (1 << (n - j - 1));
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
