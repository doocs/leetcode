# [2430. 对字母串可执行的最大删除数](https://leetcode.cn/problems/maximum-deletions-on-a-string)

[English Version](/solution/2400-2499/2430.Maximum%20Deletions%20on%20a%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个仅由小写英文字母组成的字符串 <code>s</code> 。在一步操作中，你可以：</p>

<ul>
	<li>删除 <strong>整个字符串</strong> <code>s</code> ，或者</li>
	<li>对于满足&nbsp;<code>1 &lt;= i &lt;= s.length / 2</code> 的任意 <code>i</code> ，如果 <code>s</code> 中的 <strong>前</strong> <code>i</code> 个字母和接下来的 <code>i</code> 个字母 <strong>相等</strong> ，删除 <strong>前</strong> <code>i</code> 个字母。</li>
</ul>

<p>例如，如果 <code>s = "ababc"</code> ，那么在一步操作中，你可以删除 <code>s</code> 的前两个字母得到 <code>"abc"</code> ，因为 <code>s</code> 的前两个字母和接下来的两个字母都等于 <code>"ab"</code> 。</p>

<p>返回删除 <code>s</code> 所需的最大操作数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "abcabcdabc"
<strong>输出：</strong>2
<strong>解释：</strong>
- 删除前 3 个字母（"abc"），因为它们和接下来 3 个字母相等。现在，s = "abcdabc"。
- 删除全部字母。
一共用了 2 步操作，所以返回 2 。可以证明 2 是所需的最大操作数。
注意，在第二步操作中无法再次删除 "abc" ，因为 "abc" 的下一次出现并不是位于接下来的 3 个字母。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "aaabaab"
<strong>输出：</strong>4
<strong>解释：</strong>
- 删除第一个字母（"a"），因为它和接下来的字母相等。现在，s = "aabaab"。
- 删除前 3 个字母（"aab"），因为它们和接下来 3 个字母相等。现在，s = "aab"。 
- 删除第一个字母（"a"），因为它和接下来的字母相等。现在，s = "ab"。
- 删除全部字母。
一共用了 4 步操作，所以返回 4 。可以证明 4 是所需的最大操作数。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "aaaaa"
<strong>输出：</strong>5
<strong>解释：</strong>在每一步操作中，都可以仅删除 s 的第一个字母。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 4000</code></li>
	<li><code>s</code> 仅由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划 + 最长公共前缀**

我们定义 $dp[i]$ 表示删除后缀 $s[i..]$ 字符所需的最大操作数。答案为 $dp[0]$。

显然 $dp[i]$ 可以通过 $dp[i+j]$ 转移得到，其中 $j$ 是字符串长度，满足 $1 \leq j \leq (n-i / 2)$。

如果 $s[i..i+j] = s[i+j..i+j+j]$，那么我们可以删除 $s[i..i+j]$，此时 $dp[i]$ 可以转移到 $dp[i+j]$。求 $dp[i]$ 的最大值即可。

这里我们需要快速判断 $s[i..i+j] = s[i+j..i+j+j]$，我们可以使用最长公共前缀的方法，使用 $O(n^2)$ 预处理得到 $lcp[i][j]$，表示从 $i$ 开始和从 $j$ 开始的最长公共前缀的长度，即 $lcp[i][j]$ 表示 $s[i..]$ 和 $s[j..]$ 的最长公共前缀的长度。然后我们就可以在 $O(1)$ 的时间内判断 $s[i..i+j] = s[i+j..i+j+j]$。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 为字符串 $s$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def deleteString(self, s: str) -> int:
        @cache
        def dfs(i):
            if i == n:
                return 0
            ans = 1
            m = (n - i) >> 1
            for j in range(1, m + 1):
                if s[i: i + j] == s[i + j: i + j + j]:
                    ans = max(ans, 1 + dfs(i + j))
            return ans

        n = len(s)
        return dfs(0)
```

```python
class Solution:
    def deleteString(self, s: str) -> int:
        n = len(s)
        lcp = [[0] * (n + 1) for _ in range(n + 1)]
        for i in range(n - 1, -1, -1):
            for j in range(n - 1, -1, -1):
                if s[i] == s[j]:
                    lcp[i][j] = 1 + lcp[i + 1][j + 1]
        dp = [1] * n
        for i in range(n - 1, -1, -1):
            for j in range(1, (n - i) // 2 + 1):
                if lcp[i][i + j] >= j:
                    dp[i] = max(dp[i], dp[i + j] + 1)
        return dp[0]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int deleteString(String s) {
        int n = s.length();
        int[][] lcp = new int[n + 1][n + 1];
        for (int i = n - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (s.charAt(i) == s.charAt(j)) {
                    lcp[i][j] = 1 + lcp[i + 1][j + 1];
                }
            }
        }
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = n - 1; i >= 0; --i) {
            for (int j = 1; j <= (n - i) / 2; ++j) {
                if (lcp[i][i + j] >= j) {
                    dp[i] = Math.max(dp[i], dp[i + j] + 1);
                }
            }
        }
        return dp[0];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int deleteString(string s) {
        int n = s.size();
        int lcp[n + 1][n + 1];
        memset(lcp, 0, sizeof lcp);
        for (int i = n - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (s[i] == s[j]) {
                    lcp[i][j] = 1 + lcp[i + 1][j + 1];
                }
            }
        }
        int dp[n];
        for (int i = n - 1; i >= 0; --i) {
            dp[i] = 1;
            for (int j = 1; j <= (n - i) / 2; ++j) {
                if (lcp[i][i + j] >= j) {
                    dp[i] = max(dp[i], dp[i + j] + 1);
                }
            }
        }
        return dp[0];
    }
};
```

### **Go**

```go
func deleteString(s string) int {
	n := len(s)
	lcp := make([][]int, n+1)
	for i := range lcp {
		lcp[i] = make([]int, n+1)
	}
	for i := n - 1; i >= 0; i-- {
		for j := n - 1; j >= 0; j-- {
			if s[i] == s[j] {
				lcp[i][j] = 1 + lcp[i+1][j+1]
			}
		}
	}
	dp := make([]int, n)
	for i := n - 1; i >= 0; i-- {
		dp[i] = 1
		for j := 1; j <= (n-i)/2; j++ {
			if lcp[i][i+j] >= j {
				dp[i] = max(dp[i], dp[i+j]+1)
			}
		}
	}
	return dp[0]
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
