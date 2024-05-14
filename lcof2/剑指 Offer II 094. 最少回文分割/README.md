---
comment: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20094.%20%E6%9C%80%E5%B0%91%E5%9B%9E%E6%96%87%E5%88%86%E5%89%B2/README.md
---

# [剑指 Offer II 094. 最少回文分割](https://leetcode.cn/problems/omKAoA)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串 <code>s</code>，请将 <code>s</code> 分割成一些子串，使每个子串都是回文串。</p>

<p>返回符合要求的 <strong>最少分割次数</strong> 。</p>

<div class="original__bRMd">
<div>
<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = &quot;aab&quot;
<strong>输出：</strong>1
<strong>解释：</strong>只需一次分割就可将&nbsp;s<em> </em>分割成 [&quot;aa&quot;,&quot;b&quot;] 这样两个回文子串。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = &quot;a&quot;
<strong>输出：</strong>0
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = &quot;ab&quot;
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 2000</code></li>
	<li><code>s</code> 仅由小写英文字母组成</li>
</ul>
</div>
</div>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 132&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/palindrome-partitioning-ii/">https://leetcode.cn/problems/palindrome-partitioning-ii/</a></p>

## 解法

### 方法一：动态规划

我们先预处理得到字符串 $s$ 的每一个子串 $s[i..j]$ 是否为回文串，记录在二维数组 $g[i][j]$ 中，其中 $g[i][j]$ 表示子串 $s[i..j]$ 是否为回文串。

接下来，我们定义 $f[i]$ 表示字符串 $s[0..i-1]$ 的最少分割次数，初始时 $f[i]=i$。

接下来，我们考虑 $f[i]$ 如何进行状态转移。我们可以枚举上一个分割点 $j$，如果子串 $s[j..i]$ 是一个回文串，那么 $f[i]$ 就可以从 $f[j]$ 转移而来。如果 $j=0$，那么说明 $s[0..i]$ 本身就是一个回文串，此时不需要进行分割，即 $f[i]=0$。因此，状态转移方程如下：

$$
f[i]=\min_{0\leq j \leq i}\begin{cases} f[j-1]+1, & \text{if}\ g[j][i]=\text{True} \\ 0, & \text{if}\ g[0][i]=\text{True} \end{cases}
$$

答案即为 $f[n]$，其中 $n$ 是字符串 $s$ 的长度。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 是字符串 $s$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def minCut(self, s: str) -> int:
        n = len(s)
        g = [[True] * n for _ in range(n)]
        for i in range(n - 1, -1, -1):
            for j in range(i + 1, n):
                g[i][j] = s[i] == s[j] and g[i + 1][j - 1]
        f = list(range(n))
        for i in range(1, n):
            for j in range(i + 1):
                if g[j][i]:
                    f[i] = min(f[i], 1 + f[j - 1] if j else 0)
        return f[-1]
```

```java
class Solution {
    public int minCut(String s) {
        int n = s.length();
        boolean[][] g = new boolean[n][n];
        for (var row : g) {
            Arrays.fill(row, true);
        }
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                g[i][j] = s.charAt(i) == s.charAt(j) && g[i + 1][j - 1];
            }
        }
        int[] f = new int[n];
        for (int i = 0; i < n; ++i) {
            f[i] = i;
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j <= i; ++j) {
                if (g[j][i]) {
                    f[i] = Math.min(f[i], j > 0 ? 1 + f[j - 1] : 0);
                }
            }
        }
        return f[n - 1];
    }
}
```

```cpp
class Solution {
public:
    int minCut(string s) {
        int n = s.size();
        bool g[n][n];
        memset(g, true, sizeof(g));
        for (int i = n - 1; ~i; --i) {
            for (int j = i + 1; j < n; ++j) {
                g[i][j] = s[i] == s[j] && g[i + 1][j - 1];
            }
        }
        int f[n];
        iota(f, f + n, 0);
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j <= i; ++j) {
                if (g[j][i]) {
                    f[i] = min(f[i], j ? 1 + f[j - 1] : 0);
                }
            }
        }
        return f[n - 1];
    }
};
```

```go
func minCut(s string) int {
	n := len(s)
	g := make([][]bool, n)
	f := make([]int, n)
	for i := range g {
		g[i] = make([]bool, n)
		f[i] = i
		for j := range g[i] {
			g[i][j] = true
		}
	}
	for i := n - 1; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			g[i][j] = s[i] == s[j] && g[i+1][j-1]
		}
	}
	for i := 1; i < n; i++ {
		for j := 0; j <= i; j++ {
			if g[j][i] {
				if j == 0 {
					f[i] = 0
				} else {
					f[i] = min(f[i], f[j-1]+1)
				}
			}
		}
	}
	return f[n-1]
}
```

```ts
function minCut(s: string): number {
    const n = s.length;
    const g: boolean[][] = Array(n)
        .fill(0)
        .map(() => Array(n).fill(true));
    for (let i = n - 1; ~i; --i) {
        for (let j = i + 1; j < n; ++j) {
            g[i][j] = s[i] === s[j] && g[i + 1][j - 1];
        }
    }
    const f: number[] = Array(n)
        .fill(0)
        .map((_, i) => i);
    for (let i = 1; i < n; ++i) {
        for (let j = 0; j <= i; ++j) {
            if (g[j][i]) {
                f[i] = Math.min(f[i], j ? 1 + f[j - 1] : 0);
            }
        }
    }
    return f[n - 1];
}
```

```cs
public class Solution {
    public int MinCut(string s) {
        int n = s.Length;
        bool[,] g = new bool[n,n];
        int[] f = new int[n];
        for (int i = 0; i < n; ++i) {
            f[i] = i;
            for (int j = 0; j < n; ++j) {
                g[i,j] = true;
            }
        }
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                g[i,j] = s[i] == s[j] && g[i + 1,j - 1];
            }
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j <= i; ++j) {
                if (g[j,i]) {
                    f[i] = Math.Min(f[i], j > 0 ? 1 + f[j - 1] : 0);
                }
            }
        }
        return f[n - 1];
    }
}
```

<!-- tabs:end -->

<!-- end -->
