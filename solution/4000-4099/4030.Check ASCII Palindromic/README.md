---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4030.Check%20ASCII%20Palindromic/README.md
rating: 1165
source: 第 516 场周赛 Q1
---

<!-- problem:start -->

# [4030. 判断 ASCII 值回文](https://leetcode.cn/problems/check-ascii-palindromic)

[English Version](/solution/4000-4099/4030.Check%20ASCII%20Palindromic/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个由小写英文字母组成的字符串 <code>s</code>。</p>

<p>将 <code>s</code> 中的每个字符替换为其 ASCII 值对应的 8 位二进制表示，<strong>包括前导零</strong>，并保持字符原有顺序，从而构造一个<strong>二进制字符串</strong>。</p>

<p>如果得到的二进制字符串是一个&nbsp;<strong>回文串&nbsp;</strong>，则返回 <code>true</code>；否则返回 <code>false</code>。</p>

<p><strong>二进制字符串</strong>&nbsp;是指仅由字符 <code>'0'</code> 和 <code>'1'</code> 组成的字符串。</p>

<p><strong>回文串&nbsp;</strong>是指正着读和反着读都相同的字符串。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "ff"</span></p>

<p><strong>输出：</strong> <span class="example-io">true</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>字符 <code>f</code> 的 ASCII 值为 102，其 8 位二进制表示为 <code>01100110</code>。</li>
	<li>因此，得到的二进制字符串为 <code>0110011001100110</code>。</li>
	<li>由于该二进制字符串是一个&nbsp;<strong>回文串&nbsp;</strong>，因此输出为 <code>true</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "leet"</span></p>

<p><strong>输出：</strong> <span class="example-io">false</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>字符 <code>l</code>、<code>e</code>、<code>e</code> 和 <code>t</code> 的 ASCII 值分别为 108、101、101 和 116 。</li>
	<li>它们对应的 8 位二进制表示分别为 <code>01101100</code>、<code>01100101</code>、<code>01100101</code> 和 <code>01110100</code>。</li>
	<li>因此，得到的二进制字符串为 <code>01101100011001010110010101110100</code>。</li>
	<li>由于该二进制字符串不是一个<strong>&nbsp;回文串</strong>&nbsp;，因此输出为 <code>false</code>。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> 仅由小写英文字母组成。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们按照题意，将字符串 $s$ 中每个字符替换为其 ASCII 值的 $8$ 位二进制表示（包含前导零），按原顺序拼接得到二进制字符串 $t$，然后判断 $t$ 是否为回文串即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是字符串 $s$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isPalindromic(self, s: str) -> bool:
        t = ''.join(format(ord(c), '08b') for c in s)
        return t == t[::-1]
```

#### Java

```java
class Solution {
    public boolean isPalindromic(String s) {
        StringBuilder t = new StringBuilder();
        for (char c : s.toCharArray()) {
            String b = Integer.toBinaryString(c);
            t.append("0".repeat(8 - b.length())).append(b);
        }
        return t.toString().equals(t.reverse().toString());
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isPalindromic(string s) {
        string t;
        for (unsigned char c : s) {
            for (int i = 7; i >= 0; --i) {
                t += char('0' + ((c >> i) & 1));
            }
        }
        return ranges::equal(t, t | views::reverse);
    }
};
```

#### Go

```go
func isPalindromic(s string) bool {
	var t []byte
	for _, c := range []byte(s) {
		for i := 7; i >= 0; i-- {
			t = append(t, '0'+((c>>i)&1))
		}
	}
	for i := range t[:len(t)/2] {
		if t[i] != t[len(t)-1-i] {
			return false
		}
	}
	return true
}
```

#### TypeScript

```ts
function isPalindromic(s: string): boolean {
    const t = [...s].map(c => c.charCodeAt(0).toString(2).padStart(8, '0')).join('');
    return t === [...t].reverse().join('');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
