---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1513.Number%20of%20Substrings%20With%20Only%201s/README_EN.md
rating: 1351
source: Weekly Contest 197 Q2
tags:
    - Math
    - String
---

<!-- problem:start -->

# [1513. Number of Substrings With Only 1s](https://leetcode.com/problems/number-of-substrings-with-only-1s)

[中文文档](/solution/1500-1599/1513.Number%20of%20Substrings%20With%20Only%201s/README.md)

## Description

<!-- description:start -->

<p>Given a binary string <code>s</code>, return <em>the number of substrings with all characters</em> <code>1</code><em>&#39;s</em>. Since the answer may be too large, return it modulo <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;0110111&quot;
<strong>Output:</strong> 9
<strong>Explanation:</strong> There are 9 substring in total with only 1&#39;s characters.
&quot;1&quot; -&gt; 5 times.
&quot;11&quot; -&gt; 3 times.
&quot;111&quot; -&gt; 1 time.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;101&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> Substring &quot;1&quot; is shown 2 times in s.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;111111&quot;
<strong>Output:</strong> 21
<strong>Explanation:</strong> Each substring contains only 1&#39;s characters.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Traversal and Counting

We traverse the string $s$, using a variable $\textit{cur}$ to record the current count of consecutive 1s, and a variable $\textit{ans}$ to record the answer. When we traverse to character $s[i]$, if $s[i] = 0$, then set $\textit{cur}$ to 0; otherwise, increment $\textit{cur}$ by 1, then add $\textit{cur}$ to $\textit{ans}$, and take modulo $10^9 + 7$.

After the traversal is complete, return $\textit{ans}$.

The time complexity is $O(n)$, where $n$ is the length of the string $s$. The space complexity is $O(1)$.

Similar problems:

- [413. Arithmetic Slices](https://github.com/doocs/leetcode/blob/main/solution/0400-0499/0413.Arithmetic%20Slices/README_EN.md)
- [2348. Number of Zero-Filled Subarrays](https://github.com/doocs/leetcode/blob/main/solution/2300-2399/2348.Number%20of%20Zero-Filled%20Subarrays/README_EN.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numSub(self, s: str) -> int:
        mod = 10**9 + 7
        ans = cur = 0
        for c in s:
            if c == "0":
                cur = 0
            else:
                cur += 1
                ans = (ans + cur) % mod
        return ans
```

#### Java

```java
class Solution {
    public int numSub(String s) {
        final int mod = 1_000_000_007;
        int ans = 0, cur = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '0') {
                cur = 0;
            } else {
                cur++;
                ans = (ans + cur) % mod;
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
    int numSub(string s) {
        const int mod = 1e9 + 7;
        int ans = 0, cur = 0;
        for (char c : s) {
            if (c == '0') {
                cur = 0;
            } else {
                cur++;
                ans = (ans + cur) % mod;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func numSub(s string) (ans int) {
	const mod int = 1e9 + 7
	cur := 0
	for _, c := range s {
		if c == '0' {
			cur = 0
		} else {
			cur++
			ans = (ans + cur) % mod
		}
	}
	return
}
```

#### TypeScript

```ts
function numSub(s: string): number {
    const mod = 1_000_000_007;
    let [ans, cur] = [0, 0];
    for (const c of s) {
        if (c === '0') {
            cur = 0;
        } else {
            cur++;
            ans = (ans + cur) % mod;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn num_sub(s: String) -> i32 {
        const MOD: i32 = 1_000_000_007;
        let mut ans: i32 = 0;
        let mut cur: i32 = 0;
        for c in s.chars() {
            if c == '0' {
                cur = 0;
            } else {
                cur += 1;
                ans = (ans + cur) % MOD;
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
