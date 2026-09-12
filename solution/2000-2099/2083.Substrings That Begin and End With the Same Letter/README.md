---
comments: true
difficulty: 中等
tags:
    - 哈希表
    - 数学
    - 字符串
    - 计数
    - 前缀和
---

<!-- problem:start -->

# [2083. 求以相同字母开头和结尾的子串总数 🔒](https://leetcode.cn/problems/substrings-that-begin-and-end-with-the-same-letter)

[English Version](/solution/2000-2099/2083.Substrings%20That%20Begin%20and%20End%20With%20the%20Same%20Letter/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个仅由小写英文字母组成的，&nbsp; 下标从 <code>0</code> 开始的字符串 <code>s</code> 。返回 <code>s</code> 中以相同字符开头和结尾的子字符串总数。</p>

<p>子字符串是字符串中连续的非空字符序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "abcba"
<strong>输出：</strong>7
<strong>解释：</strong>
以相同字母开头和结尾的长度为 1 的子串是："a"、"b"、"c"、"b" 和 "a" 。
以相同字母开头和结尾的长度为 3 的子串是："bcb" 。
以相同字母开头和结尾的长度为 5 的子串是："abcba" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "abacad"
<strong>输出：</strong>9
<strong>解释：</strong>
以相同字母开头和结尾的长度为 1 的子串是："a"、"b"、"a"、"c"、"a" 和 "d" 。
以相同字母开头和结尾的长度为 3 的子串是："aba" 和 "aca" 。
以相同字母开头和结尾的长度为 5 的子串是："abaca" 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "a"
<strong>输出：</strong>1
<strong>解释：</strong>
只有一个，以相同字母开头和结尾的长度为 1 的子串是："a"。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 仅包含小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数组或哈希表

<!-- thinking:start -->

> **思考**
>
> 子串起点终点同字母。枚举两端是平方，$n \le 10^5$。以某字母结尾的子串个数等于该字母至此出现次数（含单字符）。
>
> 边走边累加计数器，每见到 $c$ 先加一再把新频次加入答案。

<!-- thinking:end -->

我们可以用哈希表或者一个长度为 $26$ 的数组 $\textit{cnt}$ 来记录每个字符出现的次数。

遍历字符串 $\textit{s}$，对于每个字符 $\textit{c}$，我们将 $\textit{cnt}[c]$ 的值加 $1$，然后将 $\textit{cnt}[c]$ 的值加到答案中。

最后返回答案即可。

时间复杂度 $O(n)$，其中 $n$ 是字符串 $\textit{s}$ 的长度。空间复杂度 $O(|\Sigma|)$，其中 $\Sigma$ 是字符集，这里是小写英文字母，所以 $|\Sigma|=26$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfSubstrings(self, s: str) -> int:
        cnt = Counter()
        ans = 0
        for c in s:
            cnt[c] += 1
            ans += cnt[c]
        return ans
```

#### Java

```java
class Solution {
    public long numberOfSubstrings(String s) {
        int[] cnt = new int[26];
        long ans = 0;
        for (char c : s.toCharArray()) {
            ans += ++cnt[c - 'a'];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long numberOfSubstrings(string s) {
        int cnt[26]{};
        long long ans = 0;
        for (char& c : s) {
            ans += ++cnt[c - 'a'];
        }
        return ans;
    }
};
```

#### Go

```go
func numberOfSubstrings(s string) (ans int64) {
	cnt := [26]int{}
	for _, c := range s {
		c -= 'a'
		cnt[c]++
		ans += int64(cnt[c])
	}
	return ans
}
```

#### TypeScript

```ts
function numberOfSubstrings(s: string): number {
    const cnt: Record<string, number> = {};
    let ans = 0;
    for (const c of s) {
        cnt[c] = (cnt[c] || 0) + 1;
        ans += cnt[c];
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn number_of_substrings(s: String) -> i64 {
        let mut cnt = [0; 26];
        let mut ans = 0_i64;
        for c in s.chars() {
            let idx = (c as u8 - b'a') as usize;
            cnt[idx] += 1;
            ans += cnt[idx];
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
var numberOfSubstrings = function (s) {
    const cnt = {};
    let ans = 0;
    for (const c of s) {
        cnt[c] = (cnt[c] || 0) + 1;
        ans += cnt[c];
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
