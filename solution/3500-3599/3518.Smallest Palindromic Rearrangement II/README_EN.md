---
comments: true
difficulty: Hard
rating: 2375
source: Weekly Contest 445 Q3
tags:
    - Hash Table
    - Math
    - String
    - Combinatorics
    - Counting
---

<!-- problem:start -->

# [3518. Smallest Palindromic Rearrangement II](https://leetcode.com/problems/smallest-palindromic-rearrangement-ii)

[中文文档](/solution/3500-3599/3518.Smallest%20Palindromic%20Rearrangement%20II/README.md)

## Description

<!-- description:start -->

<p data-end="332" data-start="99">You are given a <strong><span data-keyword="palindrome-string">palindromic</span></strong> string <code>s</code> and an integer <code>k</code>.</p>

<p>Return the <strong>k-th</strong> <strong><span data-keyword="lexicographically-smaller-string">lexicographically smallest</span></strong> palindromic <span data-keyword="permutation-string">permutation</span> of <code>s</code>. If there are fewer than <code>k</code> distinct palindromic permutations, return an empty string.</p>

<p><strong>Note:</strong> Different rearrangements that yield the same palindromic string are considered identical and are counted once.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abba&quot;, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;baab&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The two distinct palindromic rearrangements of <code>&quot;abba&quot;</code> are <code>&quot;abba&quot;</code> and <code>&quot;baab&quot;</code>.</li>
	<li>Lexicographically, <code>&quot;abba&quot;</code> comes before <code>&quot;baab&quot;</code>. Since <code>k = 2</code>, the output is <code>&quot;baab&quot;</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;aa&quot;, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>There is only one palindromic rearrangement: <code data-end="1112" data-start="1106">&quot;aa&quot;</code>.</li>
	<li>The output is an empty string since <code>k = 2</code> exceeds the number of possible rearrangements.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;bacab&quot;, k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;abcba&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The two distinct palindromic rearrangements of <code>&quot;bacab&quot;</code> are <code>&quot;abcba&quot;</code> and <code>&quot;bacab&quot;</code>.</li>
	<li>Lexicographically, <code>&quot;abcba&quot;</code> comes before <code>&quot;bacab&quot;</code>. Since <code>k = 1</code>, the output is <code>&quot;abcba&quot;</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
	<li><code>s</code> is guaranteed to be palindromic.</li>
	<li><code>1 &lt;= k &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- thinking:start -->

> **Thinking**
>
> The previous problem asked only for the smallest palindrome. Here we need the $k$-th distinct one, with $|s| \le 10^4$ and $k \le 10^6$, so listing every first-half permutation is impossible.
>
> The number of permutations of the remaining half-multiset is a product of binomial coefficients, capped above $k$. Try letters from left to right: keep a letter if the suffix still contains at least the remaining rank, otherwise subtract that count and try the next. Mirror the half and insert the middle character.

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
