---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3407.Substring%20Matching%20Pattern/README.md
---

<!-- problem:start -->

# [3407. 子字符串匹配模式](https://leetcode.cn/problems/substring-matching-pattern)

[English Version](/solution/3400-3499/3407.Substring%20Matching%20Pattern/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串&nbsp;<code>s</code>&nbsp;和一个模式字符串&nbsp;<code>p</code>&nbsp;，其中&nbsp;<code>p</code> <strong>恰好</strong>&nbsp;包含 <strong>一个</strong>&nbsp;<code>'*'</code>&nbsp;符号。</p>

<p><code>p</code>&nbsp;中的 <code>'*'</code>&nbsp;符号可以被替换为零个或多个字符组成的任意字符序列。</p>

<p>如果 <code>p</code>&nbsp;可以变成 <code>s</code>&nbsp;的子字符串，那么返回&nbsp;<code>true</code>&nbsp;，否则返回 <code>false</code>&nbsp;。</p>

<p><strong>子字符串</strong>&nbsp;指的是字符串中一段连续 <strong>非空</strong>&nbsp;的字符序列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "leetcode", p = "ee*e"</span></p>

<p><span class="example-io"><b>输出：</b>true</span></p>

<p><b>解释：</b></p>

<p>将&nbsp;<code>'*'</code>&nbsp;替换为&nbsp;<code>"tcod"</code>&nbsp;，子字符串&nbsp;<code>"eetcode"</code>&nbsp;匹配模式串。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "car", p = "c*v"</span></p>

<p><span class="example-io"><b>输出：</b>false</span></p>

<p><strong>解释：</strong></p>

<p>不存在匹配模式串的子字符串。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "luck", p = "u*"</span></p>

<p><span class="example-io"><b>输出：</b>true</span></p>

<p><b>解释：</b></p>

<p>子字符串&nbsp;<code>"u"</code>&nbsp;，<code>"uc"</code>&nbsp;和&nbsp;<code>"uck"</code>&nbsp;都匹配模式串。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 50</code></li>
	<li><code>1 &lt;= p.length &lt;= 50 </code></li>
	<li><code>s</code>&nbsp;只包含小写英文字母。</li>
	<li><code>p</code>&nbsp;只包含小写英文字母和一个&nbsp;<code>'*'</code> 符号。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def hasMatch(self, s: str, p: str) -> bool:
        i = 0
        for t in p.split("*"):
            j = s.find(t, i)
            if j == -1:
                return False
            i = j + len(t)
        return True
```

#### Java

```java
class Solution {
    public boolean hasMatch(String s, String p) {
        int i = 0;
        for (String t : p.split("\\*")) {
            int j = s.indexOf(t, i);
            if (j == -1) {
                return false;
            }
            i = j + t.length();
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool hasMatch(string s, string p) {
        int i = 0;
        int pos = 0;
        int start = 0, end;
        while ((end = p.find("*", start)) != string::npos) {
            string t = p.substr(start, end - start);
            pos = s.find(t, i);
            if (pos == string::npos) {
                return false;
            }
            i = pos + t.length();
            start = end + 1;
        }
        string t = p.substr(start);
        pos = s.find(t, i);
        if (pos == string::npos) {
            return false;
        }
        return true;
    }
};
```

#### Go

```go
func hasMatch(s string, p string) bool {
	i := 0
	for _, t := range strings.Split(p, "*") {
		j := strings.Index(s[i:], t)
		if j == -1 {
			return false
		}
		i += j + len(t)
	}
	return true
}
```

#### TypeScript

```ts
function hasMatch(s: string, p: string): boolean {
    let i = 0;
    for (const t of p.split('*')) {
        const j = s.indexOf(t, i);
        if (j === -1) {
            return false;
        }
        i = j + t.length;
    }
    return true;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
