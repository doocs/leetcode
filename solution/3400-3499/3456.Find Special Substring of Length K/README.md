---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3456.Find%20Special%20Substring%20of%20Length%20K/README.md
tags:
    - 字符串
---

<!-- problem:start -->

# [3456. 找出长度为 K 的特殊子字符串](https://leetcode.cn/problems/find-special-substring-of-length-k)

[English Version](/solution/3400-3499/3456.Find%20Special%20Substring%20of%20Length%20K/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>s</code> 和一个整数 <code>k</code>。</p>

<p>判断是否存在一个长度&nbsp;<strong>恰好&nbsp;</strong>为 <code>k</code> 的子字符串，该子字符串需要满足以下条件：</p>

<ol>
	<li>该子字符串&nbsp;<strong>只包含一个唯一字符</strong>（例如，<code>"aaa"</code> 或 <code>"bbb"</code>）。</li>
	<li>如果该子字符串的&nbsp;<strong>前面&nbsp;</strong>有字符，则该字符必须与子字符串中的字符不同。</li>
	<li>如果该子字符串的&nbsp;<strong>后面&nbsp;</strong>有字符，则该字符也必须与子字符串中的字符不同。</li>
</ol>

<p>如果存在这样的子串，返回 <code>true</code>；否则，返回 <code>false</code>。</p>

<p><strong>子字符串&nbsp;</strong>是字符串中的连续、非空字符序列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "aaabaaa", k = 3</span></p>

<p><strong>输出：</strong> <span class="example-io">true</span></p>

<p><strong>解释：</strong></p>

<p>子字符串 <code>s[4..6] == "aaa"</code> 满足条件：</p>

<ul>
	<li>长度为 3。</li>
	<li>所有字符相同。</li>
	<li>子串 <code>"aaa"</code> 前的字符是 <code>'b'</code>，与 <code>'a'</code> 不同。</li>
	<li>子串 <code>"aaa"</code> 后没有字符。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "abc", k = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">false</span></p>

<p><strong>解释：</strong></p>

<p>不存在长度为 2 、仅由一个唯一字符组成且满足所有条件的子字符串。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> 仅由小写英文字母组成。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：双指针

题目相当于要我们找出每一段连续的相同字符，然后判断是否存在一段长度为 $k$ 的子字符串，若存在则返回 $\textit{true}$，否则返回 $\textit{false}$。

我们可以用双指针 $l$ 和 $r$ 来遍历字符串 $s$，当 $s[l] = s[r]$ 时，$r$ 向右移动，直到 $s[r] \neq s[l]$，此时判断 $r - l$ 是否等于 $k$，若等于则返回 $\textit{true}$，否则 $l$ 移动到 $r$ 继续遍历。

时间复杂度 $O(n)$，其中 $n$ 为字符串 $s$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def hasSpecialSubstring(self, s: str, k: int) -> bool:
        l, n = 0, len(s)
        while l < n:
            r = l
            while r < n and s[r] == s[l]:
                r += 1
            if r - l == k:
                return True
            l = r
        return False
```

#### Java

```java
class Solution {
    public boolean hasSpecialSubstring(String s, int k) {
        int n = s.length();
        for (int l = 0, cnt = 0; l < n;) {
            int r = l + 1;
            while (r < n && s.charAt(r) == s.charAt(l)) {
                ++r;
            }
            if (r - l == k) {
                return true;
            }
            l = r;
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool hasSpecialSubstring(string s, int k) {
        int n = s.length();
        for (int l = 0, cnt = 0; l < n;) {
            int r = l + 1;
            while (r < n && s[r] == s[l]) {
                ++r;
            }
            if (r - l == k) {
                return true;
            }
            l = r;
        }
        return false;
    }
};
```

#### Go

```go
func hasSpecialSubstring(s string, k int) bool {
	n := len(s)
	for l := 0; l < n; {
		r := l + 1
		for r < n && s[r] == s[l] {
			r++
		}
		if r-l == k {
			return true
		}
		l = r
	}
	return false
}
```

#### TypeScript

```ts
function hasSpecialSubstring(s: string, k: number): boolean {
    const n = s.length;
    for (let l = 0; l < n; ) {
        let r = l + 1;
        while (r < n && s[r] === s[l]) {
            r++;
        }
        if (r - l === k) {
            return true;
        }
        l = r;
    }
    return false;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
