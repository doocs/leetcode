# [2735. 收集巧克力](https://leetcode.cn/problems/collecting-chocolates)

[English Version](/solution/2700-2799/2735.Collecting%20Chocolates/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code> 、下标从 <strong>0</strong> 开始的整数数组 <code>nums</code> ，表示收集不同巧克力的成本。每个巧克力都对应一个不同的类型，最初，位于下标 <code>i</code> 的巧克力就对应第 <code>i</code> 个类型。</p>

<p>在一步操作中，你可以用成本 <code>x</code> 执行下述行为：</p>

<ul>
	<li>同时修改所有巧克力的类型，将巧克力的类型&nbsp;<code>i<sup>th</sup></code>&nbsp;修改为类型&nbsp;<code>((i + 1) mod n)<sup>th</sup></code>。</li>
</ul>

<p>假设你可以执行任意次操作，请返回收集所有类型巧克力所需的最小成本。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [20,1,15], x = 5
<strong>输出：</strong>13
<strong>解释：</strong>最开始，巧克力的类型分别是 [0,1,2] 。我们可以用成本 1 购买第 1 个类型的巧克力。
接着，我们用成本 5 执行一次操作，巧克力的类型变更为 [1,2,0] 。我们可以用成本 1 购买第 2 个类型的巧克力。
然后，我们用成本 5 执行一次操作，巧克力的类型变更为 [2,0,1] 。我们可以用成本 1 购买第 0 个类型的巧克力。
因此，收集所有类型的巧克力需要的总成本是 (1 + 5 + 1 + 5 + 1) = 13 。可以证明这是一种最优方案。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3], x = 4
<strong>输出：</strong>6
<strong>解释：</strong>我们将会按最初的成本收集全部三个类型的巧克力，而不需执行任何操作。因此，收集所有类型的巧克力需要的总成本是 1 + 2 + 3 = 6 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= x &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：枚举**

我们考虑枚举操作的次数，定义 $f[i][j]$ 表示类型为 $i$ 的巧克力进行了 $j$ 次操作后的最小成本。那么有：

$$
f[i][j] =
\begin{cases}
nums[i] ,& j = 0 \\
\min(f[i][j - 1], nums[(i + j) \bmod n]) ,& j > 0
\end{cases}
$$

接下来，我们枚举操作的次数 $j$，其中 $j \in [0,..n-1]$，那么进行 $j$ 次操作的最小成本为：

$$
\sum_{i=0}^{n-1} f[i][j] + j \times x
$$

我们取所有操作次数中的最小值即可。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 是数组 $nums$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minCost(self, nums: List[int], x: int) -> int:
        n = len(nums)
        f = [[0] * n for _ in range(n)]
        for i, v in enumerate(nums):
            f[i][0] = v
            for j in range(1, n):
                f[i][j] = min(f[i][j - 1], nums[(i + j) % n])
        ans = inf
        for j in range(n):
            cost = sum(f[i][j] for i in range(n)) + x * j
            ans = min(ans, cost)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long minCost(int[] nums, int x) {
        int n = nums.length;
        int[][] f = new int[n][n];
        for (int i = 0; i < n; ++i) {
            f[i][0] = nums[i];
            for (int j = 1; j < n; ++j) {
                f[i][j] = Math.min(f[i][j - 1], nums[(i + j) % n]);
            }
        }
        long ans = 1L << 60;
        for (int j = 0; j < n; ++j) {
            long cost = 1L * j * x;
            for (int i = 0; i < n; ++i) {
                cost += f[i][j];
            }
            ans = Math.min(ans, cost);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long minCost(vector<int>& nums, int x) {
        int n = nums.size();
        int f[n][n];
        for (int i = 0; i < n; ++i) {
            f[i][0] = nums[i];
            for (int j = 1; j < n; ++j) {
                f[i][j] = min(f[i][j - 1], nums[(i + j) % n]);
            }
        }
        long long ans = 1LL << 60;
        for (int j = 0; j < n; ++j) {
            long long cost = 1LL * j * x;
            for (int i = 0; i < n; ++i) {
                cost += f[i][j];
            }
            ans = min(ans, cost);
        }
        return ans;
    }
};
```

### **Go**

```go
func minCost(nums []int, x int) int64 {
	n := len(nums)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, n)
		f[i][0] = nums[i]
		for j := 1; j < n; j++ {
			f[i][j] = min(f[i][j-1], nums[(i+j)%n])
		}
	}
	ans := 1 << 60
	for j := 0; j < n; j++ {
		cost := x * j
		for i := range nums {
			cost += f[i][j]
		}
		ans = min(ans, cost)
	}
	return int64(ans)
}
```

### **...**

```

```

<!-- tabs:end -->
