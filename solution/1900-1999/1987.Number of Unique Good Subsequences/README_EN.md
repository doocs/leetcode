---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1987.Number%20of%20Unique%20Good%20Subsequences/README_EN.md
rating: 2422
source: Weekly Contest 256 Q4
tags:
    - String
    - Dynamic Programming
---

<!-- problem:start -->

# [1987. Number of Unique Good Subsequences](https://leetcode.com/problems/number-of-unique-good-subsequences)

[中文文档](/solution/1900-1999/1987.Number%20of%20Unique%20Good%20Subsequences/README.md)

## Description

<!-- description:start -->

<p>You are given a binary string <code>binary</code>. A <strong>subsequence</strong> of <code>binary</code> is considered <strong>good</strong> if it is <strong>not empty</strong> and has <strong>no leading zeros</strong> (with the exception of <code>&quot;0&quot;</code>).</p>

<p>Find the number of <strong>unique good subsequences</strong> of <code>binary</code>.</p>

<ul>
	<li>For example, if <code>binary = &quot;001&quot;</code>, then all the <strong>good</strong> subsequences are <code>[&quot;0&quot;, &quot;0&quot;, &quot;1&quot;]</code>, so the <strong>unique</strong> good subsequences are <code>&quot;0&quot;</code> and <code>&quot;1&quot;</code>. Note that subsequences <code>&quot;00&quot;</code>, <code>&quot;01&quot;</code>, and <code>&quot;001&quot;</code> are not good because they have leading zeros.</li>
</ul>

<p>Return <em>the number of <strong>unique good subsequences</strong> of </em><code>binary</code>. Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>A <strong>subsequence</strong> is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> binary = &quot;001&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> The good subsequences of binary are [&quot;0&quot;, &quot;0&quot;, &quot;1&quot;].
The unique good subsequences are &quot;0&quot; and &quot;1&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> binary = &quot;11&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> The good subsequences of binary are [&quot;1&quot;, &quot;1&quot;, &quot;11&quot;].
The unique good subsequences are &quot;1&quot; and &quot;11&quot;.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> binary = &quot;101&quot;
<strong>Output:</strong> 5
<strong>Explanation:</strong> The good subsequences of binary are [&quot;1&quot;, &quot;0&quot;, &quot;1&quot;, &quot;10&quot;, &quot;11&quot;, &quot;101&quot;]. 
The unique good subsequences are &quot;0&quot;, &quot;1&quot;, &quot;10&quot;, &quot;11&quot;, and &quot;101&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= binary.length &lt;= 10<sup>5</sup></code></li>
	<li><code>binary</code> consists of only <code>&#39;0&#39;</code>s and <code>&#39;1&#39;</code>s.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

We define $f$ as the number of distinct good subsequences ending with $1$, and $g$ as the number of distinct good subsequences ending with $0$ and starting with $1$. Initially, $f = g = 0$.

For a binary string, we can traverse each bit from left to right. Suppose the current bit is $c$:

-   If $c = 0$, we can append $c$ to the $f$ and $g$ distinct good subsequences, so update $g = (g + f) \bmod (10^9 + 7)$;
-   If $c = 1$, we can append $c$ to the $f$ and $g$ distinct good subsequences, and also append $c$ alone, so update $f = (f + g + 1) \bmod (10^9 + 7)$.

If the string contains $0$, the final answer is $f + g + 1$, otherwise the answer is $f + g$.

The time complexity is $O(n)$, where $n$ is the length of the string. The space complexity is $O(1)$.

Similar problems:

-   [940. Distinct Subsequences II](https://github.com/doocs/leetcode/blob/main/solution/0900-0999/0940.Distinct%20Subsequences%20II/README_EN.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfUniqueGoodSubsequences(self, binary: str) -> int:
        f = g = 0
        ans = 0
        mod = 10**9 + 7
        for c in binary:
            if c == "0":
                g = (g + f) % mod
                ans = 1
            else:
                f = (f + g + 1) % mod
        ans = (ans + f + g) % mod
        return ans
```

#### Java

```java
class Solution {
    public int numberOfUniqueGoodSubsequences(String binary) {
        final int mod = (int) 1e9 + 7;
        int f = 0, g = 0;
        int ans = 0;
        for (int i = 0; i < binary.length(); ++i) {
            if (binary.charAt(i) == '0') {
                g = (g + f) % mod;
                ans = 1;
            } else {
                f = (f + g + 1) % mod;
            }
        }
        ans = (ans + f + g) % mod;
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numberOfUniqueGoodSubsequences(string binary) {
        const int mod = 1e9 + 7;
        int f = 0, g = 0;
        int ans = 0;
        for (char& c : binary) {
            if (c == '0') {
                g = (g + f) % mod;
                ans = 1;
            } else {
                f = (f + g + 1) % mod;
            }
        }
        ans = (ans + f + g) % mod;
        return ans;
    }
};
```

#### Go

```go
func numberOfUniqueGoodSubsequences(binary string) (ans int) {
	const mod int = 1e9 + 7
	f, g := 0, 0
	for _, c := range binary {
		if c == '0' {
			g = (g + f) % mod
			ans = 1
		} else {
			f = (f + g + 1) % mod
		}
	}
	ans = (ans + f + g) % mod
	return
}
```

#### TypeScript

```ts
function numberOfUniqueGoodSubsequences(binary: string): number {
    let [f, g] = [0, 0];
    let ans = 0;
    const mod = 1e9 + 7;
    for (const c of binary) {
        if (c === '0') {
            g = (g + f) % mod;
            ans = 1;
        } else {
            f = (f + g + 1) % mod;
        }
    }
    ans = (ans + f + g) % mod;
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
