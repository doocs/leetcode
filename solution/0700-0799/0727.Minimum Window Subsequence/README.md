# [727. 最小窗口子序列 🔒](https://leetcode.cn/problems/minimum-window-subsequence)

[English Version](/solution/0700-0799/0727.Minimum%20Window%20Subsequence/README_EN.md)

<!-- tags:字符串,动态规划,滑动窗口 -->

<!-- difficulty:困难 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定字符串 <code>S</code> and <code>T</code>，找出 <code>S</code> 中最短的（连续）<strong>子串</strong> <code>W</code> ，使得 <code>T</code> 是 <code>W</code> 的 <strong>子序列</strong> 。</p>

<p>如果 <code>S</code> 中没有窗口可以包含 <code>T</code> 中的所有字符，返回空字符串 <code>&quot;&quot;</code>。如果有不止一个最短长度的窗口，返回开始位置最靠左的那个。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>
S = &quot;abcdebdde&quot;, T = &quot;bde&quot;
<strong>输出：</strong>&quot;bcde&quot;
<strong>解释：</strong>
&quot;bcde&quot; 是答案，因为它在相同长度的字符串 &quot;bdde&quot; 出现之前。
&quot;deb&quot; 不是一个更短的答案，因为在窗口中必须按顺序出现 T 中的元素。</pre>

<p>&nbsp;</p>

<p><strong>注：</strong></p>

<ul>
	<li>所有输入的字符串都只包含小写字母。All the strings in the input will only contain lowercase letters.</li>
	<li><code>S</code>&nbsp;长度的范围为&nbsp;<code>[1, 20000]</code>。</li>
	<li><code>T</code>&nbsp;长度的范围为&nbsp;<code>[1, 100]</code>。</li>
</ul>

<p>&nbsp;</p>

## 解法

### 方法一：动态规划

我们定义 $f[i][j]$ 表示字符串 $s1$ 的前 $i$ 个字符包含字符串 $s2$ 的前 $j$ 个字符时的最短子串的起始位置，如果不存在则为 $0$。

我们可以得到状态转移方程：

$$
f[i][j] = \begin{cases}
i, & j = 1 \text{ and } s1[i-1] = s2[j] \\
f[i - 1][j - 1], & j > 1 \text{ and } s1[i-1] = s2[j-1] \\
f[i - 1][j], & s1[i-1] \ne s2[j-1]
\end{cases}
$$

接下来我们只需要遍历 $s1$，如果 $f[i][n] \gt 0$，则更新最短子串的起始位置和长度。最后返回最短子串即可。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别为字符串 $s1$ 和 $s2$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def minWindow(self, s1: str, s2: str) -> str:
        m, n = len(s1), len(s2)
        f = [[0] * (n + 1) for _ in range(m + 1)]
        for i, a in enumerate(s1, 1):
            for j, b in enumerate(s2, 1):
                if a == b:
                    f[i][j] = i if j == 1 else f[i - 1][j - 1]
                else:
                    f[i][j] = f[i - 1][j]
        p, k = 0, m + 1
        for i, a in enumerate(s1, 1):
            if a == s2[n - 1] and f[i][n]:
                j = f[i][n] - 1
                if i - j < k:
                    k = i - j
                    p = j
        return "" if k > m else s1[p : p + k]
```

```java
class Solution {
    public String minWindow(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] f = new int[m + 1][n + 1];
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    f[i][j] = j == 1 ? i : f[i - 1][j - 1];
                } else {
                    f[i][j] = f[i - 1][j];
                }
            }
        }
        int p = 0, k = m + 1;
        for (int i = 1; i <= m; ++i) {
            if (s1.charAt(i - 1) == s2.charAt(n - 1) && f[i][n] > 0) {
                int j = f[i][n] - 1;
                if (i - j < k) {
                    k = i - j;
                    p = j;
                }
            }
        }
        return k > m ? "" : s1.substring(p, p + k);
    }
}
```

```cpp
class Solution {
public:
    string minWindow(string s1, string s2) {
        int m = s1.size(), n = s2.size();
        int f[m + 1][n + 1];
        memset(f, 0, sizeof(f));
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (s1[i - 1] == s2[j - 1]) {
                    f[i][j] = j == 1 ? i : f[i - 1][j - 1];
                } else {
                    f[i][j] = f[i - 1][j];
                }
            }
        }
        int p = 0, k = m + 1;
        for (int i = 1; i <= m; ++i) {
            if (s1[i - 1] == s2[n - 1] && f[i][n]) {
                int j = f[i][n] - 1;
                if (i - j < k) {
                    k = i - j;
                    p = j;
                }
            }
        }
        return k > m ? "" : s1.substr(p, k);
    }
};
```

```go
func minWindow(s1 string, s2 string) string {
	m, n := len(s1), len(s2)
	f := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			if s1[i-1] == s2[j-1] {
				if j == 1 {
					f[i][j] = i
				} else {
					f[i][j] = f[i-1][j-1]
				}
			} else {
				f[i][j] = f[i-1][j]
			}
		}
	}
	p, k := 0, m+1
	for i := 1; i <= m; i++ {
		if s1[i-1] == s2[n-1] && f[i][n] > 0 {
			j := f[i][n] - 1
			if i-j < k {
				k = i - j
				p = j
			}
		}
	}
	if k > m {
		return ""
	}
	return s1[p : p+k]
}
```

```ts
function minWindow(s1: string, s2: string): string {
    const m = s1.length;
    const n = s2.length;
    const f: number[][] = Array(m + 1)
        .fill(0)
        .map(() => Array(n + 1).fill(0));
    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            if (s1[i - 1] === s2[j - 1]) {
                f[i][j] = j === 1 ? i : f[i - 1][j - 1];
            } else {
                f[i][j] = f[i - 1][j];
            }
        }
    }
    let p = 0;
    let k = m + 1;
    for (let i = 1; i <= m; ++i) {
        if (s1[i - 1] === s2[n - 1] && f[i][n]) {
            const j = f[i][n] - 1;
            if (i - j < k) {
                k = i - j;
                p = j;
            }
        }
    }
    return k > m ? '' : s1.slice(p, p + k);
}
```

<!-- tabs:end -->

<!-- end -->
