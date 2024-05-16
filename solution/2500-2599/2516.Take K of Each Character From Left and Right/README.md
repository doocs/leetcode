---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2516.Take%20K%20of%20Each%20Character%20From%20Left%20and%20Right/README.md
rating: 1947
source: 第 325 场周赛 Q2
tags:
    - 哈希表
    - 字符串
    - 滑动窗口
---

# [2516. 每种字符至少取 K 个](https://leetcode.cn/problems/take-k-of-each-character-from-left-and-right)

[English Version](/solution/2500-2599/2516.Take%20K%20of%20Each%20Character%20From%20Left%20and%20Right/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个由字符 <code>'a'</code>、<code>'b'</code>、<code>'c'</code> 组成的字符串 <code>s</code> 和一个非负整数 <code>k</code> 。每分钟，你可以选择取走 <code>s</code> <strong>最左侧</strong> 还是 <strong>最右侧</strong> 的那个字符。</p>

<p>你必须取走每种字符 <strong>至少</strong> <code>k</code> 个，返回需要的 <strong>最少</strong> 分钟数；如果无法取到，则返回<em> </em><code>-1</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "aabaaaacaabc", k = 2
<strong>输出：</strong>8
<strong>解释：</strong>
从 s 的左侧取三个字符，现在共取到两个字符 'a' 、一个字符 'b' 。
从 s 的右侧取五个字符，现在共取到四个字符 'a' 、两个字符 'b' 和两个字符 'c' 。
共需要 3 + 5 = 8 分钟。
可以证明需要的最少分钟数是 8 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "a", k = 1
<strong>输出：</strong>-1
<strong>解释：</strong>无法取到一个字符 'b' 或者 'c'，所以返回 -1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 仅由字母 <code>'a'</code>、<code>'b'</code>、<code>'c'</code> 组成</li>
	<li><code>0 &lt;= k &lt;= s.length</code></li>
</ul>

## 解法

### 方法一：滑动窗口

我们先用哈希表或者一个长度为 $3$ 的数组 $cnt$ 统计字符串 $s$ 中每个字符的个数，如果有字符的个数小于 $k$ 个，则无法取到，提前返回 $-1$。

题目要我们在字符串左侧以及右侧取走字符，最终取到的每种字符的个数都不少于 $k$ 个。我们不妨反着考虑问题，取走中间某个窗口大小的字符串，使得剩下的两侧字符串中，每种字符的个数都不少于 $k$ 个。

因此，我们维护一个滑动窗口，用指针 $j$ 和 $i$ 分别表示窗口的左右边界，窗口内的字符串是我们要取走的。我们每一次移动右边界 $i$，将对应的字符 $s[i]$ 加入到窗口中（也即取走一个字符 $s[i]$），此时如果 $cnt[s[i]]$ 个数小于 $k$，那么我们循环移动左边界 $j$，直到 $cnt[s[i]]$ 个数不小于 $k$ 为止。此时的窗口大小为 $i - j + 1$，更新最大窗口。

最终的答案就是字符串 $s$ 的长度减去最大窗口的大小。

时间复杂度 $O(n)$，其中 $n$ 为字符串 $s$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def takeCharacters(self, s: str, k: int) -> int:
        cnt = Counter(s)
        if any(cnt[c] < k for c in "abc"):
            return -1
        mx = j = 0
        for i, c in enumerate(s):
            cnt[c] -= 1
            while cnt[c] < k:
                cnt[s[j]] += 1
                j += 1
            mx = max(mx, i - j + 1)
        return len(s) - mx
```

```java
class Solution {
    public int takeCharacters(String s, int k) {
        int[] cnt = new int[3];
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            ++cnt[s.charAt(i) - 'a'];
        }
        for (int x : cnt) {
            if (x < k) {
                return -1;
            }
        }
        int mx = 0, j = 0;
        for (int i = 0; i < n; ++i) {
            int c = s.charAt(i) - 'a';
            --cnt[c];
            while (cnt[c] < k) {
                ++cnt[s.charAt(j++) - 'a'];
            }
            mx = Math.max(mx, i - j + 1);
        }
        return n - mx;
    }
}
```

```cpp
class Solution {
public:
    int takeCharacters(string s, int k) {
        int cnt[3]{};
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            ++cnt[s[i] - 'a'];
        }
        for (int x : cnt) {
            if (x < k) {
                return -1;
            }
        }
        int mx = 0, j = 0;
        for (int i = 0; i < n; ++i) {
            int c = s[i] - 'a';
            --cnt[c];
            while (cnt[c] < k) {
                ++cnt[s[j++] - 'a'];
            }
            mx = max(mx, i - j + 1);
        }
        return n - mx;
    }
};
```

```go
func takeCharacters(s string, k int) int {
	cnt := [3]int{}
	for _, c := range s {
		cnt[c-'a']++
	}
	for _, x := range cnt {
		if x < k {
			return -1
		}
	}
	mx, j := 0, 0
	for i, c := range s {
		c -= 'a'
		for cnt[c]--; cnt[c] < k; j++ {
			cnt[s[j]-'a']++
		}
		mx = max(mx, i-j+1)
	}
	return len(s) - mx
}
```

```ts
function takeCharacters(s: string, k: number): number {
    const idx = (c: string) => c.charCodeAt(0) - 97;
    const cnt: number[] = Array(3).fill(0);
    for (const c of s) {
        ++cnt[idx(c)];
    }
    if (cnt.some(v => v < k)) {
        return -1;
    }
    const n = s.length;
    let [mx, j] = [0, 0];
    for (let i = 0; i < n; ++i) {
        const c = idx(s[i]);
        --cnt[c];
        while (cnt[c] < k) {
            ++cnt[idx(s[j++])];
        }
        mx = Math.max(mx, i - j + 1);
    }
    return n - mx;
}
```

```rust
use std::collections::HashMap;

impl Solution {
    pub fn take_characters(s: String, k: i32) -> i32 {
        let mut cnt: HashMap<char, i32> = HashMap::new();
        for c in s.chars() {
            *cnt.entry(c).or_insert(0) += 1;
        }

        if "abc".chars().any(|c| cnt.get(&c).unwrap_or(&0) < &k) {
            return -1;
        }

        let mut mx = 0;
        let mut j = 0;
        let mut cs = s.chars().collect::<Vec<char>>();
        for i in 0..cs.len() {
            let c = cs[i];
            *cnt.get_mut(&c).unwrap() -= 1;
            while cnt.get(&c).unwrap() < &k {
                *cnt.get_mut(&cs[j]).unwrap() += 1;
                j += 1;
            }
            mx = mx.max(i - j + 1);
        }
        (cs.len() as i32) - (mx as i32)
    }
}
```

<!-- tabs:end -->

<!-- end -->
