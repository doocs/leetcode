---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3707.Equal%20Score%20Substrings/README.md
rating: 1262
source: 第 167 场双周赛 Q1
tags:
    - 字符串
    - 前缀和
---

<!-- problem:start -->

# [3707. 相等子字符串分数](https://leetcode.cn/problems/equal-score-substrings)

[English Version](/solution/3700-3799/3707.Equal%20Score%20Substrings/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个由小写英文字母组成的字符串 <code>s</code>。</p>

<p>一个字符串的&nbsp;<strong>得分&nbsp;</strong>是其字符在字母表中的位置之和，其中 <code>'a' = 1</code>，<code>'b' = 2</code>，...，<code>'z' = 26</code>。</p>

<p>请你判断是否存在一个下标&nbsp;<code>i</code>，使得该字符串可以被拆分成两个&nbsp;<strong>非空子字符串</strong> <code>s[0..i]</code> 和 <code>s[(i + 1)..(n - 1)]</code>，且它们的得分&nbsp;<strong>相等&nbsp;</strong>。</p>

<p>如果存在这样的拆分，则返回 <code>true</code>，否则返回 <code>false</code>。</p>

<p>一个&nbsp;<strong>子字符串&nbsp;</strong>是字符串中&nbsp;<strong>非空&nbsp;</strong>的连续字符序列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">s = "adcb"</span></p>

<p><strong>输出:</strong> <span class="example-io">true</span></p>

<p><strong>解释:</strong></p>

<p>在下标&nbsp;<code>i = 1</code> 处拆分：</p>

<ul>
	<li>左子字符串 = <code>s[0..1] = "ad"</code>，得分 =&nbsp;<code>1 + 4 = 5</code></li>
	<li>右子字符串 = <code>s[2..3] = "cb"</code>，得分 = <code>3 + 2 = 5</code></li>
</ul>

<p>两个子字符串的得分相等，因此输出为 <code>true</code>。</p>
</div>

<p><strong class="example">示例 2:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">s = "bace"</span></p>

<p><strong>输出:</strong> <span class="example-io">false</span></p>

<p><strong>解释:​​​​​​</strong></p>

<p>没有拆分能产生相等的得分，因此输出为 <code>false</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> 由小写英文字母组成。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：前缀和

我们先计算字符串的总得分，记为 $r$。然后我们从左到右遍历前 $n-1$ 个字符，计算前缀得分 $l$，并更新后缀得分 $r$。如果在某个位置 $i$，前缀得分 $l$ 等于后缀得分 $r$，则说明存在一个下标 $i$ 可以将字符串拆分成两个得分相等的子字符串，返回 $\textit{true}$。如果遍历结束后仍未找到这样的下标，返回 $\textit{false}$。

时间复杂度 $O(n)$，其中 $n$ 是字符串的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def scoreBalance(self, s: str) -> bool:
        l = 0
        r = sum(ord(c) - ord("a") + 1 for c in s)
        for c in s[:-1]:
            x = ord(c) - ord("a") + 1
            l += x
            r -= x
            if l == r:
                return True
        return False
```

#### Java

```java
class Solution {
    public boolean scoreBalance(String s) {
        int n = s.length();
        int l = 0, r = 0;
        for (int i = 0; i < n; ++i) {
            int x = s.charAt(i) - 'a' + 1;
            r += x;
        }
        for (int i = 0; i < n - 1; ++i) {
            int x = s.charAt(i) - 'a' + 1;
            l += x;
            r -= x;
            if (l == r) {
                return true;
            }
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool scoreBalance(string s) {
        int l = 0, r = 0;
        for (char c : s) {
            int x = c - 'a' + 1;
            r += x;
        }
        for (int i = 0; i < s.size() - 1; ++i) {
            int x = s[i] - 'a' + 1;
            l += x;
            r -= x;
            if (l == r) {
                return true;
            }
        }
        return false;
    }
};
```

#### Go

```go
func scoreBalance(s string) bool {
	var l, r int
	for _, c := range s {
		x := int(c-'a') + 1
		r += x
	}
	for _, c := range s[:len(s)-1] {
		x := int(c-'a') + 1
		l += x
		r -= x
		if l == r {
			return true
		}
	}
	return false
}
```

#### TypeScript

```ts
function scoreBalance(s: string): boolean {
    let [l, r] = [0, 0];
    for (const c of s) {
        const x = c.charCodeAt(0) - 96;
        r += x;
    }
    for (let i = 0; i < s.length - 1; ++i) {
        const x = s[i].charCodeAt(0) - 96;
        l += x;
        r -= x;
        if (l === r) {
            return true;
        }
    }
    return false;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
