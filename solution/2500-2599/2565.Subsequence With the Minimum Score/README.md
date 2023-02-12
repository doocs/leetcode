# [2565. 最少得分子序列](https://leetcode.cn/problems/subsequence-with-the-minimum-score)

[English Version](/solution/2500-2599/2565.Subsequence%20With%20the%20Minimum%20Score/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个字符串&nbsp;<code>s</code> 和&nbsp;<code>t</code>&nbsp;。</p>

<p>你可以从字符串 <code>t</code>&nbsp;中删除任意数目的字符。</p>

<p>如果没有从字符串&nbsp;<code>t</code>&nbsp;中删除字符，那么得分为&nbsp;<code>0</code>&nbsp;，否则：</p>

<ul>
	<li>令&nbsp;<code>left</code>&nbsp;为删除字符中的最小下标。</li>
	<li>令&nbsp;<code>right</code>&nbsp;为删除字符中的最大下标。</li>
</ul>

<p>字符串的得分为&nbsp;<code>right - left + 1</code>&nbsp;。</p>

<p>请你返回使<em>&nbsp;</em><code>t</code><em> </em>成为<em>&nbsp;</em><code>s</code>&nbsp;子序列的最小得分。</p>

<p>一个字符串的 <strong>子序列</strong>&nbsp;是从原字符串中删除一些字符后（也可以一个也不删除），剩余字符不改变顺序得到的字符串。（比方说&nbsp;<code>"ace"</code> 是&nbsp;<code>"<strong><em>a</em></strong>b<strong><em>c</em></strong>d<strong><em>e</em></strong>"</code>&nbsp;的子序列，但是&nbsp;<code>"aec"</code>&nbsp;不是）。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>s = "abacaba", t = "bzaa"
<b>输出：</b>1
<b>解释：</b>这个例子中，我们删除下标 1 处的字符 "z" （下标从 0 开始）。
字符串 t 变为 "baa" ，它是字符串 "abacaba" 的子序列，得分为 1 - 1 + 1 = 1 。
1 是能得到的最小得分。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>s = "cde", t = "xyz"
<b>输出：</b>3
<b>解释：</b>这个例子中，我们将下标为 0， 1 和 2 处的字符 "x" ，"y" 和 "z" 删除（下标从 0 开始）。
字符串变成 "" ，它是字符串 "cde" 的子序列，得分为 2 - 0 + 1 = 3 。
3 是能得到的最小得分。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length, t.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 和&nbsp;<code>t</code>&nbsp;都只包含小写英文字母。</li>
</ul>

<p>&nbsp;</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：前后缀预处理 + 二分查找**

根据题目我们知道，删除字符的下标范围是 `[left, right]`，最优的做法一定是删除 `[left, right]` 范围内的所有字符。也就是说，我们要删除字符串 $t$ 中的一个子串，使得字符串 $t$ 的剩余前缀可以匹配字符串 $s$ 的前缀，字符串 $t$ 的剩余后缀可以匹配字符串 $s$ 的后缀，且字符串 $s$ 的前后缀不相交。注意，这里的匹配指的是子序列匹配。

因此，我们可以先预处理得到数组 $f$ 和 $g$，其中 $f[i]$ 表示字符串 $t$ 的前缀 $t[0,..i]$ 中，最少与字符串前 $[0,..f[i]]$ 个字符匹配；同理 $g[i]$ 表示字符串 $t$ 的后缀 $t[i,..n-1]$ 中，最多与字符串后 $[g[i],..n-1]$ 个字符匹配。

而删除字符的长度具备单调性，如果删除长度为 $x$ 的字符串后，满足条件，那么删除长度为 $x+1$ 的字符串也一定满足条件。因此，我们可以使用二分查找的方法，找到最小的满足条件的长度。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 $t$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimumScore(self, s: str, t: str) -> int:
        def check(x):
            for k in range(n):
                i, j = k - 1, k + x
                l = f[i] if i >= 0 else -1
                r = g[j] if j < n else m + 1
                if l < r:
                    return True
            return False

        m, n = len(s), len(t)
        f = [inf] * n
        g = [-1] * n
        i, j = 0, 0
        while i < m and j < n:
            if s[i] == t[j]:
                f[j] = i
                j += 1
            i += 1
        i, j = m - 1, n - 1
        while i >= 0 and j >= 0:
            if s[i] == t[j]:
                g[j] = i
                j -= 1
            i -= 1

        return bisect_left(range(n + 1), True, key=check)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int m;
    private int n;
    private int[] f;
    private int[] g;

    public int minimumScore(String s, String t) {
        m = s.length();
        n = t.length();
        f = new int[n];
        g = new int[n];
        for (int i = 0; i < n; ++i) {
            f[i] = 1 << 30;
            g[i] = -1;
        }
        for (int i = 0, j = 0; i < m && j < n; ++i) {
            if (s.charAt(i) == t.charAt(j)) {
                f[j] = i;
                ++j;
            }
        }
        for (int i = m - 1, j = n - 1; i >= 0 && j >= 0; --i) {
            if (s.charAt(i) == t.charAt(j)) {
                g[j] = i;
                --j;
            }
        }
        int l = 0, r = n;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private boolean check(int len) {
        for (int k = 0; k < n; ++k) {
            int i = k - 1, j = k + len;
            int l = i >= 0 ? f[i] : -1;
            int r = j < n ? g[j] : m + 1;
            if (l < r) {
                return true;
            }
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumScore(string s, string t) {
        int m = s.size(), n = t.size();
        vector<int> f(n, 1e6);
        vector<int> g(n, -1);
        for (int i = 0, j = 0; i < m && j < n; ++i) {
            if (s[i] == t[j]) {
                f[j] = i;
                ++j;
            }
        }
        for (int i = m - 1, j = n - 1; i >= 0 && j >= 0; --i) {
            if (s[i] == t[j]) {
                g[j] = i;
                --j;
            }
        }

        auto check = [&](int len) {
            for (int k = 0; k < n; ++k) {
                int i = k - 1, j = k + len;
                int l = i >= 0 ? f[i] : -1;
                int r = j < n ? g[j] : m + 1;
                if (l < r) {
                    return true;
                }
            }
            return false;
        };

        int l = 0, r = n;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
};
```

### **Go**

```go
func minimumScore(s string, t string) int {
	m, n := len(s), len(t)
	f := make([]int, n)
	g := make([]int, n)
	for i := range f {
		f[i] = 1 << 30
		g[i] = -1
	}
	for i, j := 0, 0; i < m && j < n; i++ {
		if s[i] == t[j] {
			f[j] = i
			j++
		}
	}
	for i, j := m-1, n-1; i >= 0 && j >= 0; i-- {
		if s[i] == t[j] {
			g[j] = i
			j--
		}
	}
	return sort.Search(n+1, func(x int) bool {
		for k := 0; k < n; k++ {
			i, j := k-1, k+x
			l, r := -1, m+1
			if i >= 0 {
				l = f[i]
			}
			if j < n {
				r = g[j]
			}
			if l < r {
				return true
			}
		}
		return false
	})
}
```

### **...**

```

```

<!-- tabs:end -->
