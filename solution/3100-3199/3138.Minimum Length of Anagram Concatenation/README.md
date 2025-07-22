---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3138.Minimum%20Length%20of%20Anagram%20Concatenation/README.md
rating: 1979
source: 第 396 场周赛 Q3
tags:
    - 哈希表
    - 字符串
    - 计数
---

<!-- problem:start -->

# [3138. 同位字符串连接的最小长度](https://leetcode.cn/problems/minimum-length-of-anagram-concatenation)

[English Version](/solution/3100-3199/3138.Minimum%20Length%20of%20Anagram%20Concatenation/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串&nbsp;<code>s</code>&nbsp;，它由某个字符串&nbsp;<code>t</code>&nbsp;和若干&nbsp;<code>t</code>&nbsp; 的&nbsp;<strong>同位字符串</strong>&nbsp;连接而成。</p>

<p>请你返回字符串 <code>t</code>&nbsp;的 <strong>最小</strong>&nbsp;可能长度。</p>

<p><strong>同位字符串</strong>&nbsp;指的是重新排列一个字符串的字母得到的另外一个字符串。例如，"aab"，"aba" 和 "baa" 是 "aab" 的同位字符串。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "abba"</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><strong>解释：</strong></p>

<p>一个可能的字符串&nbsp;<code>t</code>&nbsp;为&nbsp;<code>"ba"</code>&nbsp;。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>s = "cdef"</span></p>

<p><span class="example-io"><b>输出：</b>4</span></p>

<p><strong>解释：</strong></p>

<p>一个可能的字符串&nbsp;<code>t</code>&nbsp;为&nbsp;<code>"cdef"</code>&nbsp;，注意&nbsp;<code>t</code>&nbsp;可能等于&nbsp;<code>s</code>&nbsp;。</p>

<p><strong class="example">示例</strong><strong>&nbsp;3：</strong></p>

<p><strong>输入：</strong>s = "abcbcacabbaccba"</p>

<p><b>输出：</b>3</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code>&nbsp;只包含小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

根据题目描述，字符串 $\textit{t}$ 的长度一定是 $\textit{s}$ 的长度的因子，我们可以从小到大枚举字符串 $\textit{t}$ 的长度 $k$，然后检查是否满足题目要求，如果满足则返回。因此，问题转化为如何检查字符串 $\textit{t}$ 的长度 $k$ 是否满足题目要求。

我们首先统计字符串 $\textit{s}$ 中每个字符出现的次数，记录在数组或哈希表 $\textit{cnt}$ 中。

然后，我们定义一个函数 $\textit{check}(k)$，用来检查字符串 $\textit{t}$ 的长度 $k$ 是否满足题目要求。我们可以遍历字符串 $\textit{s}$，每次取长度为 $k$ 的子串，然后统计每个字符出现的次数，如果每个字符出现的次数乘以 $\frac{n}{k}$ 不等于 $\textit{cnt}$ 中的值，则返回 $\textit{false}$。遍历结束，如果都满足，则返回 $\textit{true}$。

时间复杂度 $O(n \times A)$，其中 $n$ 是字符串 $\textit{s}$ 的长度，而 $A$ 是 $n$ 的因子个数。空间复杂度 $O(|\Sigma|)$，其中 $\Sigma$ 是字符集，本题中为小写字母集合。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minAnagramLength(self, s: str) -> int:
        def check(k: int) -> bool:
            for i in range(0, n, k):
                cnt1 = Counter(s[i : i + k])
                for c, v in cnt.items():
                    if cnt1[c] * (n // k) != v:
                        return False
            return True

        cnt = Counter(s)
        n = len(s)
        for i in range(1, n + 1):
            if n % i == 0 and check(i):
                return i
```

#### Java

```java
class Solution {
    private int n;
    private char[] s;
    private int[] cnt = new int[26];

    public int minAnagramLength(String s) {
        n = s.length();
        this.s = s.toCharArray();
        for (int i = 0; i < n; ++i) {
            ++cnt[this.s[i] - 'a'];
        }
        for (int i = 1;; ++i) {
            if (n % i == 0 && check(i)) {
                return i;
            }
        }
    }

    private boolean check(int k) {
        for (int i = 0; i < n; i += k) {
            int[] cnt1 = new int[26];
            for (int j = i; j < i + k; ++j) {
                ++cnt1[s[j] - 'a'];
            }
            for (int j = 0; j < 26; ++j) {
                if (cnt1[j] * (n / k) != cnt[j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minAnagramLength(string s) {
        int n = s.size();
        int cnt[26]{};
        for (char c : s) {
            cnt[c - 'a']++;
        }
        auto check = [&](int k) {
            for (int i = 0; i < n; i += k) {
                int cnt1[26]{};
                for (int j = i; j < i + k; ++j) {
                    cnt1[s[j] - 'a']++;
                }
                for (int j = 0; j < 26; ++j) {
                    if (cnt1[j] * (n / k) != cnt[j]) {
                        return false;
                    }
                }
            }
            return true;
        };
        for (int i = 1;; ++i) {
            if (n % i == 0 && check(i)) {
                return i;
            }
        }
    }
};
```

#### Go

```go
func minAnagramLength(s string) int {
	n := len(s)
	cnt := [26]int{}
	for _, c := range s {
		cnt[c-'a']++
	}
	check := func(k int) bool {
		for i := 0; i < n; i += k {
			cnt1 := [26]int{}
			for j := i; j < i+k; j++ {
				cnt1[s[j]-'a']++
			}
			for j, v := range cnt {
				if cnt1[j]*(n/k) != v {
					return false
				}
			}
		}
		return true
	}
	for i := 1; ; i++ {
		if n%i == 0 && check(i) {
			return i
		}
	}
}
```

#### TypeScript

```ts
function minAnagramLength(s: string): number {
    const n = s.length;
    const cnt: Record<string, number> = {};
    for (let i = 0; i < n; i++) {
        cnt[s[i]] = (cnt[s[i]] || 0) + 1;
    }
    const check = (k: number): boolean => {
        for (let i = 0; i < n; i += k) {
            const cnt1: Record<string, number> = {};
            for (let j = i; j < i + k; j++) {
                cnt1[s[j]] = (cnt1[s[j]] || 0) + 1;
            }
            for (const [c, v] of Object.entries(cnt)) {
                if (cnt1[c] * ((n / k) | 0) !== v) {
                    return false;
                }
            }
        }
        return true;
    };
    for (let i = 1; ; ++i) {
        if (n % i === 0 && check(i)) {
            return i;
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
