---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1930.Unique%20Length-3%20Palindromic%20Subsequences/README.md
rating: 1533
source: 第 249 场周赛 Q2
tags:
    - 位运算
    - 哈希表
    - 字符串
    - 前缀和
---

<!-- problem:start -->

# [1930. 长度为 3 的不同回文子序列](https://leetcode.cn/problems/unique-length-3-palindromic-subsequences)

[English Version](/solution/1900-1999/1930.Unique%20Length-3%20Palindromic%20Subsequences/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>s</code> ，返回 <code>s</code> 中 <strong>长度为 3 </strong>的<strong>不同回文子序列</strong> 的个数。</p>

<p>即便存在多种方法来构建相同的子序列，但相同的子序列只计数一次。</p>

<p><strong>回文</strong> 是正着读和反着读一样的字符串。</p>

<p><strong>子序列</strong> 是由原字符串删除其中部分字符（也可以不删除）且不改变剩余字符之间相对顺序形成的一个新字符串。</p>

<ul>
	<li>例如，<code>"ace"</code> 是 <code>"<strong><em>a</em></strong>b<strong><em>c</em></strong>d<strong><em>e</em></strong>"</code> 的一个子序列。</li>
</ul>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "aabca"
<strong>输出：</strong>3
<strong>解释：</strong>长度为 3 的 3 个回文子序列分别是：
- "aba" ("<strong><em>a</em></strong>a<strong><em>b</em></strong>c<strong><em>a</em></strong>" 的子序列)
- "aaa" ("<strong><em>aa</em></strong>bc<strong><em>a</em></strong>" 的子序列)
- "aca" ("<strong><em>a</em></strong>ab<strong><em>ca</em></strong>" 的子序列)
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "adc"
<strong>输出：</strong>0
<strong>解释：</strong>"adc" 不存在长度为 3 的回文子序列。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "bbcbaba"
<strong>输出：</strong>4
<strong>解释：</strong>长度为 3 的 4 个回文子序列分别是：
- "bbb" ("<strong><em>bb</em></strong>c<strong><em>b</em></strong>aba" 的子序列)
- "bcb" ("<strong><em>b</em></strong>b<strong><em>cb</em></strong>aba" 的子序列)
- "bab" ("<strong><em>b</em></strong>bcb<strong><em>ab</em></strong>a" 的子序列)
- "aba" ("bbcb<strong><em>aba</em></strong>" 的子序列)
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 <= s.length <= 10<sup>5</sup></code></li>
	<li><code>s</code> 仅由小写英文字母组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举两端字符 + 哈希表

由于字符串中只包含小写字母，因此我们可以直接枚举所有的两端字符。对于每一对两端字符 $c$，我们找出它们在字符串中第一次和最后一次出现的位置 $l$ 和 $r$，如果 $r - l > 1$，说明找到了满足条件的回文序列，我们将 $[l+1,..r-1]$ 之间的字符去重后统计个数，即为以 $c$ 为两端字符的回文序列个数，加入答案中。

枚举结束后，即可得到答案。

时间复杂度 $O(n \times |\Sigma|)$，其中 $n$ 为字符串长度，而 $\Sigma$ 为字符集大小，本题中 $|\Sigma| = 26$。空间复杂度 $O(|\Sigma|)$ 或 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countPalindromicSubsequence(self, s: str) -> int:
        ans = 0
        for c in ascii_lowercase:
            l, r = s.find(c), s.rfind(c)
            if r - l > 1:
                ans += len(set(s[l + 1 : r]))
        return ans
```

#### Java

```java
class Solution {
    public int countPalindromicSubsequence(String s) {
        int ans = 0;
        for (char c = 'a'; c <= 'z'; ++c) {
            int l = s.indexOf(c), r = s.lastIndexOf(c);
            int mask = 0;
            for (int i = l + 1; i < r; ++i) {
                int j = s.charAt(i) - 'a';
                if ((mask >> j & 1) == 0) {
                    mask |= 1 << j;
                    ++ans;
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
    int countPalindromicSubsequence(string s) {
        int ans = 0;
        for (char c = 'a'; c <= 'z'; ++c) {
            int l = s.find_first_of(c), r = s.find_last_of(c);
            int mask = 0;
            for (int i = l + 1; i < r; ++i) {
                int j = s[i] - 'a';
                if (mask >> j & 1 ^ 1) {
                    mask |= 1 << j;
                    ++ans;
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countPalindromicSubsequence(s string) (ans int) {
	for c := 'a'; c <= 'z'; c++ {
		l, r := strings.Index(s, string(c)), strings.LastIndex(s, string(c))
		mask := 0
		for i := l + 1; i < r; i++ {
			j := int(s[i] - 'a')
			if mask>>j&1 == 0 {
				mask |= 1 << j
				ans++
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function countPalindromicSubsequence(s: string): number {
    let ans = 0;
    const a = 'a'.charCodeAt(0);
    for (let ch = 0; ch < 26; ++ch) {
        const c = String.fromCharCode(ch + a);
        const l = s.indexOf(c);
        const r = s.lastIndexOf(c);
        let mask = 0;
        for (let i = l + 1; i < r; ++i) {
            const j = s.charCodeAt(i) - a;
            if (((mask >> j) & 1) ^ 1) {
                mask |= 1 << j;
                ++ans;
            }
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn count_palindromic_subsequence(s: String) -> i32 {
        let s_bytes = s.as_bytes();
        let mut ans = 0;
        for c in b'a'..=b'z' {
            if let (Some(l), Some(r)) = (
                s_bytes.iter().position(|&ch| ch == c),
                s_bytes.iter().rposition(|&ch| ch == c),
            ) {
                let mut mask = 0u32;
                for i in (l + 1)..r {
                    let j = (s_bytes[i] - b'a') as u32;
                    if (mask >> j & 1) == 0 {
                        mask |= 1 << j;
                        ans += 1;
                    }
                }
            }
        }
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {string} s
 * @return {number}
 */
var countPalindromicSubsequence = function (s) {
    let ans = 0;
    const a = 'a'.charCodeAt(0);
    for (let ch = 0; ch < 26; ++ch) {
        const c = String.fromCharCode(ch + a);
        const l = s.indexOf(c);
        const r = s.lastIndexOf(c);
        let mask = 0;
        for (let i = l + 1; i < r; ++i) {
            const j = s.charCodeAt(i) - a;
            if (((mask >> j) & 1) ^ 1) {
                mask |= 1 << j;
                ++ans;
            }
        }
    }
    return ans;
};
```

#### C#

```cs
public class Solution {
    public int CountPalindromicSubsequence(string s) {
        int ans = 0;
        for (char c = 'a'; c <= 'z'; ++c) {
            int l = s.IndexOf(c), r = s.LastIndexOf(c);
            int mask = 0;
            for (int i = l + 1; i < r; ++i) {
                int j = s[i] - 'a';
                if ((mask >> j & 1) == 0) {
                    mask |= 1 << j;
                    ++ans;
                }
            }
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
