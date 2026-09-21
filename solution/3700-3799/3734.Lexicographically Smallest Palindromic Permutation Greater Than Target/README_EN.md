---
comments: true
difficulty: Hard
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

<!-- thinking:start -->

> **Thinking**
>
> A palindromic permutation is determined by its left half and at most one odd center; more than one odd frequency is impossible. We want the smallest palindrome strictly larger than $\textit{target}$, so the left half is built like the next permutation: match the first half of $\textit{target}$ as far as possible, raise the first feasible position, and mirror the left half to the right.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def lexPalindromicPermutation(self, s: str, target: str) -> str:
        def build(left: str, middle: str, n: int) -> str:
            right = left[::-1]
            if n % 2:
                return left + middle + right
            return left + right

        n = len(s)
        freq = [0] * 26
        for c in s:
            freq[ord(c) - 97] += 1
        odd = 0
        middle = ""
        for i, v in enumerate(freq):
            if v % 2:
                odd += 1
                middle = chr(97 + i)
        if odd > 1:
            return ""

        half = [v // 2 for v in freq]
        half_len = n // 2
        target_half = target[:half_len]
        remaining = half[:]
        prefix = []
        matched = 0
        for i in range(half_len):
            x = ord(target_half[i]) - 97
            if remaining[x] == 0:
                break
            prefix.append(target_half[i])
            remaining[x] -= 1
            matched += 1

        if matched == half_len:
            cand = build("".join(prefix), middle, n)
            if cand > target:
                return cand

        last = half_len - 1 if matched == half_len else matched
        for pos in range(last, -1, -1):
            rem = half[:]
            valid = True
            for i in range(pos):
                x = ord(target_half[i]) - 97
                if rem[x] == 0:
                    valid = False
                    break
                rem[x] -= 1
            if not valid:
                continue
            target_char = ord(target_half[pos]) - 97
            for c in range(target_char + 1, 26):
                if rem[c] == 0:
                    continue
                left = target_half[:pos] + chr(97 + c)
                rem[c] -= 1
                for x in range(26):
                    left += chr(97 + x) * rem[x]
                    rem[x] = 0
                cand = build(left, middle, n)
                if cand > target:
                    return cand
                rem = half[:]
                for i in range(pos):
                    rem[ord(target_half[i]) - 97] -= 1
        return ""
```

#### Java

```java
class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];
        for (int i = 0; i < n; ++i) {
            ++freq[s.charAt(i) - 'a'];
        }
        int odd = 0;
        char middle = 0;
        for (int i = 0; i < 26; ++i) {
            if (freq[i] % 2 == 1) {
                ++odd;
                middle = (char) ('a' + i);
            }
        }
        if (odd > 1) {
            return "";
        }
        int[] half = new int[26];
        for (int i = 0; i < 26; ++i) {
            half[i] = freq[i] / 2;
        }
        int halfLen = n / 2;
        String targetHalf = target.substring(0, halfLen);
        int[] remaining = half.clone();
        StringBuilder prefix = new StringBuilder();
        int matched = 0;
        for (int i = 0; i < halfLen; ++i) {
            int x = targetHalf.charAt(i) - 'a';
            if (remaining[x] == 0) {
                break;
            }
            prefix.append(targetHalf.charAt(i));
            --remaining[x];
            ++matched;
        }
        if (matched == halfLen) {
            String cand = build(prefix.toString(), middle, n);
            if (cand.compareTo(target) > 0) {
                return cand;
            }
        }
        int last = matched == halfLen ? halfLen - 1 : matched;
        for (int pos = last; pos >= 0; --pos) {
            int[] rem = half.clone();
            boolean valid = true;
            for (int i = 0; i < pos; ++i) {
                int x = targetHalf.charAt(i) - 'a';
                if (rem[x] == 0) {
                    valid = false;
                    break;
                }
                --rem[x];
            }
            if (!valid) {
                continue;
            }
            int targetChar = targetHalf.charAt(pos) - 'a';
            for (int c = targetChar + 1; c < 26; ++c) {
                if (rem[c] == 0) {
                    continue;
                }
                StringBuilder left = new StringBuilder(targetHalf.substring(0, pos));
                left.append((char) ('a' + c));
                --rem[c];
                for (int x = 0; x < 26; ++x) {
                    while (rem[x] > 0) {
                        left.append((char) ('a' + x));
                        --rem[x];
                    }
                }
                String cand = build(left.toString(), middle, n);
                if (cand.compareTo(target) > 0) {
                    return cand;
                }
                rem = half.clone();
                for (int i = 0; i < pos; ++i) {
                    --rem[targetHalf.charAt(i) - 'a'];
                }
            }
        }
        return "";
    }

    private String build(String left, char middle, int n) {
        String right = new StringBuilder(left).reverse().toString();
        if (n % 2 == 1) {
            return left + middle + right;
        }
        return left + right;
    }
}
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
        vector<int> freq(26, 0);
        for (char c : s) {
            freq[c - 'a']++;
        }

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

        vector<int> halfFreq(26, 0);
        for (int i = 0; i < 26; i++) {
            halfFreq[i] = freq[i] / 2;
        }

        int halfLen = n / 2;
        string targetHalf = target.substr(0, halfLen);
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

        if (matched == halfLen) {
            string candidate = buildPalindrome(prefix, middle, n);
            if (candidate > target) {
                return candidate;
            }
        }

        int lastPosition = matched == halfLen ? halfLen - 1 : matched;
        for (int pos = lastPosition; pos >= 0; pos--) {
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

            int targetChar = targetHalf[pos] - 'a';
            for (int c = targetChar + 1; c < 26; c++) {
                if (rem[c] == 0) {
                    continue;
                }
                string left = targetHalf.substr(0, pos);
                left += char('a' + c);
                rem[c]--;
                for (int x = 0; x < 26; x++) {
                    while (rem[x] > 0) {
                        left += char('a' + x);
                        rem[x]--;
                    }
                }
                string candidate = buildPalindrome(left, middle, n);
                if (candidate > target) {
                    return candidate;
                }
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
func lexPalindromicPermutation(s string, target string) string {
	build := func(left string, middle byte, n int) string {
		b := []byte(left)
		for i, j := 0, len(b)-1; i < j; i, j = i+1, j-1 {
			b[i], b[j] = b[j], b[i]
		}
		right := string(b)
		if n%2 == 1 {
			return left + string(middle) + right
		}
		return left + right
	}

	n := len(s)
	freq := make([]int, 26)
	for i := 0; i < n; i++ {
		freq[s[i]-'a']++
	}
	odd := 0
	var middle byte
	for i, v := range freq {
		if v%2 == 1 {
			odd++
			middle = byte('a' + i)
		}
	}
	if odd > 1 {
		return ""
	}
	half := make([]int, 26)
	for i, v := range freq {
		half[i] = v / 2
	}
	halfLen := n / 2
	targetHalf := target[:halfLen]
	remaining := append([]int(nil), half...)
	matched := 0
	for i := 0; i < halfLen; i++ {
		x := int(targetHalf[i] - 'a')
		if remaining[x] == 0 {
			break
		}
		remaining[x]--
		matched++
	}
	if matched == halfLen {
		cand := build(targetHalf, middle, n)
		if cand > target {
			return cand
		}
	}
	last := matched
	if matched == halfLen {
		last = halfLen - 1
	}
	for pos := last; pos >= 0; pos-- {
		rem := append([]int(nil), half...)
		valid := true
		for i := 0; i < pos; i++ {
			x := int(targetHalf[i] - 'a')
			if rem[x] == 0 {
				valid = false
				break
			}
			rem[x]--
		}
		if !valid {
			continue
		}
		targetChar := int(targetHalf[pos] - 'a')
		for c := targetChar + 1; c < 26; c++ {
			if rem[c] == 0 {
				continue
			}
			left := targetHalf[:pos] + string(byte('a'+c))
			rem[c]--
			for x := 0; x < 26; x++ {
				for rem[x] > 0 {
					left += string(byte('a' + x))
					rem[x]--
				}
			}
			cand := build(left, middle, n)
			if cand > target {
				return cand
			}
			rem = append([]int(nil), half...)
			for i := 0; i < pos; i++ {
				rem[targetHalf[i]-'a']--
			}
		}
	}
	return ""
}
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
