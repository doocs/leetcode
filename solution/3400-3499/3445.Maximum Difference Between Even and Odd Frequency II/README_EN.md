---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3445.Maximum%20Difference%20Between%20Even%20and%20Odd%20Frequency%20II/README_EN.md
rating: 2693
source: Weekly Contest 435 Q4
tags:
    - String
    - Enumeration
    - Prefix Sum
    - Sliding Window
---

<!-- problem:start -->

# [3445. Maximum Difference Between Even and Odd Frequency II](https://leetcode.com/problems/maximum-difference-between-even-and-odd-frequency-ii)

[中文文档](/solution/3400-3499/3445.Maximum%20Difference%20Between%20Even%20and%20Odd%20Frequency%20II/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> and an integer <code>k</code>. Your task is to find the <strong>maximum</strong> difference between the frequency of <strong>two</strong> characters, <code>freq[a] - freq[b]</code>, in a <span data-keyword="substring">substring</span> <code>subs</code> of <code>s</code>, such that:</p>

<ul>
	<li><code>subs</code> has a size of <strong>at least</strong> <code>k</code>.</li>
	<li>Character <code>a</code> has an <em>odd frequency</em> in <code>subs</code>.</li>
	<li>Character <code>b</code> has a <strong>non-zero</strong> <em>even frequency</em> in <code>subs</code>.</li>
</ul>

<p>Return the <strong>maximum</strong> difference.</p>

<p><strong>Note</strong> that <code>subs</code> can contain more than 2 <strong>distinct</strong> characters.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;12233&quot;, k = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>For the substring <code>&quot;12233&quot;</code>, the frequency of <code>&#39;1&#39;</code> is 1 and the frequency of <code>&#39;3&#39;</code> is 2. The difference is <code>1 - 2 = -1</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;1122211&quot;, k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>For the substring <code>&quot;11222&quot;</code>, the frequency of <code>&#39;2&#39;</code> is 3 and the frequency of <code>&#39;1&#39;</code> is 2. The difference is <code>3 - 2 = 1</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;110&quot;, k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= s.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>s</code> consists only of digits <code>&#39;0&#39;</code> to <code>&#39;4&#39;</code>.</li>
	<li>The input is generated that at least one substring has a character with an even frequency and a character with an odd frequency.</li>
	<li><code>1 &lt;= k &lt;= s.length</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumerate Character Pairs + Sliding Window + Prefix State Compression

We want to find a substring $\textit{subs}$ of string $s$ that satisfies the following conditions:

-   The length of $\textit{subs}$ is at least $k$.
-   The number of occurrences of character $a$ in $\textit{subs}$ is odd.
-   The number of occurrences of character $b$ in $\textit{subs}$ is even.
-   Maximize the frequency difference $f_a - f_b$, where $f_a$ and $f_b$ are the number of occurrences of $a$ and $b$ in $\textit{subs}$, respectively.

The characters in $s$ are from '0' to '4', so there are 5 possible characters. We can enumerate all different character pairs $(a, b)$, for a total of at most $5 \times 4 = 20$ combinations. We define:

-   Character $a$ is the target character with odd frequency.
-   Character $b$ is the target character with even frequency.

We use a sliding window to maintain the left and right boundaries of the substring, with variables:

-   $l$ denotes the position before the left boundary, so the window is $[l+1, r]$;
-   $r$ is the right boundary, traversing the entire string;
-   $\textit{curA}$ and $\textit{curB}$ denote the number of occurrences of $a$ and $b$ in the current window;
-   $\textit{preA}$ and $\textit{preB}$ denote the cumulative occurrences of $a$ and $b$ before the left boundary $l$.

We use a 2D array $t[2][2]$ to record the minimum value of $\textit{preA} - \textit{preB}$ for each possible parity combination of the window's left end, where $t[i][j]$ means $\textit{preA} \bmod 2 = i$ and $\textit{preB} \bmod 2 = j$.

Each time we move $r$ to the right, if the window length satisfies $r - l \ge k$ and $\textit{curB} - \textit{preB} \ge 2$, we try to move the left boundary $l$ to shrink the window, and update the corresponding $t[\textit{preA} \bmod 2][\textit{preB} \bmod 2]$.

Then, we try to update the answer:

$$
\textit{ans} = \max(\textit{ans},\ \textit{curA} - \textit{curB} - t[(\textit{curA} \bmod 2) \oplus 1][\textit{curB} \bmod 2])
$$

In this way, we can compute the maximum frequency difference for the current window each time $r$ moves to the right.

The time complexity is $O(n \times |\Sigma|^2)$, where $n$ is the length of $s$ and $|\Sigma|$ is the alphabet size (5 in this problem). The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxDifference(self, S: str, k: int) -> int:
        s = list(map(int, S))
        ans = -inf
        for a in range(5):
            for b in range(5):
                if a == b:
                    continue
                curA = curB = 0
                preA = preB = 0
                t = [[inf, inf], [inf, inf]]
                l = -1
                for r, x in enumerate(s):
                    curA += x == a
                    curB += x == b
                    while r - l >= k and curB - preB >= 2:
                        t[preA & 1][preB & 1] = min(t[preA & 1][preB & 1], preA - preB)
                        l += 1
                        preA += s[l] == a
                        preB += s[l] == b
                    ans = max(ans, curA - curB - t[curA & 1 ^ 1][curB & 1])
        return ans
```

#### Java

```java
class Solution {
    public int maxDifference(String S, int k) {
        char[] s = S.toCharArray();
        int n = s.length;
        final int inf = Integer.MAX_VALUE / 2;
        int ans = -inf;
        for (int a = 0; a < 5; ++a) {
            for (int b = 0; b < 5; ++b) {
                if (a == b) {
                    continue;
                }
                int curA = 0, curB = 0;
                int preA = 0, preB = 0;
                int[][] t = {{inf, inf}, {inf, inf}};
                for (int l = -1, r = 0; r < n; ++r) {
                    curA += s[r] == '0' + a ? 1 : 0;
                    curB += s[r] == '0' + b ? 1 : 0;
                    while (r - l >= k && curB - preB >= 2) {
                        t[preA & 1][preB & 1] = Math.min(t[preA & 1][preB & 1], preA - preB);
                        ++l;
                        preA += s[l] == '0' + a ? 1 : 0;
                        preB += s[l] == '0' + b ? 1 : 0;
                    }
                    ans = Math.max(ans, curA - curB - t[curA & 1 ^ 1][curB & 1]);
                }
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxDifference(string s, int k) {
        const int n = s.size();
        const int inf = INT_MAX / 2;
        int ans = -inf;

        for (int a = 0; a < 5; ++a) {
            for (int b = 0; b < 5; ++b) {
                if (a == b) {
                    continue;
                }

                int curA = 0, curB = 0;
                int preA = 0, preB = 0;
                int t[2][2] = {{inf, inf}, {inf, inf}};
                int l = -1;

                for (int r = 0; r < n; ++r) {
                    curA += (s[r] == '0' + a);
                    curB += (s[r] == '0' + b);
                    while (r - l >= k && curB - preB >= 2) {
                        t[preA & 1][preB & 1] = min(t[preA & 1][preB & 1], preA - preB);
                        ++l;
                        preA += (s[l] == '0' + a);
                        preB += (s[l] == '0' + b);
                    }
                    ans = max(ans, curA - curB - t[(curA & 1) ^ 1][curB & 1]);
                }
            }
        }

        return ans;
    }
};
```

#### Go

```go
func maxDifference(s string, k int) int {
	n := len(s)
	inf := math.MaxInt32 / 2
	ans := -inf

	for a := 0; a < 5; a++ {
		for b := 0; b < 5; b++ {
			if a == b {
				continue
			}
			curA, curB := 0, 0
			preA, preB := 0, 0
			t := [2][2]int{{inf, inf}, {inf, inf}}
			l := -1

			for r := 0; r < n; r++ {
				if s[r] == byte('0'+a) {
					curA++
				}
				if s[r] == byte('0'+b) {
					curB++
				}

				for r-l >= k && curB-preB >= 2 {
					t[preA&1][preB&1] = min(t[preA&1][preB&1], preA-preB)
					l++
					if s[l] == byte('0'+a) {
						preA++
					}
					if s[l] == byte('0'+b) {
						preB++
					}
				}

				ans = max(ans, curA-curB-t[curA&1^1][curB&1])
			}
		}
	}

	return ans
}
```

#### TypeScript

```ts
function maxDifference(S: string, k: number): number {
    const s = S.split('').map(Number);
    let ans = -Infinity;
    for (let a = 0; a < 5; a++) {
        for (let b = 0; b < 5; b++) {
            if (a === b) {
                continue;
            }
            let [curA, curB, preA, preB] = [0, 0, 0, 0];
            const t: number[][] = [
                [Infinity, Infinity],
                [Infinity, Infinity],
            ];
            let l = -1;
            for (let r = 0; r < s.length; r++) {
                const x = s[r];
                curA += x === a ? 1 : 0;
                curB += x === b ? 1 : 0;
                while (r - l >= k && curB - preB >= 2) {
                    t[preA & 1][preB & 1] = Math.min(t[preA & 1][preB & 1], preA - preB);
                    l++;
                    preA += s[l] === a ? 1 : 0;
                    preB += s[l] === b ? 1 : 0;
                }
                ans = Math.max(ans, curA - curB - t[(curA & 1) ^ 1][curB & 1]);
            }
        }
    }
    return ans;
}
```

#### Rust

```rust
use std::cmp::{max, min};
use std::i32::{MAX, MIN};

impl Solution {
    pub fn max_difference(S: String, k: i32) -> i32 {
        let s: Vec<usize> = S.chars().map(|c| c.to_digit(10).unwrap() as usize).collect();
        let k = k as usize;
        let mut ans = MIN;

        for a in 0..5 {
            for b in 0..5 {
                if a == b {
                    continue;
                }

                let mut curA = 0;
                let mut curB = 0;
                let mut preA = 0;
                let mut preB = 0;
                let mut t = [[MAX; 2]; 2];
                let mut l: isize = -1;

                for (r, &x) in s.iter().enumerate() {
                    curA += (x == a) as i32;
                    curB += (x == b) as i32;

                    while (r as isize - l) as usize >= k && curB - preB >= 2 {
                        let i = (preA & 1) as usize;
                        let j = (preB & 1) as usize;
                        t[i][j] = min(t[i][j], preA - preB);
                        l += 1;
                        if l >= 0 {
                            preA += (s[l as usize] == a) as i32;
                            preB += (s[l as usize] == b) as i32;
                        }
                    }

                    let i = (curA & 1 ^ 1) as usize;
                    let j = (curB & 1) as usize;
                    if t[i][j] != MAX {
                        ans = max(ans, curA - curB - t[i][j]);
                    }
                }
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
