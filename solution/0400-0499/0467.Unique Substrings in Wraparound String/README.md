---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0467.Unique%20Substrings%20in%20Wraparound%20String/README.md
tags:
    - 字符串
    - 动态规划
---

<!-- problem:start -->

# [467. 环绕字符串中唯一的子字符串](https://leetcode.cn/problems/unique-substrings-in-wraparound-string)

[English Version](/solution/0400-0499/0467.Unique%20Substrings%20in%20Wraparound%20String/README_EN.md)

## 题目描述

<!-- description:start -->

<p>定义字符串&nbsp;<code>base</code>&nbsp;为一个&nbsp;<code>"abcdefghijklmnopqrstuvwxyz"</code>&nbsp;无限环绕的字符串，所以&nbsp;<code>base</code>&nbsp;看起来是这样的：</p>

<ul>
	<li><code>"...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd...."</code>.</li>
</ul>

<p>给你一个字符串&nbsp;<code>s</code> ，请你统计并返回&nbsp;<code>s</code>&nbsp;中有多少&nbsp;<strong>不同</strong><strong>非空子串</strong>&nbsp;也在&nbsp;<code>base</code>&nbsp;中出现。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1：</strong></p>

<pre>
<strong>输入：</strong>s = "a"
<strong>输出：</strong>1
<strong>解释：</strong>字符串 s 的子字符串 "a" 在 base 中出现。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "cac"
<strong>输出：</strong>2
<strong>解释：</strong>字符串 s 有两个子字符串 ("a", "c") 在 base 中出现。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "zab"
<strong>输出：</strong>6
<strong>解释：</strong>字符串 s 有六个子字符串 ("z", "a", "b", "za", "ab", and "zab") 在 base 中出现。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><font color="#c7254e" face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size: 12.6px; background-color: rgb(249, 242, 244);">s</span></font> 由小写英文字母组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

我们不妨定义一个长度为 $26$ 的数组 $f$，其中 $f[i]$ 表示以第 $i$ 个字符结尾的最长连续子串的长度。那么答案就是 $f$ 中所有元素的和。

我们定义一个变量 $k$，表示以当前字符结尾的最长连续子串的长度。遍历字符串 $s$，对于每个字符 $c$，如果 $c$ 和前一个字符 $s[i - 1]$ 之间的差值为 $1$，那么 $k$ 就加 $1$，否则 $k$ 重置为 $1$。然后更新 $f[c]$ 为 $f[c]$ 和 $k$ 的较大值。

最后返回 $f$ 中所有元素的和即可。

时间复杂度 $O(n)$，其中 $n$ 是字符串 $s$ 的长度。空间复杂度 $O(|\Sigma|)$，其中 $\Sigma$ 是字符集，这里是小写字母集合。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findSubstringInWraproundString(self, s: str) -> int:
        f = defaultdict(int)
        k = 0
        for i, c in enumerate(s):
            if i and (ord(c) - ord(s[i - 1])) % 26 == 1:
                k += 1
            else:
                k = 1
            f[c] = max(f[c], k)
        return sum(f.values())
```

#### Java

```java
class Solution {
    public int findSubstringInWraproundString(String s) {
        int[] f = new int[26];
        int n = s.length();
        for (int i = 0, k = 0; i < n; ++i) {
            if (i > 0 && (s.charAt(i) - s.charAt(i - 1) + 26) % 26 == 1) {
                ++k;
            } else {
                k = 1;
            }
            f[s.charAt(i) - 'a'] = Math.max(f[s.charAt(i) - 'a'], k);
        }
        return Arrays.stream(f).sum();
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findSubstringInWraproundString(string s) {
        int f[26]{};
        int n = s.length();
        for (int i = 0, k = 0; i < n; ++i) {
            if (i && (s[i] - s[i - 1] + 26) % 26 == 1) {
                ++k;
            } else {
                k = 1;
            }
            f[s[i] - 'a'] = max(f[s[i] - 'a'], k);
        }
        return accumulate(begin(f), end(f), 0);
    }
};
```

#### Go

```go
func findSubstringInWraproundString(s string) (ans int) {
	f := [26]int{}
	k := 0
	for i := range s {
		if i > 0 && (s[i]-s[i-1]+26)%26 == 1 {
			k++
		} else {
			k = 1
		}
		f[s[i]-'a'] = max(f[s[i]-'a'], k)
	}
	for _, x := range f {
		ans += x
	}
	return
}
```

#### TypeScript

```ts
function findSubstringInWraproundString(s: string): number {
    const idx = (c: string): number => c.charCodeAt(0) - 97;
    const f: number[] = Array(26).fill(0);
    const n = s.length;
    for (let i = 0, k = 0; i < n; ++i) {
        const j = idx(s[i]);
        if (i && (j - idx(s[i - 1]) + 26) % 26 === 1) {
            ++k;
        } else {
            k = 1;
        }
        f[j] = Math.max(f[j], k);
    }
    return f.reduce((acc, cur) => acc + cur, 0);
}
```

#### Rust

```rust
impl Solution {
    pub fn find_substring_in_wrapround_string(s: String) -> i32 {
        let idx = |c: u8| -> usize { (c - b'a') as usize };
        let mut f = vec![0; 26];
        let n = s.len();
        let s = s.as_bytes();
        let mut k = 0;
        for i in 0..n {
            let j = idx(s[i]);
            if i > 0 && ((j as i32) - (idx(s[i - 1]) as i32) + 26) % 26 == 1 {
                k += 1;
            } else {
                k = 1;
            }
            f[j] = f[j].max(k);
        }

        f.iter().sum()
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
