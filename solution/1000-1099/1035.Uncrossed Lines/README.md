# [1035. 不相交的线](https://leetcode-cn.com/problems/uncrossed-lines)

[English Version](/solution/1000-1099/1035.Uncrossed%20Lines/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>我们在两条独立的水平线上按给定的顺序写下&nbsp;<code>A</code>&nbsp;和&nbsp;<code>B</code>&nbsp;中的整数。</p>

<p>现在，我们可以绘制一些连接两个数字&nbsp;<code>A[i]</code>&nbsp;和&nbsp;<code>B[j]</code>&nbsp;的直线，只要&nbsp;<code>A[i] == B[j]</code>，且我们绘制的直线不与任何其他连线（非水平线）相交。</p>

<p>以这种方法绘制线条，并返回我们可以绘制的最大连线数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1035.Uncrossed%20Lines/images/142.png" style="height: 72px; width: 100px;"></strong></p>

<pre><strong>输入：</strong>A = [1,4,2], B = [1,2,4]
<strong>输出：</strong>2
<strong>解释：
</strong>我们可以画出两条不交叉的线，如上图所示。
我们无法画出第三条不相交的直线，因为从 A[1]=4 到 B[2]=4 的直线将与从 A[2]=2 到 B[1]=2 的直线相交。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>A = [2,5,1,2,5], B = [10,5,2,1,5,2]
<strong>输出：</strong>3
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>A = [1,3,7,1,7,5], B = [1,9,2,5,1]
<strong>输出：</strong>2</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= A.length &lt;= 500</code></li>
	<li><code>1 &lt;= B.length &lt;= 500</code></li>
	<li><code>1 &lt;= A[i], B[i] &lt;= 2000</code></li>
</ol>

<p>&nbsp;</p>


## 解法

<!-- 这里可写通用的实现逻辑 -->

最长公共子序列问题

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxUncrossedLines(self, nums1: List[int], nums2: List[int]) -> int:
        m, n = len(nums1), len(nums2)
        dp = [[0] * (n + 1) for i in range(m + 1)]
        for i in range(1, m + 1):
            for j in range(1, n + 1):
                if nums1[i - 1] == nums2[j - 1]:
                    dp[i][j] = dp[i - 1][j - 1] + 1
                else:
                    dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])
        return dp[m][n]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
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
    int maxUncrossedLines(vector<int>& nums1, vector<int>& nums2) {
        int m = nums1.size(), n = nums2.size();
        vector<vector<int>> dp(m + 1, vector<int>(n + 1));
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
};
```

### **...**

```

```

<!-- tabs:end -->
