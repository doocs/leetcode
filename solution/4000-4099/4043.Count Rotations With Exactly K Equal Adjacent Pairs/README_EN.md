---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4043.Count%20Rotations%20With%20Exactly%20K%20Equal%20Adjacent%20Pairs/README_EN.md
---

<!-- problem:start -->

# [4043. Count Rotations With Exactly K Equal Adjacent Pairs](https://leetcode.com/problems/count-rotations-with-exactly-k-equal-adjacent-pairs)

[中文文档](/solution/4000-4099/4043.Count%20Rotations%20With%20Exactly%20K%20Equal%20Adjacent%20Pairs/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> of length <code>n</code> and an integer <code>k</code>.</p>

<p>A <strong>cyclic rotation</strong> of <code>s</code> is obtained by choosing a <span data-keyword="string-prefix">prefix</span> of <code>s</code> whose length is between 0 and <code>n - 1</code> (inclusive), and moving it to the end of the string while preserving the order of all characters.</p>

<p>For <strong>every</strong> cyclic rotation of <code>s</code>, let its <strong>score</strong> be the number of indices <code>i</code> such that <code>0 &lt;= i &lt; n - 1</code> and the characters at positions <code>i</code> and <code>i + 1</code> are equal.</p>

<p>Return the number of cyclic rotations of <code>s</code> whose score equals <code>k</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;aab&quot;, k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The cyclic rotations of <code>s</code> are:</p>

<ul>
	<li><code>&quot;aab&quot;</code>: The characters at positions 0 and 1 are equal, so <code>score = 1</code>.</li>
	<li><code>&quot;aba&quot;</code>: No two adjacent characters are equal, so <code>score = 0</code>.</li>
	<li><code>&quot;baa&quot;</code>: The characters at positions 1 and 2 are equal, so <code>score = 1</code>.</li>
</ul>

<p>Since <code>score</code> equals <code>k</code> for 2 cyclic rotations of <code>s</code>, the answer is 2.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abca&quot;, k = 0</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>The cyclic rotations of <code>s</code> are:</p>

<ul>
	<li><code>&quot;abca&quot;</code>: No two adjacent characters are equal, so <code>score = 0</code>.</li>
	<li><code>&quot;bcaa&quot;</code>: The characters at positions 2 and 3 are equal, so <code>score = 1</code>.</li>
	<li><code>&quot;caab&quot;</code>: The characters at positions 1 and 2 are equal, so <code>score = 1</code>.</li>
	<li><code>&quot;aabc&quot;</code>: The characters at positions 0 and 1 are equal, so <code>score = 1</code>.</li>
</ul>

<p>Since <code>score</code> equals <code>k</code> for only 1 cyclic rotation of <code>s</code>, the answer is 1.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n == s.length &lt;= 100</code></li>
	<li><code>s</code> only consists of lowercase English letters.</li>
	<li><code>0 &lt;= k &lt;= n - 1</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

Let $n$ be the length of the string. First compute the score of the original string $s$, i.e. the number of indices $i$ such that $s[i] = s[i + 1]$ ($0 \leq i < n - 1$). If $\textit{score} = k$, increment the answer by $1$.

Then start from the original string and cyclically shift it left by one character, $n - 1$ times in total. On the $t$-th shift ($t = 0, 1, \ldots, n - 2$), the character moved to the end is $s[t]$, and the score changes in only two places:

- the adjacent pair at the front disappears, namely $s[t]$ and $s[t + 1]$;
- a new adjacent pair appears at the end, namely $s[t - 1]$ and $s[t]$.

All indices are taken modulo $n$. Thus $\textit{score}$ can be updated in $O(1)$ time, and we count the cyclic rotations whose score equals $k$.

The time complexity is $O(n)$ and the space complexity is $O(1)$, where $n$ is the length of the string $s$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countRotations(self, s: str, k: int) -> int:
        n = len(s)
        score = sum(a == b for a, b in pairwise(s))
        ans = int(score == k)

        for i in range(n, n * 2 - 1):
            score += int(s[i % n] == s[(i - 1) % n])
            score -= int(s[i % n] == s[(i + 1) % n])
            ans += int(score == k)

        return ans
```

#### Java

```java
class Solution {
    public int countRotations(String s, int k) {
        int n = s.length();
        int score = 0;

        for (int i = 0; i < n - 1; i++) {
            score += s.charAt(i) == s.charAt(i + 1) ? 1 : 0;
        }

        int ans = score == k ? 1 : 0;

        for (int i = n; i < n * 2 - 1; i++) {
            score += s.charAt(i % n) == s.charAt((i - 1) % n) ? 1 : 0;
            score -= s.charAt(i % n) == s.charAt((i + 1) % n) ? 1 : 0;
            ans += score == k ? 1 : 0;
        }

        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countRotations(string s, int k) {
        int n = s.size();
        int score = 0;

        for (int i = 0; i < n - 1; i++) {
            score += s[i] == s[i + 1];
        }

        int ans = score == k;

        for (int i = n; i < n * 2 - 1; i++) {
            score += s[i % n] == s[(i - 1) % n];
            score -= s[i % n] == s[(i + 1) % n];
            ans += score == k;
        }

        return ans;
    }
};
```

#### Go

```go
func countRotations(s string, k int) int {
	n := len(s)
	score := 0

	for i := 0; i < n-1; i++ {
		if s[i] == s[i+1] {
			score++
		}
	}

	ans := 0
	if score == k {
		ans++
	}

	for i := n; i < n*2-1; i++ {
		if s[i%n] == s[(i-1)%n] {
			score++
		}
		if s[i%n] == s[(i+1)%n] {
			score--
		}
		if score == k {
			ans++
		}
	}

	return ans
}
```

#### TypeScript

```ts
function countRotations(s: string, k: number): number {
    const n = s.length;
    let score = 0;

    for (let i = 0; i < n - 1; i++) {
        score += s[i] === s[i + 1] ? 1 : 0;
    }

    let ans = score === k ? 1 : 0;

    for (let i = n; i < n * 2 - 1; i++) {
        score += s[i % n] === s[(i - 1) % n] ? 1 : 0;
        score -= s[i % n] === s[(i + 1) % n] ? 1 : 0;
        ans += score === k ? 1 : 0;
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
