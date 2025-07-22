---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3503.Longest%20Palindrome%20After%20Substring%20Concatenation%20I/README.md
rating: 1548
source: 第 443 场周赛 Q2
tags:
    - 双指针
    - 字符串
    - 动态规划
    - 枚举
---

<!-- problem:start -->

# [3503. 子字符串连接后的最长回文串 I](https://leetcode.cn/problems/longest-palindrome-after-substring-concatenation-i)

[English Version](/solution/3500-3599/3503.Longest%20Palindrome%20After%20Substring%20Concatenation%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个字符串 <code>s</code> 和 <code>t</code>。</p>

<p>你可以从 <code>s</code> 中选择一个子串（可以为空）以及从 <code>t</code> 中选择一个子串（可以为空），然后将它们<strong> 按顺序 </strong>连接，得到一个新的字符串。</p>

<p>返回可以由上述方法构造出的<strong> 最长</strong> 回文串的长度。</p>

<p><strong>回文串</strong> 是指正着读和反着读都相同的字符串。</p>

<p><strong>子字符串 </strong>是指字符串中的一个连续字符序列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "a", t = "a"</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p>从 <code>s</code> 中选择 <code>"a"</code>，从 <code>t</code> 中选择 <code>"a"</code>，拼接得到 <code>"aa"</code>，这是一个长度为 2 的回文串。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "abc", t = "def"</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<p>由于两个字符串的所有字符都不同，最长的回文串只能是任意一个单独的字符，因此答案是 1。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "b", t = "aaaa"</span></p>

<p><strong>输出：</strong> 4</p>

<p><strong>解释：</strong></p>

<p>可以选择 <code>"aaaa"</code> 作为回文串，其长度为 4。</p>
</div>

<p><strong class="example">示例 4：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "abcde", t = "ecdba"</span></p>

<p><strong>输出：</strong> 5</p>

<p><strong>解释：</strong></p>

<p>从 <code>s</code> 中选择 <code>"abc"</code>，从 <code>t</code> 中选择 <code>"ba"</code>，拼接得到 <code>"abcba"</code>，这是一个长度为 5 的回文串。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length, t.length &lt;= 30</code></li>
	<li><code>s</code> 和 <code>t</code> 仅由小写英文字母组成。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举回文中点 + 动态规划

根据题目描述，连接后的回文串，可以只由字符串 $s$ 组成，也可以只由字符串 $t$ 组成，也可以由字符串 $s$ 和字符串 $t$ 组成，并且还可能在字符串 $s$ 或 $t$ 中多出一部分回文子串。

因此，我们先将字符串 $t$ 反转，然后预处理出数组 $\textit{g1}$ 和 $\textit{g2}$，其中 $\textit{g1}[i]$ 表示在字符串 $s$ 中以下标 $i$ 开始的最长回文子串长度，而 $\textit{g2}[i]$ 表示在字符串 $t$ 中以下标 $i$ 开始的最长回文子串长度。

那么我们可以初始化答案 $\textit{ans}$ 为 $\textit{g1}$ 和 $\textit{g2}$ 中的最大值。

接下来，我们定义 $\textit{f}[i][j]$ 表示以字符串 $s$ 的第 $i$ 个字符结尾，以字符串 $t$ 的第 $j$ 个字符结尾的回文子串的长度。

对于 $\textit{f}[i][j]$，如果 $s[i - 1]$ 等于 $t[j - 1]$，那么有 $\textit{f}[i][j] = \textit{f}[i - 1][j - 1] + 1$。然后，我们更新答案：

$$
\textit{ans} = \max(\textit{ans}, \textit{f}[i][j] \times 2 + (0 \text{ if } i \geq m \text{ else } \textit{g1}[i])) \\

\textit{ans} = \max(\textit{ans}, \textit{f}[i][j] \times 2 + (0 \text{ if } j \geq n \text{ else } \textit{g2}[j])) \
$$

最后，我们返回答案 $\textit{ans}$ 即可。

时间复杂度 $O(m \times (m + n))$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是字符串 $s$ 和 $t$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestPalindrome(self, s: str, t: str) -> int:
        def expand(s: str, g: List[int], l: int, r: int):
            while l >= 0 and r < len(s) and s[l] == s[r]:
                g[l] = max(g[l], r - l + 1)
                l, r = l - 1, r + 1

        def calc(s: str) -> List[int]:
            n = len(s)
            g = [0] * n
            for i in range(n):
                expand(s, g, i, i)
                expand(s, g, i, i + 1)
            return g

        m, n = len(s), len(t)
        t = t[::-1]
        g1, g2 = calc(s), calc(t)
        ans = max(*g1, *g2)
        f = [[0] * (n + 1) for _ in range(m + 1)]
        for i, a in enumerate(s, 1):
            for j, b in enumerate(t, 1):
                if a == b:
                    f[i][j] = f[i - 1][j - 1] + 1
                    ans = max(ans, f[i][j] * 2 + (0 if i >= m else g1[i]))
                    ans = max(ans, f[i][j] * 2 + (0 if j >= n else g2[j]))
        return ans
```

#### Java

```java
class Solution {
    public int longestPalindrome(String S, String T) {
        char[] s = S.toCharArray();
        char[] t = new StringBuilder(T).reverse().toString().toCharArray();
        int m = s.length, n = t.length;
        int[] g1 = calc(s), g2 = calc(t);
        int ans = Math.max(Arrays.stream(g1).max().getAsInt(), Arrays.stream(g2).max().getAsInt());
        int[][] f = new int[m + 1][n + 1];
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (s[i - 1] == t[j - 1]) {
                    f[i][j] = f[i - 1][j - 1] + 1;
                    ans = Math.max(ans, f[i][j] * 2 + (i < m ? g1[i] : 0));
                    ans = Math.max(ans, f[i][j] * 2 + (j < n ? g2[j] : 0));
                }
            }
        }
        return ans;
    }

    private void expand(char[] s, int[] g, int l, int r) {
        while (l >= 0 && r < s.length && s[l] == s[r]) {
            g[l] = Math.max(g[l], r - l + 1);
            --l;
            ++r;
        }
    }

    private int[] calc(char[] s) {
        int n = s.length;
        int[] g = new int[n];
        for (int i = 0; i < n; ++i) {
            expand(s, g, i, i);
            expand(s, g, i, i + 1);
        }
        return g;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int longestPalindrome(string s, string t) {
        int m = s.size(), n = t.size();
        ranges::reverse(t);
        vector<int> g1 = calc(s), g2 = calc(t);
        int ans = max(ranges::max(g1), ranges::max(g2));
        vector<vector<int>> f(m + 1, vector<int>(n + 1));
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (s[i - 1] == t[j - 1]) {
                    f[i][j] = f[i - 1][j - 1] + 1;
                    ans = max(ans, f[i][j] * 2 + (i < m ? g1[i] : 0));
                    ans = max(ans, f[i][j] * 2 + (j < n ? g2[j] : 0));
                }
            }
        }
        return ans;
    }

private:
    void expand(const string& s, vector<int>& g, int l, int r) {
        while (l >= 0 && r < s.size() && s[l] == s[r]) {
            g[l] = max(g[l], r - l + 1);
            --l;
            ++r;
        }
    }

    vector<int> calc(const string& s) {
        int n = s.size();
        vector<int> g(n, 0);
        for (int i = 0; i < n; ++i) {
            expand(s, g, i, i);
            expand(s, g, i, i + 1);
        }
        return g;
    }
};
```

#### Go

```go
func longestPalindrome(s, t string) int {
	m, n := len(s), len(t)
	t = reverse(t)

	g1, g2 := calc(s), calc(t)
	ans := max(slices.Max(g1), slices.Max(g2))

	f := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}

	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			if s[i-1] == t[j-1] {
				f[i][j] = f[i-1][j-1] + 1
				a, b := 0, 0
				if i < m {
					a = g1[i]
				}
				if j < n {
					b = g2[j]
				}
				ans = max(ans, f[i][j]*2+a)
				ans = max(ans, f[i][j]*2+b)
			}
		}
	}
	return ans
}

func calc(s string) []int {
	n, g := len(s), make([]int, len(s))
	for i := 0; i < n; i++ {
		expand(s, g, i, i)
		expand(s, g, i, i+1)
	}
	return g
}

func expand(s string, g []int, l, r int) {
	for l >= 0 && r < len(s) && s[l] == s[r] {
		g[l] = max(g[l], r-l+1)
		l, r = l-1, r+1
	}
}

func reverse(s string) string {
	r := []rune(s)
	slices.Reverse(r)
	return string(r)
}
```

#### TypeScript

```ts
function longestPalindrome(s: string, t: string): number {
    function expand(s: string, g: number[], l: number, r: number): void {
        while (l >= 0 && r < s.length && s[l] === s[r]) {
            g[l] = Math.max(g[l], r - l + 1);
            l--;
            r++;
        }
    }

    function calc(s: string): number[] {
        const n = s.length;
        const g: number[] = Array(n).fill(0);
        for (let i = 0; i < n; i++) {
            expand(s, g, i, i);
            expand(s, g, i, i + 1);
        }
        return g;
    }

    const m = s.length,
        n = t.length;
    t = t.split('').reverse().join('');
    const g1 = calc(s);
    const g2 = calc(t);
    let ans = Math.max(...g1, ...g2);

    const f: number[][] = Array.from({ length: m + 1 }, () => Array(n + 1).fill(0));

    for (let i = 1; i <= m; i++) {
        for (let j = 1; j <= n; j++) {
            if (s[i - 1] === t[j - 1]) {
                f[i][j] = f[i - 1][j - 1] + 1;
                ans = Math.max(ans, f[i][j] * 2 + (i >= m ? 0 : g1[i]));
                ans = Math.max(ans, f[i][j] * 2 + (j >= n ? 0 : g2[j]));
            }
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
