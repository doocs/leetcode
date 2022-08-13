# [474. 一和零](https://leetcode.cn/problems/ones-and-zeroes)

[English Version](/solution/0400-0499/0474.Ones%20and%20Zeroes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二进制字符串数组 <code>strs</code> 和两个整数 <code>m</code> 和 <code>n</code> 。</p>

<div class="MachineTrans-Lines">
<p class="MachineTrans-lang-zh-CN">请你找出并返回 <code>strs</code> 的最大子集的长度，该子集中 <strong>最多</strong> 有 <code>m</code> 个 <code>0</code> 和 <code>n</code> 个 <code>1</code> 。</p>

<p class="MachineTrans-lang-zh-CN">如果 <code>x</code> 的所有元素也是 <code>y</code> 的元素，集合 <code>x</code> 是集合 <code>y</code> 的 <strong>子集</strong> 。</p>
</div>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
<strong>输出：</strong>4
<strong>解释：</strong>最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>strs = ["10", "0", "1"], m = 1, n = 1
<strong>输出：</strong>2
<strong>解释：</strong>最大的子集是 {"0", "1"} ，所以答案是 2 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= strs.length &lt;= 600</code></li>
	<li><code>1 &lt;= strs[i].length &lt;= 100</code></li>
	<li><code>strs[i]</code>&nbsp;仅由&nbsp;<code>'0'</code> 和&nbsp;<code>'1'</code> 组成</li>
	<li><code>1 &lt;= m, n &lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

题目可以转换为 `0-1` 背包问题，在 k 个字符串中选出一些字符串（每个字符串只能使用一次），并且满足字符串最多包含 m 个 0 和 n 个 1，求满足此条件的字符串的最大长度（字符串个数）。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findMaxForm(self, strs: List[str], m: int, n: int) -> int:
        l = len(strs)
        dp = [[[0] * (n + 1) for i in range(m + 1)] for j in range(l)]
        t = [(s.count('0'), s.count('1')) for s in strs]
        n0, n1 = t[0]
        for j in range(m + 1):
            for k in range(n + 1):
                if n0 <= j and n1 <= k:
                    dp[0][j][k] = 1

        for i in range(1, l):
            n0, n1 = t[i]
            for j in range(m + 1):
                for k in range(n + 1):
                    dp[i][j][k] = dp[i - 1][j][k]
                    if n0 <= j and n1 <= k:
                        dp[i][j][k] = max(dp[i][j][k], dp[i - 1][j - n0][k - n1] + 1)

        return dp[-1][-1][-1]
```

空间优化：

```python
class Solution:
    def findMaxForm(self, strs: List[str], m: int, n: int) -> int:
        dp = [[0] * (n + 1) for _ in range(m + 1)]
        t = [(s.count('0'), s.count('1')) for s in strs]
        for k in range(len(strs)):
            n0, n1 = t[k]
            for i in range(m, n0 - 1, -1):
                for j in range(n, n1 - 1, -1):
                    dp[i][j] = max(dp[i][j], dp[i - n0][j - n1] + 1)
        return dp[-1][-1]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
public:
    int findMaxForm(vector<string>& strs, int m, int n) {
        vector<vector<int>> dp(m + 1, vector<int>(n + 1));
        for (auto s : strs)
        {
            vector<int> t = count(s);
            for (int i = m; i >= t[0]; --i)
                for (int j = n; j >= t[1]; --j)
                    dp[i][j] = max(dp[i][j], dp[i - t[0]][j - t[1]] + 1);
        }
        return dp[m][n];
    }

    vector<int> count(string s) {
        int n0 = 0;
        for (char c : s)
            if (c == '0') ++n0;
        return {n0, (int) s.size() - n0};
    }
};
```

### **C++**

```cpp
class Solution {
public:
    int findMaxForm(vector<string>& strs, int m, int n) {
        vector<vector<int>> dp(m + 1, vector<int>(n + 1));
        for (int k = 0; k < strs.size(); ++k) {
            vector<int> t = count(strs[k]);
            for (int i = m; i >= t[0]; --i)
                for (int j = n; j >= t[1]; --j)
                    dp[i][j] = max(dp[i][j], dp[i - t[0]][j - t[1]] + 1);
        }
        return dp[m][n];
    }

    vector<int> count(string s) {
        int n0 = 0;
        for (char c : s)
            if (c == '0') ++n0;
        return {n0, (int)s.size() - n0};
    }
};
```

### **Go**

```go
func findMaxForm(strs []string, m int, n int) int {
	dp := make([][]int, m+1)
	for i := 0; i < m+1; i++ {
		dp[i] = make([]int, n+1)
	}
	for _, s := range strs {
		t := count(s)
		for i := m; i >= t[0]; i-- {
			for j := n; j >= t[1]; j-- {
				dp[i][j] = max(dp[i][j], dp[i-t[0]][j-t[1]]+1)
			}
		}
	}
	return dp[m][n]
}

func count(s string) []int {
	n0 := 0
	for i := 0; i < len(s); i++ {
		if s[i] == '0' {
			n0++
		}
	}
	return []int{n0, len(s) - n0}
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
