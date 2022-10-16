# [2428. 沙漏的最大总和](https://leetcode.cn/problems/maximum-sum-of-an-hourglass)

[English Version](/solution/2400-2499/2428.Maximum%20Sum%20of%20an%20Hourglass/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个大小为 <code>m x n</code> 的整数矩阵 <code>grid</code> 。</p>

<p>按以下形式将矩阵的一部分定义为一个 <strong>沙漏</strong> ：</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2428.Maximum%20Sum%20of%20an%20Hourglass/images/img.jpg" style="width: 243px; height: 243px;">
<p>返回沙漏中元素的 <strong>最大</strong> 总和。</p>

<p><strong>注意：</strong>沙漏无法旋转且必须整个包含在矩阵中。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2428.Maximum%20Sum%20of%20an%20Hourglass/images/1.jpg" style="width: 323px; height: 323px;">
<pre><strong>输入：</strong>grid = [[6,2,1,3],[4,2,1,5],[9,2,8,7],[4,1,2,9]]
<strong>输出：</strong>30
<strong>解释：</strong>上图中的单元格表示元素总和最大的沙漏：6 + 2 + 1 + 2 + 9 + 2 + 8 = 30 。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2428.Maximum%20Sum%20of%20an%20Hourglass/images/2.jpg" style="width: 243px; height: 243px;">
<pre><strong>输入：</strong>grid = [[1,2,3],[4,5,6],[7,8,9]]
<strong>输出：</strong>35
<strong>解释：</strong>上图中的单元格表示元素总和最大的沙漏：1 + 2 + 3 + 5 + 7 + 8 + 9 = 35 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>3 &lt;= m, n &lt;= 150</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：枚举**

从左上角开始，枚举每个可能的沙漏的中间坐标 $(i, j)$，计算沙漏的元素和，取最大值即可。

时间复杂度 $O(m\times n)$。其中 $m$ 和 $n$ 分别是矩阵的行数和列数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxSum(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        ans = 0
        for i in range(1, m - 1):
            for j in range(1, n - 1):
                t = 0
                for x in [i - 1, i, i + 1]:
                    for y in [j - 1, j, j + 1]:
                        t += grid[x][y]

                t -= grid[i][j - 1]
                t -= grid[i][j + 1]
                ans = max(ans, t)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int ans = 0;
        for (int i = 1; i < m - 1; ++i) {
            for (int j = 1; j < n - 1; ++j) {
                int t = 0;
                for (int x = i - 1; x <= i + 1; ++x) {
                    for (int y = j - 1; y <= j + 1; ++y) {
                        t += grid[x][y];
                    }
                }
                t -= grid[i][j - 1];
                t -= grid[i][j + 1];
                ans = Math.max(ans, t);
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
    int maxSum(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int ans = 0;
        for (int i = 1; i < m - 1; ++i) {
            for (int j = 1; j < n - 1; ++j) {
                int t = 0;
                for (int x = i - 1; x <= i + 1; ++x) {
                    for (int y = j - 1; y <= j + 1; ++y) {
                        t += grid[x][y];
                    }
                }
                t -= grid[i][j - 1];
                t -= grid[i][j + 1];
                ans = max(ans, t);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func maxSum(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	ans := 0
	for i := 1; i < m-1; i++ {
		for j := 1; j < n-1; j++ {
			t := 0
			for x := i - 1; x <= i+1; x++ {
				for y := j - 1; y <= j+1; y++ {
					t += grid[x][y]
				}
			}
			t -= grid[i][j-1]
			t -= grid[i][j+1]
			ans = max(ans, t)
		}
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
function maxSum(grid: number[][]): number {
    const m = grid.length,
        n = grid[0].length;
    let threeSum = Array.from({ length: m }, () => new Array(n - 2).fill(0));
    for (let i = 0; i < m; i++) {
        for (let j = 1; j < n - 1; j++) {
            threeSum[i][j - 1] = grid[i][j - 1] + grid[i][j] + grid[i][j + 1];
        }
    }
    let ans = 0;
    for (let i = 1; i < m - 1; i++) {
        for (let j = 1; j < n - 1; j++) {
            ans = Math.max(
                ans,
                threeSum[i - 1][j - 1] + grid[i][j] + threeSum[i + 1][j - 1],
            );
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
