---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2414.Length%20of%20the%20Longest%20Alphabetical%20Continuous%20Substring/README.md
rating: 1221
source: 第 311 场周赛 Q2
tags:
    - 字符串
---

<!-- problem:start -->

# [2414. 最长的字母序连续子字符串的长度](https://leetcode.cn/problems/length-of-the-longest-alphabetical-continuous-substring)

[English Version](/solution/2400-2499/2414.Length%20of%20the%20Longest%20Alphabetical%20Continuous%20Substring/README_EN.md)

## 题目描述

<!-- description:start -->

<p><strong>字母序连续字符串</strong> 是由字母表中连续字母组成的字符串。换句话说，字符串 <code>"abcdefghijklmnopqrstuvwxyz"</code> 的任意子字符串都是 <strong>字母序连续字符串</strong> 。</p>

<ul>
	<li>例如，<code>"abc"</code> 是一个字母序连续字符串，而 <code>"acb"</code> 和 <code>"za"</code> 不是。</li>
</ul>

<p>给你一个仅由小写英文字母组成的字符串 <code>s</code> ，返回其 <strong>最长</strong> 的 字母序连续子字符串 的长度。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = "abacaba"
<strong>输出：</strong>2
<strong>解释：</strong>共有 4 个不同的字母序连续子字符串 "a"、"b"、"c" 和 "ab" 。
"ab" 是最长的字母序连续子字符串。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = "abcde"
<strong>输出：</strong>5
<strong>解释：</strong>"abcde" 是最长的字母序连续子字符串。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 由小写英文字母组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：一次遍历

我们可以遍历字符串 $s$，用一个变量 $\textit{ans}$ 记录最长的字母序连续子字符串的长度，用另一个变量 $\textit{cnt}$ 记录当前连续子字符串的长度。初始时 $\textit{ans} = \textit{cnt} = 1$。

接下来，我们从下标为 $1$ 的字符开始遍历字符串 $s$，对于每个字符 $s[i]$，如果 $s[i] - s[i - 1] = 1$，则说明当前字符和前一个字符是连续的，此时 $\textit{cnt} = \textit{cnt} + 1$，并更新 $\textit{ans} = \max(\textit{ans}, \textit{cnt})$；否则，说明当前字符和前一个字符不连续，此时 $\textit{cnt} = 1$。

最终返回 $\textit{ans}$ 即可。

时间复杂度 $O(n)$，其中 $n$ 为字符串 $s$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestContinuousSubstring(self, s: str) -> int:
        ans = cnt = 1
        for x, y in pairwise(map(ord, s)):
            if y - x == 1:
                cnt += 1
                ans = max(ans, cnt)
            else:
                cnt = 1
        return ans
```

#### Java

```java
class Solution {
    public int longestContinuousSubstring(String s) {
        int ans = 1, cnt = 1;
        for (int i = 1; i < s.length(); ++i) {
            if (s.charAt(i) - s.charAt(i - 1) == 1) {
                ans = Math.max(ans, ++cnt);
            } else {
                cnt = 1;
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
    int longestContinuousSubstring(string s) {
        int ans = 1, cnt = 1;
        for (int i = 1; i < s.size(); ++i) {
            if (s[i] - s[i - 1] == 1) {
                ans = max(ans, ++cnt);
            } else {
                cnt = 1;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func longestContinuousSubstring(s string) int {
	ans, cnt := 1, 1
	for i := range s[1:] {
		if s[i+1]-s[i] == 1 {
			cnt++
			ans = max(ans, cnt)
		} else {
			cnt = 1
		}
	}
	return ans
}
```

#### TypeScript

```ts
function longestContinuousSubstring(s: string): number {
    let [ans, cnt] = [1, 1];
    for (let i = 1; i < s.length; ++i) {
        if (s.charCodeAt(i) - s.charCodeAt(i - 1) === 1) {
            ans = Math.max(ans, ++cnt);
        } else {
            cnt = 1;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn longest_continuous_substring(s: String) -> i32 {
        let mut ans = 1;
        let mut cnt = 1;
        let s = s.as_bytes();
        for i in 1..s.len() {
            if s[i] - s[i - 1] == 1 {
                cnt += 1;
                ans = ans.max(cnt);
            } else {
                cnt = 1;
            }
        }
        ans
    }
}
```

#### C

```c
#define max(a, b) (((a) > (b)) ? (a) : (b))

int longestContinuousSubstring(char* s) {
    int n = strlen(s);
    int ans = 1, cnt = 1;
    for (int i = 1; i < n; ++i) {
        if (s[i] - s[i - 1] == 1) {
            ++cnt;
            ans = max(ans, cnt);
        } else {
            cnt = 1;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
