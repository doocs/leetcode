---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2712.Minimum%20Cost%20to%20Make%20All%20Characters%20Equal/README_EN.md
rating: 1791
source: Weekly Contest 347 Q3
tags:
    - Greedy
    - String
    - Dynamic Programming
---

<!-- problem:start -->

# [2712. Minimum Cost to Make All Characters Equal](https://leetcode.com/problems/minimum-cost-to-make-all-characters-equal)

[中文文档](/solution/2700-2799/2712.Minimum%20Cost%20to%20Make%20All%20Characters%20Equal/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> binary string <code>s</code> of length <code>n</code> on which you can apply two types of operations:</p>

<ul>
	<li>Choose an index <code>i</code> and invert all characters from&nbsp;index <code>0</code> to index <code>i</code>&nbsp;(both inclusive), with a cost of <code>i + 1</code></li>
	<li>Choose an index <code>i</code> and invert all characters&nbsp;from&nbsp;index <code>i</code> to index <code>n - 1</code>&nbsp;(both inclusive), with a cost of <code>n - i</code></li>
</ul>

<p>Return <em>the <strong>minimum cost </strong>to make all characters of the string <strong>equal</strong></em>.</p>

<p><strong>Invert</strong> a character means&nbsp;if its value is &#39;0&#39; it becomes &#39;1&#39; and vice-versa.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;0011&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> Apply the second operation with <code>i = 2</code> to obtain <code>s = &quot;0000&quot; for a cost of 2</code>. It can be shown that 2 is the minimum cost to make all characters equal.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;010101&quot;
<strong>Output:</strong> 9
<strong>Explanation:</strong> Apply the first operation with i = 2 to obtain s = &quot;101101&quot; for a cost of 3.
Apply the first operation with i = 1 to obtain s = &quot;011101&quot; for a cost of 2. 
Apply the first operation with i = 0 to obtain s = &quot;111101&quot; for a cost of 1. 
Apply the second operation with i = 4 to obtain s = &quot;111110&quot; for a cost of 2.
Apply the second operation with i = 5 to obtain s = &quot;111111&quot; for a cost of 1. 
The total cost to make all characters equal is 9. It can be shown that 9 is the minimum cost to make all characters equal.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length == n &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy Algorithm

According to the problem description, if $s[i] \neq s[i - 1]$, an operation must be performed; otherwise, it's impossible to make all characters equal.

We can either choose to reverse all characters from $s[0..i-1]$, with a cost of $i$, or reverse all characters from $s[i..n-1]$, with a cost of $n - i$. We take the minimum of the two.

By iterating through the string $s$ and summing up the costs of all characters that need to be reversed, we can obtain the minimum cost.

The time complexity is $O(n)$, where $n$ is the length of the string $s$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumCost(self, s: str) -> int:
        ans, n = 0, len(s)
        for i in range(1, n):
            if s[i] != s[i - 1]:
                ans += min(i, n - i)
        return ans
```

#### Java

```java
class Solution {
    public long minimumCost(String s) {
        long ans = 0;
        int n = s.length();
        for (int i = 1; i < n; ++i) {
            if (s.charAt(i) != s.charAt(i - 1)) {
                ans += Math.min(i, n - i);
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
    long long minimumCost(string s) {
        long long ans = 0;
        int n = s.size();
        for (int i = 1; i < n; ++i) {
            if (s[i] != s[i - 1]) {
                ans += min(i, n - i);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minimumCost(s string) (ans int64) {
	n := len(s)
	for i := 1; i < n; i++ {
		if s[i] != s[i-1] {
			ans += int64(min(i, n-i))
		}
	}
	return
}
```

#### TypeScript

```ts
function minimumCost(s: string): number {
    let ans = 0;
    const n = s.length;
    for (let i = 1; i < n; ++i) {
        if (s[i] !== s[i - 1]) {
            ans += Math.min(i, n - i);
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn minimum_cost(s: String) -> i64 {
        let mut ans = 0;
        let n = s.len();
        let s = s.as_bytes();
        for i in 1..n {
            if s[i] != s[i - 1] {
                ans += i.min(n - i);
            }
        }
        ans as i64
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
