# [1638. 统计只差一个字符的子串数目](https://leetcode.cn/problems/count-substrings-that-differ-by-one-character)

[English Version](/solution/1600-1699/1638.Count%20Substrings%20That%20Differ%20by%20One%20Character/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个字符串&nbsp;<code>s</code> 和&nbsp;<code>t</code>&nbsp;，请你找出 <code>s</code>&nbsp;中的非空子串的数目，这些子串满足替换 <strong>一个不同字符</strong>&nbsp;以后，是 <code>t</code>&nbsp;串的子串。换言之，请你找到 <code>s</code>&nbsp;和 <code>t</code>&nbsp;串中 <strong>恰好</strong>&nbsp;只有一个字符不同的子字符串对的数目。</p>

<p>比方说，&nbsp;<code>"<u>compute</u>r"</code>&nbsp;and&nbsp;<code>"<u>computa</u>tion"&nbsp;</code>只有一个字符不同：&nbsp;<code>'e'</code>/<code>'a'</code>&nbsp;，所以这一对子字符串会给答案加 1 。</p>

<p>请你返回满足上述条件的不同子字符串对数目。</p>

<p>一个 <strong>子字符串</strong>&nbsp;是一个字符串中连续的字符。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>s = "aba", t = "baba"
<b>输出：</b>6
<strong>解释：</strong>以下为只相差 1 个字符的 s 和 t 串的子字符串对：
("<strong>a</strong>ba", "<strong>b</strong>aba")
("<strong>a</strong>ba", "ba<strong>b</strong>a")
("ab<strong>a</strong>", "<strong>b</strong>aba")
("ab<strong>a</strong>", "ba<strong>b</strong>a")
("a<strong>b</strong>a", "b<strong>a</strong>ba")
("a<strong>b</strong>a", "bab<strong>a</strong>")
加粗部分分别表示 s 和 t 串选出来的子字符串。
</pre>

<strong>示例 2：</strong>

<pre>
<b>输入：</b>s = "ab", t = "bb"
<b>输出：</b>3
<strong>解释：</strong>以下为只相差 1 个字符的 s 和 t 串的子字符串对：
("<strong>a</strong>b", "<strong>b</strong>b")
("<strong>a</strong>b", "b<strong>b</strong>")
("<strong>ab</strong>", "<strong>bb</strong>")
加粗部分分别表示 s 和 t 串选出来的子字符串。
</pre>

<strong>示例 3：</strong>

<pre>
<b>输入：</b>s = "a", t = "a"
<b>输出：</b>0
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<b>输入：</b>s = "abe", t = "bbc"
<b>输出：</b>10
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length, t.length &lt;= 100</code></li>
	<li><code>s</code> 和&nbsp;<code>t</code>&nbsp;都只包含小写英文字母。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：枚举**

我们可以枚举字符串 $s$ 和 $t$ 中不同的那个字符位置，然后分别向两边扩展，直到遇到不同的字符为止，这样就可以得到以该位置为中心的满足条件的子串对数目。我们记左边扩展的相同字符个数为 $l$，右边扩展的相同字符个数为 $r$，那么以该位置为中心的满足条件的子串对数目为 $(l + 1) \times (r + 1)$，累加到答案中即可。

枚举结束后，即可得到答案。

时间复杂度 $O(m \times n \times \min(m, n))$，空间复杂度 $O(1)$。其中 $m$ 和 $n$ 分别为字符串 $s$ 和 $t$ 的长度。

**方法二：预处理 + 枚举**

方法一中，我们每次需要分别往左右两边扩展，得出 $l$ 和 $r$ 的值。实际上，我们可以预处理出以每个位置 $(i, j)$ 结尾的最长相同后缀的长度，以及以每个位置 $(i, j)$ 开头的最长相同前缀的长度，分别记录在数组 $f$ 和 $g$ 中。

接下来，与方法一类似，我们枚举字符串 $s$ 和 $t$ 中不同的那个字符位置 $(i, j)$，那么以该位置为中心的满足条件的子串对数目为 $(f[i][j] + 1) \times (g[i + 1][j + 1] + 1)$，累加到答案中即可。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别为字符串 $s$ 和 $t$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countSubstrings(self, s: str, t: str) -> int:
        ans = 0
        m, n = len(s), len(t)
        for i, a in enumerate(s):
            for j, b in enumerate(t):
                if a != b:
                    l = r = 0
                    while i > l and j > l and s[i - l - 1] == t[j - l - 1]:
                        l += 1
                    while i + r + 1 < m and j + r + 1 < n and s[i + r + 1] == t[j + r + 1]:
                        r += 1
                    ans += (l + 1) * (r + 1)
        return ans
```

```python
class Solution:
    def countSubstrings(self, s: str, t: str) -> int:
        ans = 0
        m, n = len(s), len(t)
        f = [[0] * (n + 1) for _ in range(m + 1)]
        g = [[0] * (n + 1) for _ in range(m + 1)]
        for i, a in enumerate(s, 1):
            for j, b in enumerate(t, 1):
                if a == b:
                    f[i][j] = f[i - 1][j - 1] + 1
        for i in range(m - 1, -1, -1):
            for j in range(n - 1, -1, -1):
                if s[i] == t[j]:
                    g[i][j] = g[i + 1][j + 1] + 1
                else:
                    ans += (f[i][j] + 1) * (g[i + 1][j + 1] + 1)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int countSubstrings(String s, String t) {
        int ans = 0;
        int m = s.length(), n = t.length();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (s.charAt(i) != t.charAt(j)) {
                    int l = 0, r = 0;
                    while (i - l > 0 && j - l > 0 && s.charAt(i - l - 1) == t.charAt(j - l - 1)) {
                        ++l;
                    }
                    while (i + r + 1 < m && j + r + 1 < n && s.charAt(i + r + 1) == t.charAt(j + r + 1)) {
                        ++r;
                    }
                    ans += (l + 1) * (r + 1);
                }
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int countSubstrings(String s, String t) {
        int ans = 0;
        int m = s.length(), n = t.length();
        int[][] f = new int[m + 1][n + 1];
        int[][] g = new int[m + 1][n + 1];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (s.charAt(i) == t.charAt(j)) {
                    f[i + 1][j + 1] = f[i][j] + 1;
                }
            }
        }
        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (s.charAt(i) == t.charAt(j)) {
                    g[i][j] = g[i + 1][j + 1] + 1;
                } else {
                    ans += (f[i][j] + 1) * (g[i + 1][j + 1] + 1);
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
    int countSubstrings(string s, string t) {
        int ans = 0;
        int m = s.size(), n = t.size();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (s[i] != t[j]) {
                    int l = 0, r = 0;
                    while (i - l > 0 && j - l > 0 && s[i - l - 1] == t[j - l - 1]) {
                        ++l;
                    }
                    while (i + r + 1 < m && j + r + 1 < n && s[i + r + 1] == t[j + r + 1]) {
                        ++r;
                    }
                    ans += (l + 1) * (r + 1);
                }
            }
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int countSubstrings(string s, string t) {
        int ans = 0;
        int m = s.length(), n = t.length();
        int f[m + 1][n + 1];
        int g[m + 1][n + 1];
        memset(f, 0, sizeof(f));
        memset(g, 0, sizeof(g));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (s[i] == t[j]) {
                    f[i + 1][j + 1] = f[i][j] + 1;
                }
            }
        }
        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (s[i] == t[j]) {
                    g[i][j] = g[i + 1][j + 1] + 1;
                } else {
                    ans += (f[i][j] + 1) * (g[i + 1][j + 1] + 1);
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func countSubstrings(s string, t string) (ans int) {
	m, n := len(s), len(t)
	for i, a := range s {
		for j, b := range t {
			if a != b {
				l, r := 0, 0
				for i > l && j > l && s[i-l-1] == t[j-l-1] {
					l++
				}
				for i+r+1 < m && j+r+1 < n && s[i+r+1] == t[j+r+1] {
					r++
				}
				ans += (l + 1) * (r + 1)
			}
		}
	}
	return
}
```

```go
func countSubstrings(s string, t string) (ans int) {
	m, n := len(s), len(t)
	f := make([][]int, m+1)
	g := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, n+1)
		g[i] = make([]int, n+1)
	}
	for i, a := range s {
		for j, b := range t {
			if a == b {
				f[i+1][j+1] = f[i][j] + 1
			}
		}
	}
	for i := m - 1; i >= 0; i-- {
		for j := n - 1; j >= 0; j-- {
			if s[i] == t[j] {
				g[i][j] = g[i+1][j+1] + 1
			} else {
				ans += (f[i][j] + 1) * (g[i+1][j+1] + 1)
			}
		}
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
