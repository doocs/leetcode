---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2900.Longest%20Unequal%20Adjacent%20Groups%20Subsequence%20I/README_EN.md
rating: 1468
source: Biweekly Contest 115 Q2
tags:
    - Greedy
    - Array
    - String
    - Dynamic Programming
---

<!-- problem:start -->

# [2900. Longest Unequal Adjacent Groups Subsequence I](https://leetcode.com/problems/longest-unequal-adjacent-groups-subsequence-i)

[中文文档](/solution/2900-2999/2900.Longest%20Unequal%20Adjacent%20Groups%20Subsequence%20I/README.md)

## Description

<!-- description:start -->

<p>You are given a string array <code>words</code> and a <strong>binary</strong> array <code>groups</code> both of length <code>n</code>.</p>

<p>A <span data-keyword="subsequence-array">subsequence</span> of <code>words</code> is <strong>alternating</strong> if for any two <em>consecutive</em> strings in the sequence, their corresponding elements at the <em>same</em> indices in <code>groups</code> are <strong>different</strong> (that is, there <em>cannot</em> be consecutive 0 or 1).</p>

<p>Your task is to select the <strong>longest alternating</strong> subsequence from <code>words</code>.</p>

<p>Return <em>the selected subsequence. If there are multiple answers, return <strong>any</strong> of them.</em></p>

<p><strong>Note:</strong> The elements in <code>words</code> are distinct.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block" style="
    border-color: var(--border-tertiary);
    border-left-width: 2px;
    color: var(--text-secondary);
    font-size: .875rem;
    margin-bottom: 1rem;
    margin-top: 1rem;
    overflow: visible;
    padding-left: 1rem;
">
<p><strong>Input:</strong> <span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
">words = [&quot;e&quot;,&quot;a&quot;,&quot;b&quot;], groups = [0,0,1]</span></p>

<p><strong>Output:</strong> <span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
">[&quot;e&quot;,&quot;b&quot;]</span></p>

<p><strong>Explanation:</strong> A subsequence that can be selected is <code>[&quot;e&quot;,&quot;b&quot;]</code> because <code>groups[0] != groups[2]</code>. Another subsequence that can be selected is <code>[&quot;a&quot;,&quot;b&quot;]</code> because <code>groups[1] != groups[2]</code>. It can be demonstrated that the length of the longest subsequence of indices that satisfies the condition is <code>2</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block" style="
    border-color: var(--border-tertiary);
    border-left-width: 2px;
    color: var(--text-secondary);
    font-size: .875rem;
    margin-bottom: 1rem;
    margin-top: 1rem;
    overflow: visible;
    padding-left: 1rem;
">
<p><strong>Input:</strong> <span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
">words = [&quot;a&quot;,&quot;b&quot;,&quot;c&quot;,&quot;d&quot;], groups = [1,0,1,1]</span></p>

<p><strong>Output:</strong> <span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
">[&quot;a&quot;,&quot;b&quot;,&quot;c&quot;]</span></p>

<p><strong>Explanation:</strong> A subsequence that can be selected is <code>[&quot;a&quot;,&quot;b&quot;,&quot;c&quot;]</code> because <code>groups[0] != groups[1]</code> and <code>groups[1] != groups[2]</code>. Another subsequence that can be selected is <code>[&quot;a&quot;,&quot;b&quot;,&quot;d&quot;]</code> because <code>groups[0] != groups[1]</code> and <code>groups[1] != groups[3]</code>. It can be shown that the length of the longest subsequence of indices that satisfies the condition is <code>3</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == words.length == groups.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 10</code></li>
	<li><code>groups[i]</code> is either <code>0</code> or <code>1.</code></li>
	<li><code>words</code> consists of <strong>distinct</strong> strings.</li>
	<li><code>words[i]</code> consists of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy

We can traverse the array $groups$, and for the current index $i$, if $i=0$ or $groups[i] \neq groups[i - 1]$, we add $words[i]$ to the answer array.

The time complexity is $O(n)$, where $n$ is the length of the array $groups$. The space complexity is $O(n)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def getLongestSubsequence(self, words: List[str], groups: List[int]) -> List[str]:
        return [words[i] for i, x in enumerate(groups) if i == 0 or x != groups[i - 1]]
```

#### Java

```java
class Solution {
    public List<String> getLongestSubsequence(String[] words, int[] groups) {
        int n = groups.length;
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (i == 0 || groups[i] != groups[i - 1]) {
                ans.add(words[i]);
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
    vector<string> getLongestSubsequence(vector<string>& words, vector<int>& groups) {
        int n = groups.size();
        vector<string> ans;
        for (int i = 0; i < n; ++i) {
            if (i == 0 || groups[i] != groups[i - 1]) {
                ans.emplace_back(words[i]);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func getLongestSubsequence(words []string, groups []int) (ans []string) {
	for i, x := range groups {
		if i == 0 || x != groups[i-1] {
			ans = append(ans, words[i])
		}
	}
	return
}
```

#### TypeScript

```ts
function getLongestSubsequence(words: string[], groups: number[]): string[] {
    const ans: string[] = [];
    for (let i = 0; i < groups.length; ++i) {
        if (i === 0 || groups[i] !== groups[i - 1]) {
            ans.push(words[i]);
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn get_longest_subsequence(words: Vec<String>, groups: Vec<i32>) -> Vec<String> {
        let mut ans = Vec::new();
        for (i, &g) in groups.iter().enumerate() {
            if i == 0 || g != groups[i - 1] {
                ans.push(words[i].clone());
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
