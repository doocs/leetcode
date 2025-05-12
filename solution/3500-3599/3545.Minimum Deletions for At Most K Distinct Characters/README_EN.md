---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3545.Minimum%20Deletions%20for%20At%20Most%20K%20Distinct%20Characters/README_EN.md
---

<!-- problem:start -->

# [3545. Minimum Deletions for At Most K Distinct Characters](https://leetcode.com/problems/minimum-deletions-for-at-most-k-distinct-characters)

[中文文档](/solution/3500-3599/3545.Minimum%20Deletions%20for%20At%20Most%20K%20Distinct%20Characters/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> consisting of lowercase English letters, and an integer <code>k</code>.</p>

<p>Your task is to delete some (possibly none) of the characters in the string so that the number of <strong>distinct</strong> characters in the resulting string is <strong>at most</strong> <code>k</code>.</p>

<p>Return the <strong>minimum</strong> number of deletions required to achieve this.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abc&quot;, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>s</code> has three distinct characters: <code>&#39;a&#39;</code>, <code>&#39;b&#39;</code> and <code>&#39;c&#39;</code>, each with a frequency of 1.</li>
	<li>Since we can have at most <code>k = 2</code> distinct characters, remove all occurrences of any one character from the string.</li>
	<li>For example, removing all occurrences of <code>&#39;c&#39;</code> results in at most <code>k</code> distinct characters. Thus, the answer is 1.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;aabb&quot;, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>s</code> has two distinct characters (<code>&#39;a&#39;</code> and <code>&#39;b&#39;</code>) with frequencies of 2 and 2, respectively.</li>
	<li>Since we can have at most <code>k = 2</code> distinct characters, no deletions are required. Thus, the answer is 0.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;yyyzz&quot;, k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>s</code> has two distinct characters (<code>&#39;y&#39;</code> and <code>&#39;z&#39;</code>) with frequencies of 3 and 2, respectively.</li>
	<li>Since we can have at most <code>k = 1</code> distinct character, remove all occurrences of any one character from the string.</li>
	<li>Removing all <code>&#39;z&#39;</code> results in at most <code>k</code> distinct characters. Thus, the answer is 2.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 16</code></li>
	<li><code>1 &lt;= k &lt;= 16</code></li>
	<li><code>s</code> consists only of lowercase English letters.</li>
</ul>

<p> </p>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting + Greedy

We can use an array $\textit{cnt}$ to count the frequency of each character. Then, we sort this array and return the sum of the first $26 - k$ elements.

The time complexity is $O(|\Sigma| \times \log |\Sigma|)$, and the space complexity is $O(|\Sigma|)$, where $|\Sigma|$ is the size of the character set. In this problem, $|\Sigma| = 26$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minDeletion(self, s: str, k: int) -> int:
        return sum(sorted(Counter(s).values())[:-k])
```

#### Java

```java
class Solution {
    public int minDeletion(String s, int k) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            ++cnt[c - 'a'];
        }
        Arrays.sort(cnt);
        int ans = 0;
        for (int i = 0; i + k < 26; ++i) {
            ans += cnt[i];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minDeletion(string s, int k) {
        vector<int> cnt(26);
        for (char c : s) {
            ++cnt[c - 'a'];
        }
        ranges::sort(cnt);
        int ans = 0;
        for (int i = 0; i + k < 26; ++i) {
            ans += cnt[i];
        }
        return ans;
    }
};
```

#### Go

```go
func minDeletion(s string, k int) (ans int) {
	cnt := make([]int, 26)
	for _, c := range s {
		cnt[c-'a']++
	}
	sort.Ints(cnt)
	for i := 0; i+k < len(cnt); i++ {
		ans += cnt[i]
	}
	return
}
```

#### TypeScript

```ts
function minDeletion(s: string, k: number): number {
    const cnt: number[] = Array(26).fill(0);
    for (const c of s) {
        ++cnt[c.charCodeAt(0) - 97];
    }
    cnt.sort((a, b) => a - b);
    return cnt.slice(0, 26 - k).reduce((a, b) => a + b, 0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
