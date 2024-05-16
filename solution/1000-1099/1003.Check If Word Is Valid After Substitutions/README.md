---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1003.Check%20If%20Word%20Is%20Valid%20After%20Substitutions/README.md
rating: 1426
source: 第 126 场周赛 Q2
tags:
    - 栈
    - 字符串
---

<!-- problem:start -->

# [1003. 检查替换后的词是否有效](https://leetcode.cn/problems/check-if-word-is-valid-after-substitutions)

[English Version](/solution/1000-1099/1003.Check%20If%20Word%20Is%20Valid%20After%20Substitutions/README_EN.md)

## 题目描述

<!-- description:start -->

给你一个字符串 <code>s</code> ，请你判断它是否 <strong>有效</strong> 。

<p>字符串 <code>s</code> <strong>有效</strong> 需要满足：假设开始有一个空字符串 <code>t = ""</code> ，你可以执行 <strong>任意次</strong> 下述操作将<strong> </strong><code>t</code><strong> 转换为 </strong><code>s</code> ：</p>

<ul>
	<li>将字符串 <code>"abc"</code> 插入到 <code>t</code> 中的任意位置。形式上，<code>t</code> 变为 <code>t<sub>left</sub> + "abc" + t<sub>right</sub></code>，其中 <code>t == t<sub>left</sub> + t<sub>right</sub></code> 。注意，<code>t<sub>left</sub></code> 和 <code>t<sub>right</sub></code> 可能为 <strong>空</strong> 。</li>
</ul>

<p>如果字符串 <code>s</code> 有效，则返回 <code>true</code>；否则，返回 <code>false</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "aabcbc"
<strong>输出：</strong>true
<strong>解释：</strong>
"" -&gt; "<strong>abc</strong>" -&gt; "a<strong>abc</strong>bc"
因此，"aabcbc" 有效。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "abcabcababcc"
<strong>输出：</strong>true
<strong>解释：</strong>
"" -&gt; "<strong>abc</strong>" -&gt; "abc<strong>abc</strong>" -&gt; "abcabc<strong>abc</strong>" -&gt; "abcabcab<strong>abc</strong>c"
因此，"abcabcababcc" 有效。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "abccba"
<strong>输出：</strong>false
<strong>解释：</strong>执行操作无法得到 "abccba" 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>s</code> 由字母 <code>'a'</code>、<code>'b'</code> 和 <code>'c'</code> 组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：栈

我们观察题目中的操作，可以发现，每一次都会在字符串的任意位置插入字符串 `"abc"`，所以每次插入操作之后，字符串的长度都会增加 $3$。如果字符串 $s$ 有效，那么它的长度一定是 $3$ 的倍数。因此，我们先对字符串 $s$ 的长度进行判断，如果不是 $3$ 的倍数，那么 $s$ 一定无效，可以直接返回 `false`。

接下来我们遍历字符串 $s$ 的每个字符 $c$，我们先将字符 $c$ 压入栈 $t$ 中。如果此时栈 $t$ 的长度大于等于 $3$，并且栈顶的三个元素组成了字符串 `"abc"`，那么我们就将栈顶的三个元素弹出。然后继续遍历字符串 $s$ 的下一个字符。

遍历结束之后，如果栈 $t$ 为空，那么说明字符串 $s$ 有效，返回 `true`，否则返回 `false`。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是字符串 $s$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def isValid(self, s: str) -> bool:
        if len(s) % 3:
            return False
        t = []
        for c in s:
            t.append(c)
            if ''.join(t[-3:]) == 'abc':
                t[-3:] = []
        return not t
```

```java
class Solution {
    public boolean isValid(String s) {
        if (s.length() % 3 > 0) {
            return false;
        }
        StringBuilder t = new StringBuilder();
        for (char c : s.toCharArray()) {
            t.append(c);
            if (t.length() >= 3 && "abc".equals(t.substring(t.length() - 3))) {
                t.delete(t.length() - 3, t.length());
            }
        }
        return t.isEmpty();
    }
}
```

```cpp
class Solution {
public:
    bool isValid(string s) {
        if (s.size() % 3) {
            return false;
        }
        string t;
        for (char c : s) {
            t.push_back(c);
            if (t.size() >= 3 && t.substr(t.size() - 3, 3) == "abc") {
                t.erase(t.end() - 3, t.end());
            }
        }
        return t.empty();
    }
};
```

```go
func isValid(s string) bool {
	if len(s)%3 > 0 {
		return false
	}
	t := []byte{}
	for i := range s {
		t = append(t, s[i])
		if len(t) >= 3 && string(t[len(t)-3:]) == "abc" {
			t = t[:len(t)-3]
		}
	}
	return len(t) == 0
}
```

```ts
function isValid(s: string): boolean {
    if (s.length % 3 !== 0) {
        return false;
    }
    const t: string[] = [];
    for (const c of s) {
        t.push(c);
        if (t.slice(-3).join('') === 'abc') {
            t.splice(-3);
        }
    }
    return t.length === 0;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
