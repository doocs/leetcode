---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3856.Trim%20Trailing%20Vowels/README.md
---

<!-- problem:start -->

# [3856. 移除尾部元音字母](https://leetcode.cn/problems/trim-trailing-vowels)

[English Version](/solution/3800-3899/3856.Trim%20Trailing%20Vowels/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个由小写英文字母组成的字符串 <code>s</code>。</p>

<p>返回移除字符串 <code>s</code> 尾部<strong>&nbsp;所有</strong><strong>元音字母</strong>&nbsp;后得到的字符串。</p>

<p>元音字母包括字符 <code>'a'</code>、<code>'e'</code>、<code>'i'</code>、<code>'o'</code> 和 <code>'u'</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "idea"</span></p>

<p><strong>输出：</strong> <span class="example-io">"id"</span></p>

<p><strong>解释：</strong></p>

<p>移除 <code>"id<u><strong>ea</strong></u>"</code> 后，得到字符串 <code>"id"</code>。</p>
</div>

<p><strong>示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "day"</span></p>

<p><strong>输出：</strong> <span class="example-io">"day"</span></p>

<p><strong>解释：</strong></p>

<p>字符串 <code>"day"</code>&nbsp;尾部没有元音字母。</p>
</div>

<p><strong>示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "aeiou"</span></p>

<p><strong>输出：</strong> <span class="example-io">""</span></p>

<p><strong>解释：</strong></p>

<p>移除 <code>"<u><strong>aeiou</strong></u>"</code> 后，得到字符串 <code>""</code>。</p>
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

### 方法一：逆序遍历

我们从字符串的末尾开始逆序遍历，直到遇到第一个非元音字母为止。然后返回从字符串开头到该位置的子字符串即可。

时间复杂度 $O(n)$，其中 $n$ 是字符串的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def trimTrailingVowels(self, s: str) -> str:
        i = len(s) - 1
        while i >= 0 and s[i] in "aeiou":
            i -= 1
        return s[: i + 1]
```

#### Java

```java
class Solution {
    public String trimTrailingVowels(String s) {
        int i = s.length() - 1;
        while (i >= 0 && "aeiou".indexOf(s.charAt(i)) != -1) {
            i--;
        }
        return s.substring(0, i + 1);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string trimTrailingVowels(string s) {
        int i = s.size() - 1;
        while (i >= 0 && string("aeiou").find(s[i]) != string::npos) {
            i--;
        }
        return s.substr(0, i + 1);
    }
};
```

#### Go

```go
func trimTrailingVowels(s string) string {
	i := len(s) - 1
	for i >= 0 && strings.IndexByte("aeiou", s[i]) != -1 {
		i--
	}
	return s[:i+1]
}
```

#### TypeScript

```ts
function trimTrailingVowels(s: string): string {
    let i = s.length - 1;
    while (i >= 0 && 'aeiou'.indexOf(s[i]) !== -1) {
        i--;
    }
    return s.slice(0, i + 1);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
