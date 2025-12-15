---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3760.Maximum%20Substrings%20With%20Distinct%20Start/README_EN.md
rating: 1364
source: Weekly Contest 478 Q2
tags:
    - Hash Table
    - String
---

<!-- problem:start -->

# [3760. Maximum Substrings With Distinct Start](https://leetcode.com/problems/maximum-substrings-with-distinct-start)

[中文文档](/solution/3700-3799/3760.Maximum%20Substrings%20With%20Distinct%20Start/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> consisting of lowercase English letters.</p>

<p>Return an integer denoting the <strong>maximum</strong> number of <span data-keyword="substring-nonempty">substrings</span> you can split <code>s</code> into such that each <strong>substring</strong> starts with a <strong>distinct</strong> character (i.e., no two substrings start with the same character).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abab&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Split <code>&quot;abab&quot;</code> into <code>&quot;a&quot;</code> and <code>&quot;bab&quot;</code>.</li>
	<li>Each substring starts with a distinct character i.e <code>&#39;a&#39;</code> and <code>&#39;b&#39;</code>. Thus, the answer is 2.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abcd&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Split <code>&quot;abcd&quot;</code> into <code>&quot;a&quot;</code>, <code>&quot;b&quot;</code>, <code>&quot;c&quot;</code>, and <code>&quot;d&quot;</code>.</li>
	<li>Each substring starts with a distinct character. Thus, the answer is 4.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;aaaa&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>All characters in <code>&quot;aaaa&quot;</code> are <code>&#39;a&#39;</code>.</li>
	<li>Only one substring can start with <code>&#39;a&#39;</code>. Thus, the answer is 1.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxDistinct(self, s: str) -> int:
        return len(set(s))
```

#### Java

```java
class Solution {
    public int maxDistinct(String s) {
        int ans = 0;
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            if (++cnt[s.charAt(i) - 'a'] == 1) {
                ++ans;
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
    int maxDistinct(string s) {
        int ans = 0;
        int cnt[26]{};
        for (char c : s) {
            if (++cnt[c - 'a'] == 1) {
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxDistinct(s string) (ans int) {
	cnt := [26]int{}
	for _, c := range s {
		cnt[c-'a']++
		if cnt[c-'a'] == 1 {
			ans++
		}
	}
	return
}
```

#### TypeScript

```ts
function maxDistinct(s: string): number {
    let ans = 0;
    const cnt: number[] = Array(26).fill(0);
    for (const ch of s) {
        const idx = ch.charCodeAt(0) - 97;
        if (++cnt[idx] === 1) {
            ++ans;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
