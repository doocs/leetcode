---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3541.Find%20Most%20Frequent%20Vowel%20and%20Consonant/README.md
rating: 1238
source: 第 156 场双周赛 Q1
tags:
    - 哈希表
    - 字符串
    - 计数
---

<!-- problem:start -->

# [3541. 找到频率最高的元音和辅音](https://leetcode.cn/problems/find-most-frequent-vowel-and-consonant)

[English Version](/solution/3500-3599/3541.Find%20Most%20Frequent%20Vowel%20and%20Consonant/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个由小写英文字母（<code>'a'</code> 到 <code>'z'</code>）组成的字符串 <code>s</code>。你的任务是找出出现频率&nbsp;<strong>最高&nbsp;</strong>的元音（<code>'a'</code>、<code>'e'</code>、<code>'i'</code>、<code>'o'</code>、<code>'u'</code> 中的一个）和出现频率<strong>最高</strong>的辅音（除元音以外的所有字母），并返回这两个频率之和。</p>

<p><strong>注意</strong>：如果有多个元音或辅音具有相同的最高频率，可以任选其中一个。如果字符串中没有元音或没有辅音，则其频率视为 0。</p>
一个字母 <code>x</code> 的&nbsp;<strong>频率&nbsp;</strong>是它在字符串中出现的次数。

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">s = "successes"</span></p>

<p><strong>输出:</strong> <span class="example-io">6</span></p>

<p><strong>解释:</strong></p>

<ul>
	<li>元音有：<code>'u'</code> 出现 1 次，<code>'e'</code> 出现 2 次。最大元音频率 = 2。</li>
	<li>辅音有：<code>'s'</code> 出现 4 次，<code>'c'</code> 出现 2 次。最大辅音频率 = 4。</li>
	<li>输出为 <code>2 + 4 = 6</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">s = "aeiaeia"</span></p>

<p><strong>输出:</strong> <span class="example-io">3</span></p>

<p><strong>解释:</strong></p>

<ul>
	<li>元音有：<code>'a'</code> 出现 3 次，<code>'e'</code> 出现 2 次，<code>'i'</code> 出现 2 次。最大元音频率 = 3。</li>
	<li><code>s</code> 中没有辅音。因此，最大辅音频率 = 0。</li>
	<li>输出为 <code>3 + 0 = 3</code>。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> 只包含小写英文字母</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数

我们先用一个哈希表或者一个长度为 $26$ 的数组 $\textit{cnt}$ 统计每个字母的出现频率。然后我们遍历这个表，找出元音和辅音中出现频率最高的字母，返回它们的频率之和。

我们可以用一个变量 $\textit{a}$ 记录元音的最大频率，另一个变量 $\textit{b}$ 记录辅音的最大频率。遍历时，如果当前字母是元音，就更新 $\textit{a}$；否则就更新 $\textit{b}$。

最后返回 $\textit{a} + \textit{b}$ 即可。

时间复杂度 $O(n)$，其中 $n$ 是字符串的长度。空间复杂度 $(|\Sigma|)$，其中 $|\Sigma|$ 是字母表的大小，这里是 $26$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxFreqSum(self, s: str) -> int:
        cnt = Counter(s)
        a = b = 0
        for c, v in cnt.items():
            if c in "aeiou":
                a = max(a, v)
            else:
                b = max(b, v)
        return a + b
```

#### Java

```java
class Solution {
    public int maxFreqSum(String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            ++cnt[c - 'a'];
        }
        int a = 0, b = 0;
        for (int i = 0; i < cnt.length; ++i) {
            char c = (char) (i + 'a');
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                a = Math.max(a, cnt[i]);
            } else {
                b = Math.max(b, cnt[i]);
            }
        }
        return a + b;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxFreqSum(string s) {
        int cnt[26]{};
        for (char c : s) {
            ++cnt[c - 'a'];
        }
        int a = 0, b = 0;
        for (int i = 0; i < 26; ++i) {
            char c = 'a' + i;
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                a = max(a, cnt[i]);
            } else {
                b = max(b, cnt[i]);
            }
        }
        return a + b;
    }
};
```

#### Go

```go
func maxFreqSum(s string) int {
	cnt := [26]int{}
	for _, c := range s {
		cnt[c-'a']++
	}
	a, b := 0, 0
	for i := range cnt {
		c := byte(i + 'a')
		if c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' {
			a = max(a, cnt[i])
		} else {
			b = max(b, cnt[i])
		}
	}
	return a + b
}
```

#### TypeScript

```ts
function maxFreqSum(s: string): number {
    const cnt: number[] = Array(26).fill(0);
    for (const c of s) {
        ++cnt[c.charCodeAt(0) - 97];
    }
    let [a, b] = [0, 0];
    for (let i = 0; i < 26; ++i) {
        const c = String.fromCharCode(i + 97);
        if ('aeiou'.includes(c)) {
            a = Math.max(a, cnt[i]);
        } else {
            b = Math.max(b, cnt[i]);
        }
    }
    return a + b;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
