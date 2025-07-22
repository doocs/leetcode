---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0345.Reverse%20Vowels%20of%20a%20String/README.md
tags:
    - 双指针
    - 字符串
---

<!-- problem:start -->

# [345. 反转字符串中的元音字母](https://leetcode.cn/problems/reverse-vowels-of-a-string)

[English Version](/solution/0300-0399/0345.Reverse%20Vowels%20of%20a%20String/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>s</code> ，仅反转字符串中的所有元音字母，并返回结果字符串。</p>

<p>元音字母包括 <code>'a'</code>、<code>'e'</code>、<code>'i'</code>、<code>'o'</code>、<code>'u'</code>，且可能以大小写两种形式出现不止一次。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">s = "IceCreAm"</span></p>

<p><span class="example-io"><b>输出：</b>"AceCreIm"</span></p>

<p><strong>解释：</strong></p>

<p><code>s</code>&nbsp;中的元音是&nbsp;<code>['I', 'e', 'e', 'A']</code>。反转这些元音，<code>s</code> 变为&nbsp;<code>"AceCreIm"</code>.</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "leetcode"</span></p>

<p><strong>输出：</strong><span class="example-io">"leotcede"</span></p>

<p>&nbsp;</p>
</div>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 3 * 10<sup>5</sup></code></li>
	<li><code>s</code> 由 <strong>可打印的 ASCII</strong> 字符组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：双指针

我们可以用两个指针 $i$ 和 $j$，初始时分别指向字符串的首尾。

每次循环判断 $i$ 指向的字符是否是元音字母，如果不是则向后移动 $i$；同理，判断 $j$ 指向的字符是否是元音字母，如果不是则向前移动 $j$。如果此时 $i \lt j$，那么 $i$ 和 $j$ 指向的字符都是元音字母，交换这两个字符。然后向后移动 $i$，向前移动 $j$。继续上述操作，直到 $i \ge j$。

时间复杂度 $O(n)$，其中 $n$ 是字符串的长度。空间复杂度 $O(|\Sigma|)$，其中 $\Sigma$ 是字符集的大小。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def reverseVowels(self, s: str) -> str:
        vowels = "aeiouAEIOU"
        i, j = 0, len(s) - 1
        cs = list(s)
        while i < j:
            while i < j and cs[i] not in vowels:
                i += 1
            while i < j and cs[j] not in vowels:
                j -= 1
            if i < j:
                cs[i], cs[j] = cs[j], cs[i]
                i, j = i + 1, j - 1
        return "".join(cs)
```

#### Java

```java
class Solution {
    public String reverseVowels(String s) {
        boolean[] vowels = new boolean[128];
        for (char c : "aeiouAEIOU".toCharArray()) {
            vowels[c] = true;
        }
        char[] cs = s.toCharArray();
        int i = 0, j = cs.length - 1;
        while (i < j) {
            while (i < j && !vowels[cs[i]]) {
                ++i;
            }
            while (i < j && !vowels[cs[j]]) {
                --j;
            }
            if (i < j) {
                char t = cs[i];
                cs[i] = cs[j];
                cs[j] = t;
                ++i;
                --j;
            }
        }
        return String.valueOf(cs);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string reverseVowels(string s) {
        bool vowels[128];
        memset(vowels, false, sizeof(vowels));
        for (char c : "aeiouAEIOU") {
            vowels[c] = true;
        }
        int i = 0, j = s.size() - 1;
        while (i < j) {
            while (i < j && !vowels[s[i]]) {
                ++i;
            }
            while (i < j && !vowels[s[j]]) {
                --j;
            }
            if (i < j) {
                swap(s[i++], s[j--]);
            }
        }
        return s;
    }
};
```

#### Go

```go
func reverseVowels(s string) string {
	vowels := [128]bool{}
	for _, c := range "aeiouAEIOU" {
		vowels[c] = true
	}
	cs := []byte(s)
	i, j := 0, len(cs)-1
	for i < j {
		for i < j && !vowels[cs[i]] {
			i++
		}
		for i < j && !vowels[cs[j]] {
			j--
		}
		if i < j {
			cs[i], cs[j] = cs[j], cs[i]
			i, j = i+1, j-1
		}
	}
	return string(cs)
}
```

#### TypeScript

```ts
function reverseVowels(s: string): string {
    const vowels = new Set(['a', 'e', 'i', 'o', 'u']);
    const cs = s.split('');
    for (let i = 0, j = cs.length - 1; i < j; ++i, --j) {
        while (i < j && !vowels.has(cs[i].toLowerCase())) {
            ++i;
        }
        while (i < j && !vowels.has(cs[j].toLowerCase())) {
            --j;
        }
        [cs[i], cs[j]] = [cs[j], cs[i]];
    }
    return cs.join('');
}
```

#### Rust

```rust
impl Solution {
    pub fn reverse_vowels(s: String) -> String {
        let vowel = String::from("aeiouAEIOU");
        let mut data: Vec<char> = s.chars().collect();
        let (mut i, mut j) = (0, s.len() - 1);
        while i < j {
            while i < j && !vowel.contains(data[i]) {
                i += 1;
            }
            while i < j && !vowel.contains(data[j]) {
                j -= 1;
            }
            if i < j {
                data.swap(i, j);
                i += 1;
                j -= 1;
            }
        }
        data.iter().collect()
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
