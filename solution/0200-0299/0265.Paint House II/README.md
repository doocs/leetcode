# [265. 粉刷房子 II](https://leetcode.cn/problems/paint-house-ii)

[English Version](/solution/0200-0299/0265.Paint%20House%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>假如有一排房子共有&nbsp;<code>n</code>&nbsp;幢，每个房子可以被粉刷成 <code>k</code>&nbsp;种颜色中的一种。房子粉刷成不同颜色的花费成本也是不同的。你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。</p>

<p>每个房子粉刷成不同颜色的花费以一个 <code>n x k</code> 的矩阵表示。</p>

<ul>
	<li>例如，<code>costs[0][0]</code> 表示第 <code>0</code>&nbsp;幢房子粉刷成 <code>0</code> 号颜色的成本；<code>costs[1][2]</code>&nbsp;表示第 <code>1</code> 幢房子粉刷成 <code>2</code> 号颜色的成本，以此类推。</li>
</ul>

<p>返回 <em>粉刷完所有房子的最低成本</em>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入: </strong>costs = [[1,5,3],[2,9,4]]
<strong>输出: </strong>5
<strong>解释: 
</strong>将房子 0 刷成 0 号颜色，房子 1 刷成 2 号颜色。花费: 1 + 4 = 5; 
或者将 房子 0 刷成 2 号颜色，房子 1 刷成 0 号颜色。花费: 3 + 2 = 5. </pre>

<p><strong>示例&nbsp;<strong>2:</strong></strong></p>

<pre>
<strong>输入:</strong> costs = [[1,3],[2,4]]
<strong>输出:</strong> 5
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>costs.length == n</code></li>
	<li><code>costs[i].length == k</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>2 &lt;= k &lt;= 20</code></li>
	<li><code>1 &lt;= costs[i][j] &lt;= 20</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>您能否在&nbsp;<code>O(nk)</code> 的时间复杂度下解决此问题？</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

定义 $f[i][j]$ 表示粉刷前 $i$ 个房子，且最后一个房子被粉刷成第 $j$ 种颜色的最小花费。答案为 $\min_{0 \leq j < k} f[n][j]$。

对于 $f[i][j]$，可以从 $f[i - 1][j']$ 转移而来，其中 $j' \neq j$。因此，可以得到状态转移方程：

$$
f[i][j] = \min_{0 \leq j' < k, j' \neq j} f[i - 1][j'] + costs[i - 1][j]
$$

由于 $f[i][j]$ 只与 $f[i - 1][j']$ 有关，因此可以使用滚动数组优化空间复杂度。

时间复杂度 $O(n \times k^2)$，空间复杂度 $O(k)$。其中 $n$ 和 $k$ 分别为房子数量和颜色数量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minCostII(self, costs: List[List[int]]) -> int:
        n, k = len(costs), len(costs[0])
        f = costs[0][:]
        for i in range(1, n):
            g = costs[i][:]
            for j in range(k):
                t = min(f[h] for h in range(k) if h != j)
                g[j] += t
            f = g
        return min(f)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minCostII(int[][] costs) {
        int n = costs.length, k = costs[0].length;
        int[] f = costs[0].clone();
        for (int i = 1; i < n; ++i) {
            int[] g = costs[i].clone();
            for (int j = 0; j < k; ++j) {
                int t = Integer.MAX_VALUE;
                for (int h = 0; h < k; ++h) {
                    if (h != j) {
                        t = Math.min(t, f[h]);
                    }
                }
                g[j] += t;
            }
            f = g;
        }
        return Arrays.stream(f).min().getAsInt();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minCostII(vector<vector<int>>& costs) {
        int n = costs.size(), k = costs[0].size();
        vector<int> f = costs[0];
        for (int i = 1; i < n; ++i) {
            vector<int> g = costs[i];
            for (int j = 0; j < k; ++j) {
                int t = INT_MAX;
                for (int h = 0; h < k; ++h) {
                    if (h != j) {
                        t = min(t, f[h]);
                    }
                }
                g[j] += t;
            }
            f = move(g);
        }
        return *min_element(f.begin(), f.end());
    }
};
```

### **Go**

```go
func minCostII(costs [][]int) (ans int) {
	n, k := len(costs), len(costs[0])
	f := cp(costs[0])
	for i := 1; i < n; i++ {
		g := cp(costs[i])
		for j := 0; j < k; j++ {
			t := math.MaxInt32
			for h := 0; h < k; h++ {
				if h != j && t > f[h] {
					t = f[h]
				}
			}
			g[j] += t
		}
		f = g
	}
	ans = f[0]
	for _, v := range f {
		if ans > v {
			ans = v
		}
	}
	return
}

func cp(arr []int) []int {
	t := make([]int, len(arr))
	copy(t, arr)
	return t
}
```

### **...**

```

```

<!-- tabs:end -->
