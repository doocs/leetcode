---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3813.Vowel-Consonant%20Score/README.md
---

<!-- problem:start -->

# [3813. 元音辅音得分](https://leetcode.cn/problems/vowel-consonant-score)

[English Version](/solution/3800-3899/3813.Vowel-Consonant%20Score/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>s</code>，由小写英文字母、空格和数字组成。</p>

<p>令 <code>v</code> 表示 <code>s</code> 中元音字母的数量，<code>c</code> 表示辅音字母的数量。</p>

<p>元音字母是 <code>'a'</code>、<code>'e'</code>、<code>'i'</code>、<code>'o'</code> 和 <code>'u'</code>，而英文字母表中除元音外的其他字母均视为辅音字母。</p>

<p>字符串 <code>s</code> 的<strong>&nbsp;得分&nbsp;</strong>定义如下：</p>

<ul>
	<li>如果 <code>c &gt; 0</code>，则 <code>score&nbsp;= floor(v / c)</code>，其中 <code>floor</code> 表示<strong>&nbsp;向下取整&nbsp;</strong>到最接近的整数。</li>
	<li>否则，如果 <code>c = 0</code>，则 <code>score&nbsp;= 0</code>。</li>
</ul>

<p>返回一个整数，表示字符串的得分。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">s = "cooear"</span></p>

<p><strong>输出:</strong> <span class="example-io">2</span></p>

<p><strong>解释:</strong></p>

<p>字符串 <code>s = "cooear"</code> 包含 <code>v = 4</code> 个元音字母 <code>('o', 'o', 'e', 'a')</code> 和 <code>c = 2</code> 个辅音字母 <code>('c', 'r')</code>。</p>

<p>得分为 <code>floor(v / c) = floor(4 / 2) = 2</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">s = "axeyizou"</span></p>

<p><strong>输出:</strong> <span class="example-io">1</span></p>

<p><strong>解释:</strong></p>

<p>字符串 <code>s = "axeyizou"</code> 包含 <code>v = 5</code> 个元音字母 <code>('a', 'e', 'i', 'o', 'u')</code> 和 <code>c = 3</code> 个辅音字母 <code>('x', 'y', 'z')</code>。</p>

<p>得分为 <code>floor(v / c) = floor(5 / 3) = 1</code>。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">s = "au 123"</span></p>

<p><strong>输出:</strong> <span class="example-io">0</span></p>

<p><strong>解释:</strong></p>

<p>字符串 <code>s = "au 123"</code> 不包含辅音字母 <code>(c = 0)</code>，因此得分为 0。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> 仅由小写英文字母、空格和数字组成。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数

我们遍历字符串，统计元音字母和辅音字母的数量，分别记为 $v$ 和 $c$。最后根据题意计算得分即可。

时间复杂度 $O(n)$，其中 $n$ 为字符串的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def vowelConsonantScore(self, s: str) -> int:
        v = c = 0
        for ch in s:
            if ch.isalpha():
                c += 1
                if ch in "aeiou":
                    v += 1
        c -= v
        return 0 if c == 0 else v // c
```

#### Java

```java
class Solution {
    public int vowelConsonantScore(String s) {
        int v = 0, c = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isLetter(ch)) {
                c++;
                if ("aeiou".indexOf(ch) != -1) {
                    v++;
                }
            }
        }
        c -= v;
        return c == 0 ? 0 : v / c;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int vowelConsonantScore(string s) {
        int v = 0, c = 0;
        for (char ch : s) {
            if (isalpha(ch)) {
                c++;
                if (string("aeiou").find(ch) != string::npos) {
                    v++;
                }
            }
        }
        c -= v;
        return c == 0 ? 0 : v / c;
    }
};
```

#### Go

```go
func vowelConsonantScore(s string) int {
	v, c := 0, 0
	for _, ch := range s {
		if unicode.IsLetter(ch) {
			c++
			if strings.ContainsRune("aeiou", ch) {
				v++
			}
		}
	}
	c -= v
	if c == 0 {
		return 0
	}
	return v / c
}
```

#### TypeScript

```ts
function vowelConsonantScore(s: string): number {
    let [v, c] = [0, 0];
    for (const ch of s) {
        if (/[a-zA-Z]/.test(ch)) {
            c++;
            if ('aeiou'.includes(ch)) {
                v++;
            }
        }
    }
    c -= v;
    return c === 0 ? 0 : Math.floor(v / c);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
