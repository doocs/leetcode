# [375. 猜数字大小 II](https://leetcode-cn.com/problems/guess-number-higher-or-lower-ii)

[English Version](/solution/0300-0399/0375.Guess%20Number%20Higher%20or%20Lower%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>我们正在玩一个猜数游戏，游戏规则如下：</p>

<p>我从&nbsp;<strong>1&nbsp;</strong>到 <strong>n</strong> 之间选择一个数字，你来猜我选了哪个数字。</p>

<p>每次你猜错了，我都会告诉你，我选的数字比你的大了或者小了。</p>

<p>然而，当你猜了数字 x 并且猜错了的时候，你需要支付金额为 x 的现金。直到你猜到我选的数字，你才算赢得了这个游戏。</p>

<p><strong>示例:</strong></p>

<pre>n = 10, 我选择了8.

第一轮: 你猜我选择的数字是5，我会告诉你，我的数字更大一些，然后你需要支付5块。
第二轮: 你猜是7，我告诉你，我的数字更大一些，你支付7块。
第三轮: 你猜是9，我告诉你，我的数字更小一些，你支付9块。

游戏结束。8 就是我选的数字。

你最终要支付 5 + 7 + 9 = 21 块钱。
</pre>

<p>给定&nbsp;<strong>n &ge; 1，</strong>计算你至少需要拥有多少现金才能确保你能赢得这个游戏。</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

区间 DP。

- 状态表示：`dp[i][j]` 表示数字区间 `[i, j]` 确保赢得游戏的最少现金。
- 状态计算：枚举闭区间 `[i, j]` 中以数字 k 作为选择的数字。那么 `dp[i][j] = min(dp[i][j], max(dp[i][k - 1], dp[k + 1][j]) + k), k ∈ [i, j]`。

以区间长度 l 从小到大开始处理每个状态值。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def getMoneyAmount(self, n: int) -> int:
        dp = [[0] * (n + 10) for _ in range(n + 10)]
        for l in range(2, n + 1):
            for i in range(1, n - l + 2):
                j = i + l - 1
                dp[i][j] = float('inf')
                for k in range(i, j + 1):
                    t = max(dp[i][k - 1], dp[k + 1][j]) + k
                    dp[i][j] = min(dp[i][j], t)
        return dp[1][n]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 10][n + 10];
        for (int l = 2; l <= n; ++l) {
            for (int i = 1; i + l - 1 <= n; ++i) {
                int j = i + l - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j; ++k) {
                    int t = Math.max(dp[i][k - 1], dp[k + 1][j]) + k;
                    dp[i][j] = Math.min(dp[i][j], t);
                }
            }
        }
        return dp[1][n];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int getMoneyAmount(int n) {
        vector<vector<int>> dp(n + 10, vector<int>(n + 10));
        for (int l = 2; l <= n; ++l)
        {
            for (int i = 1; i + l - 1 <= n; ++i)
            {
                int j = i + l - 1;
                dp[i][j] = INT_MAX;
                for (int k = i; k <= j; ++k)
                {
                    int t = max(dp[i][k - 1], dp[k + 1][j]) + k;
                    dp[i][j] = min(dp[i][j], t);
                }
            }
        }
        return dp[1][n];
    }
};
```

### **Go**

```go
func getMoneyAmount(n int) int {
	dp := make([][]int, n+10)
	for i := 0; i < len(dp); i++ {
		dp[i] = make([]int, n+10)
	}
	for l := 2; l <= n; l++ {
		for i := 1; i+l-1 <= n; i++ {
			j := i + l - 1
			dp[i][j] = math.MaxInt32
			for k := i; k <= j; k++ {
				t := max(dp[i][k-1], dp[k+1][j]) + k
				dp[i][j] = min(dp[i][j], t)
			}
		}
	}
	return dp[1][n]
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
```

### **...**

```

```

<!-- tabs:end -->
