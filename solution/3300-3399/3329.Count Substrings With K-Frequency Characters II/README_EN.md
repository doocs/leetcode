---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3329.Count%20Substrings%20With%20K-Frequency%20Characters%20II/README_EN.md
tags:
    - Hash Table
    - String
    - Sliding Window
---

<!-- problem:start -->

# [3329. Count Substrings With K-Frequency Characters II 🔒](https://leetcode.com/problems/count-substrings-with-k-frequency-characters-ii)

[中文文档](/solution/3300-3399/3329.Count%20Substrings%20With%20K-Frequency%20Characters%20II/README.md)

## Description

<!-- description:start -->

<p>Given a string <code>s</code> and an integer <code>k</code>, return the total number of <span data-keyword="substring-nonempty">substrings</span> of <code>s</code> where <strong>at least one</strong> character appears <strong>at least</strong> <code>k</code> times.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abacb&quot;, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>The valid substrings are:</p>

<ul>
	<li>&quot;<code>aba&quot;</code> (character <code>&#39;a&#39;</code> appears 2 times).</li>
	<li><code>&quot;abac&quot;</code> (character <code>&#39;a&#39;</code> appears 2 times).</li>
	<li><code>&quot;abacb&quot;</code> (character <code>&#39;a&#39;</code> appears 2 times).</li>
	<li><code>&quot;bacb&quot;</code> (character <code>&#39;b&#39;</code> appears 2 times).</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abcde&quot;, k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">15</span></p>

<p><strong>Explanation:</strong></p>

<p>All substrings are valid because every character appears at least once.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 3 * 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= s.length</code></li>
	<li><code>s</code> consists only of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sliding Window

We can enumerate the right endpoint of the substring, and then use a sliding window to maintain the left endpoint of the substring, ensuring that the occurrence count of each character in the sliding window is less than $k$.

We can use an array $\textit{cnt}$ to maintain the occurrence count of each character in the sliding window, then use a variable $\textit{l}$ to maintain the left endpoint of the sliding window, and use a variable $\textit{ans}$ to maintain the answer.

When we enumerate the right endpoint, we can add the character at the right endpoint to the sliding window, then check if the occurrence count of the character at the right endpoint in the sliding window is greater than or equal to $k$. If it is, we remove the character at the left endpoint from the sliding window until the occurrence count of each character in the sliding window is less than $k$. At this point, for substrings with left endpoints in the range $[0, ..l - 1]$ and right endpoint $r$, all satisfy the problem's requirements, so we add $l$ to the answer.

After enumeration, we return the answer.

The time complexity is $O(n)$, where $n$ is the length of the string $s$. The space complexity is $O(|\Sigma|)$, where $\Sigma$ is the character set, which in this case is the set of lowercase letters, so $|\Sigma| = 26$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfSubstrings(self, s: str, k: int) -> int:
        cnt = Counter()
        ans = l = 0
        for c in s:
            cnt[c] += 1
            while cnt[c] >= k:
                cnt[s[l]] -= 1
                l += 1
            ans += l
        return ans
```

#### Java

```java
class Solution {
    public long numberOfSubstrings(String s, int k) {
        int[] cnt = new int[26];
        long ans = 0;
        for (int l = 0, r = 0; r < s.length(); ++r) {
            int c = s.charAt(r) - 'a';
            ++cnt[c];
            while (cnt[c] >= k) {
                --cnt[s.charAt(l) - 'a'];
                l++;
            }
            ans += l;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long numberOfSubstrings(string s, int k) {
        int n = s.size();
        long long ans = 0, l = 0;
        int cnt[26]{};
        for (char& c : s) {
            ++cnt[c - 'a'];
            while (cnt[c - 'a'] >= k) {
                --cnt[s[l++] - 'a'];
            }
            ans += l;
        }
        return ans;
    }
};
```

#### Go

```go
func numberOfSubstrings(s string, k int) (ans int64) {
	l := 0
	cnt := [26]int{}
	for _, c := range s {
		cnt[c-'a']++
		for cnt[c-'a'] >= k {
			cnt[s[l]-'a']--
			l++
		}
		ans += int64(l)
	}
	return
}
```

#### TypeScript

```ts
function numberOfSubstrings(s: string, k: number): number {
    let [ans, l] = [0, 0];
    const cnt: number[] = Array(26).fill(0);
    for (const c of s) {
        const x = c.charCodeAt(0) - 97;
        ++cnt[x];
        while (cnt[x] >= k) {
            --cnt[s[l++].charCodeAt(0) - 97];
        }
        ans += l;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
