# [1771. 由子序列构造的最长回文串的长度](https://leetcode.cn/problems/maximize-palindrome-length-from-subsequences)

[English Version](/solution/1700-1799/1771.Maximize%20Palindrome%20Length%20From%20Subsequences/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个字符串 <code>word1</code> 和 <code>word2</code> ，请你按下述方法构造一个字符串：</p>

<ul>
	<li>从 <code>word1</code> 中选出某个 <strong>非空</strong> 子序列 <code>subsequence1</code> 。</li>
	<li>从 <code>word2</code> 中选出某个 <strong>非空</strong> 子序列 <code>subsequence2</code> 。</li>
	<li>连接两个子序列 <code>subsequence1 + subsequence2</code> ，得到字符串。</li>
</ul>

<p>返回可按上述方法构造的最长 <strong>回文串</strong> 的 <strong>长度</strong> 。如果无法构造回文串，返回 <code>0</code> 。</p>

<p>字符串 <code>s</code> 的一个 <strong>子序列</strong> 是通过从 <code>s</code> 中删除一些（也可能不删除）字符而不更改其余字符的顺序生成的字符串。</p>

<p><strong>回文串</strong> 是正着读和反着读结果一致的字符串。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>word1 = "cacb", word2 = "cbba"
<strong>输出：</strong>5
<strong>解释：</strong>从 word1 中选出 "ab" ，从 word2 中选出 "cba" ，得到回文串 "abcba" 。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>word1 = "ab", word2 = "ab"
<strong>输出：</strong>3
<strong>解释：</strong>从 word1 中选出 "ab" ，从 word2 中选出 "a" ，得到回文串 "aba" 。</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>word1 = "aa", word2 = "bb"
<strong>输出：</strong>0
<strong>解释：</strong>无法按题面所述方法构造回文串，所以返回 0 。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= word1.length, word2.length &lt;= 1000</code></li>
	<li><code>word1</code> 和 <code>word2</code> 由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

我们首先将字符串 `word1` 和 `word2` 连接起来，得到字符串 $s$，然后我们可以将问题转化为求字符串 $s$ 的最长回文子序列的长度。只不过这里在算最后的答案时，需要保证回文字符串中，至少有一个字符来自 `word1`，另一个字符来自 `word2`。

我们定义 $f[i][j]$ 表示字符串 $s$ 中下标范围在 $[i, j]$ 内的子串的最长回文子序列的长度。

如果 $s[i] = s[j]$，那么 $s[i]$ 和 $s[j]$ 一定在最长回文子序列中，此时 $f[i][j] = f[i + 1][j - 1] + 2$，这时候我们还需要判断 $s[i]$ 和 $s[j]$ 是否来自 `word1` 和 `word2`，如果是，我们将答案的最大值更新为 $ans=\max(ans, f[i][j])$。

如果 $s[i] \neq s[j]$，那么 $s[i]$ 和 $s[j]$ 一定不会同时出现在最长回文子序列中，此时 $f[i][j] = max(f[i + 1][j], f[i][j - 1])$。

最后我们返回答案即可。

时间复杂度为 $O(n^2)$，其中 $n$ 是字符串 $s$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def longestPalindrome(self, word1: str, word2: str) -> int:
        s = word1 + word2
        n = len(s)
        f = [[0] * n for _ in range(n)]
        for i in range(n):
            f[i][i] = 1
        ans = 0
        for i in range(n - 1, -1, -1):
            for j in range(i + 1, n):
                if s[i] == s[j]:
                    f[i][j] = f[i + 1][j - 1] + 2
                    if i < len(word1) and j >= len(word1):
                        ans = max(ans, f[i][j])
                else:
                    f[i][j] = max(f[i + 1][j], f[i][j - 1])
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int longestPalindrome(String word1, String word2) {
        String s = word1 + word2;
        int n = s.length();
        int[][] f = new int[n][n];
        for (int i = 0; i < n; ++i) {
            f[i][i] = 1;
        }
        int ans = 0;
        for (int i = n - 2; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                if (s.charAt(i) == s.charAt(j)) {
                    f[i][j] = f[i + 1][j - 1] + 2;
                    if (i < word1.length() && j >= word1.length()) {
                        ans = Math.max(ans, f[i][j]);
                    }
                } else {
                    f[i][j] = Math.max(f[i + 1][j], f[i][j - 1]);
                }
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
    int longestPalindrome(string word1, string word2) {
        string s = word1 + word2;
        int n = s.size();
        int f[n][n];
        memset(f, 0, sizeof f);
        for (int i = 0; i < n; ++i) f[i][i] = 1;
        int ans = 0;
        for (int i = n - 2; ~i; --i) {
            for (int j = i + 1; j < n; ++j) {
                if (s[i] == s[j]) {
                    f[i][j] = f[i + 1][j - 1] + 2;
                    if (i < word1.size() && j >= word1.size()) {
                        ans = max(ans, f[i][j]);
                    }
                } else {
                    f[i][j] = max(f[i + 1][j], f[i][j - 1]);
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func longestPalindrome(word1 string, word2 string) (ans int) {
	s := word1 + word2
	n := len(s)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, n)
		f[i][i] = 1
	}
	for i := n - 2; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			if s[i] == s[j] {
				f[i][j] = f[i+1][j-1] + 2
				if i < len(word1) && j >= len(word1) && ans < f[i][j] {
					ans = f[i][j]
				}
			} else {
				f[i][j] = max(f[i+1][j], f[i][j-1])
			}
		}
	}
	return ans
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
