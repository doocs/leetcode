---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3090.Maximum%20Length%20Substring%20With%20Two%20Occurrences/README_EN.md
rating: 1329
source: Weekly Contest 390 Q1
tags:
    - Hash Table
    - String
    - Sliding Window
---

<!-- problem:start -->

# [3090. Maximum Length Substring With Two Occurrences](https://leetcode.com/problems/maximum-length-substring-with-two-occurrences)

[中文文档](/solution/3000-3099/3090.Maximum%20Length%20Substring%20With%20Two%20Occurrences/README.md)

## Description

<!-- description:start -->

Given a string <code>s</code>, return the <strong>maximum</strong> length of a <span data-keyword="substring">substring</span>&nbsp;such that it contains <em>at most two occurrences</em> of each character.
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;bcbbbcba&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>
The following substring has a length of 4 and contains at most two occurrences of each character: <code>&quot;bcbb<u>bcba</u>&quot;</code>.</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;aaaa&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>
The following substring has a length of 2 and contains at most two occurrences of each character: <code>&quot;<u>aa</u>aa&quot;</code>.</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> consists only of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Two Pointers

We use two pointers $l$ and $r$ to maintain a sliding window, and an array $cnt$ to record the occurrence times of each character in the window.

In each iteration, we add the character $c$ at the pointer $r$ into the window, then check if $cnt[c]$ is greater than $2$. If it is, we move the pointer $l$ to the right until $cnt[c]$ is less than or equal to $2$. At this point, we update the answer $ans = \max(ans, r - l + 1)$.

Finally, we return the answer $ans$.

The time complexity is $O(n)$, where $n$ is the length of the string $s$. The space complexity is $O(|\Sigma|)$, where $\Sigma$ is the character set, and in this problem, $\Sigma = 26$.

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
