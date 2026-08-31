---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3720.Lexicographically%20Smallest%20Permutation%20Greater%20Than%20Target/README_EN.md
rating: 1958
source: Weekly Contest 472 Q3
tags:
    - Greedy
    - Hash Table
    - String
    - Counting
    - Enumeration
---

<!-- problem:start -->

# [3720. Lexicographically Smallest Permutation Greater Than Target](https://leetcode.com/problems/lexicographically-smallest-permutation-greater-than-target)

[中文文档](/solution/3700-3799/3720.Lexicographically%20Smallest%20Permutation%20Greater%20Than%20Target/README.md)

## Description

<!-- description:start -->

<p>You are given two strings <code>s</code> and <code>target</code>, both having length <code>n</code>, consisting of lowercase English letters.</p>

<p>Return the <strong>lexicographically smallest <span data-keyword="permutation-string">permutation</span></strong> of <code>s</code> that is <strong>strictly</strong> greater than <code>target</code>. If no permutation of <code>s</code> is lexicographically strictly greater than <code>target</code>, return an empty string.</p>

<p>A string <code>a</code> is <strong>lexicographically strictly greater </strong>than a string <code>b</code> (of the same length) if in the first position where <code>a</code> and <code>b</code> differ, string <code>a</code> has a letter that appears later in the alphabet than the corresponding letter in <code>b</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abc&quot;, target = &quot;bba&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;bca&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The permutations of <code>s</code> (in lexicographical order) are <code>&quot;abc&quot;</code>, <code>&quot;acb&quot;</code>, <code>&quot;bac&quot;</code>, <code>&quot;bca&quot;</code>, <code>&quot;cab&quot;</code>, and <code>&quot;cba&quot;</code>.</li>
	<li>The lexicographically smallest permutation that is strictly greater than <code>target</code> is <code>&quot;bca&quot;</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;leet&quot;, target = &quot;code&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;eelt&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The permutations of <code>s</code> (in lexicographical order) are <code>&quot;eelt&quot;</code>, <code>&quot;eetl&quot;</code>, <code>&quot;elet&quot;</code>, <code>&quot;elte&quot;</code>, <code>&quot;etel&quot;</code>, <code>&quot;etle&quot;</code>, <code>&quot;leet&quot;</code>, <code>&quot;lete&quot;</code>, <code>&quot;ltee&quot;</code>, <code>&quot;teel&quot;</code>, <code>&quot;tele&quot;</code>, and <code>&quot;tlee&quot;</code>.</li>
	<li>The lexicographically smallest permutation that is strictly greater than <code>target</code> is <code>&quot;eelt&quot;</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;baba&quot;, target = &quot;bbaa&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The permutations of <code>s</code> (in lexicographical order) are <code>&quot;aabb&quot;</code>, <code>&quot;abab&quot;</code>, <code>&quot;abba&quot;</code>, <code>&quot;baab&quot;</code>, <code>&quot;baba&quot;</code>, and <code>&quot;bbaa&quot;</code>.</li>
	<li>None of them is lexicographically strictly greater than <code>target</code>. Therefore, the answer is <code>&quot;&quot;</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length == target.length &lt;= 300</code></li>
	<li><code>s</code> and <code>target</code> consist of only lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Backtracking

To be strictly greater than $\textit{target}$, the answer must look like this: it matches some prefix of $\textit{target}$ exactly, places a character greater than the corresponding character of $\textit{target}$ at the next position, and arranges the remaining characters in ascending order. The longer this common prefix is, the smaller the resulting permutation, so we want the common prefix to be as long as possible.

We first count the occurrences of every character of $s$ in $\textit{cnt}$, then match $\textit{target}$ from left to right as far as we can: as long as the current character is still available we take it and append it to the answer, stopping once some character runs out. This yields the longest common prefix.

Next we walk back from the end of that prefix, trying each position $i$ as the place where the answer diverges: we put the smallest still available character greater than $\textit{target}[i]$ at position $i$, and if that succeeds we append the remaining characters in ascending order and return. Otherwise we give $\textit{target}[i - 1]$ back to $\textit{cnt}$ and try an earlier position. Note that when $\textit{target}$ itself is a permutation of $s$, no character can be placed at position $n$, so we have to start backtracking from the last position because the answer must be strictly greater. If every position fails, no such permutation exists and we return an empty string.

The time complexity is $O(n \times |\Sigma|)$, and the space complexity is $O(n + |\Sigma|)$. Here, $n$ is the length of the string $s$, and $|\Sigma| = 26$ is the size of the character set.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def lexGreaterPermutation(self, s: str, target: str) -> str:
        cnt = Counter(s)
        n = len(target)
        ans = []
        for c in target:
            if cnt[c] == 0:
                break
            cnt[c] -= 1
            ans.append(c)
        for i in range(len(ans), -1, -1):
            if i < n:
                for c in ascii_lowercase:
                    if c > target[i] and cnt[c] > 0:
                        cnt[c] -= 1
                        rest = ''.join(x * cnt[x] for x in ascii_lowercase)
                        return ''.join(ans[:i]) + c + rest
            if i > 0:
                cnt[ans[i - 1]] += 1
        return ''
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
    pub fn lex_greater_permutation(s: String, target: String) -> String {
        let mut permutation = s.into_bytes();
        let target_bytes = target.as_bytes();
        let mut letter_counts = [0usize; 26];
        for &byte in &permutation {
            letter_counts[(byte - b'a') as usize] += 1;
        }
        let mut prefix_length = 0;
        while prefix_length < target_bytes.len() {
            let target_letter = (target_bytes[prefix_length] - b'a') as usize;
            if letter_counts[target_letter] == 0 {
                break;
            }
            permutation[prefix_length] = target_bytes[prefix_length];
            letter_counts[target_letter] -= 1;
            prefix_length += 1;
        }
        loop {
            if prefix_length < target_bytes.len() {
                let next_letter = (target_bytes[prefix_length] - b'a') as usize + 1;
                if let Some(replacement_letter) =
                    (next_letter..26).find(|&letter| letter_counts[letter] > 0)
                {
                    permutation[prefix_length] = b'a' + replacement_letter as u8;
                    letter_counts[replacement_letter] -= 1;
                    let mut write_index = prefix_length + 1;
                    for (letter, &count) in letter_counts.iter().enumerate() {
                        for _ in 0..count {
                            permutation[write_index] = b'a' + letter as u8;
                            write_index += 1;
                        }
                    }
                    return String::from_utf8(permutation).unwrap();
                }
            }
            if prefix_length == 0 {
                return String::new();
            }
            prefix_length -= 1;
            letter_counts[(target_bytes[prefix_length] - b'a') as usize] += 1;
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
