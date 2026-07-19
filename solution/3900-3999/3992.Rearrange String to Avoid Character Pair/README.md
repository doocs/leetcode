---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3992.Rearrange%20String%20to%20Avoid%20Character%20Pair/README.md
---

<!-- problem:start -->

# [3992. 重新排列字符串以避免字符对](https://leetcode.cn/problems/rearrange-string-to-avoid-character-pair)

[English Version](/solution/3900-3999/3992.Rearrange%20String%20to%20Avoid%20Character%20Pair/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>s</code> 和两个 <strong>不同</strong> 的小写英文字母 <code>x</code> 和 <code>y</code>。</p>

<p>重新排列 <code>s</code> 中的字符来构造一个新的字符串 <code>t</code>，使得：</p>

<ul>
	<li><code>t</code> 是 <code>s</code> 的一个 <strong>排列</strong>。</li>
	<li>在 <code>t</code> 中，所有&nbsp;<code>y</code> 都必须在所有&nbsp;<code>x</code> <strong>之前</strong>。</li>
</ul>

<p>返回 <strong>任意</strong> 一个有效的字符串 <code>t</code>。</p>

<p><strong>排列</strong> 是对一个字符串中所有字符的重新排列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "aabc", x = "a", y = "c"</span></p>

<p><strong>输出：</strong> <span class="example-io">"cbaa"</span></p>

<p><strong>解释：</strong></p>

<p>字符串 <code>"cbaa"</code> 是 <code>"aabc"</code> 的一个排列，且每次出现的 <code>'c'</code> 都在每次出现的 <code>'a'</code> 之前。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "dcab", x = "d", y = "b"</span></p>

<p><strong>输出：</strong> <span class="example-io">"cabd"</span></p>

<p><strong>解释：</strong></p>

<p>字符串 <code>"cabd"</code> 是 <code>"dcab"</code> 的一个排列，且每次出现的 <code>'b'</code> 都在每次出现的 <code>'d'</code> 之前。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "axe", x = "o", y = "x"</span></p>

<p><strong>输出：</strong> <span class="example-io">"axe"</span></p>

<p><strong>解释：</strong></p>

<p>字符串 <code>"axe"</code> 已经有效。因为 <code>'o'</code> 没有在字符串中出现，所以自动满足要求的条件。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> 仅由小写英文字母组成。</li>
	<li><code>x</code> 和 <code>y</code> 都是小写英文字母。</li>
	<li><code>x != y</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：双指针

题目要求构造 $s$ 的一个排列 $t$，使得所有字符 $y$ 都出现在所有字符 $x$ 之前。其余字符的相对位置没有额外约束。

因此，只需把所有 $y$ 移到字符串前面即可。用双指针遍历字符串：指针 $i$ 指向下一个应放置 $y$ 的位置，指针 $j$ 从左到右扫描；每当 $t[j] = y$ 时，交换 $t[i]$ 与 $t[j]$，并将 $i$ 右移一位。扫描结束后，$t$ 的前缀全部为 $y$，自然满足「所有 $y$ 在所有 $x$ 之前」。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是字符串 $s$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def rearrangeString(self, s: str, x: str, y: str) -> str:
        t = list(s)
        i = 0
        for j, c in enumerate(t):
            if c == y:
                t[i], t[j] = c, t[i]
                i += 1
        return ''.join(t)
```

#### Java

```java
class Solution {
    public String rearrangeString(String s, char x, char y) {
        char[] t = s.toCharArray();
        int i = 0;
        for (int j = 0; j < t.length; j++) {
            if (t[j] == y) {
                char tmp = t[i];
                t[i] = t[j];
                t[j] = tmp;
                i++;
            }
        }
        return new String(t);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string rearrangeString(string s, char x, char y) {
        int i = 0;
        for (int j = 0; j < s.size(); j++) {
            if (s[j] == y) {
                swap(s[i], s[j]);
                i++;
            }
        }
        return s;
    }
};
```

#### Go

```go
func rearrangeString(s string, x byte, y byte) string {
	t := []byte(s)
	i := 0
	for j, c := range t {
		if c == y {
			t[i], t[j] = t[j], t[i]
			i++
		}
	}
	return string(t)
}
```

#### TypeScript

```ts
function rearrangeString(s: string, x: string, y: string): string {
    const t = s.split('');
    let i = 0;
    for (let j = 0; j < t.length; j++) {
        if (t[j] === y) {
            [t[i], t[j]] = [t[j], t[i]];
            i++;
        }
    }
    return t.join('');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
