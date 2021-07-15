# [96. 不同的二叉搜索树](https://leetcode-cn.com/problems/unique-binary-search-trees)

[English Version](/solution/0000-0099/0096.Unique%20Binary%20Search%20Trees/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数 <em>n</em>，求以&nbsp;1 ...&nbsp;<em>n</em>&nbsp;为节点组成的二叉搜索树有多少种？</p>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong> 3
<strong>输出:</strong> 5
<strong>解释:
</strong>给定 <em>n</em> = 3, 一共有 5 种不同结构的二叉搜索树:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

假设 n 个节点存在二叉搜索树的个数是 `G(n)`，1 为根节点，2 为根节点，...，n 为根节点，当 1 为根节点时，其左子树节点个数为 0，右子树节点个数为 n-1，同理当 2 为根节点时，其左子树节点个数为 1，右子树节点为 n-2，所以可得 `G(n) = G(0) * G(n-1) + G(1) * (n-2) + ... + G(n-1) * G(0)`。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numTrees(self, n: int) -> int:
        dp = [0] * (n + 1)
        dp[0] = 1
        for i in range(1, n + 1):
            for j in range(i):
                dp[i] += dp[j] * dp[i - j - 1]
        return dp[-1]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < i; ++j) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numTrees(int n) {
        vector<int> dp(n + 1);
        dp[0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < i; ++j) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }
};
```

### **Go**

```go
func numTrees(n int) int {
	dp := make([]int, n+1)
	dp[0] = 1
	for i := 1; i <= n; i++ {
		for j := 0; j < i; j++ {
			dp[i] += dp[j] * dp[i-j-1]
		}
	}
	return dp[n]
}
```

### **...**

```

```

<!-- tabs:end -->
