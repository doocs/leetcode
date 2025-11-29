---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3517.Smallest%20Palindromic%20Rearrangement%20I/README.md
rating: 1357
source: 第 445 场周赛 Q2
tags:
    - 字符串
    - 计数排序
    - 排序
---

<!-- problem:start -->

# [3517. 最小回文排列 I](https://leetcode.cn/problems/smallest-palindromic-rearrangement-i)

[English Version](/solution/3500-3599/3517.Smallest%20Palindromic%20Rearrangement%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个&nbsp;<strong>回文&nbsp;</strong>字符串 <code>s</code>。</p>

<p>返回 <code>s</code> 的按字典序排列的&nbsp;<strong>最小&nbsp;</strong>回文排列。</p>

<p>如果一个字符串从前往后和从后往前读都相同，那么这个字符串是一个&nbsp;<strong>回文 </strong>字符串。</p>

<p><strong>排列&nbsp;</strong>是字符串中所有字符的重排。</p>
如果字符串 <code>a</code> 按字典序小于字符串 <code>b</code>，则表示在第一个不同的位置，<code>a</code> 中的字符比 <code>b</code> 中的对应字符在字母表中更靠前。<br />
如果在前 <code>min(a.length, b.length)</code> 个字符中没有区别，则较短的字符串按字典序更小。

<p>&nbsp;</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "z"</span></p>

<p><strong>输出：</strong> <span class="example-io">"z"</span></p>

<p><strong>解释：</strong></p>

<p>仅由一个字符组成的字符串已经是按字典序最小的回文。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "babab"</span></p>

<p><strong>输出：</strong> <span class="example-io">"abbba"</span></p>

<p><strong>解释：</strong></p>

<p>通过重排 <code>"babab"</code> → <code>"abbba"</code>，可以得到按字典序最小的回文。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "daccad"</span></p>

<p><strong>输出：</strong> <span class="example-io">"acddca"</span></p>

<p><strong>解释：</strong></p>

<p>通过重排 <code>"daccad"</code> → <code>"acddca"</code>，可以得到按字典序最小的回文。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 由小写英文字母组成。</li>
	<li>保证 <code>s</code> 是回文字符串。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数

我们首先统计字符串中每个字符的出现次数，记录在哈希表或数组 $\textit{cnt}$ 中。由于字符串是回文字符串，因此每个字符的出现次数要么是偶数次，要么有且仅有一个字符出现奇数次。

接下来，我们从字典序最小的字符开始，依次将每个字符的一半次数添加到结果字符串的前半部分 $\textit{t}$ 中。如果某个字符出现了奇数次，我们将该字符记录为中间字符 $\textit{ch}$。最后，我们将 $\textit{t}$、$\textit{ch}$ 和 $\textit{t}$ 的反转拼接起来，得到最终的按字典序排列的最小回文排列。

时间复杂度 $O(n)$，其中 $n$ 是字符串的长度。空间复杂度 $O(|\Sigma|)$，其中 $|\Sigma|$ 是字符集的大小，本题中 $|\Sigma|=26$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def smallestPalindrome(self, s: str) -> str:
        cnt = Counter(s)
        t = []
        ch = ""
        for c in ascii_lowercase:
            v = cnt[c] // 2
            t.append(c * v)
            cnt[c] -= v * 2
            if cnt[c] == 1:
                ch = c
        ans = "".join(t)
        ans = ans + ch + ans[::-1]
        return ans
```

#### Java

```java
class Solution {
    public String smallestPalindrome(String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        StringBuilder t = new StringBuilder();
        String ch = "";

        for (char c = 'a'; c <= 'z'; c++) {
            int idx = c - 'a';
            int v = cnt[idx] / 2;
            if (v > 0) {
                t.append(String.valueOf(c).repeat(v));
            }
            cnt[idx] -= v * 2;
            if (cnt[idx] == 1) {
                ch = String.valueOf(c);
            }
        }

        String ans = t.toString();
        ans = ans + ch + new StringBuilder(ans).reverse();
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    string smallestPalindrome(string s) {
        vector<int> cnt(26);
        for (char c : s) {
            cnt[c - 'a']++;
        }
        string t = "";
        string ch = "";
        for (char c = 'a'; c <= 'z'; ++c) {
            int v = cnt[c - 'a'] / 2;
            if (v > 0) {
                t.append(v, c);
            }
            cnt[c - 'a'] -= v * 2;
            if (cnt[c - 'a'] == 1) {
                ch = string(1, c);
            }
        }
        string ans = t;
        ans += ch;
        string rev = t;
        reverse(rev.begin(), rev.end());
        ans += rev;
        return ans;
    }
};
```

#### Go

```go
func smallestPalindrome(s string) string {
	cnt := make([]int, 26)
	for i := 0; i < len(s); i++ {
		cnt[s[i]-'a']++
	}

	t := make([]byte, 0, len(s)/2)
	var ch byte
	for c := byte('a'); c <= 'z'; c++ {
		v := cnt[c-'a'] / 2
		for i := 0; i < v; i++ {
			t = append(t, c)
		}
		cnt[c-'a'] -= v * 2
		if cnt[c-'a'] == 1 {
			ch = c
		}
	}

	totalLen := len(t) * 2
	if ch != 0 {
		totalLen++
	}
	var sb strings.Builder
	sb.Grow(totalLen)

	sb.Write(t)
	if ch != 0 {
		sb.WriteByte(ch)
	}
	for i := len(t) - 1; i >= 0; i-- {
		sb.WriteByte(t[i])
	}
	return sb.String()
}
```

#### TypeScript

```ts
function smallestPalindrome(s: string): string {
    const ascii_lowercase = 'abcdefghijklmnopqrstuvwxyz';
    const cnt = new Array<number>(26).fill(0);
    for (const chKey of s) {
        cnt[chKey.charCodeAt(0) - 97]++;
    }

    const t: string[] = [];
    let ch = '';
    for (let i = 0; i < 26; i++) {
        const c = ascii_lowercase[i];
        const v = Math.floor(cnt[i] / 2);
        t.push(c.repeat(v));
        cnt[i] -= v * 2;
        if (cnt[i] === 1) {
            ch = c;
        }
    }

    let ans = t.join('');
    ans = ans + ch + ans.split('').reverse().join('');
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
