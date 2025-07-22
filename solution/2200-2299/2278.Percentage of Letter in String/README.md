---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2278.Percentage%20of%20Letter%20in%20String/README.md
rating: 1161
source: 第 294 场周赛 Q1
tags:
    - 字符串
---

<!-- problem:start -->

# [2278. 字母在字符串中的百分比](https://leetcode.cn/problems/percentage-of-letter-in-string)

[English Version](/solution/2200-2299/2278.Percentage%20of%20Letter%20in%20String/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>s</code> 和一个字符 <code>letter</code> ，返回在 <code>s</code> 中等于&nbsp;<code>letter</code>&nbsp;字符所占的 <strong>百分比</strong> ，向下取整到最接近的百分比。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "foobar", letter = "o"
<strong>输出：</strong>33
<strong>解释：</strong>
等于字母 'o' 的字符在 s 中占到的百分比是 2 / 6 * 100% = 33% ，向下取整，所以返回 33 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "jjjj", letter = "k"
<strong>输出：</strong>0
<strong>解释：</strong>
等于字母 'k' 的字符在 s 中占到的百分比是 0% ，所以返回 0 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> 由小写英文字母组成</li>
	<li><code>letter</code> 是一个小写英文字母</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数

我们可以遍历字符串 $\textit{s}$，统计其中等于 $\textit{letter}$ 的字符的个数，然后根据公式 $\textit{count} \times 100 \, / \, \textit{len}(\textit{s})$ 计算百分比。

时间复杂度 $O(n)$，其中 $n$ 为字符串 $\textit{s}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def percentageLetter(self, s: str, letter: str) -> int:
        return s.count(letter) * 100 // len(s)
```

#### Java

```java
class Solution {
    public int percentageLetter(String s, char letter) {
        int cnt = 0;
        for (char c : s.toCharArray()) {
            if (c == letter) {
                ++cnt;
            }
        }
        return cnt * 100 / s.length();
    }
}
```

#### C++

```cpp
class Solution {
public:
    int percentageLetter(string s, char letter) {
        return 100 * ranges::count(s, letter) / s.size();
    }
};
```

#### Go

```go
func percentageLetter(s string, letter byte) int {
	return strings.Count(s, string(letter)) * 100 / len(s)
}
```

#### TypeScript

```ts
function percentageLetter(s: string, letter: string): number {
    const count = s.split('').filter(c => c === letter).length;
    return Math.floor((100 * count) / s.length);
}
```

#### Rust

```rust
impl Solution {
    pub fn percentage_letter(s: String, letter: char) -> i32 {
        let count = s.chars().filter(|&c| c == letter).count();
        (100 * count as i32 / s.len() as i32) as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
