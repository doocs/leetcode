---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3662.Filter%20Characters%20by%20Frequency/README_EN.md
tags:
    - Hash Table
    - String
    - Counting
---

<!-- problem:start -->

# [3662. Filter Characters by Frequency 🔒](https://leetcode.com/problems/filter-characters-by-frequency)

[中文文档](/solution/3600-3699/3662.Filter%20Characters%20by%20Frequency/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> consisting of lowercase English letters and an integer <code>k</code>.</p>

<p>Your task is to construct a new string that contains only those characters from <code>s</code> which appear <strong>fewer</strong> than <code>k</code> times in the entire string. The order of characters in the new string must be the <strong>same</strong> as their <strong>order</strong> in <code>s</code>.</p>

<p>Return the resulting string. If no characters qualify, return an empty string.</p>

<p>Note: <strong>Every occurrence</strong> of a character that occurs fewer than <code>k</code> times is kept.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;aadbbcccca&quot;, k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;dbb&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>Character frequencies in <code>s</code>:</p>

<ul>
	<li><code>&#39;a&#39;</code> appears 3 times</li>
	<li><code>&#39;d&#39;</code> appears 1 time</li>
	<li><code>&#39;b&#39;</code> appears 2 times</li>
	<li><code>&#39;c&#39;</code> appears 4 times</li>
</ul>

<p>Only <code>&#39;d&#39;</code> and <code>&#39;b&#39;</code> appear fewer than 3 times. Preserving their order, the result is <code>&quot;dbb&quot;</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;xyz&quot;, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;xyz&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>All characters (<code>&#39;x&#39;</code>, <code>&#39;y&#39;</code>, <code>&#39;z&#39;</code>) appear exactly once, which is fewer than 2. Thus the whole string is returned.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
	<li><code>1 &lt;= k &lt;= s.length</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting

First, we iterate through the string $s$ and count the frequency of each character, storing the results in a hash table or array $\textit{cnt}$.

Then, we iterate through the string $s$ again, adding characters whose frequency is less than $k$ to the result string. Finally, we return the result string.

The time complexity is $O(n)$, where $n$ is the length of the string $s$. The space complexity is $O(|\Sigma|)$, where $\Sigma$ is the size of the character set.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def filterCharacters(self, s: str, k: int) -> str:
        cnt = Counter(s)
        ans = []
        for c in s:
            if cnt[c] < k:
                ans.append(c)
        return "".join(ans)
```

#### Java

```java
class Solution {
    public String filterCharacters(String s, int k) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            ++cnt[c - 'a'];
        }
        StringBuilder ans = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (cnt[c - 'a'] < k) {
                ans.append(c);
            }
        }
        return ans.toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string filterCharacters(string s, int k) {
        int cnt[26]{};
        for (char c : s) {
            ++cnt[c - 'a'];
        }
        string ans;
        for (char c : s) {
            if (cnt[c - 'a'] < k) {
                ans.push_back(c);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func filterCharacters(s string, k int) string {
	cnt := [26]int{}
	for _, c := range s {
		cnt[c-'a']++
	}
	ans := []rune{}
	for _, c := range s {
		if cnt[c-'a'] < k {
			ans = append(ans, c)
		}
	}
	return string(ans)
}
```

#### TypeScript

```ts
function filterCharacters(s: string, k: number): string {
    const cnt: Record<string, number> = {};
    for (const c of s) {
        cnt[c] = (cnt[c] || 0) + 1;
    }
    const ans: string[] = [];
    for (const c of s) {
        if (cnt[c] < k) {
            ans.push(c);
        }
    }
    return ans.join('');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
