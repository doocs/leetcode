# [1653. 使字符串平衡的最少删除次数](https://leetcode.cn/problems/minimum-deletions-to-make-string-balanced)

[English Version](/solution/1600-1699/1653.Minimum%20Deletions%20to%20Make%20String%20Balanced/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code> ，它仅包含字符 <code>'a'</code> 和 <code>'b'</code>​​​​ 。</p>

<p>你可以删除 <code>s</code> 中任意数目的字符，使得 <code>s</code> <strong>平衡</strong> 。我们称 <code>s</code> <strong>平衡的</strong> 当不存在下标对 <code>(i,j)</code> 满足 <code>i < j</code> 且 <code>s[i] = 'b'</code> 同时 <code>s[j]= 'a'</code> 。</p>

<p>请你返回使 <code>s</code> <strong>平衡</strong> 的 <strong>最少</strong> 删除次数。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>s = "aababbab"
<b>输出：</b>2
<b>解释：</b>你可以选择以下任意一种方案：
下标从 0 开始，删除第 2 和第 6 个字符（"aa<strong>b</strong>abb<strong>a</strong>b" -> "aaabbb"），
下标从 0 开始，删除第 3 和第 6 个字符（"aab<strong>a</strong>bb<strong>a</strong>b" -> "aabbbb"）。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>s = "bbaaaaabb"
<b>输出：</b>2
<b>解释：</b>唯一的最优解是删除最前面两个字符。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length <= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> 要么是 <code>'a'</code> 要么是 <code>'b'</code>​<strong> </strong>。​</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

定义 `dp[i]` 表示字符串 `s[0..i)` 达到平衡时，需要删除的最少字符数，答案为 `dp[n]`。用变量 $b$ 统计字符 `b` 的个数。

遍历字符串 $s$：

如果当前字符为 `b`，此时不影响平衡串的最小删除次数，那么 $dp[i]=dp[i-1]$，并且累加 $b$。

如果当前字符为 `a`，此时，要想达到平衡，要么把前面的所有 `b` 都删掉，操作次数为 $b$；要么把当前的 `a` 删除，操作次数为 $dp[i-1]+1$。取两者的最小值即可。即 $dp[i]=\min(b,dp[i-1]+1)$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 $s$ 的长度。

我们发现 $dp[i]$ 只与 $dp[i-1]$ 有关，因此可以使用变量 `ans` 来代替数组 `dp`。空间复杂度优化为 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimumDeletions(self, s: str) -> int:
        n = len(s)
        dp = [0] * (n + 1)
        b = 0
        for i, c in enumerate(s, 1):
            if c == 'b':
                dp[i] = dp[i - 1]
                b += 1
            else:
                dp[i] = min(dp[i - 1] + 1, b)
        return dp[n]
```

```python
class Solution:
    def minimumDeletions(self, s: str) -> int:
        ans = b = 0
        for c in s:
            if c == 'b':
                b += 1
            else:
                ans = min(b, ans + 1)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minimumDeletions(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        int b = 0;
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == 'b') {
                dp[i + 1] = dp[i];
                ++b;
            } else {
                dp[i + 1] = Math.min(dp[i] + 1, b);
            }
        }
        return dp[n];
    }
}
```

```java
class Solution {
    public int minimumDeletions(String s) {
        int ans = 0, b = 0;
        for (char c : s.toCharArray()) {
            if (c == 'b') {
                ++b;
            } else {
                ans = Math.min(b, ans + 1);
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
    int minimumDeletions(string s) {
        int n = s.size();
        vector<int> dp(n + 1);
        int b = 0;
        for (int i = 0; i < n; ++i) {
            if (s[i] == 'b') {
                dp[i + 1] = dp[i];
                ++b;
            } else {
                dp[i + 1] = min(dp[i] + 1, b);
            }
        }
        return dp[n];
    }
};
```

```cpp
class Solution {
public:
    int minimumDeletions(string s) {
        int ans = 0, b = 0;
        for (char c : s) {
            if (c == 'b') {
                ++b;
            } else {
                ans = min(b, ans + 1);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func minimumDeletions(s string) int {
	n := len(s)
	dp := make([]int, n+1)
	b := 0
	for i, c := range s {
		if c == 'b' {
			b++
			dp[i+1] = dp[i]
		} else {
			dp[i+1] = min(dp[i]+1, b)
		}
	}
	return dp[n]
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

```go
func minimumDeletions(s string) int {
	ans, b := 0, 0
	for _, c := range s {
		if c == 'b' {
			b++
		} else {
			ans = min(b, ans+1)
		}
	}
	return ans
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
