---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3090.Maximum%20Length%20Substring%20With%20Two%20Occurrences/README.md
rating: 1329
source: 第 390 场周赛 Q1
tags:
    - 哈希表
    - 字符串
    - 滑动窗口
---

<!-- problem:start -->

# [3090. 每个字符最多出现两次的最长子字符串](https://leetcode.cn/problems/maximum-length-substring-with-two-occurrences)

[English Version](/solution/3000-3099/3090.Maximum%20Length%20Substring%20With%20Two%20Occurrences/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>s</code> ，请找出满足每个字符最多出现两次的最长子字符串，并返回该<span data-keyword="substring">子字符串</span>的<strong> 最大 </strong>长度。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "bcbbbcba"</span></p>

<p><strong>输出：</strong> <span class="example-io">4</span></p>

<p><strong>解释：</strong></p>

<p>以下子字符串长度为 4，并且每个字符最多出现两次：<code>"bcbb<u>bcba</u>"</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "aaaa"</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p>以下子字符串长度为 2，并且每个字符最多出现两次：<code>"<u>aa</u>aa"</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul><!-- 字符串 s 的长度在 2 到 100 之间 -->
	<li><code>2 &lt;= s.length &lt;= 100</code></li>
	<!-- 字符串 s 仅包含小写英文字母 -->
	<li><code>s</code> 仅由小写英文字母组成。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：双指针

我们用两个指针 $l$ 和 $r$ 来维护一个滑动窗口，用一个数组 $cnt$ 来记录窗口中每个字符的出现次数。

每一次，我们将指针 $r$ 对应的字符 $c$ 加入窗口，然后判断 $cnt[c]$ 是否大于 $2$，如果大于 $2$，则将指针 $l$ 循环右移，直到 $cnt[c]$ 小于等于 $2$。此时，我们更新答案 $ans = \max(ans, r - l + 1)$。

最终，我们返回答案 $ans$。

时间复杂度 $O(n)$，其中 $n$ 为字符串 $s$ 的长度。空间复杂度 $O(|\Sigma|)$，其中 $\Sigma$ 为字符集，本题中 $\Sigma = 26$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumLengthSubstring(self, s: str) -> int:
        ans = l = 0
        cnt = defaultdict(int)
        for r, c in enumerate(s):
            cnt[c] += 1
            while cnt[c] > 2:
                cnt[s[l]] -= 1
                l += 1
            ans = max(ans, r - l + 1)
        return ans
```

#### Java

```java
class Solution {
    public int maximumLengthSubstring(String s) {
        int ans = 0;
        int[] cnt = new int[26];
        for (int l = 0, r = 0; r < s.length(); ++r) {
            int idx = s.charAt(r) - 'a';
            ++cnt[idx];
            while (cnt[idx] > 2) {
                --cnt[s.charAt(l++) - 'a'];
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximumLengthSubstring(string s) {
        int ans = 0;
        int cnt[26]{};
        for (int l = 0, r = 0; r < s.size(); ++r) {
            int idx = s[r] - 'a';
            ++cnt[idx];
            while (cnt[idx] > 2) {
                --cnt[s[l++] - 'a'];
            }
            ans = max(ans, r - l + 1);
        }
        return ans;
    }
};
```

#### Go

```go
func maximumLengthSubstring(s string) (ans int) {
	l := 0
	cnt := [26]int{}
	for r, c := range s {
		idx := int(c - 'a')
		cnt[idx]++
		for cnt[idx] > 2 {
			cnt[s[l]-'a']--
			l++
		}
		ans = max(ans, r-l+1)
	}
	return
}
```

#### TypeScript

```ts
function maximumLengthSubstring(s: string): number {
    let ans = 0;
    const cnt: number[] = Array(26).fill(0);
    for (let l = 0, r = 0; r < s.length; ++r) {
        const idx = s[r].charCodeAt(0) - 97;
        ++cnt[idx];
        while (cnt[idx] > 2) {
            --cnt[s[l++].charCodeAt(0) - 97];
        }
        ans = Math.max(ans, r - l + 1);
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn maximum_length_substring(s: String) -> i32 {
        let mut cnt = [0; 26];
        let mut ans = 0;
        let mut l = 0;
        let s = s.as_bytes();

        for (r, &c) in s.iter().enumerate() {
            let i = (c - b'a') as usize;
            cnt[i] += 1;

            while cnt[i] > 2 {
                cnt[(s[l] - b'a') as usize] -= 1;
                l += 1;
            }

            ans = ans.max((r - l + 1) as i32);
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
