# [276. 栅栏涂色](https://leetcode.cn/problems/paint-fence)

[English Version](/solution/0200-0299/0276.Paint%20Fence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有 <code>k</code> 种颜色的涂料和一个包含 <code>n</code> 个栅栏柱的栅栏，请你按下述规则为栅栏设计涂色方案：</p>

<ul>
	<li>每个栅栏柱可以用其中 <strong>一种</strong> 颜色进行上色。</li>
	<li>相邻的栅栏柱 <strong>最多连续两个 </strong>颜色相同。</li>
</ul>

<p>给你两个整数 <code>k</code> 和 <code>n</code> ，返回所有有效的涂色 <strong>方案数</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0276.Paint%20Fence/images/paintfenceex1.png" style="width: 507px; height: 313px;" />
<pre>
<strong>输入：</strong>n = 3, k = 2
<strong>输出：</strong>6
<strong>解释：</strong>所有的可能涂色方案如上图所示。注意，全涂红或者全涂绿的方案属于无效方案，因为相邻的栅栏柱 <strong>最多连续两个 </strong>颜色相同。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 1, k = 1
<strong>输出：</strong>1
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 7, k = 2
<strong>输出：</strong>42
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= n <= 50</code></li>
	<li><code>1 <= k <= 10<sup>5</sup></code></li>
	<li>题目数据保证：对于输入的 <code>n</code> 和 <code>k</code> ，其答案在范围 <code>[0, 2<sup>31</sup> - 1]</code> 内</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

定义 $dp[i][0]$ 表示栅栏 $[0,..i]$ 且最后两个栅栏颜色不同的方案数，$dp[i][1]$ 表示栅栏 $[0,..i]$ 且最后两个栅栏颜色相同的方案数。

初始时 $dp[0][0]=k$。当 $i \ge 1$ 时，有：

$$
\begin{cases}
dp[i][0]=(dp[i-1][0]+dp[i-1]) \times (k-1)\\
dp[i][1]=dp[i-1][0]
\end{cases}
$$

答案为 $dp[n-1][0] + dp[n-1][1]$。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 是栅栏柱的数量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numWays(self, n: int, k: int) -> int:
        dp = [[0] * 2 for _ in range(n)]
        dp[0][0] = k
        for i in range(1, n):
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1]) * (k - 1)
            dp[i][1] = dp[i - 1][0]
        return sum(dp[-1])
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numWays(int n, int k) {
        int[][] dp = new int[n][2];
        dp[0][0] = k;
        for (int i = 1; i < n; ++i) {
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1]) * (k - 1);
            dp[i][1] = dp[i - 1][0];
        }
        return dp[n - 1][0] + dp[n - 1][1];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numWays(int n, int k) {
        vector<vector<int>> dp(n, vector<int>(2));
        dp[0][0] = k;
        for (int i = 1; i < n; ++i) {
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1]) * (k - 1);
            dp[i][1] = dp[i - 1][0];
        }
        return dp[n - 1][0] + dp[n - 1][1];
    }
};
```

### **Go**

```go
func numWays(n int, k int) int {
	dp := make([][]int, n)
	for i := range dp {
		dp[i] = make([]int, 2)
	}
	dp[0][0] = k
	for i := 1; i < n; i++ {
		dp[i][0] = (dp[i-1][0] + dp[i-1][1]) * (k - 1)
		dp[i][1] = dp[i-1][0]
	}
	return dp[n-1][0] + dp[n-1][1]
}
```

### **...**

```

```

<!-- tabs:end -->
