---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3773.Maximum%20Number%20of%20Equal%20Length%20Runs/README_EN.md
tags:
    - Hash Table
    - String
    - Counting
---

<!-- problem:start -->

# [3773. Maximum Number of Equal Length Runs 🔒](https://leetcode.com/problems/maximum-number-of-equal-length-runs)

[中文文档](/solution/3700-3799/3773.Maximum%20Number%20of%20Equal%20Length%20Runs/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> consisting of lowercase English letters.</p>

<p>A <strong>run</strong> in <code>s</code> is a <strong><span data-keyword="substring-nonempty">substring</span></strong> of <strong>equal</strong> letters that cannot be extended further. For example, the runs in <code>&quot;hello&quot;</code> are <code>&quot;h&quot;</code>, <code>&quot;e&quot;</code>, <code>&quot;ll&quot;</code>, and <code>&quot;o&quot;</code>.</p>

<p>You can <strong>select</strong> runs that have the <strong>same</strong> length in <code>s</code>.</p>

<p>Return an integer denoting the <strong>maximum</strong> number of runs you can select in <code>s</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;hello&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>The runs in <code>s</code> are <code>&quot;h&quot;</code>, <code>&quot;e&quot;</code>, <code>&quot;ll&quot;</code>, and <code>&quot;o&quot;</code>. You can select <code>&quot;h&quot;</code>, <code>&quot;e&quot;</code>, and <code>&quot;o&quot;</code> because they have the same length 1.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;aaabaaa&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The runs in <code>s</code> are <code>&quot;aaa&quot;</code>, <code>&quot;b&quot;</code>, and <code>&quot;aaa&quot;</code>. You can select <code>&quot;aaa&quot;</code> and <code>&quot;aaa&quot;</code> because they have the same length 3.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of lowercase English letters only.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

We can use a hash table $\textit{cnt}$ to record the number of occurrences of each run length. We traverse the string $s$, and for each run, we calculate its length $m$ and increment $\textit{cnt}[m]$ by $1$. Finally, the answer is the maximum value in $\textit{cnt}$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Where $n$ is the length of the string $s$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxSameLengthRuns(self, s: str) -> int:
        cnt = Counter()
        for _, g in groupby(s):
            cnt[len(list(g))] += 1
        return max(cnt.values())
```

#### Java

```java
class Solution {
    public int maxSameLengthRuns(String s) {
        Map<Integer, Integer> cnt = new HashMap<>();
        int ans = 0;
        int n = s.length();
        for (int i = 0; i < n;) {
            int j = i + 1;
            while (j < n && s.charAt(j) == s.charAt(i)) {
                ++j;
            }
            int m = j - i;
            ans = Math.max(ans, cnt.merge(m, 1, Integer::sum));
            i = j;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxSameLengthRuns(string s) {
        unordered_map<int, int> cnt;
        int ans = 0;
        int n = s.size();
        for (int i = 0; i < n;) {
            int j = i + 1;
            while (j < n && s[j] == s[i]) {
                ++j;
            }
            int m = j - i;
            ans = max(ans, ++cnt[m]);
            i = j;
        }
        return ans;
    }
};
```

#### Go

```go
func maxSameLengthRuns(s string) (ans int) {
	cnt := map[int]int{}
	n := len(s)
	for i := 0; i < n; {
		j := i + 1
		for j < n && s[j] == s[i] {
			j++
		}
		m := j - i
		cnt[m]++
		ans = max(ans, cnt[m])
		i = j
	}
	return
}
```

#### TypeScript

```ts
function maxSameLengthRuns(s: string): number {
    const cnt: Record<number, number> = {};
    const n = s.length;
    let ans = 0;
    for (let i = 0; i < n; ) {
        let j = i + 1;
        while (j < n && s[j] === s[i]) {
            ++j;
        }
        const m = j - i;
        cnt[m] = (cnt[m] || 0) + 1;
        ans = Math.max(ans, cnt[m]);
        i = j;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
