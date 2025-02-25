---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1745.Palindrome%20Partitioning%20IV/README.md
rating: 1924
source: 第 226 场周赛 Q4
tags:
    - 字符串
    - 动态规划
---

<!-- problem:start -->

# [1745. 分割回文串 IV](https://leetcode.cn/problems/palindrome-partitioning-iv)

[English Version](/solution/1700-1799/1745.Palindrome%20Partitioning%20IV/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>s</code> ，如果可以将它分割成三个 <strong>非空</strong> 回文子字符串，那么返回 <code>true</code> ，否则返回 <code>false</code> 。</p>

<p>当一个字符串正着读和反着读是一模一样的，就称其为 <strong>回文字符串</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>s = "abcbdd"
<b>输出：</b>true
<strong>解释：</strong>"abcbdd" = "a" + "bcb" + "dd"，三个子字符串都是回文的。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>s = "bcbddxy"
<b>输出：</b>false
<strong>解释：</strong>s 没办法被分割成 3 个回文子字符串。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 <= s.length <= 2000</code></li>
	<li><code>s</code>​​​​​​ 只包含小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

我们定义 $f[i][j]$ 表示字符串 $s$ 的第 $i$ 个字符到第 $j$ 个字符是否为回文串，初始时 $f[i][j] = \textit{true}$。

然后我们可以通过以下的状态转移方程来计算 $f[i][j]$：

$$
f[i][j] = \begin{cases}
\textit{true}, & \text{if } s[i] = s[j] \text{ and } (i + 1 = j \text{ or } f[i + 1][j - 1]) \\
\textit{false}, & \text{otherwise}
\end{cases}
$$

由于 $f[i][j]$ 依赖于 $f[i + 1][j - 1]$，因此，我们需要从大到小的顺序枚举 $i$，从小到大的顺序枚举 $j$，这样才能保证当计算 $f[i][j]$ 时 $f[i + 1][j - 1]$ 已经被计算过。

接下来，我们枚举第一个子串的右端点 $i$，第二个子串的右端点 $j$，那么第三个子串的左端点可以枚举的范围为 $[j + 1, n - 1]$，其中 $n$ 是字符串 $s$ 的长度。如果第一个子串 $s[0..i]$、第二个子串 $s[i+1..j]$ 和第三个子串 $s[j+1..n-1]$ 都是回文串，那么我们就找到了一种可行的分割方案，返回 $\textit{true}$。

枚举完所有的分割方案后，如果没有找到符合要求的分割方案，那么返回 $\textit{false}$。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 是字符串 $s$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def checkPartitioning(self, s: str) -> bool:
        n = len(s)
        f = [[True] * n for _ in range(n)]
        for i in range(n - 1, -1, -1):
            for j in range(i + 1, n):
                f[i][j] = s[i] == s[j] and (i + 1 == j or f[i + 1][j - 1])
        for i in range(n - 2):
            for j in range(i + 1, n - 1):
                if f[0][i] and f[i + 1][j] and f[j + 1][-1]:
                    return True
        return False
```

#### Java

```java
class Solution {
    public boolean checkPartitioning(String s) {
        int n = s.length();
        boolean[][] f = new boolean[n][n];
        for (var g : f) {
            Arrays.fill(g, true);
        }
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                f[i][j] = s.charAt(i) == s.charAt(j) && (i + 1 == j || f[i + 1][j - 1]);
            }
        }
        for (int i = 0; i < n - 2; ++i) {
            for (int j = i + 1; j < n - 1; ++j) {
                if (f[0][i] && f[i + 1][j] && f[j + 1][n - 1]) {
                    return true;
                }
            }
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool checkPartitioning(string s) {
        int n = s.size();
        vector<vector<bool>> f(n, vector<bool>(n, true));
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                f[i][j] = s[i] == s[j] && (i + 1 == j || f[i + 1][j - 1]);
            }
        }
        for (int i = 0; i < n - 2; ++i) {
            for (int j = i + 1; j < n - 1; ++j) {
                if (f[0][i] && f[i + 1][j] && f[j + 1][n - 1]) {
                    return true;
                }
            }
        }
        return false;
    }
};
```

#### Go

```go
func checkPartitioning(s string) bool {
	n := len(s)
	f := make([][]bool, n)
	for i := range f {
		f[i] = make([]bool, n)
		for j := range f[i] {
			f[i][j] = true
		}
	}
	for i := n - 1; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			f[i][j] = s[i] == s[j] && (i+1 == j || f[i+1][j-1])
		}
	}
	for i := 0; i < n-2; i++ {
		for j := i + 1; j < n-1; j++ {
			if f[0][i] && f[i+1][j] && f[j+1][n-1] {
				return true
			}
		}
	}
	return false
}
```

#### TypeScript

```ts
function checkPartitioning(s: string): boolean {
    const n = s.length;
    const f: boolean[][] = Array.from({ length: n }, () => Array(n).fill(true));
    for (let i = n - 1; i >= 0; --i) {
        for (let j = i + 1; j < n; ++j) {
            f[i][j] = s[i] === s[j] && f[i + 1][j - 1];
        }
    }
    for (let i = 0; i < n - 2; ++i) {
        for (let j = i + 1; j < n - 1; ++j) {
            if (f[0][i] && f[i + 1][j] && f[j + 1][n - 1]) {
                return true;
            }
        }
    }
    return false;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
