---
comments: true
difficulty: 困难
rating: 2375
source: 第 445 场周赛 Q3
tags:
    - 哈希表
    - 数学
    - 字符串
    - 组合数学
    - 计数
---

<!-- problem:start -->

# [3518. 最小回文排列 II](https://leetcode.cn/problems/smallest-palindromic-rearrangement-ii)

[English Version](/solution/3500-3599/3518.Smallest%20Palindromic%20Rearrangement%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p data-end="332" data-start="99">给你一个&nbsp;<strong>回文&nbsp;</strong>字符串 <code>s</code> 和一个整数 <code>k</code>。</p>

<p>返回 <code>s</code> 的按字典序排列的&nbsp;<strong>第 k 小&nbsp;</strong>回文排列。如果不存在&nbsp;<code>k</code> 个不同的回文排列，则返回空字符串。</p>

<p><strong>注意：</strong> 产生相同回文字符串的不同重排视为相同，仅计为一次。</p>

<p>如果一个字符串从前往后和从后往前读都相同，那么这个字符串是一个&nbsp;<strong>回文 </strong>字符串。</p>

<p><strong>排列&nbsp;</strong>是字符串中所有字符的重排。</p>

<p>如果字符串 <code>a</code> 按字典序小于字符串 <code>b</code>，则表示在第一个不同的位置，<code>a</code> 中的字符比 <code>b</code> 中的对应字符在字母表中更靠前。<br />
如果在前 <code>min(a.length, b.length)</code> 个字符中没有区别，则较短的字符串按字典序更小。</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "abba", k = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">"baab"</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>"abba"</code> 的两个不同的回文排列是 <code>"abba"</code> 和 <code>"baab"</code>。</li>
	<li>按字典序，<code>"abba"</code> 位于 <code>"baab"</code> 之前。由于 <code>k = 2</code>，输出为 <code>"baab"</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "aa", k = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">""</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>仅有一个回文排列：<code>"aa"</code>。</li>
	<li>由于 <code>k = 2</code> 超过了可能的排列数，输出为空字符串。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "bacab", k = 1</span></p>

<p><strong>输出：</strong> <span class="example-io">"abcba"</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>"bacab"</code> 的两个不同的回文排列是 <code>"abcba"</code> 和 <code>"bacab"</code>。</li>
	<li>按字典序，<code>"abcba"</code> 位于 <code>"bacab"</code> 之前。由于 <code>k = 1</code>，输出为 <code>"abcba"</code>。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code> 由小写英文字母组成。</li>
	<li>保证 <code>s</code> 是回文字符串。</li>
	<li><code>1 &lt;= k &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 上一题只求最小回文；此处要第 $k$ 个不同回文，$|s| \le 10^4$ 且 $k \le 10^6$，不能列出全部前半排列。
>
> 前半多重集的排列数可用组合数计算，超过 $k$ 时截断以免溢出。从左到右试填字母：若将该字母放在当前位置后后缀排列数不少于剩余名次，则选定；否则累减名次并换下一个字母。最后镜像并插入中心字符。

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def smallestPalindrome(self, s: str, k: int) -> str:
        limit = 1_000_001
        freq = [0] * 26
        for ch in s:
            freq[ord(ch) - 97] += 1
        odd = 0
        mid = 26
        for i, v in enumerate(freq):
            if v & 1:
                odd += 1
                mid = i
        if odd > 1:
            return ""
        half = [v // 2 for v in freq]
        half_len = sum(half)

        def count_permutations(counts: List[int]) -> int:
            remaining = sum(counts)
            perms = 1
            for count in counts:
                if count == 0:
                    continue
                selected = min(count, remaining - count)
                combos = 1
                for step in range(1, selected + 1):
                    combos = combos * (remaining - step + 1) // step
                    if combos >= limit:
                        combos = limit
                        break
                perms *= combos
                if perms >= limit:
                    return limit
                remaining -= count
            return perms

        if k > count_permutations(half):
            return ""
        n = len(s)
        pal = [""] * n
        rank = k
        pos = 0
        for _ in range(half_len):
            for i in range(26):
                if half[i] == 0:
                    continue
                half[i] -= 1
                suffix = count_permutations(half)
                if suffix >= rank:
                    pal[pos] = chr(97 + i)
                    pos += 1
                    break
                rank -= suffix
                half[i] += 1
        if mid < 26:
            pal[half_len] = chr(97 + mid)
        for i in range(half_len):
            pal[n - 1 - i] = pal[i]
        return "".join(pal)
```

#### Java

```java
class Solution {
    private static final int LIMIT = 1_000_001;

    public String smallestPalindrome(String s, int k) {
        int[] freq = new int[26];
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            ++freq[s.charAt(i) - 'a'];
        }
        int odd = 0;
        int mid = 26;
        for (int i = 0; i < 26; ++i) {
            if ((freq[i] & 1) == 1) {
                ++odd;
                mid = i;
            }
        }
        if (odd > 1) {
            return "";
        }
        int[] half = new int[26];
        int halfLen = 0;
        for (int i = 0; i < 26; ++i) {
            half[i] = freq[i] / 2;
            halfLen += half[i];
        }
        if (k > countPermutations(half)) {
            return "";
        }
        char[] pal = new char[n];
        int rank = k;
        int pos = 0;
        for (int t = 0; t < halfLen; ++t) {
            for (int i = 0; i < 26; ++i) {
                if (half[i] == 0) {
                    continue;
                }
                --half[i];
                int suffix = countPermutations(half);
                if (suffix >= rank) {
                    pal[pos++] = (char) ('a' + i);
                    break;
                }
                rank -= suffix;
                ++half[i];
            }
        }
        if (mid < 26) {
            pal[halfLen] = (char) ('a' + mid);
        }
        for (int i = 0; i < halfLen; ++i) {
            pal[n - 1 - i] = pal[i];
        }
        return new String(pal);
    }

    private int countPermutations(int[] counts) {
        int remaining = 0;
        for (int c : counts) {
            remaining += c;
        }
        long perms = 1;
        for (int count : counts) {
            if (count == 0) {
                continue;
            }
            int selected = Math.min(count, remaining - count);
            long combos = 1;
            for (int step = 1; step <= selected; ++step) {
                combos = combos * (remaining - step + 1) / step;
                if (combos >= LIMIT) {
                    combos = LIMIT;
                    break;
                }
            }
            perms *= combos;
            if (perms >= LIMIT) {
                return LIMIT;
            }
            remaining -= count;
        }
        return (int) perms;
    }
}
```

#### C++

```cpp
class Solution {
public:
    string smallestPalindrome(string s, int k) {
        int freq[26]{};
        int n = s.size();
        for (char c : s) {
            ++freq[c - 'a'];
        }
        int odd = 0, mid = 26;
        for (int i = 0; i < 26; ++i) {
            if (freq[i] & 1) {
                ++odd;
                mid = i;
            }
        }
        if (odd > 1) {
            return "";
        }
        int half[26]{};
        int halfLen = 0;
        for (int i = 0; i < 26; ++i) {
            half[i] = freq[i] / 2;
            halfLen += half[i];
        }
        if (k > countPermutations(half)) {
            return "";
        }
        string pal(n, 0);
        int rank = k, pos = 0;
        for (int t = 0; t < halfLen; ++t) {
            for (int i = 0; i < 26; ++i) {
                if (half[i] == 0) {
                    continue;
                }
                --half[i];
                int suffix = countPermutations(half);
                if (suffix >= rank) {
                    pal[pos++] = char('a' + i);
                    break;
                }
                rank -= suffix;
                ++half[i];
            }
        }
        if (mid < 26) {
            pal[halfLen] = char('a' + mid);
        }
        for (int i = 0; i < halfLen; ++i) {
            pal[n - 1 - i] = pal[i];
        }
        return pal;
    }

private:
    static constexpr int LIMIT = 1000001;

    int countPermutations(const int counts[26]) {
        int remaining = 0;
        for (int i = 0; i < 26; ++i) {
            remaining += counts[i];
        }
        long long perms = 1;
        for (int i = 0; i < 26; ++i) {
            int count = counts[i];
            if (count == 0) {
                continue;
            }
            int selected = min(count, remaining - count);
            long long combos = 1;
            for (int step = 1; step <= selected; ++step) {
                combos = combos * (remaining - step + 1) / step;
                if (combos >= LIMIT) {
                    combos = LIMIT;
                    break;
                }
            }
            perms *= combos;
            if (perms >= LIMIT) {
                return LIMIT;
            }
            remaining -= count;
        }
        return (int) perms;
    }
};
```

#### Go

```go
func smallestPalindrome(s string, k int) string {
	const limit = 1_000_001
	freq := [26]int{}
	for i := 0; i < len(s); i++ {
		freq[s[i]-'a']++
	}
	odd, mid := 0, 26
	for i, v := range freq {
		if v&1 == 1 {
			odd++
			mid = i
		}
	}
	if odd > 1 {
		return ""
	}
	half := [26]int{}
	halfLen := 0
	for i, v := range freq {
		half[i] = v / 2
		halfLen += half[i]
	}
	countPermutations := func(counts [26]int) int {
		remaining := 0
		for _, c := range counts {
			remaining += c
		}
		perms := 1
		for _, count := range counts {
			if count == 0 {
				continue
			}
			selected := count
			if remaining-count < selected {
				selected = remaining - count
			}
			combos := 1
			for step := 1; step <= selected; step++ {
				combos = combos * (remaining - step + 1) / step
				if combos >= limit {
					combos = limit
					break
				}
			}
			perms *= combos
			if perms >= limit {
				return limit
			}
			remaining -= count
		}
		return perms
	}
	if k > countPermutations(half) {
		return ""
	}
	n := len(s)
	pal := make([]byte, n)
	rank, pos := k, 0
	for t := 0; t < halfLen; t++ {
		for i := 0; i < 26; i++ {
			if half[i] == 0 {
				continue
			}
			half[i]--
			suffix := countPermutations(half)
			if suffix >= rank {
				pal[pos] = byte('a' + i)
				pos++
				break
			}
			rank -= suffix
			half[i]++
		}
	}
	if mid < 26 {
		pal[halfLen] = byte('a' + mid)
	}
	for i := 0; i < halfLen; i++ {
		pal[n-1-i] = pal[i]
	}
	return string(pal)
}
```

#### Rust

```rust
impl Solution {
    pub fn smallest_palindrome(s: String, k: i32) -> String {
        let bytes = s.as_bytes();
        let rank = k.max(1) as usize;
        const COUNT_LIMIT: usize = 1_000_001;
        let mut frequencies = [0usize; 26];
        for &byte in bytes {
            frequencies[(byte - b'a') as usize] += 1;
        }
        let mut odd_count = 0usize;
        let mut middle_char_index = 26u8;
        for char_index in 0..26 {
            if frequencies[char_index] & 1 == 1 {
                odd_count += 1;
                middle_char_index = char_index as u8;
            }
        }
        if odd_count > 1 {
            return String::new();
        }
        let mut half_frequencies = [0usize; 26];
        let mut half_length = 0usize;
        for char_index in 0..26 {
            half_frequencies[char_index] = frequencies[char_index] / 2;
            half_length += half_frequencies[char_index];
        }
        let count_permutations = |counts: &[usize; 26]| -> usize {
            let mut remaining: usize = counts.iter().sum();
            let mut permutations = 1usize;
            for &count in counts {
                if count == 0 {
                    continue;
                }
                let selected = count.min(remaining - count);
                let mut combinations = 1usize;
                for step in 1..=selected {
                    combinations = combinations * (remaining - step + 1) / step;
                    if combinations >= COUNT_LIMIT {
                        combinations = COUNT_LIMIT;
                        break;
                    }
                }
                permutations *= combinations;
                if permutations >= COUNT_LIMIT {
                    return COUNT_LIMIT;
                }
                remaining -= count;
            }
            permutations
        };
        if rank > count_permutations(&half_frequencies) {
            return String::new();
        }
        let length = bytes.len();
        let mut palindrome = vec![0u8; length];
        let mut remaining_rank = rank;
        let mut position = 0usize;
        for _ in 0..half_length {
            for char_index in 0..26 {
                if half_frequencies[char_index] == 0 {
                    continue;
                }
                half_frequencies[char_index] -= 1;
                let suffix_count = count_permutations(&half_frequencies);
                if suffix_count >= remaining_rank {
                    palindrome[position] = char_index as u8 + b'a';
                    position += 1;
                    break;
                }
                remaining_rank -= suffix_count;
                half_frequencies[char_index] += 1;
            }
        }
        if middle_char_index < 26 {
            palindrome[half_length] = middle_char_index + b'a';
        }
        for index in 0..half_length {
            palindrome[length - 1 - index] = palindrome[index];
        }
        String::from_utf8(palindrome).unwrap()
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
