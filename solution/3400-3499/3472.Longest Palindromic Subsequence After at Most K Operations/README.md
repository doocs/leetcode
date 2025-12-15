---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3472.Longest%20Palindromic%20Subsequence%20After%20at%20Most%20K%20Operations/README.md
rating: 1883
source: 第 439 场周赛 Q2
tags:
    - 字符串
    - 动态规划
---

<!-- problem:start -->

# [3472. 至多 K 次操作后的最长回文子序列](https://leetcode.cn/problems/longest-palindromic-subsequence-after-at-most-k-operations)

[English Version](/solution/3400-3499/3472.Longest%20Palindromic%20Subsequence%20After%20at%20Most%20K%20Operations/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>s</code> 和一个整数 <code>k</code>。</p>

<p>在一次操作中，你可以将任意位置的字符替换为字母表中相邻的字符（字母表是循环的，因此&nbsp;<code>'z'</code>&nbsp;的下一个字母是&nbsp;<code>'a'</code>）。例如，将 <code>'a'</code> 替换为下一个字母结果是 <code>'b'</code>，将 <code>'a'</code> 替换为上一个字母结果是 <code>'z'</code>；同样，将 <code>'z'</code> 替换为下一个字母结果是 <code>'a'</code>，替换为上一个字母结果是 <code>'y'</code>。</p>

<p>返回在进行&nbsp;<strong>最多</strong> <code>k</code> 次操作后，<code>s</code> 的&nbsp;<strong>最长回文子序列&nbsp;</strong>的长度。</p>

<p><strong>子序列&nbsp;</strong>是一个&nbsp;<strong>非空&nbsp;</strong>字符串，可以通过删除原字符串中的某些字符（或不删除任何字符）并保持剩余字符的相对顺序得到。</p>

<p><strong>回文&nbsp;</strong>是正着读和反着读都相同的字符串。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">s = "abced", k = 2</span></p>

<p><strong>输出:</strong> <span class="example-io">3</span></p>

<p><strong>解释:</strong></p>

<ul>
	<li>将 <code>s[1]</code> 替换为下一个字母，得到 <code>"acced"</code>。</li>
	<li>将 <code>s[4]</code> 替换为上一个字母，得到 <code>"accec"</code>。</li>
</ul>

<p>子序列 <code>"ccc"</code> 形成一个长度为 3 的回文，这是最长的回文子序列。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">s = "aaazzz", k = 4</span></p>

<p><strong>输出:</strong> 6</p>

<p><strong>解释:</strong></p>

<ul>
	<li>将 <code>s[0]</code> 替换为上一个字母，得到 <code>"zaazzz"</code>。</li>
	<li>将 <code>s[4]</code> 替换为下一个字母，得到 <code>"zaazaz"</code>。</li>
	<li>将 <code>s[3]</code> 替换为下一个字母，得到 <code>"zaaaaz"</code>。</li>
</ul>

<p>整个字符串形成一个长度为 6 的回文。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 200</code></li>
	<li><code>1 &lt;= k &lt;= 200</code></li>
	<li><code>s</code> 仅由小写英文字母组成。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：记忆化搜索

我们设计一个函数 $\textit{dfs}(i, j, k)$，表示在字符串 $s[i..j]$ 中最多可以进行 $k$ 次操作，得到的最长回文子序列的长度。那么答案为 $\textit{dfs}(0, n - 1, k)$。

函数 $\textit{dfs}(i, j, k)$ 的计算过程如下：

- 如果 $i > j$，返回 $0$；
- 如果 $i = j$，返回 $1$；
- 否则，我们可以忽略 $s[i]$ 或 $s[j]$，分别计算 $\textit{dfs}(i + 1, j, k)$ 和 $\textit{dfs}(i, j - 1, k)$；或者我们可以将 $s[i]$ 和 $s[j]$ 变成相同的字符，计算 $\textit{dfs}(i + 1, j - 1, k - t) + 2$，其中 $t$ 是 $s[i]$ 和 $s[j]$ 的 ASCII 码差值。
- 返回上述三种情况的最大值。

为了避免重复计算，我们使用记忆化搜索的方法。

时间复杂度 $O(n^2 \times k)$，空间复杂度 $O(n^2 \times k)$。其中 $n$ 是字符串 $s$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestPalindromicSubsequence(self, s: str, k: int) -> int:
        @cache
        def dfs(i: int, j: int, k: int) -> int:
            if i > j:
                return 0
            if i == j:
                return 1
            res = max(dfs(i + 1, j, k), dfs(i, j - 1, k))
            d = abs(s[i] - s[j])
            t = min(d, 26 - d)
            if t <= k:
                res = max(res, dfs(i + 1, j - 1, k - t) + 2)
            return res

        s = list(map(ord, s))
        n = len(s)
        ans = dfs(0, n - 1, k)
        dfs.cache_clear()
        return ans
```

#### Java

```java
class Solution {
    private char[] s;
    private Integer[][][] f;

    public int longestPalindromicSubsequence(String s, int k) {
        this.s = s.toCharArray();
        int n = s.length();
        f = new Integer[n][n][k + 1];
        return dfs(0, n - 1, k);
    }

    private int dfs(int i, int j, int k) {
        if (i > j) {
            return 0;
        }
        if (i == j) {
            return 1;
        }
        if (f[i][j][k] != null) {
            return f[i][j][k];
        }
        int res = Math.max(dfs(i + 1, j, k), dfs(i, j - 1, k));
        int d = Math.abs(s[i] - s[j]);
        int t = Math.min(d, 26 - d);
        if (t <= k) {
            res = Math.max(res, 2 + dfs(i + 1, j - 1, k - t));
        }
        f[i][j][k] = res;
        return res;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int longestPalindromicSubsequence(string s, int k) {
        int n = s.size();
        vector f(n, vector(n, vector<int>(k + 1, -1)));
        auto dfs = [&](this auto&& dfs, int i, int j, int k) -> int {
            if (i > j) {
                return 0;
            }
            if (i == j) {
                return 1;
            }
            if (f[i][j][k] != -1) {
                return f[i][j][k];
            }
            int res = max(dfs(i + 1, j, k), dfs(i, j - 1, k));
            int d = abs(s[i] - s[j]);
            int t = min(d, 26 - d);
            if (t <= k) {
                res = max(res, 2 + dfs(i + 1, j - 1, k - t));
            }
            return f[i][j][k] = res;
        };
        return dfs(0, n - 1, k);
    }
};
```

#### Go

```go
func longestPalindromicSubsequence(s string, k int) int {
	n := len(s)
	f := make([][][]int, n)
	for i := range f {
		f[i] = make([][]int, n)
		for j := range f[i] {
			f[i][j] = make([]int, k+1)
			for l := range f[i][j] {
				f[i][j][l] = -1
			}
		}
	}
	var dfs func(int, int, int) int
	dfs = func(i, j, k int) int {
		if i > j {
			return 0
		}
		if i == j {
			return 1
		}
		if f[i][j][k] != -1 {
			return f[i][j][k]
		}
		res := max(dfs(i+1, j, k), dfs(i, j-1, k))
		d := abs(int(s[i]) - int(s[j]))
		t := min(d, 26-d)
		if t <= k {
			res = max(res, 2+dfs(i+1, j-1, k-t))
		}
		f[i][j][k] = res
		return res
	}
	return dfs(0, n-1, k)
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function longestPalindromicSubsequence(s: string, k: number): number {
    const n = s.length;
    const sCodes = s.split('').map(c => c.charCodeAt(0));
    const f: number[][][] = Array.from({ length: n }, () =>
        Array.from({ length: n }, () => Array(k + 1).fill(-1)),
    );

    function dfs(i: number, j: number, k: number): number {
        if (i > j) {
            return 0;
        }
        if (i === j) {
            return 1;
        }

        if (f[i][j][k] !== -1) {
            return f[i][j][k];
        }

        let res = Math.max(dfs(i + 1, j, k), dfs(i, j - 1, k));
        const d = Math.abs(sCodes[i] - sCodes[j]);
        const t = Math.min(d, 26 - d);
        if (t <= k) {
            res = Math.max(res, 2 + dfs(i + 1, j - 1, k - t));
        }
        return (f[i][j][k] = res);
    }

    return dfs(0, n - 1, k);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
