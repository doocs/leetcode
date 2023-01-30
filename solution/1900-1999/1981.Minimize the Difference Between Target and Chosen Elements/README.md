# [1981. 最小化目标值与所选元素的差](https://leetcode.cn/problems/minimize-the-difference-between-target-and-chosen-elements)

[English Version](/solution/1900-1999/1981.Minimize%20the%20Difference%20Between%20Target%20and%20Chosen%20Elements/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个大小为 <code>m x n</code> 的整数矩阵 <code>mat</code> 和一个整数 <code>target</code> 。</p>

<p>从矩阵的 <strong>每一行</strong> 中选择一个整数，你的目标是&nbsp;<strong>最小化</strong>&nbsp;所有选中元素之&nbsp;<strong>和</strong>&nbsp;与目标值 <code>target</code> 的 <strong>绝对差</strong> 。</p>

<p>返回 <strong>最小的绝对差</strong> 。</p>

<p><code>a</code> 和 <code>b</code> 两数字的 <strong>绝对差</strong> 是 <code>a - b</code> 的绝对值。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1981.Minimize%20the%20Difference%20Between%20Target%20and%20Chosen%20Elements/images/matrix1.png" style="width: 181px; height: 181px;" /></p>

<pre>
<strong>输入：</strong>mat = [[1,2,3],[4,5,6],[7,8,9]], target = 13
<strong>输出：</strong>0
<strong>解释：</strong>一种可能的最优选择方案是：
- 第一行选出 1
- 第二行选出 5
- 第三行选出 7
所选元素的和是 13 ，等于目标值，所以绝对差是 0 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1981.Minimize%20the%20Difference%20Between%20Target%20and%20Chosen%20Elements/images/matrix1-1.png" style="width: 61px; height: 181px;" /></p>

<pre>
<strong>输入：</strong>mat = [[1],[2],[3]], target = 100
<strong>输出：</strong>94
<strong>解释：</strong>唯一一种选择方案是：
- 第一行选出 1
- 第二行选出 2
- 第三行选出 3
所选元素的和是 6 ，绝对差是 94 。
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1981.Minimize%20the%20Difference%20Between%20Target%20and%20Chosen%20Elements/images/matrix1-3.png" style="width: 301px; height: 61px;" /></p>

<pre>
<strong>输入：</strong>mat = [[1,2,9,8,7]], target = 6
<strong>输出：</strong>1
<strong>解释：</strong>最优的选择方案是选出第一行的 7 。
绝对差是 1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == mat.length</code></li>
	<li><code>n == mat[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 70</code></li>
	<li><code>1 &lt;= mat[i][j] &lt;= 70</code></li>
	<li><code>1 &lt;= target &lt;= 800</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划（分组背包）**

设 $f[i][j]$ 表示前 $i$ 行是否能选出元素和为 $j$，则有状态转移方程：

$$
f[i][j] = \begin{cases} 1 & \text{如果存在 } x \in row[i] \text{ 使得 } f[i - 1][j - x] = 1 \\ 0 & \text{否则} \end{cases}
$$

其中 $row[i]$ 表示第 $i$ 行的元素集合。

由于 $f[i][j]$ 只与 $f[i - 1][j]$ 有关，因此我们可以使用滚动数组优化空间复杂度。

最后，遍历 $f$ 数组，找出最小的绝对差即可。

时间复杂度 $O(m^2 \times n \times C)$，空间复杂度 $O(m \times C)$。其中 $m$ 和 $n$ 分别为矩阵的行数和列数；而 $C$ 为矩阵元素的最大值。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimizeTheDifference(self, mat: List[List[int]], target: int) -> int:
        f = {0}
        for row in mat:
            f = set(a + b for a in f for b in row)
        return min(abs(v - target) for v in f)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minimizeTheDifference(int[][] mat, int target) {
        Set<Integer> f = new HashSet<>();
        f.add(0);
        for (var row : mat) {
            Set<Integer> g = new HashSet<>();
            for (int a : f) {
                for (int b : row) {
                    g.add(a + b);
                }
            }
            f = g;
        }
        int ans = 1 << 30;
        for (int v : f) {
            ans = Math.min(ans, Math.abs(v - target));
        }
        return ans;
    }
}
```

```java
class Solution {
    public int minimizeTheDifference(int[][] mat, int target) {
        boolean[] f = {true};
        for (var row : mat) {
            int mx = 0;
            for (int x : row) {
                mx = Math.max(mx, x);
            }
            boolean[] g = new boolean[f.length + mx];
            for (int x : row) {
                for (int j = x; j < f.length + x; ++j) {
                    g[j] |= f[j - x];
                }
            }
            f = g;
        }
        int ans = 1 << 30;
        for (int j = 0; j < f.length; ++j) {
            if (f[j]) {
                ans = Math.min(ans, Math.abs(j - target));
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
    int minimizeTheDifference(vector<vector<int>>& mat, int target) {
        vector<int> f = {1};
        for (auto& row : mat) {
            int mx = *max_element(row.begin(), row.end());
            vector<int> g(f.size() + mx);
            for (int x : row) {
                for (int j = x; j < f.size() + x; ++j) {
                    g[j] |= f[j - x];
                }
            }
            f = move(g);
        }
        int ans = 1 << 30;
        for (int j = 0; j < f.size(); ++j) {
            if (f[j]) {
                ans = min(ans, abs(j - target));
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func minimizeTheDifference(mat [][]int, target int) int {
	f := []int{1}
	for _, row := range mat {
		mx := 0
		for _, x := range row {
			mx = max(mx, x)
		}
		g := make([]int, len(f)+mx)
		for _, x := range row {
			for j := x; j < len(f)+x; j++ {
				g[j] |= f[j-x]
			}
		}
		f = g
	}
	ans := 1 << 30
	for j, v := range f {
		if v == 1 {
			ans = min(ans, abs(j-target))
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

### **...**

```

```

<!-- tabs:end -->
