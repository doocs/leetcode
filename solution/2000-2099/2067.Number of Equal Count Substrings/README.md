---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2067.Number%20of%20Equal%20Count%20Substrings/README.md
tags:
    - 字符串
    - 计数
    - 前缀和
---

# [2067. 等计数子串的数量 🔒](https://leetcode.cn/problems/number-of-equal-count-substrings)

[English Version](/solution/2000-2099/2067.Number%20of%20Equal%20Count%20Substrings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的字符串 <code>s</code>，只包含小写英文字母和一个整数 <code>count</code>。如果&nbsp;<code>s</code>&nbsp;的&nbsp;<strong>子串 </strong>中的每种字母在子串中恰好出现 <code>count</code> 次，这个子串就被称为&nbsp;<strong>等计数子串</strong>。</p>

<p>返回<em> <code>s</code> 中&nbsp;<strong>等计数子串&nbsp;</strong>的个数。</em></p>

<p><strong>子串&nbsp;</strong>是字符串中连续的非空字符序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> s = "aaabcbbcc", count = 3
<strong>输出:</strong> 3
<strong>解释:</strong>
从下标 0 开始到下标 2 结束的子串是 "aaa"。
字母 “a” 在子串中恰好出现了 3 次。
从下标 3 开始到下标 8 结束的子串是 "bcbbcc"。
字母 “b” 和 “c” 在子串中恰好出现了 3 次。
从下标 0 开始到下标 8 结束的子串是 "aaabcbbcc"。
字母 “a”、“b” 和 “c” 在子串中恰好出现了 3 次。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> s = "abcd", count = 2
<strong>输出:</strong> 0
<strong>解释:</strong>
每种字母在 s 中出现的次数小于 count。
因此，s 中没有子串是等计数子串，返回 0。
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> s = "a", count = 5
<strong>输出:</strong> 0
<strong>解释:</strong>
每种字母在 s 中出现的次数小于 count。
因此，s 中没有子串是等计数子串，返回 0。</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= count &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>s</code> 只由小写英文字母组成。</li>
</ul>

## 解法

### 方法一：枚举 + 滑动窗口

我们可以在 $[1..26]$ 范围内枚举子串的字母种类数 $i$，那么子串长度就是 $i \times count$。

接下来，我们将当前子串长度作为窗口的大小，统计窗口大小中有多少种字母的个数为 $count$，记录在 $t$ 中。如果此时 $i = t$，说明当前窗口中的字母个数都为 $count$，那么就可以将答案加一。

时间复杂度 $O(n \times C)$，空间复杂度 $O(C)$。其中 $n$ 为字符串 $s$ 的长度，而 $C$ 为字母的种类数，本题中 $C = 26$。

<!-- tabs:start -->

```python
class Solution:
    def equalCountSubstrings(self, s: str, count: int) -> int:
        ans = 0
        for i in range(1, 27):
            k = i * count
            if k > len(s):
                break
            cnt = Counter()
            t = 0
            for j, c in enumerate(s):
                cnt[c] += 1
                t += cnt[c] == count
                t -= cnt[c] == count + 1
                if j >= k:
                    cnt[s[j - k]] -= 1
                    t += cnt[s[j - k]] == count
                    t -= cnt[s[j - k]] == count - 1
                ans += i == t
        return ans
```

```java
class Solution {
    public int equalCountSubstrings(String s, int count) {
        int ans = 0;
        int[] cnt = new int[26];
        int n = s.length();
        for (int i = 1; i < 27 && i * count <= n; ++i) {
            int k = i * count;
            Arrays.fill(cnt, 0);
            int t = 0;
            for (int j = 0; j < n; ++j) {
                int a = s.charAt(j) - 'a';
                ++cnt[a];
                t += cnt[a] == count ? 1 : 0;
                t -= cnt[a] == count + 1 ? 1 : 0;
                if (j - k >= 0) {
                    int b = s.charAt(j - k) - 'a';
                    --cnt[b];
                    t += cnt[b] == count ? 1 : 0;
                    t -= cnt[b] == count - 1 ? 1 : 0;
                }
                ans += i == t ? 1 : 0;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int equalCountSubstrings(string s, int count) {
        int ans = 0;
        int n = s.size();
        int cnt[26];
        for (int i = 1; i < 27 && i * count <= n; ++i) {
            int k = i * count;
            memset(cnt, 0, sizeof(cnt));
            int t = 0;
            for (int j = 0; j < n; ++j) {
                int a = s[j] - 'a';
                t += ++cnt[a] == count;
                t -= cnt[a] == count + 1;
                if (j >= k) {
                    int b = s[j - k] - 'a';
                    t += --cnt[b] == count;
                    t -= cnt[b] == count - 1;
                }
                ans += i == t;
            }
        }
        return ans;
    }
};
```

```go
func equalCountSubstrings(s string, count int) (ans int) {
	n := len(s)
	for i := 1; i < 27 && i*count <= n; i++ {
		k := i * count
		cnt := [26]int{}
		t := 0
		for j, c := range s {
			a := c - 'a'
			cnt[a]++
			if cnt[a] == count {
				t++
			} else if cnt[a] == count+1 {
				t--
			}
			if j >= k {
				b := s[j-k] - 'a'
				cnt[b]--
				if cnt[b] == count {
					t++
				} else if cnt[b] == count-1 {
					t--
				}
			}
			if i == t {
				ans++
			}
		}
	}
	return
}
```

```ts
function equalCountSubstrings(s: string, count: number): number {
    const n = s.length;
    let ans = 0;
    for (let i = 1; i < 27 && i * count <= n; ++i) {
        const k = i * count;
        const cnt: number[] = Array(26).fill(0);
        let t = 0;
        for (let j = 0; j < n; ++j) {
            const a = s.charCodeAt(j) - 97;
            t += ++cnt[a] === count ? 1 : 0;
            t -= cnt[a] === count + 1 ? 1 : 0;
            if (j >= k) {
                const b = s.charCodeAt(j - k) - 97;
                t += --cnt[b] === count ? 1 : 0;
                t -= cnt[b] === count - 1 ? 1 : 0;
            }
            ans += i === t ? 1 : 0;
        }
    }
    return ans;
}
```

```js
/**
 * @param {string} s
 * @param {number} count
 * @return {number}
 */
var equalCountSubstrings = function (s, count) {
    const n = s.length;
    let ans = 0;
    for (let i = 1; i < 27 && i * count <= n; ++i) {
        const k = i * count;
        const cnt = Array(26).fill(0);
        let t = 0;
        for (let j = 0; j < n; ++j) {
            const a = s.charCodeAt(j) - 97;
            t += ++cnt[a] === count ? 1 : 0;
            t -= cnt[a] === count + 1 ? 1 : 0;
            if (j >= k) {
                const b = s.charCodeAt(j - k) - 97;
                t += --cnt[b] === count ? 1 : 0;
                t -= cnt[b] === count - 1 ? 1 : 0;
            }
            ans += i === t ? 1 : 0;
        }
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- end -->
