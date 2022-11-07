# [1682. 最长回文子序列 II](https://leetcode.cn/problems/longest-palindromic-subsequence-ii)

[English Version](/solution/1600-1699/1682.Longest%20Palindromic%20Subsequence%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>字符串&nbsp;<code>s</code>&nbsp;的某个子序列符合下列条件时，称为“<strong>好的回文子序列</strong>”：</p>

<ul>
	<li>它是&nbsp;<code>s</code>&nbsp;的子序列。</li>
	<li>它是回文序列（反转后与原序列相等）。</li>
	<li>长度为<strong>偶数</strong>。</li>
	<li>除中间的两个字符外，其余任意两个连续字符不相等。</li>
</ul>

<p>例如，若&nbsp;<code>s = "abcabcabb"</code>，则&nbsp;<code>"abba"</code>&nbsp;可称为“好的回文子序列”，而&nbsp;<code>"bcb"</code>&nbsp;（长度不是偶数）和&nbsp;<code>"bbbb"</code>&nbsp;（含有相等的连续字符）不能称为“好的回文子序列”。</p>

<p>给定一个字符串&nbsp;<code>s</code>， 返回<em>&nbsp;</em><code>s</code>&nbsp;的<strong>最长“好的回文子序列”</strong>的<strong>长度</strong>。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> s = "bbabab"
<strong>输出:</strong> 4
<strong>解释:</strong> s 的最长“好的回文子序列”是 "baab"。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> s = "dcbccacdb"
<strong>输出:</strong> 4
<strong>解释:</strong> s 的最长“好的回文子序列”是 "dccd"。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 250</code></li>
	<li><code>s</code>&nbsp;包含小写英文字母。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：记忆化搜索**

我们设计一个函数 $dfs(i, j, x)$ 表示字符串 $s$ 中下标范围 $[i, j]$ 内，且以字符 $x$ 结尾的最长“好的回文子序列”的长度。答案为 $dfs(0, n - 1, 26)$。

函数 $dfs(i, j, x)$ 的计算过程如下：

-   如果 $i >= j$，则 $dfs(i, j, x) = 0$；
-   如果 $s[i] = s[j]$，且 $s[i] \neq x$，那么 $dfs(i, j, x) = dfs(i + 1, j - 1, s[i]) + 2$；
-   如果 $s[i] \neq s[j]$，那么 $dfs(i, j, x) = max(dfs(i + 1, j, x), dfs(i, j - 1, x))$。

过程中，我们可以使用记忆化搜索的方式，避免重复计算。

时间复杂度 $O(n^2 \times C)$。其中 $n$ 为字符串 $s$ 的长度，而 $C$ 为字符集大小。本题中 $C = 26$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def longestPalindromeSubseq(self, s: str) -> int:
        @cache
        def dfs(i, j, x):
            if i >= j:
                return 0
            if s[i] == s[j] and s[i] != x:
                return dfs(i + 1, j - 1, s[i]) + 2
            return max(dfs(i + 1, j, x), dfs(i, j - 1, x))

        ans = dfs(0, len(s) - 1, '')
        dfs.cache_clear()
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[][][] f;
    private String s;

    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        this.s = s;
        f = new int[n][n][27];
        for (var a : f) {
            for (var b : a) {
                Arrays.fill(b, -1);
            }
        }
        return dfs(0, n - 1, 26);
    }

    private int dfs(int i, int j, int x) {
        if (i >= j) {
            return 0;
        }
        if (f[i][j][x] != -1) {
            return f[i][j][x];
        }
        int ans = 0;
        if (s.charAt(i) == s.charAt(j) && s.charAt(i) - 'a' != x) {
            ans = dfs(i + 1, j - 1, s.charAt(i) - 'a') + 2;
        } else {
            ans = Math.max(dfs(i + 1, j, x), dfs(i, j - 1, x));
        }
        f[i][j][x] = ans;
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int f[251][251][27];

    int longestPalindromeSubseq(string s) {
        int n = s.size();
        memset(f, -1, sizeof f);
        function<int(int, int, int)> dfs = [&](int i, int j, int x) -> int {
            if (i >= j) return 0;
            if (f[i][j][x] != -1) return f[i][j][x];
            int ans = 0;
            if (s[i] == s[j] && s[i] - 'a' != x) ans = dfs(i + 1, j - 1, s[i] - 'a') + 2;
            else ans = max(dfs(i + 1, j, x), dfs(i, j - 1, x));
            f[i][j][x] = ans;
            return ans;
        };
        return dfs(0, n - 1, 26);
    }
};
```

### **Go**

```go
func longestPalindromeSubseq(s string) int {
	n := len(s)
	f := make([][][]int, n)
	for i := range f {
		f[i] = make([][]int, n)
		for j := range f[i] {
			f[i][j] = make([]int, 27)
			for k := range f[i][j] {
				f[i][j][k] = -1
			}
		}
	}
	var dfs func(i, j, x int) int
	dfs = func(i, j, x int) int {
		if i >= j {
			return 0
		}
		if f[i][j][x] != -1 {
			return f[i][j][x]
		}
		ans := 0
		if s[i] == s[j] && int(s[i]-'a') != x {
			ans = dfs(i+1, j-1, int(s[i]-'a')) + 2
		} else {
			ans = max(dfs(i+1, j, x), dfs(i, j-1, x))
		}
		f[i][j][x] = ans
		return ans
	}
	return dfs(0, n-1, 26)
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
