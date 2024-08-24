---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3146.Permutation%20Difference%20between%20Two%20Strings/README_EN.md
rating: 1152
source: Weekly Contest 397 Q1
tags:
    - Hash Table
    - String
---

<!-- problem:start -->

# [3146. Permutation Difference between Two Strings](https://leetcode.com/problems/permutation-difference-between-two-strings)

[中文文档](/solution/3100-3199/3146.Permutation%20Difference%20between%20Two%20Strings/README.md)

## Description

<!-- description:start -->

<p>You are given two strings <code>s</code> and <code>t</code> such that every character occurs at most once in <code>s</code> and <code>t</code> is a permutation of <code>s</code>.</p>

<p>The <strong>permutation difference</strong> between <code>s</code> and <code>t</code> is defined as the <strong>sum</strong> of the absolute difference between the index of the occurrence of each character in <code>s</code> and the index of the occurrence of the same character in <code>t</code>.</p>

<p>Return the <strong>permutation difference</strong> between <code>s</code> and <code>t</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abc&quot;, t = &quot;bac&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>For <code>s = &quot;abc&quot;</code> and <code>t = &quot;bac&quot;</code>, the permutation difference of <code>s</code> and <code>t</code> is equal to the sum of:</p>

<ul>
	<li>The absolute difference between the index of the occurrence of <code>&quot;a&quot;</code> in <code>s</code> and the index of the occurrence of <code>&quot;a&quot;</code> in <code>t</code>.</li>
	<li>The absolute difference between the index of the occurrence of <code>&quot;b&quot;</code> in <code>s</code> and the index of the occurrence of <code>&quot;b&quot;</code> in <code>t</code>.</li>
	<li>The absolute difference between the index of the occurrence of <code>&quot;c&quot;</code> in <code>s</code> and the index of the occurrence of <code>&quot;c&quot;</code> in <code>t</code>.</li>
</ul>

<p>That is, the permutation difference between <code>s</code> and <code>t</code> is equal to <code>|0 - 1| + |2 - 2| + |1 - 0| = 2</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abcde&quot;, t = &quot;edbac&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">12</span></p>

<p><strong>Explanation:</strong> The permutation difference between <code>s</code> and <code>t</code> is equal to <code>|0 - 3| + |1 - 2| + |2 - 4| + |3 - 1| + |4 - 0| = 12</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 26</code></li>
	<li>Each character occurs at most once in <code>s</code>.</li>
	<li><code>t</code> is a permutation of <code>s</code>.</li>
	<li><code>s</code> consists only of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table or Array

We can use a hash table or an array of length $26$, denoted as $\textit{d}$, to store the positions of each character in the string $\textit{s}$.

Then, we traverse the string $\textit{t}$ and calculate the sum of the absolute differences between the positions of each character in the string $\textit{t}$ and the positions in the string $\textit{s}$.

The time complexity is $O(n)$, where $n$ is the length of the string $\textit{s}$. The space complexity is $O(|\Sigma|)$, where $\Sigma$ is the character set. Here, it is lowercase English letters, so $|\Sigma| \leq 26$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findPermutationDifference(self, s: str, t: str) -> int:
        d = {c: i for i, c in enumerate(s)}
        return sum(abs(d[c] - i) for i, c in enumerate(t))
```

#### Java

```java
class Solution {
    public int findPermutationDifference(String s, String t) {
        int[] d = new int[26];
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            d[s.charAt(i) - 'a'] = i;
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += Math.abs(d[t.charAt(i) - 'a'] - i);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findPermutationDifference(string s, string t) {
        int d[26]{};
        int n = s.size();
        for (int i = 0; i < n; ++i) {
            d[s[i] - 'a'] = i;
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += abs(d[t[i] - 'a'] - i);
        }
        return ans;
    }
};
```

#### Go

```go
func findPermutationDifference(s string, t string) (ans int) {
	d := [26]int{}
	for i, c := range s {
		d[c-'a'] = i
	}
	for i, c := range t {
		ans += max(d[c-'a']-i, i-d[c-'a'])
	}
	return
}
```

#### TypeScript

```ts
function findPermutationDifference(s: string, t: string): number {
    const d: number[] = Array(26).fill(0);
    const n = s.length;
    for (let i = 0; i < n; ++i) {
        d[s.charCodeAt(i) - 97] = i;
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        ans += Math.abs(d[t.charCodeAt(i) - 97] - i);
    }
    return ans;
}
```

#### C#

```cs
public class Solution {
    public int FindPermutationDifference(string s, string t) {
        int[] d = new int[26];
        int n = s.Length;
        for (int i = 0; i < n; ++i) {
            d[s[i] - 'a'] = i;
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += Math.Abs(d[t[i] - 'a'] - i);
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
