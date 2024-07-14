---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2516.Take%20K%20of%20Each%20Character%20From%20Left%20and%20Right/README_EN.md
rating: 1947
source: Weekly Contest 325 Q2
tags:
    - Hash Table
    - String
    - Sliding Window
---

<!-- problem:start -->

# [2516. Take K of Each Character From Left and Right](https://leetcode.com/problems/take-k-of-each-character-from-left-and-right)

[中文文档](/solution/2500-2599/2516.Take%20K%20of%20Each%20Character%20From%20Left%20and%20Right/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> consisting of the characters <code>&#39;a&#39;</code>, <code>&#39;b&#39;</code>, and <code>&#39;c&#39;</code> and a non-negative integer <code>k</code>. Each minute, you may take either the <strong>leftmost</strong> character of <code>s</code>, or the <strong>rightmost</strong> character of <code>s</code>.</p>

<p>Return<em> the <strong>minimum</strong> number of minutes needed for you to take <strong>at least</strong> </em><code>k</code><em> of each character, or return </em><code>-1</code><em> if it is not possible to take </em><code>k</code><em> of each character.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aabaaaacaabc&quot;, k = 2
<strong>Output:</strong> 8
<strong>Explanation:</strong> 
Take three characters from the left of s. You now have two &#39;a&#39; characters, and one &#39;b&#39; character.
Take five characters from the right of s. You now have four &#39;a&#39; characters, two &#39;b&#39; characters, and two &#39;c&#39; characters.
A total of 3 + 5 = 8 minutes is needed.
It can be proven that 8 is the minimum number of minutes needed.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;a&quot;, k = 1
<strong>Output:</strong> -1
<strong>Explanation:</strong> It is not possible to take one &#39;b&#39; or &#39;c&#39; so return -1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of only the letters <code>&#39;a&#39;</code>, <code>&#39;b&#39;</code>, and <code>&#39;c&#39;</code>.</li>
	<li><code>0 &lt;= k &lt;= s.length</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sliding Window

First, we use a hash table or an array of length $3$, denoted as $cnt$, to count the number of each character in string $s$. If any character appears less than $k$ times, it cannot be obtained, so we return $-1$ in advance.

The problem asks us to remove characters from the left and right sides of the string, so that the number of each remaining character is not less than $k$. We can consider the problem in reverse: remove a substring of certain size in the middle, so that in the remaining string on both sides, the number of each character is not less than $k$.

Therefore, we maintain a sliding window, with pointers $j$ and $i$ representing the left and right boundaries of the window, respectively. The string within the window is what we want to remove. Each time we move the right boundary $i$, we add the corresponding character $s[i]$ to the window (i.e., remove one character $s[i]$). If the count of $cnt[s[i]]$ is less than $k$, then we move the left boundary $j$ in a loop until the count of $cnt[s[i]]$ is not less than $k$. The size of the window at this time is $i - j + 1$, and we update the maximum window size.

The final answer is the length of string $s$ minus the size of the maximum window.

The time complexity is $O(n)$, where $n$ is the length of string $s$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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

#### TypeScript

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

#### Rust

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

<!-- solution:end -->

<!-- problem:end -->
