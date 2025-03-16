---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3442.Maximum%20Difference%20Between%20Even%20and%20Odd%20Frequency%20I/README.md
rating: 1220
source: 第 435 场周赛 Q1
tags:
    - 哈希表
    - 字符串
    - 计数
---

<!-- problem:start -->

# [3442. 奇偶频次间的最大差值 I](https://leetcode.cn/problems/maximum-difference-between-even-and-odd-frequency-i)

[English Version](/solution/3400-3499/3442.Maximum%20Difference%20Between%20Even%20and%20Odd%20Frequency%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个由小写英文字母组成的字符串&nbsp;<code>s</code> 。请你找出字符串中两个字符的出现频次之间的 <strong>最大</strong> 差值，这两个字符需要满足：</p>

<ul>
	<li>一个字符在字符串中出现 <strong>偶数次</strong> 。</li>
	<li>另一个字符在字符串中出现 <strong>奇数次</strong>&nbsp;。</li>
</ul>

<p>返回 <strong>最大</strong> 差值，计算方法是出现 <strong>奇数次</strong> 字符的次数 <strong>减去</strong> 出现 <strong>偶数次</strong> 字符的次数。</p>

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "aaaaabbc"</span></p>

<p><b>输出：</b>3</p>

<p><b>解释：</b></p>

<ul>
	<li>字符&nbsp;<code>'a'</code>&nbsp;出现 <strong>奇数次</strong>&nbsp;，次数为&nbsp;<code><font face="monospace">5</font></code><font face="monospace"> ；字符</font>&nbsp;<code>'b'</code>&nbsp;出现 <strong>偶数次</strong> ，次数为&nbsp;<code><font face="monospace">2</font></code>&nbsp;。</li>
	<li>最大差值为&nbsp;<code>5 - 2 = 3</code>&nbsp;。</li>
</ul>
</div>

<p><b>示例 2：</b></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "abcabcab"</span></p>

<p><b>输出：</b>1</p>

<p><b>解释：</b></p>

<ul>
	<li>字符&nbsp;<code>'a'</code>&nbsp;出现 <strong>奇数次</strong>&nbsp;，次数为&nbsp;<code><font face="monospace">3</font></code><font face="monospace"> ；字符</font>&nbsp;<code>'c'</code>&nbsp;出现 <strong>偶数次</strong>&nbsp;，次数为&nbsp;<font face="monospace">2 。</font></li>
	<li>最大差值为&nbsp;<code>3 - 2 = 1</code> 。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>3 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code>&nbsp;仅由小写英文字母组成。</li>
	<li><code>s</code>&nbsp;至少由一个出现奇数次的字符和一个出现偶数次的字符组成。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数

我们可以用一个哈希表或数组 $\textit{cnt}$ 记录字符串 $s$ 中每个字符的出现次数。然后遍历 $\textit{cnt}$，找出出现奇数次的字符的最大频次 $a$ 和出现偶数次的字符的最小频次 $b$，最后返回 $a - b$ 即可。

时间复杂度 $O(n)$，其中 $n$ 是字符串 $s$ 的长度。空间复杂度 $O(|\Sigma|)$，其中 $\Sigma$ 是字符集，本题中 $|\Sigma| = 26$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxDifference(self, s: str) -> int:
        cnt = Counter(s)
        a, b = 0, inf
        for v in cnt.values():
            if v % 2:
                a = max(a, v)
            else:
                b = min(b, v)
        return a - b
```

#### Java

```java
class Solution {
    public int maxDifference(String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            ++cnt[c - 'a'];
        }
        int a = 0, b = 1 << 30;
        for (int v : cnt) {
            if (v % 2 == 1) {
                a = Math.max(a, v);
            } else if (v > 0) {
                b = Math.min(b, v);
            }
        }
        return a - b;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxDifference(string s) {
        int cnt[26]{};
        for (char c : s) {
            ++cnt[c - 'a'];
        }
        int a = 0, b = 1 << 30;
        for (int v : cnt) {
            if (v % 2 == 1) {
                a = max(a, v);
            } else if (v > 0) {
                b = min(b, v);
            }
        }
        return a - b;
    }
};
```

#### Go

```go
func maxDifference(s string) int {
	cnt := [26]int{}
	for _, c := range s {
		cnt[c-'a']++
	}
	a, b := 0, 1<<30
	for _, v := range cnt {
		if v%2 == 1 {
			a = max(a, v)
		} else if v > 0 {
			b = min(b, v)
		}
	}
	return a - b
}
```

#### TypeScript

```ts
function maxDifference(s: string): number {
    const cnt: Record<string, number> = {};
    for (const c of s) {
        cnt[c] = (cnt[c] || 0) + 1;
    }
    let [a, b] = [0, Infinity];
    for (const [_, v] of Object.entries(cnt)) {
        if (v % 2 === 1) {
            a = Math.max(a, v);
        } else {
            b = Math.min(b, v);
        }
    }
    return a - b;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
