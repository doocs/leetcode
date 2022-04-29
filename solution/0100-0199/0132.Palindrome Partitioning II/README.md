# [132. 分割回文串 II](https://leetcode.cn/problems/palindrome-partitioning-ii)

[English Version](/solution/0100-0199/0132.Palindrome%20Partitioning%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code>，请你将 <code>s</code> 分割成一些子串，使每个子串都是回文。</p>

<p>返回符合要求的 <strong>最少分割次数</strong> 。</p>

<div class="original__bRMd">
<div>
<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "aab"
<strong>输出：</strong>1
<strong>解释：</strong>只需一次分割就可将 <em>s </em>分割成 ["aa","b"] 这样两个回文子串。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "a"
<strong>输出：</strong>0
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "ab"
<strong>输出：</strong>1
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length <= 2000</code></li>
	<li><code>s</code> 仅由小写英文字母组成</li>
</ul>
</div>
</div>

## 解法

<!-- 这里可写通用的实现逻辑 -->

两次 dp，`dp1[i][j]` 表示 i ~ j 的子串是否是回文，可以参考 [5. 最长回文子串](../../0000-0099/0005.Longest%20Palindromic%20Substring/README.md)。`dp2[i]` 表示以 i 结尾的子串最少需要分割几次，如果本来就是回文串（`dp[0][i] == true`）就不需要分割，否则枚举分割点 `j`

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minCut(self, s: str) -> int:
        n = len(s)
        dp1 = [[False] * n for _ in range(n)]
        for i in range(n - 1, -1, -1):
            for j in range(i, n):
                dp1[i][j] = s[i] == s[j] and (j - i < 3 or dp1[i + 1][j - 1])
        dp2 = [0] * n
        for i in range(n):
            if not dp1[0][i]:
                dp2[i] = i
                for j in range(1, i + 1):
                    if dp1[j][i]:
                        dp2[i] = min(dp2[i], dp2[j - 1] + 1)
        return dp2[-1]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minCut(String s) {
        int n = s.length();
        boolean[][] dp1 = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                dp1[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp1[i + 1][j - 1]);
            }
        }
        int[] dp2 = new int[n];
        for (int i = 0; i < n; i++) {
            if (!dp1[0][i]) {
                dp2[i] = i;
                for (int j = 1; j <= i; j++) {
                    if (dp1[j][i]) {
                        dp2[i] = Math.min(dp2[i], dp2[j - 1] + 1);
                    }
                }
            }
        }
        return dp2[n - 1];
    }
}
```

### **Go**

```go
func minCut(s string) int {
	n := len(s)
	dp1 := make([][]bool, n)
	for i := 0; i < n; i++ {
		dp1[i] = make([]bool, n)
	}
	for i := n - 1; i >= 0; i-- {
		for j := i; j < n; j++ {
			dp1[i][j] = s[i] == s[j] && (j-i < 3 || dp1[i+1][j-1])
		}
	}
	dp2 := make([]int, n)
	for i := 0; i < n; i++ {
		if !dp1[0][i] {
			dp2[i] = i
			for j := 1; j <= i; j++ {
				if dp1[j][i] {
					dp2[i] = min(dp2[i], dp2[j-1]+1)
				}
			}
		}
	}
	return dp2[n-1]
}

func min(x, y int) int {
	if x < y {
		return x
	}
	return y
}
```

### **C++**

```cpp
class Solution {
public:
    int minCut(string s) {
        int n = s.size();
        vector<vector<bool>> dp1(n, vector<bool>(n));
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i; j < n; ++j) {
                dp1[i][j] = s[i] == s[j] && (j - i < 3 || dp1[i + 1][j - 1]);
            }
        }
        vector<int> dp2(n);
        for (int i = 0; i < n; ++i) {
            if (!dp1[0][i]) {
                dp2[i] = i;
                for (int j = 1; j <= i; ++j) {
                    if (dp1[j][i]) {
                        dp2[i] = min(dp2[i], dp2[j - 1] + 1);
                    }
                }
            }
        }
        return dp2[n - 1];
    }
};
```

### **...**

```

```

<!-- tabs:end -->
