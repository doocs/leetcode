---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3884.First%20Matching%20Character%20From%20Both%20Ends/README_EN.md
rating: 1161
source: Weekly Contest 495 Q1
tags:
    - Two Pointers
    - String
---

<!-- problem:start -->

# [3884. First Matching Character From Both Ends](https://leetcode.com/problems/first-matching-character-from-both-ends)

[中文文档](/solution/3800-3899/3884.First%20Matching%20Character%20From%20Both%20Ends/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> of length <code>n</code> consisting of lowercase English letters.</p>

<p>Return the smallest index <code>i</code> such that <code>s[i] == s[n - i - 1]</code>.</p>

<p>If no such index exists, return -1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abcacbd&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>At index <code>i = 1</code>, <code>s[1]</code> and <code>s[5]</code> are both <code>&#39;b&#39;</code>.</p>

<p>No smaller index satisfies the condition, so the answer is 1.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abc&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>​​​​​​​At index <code>i = 1</code>, the two compared positions coincide, so both characters are <code>&#39;b&#39;</code>.</p>

<p>No smaller index satisfies the condition, so the answer is 1.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abcdab&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>​​​​​​​For every index <code>i</code>, the characters at positions <code>i</code> and <code>n - i - 1</code> are different.</p>

<p>Therefore, no valid index exists, so the answer is -1.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == s.length &lt;= 100</code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We iterate over the first half of the string $s$. For each index $i$, we check whether the characters at position $i$ and position $n - i - 1$ are equal. If they are, we return index $i$. If no such index is found after the iteration, we return -1.

The time complexity is $O(n)$, where $n$ is the length of the string $s$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def firstMatchingIndex(self, s: str) -> int:
        for i in range(len(s) // 2 + 1):
            if s[i] == s[-i - 1]:
                return i
        return -1
```

#### Java

```java
class Solution {
    public int firstMatchingIndex(String s) {
        int n = s.length();
        for (int i = 0; i < n / 2 + 1; ++i) {
            if (s.charAt(i) == s.charAt(n - i - 1)) {
                return i;
            }
        }
        return -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int firstMatchingIndex(string s) {
        int n = s.size();
        for (int i = 0; i < n / 2 + 1; ++i) {
            if (s[i] == s[n - i - 1]) {
                return i;
            }
        }
        return -1;
    }
};
```

#### Go

```go
func firstMatchingIndex(s string) int {
	n := len(s)
	for i := 0; i < n/2+1; i++ {
		if s[i] == s[n-i-1] {
			return i
		}
	}
	return -1
}
```

#### TypeScript

```ts
function firstMatchingIndex(s: string): number {
    const n = s.length;
    for (let i = 0; i < Math.floor(n / 2) + 1; i++) {
        if (s[i] === s[n - i - 1]) {
            return i;
        }
    }
    return -1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
