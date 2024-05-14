---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0917.Reverse%20Only%20Letters/README.md
tags:
    - 双指针
    - 字符串
---

# [917. 仅仅反转字母](https://leetcode.cn/problems/reverse-only-letters)

[English Version](/solution/0900-0999/0917.Reverse%20Only%20Letters/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code> ，根据下述规则反转字符串：</p>

<ul>
	<li>所有非英文字母保留在原有位置。</li>
	<li>所有英文字母（小写或大写）位置反转。</li>
</ul>

<p>返回反转后的 <code>s</code><em> 。</em></p>

<p>&nbsp;</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "ab-cd"
<strong>输出：</strong>"dc-ba"
</pre>

<ol>
</ol>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "a-bC-dEf-ghIj"
<strong>输出：</strong>"j-Ih-gfE-dCba"
</pre>

<ol>
</ol>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "Test1ng-Leet=code-Q!"
<strong>输出：</strong>"Qedo1ct-eeLg=ntse-T!"
</pre>

<p>&nbsp;</p>

<p><strong>提示</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> 仅由 ASCII 值在范围 <code>[33, 122]</code> 的字符组成</li>
	<li><code>s</code> 不含 <code>'\"'</code> 或 <code>'\\'</code></li>
</ul>

## 解法

### 方法一：双指针

我们用两个指针 $i$ 和 $j$ 分别指向字符串的头部和尾部。当 $i < j$ 时，我们不断地移动 $i$ 和 $j$，直到 $i$ 指向一个英文字母，并且 $j$ 指向一个英文字母，然后交换 $s[i]$ 和 $s[j]$。最后返回字符串即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串的长度。

<!-- tabs:start -->

```python
class Solution:
    def reverseOnlyLetters(self, s: str) -> str:
        cs = list(s)
        i, j = 0, len(cs) - 1
        while i < j:
            while i < j and not cs[i].isalpha():
                i += 1
            while i < j and not cs[j].isalpha():
                j -= 1
            if i < j:
                cs[i], cs[j] = cs[j], cs[i]
                i, j = i + 1, j - 1
        return "".join(cs)
```

```java
class Solution {
    public String reverseOnlyLetters(String s) {
        char[] cs = s.toCharArray();
        int i = 0, j = cs.length - 1;
        while (i < j) {
            while (i < j && !Character.isLetter(cs[i])) {
                ++i;
            }
            while (i < j && !Character.isLetter(cs[j])) {
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
        return new String(cs);
    }
}
```

```cpp
class Solution {
public:
    string reverseOnlyLetters(string s) {
        int i = 0, j = s.size() - 1;
        while (i < j) {
            while (i < j && !isalpha(s[i])) {
                ++i;
            }
            while (i < j && !isalpha(s[j])) {
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

```go
func reverseOnlyLetters(s string) string {
	cs := []rune(s)
	i, j := 0, len(s)-1
	for i < j {
		for i < j && !unicode.IsLetter(cs[i]) {
			i++
		}
		for i < j && !unicode.IsLetter(cs[j]) {
			j--
		}
		if i < j {
			cs[i], cs[j] = cs[j], cs[i]
			i++
			j--
		}
	}
	return string(cs)
}
```

```ts
function reverseOnlyLetters(s: string): string {
    const cs = [...s];
    let [i, j] = [0, cs.length - 1];
    while (i < j) {
        while (!/[a-zA-Z]/.test(cs[i]) && i < j) {
            i++;
        }
        while (!/[a-zA-Z]/.test(cs[j]) && i < j) {
            j--;
        }
        [cs[i], cs[j]] = [cs[j], cs[i]];
        i++;
        j--;
    }
    return cs.join('');
}
```

```rust
impl Solution {
    pub fn reverse_only_letters(s: String) -> String {
        let mut cs: Vec<char> = s.chars().collect();
        let n = cs.len();
        let mut l = 0;
        let mut r = n - 1;
        while l < r {
            if !cs[l].is_ascii_alphabetic() {
                l += 1;
            } else if !cs[r].is_ascii_alphabetic() {
                r -= 1;
            } else {
                cs.swap(l, r);
                l += 1;
                r -= 1;
            }
        }
        cs.iter().collect()
    }
}
```

<!-- tabs:end -->

<!-- end -->
