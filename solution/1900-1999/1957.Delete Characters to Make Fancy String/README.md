---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1957.Delete%20Characters%20to%20Make%20Fancy%20String/README.md
rating: 1357
source: 第 58 场双周赛 Q1
tags:
    - 字符串
---

<!-- problem:start -->

# [1957. 删除字符使字符串变好](https://leetcode.cn/problems/delete-characters-to-make-fancy-string)

[English Version](/solution/1900-1999/1957.Delete%20Characters%20to%20Make%20Fancy%20String/README_EN.md)

## 题目描述

<!-- description:start -->

<p>一个字符串如果没有 <strong>三个连续</strong>&nbsp;相同字符，那么它就是一个 <strong>好字符串</strong>&nbsp;。</p>

<p>给你一个字符串&nbsp;<code>s</code>&nbsp;，请你从 <code>s</code>&nbsp;删除&nbsp;<strong>最少</strong>&nbsp;的字符，使它变成一个 <strong>好字符串</strong> 。</p>

<p>请你返回删除后的字符串。题目数据保证答案总是 <strong>唯一的 </strong>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>s = "le<strong>e</strong>etcode"
<b>输出：</b>"leetcode"
<strong>解释：</strong>
从第一组 'e' 里面删除一个 'e' ，得到 "leetcode" 。
没有连续三个相同字符，所以返回 "leetcode" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>s = "<strong>a</strong>aab<strong>aa</strong>aa"
<b>输出：</b>"aabaa"
<strong>解释：</strong>
从第一组 'a' 里面删除一个 'a' ，得到 "aabaaaa" 。
从第二组 'a' 里面删除两个 'a' ，得到 "aabaa" 。
没有连续三个相同字符，所以返回 "aabaa" 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>s = "aab"
<b>输出：</b>"aab"
<b>解释：</b>没有连续三个相同字符，所以返回 "aab" 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code>&nbsp;只包含小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们可以遍历字符串 $s$，并使用一个数组 $\textit{ans}$ 记录当前的答案。对于每一个字符 $\textit{s[i]}$，如果 $i \lt 2$ 或者 $s[i]$ 与 $s[i - 1]$ 不等，或者 $s[i]$ 与 $s[i - 2]$ 不等，我们就将 $s[i]$ 添加到 $\textit{ans}$ 中。

最后，我们将 $\textit{ans}$ 中的字符连接起来，就得到了答案。

时间复杂度 $O(n)$，其中 $n$ 为字符串 $s$ 的长度。忽略答案的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def makeFancyString(self, s: str) -> str:
        ans = []
        for i, c in enumerate(s):
            if i < 2 or c != s[i - 1] or c != s[i - 2]:
                ans.append(c)
        return "".join(ans)
```

#### Java

```java
class Solution {
    public String makeFancyString(String s) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (i < 2 || c != s.charAt(i - 1) || c != s.charAt(i - 2)) {
                ans.append(c);
            }
        }
        return ans.toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string makeFancyString(string s) {
        string ans = "";
        for (int i = 0; i < s.length(); ++i) {
            char c = s[i];
            if (i < 2 || c != s[i - 1] || c != s[i - 2]) {
                ans += c;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func makeFancyString(s string) string {
	ans := []byte{}
	for i, ch := range s {
		if c := byte(ch); i < 2 || c != s[i-1] || c != s[i-2] {
			ans = append(ans, c)
		}
	}
	return string(ans)
}
```

#### TypeScript

```ts
function makeFancyString(s: string): string {
    const ans: string[] = [];
    for (let i = 0; i < s.length; ++i) {
        if (s[i] !== s[i - 1] || s[i] !== s[i - 2]) {
            ans.push(s[i]);
        }
    }
    return ans.join('');
}
```

#### JavaScript

```js
/**
 * @param {string} s
 * @return {string}
 */
var makeFancyString = function (s) {
    const ans = [];
    for (let i = 0; i < s.length; ++i) {
        if (s[i] !== s[i - 1] || s[i] !== s[i - 2]) {
            ans.push(s[i]);
        }
    }
    return ans.join('');
};
```

#### PHP

```php
class Solution {
    /**
     * @param String $s
     * @return String
     */
    function makeFancyString($s) {
        $ans = '';
        for ($i = 0; $i < strlen($s); $i++) {
            $c = $s[$i];
            if ($i < 2 || $c !== $s[$i - 1] || $c !== $s[$i - 2]) {
                $ans .= $c;
            }
        }
        return $ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
