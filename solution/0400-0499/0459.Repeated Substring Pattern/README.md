---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0459.Repeated%20Substring%20Pattern/README.md
tags:
    - 字符串
    - 字符串匹配
---

<!-- problem:start -->

# [459. 重复的子字符串](https://leetcode.cn/problems/repeated-substring-pattern)

[English Version](/solution/0400-0499/0459.Repeated%20Substring%20Pattern/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个非空的字符串<meta charset="UTF-8" />&nbsp;<code>s</code>&nbsp;，检查是否可以通过由它的一个子串重复多次构成。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> s = "abab"
<strong>输出:</strong> true
<strong>解释:</strong> 可由子串 "ab" 重复两次构成。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> s = "aba"
<strong>输出:</strong> false
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> s = "abcabcabcabc"
<strong>输出:</strong> true
<strong>解释:</strong> 可由子串 "abc" 重复四次构成。 (或子串 "abcabc" 重复两次构成。)
</pre>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<p><meta charset="UTF-8" /></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code>&nbsp;由小写英文字母组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：双倍字符串

若长度为 $n$ 的字符串 $s$ 由 $m$ 个重复子串组成，将 $s$ 拼接在自身上，得到字符串 $ss$，长度为 $2n$，此时若从下标 $1$ 开始查找 $s$，那么查找到的下标一定小于 $s$ 的长度。

若长度为 $n$ 的字符串 $s$ 不由重复子串组成，将 $s$ 拼接在自身上，得到字符串 $ss$，长度为 $2n$，此时若从下标 $1$ 开始查找 $s$，那么查找到的下标一定等于 $s$ 的长度。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 $s$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def repeatedSubstringPattern(self, s: str) -> bool:
        return (s + s).index(s, 1) < len(s)
```

#### Java

```java
class Solution {
    public boolean repeatedSubstringPattern(String s) {
        String str = s + s;
        return str.substring(1, str.length() - 1).contains(s);
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool repeatedSubstringPattern(string s) {
        return (s + s).find(s, 1) < s.size();
    }
};
```

#### Go

```go
func repeatedSubstringPattern(s string) bool {
	return strings.Index(s[1:]+s, s) < len(s)-1
}
```

#### TypeScript

```ts
function repeatedSubstringPattern(s: string): boolean {
    return (s + s).slice(1, (s.length << 1) - 1).includes(s);
}
```

#### Rust

```rust
impl Solution {
    pub fn repeated_substring_pattern(s: String) -> bool {
        (s.clone() + &s)[1..s.len() * 2 - 1].contains(&s)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
