---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3662.Filter%20Characters%20by%20Frequency/README.md
---

<!-- problem:start -->

# [3662. 按频率筛选字符 🔒](https://leetcode.cn/problems/filter-characters-by-frequency)

[English Version](/solution/3600-3699/3662.Filter%20Characters%20by%20Frequency/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个包含小写英文字母的字符串&nbsp;<code>s</code> 和一个整数&nbsp;<code>k</code>。</p>

<p>你的任务是构造一个新的字符串，其中只包含在整个字符串&nbsp;<code>s</code> 中出现次数 <strong>少于</strong> <code>k</code> 次的字符。新字符串中字符的顺序必须与 <code>s</code> 中的 <strong>顺序相同</strong>。</p>

<p>返回结果字符串。如果没有字符满足，返回一个空字符串。</p>

<p>注意：出现次数少于 <code>k</code> 次的字符的每次出现都被保留。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "aadbbcccca", k = 3</span></p>

<p><span class="example-io"><b>输出：</b>"dbb"</span></p>

<p><strong>解释：</strong></p>

<p><code>s</code>&nbsp;中字符出现的频率：</p>

<ul>
	<li><code>'a'</code>&nbsp;出现 3 次</li>
	<li><code>'d'</code> 出现 1&nbsp;次</li>
	<li><code>'b'</code> 出现 2&nbsp;次</li>
	<li><code>'c'</code> 出现 4&nbsp;次</li>
</ul>

<p>只有&nbsp;<code>'d'</code> 和&nbsp;<code>'b'</code>&nbsp;出现少于 3 次。保持它们的顺序，结果是&nbsp;<code>"dbb"</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "xyz", k = 2</span></p>

<p><span class="example-io"><b>输出：</b>"xyz"</span></p>

<p><strong>解释：</strong></p>

<p>所有字符（<code>'x'</code>，<code>'y'</code>，<code>'z'</code>）只出现一次，比 2 少。因此返回整个字符串。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code>&nbsp;只包含小写英文字母。</li>
	<li><code>1 &lt;= k &lt;= s.length</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数

我们先遍历字符串 $s$，统计每个字符出现的频率，记录在哈希表或数组 $\textit{cnt}$ 中。

然后再遍历字符串 $s$，将出现次数少于 $k$ 的字符添加到结果字符串中，最后返回结果字符串。

时间复杂度 $O(n)$，其中 $n$ 是字符串 $s$ 的长度。空间复杂度 $O(|\Sigma|)$，其中 $\Sigma$ 是字符集大小。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def filterCharacters(self, s: str, k: int) -> str:
        cnt = Counter(s)
        ans = []
        for c in s:
            if cnt[c] < k:
                ans.append(c)
        return "".join(ans)
```

#### Java

```java
class Solution {
    public String filterCharacters(String s, int k) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            ++cnt[c - 'a'];
        }
        StringBuilder ans = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (cnt[c - 'a'] < k) {
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
    string filterCharacters(string s, int k) {
        int cnt[26]{};
        for (char c : s) {
            ++cnt[c - 'a'];
        }
        string ans;
        for (char c : s) {
            if (cnt[c - 'a'] < k) {
                ans.push_back(c);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func filterCharacters(s string, k int) string {
	cnt := [26]int{}
	for _, c := range s {
		cnt[c-'a']++
	}
	ans := []rune{}
	for _, c := range s {
		if cnt[c-'a'] < k {
			ans = append(ans, c)
		}
	}
	return string(ans)
}
```

#### TypeScript

```ts
function filterCharacters(s: string, k: number): string {
    const cnt: Record<string, number> = {};
    for (const c of s) {
        cnt[c] = (cnt[c] || 0) + 1;
    }
    const ans: string[] = [];
    for (const c of s) {
        if (cnt[c] < k) {
            ans.push(c);
        }
    }
    return ans.join('');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
