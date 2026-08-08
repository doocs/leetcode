---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3302.Find%20the%20Lexicographically%20Smallest%20Valid%20Sequence/README_EN.md
rating: 2473
source: Biweekly Contest 140 Q3
tags:
    - Greedy
    - Two Pointers
    - String
    - Dynamic Programming
---

<!-- problem:start -->

# [3302. Find the Lexicographically Smallest Valid Sequence](https://leetcode.com/problems/find-the-lexicographically-smallest-valid-sequence)

[中文文档](/solution/3300-3399/3302.Find%20the%20Lexicographically%20Smallest%20Valid%20Sequence/README.md)

## Description

<!-- description:start -->

<p>You are given two strings <code>word1</code> and <code>word2</code>.</p>

<p>A string <code>x</code> is called <strong>almost equal</strong> to <code>y</code> if you can change <strong>at most</strong> one character in <code>x</code> to make it <em>identical</em> to <code>y</code>.</p>

<p>A sequence of indices <code>seq</code> is called <strong>valid</strong> if:</p>

<ul>
	<li>The indices are sorted in <strong>ascending</strong> order.</li>
	<li><em>Concatenating</em> the characters at these indices in <code>word1</code> in <strong>the same</strong> order results in a string that is <strong>almost equal</strong> to <code>word2</code>.</li>
</ul>

<p>Return an array of size <code>word2.length</code> representing the <span data-keyword="lexicographically-smaller-array">lexicographically smallest</span> <strong>valid</strong> sequence of indices. If no such sequence of indices exists, return an <strong>empty</strong> array.</p>

<p><strong>Note</strong> that the answer must represent the <em>lexicographically smallest array</em>, <strong>not</strong> the corresponding string formed by those indices.<!-- notionvc: 2ff8e782-bd6f-4813-a421-ec25f7e84c1e --></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">word1 = &quot;vbcca&quot;, word2 = &quot;abc&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">[0,1,2]</span></p>

<p><strong>Explanation:</strong></p>

<p>The lexicographically smallest valid sequence of indices is <code>[0, 1, 2]</code>:</p>

<ul>
	<li>Change <code>word1[0]</code> to <code>&#39;a&#39;</code>.</li>
	<li><code>word1[1]</code> is already <code>&#39;b&#39;</code>.</li>
	<li><code>word1[2]</code> is already <code>&#39;c&#39;</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">word1 = &quot;bacdc&quot;, word2 = &quot;abc&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,2,4]</span></p>

<p><strong>Explanation:</strong></p>

<p>The lexicographically smallest valid sequence of indices is <code>[1, 2, 4]</code>:</p>

<ul>
	<li><code>word1[1]</code> is already <code>&#39;a&#39;</code>.</li>
	<li>Change <code>word1[2]</code> to <code>&#39;b&#39;</code>.</li>
	<li><code>word1[4]</code> is already <code>&#39;c&#39;</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">word1 = &quot;aaaaaa&quot;, word2 = &quot;aaabc&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">[]</span></p>

<p><strong>Explanation:</strong></p>

<p>There is no valid sequence of indices.</p>
</div>

<p><strong class="example">Example 4:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">word1 = &quot;abc&quot;, word2 = &quot;ab&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">[0,1]</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word2.length &lt; word1.length &lt;= 3 * 10<sup>5</sup></code></li>
	<li><code>word1</code> and <code>word2</code> consist only of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Two Pointers

We first use two pointers to preprocess a suffix array $\textit{suf}$ from right to left, where $\textit{suf}[i]$ represents the smallest starting index in $\textit{word2}$ such that $\textit{word2}[\textit{suf}[i]:]$ is a subsequence of $\textit{word1}[i:]$. Specifically, we use a pointer $j$ pointing to the frontmost unmatched character in $\textit{word2}$, initially $j = n - 1$, and set $\textit{suf}[m] = n$. Starting from $i = m - 1$, we traverse $\textit{word1}$ from right to left. If $j \ge 0$ and $\textit{word1}[i] = \textit{word2}[j]$, it means $\textit{word2}[j]$ can be matched, so we decrement $j$ by one, and then set $\textit{suf}[i] = j + 1$.

Next, we traverse $\textit{word1}$ from left to right, using a pointer $j$ to denote the index of the character in $\textit{word2}$ that we currently need to match (initially $j = 0$), and a variable $\textit{changed}$ to record whether we have already modified a character. For each character $c$ at index $i$:

- If $c = \textit{word2}[j]$, choosing index $i$ is always no worse (the smaller the index, the smaller the lexicographical order of the sequence), so we directly add $i$ to the answer and increment $j$ by one;
- Otherwise, if we have not modified a character yet and $\textit{suf}[i+1] \le j + 1$, it means we can modify $\textit{word1}[i]$ to $\textit{word2}[j]$, and the remaining part $\textit{word2}[j+1:]$ can still be matched within $\textit{word1}[i+1:]$. In this case, we choose index $i$ and set $\textit{changed}$ to true.

When $j = n$, we have matched all of $\textit{word2}$ and can return the answer. If the traversal ends without completing the match, we return an empty array.

The time complexity is $O(m + n)$, and the space complexity is $O(m)$, where $m$ and $n$ are the lengths of the strings $\textit{word1}$ and $\textit{word2}$, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def validSequence(self, word1: str, word2: str) -> List[int]:
        m, n = len(word1), len(word2)
        suf = [0] * (m + 1)
        suf[m] = n
        j = n - 1
        for i in range(m - 1, -1, -1):
            if j >= 0 and word1[i] == word2[j]:
                j -= 1
            suf[i] = j + 1

        ans = []
        changed = False
        j = 0
        for i, c in enumerate(word1):
            if c == word2[j] or (not changed and suf[i + 1] <= j + 1):
                if c != word2[j]:
                    changed = True
                ans.append(i)
                j += 1
                if j == n:
                    return ans
        return []
```

#### Java

```java
class Solution {
    public int[] validSequence(String word1, String word2) {
        int m = word1.length(), n = word2.length();

        int[] suf = new int[m + 1];
        suf[m] = n;

        int j = n - 1;
        for (int i = m - 1; i >= 0; i--) {
            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                j--;
            }
            suf[i] = j + 1;
        }

        int[] ans = new int[n];
        int size = 0;
        boolean changed = false;
        j = 0;

        for (int i = 0; i < m; i++) {
            char c = word1.charAt(i);
            if (c == word2.charAt(j) || (!changed && suf[i + 1] <= j + 1)) {
                if (c != word2.charAt(j)) {
                    changed = true;
                }
                ans[size++] = i;
                j++;
                if (j == n) {
                    return ans;
                }
            }
        }

        return new int[0];
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> validSequence(string word1, string word2) {
        int m = word1.size(), n = word2.size();

        vector<int> suf(m + 1);
        suf[m] = n;

        int j = n - 1;
        for (int i = m - 1; i >= 0; i--) {
            if (j >= 0 && word1[i] == word2[j]) {
                j--;
            }
            suf[i] = j + 1;
        }

        vector<int> ans;
        bool changed = false;
        j = 0;

        for (int i = 0; i < m; i++) {
            char c = word1[i];
            if (c == word2[j] || (!changed && suf[i + 1] <= j + 1)) {
                if (c != word2[j]) {
                    changed = true;
                }
                ans.push_back(i);
                j++;

                if (j == n) {
                    return ans;
                }
            }
        }

        return {};
    }
};
```

#### Go

```go
func validSequence(word1 string, word2 string) []int {
	m, n := len(word1), len(word2)

	suf := make([]int, m+1)
	suf[m] = n

	j := n - 1
	for i := m - 1; i >= 0; i-- {
		if j >= 0 && word1[i] == word2[j] {
			j--
		}
		suf[i] = j + 1
	}

	ans := make([]int, 0, n)
	changed := false
	j = 0

	for i := 0; i < m; i++ {
		c := word1[i]
		if c == word2[j] || (!changed && suf[i+1] <= j+1) {
			if c != word2[j] {
				changed = true
			}
			ans = append(ans, i)
			j++

			if j == n {
				return ans
			}
		}
	}

	return []int{}
}
```

#### TypeScript

```ts
function validSequence(word1: string, word2: string): number[] {
    const m = word1.length;
    const n = word2.length;

    const suf = new Array<number>(m + 1).fill(0);
    suf[m] = n;

    let j = n - 1;
    for (let i = m - 1; i >= 0; i--) {
        if (j >= 0 && word1[i] === word2[j]) {
            j--;
        }
        suf[i] = j + 1;
    }

    const ans: number[] = [];
    let changed = false;
    j = 0;

    for (let i = 0; i < m; i++) {
        const c = word1[i];

        if (c === word2[j] || (!changed && suf[i + 1] <= j + 1)) {
            if (c !== word2[j]) {
                changed = true;
            }

            ans.push(i);
            j++;

            if (j === n) {
                return ans;
            }
        }
    }

    return [];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
