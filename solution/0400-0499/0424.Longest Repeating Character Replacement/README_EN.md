---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0424.Longest%20Repeating%20Character%20Replacement/README_EN.md
tags:
    - Hash Table
    - String
    - Sliding Window
---

<!-- problem:start -->

# [424. Longest Repeating Character Replacement](https://leetcode.com/problems/longest-repeating-character-replacement)

[中文文档](/solution/0400-0499/0424.Longest%20Repeating%20Character%20Replacement/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> and an integer <code>k</code>. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most <code>k</code> times.</p>

<p>Return <em>the length of the longest substring containing the same letter you can get after performing the above operations</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;ABAB&quot;, k = 2
<strong>Output:</strong> 4
<strong>Explanation:</strong> Replace the two &#39;A&#39;s with two &#39;B&#39;s or vice versa.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;AABABBA&quot;, k = 1
<strong>Output:</strong> 4
<strong>Explanation:</strong> Replace the one &#39;A&#39; in the middle with &#39;B&#39; and form &quot;AABBBBA&quot;.
The substring &quot;BBBB&quot; has the longest repeating letters, which is 4.
There may exists other ways to achieve this answer too.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of only uppercase English letters.</li>
	<li><code>0 &lt;= k &lt;= s.length</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Two Pointers

We use a hash table `cnt` to count the occurrence of each character in the string, and two pointers `l` and `r` to maintain a sliding window, such that the size of the window minus the count of the most frequent character does not exceed $k$.

We iterate through the string, updating the right boundary `r` of the window each time, updating the count of characters within the window, and updating the maximum count `mx` of the characters that have appeared. When the size of the window minus `mx` is greater than $k$, we need to shrink the left boundary `l` of the window, updating the count of characters within the window, until the size of the window minus `mx` is no longer greater than $k$.

Finally, the answer is $n - l$, where $n$ is the length of the string.

The time complexity is $O(n)$, and the space complexity is $O(|\Sigma|)$. Where $n$ is the length of the string, and $|\Sigma|$ is the size of the character set. In this problem, the character set is uppercase English letters, so $|\Sigma| = 26$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def characterReplacement(self, s: str, k: int) -> int:
        cnt = Counter()
        l = mx = 0
        for r, c in enumerate(s):
            cnt[c] += 1
            mx = max(mx, cnt[c])
            if r - l + 1 - mx > k:
                cnt[s[l]] -= 1
                l += 1
        return len(s) - l
```

#### Java

```java
class Solution {
    public int characterReplacement(String s, int k) {
        int[] cnt = new int[26];
        int l = 0, mx = 0;
        int n = s.length();
        for (int r = 0; r < n; ++r) {
            mx = Math.max(mx, ++cnt[s.charAt(r) - 'A']);
            if (r - l + 1 - mx > k) {
                --cnt[s.charAt(l++) - 'A'];
            }
        }
        return n - l;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int characterReplacement(string s, int k) {
        int cnt[26]{};
        int l = 0, mx = 0;
        int n = s.length();
        for (int r = 0; r < n; ++r) {
            mx = max(mx, ++cnt[s[r] - 'A']);
            if (r - l + 1 - mx > k) {
                --cnt[s[l++] - 'A'];
            }
        }
        return n - l;
    }
};
```

#### Go

```go
func characterReplacement(s string, k int) int {
	cnt := [26]int{}
	l, mx := 0, 0
	for r, c := range s {
		cnt[c-'A']++
		mx = max(mx, cnt[c-'A'])
		if r-l+1-mx > k {
			cnt[s[l]-'A']--
			l++
		}
	}
	return len(s) - l
}
```

#### TypeScript

```ts
function characterReplacement(s: string, k: number): number {
    const idx = (c: string) => c.charCodeAt(0) - 65;
    const cnt: number[] = Array(26).fill(0);
    const n = s.length;
    let [l, mx] = [0, 0];
    for (let r = 0; r < n; ++r) {
        mx = Math.max(mx, ++cnt[idx(s[r])]);
        if (r - l + 1 - mx > k) {
            --cnt[idx(s[l++])];
        }
    }
    return n - l;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
