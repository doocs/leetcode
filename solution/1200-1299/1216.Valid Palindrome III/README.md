---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1216.Valid%20Palindrome%20III/README.md
rating: 1753
tags:
    - 字符串
    - 动态规划
---

# [1216. 验证回文串 III 🔒](https://leetcode.cn/problems/valid-palindrome-iii)

[English Version](/solution/1200-1299/1216.Valid%20Palindrome%20III/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给出一个字符串&nbsp;<code>s</code>&nbsp;和一个整数&nbsp;<code>k</code>，若这个字符串是一个「k&nbsp;<strong>回文</strong>&nbsp;」，则返回 <code>true</code> 。</p>

<p>如果可以通过从字符串中删去最多 <code>k</code> 个字符将其转换为回文，那么这个字符串就是一个「<strong>k</strong>&nbsp;<strong>回文</strong>&nbsp;」。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "abcdeca", k = 2
<strong>输</strong><strong>出：</strong>true
<strong>解释：</strong>删去字符 “b” 和 “e”。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入：</strong>s = "abbababa", k = 1
<strong>输</strong><strong>出：</strong>true
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code>&nbsp;中只含有小写英文字母</li>
	<li><code>1 &lt;= k&nbsp;&lt;= s.length</code></li>
</ul>

## 解法

### 方法一：动态规划

题目要求删去最多 $k$ 个字符，使得剩余的字符串是回文串。可以转换为求最长回文子序列的问题。

我们定义 $f[i][j]$ 表示字符串 $s$ 中下标范围 $[i, j]$ 内的最长回文子序列的长度。初始时 $f[i][i] = 1$，即每个单独的字符都是一个回文子序列。

当 $s[i] = s[j]$ 时，有 $f[i][j] = f[i + 1][j - 1] + 2$，即去掉 $s[i]$ 和 $s[j]$ 后，剩余的字符串的最长回文子序列长度加 $2$。

当 $s[i] \neq s[j]$ 时，有 $f[i][j] = \max(f[i + 1][j], f[i][j - 1])$，即去掉 $s[i]$ 或 $s[j]$ 后，剩余的字符串的最长回文子序列长度。

然后是否存在 $f[i][j] + k \geq n$，如果存在，说明可以通过删去 $k$ 个字符，使得剩余的字符串是回文串。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 为字符串 $s$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def isValidPalindrome(self, s: str, k: int) -> bool:
        n = len(s)
        f = [[0] * n for _ in range(n)]
        for i in range(n):
            f[i][i] = 1
        for i in range(n - 2, -1, -1):
            for j in range(i + 1, n):
                if s[i] == s[j]:
                    f[i][j] = f[i + 1][j - 1] + 2
                else:
                    f[i][j] = max(f[i + 1][j], f[i][j - 1])
                if f[i][j] + k >= n:
                    return True
        return False
```

```java
class Solution {
    public boolean isValidPalindrome(String s, int k) {
        int n = s.length();
        int[][] f = new int[n][n];
        for (int i = 0; i < n; ++i) {
            f[i][i] = 1;
        }
        for (int i = n - 2; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                if (s.charAt(i) == s.charAt(j)) {
                    f[i][j] = f[i + 1][j - 1] + 2;
                } else {
                    f[i][j] = Math.max(f[i + 1][j], f[i][j - 1]);
                }
                if (f[i][j] + k >= n) {
                    return true;
                }
            }
        }
        return false;
    }
}
```

```cpp
class Solution {
public:
    bool isValidPalindrome(string s, int k) {
        int n = s.length();
        int f[n][n];
        memset(f, 0, sizeof f);
        for (int i = 0; i < n; ++i) {
            f[i][i] = 1;
        }
        for (int i = n - 2; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                if (s[i] == s[j]) {
                    f[i][j] = f[i + 1][j - 1] + 2;
                } else {
                    f[i][j] = max(f[i + 1][j], f[i][j - 1]);
                }
                if (f[i][j] + k >= n) {
                    return true;
                }
            }
        }
        return false;
    }
};
```

```go
func isValidPalindrome(s string, k int) bool {
	n := len(s)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, n)
		f[i][i] = 1
	}
	for i := n - 2; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			if s[i] == s[j] {
				f[i][j] = f[i+1][j-1] + 2
			} else {
				f[i][j] = max(f[i+1][j], f[i][j-1])
			}
			if f[i][j]+k >= n {
				return true
			}
		}
	}
	return false
}
```

```ts
function isValidPalindrome(s: string, k: number): boolean {
    const n = s.length;
    const f: number[][] = Array.from({ length: n }, () => Array.from({ length: n }, () => 0));
    for (let i = 0; i < n; ++i) {
        f[i][i] = 1;
    }
    for (let i = n - 2; ~i; --i) {
        for (let j = i + 1; j < n; ++j) {
            if (s[i] === s[j]) {
                f[i][j] = f[i + 1][j - 1] + 2;
            } else {
                f[i][j] = Math.max(f[i + 1][j], f[i][j - 1]);
            }
            if (f[i][j] + k >= n) {
                return true;
            }
        }
    }
    return false;
}
```

```rust
impl Solution {
    pub fn is_valid_palindrome(s: String, k: i32) -> bool {
        let s = s.as_bytes();
        let n = s.len();
        let mut f = vec![vec![0; n]; n];

        for i in 0..n {
            f[i][i] = 1;
        }

        for i in (0..n - 2).rev() {
            for j in i + 1..n {
                if s[i] == s[j] {
                    f[i][j] = f[i + 1][j - 1] + 2;
                } else {
                    f[i][j] = std::cmp::max(f[i + 1][j], f[i][j - 1]);
                }

                if f[i][j] + k >= (n as i32) {
                    return true;
                }
            }
        }

        false
    }
}
```

<!-- tabs:end -->

<!-- end -->
