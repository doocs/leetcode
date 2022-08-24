# [1458. 两个子序列的最大点积](https://leetcode.cn/problems/max-dot-product-of-two-subsequences)

[English Version](/solution/1400-1499/1458.Max%20Dot%20Product%20of%20Two%20Subsequences/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个数组&nbsp;<code>nums1</code>&nbsp;和&nbsp;<code>nums2</code>&nbsp;。</p>

<p>请你返回 <code>nums1</code> 和 <code>nums2</code> 中两个长度相同的 <strong>非空</strong> 子序列的最大点积。</p>

<p>数组的非空子序列是通过删除原数组中某些元素（可能一个也不删除）后剩余数字组成的序列，但不能改变数字间相对顺序。比方说，<code>[2,3,5]</code>&nbsp;是&nbsp;<code>[1,2,3,4,5]</code>&nbsp;的一个子序列而&nbsp;<code>[1,5,3]</code>&nbsp;不是。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [2,1,-2,5], nums2 = [3,0,-6]
<strong>输出：</strong>18
<strong>解释：</strong>从 nums1 中得到子序列 [2,-2] ，从 nums2 中得到子序列 [3,-6] 。
它们的点积为 (2*3 + (-2)*(-6)) = 18 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [3,-2], nums2 = [2,-6,7]
<strong>输出：</strong>21
<strong>解释：</strong>从 nums1 中得到子序列 [3] ，从 nums2 中得到子序列 [7] 。
它们的点积为 (3*7) = 21 。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [-1,-1], nums2 = [1,1]
<strong>输出：</strong>-1
<strong>解释：</strong>从 nums1 中得到子序列 [-1] ，从 nums2 中得到子序列 [1] 。
它们的点积为 -1 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length, nums2.length &lt;= 500</code></li>
	<li><code>-1000 &lt;= nums1[i], nums2[i] &lt;= 100</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>点积：</strong></p>

<pre>
定义 <code><strong>a</strong>&nbsp;= [<em>a</em><sub>1</sub>,&nbsp;<em>a</em><sub>2</sub>,&hellip;,&nbsp;<em>a</em><sub><em>n</em></sub>]</code> 和<strong> <code>b</code></strong><code>&nbsp;= [<em>b</em><sub>1</sub>,&nbsp;<em>b</em><sub>2</sub>,&hellip;,&nbsp;<em>b</em><sub><em>n</em></sub>]</code> 的点积为：

<img alt="\mathbf{a}\cdot \mathbf{b} = \sum_{i=1}^n a_ib_i = a_1b_1 + a_2b_2 + \cdots + a_nb_n " class="tex" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1458.Max%20Dot%20Product%20of%20Two%20Subsequences/images/c329bf86e747d74f55ed2e17c36fd83f.png" />

这里的 <strong>&Sigma;</strong> 指示总和符号。
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

定义 $dp[i][j]$ 表示 $nums1$ 前 $i$ 个元素和 $nums2$ 前 $j$ 个元素得到的最大点积。

那么有：

$$
dp[i][j]=max(dp[i-1][j], dp[i][j - 1], max(dp[i - 1][j - 1], 0) + nums1[i] \times nums2[j])
$$

答案为 $dp[m][n]$。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是数组 $nums1$ 和 $nums2$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxDotProduct(self, nums1: List[int], nums2: List[int]) -> int:
        m, n = len(nums1), len(nums2)
        dp = [[-inf] * (n + 1) for _ in range(m + 1)]
        for i in range(1, m + 1):
            for j in range(1, n + 1):
                v = nums1[i - 1] * nums2[j - 1]
                dp[i][j] = max(dp[i - 1][j], dp[i][j - 1],
                               max(dp[i - 1][j - 1], 0) + v)
        return dp[-1][-1]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int[] e : dp) {
            Arrays.fill(e, Integer.MIN_VALUE);
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                dp[i][j] = Math.max(dp[i][j], Math.max(0, dp[i - 1][j - 1]) + nums1[i - 1] * nums2[j - 1]);
            }
        }
        return dp[m][n];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxDotProduct(vector<int>& nums1, vector<int>& nums2) {
        int m = nums1.size(), n = nums2.size();
        vector<vector<int>> dp(m + 1, vector<int>(n + 1, INT_MIN));
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                int v = nums1[i - 1] * nums2[j - 1];
                dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
                dp[i][j] = max(dp[i][j], max(0, dp[i - 1][j - 1]) + v);
            }
        }
        return dp[m][n];
    }
};
```

### **Go**

```go
func maxDotProduct(nums1 []int, nums2 []int) int {
	m, n := len(nums1), len(nums2)
	dp := make([][]int, m+1)
	for i := range dp {
		dp[i] = make([]int, n+1)
		for j := range dp[i] {
			dp[i][j] = math.MinInt32
		}
	}
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			v := nums1[i-1] * nums2[j-1]
			dp[i][j] = max(dp[i-1][j], dp[i][j-1])
			dp[i][j] = max(dp[i][j], max(0, dp[i-1][j-1])+v)
		}
	}
	return dp[m][n]
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
