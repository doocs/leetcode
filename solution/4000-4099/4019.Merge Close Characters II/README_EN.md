---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4019.Merge%20Close%20Characters%20II/README_EN.md
tags:
    - Hash Table
    - String
---

<!-- problem:start -->

# [4019. Merge Close Characters II 🔒](https://leetcode.com/problems/merge-close-characters-ii)

[中文文档](/solution/4000-4099/4019.Merge%20Close%20Characters%20II/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> consisting of lowercase English letters and an integer <code>k</code>.</p>

<p>Two equal characters <code>s[i]</code> and <code>s[j]</code>, where <code>0 &lt;= i &lt; j &lt; s.length</code>, are considered <strong>close</strong> if <code>j - i &lt;= k</code>. All indices refer to the <strong>current</strong> string.</p>

<p>Repeatedly perform the following operation until no close pair remains:</p>

<ul>
	<li>Among all close pairs <code>(i, j)</code>, choose the pair with the smallest <code>i</code>. If multiple pairs have the same <code>i</code>, choose the one with the smallest <code>j</code>.</li>
	<li>Merge the right character into the left character by removing <code>s[j]</code> from <code>s</code>. The character <code>s[i]</code> remains unchanged, and the remaining characters are reindexed.</li>
</ul>

<p>Return the resulting string after performing all possible merges.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abca&quot;, k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;abc&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The characters <code>&#39;a&#39;</code> at indices 0 and 3 are close because <code>3 - 0 = 3 &lt;= k</code>.</li>
	<li>Remove the right <code>&#39;a&#39;</code>, resulting in <code>s = &quot;abc&quot;</code>.</li>
	<li>No close pair remains, so no further merges are performed.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;aabca&quot;, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;abca&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The characters <code>&#39;a&#39;</code> at indices 0 and 1 are close because <code>1 - 0 = 1 &lt;= k</code>.</li>
	<li>Remove the right <code>&#39;a&#39;</code>, resulting in <code>s = &quot;abca&quot;</code>.</li>
	<li>The remaining <code>&#39;a&#39;</code> characters are at indices 0 and 3. Since <code>3 - 0 = 3 &gt; k</code>, no further merges are performed.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;yybyzybz&quot;, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;ybzybz&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The characters <code>&#39;y&#39;</code> at indices 0 and 1 are close because <code>1 - 0 = 1 &lt;= k</code>. This pair has the smallest left index among all close pairs.</li>
	<li>Remove the right <code>&#39;y&#39;</code>, resulting in <code>s = &quot;ybyzybz&quot;</code>.</li>
	<li>The characters <code>&#39;y&#39;</code> at indices 0 and 2 are now close because <code>2 - 0 = 2 &lt;= k</code>.</li>
	<li>Remove the right <code>&#39;y&#39;</code>, resulting in <code>s = &quot;ybzybz&quot;</code>.</li>
	<li>No close pair remains, so no further merges are performed.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 5 * 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= s.length</code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

We use a hash table $\textit{last}$ to record the last occurrence position of each character in the answer string. We iterate over each character in $s$ from left to right. Let $\textit{cur}$ be the current length of the answer. If the character has appeared before and the difference between $\textit{cur}$ and its last occurrence is at most $k$, we skip it; otherwise, we append the character to the answer and update its position in the hash table.

Each merge always removes the right character, so the positions in the answer are exactly the indices in the current string. This greedy process is equivalent to repeatedly performing the required merge operations.

The time complexity is $O(n)$, and the space complexity is $O(|\Sigma|)$, where $n$ is the length of the string, and $|\Sigma|$ is the size of the character set. In this problem, the character set consists of lowercase English letters, so $|\Sigma|$ is a constant.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def mergeCharacters(self, s: str, k: int) -> str:
        last = {}
        ans = []
        for c in s:
            cur = len(ans)
            if c in last and cur - last[c] <= k:
                continue
            ans.append(c)
            last[c] = cur
        return ''.join(ans)
```

#### Java

```java
class Solution {
    public String mergeCharacters(String s, int k) {
        Map<Character, Integer> last = new HashMap<>();
        StringBuilder ans = new StringBuilder();
        for (char c : s.toCharArray()) {
            int cur = ans.length();
            if (last.containsKey(c) && cur - last.get(c) <= k) {
                continue;
            }
            ans.append(c);
            last.put(c, cur);
        }
        return ans.toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string mergeCharacters(string s, int k) {
        unordered_map<char, int> last;
        string ans;
        for (char c : s) {
            int cur = ans.size();
            if (last.count(c) && cur - last[c] <= k) {
                continue;
            }
            ans += c;
            last[c] = cur;
        }
        return ans;
    }
};
```

#### Go

```go
func mergeCharacters(s string, k int) string {
	last := make(map[byte]int)
	var ans []byte
	for i := 0; i < len(s); i++ {
		c := s[i]
		cur := len(ans)
		if lastIdx, ok := last[c]; ok && cur-lastIdx <= k {
			continue
		}
		ans = append(ans, c)
		last[c] = cur
	}
	return string(ans)
}
```

#### TypeScript

```ts
function mergeCharacters(s: string, k: number): string {
    const last = new Map<string, number>();
    const ans: string[] = [];
    for (const c of s) {
        const cur = ans.length;
        if (last.has(c) && cur - last.get(c)! <= k) {
            continue;
        }
        ans.push(c);
        last.set(c, cur);
    }
    return ans.join('');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
