---
comments: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20097.%20%E5%AD%90%E5%BA%8F%E5%88%97%E7%9A%84%E6%95%B0%E7%9B%AE/README.md
---

<!-- problem:start -->

# [剑指 Offer II 097. 子序列的数目](https://leetcode.cn/problems/21dk04)

## 题目描述

<!-- description:start -->

<p>给定一个字符串 <code>s</code><strong> </strong>和一个字符串 <code>t</code> ，计算在 <code>s</code> 的子序列中 <code>t</code> 出现的个数。</p>

<p>字符串的一个 <strong>子序列</strong> 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，<code>&quot;ACE&quot;</code>&nbsp;是&nbsp;<code>&quot;ABCDE&quot;</code>&nbsp;的一个子序列，而&nbsp;<code>&quot;AEC&quot;</code>&nbsp;不是）</p>

<p>题目数据保证答案符合 32 位带符号整数范围。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1：</strong></p>

<pre>
<strong>输入：</strong>s = &quot;rabbbit&quot;, t = &quot;rabbit&quot;<code>
<strong>输出</strong></code><strong>：</strong><code>3
</code><strong>解释：</strong>
如下图所示, 有 3 种可以从 s 中得到 <code>&quot;rabbit&quot; 的方案</code>。
<code><strong><u>rabb</u></strong>b<strong><u>it</u></strong></code>
<code><strong><u>ra</u></strong>b<strong><u>bbit</u></strong></code>
<code><strong><u>rab</u></strong>b<strong><u>bit</u></strong></code></pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre>
<strong>输入：</strong>s = &quot;babgbag&quot;, t = &quot;bag&quot;
<code><strong>输出</strong></code><strong>：</strong><code>5
</code><strong>解释：</strong>
如下图所示, 有 5 种可以从 s 中得到 <code>&quot;bag&quot; 的方案</code>。
<code><strong><u>ba</u></strong>b<u><strong>g</strong></u>bag</code>
<code><strong><u>ba</u></strong>bgba<strong><u>g</u></strong></code>
<code><u><strong>b</strong></u>abgb<strong><u>ag</u></strong></code>
<code>ba<u><strong>b</strong></u>gb<u><strong>ag</strong></u></code>
<code>babg<strong><u>bag</u></strong></code>
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= s.length, t.length &lt;= 1000</code></li>
	<li><code>s</code> 和 <code>t</code> 由英文字母组成</li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 115&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/distinct-subsequences/">https://leetcode.cn/problems/distinct-subsequences/</a></p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

我们定义 $f[i][j]$ 表示字符串 $s$ 的前 $i$ 个字符中，子序列构成字符串 $t$ 的前 $j$ 个字符的方案数。初始时 $f[i][0]=1$，其中 $i \in [0,m]$。

当 $i \gt 0$ 时，考虑 $f[i][j]$ 的计算：

-   当 $s[i-1] \ne t[j-1]$ 时，不能选取 $s[i-1]$，因此 $f[i][j]=f[i-1][j]$；
-   否则，可以选取 $s[i-1]$，此时 $f[i][j]=f[i-1][j-1]$。

因此我们有如下的状态转移方程：

$$
f[i][j]=\left\{
\begin{aligned}
&f[i-1][j], &s[i-1] \ne t[j-1] \\
&f[i-1][j-1]+f[i-1][j], &s[i-1]=t[j-1]
\end{aligned}
\right.
$$

最终的答案即为 $f[m][n]$，其中 $m$ 和 $n$ 分别是字符串 $s$ 和 $t$ 的长度。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。

我们注意到 $f[i][j]$ 的计算只和 $f[i-1][..]$ 有关，因此，我们可以优化掉第一维，这样空间复杂度可以降低到 $O(n)$。

<!-- tabs:start -->

```python
class Solution:
    def numDistinct(self, s: str, t: str) -> int:
        m, n = len(s), len(t)
        f = [[0] * (n + 1) for _ in range(m + 1)]
        for i in range(m + 1):
            f[i][0] = 1
        for i, a in enumerate(s, 1):
            for j, b in enumerate(t, 1):
                f[i][j] = f[i - 1][j]
                if a == b:
                    f[i][j] += f[i - 1][j - 1]
        return f[m][n]
```

```java
class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        int[][] f = new int[m + 1][n + 1];
        for (int i = 0; i < m + 1; ++i) {
            f[i][0] = 1;
        }
        for (int i = 1; i < m + 1; ++i) {
            for (int j = 1; j < n + 1; ++j) {
                f[i][j] = f[i - 1][j];
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    f[i][j] += f[i - 1][j - 1];
                }
            }
        }
        return f[m][n];
    }
}
```

```cpp
class Solution {
public:
    int numDistinct(string s, string t) {
        int m = s.size(), n = t.size();
        unsigned long long f[m + 1][n + 1];
        memset(f, 0, sizeof(f));
        for (int i = 0; i < m + 1; ++i) {
            f[i][0] = 1;
        }
        for (int i = 1; i < m + 1; ++i) {
            for (int j = 1; j < n + 1; ++j) {
                f[i][j] = f[i - 1][j];
                if (s[i - 1] == t[j - 1]) {
                    f[i][j] += f[i - 1][j - 1];
                }
            }
        }
        return f[m][n];
    }
};
```

```go
func numDistinct(s string, t string) int {
	m, n := len(s), len(t)
	f := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}
	for i := 0; i <= m; i++ {
		f[i][0] = 1
	}
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			f[i][j] = f[i-1][j]
			if s[i-1] == t[j-1] {
				f[i][j] += f[i-1][j-1]
			}
		}
	}
	return f[m][n]
}
```

```ts
function numDistinct(s: string, t: string): number {
    const m = s.length;
    const n = t.length;
    const f: number[][] = new Array(m + 1).fill(0).map(() => new Array(n + 1).fill(0));
    for (let i = 0; i <= m; ++i) {
        f[i][0] = 1;
    }
    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            f[i][j] = f[i - 1][j];
            if (s[i - 1] === t[j - 1]) {
                f[i][j] += f[i - 1][j - 1];
            }
        }
    }
    return f[m][n];
}
```

<!-- tabs:end -->

<!-- solution:end -->

### 方法二

<!-- tabs:start -->

```python
class Solution:
    def numDistinct(self, s: str, t: str) -> int:
        n = len(t)
        f = [1] + [0] * n
        for a in s:
            for j in range(n, 0, -1):
                if a == t[j - 1]:
                    f[j] += f[j - 1]
        return f[n]
```

```java
class Solution {
    public int numDistinct(String s, String t) {
        int n = t.length();
        int[] f = new int[n + 1];
        f[0] = 1;
        for (char a : s.toCharArray()) {
            for (int j = n; j > 0; --j) {
                char b = t.charAt(j - 1);
                if (a == b) {
                    f[j] += f[j - 1];
                }
            }
        }
        return f[n];
    }
}
```

```cpp
class Solution {
public:
    int numDistinct(string s, string t) {
        int n = t.size();
        unsigned long long f[n + 1];
        memset(f, 0, sizeof(f));
        f[0] = 1;
        for (char& a : s) {
            for (int j = n; j; --j) {
                char b = t[j - 1];
                if (a == b) {
                    f[j] += f[j - 1];
                }
            }
        }
        return f[n];
    }
};
```

```go
func numDistinct(s string, t string) int {
	n := len(t)
	f := make([]int, n+1)
	f[0] = 1
	for _, a := range s {
		for j := n; j > 0; j-- {
			if b := t[j-1]; byte(a) == b {
				f[j] += f[j-1]
			}
		}
	}
	return f[n]
}
```

```ts
function numDistinct(s: string, t: string): number {
    const n = t.length;
    const f: number[] = new Array(n + 1).fill(0);
    f[0] = 1;
    for (const a of s) {
        for (let j = n; j; --j) {
            const b = t[j - 1];
            if (a === b) {
                f[j] += f[j - 1];
            }
        }
    }
    return f[n];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
