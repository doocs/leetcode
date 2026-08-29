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
class Solution {
public:

    string buildPalindrome(string left, char middle, int n) {

        string right = left;
        reverse(right.begin(), right.end());

        if (n % 2 == 1) {
            return left + string(1, middle) + right;
        }

        return left + right;
    }

    string lexPalindromicPermutation(string s, string target) {

        int n = s.size();

        // -----------------------------------------
        // STEP 1: Count frequencies
        // -----------------------------------------

        vector<int> freq(26, 0);

        for (char c : s) {
            freq[c - 'a']++;
        }

        // -----------------------------------------
        // STEP 2: Check if palindrome is possible
        // -----------------------------------------

        int oddCount = 0;
        char middle = 0;

        for (int i = 0; i < 26; i++) {

            if (freq[i] % 2 == 1) {
                oddCount++;
                middle = char('a' + i);
            }
        }

        if (oddCount > 1) {
            return "";
        }

        // -----------------------------------------
        // STEP 3: Frequency for LEFT HALF
        // -----------------------------------------

        vector<int> halfFreq(26, 0);

        for (int i = 0; i < 26; i++) {
            halfFreq[i] = freq[i] / 2;
        }

        int halfLen = n / 2;

        string targetHalf = target.substr(0, halfLen);

        // -----------------------------------------
        // STEP 4:
        // Try to construct targetHalf exactly
        // -----------------------------------------

        vector<int> remaining = halfFreq;

        string prefix = "";

        int matched = 0;

        for (int i = 0; i < halfLen; i++) {

            int x = targetHalf[i] - 'a';

            if (remaining[x] == 0) {
                break;
            }

            prefix += targetHalf[i];
            remaining[x]--;

            matched++;
        }

        // -----------------------------------------
        // STEP 5:
        // If we constructed the entire targetHalf,
        // check its palindrome.
        // -----------------------------------------

        if (matched == halfLen) {

            string candidate =
                buildPalindrome(prefix, middle, n);

            if (candidate > target) {
                return candidate;
            }

            /*
                candidate == target

                So now we need the NEXT greater
                permutation of targetHalf.
            */
        }

        // -----------------------------------------
        // STEP 6:
        // Backtracking
        //
        // We try to increase the RIGHTMOST possible
        // position.
        // -----------------------------------------

        /*
            Suppose:

                targetHalf = "abc"

            and we matched:

                prefix = "ab"

            We first try:

                ab -> ac

            If impossible:

                ab -> ?

            Then backtrack:

                a -> b

            etc.
        */

        int lastPosition;

        if (matched == halfLen) {
            lastPosition = halfLen - 1;
        }
        else {
            lastPosition = matched;
        }

        for (int pos = lastPosition; pos >= 0; pos--) {

            // -----------------------------------------
            // Rebuild frequency available BEFORE pos
            // -----------------------------------------

            vector<int> rem = halfFreq;

            bool validPrefix = true;

            for (int i = 0; i < pos; i++) {

                int x = targetHalf[i] - 'a';

                if (rem[x] == 0) {
                    validPrefix = false;
                    break;
                }

                rem[x]--;
            }

            if (!validPrefix) {
                continue;
            }

            // -----------------------------------------
            // At this position, we need a character
            // GREATER than targetHalf[pos]
            // -----------------------------------------

            int targetChar = targetHalf[pos] - 'a';

            for (int c = targetChar + 1; c < 26; c++) {

                if (rem[c] == 0) {
                    continue;
                }

                // -----------------------------------------
                // Build new left half
                // -----------------------------------------

                string left = targetHalf.substr(0, pos);

                // Put smallest possible greater character
                left += char('a' + c);

                rem[c]--;

                // -----------------------------------------
                // Fill remaining positions with the
                // smallest available characters
                // -----------------------------------------

                for (int x = 0; x < 26; x++) {

                    while (rem[x] > 0) {

                        left += char('a' + x);

                        rem[x]--;
                    }
                }

                // -----------------------------------------
                // Build palindrome
                // -----------------------------------------

                string candidate =
                    buildPalindrome(left, middle, n);

                if (candidate > target) {
                    return candidate;
                }

                // Restore is not necessary because rem
                // is recreated for every `c` iteration
                rem = halfFreq;

                for (int i = 0; i < pos; i++) {
                    rem[targetHalf[i] - 'a']--;
                }
            }
        }

        return "";
    }
};

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
