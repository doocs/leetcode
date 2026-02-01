---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3823.Reverse%20Letters%20Then%20Special%20Characters%20in%20a%20String/README.md
---

<!-- problem:start -->

# [3823. 反转一个字符串里的字母后反转特殊字符](https://leetcode.cn/problems/reverse-letters-then-special-characters-in-a-string)

[English Version](/solution/3800-3899/3823.Reverse%20Letters%20Then%20Special%20Characters%20in%20a%20String/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个由小写英文字母和特殊字符组成的字符串 <code>s</code>。</p>

<p>你的任务是 <strong>按顺序</strong> 执行以下操作：</p>

<ul>
	<li><strong>反转</strong><strong>小写字母</strong>，并将它们放回原来字母所占据的位置。</li>
	<li><strong>反转</strong><strong>特殊字符</strong>，并将它们放回原来特殊字符所占据的位置。</li>
</ul>

<p>返回执行反转操作后的结果字符串。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "</span>)ebc#da@f(<span class="example-io">"</span></p>

<p><strong>输出：</strong> <span class="example-io">"</span>(fad@cb#e)<span class="example-io">"</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>字符串中的字母为 <code>['e', 'b', 'c', 'd', 'a', 'f']</code>：

    <ul>
    	<li>反转它们得到 <code>['f', 'a', 'd', 'c', 'b', 'e']</code></li>
    	<li><code>s</code> 变为 <code>")fad#cb@e("</code></li>
    </ul>
    </li>
    <li>字符串中的特殊字符为 <code>[')', '#', '@', '(']</code>：
    <ul>
    	<li>反转它们得到 <code>['(', '@', '#', ')']</code></li>
    	<li><code>s</code> 变为 <code><span class="example-io">"</span>(fad@cb#e)<span class="example-io">"</span></code></li>
    </ul>
    </li>

</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "z"</span></p>

<p><strong>输出：</strong> <span class="example-io">"z"</span></p>

<p><strong>解释：</strong></p>

<p>字符串仅包含一个字母，反转它不会改变字符串。没有特殊字符。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "!@#$%^&amp;*()"</span></p>

<p><strong>输出：</strong> <span class="example-io">"</span>)(*&amp;^%$#@!<span class="example-io">"</span></p>

<p><strong>解释：</strong></p>

<p>字符串不包含字母。字符串全部由特殊字符组成，因此反转特殊字符即反转整个字符串。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> 仅由小写英文字母和 <code>"!@#$%^&amp;*()"</code> 中的特殊字符组成。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def reverseByType(self, s: str) -> str:
        a = []
        b = []
        for c in s:
            if c.isalpha():
                a.append(c)
            else:
                b.append(c)
        return ''.join(a.pop() if c.isalpha() else b.pop() for c in s)
```

#### Java

```java
class Solution {
    public String reverseByType(String s) {
        StringBuilder a = new StringBuilder();
        StringBuilder b = new StringBuilder();
        char[] t = s.toCharArray();
        for (char c : t) {
            if (Character.isLetter(c)) {
                a.append(c);
            } else {
                b.append(c);
            }
        }
        int j = a.length(), k = b.length();
        for (int i = 0; i < t.length; ++i) {
            if (Character.isLetter(t[i])) {
                t[i] = a.charAt(--j);
            } else {
                t[i] = b.charAt(--k);
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
    string reverseByType(string s) {
        string a, b;

        for (char c : s) {
            if (isalpha(c)) {
                a.push_back(c);
            } else {
                b.push_back(c);
            }
        }

        int j = a.size(), k = b.size();
        for (int i = 0; i < s.size(); ++i) {
            if (isalpha(s[i])) {
                s[i] = a[--j];
            } else {
                s[i] = b[--k];
            }
        }

        return s;
    }
};
```

#### Go

```go
func reverseByType(s string) string {
	a := make([]byte, 0)
	b := make([]byte, 0)
	t := []byte(s)

	for _, c := range t {
		if (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') {
			a = append(a, c)
		} else {
			b = append(b, c)
		}
	}

	j, k := len(a), len(b)
	for i := 0; i < len(t); i++ {
		if (t[i] >= 'A' && t[i] <= 'Z') || (t[i] >= 'a' && t[i] <= 'z') {
			j--
			t[i] = a[j]
		} else {
			k--
			t[i] = b[k]
		}
	}

	return string(t)
}
```

#### TypeScript

```ts
function reverseByType(s: string): string {
    const a: string[] = [];
    const b: string[] = [];
    const t = s.split('');

    for (const c of t) {
        if (/[a-zA-Z]/.test(c)) {
            a.push(c);
        } else {
            b.push(c);
        }
    }

    let j = a.length,
        k = b.length;
    for (let i = 0; i < t.length; i++) {
        if (/[a-zA-Z]/.test(t[i])) {
            t[i] = a[--j];
        } else {
            t[i] = b[--k];
        }
    }

    return t.join('');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
