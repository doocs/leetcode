---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3803.Count%20Residue%20Prefixes/README.md
rating: 1248
source: 第 484 场周赛 Q1
tags:
    - 哈希表
    - 字符串
---

<!-- problem:start -->

# [3803. 统计残差前缀](https://leetcode.cn/problems/count-residue-prefixes)

[English Version](/solution/3800-3899/3803.Count%20Residue%20Prefixes/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个仅由小写英文字母组成的字符串 <code>s</code>。</p>

<p>如果字符串 <code>s</code> 的某个&nbsp;<strong>前缀</strong>&nbsp;中<strong>&nbsp;不同字符的数量</strong>&nbsp;等于 <code>len(prefix) % 3</code>，则该前缀被称为<strong>残差前缀</strong>（residue）。</p>

<p>返回字符串 <code>s</code> 中<strong>&nbsp;残差前缀&nbsp;</strong>的数量。</p>

<p>字符串的<strong>&nbsp;前缀&nbsp;</strong>是一个&nbsp;<strong>非空子字符串</strong>，从字符串的开头起始并延伸到任意位置。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">s = "abc"</span></p>

<p><strong>输出:</strong> <span class="example-io">2</span></p>

<p><strong>解释:</strong>​​​​​​​</p>

<ul>
	<li>前缀 <code>"a"</code> 有 1 个不同字符，且长度模 3 为 1，因此它是一个残差前缀。</li>
	<li>前缀 <code>"ab"</code> 有 2 个不同字符，且长度模 3 为 2，因此它是一个残差前缀。</li>
	<li>前缀 <code>"abc"</code> 不满足条件，因此不是残差前缀。</li>
</ul>

<p>因此，答案是 2。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">s = "dd"</span></p>

<p><strong>输出:</strong> <span class="example-io">1</span></p>

<p><strong>解释:</strong>​​​​​​​</p>

<ul>
	<li>前缀 <code>"d"</code> 有 1 个不同字符，且长度模 3 为 1，因此它是一个残差前缀。</li>
	<li>前缀 <code>"dd"</code> 有 1 个不同字符，但长度模 3 为 2，因此它不是残差前缀。</li>
</ul>

<p>因此，答案是 1。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">s = "bob"</span></p>

<p><strong>输出:</strong> <span class="example-io">2</span></p>

<p><strong>解释:</strong></p>

<ul>
	<li>前缀 <code>"b"</code> 有 1 个不同字符，且长度模 3 为 1，因此它是一个残差前缀。</li>
	<li>前缀 <code>"bo"</code> 有 2 个不同字符，且长度模 3 为 2，因此它是一个残差前缀。</li>
	<li>前缀 <code>"bob"</code> 不满足条件。</li>
</ul>

<p>因此，答案是 2。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> 仅包含小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

我们用一个哈希表 $\textit{st}$ 来记录当前前缀中出现过的不同字符的集合。遍历字符串 $s$ 的每个字符 $c$，将其加入集合 $\textit{st}$ 中，然后判断当前前缀的长度对 $3$ 取模的结果是否等于集合 $\textit{st}$ 的大小。如果相等，则说明当前前缀是一个残差前缀，答案加 $1$。

遍历结束后，返回答案即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是字符串 $s$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def residuePrefixes(self, s: str) -> int:
        st = set()
        ans = 0
        for i, c in enumerate(s, 1):
            st.add(c)
            if len(st) == i % 3:
                ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int residuePrefixes(String s) {
        Set<Character> st = new HashSet<>();
        int ans = 0;
        for (int i = 1; i <= s.length(); i++) {
            char c = s.charAt(i - 1);
            st.add(c);
            if (st.size() == i % 3) {
                ans++;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int residuePrefixes(string s) {
        unordered_set<char> st;
        int ans = 0;
        for (int i = 1; i <= s.size(); i++) {
            char c = s[i - 1];
            st.insert(c);
            if (st.size() == i % 3) {
                ans++;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func residuePrefixes(s string) int {
	st := make(map[rune]struct{})
	ans := 0
	for i, c := range s {
		idx := i + 1
		st[c] = struct{}{}
		if len(st) == idx%3 {
			ans++
		}
	}
	return ans
}
```

#### TypeScript

```ts
function residuePrefixes(s: string): number {
    const st = new Set<string>();
    let ans = 0;
    for (let i = 0; i < s.length; i++) {
        const c = s[i];
        st.add(c);
        if (st.size === (i + 1) % 3) {
            ans++;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
