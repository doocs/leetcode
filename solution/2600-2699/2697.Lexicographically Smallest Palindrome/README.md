---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2697.Lexicographically%20Smallest%20Palindrome/README.md
rating: 1303
tags:
    - 贪心
    - 双指针
    - 字符串
---

# [2697. 字典序最小回文串](https://leetcode.cn/problems/lexicographically-smallest-palindrome)

[English Version](/solution/2600-2699/2697.Lexicographically%20Smallest%20Palindrome/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个由 <strong>小写英文字母</strong> 组成的字符串 <code>s</code> ，你可以对其执行一些操作。在一步操作中，你可以用其他小写英文字母 <strong>替换</strong>&nbsp; <code>s</code> 中的一个字符。</p>

<p>请你执行 <strong>尽可能少的操作</strong> ，使 <code>s</code> 变成一个 <strong>回文串</strong> 。如果执行 <strong>最少</strong> 操作次数的方案不止一种，则只需选取 <strong>字典序最小</strong> 的方案。</p>

<p>对于两个长度相同的字符串 <code>a</code> 和 <code>b</code> ，在 <code>a</code> 和 <code>b</code> 出现不同的第一个位置，如果该位置上 <code>a</code> 中对应字母比 <code>b</code> 中对应字母在字母表中出现顺序更早，则认为 <code>a</code> 的字典序比 <code>b</code> 的字典序要小。</p>

<p>返回最终的回文字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "egcfe"
<strong>输出：</strong>"efcfe"
<strong>解释：</strong>将 "egcfe" 变成回文字符串的最小操作次数为 1 ，修改 1 次得到的字典序最小回文字符串是 "efcfe"，只需将 'g' 改为 'f' 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "abcd"
<strong>输出：</strong>"abba"
<strong>解释：</strong>将 "abcd" 变成回文字符串的最小操作次数为 2 ，修改 2 次得到的字典序最小回文字符串是 "abba" 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "seven"
<strong>输出：</strong>"neven"
<strong>解释：</strong>将 "seven" 变成回文字符串的最小操作次数为 1 ，修改 1 次得到的字典序最小回文字符串是 "neven" 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code> 仅由小写英文字母组成</li>
</ul>

## 解法

### 方法一：贪心 + 双指针

我们用两个指针 $i$ 和 $j$ 分别指向字符串的首尾，初始时 $i = 0$, $j = n - 1$。

接下来，我们每一次都贪心地将 $s[i]$ 和 $s[j]$ 修改为它们中的较小值，使得它们相等。然后将 $i$ 向后移动一位，将 $j$ 向前移动一位，继续进行这一过程，直到 $i \ge j$ 为止。此时，我们就得到了最小的回文串。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是字符串的长度。

<!-- tabs:start -->

```python
class Solution:
    def makeSmallestPalindrome(self, s: str) -> str:
        cs = list(s)
        i, j = 0, len(s) - 1
        while i < j:
            cs[i] = cs[j] = min(cs[i], cs[j])
            i, j = i + 1, j - 1
        return "".join(cs)
```

```java
class Solution {
    public String makeSmallestPalindrome(String s) {
        char[] cs = s.toCharArray();
        for (int i = 0, j = cs.length - 1; i < j; ++i, --j) {
            cs[i] = cs[j] = (char) Math.min(cs[i], cs[j]);
        }
        return new String(cs);
    }
}
```

```cpp
class Solution {
public:
    string makeSmallestPalindrome(string s) {
        for (int i = 0, j = s.size() - 1; i < j; ++i, --j) {
            s[i] = s[j] = min(s[i], s[j]);
        }
        return s;
    }
};
```

```go
func makeSmallestPalindrome(s string) string {
	cs := []byte(s)
	for i, j := 0, len(s)-1; i < j; i, j = i+1, j-1 {
		cs[i] = min(cs[i], cs[j])
		cs[j] = cs[i]
	}
	return string(cs)
}
```

```ts
function makeSmallestPalindrome(s: string): string {
    const cs = s.split('');
    for (let i = 0, j = s.length - 1; i < j; ++i, --j) {
        cs[i] = cs[j] = s[i] < s[j] ? s[i] : s[j];
    }
    return cs.join('');
}
```

```rust
impl Solution {
    pub fn make_smallest_palindrome(s: String) -> String {
        let mut cs: Vec<char> = s.chars().collect();
        let n = cs.len();
        for i in 0..n / 2 {
            let j = n - 1 - i;
            cs[i] = std::cmp::min(cs[i], cs[j]);
            cs[j] = cs[i];
        }
        cs.into_iter().collect()
    }
}
```

<!-- tabs:end -->

<!-- end -->
