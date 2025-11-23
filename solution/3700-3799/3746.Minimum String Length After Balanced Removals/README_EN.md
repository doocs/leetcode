---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3746.Minimum%20String%20Length%20After%20Balanced%20Removals/README_EN.md
rating: 1326
source: Weekly Contest 476 Q2
---

<!-- problem:start -->

# [3746. Minimum String Length After Balanced Removals](https://leetcode.com/problems/minimum-string-length-after-balanced-removals)

[中文文档](/solution/3700-3799/3746.Minimum%20String%20Length%20After%20Balanced%20Removals/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> consisting only of the characters <code>&#39;a&#39;</code> and <code>&#39;b&#39;</code>.</p>

<p>You are allowed to repeatedly remove <strong>any <span data-keyword="substring-nonempty">substring</span></strong> where the number of <code>&#39;a&#39;</code> characters is equal to the number of <code>&#39;b&#39;</code> characters. After each removal, the remaining parts of the string are concatenated together without gaps.</p>

<p>Return an integer denoting the <strong>minimum possible length</strong> of the string after performing any number of such operations.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = <code>&quot;aabbab&quot;</code></span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>The substring <code>&quot;aabbab&quot;</code> has three <code>&#39;a&#39;</code> and three <code>&#39;b&#39;</code>. Since their counts are equal, we can remove the entire string directly. The minimum length is 0.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = <code>&quot;aaaa&quot;</code></span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>Every substring of <code>&quot;aaaa&quot;</code> contains only <code>&#39;a&#39;</code> characters. No substring can be removed as a result, so the minimum length remains 4.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = <code>&quot;aaabb&quot;</code></span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>First, remove the substring <code>&quot;ab&quot;</code>, leaving <code>&quot;aab&quot;</code>. Next, remove the new substring <code>&quot;ab&quot;</code>, leaving <code>&quot;a&quot;</code>. No further removals are possible, so the minimum length is 1.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> is either <code>&#39;a&#39;</code> or <code>&#39;b&#39;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting

According to the problem description, as long as adjacent characters are different, we can remove them. Therefore, the final remaining string will only contain the same character, either all 'a' or all 'b'. So we only need to count the number of 'a' and 'b' in the string, and the final minimum length is the absolute difference between their counts.

The time complexity is $O(n)$, where $n$ is the length of the string. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minLengthAfterRemovals(self, s: str) -> int:
        a = s.count("a")
        b = len(s) - a
        return abs(a - b)
```

#### Java

```java
class Solution {
    public int minLengthAfterRemovals(String s) {
        int n = s.length();
        int a = 0;
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == 'a') {
                ++a;
            }
        }
        int b = n - a;
        return Math.abs(a - b);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minLengthAfterRemovals(string s) {
        int a = 0;
        for (char c : s) {
            if (c == 'a') {
                ++a;
            }
        }
        int b = s.size() - a;
        return abs(a - b);
    }
};
```

#### Go

```go
func minLengthAfterRemovals(s string) int {
	a := strings.Count(s, "a")
	b := len(s) - a
	return abs(a - b)
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function minLengthAfterRemovals(s: string): number {
    let a = 0;
    for (const c of s) {
        if (c === 'a') {
            ++a;
        }
    }
    const b = s.length - a;
    return Math.abs(a - b);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
