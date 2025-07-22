---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3174.Clear%20Digits/README.md
rating: 1255
source: 第 132 场双周赛 Q1
tags:
    - 栈
    - 字符串
    - 模拟
---

<!-- problem:start -->

# [3174. 清除数字](https://leetcode.cn/problems/clear-digits)

[English Version](/solution/3100-3199/3174.Clear%20Digits/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串&nbsp;<code>s</code>&nbsp;。</p>

<p>你的任务是重复以下操作删除 <strong>所有</strong>&nbsp;数字字符：</p>

<ul>
	<li>删除 <strong>第一个数字字符</strong>&nbsp;以及它左边 <strong>最近</strong>&nbsp;的 <strong>非数字</strong>&nbsp;字符。</li>
</ul>

<p>请你返回删除所有数字字符以后剩下的字符串。</p>

<p><strong>注意</strong>，该操作不能对左侧没有任何非数字字符的数字执行。</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "abc"</span></p>

<p><span class="example-io"><b>输出：</b>"abc"</span></p>

<p><strong>解释：</strong></p>

<p>字符串中没有数字。<!-- notionvc: ff07e34f-b1d6-41fb-9f83-5d0ba3c1ecde --></p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "cb34"</span></p>

<p><span class="example-io"><b>输出：</b>""</span></p>

<p><b>解释：</b></p>

<p>一开始，我们对&nbsp;<code>s[2]</code>&nbsp;执行操作，<code>s</code>&nbsp;变为&nbsp;<code>"c4"</code>&nbsp;。</p>

<p>然后对&nbsp;<code>s[1]</code>&nbsp;执行操作，<code>s</code>&nbsp;变为&nbsp;<code>""</code>&nbsp;。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code>&nbsp;只包含小写英文字母和数字字符。</li>
	<li>输入保证所有数字都可以按以上操作被删除。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：栈 + 模拟

我们用一个栈 `stk` 来模拟这个过程，遍历字符串 `s`，如果当前字符是数字，就弹出栈顶元素，否则将当前字符入栈。

最后将栈中的元素拼接成字符串返回。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 `s` 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def clearDigits(self, s: str) -> str:
        stk = []
        for c in s:
            if c.isdigit():
                stk.pop()
            else:
                stk.append(c)
        return "".join(stk)
```

#### Java

```java
class Solution {
    public String clearDigits(String s) {
        StringBuilder stk = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                stk.deleteCharAt(stk.length() - 1);
            } else {
                stk.append(c);
            }
        }
        return stk.toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string clearDigits(string s) {
        string stk;
        for (char c : s) {
            if (isdigit(c)) {
                stk.pop_back();
            } else {
                stk.push_back(c);
            }
        }
        return stk;
    }
};
```

#### Go

```go
func clearDigits(s string) string {
	stk := []byte{}
	for i := range s {
		if s[i] >= '0' && s[i] <= '9' {
			stk = stk[:len(stk)-1]
		} else {
			stk = append(stk, s[i])
		}
	}
	return string(stk)
}
```

#### TypeScript

```ts
function clearDigits(s: string): string {
    const stk: string[] = [];
    for (const c of s) {
        if (!isNaN(parseInt(c))) {
            stk.pop();
        } else {
            stk.push(c);
        }
    }
    return stk.join('');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
