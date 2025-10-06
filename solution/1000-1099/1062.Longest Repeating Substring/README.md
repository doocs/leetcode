---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1062.Longest%20Repeating%20Substring/README.md
tags:
    - 字符串
    - 二分查找
    - 动态规划
    - 后缀数组
    - 哈希函数
    - 滚动哈希
---

<!-- problem:start -->

# [1062. 最长重复子串的长度 🔒](https://leetcode.cn/problems/longest-repeating-substring)

[English Version](/solution/1000-1099/1062.Longest%20Repeating%20Substring/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串&nbsp;<code>s</code>，找出并返回&nbsp;<strong>最长重复子串</strong> 的长度。如果不存在重复子串，返回 <code>0</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "abcd"
<strong>输出：</strong>0
<strong>解释：</strong>没有重复子串。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "abbaba"
<strong>输出：</strong>2
<strong>解释：</strong>最长的重复子串为 "ab" 和 "ba"，每个出现 2 次。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "aabcaabdaab"
<strong>输出：</strong>3
<strong>解释：</strong>最长的重复子串为 "aab"，出现 3 次。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 2000</code></li>
	<li>字符串&nbsp;<code>s</code>&nbsp;仅包含从&nbsp;<code>'a'</code> 到&nbsp;<code>'z'</code>&nbsp;的小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

我们定义 $f[i][j]$ 表示以 $s[i]$ 和 $s[j]$ 结尾的最长重复子串的长度，初始时 $f[i][j]=0$。

我们在 $[1, n)$ 的区间内枚举 $i$，在 $[0, i)$ 的区间内枚举 $j$，如果 $s[i]=s[j]$，那么有：

$$
f[i][j]=
\begin{cases}
f[i-1][j-1]+1, & j>0 \\
1, & j=0
\end{cases}
$$

我们求出所有 $f[i][j]$ 的最大值即为答案。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 为字符串 $s$ 的长度。

相似题目：

-   [1044. 最长重复子串 🔒](https://github.com/doocs/leetcode/blob/main/solution/1000-1099/1044.Longest%20Duplicate%20Substring/README.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestRepeatingSubstring(self, s: str) -> int:
        n = len(s)
        f = [[0] * n for _ in range(n)]
        ans = 0
        for i in range(1, n):
            for j in range(i):
                if s[i] == s[j]:
                    f[i][j] = 1 + (f[i - 1][j - 1] if j else 0)
                    ans = max(ans, f[i][j])
        return ans
```

#### Java

```java
class Solution {
    public int longestRepeatingSubstring(String s) {
        int n = s.length();
        int[][] f = new int[n][n];
        int ans = 0;
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (s.charAt(i) == s.charAt(j)) {
                    f[i][j] = 1 + (j > 0 ? f[i - 1][j - 1] : 0);
                    ans = Math.max(ans, f[i][j]);
                }
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int longestRepeatingSubstring(string s) {
        int n = s.length();
        int f[n][n];
        memset(f, 0, sizeof(f));
        int ans = 0;
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (s[i] == s[j]) {
                    f[i][j] = 1 + (j > 0 ? f[i - 1][j - 1] : 0);
                    ans = max(ans, f[i][j]);
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func longestRepeatingSubstring(s string) (ans int) {
	n := len(s)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, n)
	}
	for i := 1; i < n; i++ {
		for j := 0; j < i; j++ {
			if s[i] == s[j] {
				if j > 0 {
					f[i][j] = f[i-1][j-1]
				}
				f[i][j]++
				ans = max(ans, f[i][j])
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function longestRepeatingSubstring(s: string): number {
    const n = s.length;
    const f: number[][] = Array.from({ length: n }).map(() => Array(n).fill(0));
    let ans = 0;
    for (let i = 1; i < n; ++i) {
        for (let j = 0; j < i; ++j) {
            if (s[i] === s[j]) {
                f[i][j] = 1 + (f[i - 1][j - 1] || 0);
                ans = Math.max(ans, f[i][j]);
            }
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn longest_repeating_substring(s: String) -> i32 {
        let n = s.len();
        let mut f = vec![vec![0; n]; n];
        let mut ans = 0;
        let s = s.as_bytes();

        for i in 1..n {
            for j in 0..i {
                if s[i] == s[j] {
                    f[i][j] = if j > 0 { f[i - 1][j - 1] + 1 } else { 1 };
                    ans = ans.max(f[i][j]);
                }
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
