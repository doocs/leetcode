---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3734.Lexicographically%20Smallest%20Palindromic%20Permutation%20Greater%20Than%20Target/README_EN.md
rating: 2330
source: Weekly Contest 474 Q4
tags:
    - Two Pointers
    - String
    - Enumeration
---

<!-- problem:start -->

# [3734. Lexicographically Smallest Palindromic Permutation Greater Than Target](https://leetcode.com/problems/lexicographically-smallest-palindromic-permutation-greater-than-target)

[中文文档](/solution/3700-3799/3734.Lexicographically%20Smallest%20Palindromic%20Permutation%20Greater%20Than%20Target/README.md)

## Description

<!-- description:start -->

<p>You are given two strings <code>s</code> and <code>target</code>, each of length <code>n</code>, consisting of lowercase English letters.</p>

<p>Return the <strong><span data-keyword="lexicographically-smaller-string">lexicographically smallest</span> string</strong> that is <strong>both</strong> a <strong><span data-keyword="palindrome-string">palindromic</span> <span data-keyword="permutation">permutation</span></strong> of <code>s</code> and <strong>strictly</strong> greater than <code>target</code>. If no such permutation exists, return an empty string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;baba&quot;, target = &quot;abba&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;baab&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The palindromic permutations of <code>s</code> (in lexicographical order) are <code>&quot;abba&quot;</code> and <code>&quot;baab&quot;</code>.</li>
	<li>The lexicographically smallest permutation that is strictly greater than <code>target</code> is <code>&quot;baab&quot;</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;baba&quot;, target = &quot;bbaa&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The palindromic permutations of <code>s</code> (in lexicographical order) are <code>&quot;abba&quot;</code> and <code>&quot;baab&quot;</code>.</li>
	<li>None of them is lexicographically strictly greater than <code>target</code>. Therefore, the answer is <code>&quot;&quot;</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abc&quot;, target = &quot;abb&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p><code>s</code> has no palindromic permutations. Therefore, the answer is <code>&quot;&quot;</code>.</p>
</div>

<p><strong class="example">Example 4:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;aac&quot;, target = &quot;abb&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;aca&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The only palindromic permutation of <code>s</code> is <code>&quot;aca&quot;</code>.</li>
	<li><code>&quot;aca&quot;</code> is strictly greater than <code>target</code>. Therefore, the answer is <code>&quot;aca&quot;</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == s.length == target.length &lt;= 300</code></li>
	<li><code>s</code> and <code>target</code> consist of only lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python

```

#### Java

```java

```

#### C++

```cpp

```

#### Go

```go

```

#### Rust

```rust
impl Solution {
    pub fn lex_palindromic_permutation(s: String, target: String) -> String {
        let mut freq = [0usize; 26];
        s.bytes().for_each(|ch| freq[(ch - b'a') as usize] += 1);
        if freq.iter().filter(|&&cnt| cnt & 1 != 0).count() > 1 {
            return String::new();
        }
        let mid = freq.iter().position(|cnt| cnt & 1 != 0);
        freq.iter_mut().for_each(|cnt| *cnt /= 2);
        let mut ans = s.into_bytes();
        let tgt = target.as_bytes();
        let half = ans.len() / 2;
        let make = |buf: &mut [u8]| {
            if let Some(ch) = mid {
                buf[half] = b'a' + ch as u8;
            }
            let len = buf.len();
            for idx in 0..half {
                let ch = buf[idx];
                buf[len - 1 - idx] = ch;
            }
        };
        let mut pos = 0;
        while pos < half {
            let ch = (tgt[pos] - b'a') as usize;
            if freq[ch] == 0 {
                break;
            }
            ans[pos] = tgt[pos];
            freq[ch] -= 1;
            pos += 1;
        }
        if pos == half {
            make(&mut ans);
            if ans.as_slice() > tgt {
                return String::from_utf8(ans).unwrap();
            }
        }
        loop {
            if pos < half {
                let min = (tgt[pos] - b'a' + 1) as usize;
                if let Some(ch) = (min..26).find(|&ch| freq[ch] != 0) {
                    ans[pos] = b'a' + ch as u8;
                    freq[ch] -= 1;
                    let mut dst = pos + 1;
                    for (ch, &cnt) in freq.iter().enumerate() {
                        for off in 0..cnt {
                            ans[dst + off] = b'a' + ch as u8;
                        }
                        dst += cnt;
                    }
                    make(&mut ans);
                    return String::from_utf8(ans).unwrap();
                }
            }
            if pos == 0 {
                return String::new();
            }
            pos -= 1;
            freq[(tgt[pos] - b'a') as usize] += 1;
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
