---
comments: true
difficulty: 简单
rating: 1281
source: 第 211 场周赛 Q1
tags:
    - 哈希表
    - 字符串
---

<!-- problem:start -->

# [1624. 两个相同字符之间的最长子字符串](https://leetcode.cn/problems/largest-substring-between-two-equal-characters)

[English Version](/solution/1600-1699/1624.Largest%20Substring%20Between%20Two%20Equal%20Characters/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>s</code>，请你返回 <strong>两个相同字符之间的最长子字符串的长度</strong> <em>，</em>计算长度时不含这两个字符。如果不存在这样的子字符串，返回 <code>-1</code> 。</p>

<p><strong>子字符串</strong> 是字符串中的一个连续字符序列。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = "aa"
<strong>输出：</strong>0
<strong>解释：</strong>最优的子字符串是两个 'a' 之间的空子字符串。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = "abca"
<strong>输出：</strong>2
<strong>解释：</strong>最优的子字符串是 "bc" 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>s = "cbzxy"
<strong>输出：</strong>-1
<strong>解释：</strong>s 中不存在出现出现两次的字符，所以返回 -1 。
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>s = "cabbac"
<strong>输出：</strong>4
<strong>解释：</strong>最优的子字符串是 "abba" ，其他的非最优解包括 "bb" 和 "" 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 300</code></li>
	<li><code>s</code> 只含小写英文字母</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数组

<!-- thinking:start -->

> **思考**
>
> 两个相同字符之间的子串长度，由这个字符第一次出现和某次更靠后出现的位置差决定。字符串虽然不长，但只要记下每个字母第一次出现的下标，扫一遍就能得到最大间距。
>
> 再次遇到某个字符时，用当前位置减去首次下标再减一来更新答案，并且不要覆盖第一次的下标，这样跨度才最大。
>
> 字符串只含小写字母，用长度为 $26$ 的数组记下首次位置即可；如果没有任何字符出现两次，答案保持 $-1$。

<!-- thinking:end -->

由于字符串 $s$ 只含小写英文字母，我们可以用一个长度为 $26$ 的数组 $d$ 记录每个字符第一次出现的位置，初始时数组元素均为 $-1$。

遍历字符串 $s$ 中每个下标为 $i$ 的字符 $c$，令 $j$ 为 $c$ 相对字母 `a` 的偏移。若 $d[j] = -1$，说明这是 $c$ 第一次出现，令 $d[j] = i$；否则用 $i - d[j] - 1$ 更新答案，即 $ans = \max(ans, i - d[j] - 1)$。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。其中 $n$ 为字符串长度，$C$ 为字符集大小，本题中 $C = 26$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxLengthBetweenEqualCharacters(self, s: str) -> int:
        d = [-1] * 26
        ans = -1
        for i, c in enumerate(s):
            j = ord(c) - ord("a")
            if d[j] == -1:
                d[j] = i
            else:
                ans = max(ans, i - d[j] - 1)
        return ans
```

#### Java

```java
class Solution {
    public int maxLengthBetweenEqualCharacters(String s) {
        int[] d = new int[26];
        Arrays.fill(d, -1);
        int ans = -1;
        for (int i = 0; i < s.length(); ++i) {
            int j = s.charAt(i) - 'a';
            if (d[j] == -1) {
                d[j] = i;
            } else {
                ans = Math.max(ans, i - d[j] - 1);
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
    int maxLengthBetweenEqualCharacters(string s) {
        vector<int> d(26, -1);
        int ans = -1;
        for (int i = 0; i < s.size(); ++i) {
            int j = s[i] - 'a';
            if (d[j] == -1) {
                d[j] = i;
            } else {
                ans = max(ans, i - d[j] - 1);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxLengthBetweenEqualCharacters(s string) int {
	d := make([]int, 26)
	for i := range d {
		d[i] = -1
	}
	ans := -1
	for i := range s {
		j := int(s[i] - 'a')
		if d[j] == -1 {
			d[j] = i
		} else {
			ans = max(ans, i-d[j]-1)
		}
	}
	return ans
}
```

#### TypeScript

```ts
function maxLengthBetweenEqualCharacters(s: string): number {
    const d = Array(26).fill(-1);
    let ans = -1;
    for (let i = 0; i < s.length; ++i) {
        const j = s.charCodeAt(i) - 97;
        if (d[j] === -1) {
            d[j] = i;
        } else {
            ans = Math.max(ans, i - d[j] - 1);
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_length_between_equal_characters(s: String) -> i32 {
        let s = s.as_bytes();
        let mut d = [-1; 26];
        let mut ans = -1;
        for i in 0..s.len() {
            let j = (s[i] - b'a') as usize;
            if d[j] == -1 {
                d[j] = i as i32;
            } else {
                ans = ans.max(i as i32 - d[j] - 1);
            }
        }
        ans
    }
}
```

#### C

```c
#define max(a, b) (((a) > (b)) ? (a) : (b))

int maxLengthBetweenEqualCharacters(char* s) {
    int d[26];
    memset(d, -1, sizeof(d));
    int ans = -1;
    for (int i = 0; s[i]; ++i) {
        int j = s[i] - 'a';
        if (d[j] == -1) {
            d[j] = i;
        } else {
            ans = max(ans, i - d[j] - 1);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
