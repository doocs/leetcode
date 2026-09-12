---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3571.Find%20the%20Shortest%20Superstring%20II/README.md
tags:
    - 字符串
---

<!-- problem:start -->

# [3571. 最短超级串 II 🔒](https://leetcode.cn/problems/find-the-shortest-superstring-ii)

[English Version](/solution/3500-3599/3571.Find%20the%20Shortest%20Superstring%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定 <strong>两</strong> 个字符串，<code>s1</code> 和&nbsp;<code>s2</code>。返回同时包含 <code>s1</code> 和&nbsp;<code>s2</code>&nbsp;作为子串的 <strong>最短</strong>&nbsp;字符串。如果有多个合法的答案，返回任意一个。</p>

<p><strong>子串</strong> 是字符串中连续的字符序列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">s1 = "aba", s2 = "bab"</span></p>

<p><span class="example-io"><b>输出：</b>"abab"</span></p>

<p><strong>解释：</strong></p>

<p><code>"abab"</code>&nbsp;是同时包含 <code>"aba"</code> 和&nbsp;<code>"bab"</code>&nbsp;作为子串的最短字符串。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">s1 = "aa", s2 = "aaa"</span></p>

<p><span class="example-io"><b>输出：</b>"aaa"</span></p>

<p><strong>解释：</strong></p>

<p><code>"aa"</code>&nbsp;已经被包含在&nbsp;<code>"aaa"</code>&nbsp; 中，所以最短超级串是&nbsp;<code>"aaa"</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li data-end="23" data-start="2"><code>1 &lt;= s1.length &lt;= 100</code></li>
	<li data-end="47" data-start="26"><code>1 &lt;= s2.length &lt;= 100</code></li>
	<li data-end="102" data-is-last-node="" data-start="50"><code>s1</code> 和&nbsp;<code>s2</code>&nbsp;只包含小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举重叠部分

<!-- thinking:start -->

> **思考**
>
> 最短超串要么是较长者（若已包含较短者），要么是一侧前缀与另一侧后缀的最大重叠拼接。$|s_1|$、$|s_2|$ 很小，枚举重叠长度即可。
>
> 先保证 $s_1$ 较短；若 $s_1$ 是 $s_2$ 的子串则返回 $s_2$。否则从大到小试 $s_1$ 的后缀是否为 $s_2$ 的前缀、前缀是否为 $s_2$ 的后缀，取能拼出的串；都失败则直接拼接。

<!-- thinking:end -->

我们可以通过枚举两个字符串的重叠部分，构造一个包含 `s1` 和 `s2` 的最短字符串。

我们希望构造一个最短的字符串，使得它同时包含 `s1` 和 `s2` 作为子串。由于子串要求是连续的，因此我们尝试让一个字符串的**后缀**和另一个字符串的**前缀**重叠，从而实现拼接时长度的压缩。

具体分为三种情况：

1. **包含关系**：如果 `s1` 是 `s2` 的子串，那么 `s2` 本身就满足条件，返回 `s2` 即可；反之亦然。
2. **s1 在前拼接 s2**：我们枚举 `s1` 的后缀是否是 `s2` 的前缀，找到最大重叠部分后拼接。
3. **s2 在前拼接 s1**：我们枚举 `s1` 的前缀是否是 `s2` 的后缀，找到最大重叠部分后拼接。
4. **无重叠**：若两者无任何前后缀重叠，直接返回 `s1 + s2`。

我们对这两种拼接方式都尝试一遍，取较短的那个（若长度相同可返回任意一个）。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 是 `s1` 和 `s2` 的最大长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def shortestSuperstring(self, s1: str, s2: str) -> str:
        m, n = len(s1), len(s2)
        if m > n:
            return self.shortestSuperstring(s2, s1)
        if s1 in s2:
            return s2
        for i in range(m):
            if s2.startswith(s1[i:]):
                return s1[:i] + s2
            if s2.endswith(s1[: m - i]):
                return s2 + s1[m - i :]
        return s1 + s2
```

#### Java

```java
class Solution {
    public String shortestSuperstring(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        if (m > n) {
            return shortestSuperstring(s2, s1);
        }
        if (s2.contains(s1)) {
            return s2;
        }
        for (int i = 0; i < m; i++) {
            if (s2.startsWith(s1.substring(i))) {
                return s1.substring(0, i) + s2;
            }
            if (s2.endsWith(s1.substring(0, m - i))) {
                return s2 + s1.substring(m - i);
            }
        }
        return s1 + s2;
    }
}
```

#### C++

```cpp
class Solution {
public:
    string shortestSuperstring(string s1, string s2) {
        int m = s1.size(), n = s2.size();
        if (m > n) {
            return shortestSuperstring(s2, s1);
        }
        if (s2.find(s1) != string::npos) {
            return s2;
        }
        for (int i = 0; i < m; ++i) {
            if (s2.find(s1.substr(i)) == 0) {
                return s1.substr(0, i) + s2;
            }
            if (s2.rfind(s1.substr(0, m - i)) == s2.size() - (m - i)) {
                return s2 + s1.substr(m - i);
            }
        }
        return s1 + s2;
    }
};
```

#### Go

```go
func shortestSuperstring(s1 string, s2 string) string {
	m, n := len(s1), len(s2)

	if m > n {
		return shortestSuperstring(s2, s1)
	}

	if strings.Contains(s2, s1) {
		return s2
	}

	for i := 0; i < m; i++ {
		if strings.HasPrefix(s2, s1[i:]) {
			return s1[:i] + s2
		}
		if strings.HasSuffix(s2, s1[:m-i]) {
			return s2 + s1[m-i:]
		}
	}

	return s1 + s2
}
```

#### TypeScript

```ts
function shortestSuperstring(s1: string, s2: string): string {
    const m = s1.length,
        n = s2.length;

    if (m > n) {
        return shortestSuperstring(s2, s1);
    }

    if (s2.includes(s1)) {
        return s2;
    }

    for (let i = 0; i < m; i++) {
        if (s2.startsWith(s1.slice(i))) {
            return s1.slice(0, i) + s2;
        }
        if (s2.endsWith(s1.slice(0, m - i))) {
            return s2 + s1.slice(m - i);
        }
    }

    return s1 + s2;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
