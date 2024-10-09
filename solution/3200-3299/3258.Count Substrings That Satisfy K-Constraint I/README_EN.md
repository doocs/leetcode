---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3258.Count%20Substrings%20That%20Satisfy%20K-Constraint%20I/README_EN.md
rating: 1258
source: Weekly Contest 411 Q1
tags:
    - String
    - Sliding Window
---

<!-- problem:start -->

# [3258. Count Substrings That Satisfy K-Constraint I](https://leetcode.com/problems/count-substrings-that-satisfy-k-constraint-i)

[中文文档](/solution/3200-3299/3258.Count%20Substrings%20That%20Satisfy%20K-Constraint%20I/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>binary</strong> string <code>s</code> and an integer <code>k</code>.</p>

<p>A <strong>binary string</strong> satisfies the <strong>k-constraint</strong> if <strong>either</strong> of the following conditions holds:</p>

<ul>
	<li>The number of <code>0</code>&#39;s in the string is at most <code>k</code>.</li>
	<li>The number of <code>1</code>&#39;s in the string is at most <code>k</code>.</li>
</ul>

<p>Return an integer denoting the number of <span data-keyword="substring-nonempty">substrings</span> of <code>s</code> that satisfy the <strong>k-constraint</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;10101&quot;, k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">12</span></p>

<p><strong>Explanation:</strong></p>

<p>Every substring of <code>s</code> except the substrings <code>&quot;1010&quot;</code>, <code>&quot;10101&quot;</code>, and <code>&quot;0101&quot;</code> satisfies the k-constraint.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;1010101&quot;, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">25</span></p>

<p><strong>Explanation:</strong></p>

<p>Every substring of <code>s</code> except the substrings with a length greater than 5 satisfies the k-constraint.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;11111&quot;, k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">15</span></p>

<p><strong>Explanation:</strong></p>

<p>All substrings of <code>s</code> satisfy the k-constraint.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 50 </code></li>
	<li><code>1 &lt;= k &lt;= s.length</code></li>
	<li><code>s[i]</code> is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sliding Window

We use two variables $\textit{cnt0}$ and $\textit{cnt1}$ to record the number of $0$s and $1$s in the current window, respectively. We use $\textit{ans}$ to record the number of substrings that satisfy the $k$ constraint, and $l$ to record the left boundary of the window.

When we move the window to the right, if the number of $0$s and $1$s in the window both exceed $k$, we need to move the window to the left until the number of $0$s and $1$s in the window are both no greater than $k$. At this point, all substrings in the window satisfy the $k$ constraint, and the number of such substrings is $r - l + 1$, where $r$ is the right boundary of the window. We add this count to $\textit{ans}$.

Finally, we return $\textit{ans}$.

The time complexity is $O(n)$, where $n$ is the length of the string $s$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countKConstraintSubstrings(self, s: str, k: int) -> int:
        cnt0 = cnt1 = 0
        ans = l = 0
        for r, c in enumerate(s):
            cnt0 += int(c) ^ 1
            cnt1 += int(c)
            while cnt0 > k and cnt1 > k:
                cnt0 -= int(s[l]) ^ 1
                cnt1 -= int(s[l])
                l += 1
            ans += r - l + 1
        return ans
```

#### Java

```java
class Solution {
    public int countKConstraintSubstrings(String s, int k) {
        int cnt0 = 0, cnt1 = 0;
        int ans = 0, l = 0;
        for (int r = 0; r < s.length(); ++r) {
            int x = s.charAt(r) - '0';
            cnt0 += x ^ 1;
            cnt1 += x;
            while (cnt0 > k && cnt1 > k) {
                int y = s.charAt(l++) - '0';
                cnt0 -= y ^ 1;
                cnt1 -= y;
            }
            ans += r - l + 1;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countKConstraintSubstrings(string s, int k) {
        int cnt0 = 0, cnt1 = 0;
        int ans = 0, l = 0;
        for (int r = 0; r < s.length(); ++r) {
            int x = s[r] - '0';
            cnt0 += x ^ 1;
            cnt1 += x;
            while (cnt0 > k && cnt1 > k) {
                int y = s[l++] - '0';
                cnt0 -= y ^ 1;
                cnt1 -= y;
            }
            ans += r - l + 1;
        }
        return ans;
    }
};
```

#### Go

```go
func countKConstraintSubstrings(s string, k int) (ans int) {
	cnt0, cnt1, l := 0, 0, 0
	for r, c := range s {
		x := int(c - '0')
		cnt0 += x ^ 1
		cnt1 += x
		for cnt0 > k && cnt1 > k {
			y := int(s[l] - '0')
			cnt0 -= y ^ 1
			cnt1 -= y
			l++
		}
		ans += r - l + 1
	}
	return
}
```

#### TypeScript

```ts
function countKConstraintSubstrings(s: string, k: number): number {
    let [cnt0, cnt1, ans, l] = [0, 0, 0, 0];
    for (let r = 0; r < s.length; ++r) {
        const x = s[r] === '1' ? 1 : 0;
        cnt0 += x ^ 1;
        cnt1 += x;
        while (cnt0 > k && cnt1 > k) {
            const y = s[l++] === '1' ? 1 : 0;
            cnt0 -= y ^ 1;
            cnt1 -= y;
        }
        ans += r - l + 1;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
