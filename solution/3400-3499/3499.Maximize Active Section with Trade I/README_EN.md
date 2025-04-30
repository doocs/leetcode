---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3499.Maximize%20Active%20Section%20with%20Trade%20I/README_EN.md
rating: 1729
source: Biweekly Contest 153 Q2
tags:
    - String
    - Enumeration
---

<!-- problem:start -->

# [3499. Maximize Active Section with Trade I](https://leetcode.com/problems/maximize-active-section-with-trade-i)

[中文文档](/solution/3400-3499/3499.Maximize%20Active%20Section%20with%20Trade%20I/README.md)

## Description

<!-- description:start -->

<p>You are given a binary string <code>s</code> of length <code>n</code>, where:</p>

<ul>
	<li><code>&#39;1&#39;</code> represents an <strong>active</strong> section.</li>
	<li><code>&#39;0&#39;</code> represents an <strong>inactive</strong> section.</li>
</ul>

<p>You can perform <strong>at most one trade</strong> to maximize the number of active sections in <code>s</code>. In a trade, you:</p>

<ul>
	<li>Convert a contiguous block of <code>&#39;1&#39;</code>s that is surrounded by <code>&#39;0&#39;</code>s to all <code>&#39;0&#39;</code>s.</li>
	<li>Afterward, convert a contiguous block of <code>&#39;0&#39;</code>s that is surrounded by <code>&#39;1&#39;</code>s to all <code>&#39;1&#39;</code>s.</li>
</ul>

<p>Return the <strong>maximum</strong> number of active sections in <code>s</code> after making the optimal trade.</p>

<p><strong>Note:</strong> Treat <code>s</code> as if it is <strong>augmented</strong> with a <code>&#39;1&#39;</code> at both ends, forming <code>t = &#39;1&#39; + s + &#39;1&#39;</code>. The augmented <code>&#39;1&#39;</code>s <strong>do not</strong> contribute to the final count.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;01&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>Because there is no block of <code>&#39;1&#39;</code>s surrounded by <code>&#39;0&#39;</code>s, no valid trade is possible. The maximum number of active sections is 1.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;0100&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>String <code>&quot;0100&quot;</code> &rarr; Augmented to <code>&quot;101001&quot;</code>.</li>
	<li>Choose <code>&quot;0100&quot;</code>, convert <code>&quot;10<u><strong>1</strong></u>001&quot;</code> &rarr; <code>&quot;1<u><strong>0000</strong></u>1&quot;</code> &rarr; <code>&quot;1<u><strong>1111</strong></u>1&quot;</code>.</li>
	<li>The final string without augmentation is <code>&quot;1111&quot;</code>. The maximum number of active sections is 4.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;1000100&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">7</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>String <code>&quot;1000100&quot;</code> &rarr; Augmented to <code>&quot;110001001&quot;</code>.</li>
	<li>Choose <code>&quot;000100&quot;</code>, convert <code>&quot;11000<u><strong>1</strong></u>001&quot;</code> &rarr; <code>&quot;11<u><strong>000000</strong></u>1&quot;</code> &rarr; <code>&quot;11<u><strong>111111</strong></u>1&quot;</code>.</li>
	<li>The final string without augmentation is <code>&quot;1111111&quot;</code>. The maximum number of active sections is 7.</li>
</ul>
</div>

<p><strong class="example">Example 4:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;01010&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>String <code>&quot;01010&quot;</code> &rarr; Augmented to <code>&quot;1010101&quot;</code>.</li>
	<li>Choose <code>&quot;010&quot;</code>, convert <code>&quot;10<u><strong>1</strong></u>0101&quot;</code> &rarr; <code>&quot;1<u><strong>000</strong></u>101&quot;</code> &rarr; <code>&quot;1<u><strong>111</strong></u>101&quot;</code>.</li>
	<li>The final string without augmentation is <code>&quot;11110&quot;</code>. The maximum number of active sections is 4.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Two Pointers

The problem is essentially equivalent to finding the number of `'1'` characters in the string $\textit{s}$, plus the maximum number of `'0'` characters in two adjacent consecutive `'0'` segments.

Thus, we can use two pointers to traverse the string $\textit{s}$. Use a variable $\textit{mx}$ to record the maximum number of `'0'` characters in two adjacent consecutive `'0'` segments. We also need a variable $\textit{pre}$ to record the number of `'0'` characters in the previous consecutive `'0'` segment.

Each time, we count the number of consecutive identical characters $\textit{cnt}$. If the current character is `'1'`, add $\textit{cnt}$ to the answer. If the current character is `'0'`, update $\textit{mx}$ as $\textit{mx} = \max(\textit{mx}, \textit{pre} + \textit{cnt})$, and update $\textit{pre}$ to $\textit{cnt}$. Finally, add $\textit{mx}$ to the answer.

Time complexity is $O(n)$, where $n$ is the length of the string $\textit{s}$. Space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxActiveSectionsAfterTrade(self, s: str) -> int:
        n = len(s)
        ans = i = 0
        pre, mx = -inf, 0
        while i < n:
            j = i + 1
            while j < n and s[j] == s[i]:
                j += 1
            cur = j - i
            if s[i] == "1":
                ans += cur
            else:
                mx = max(mx, pre + cur)
                pre = cur
            i = j
        ans += mx
        return ans
```

#### Java

```java
class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int n = s.length();
        int ans = 0, i = 0;
        int pre = Integer.MIN_VALUE, mx = 0;

        while (i < n) {
            int j = i + 1;
            while (j < n && s.charAt(j) == s.charAt(i)) {
                j++;
            }
            int cur = j - i;
            if (s.charAt(i) == '1') {
                ans += cur;
            } else {
                mx = Math.max(mx, pre + cur);
                pre = cur;
            }
            i = j;
        }

        ans += mx;
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxActiveSectionsAfterTrade(std::string s) {
        int n = s.length();
        int ans = 0, i = 0;
        int pre = INT_MIN, mx = 0;

        while (i < n) {
            int j = i + 1;
            while (j < n && s[j] == s[i]) {
                j++;
            }
            int cur = j - i;
            if (s[i] == '1') {
                ans += cur;
            } else {
                mx = std::max(mx, pre + cur);
                pre = cur;
            }
            i = j;
        }

        ans += mx;
        return ans;
    }
};
```

#### Go

```go
func maxActiveSectionsAfterTrade(s string) (ans int) {
	n := len(s)
	pre, mx := math.MinInt, 0

	for i := 0; i < n; {
		j := i + 1
		for j < n && s[j] == s[i] {
			j++
		}
		cur := j - i
		if s[i] == '1' {
			ans += cur
		} else {
			mx = max(mx, pre+cur)
			pre = cur
		}
		i = j
	}

	ans += mx
	return
}
```

#### TypeScript

```ts
function maxActiveSectionsAfterTrade(s: string): number {
    let n = s.length;
    let [ans, mx] = [0, 0];
    let pre = Number.MIN_SAFE_INTEGER;

    for (let i = 0; i < n; ) {
        let j = i + 1;
        while (j < n && s[j] === s[i]) {
            j++;
        }
        let cur = j - i;
        if (s[i] === '1') {
            ans += cur;
        } else {
            mx = Math.max(mx, pre + cur);
            pre = cur;
        }
        i = j;
    }

    ans += mx;
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
