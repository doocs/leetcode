---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2063.Vowels%20of%20All%20Substrings/README.md
rating: 1663
source: 第 266 场周赛 Q2
tags:
    - 数学
    - 字符串
    - 动态规划
    - 组合数学
---

<!-- problem:start -->

# [2063. 所有子字符串中的元音](https://leetcode.cn/problems/vowels-of-all-substrings)

[English Version](/solution/2000-2099/2063.Vowels%20of%20All%20Substrings/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>word</code> ，返回 <code>word</code> 的所有子字符串中 <strong>元音的总数</strong> ，元音是指 <code>'a'</code>、<code>'e'</code><em>、</em><code>'i'</code><em>、</em><code>'o'</code><em> </em>和 <code>'u'</code><em> 。</em></p>

<p><strong>子字符串</strong> 是字符串中一个连续（非空）的字符序列。</p>

<p><strong>注意：</strong>由于对 <code>word</code> 长度的限制比较宽松，答案可能超过有符号 32 位整数的范围。计算时需当心。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>word = "aba"
<strong>输出：</strong>6
<strong>解释：</strong>
所有子字符串是："a"、"ab"、"aba"、"b"、"ba" 和 "a" 。
- "b" 中有 0 个元音
- "a"、"ab"、"ba" 和 "a" 每个都有 1 个元音
- "aba" 中有 2 个元音
因此，元音总数 = 0 + 1 + 1 + 1 + 1 + 2 = 6 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>word = "abc"
<strong>输出：</strong>3
<strong>解释：</strong>
所有子字符串是："a"、"ab"、"abc"、"b"、"bc" 和 "c" 。
- "a"、"ab" 和 "abc" 每个都有 1 个元音
- "b"、"bc" 和 "c" 每个都有 0 个元音
因此，元音总数 = 1 + 1 + 1 + 0 + 0 + 0 = 3 。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>word = "ltcd"
<strong>输出：</strong>0
<strong>解释：</strong>"ltcd" 的子字符串均不含元音。</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>word = "noosabasboosa"
<strong>输出：</strong>237
<strong>解释：</strong>所有子字符串中共有 237 个元音。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 10<sup>5</sup></code></li>
	<li><code>word</code> 由小写英文字母组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举贡献

我们可以枚举字符串的每个字符 $\textit{word}[i]$，如果 $\textit{word}[i]$ 是元音字母，那么 $\textit{word}[i]$ 一共在 $(i + 1) \times (n - i)$ 个子字符串中出现，将这些子字符串的个数累加即可。

时间复杂度 $O(n)$，其中 $n$ 为字符串 $\textit{word}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countVowels(self, word: str) -> int:
        n = len(word)
        return sum((i + 1) * (n - i) for i, c in enumerate(word) if c in 'aeiou')
```

#### Java

```java
class Solution {
    public long countVowels(String word) {
        long ans = 0;
        for (int i = 0, n = word.length(); i < n; ++i) {
            char c = word.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                ans += (i + 1L) * (n - i);
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
    long long countVowels(string word) {
        long long ans = 0;
        for (int i = 0, n = word.size(); i < n; ++i) {
            char c = word[i];
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                ans += (i + 1LL) * (n - i);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countVowels(word string) (ans int64) {
	for i, c := range word {
		if c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' {
			ans += int64((i + 1) * (len(word) - i))
		}
	}
	return
}
```

#### TypeScript

```ts
function countVowels(word: string): number {
    const n = word.length;
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        if (['a', 'e', 'i', 'o', 'u'].includes(word[i])) {
            ans += (i + 1) * (n - i);
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn count_vowels(word: String) -> i64 {
        let n = word.len() as i64;
        word.chars()
            .enumerate()
            .filter(|(_, c)| "aeiou".contains(*c))
            .map(|(i, _)| (i as i64 + 1) * (n - i as i64))
            .sum()
    }
}
```

#### JavaScript

```js
/**
 * @param {string} word
 * @return {number}
 */
var countVowels = function (word) {
    const n = word.length;
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        if (['a', 'e', 'i', 'o', 'u'].includes(word[i])) {
            ans += (i + 1) * (n - i);
        }
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
