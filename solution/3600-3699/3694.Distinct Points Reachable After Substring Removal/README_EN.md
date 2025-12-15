---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3694.Distinct%20Points%20Reachable%20After%20Substring%20Removal/README_EN.md
rating: 1739
source: Biweekly Contest 166 Q3
tags:
    - Hash Table
    - String
    - Prefix Sum
    - Sliding Window
---

<!-- problem:start -->

# [3694. Distinct Points Reachable After Substring Removal](https://leetcode.com/problems/distinct-points-reachable-after-substring-removal)

[中文文档](/solution/3600-3699/3694.Distinct%20Points%20Reachable%20After%20Substring%20Removal/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> consisting of characters <code>&#39;U&#39;</code>, <code>&#39;D&#39;</code>, <code>&#39;L&#39;</code>, and <code>&#39;R&#39;</code>, representing moves on an infinite 2D Cartesian grid.</p>

<ul>
	<li><code>&#39;U&#39;</code>: Move from <code>(x, y)</code> to <code>(x, y + 1)</code>.</li>
	<li><code>&#39;D&#39;</code>: Move from <code>(x, y)</code> to <code>(x, y - 1)</code>.</li>
	<li><code>&#39;L&#39;</code>: Move from <code>(x, y)</code> to <code>(x - 1, y)</code>.</li>
	<li><code>&#39;R&#39;</code>: Move from <code>(x, y)</code> to <code>(x + 1, y)</code>.</li>
</ul>

<p>You are also given a positive integer <code>k</code>.</p>

<p>You <strong>must</strong> choose and remove <strong>exactly one</strong> contiguous substring of length <code>k</code> from <code>s</code>. Then, start from coordinate <code>(0, 0)</code> and perform the remaining moves in order.</p>

<p>Return an integer denoting the number of <strong>distinct</strong> final coordinates reachable.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;LUL&quot;, k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>After removing a substring of length 1, <code>s</code> can be <code>&quot;UL&quot;</code>, <code>&quot;LL&quot;</code> or <code>&quot;LU&quot;</code>. Following these moves, the final coordinates will be <code>(-1, 1)</code>, <code>(-2, 0)</code> and <code>(-1, 1)</code> respectively. There are two distinct points <code>(-1, 1)</code> and <code>(-2, 0)</code> so the answer is 2.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;UDLR&quot;, k = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>After removing a substring of length 4, <code>s</code> can only be the empty string. The final coordinates will be <code>(0, 0)</code>. There is only one distinct point <code>(0, 0)</code> so the answer is 1.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;UU&quot;, k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>After removing a substring of length 1, <code>s</code> becomes <code>&quot;U&quot;</code>, which always ends at <code>(0, 1)</code>, so there is only one distinct final coordinate.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of only <code>&#39;U&#39;</code>, <code>&#39;D&#39;</code>, <code>&#39;L&#39;</code>, and <code>&#39;R&#39;</code>.</li>
	<li><code>1 &lt;= k &lt;= s.length</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Prefix Sum + Hash Table

We can use prefix sum arrays to track position changes after each move. Specifically, we use two prefix sum arrays $f$ and $g$ to record the position changes on the $x$-axis and $y$-axis respectively after each move.

Initialize $f[0] = 0$ and $g[0] = 0$, representing the initial position at $(0, 0)$. Then, we iterate through the string $s$, and for each character:

- If the character is 'U', then $g[i] = g[i-1] + 1$.
- If the character is 'D', then $g[i] = g[i-1] - 1$.
- If the character is 'L', then $f[i] = f[i-1] - 1$.
- If the character is 'R', then $f[i] = f[i-1] + 1$.

Next, we use a hash set to store the distinct final coordinates. For each possible substring removal position $i$ (from $k$ to $n$), we calculate the final coordinates $(a, b)$ after removing the substring, where $a = f[n] - (f[i] - f[i-k])$ and $b = g[n] - (g[i] - g[i-k])$. Add the coordinates $(a, b)$ to the hash set.

Finally, the size of the hash set is the number of distinct final coordinates.

The time complexity is $O(n)$ and the space complexity is $O(n)$, where $n$ is the length of the string $s$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def distinctPoints(self, s: str, k: int) -> int:
        n = len(s)
        f = [0] * (n + 1)
        g = [0] * (n + 1)
        x = y = 0
        for i, c in enumerate(s, 1):
            if c == "U":
                y += 1
            elif c == "D":
                y -= 1
            elif c == "L":
                x -= 1
            else:
                x += 1
            f[i] = x
            g[i] = y
        st = set()
        for i in range(k, n + 1):
            a = f[n] - (f[i] - f[i - k])
            b = g[n] - (g[i] - g[i - k])
            st.add((a, b))
        return len(st)
```

#### Java

```java
class Solution {
    public int distinctPoints(String s, int k) {
        int n = s.length();
        int[] f = new int[n + 1];
        int[] g = new int[n + 1];
        int x = 0, y = 0;
        for (int i = 1; i <= n; ++i) {
            char c = s.charAt(i - 1);
            if (c == 'U') {
                ++y;
            } else if (c == 'D') {
                --y;
            } else if (c == 'L') {
                --x;
            } else {
                ++x;
            }
            f[i] = x;
            g[i] = y;
        }
        Set<Long> st = new HashSet<>();
        for (int i = k; i <= n; ++i) {
            int a = f[n] - (f[i] - f[i - k]);
            int b = g[n] - (g[i] - g[i - k]);
            st.add(1L * a * n + b);
        }
        return st.size();
    }
}
```

#### C++

```cpp
class Solution {
public:
    int distinctPoints(string s, int k) {
        int n = s.size();
        vector<int> f(n + 1), g(n + 1);
        int x = 0, y = 0;
        for (int i = 1; i <= n; ++i) {
            char c = s[i - 1];
            if (c == 'U')
                ++y;
            else if (c == 'D')
                --y;
            else if (c == 'L')
                --x;
            else
                ++x;
            f[i] = x;
            g[i] = y;
        }
        unordered_set<long long> st;
        for (int i = k; i <= n; ++i) {
            int a = f[n] - (f[i] - f[i - k]);
            int b = g[n] - (g[i] - g[i - k]);
            st.insert(1LL * a * n + b);
        }
        return st.size();
    }
};
```

#### Go

```go
func distinctPoints(s string, k int) int {
	n := len(s)
	f := make([]int, n+1)
	g := make([]int, n+1)
	x, y := 0, 0
	for i := 1; i <= n; i++ {
		c := s[i-1]
		if c == 'U' {
			y++
		} else if c == 'D' {
			y--
		} else if c == 'L' {
			x--
		} else {
			x++
		}
		f[i] = x
		g[i] = y
	}
	st := make(map[int64]struct{})
	for i := k; i <= n; i++ {
		a := f[n] - (f[i] - f[i-k])
		b := g[n] - (g[i] - g[i-k])
		key := int64(a)*int64(n) + int64(b)
		st[key] = struct{}{}
	}
	return len(st)
}
```

#### TypeScript

```ts
function distinctPoints(s: string, k: number): number {
    const n = s.length;
    const f = new Array(n + 1).fill(0);
    const g = new Array(n + 1).fill(0);
    let x = 0,
        y = 0;
    for (let i = 1; i <= n; ++i) {
        const c = s[i - 1];
        if (c === 'U') ++y;
        else if (c === 'D') --y;
        else if (c === 'L') --x;
        else ++x;
        f[i] = x;
        g[i] = y;
    }
    const st = new Set<number>();
    for (let i = k; i <= n; ++i) {
        const a = f[n] - (f[i] - f[i - k]);
        const b = g[n] - (g[i] - g[i - k]);
        st.add(a * n + b);
    }
    return st.size;
}
```

#### Rust

```rust
use std::collections::HashSet;

impl Solution {
    pub fn distinct_points(s: String, k: i32) -> i32 {
        let n = s.len();
        let mut f = vec![0; n + 1];
        let mut g = vec![0; n + 1];
        let mut x = 0;
        let mut y = 0;
        let bytes = s.as_bytes();
        for i in 1..=n {
            match bytes[i - 1] as char {
                'U' => y += 1,
                'D' => y -= 1,
                'L' => x -= 1,
                _ => x += 1,
            }
            f[i] = x;
            g[i] = y;
        }
        let mut st = HashSet::new();
        let k = k as usize;
        for i in k..=n {
            let a = f[n] - (f[i] - f[i - k]);
            let b = g[n] - (g[i] - g[i - k]);
            st.insert((a as i64) * (n as i64) + (b as i64));
        }
        st.len() as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
